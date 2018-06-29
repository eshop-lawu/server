package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.MerchantInviterService;
import com.lawu.eshop.product.dto.FavoriteProductDTO;
import com.lawu.eshop.user.dto.MerchantInviterDTO;
import com.lawu.eshop.user.param.MerchantInviterParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockMerchantInviterService extends BaseController implements MerchantInviterService {

    @Override
    public Result<Page<MerchantInviterDTO>> getMerchantByInviter(@RequestParam("userId") Long id, @RequestBody MerchantInviterParam query, @RequestParam("inviterType") Byte inviterType) {
        MerchantInviterDTO dto = new MerchantInviterDTO();
        List<MerchantInviterDTO> list = new ArrayList<>();
        list.add(dto);
        Page<MerchantInviterDTO> page = new Page();
        page.setCurrentPage(1);
        page.setTotalCount(100);
        page.setRecords(list);
        return successCreated(page);
    }
}
