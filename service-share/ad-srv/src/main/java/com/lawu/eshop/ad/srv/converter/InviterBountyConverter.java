package com.lawu.eshop.ad.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.ad.constants.PlatformRedPacketStatusEnum;
import com.lawu.eshop.ad.dto.InviterBountyDTO;
import com.lawu.eshop.ad.srv.bo.InviterBountyBO;
import com.lawu.eshop.ad.srv.domain.InviterBountyDO;

/**
 * 
 * 平台红包实体转换类
 * @author zhangrc
 * @date 2017/10/18
 *
 */
public class InviterBountyConverter {
	
	
	public static List<InviterBountyDTO> convertDTOS(List<InviterBountyBO> list){
		List<InviterBountyDTO> dtos=new ArrayList<InviterBountyDTO>();
		if(list==null){
			return dtos;
		}
		for (InviterBountyBO bo : list) {
			InviterBountyDTO dto = new InviterBountyDTO();
			dto.setId(bo.getId());
			dto.setAuditorId(bo.getAuditorId());
			dto.setMoney(bo.getMoney());
			dto.setSendCount(bo.getSendCount());
			dto.setStatusEnum(bo.getStatusEnum());
			dto.setGmtCreate(bo.getGmtCreate());
			dto.setGmtModified(bo.getGmtModified());
			dto.setTotalMoney(bo.getMoney().multiply(BigDecimal.valueOf(bo.getSendCount())));
			dtos.add(dto);
		}
		return dtos;
	}
	
	
	public static List<InviterBountyBO> convertBOS(List<InviterBountyDO> list){
		
		List<InviterBountyBO> bos=new ArrayList<InviterBountyBO>();
		if(list==null){
			return bos;
		}
		for (InviterBountyDO record : list) {
			
			InviterBountyBO bo = new InviterBountyBO();
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
