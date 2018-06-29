package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.product.constant.CustomSpecStatusEnum;
import com.lawu.eshop.product.dto.ProductCustomSpecDTO;
import com.lawu.eshop.product.query.ProductCustomSpecPageQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "product-srv")
public interface ProductCustomSpecService {
	
	@RequestMapping(value = "productCustomSpec/specPage", method = RequestMethod.POST)
	Result<Page<ProductCustomSpecDTO>> specPage(@RequestBody ProductCustomSpecPageQuery query);
	
	
	@RequestMapping(value = "productCustomSpec/dealCustomSpec/{id}", method = RequestMethod.PUT)
	Result dealCustomSpec(@PathVariable("id") Long id ,@RequestParam("statusEnum") CustomSpecStatusEnum statusEnum);

}
