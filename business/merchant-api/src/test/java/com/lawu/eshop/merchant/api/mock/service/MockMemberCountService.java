package com.lawu.eshop.merchant.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lawu.eshop.merchant.api.service.MemberCountService;
import com.lawu.eshop.user.param.AreasCountQuery;
import com.lawu.eshop.user.param.FensCountQuery;
import com.lawu.framework.web.BaseController;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockMemberCountService extends BaseController implements MemberCountService {

	@Override
	public Integer findMemberCount(@RequestBody AreasCountQuery query) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer findFensCount(@RequestBody FensCountQuery query) {
		// TODO Auto-generated method stub
		return 0;
	}
    
}
