package com.lawu.eshop.statistics.srv.converter;

import java.math.BigDecimal;

import org.junit.Test;

import com.lawu.eshop.statistics.srv.bo.AgentAreaRechargeQReturnBO;
import com.lawu.eshop.statistics.srv.domain.extend.AgentAreaRechargeDailyMonthDOExtend;

public class ReportAreaRechargeConverterTest {

	@Test
	public void convertBO() {
		AgentAreaRechargeDailyMonthDOExtend dxExtend = new AgentAreaRechargeDailyMonthDOExtend();
		dxExtend.setCityId(1);
		dxExtend.setMemberRechargeBalance(new BigDecimal(1));
		dxExtend.setMemberRechargePoint(new BigDecimal(1));
		dxExtend.setMerchantRechargeBalance(new BigDecimal(1));
		dxExtend.setMerchantRechargePoint(new BigDecimal(1));
		ReportAreaRechargeConverter.convertBO(dxExtend);
		ReportAreaRechargeConverter.convertBO(null);
	}
	
	@Test
	public void convertDTO() {
		AgentAreaRechargeQReturnBO bo = new AgentAreaRechargeQReturnBO();
		bo.setAreaId(1);
		bo.setCityId(1);
		bo.setMemberRechargeBalance(new BigDecimal(1));
		bo.setMemberRechargePoint(new BigDecimal(1));
		bo.setMerchantRechargeBalance(new BigDecimal(1));
		bo.setMerchantRechargePoint(new BigDecimal(1));
		bo.setProvinceId(1);
		ReportAreaRechargeConverter.convertDTO(bo);
		ReportAreaRechargeConverter.convertDTO(null);
	}
}
