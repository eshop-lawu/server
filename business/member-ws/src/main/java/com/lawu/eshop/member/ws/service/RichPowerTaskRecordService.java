package com.lawu.eshop.member.ws.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
