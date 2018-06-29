package com.lawu.eshop.user.srv.solr.mock;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;
import com.lawu.eshop.user.param.DiscountStoreParam;
import com.lawu.eshop.user.param.StoreSolrParam;
import com.lawu.eshop.user.srv.solr.service.MerchantStoreSolrService;

@Primary
@Service
public class MockMerchantStoreSolrService implements MerchantStoreSolrService {

    @Override
    public void update(MerchantStoreSolr solrBean) {
        // TODO Auto-generated method stub

    }

    @Override
    public void save(MerchantStoreSolr solrBean) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

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
    public void save(List<MerchantStoreSolr> list) {
        // TODO Auto-generated method stub

    }

    @Override
    public Page<MerchantStoreSolr> listStore(StoreSolrParam storeSolrParam) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<MerchantStoreSolr> findKeywordssByKeywordssStartingWith(String keyword, String regionPath) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<MerchantStoreSolr> findCountBykeyword(String keyword, String regionPath) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<MerchantStoreSolr> discountStore(DiscountStoreParam discountStoreParam) {
        // TODO Auto-generated method stub
        return null;
    }

}
