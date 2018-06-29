package com.lawu.eshop.property.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.property.dto.PropertyInfoDTO;
import com.lawu.eshop.property.srv.bo.PropertyInfoBO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;

/**
 * @author yangqh
 * @date 2017/7/19
 */
public class PropertyInfoConverterTest {

    @Test
    public void convertBO(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setBalance(new BigDecimal("1"));
        propertyInfoDO.setPoint(new BigDecimal("1"));
        propertyInfoDO.setLoveAccount(new BigDecimal("1"));
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setFreeze(new Byte("1"));
        propertyInfoDO.setPayPassword("111111");
        PropertyInfoBO bo = PropertyInfoConverter.convertBO(propertyInfoDO);
        Assert.assertNotNull(bo);
    }

    @Test
    public void convertDTO(){
        PropertyInfoBO propertyInfoBO = new PropertyInfoBO();
        propertyInfoBO.setBalance(new BigDecimal(("1")));
        propertyInfoBO.setPoint(new BigDecimal("1"));
        propertyInfoBO.setLoveAccount(new BigDecimal("1"));
        propertyInfoBO.setUserNum("M10001");
        propertyInfoBO.setFreeze(new Byte("1"));
        propertyInfoBO.setPayPassword("111111");
        PropertyInfoDTO dto = PropertyInfoConverter.convertDTO(propertyInfoBO);
        Assert.assertNotNull(dto);
    }

    @Test
    public void convertBO1(){
        List<PropertyInfoDO> propertyInfoDOList = new ArrayList<>();
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setBalance(new BigDecimal("1"));
        propertyInfoDO.setPoint(new BigDecimal("1"));
        propertyInfoDO.setLoveAccount(new BigDecimal("1"));
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setFreeze(new Byte("1"));
        propertyInfoDO.setPayPassword("111111");
        propertyInfoDOList.add(propertyInfoDO);
        List<PropertyInfoBO> rtnList = PropertyInfoConverter.convertBO(propertyInfoDOList);
        Assert.assertNotNull(rtnList);
    }

    @Test
    public void convertDTO1(){
        List<PropertyInfoBO> propertyInfoBOList = new ArrayList<>();
        PropertyInfoBO propertyInfoBO = new PropertyInfoBO();
        propertyInfoBO.setBalance(new BigDecimal(("1")));
        propertyInfoBO.setPoint(new BigDecimal("1"));
        propertyInfoBO.setLoveAccount(new BigDecimal("1"));
        propertyInfoBO.setUserNum("M10001");
        propertyInfoBO.setFreeze(new Byte("1"));
        propertyInfoBO.setPayPassword("111111");
        propertyInfoBOList.add(propertyInfoBO);
        List<PropertyInfoDTO> rtnList = PropertyInfoConverter.convertDTO(propertyInfoBOList);
        Assert.assertNotNull(rtnList);
    }
}
