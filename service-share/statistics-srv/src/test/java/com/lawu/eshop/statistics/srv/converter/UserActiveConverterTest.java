package com.lawu.eshop.statistics.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaMonthBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveBO;
import com.lawu.eshop.statistics.srv.bo.UserActiveBO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveMonthDO;
import com.lawu.eshop.statistics.srv.domain.extend.UserActiveDOView;

public class UserActiveConverterTest {

	
	
	@Test
	public void coverBOS() {
		try {
			List<UserActiveDOView> userActiveDOViews = new ArrayList<UserActiveDOView>();
			UserActiveConverter.coverBOS(userActiveDOViews);
			UserActiveDOView userActiveDOView = new UserActiveDOView();
			userActiveDOView.setCityId(4403);
			userActiveDOView.setCityName("深圳市");
			userActiveDOView.setUserCount(10);
			userActiveDOView.setVisitDate(new Date());
			userActiveDOViews.add(userActiveDOView);
			UserActiveConverter.coverBOS(userActiveDOViews);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void coverDTOS() {
		try {
			List<UserActiveBO> userActiveBOS = new ArrayList<UserActiveBO>();
			UserActiveConverter.coverDTOS(userActiveBOS);
			UserActiveBO userActiveBO = new UserActiveBO();
			userActiveBO.setCityId(4403);
			userActiveBO.setCityName("深圳市");
			userActiveBO.setUserCount(10);
			userActiveBO.setVisitDate(new Date());
			userActiveBOS.add(userActiveBO);
			UserActiveConverter.coverDTOS(userActiveBOS);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	@Test
	public void coverReportBOS() {
		try {
			List<ReportUserActiveMonthDO> userActiveMonthDOS = new ArrayList<ReportUserActiveMonthDO>();
			UserActiveConverter.coverReportBOS(userActiveMonthDOS);
			ReportUserActiveMonthDO reportUserActiveDO = new ReportUserActiveMonthDO();
			reportUserActiveDO.setGmtReport(new Date());
			reportUserActiveDO.setMemberCount(10);
			reportUserActiveDO.setMerchantCount(10);
			userActiveMonthDOS.add(reportUserActiveDO);
			UserActiveConverter.coverReportBOS(userActiveMonthDOS);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void coverReportDTOS() {
		try {
			List<ReportUserActiveBO> listBOS = new ArrayList<ReportUserActiveBO>();
			UserActiveConverter.coverReportDTOS(listBOS);
			ReportUserActiveBO userActiveBO = new ReportUserActiveBO();
			userActiveBO.setGmtReport(new Date());
			userActiveBO.setMemberCount(10);
			userActiveBO.setMerchantCount(10);
			listBOS.add(userActiveBO);
			UserActiveConverter.coverReportDTOS(listBOS);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void coverReportAreaBOS() {
		try {
			List<ReportUserActiveAreaDailyDO> areaDailyDOS = new ArrayList<ReportUserActiveAreaDailyDO>();
			UserActiveConverter.coverReportAreaBOS(areaDailyDOS);
			ReportUserActiveAreaDailyDO reportUserActiveDO = new ReportUserActiveAreaDailyDO();
			reportUserActiveDO.setGmtReport(new Date());
			reportUserActiveDO.setMemberCount(10);
			reportUserActiveDO.setMerchantCount(10);
			reportUserActiveDO.setCityId(4403);
			reportUserActiveDO.setCityName("深圳市");
			areaDailyDOS.add(reportUserActiveDO);
			UserActiveConverter.coverReportAreaBOS(areaDailyDOS);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	@Test
	public void coverReportAreaDTOS() {
		try {
			List<ReportUserActiveAreaDailyBO> listBOS = new ArrayList<ReportUserActiveAreaDailyBO>();
			UserActiveConverter.coverReportAreaDTOS(listBOS);
			ReportUserActiveAreaDailyBO reportUserActiveAreaDailyBO = new ReportUserActiveAreaDailyBO();
			reportUserActiveAreaDailyBO.setMemberCount(10);
			reportUserActiveAreaDailyBO.setMerchantCount(10);
			reportUserActiveAreaDailyBO.setCityId(4403);
			reportUserActiveAreaDailyBO.setCityName("深圳市");
			listBOS.add(reportUserActiveAreaDailyBO);
			UserActiveConverter.coverReportAreaDTOS(listBOS);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	@Test
	public void coverReportAreaMonthBOS() {
		try {
			List<ReportUserActiveAreaMonthDO> areaMonthDOS = new ArrayList<ReportUserActiveAreaMonthDO>();
			UserActiveConverter.coverReportAreaMonthBOS(areaMonthDOS);
			ReportUserActiveAreaMonthDO reportUserActiveDO = new ReportUserActiveAreaMonthDO();
			reportUserActiveDO.setMemberCount(10);
			reportUserActiveDO.setMerchantCount(10);
			reportUserActiveDO.setCityId(4403);
			reportUserActiveDO.setCityName("深圳市");
			reportUserActiveDO.setGmtReport(new Date());
			areaMonthDOS.add(reportUserActiveDO);
			UserActiveConverter.coverReportAreaMonthBOS(areaMonthDOS);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	@Test
	public void coverReportAreaMonthDTOS() {
		try {
			List<ReportUserActiveAreaMonthBO> listBOS = new ArrayList<ReportUserActiveAreaMonthBO>();
			UserActiveConverter.coverReportAreaMonthDTOS(listBOS);
			ReportUserActiveAreaMonthBO reportUserActiveAreaBO = new ReportUserActiveAreaMonthBO();
			reportUserActiveAreaBO.setMemberCount(10);
			reportUserActiveAreaBO.setMerchantCount(10);
			reportUserActiveAreaBO.setCityId(4403);
			reportUserActiveAreaBO.setCityName("深圳市");
			listBOS.add(reportUserActiveAreaBO);
			UserActiveConverter.coverReportAreaMonthDTOS(listBOS);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void coverReportBOSWithDOS() {
		try {
			List<ReportUserActiveDailyDO> userActiveDailyDOS = new ArrayList<ReportUserActiveDailyDO>();
			UserActiveConverter.coverReportBOSWithDOS(userActiveDailyDOS);
			ReportUserActiveDailyDO reportUserActiveDOView = new ReportUserActiveDailyDO();
			reportUserActiveDOView.setMemberCount(10);
			reportUserActiveDOView.setMerchantCount(10);
			reportUserActiveDOView.setGmtReport(new Date());
			userActiveDailyDOS.add(reportUserActiveDOView);
			UserActiveConverter.coverReportBOSWithDOS(userActiveDailyDOS);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void coverAgentReportDTOS() {
		try {
			List<ReportUserActiveAreaDailyBO> list = new ArrayList<>();
			UserActiveConverter.coverAgentReportDTOS(list);
			for(int i = 0; i < 2; i++) {
				ReportUserActiveAreaDailyBO bo = new ReportUserActiveAreaDailyBO();
				bo.setMemberCount(10);
				bo.setMerchantCount(10);
				bo.setGmtReport(new Date());
				bo.setCityId(1);
				bo.setCityName("");
				list.add(bo);
			}
			UserActiveConverter.coverAgentReportDTOS(list);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
