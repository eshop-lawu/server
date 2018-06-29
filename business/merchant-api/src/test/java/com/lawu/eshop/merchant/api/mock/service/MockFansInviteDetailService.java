package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.FansInviteDetailService;
import com.lawu.eshop.property.dto.FansInviteDetailDTO;
import com.lawu.eshop.property.param.ListFansInviteDetailParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockFansInviteDetailService extends BaseController implements FansInviteDetailService {
    @Override
    public Result<Page<FansInviteDetailDTO>> listFansInviteDetail(@PathVariable("merchantId") Long merchantId, @ModelAttribute ListFansInviteDetailParam listFansInviteDetailParam) {
        return successCreated();
    }

    @Override
    public Result<FansInviteDetailDTO> getFansInviteDetailByPointNum(@PathVariable("pointNum") String pointNum) {
        return successGet();
    }
}
