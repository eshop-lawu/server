package com.lawu.eshop.product.srv.solr.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.product.param.ProductListSearchParam;
import com.lawu.eshop.product.param.ProductSearchParam;
import com.lawu.eshop.product.param.ProductSearchRealParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.solr.service.ProductSolrService;
import com.lawu.eshop.solr.impl.entity.ProductSolr;
import com.lawu.eshop.solr.impl.repository.ProductSolrRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
public class ProductSolrServiceImplTest {
    
    @Autowired
    private SolrClient solrClient;
    
    @Autowired
    private ProductSolrRepository productSolrRepository;
    
    @Autowired
    private ProductSolrService productSolrService;
    
    @Ignore
    @Test
    public void getById() throws SolrServerException, IOException {
        // 原生查询
        SolrDocument solrDocument = solrClient.getById("product", "9");
        Assert.assertNotNull(solrDocument);
        
        // 通过Repository查询
        Page<ProductSolr> page = productSolrRepository.findAll(new SolrPageRequest(0, 10));
        Assert.assertNotNull(page);
    }
    
    @Ignore
    @Test
    public void save() {
        ProductSolr expected = new ProductSolr();
        expected.setAverageDailySales(1D);
        expected.setCategoryId(1);
        expected.setFeatureImage("test.jpg");
        expected.setId(1L);
        expected.setInventory(1);
        expected.setName("test2");
        expected.setOriginalPrice(2D);
        expected.setPrice(1D);
        expected.setSalesVolume(1);
        productSolrService.save(expected);
        
        ProductSolr actual = productSolrRepository.findOne(expected.getId());
        Assert.assertEquals(expected.getAverageDailySales().doubleValue(), actual.getAverageDailySales().doubleValue(), 0D);
        Assert.assertEquals(expected.getCategoryId(), actual.getCategoryId());
        Assert.assertEquals(expected.getFeatureImage(), actual.getFeatureImage());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getInventory(), actual.getInventory());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getOriginalPrice().doubleValue(), actual.getOriginalPrice().doubleValue(), 0D);
        Assert.assertEquals(expected.getPrice().doubleValue(), actual.getPrice().doubleValue(), 0D);
        Assert.assertEquals(expected.getSalesVolume(), actual.getSalesVolume());
    }
    
    @Ignore
    @Test
    public void saveBatch() {
        Map<Long, ProductSolr> map = new HashMap<Long, ProductSolr>();
        List<ProductSolr> list = new ArrayList<>();
        ProductSolr productSolr = new ProductSolr();
        productSolr.setAverageDailySales(1D);
        productSolr.setCategoryId(1);
        productSolr.setFeatureImage("test.jpg");
        productSolr.setId(1L);
        productSolr.setInventory(1);
        productSolr.setName("test1");
        productSolr.setOriginalPrice(2D);
        productSolr.setPrice(1D);
        productSolr.setSalesVolume(1);
        list.add(productSolr);
        map.put(productSolr.getId(), productSolr);
        productSolr = new ProductSolr();
        productSolr.setAverageDailySales(1D);
        productSolr.setCategoryId(1);
        productSolr.setFeatureImage("test.jpg");
        productSolr.setId(2L);
        productSolr.setInventory(1);
        productSolr.setName("test2");
        productSolr.setOriginalPrice(2D);
        productSolr.setPrice(1D);
        productSolr.setSalesVolume(1);
        list.add(productSolr);
        map.put(productSolr.getId(), productSolr);
        productSolrService.save(list);
        
        for (ProductSolr item : list) {
            ProductSolr expected = map.get(item.getId());
            ProductSolr actual = productSolrRepository.findOne(item.getId());
            Assert.assertEquals(expected.getAverageDailySales().doubleValue(), actual.getAverageDailySales().doubleValue(), 0D);
            Assert.assertEquals(expected.getCategoryId(), actual.getCategoryId());
            Assert.assertEquals(expected.getFeatureImage(), actual.getFeatureImage());
            Assert.assertEquals(expected.getId(), actual.getId());
            Assert.assertEquals(expected.getInventory(), actual.getInventory());
            Assert.assertEquals(expected.getName(), actual.getName());
            Assert.assertEquals(expected.getOriginalPrice().doubleValue(), actual.getOriginalPrice().doubleValue(), 0D);
            Assert.assertEquals(expected.getPrice().doubleValue(), actual.getPrice().doubleValue(), 0D);
            Assert.assertEquals(expected.getSalesVolume(), actual.getSalesVolume());
        }
    }
    
    @Ignore
    @Test
    public void update() {
        ProductSolr expected = new ProductSolr();
        expected.setId(1L);
        expected.setName("productSolr");
        productSolrService.update(expected);
        ProductSolr actual = productSolrRepository.findOne(1L);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
    }
    
    @Ignore
    @Test
    public void listProductByCategoryId() {
        ProductSearchRealParam param = new ProductSearchRealParam();
        param.setCategoryId(1);
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<ProductSolr> page = productSolrService.listProductByCategoryId(param);
        Assert.assertNotNull(page);
        for (ProductSolr item : page.getContent()) {
            Assert.assertEquals(param.getCategoryId(), item.getCategoryId());
        }
    }
    
    @Ignore
    @Test
    public void listRecommendProduct() {
        ProductSearchRealParam param = new ProductSearchRealParam();
        param.setCategoryId(1);
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setProductId(61L);
        Page<ProductSolr> page = productSolrService.listRecommendProduct(param);
        Assert.assertNotNull(page);
        for (ProductSolr item : page.getContent()) {
            Assert.assertEquals(param.getCategoryId(), item.getCategoryId());
            Assert.assertNotEquals(param.getProductId().toString(), item.getId());
        }
    }
    
    @Ignore
    @Test
    public void listYouLikeProduct() {
        ProductSearchParam param = new ProductSearchParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<ProductSolr> page = productSolrService.listYouLikeProduct(param);
        Assert.assertNotNull(page);
    }
    
    @Ignore
    @Test
    public void listProductByName() {
        ProductSearchRealParam param = new ProductSearchRealParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setName("糖");
        Page<ProductSolr> page = productSolrService.listProductByName(param);
        Assert.assertNotNull(page);
        for (ProductSolr item : page.getContent()) {
            Assert.assertTrue(item.getName().contains(param.getName()) || item.getKeywords().contains(param.getName()));
        }
    }
    
    @Ignore
    @Test
    public void findKeywordssByKeywordssStartingWith() {
        String key = "电";
        Page<ProductSolr> page = productSolrService.findKeywordssByKeywordssStartingWith(key);
        Assert.assertNotNull(page);
        for (ProductSolr item : page.getContent()) {
            boolean ismatch = false;
            for (String keyword : item.getKeywordss()) {
                if (keyword.startsWith(key)) {
                    ismatch = true;
                    break;
                }
            }
            Assert.assertTrue(ismatch);
        }
    }
    
    @Ignore
    @Test
    public void findCountBykeyword() {
        String key = "电";
        Page<ProductSolr> page = productSolrService.findCountBykeyword(key);
        Assert.assertNotNull(page);
        Assert.assertEquals(2, page.getTotalElements());
    }
    
    @Ignore
    @Test
    public void findProductSearchList() {
        ProductSearchParam param = new ProductSearchParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<ProductSolr> page = productSolrService.findProductSearchList(param);
        Assert.assertNotNull(page);
    }
    
    @Ignore
    @Test
    public void deleteBatch() {
        List<Long> ids = new ArrayList<>();
        ids.add(9L);
        ids.add(10L);
        productSolrService.delete(ids);
        for (Long id : ids) {
            ProductSolr productSolr = productSolrRepository.findOne(id);
            Assert.assertNull(productSolr);
        }
    }
    
    @Ignore
    @Test
    public void delete() {
        Long id = 12L;
        productSolrService.delete(id);
        ProductSolr productSolr = productSolrRepository.findOne(id);
        Assert.assertNull(productSolr);
    }

    @Ignore
    @Test
    public void listProduct() {
        ProductListSearchParam param = new ProductListSearchParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<ProductSolr> page = productSolrService.listProduct(param);
        Assert.assertNotNull(page);
    }

}
