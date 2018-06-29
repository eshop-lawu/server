package com.lawu.eshop.jobs.mock.service;

import com.lawu.eshop.jobs.service.UserVisitRecordService;
import com.lawu.eshop.statistics.param.UserVisitRecordParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockUserVisitRecordServiceImpl implements UserVisitRecordService {


	@Override
	public Result addUserVisitRecord(@RequestBody UserVisitRecordParam userVisitRecordParam) {
		return null;
	}
}
