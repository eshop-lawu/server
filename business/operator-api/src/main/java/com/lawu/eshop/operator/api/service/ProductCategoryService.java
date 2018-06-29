package com.lawu.eshop.operator.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.product.constant.ProductCategoryTypeEnum;
import com.lawu.eshop.product.dto.OperatorProductCategoryListDTO;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.eshop.product.param.OperatorProductCategoryParam;
import com.lawu.eshop.product.param.ProductCategoryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
@FeignClient(value = "product-srv" , path = "productCategory")
public interface ProductCategoryService {

    @RequestMapping(value = "findOperatorAll", method = RequestMethod.POST)
    Result<Page<OperatorProductCategoryListDTO>> findOperatorAll(@ModelAttribute OperatorProductCategoryParam param);

    @RequestMapping(value = "addProductCategory", method = RequestMethod.POST)
    Result addProductCategory(@ModelAttribute ProductCategoryParam param);

    @RequestMapping(value = "delProductCategory", method = RequestMethod.PUT)
    Result delProductCategory(@RequestParam("ids") String ids);

    @RequestMapping(value = "find/{parentId}", method = RequestMethod.GET)
    Result<List<ProductCategoryDTO>> find(@PathVariable("parentId") Long parentId);

    @RequestMapping(value = "editProductCategory/{id}", method = RequestMethod.POST)
    Result editProductCategory(@PathVariable("id") Integer id,@ModelAttribute ProductCategoryParam param);

    @RequestMapping(value = "getById", method = RequestMethod.GET)
    ProductCategoryDTO getById(@RequestParam("id") Integer id);

    @RequestMapping(value = "editHot/{id}", method = RequestMethod.PUT)
    Result editHot(@PathVariable("id") Integer id, @RequestParam("type") ProductCategoryTypeEnum type);
}
