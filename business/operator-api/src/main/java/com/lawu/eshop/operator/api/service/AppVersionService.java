package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.constants.AppStatusEnum;
import com.lawu.eshop.mall.dto.AppVersionOperatorDTO;
import com.lawu.eshop.mall.param.AppVersionOperatorParam;
import com.lawu.eshop.mall.param.AppVersionParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "mall-srv")
public interface AppVersionService {

	
	 @RequestMapping(value = "appVersion/saveAppVersion", method = RequestMethod.POST)
	 Result saveAppVersion(@RequestBody AppVersionParam param);
	 
	 @RequestMapping(value = "appVersion/updateAppVersion/{id}", method = RequestMethod.PUT)
	 Result updateAppVersion(@PathVariable("id") Integer id ,@RequestParam("statusEnum") AppStatusEnum statusEnum);
	 
	 @RequestMapping(value = "appVersion/getVersionOperator", method = RequestMethod.POST)
	 Result< Page<AppVersionOperatorDTO>> getVersionOperator(@RequestBody AppVersionOperatorParam query);
	
}
