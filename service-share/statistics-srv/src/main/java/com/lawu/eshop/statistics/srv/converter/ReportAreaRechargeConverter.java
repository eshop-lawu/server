package com.lawu.eshop.statistics.srv.converter;

import java.math.BigDecimal;

import com.lawu.eshop.statistics.dto.AgentAreaRechargeQReturnDTO;
import com.lawu.eshop.statistics.srv.bo.AgentAreaRechargeQReturnBO;
import com.lawu.eshop.statistics.srv.domain.extend.AgentAreaRechargeDailyMonthDOExtend;

public class ReportAreaRechargeConverter {

	public static AgentAreaRechargeQReturnDTO convertDTO(AgentAreaRechargeQReturnBO bo){
		AgentAreaRechargeQReturnDTO dto = new AgentAreaRechargeQReturnDTO();
		if(bo == null) {
			dto.setProvinceId(0);
			dto.setCityId(0);
			dto.setAreaId(0);
			dto.setMerchantRechargePoint(new BigDecimal(0));
			dto.setMerchantRechargeBalance(new BigDecimal(0));
			dto.setMemberRechargePoint(new BigDecimal(0));
			dto.setMemberRechargeBalance(new BigDecimal(0));
			return dto;
		}
		dto.setProvinceId(bo.getProvinceId());
		dto.setCityId(bo.getCityId());
		dto.setAreaId(bo.getAreaId());
		dto.setMerchantRechargePoint(bo.getMerchantRechargePoint());
		dto.setMerchantRechargeBalance(bo.getMerchantRechargeBalance());
		dto.setMemberRechargePoint(bo.getMemberRechargePoint());
		dto.setMemberRechargeBalance(bo.getMemberRechargeBalance());
		return dto;
	}

	public static AgentAreaRechargeQReturnBO convertBO(AgentAreaRechargeDailyMonthDOExtend doExtend) {
		if(doExtend == null){
			return null;
		}
		AgentAreaRechargeQReturnBO bo = new AgentAreaRechargeQReturnBO();
		bo.setCityId(doExtend.getCityId());
		bo.setMemberRechargeBalance(doExtend.getMemberRechargeBalance());
		bo.setMemberRechargePoint(doExtend.getMemberRechargePoint());
		bo.setMerchantRechargeBalance(doExtend.getMerchantRechargeBalance());
		bo.setMerchantRechargePoint(doExtend.getMerchantRechargePoint());
		return bo;
	}
}
