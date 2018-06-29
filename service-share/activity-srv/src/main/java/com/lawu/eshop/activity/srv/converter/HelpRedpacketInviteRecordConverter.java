package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.activity.dto.InviteRecordDTO;
import com.lawu.eshop.activity.srv.bo.InviteRecordBO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketInviteRecordDO;

/**
 * 助力好友实体转换
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
public class HelpRedpacketInviteRecordConverter {
	
	
	public static InviteRecordBO helpRedpacketInviteRecordConverterBO(HelpRedpacketInviteRecordDO helpRedpacketInviteRecordDO){
		
		InviteRecordBO inviteRecordBO = new InviteRecordBO();
		if(helpRedpacketInviteRecordDO == null){
			return inviteRecordBO;
		}
		inviteRecordBO.setAccount(helpRedpacketInviteRecordDO.getHelpUserAccount());
		inviteRecordBO.setHeadImg(helpRedpacketInviteRecordDO.getHelpUserHeadimg());
		return inviteRecordBO;
	}
	
	
	public static List<InviteRecordBO> helpRedpacketInviteRecordConverterBOS(List<HelpRedpacketInviteRecordDO> helpRedpacketInviteRecordDOS) {
		List<InviteRecordBO> list = new ArrayList<>();
		for (HelpRedpacketInviteRecordDO helpRedpacketInviteRecordDO : helpRedpacketInviteRecordDOS) {
			InviteRecordBO inviteRecordBO = helpRedpacketInviteRecordConverterBO(helpRedpacketInviteRecordDO);
			list.add(inviteRecordBO);
		}
	
		return list;
	}
	
	
	public static List<InviteRecordDTO> helpRedpacketInviteRecordConverterDTOS(List<InviteRecordBO> inviteRecordBOS) {
		List<InviteRecordDTO> list = new ArrayList<>();
		for (InviteRecordBO inviteRecordBO : inviteRecordBOS) {
			InviteRecordDTO InviteRecordDTO = new InviteRecordDTO();
			InviteRecordDTO.setAccount("*"+inviteRecordBO.getAccount().substring(inviteRecordBO.getAccount().length()-4, inviteRecordBO.getAccount().length()));
			InviteRecordDTO.setHeadImg(inviteRecordBO.getHeadImg());
			list.add(InviteRecordDTO);
		}
	
		return list;
	}

}
