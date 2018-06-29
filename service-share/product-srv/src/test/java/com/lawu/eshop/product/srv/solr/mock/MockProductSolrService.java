package com.lawu.eshop.product.srv.solr.mock;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lawu.eshop.product.param.ProductListSearchParam;
import com.lawu.eshop.product.param.ProductSearchParam;
import com.lawu.eshop.product.param.ProductSearchRealParam;
import com.lawu.eshop.product.srv.solr.service.ProductSolrService;
import com.lawu.eshop.solr.impl.entity.ProductSolr;

@Primary
@Service
public class MockProductSolrService implements ProductSolrService {

    @Override
    public void update(ProductSolr product) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void save(ProductSolr product) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Page<ProductSolr> listProductByCategoryId(ProductSearchRealParam param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<ProductSolr> listRecommendProduct(ProductSearchRealParam param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<ProductSolr> listYouLikeProduct(ProductSearchParam param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<ProductSolr> listProductByName(ProductSearchRealParam param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<ProductSolr> findKeywordssByKeywordssStartingWith(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<ProductSolr> findCountBykeyword(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<ProductSolr> findProductSearchList(ProductSearchParam param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(List<Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void save(List<ProductSolr> list) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Page<ProductSolr> listProduct(ProductListSearchParam param) {
        return null;
    }
}
