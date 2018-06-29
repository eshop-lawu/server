package com.lawu.eshop.member.api.service;

import com.lawu.eshop.product.dto.ProductSearchDTO;
import com.lawu.eshop.product.param.ListShoppingProductParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author meishuquan
 * @date 2017/4/21.
 */
@FeignClient(value = "product-srv")
public interface ShoppingProductService {

    /**
     * 要购物门店详情店铺首页
     *
     * @param listShoppingProductParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "shoppingProduct/listHotProduct")
    Result<Page<ProductSearchDTO>> listHotProduct(@ModelAttribute ListShoppingProductParam listShoppingProductParam);

    /**
     * 要购物门店详情全部商品
     *
     * @param listShoppingProductParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "shoppingProduct/listAllProduct")
    Result<Page<ProductSearchDTO>> listAllProduct(@ModelAttribute ListShoppingProductParam listShoppingProductParam);

    /**
     * 要购物门店详情最新上架
     *
     * @param listShoppingProductParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "shoppingProduct/listNewProduct")
    Result<Page<ProductSearchDTO>> listNewProduct(@ModelAttribute ListShoppingProductParam listShoppingProductParam);
}
