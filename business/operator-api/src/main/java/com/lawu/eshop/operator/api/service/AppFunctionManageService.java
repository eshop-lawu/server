package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.mall.dto.AppFunctionManageInfoDTO;
import com.lawu.eshop.mall.param.AppFunctionManageParam;
import com.lawu.framework.web.Result;

@FeignClient(value= "mall-srv")
public interface AppFunctionManageService {
	
   @RequestMapping(value = "appFunctionManage/getAppFunctionManageInfo", method = RequestMethod.GET)
   Result<AppFunctionManageInfoDTO> getAppFunctionManageInfo();

   @RequestMapping(value = "appFunctionManage/saveAppFunctionManage", method = RequestMethod.POST)
   Result saveAppFunctionManage(@RequestBody AppFunctionManageParam param);

}
