package com.lawu.eshop.merchant.api.service.hystrix;

import com.lawu.eshop.user.param.RegisterParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author meishuquan
 * @date 2017/3/22
 */
@Component
public class MerchantServiceHystrix  {

    public Result updateLoginPwd(@RequestParam("id") Long id, @RequestParam("originalPwd") String originalPwd, @RequestParam("newPwd") String newPwd) {
        return null;
    }

    public Result getInviterByAccount(@RequestParam("account") String account) {
        return null;
    }

    public Result register(@RequestBody RegisterParam registerParam) {
        return null;
    }
}
