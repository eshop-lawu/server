package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.ProductCategoryService;
import com.lawu.eshop.operator.api.service.ProductSpecService;
import com.lawu.eshop.product.dto.OperatorProductSpecDTO;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.eshop.product.dto.ProductSpecDetailDTO;
import com.lawu.eshop.product.param.OperatorProductSpecParam;
import com.lawu.eshop.product.param.ProductSpecParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/4/17.
 */
@Api(tags = "productSpec")
@RestController
@RequestMapping(value = "productSpec/")
public class ProductSpecController extends BaseController {

    @Autowired
    private ProductSpecService productSpecService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @ApiOperation(value = "添加规格", notes = "添加规格（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "addProductSpec", method = RequestMethod.POST)
    public Result addProductSpec(@ModelAttribute ProductSpecParam param) {
        productSpecService.addProductSpec(param);
        return successCreated();
    }

    @ApiOperation(value = "编辑规格", notes = "编辑规格（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "editProductSpec/{id}", method = RequestMethod.POST)
    public Result editProductSpec(@PathVariable("id") Long id, @ModelAttribute ProductSpecParam param) {
        productSpecService.editProductSpec(id, param);
        return successCreated();
    }

    @ApiOperation(value = "删除规格", notes = "删除规格（章勇）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "delProductSpec", method = RequestMethod.PUT)
    public Result delProductSpec(@RequestParam("ids") String ids) {
        productSpecService.delProductSpec(ids);
        return successCreated();
    }

    @ApiOperation(value = "查询所有规格", notes = "查询所有规格（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("productSpec:list")
    @PageBody
    @RequestMapping(value = "getOperatorProductSpecList", method = RequestMethod.GET)
    public Result<Page<OperatorProductSpecDTO>> getOperatorProductSpecList(@ModelAttribute OperatorProductSpecParam param) {
        return productSpecService.getOperatorProductSpecList(param);
    }

    @ApiOperation(value = "查询规格详情", notes = "查询规格详情（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "getSpecDetailById/{id}", method = RequestMethod.GET)
    public Result<ProductSpecDetailDTO> getSpecDetailById(@PathVariable("id") Long id) {
        Result<ProductSpecDetailDTO> detailDTOResult = productSpecService.getSpecDetailById(id);
        ProductCategoryDTO categoryDTO = productCategoryService.getById(detailDTOResult.getModel().getProductCategoryId());
        detailDTOResult.getModel().setCategoryName(categoryDTO.getName());
        return successGet(detailDTOResult.getModel());

    }

}
