package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.dto.RichPowerTaskDTO;
import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.framework.web.Result;

@FeignClient(value = "activity-srv" , path="richPowerTaskRecord/")
public interface RichPowerTaskRecordService {
	
	/**
	 * 更新动力任务完成情况记录
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "saveRichPowerTaskRecord", method = RequestMethod.POST)
    Result saveRichPowerTaskRecord(@RequestBody RichPowerTaskRecordParam param);
	
	/**
	 * 获取所有动力任务
	 * 
	 * @param memberNum
	 * @return
	 */
	@RequestMapping(value = "getPowerTasks", method = RequestMethod.GET)
    Result<RichPowerTaskDTO> getPowerTasks(@RequestParam("memberNum") String memberNum);

	/**
	 * 更新动力任务完成情况记录(商家)
	 *
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "richMerchantPowerTaskRecord/saveRichMerchantPowerTaskRecord", method = RequestMethod.POST)
	Result saveRichMerchantPowerTaskRecord(@RequestBody RichMerchantPowerTaskRecordParam param);

}
