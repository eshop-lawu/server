package com.lawu.eshop.mall.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.param.AppFunctionManageParam;
import com.lawu.eshop.mall.srv.bo.AppFunctionManageBO;
import com.lawu.eshop.mall.srv.bo.AppFunctionManageInfoBO;
import com.lawu.eshop.mall.srv.domain.AppFunctionManageDO;
import com.lawu.eshop.mall.srv.domain.AppFunctionManageDOExample;
import com.lawu.eshop.mall.srv.mapper.AppFunctionManageDOMapper;
import com.lawu.eshop.mall.srv.service.AppFunctionManageService;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月11日
 */
@Service
public class AppFunctionManageServiceImpl implements AppFunctionManageService{
	
	@Autowired
	private AppFunctionManageDOMapper appFunctionManageDOMapper;

	@Override
	public AppFunctionManageBO getAppFunctionManage(String appVersion,UserTypeEnum userType) {
		AppFunctionManageDOExample example = new AppFunctionManageDOExample();
		if(userType == UserTypeEnum.MEMBER){
			example.createCriteria().andAppVersionEqualTo(appVersion);
		}else{
			example.createCriteria().andAppMerchantVersionEqualTo(appVersion);
		}
		List<AppFunctionManageDO>  list = appFunctionManageDOMapper.selectByExample(example);
		AppFunctionManageBO bo = new AppFunctionManageBO();
		//版本不匹配，直接返回开启状态
		if(list.isEmpty()){
			return bo;
		}
		AppFunctionManageDO record = list.get(0);
		//如果管理禁用，直接返回开启状态
		if(!record.getIsEnable()){
			return bo;
		}
		bo.setIsEnableGame(record.getIsEnableGame());
		bo.setIsEnableLove(record.getIsEnableLove());
		bo.setIsEnableRich(record.getIsEnableRich());
		bo.setIsEnableMerchantRich(record.getIsEnableMerchantRich());
		return bo;
	}

	@Override
	public AppFunctionManageInfoBO getAppFunctionManageInfo() {
		List<AppFunctionManageDO>  list = appFunctionManageDOMapper.selectByExample(null);
		AppFunctionManageInfoBO recordBO = new AppFunctionManageInfoBO();
		if(list.isEmpty()) return recordBO;
		AppFunctionManageDO record = list.get(0);
		recordBO.setAppVersion(record.getAppVersion());
		recordBO.setId(record.getId());
		recordBO.setIsEnable(record.getIsEnable());
		recordBO.setIsEnableGame(record.getIsEnableGame());
		recordBO.setIsEnableLove(record.getIsEnableLove());
		recordBO.setIsEnableRich(record.getIsEnableRich());
		recordBO.setIsEnableMerchantRich(record.getIsEnableMerchantRich());
		recordBO.setAppMerchantVersion(record.getAppMerchantVersion());
		return recordBO;
	}

	@Override
	public void saveAppFunctionManage(AppFunctionManageParam param) {
		AppFunctionManageDO record = new AppFunctionManageDO();
		record.setAppVersion(param.getAppVersion());
		record.setAppMerchantVersion(param.getAppMerchantVersion());
		record.setIsEnableGame(param.getIsEnableGame());
		record.setIsEnableLove(param.getIsEnableLove());
		record.setIsEnableRich(param.getIsEnableRich());
		record.setIsEnable(param.getIsEnable());
		record.setIsEnableMerchantRich(param.getIsEnableMerchantRich());
		if(param.getId() != null && param.getId()>0){
			record.setId(param.getId());
			record.setGmtModified(new Date());
			appFunctionManageDOMapper.updateByPrimaryKeySelective(record);
			return ;
			
		}
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		appFunctionManageDOMapper.insertSelective(record);
		
	}
	
	

}
