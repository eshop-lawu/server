package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.product.dto.ProductSpecOptionDTO;
import com.lawu.framework.web.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品规格选项
 */
@FeignClient(value= "product-srv")
public interface ProductSpecOptionService {

    @RequestMapping(method = RequestMethod.GET, value = "productSpecOption/getSpecOptionBySpecId")
    Result<ProductSpecOptionDTO> getSpecOptionBySpecId(@RequestParam("productSpecId") Long productSpecId);
}
