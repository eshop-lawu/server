package com.lawu.eshop.jobs.mock.service;

import com.lawu.eshop.jobs.service.MessageService;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class MockMessageServiceImpl implements MessageService {


	@Override
	public Result saveMessage(@PathVariable("userNum") String userNum, @ModelAttribute MessageInfoParam messageInfoParam) {
		return null;
	}
}
