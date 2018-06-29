/**
 * 
 */
package com.lawu.eshop.mall.srv.converter;

import java.util.List;

import com.google.common.collect.Lists;
import com.lawu.eshop.mall.dto.InformDTO;
import com.lawu.eshop.mall.dto.InformEnum;
import com.lawu.eshop.mall.dto.InformStatusEnum;
import com.lawu.eshop.mall.param.InformEditParam;
import com.lawu.eshop.mall.param.InformSaveParam;
import com.lawu.eshop.mall.srv.bo.InformBO;
import com.lawu.eshop.mall.srv.bo.InformEditBO;
import com.lawu.eshop.mall.srv.domain.InformDO;

/**
 * @author lihj
 * @date 2017年7月27日
 */
public class InformConverter {

	public static InformDO converDO(InformSaveParam param) {
		InformDO info = new InformDO();
		info.setContent(param.getContent());
		info.setGmtCreate(param.getGmtCreate());
		info.setGmtModified(param.getGmtModified());
		info.setInformerAccount(param.getInformerAccount());
		info.setInformerUserNum(param.getInformerUserNum());
		info.setInformtItemId(param.getInformtItemId());
		info.setInformtItemName(param.getInformtItemName());
		info.setInformType(param.getInformType());
		info.setStatus(param.getStatus());
		return info;
	}

	public static InformBO converBO(InformDO info) {
		InformBO bo = new InformBO();
		bo.setAuditorName(info.getAuditorName());
		bo.setContent(info.getContent());
		bo.setGmtCreate(info.getGmtCreate());
		bo.setId(info.getId());
		bo.setInformerAccount(info.getInformerAccount());
		bo.setInformtItemId(info.getInformtItemId());
		bo.setInformtItemName(info.getInformtItemName());
		bo.setInformType(info.getInformType());
		bo.setRemark(info.getRemark());
		bo.setStatus(info.getStatus());
		bo.setInformTypeStr(InformEnum.getEnum(info.getInformType()).getName());
		bo.setStatusStr(InformStatusEnum.getEnum(info.getStatus()).getName());
		return bo;
	}

	public static List<InformDTO> convertDTOS(List<InformBO> infos) {
		List<InformDTO> list = Lists.newArrayList();
		if (null == infos) {
			return list;
		}
		for (InformBO bo : infos) {
			InformDTO dto = convertDTO(bo);
			list.add(dto);
		}
		return list;
	}

	/**
	 * @param bo
	 * @return
	 */
	private static InformDTO convertDTO(InformBO bo) {
		InformDTO dto = new InformDTO();
		dto.setAuditorName(bo.getAuditorName());
		dto.setContent(bo.getContent());
		dto.setGmtCreate(bo.getGmtCreate());
		dto.setId(bo.getId());
		dto.setInformerAccount(bo.getInformerAccount());
		dto.setInformtItemId(bo.getInformtItemId());
		dto.setInformtItemName(bo.getInformtItemName());
		dto.setInformType(bo.getInformType());
		dto.setInformTypeStr(bo.getInformTypeStr());
		dto.setRemark(bo.getRemark());
		dto.setStatus(bo.getStatus());
		dto.setStatusStr(bo.getStatusStr());
		return dto;
	}

	public static InformEditBO convertBO(InformEditParam param) {
		InformEditBO bo =new InformEditBO();
		bo.setId(param.getId());
		bo.setAuditorId(param.getAuditorId());
		bo.setAuditorName(param.getAuditorName());
		bo.setAuditTime(param.getAuditTime());
		bo.setGmtModified(param.getGmtModified());
		bo.setRemark(param.getRemark());
		bo.setStatus(param.getStatus());
		return bo;
	}

}
