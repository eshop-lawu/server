package com.lawu.eshop.merchant.api.mock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lawu.eshop.mall.dto.DiscountPackagePurchaseNotesDTO;
import com.lawu.eshop.merchant.api.service.DiscountPackagePurchaseNotesService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/8/3.
 */
@Service
public class MockDiscountPackagePurchaseNotesService extends BaseController implements DiscountPackagePurchaseNotesService {
    @Override
    public Result<List<DiscountPackagePurchaseNotesDTO>> list() {
        return successGet();
    }
}
