package com.lawu.eshop.jobs.mock.service;

import java.util.Map;

import com.lawu.eshop.jobs.service.UserVisitCacheService;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockUserVisitCacheServiceImpl implements UserVisitCacheService {


	@Override
	public Map<String, String> getVisitRecords(@RequestParam("currentPage") Integer currentPage, @RequestParam("time") String time, @RequestParam("type") Byte type) {
		return null;
	}

	@Override
	public Result delVisitRecords(@RequestParam("time") String time) {
		return null;
	}
}
