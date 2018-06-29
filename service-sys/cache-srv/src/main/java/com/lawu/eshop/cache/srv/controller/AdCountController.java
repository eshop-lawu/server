package com.lawu.eshop.cache.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.srv.service.AdCountService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "adCount/")
public class AdCountController extends BaseController{
	
	@Autowired
	private AdCountService adCountService;
	
	@RequestMapping(value = "setAdCountRecord", method = RequestMethod.GET)
    public Result setAdCountRecord(@RequestParam Long id, @RequestParam Integer count) {
		adCountService.setAdCountRecord(id, count);
		return successCreated();
    }

    @RequestMapping(value = "getAdCountRecord", method = RequestMethod.GET)
    public Result<Object> getAdCountRecord(@RequestParam Long id) {
        return successAccepted(adCountService.getAdCountRecord(id));
    }
    
    
    @RequestMapping(value = "getSurplusCount", method = RequestMethod.GET)
    public Result<Integer> getSurplusCount(@RequestParam Long id) {
        return successGet(adCountService.getSurplusCount(id));
    }
    
   

}
