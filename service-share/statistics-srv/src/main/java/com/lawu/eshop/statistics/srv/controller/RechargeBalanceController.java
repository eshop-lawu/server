package com.lawu.eshop.statistics.srv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.statistics.dto.RechargeBalanceDailyDTO;
import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.eshop.statistics.srv.bo.RechargeBalanceDailyBO;
import com.lawu.eshop.statistics.srv.service.RechargeBalanceService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年6月29日 下午4:53:16
 *
 */
@RestController
@RequestMapping(value = "rechargeBalance/")
public class RechargeBalanceController extends BaseController {

	@Autowired
	private RechargeBalanceService rechargeBalanceService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveDaily", method = RequestMethod.POST)
	public Result saveDaily(@RequestBody @Valid ReportKCommonParam param, BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	rechargeBalanceService.saveDaily(param);
		return successCreated(ResultCode.SUCCESS);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveMonth", method = RequestMethod.POST)
	public Result saveMonth(@RequestBody @Valid ReportKCommonParam param, BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	rechargeBalanceService.saveMonth(param);
		return successCreated(ResultCode.SUCCESS);
	}
	
	@RequestMapping(value = "getDailyList", method = RequestMethod.GET)
	public Result<List<RechargeBalanceDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate) {
		List<RechargeBalanceDailyBO> rntList = rechargeBalanceService.getDailyList(reportDate);
		List<RechargeBalanceDailyDTO> dtoList = new ArrayList<>();
		for(RechargeBalanceDailyBO rdo : rntList){
			RechargeBalanceDailyDTO dto = new RechargeBalanceDailyDTO();
			dto.setGmtCreate(rdo.getGmtCreate());
			dto.setGmtReport(rdo.getGmtReport());
			dto.setId(rdo.getId());
			dto.setMemberMoney(rdo.getMemberMoney());
			dto.setMerchantMoney(rdo.getMerchantMoney());
			dto.setTotalMoney(rdo.getTotalMoney());
			dtoList.add(dto);
		}
		return successCreated(dtoList);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "deleteDailyByReportDate", method = RequestMethod.DELETE)
	public Result deleteDailyByReportDate(@RequestParam("reportDate") String reportDate) {
		rechargeBalanceService.deleteDailyByReportDate(reportDate);
		return successCreated(ResultCode.SUCCESS);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "deleteMonthByReportDate", method = RequestMethod.DELETE)
	public Result deleteMonthByReportDate(@RequestParam("reportDate") String reportDate) {
		rechargeBalanceService.deleteMonthByReportDate(reportDate);
		return successCreated(ResultCode.SUCCESS);
	}
	
	/**
	 * 
	 * @param bdate
	 * @param edate
	 * @return
	 * @author yangqh
	 * @date 2017年7月3日 下午3:22:46
	 */
	@RequestMapping(value = "selectReport", method = RequestMethod.GET)
	public ReportCommonBackDTO selectReport(@RequestParam("bdate") String bdate,@RequestParam("edate") String edate) {
		return rechargeBalanceService.selectReport(bdate,edate);
	}
	
	/**
	 * 获取最后一次统计日充值余额的日期
	 * @return
	 */
	@RequestMapping(value = "getLastRechargeDay", method = RequestMethod.GET)
	public Date getLastRechargeDay() {
		return rechargeBalanceService.getLastRechargeDay();
	}
	
	/**
	 * 获取最后一次统计月充值余额的日期
	 * @return
	 */
	@RequestMapping(value = "getLastRechargeMonth", method = RequestMethod.GET)
	public Date getLastRechargeMonth() {
		return rechargeBalanceService.getLastRechargeMonth();
	}
}
