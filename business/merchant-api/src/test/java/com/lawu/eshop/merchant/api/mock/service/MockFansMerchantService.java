package com.lawu.eshop.merchant.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.lawu.eshop.merchant.api.service.FansMerchantService;
import com.lawu.eshop.user.dto.FansMerchantDTO;
import com.lawu.eshop.user.param.ListFansParam;
import com.lawu.eshop.user.param.ListInviteFansParam;
import com.lawu.eshop.user.param.ListInviteFansWithContentParam;
import com.lawu.eshop.user.param.PageListInviteFansParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockFansMerchantService extends BaseController implements FansMerchantService {
    @Override
    public Result<List<FansMerchantDTO>> listInviteFans(@PathVariable("merchantId") Long merchantId, @ModelAttribute ListInviteFansParam param) {
        return successCreated();
    }

    @Override
    public Result<Page<FansMerchantDTO>> pageListInviteFans(@PathVariable("merchantId") Long merchantId, @ModelAttribute PageListInviteFansParam param) {
        return successCreated();
    }

    @Override
    public Result<Page<FansMerchantDTO>> listFans(@PathVariable("merchantId") Long merchantId, @ModelAttribute ListFansParam listFansParam) {
        return successCreated();
    }

    @Override
    public Result<Integer> countFans(@PathVariable("merchantId") Long merchantId) {
        return successGet();
    }

	@Override
	public Result<List<FansMerchantDTO>> listInviteFansWithContent(Long merchantId,
			ListInviteFansWithContentParam param) {
		 return successGet(new ArrayList<FansMerchantDTO>());
	}
}
