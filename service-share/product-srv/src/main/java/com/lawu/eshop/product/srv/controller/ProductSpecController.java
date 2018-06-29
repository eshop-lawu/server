package com.lawu.eshop.product.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.product.dto.OperatorProductSpecDTO;
import com.lawu.eshop.product.dto.ProductSpecDTO;
import com.lawu.eshop.product.dto.ProductSpecDetailDTO;
import com.lawu.eshop.product.param.OperatorProductSpecParam;
import com.lawu.eshop.product.param.ProductSpecParam;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecBO;
import com.lawu.eshop.product.srv.bo.ProductSpecBO;
import com.lawu.eshop.product.srv.converter.ProductSpecConverter;
import com.lawu.eshop.product.srv.service.ProductSpecService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
@RestController
@RequestMapping(value = "productSpec/")
public class ProductSpecController extends BaseController{

    @Autowired
    private ProductSpecService productSpecService;

    /**
     * 根据商品类别ID查询规格列表
     * @param productCategoryId
     * @return
     */
    @RequestMapping(value = "getProductSpecByCategoryId",method = RequestMethod.GET)
    public Result<ProductSpecDTO> getProductSpecByCategoryId(@RequestParam("productCategoryId") Integer productCategoryId ){
        List<ProductSpecBO> specBOS = productSpecService.getProductSpecByCategoryId(productCategoryId);
        ProductSpecDTO specDTO = new ProductSpecDTO();
        specDTO.setSpecList(ProductSpecConverter.convertDTOS(specBOS));
        return successGet(specDTO);
    }

    /**
     * 添加规格信息
     * @param param
     * @return
     */
    @RequestMapping(value = "addProductSpec", method = RequestMethod.POST)
    public Result addProductSpec(@RequestBody ProductSpecParam param){
        productSpecService.addProductSpec(param);
        return successCreated();
    }

    /**
     * 编辑规格信息
     * @param id
     * @param param
     * @return
     */
    @RequestMapping(value = "editProductSpec/{id}", method = RequestMethod.POST)
    public Result editProductSpec(@PathVariable("id") Long id, @RequestBody ProductSpecParam param){
        productSpecService.editProductSpec(id,param);
        return successCreated();
    }

    /**
     * 分页查询规格列表
     * @param param
     * @return
     */
    @RequestMapping(value = "getOperatorProductSpecList", method = RequestMethod.POST)
    public Result<Page<OperatorProductSpecDTO>> getOperatorProductSpecList(@RequestBody OperatorProductSpecParam param){
        Page<OperatorProductSpecBO> specBOPage = productSpecService.getOperatorProductSpecList(param);
        Page<OperatorProductSpecDTO> page  = new Page<>();
        page.setCurrentPage(specBOPage.getCurrentPage());
        page.setTotalCount(specBOPage.getTotalCount());
        page.setRecords(ProductSpecConverter.convertOperatorDTOS(specBOPage.getRecords()));
        return successGet(page);
    }

    /**
     * 删除规格
     * @param ids
     * @return
     */
    @RequestMapping(value = "delProductSpec", method = RequestMethod.PUT)
    public Result delProductSpec(@RequestParam("ids") String ids) {
        String[] idString = ids.split(",");
        Long[] lids = new Long[idString.length];
        for (int i = 0; i < idString.length; i++) {
            lids[i] = Long.valueOf(idString[i]);
            productSpecService.delProductSpec(lids[i]);
        }
        return successCreated();
    }

    /**
     * 查询规格详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getSpecDetailById/{id}", method = RequestMethod.GET)
    public Result<ProductSpecDetailDTO> getSpecDetailById(@PathVariable("id") Long id) {
        ProductSpecBO specBO = productSpecService.getSpecDetailById(id);
        return successGet(ProductSpecConverter.convertDTO(specBO));
    }
}
