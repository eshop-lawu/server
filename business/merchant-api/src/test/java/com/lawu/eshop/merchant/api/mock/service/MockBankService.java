package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.BankService;
import com.lawu.eshop.property.dto.BankDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockBankService extends BaseController implements BankService {
    @Override
    public Result<List<BankDTO>> findBank() {
        return successGet();
    }
}
