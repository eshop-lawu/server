package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.AlipayService;
import com.lawu.eshop.property.param.AlipayOauthDataParam;
import com.lawu.eshop.property.param.AlipayOauthParam;
import com.lawu.eshop.property.param.PcAlipayParam;
import com.lawu.eshop.property.param.ThirdPayDataParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockAlipayService extends BaseController implements AlipayService {


	@Override
	public Result getAppAlipayReqParams(@RequestBody ThirdPayDataParam param) {
		return successCreated();
	}

	@Override
	public Result initPcPay(PcAlipayParam param) {
		return null;
	}

	@Override
	public Result alipayUserInfoAuth(AlipayOauthDataParam alipayOauthDataParam) {
		return null;
	}

	@Override
	public Result getOauthToken(AlipayOauthParam alipayOauthParam) {
		return null;
	}
}
