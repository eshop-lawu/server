package com.lawu.eshop.order.srv.converter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.order.dto.ReportRiseRateDTO;
import com.lawu.eshop.order.srv.domain.extend.ReportRiseRateView;
import com.lawu.utils.DateUtil;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月25日
 */
public class ReportConvertTest {

	@Test
	public void reportBrokeLineShow() {
		Date now = new Date();
		/**
		 * 年报表
		 */
		List<ReportRiseRateView> expected = new  ArrayList<>();
		ReportRiseRateView reportRiseRateView = new ReportRiseRateView();
		reportRiseRateView.setKeyTxt(DateUtil.getDateFormat(now, "M"));
		reportRiseRateView.setNum("1");
		// 获取当年最大月份
		int maxMonth = DateUtil.getFieldMaxValue(now, Calendar.MONTH);
		// 获取当年每个月的数据
		ReportRiseRateDTO actual = ReportConvert.reportBrokeLineShow(expected, maxMonth);
		assertReportRiseRateDTO(expected, actual);
		
		/**
		 * 月报表
		 */
		expected = new  ArrayList<>();
		reportRiseRateView = new ReportRiseRateView();
		reportRiseRateView.setKeyTxt(DateUtil.getDateFormat(now, "d"));
		reportRiseRateView.setNum("1");
		// 获取当月最大的天数
		int maxDay = DateUtil.getFieldMaxValue(now, Calendar.DAY_OF_MONTH);
		// 获取当月每天的数据
		actual = ReportConvert.reportBrokeLineShow(expected, maxDay);
		assertReportRiseRateDTO(expected, actual);
	}
	
	private void assertReportRiseRateDTO(List<ReportRiseRateView> expected, ReportRiseRateDTO actual) {
		ReportRiseRateView reportRiseRateView = expected.get(0);
		Assert.assertNotNull(actual);
		for (int i = 0; i < actual.getX().size(); i++) {
			Assert.assertNotNull(actual.getX().get(i));
			Assert.assertNotNull(actual.getY().get(i));
			if (reportRiseRateView.getKeyTxt().equals(actual.getX().get(i))) {
				Assert.assertEquals(reportRiseRateView.getNum(), actual.getY().get(i));
			}
		}
	}
}
