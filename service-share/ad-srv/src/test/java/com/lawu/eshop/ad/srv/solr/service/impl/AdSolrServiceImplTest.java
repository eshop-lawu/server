package com.lawu.eshop.ad.srv.solr.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.OrderTypeEnum;
import com.lawu.eshop.ad.constants.PraiseTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.param.AdSolrParam;
import com.lawu.eshop.ad.param.AdSolrRealParam;
import com.lawu.eshop.ad.param.AdsolrFindParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.solr.service.AdSolrService;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.UserSexEnum;
import com.lawu.eshop.solr.impl.entity.AdSolr;
import com.lawu.eshop.solr.impl.repository.AdSolrRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
public class AdSolrServiceImplTest {
    
    @Autowired
    private AdSolrRepository adSolrRepository;
    
    @Autowired
    private AdSolrService adSolrService;
    
    @Ignore
    @Test
    public void getById() throws SolrServerException, IOException {
        // 通过Repository查询
        AdSolr merchantStoreSolr = adSolrRepository.findOne(1L);
        Assert.assertNotNull(merchantStoreSolr);
    }
    
    @Ignore
    @Test
    public void save() {
        AdSolr expected = new AdSolr();
        expected.setAdCount(10);
        expected.setAdMark(1);
        expected.setArea(Arrays.asList(new Integer[] {0}));
        expected.setBeginTime(new Date().getTime());
        expected.setContent("广告内容");
        expected.setId(1L);
        expected.setLatLon("22.5440000,113.9581000");
        expected.setMediaUrl("group1/M01/00/60/wKgBtVpTP0CAP1r0AAJO0fSLKB0089.jpg");
        expected.setMerchantId(1L);
        expected.setPoint(1D);
        expected.setPraiseType(PraiseTypeEnum.PRAISE_TYPE_PUZZLE.getVal());
        expected.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
        expected.setRadius(30);
        expected.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        expected.setStoreLogo("group1/M00/00/5C/wKgBtFo8VxWAMbNFAAAvDvar19o757.png");
        expected.setStoreName("storeName");
        expected.setTitle("title");
        expected.setTotalPoint(10D);
        expected.setType(TypeEnum.TYPE_AD.val);
        expected.setVideoImgUrl("group1/M00/00/5E/wKgBtFpO5MyAV7g1AAA_ca6t51E244.jpg");
        expected.setViewCount(5);
        expected.setHits(10);
        adSolrService.save(expected);
        AdSolr actual = adSolrRepository.findOne(expected.getId());
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getAdCount(), actual.getAdCount());
        Assert.assertEquals(expected.getAdMark(), actual.getAdMark());
        Assert.assertEquals(expected.getArea(), actual.getArea());
        Assert.assertEquals(expected.getBeginTime(), actual.getBeginTime());
        Assert.assertEquals(expected.getContent(), actual.getContent());
        Assert.assertEquals(expected.getHits(), actual.getHits());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getLatLon(), actual.getLatLon());
        Assert.assertEquals(expected.getMediaUrl(), actual.getMediaUrl());
        Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
        Assert.assertEquals(expected.getPoint(), actual.getPoint());
        Assert.assertEquals(expected.getPraiseType(), actual.getPraiseType());
        Assert.assertEquals(expected.getPutWay(), actual.getPutWay());
        Assert.assertEquals(expected.getRadius(), actual.getRadius());
        Assert.assertEquals(expected.getStatus(), actual.getStatus());
        Assert.assertEquals(expected.getStoreLogo(), actual.getStoreLogo());
        Assert.assertEquals(expected.getStoreName(), actual.getStoreName());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getTotalPoint(), actual.getTotalPoint());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getVideoImgUrl(), actual.getVideoImgUrl());
        Assert.assertEquals(expected.getViewCount(), actual.getViewCount());
    }
    
    @Ignore
    @Test
    public void saveBatch() {
        Map<Long, AdSolr> map = new HashMap<Long, AdSolr>();
        List<AdSolr> list = new ArrayList<>();
        AdSolr adSolr = new AdSolr();
        adSolr.setAdCount(1);
        adSolr.setAdMark(1);
        adSolr.setArea(Arrays.asList(new Integer[] {0}));
        adSolr.setBeginTime(new Date().getTime());
        adSolr.setContent("广告内容");
        adSolr.setId(1L);
        adSolr.setLatLon("22.5440000,113.9581000");
        adSolr.setMediaUrl("group1/M01/00/60/wKgBtVpTP0CAP1r0AAJO0fSLKB0089.jpg");
        adSolr.setMerchantId(1L);
        adSolr.setPoint(1D);
        adSolr.setPraiseType(PraiseTypeEnum.PRAISE_TYPE_PUZZLE.getVal());
        adSolr.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
        adSolr.setRadius(30);
        adSolr.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        adSolr.setStoreLogo("group1/M00/00/5C/wKgBtFo8VxWAMbNFAAAvDvar19o757.png");
        adSolr.setStoreName("storeName");
        adSolr.setTitle("title");
        adSolr.setTotalPoint(10D);
        adSolr.setType(TypeEnum.TYPE_AD.val);
        adSolr.setVideoImgUrl("group1/M00/00/5E/wKgBtFpO5MyAV7g1AAA_ca6t51E244.jpg");
        adSolr.setViewCount(20);
        list.add(adSolr);
        map.put(adSolr.getId(), adSolr);
        adSolr.setAdCount(1);
        adSolr.setAdMark(1);
        adSolr.setArea(Arrays.asList(new Integer[] {0}));
        adSolr.setBeginTime(new Date().getTime());
        adSolr.setContent("广告内容");
        adSolr.setId(2L);
        adSolr.setLatLon("22.5440000,113.9581000");
        adSolr.setMediaUrl("group1/M01/00/60/wKgBtVpTP0CAP1r0AAJO0fSLKB0089.jpg");
        adSolr.setMerchantId(1L);
        adSolr.setPoint(1D);
        adSolr.setPraiseType(PraiseTypeEnum.PRAISE_TYPE_PUZZLE.getVal());
        adSolr.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
        adSolr.setRadius(30);
        adSolr.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        adSolr.setStoreLogo("group1/M00/00/5C/wKgBtFo8VxWAMbNFAAAvDvar19o757.png");
        adSolr.setStoreName("storeName");
        adSolr.setTitle("title");
        adSolr.setTotalPoint(10D);
        adSolr.setType(TypeEnum.TYPE_AD.val);
        adSolr.setVideoImgUrl("group1/M00/00/5E/wKgBtFpO5MyAV7g1AAA_ca6t51E244.jpg");
        adSolr.setViewCount(20);
        list.add(adSolr);
        map.put(adSolr.getId(), adSolr);
        adSolrService.save(list);
        
        for (AdSolr item : list) {
            AdSolr expected = map.get(item.getId());
            AdSolr actual = adSolrRepository.findOne(item.getId());
            Assert.assertNotNull(actual);
            Assert.assertEquals(expected.getAdCount(), actual.getAdCount());
            Assert.assertEquals(expected.getAdMark(), actual.getAdMark());
            Assert.assertEquals(expected.getArea(), actual.getArea());
            Assert.assertEquals(expected.getBeginTime(), actual.getBeginTime());
            Assert.assertEquals(expected.getContent(), actual.getContent());
            Assert.assertEquals(expected.getHits(), actual.getHits());
            Assert.assertEquals(expected.getId(), actual.getId());
            Assert.assertEquals(expected.getLatLon(), actual.getLatLon());
            Assert.assertEquals(expected.getMediaUrl(), actual.getMediaUrl());
            Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
            Assert.assertEquals(expected.getPoint(), actual.getPoint());
            Assert.assertEquals(expected.getPraiseType(), actual.getPraiseType());
            Assert.assertEquals(expected.getPutWay(), actual.getPutWay());
            Assert.assertEquals(expected.getRadius(), actual.getRadius());
            Assert.assertEquals(expected.getStatus(), actual.getStatus());
            Assert.assertEquals(expected.getStoreLogo(), actual.getStoreLogo());
            Assert.assertEquals(expected.getStoreName(), actual.getStoreName());
            Assert.assertEquals(expected.getTitle(), actual.getTitle());
            Assert.assertEquals(expected.getTotalPoint(), actual.getTotalPoint());
            Assert.assertEquals(expected.getType(), actual.getType());
            Assert.assertEquals(expected.getVideoImgUrl(), actual.getVideoImgUrl());
            Assert.assertEquals(expected.getViewCount(), actual.getViewCount());
        }
    }
    
    @Ignore
    @Test
    public void update() {
        AdSolr expected = new AdSolr();
        expected.setAdCount(1);
        expected.setAdMark(1);
        expected.setArea(Arrays.asList(new Integer[] {0}));
        expected.setBeginTime(new Date().getTime());
        expected.setContent("广告内容");
        expected.setId(1L);
        expected.setLatLon("22.5440000,113.9581000");
        expected.setMediaUrl("group1/M01/00/60/wKgBtVpTP0CAP1r0AAJO0fSLKB0089.jpg");
        expected.setMerchantId(1L);
        expected.setPoint(1D);
        expected.setPraiseType(PraiseTypeEnum.PRAISE_TYPE_PUZZLE.getVal());
        expected.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
        expected.setRadius(30);
        expected.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        expected.setStoreLogo("group1/M00/00/5C/wKgBtFo8VxWAMbNFAAAvDvar19o757.png");
        expected.setStoreName("storeName");
        expected.setTitle("title");
        expected.setTotalPoint(10D);
        expected.setType(TypeEnum.TYPE_AD.val);
        expected.setVideoImgUrl("group1/M00/00/5E/wKgBtFpO5MyAV7g1AAA_ca6t51E244.jpg");
        expected.setViewCount(20);
        adSolrService.update(expected);
        AdSolr actual = adSolrRepository.findOne(1L);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getAdCount(), actual.getAdCount());
        Assert.assertEquals(expected.getAdMark(), actual.getAdMark());
        Assert.assertEquals(expected.getArea(), actual.getArea());
        Assert.assertEquals(expected.getBeginTime(), actual.getBeginTime());
        Assert.assertEquals(expected.getContent(), actual.getContent());
        Assert.assertEquals(expected.getHits(), actual.getHits());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getLatLon(), actual.getLatLon());
        Assert.assertEquals(expected.getMediaUrl(), actual.getMediaUrl());
        Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
        Assert.assertEquals(expected.getPoint(), actual.getPoint());
        Assert.assertEquals(expected.getPraiseType(), actual.getPraiseType());
        Assert.assertEquals(expected.getPutWay(), actual.getPutWay());
        Assert.assertEquals(expected.getRadius(), actual.getRadius());
        Assert.assertEquals(expected.getStatus(), actual.getStatus());
        Assert.assertEquals(expected.getStoreLogo(), actual.getStoreLogo());
        Assert.assertEquals(expected.getStoreName(), actual.getStoreName());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getTotalPoint(), actual.getTotalPoint());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getVideoImgUrl(), actual.getVideoImgUrl());
        Assert.assertEquals(expected.getViewCount(), actual.getViewCount());
    }
    
    @Ignore
    @Test
    public void deleteBatch() {
        List<Long> ids = new ArrayList<>();
        ids.add(9L);
        ids.add(10L);
        adSolrService.delete(ids);
        for (Long id : ids) {
            AdSolr AdSolr = adSolrRepository.findOne(id);
            Assert.assertNull(AdSolr);
        }
    }
    
    @Ignore
    @Test
    public void delete() {
        Long id = 12L;
        adSolrService.delete(id);
        AdSolr AdSolr = adSolrRepository.findOne(id);
        Assert.assertNull(AdSolr);
    }
    
    @Ignore
    @Test
    public void getRecommendAdByType() {
        AdSolrRealParam param = new AdSolrRealParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        param.setAge(10);
        param.setSexEnum(UserSexEnum.SEX_SECRET);
        Page<AdSolr> page = adSolrService.getRecommendAdByType(param);
        Assert.assertNotNull(page);
    }
    
    @Ignore
    @Test
    public void getRecommendEgain() {
        AdSolrRealParam param = new AdSolrRealParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setStatusEnum(AdStatusEnum.AD_STATUS_ADD);
        Page<AdSolr> page = adSolrService.getRecommendEgain(param);
        Assert.assertNotNull(page);
    }
    
    @Ignore
    @Test
    public void queryAdByTitle() {
        AdsolrFindParam param = new AdsolrFindParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setAdSolrParam(new AdSolrParam());
        Page<AdSolr> page = adSolrService.queryAdByTitle(param);
        Assert.assertNotNull(page);
    }
    
    @Ignore
    @Test
    public void selectChoiceness() {
        AdSolrRealParam param = new AdSolrRealParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<AdSolr> page = adSolrService.selectChoiceness(param);
        Assert.assertNotNull(page);
    }
    
    @Ignore
    @Test
    public void listAdRank() {
        AdSolrRealParam param = new AdSolrRealParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setOrderTypeEnum(OrderTypeEnum.AD_POINT_DESC);
        List<AdSolr> list = adSolrService.listAdRank(param);
        Assert.assertNotNull(list);
    }
}
