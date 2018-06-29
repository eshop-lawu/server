package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.product.dto.OperatorProductBrandDTO;
import com.lawu.eshop.product.dto.ProductBrandDetailDTO;
import com.lawu.eshop.product.param.OperatorProductBrandParam;
import com.lawu.eshop.product.param.ProductBrandParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/4/17.
 */
@FeignClient(value = "product-srv", path = "productBrand")
public interface ProductBrandService {

    @RequestMapping(value = "addProductBrand", method = RequestMethod.POST)
    Result addProductBrand(@ModelAttribute ProductBrandParam param);

    @RequestMapping(value = "getProductBrandDetail/{id}", method = RequestMethod.GET)
    Result<ProductBrandDetailDTO> getProductBrandDetail(@PathVariable("id") Long id);

    @RequestMapping(value = "editProductBrand/{id}", method = RequestMethod.POST)
    Result editProductBrand(@PathVariable("id") Long id, @ModelAttribute ProductBrandParam param);

    @RequestMapping(value = "getOperatorProductBrandList", method = RequestMethod.POST)
    Result<Page<OperatorProductBrandDTO>> getOperatorProductBrandList(@ModelAttribute OperatorProductBrandParam param);

    @RequestMapping(value = "delProductBrand", method = RequestMethod.PUT)
    Result delProductBrand(@RequestParam("ids") String ids);


}
