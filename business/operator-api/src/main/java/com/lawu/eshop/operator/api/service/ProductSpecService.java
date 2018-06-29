package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.product.dto.OperatorProductSpecDTO;
import com.lawu.eshop.product.dto.ProductSpecDetailDTO;
import com.lawu.eshop.product.param.OperatorProductSpecParam;
import com.lawu.eshop.product.param.ProductSpecParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/4/17.
 */
@FeignClient(value = "product-srv", path = "productSpec")
public interface ProductSpecService {

    @RequestMapping(value = "addProductSpec", method = RequestMethod.POST)
    Result addProductSpec(@ModelAttribute ProductSpecParam param);

    @RequestMapping(value = "editProductSpec/{id}", method = RequestMethod.POST)
    Result editProductSpec(@PathVariable("id") Long id, @ModelAttribute ProductSpecParam param);

    @RequestMapping(value = "getOperatorProductSpecList", method = RequestMethod.POST)
    Result<Page<OperatorProductSpecDTO>> getOperatorProductSpecList(@ModelAttribute OperatorProductSpecParam param);

    @RequestMapping(value = "delProductSpec", method = RequestMethod.PUT)
    Result delProductSpec(@RequestParam("ids") String ids);

    @RequestMapping(value = "getSpecDetailById/{id}", method = RequestMethod.GET)
    Result<ProductSpecDetailDTO> getSpecDetailById(@PathVariable("id") Long id);

}
