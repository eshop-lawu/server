package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.dto.MerchantRichPowerTaskDTO;
import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.framework.web.Result;

@FeignClient(value = "activity-srv")
public interface RichPowerTaskRecordService {
	
	/**
	 * 更新动力任务完成情况记录(用户)
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "richPowerTaskRecord/saveRichPowerTaskRecord", method = RequestMethod.POST)
    Result saveRichPowerTaskRecord(@RequestBody RichPowerTaskRecordParam param);
	
	
	/**
	 * 更新动力任务完成情况记录(商家)
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "richMerchantPowerTaskRecord/saveRichMerchantPowerTaskRecord", method = RequestMethod.POST)
    Result saveRichMerchantPowerTaskRecord(@RequestBody RichMerchantPowerTaskRecordParam param);

	/**
	 * 商家端获取动力任务列表
	 * @param memberNum
	 * @return
	 */
	@RequestMapping(value = "richMerchantPowerTaskRecord/getPowerTasks", method = RequestMethod.GET)
	Result<MerchantRichPowerTaskDTO> getPowerTasks(@RequestParam("memberNum") String memberNum);

}
