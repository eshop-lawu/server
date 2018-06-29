package com.lawu.eshop.merchant.api.mock.service;

import org.springframework.stereotype.Service;

import com.lawu.eshop.merchant.api.service.FansInviteContentService;
import com.lawu.eshop.user.param.FansInviteContentExtendParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
@Service
public class MockFansInviteContentService extends BaseController implements FansInviteContentService {

	@Override
	public Result saveFansInviteContent(FansInviteContentExtendParam fansInviteContentParam) {
		return successCreated((Object)10);
	}

	@Override
	public Result saveFansInviteExtendContent(FansInviteContentExtendParam fansInviteContentExtendParam) {
		return successCreated((Object)10);
	}

}
