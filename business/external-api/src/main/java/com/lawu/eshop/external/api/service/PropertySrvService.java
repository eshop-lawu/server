package com.lawu.eshop.external.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月12日 下午6:34:39
 *
 */
@FeignClient(value= "property-srv")
public interface PropertySrvService {
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET, value = "property/getValue")
	Result getValue(@RequestParam("name") String name);
	
	/**
	 * 获取用户积分余额
	 * @param userNum
	 * @return
	 * @author yangqh
	 * @date 2017年5月22日 下午2:27:44
	 */
	@RequestMapping(method = RequestMethod.GET, value = "propertyInfo/getPropertyInfoMoney/{userNum}")
	Result<PropertyPointAndBalanceDTO> getPropertyInfoMoney(@PathVariable("userNum") String userNum);
}
