package com.lawu.eshop.operator.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.AdRateSettingDTO;
import com.lawu.eshop.ad.param.RateParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "ad-srv")
public interface AdRateSettingService {
	
	/**
	 * 咻一咻中奖率配置数据
	 * @return
	 */
	@RequestMapping(value = "adRateSetting/queryRatePage", method = RequestMethod.POST)
	Result<Page<AdRateSettingDTO>> queryRatePage(@RequestBody RateParam param);
	
	/**
	 * 保存中奖率设置
	 * @param gameTime
	 * @param rate
	 * @return
	 */
	@RequestMapping(value = "adRateSetting/saveRateSetting", method = RequestMethod.POST)
	Result saveRateSetting(@RequestParam("gameTime") int gameTime,@RequestParam("rate") int rate);
	
	/**
	 * 删除中奖率设置
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "adRateSetting/deleteRateSetting/{id}", method = RequestMethod.DELETE)
	Result deleteRateSetting(@PathVariable("id") Long id);

}
