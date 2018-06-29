package com.lawu.eshop.statistics.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.statistics.srv.bo.ReportUserRegAreaBO;
import com.lawu.eshop.statistics.srv.domain.ReportUserRegAreaDO;
import com.lawu.eshop.statistics.srv.domain.extend.ReportUserRegDOView;

public class ReportUserRegConverterTest {

	
	@Test
	public void convertAreaBO() {
		try {
			List<ReportUserRegAreaDO> regAreaDOList = new ArrayList<ReportUserRegAreaDO>();
			ReportUserRegConverter.convertAreaBO(regAreaDOList);
			ReportUserRegAreaDO regAreaDO = new ReportUserRegAreaDO();
			regAreaDO.setMemberCount(10);
			regAreaDO.setMerchantCount(10);
			regAreaDO.setMerchantCommonCount(20);
			regAreaDO.setMerchantEntityCount(20);
			regAreaDO.setCityId(4403);
			regAreaDO.setCityName("深圳市");
			regAreaDOList.add(regAreaDO);
			ReportUserRegConverter.convertAreaBO(regAreaDOList);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	@Test
	public void convertAreaDTO() {
		try {
			List<ReportUserRegAreaBO> regAreaBOList = new ArrayList<ReportUserRegAreaBO>();
			ReportUserRegConverter.convertAreaDTO(regAreaBOList);
			ReportUserRegAreaBO regAreaBO = new ReportUserRegAreaBO();
			regAreaBO.setMemberCount(10);
			regAreaBO.setMerchantCount(10);
			regAreaBO.setMerchantCommonCount(20);
			regAreaBO.setMerchantEntityCount(20);
			regAreaBO.setCityId(4403);
			regAreaBO.setCityName("深圳市");
			regAreaBOList.add(regAreaBO);
			ReportUserRegConverter.convertAreaDTO(regAreaBOList);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	@Test
	public void convertDTO() {
		try {
			List<ReportUserRegDOView> regDOViewList = new ArrayList<ReportUserRegDOView>();
			ReportUserRegConverter.convertDTO(regDOViewList);
			ReportUserRegDOView regDOView = new ReportUserRegDOView();
			regDOView.setGmtReport(new Date());
			regDOView.setMemberCount(10);
			regDOView.setMerchantCount(10);
			regDOViewList.add(regDOView);
			ReportUserRegConverter.convertDTO(regDOViewList);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
