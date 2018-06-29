package com.lawu.eshop.cache.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.srv.service.UserRedPacketCountService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "userRedPacketCount/")
public class UserRedPacketCountController extends BaseController{
	
	@Autowired
	private UserRedPacketCountService userRedPacketCountService;
	
	@RequestMapping(value = "setUserRedPacketCountRecord", method = RequestMethod.GET)
    public Result setUserRedPacketCountRecord(@RequestParam Long id, @RequestParam Integer count) {
		userRedPacketCountService.setUserRedPacketCountRecord(id, count);
		return successCreated();
    }

    @RequestMapping(value = "getUserRedPacketCountRecord", method = RequestMethod.GET)
    public Result<Object> getAdCountRecord(@RequestParam Long id) {
        return successAccepted(userRedPacketCountService.getUserRedPacketCountRecord(id));
    }
    
   

}
