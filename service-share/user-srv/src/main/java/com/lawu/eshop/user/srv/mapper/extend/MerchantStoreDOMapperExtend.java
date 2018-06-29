package com.lawu.eshop.user.srv.mapper.extend;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lawu.eshop.user.param.ListMerchantStoreParam;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.extend.MerchantAdInfoView;
import com.lawu.eshop.user.srv.domain.extend.MerchantPushView;
import com.lawu.eshop.user.srv.domain.extend.NewMerchantStoreDOView;
import com.lawu.eshop.user.srv.domain.extend.PayOrderStoreInfoView;
import com.lawu.eshop.user.srv.domain.extend.RecommendFoodDOview;
import com.lawu.eshop.user.srv.domain.extend.ShoppingStoreInfoDOView;
import com.lawu.eshop.user.srv.domain.extend.StoreDetailDOView;
import com.lawu.eshop.user.srv.domain.extend.StoreSolrInfoDOView;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
public interface MerchantStoreDOMapperExtend {
    Integer  addMerchantStoreBuyNums(Long merchantId);

    Integer addMerchantStoreCommentsCount(@Param("merchantId") Long merchantId, @Param("commentsCount") Integer commentsCount);

    List<MerchantPushView> selectPushInfo(@Param("area") String area);

    List<MerchantStoreDO> listMerchantStore(ListMerchantStoreParam listMerchantStoreParam);

    List<MerchantStoreDO> listInvalidMerchantStore(ListMerchantStoreParam listMerchantStoreParam);

    List<PayOrderStoreInfoView> getPayOrderStoreInfo(List<Long> merchantIds);

    List<StoreSolrInfoDOView> getMerchantStoreByIds(List<Long> merchantStoreIds);

    List<StoreDetailDOView> getStoreDetailById(Long id);

    ShoppingStoreInfoDOView getShoppingStoreInfo(Long id);
    
    List<MerchantAdInfoView> getAdMerchantStoreByIds(List<Long> merchantIds);

    PayOrderStoreInfoView getPayOrderDetailStoreInfo(Long merchantId);

    PayOrderStoreInfoView getPayOrderMerchantInfo(Long merchantId);

    List<NewMerchantStoreDOView> listNewMerchant(String regionPath);

    List<RecommendFoodDOview> listRecommendFoodConsume(Map<String, Object> map);

    List<RecommendFoodDOview> listRecommendFoodComment(Map<String, Object> map);

    Integer updateMerchantStoreBuyNums(@Param("merchantId") Long merchantId, @Param("buyNums") Integer buyNums);
}
