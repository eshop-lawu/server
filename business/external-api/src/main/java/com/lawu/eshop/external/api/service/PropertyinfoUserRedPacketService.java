package com.lawu.eshop.external.api.service;

import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * <p>
 * Description: 用户红包
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月13日 下午1:58:23
 *
 */
@FeignClient(value= "property-srv")
public interface PropertyinfoUserRedPacketService {

	/**
	 * 处理第三方用户红包回调
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "userRedPacket/doHandleMemberRedPacketNotify")
	Result doHandleMemberRedPacketNotify(@RequestBody NotifyCallBackParam param);
}
