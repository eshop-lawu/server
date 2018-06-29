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
import com.lawu.eshop.statistics.dto.ReportAreaWithdrawDTO;
import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;
import com.lawu.eshop.statistics.dto.ReportWithdrawDailyDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.param.AgentWithdrawCashParam;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaWithdrawBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaWithdrawDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportWithdrawDailyBO;
import com.lawu.eshop.statistics.srv.service.WithdrawCashService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年6月28日 下午5:42:20
 *
 */
@RestController
@RequestMapping(value = "withdrawCash/")
public class WithdrawCashController extends BaseController {

	@Autowired
	private WithdrawCashService withdrawCashService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveDaily", method = RequestMethod.POST)
	public Result saveDaily(@RequestBody @Valid ReportKCommonParam param, BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	withdrawCashService.saveDaily(param);
		return successCreated(ResultCode.SUCCESS);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveMonth", method = RequestMethod.POST)
	public Result saveMonth(@RequestBody @Valid ReportKCommonParam param, BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	withdrawCashService.saveMonth(param);
		return successCreated(ResultCode.SUCCESS);
	}
	
	@RequestMapping(value = "getDailyList", method = RequestMethod.GET)
	public Result<List<ReportWithdrawDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate) {
		List<ReportWithdrawDailyBO> rntList = withdrawCashService.getDailyList(reportDate);
		List<ReportWithdrawDailyDTO> dtoList = new ArrayList<>();
		for(ReportWithdrawDailyBO rdo : rntList){
			ReportWithdrawDailyDTO dto = new ReportWithdrawDailyDTO();
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
		withdrawCashService.deleteDailyByReportDate(reportDate);
		return successCreated(ResultCode.SUCCESS);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "deleteMonthByReportDate", method = RequestMethod.DELETE)
	public Result deleteMonthByReportDate(@RequestParam("reportDate") String reportDate) {
		withdrawCashService.deleteMonthByReportDate(reportDate);
		return successCreated(ResultCode.SUCCESS);
	}
	
	/**
	 * 
	 * @param bdate 时间条件
	 * @param edate
	 * @return
	 * @author yangqh
	 * @date 2017年7月3日 上午10:28:40
	 */
	@RequestMapping(value = "selectReport", method = RequestMethod.GET)
	public ReportCommonBackDTO selectReport(@RequestParam("bdate") String bdate,@RequestParam("edate") String edate) {
		return withdrawCashService.selectReport(bdate,edate);
	}

	@RequestMapping(method = RequestMethod.POST, value = "saveAgentDaily")
	public Result saveAgentDaily(@RequestBody AgentWithdrawCashParam param){
		withdrawCashService.saveAgentDaily(param);
		return successCreated(ResultCode.SUCCESS);
	}

	@RequestMapping(method = RequestMethod.GET, value = "withdrawCash/selectReportAreaWithdrawCashList")
	Result<List<ReportWithdrawDailyDTO>> selectReportAreaWithdrawCashList(
			@RequestParam("month") String month, @RequestParam("cityId") Integer cityId){
		List<ReportAreaWithdrawDailyBO> rntList = withdrawCashService.selectReportAreaWithdrawCashList(month,cityId);
		if(rntList.isEmpty()){
			return successGet(new ArrayList<>());
		}
		List<ReportWithdrawDailyDTO> dailyDTOS = new ArrayList<>();
		ReportWithdrawDailyDTO reportWithdrawDailyDTO;
		for (ReportAreaWithdrawDailyBO dailyBO : rntList) {
			reportWithdrawDailyDTO = new ReportWithdrawDailyDTO();
			reportWithdrawDailyDTO.setId(dailyBO.getId());
			reportWithdrawDailyDTO.setTotalMoney(dailyBO.getTotalMoney());
			reportWithdrawDailyDTO.setMerchantMoney(dailyBO.getMerchantMoney());
			reportWithdrawDailyDTO.setMemberMoney(dailyBO.getMemberMoney());
			reportWithdrawDailyDTO.setGmtReport(dailyBO.getGmtReport());
			dailyDTOS.add(reportWithdrawDailyDTO);
		}
		return successGet(dailyDTOS);
	}

	@RequestMapping(method = RequestMethod.POST, value = "saveAgentMonth")
	Result saveAgentMonth(@RequestBody AgentWithdrawCashParam param){
		withdrawCashService.saveAgentMonth(param);
		return successCreated(ResultCode.SUCCESS);
	}

	/**
	 * 代理商日统计提现查询
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "selectAreaWithdrawDailyReport", method = RequestMethod.POST)
	Result<ReportAreaWithdrawDTO> selectAreaWithdrawDailyReport(@RequestBody AgentReportParam param) {
		ReportAreaWithdrawBO withdrawBO = withdrawCashService.selectAreaWithdrawDailyReport(param);
		ReportAreaWithdrawDTO withdrawDTO = new ReportAreaWithdrawDTO();
		withdrawDTO.setMerchantMoney(withdrawBO.getMerchantMoney());
		withdrawDTO.setMemberMoney(withdrawBO.getMemberMoney());
		withdrawDTO.setTotalMoney(withdrawBO.getTotalMoney());
		return successCreated(withdrawDTO);
	}

	/**
	 * 代理商月统计提现查询
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "selectAreaWithdrawMonthReport", method = RequestMethod.POST)
	Result<ReportAreaWithdrawDTO> selectAreaWithdrawMonthReport(@RequestBody AgentReportParam param) {
		ReportAreaWithdrawBO withdrawBO = withdrawCashService.selectAreaWithdrawMonthReport(param);
		ReportAreaWithdrawDTO withdrawDTO = new ReportAreaWithdrawDTO();
		withdrawDTO.setMerchantMoney(withdrawBO.getMerchantMoney());
		withdrawDTO.setMemberMoney(withdrawBO.getMemberMoney());
		withdrawDTO.setTotalMoney(withdrawBO.getTotalMoney());
		return successCreated(withdrawDTO);
	}

	
	/**
	 * 获取统计的最后一条提现日统计的数据
	 * @return
	 */
	@RequestMapping(value = "getLastReportWithdraw", method = RequestMethod.GET)
	Result<Date> getLastReportWithdraw() {
		Date date = withdrawCashService.getLastReportWithdraw();
		return successCreated(date);
	}
	
	
	/**
	 * 获取统计的最后一条提现月统计的数据
	 * @return
	 */
	@RequestMapping(value = "getLastReportWithdrawMonth", method = RequestMethod.GET)
	Result<Date> getLastReportWithdrawMonth() {
		Date date = withdrawCashService.getLastReportWithdrawMonth();
		return successCreated(date);
	}
}
