package com.lawu.eshop.user.srv.service.impl;

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

import com.lawu.eshop.user.param.ListPropertyParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.PropertyBO;
import com.lawu.eshop.user.srv.domain.PropertyDO;
import com.lawu.eshop.user.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.user.srv.service.PropertyService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class PropertyServiceImplTest {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyDOMapper propertyDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveProperty() {
        propertyService.saveProperty("test", "测试", "备注");
        List<PropertyDO> propertyDOList = propertyDOMapper.selectByExample(null);
        Assert.assertNotNull(propertyDOList);
        Assert.assertEquals(1, propertyDOList.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void deletePropertyById() {
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setName("test");
        propertyDO.setValue("测试");
        propertyDO.setRemark("备注");
        propertyDO.setGmtCreate(new Date());
        propertyDOMapper.insertSelective(propertyDO);

        propertyService.deletePropertyById(propertyDO.getId());
        List<PropertyDO> propertyDOList = propertyDOMapper.selectByExample(null);
        Assert.assertNotNull(propertyDOList);
        Assert.assertEquals(0, propertyDOList.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updatePropertyById() {
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setName("test");
        propertyDO.setValue("测试");
        propertyDO.setRemark("备注");
        propertyDO.setGmtCreate(new Date());
        propertyDOMapper.insertSelective(propertyDO);

        propertyService.updatePropertyById(propertyDO.getId(), "update", null, null);
        List<PropertyDO> propertyDOList = propertyDOMapper.selectByExample(null);
        Assert.assertNotNull(propertyDOList);
        Assert.assertEquals(1, propertyDOList.size());
        Assert.assertEquals("update", propertyDOList.get(0).getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPropertyById() {
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setName("test");
        propertyDO.setValue("测试");
        propertyDO.setRemark("备注");
        propertyDO.setGmtCreate(new Date());
        propertyDOMapper.insertSelective(propertyDO);

        PropertyBO propertyBO = propertyService.getPropertyById(propertyDO.getId());
        Assert.assertNotNull(propertyBO);
        Assert.assertEquals("test", propertyBO.getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listProperty() {
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setName("test");
        propertyDO.setValue("测试");
        propertyDO.setRemark("备注");
        propertyDO.setGmtCreate(new Date());
        propertyDOMapper.insertSelective(propertyDO);

        ListPropertyParam param = new ListPropertyParam();
        Page<PropertyBO> propertyBOPage = propertyService.listProperty(param);
        Assert.assertNotNull(propertyBOPage.getRecords());
        Assert.assertEquals(1, propertyBOPage.getTotalCount().intValue());
    }

}
