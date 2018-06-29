package com.lawu.eshop.mall.srv.solr;

import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;

public interface MerchantStoreSolrService {
    
    /**
     * 更新索引
     * 
     * @author jiangxinjun
     * @createDate 2018年1月20日
     * @updateDate 2018年1月20日
     */
    void update(MerchantStoreSolr solrBean);
    
}
