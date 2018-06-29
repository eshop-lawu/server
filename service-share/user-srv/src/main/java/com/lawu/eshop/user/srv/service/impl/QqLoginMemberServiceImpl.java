package com.lawu.eshop.user.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.user.constants.RegOriginEnum;
import com.lawu.eshop.user.param.QqLoginMemberParam;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.eshop.user.srv.bo.QqLoginMemberBO;
import com.lawu.eshop.user.srv.converter.QqLoginMemberConverter;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MemberDOExample;
import com.lawu.eshop.user.srv.domain.QqLoginMemberDO;
import com.lawu.eshop.user.srv.domain.QqLoginMemberDOExample;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.QqLoginMemberDOMapper;
import com.lawu.eshop.user.srv.service.MemberService;
import com.lawu.eshop.user.srv.service.QqLoginMemberService;

@Service
public class QqLoginMemberServiceImpl implements QqLoginMemberService {
	
	@Autowired
	private QqLoginMemberDOMapper qqLoginMemberDOMapper;
	
	@Autowired
	private MemberDOMapper memberDOMapper;
	
	@Autowired
	private MemberService memberService;
	

	@Override
	public void QqLoginMemberSave(QqLoginMemberParam param) {
		QqLoginMemberDOExample example = new QqLoginMemberDOExample();
		example.createCriteria().andOpenidEqualTo(param.getOpenId());
		if (qqLoginMemberDOMapper.countByExample(example) > 0) {
			return;
		}
		
		QqLoginMemberDO record = new QqLoginMemberDO();
		
		record.setFigureurl(param.getFigureurl());
		record.setGender(param.getGender());
		record.setGmtCreate(new Date());
		record.setMsg(param.getMsg());
		record.setNickname(param.getNickname());
		record.setOpenid(param.getOpenId());
		qqLoginMemberDOMapper.insertSelective(record);
		
	}

	@Override
	public QqLoginMemberBO qqLogin(String openId) {
		QqLoginMemberDOExample example = new QqLoginMemberDOExample();
		example.createCriteria().andOpenidEqualTo(openId);
		List<QqLoginMemberDO> list = qqLoginMemberDOMapper.selectByExample(example);
		
		if(list.isEmpty() || StringUtils.isEmpty(list.get(0).getUserNum())){
			return null;
		}
		
		//获取登录信息
		MemberDOExample memberDOExample = new MemberDOExample();
		memberDOExample.createCriteria().andNumEqualTo(list.get(0).getUserNum());
		List<MemberDO> memberList = memberDOMapper.selectByExample(memberDOExample);
		
		return QqLoginMemberConverter.qqLoginMemberConverterBO(memberList.get(0));
	}

	@Override
	public void qqMemberBind(String openId, String account,String imgUrl) {
		
		QqLoginMemberDOExample example = new QqLoginMemberDOExample();
		example.createCriteria().andOpenidEqualTo(openId);
		List<QqLoginMemberDO> list = qqLoginMemberDOMapper.selectByExample(example);
		
		if(list.isEmpty()){
			throw new DataNotExistException("当前没有通过QQ授权");
		}
		
		MemberDOExample memberDOExample = new MemberDOExample();
		memberDOExample.createCriteria().andAccountEqualTo(account);
		
		int count = memberDOMapper.countByExample(memberDOExample);
		
		//用户没有注册，默认注册
		if(count == 0 ){
			RegisterRealParam registerRealParam = new RegisterRealParam();
			registerRealParam.setAccount(account);
			registerRealParam.setRegOrigin(RegOriginEnum.QQ.getName());
			memberService.register(registerRealParam);
			
			MemberDO MemberDO = new MemberDO();
			MemberDO.setHeadimg(imgUrl);
			MemberDO.setNickname(list.get(0).getNickname());
			
			memberDOMapper.updateByExampleSelective(MemberDO, memberDOExample);
		}
		
		List<MemberDO> memberList = memberDOMapper.selectByExample(memberDOExample);
		
		QqLoginMemberDO record =  new QqLoginMemberDO();
		record.setId(list.get(0).getId());
		record.setUserNum(memberList.get(0).getNum());
		record.setGmtModified(new Date());
		qqLoginMemberDOMapper.updateByPrimaryKeySelective(record);
		
		
	}
	
	@Override
	public String getHeadImg(String openId) {
		QqLoginMemberDOExample example = new QqLoginMemberDOExample();
		example.createCriteria().andOpenidEqualTo(openId);
		List<QqLoginMemberDO> list = qqLoginMemberDOMapper.selectByExample(example);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0).getFigureurl();
	}
	

}
