package com.lawu.eshop.mall.srv.solr.mock;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.lawu.eshop.mall.srv.solr.MerchantStoreSolrService;
import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;

@Primary
@Service
public class MockMerchantStoreSolrService implements MerchantStoreSolrService {

    @Override
    public void update(MerchantStoreSolr solrBean) {
        // TODO Auto-generated method stub

    }
}
