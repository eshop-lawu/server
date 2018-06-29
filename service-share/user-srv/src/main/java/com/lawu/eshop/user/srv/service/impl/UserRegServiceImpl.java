package com.lawu.eshop.user.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.user.param.CollectionUserRegParam;
import com.lawu.eshop.user.srv.mapper.extend.UserRegDOMapperExtend;
import com.lawu.eshop.user.srv.service.UserRegService;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
@Service
public class UserRegServiceImpl implements UserRegService {

    @Autowired
    private UserRegDOMapperExtend userRegDOMapperExtend;

    @Override
    public Integer collectionMemberRegDaily(CollectionUserRegParam param) {
        return  userRegDOMapperExtend.collectionMemberRegDaily(param);
    }

    @Override
    public Integer collectionMerchantRegDaily(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMerchantRegDaily(param);
    }

    @Override
    public Integer collectionMemberRegMonth(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMemberRegMonth(param);
    }

    @Override
    public Integer collectionMerchantRegMonth(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMerchantRegMonth(param);
    }

    @Override
    public Integer collectionMemberRegArea(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMemberRegArea(param);
    }

    @Override
    public Integer collectionMerchantCommonRegArea(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMerchantCommonRegArea(param);
    }

    @Override
    public Integer collectionMerchantEntityRegArea(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMerchantEntityRegArea(param);
    }

    @Override
    public Integer collectionMemberRegDailyArea(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMemberRegDailyArea(param);
    }

    @Override
    public Integer collectionMerchantEntityRegDailyArea(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMerchantEntityRegDailyArea(param);
    }

    @Override
    public Integer collectionMerchantNormalRegDailyArea(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMerchantNormalRegDailyArea(param);
    }

    @Override
    public Integer collectionMemberRegMonthArea(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMemberRegMonthArea(param);
    }

    @Override
    public Integer collectionMerchantNormalRegMonthArea(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMerchantNormalRegMonthArea(param);
    }

    @Override
    public Integer collectionMerchantEntityRegMonthArea(CollectionUserRegParam param) {
        return userRegDOMapperExtend.collectionMerchantEntityRegMonthArea(param);
    }
}
