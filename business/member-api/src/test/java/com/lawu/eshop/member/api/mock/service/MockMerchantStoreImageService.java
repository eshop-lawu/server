package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.MerchantStoreImageService;
import com.lawu.eshop.user.dto.MerchantStoreImageDTO;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
class MockMerchantStoreImageService extends BaseController implements MerchantStoreImageService {

    @Override
    public Result<List<MerchantStoreImageDTO>> listMerchantStoreImageByType(@PathVariable("merchantId") Long merchantId, @RequestParam("merchantStoreImageEnum") MerchantStoreImageEnum merchantStoreImageEnum) {
        return successCreated();
    }
}
