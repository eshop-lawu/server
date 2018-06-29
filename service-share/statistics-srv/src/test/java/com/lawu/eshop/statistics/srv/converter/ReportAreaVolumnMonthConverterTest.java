package com.lawu.eshop.statistics.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeMonthDO;

public class ReportAreaVolumnMonthConverterTest {

	@Test
	public void test() {
		List<ReportAreaVolumnMonthBO> list = new ArrayList<ReportAreaVolumnMonthBO>();
		for(int i = 0; i < 2; i++) {
			ReportAreaVolumnMonthBO bo = new ReportAreaVolumnMonthBO();
			bo.setAreaId(1);
			bo.setCityId(1);
			bo.setGmtCreate(new Date());
			bo.setGmtReport(new Date());
			bo.setProvinceId(1);
			bo.setReportTotalMoney(new BigDecimal(1));
			bo.setType((byte)1);
			list.add(bo);
		}
		ReportAreaVolumnMonthConverter.converBOtoDTOList(list);
	}
	
	@Test
	public void converDOtoBOList() {
		List<ReportAreaVolumeMonthDO> list = new ArrayList<ReportAreaVolumeMonthDO>();
		for(int i = 0; i < 2; i++) {
			ReportAreaVolumeMonthDO bo = new ReportAreaVolumeMonthDO();
			bo.setAreaId(1);
			bo.setCityId(1);
			bo.setGmtCreate(new Date());
			bo.setGmtReport(new Date());
			bo.setProvinceId(1);
			bo.setReportTotalMoney(new BigDecimal(1));
			bo.setType((byte)1);
			list.add(bo);
		}
		ReportAreaVolumnMonthConverter.converDOtoBOList(list);
	}
	
}
