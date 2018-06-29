package com.lawu.eshop.ad.srv.solr.mock;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lawu.eshop.ad.param.AdSolrRealParam;
import com.lawu.eshop.ad.param.AdsolrFindParam;
import com.lawu.eshop.ad.srv.solr.service.AdSolrService;
import com.lawu.eshop.solr.impl.entity.AdSolr;

@Primary
@Service
public class MockAdSolrService implements AdSolrService {

    @Override
    public void update(AdSolr solrBean) {
    }

    @Override
    public void save(AdSolr solrBean) {
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public void delete(List<Long> ids) {
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public void save(List<AdSolr> list) {
    }

    @Override
    public Page<AdSolr> selectChoiceness(AdSolrRealParam param) {
        return null;
    }

    @Override
    public Page<AdSolr> queryAdByTitle(AdsolrFindParam param) {
        return null;
    }

    @Override
    public Page<AdSolr> getRecommendAdByType(AdSolrRealParam param) {
        return null;
    }

    @Override
    public Page<AdSolr> getRecommendEgain(AdSolrRealParam param) {
        return null;
    }

    @Override
    public List<AdSolr> listAdRank(AdSolrRealParam param) {
        return null;
    }

}
