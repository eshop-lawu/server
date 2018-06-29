package com.lawu.eshop.mall.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.dto.AppFunctionManageDTO;
import com.lawu.eshop.mall.dto.AppFunctionManageInfoDTO;
import com.lawu.eshop.mall.param.AppFunctionManageParam;
import com.lawu.eshop.mall.srv.bo.AppFunctionManageBO;
import com.lawu.eshop.mall.srv.bo.AppFunctionManageInfoBO;
import com.lawu.eshop.mall.srv.service.AppFunctionManageService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月11日
 */
@RestController
@RequestMapping(value = "appFunctionManage/")
public class AppFunctionManageController extends BaseController{
	
	@Autowired AppFunctionManageService appFunctionManageService;
	
	/**
	 * 获取功能开启状态
	 * @param appVersion
	 * @return
	 */
	@RequestMapping(value = "getAppFunctionManage", method = RequestMethod.GET)
    public Result<AppFunctionManageDTO> getAppFunctionManage(@RequestParam String appVersion,@RequestParam UserTypeEnum userType) {
		AppFunctionManageBO recordBO = appFunctionManageService.getAppFunctionManage(appVersion,userType);
		AppFunctionManageDTO recoredDTO = new AppFunctionManageDTO();
		recoredDTO.setIsEnableGame(recordBO.getIsEnableGame());
		recoredDTO.setIsEnableLove(recordBO.getIsEnableLove());
		recoredDTO.setIsEnableRich(recordBO.getIsEnableRich());
		recoredDTO.setIsEnableMerchantRich(recordBO.getIsEnableMerchantRich());
        return successGet(recoredDTO);
    }

	/**
	 * 查询app功能信息
	 * @return
	 */
	@RequestMapping(value = "getAppFunctionManageInfo", method = RequestMethod.GET)
    public Result<AppFunctionManageInfoDTO> getAppFunctionManage() {
		AppFunctionManageInfoBO recordBO = appFunctionManageService.getAppFunctionManageInfo();
		AppFunctionManageInfoDTO recoredDTO = new AppFunctionManageInfoDTO();
		recoredDTO.setIsEnableGame(recordBO.getIsEnableGame());
		recoredDTO.setIsEnableLove(recordBO.getIsEnableLove());
		recoredDTO.setIsEnableRich(recordBO.getIsEnableRich());
		recoredDTO.setAppVersion(recordBO.getAppVersion());
		recoredDTO.setIsEnable(recordBO.getIsEnable());
		recoredDTO.setId(recordBO.getId());
		recoredDTO.setIsEnableMerchantRich(recordBO.getIsEnableMerchantRich());
		recoredDTO.setAppMerchantVersion(recordBO.getAppMerchantVersion());
        return successGet(recoredDTO);
    }
	
	
	/**
	 * 保存
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "saveAppFunctionManage", method = RequestMethod.POST)
	Result saveAppFunctionManage(@RequestBody AppFunctionManageParam param){
		appFunctionManageService.saveAppFunctionManage(param);
		return successCreated();
	}
}
