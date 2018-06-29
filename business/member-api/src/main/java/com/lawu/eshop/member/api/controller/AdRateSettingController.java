package com.lawu.eshop.member.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.AdRateSettingDTO;
import com.lawu.eshop.member.api.service.AdRateSettingService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 描述：咻一咻中奖率配置
 *
 * @author zhangrc
 * @date 2017/11/23
 */
@Api(tags = "adRateSetting", value = "咻一咻中奖率配置接口")
@RestController
@RequestMapping(value = "adRateSetting/")
public class AdRateSettingController extends BaseController{
	
	@Autowired
	private AdRateSettingService adRateSettingService;

	@Audit(date = "2017-11-24", reviewer = "孙林青")
	@ApiOperation(value = "咻一咻中奖率", notes = "咻一咻存在高并发，避免频繁访问接口，客户端进行数据缓存,判断概率，并定期重新请求接口[]（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "queryAdRateSetting", method = RequestMethod.GET)
	public Result<List<AdRateSettingDTO>> queryAdRateSetting() {
		return adRateSettingService.queryAdRateSetting();
	}

}
