package com.lawu.eshop.agent.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.agent.api.service.ReportAreaPointConsumeMonthService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.statistics.dto.ReportAreaPointConsumeMonthForCircleDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;

/**
 * @author hongqm
 * @date 2017/8/15
 */
@Api(tags = "reportAreaPointConsumeMonth")
@RestController
@RequestMapping(value = "reportAreaPointConsumeMonth/")
public class ReportAreaPointConsumeMonthController extends BaseController{
	
	@Autowired
	private ReportAreaPointConsumeMonthService reportAreaPointConsumeMonthService;

	/**
	 * 区域积分消费月查询
	 * @param token
	 * @param param
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "getReportAreaPointConsumeMonth", method = RequestMethod.GET)
	public Result<ReportAreaPointConsumeMonthForCircleDTO> getReportAreaPointConsumeMonth(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
															  @ModelAttribute @Valid AgentReportParam param, BindingResult result){
		String message = validate(result);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		return reportAreaPointConsumeMonthService.selectReportAreaPointConsumeMonth(new Integer(param.getRegionPath().split("/")[1]), param.getBeginTime()+"-01", param.getEndTime()+"-01");
	}
}
