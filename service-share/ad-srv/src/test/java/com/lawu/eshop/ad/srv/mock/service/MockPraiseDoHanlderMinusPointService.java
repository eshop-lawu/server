package com.lawu.eshop.ad.srv.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.srv.service.PraiseDoHanlderMinusPointService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockPraiseDoHanlderMinusPointService extends BaseController implements PraiseDoHanlderMinusPointService {
	
	@Override
	@RequestMapping(value = "praiseDoHanlderMinusPoint/getAdPraiseIsDoPointRecord", method = RequestMethod.GET)
    public Result getAdPraiseIsDoPointRecord(@RequestParam("adIdAndMemberId") String adIdAndMemberId) {
		return successGet(true);
	}

}
