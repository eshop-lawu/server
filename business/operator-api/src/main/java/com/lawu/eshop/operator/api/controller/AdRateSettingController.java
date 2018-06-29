package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.AdRateSettingDTO;
import com.lawu.eshop.ad.param.RateParam;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.AdRateSettingService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

	@SuppressWarnings("unchecked")
	@Audit(date = "2017-11-24", reviewer = "孙林青")
	@ApiOperation(value = "咻一咻中奖率列表", notes = "列表数量（张荣成）", httpMethod = "GET")
	@RequiresPermissions("rate:list")
	@PageBody
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "queryAdRateSetting", method = RequestMethod.GET)
	public Result<Page<AdRateSettingDTO>> queryAdRateSetting( @ModelAttribute @ApiParam(value = "查询信息") RateParam param) {
	    Result<Page<AdRateSettingDTO>> result = adRateSettingService.queryRatePage(param);
		return successGet(result);
	}


	@Audit(date = "2017-11-24", reviewer = "孙林青")
	@ApiOperation(value = "保存咻一咻中奖率", notes = "保存咻一咻中奖率（张荣成）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("rate:save")
	@RequestMapping(value = "saveRateSetting", method = RequestMethod.POST)
	public Result saveRateSetting( @RequestParam @ApiParam(required = true, value = "时间") int gameTime,
			 @RequestParam @ApiParam(required = true, value = "概率") int rate) {
		return adRateSettingService.saveRateSetting(gameTime, rate);
	}


	@Audit(date = "2017-11-24", reviewer = "孙林青")
	@ApiOperation(value = "删除咻一咻中奖率", notes = "删除（张荣成）", httpMethod = "DELETE")
	@RequiresPermissions("rate:delete")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "deleteRateSetting/{id}", method = RequestMethod.DELETE)
	public Result deleteRateSetting(@PathVariable @ApiParam(required = true, value = "广告id") Long id) {
		adRateSettingService.deleteRateSetting(id);
		return successDelete();
	}

}
