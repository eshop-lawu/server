package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.InviterService;
import com.lawu.eshop.user.dto.EFriendInviterDTO;
import com.lawu.eshop.user.dto.InviterDTO;
import com.lawu.eshop.user.param.EFriendQueryDataParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockInviterService extends BaseController implements InviterService {
    @Override
    public Result<InviterDTO> getInviterByAccount(@PathVariable("account") String account) {
        InviterDTO dto = new InviterDTO();
        dto.setInviterId(100L);
        dto.setUserNum("M0001");
        dto.setInviterName("test");
        return successGet(dto);
    }

    @Override
    public Result<Page<EFriendInviterDTO>> selectEFriend(@RequestBody EFriendQueryDataParam dataParam) {
        return null;
    }
}
