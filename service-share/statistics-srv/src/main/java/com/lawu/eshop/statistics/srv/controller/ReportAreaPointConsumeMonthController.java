package com.lawu.eshop.statistics.srv.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.statistics.dto.ReportAreaPointConsumeMonthForCircleDTO;
import com.lawu.eshop.statistics.param.ReportAreaPointConsumeMonthParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaPointConsumeMonthBO;
import com.lawu.eshop.statistics.srv.service.ReportAreaPointConsumeMonthService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
/**
 * 
 * @author hongqm
 * @date 2017/8/15
 *
 */
@RestController
@RequestMapping(value = "reportAreaPointConsumeMonth/")
public class ReportAreaPointConsumeMonthController extends BaseController{
	
	@Autowired
	private ReportAreaPointConsumeMonthService reportAreaPointConsumeMonthService;
	
	/**
	 * 统计保存
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveReportAreaPointConsumeMonth", method = RequestMethod.POST)
	public Result saveReportAreaPointConsumeMonth(@RequestBody ReportAreaPointConsumeMonthParam param){
		reportAreaPointConsumeMonthService.saveReportAreaPointConsumeMonth(param);
		return successCreated(ResultCode.SUCCESS);
	}
	
	@RequestMapping(value = "selectReportAreaPointConsumeMonth", method = RequestMethod.GET)
	public Result<ReportAreaPointConsumeMonthForCircleDTO> selectReportAreaPointConsumeMonth(@RequestParam("cityId") Integer cityId,@RequestParam("bdate") String bdate,@RequestParam("edate") String edate){
		List<ReportAreaPointConsumeMonthBO> list = reportAreaPointConsumeMonthService.selectReportAreaPointConsumeMonth(cityId, bdate, edate);
		ReportAreaPointConsumeMonthForCircleDTO dto = new ReportAreaPointConsumeMonthForCircleDTO();
		BigDecimal memberPoint = new BigDecimal(0);
		BigDecimal memberRechargePoint = new BigDecimal(0);
		BigDecimal merchantPoint = new BigDecimal(0);
		BigDecimal merchantRechargePoint = new BigDecimal(0);
		BigDecimal totalPoint = new BigDecimal(0);
		BigDecimal totalRechargePoint = new BigDecimal(0);
		if(list != null && !list.isEmpty()) {
			for(ReportAreaPointConsumeMonthBO bo : list) {
				memberPoint = memberPoint.add(bo.getMemberPoint());
				memberRechargePoint = memberRechargePoint.add(bo.getMemberRechargePoint());
				merchantPoint = merchantPoint.add(bo.getMerchantPoint());
				merchantRechargePoint = merchantRechargePoint.add(bo.getMerchantRechargePoint());
				totalRechargePoint = totalRechargePoint.add(bo.getMemberRechargePoint().add(bo.getMemberRechargePoint()));
				totalPoint = totalPoint.add(bo.getMerchantPoint().add(bo.getMemberPoint()));
			}
			dto.setMemberPoint(memberPoint);
			dto.setMemberRechargePoint(memberRechargePoint);
			dto.setMerchantPoint(merchantPoint);
			dto.setMerchantRechargePoint(merchantRechargePoint);
			dto.setTotalPoint(totalPoint);
			dto.setTotalRechargePoint(totalRechargePoint);
		}
		return successGet(dto);
	}

	@RequestMapping(value = "executeCollectReportAreaPointConsumeMonth", method = RequestMethod.GET)
	public Result executeCollectReportAreaPointConsumeMonth(@RequestParam("pageSize") Integer pageSize) {
		reportAreaPointConsumeMonthService.executeCollectReportAreaPointConsumeMonth(pageSize);
		return successGet();
	}
}
