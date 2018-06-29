package com.lawu.eshop.activity.srv.servcie;

import com.lawu.eshop.activity.param.DiamondConfigParam;
import com.lawu.eshop.activity.param.MerchantPowerTaskConfigParam;
import com.lawu.eshop.activity.param.PowerTaskConfigParam;
import com.lawu.eshop.activity.srv.bo.DiamondConfigBO;
import com.lawu.eshop.activity.srv.bo.PowerTaskConfigBO;
import com.lawu.eshop.activity.srv.bo.RichConfigBO;

/**
 * 配置
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public interface RichConfigService {
	
	/**
	 * 保存动力任务配置
	 * 
	 * @param param
	 */
	void savePowerTaskConfig(PowerTaskConfigParam param);
	
	/**
	 * 定时任务配置生效
	 * 
	 */
	void effectiveJob();
	
	
	/**
	 * 定时任务配置生效
	 * 
	 */
	void effectivePowerJob();
	
	
	/**
	 * 动力任务配置详情
	 * @return
	 */
	PowerTaskConfigBO getPowerTaskConfig();
	
	/**
	 * 获取瑞奇岛配置
	 * @return
	 */
	RichConfigBO getRichConfig();

	/**
	 * 保存E钻配置
	 *
	 * @param param
	 * @author meishuquan
	 */
	void saveDiamondConfig(DiamondConfigParam param);

	/**
	 * 获取E钻配置
	 *
	 * @return
	 * @author meishuquan
	 */
	DiamondConfigBO getDiamondConfig();

	void saveMerchantPowerTaskConfig(MerchantPowerTaskConfigParam param);
}
