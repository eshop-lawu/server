package com.lawu.eshop.cache.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.srv.service.PraiseDoHanlderMinusPointService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "praiseDoHanlderMinusPoint/")
public class PraiseDoHanlderMinusPointController extends BaseController{
	
	@Autowired
	private PraiseDoHanlderMinusPointService praiseDoHanlderMinusPointService;
	
	@RequestMapping(value = "setAdPraiseIsDoPointRecord", method = RequestMethod.GET)
    public Result setAdPraiseIsDoPointRecord(@RequestParam String adIdAndMemberId) {
		praiseDoHanlderMinusPointService.setAdPraiseIsDoPointRecord(adIdAndMemberId);
		return successCreated();
    }

    @RequestMapping(value = "getAdPraiseIsDoPointRecord", method = RequestMethod.GET)
    public Result<Boolean> getAdPraiseIsDoPointRecord(@RequestParam String adIdAndMemberId) {
        return successAccepted(praiseDoHanlderMinusPointService.getAdPraiseIsDoPointRecord(adIdAndMemberId));
    }

}
