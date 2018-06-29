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
import com.lawu.eshop.statistics.dto.ReportAreaPointConsumeDailyDTO;
import com.lawu.eshop.statistics.dto.ReportAreaPointConsumeDailyInMonthDTO;
import com.lawu.eshop.statistics.param.ReportAreaPointConsumeDailyParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaPointConsumeDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaPointConsumeDailyInMonthBO;
import com.lawu.eshop.statistics.srv.service.ReportAreaPointConsumeDailyService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
/**
 * 
 * @author hongqm
 * @date 2017/8/15
 *
 */
@RestController
@RequestMapping(value = "reportAreaPointConsumeDaily/")
public class ReportAreaPointConsumeDailyController extends BaseController{

	@Autowired
	private ReportAreaPointConsumeDailyService reportAreaPointConsumeDailyService;
	
	/**
	 * 统计保存
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "insertReportAreaPointConsumeDaily", method = RequestMethod.POST)
	public Result insertReportAreaPointConsumeDaily(@RequestBody ReportAreaPointConsumeDailyParam param){
		reportAreaPointConsumeDailyService.insertReportAreaPointConsumeDaily(param);
		return successCreated(ResultCode.SUCCESS);
	}
	
	/**
	 * 区域积分消费日查询
	 * @param cityId
	 * @param bdate
	 * @param edate
	 * @return
	 */
	@RequestMapping(value = "getReportAreaPointConsumeDaily", method = RequestMethod.GET)
	public Result<List<ReportAreaPointConsumeDailyDTO>> getReportAreaPointConsumeDaily(@RequestParam("cityId")Integer cityId, @RequestParam("bdate")String bdate, @RequestParam("edate")String edate){
		List<ReportAreaPointConsumeDailyBO> list = reportAreaPointConsumeDailyService.getReportAreaPointConsumeDaily(cityId,bdate,edate);
		List<ReportAreaPointConsumeDailyDTO> rtnList = new ArrayList<>();
		if(list != null && !list.isEmpty()) {
			for(ReportAreaPointConsumeDailyBO bo : list) {
				ReportAreaPointConsumeDailyDTO dto = new ReportAreaPointConsumeDailyDTO();
				dto.setGmtReport(bo.getGmtReport());
				dto.setMemberPoint(bo.getMemberPoint());
				dto.setMerchantPoint(bo.getMerchantPoint());
				dto.setMemberRechargePoint(bo.getMemberRechargePoint());
				dto.setMerchantRechargePoint(bo.getMerchantRechargePoint());
				dto.setTotalPoint(bo.getTotalPoint());
				dto.setTotalRechargePoint(bo.getTotalRechargePoint());
				rtnList.add(dto);
			}
		}
		return successGet(rtnList);
	}
	
	/**
	 * 定时任务调用统计每月区域积分消费的查询
	 * @param bdate
	 * @param edate
	 * @return
	 */
	@RequestMapping(value = "selectReportAreaPointConsumeDailyInMonth", method = RequestMethod.GET)
	public Result<List<ReportAreaPointConsumeDailyInMonthDTO>> selectReportAreaPointConsumeDailyInMonth(@RequestParam("bdate")String bdate, @RequestParam("edate")String edate){
		List<ReportAreaPointConsumeDailyInMonthBO> list = reportAreaPointConsumeDailyService.selectReportAreaPointConsumeDailyInMonth(bdate, edate);
		List<ReportAreaPointConsumeDailyInMonthDTO> rtnList = new ArrayList<>();
		if(list != null && !list.isEmpty()) {
			for(ReportAreaPointConsumeDailyInMonthBO bo : list) {
				ReportAreaPointConsumeDailyInMonthDTO dto = new ReportAreaPointConsumeDailyInMonthDTO();
				dto.setMemberPoint(bo.getMemberPoint());
				dto.setMerchantPoint(bo.getMerchantPoint());
				dto.setMemberRechargePoint(bo.getMemberRechargePoint());
				dto.setMerchantRechargePoint(bo.getMerchantRechargePoint());
				dto.setAreaId(bo.getAreaId());
				dto.setCityId(bo.getCityId());
				dto.setProvinceId(bo.getProvinceId());
				rtnList.add(dto);
			}
		}
		return successGet(rtnList);
	}
	
}
