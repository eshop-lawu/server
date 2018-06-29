package com.lawu.eshop.order.srv.service.impl;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.domain.PropertyDO;
import com.lawu.eshop.order.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.order.srv.service.PropertyService;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月12日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
public class PropertyServiceImplTest {

    @Autowired
    private PropertyService propertyService;

	@Autowired
	private PropertyDOMapper propertyDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getByName() {
    	String name = "name";
    	String value = "value";
    	PropertyDO record = new PropertyDO();
    	record.setGmtCreate(new Date());
    	record.setGmtModified(new Date());
    	record.setName(name);
    	record.setRemark("remark");
    	record.setValue(value);
    	propertyDOMapper.insert(record);
    	
    	String result = propertyService.getByName(name);
    	Assert.assertNotNull(result);
    	Assert.assertEquals(value, result);
    }
    
}
