package com.lawu.eshop.user.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.user.param.CollectionUserRegParam;
import com.lawu.eshop.user.srv.service.UserRegService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
@RestController
@RequestMapping(value = "userReg/")
public class UserRegController extends BaseController {

    @Autowired
    private UserRegService userRegService;

    /**
     * 按日统计会员注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMemberRegDaily", method = RequestMethod.POST)
    public Result<Integer> collectionMemberRegDaily(@RequestBody CollectionUserRegParam param) {
        Integer count = userRegService.collectionMemberRegDaily(param);
        return successGet(count);
    }

    /**
     * 按日统计商家注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMerchantRegDaily", method = RequestMethod.POST)
    public Result<Integer> collectionMerchantRegDaily(@RequestBody CollectionUserRegParam param) {
        Integer count = userRegService.collectionMerchantRegDaily(param);
        return successGet(count);
    }

    /**
     * 按月统计会员注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMemberRegMonth", method = RequestMethod.POST)
    public Result<Integer> collectionMemberRegMonth(@RequestBody CollectionUserRegParam param) {
        Integer count = userRegService.collectionMemberRegMonth(param);
        return successGet(count);
    }

    /**
     * 按月统计商家注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMerchantRegMonth", method = RequestMethod.POST)
    public Result<Integer> collectionMerchantRegMonth(@RequestBody CollectionUserRegParam param) {
        Integer count = userRegService.collectionMerchantRegMonth(param);
        return successGet(count);
    }

    /**
     * 按区域统计会员注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMemberRegArea", method = RequestMethod.POST)
    public Result<Integer> collectionMemberRegArea(@RequestBody CollectionUserRegParam param) {
        Integer count = userRegService.collectionMemberRegArea(param);
        return successGet(count);
    }

    /**
     * 按区域统计普通商家注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMerchantCommonRegArea", method = RequestMethod.POST)
    public Result<Integer> collectionMerchantCommonRegArea(@RequestBody CollectionUserRegParam param) {
        Integer count = userRegService.collectionMerchantCommonRegArea(param);
        return successGet(count);
    }

    /**
     * 按区域统计实体商家注册量
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMerchantEntityRegArea", method = RequestMethod.POST)
    public Result<Integer> collectionMerchantEntityRegArea(@RequestBody CollectionUserRegParam param) {
        Integer count = userRegService.collectionMerchantEntityRegArea(param);
        return successGet(count);
    }


    /**
     *查询日用户总数
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMemberRegDailyArea", method = RequestMethod.POST)
    public Result<Integer> collectionMemberRegDailyArea(@RequestBody CollectionUserRegParam param){
        return successCreated(userRegService.collectionMemberRegDailyArea(param));
    }

    /**
     * 查询日普通店铺总数
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMerchantNormalRegDailyArea", method = RequestMethod.POST)
    public Result<Integer> collectionMerchantNormalRegDailyArea(@RequestBody CollectionUserRegParam param){
        return successCreated(userRegService.collectionMerchantNormalRegDailyArea(param));
    }

    /**
     * 查询日实体店铺总数
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMerchantEntityRegDailyArea", method = RequestMethod.POST)
    public Result<Integer> collectionMerchantEntityRegDailyArea(@RequestBody CollectionUserRegParam param){
        return successCreated(userRegService.collectionMerchantEntityRegDailyArea(param));
    }


    /**
     *查询月注册用户总数
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMemberRegMonthArea", method = RequestMethod.POST)
    public Result<Integer> collectionMemberRegMonthArea(@RequestBody CollectionUserRegParam param){
        return successCreated(userRegService.collectionMemberRegMonthArea(param));
    }

    /**
     * 查询月注册普通店铺总数
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMerchantNormalRegMonthArea", method = RequestMethod.POST)
    public  Result<Integer> collectionMerchantNormalRegMonthArea(@RequestBody CollectionUserRegParam param){
        return successCreated(userRegService.collectionMerchantNormalRegMonthArea(param));
    }

    /**
     * 查询月注册实体店铺总数
     * @param param
     * @return
     */
    @RequestMapping(value = "collectionMerchantEntityRegMonthArea", method = RequestMethod.POST)
    public Result<Integer> collectionMerchantEntityRegMonthArea(@RequestBody CollectionUserRegParam param){
        return successCreated(userRegService.collectionMerchantEntityRegMonthArea(param));
    }

}
