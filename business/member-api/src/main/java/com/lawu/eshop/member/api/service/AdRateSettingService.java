package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.ad.dto.AdRateSettingDTO;
import com.lawu.framework.web.Result;

@FeignClient(value = "ad-srv")
public interface AdRateSettingService {
	
	/**
	 * 咻一咻中奖率配置数据
	 * @return
	 */
	@RequestMapping(value = "adRateSetting/queryAdRateSetting", method = RequestMethod.GET)
	Result<List<AdRateSettingDTO>> queryAdRateSetting();

}
