package com.lawu.eshop.solr.impl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.lawu.eshop.solr.impl.entity.ProductSolr;

public interface ProductSolrRepository extends SolrCrudRepository<ProductSolr, Long> {
    
    /**
     * 通过商品分类查询
     * @param categoryId 种类id
     * @param id 过滤id
     * @param page 分页和排序参数
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月22日
     * @updateDate 2018年1月22日
     */
    @Query(filters = {"-id:?1"})
    Page<ProductSolr> findByCategoryId(Integer categoryId, String id, Pageable page);
    
    /**
     * 商品搜索
     * @param name 商品名称
     * @param page 分页和排序参数
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月22日
     * @updateDate 2018年1月22日
     */
    @Query("text:?0")
    Page<ProductSolr> listProductByName(String name, Pageable page);
    
    /**
     * 根据搜索词查询相关的关键词
     * @param name 搜索词
     * @param page 分页排序参数
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月22日
     * @updateDate 2018年1月22日
     */
    @Query(fields = "keyword_ss")
    Page<ProductSolr> findKeywordssByKeywordssStartingWith(String keywordss, Pageable page);
    
    /**
     * 
     * @param keyword
     * @param page
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月23日
     * @updateDate 2018年1月23日
     */
    @Query(value = "text:?0", fields = {"id"})
    Page<ProductSolr> findCountBykeyword(String keyword, Pageable page);
    
}