package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.user.param.CollectionUserRegParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
@FeignClient(value = "user-srv")
public interface CollectionUserRegService {

    /**
     * 按日统计会员注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMemberRegDaily", method = RequestMethod.POST)
    Result<Integer> collectionMemberRegDaily(@RequestBody CollectionUserRegParam param);

    /**
     * 按日统计商家注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMerchantRegDaily", method = RequestMethod.POST)
    Result<Integer> collectionMerchantRegDaily(@RequestBody CollectionUserRegParam param);

    /**
     * 按月统计会员注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMemberRegMonth", method = RequestMethod.POST)
    Result<Integer> collectionMemberRegMonth(@RequestBody CollectionUserRegParam param);

    /**
     * 按月统计商家注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMerchantRegMonth", method = RequestMethod.POST)
    Result<Integer> collectionMerchantRegMonth(@RequestBody CollectionUserRegParam param);

    /**
     * 按区域统计会员注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMemberRegArea", method = RequestMethod.POST)
    Result<Integer> collectionMemberRegArea(@RequestBody CollectionUserRegParam param);

    /**
     * 按区域统计普通商家注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMerchantCommonRegArea", method = RequestMethod.POST)
    Result<Integer> collectionMerchantCommonRegArea(@RequestBody CollectionUserRegParam param);

    /**
     * 按区域统计实体商家注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMerchantEntityRegArea", method = RequestMethod.POST)
    Result<Integer> collectionMerchantEntityRegArea(@RequestBody CollectionUserRegParam param);

    /**
     *查询日注册用户总数
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMemberRegDailyArea", method = RequestMethod.POST)
    Result<Integer> collectionMemberRegDailyArea(@RequestBody CollectionUserRegParam param);

    /**
     * 查询日注册普通店铺总数
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMerchantNormalRegDailyArea", method = RequestMethod.POST)
    Result<Integer> collectionMerchantNormalRegDailyArea(@RequestBody CollectionUserRegParam param);

    /**
     * 查询日注册实体店铺总数
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMerchantEntityRegDailyArea", method = RequestMethod.POST)
    Result<Integer> collectionMerchantEntityRegDailyArea(@RequestBody CollectionUserRegParam param);

    /**
     *查询月注册用户总数
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMemberRegMonthArea", method = RequestMethod.POST)
    Result<Integer> collectionMemberRegMonthArea(@RequestBody CollectionUserRegParam param);

    /**
     * 查询月注册普通店铺总数
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMerchantNormalRegMonthArea", method = RequestMethod.POST)
    Result<Integer> collectionMerchantNormalRegMonthArea(@RequestBody CollectionUserRegParam param);

    /**
     * 查询月注册实体店铺总数
     * @param param
     * @return
     */
    @RequestMapping(value = "userReg/collectionMerchantEntityRegMonthArea", method = RequestMethod.POST)
    Result<Integer> collectionMerchantEntityRegMonthArea(@RequestBody CollectionUserRegParam param);
}
