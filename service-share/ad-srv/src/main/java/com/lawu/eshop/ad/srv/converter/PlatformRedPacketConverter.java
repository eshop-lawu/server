package com.lawu.eshop.ad.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.ad.constants.PlatformRedPacketStatusEnum;
import com.lawu.eshop.ad.dto.PlatformRedPacketDTO;
import com.lawu.eshop.ad.srv.bo.PlatformRedPacketBO;
import com.lawu.eshop.ad.srv.domain.PlatformRedPacketDO;

/**
 * 
 * 平台红包实体转换类
 * @author zhangrc
 * @date 2017/10/18
 *
 */
public class PlatformRedPacketConverter {
	
	
	public static List<PlatformRedPacketDTO> convertDTOS(List<PlatformRedPacketBO> list){
		List<PlatformRedPacketDTO> dtos=new ArrayList<PlatformRedPacketDTO>();
		if(list==null){
			return dtos;
		}
		for (PlatformRedPacketBO bo : list) {
			PlatformRedPacketDTO dto = new PlatformRedPacketDTO();
			dto.setId(bo.getId());
			dto.setAuditorId(bo.getAuditorId());
			dto.setMoney(bo.getMoney());
			dto.setSendCount(bo.getSendCount());
			dto.setStatusEnum(bo.getStatusEnum());
			dto.setGmtCreate(bo.getGmtCreate());
			dto.setGmtModified(bo.getGmtModified());
			dtos.add(dto);
		}
		return dtos;
	}
	
	
	public static List<PlatformRedPacketBO> convertBOS(List<PlatformRedPacketDO> list){
		
		List<PlatformRedPacketBO> bos=new ArrayList<PlatformRedPacketBO>();
		if(list==null){
			return bos;
		}
		for (PlatformRedPacketDO record : list) {
			
			PlatformRedPacketBO bo = new PlatformRedPacketBO();
			bo.setId(record.getId());
			bo.setAuditorId(record.getAuditorId());
			bo.setMoney(record.getMoney());
			bo.setSendCount(record.getSendCount());
			bo.setStatusEnum(PlatformRedPacketStatusEnum.getEnum(record.getStatus()));
			bo.setGmtCreate(record.getGmtCreate());
			bo.setGmtModified(record.getGmtModified());
			bos.add(bo);
			
		}
		return bos;
	}

}
