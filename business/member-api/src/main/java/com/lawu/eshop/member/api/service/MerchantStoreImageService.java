package com.lawu.eshop.member.api.service;

import com.lawu.eshop.user.dto.MerchantStoreImageDTO;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/11.
 */
@FeignClient(value = "user-srv")
public interface MerchantStoreImageService {

    /**
     * 根据图片类别查询门店图片
     *
     * @param merchantId
     * @param merchantStoreImageEnum
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStoreImage/listMerchantStoreImage/{merchantId}")
    Result<List<MerchantStoreImageDTO>> listMerchantStoreImageByType(@PathVariable("merchantId") Long merchantId, @RequestParam("merchantStoreImageEnum") MerchantStoreImageEnum merchantStoreImageEnum);
}
