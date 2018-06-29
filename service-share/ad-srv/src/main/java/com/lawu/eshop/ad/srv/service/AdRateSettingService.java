package com.lawu.eshop.ad.srv.service;

import java.util.List;

import com.lawu.eshop.ad.param.RateParam;
import com.lawu.eshop.ad.srv.bo.AdRateSettingBO;
import com.lawu.framework.core.page.Page;

/**
 * 咻一咻中奖率配置
 * 
 * @author zhangrc
 * @date 2017/11/23
 *
 */
public interface AdRateSettingService {
	
	/**
	 * 中奖率配置数据
	 * @return
	 */
	List<AdRateSettingBO> queryAdRateSetting();
	
	/**
	 * 中奖率设置
	 * @param gameTime
	 * @param rate
	 */
	void saveRateSetting(int gameTime , int rate);
	
	/**
	 * 删除中奖率
	 * @param id
	 */
	void deleteRateSetting(Long id);
	
	/**
	 * 平台查询中奖率
	 * @param param
	 * @return
	 */
	Page<AdRateSettingBO> queryRatePage(RateParam param);

}
