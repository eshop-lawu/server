package com.lawu.eshop.mall.srv.solr.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.mall.srv.solr.MerchantStoreSolrService;
import com.lawu.eshop.solr.impl.BaseSolrServiceImpl;
import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;
import com.lawu.eshop.solr.impl.repository.MerchantStoreSolrRepository;

@Service
public class MerchantStoreSolrServiceImpl extends BaseSolrServiceImpl<MerchantStoreSolr, Long> implements MerchantStoreSolrService {
    
    private MerchantStoreSolrRepository repository;
    
    @Autowired
    private void setRepository(MerchantStoreSolrRepository repository) {
        this.repository = repository;
        super.init(repository);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(MerchantStoreSolr solrBean) {
        MerchantStoreSolr merchantStoreSolr = repository.findOne(solrBean.getId());
        if (merchantStoreSolr == null) {
            merchantStoreSolr = new MerchantStoreSolr();
        }
        
        // 添加默认值
        if (merchantStoreSolr.getFavoreInfo() == null) merchantStoreSolr.setFavoreInfo("");
        if (merchantStoreSolr.getFavoreEndTime() == null) merchantStoreSolr.setFavoreEndTime("");
        if (merchantStoreSolr.getDiscountPackage() == null) merchantStoreSolr.setDiscountPackage("");
        if (merchantStoreSolr.getDiscountOrdinal() == null) merchantStoreSolr.setDiscountOrdinal(1000D);
        
        if (solrBean.getName() != null) {
            merchantStoreSolr.setName(solrBean.getName());
        }
        if (solrBean.getAverageConsumeAmount() != null) {
            merchantStoreSolr.setAverageConsumeAmount(solrBean.getAverageConsumeAmount());
        }
        if (solrBean.getAverageScore() != null) {
            merchantStoreSolr.setAverageScore(solrBean.getAverageScore());
        }
        if (solrBean.getDiscountOrdinal() != null) {
            merchantStoreSolr.setDiscountOrdinal(solrBean.getDiscountOrdinal());
        }
        if (solrBean.getDiscountPackage() != null) {
            merchantStoreSolr.setDiscountPackage(solrBean.getDiscountPackage());
        }
        if (solrBean.getFavoreEndTime() != null) {
            merchantStoreSolr.setFavoreEndTime(solrBean.getFavoreEndTime());
        }
        if (solrBean.getFavoreInfo() != null) {
            merchantStoreSolr.setFavoreInfo(solrBean.getFavoreInfo());
        }
        if (solrBean.getFavoriteNumber() != null) {
            merchantStoreSolr.setFavoriteNumber(solrBean.getFavoriteNumber());
        }
        if (solrBean.getIndustryName() != null) {
            merchantStoreSolr.setIndustryName(solrBean.getIndustryName());
        }
        if (solrBean.getIndustryPath() != null) {
            merchantStoreSolr.setIndustryPath(solrBean.getIndustryPath());
        }
        if (solrBean.getKeywordss() != null) {
            merchantStoreSolr.setKeywordss(solrBean.getKeywordss());
        }
        if (solrBean.getKeywords() != null) {
            merchantStoreSolr.setKeywords(solrBean.getKeywords());
        }
        if (solrBean.getLatLon() != null) {
            merchantStoreSolr.setLatLon(solrBean.getLatLon());
        }
        if (solrBean.getMerchantId() != null) {
            merchantStoreSolr.setMerchantId(solrBean.getMerchantId());
        }
        if (solrBean.getName() != null) {
            merchantStoreSolr.setName(solrBean.getName());
        }
        if (solrBean.getRegionPath() != null) {
            merchantStoreSolr.setRegionPath(solrBean.getRegionPath());
        }
        if (solrBean.getStorePic() != null) {
            merchantStoreSolr.setStorePic(solrBean.getStorePic());
        }
        repository.save(merchantStoreSolr);
    }
}
