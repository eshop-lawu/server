package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.eshop.mall.param.MerchantFavoredParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
@FeignClient(value = "mall-srv")
public interface MerchantFavoredService {

    @RequestMapping(value = "merchantFavored/saveMerchantFavoredInfo/{merchantId}", method = RequestMethod.POST)
    public Result saveMerchantFavoredInfo(@PathVariable("merchantId") Long merchantId, @RequestParam("storeId") Long storeId, @ModelAttribute MerchantFavoredParam param);

    @RequestMapping(value = "merchantFavored/findFavoredByMerchantId/{merchantId}", method = RequestMethod.GET)
    public Result<MerchantFavoredDTO> findFavoredByMerchantId(@PathVariable("merchantId") Long merchantId);

    @RequestMapping(value = "merchantFavored/delMerchantFavoredInfo/{id}", method = RequestMethod.DELETE)
    public Result delMerchantFavoredInfo(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId, @RequestParam("storeId") Long storeId);

    @RequestMapping(value = "merchantFavored/updateMerchantFavoredInfo/{merchantId}", method = RequestMethod.PUT)
    public Result updateMerchantFavoredInfo(@PathVariable("merchantId") Long merchantId, @RequestParam("storeId") Long storeId, @ModelAttribute MerchantFavoredParam param);
}
