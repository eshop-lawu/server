package com.lawu.eshop.ad.srv.service;

import java.util.List;

import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.param.AdPlatformFindParam;
import com.lawu.eshop.ad.param.AdPlatformInternalParam;
import com.lawu.eshop.ad.param.AdPlatformParam;
import com.lawu.eshop.ad.param.AdPlatformUpdateParam;
import com.lawu.eshop.ad.srv.bo.AdPlatformBO;
import com.lawu.eshop.ad.srv.bo.AdPlatformVideoFlatBO;
import com.lawu.framework.core.page.Page;

/**
 * 平台广告管理
 *
 * @author zhangrc
 * @date 2017/4/5
 */
public interface AdPlatformService {

    /**
     * 添加广告
     *
     * @param adPlatformParam
     * @param url
     * @return
     */
    Integer saveAdPlatform(AdPlatformParam adPlatformParam);

    /**
     * 删除广告
     *
     * @param id
     * @return
     */
    Integer removeAdPlatform(Long id);

    /**
     * 根据不同的位置查询不同的广告
     *
     * @param positionEnum
     * @return
     */
    List<AdPlatformBO> selectByPosition(PositionEnum positionEnum);


    /**
     * 运营平台查询广告
     *
     * @param param
     * @return
     */
    Page<AdPlatformBO> selectList(AdPlatformFindParam param);


    /**
     * @param id
     * @param adPlatformParam
     * @param url
     * @return
     */
    Integer update(Long id, AdPlatformParam adPlatformParam);

    /**
     * 单个查询
     *
     * @param id
     * @return
     */
    AdPlatformBO select(Long id);

    /**
     * 广告下架
     *
     * @param id
     */
    void unShelve(Long id);

    /**
     * 广告上架
     *
     * @param id
     */
    void onShelve(Long id);


    /**
     * 根据类型位置查询广告
     *
     * @param typeEnum
     * @param positionEnum
     * @param regionPath
     * @return
     */
    List<AdPlatformBO> getAdPlatformByTypePositionRegionPath(TypeEnum typeEnum, PositionEnum positionEnum, String regionPath);

    /**
     * 根据类型位置查询广告
     *
     * @param typeEnum
     * @param positionEnum
     * @return
     */
    List<AdPlatformBO> getAdPlatformByTypePosition(TypeEnum typeEnum, PositionEnum positionEnum);

    /**
     * 根据商品ID查询该商品是否已上架广告位
     *
     * @param productId
     * @return
     */
    boolean selectByProductIdAndStatus(Long productId);
    
    /**
     * 广告首页广告位
     * @return
     */
    AdPlatformVideoFlatBO selAdPlatformPositionAd(AdPlatformInternalParam param);

    /**
     * 查询积分夺宝和抢购广告位
     * @return
     */
	List<AdPlatformBO> getAdPlatformByTypePosition();
    
    
	/**
	 * 修改广告位排序优先级
	 * 
	 * @param id
	 * @param param
	 */
    void updatePriority(Long id, AdPlatformUpdateParam param);
    
    
    /**
     * 查询广告位下关联总数
     * 
     * @param positionEnum
     * @return
     */
    int count(PositionEnum positionEnum);
    
    
}
