package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.activity.dto.DiamondConfigDTO;
import com.lawu.eshop.activity.dto.PowerTaskConfigDTO;
import com.lawu.eshop.activity.param.DiamondConfigParam;
import com.lawu.eshop.activity.param.MerchantPowerTaskConfigParam;
import com.lawu.eshop.activity.param.PowerTaskConfigParam;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月3日
 */
@FeignClient(value = "activity-srv", path = "richConfig/")
public interface RichConfigService {
	
	/**
	 * 保存动力任务配置
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "savePowerTaskConfig", method = RequestMethod.POST)
    Result savePowerTaskConfig(@RequestBody PowerTaskConfigParam param);
	
	/**
	 * 获取动力任务配置
	 * 
	 * @return
	 */
	@RequestMapping(value = "getPowerTaskConfig", method = RequestMethod.GET)
	Result<PowerTaskConfigDTO> getPowerTaskConfig();

	/**
	 * 保存E钻配置
	 *
	 * @param param
	 * @author meishuquan
	 */
	@RequestMapping(value = "saveDiamondConfig", method = RequestMethod.POST)
	Result saveDiamondConfig(@RequestBody DiamondConfigParam param);

	/**
	 * 获取E钻配置
	 *
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "getDiamondConfig", method = RequestMethod.GET)
	Result<DiamondConfigDTO> getDiamondConfig();

	@RequestMapping(value = "saveMerchantPowerTaskConfig", method = RequestMethod.POST)
	Result saveMerchantPowerTaskConfig(@ModelAttribute MerchantPowerTaskConfigParam param);

}
