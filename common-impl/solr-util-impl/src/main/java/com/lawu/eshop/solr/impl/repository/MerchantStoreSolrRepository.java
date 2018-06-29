package com.lawu.eshop.solr.impl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;

public interface MerchantStoreSolrRepository extends SolrCrudRepository<MerchantStoreSolr, Long> {
    /**
     * 根据搜索词查询相关的关键词
     * @param name 搜索词
     * @param regionPath 地区
     * @param page 分页排序参数
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月26日
     * @updateDate 2018年1月26日
     */
    @Query(value= "keyword_ss:?0* AND regionPath_s:?1*", fields = "keyword_ss")
    Page<MerchantStoreSolr> findKeywordssByKeywordssStartingWith(String keywordss, String regionPath, Pageable page);
    
    /**
     * 根据搜索词统计相关数据的总数
     * @param keyword 关键词
     * @param regionPath 地区
     * @param page
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月26日
     * @updateDate 2018年1月26日
     */
    @Query(value = "keyword_ss:?0 AND regionPath_s:?1*", fields = {"id"})
    Page<MerchantStoreSolr> findCountBykeyword(String keyword, String regionPath, Pageable page);
}