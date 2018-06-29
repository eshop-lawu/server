package com.lawu.eshop.statistics.srv.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.statistics.constants.GameTypeEnum;
import com.lawu.eshop.statistics.dto.ReportDialTotalGamePointDTO;
import com.lawu.eshop.statistics.dto.ReportGamePointDTO;
import com.lawu.eshop.statistics.dto.ReportTotalGamePointDTO;
import com.lawu.eshop.statistics.param.GamePointReportParam;
import com.lawu.eshop.statistics.param.ReportGamePointQuery;
import com.lawu.eshop.statistics.srv.bo.ReportGamePointBO;
import com.lawu.eshop.statistics.srv.bo.ReportTotalGamePointBO;
import com.lawu.eshop.statistics.srv.domain.extend.ReportGamePointDailyDOView;
import com.lawu.eshop.statistics.srv.service.ReportGamePointService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月23日
 */
@RestController
@RequestMapping(value = "reportGamePoint/")
public class ReportGamePointController extends BaseController{
	
	@Autowired
	private ReportGamePointService reportGamePointService;
	
	/**
	 * 获取最后一次统计日期
	 * @return
	 */
	@RequestMapping(value = "getDaily", method = RequestMethod.GET)
	public Result<Date> getDaily() {
		 return successGet(reportGamePointService.getDaily());
	}
	
	/**
	 * 保存日统计
	 * 
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "saveReportGamePointDaily", method = RequestMethod.POST)
	public Result saveReportGamePointDaily(@RequestBody List<GamePointReportParam> list){
		reportGamePointService.saveReportGamePointDaily(list);
		return successCreated();
	}
	
	/**
	 * 游戏日统计报表
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "selectReport", method = RequestMethod.POST)
	public Result<ReportGamePointDTO> selectReport(@RequestBody ReportGamePointQuery query ) {
		 ReportGamePointBO  bo=reportGamePointService.selectReport(query);
		 
		 ReportGamePointDTO dto=new ReportGamePointDTO();
		 
		 dto.setBdate(bo.getBdate());
		 dto.setEdate(bo.getEdate());
		 dto.setxAxisData(bo.getxAxisData());
		 dto.setyAxisStandAloneExpendPointData(bo.getyAxisStandAloneExpendPointData());
		 dto.setyAxisStandAloneIncomePointData(bo.getyAxisStandAloneIncomePointData());
		 dto.setyAxisRandomExpendPointData(bo.getyAxisRandomExpendPointData());
		 dto.setyAxisRandomIncomePointData(bo.getyAxisRandomIncomePointData());
		 dto.setyAxisManyPeopleExpendPointData(bo.getyAxisManyPeopleExpendPointData());
		 dto.setyAxisManyPeopleIncomePointData(bo.getyAxisManyPeopleIncomePointData());
		dto.setTotalIncomePointData(bo.getTotalIncomePointData());
		 return successCreated(dto);
	}

	/**
	 * 查询游戏总收入
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "getTotalGamePoint", method = RequestMethod.POST)
	public Result<ReportDialTotalGamePointDTO> getTotalGamePoint(@RequestBody ReportGamePointQuery param) {
		ReportGamePointDailyDOView dailyDOView = reportGamePointService.getTotalGamePoint(param.getTypeEnum());
		ReportDialTotalGamePointDTO pointDTO = new ReportDialTotalGamePointDTO();
		if (dailyDOView == null) {
			pointDTO.setTotalExpendPoint(BigDecimal.ZERO);
			pointDTO.setTotalIncomePoint(BigDecimal.ZERO);
		} else {
			pointDTO.setTotalIncomePoint(dailyDOView.getTotalIncomePoint() == null ? BigDecimal.ZERO : dailyDOView.getTotalIncomePoint());
			pointDTO.setTotalExpendPoint(dailyDOView.getTotalExpendPoint() == null ? BigDecimal.ZERO : dailyDOView.getTotalExpendPoint());
		}
		return successGet(pointDTO);
	}
	
	
	@RequestMapping(value = "selectTotalReport", method = RequestMethod.POST)
	public Result<ReportTotalGamePointDTO> selectTotalReport(@RequestParam GameTypeEnum typeEnum) {
		ReportTotalGamePointBO bo = reportGamePointService.selectTotalReport(typeEnum);

		ReportTotalGamePointDTO dto = new ReportTotalGamePointDTO();
		
		dto.setyAxisStandAloneExpendPointData(bo.getyAxisStandAloneExpendPointData());
		dto.setyAxisStandAloneIncomePointData(bo.getyAxisStandAloneIncomePointData());
		dto.setyAxisRandomExpendPointData(bo.getyAxisRandomExpendPointData());
		dto.setyAxisRandomIncomePointData(bo.getyAxisRandomIncomePointData());
		dto.setyAxisManyPeopleExpendPointData(bo.getyAxisManyPeopleExpendPointData());
		dto.setyAxisManyPeopleIncomePointData(bo.getyAxisManyPeopleIncomePointData());

		return successCreated(dto);
	}
	

}
