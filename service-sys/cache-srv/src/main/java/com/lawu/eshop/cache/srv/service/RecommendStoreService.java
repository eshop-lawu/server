package com.lawu.eshop.cache.srv.service;

/**
 * @author meishuquan
 * @date 2017/7/28.
 */
public interface RecommendStoreService {

    /**
     * 新店推荐
     *
     * @param regionPath
     * @param storeInfo
     */
    void setNewMerchant(String regionPath, String storeInfo);

    /**
     * 优选美食-人气最高
     *
     * @param regionPath
     * @param storeInfo
     */
    void setRecommendFoodConsume(String regionPath, String storeInfo);

    /**
     * 优选美食-评价最高
     *
     * @param regionPath
     * @param storeInfo
     */
    void setRecommendFoodComment(String regionPath, String storeInfo);

    /**
     * 新店推荐
     *
     * @param regionPath
     * @return
     */
    String getNewMerchant(String regionPath);

    /**
     * 优选美食-人气最高
     *
     * @param regionPath
     * @return
     */
    String getRecommendFoodConsume(String regionPath);

    /**
     * 优选美食-评价最高
     *
     * @param regionPath
     * @return
     */
    String getRecommendFoodComment(String regionPath);

    /**
     * 新店推荐
     *
     * @param regionPath
     */
    void delNewMerchant(String regionPath);

    /**
     * 优选美食-人气最高
     *
     * @param regionPath
     */
    void delRecommendFoodConsume(String regionPath);

    /**
     * 优选美食-评价最高
     *
     * @param regionPath
     */
    void delRecommendFoodComment(String regionPath);
}
