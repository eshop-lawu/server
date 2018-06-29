package com.lawu.eshop.product.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.constant.ProductCategoryTypeEnum;
import com.lawu.eshop.product.dto.OperatorProductCategoryListDTO;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.eshop.product.dto.ProductCategoryHotDTO;
import com.lawu.eshop.product.param.OperatorProductCategoryParam;
import com.lawu.eshop.product.param.ProductCategoryParam;
import com.lawu.eshop.product.srv.bo.ProductCategoryBO;
import com.lawu.eshop.product.srv.converter.ProductCategoryConverter;
import com.lawu.eshop.product.srv.service.ProductCategoryService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author Yangqh
 * @date 2017/3/13
 */
@RestController
@RequestMapping(value = "productCategory/")
public class ProductCategoryController extends BaseController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 查询所有商品分类
     *
     * @return
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<ProductCategoryDTO> findAll() {
        List<ProductCategoryBO> productCategoryBOS = productCategoryService.findAll();
        return productCategoryBOS.isEmpty() ? null : ProductCategoryConverter.convertDTOS(productCategoryBOS);
    }

    /**
     * 根据ID查询商品分类
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public ProductCategoryDTO getById(@RequestParam Integer id) {
        ProductCategoryBO productCategoryBO = productCategoryService.getById(id);
        return productCategoryBO == null ? null : ProductCategoryConverter.convertDTO(productCategoryBO);
    }

    /**
     * 查询商品类别(一级)
     *
     * @return
     */
    @RequestMapping(value = "listRecommendProductCategory", method = RequestMethod.GET)
    public Result<List<ProductCategoryDTO>> listRecommendProductCategory() {
        List<ProductCategoryBO> productCategoryBOS = productCategoryService.listRecommendProductCategory();
        return successGet(ProductCategoryConverter.convertDTOS(productCategoryBOS));
    }
    
    /**
     * 根据parent_id查询
     *
     * @return
     */
    @RequestMapping(value = "find/{parentId}", method = RequestMethod.GET)
    public Result<List<ProductCategoryDTO>> find(@PathVariable("parentId") Long parentId) {
        List<ProductCategoryBO> productCategoryBOS = productCategoryService.find(parentId);
        return successGet(ProductCategoryConverter.convertDTOS(productCategoryBOS));
    }

    /**
     * 运营平台查询商品分类列表
     * @param param 层级分页
     * @return 商品分类列表
     */
    @RequestMapping(value = "findOperatorAll", method = RequestMethod.POST)
    public Result<Page<OperatorProductCategoryListDTO>> findOperatorAll(@RequestBody OperatorProductCategoryParam param){
        Page<ProductCategoryBO> categoryBOPage = productCategoryService.findOperatorAll(param);
        Page<OperatorProductCategoryListDTO> page = new Page<>();
        page.setTotalCount(categoryBOPage.getTotalCount());
        page.setCurrentPage(categoryBOPage.getCurrentPage());
        page.setRecords(ProductCategoryConverter.convertOperatorDTOS(categoryBOPage.getRecords()));
        return successGet(page);
    }

    /**
     * 新增商品分类
     * @param param
     * @return
     */
    @RequestMapping(value = "addProductCategory", method = RequestMethod.POST)
    public Result addProductCategory(@RequestBody ProductCategoryParam param) {
        productCategoryService.addProductCategory(param);
        return successCreated();
    }

    /**
     * 删除商品分类
     * @param ids
     * @return
     */
    @RequestMapping(value = "delProductCategory", method = RequestMethod.PUT)
    public Result delProductCategory(@RequestParam("ids") String ids) {
        String[] idString = ids.split(",");
        Integer[] lids = new Integer[idString.length];
        for (int i = 0; i < idString.length; i++) {
            lids[i] = Integer.valueOf(idString[i]);
            productCategoryService.delProductCategory(lids[i]);
        }
        return successCreated();
    }

    @RequestMapping(value = "editProductCategory/{id}", method = RequestMethod.POST)
    public Result editProductCategory(@PathVariable("id") Integer id,@RequestBody ProductCategoryParam param){
        ProductCategoryBO productCategoryBO = productCategoryService.getById(id);
        if(productCategoryBO == null){
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        productCategoryService.editProductCategory(id,param);
        return successCreated();
    }

    /**
     * 编辑是否热门
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "editHot/{id}", method = RequestMethod.PUT)
    public Result editHot(@PathVariable("id") Integer id, @RequestParam("type") ProductCategoryTypeEnum type) {
        productCategoryService.editHot(id, type);
        return successCreated();
    }

    /**
     * 获取热门商品分类
     * @return
     */
    @RequestMapping(value = "getHotProductCategory", method = RequestMethod.GET)
    public Result<ProductCategoryHotDTO> getHotProductCategory() {
        List<ProductCategoryBO> categoryBOS = productCategoryService.getHotProductCategory();
        ProductCategoryHotDTO hotDTO = new ProductCategoryHotDTO();
        hotDTO.setCategoryList(ProductCategoryConverter.convertHotDTOS(categoryBOS));
        return successGet(hotDTO);
    }
}
