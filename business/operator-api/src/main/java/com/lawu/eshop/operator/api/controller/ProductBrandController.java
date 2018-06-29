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
import com.lawu.eshop.operator.api.service.ProductBrandService;
import com.lawu.eshop.operator.api.service.ProductCategoryService;
import com.lawu.eshop.product.dto.OperatorProductBrandDTO;
import com.lawu.eshop.product.dto.ProductBrandDetailDTO;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.eshop.product.param.OperatorProductBrandParam;
import com.lawu.eshop.product.param.ProductBrandParam;
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
@Api(tags = "productBrand")
@RestController
@RequestMapping(value = "productBrand/")
public class ProductBrandController extends BaseController {

    @Autowired
    private ProductBrandService productBrandService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @ApiOperation(value = "添加品牌分类", notes = "添加品牌分类（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "addProductBrand", method = RequestMethod.POST)
    public Result addProductBrand(@ModelAttribute ProductBrandParam param) {
        productBrandService.addProductBrand(param);
        return successCreated();
    }

    @ApiOperation(value = "查询品牌分类详情", notes = "查询品牌分类详情（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "getProductBrandDetail/{id}", method = RequestMethod.GET)
    public Result<ProductBrandDetailDTO> getProductBrandDetail(@PathVariable("id") Long id){
        Result<ProductBrandDetailDTO> detailDTOResult = productBrandService.getProductBrandDetail(id);
        ProductCategoryDTO categoryDTO = productCategoryService.getById(detailDTOResult.getModel().getProductCategoryId());
        detailDTOResult.getModel().setCategoryName(categoryDTO.getName());
        return successGet(detailDTOResult.getModel());

    }

    @ApiOperation(value = "编辑品牌分类", notes = "编辑品牌分类（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "editProductBrand/{id}", method = RequestMethod.POST)
    public Result editProductBrand(@PathVariable("id") Long id, @ModelAttribute ProductBrandParam param) {
        productBrandService.editProductBrand(id, param);
        return successCreated();
    }

    @ApiOperation(value = "查询所有商品分类", notes = "查询所有商品分类（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("productBrand:list")
    @PageBody
    @RequestMapping(value = "getOperatorProductBrandList", method = RequestMethod.GET)
    public Result<Page<OperatorProductBrandDTO>> getOperatorProductBrandList(@ModelAttribute OperatorProductBrandParam param){
        return productBrandService.getOperatorProductBrandList(param);
    }

    @ApiOperation(value = "删除品牌分类", notes = "删除品牌分类（章勇）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "delProductBrand", method = RequestMethod.PUT)
    public Result delProductBrand(@RequestParam("ids") String ids){
        productBrandService.delProductBrand(ids);
        return successCreated();
    }
}
