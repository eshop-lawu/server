package com.lawu.eshop.product.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.product.dto.OperatorProductSpecOptionDTO;
import com.lawu.eshop.product.dto.ProductSpecOptionDTO;
import com.lawu.eshop.product.dto.ProductSpecOptionDetailDTO;
import com.lawu.eshop.product.param.OperatorSpecOptionListParam;
import com.lawu.eshop.product.param.ProductSpecOptionParam;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecOptionBO;
import com.lawu.eshop.product.srv.bo.ProductSpecOptionBO;
import com.lawu.eshop.product.srv.converter.ProductSpecOptionConverter;
import com.lawu.eshop.product.srv.service.ProductSpecOptionService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
@RestController
@RequestMapping(value = "productSpecOption/")
public class ProductSpecOptionController extends BaseController{

    @Autowired
    private ProductSpecOptionService productSpecOptionService;

    /**
     * 根据规格ID查询规格项列表
     * @param productSpecId
     * @return
     */
    @RequestMapping(value = "getSpecOptionBySpecId",method = RequestMethod.GET)
    public Result<ProductSpecOptionDTO> getSpecOptionBySpecId(@RequestParam("productSpecId") Long productSpecId){
        List<ProductSpecOptionBO> list = productSpecOptionService.getSpecOptionBySpecId(productSpecId);
        ProductSpecOptionDTO optionDTO = new ProductSpecOptionDTO();
        optionDTO.setOptionList(ProductSpecOptionConverter.convertDTOS(list));
        return successGet(optionDTO);
    }


    /**
     * 新增规格项记录
     * @param param
     * @return
     */
    @RequestMapping(value = "addProductSpecOption",method = RequestMethod.POST)
    public Result addProductSpecOption(@RequestBody ProductSpecOptionParam param){
        productSpecOptionService.addProductSpecOption(param);
        return successCreated();
    }

    /**
     * 编辑规格项信息
     * @param id
     * @param param
     * @return
     */
    @RequestMapping(value = "editProductSpecOption/{id}", method = RequestMethod.POST)
    public Result editProductSpecOption(@PathVariable("id") Long id, @RequestBody ProductSpecOptionParam param) {
        productSpecOptionService.editProductSpecOption(id, param);
        return successCreated();
    }

    /**
     * 删除规格项
     * @param ids
     * @return
     */
    @RequestMapping(value = "delProductSpecOption", method = RequestMethod.PUT)
    public Result delProductSpecOption(@RequestParam("ids") String ids) {
        String[] idString = ids.split(",");
        Long[] lids = new Long[idString.length];
        for (int i = 0; i < idString.length; i++) {
            lids[i] = Long.valueOf(idString[i]);
            productSpecOptionService.delProductSpecOption(lids[i]);
        }
        return successCreated();
    }

    /**
     * 规格项详情
     * @param id
     * @return
     */
    @RequestMapping(value = "getSpecOptionDetail/{id}", method = RequestMethod.GET)
    public Result<ProductSpecOptionDetailDTO> getSpecOptionDetail(@PathVariable("id") Long id) {
        ProductSpecOptionBO optionBO = productSpecOptionService.getSpecOptionDetail(id);
        return successGet(ProductSpecOptionConverter.convertDTO(optionBO));
    }

    /**
     * 查询规格项列表
     * @param param
     * @return
     */
    @RequestMapping(value = "getOperatorSpecOptionList", method = RequestMethod.POST)
    public Result<Page<OperatorProductSpecOptionDTO>> getOperatorSpecOptionList(@RequestBody OperatorSpecOptionListParam param) {
        Page<OperatorProductSpecOptionBO> optionBOPage = productSpecOptionService.getOperatorSpecOptionList(param);
        Page<OperatorProductSpecOptionDTO> page = new Page<>();
        page.setCurrentPage(optionBOPage.getCurrentPage());
        page.setTotalCount(optionBOPage.getTotalCount());
        page.setRecords(ProductSpecOptionConverter.convertOperatorDTOS(optionBOPage.getRecords()));
        return successGet(page);
    }
}
