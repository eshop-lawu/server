package com.lawu.eshop.product.srv.service;

import java.util.List;

import com.lawu.eshop.product.param.OperatorSpecOptionListParam;
import com.lawu.eshop.product.param.ProductSpecOptionParam;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecOptionBO;
import com.lawu.eshop.product.srv.bo.ProductSpecOptionBO;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public interface ProductSpecOptionService {
    List<ProductSpecOptionBO> getSpecOptionBySpecId(Long productSpecId);

    void addProductSpecOption(ProductSpecOptionParam param);

    void editProductSpecOption(Long id, ProductSpecOptionParam param);

    void delProductSpecOption(Long id);

    ProductSpecOptionBO getSpecOptionDetail(Long id);

    Page<OperatorProductSpecOptionBO> getOperatorSpecOptionList(OperatorSpecOptionListParam param);

}
