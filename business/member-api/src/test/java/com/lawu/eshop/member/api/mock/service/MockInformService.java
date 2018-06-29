/**
 * 
 */
package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.mall.param.InformSaveParam;
import com.lawu.eshop.member.api.service.InformService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class MockInformService extends BaseController implements InformService {


	@Override
	public Result addInform(@ModelAttribute InformSaveParam param) {
		return null;
	}
}
