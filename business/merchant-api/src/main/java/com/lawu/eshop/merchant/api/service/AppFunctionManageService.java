package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.dto.AppFunctionManageDTO;
import com.lawu.framework.web.Result;

@FeignClient(value= "mall-srv")
public interface AppFunctionManageService {
	
	@RequestMapping(value = "appFunctionManage/getAppFunctionManage", method = RequestMethod.GET)
    Result<AppFunctionManageDTO> getAppFunctionManage(@RequestParam("appVersion") String appVersion,@RequestParam("userType") UserTypeEnum userType);

}
