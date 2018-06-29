package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.PropertyDTO;
import com.lawu.eshop.user.srv.bo.PropertyBO;
import com.lawu.eshop.user.srv.domain.PropertyDO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class PropertyConverterTest {

    @Test
    public void convertBO() {
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setId(3000L);
        propertyDO.setName("test");
        propertyDO.setValue("test");
        propertyDO.setRemark("test");
        propertyDO.setGmtModified(new Date());
        propertyDO.setGmtCreate(new Date());
        PropertyBO propertyBO = PropertyConverter.convertBO(propertyDO);
        Assert.assertNotNull(propertyBO);
        Assert.assertEquals(propertyDO.getId(), propertyBO.getId());
    }

    @Test
    public void convertBOS() {
        List<PropertyDO> propertyDOS = new ArrayList<>();
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setId(3000L);
        propertyDO.setName("test");
        propertyDO.setValue("test");
        propertyDO.setRemark("test");
        propertyDO.setGmtModified(new Date());
        propertyDO.setGmtCreate(new Date());
        propertyDOS.add(propertyDO);
        List<PropertyBO> propertyBOS = PropertyConverter.convertBO(propertyDOS);
        Assert.assertNotNull(propertyBOS);
        Assert.assertEquals(propertyDO.getId(), propertyBOS.get(0).getId());
    }

    @Test
    public void convertDTO() {
        PropertyBO propertyBO = new PropertyBO();
        propertyBO.setId(300L);
        propertyBO.setName("test");
        propertyBO.setValue("test");
        propertyBO.setRemark("test");
        propertyBO.setGmtModified(new Date());
        propertyBO.setGmtCreate(new Date());
        PropertyDTO propertyDTO = PropertyConverter.convertDTO(propertyBO);
        Assert.assertNotNull(propertyDTO);
        Assert.assertEquals(propertyBO.getId(), propertyDTO.getId());
    }

    @Test
    public void convertDTOS() {
        List<PropertyBO> propertyBOS = new ArrayList<>();
        PropertyBO propertyBO = new PropertyBO();
        propertyBO.setId(300L);
        propertyBO.setName("test");
        propertyBO.setValue("test");
        propertyBO.setRemark("test");
        propertyBO.setGmtModified(new Date());
        propertyBO.setGmtCreate(new Date());
        propertyBOS.add(propertyBO);
        List<PropertyDTO> propertyDTOS = PropertyConverter.convertDTO(propertyBOS);
        Assert.assertNotNull(propertyDTOS);
        Assert.assertEquals(propertyBO.getId(), propertyDTOS.get(0).getId());
    }

}
