package com.lawu.eshop.user.srv.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.dto.ReportRiseRateDTO;
import com.lawu.eshop.user.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.user.param.ReportDataParam;
import com.lawu.eshop.user.srv.service.ReportFansService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author Yangqh
 * @date 2017年5月2日 下午2:36:03
 *
 */
@RestController
@RequestMapping(value = "reportFans/")
public class ReportFnasController extends BaseController {

	@Autowired
	private ReportFansService reportFansService;
	
	@RequestMapping(value = "fansRiseRate", method = RequestMethod.POST)
	public Result<ReportRiseRateDTO> fansRiseRate(@RequestBody @Valid ReportDataParam dparam,
			BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
		
		ReportRiseRateDTO dto = reportFansService.fansRiseRate(dparam);
		return successCreated(dto);
	}
	
	/**
	 * 增长来源
	 * @param dparam
	 * @param result
	 * @return
	 * @author yangqh
	 * @date 2017年5月2日 下午7:16:27
	 */
	@RequestMapping(value = "fansRiseSource", method = RequestMethod.POST)
	public Result<List<ReportRiseRerouceDTO>> fansRiseSource(@RequestBody @Valid ReportDataParam dparam,
			BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
		
		List<ReportRiseRerouceDTO> dtos = reportFansService.fansRiseSource(dparam);
		return successCreated(dtos);
	}

}
