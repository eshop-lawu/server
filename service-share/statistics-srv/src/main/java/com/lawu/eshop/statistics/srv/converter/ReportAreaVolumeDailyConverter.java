package com.lawu.eshop.statistics.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.statistics.dto.ReportAreaVolumnDailyInMonthDTO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumeDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnDailyInMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeDailyDO;
import com.lawu.eshop.statistics.srv.domain.extend.ReportAreaVolumnDailyInMonthDOView;

public class ReportAreaVolumeDailyConverter {

	public static List<ReportAreaVolumeDailyBO> convertDOToBO(List<ReportAreaVolumeDailyDO> list) {
		List<ReportAreaVolumeDailyBO> rtnList = new ArrayList<ReportAreaVolumeDailyBO>();
		if(list != null && !list.isEmpty()) {
			for(ReportAreaVolumeDailyDO DO : list) {
				ReportAreaVolumeDailyBO bo = new ReportAreaVolumeDailyBO();
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
	
	public static List<ReportAreaVolumnDailyInMonthBO> convertDOViewToBO(List<ReportAreaVolumnDailyInMonthDOView> list) {
		List<ReportAreaVolumnDailyInMonthBO> rtnList = new ArrayList<ReportAreaVolumnDailyInMonthBO>();
		if(list != null && !list.isEmpty()) {
			for(ReportAreaVolumnDailyInMonthDOView view : list) {
				ReportAreaVolumnDailyInMonthBO bo = new ReportAreaVolumnDailyInMonthBO();
				bo.setAreaId(view.getAreaId());
				bo.setCityId(view.getCityId());
				bo.setProvinceId(view.getProvinceId());
				bo.setTotalMoney(view.getTotalMoney());
				bo.setType(view.getType());
				rtnList.add(bo);
			}
		}
		return rtnList;
	}
	
	public static List<ReportAreaVolumnDailyInMonthDTO> convertBOToDTO(List<ReportAreaVolumnDailyInMonthBO> list) {
		List<ReportAreaVolumnDailyInMonthDTO> rtnList = new ArrayList<ReportAreaVolumnDailyInMonthDTO>();
		if(list != null && !list.isEmpty()) {
			for(ReportAreaVolumnDailyInMonthBO bo : list) {
				ReportAreaVolumnDailyInMonthDTO dto = new ReportAreaVolumnDailyInMonthDTO();
				dto.setAreaId(bo.getAreaId());
				dto.setCityId(bo.getCityId());
				dto.setProvinceId(bo.getProvinceId());
				dto.setTotalMoney(bo.getTotalMoney());
				dto.setType(bo.getType());
				rtnList.add(dto);
			}
		}
		return rtnList;
	}
}
