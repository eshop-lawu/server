package com.lawu.eshop.mall.srv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.mall.srv.domain.PropertyDO;
import com.lawu.eshop.mall.srv.domain.PropertyDOExample;
import com.lawu.eshop.mall.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.mall.srv.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {
	
	@Autowired
	private PropertyDOMapper propertyDOMapper;

	@Override
	public String getValue(String key) {
		PropertyDOExample example = new PropertyDOExample();
		example.createCriteria().andNameEqualTo(key);
		List<PropertyDO> list = propertyDOMapper.selectByExample(example);
		if(list == null || list.isEmpty()){
			return "";
		}
		return list.get(0).getValue();
	}

	@Override
	public List<String> getValues(String key) {
		PropertyDOExample example = new PropertyDOExample();
		example.createCriteria().andNameEqualTo(key);
		List<PropertyDO> list = propertyDOMapper.selectByExample(example);
		if(list == null || list.isEmpty()){
			return null;
		}
		List<String> values = new ArrayList<String>();
		for(PropertyDO pdo : list){
			values.add(pdo.getValue());
		}
		return values;
	}

}
