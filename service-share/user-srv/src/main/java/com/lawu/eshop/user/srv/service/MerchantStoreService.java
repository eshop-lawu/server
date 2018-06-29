package com.lawu.eshop.user.srv.service;

import java.util.List;

import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.user.param.ListMerchantStoreParam;
import com.lawu.eshop.user.param.MerchantStorePlatParam;
import com.lawu.eshop.user.param.StoreIndexParam;
import com.lawu.eshop.user.param.StoreStatisticsParam;
import com.lawu.eshop.user.srv.bo.MerchantAdInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreAdInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreFavorInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreStatusBO;
import com.lawu.eshop.user.srv.bo.NewMerchantStoreBO;
import com.lawu.eshop.user.srv.bo.RecommendFoodBO;
import com.lawu.framework.core.page.Page;

/**
 * 店面信息获取
 *
 * @author zhangrc
 * @date 2017/4/10
 */
public interface MerchantStoreService {
    /**
     * 根据商家id查询门店信息
     *
     * @param merchantId
     * @return
     */
    MerchantStoreBO selectMerchantStore(Long merchantId);

    /**
     * 加入7天退货保障
     *
     * @param id
     */
    void updateNoReasonReturn(Long id);

    /**
     * 根据门店ID查询门店信息
     *
     * @param id
     * @return
     */
    MerchantStoreBO getMerchantStoreById(Long id);

    /**
     * 查询所有的门店
     * @param param
     * @return
     */
    Page<MerchantStoreBO> selectAllMerchantStore(MerchantStorePlatParam param);


    /**
     * 查询所有审核通过的实体店铺
     *
     * @return
     */
    List<MerchantStoreBO> listMerchantStore(ListMerchantStoreParam listMerchantStoreParam);

    /**
     * 更新门店统计数据
     *
     * @param id
     * @param param
     */
    void updateStoreStatisticsById(Long id, StoreStatisticsParam param);

    /**
     * 重建门店索引
     */
    void rebuildStoreIndex(List<StoreIndexParam> indexParamList);

    /**
     * 删除无效的门店索引
     *
     * @param typeEnum
     */
    void delInvalidStoreIndex(DelIndexTypeEnum typeEnum);
    
    /**
     * 查询商家相关信息
     * @param merchantIds
     * @return
     */
    List<MerchantAdInfoBO> getAdMerchantStoreByIds(List<Long> merchantIds);

    MerchantStoreStatusBO merchantStoreIsExist(Long id);

    MerchantInfoBO findAccountAndRegionPathByNum(String merchantNum);

    /**
     * 新店推荐
     *
     * @param regionPath
     * @return
     * @author meishuquan
     */
    List<NewMerchantStoreBO> listNewMerchant(String regionPath);

    /**
     * 优选美食-人气最高
     *
     * @param industryId
     * @param regionPath
     * @return
     * @author meishuquan
     */
    List<RecommendFoodBO> listRecommendFoodConsume(Integer industryId, String regionPath);

    /**
     * 优选美食-评价最高
     *
     * @param industryId
     * @param regionPath
     * @return
     * @author meishuquan
     */
    List<RecommendFoodBO> listRecommendFoodComment(Integer industryId, String regionPath);
    
    /**
     * 
     * @param merchantId
     * @return
     */
    MerchantStoreAdInfoBO selectMerchantStoreAdInfo(Long merchantId);

    /**
     * 获取负责人名称
     * @param merchantId
     * @return
     */
	String getPrincipalName(Long merchantId);
	
	/**
	 * 获取门店信息
	 * @param merchantId
	 * @return
	 */
	MerchantStoreFavorInfoBO selectMerchantStoreFavor(Long merchantId);

}
