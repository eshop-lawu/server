package com.lawu.eshop.user.srv.service;

import com.lawu.eshop.user.param.CollectionUserRegParam;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
public interface UserRegService {

    /**
     * 按日统计会员注册量
     *
     * @param param
     * @return
     */
    Integer collectionMemberRegDaily(CollectionUserRegParam param);


    /**
     * 按日统计商家注册量
     *
     * @param param
     * @return
     */
    Integer collectionMerchantRegDaily(CollectionUserRegParam param);


    /**
     * 按月统计会员注册量
     *
     * @param param
     * @return
     */
    Integer collectionMemberRegMonth(CollectionUserRegParam param);


    /**
     * 按月统计商家注册量
     *
     * @param param
     * @return
     */
    Integer collectionMerchantRegMonth(CollectionUserRegParam param);


    /**
     * 按区域统计会员注册量
     *
     * @param param
     * @return
     */
    Integer collectionMemberRegArea(CollectionUserRegParam param);

    /**
     * 按区域统计普通商家注册量
     *
     * @param param
     * @return
     */
    Integer collectionMerchantCommonRegArea(CollectionUserRegParam param);


    /**
     * 按区域统计实体商家注册量
     *
     * @param param
     * @return
     */
    Integer collectionMerchantEntityRegArea(CollectionUserRegParam param);

    Integer collectionMemberRegDailyArea(CollectionUserRegParam param);

    Integer collectionMerchantEntityRegDailyArea(CollectionUserRegParam param);

    Integer collectionMerchantNormalRegDailyArea(CollectionUserRegParam param);

    Integer collectionMemberRegMonthArea(CollectionUserRegParam param);

    Integer collectionMerchantNormalRegMonthArea(CollectionUserRegParam param);

    Integer collectionMerchantEntityRegMonthArea(CollectionUserRegParam param);
}
