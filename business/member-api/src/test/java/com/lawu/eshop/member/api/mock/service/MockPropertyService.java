package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.PropertyService;
import org.springframework.stereotype.Service;


@Service
public class MockPropertyService implements PropertyService {

	@Override
	public String getValue(String name) {
		return null;
	}
}
