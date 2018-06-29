package com.lawu.eshop.external.api.service;

import com.lawu.eshop.user.dto.PayOrderMerchantStoreInfoDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@FeignClient(value = "user-srv")
public interface MerchantStoreService {

    /**
     * 买单详情门店信息
     * @param merchantId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/getPayOrderDetailStoreInfo")
    PayOrderMerchantStoreInfoDTO getPayOrderDetailStoreInfo(@RequestParam("merchantId") Long merchantId);
}
