package com.lawu.eshop.external.api.service;

import com.lawu.eshop.ad.dto.AdPayInfoDTO;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ad-srv")
public interface AdService {
	
	/**
       * 根据ID查询第三方支付时需要的参数
	 * @param id
       * @return
       */
	@RequestMapping(value = "ad/selectAdPayInfoById/{id}", method = RequestMethod.GET)
	Result<AdPayInfoDTO> selectAdPayInfoById(@PathVariable("id") Long id);
}
