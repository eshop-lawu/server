package com.lawu.eshop.product.srv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.product.constant.ProductPositionEnum;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.param.ListShoppingProductParam;
import com.lawu.eshop.product.srv.bo.ProductSearchBO;
import com.lawu.eshop.product.srv.converter.ProductConverter;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.ProductDOExample;
import com.lawu.eshop.product.srv.domain.extend.ShoppingProductDOView;
import com.lawu.eshop.product.srv.mapper.ProductDOMapper;
import com.lawu.eshop.product.srv.mapper.extend.ProductDOMapperExtend;
import com.lawu.eshop.product.srv.service.ShoppingProductService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/4/21.
 */
@Service
public class ShoppingProductServiceImpl implements ShoppingProductService {

    @Autowired
    private ProductDOMapperExtend productDOMapperExtend;

    @Autowired
    private ProductDOMapper productDOMapper;

    @Override
    public Page<ProductSearchBO> listHotProduct(ListShoppingProductParam listShoppingProductParam) {
        Page<ProductSearchBO> page = new Page<>();
        page.setCurrentPage(listShoppingProductParam.getCurrentPage());
        page.setTotalCount(5);

        ProductDOExample example = new ProductDOExample();
        example.setOrderByClause("total_sales_volume desc");
        example.createCriteria().andMerchantIdEqualTo(listShoppingProductParam.getMerchantId()).andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal()).andPositionEqualTo(ProductPositionEnum.SHOPWINDOW.getVal());
        List<ProductDO> productDOS = productDOMapper.selectByExample(example);
        if (!productDOS.isEmpty() && productDOS.size() >= 5) {
            page.setRecords(ProductConverter.convertProductSearchBOS(productDOS.subList(0, 5)));
            return page;
        }

        example.clear();
        example.setOrderByClause("total_sales_volume desc");
        example.createCriteria().andMerchantIdEqualTo(listShoppingProductParam.getMerchantId()).andStatusEqualTo(ProductStatusEnum.PRODUCT_STATUS_UP.getVal()).andPositionNotEqualTo(ProductPositionEnum.SHOPWINDOW.getVal());
        productDOS.addAll(productDOMapper.selectByExample(example));
        if (!productDOS.isEmpty() && productDOS.size() >= 5) {
            page.setRecords(ProductConverter.convertProductSearchBOS(productDOS.subList(0, 5)));
        } else {
            page.setTotalCount(productDOS.size());
            page.setRecords(ProductConverter.convertProductSearchBOS(productDOS));
        }
        return page;
    }

    @Override
    public Page<ProductSearchBO> listAllProduct(ListShoppingProductParam listShoppingProductParam) {
        List<ShoppingProductDOView> productDOViews = productDOMapperExtend.listAllProduct(listShoppingProductParam);
        Page<ProductSearchBO> page = new Page<>();
        page.setCurrentPage(listShoppingProductParam.getCurrentPage());
        page.setTotalCount(0);
        page.setRecords(ProductConverter.convertSearchBOS(productDOViews));
        return page;
    }

    @Override
    public Page<ProductSearchBO> listNewProduct(ListShoppingProductParam listShoppingProductParam) {
        List<ShoppingProductDOView> productDOViews = productDOMapperExtend.listNewProduct(listShoppingProductParam);
        Page<ProductSearchBO> page = new Page<>();
        page.setCurrentPage(listShoppingProductParam.getCurrentPage());
        page.setTotalCount(0);
        page.setRecords(ProductConverter.convertSearchBOS(productDOViews));
        return page;
    }
}
