package com.lawu.eshop.statistics.srv.converter;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.statistics.constants.AdStatusEnum;
import com.lawu.eshop.statistics.constants.ReportAdEarningsStatusEnum;
import com.lawu.eshop.statistics.srv.bo.ReportAdEarningsBO;
import com.lawu.eshop.statistics.srv.domain.ReportAdEarningsDO;

public class ReportAdEarningsConverterTest {

	
	@Test
	public void convertBO() {
		ReportAdEarningsDO reportAdEarningsDO = new ReportAdEarningsDO();
		reportAdEarningsDO.setId(1L);
		reportAdEarningsDO.setAdId(123456L);
		reportAdEarningsDO.setAdCreateTime(new Date());
		reportAdEarningsDO.setAdStatus((byte)1);
		reportAdEarningsDO.setAdTitle("title");
		reportAdEarningsDO.setAdTotalPoint(new BigDecimal(10));
		reportAdEarningsDO.setAdType((byte)1);
		reportAdEarningsDO.setGmtCreate(new Date());
		reportAdEarningsDO.setIsProcessed(false);
		reportAdEarningsDO.setLoveTotalPoint(new BigDecimal(199));
		reportAdEarningsDO.setMerchantName("hhh");
		reportAdEarningsDO.setStatus((byte)1);
		reportAdEarningsDO.setUserTotalPoint(new BigDecimal(1000));
		ReportAdEarningsConverter.convertBO(reportAdEarningsDO);
	}
	
	@Test
	public void convertDTO() {
		ReportAdEarningsBO reportAdEarningsBO = new ReportAdEarningsBO();
		reportAdEarningsBO.setId(1L);
		reportAdEarningsBO.setAdId(123456L);
		reportAdEarningsBO.setAdCreateTime(new Date());
		reportAdEarningsBO.setAdStatusEnum(AdStatusEnum.AD_STATUS_ADD);
		reportAdEarningsBO.setAdTitle("title");
		reportAdEarningsBO.setAdTotalPoint(new BigDecimal(10));
		reportAdEarningsBO.setAdTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
		reportAdEarningsBO.setGmtCreate(new Date());
		reportAdEarningsBO.setIsProcessed(false);
		reportAdEarningsBO.setLoveTotalPoint(new BigDecimal(199));
		reportAdEarningsBO.setMerchantName("hhh");
		reportAdEarningsBO.setReportAdEarningsStatusEnum(ReportAdEarningsStatusEnum.ANOMALY);
		reportAdEarningsBO.setUserTotalPoint(new BigDecimal(1000));
		ReportAdEarningsConverter.convertDTO(reportAdEarningsBO);
		
	}
	
}
