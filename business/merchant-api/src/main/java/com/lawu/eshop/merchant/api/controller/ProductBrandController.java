package com.lawu.eshop.merchant.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.service.ProductBrandService;
import com.lawu.eshop.product.dto.ProductBrandDTO;
import com.lawu.eshop.product.dto.ProductBrandListDTO;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Authorization
    @RequestMapping(value = "getBrandByCategoryId/{categoryId}", method = RequestMethod.GET)
    public Result<ProductBrandDTO> getBrandByCategoryId(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                              @PathVariable @ApiParam(name = "categoryId", required = true, value = "类目ID") Integer categoryId) {
        Result<ProductBrandDTO> result = productBrandService.getBrandByCategoryId(categoryId);
        return successCreated(result);
    }

    @Audit(date = "2018-04-18", reviewer = "孙林青")
    @ApiOperation(value = "品牌模糊查询", notes = "品牌模糊查询(杨清华)", httpMethod = "GET")
    @Authorization
    @RequestMapping(value = "getBrandByName", method = RequestMethod.GET)
    public Result<ProductBrandDTO> getBrandByName(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                        @RequestParam @ApiParam(name = "brandName", required = true, value = "品牌名称") String brandName,
                                                  @RequestParam @ApiParam(name = "categoryId", required = true, value = "类目ID") Integer categoryId) {
        Result<ProductBrandDTO> result = productBrandService.getProductBrandByName(brandName,categoryId);
        return successCreated(result);
    }

}
