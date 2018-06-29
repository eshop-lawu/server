package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.product.dto.ProductSpecDTO;
import com.lawu.framework.web.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品规格
 */
@FeignClient(value= "product-srv")
public interface ProductSpecService {

    @RequestMapping(method = RequestMethod.GET, value = "productSpec/getProductSpecByCategoryId")
    Result<ProductSpecDTO> getProductSpecByCategoryId(@RequestParam("productCategoryId") Integer productCategoryId);
}
