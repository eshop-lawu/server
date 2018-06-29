package com.lawu.eshop.statistics.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.lawu.eshop.statistics.srv.bo.ReportAreaUserRegDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaUserRegMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegMonthDO;

public class ReportAreaUserRegConverterTest {

	@Test
	public void test() {
		List<ReportAreaUserRegDailyDO> userRegDailyDOS = new ArrayList<>();
		ReportAreaUserRegConverter.coverBOS(userRegDailyDOS);
		for(int i = 0; i < 2; i++) {
			ReportAreaUserRegDailyDO DO = new ReportAreaUserRegDailyDO();
			DO.setCityId(1);
			DO.setCityName("");
			DO.setGmtCreate(new Date());
			DO.setGmtReport(new Date());
			DO.setMemberCount(1);
			DO.setMerchantCount(1);
			DO.setMerchantEntityCount(1);
			DO.setMerchantNormalCount(1);
			userRegDailyDOS.add(DO);
		}
		ReportAreaUserRegConverter.coverBOS(userRegDailyDOS);
		
		List<ReportAreaUserRegDailyBO> listBOS = new ArrayList<>();
		ReportAreaUserRegConverter.coverDTOS(listBOS);
		for(int i = 0; i < 2; i++) {
			ReportAreaUserRegDailyBO bo = new ReportAreaUserRegDailyBO();
			bo.setCityId(1);
			bo.setCityName("");
			bo.setGmtCreate(new Date());
			bo.setGmtReport(new Date());
			bo.setMemberCount(1);
			bo.setMerchantCount(1);
			bo.setMerchantEntityCount(1);
			bo.setMerchantNormalCount(1);
			listBOS.add(bo);
		}
		ReportAreaUserRegConverter.coverDTOS(listBOS);
		
		
		List<ReportAreaUserRegMonthDO> userRegMonthDOS = new ArrayList<>();
		ReportAreaUserRegConverter.coverMonthBOS(userRegMonthDOS);
		for(int i = 0; i < 2; i++) {
			ReportAreaUserRegMonthDO DO = new ReportAreaUserRegMonthDO();
			DO.setCityId(1);
			DO.setCityName("");
			DO.setGmtCreate(new Date());
			DO.setGmtReport(new Date());
			DO.setMemberCount(1);
			DO.setMerchantCount(1);
			DO.setMerchantEntityCount(1);
			DO.setMerchantNormalCount(1);
			userRegMonthDOS.add(DO);
		}
		ReportAreaUserRegConverter.coverMonthBOS(userRegMonthDOS);
		
		
		List<ReportAreaUserRegMonthBO> listBO = new ArrayList<>();
		ReportAreaUserRegConverter.coverMonthDTOS(listBO);
		for(int i = 0; i < 2; i++) {
			ReportAreaUserRegMonthBO bo = new ReportAreaUserRegMonthBO();
			bo.setCityId(1);
			bo.setCityName("");
			bo.setGmtCreate(new Date());
			bo.setGmtReport(new Date());
			bo.setMemberCount(1);
			bo.setMerchantCount(1);
			bo.setMerchantEntityCount(1);
			bo.setMerchantNormalCount(1);
			listBO.add(bo);
		}
		ReportAreaUserRegConverter.coverMonthDTOS(listBO);
		
	}
}
