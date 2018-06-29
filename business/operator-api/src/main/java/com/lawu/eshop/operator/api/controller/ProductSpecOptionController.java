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
import com.lawu.eshop.operator.api.service.ProductSpecOptionService;
import com.lawu.eshop.operator.api.service.ProductSpecService;
import com.lawu.eshop.product.dto.OperatorProductSpecOptionDTO;
import com.lawu.eshop.product.dto.ProductSpecDetailDTO;
import com.lawu.eshop.product.dto.ProductSpecOptionDetailDTO;
import com.lawu.eshop.product.param.OperatorSpecOptionListParam;
import com.lawu.eshop.product.param.ProductSpecOptionParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/4/18.
 */
@Api(tags = "productSpecOption")
@RestController
@RequestMapping(value = "productSpecOption/")
public class ProductSpecOptionController extends BaseController {

    @Autowired
    private ProductSpecOptionService productSpecOptionService;

    @Autowired
    private ProductSpecService productSpecService;

    @ApiOperation(value = "添加规格项", notes = "添加规格项（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "addProductSpecOption", method = RequestMethod.POST)
    public Result addProductSpecOption(@ModelAttribute ProductSpecOptionParam param) {
        productSpecOptionService.addProductSpecOption(param);
        return successCreated();
    }

    @ApiOperation(value = "编辑规格项", notes = "编辑规格项（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "editProductSpecOption/{id}", method = RequestMethod.POST)
    public Result editProductSpecOption(@PathVariable("id") Long id, @ModelAttribute ProductSpecOptionParam param) {
        productSpecOptionService.editProductSpecOption(id, param);
        return successCreated();
    }

    @ApiOperation(value = "删除规格项", notes = "删除规格项（章勇）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "delProductSpecOption", method = RequestMethod.PUT)
    public Result delProductSpecOption(@RequestParam("ids") String ids) {
        productSpecOptionService.delProductSpecOption(ids);
        return successCreated();
    }

    @ApiOperation(value = "查询所有规格项", notes = "查询所有规格项（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("productSpecOption:list")
    @PageBody
    @RequestMapping(value = "getOperatorSpecOptionList", method = RequestMethod.GET)
    public Result<Page<OperatorProductSpecOptionDTO>> getOperatorSpecOptionList(@ModelAttribute OperatorSpecOptionListParam param) {
        return productSpecOptionService.getOperatorSpecOptionList(param);
    }

    @ApiOperation(value = "查询规格详情", notes = "查询规格详情（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "getSpecOptionDetail/{id}", method = RequestMethod.GET)
    public Result<ProductSpecOptionDetailDTO> getSpecOptionDetail(@PathVariable("id") Long id) {
        Result<ProductSpecOptionDetailDTO> detailDTOResult = productSpecOptionService.getSpecOptionDetail(id);
        Result<ProductSpecDetailDTO> specDetailDTOResult = productSpecService.getSpecDetailById(id);
        detailDTOResult.getModel().setSpecName(specDetailDTOResult.getModel().getSpecName());
        return successGet(detailDTOResult.getModel());

    }


}
