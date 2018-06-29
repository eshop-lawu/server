package com.lawu.eshop.ad.srv.solr.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lawu.eshop.ad.param.AdSolrRealParam;
import com.lawu.eshop.ad.param.AdsolrFindParam;
import com.lawu.eshop.solr.impl.entity.AdSolr;

public interface AdSolrService {
    
    /**
     * 更新索引
     * 
     * @author jiangxinjun
     * @createDate 2018年1月20日
     * @updateDate 2018年1月20日
     */
    void update(AdSolr solrBean);
    
    /**
     * 全面更新以及新增索引
     * 
     * @author jiangxinjun
     * @createDate 2018年1月20日
     * @updateDate 2018年1月20日
     */
    void save(AdSolr solrBean);
    
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
    void save(List<AdSolr> list);
    
    /**
     * 会员查询广告
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月28日
     * @updateDate 2018年1月28日
     */
    Page<AdSolr> selectChoiceness(AdSolrRealParam param);
    
    /**
     * 根据广告名称查询广告
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月29日
     * @updateDate 2018年1月29日
     */
    Page<AdSolr> queryAdByTitle(AdsolrFindParam param);
    
    /**
     * 推荐广告
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月29日
     * @updateDate 2018年1月29日
     */
    Page<AdSolr> getRecommendAdByType(AdSolrRealParam param);
    
    /**
     * 推荐抢赞
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月29日
     * @updateDate 2018年1月29日
     */
    Page<AdSolr> getRecommendEgain(AdSolrRealParam param);
    
    /**
     * 平面广告排行榜
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月29日
     * @updateDate 2018年1月29日
     */
    List<AdSolr> listAdRank(AdSolrRealParam param);
}
