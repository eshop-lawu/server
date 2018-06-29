package com.lawu.eshop.ad.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.extend.AdDOView;
import com.lawu.eshop.ad.srv.domain.extend.ReportAdView;
import com.lawu.eshop.ad.srv.domain.extend.SelectAdEgainIdExample;
import com.lawu.eshop.ad.srv.domain.extend.SelectAdPointIdExample;
import com.lawu.eshop.ad.srv.domain.extend.SelectChoicenessAdIdExample;

public interface AdDOMapperExtend {
	/**
	 * 浏览次数加一
	 * @param id
	 * @return
	 */
    int updateHitsByPrimaryKey(Long id);
    
    /**
     * 积分榜，人气榜
     * @param adDO
     * @return
     */
    List<AdDO> selectAdAll(AdDOView adDOView);
    
    /**
     * 精选广告
     * @return
     */
    List<AdDO> selectChoiceness();
    
    /**
     * 商家端批量删除
     * @param adIds
     */
    void batchDeleteAd(List<Long> adIds);

    /**
     * 统计广告
     * @return
     */
	List<ReportAdView> selectReportAdEarningsByRowbounds(RowBounds rowBounds);
	
	/**
	 * 收益总记录数
	 * @return
	 */
	Integer selectReportAdEarningscount();
	
	/**
	 * 分页查询E赚广告
	 * 
	 * @param example
	 * @param rowBounds
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月18日
	 */
	List<Long> selectPageAdEgainId(SelectAdEgainIdExample example, RowBounds rowBounds);
	
	/**
	 * 查询E赚广告总数
	 * 
	 * @param example
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月18日
	 */
	Long selectAdEgainCount(SelectAdEgainIdExample example);
	
	/**
	 * 查询广告排行版
	 * 
	 * @param example 查询参数
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月19日
	 */
	List<Long> selectAdPointIdExample(SelectAdPointIdExample example);
	
	/**
	 * 分页查询精选推荐广告
	 * 
	 * @param example
	 * @param rowBounds
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月19日
	 */
	List<Long> selectPageChoicenessAdId(SelectChoicenessAdIdExample example, RowBounds rowBounds);
	
	/**
	 * 查询精选推荐广告总数
	 * 
	 * @param example
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月19日
	 */
	Long selectChoicenessAdCount(SelectChoicenessAdIdExample example);
	
}