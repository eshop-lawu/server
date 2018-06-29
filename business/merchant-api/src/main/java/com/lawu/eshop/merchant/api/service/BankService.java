package com.lawu.eshop.merchant.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.BankDTO;
import com.lawu.framework.web.Result;

/**
 * merchant pai 银行数据接口
 * @author zhangrc
 * @date 2017/03/29
 */
@FeignClient(value= "property-srv")
public interface BankService {
	
	@RequestMapping(method = RequestMethod.GET, value = "bank/findBank")
    Result<List<BankDTO>> findBank();

}
