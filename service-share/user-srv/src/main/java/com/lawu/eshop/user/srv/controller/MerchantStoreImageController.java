package com.lawu.eshop.user.srv.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.dto.MerchantStoreImageDTO;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.srv.bo.MerchantStoreImageBO;
import com.lawu.eshop.user.srv.converter.MerchantStoreImageConverter;
import com.lawu.eshop.user.srv.service.MerchantStoreImageService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/11.
 */
@RestController
@RequestMapping(value = "merchantStoreImage/")
public class MerchantStoreImageController extends BaseController {

    @Autowired
    private MerchantStoreImageService merchantStoreImageService;

    /**
     * 根据图片类别查询门店图片
     *
     * @param merchantId
     * @param merchantStoreImageEnum
     * @return
     */
    @RequestMapping(value = "listMerchantStoreImage/{merchantId}", method = RequestMethod.GET)
    public Result<List<MerchantStoreImageDTO>> listMerchantStoreImage(@PathVariable Long merchantId, @RequestParam MerchantStoreImageEnum merchantStoreImageEnum) {
        List<MerchantStoreImageBO> merchantStoreImageBOS = merchantStoreImageService.listMerchantStoreImageByType(merchantId, merchantStoreImageEnum);
        if (merchantStoreImageBOS == null || merchantStoreImageBOS.isEmpty()) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(MerchantStoreImageConverter.convertDTO(merchantStoreImageBOS));
    }
}
