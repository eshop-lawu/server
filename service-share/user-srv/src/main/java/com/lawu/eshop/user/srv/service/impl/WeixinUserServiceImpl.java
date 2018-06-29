package com.lawu.eshop.user.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.user.constants.WxBindTypeEnum;
import com.lawu.eshop.user.param.WeixinUserBindParam;
import com.lawu.eshop.user.param.WeixinUserParam;
import com.lawu.eshop.user.srv.bo.WeixinUserBindBO;
import com.lawu.eshop.user.srv.bo.WeixinUserInfoBO;
import com.lawu.eshop.user.srv.converter.WeixinUserConverter;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MemberDOExample;
import com.lawu.eshop.user.srv.domain.WeixinUserDO;
import com.lawu.eshop.user.srv.domain.WeixinUserDOExample;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.WeixinUserDOMapper;
import com.lawu.eshop.user.srv.mapper.extend.WeixinUserDOMapperExtend;
import com.lawu.eshop.user.srv.service.WeixinUserService;
import com.lawu.utils.PwdUtil;

@Service
public class WeixinUserServiceImpl implements WeixinUserService {
	
	@Autowired
	private WeixinUserDOMapper weixinUserDOMapper;
	
	@Autowired
	private MemberDOMapper memberDOMapper;
	
	
	@Autowired
	private WeixinUserDOMapperExtend weixinUserDOMapperExtend;
	
	
	@Autowired
	@Qualifier("wxOpenIdUpdateTransactionMainServiceImpl")
	private TransactionMainService<Reply> transactionMainService;

	@Override
	public WeixinUserInfoBO getWeixinUserInfo(String userNum) {
		WeixinUserDOExample example = new WeixinUserDOExample();
		example.createCriteria().andUserNumEqualTo(userNum);
		List<WeixinUserDO> list = weixinUserDOMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		WeixinUserInfoBO weixinUserInfoBO = WeixinUserConverter.WeixinUserConverterBO(list.get(0));
		MemberDOExample memberDOExample = new MemberDOExample();
		memberDOExample.createCriteria().andNumEqualTo(userNum);
		List<MemberDO> member = memberDOMapper.selectByExample(memberDOExample);
		weixinUserInfoBO.setAccount(member.get(0).getAccount());
		weixinUserInfoBO.setHeadimg(member.get(0).getHeadimg());
		return weixinUserInfoBO;
	}

	@Override
	public WeixinUserBindBO getWeixinUserIsBind(String openId) {
		WeixinUserDOExample example = new WeixinUserDOExample();
		example.createCriteria().andOpenidEqualTo(openId);
		List<WeixinUserDO> list = weixinUserDOMapper.selectByExample(example);
		
		WeixinUserBindBO bind = new WeixinUserBindBO();
		if (list.isEmpty()) {
			bind.setIsBind(false);
		}else{
			if( !StringUtils.isEmpty(list.get(0).getUserNum()) && list.get(0).getIsSubscribeMp()){
				bind.setIsBind(true);
			}else{
				bind.setIsBind(false);
				
			}
		}
		return bind;
	}

	@Override
	public Boolean weixinUserBind(WeixinUserBindParam bindParam) {
		
	    MemberDOExample example = new MemberDOExample();
        example.createCriteria().andAccountEqualTo(bindParam.getAccount());
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        if(memberDOS.isEmpty()){
        	return true;
        }else{
        	
        	if(!PwdUtil.verify(bindParam.getPwd(), memberDOS.get(0).getPwd())){
        		return true;
        	}
        }
        
        WeixinUserDOExample wxExample = new WeixinUserDOExample();
        wxExample.createCriteria().andUserNumEqualTo(memberDOS.get(0).getNum());
        List<WeixinUserDO> list = weixinUserDOMapper.selectByExample(wxExample);
        //之前存在绑定过微信，直接取消绑定
        if(!list.isEmpty()){
        	 weixinUserDOMapperExtend.updateNotBind(list.get(0).getId());
        	
        	
             
        }
        
        WeixinUserDO weixinUserDO = new WeixinUserDO();
        weixinUserDO.setGmtModified(new Date());
        weixinUserDO.setUserNum(memberDOS.get(0).getNum());
        
        WeixinUserDOExample wuExample = new WeixinUserDOExample();
        wuExample.createCriteria().andOpenidEqualTo(bindParam.getOpenId());
        weixinUserDOMapper.updateByExampleSelective(weixinUserDO, wuExample);
        
        if(!list.isEmpty()){
			// 更新活动参与openId
			List<WeixinUserDO> wxlist = weixinUserDOMapper.selectByExample(wuExample);
			transactionMainService.sendNotice(wxlist.get(0).getId());
       }
        return false;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveWeixinUser(WeixinUserParam param) {
		WeixinUserDOExample example = new WeixinUserDOExample();
		example.createCriteria().andOpenidEqualTo(param.getOpenid());
		WeixinUserDO weixinUserDO = new WeixinUserDO();
		if(param.getTypeEnum() == WxBindTypeEnum.H5_BIND){
			weixinUserDO.setIsSubscribeMp(true);
		}else{
			weixinUserDO.setIsSubscribeMp(false);
		}
		
		if (weixinUserDOMapper.countByExample(example) > 0) {
		
			weixinUserDO.setNickname(param.getNickname());
			
			weixinUserDOMapper.updateByExampleSelective(weixinUserDO, example);
			return;
		}
		
		weixinUserDO.setOpenid(param.getOpenid());
		weixinUserDO.setNickname(param.getNickname());
		weixinUserDO.setSex(param.getSex());
		weixinUserDO.setCity(param.getCity());
		weixinUserDO.setProvince(param.getProvince());
		weixinUserDO.setCountry(param.getCountry());
		weixinUserDO.setHeadimgurl(param.getHeadimgurl());
		weixinUserDO.setSubscribeTime(param.getSubscribeTime());
		weixinUserDO.setLanguage(param.getLanguage());
		weixinUserDO.setUnionid(param.getUnionid());
		weixinUserDO.setRemark(param.getRemark());
		weixinUserDO.setGroupid(param.getGroupid());
		weixinUserDO.setTagidList(param.getTagidList());
		if (weixinUserDO.getSubscribe() != null) {
			weixinUserDO.setSubscribe(param.getSubscribe() ? 1 : 0);
		}
		weixinUserDO.setGmtCreate(new Date());
		weixinUserDOMapper.insertSelective(weixinUserDO);
	}

	@Override
	public Boolean weixinUserIsAttend(String openId) {
		WeixinUserDOExample example = new WeixinUserDOExample();
		example.createCriteria().andOpenidEqualTo(openId);
		List<WeixinUserDO> list = weixinUserDOMapper.selectByExample(example);
	     return list.size() == 0 ?false :true;
		
	}

	@Override
	public Boolean getwxUserIsBindByAccount(String account) {
		
		MemberDOExample example = new MemberDOExample();
        example.createCriteria().andAccountEqualTo(account);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        if(memberDOS.isEmpty()){
        	return false;
        }
        WeixinUserDOExample wxexample = new WeixinUserDOExample();
    	wxexample.createCriteria().andUserNumEqualTo(memberDOS.get(0).getNum());
		int count = weixinUserDOMapper.countByExample(wxexample);
		return count > 0 ;
	}

}
