package com.lawu.eshop.operator.api.controller;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.MerchantStoreService;
import com.lawu.eshop.operator.api.service.ProductCategoryService;
import com.lawu.eshop.operator.api.service.ProductService;
import com.lawu.eshop.operator.api.service.RegionService;
import com.lawu.eshop.product.constant.ProductCategoryTypeEnum;
import com.lawu.eshop.product.dto.*;
import com.lawu.eshop.product.param.OperatorProductCategoryParam;
import com.lawu.eshop.product.param.ProductCategoryParam;
import com.lawu.eshop.product.param.ProductCompatibleParam;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
@Api(tags = "productCategory")
@RestController
@RequestMapping(value = "productCategory/")
public class ProductCategoryController  extends BaseController{

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "查询所有商品分类", notes = "查询所有商品分类（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("productCategory:list")
    @PageBody
    @RequestMapping(value = "findOperatorAll", method = RequestMethod.GET)
    public Result<Page<OperatorProductCategoryListDTO>> findOperatorAll(@ModelAttribute OperatorProductCategoryParam param){
        Result<Page<OperatorProductCategoryListDTO>> page =  productCategoryService.findOperatorAll(param);
        List<OperatorProductCategoryListDTO> records = page.getModel().getRecords();
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getParentId() != null && records.get(i).getParentId() > 0) {
                ProductCategoryDTO categoryDTO = productCategoryService.getById(records.get(i).getParentId());
                records.get(i).setParentName(categoryDTO.getName());
            }
        }

        return page;
    }

    @ApiOperation(value = "添加商品分类", notes = "添加商品分类（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "addProductCategory", method = RequestMethod.POST)
    public Result addProductCategory(@ModelAttribute ProductCategoryParam param) {
        if (param.getParentId() != null && param.getParentId() > 0) {
            param.setLevel((byte) 2);
        } else {
            param.setLevel((byte) 1);
        }
        param.setName(param.getName().replace("/",""));
        productCategoryService.addProductCategory(param);
        return successCreated();
    }

    @ApiOperation(value = "删除商品分类", notes = "删除商品分类（章勇）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "delProductCategory", method = RequestMethod.PUT)
    public Result delProductCategory(@RequestParam("ids") String ids) {
        productCategoryService.delProductCategory(ids);
        return successCreated();
    }

    @ApiOperation(value = "查询子分类列表", notes = "查询子分类列表（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "find/{parentId}", method = RequestMethod.GET)
    public Result<List<ProductCategoryDTO>> find(@PathVariable("parentId") Long parentId) {
        return productCategoryService.find(parentId);
    }

    @ApiOperation(value = "编辑商品分类", notes = "编辑商品分类（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "editProductCategory/{id}", method = RequestMethod.POST)
    public Result editProductCategory(@PathVariable("id") Integer id,@ModelAttribute ProductCategoryParam param) {
        if (param.getParentId() != null && param.getParentId() > 0) {
            param.setLevel((byte) 2);
        } else {
            param.setLevel((byte) 1);
        }
        param.setName(param.getName().replace("/",""));
        productCategoryService.editProductCategory(id,param);
        return successCreated();
    }

    @ApiOperation(value = "查询商品分类详情", notes = "查询商品分类详情（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public Result<OperatorProductCategoryDetailDTO> getById(@RequestParam("id") Integer id) {
        OperatorProductCategoryDetailDTO detailDTO = new OperatorProductCategoryDetailDTO();
        detailDTO.setCategoryDTO(productCategoryService.getById(id));
        detailDTO.setCategoryDTOS(productCategoryService.find(Long.parseLong("0")).getModel());
        return successGet(detailDTO);
    }

    @ApiOperation(value = "编辑是否热门", notes = "编辑是否热门。（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "editHot/{id}", method = RequestMethod.PUT)
    public Result editHot(@PathVariable("id") Integer id, @RequestParam("type") ProductCategoryTypeEnum type) {
        return productCategoryService.editHot(id, type);
    }

    @ApiOperation(value = "商品型号兼容处理", notes = "商品型号兼容处理（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "productCompatible", method = RequestMethod.GET)
    public Result productCompatible() {
        Result<ProductAllRtnDTO> productAllRtnDTOResult = productService.getAllProduct();
        List<ProductAllDTO> productAllDTOList = productAllRtnDTOResult.getModel() == null ? new ArrayList<>() : productAllRtnDTOResult.getModel().getProductAllDTOList();
        for (ProductAllDTO productAllDTO : productAllDTOList) {
            VisitUserInfoDTO merchantInfo = merchantStoreService.findAccountAndRegionPathByNum(productAllDTO.getMerchantNum());
            Long provinceId = new Long(merchantInfo.getRegionPath().split("/")[0]);
            Long cityId = new Long(merchantInfo.getRegionPath().split("/")[1]);
            String provinceName = regionService.getById(provinceId).getModel();
            String cityName = regionService.getById(cityId).getModel();
            ProductCompatibleParam productCompatibleParam = new ProductCompatibleParam();
            productCompatibleParam.setProductId(productAllDTO.getId());
            productCompatibleParam.setCategoryId(productAllDTO.getCategoryId());
            productCompatibleParam.setProvinceId(provinceId);
            productCompatibleParam.setCityId(cityId);
            productCompatibleParam.setProvinceName(provinceName);
            productCompatibleParam.setCityName(cityName);
            productService.compatible(productCompatibleParam);
        }
        return successCreated();
    }
}
