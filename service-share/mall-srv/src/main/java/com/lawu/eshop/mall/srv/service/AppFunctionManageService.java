package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.param.AppFunctionManageParam;
import com.lawu.eshop.mall.srv.bo.AppFunctionManageBO;
import com.lawu.eshop.mall.srv.bo.AppFunctionManageInfoBO;

/**
 * app功能管理
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月11日
 */
public interface AppFunctionManageService {
	
	/**
	 * 根据版本号判断功能是否开启
	 * 
	 * @param appVersion
	 * @return
	 */
	AppFunctionManageBO getAppFunctionManage(String appVersion,UserTypeEnum userType);
	
	/**
	 * 获取app功能信息
	 * @return
	 */
	AppFunctionManageInfoBO getAppFunctionManageInfo();

	/**
	 * 保存修改信息
	 * @param param
	 */
	void saveAppFunctionManage(AppFunctionManageParam param);

}
