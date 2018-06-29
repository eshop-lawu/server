package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.MerchantInviterService;
import com.lawu.eshop.user.dto.MerchantInviterDTO;
import com.lawu.eshop.user.param.MerchantInviterParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockMerchantInviterService extends BaseController implements MerchantInviterService {
    @Override
    public Result<Page<MerchantInviterDTO>> getMerchantByInviter(@RequestParam("userId") Long id, @RequestBody MerchantInviterParam query, @RequestParam("inviterType") byte inviterType) {
        return successCreated();
    }
}
