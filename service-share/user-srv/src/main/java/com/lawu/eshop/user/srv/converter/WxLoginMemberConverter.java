package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.constants.UserSexEnum;
import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.srv.bo.WxLoginMemberBO;
import com.lawu.eshop.user.srv.domain.MemberDO;


/**
 * 微信登录实体转换
 * 
 * @Description
 * @author zhangrc
 * @date 2018年1月16日
 */
public class WxLoginMemberConverter {
	
	
	public static WxLoginMemberBO wxLoginMemberConverterBO(MemberDO memberDO){
		if(memberDO == null){
			return null;
		}
		WxLoginMemberBO wxLoginMemberBO = new WxLoginMemberBO();
		
		wxLoginMemberBO.setAccount(memberDO.getAccount());
		wxLoginMemberBO.setId(memberDO.getId());
		wxLoginMemberBO.setIsFreeze(memberDO.getIsFreeze());
		wxLoginMemberBO.setNum(memberDO.getNum());
		wxLoginMemberBO.setRyToken(memberDO.getRyToken());
		if(memberDO.getSex() !=null){
			wxLoginMemberBO.setUserSex(UserSexEnum.getEnum(memberDO.getSex()));
		}
		
		return wxLoginMemberBO;
	}
	
	public static LoginUserDTO wxLoginMemberConverterDTO(WxLoginMemberBO wxLoginMemberBO){
		if(wxLoginMemberBO == null){
			return null;
		}
		LoginUserDTO wxLoginMemberDTO = new LoginUserDTO();
		wxLoginMemberDTO.setAccount(wxLoginMemberBO.getAccount());
		wxLoginMemberDTO.setId(wxLoginMemberBO.getId());
		wxLoginMemberDTO.setNum(wxLoginMemberBO.getNum());
		wxLoginMemberDTO.setIsFreeze(wxLoginMemberBO.getIsFreeze());
		wxLoginMemberDTO.setUserSex(wxLoginMemberBO.getUserSex());
		wxLoginMemberDTO.setRyToken(wxLoginMemberBO.getRyToken());
		return wxLoginMemberDTO;
	}

}
