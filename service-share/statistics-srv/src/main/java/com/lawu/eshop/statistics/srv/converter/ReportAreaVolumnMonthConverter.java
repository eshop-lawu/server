package com.lawu.eshop.statistics.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.statistics.dto.ReportAreaVolumnMonthDTO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeMonthDO;

public class ReportAreaVolumnMonthConverter {

	
	public static List<ReportAreaVolumnMonthBO> converDOtoBOList(List<ReportAreaVolumeMonthDO> list) {
		List<ReportAreaVolumnMonthBO> rtnList = new ArrayList<ReportAreaVolumnMonthBO>();
		if(list != null && !list.isEmpty()) {
			for(ReportAreaVolumeMonthDO DO : list) {
				ReportAreaVolumnMonthBO bo = new ReportAreaVolumnMonthBO();
				bo.setAreaId(DO.getAreaId());
				bo.setCityId(DO.getCityId());
				bo.setGmtCreate(DO.getGmtCreate());
				bo.setGmtReport(DO.getGmtReport());
				bo.setId(DO.getId());
				bo.setProvinceId(DO.getProvinceId());
				bo.setReportTotalMoney(DO.getReportTotalMoney());
				bo.setType(DO.getType());
				rtnList.add(bo);
			}
		}
		return rtnList;
	}
	
	public static List<ReportAreaVolumnMonthDTO> converBOtoDTOList(List<ReportAreaVolumnMonthBO> list) {
		List<ReportAreaVolumnMonthDTO> rtnList = new ArrayList<ReportAreaVolumnMonthDTO>();
		if(list != null && !list.isEmpty()) {
			for(ReportAreaVolumnMonthBO BO : list) {
				ReportAreaVolumnMonthDTO dto = new ReportAreaVolumnMonthDTO();
				dto.setAreaId(BO.getAreaId());
				dto.setCityId(BO.getCityId());
				dto.setGmtCreate(BO.getGmtCreate());
				dto.setGmtReport(BO.getGmtReport());
				dto.setId(BO.getId());
				dto.setProvinceId(BO.getProvinceId());
				dto.setReportTotalMoney(BO.getReportTotalMoney());
				dto.setType(BO.getType());
				rtnList.add(dto);
			}
		}
		return rtnList;
	}
	
}
