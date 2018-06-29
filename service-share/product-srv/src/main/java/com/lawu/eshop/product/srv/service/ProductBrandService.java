package com.lawu.eshop.product.srv.service;

import java.util.List;

import com.lawu.eshop.product.param.OperatorProductBrandParam;
import com.lawu.eshop.product.param.ProductBrandParam;
import com.lawu.eshop.product.srv.bo.OperatorProductBrandBO;
import com.lawu.eshop.product.srv.bo.ProductBrandBO;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public interface ProductBrandService {
    List<ProductBrandBO> getProductBrandByCategoryId(Integer productCategoryId);

    ProductBrandBO getProductBrandById(Long brandId);

    void addProductBrand(ProductBrandParam param);

    ProductBrandBO getProductBrandDetail(Long id);

    void editProductBrand(Long id, ProductBrandParam param);

    Page<OperatorProductBrandBO> getOperatorProductBrandList(OperatorProductBrandParam param);

    void delProductBrand(Long id);

    List<ProductBrandBO> getProductBrandByName(String brandName,Integer categoryId);

    List<ProductBrandBO> listProductBrand(Integer productCategoryId);
}
