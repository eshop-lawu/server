package com.lawu.eshop.product.srv.controller;

import com.lawu.eshop.product.dto.ProductSearchDTO;
import com.lawu.eshop.product.param.ListShoppingProductParam;
import com.lawu.eshop.product.srv.bo.ProductSearchBO;
import com.lawu.eshop.product.srv.converter.ProductConverter;
import com.lawu.eshop.product.srv.service.ShoppingProductService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author meishuquan
 * @date 2017/4/21.
 */
@RestController
@RequestMapping(value = "shoppingProduct/")
public class ShoppingProductController extends BaseController {

    @Autowired
    private ShoppingProductService shoppingProductService;

    /**
     * 要购物门店详情店铺首页
     *
     * @param listShoppingProductParam
     * @return
     */
    @RequestMapping(value = "listHotProduct", method = RequestMethod.POST)
    public Result<Page<ProductSearchDTO>> listHotProduct(@RequestBody ListShoppingProductParam listShoppingProductParam) {
        Page<ProductSearchBO> productSearchBOPage = shoppingProductService.listHotProduct(listShoppingProductParam);
        Page<ProductSearchDTO> page = new Page<>();
        page.setRecords(ProductConverter.convertDTO(productSearchBOPage.getRecords()));
        page.setCurrentPage(productSearchBOPage.getCurrentPage());
        page.setTotalCount(productSearchBOPage.getTotalCount());
        return successCreated(page);
    }

    /**
     * 要购物门店详情全部商品
     *
     * @param listShoppingProductParam
     * @return
     */
    @RequestMapping(value = "listAllProduct", method = RequestMethod.POST)
    public Result<Page<ProductSearchDTO>> listAllProduct(@RequestBody ListShoppingProductParam listShoppingProductParam) {
        Page<ProductSearchBO> productSearchBOPage = shoppingProductService.listAllProduct(listShoppingProductParam);
        Page<ProductSearchDTO> page = new Page<>();
        page.setRecords(ProductConverter.convertDTO(productSearchBOPage.getRecords()));
        page.setCurrentPage(productSearchBOPage.getCurrentPage());
        page.setTotalCount(productSearchBOPage.getTotalCount());
        return successCreated(page);
    }

    /**
     * 要购物门店详情最新上架
     *
     * @param listShoppingProductParam
     * @return
     */
    @RequestMapping(value = "listNewProduct", method = RequestMethod.POST)
    public Result<Page<ProductSearchDTO>> listNewProduct(@RequestBody ListShoppingProductParam listShoppingProductParam) {
        Page<ProductSearchBO> productSearchBOPage = shoppingProductService.listNewProduct(listShoppingProductParam);
        Page<ProductSearchDTO> page = new Page<>();
        page.setRecords(ProductConverter.convertDTO(productSearchBOPage.getRecords()));
        page.setCurrentPage(productSearchBOPage.getCurrentPage());
        page.setTotalCount(productSearchBOPage.getTotalCount());
        return successCreated(page);
    }
}
