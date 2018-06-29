package com.lawu.eshop.product.srv.service;

import com.lawu.eshop.product.param.ListShoppingProductParam;
import com.lawu.eshop.product.srv.bo.ProductSearchBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/4/21.
 */
public interface ShoppingProductService {

    Page<ProductSearchBO> listHotProduct(ListShoppingProductParam listShoppingProductParam);

    Page<ProductSearchBO> listAllProduct(ListShoppingProductParam listShoppingProductParam);

    Page<ProductSearchBO> listNewProduct(ListShoppingProductParam listShoppingProductParam);

}
