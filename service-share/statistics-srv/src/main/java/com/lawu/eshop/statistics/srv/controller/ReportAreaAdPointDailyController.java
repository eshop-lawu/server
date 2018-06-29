package com.lawu.eshop.statistics.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.statistics.dto.ReportAreaAdPointDailyDTO;
import com.lawu.eshop.statistics.dto.ReportAreaAdPointMonthDTO;
import com.lawu.eshop.statistics.dto.ReportAreaAdPorintDailyByAreaIdDTO;
import com.lawu.eshop.statistics.param.AgentSelectAreaAdPointParam;
import com.lawu.eshop.statistics.param.ReportAreaAdPointDailyParams;
import com.lawu.eshop.statistics.srv.bo.ReportAreaAdPointDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaAdPointMonthBO;
import com.lawu.eshop.statistics.srv.service.ReportAreaAdPointDailyService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "reportAreaAdPointDaily/")
public class ReportAreaAdPointDailyController extends BaseController {

	@Autowired
	private ReportAreaAdPointDailyService reportAreaAdPointDailyService;
	
	@RequestMapping(value = "selectReportAreaAdPointDaily/{areaId}", method = RequestMethod.GET)
	public Result<List<ReportAreaAdPointDailyDTO>> selectReportAreaAdPointDaily(@PathVariable @ApiParam(required = true, value = "区域id") Integer areaId, @RequestParam("date") String date) {
		List<ReportAreaAdPointDailyBO> list = reportAreaAdPointDailyService.selectReportAreaAdPointDaily(areaId, date);
		List<ReportAreaAdPointDailyDTO> rtnList = new ArrayList<ReportAreaAdPointDailyDTO>();
		for(ReportAreaAdPointDailyBO BO : list) {
			ReportAreaAdPointDailyDTO reportAreaAdPointDailyDTO = new ReportAreaAdPointDailyDTO();
			reportAreaAdPointDailyDTO.setAreaId(BO.getAreaId());
			reportAreaAdPointDailyDTO.setId(BO.getId());
			reportAreaAdPointDailyDTO.setCityId(BO.getCityId());
			reportAreaAdPointDailyDTO.setGmtCreate(BO.getGmtCreate());
			reportAreaAdPointDailyDTO.setGmtReport(BO.getGmtReport());
			reportAreaAdPointDailyDTO.setProvinceId(BO.getProvinceId());
			reportAreaAdPointDailyDTO.setReportTotalPoint(BO.getReportTotalPoint());
			rtnList.add(reportAreaAdPointDailyDTO);
		}
		return successCreated(rtnList);
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "insertReportAreaAdPointDaily", method = RequestMethod.POST)
	public Result insertReportAreaAdPointDaily(@RequestBody ReportAreaAdPointDailyParams param) {
		reportAreaAdPointDailyService.insertReportAreaAdPointDaily(param);
		return successCreated(ResultCode.SUCCESS);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "deleteReportAreaAdPointDaily", method = RequestMethod.POST)
	public Result deleteReportAreaAdPointDaily(@RequestParam(value = "id") Long id) {
		reportAreaAdPointDailyService.deleteReportAreaAdPointDaily(id);
		return successCreated(ResultCode.SUCCESS);
	}
	
	
	@RequestMapping(value = "selectReportAreaAdPointDailyInMonth", method = RequestMethod.GET)
	public Result<List<ReportAreaAdPointMonthDTO>> selectReportAreaAdPointDailyInMonth(@RequestParam(value = "bdate") String bdate, 
				@RequestParam(value = "edate") String edate) {
		List<ReportAreaAdPointMonthBO> list = reportAreaAdPointDailyService.selectReportAreaAdPointDailyInMonth(bdate, edate);
		List<ReportAreaAdPointMonthDTO> rtnList = new ArrayList<ReportAreaAdPointMonthDTO>();
		if(list != null && !list.isEmpty()) {
			for(ReportAreaAdPointMonthBO reportAreaAdPointMonthBO : list) {
				ReportAreaAdPointMonthDTO dto = new ReportAreaAdPointMonthDTO();
				dto.setAreaId(reportAreaAdPointMonthBO.getAreaId());
				dto.setCityId(reportAreaAdPointMonthBO.getCityId());
				dto.setProvinceId(reportAreaAdPointMonthBO.getProvinceId());
				dto.setReportTotalPoint(reportAreaAdPointMonthBO.getReportTotalPoint());
				rtnList.add(dto);
			}
		}
		return successCreated(rtnList);
	}
	
	@RequestMapping(value = "selectReportAreaAdPointDailyByAgentSelectAreaAdPointParam", method = RequestMethod.POST)
	Result<List<ReportAreaAdPorintDailyByAreaIdDTO>> selectReportAreaAdPointDaily(@RequestBody AgentSelectAreaAdPointParam param) {
		List<ReportAreaAdPointDailyBO> list = reportAreaAdPointDailyService.selectReportAreaAdPointDaily(param);
		List<ReportAreaAdPorintDailyByAreaIdDTO> rtnList = new ArrayList<ReportAreaAdPorintDailyByAreaIdDTO>();
		if(list != null && !list.isEmpty()) {
			for(ReportAreaAdPointDailyBO bo : list) {
				ReportAreaAdPorintDailyByAreaIdDTO dto = new ReportAreaAdPorintDailyByAreaIdDTO();
				dto.setGmtReport(bo.getGmtReport());
				dto.setReportTotalPoint(bo.getReportTotalPoint());
				rtnList.add(dto);
			}
		}
		return  successCreated(rtnList);
	}
	
}
