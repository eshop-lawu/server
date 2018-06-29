package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangyong
 * @date 2017/8/4.
 */
@FeignClient(value = "cache-srv")
public interface TokenService {

    @RequestMapping(value = "userToken/delMerchantRelationshipByAccount", method = RequestMethod.DELETE)
    void delMerchantRelationshipByAccount(@RequestParam("account") String account);

    @RequestMapping(value = "userToken/delMemberRelationshipByAccount", method = RequestMethod.DELETE)
    void delMemberRelationshipByAccount(@RequestParam("account") String account);
}
