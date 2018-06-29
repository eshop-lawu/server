package com.lawu.eshop.user.srv.solr.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;
import com.lawu.eshop.solr.impl.repository.MerchantStoreSolrRepository;
import com.lawu.eshop.user.constants.StoreSolrEnum;
import com.lawu.eshop.user.param.DiscountStoreParam;
import com.lawu.eshop.user.param.StoreSolrParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.solr.service.MerchantStoreSolrService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class MerchantStoreSolrServiceImplTest {
    
    @Autowired
    private MerchantStoreSolrRepository merchantStoreSolrRepository;
    
    @Autowired
    private MerchantStoreSolrService merchantStoreSolrService;
    
    @Ignore
    @Test
    public void getById() throws SolrServerException, IOException {
        // 通过Repository查询
        MerchantStoreSolr merchantStoreSolr = merchantStoreSolrRepository.findOne(1L);
        Assert.assertNotNull(merchantStoreSolr);
    }
    
    @Ignore
    @Test
    public void save() {
        MerchantStoreSolr expected = new MerchantStoreSolr();
        expected.setId(1L);
        expected.setAverageConsumeAmount(0D);
        expected.setAverageScore(0D);
        expected.setDiscountOrdinal(0D);
        expected.setDiscountPackage("优惠套餐");
        expected.setFavoreEndTime("2017-01-26");
        expected.setFavoreInfo("打折");
        expected.setFavoriteNumber(10);
        expected.setIndustryName("生活服务-宠物服务");
        expected.setIndustryPath("15/1506");
        expected.setKeywords("二哈,金毛");
        expected.setKeywordss(Arrays.asList(new String[]{"", ""}));
        expected.setLatLon("22.5464360,113.9601400");
        expected.setMerchantId(1L);
        expected.setName("爱心宠物店");
        expected.setRegionPath("44/4403/440305");
        expected.setStorePic("store/1502161171214377506.jpg");
        merchantStoreSolrService.save(expected);
        MerchantStoreSolr actual = merchantStoreSolrRepository.findOne(expected.getId());
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getAverageConsumeAmount(), actual.getAverageConsumeAmount());
        Assert.assertEquals(expected.getAverageScore(), actual.getAverageScore());
        Assert.assertEquals(expected.getDiscountOrdinal(), actual.getDiscountOrdinal());
        Assert.assertEquals(expected.getDiscountPackage(), actual.getDiscountPackage());
        Assert.assertEquals(expected.getFavoreEndTime(), actual.getFavoreEndTime());
        Assert.assertEquals(expected.getFavoreInfo(), actual.getFavoreInfo());
        Assert.assertEquals(expected.getFavoriteNumber(), actual.getFavoriteNumber());
        Assert.assertEquals(expected.getIndustryName(), actual.getIndustryName());
        Assert.assertEquals(expected.getIndustryPath(), actual.getIndustryPath());
        Assert.assertEquals(expected.getKeywords(), actual.getKeywords());
        Assert.assertEquals(expected.getKeywordss(), actual.getKeywordss());
        Assert.assertEquals(expected.getLatLon(), actual.getLatLon());
        Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getRegionPath(), actual.getRegionPath());
        Assert.assertEquals(expected.getStorePic(), actual.getStorePic());
    }
    
    @Ignore
    @Test
    public void saveBatch() {
        Map<Long, MerchantStoreSolr> map = new HashMap<Long, MerchantStoreSolr>();
        List<MerchantStoreSolr> list = new ArrayList<>();
        MerchantStoreSolr merchantStoreSolr = new MerchantStoreSolr();
        merchantStoreSolr.setId(1L);
        merchantStoreSolr.setAverageConsumeAmount(0D);
        merchantStoreSolr.setAverageScore(0D);
        merchantStoreSolr.setDiscountOrdinal(0D);
        merchantStoreSolr.setDiscountPackage("优惠套餐");
        merchantStoreSolr.setFavoreEndTime("2017-01-26");
        merchantStoreSolr.setFavoreInfo("打折");
        merchantStoreSolr.setFavoriteNumber(10);
        merchantStoreSolr.setIndustryName("生活服务-宠物服务");
        merchantStoreSolr.setIndustryPath("15/1506");
        merchantStoreSolr.setKeywords("二哈,金毛");
        merchantStoreSolr.setKeywordss(Arrays.asList(new String[]{"", ""}));
        merchantStoreSolr.setLatLon("22.5464360,113.9601400");
        merchantStoreSolr.setMerchantId(1L);
        merchantStoreSolr.setName("爱心宠物店");
        merchantStoreSolr.setRegionPath("44/4403/440305");
        merchantStoreSolr.setStorePic("store/1502161171214377506.jpg");
        list.add(merchantStoreSolr);
        map.put(merchantStoreSolr.getId(), merchantStoreSolr);
        merchantStoreSolr = new MerchantStoreSolr();
        merchantStoreSolr.setId(2L);
        merchantStoreSolr.setAverageConsumeAmount(0D);
        merchantStoreSolr.setAverageScore(0D);
        merchantStoreSolr.setDiscountOrdinal(0D);
        merchantStoreSolr.setDiscountPackage("优惠套餐");
        merchantStoreSolr.setFavoreEndTime("2017-01-26");
        merchantStoreSolr.setFavoreInfo("打折");
        merchantStoreSolr.setFavoriteNumber(10);
        merchantStoreSolr.setIndustryName("生活服务-宠物服务");
        merchantStoreSolr.setIndustryPath("15/1506");
        merchantStoreSolr.setKeywords("二哈,金毛");
        merchantStoreSolr.setKeywordss(Arrays.asList(new String[]{"", ""}));
        merchantStoreSolr.setLatLon("22.5464360,113.9601400");
        merchantStoreSolr.setMerchantId(1L);
        merchantStoreSolr.setName("爱心宠物店");
        merchantStoreSolr.setRegionPath("44/4403/440305");
        merchantStoreSolr.setStorePic("store/1502161171214377506.jpg");
        list.add(merchantStoreSolr);
        map.put(merchantStoreSolr.getId(), merchantStoreSolr);
        merchantStoreSolrService.save(list);
        
        for (MerchantStoreSolr item : list) {
            MerchantStoreSolr expected = map.get(item.getId());
            MerchantStoreSolr actual = merchantStoreSolrRepository.findOne(item.getId());
            Assert.assertNotNull(actual);
            Assert.assertEquals(expected.getId(), actual.getId());
            Assert.assertEquals(expected.getAverageConsumeAmount(), actual.getAverageConsumeAmount());
            Assert.assertEquals(expected.getAverageScore(), actual.getAverageScore());
            Assert.assertEquals(expected.getDiscountOrdinal(), actual.getDiscountOrdinal());
            Assert.assertEquals(expected.getDiscountPackage(), actual.getDiscountPackage());
            Assert.assertEquals(expected.getFavoreEndTime(), actual.getFavoreEndTime());
            Assert.assertEquals(expected.getFavoreInfo(), actual.getFavoreInfo());
            Assert.assertEquals(expected.getFavoriteNumber(), actual.getFavoriteNumber());
            Assert.assertEquals(expected.getIndustryName(), actual.getIndustryName());
            Assert.assertEquals(expected.getIndustryPath(), actual.getIndustryPath());
            Assert.assertEquals(expected.getKeywords(), actual.getKeywords());
            Assert.assertEquals(expected.getKeywordss(), actual.getKeywordss());
            Assert.assertEquals(expected.getLatLon(), actual.getLatLon());
            Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
            Assert.assertEquals(expected.getName(), actual.getName());
            Assert.assertEquals(expected.getRegionPath(), actual.getRegionPath());
            Assert.assertEquals(expected.getStorePic(), actual.getStorePic());
        }
    }
    
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
    
    @Ignore
    @Test
    public void deleteBatch() {
        List<Long> ids = new ArrayList<>();
        ids.add(9L);
        ids.add(10L);
        merchantStoreSolrService.delete(ids);
        for (Long id : ids) {
            MerchantStoreSolr MerchantStoreSolr = merchantStoreSolrRepository.findOne(id);
            Assert.assertNull(MerchantStoreSolr);
        }
    }
    
    @Ignore
    @Test
    public void delete() {
        Long id = 12L;
        merchantStoreSolrService.delete(id);
        MerchantStoreSolr MerchantStoreSolr = merchantStoreSolrRepository.findOne(id);
        Assert.assertNull(MerchantStoreSolr);
    }
    
    @Ignore
    @Test
    public void listStore() {
        StoreSolrParam param = new StoreSolrParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setDistance(1000);
        param.setLatitude(new BigDecimal(22.5));
        param.setLongitude(new BigDecimal(114.1));
        //param.setName("酒");
        //param.setIndustryPath("44/4403");
        param.setStoreSolrEnum(StoreSolrEnum.DISTANCE_SORT);
        param.setRegionPath("44/4403");
        Page<MerchantStoreSolr> merchantStoreSolr = merchantStoreSolrService.listStore(param);
        Assert.assertNotNull(merchantStoreSolr);
    }
    
    @Ignore
    @Test
    public void findKeywordssByKeywordssStartingWith() {
        String key = "酒";
        String regionPath = "44/4403";
        Page<MerchantStoreSolr> page = merchantStoreSolrService.findKeywordssByKeywordssStartingWith(key, regionPath);
        Assert.assertNotNull(page);
        for (MerchantStoreSolr item : page.getContent()) {
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
        String key = "酒";
        String regionPath = "44/4403";
        Page<MerchantStoreSolr> page = merchantStoreSolrService.findCountBykeyword(key, regionPath);
        Assert.assertNotNull(page);
    }
    
    @Ignore
    @Test
    public void discountStore() {
        DiscountStoreParam discountStoreParam = new DiscountStoreParam();
        discountStoreParam.setCurrentPage(1);
        discountStoreParam.setPageSize(10);
        discountStoreParam.setRegionPath("44/4403");
        discountStoreParam.setLatitude(new BigDecimal(22.5));
        discountStoreParam.setLongitude(new BigDecimal(114.1));
        Page<MerchantStoreSolr> page = merchantStoreSolrService.discountStore(discountStoreParam);
        Assert.assertNotNull(page);
    }
    
}
