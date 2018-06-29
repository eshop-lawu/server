package com.lawu.eshop.order.srv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.order.srv.domain.PropertyDO;
import com.lawu.eshop.order.srv.domain.PropertyDOExample;
import com.lawu.eshop.order.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.order.srv.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {
	
	@Autowired
	private PropertyDOMapper propertyDOMapper;
	
	@Override
	public String getByName(String name) {
		if (name == null) {
			return null;
		}
		
		PropertyDOExample propertyDOExample = new PropertyDOExample();
		PropertyDOExample.Criteria propertyDOExampleCriteria = propertyDOExample.createCriteria();
		propertyDOExampleCriteria.andNameEqualTo(name);
		List<PropertyDO> propertyDOList = propertyDOMapper.selectByExample(propertyDOExample);
		
		if (propertyDOList != null && !propertyDOList.isEmpty()) {
			return propertyDOList.get(0) == null ? null : propertyDOList.get(0).getValue();
		}
		
		return null;
	}

}
