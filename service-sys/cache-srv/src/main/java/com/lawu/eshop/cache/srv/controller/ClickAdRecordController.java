package com.lawu.eshop.cache.srv.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.srv.service.ClickAdRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "clickAdRecord/")
public class ClickAdRecordController extends BaseController{
	
	@Autowired
	private ClickAdRecordService clickAdRecordService;
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "setClickAdRecord", method = RequestMethod.POST)
    public Result setClickAdRecord(@RequestParam String key) {
		clickAdRecordService.setClickAdRecord(key);
		return successCreated();
    }

    @RequestMapping(value = "getClickAdRecord", method = RequestMethod.GET)
    public Result<Boolean> getClickAdRecord(@RequestParam String key) {
        return successAccepted(clickAdRecordService.getClickAdRecord(key));
    }

}
