package com.lawu.eshop.order.srv.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.order.dto.ReportRiseRateDTO;
import com.lawu.eshop.order.param.ReportDataParam;
import com.lawu.eshop.order.srv.service.ReportPayService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 买单交易统计
 * </p>
 * 
 * @author Yangqh
 * @date 2017年5月3日 下午12:01:54
 *
 */
@RestController
@RequestMapping(value = "reportPay/")
public class ReportPayController extends BaseController {

	@Autowired
	private ReportPayService reportPayService;

	@RequestMapping(value = "payVolumeRiseRate", method = RequestMethod.POST)
	public Result<ReportRiseRateDTO> payVolumeRiseRate(@RequestBody @Valid ReportDataParam dparam, BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	
		ReportRiseRateDTO dto = reportPayService.payVolumeRiseRate(dparam);
		return successCreated(dto);
	}
}
