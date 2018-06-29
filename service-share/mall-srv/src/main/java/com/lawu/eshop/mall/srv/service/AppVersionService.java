package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.mall.constants.AppStatusEnum;
import com.lawu.eshop.mall.param.AppVersionOperatorParam;
import com.lawu.eshop.mall.param.AppVersionParam;
import com.lawu.eshop.mall.srv.bo.AppVersionBO;
import com.lawu.eshop.mall.srv.bo.AppVersionOperatorBO;
import com.lawu.framework.core.page.Page;

public interface AppVersionService {

	/**
	 * 版本查询
	 * @param mobileType
	 * @param appType
	 * @return
	 */
	AppVersionBO getVersion(byte mobileType , byte appType);
	
	/**
	 * 版本保存
	 * @param param
	 */
	void saveAppVersion(AppVersionParam param);
	
	/**
	 * 修改版本状态  删除|启用|禁用
	 * @param id
	 * @param statusEnum
	 */
	void updateAppVersion(Integer id,AppStatusEnum statusEnum);
	
	/**
	 * 平台列表查询
	 * @param query
	 * @return
	 */
	Page<AppVersionOperatorBO> getVersionOperator(AppVersionOperatorParam query);
	
	/**
	 * 判断是否存在有启用的版本
	 * @param mobileType
	 * @param appType
	 * @return
	 */
	Boolean isExistEnable(byte mobileType , byte appType);
	
	/**
	 * 判断是否存在有启用的版本
	 * @param id
	 * @return
	 */
	Boolean isExistEnable(Integer id);
	
}
