package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.user.dto.AddressDTO;
import com.lawu.eshop.user.param.OperatorAddressParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月14日
 */
@FeignClient(value = "user-srv")
public interface AddressService {
	
	@RequestMapping(value = "address/operatorAddressPage", method = RequestMethod.POST)
	Result<Page<AddressDTO>> operatorAddressPage(@RequestBody OperatorAddressParam param);

}
