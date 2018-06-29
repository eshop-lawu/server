package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.member.api.service.ProductBrandService;
import com.lawu.eshop.product.dto.ProductBrandDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 商品品牌
 */
@Api(tags = "productBrand")
@RestController
@RequestMapping(value = "productBrand/")
public class ProductBrandController extends BaseController {

    @Autowired
    private ProductBrandService productBrandService;

    @Audit(date = "2018-04-18", reviewer = "孙林青")
    @ApiOperation(value = "根据类目ID查询品牌", notes = "根据类目ID查询品牌(杨清华)", httpMethod = "GET")
    @RequestMapping(value = "getBrandByCategoryId/{categoryId}", method = RequestMethod.GET)
    public Result<ProductBrandDTO> getBrandByCategoryId(@PathVariable @ApiParam(name = "categoryId", required = true, value = "类目ID") Integer categoryId) {
        Result<ProductBrandDTO> result = productBrandService.getBrandByCategoryId(categoryId);
        return successCreated(result);
    }

    @Audit(date = "2018-04-28", reviewer = "孙林青")
    @ApiOperation(value = "根据类目ID查询所有品牌", notes = "根据类目ID查询所有品牌 (梅述全)", httpMethod = "GET")
    @RequestMapping(value = "listProductBrand/{categoryId}", method = RequestMethod.GET)
    public Result<ProductBrandDTO> listProductBrand(@PathVariable @ApiParam(name = "categoryId", required = true, value = "类目ID") Integer categoryId) {
        Result<ProductBrandDTO> result = productBrandService.listProductBrand(categoryId);
        return successGet(result);
    }

}
