package com.lawu.eshop.external.api.service;

import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.framework.web.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "activity-srv")
public interface RichPowerTaskRecordService {
	
	/**
	 * 更新动力任务完成情况记录(商家)
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "richMerchantPowerTaskRecord/saveRichMerchantPowerTaskRecord", method = RequestMethod.POST)
    Result saveRichMerchantPowerTaskRecord(@RequestBody RichMerchantPowerTaskRecordParam param);

}
