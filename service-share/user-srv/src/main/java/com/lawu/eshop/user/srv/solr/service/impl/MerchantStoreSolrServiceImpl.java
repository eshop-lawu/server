package com.lawu.eshop.user.srv.solr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.solr.impl.BaseSolrServiceImpl;
import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;
import com.lawu.eshop.solr.impl.repository.MerchantStoreSolrRepository;
import com.lawu.eshop.user.constants.StoreSolrEnum;
import com.lawu.eshop.user.param.DiscountStoreParam;
import com.lawu.eshop.user.param.StoreSolrParam;
import com.lawu.eshop.user.srv.solr.service.MerchantStoreSolrService;

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
            return;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(MerchantStoreSolr solrBean) {
        repository.save(solrBean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Long> ids) {
        List<MerchantStoreSolr> idsToDelete = new ArrayList<>();
        for (Long id : ids) {
            MerchantStoreSolr item = new MerchantStoreSolr();
            item.setId(id);
            idsToDelete.add(item);
        }
        repository.delete(idsToDelete);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(List<MerchantStoreSolr> list) {
        repository.save(list);
    }

    @Override
    public Page<MerchantStoreSolr> listStore(StoreSolrParam param) {
        double lat = param.getLatitude().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        double lon = param.getLongitude().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        String latLon = lat + "," + lon;
        StringBuilder sb = new StringBuilder("regionPath_s:");
        sb.append(param.getRegionPath()).append("*");
        if (StringUtils.isNotEmpty(param.getName())) {
            sb.append(" AND text:").append(param.getName());
        }
        if (StringUtils.isNotEmpty(param.getIndustryPath())) {
            sb.append(" AND industryPath_s:").append(param.getIndustryPath()).append("*");
        }
        if (param.getStoreId() != null && param.getStoreId() > 0) {
            sb.append(" AND -id:").append(param.getStoreId());
        }
        SolrQuery query = new SolrQuery();
        query.setParam("q", sb.toString());
        if (param.getDistance() != null && param.getDistance() > 0) {
            query.setParam("d", String.valueOf(param.getDistance()));
        } else {
            query.setParam("d", "10000000");
        }
        query.setParam("pt", latLon);
        query.setParam("fq", "{!geofilt}");
        query.setParam("sfield", "latLon_p");
        query.setParam("fl", "*,distance:geodist(latLon_p," + latLon + ")");

        if (param.getStoreSolrEnum() != null) {
            if (param.getStoreSolrEnum().val == StoreSolrEnum.DISTANCE_SORT.val) {
                query.setParam("sort", "geodist() asc");
            } else if (param.getStoreSolrEnum().val == StoreSolrEnum.FEEDBACK_SORT.val) {
                query.setParam("sort", "averageScore_d desc");
            } else if (param.getStoreSolrEnum().val == StoreSolrEnum.POPULARITY_SORT.val) {
                query.setParam("sort", "favoriteNumber_i desc");
            } else {
                query.setParam("sort", "favoriteNumber_i desc,averageScore_d desc,geodist() asc");
            }
        }
        query.setStart(param.getOffset());
        query.setRows(param.getPageSize());
        return query(query);
    }
    
    @Override
    public Page<MerchantStoreSolr> findKeywordssByKeywordssStartingWith(String keyword, String regionPath) {
        Pageable pageable = new SolrPageRequest(0, 10);
        return repository.findKeywordssByKeywordssStartingWith(keyword, regionPath, pageable);
    }

    @Override
    public Page<MerchantStoreSolr> findCountBykeyword(String keyword, String regionPath) {
        Pageable pageable = new SolrPageRequest(0, 10);
        return repository.findCountBykeyword(keyword, regionPath, pageable);
    }

    @Override
    public Page<MerchantStoreSolr> discountStore(DiscountStoreParam discountStoreParam) {
        double lat = discountStoreParam.getLatitude().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        double lon = discountStoreParam.getLongitude().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        String latLon = lat + "," + lon;
        StringBuilder sb = new StringBuilder("regionPath_s:");
        sb.append(discountStoreParam.getRegionPath()).append("*");
        SolrQuery query = new SolrQuery();
        query.setParam("q", sb.toString());
        query.setParam("d", "10000000");
        query.setParam("pt", latLon);
        query.setParam("fq", "{!geofilt}");
        query.setParam("sfield", "latLon_p");
        query.setParam("fl", "*,distance:geodist(latLon_p," + latLon + ")");
        query.setParam("sort", "discountOrdinal_d asc");
        query.setStart(discountStoreParam.getOffset());
        query.setRows(discountStoreParam.getPageSize());
        return query(query);
    }
}
