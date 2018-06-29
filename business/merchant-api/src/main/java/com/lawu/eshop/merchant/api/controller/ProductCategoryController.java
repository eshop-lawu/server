package com.lawu.eshop.merchant.api.controller;

import java.util.List;

import com.lawu.eshop.framework.web.impl.constants.UserConstant;

import com.lawu.eshop.product.dto.ProductCategoryRtnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.merchant.api.service.ProductCategoryService;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Yangqh
 * @date 2017/3/11
 */
@Api(tags = "productCategory")
@RestController
@RequestMapping(value = "productCategory/")
public class ProductCategoryController extends BaseController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "查询所有商品分类", notes = "查询所有商品分类，(杨清华)", httpMethod = "GET")
    @Authorization
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Result<List<ProductCategoryDTO>> findAll(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {

        List<ProductCategoryDTO> dtos = productCategoryService.findAll();
        return successCreated(dtos);
    }

    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "根据ID查询商品分类", notes = "根据ID查询商品分类，(杨清华)", httpMethod = "GET")
    @Authorization
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Result<ProductCategoryDTO> getById(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
    								  @PathVariable @ApiParam(name = "id", required = true, value = "商品分类ID") Integer id) {

        ProductCategoryDTO dto = productCategoryService.getById(id);
        return successCreated(dto);
    }

    @Audit(date = "2018-04-18", reviewer = "孙林青")
    @ApiOperation(value = "根据父ID查询类目", notes = "根据父ID查询类目，查第一级分类传0(杨清华)", httpMethod = "GET")
    @Authorization
    @RequestMapping(value = "getByParentId/{parentId}", method = RequestMethod.GET)
    public Result<ProductCategoryRtnDTO> getByParentId(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                       @PathVariable @ApiParam(name = "parentId", required = true, value = "父ID") Integer parentId) {
        Result<List<ProductCategoryDTO>> result = productCategoryService.getByParentId(parentId);
        ProductCategoryRtnDTO rtn = new ProductCategoryRtnDTO();
        rtn.setProductCategoryDTOList(result.getModel());
        return successCreated(rtn);
    }

}
