package com.lawu.eshop.user.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.user.constants.RegOriginEnum;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.eshop.user.param.WxLoginMemberParam;
import com.lawu.eshop.user.srv.bo.WxLoginMemberBO;
import com.lawu.eshop.user.srv.converter.WxLoginMemberConverter;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MemberDOExample;
import com.lawu.eshop.user.srv.domain.WxLoginMemberDO;
import com.lawu.eshop.user.srv.domain.WxLoginMemberDOExample;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.WxLoginMemberDOMapper;
import com.lawu.eshop.user.srv.service.MemberService;
import com.lawu.eshop.user.srv.service.WxLoginMemberService;

@Service
public class WxLoginMemberServiceImpl implements WxLoginMemberService{

	@Autowired
	private WxLoginMemberDOMapper wxLoginMemberDOMapper;
	
	@Autowired
	private MemberDOMapper memberDOMapper;
	
	@Autowired
	private MemberService memberService;
	
	
	@Override
	public WxLoginMemberBO wxLogin(String openId) {
		WxLoginMemberDOExample example = new WxLoginMemberDOExample();
		example.createCriteria().andOpenidEqualTo(openId);
		List<WxLoginMemberDO> list = wxLoginMemberDOMapper.selectByExample(example);
		
		if(list.isEmpty() || StringUtils.isEmpty(list.get(0).getUserNum())){
			return null;
		}
		
		//获取登录信息
		MemberDOExample memberDOExample = new MemberDOExample();
		memberDOExample.createCriteria().andNumEqualTo(list.get(0).getUserNum());
		List<MemberDO> memberList = memberDOMapper.selectByExample(memberDOExample);
		
		return WxLoginMemberConverter.wxLoginMemberConverterBO(memberList.get(0));
	}

	@Override
	public void wxMemberBind(String openId, String account,String imgUrl) throws DataNotExistException{
		
		WxLoginMemberDOExample example = new WxLoginMemberDOExample();
		example.createCriteria().andOpenidEqualTo(openId);
		List<WxLoginMemberDO> list = wxLoginMemberDOMapper.selectByExample(example);
		
		if(list.isEmpty()){
			throw new DataNotExistException("当前没有通过微信授权");
		}
		
		MemberDOExample memberDOExample = new MemberDOExample();
		memberDOExample.createCriteria().andAccountEqualTo(account);
		
		int count = memberDOMapper.countByExample(memberDOExample);
		
		//用户没有注册，默认注册
		if(count == 0 ){
			RegisterRealParam registerRealParam = new RegisterRealParam();
			registerRealParam.setAccount(account);
			registerRealParam.setRegOrigin(RegOriginEnum.WEIXIN.getName());
			memberService.register(registerRealParam);
			
			MemberDO memberDO = new MemberDO();
			memberDO.setHeadimg(imgUrl);
			memberDO.setNickname(list.get(0).getNickname());
			
			memberDOMapper.updateByExampleSelective(memberDO, memberDOExample);
		}
		
		List<MemberDO> memberList = memberDOMapper.selectByExample(memberDOExample);
		
		WxLoginMemberDO record =  new WxLoginMemberDO();
		record.setId(list.get(0).getId());
		record.setUserNum(memberList.get(0).getNum());
		record.setGmtModified(new Date());
		wxLoginMemberDOMapper.updateByPrimaryKeySelective(record);
		
		
	}

	
	@Override
	public String getHeadImg(String openId) {
		WxLoginMemberDOExample example = new WxLoginMemberDOExample();
		example.createCriteria().andOpenidEqualTo(openId);
		List<WxLoginMemberDO> list = wxLoginMemberDOMapper.selectByExample(example);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0).getHeadimgurl();
	}
	
	
	@Override
	public void wxLoginMemberSave(WxLoginMemberParam param) {
		
		WxLoginMemberDOExample example = new WxLoginMemberDOExample();
		example.createCriteria().andOpenidEqualTo(param.getOpenid());
		
		WxLoginMemberDO weixinUserDO = new WxLoginMemberDO();
		
		weixinUserDO.setCity(param.getCity());
		weixinUserDO.setCountry(param.getCountry());
		if (param.getGroupid() != null) {
			weixinUserDO.setGroupid(Long.parseLong(param.getGroupid().toString()));
		}
		weixinUserDO.setHeadimgurl(param.getHeadimgurl());
		weixinUserDO.setLanguage(param.getLanguage());
		weixinUserDO.setNickname(param.getNickname());
		weixinUserDO.setProvince(param.getProvince());
		weixinUserDO.setRemark(param.getRemark());
		weixinUserDO.setSex(param.getSex());
		if (param.getSubscribe() != null && param.getSubscribe()) {
			weixinUserDO.setSubscribe(1);
		} else {
			weixinUserDO.setSubscribe(0);
		}
		
		weixinUserDO.setSubscribeTime(param.getSubscribeTime());
		
		if (param.getTagidList()!=null && param.getTagidList().length > 0) {
			weixinUserDO.setTagidList(JSONObject.toJSONString(param.getTagidList()));
		}
		weixinUserDO.setUnionid(param.getUnionid());
		
		if (wxLoginMemberDOMapper.countByExample(example) > 0) {
			
			wxLoginMemberDOMapper.updateByExampleSelective(weixinUserDO, example);
			
			return;
		}

		weixinUserDO.setOpenid(param.getOpenid());
		weixinUserDO.setGmtCreate(new Date());
		
		wxLoginMemberDOMapper.insertSelective(weixinUserDO);
		
	}

}
