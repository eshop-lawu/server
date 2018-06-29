package com.lawu.eshop.mall.srv.solr.service.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.solr.MerchantStoreSolrService;
import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;
import com.lawu.eshop.solr.impl.repository.MerchantStoreSolrRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class MerchantStoreSolrServiceImplTest {
    
    @Autowired
    private MerchantStoreSolrRepository merchantStoreSolrRepository;
    
    @Autowired
    private MerchantStoreSolrService merchantStoreSolrService;
    
    @Ignore
    @Test
    public void update() {
        MerchantStoreSolr expected = new MerchantStoreSolr();
        expected.setId(1L);
        expected.setName("MerchantStoreSolr");
        
        merchantStoreSolrService.update(expected);
        
        MerchantStoreSolr actual = merchantStoreSolrRepository.findOne(1L);
        
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
    }
    
}
