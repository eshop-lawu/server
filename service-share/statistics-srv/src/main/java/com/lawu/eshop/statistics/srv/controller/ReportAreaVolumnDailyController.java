package com.lawu.eshop.statistics.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.statistics.dto.ReportAreaVolumeDailyDTO;
import com.lawu.eshop.statistics.dto.ReportAreaVolumnDailyInMonthDTO;
import com.lawu.eshop.statistics.param.ReportAreaVolumnDailyParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumeDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnDailyInMonthBO;
import com.lawu.eshop.statistics.srv.converter.ReportAreaVolumeDailyConverter;
import com.lawu.eshop.statistics.srv.service.ReportAreaVolumnDailyService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author hongqm
 * @date 2017/8/14
 */
@RestController
@RequestMapping(value = "reportAreaVolumnDaily/")
public class ReportAreaVolumnDailyController extends BaseController {

	@Autowired
	private ReportAreaVolumnDailyService reportAreaVolumnDailyService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "insertReportAreaVolumnDaily", method = RequestMethod.POST)
    public Result insertReportAreaVolumnDaily(@RequestBody ReportAreaVolumnDailyParam param) {
		reportAreaVolumnDailyService.insertReportAreaVolumnDaily(param);
        return successCreated(ResultCode.SUCCESS);
    }
	
	
	@RequestMapping(value = "selectReportAreaVolumeDailyInMonth", method = RequestMethod.GET)
    public Result<List<ReportAreaVolumnDailyInMonthDTO>> selectReportAreaVolumeDailyInMonth(@RequestParam("bdate") String bdate, @RequestParam("edate") String edate) {
		List<ReportAreaVolumnDailyInMonthBO> boList = reportAreaVolumnDailyService.selectReportAreaVolumeDailyInMonth(bdate, edate);
		List<ReportAreaVolumnDailyInMonthDTO> rtn = ReportAreaVolumeDailyConverter.convertBOToDTO(boList);
        return successGet(rtn);
    }
	
	@RequestMapping(value = "selectReportAreaVolumnDaily", method = RequestMethod.GET)
	Result<List<ReportAreaVolumeDailyDTO>> selectReportAreaVolumnDaily(@RequestParam("cityId") Integer cityId,@RequestParam("bdate") String bdate,@RequestParam("edate") String edate) {
		List<ReportAreaVolumeDailyBO> list = reportAreaVolumnDailyService.selectReportAreaVolumnDaily(cityId, bdate, edate);
		List<ReportAreaVolumeDailyDTO> rtnList = new ArrayList<ReportAreaVolumeDailyDTO>();
		if(list != null && !list.isEmpty()) {
			for(ReportAreaVolumeDailyBO BO : list) {
				ReportAreaVolumeDailyDTO dto = new ReportAreaVolumeDailyDTO();
				dto.setAreaId(BO.getAreaId());
				dto.setCityId(BO.getCityId());
				dto.setGmtCreate(BO.getGmtCreate());
				dto.setGmtReport(BO.getGmtReport());
				dto.setProvinceId(BO.getProvinceId());
				dto.setReportTotalMoney(BO.getReportTotalMoney());
				dto.setType(BO.getType());
				rtnList.add(dto);
			}
		}
		return successGet(rtnList);
	}
	
}
