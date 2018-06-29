package com.lawu.eshop.statistics.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnDailyInMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeDailyDO;
import com.lawu.eshop.statistics.srv.domain.extend.ReportAreaVolumnDailyInMonthDOView;

public class ReportAreaVolumeDailyConverterTest {

	
	@Test
	public void test() {
		List<ReportAreaVolumnDailyInMonthBO> list = new ArrayList<>();
		for(int i = 0; i < 2; i++) {
			ReportAreaVolumnDailyInMonthBO bo = new ReportAreaVolumnDailyInMonthBO();
			bo.setAreaId(1);
			bo.setCityId(1);
			bo.setProvinceId(1);
			bo.setTotalMoney(new BigDecimal(1));
			bo.setType((byte)1);
			list.add(bo);
		}
		ReportAreaVolumeDailyConverter.convertBOToDTO(list);
		List<ReportAreaVolumeDailyDO> list1 = new ArrayList<>();
		for(int i = 0; i < 2; i++) {
			ReportAreaVolumeDailyDO bo = new ReportAreaVolumeDailyDO();
			bo.setAreaId(1);
			bo.setCityId(1);
			bo.setProvinceId(1);
			bo.setGmtCreate(new Date());
			bo.setGmtReport(new Date());
			bo.setReportTotalMoney(new BigDecimal(1));
			bo.setType((byte)1);
			list1.add(bo);
		}
		ReportAreaVolumeDailyConverter.convertDOToBO(list1);
		List<ReportAreaVolumnDailyInMonthDOView> list2 = new ArrayList<>();
		for(int i = 0; i < 2; i++) {
			ReportAreaVolumnDailyInMonthDOView bo = new ReportAreaVolumnDailyInMonthDOView();
			bo.setAreaId(1);
			bo.setCityId(1);
			bo.setProvinceId(1);
			bo.setTotalMoney(new BigDecimal(1));
			bo.setType((byte)1);
			list2.add(bo);
		}
		ReportAreaVolumeDailyConverter.convertDOViewToBO(list2);
	}
}
