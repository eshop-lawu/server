package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.product.dto.OperatorProductSpecOptionDTO;
import com.lawu.eshop.product.dto.ProductSpecOptionDetailDTO;
import com.lawu.eshop.product.param.OperatorSpecOptionListParam;
import com.lawu.eshop.product.param.ProductSpecOptionParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/4/18.
 */
@FeignClient(value = "product-srv", path = "productSpecOption")
public interface ProductSpecOptionService {

    @RequestMapping(value = "addProductSpecOption", method = RequestMethod.POST)
    Result addProductSpecOption(@ModelAttribute ProductSpecOptionParam param);

    @RequestMapping(value = "editProductSpecOption/{id}", method = RequestMethod.POST)
    Result editProductSpecOption(@PathVariable("id") Long id, @ModelAttribute ProductSpecOptionParam param);

    @RequestMapping(value = "delProductSpecOption", method = RequestMethod.PUT)
    Result delProductSpecOption(@RequestParam("ids") String ids);

    @RequestMapping(value = "getSpecOptionDetail/{id}", method = RequestMethod.GET)
    Result<ProductSpecOptionDetailDTO> getSpecOptionDetail(@PathVariable("id") Long id);

    @RequestMapping(value = "getOperatorSpecOptionList", method = RequestMethod.POST)
    Result<Page<OperatorProductSpecOptionDTO>> getOperatorSpecOptionList(@ModelAttribute OperatorSpecOptionListParam param);

}
