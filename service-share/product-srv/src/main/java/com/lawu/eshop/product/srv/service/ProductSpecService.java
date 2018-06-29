package com.lawu.eshop.product.srv.service;

import java.util.List;

import com.lawu.eshop.product.param.OperatorProductSpecParam;
import com.lawu.eshop.product.param.ProductSpecParam;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecBO;
import com.lawu.eshop.product.srv.bo.ProductSpecBO;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public interface ProductSpecService {
    List<ProductSpecBO> getProductSpecByCategoryId(Integer productCategoryId);

    void addProductSpec(ProductSpecParam param);

    void editProductSpec(Long id, ProductSpecParam param);

    Page<OperatorProductSpecBO> getOperatorProductSpecList(OperatorProductSpecParam param);

    void delProductSpec(Long id);

    ProductSpecBO getSpecDetailById(Long id);
}
