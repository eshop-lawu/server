package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.constants.UserSexEnum;
import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.srv.bo.QqLoginMemberBO;
import com.lawu.eshop.user.srv.domain.MemberDO;


/**
 * QQ登录实体转换
 * 
 * @Description
 * @author zhangrc
 * @date 2018年1月16日
 */
public class QqLoginMemberConverter {
	
	
	public static QqLoginMemberBO qqLoginMemberConverterBO(MemberDO memberDO){
		if(memberDO == null){
			return null;
		}
		QqLoginMemberBO qqLoginMemberBO = new QqLoginMemberBO();
		
		qqLoginMemberBO.setAccount(memberDO.getAccount());
		qqLoginMemberBO.setId(memberDO.getId());
		qqLoginMemberBO.setIsFreeze(memberDO.getIsFreeze());
		qqLoginMemberBO.setNum(memberDO.getNum());
		qqLoginMemberBO.setRyToken(memberDO.getRyToken());
		if(memberDO.getSex() !=null){
			qqLoginMemberBO.setUserSex(UserSexEnum.getEnum(memberDO.getSex()));
		}
		
		return qqLoginMemberBO;
	}
	
	public static LoginUserDTO qqLoginMemberConverterDTO(QqLoginMemberBO qqLoginMemberBO){
		if(qqLoginMemberBO == null){
			return null;
		}
		LoginUserDTO qqLoginMemberDTO = new LoginUserDTO();
		qqLoginMemberDTO.setAccount(qqLoginMemberBO.getAccount());
		qqLoginMemberDTO.setId(qqLoginMemberBO.getId());
		qqLoginMemberDTO.setNum(qqLoginMemberBO.getNum());
		qqLoginMemberDTO.setIsFreeze(qqLoginMemberBO.getIsFreeze());
		qqLoginMemberDTO.setUserSex(qqLoginMemberBO.getUserSex());
		qqLoginMemberDTO.setRyToken(qqLoginMemberBO.getRyToken());
		
		return qqLoginMemberDTO;
	}

}
