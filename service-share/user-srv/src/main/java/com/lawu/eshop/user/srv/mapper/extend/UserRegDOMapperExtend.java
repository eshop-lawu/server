package com.lawu.eshop.user.srv.mapper.extend;

import com.lawu.eshop.user.param.CollectionUserRegParam;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
public interface UserRegDOMapperExtend {

	Integer collectionMemberRegDaily(CollectionUserRegParam param);

	Integer collectionMerchantRegDaily(CollectionUserRegParam param);

	Integer collectionMemberRegMonth(CollectionUserRegParam param);

	Integer collectionMerchantRegMonth(CollectionUserRegParam param);

	Integer collectionMemberRegArea(CollectionUserRegParam param);

	Integer collectionMerchantCommonRegArea(CollectionUserRegParam param);

	Integer collectionMerchantEntityRegArea(CollectionUserRegParam param);

    Integer collectionMemberRegDailyArea(CollectionUserRegParam param);

	Integer collectionMerchantEntityRegDailyArea(CollectionUserRegParam param);

	Integer collectionMerchantNormalRegDailyArea(CollectionUserRegParam param);

    Integer collectionMemberRegMonthArea(CollectionUserRegParam param);

	Integer collectionMerchantNormalRegMonthArea(CollectionUserRegParam param);

	Integer collectionMerchantEntityRegMonthArea(CollectionUserRegParam param);
}
