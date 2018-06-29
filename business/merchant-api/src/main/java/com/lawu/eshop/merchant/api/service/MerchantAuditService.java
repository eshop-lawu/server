package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.user.dto.MerchantStoreAuditDTO;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangyong
 * @date 2017/7/17.
 */
@FeignClient(value = "user-srv")
public interface MerchantAuditService {

    @RequestMapping(value = "audit/getRecentMerchantAuditRecord", method = RequestMethod.GET)
    Result<MerchantStoreAuditDTO> getRecentMerchantAuditRecord(@RequestParam("merchantId") Long merchantId);
}
