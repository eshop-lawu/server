package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.mall.dto.WorkOrderDTO;
import com.lawu.eshop.mall.srv.bo.WorkOrderBO;
import com.lawu.eshop.mall.srv.domain.WorkOrderDO;


public class WorkOrderConverter {

	@SuppressWarnings("null")
	public static WorkOrderBO convertDoToBo(WorkOrderDO workOrderDO) {
		WorkOrderBO bo = null;
		if(workOrderDO == null)
			return bo;
		bo.setAccount(workOrderDO.getAccount());
		bo.setContent(workOrderDO.getContent());
		bo.setGmtCreat(workOrderDO.getGmtCreate());
		bo.setGmtDeal(workOrderDO.getGmtDeal());
		bo.setId(workOrderDO.getId());
		bo.setName(workOrderDO.getName());
		bo.setReplyContent(workOrderDO.getReplyContent());
		bo.setStatus(workOrderDO.getStatus());
		bo.setType(workOrderDO.getType());
		bo.setAuditorId(workOrderDO.getAuditorId());
		bo.setAuditorName(workOrderDO.getAuditorName());
		bo.setGmtModified(workOrderDO.getGmtModified());
		bo.setUserNum(workOrderDO.getUserNum());
		return bo;
	}
	
	public static List<WorkOrderBO> convertDoToBo(List<WorkOrderDO> list) {
		List<WorkOrderBO> result = new ArrayList<WorkOrderBO>();
		if(list == null || list.isEmpty())
			return result;
		for(WorkOrderDO workOrderDO : list) {
			WorkOrderBO bo = new WorkOrderBO();
			bo.setAccount(workOrderDO.getAccount());
			bo.setContent(workOrderDO.getContent());
			bo.setGmtCreat(workOrderDO.getGmtCreate());
			bo.setGmtDeal(workOrderDO.getGmtDeal());
			bo.setId(workOrderDO.getId());
			bo.setName(workOrderDO.getName());
			bo.setReplyContent(workOrderDO.getReplyContent());
			bo.setStatus(workOrderDO.getStatus());
			bo.setType(workOrderDO.getType());
			bo.setAuditorId(workOrderDO.getAuditorId());
			bo.setAuditorName(workOrderDO.getAuditorName());
			bo.setGmtModified(workOrderDO.getGmtModified());
			bo.setUserNum(workOrderDO.getUserNum());
			result.add(bo);
		}
		return result;
	}
	
	
	public static List<WorkOrderDTO> convertBoToDto(List<WorkOrderBO> list) {
		List<WorkOrderDTO> result = new ArrayList<WorkOrderDTO>();
		if(list == null || list.isEmpty())
			return result;
		for(WorkOrderBO workOrderBO : list) {
			WorkOrderDTO dto = new WorkOrderDTO();
			dto.setAccount(workOrderBO.getAccount());
			dto.setContent(workOrderBO.getContent());
			dto.setGmtCreate(workOrderBO.getGmtCreat());
			dto.setGmtDeal(workOrderBO.getGmtDeal());
			dto.setId(workOrderBO.getId());
			dto.setName(workOrderBO.getName());
			dto.setReplyContent(workOrderBO.getReplyContent());
			dto.setStatus(workOrderBO.getStatus());
			dto.setType(workOrderBO.getType());
			dto.setAuditorId(workOrderBO.getAuditorId());
			dto.setAuditorName(workOrderBO.getAuditorName());
			dto.setGmtModified(workOrderBO.getGmtModified());
			dto.setUserNum(workOrderBO.getUserNum());
			result.add(dto);
		}
		return result;
	}
	
}
