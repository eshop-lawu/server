package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.DiamondConfigDTO;
import com.lawu.eshop.activity.dto.PowerTaskConfigDTO;
import com.lawu.eshop.activity.param.DiamondConfigParam;
import com.lawu.eshop.activity.param.MerchantPowerTaskConfigParam;
import com.lawu.eshop.activity.param.PowerTaskConfigParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.RichConfigService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月3日
 */
@Api(tags = "richConfig")
@RestController
@RequestMapping(value = "richConfig/")
public class RichConfigController extends BaseController {
	
	@Autowired
	private RichConfigService richConfigService;
	
	
	@LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.RICH_CONFIG_ADD)
    @ApiOperation(value = "保存动力任务配置", notes = "保存动力任务配置（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("powerTask:save")
    @RequestMapping(value = {"savePowerTaskConfig"}, method = RequestMethod.POST)
	public Result savePowerTaskConfig(@RequestBody @ApiParam PowerTaskConfigParam param , BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		Result result = richConfigService.savePowerTaskConfig(param);
		return successCreated();
	}

	
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "查询动力任务配置", notes = "查询动力任务配置（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("powerTask:get")
    @RequestMapping(value = {"getPowerTaskConfig"}, method = RequestMethod.GET)
	public Result<PowerTaskConfigDTO> getPowerTaskConfig() {
		Result<PowerTaskConfigDTO> result =richConfigService.getPowerTaskConfig();
	    return successGet(result);
	}

	@LogRecord(type = OperationTypeEnum.ADD, title = LogTitleEnum.DIAMOND_CONFIG_ADD)
	@ApiOperation(value = "保存E钻配置", notes = "保存E钻配置（梅述全）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequiresPermissions("diamondConfig:save")
	@RequestMapping(value = "saveDiamondConfig", method = RequestMethod.POST)
	public Result saveDiamondConfig(@ModelAttribute @ApiParam DiamondConfigParam param) {
		richConfigService.saveDiamondConfig(param);
		return successCreated();
	}

	@ApiOperation(value = "获取E钻配置", notes = "获取E钻配置（梅述全）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getDiamondConfig", method = RequestMethod.GET)
	public Result<DiamondConfigDTO> getDiamondConfig() {
		Result<DiamondConfigDTO> result = richConfigService.getDiamondConfig();
		return successGet(result);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询商家动力任务配置", notes = "查询商家动力任务配置（章勇）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("powerTask:list")
	@RequestMapping(value = "getMerchantPowerTaskConfig", method = RequestMethod.GET)
	public Result<PowerTaskConfigDTO> getMerchantPowerTaskConfig() {
		Result<PowerTaskConfigDTO> result = richConfigService.getPowerTaskConfig();
		return successGet(result);
	}

	@LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.RICH_CONFIG_ADD)
	@ApiOperation(value = "保存商家动力任务配置", notes = "保存商家动力任务配置（章勇）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequiresPermissions("powerTask:save")
	@RequestMapping(value = "saveMerchantPowerTaskConfig", method = RequestMethod.POST)
	public Result saveMerchantPowerTaskConfig(@RequestBody @ApiParam MerchantPowerTaskConfigParam param , BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		richConfigService.saveMerchantPowerTaskConfig(param);
		return successCreated();
	}
	
}
