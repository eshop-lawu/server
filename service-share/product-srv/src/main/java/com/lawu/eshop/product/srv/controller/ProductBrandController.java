package com.lawu.eshop.product.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.product.dto.OperatorProductBrandDTO;
import com.lawu.eshop.product.dto.ProductBrandDTO;
import com.lawu.eshop.product.dto.ProductBrandDetailDTO;
import com.lawu.eshop.product.param.OperatorProductBrandParam;
import com.lawu.eshop.product.param.ProductBrandParam;
import com.lawu.eshop.product.srv.bo.OperatorProductBrandBO;
import com.lawu.eshop.product.srv.bo.ProductBrandBO;
import com.lawu.eshop.product.srv.converter.ProductBrandConverter;
import com.lawu.eshop.product.srv.service.ProductBrandService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
@Api(tags = "productBrand")
@RestController
@RequestMapping(value = "productBrand/")
public class ProductBrandController extends BaseController {

    @Autowired
    private ProductBrandService productBrandService;


    /**
     * 根据商品类目查品牌的接口
     *
     * @param productCategoryId
     * @return
     */
    @RequestMapping(value = "getProductBrandByCategoryId", method = RequestMethod.GET)
    public Result<ProductBrandDTO> getProductBrandByCategoryId(@RequestParam("productCategoryId") Integer productCategoryId) {
        List<ProductBrandBO> brandBOS = productBrandService.getProductBrandByCategoryId(productCategoryId);
        ProductBrandDTO brandDTO = new ProductBrandDTO();
        brandDTO.setBrandList(ProductBrandConverter.covertDTOS(brandBOS));
        return successGet(brandDTO);
    }

    /**
     * 新增品牌管理
     * @param param
     * @return
     */
    @RequestMapping(value = "addProductBrand", method = RequestMethod.POST)
    public Result addProductBrand(@RequestBody ProductBrandParam param) {
        productBrandService.addProductBrand(param);
        return successCreated();
    }

    /**
     * 查询品牌详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getProductBrandDetail/{id}", method = RequestMethod.GET)
    public Result<ProductBrandDetailDTO> getProductBrandDetail(@PathVariable("id") Long id) {
        ProductBrandBO brandBO = productBrandService.getProductBrandDetail(id);
        return successGet(ProductBrandConverter.covertDTO(brandBO));
    }

    /**
     * 编辑品牌信息
     * @param id
     * @param param
     * @return
     */
    @RequestMapping(value = "editProductBrand/{id}", method = RequestMethod.POST)
    public Result editProductBrand(@PathVariable("id") Long id, @RequestBody ProductBrandParam param) {
        productBrandService.editProductBrand(id, param);
        return successCreated();
    }

    /**
     * 分页查询品牌列表
     * @param param
     * @return
     */
    @RequestMapping(value = "getOperatorProductBrandList", method = RequestMethod.POST)
    public Result<Page<OperatorProductBrandDTO>> getOperatorProductBrandList(@RequestBody OperatorProductBrandParam param) {
        Page<OperatorProductBrandBO> brandBOPage = productBrandService.getOperatorProductBrandList(param);
        Page<OperatorProductBrandDTO> page = new Page<>();
        page.setCurrentPage(brandBOPage.getCurrentPage());
        page.setTotalCount(brandBOPage.getTotalCount());
        page.setRecords(ProductBrandConverter.covertOperatorDTOS(brandBOPage.getRecords()));
        return successGet(page);
    }

    /**
     * 删除品牌
     * @param ids
     * @return
     */
    @RequestMapping(value = "delProductBrand", method = RequestMethod.PUT)
    public Result delProductBrand(@RequestParam("ids") String ids){
        String[] idString = ids.split(",");
        Long[] lids = new Long[idString.length];
        for (int i = 0; i < idString.length; i++) {
            lids[i] = Long.valueOf(idString[i]);
            productBrandService.delProductBrand(lids[i]);
        }
        return successCreated();
    }

    /**
     * 根据名称模糊查询品牌
     * @param brandName
     * @return
     */
    @RequestMapping(value = "getProductBrandByName", method = RequestMethod.GET)
    public Result<ProductBrandDTO> getProductBrandByName(@RequestParam("brandName") String brandName ,@RequestParam("categoryId") Integer categoryId) {
        List<ProductBrandBO> brandBOS = productBrandService.getProductBrandByName(brandName,categoryId);
        ProductBrandDTO brandDTO = new ProductBrandDTO();
        brandDTO.setBrandList(ProductBrandConverter.covertDTOS(brandBOS));
        return successGet(brandDTO);
    }

    /**
     * 根据商品类目查所有品牌
     *
     * @param productCategoryId
     * @return
     */
    @RequestMapping(value = "listProductBrand/{productCategoryId}", method = RequestMethod.GET)
    public Result<ProductBrandDTO> listProductBrand(@PathVariable Integer productCategoryId) {
        List<ProductBrandBO> brandBOS = productBrandService.listProductBrand(productCategoryId);
        ProductBrandDTO brandDTO = new ProductBrandDTO();
        brandDTO.setBrandList(ProductBrandConverter.covertDTOS(brandBOS));
        return successGet(brandDTO);
    }

}
