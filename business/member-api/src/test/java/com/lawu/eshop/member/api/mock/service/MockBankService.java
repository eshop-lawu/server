package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.BankService;
import com.lawu.eshop.property.dto.BankDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockBankService extends BaseController implements BankService {


    @Override
    public Result<List<BankDTO>> findBank() {
        BankDTO dto = new BankDTO();
        List<BankDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }
}
