package com.lawu.eshop.mall.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.constants.AppStatusEnum;
import com.lawu.eshop.mall.dto.AppVersionDTO;
import com.lawu.eshop.mall.dto.AppVersionOperatorDTO;
import com.lawu.eshop.mall.param.AppVersionOperatorParam;
import com.lawu.eshop.mall.param.AppVersionParam;
import com.lawu.eshop.mall.srv.bo.AppVersionBO;
import com.lawu.eshop.mall.srv.bo.AppVersionOperatorBO;
import com.lawu.eshop.mall.srv.converter.AppVersionConverter;
import com.lawu.eshop.mall.srv.service.AppVersionService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "appVersion")
public class AppVersionController extends BaseController{

	
	@Autowired
    private AppVersionService appVersionService;

    @RequestMapping(value = "getAppVersion/{appType}", method = RequestMethod.GET)
    public Result<AppVersionDTO> getAppVersion(@PathVariable byte appType,@RequestParam byte mobileType) {
    	AppVersionBO bo = appVersionService.getVersion(mobileType,appType);
    	AppVersionDTO dto = new AppVersionDTO();
    	if(bo != null) {
    		dto.setAppVersionCode(Integer.valueOf(bo.getAppBigVersion()));
    		dto.setAppVersion(bo.getAppVersion());
    		dto.setIsForce(bo.getIsForce());
    		dto.setUpdateDesc(bo.getUpdateDesc());
    	}
        return successCreated(dto);
    }
    
    @RequestMapping(value = "saveAppVersion", method = RequestMethod.POST)
	public Result saveAppVersion(@RequestBody AppVersionParam param){
    	
    	Boolean flag = appVersionService.isExistEnable(param.getMobileType().val,param.getAppType().val);
    	if(flag){ //存在正在启用的状态
    		return successCreated(ResultCode.EXISTS_ENABLE_APP_VERSION);
    	}else{
    		appVersionService.saveAppVersion(param);
    	}
    	return successCreated();
    }
    
    @RequestMapping(value = "updateAppVersion/{id}", method = RequestMethod.PUT)
	public Result updateAppVersion(@PathVariable Integer id ,@RequestParam AppStatusEnum statusEnum){
    	
    	Boolean flag = false;
    	if(statusEnum.val==AppStatusEnum.ENABLE.val){ //启用  判断是否存在已经启用的版本
    		flag = appVersionService.isExistEnable(id);
    	}
    	if(flag){ //存在正在启用的状态
    		return successCreated(ResultCode.EXISTS_ENABLE_APP_VERSION);
    	}else{
    		appVersionService.updateAppVersion(id, statusEnum);
    	}
    	return successCreated();
    }
    
    @RequestMapping(value = "getVersionOperator", method = RequestMethod.POST)
	public Result< Page<AppVersionOperatorDTO>> getVersionOperator(@RequestBody AppVersionOperatorParam query){
    	
    	 Page<AppVersionOperatorBO> page = appVersionService.getVersionOperator(query);
    	 
    	 Page<AppVersionOperatorDTO> pageDTO = new Page<>();
    	 pageDTO.setCurrentPage(page.getCurrentPage());
    	 pageDTO.setTotalCount(page.getTotalCount());
    	 pageDTO.setRecords(AppVersionConverter.converterDTOS(page.getRecords()));
    	 
    	 return successGet(pageDTO);
    }
}
