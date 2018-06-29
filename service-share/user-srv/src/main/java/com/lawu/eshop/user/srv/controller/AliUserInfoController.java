package com.lawu.eshop.user.srv.controller;

import com.lawu.eshop.user.param.AliUserInfoDataParam;
import com.lawu.eshop.user.srv.service.AliUserInfoService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "aliUserInfo/")
public class AliUserInfoController extends BaseController {

	@Autowired
	private AliUserInfoService aliUserInfoService;

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result save(@RequestBody AliUserInfoDataParam aliUserInfoDataParam) {
		aliUserInfoService.save(aliUserInfoDataParam);
		return successCreated();
	}

}
