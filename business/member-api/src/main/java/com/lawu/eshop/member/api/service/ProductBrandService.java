package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.product.dto.ProductBrandDTO;
import com.lawu.framework.web.Result;

/**
 * 商品品牌
 */
@FeignClient(value= "product-srv")
public interface ProductBrandService {

    @RequestMapping(method = RequestMethod.GET, value = "productBrand/getProductBrandByCategoryId")
    Result<ProductBrandDTO> getBrandByCategoryId(@RequestParam("productCategoryId") Integer productCategoryId);

    @RequestMapping(method = RequestMethod.GET, value = "productBrand/listProductBrand/{productCategoryId}")
    Result<ProductBrandDTO> listProductBrand(@PathVariable("productCategoryId") Integer productCategoryId);

}
