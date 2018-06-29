package com.lawu.eshop.property.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.domain.PropertyDO;
import com.lawu.eshop.property.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.property.srv.service.PropertyService;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class PropertyServiceImplTest {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyDOMapper propertyDOMapper;


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getValue(){
        String value = propertyService.getValue(PropertyType.CASH_SCALE);
        Assert.assertNotEquals("",value);
        String value1 = propertyService.getValue(PropertyType.CASH_GREATER_ONE_MINUS_MONEY);
        Assert.assertNotEquals("",value1);
        String value2 = propertyService.getValue(PropertyType.CASH_MIN_MONEY);
        Assert.assertNotEquals("",value2);
        String value3 = propertyService.getValue(PropertyType.MEMBER_THIRD_PAY_POINT);
        Assert.assertNotEquals("",value3);
        String value4 = propertyService.getValue(PropertyType.MERCHANT_BONT);
        Assert.assertNotEquals("",value4);
        String value5 = propertyService.getValue(PropertyType.PRODUCT_ORDER_MONEY_FREEZE_DAYS);
        Assert.assertNotEquals("",value5);
        String value6 = propertyService.getValue(PropertyType.DEPOSIT_REFUND_DIFF_DAYS);
        Assert.assertNotEquals("",value6);
        String value7 = propertyService.getValue(PropertyType.ad_commission_0);
        Assert.assertNotEquals("",value7);
        value = propertyService.getValue(PropertyType.ad_commission_1);
        Assert.assertNotEquals("",value);
        value = propertyService.getValue(PropertyType.ad_commission_2);
        Assert.assertNotEquals("",value);
        value = propertyService.getValue(PropertyType.ad_commission_3);
        Assert.assertNotEquals("",value);
        value = propertyService.getValue(PropertyType.love_account_scale);
        Assert.assertNotEquals("",value);
        value = propertyService.getValue(PropertyType.sale_commission_1);
        Assert.assertNotEquals("",value);
        value = propertyService.getValue(PropertyType.sale_commission_2);
        Assert.assertNotEquals("",value);
        value = propertyService.getValue(PropertyType.sale_commission_3);
        Assert.assertNotEquals("",value);
        value = propertyService.getValue(PropertyType.sale_commission_add_scope);
        Assert.assertNotEquals("",value);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getValues(){
        PropertyDO record = new PropertyDO();
        record.setName(PropertyType.CASH_SCALE);
        record.setValue(PropertyType.CASH_SCALE);
        record.setGmtCreate(new Date());
        propertyDOMapper.insert(record);
        List<String> values = propertyService.getValues(PropertyType.CASH_SCALE);
        Assert.assertNotNull(values);
    }
}
