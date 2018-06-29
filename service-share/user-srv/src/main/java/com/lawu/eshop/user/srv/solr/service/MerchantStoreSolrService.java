package com.lawu.eshop.user.srv.solr.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;
import com.lawu.eshop.user.param.DiscountStoreParam;
import com.lawu.eshop.user.param.StoreSolrParam;

public interface MerchantStoreSolrService {
    
    /**
     * 更新索引
     * 
     * @author jiangxinjun
     * @createDate 2018年1月20日
     * @updateDate 2018年1月20日
     */
    void update(MerchantStoreSolr solrBean);
    
    /**
     * 全面更新以及新增索引
     * 
     * @author jiangxinjun
     * @createDate 2018年1月20日
     * @updateDate 2018年1月20日
     */
    void save(MerchantStoreSolr solrBean);
    
    /**
     * 删除所有索引
     * 
     * @author jiangxinjun
     * @createDate 2018年1月21日
     * @updateDate 2018年1月21日
     */
    void deleteAll();
    
    /**
     * 根据id集合批量删除索引
     * @param ids
     * @author jiangxinjun
     * @createDate 2018年1月23日
     * @updateDate 2018年1月23日
     */
    void delete(List<Long> ids);
    
    /**
     * 根据id删除索引
     * @param ids
     * @author jiangxinjun
     * @createDate 2018年1月23日
     * @updateDate 2018年1月23日
     */
    void delete(Long id);
    
    /**
     * 批量全面更新以及新增索引
     * 
     * @author jiangxinjun
     * @createDate 2018年1月20日
     * @updateDate 2018年1月20日
     */
    void save(List<MerchantStoreSolr> list);
    
    /**
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月24日
     * @updateDate 2018年1月24日
     */
    Page<MerchantStoreSolr> listStore(StoreSolrParam storeSolrParam);
    
    /**
     * 根据搜索词查询相关的关键词
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月22日
     * @updateDate 2018年1月22日
     */
    Page<MerchantStoreSolr> findKeywordssByKeywordssStartingWith(String keyword, String regionPath);
    
    /**
     * 根据搜索词统计相关数据的总数
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月22日
     * @updateDate 2018年1月22日
     */
    Page<MerchantStoreSolr> findCountBykeyword(String keyword, String regionPath);
    
    /**
     * 专属特惠(按折扣系数升序)
     * @param discountStoreParam
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月26日
     * @updateDate 2018年1月26日
     */
    Page<MerchantStoreSolr> discountStore(DiscountStoreParam discountStoreParam);
}
