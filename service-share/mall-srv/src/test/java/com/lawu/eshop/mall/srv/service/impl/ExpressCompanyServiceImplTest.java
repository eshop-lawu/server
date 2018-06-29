package com.lawu.eshop.mall.srv.service.impl;

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

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.ExpressCompanyBO;
import com.lawu.eshop.mall.srv.domain.ExpressCompanyDO;
import com.lawu.eshop.mall.srv.mapper.ExpressCompanyDOMapper;
import com.lawu.eshop.mall.srv.service.ExpressCompanyService;

/**
 * @author zhangyong
 * @date 2017/7/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class ExpressCompanyServiceImplTest {

    @Autowired
    private ExpressCompanyDOMapper expressCompanyDOMapper;
    @Autowired
    private ExpressCompanyService expressCompanyService;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void list(){
        ExpressCompanyDO expressCompanyDO = new ExpressCompanyDO();
        expressCompanyDO.setStatus(StatusEnum.VALID.getValue());
        expressCompanyDO.setName("test");
        expressCompanyDO.setIsShow(false);
        expressCompanyDO.setOrdinal(1);
        expressCompanyDO.setGmtCreate(new Date());
        expressCompanyDOMapper.insert(expressCompanyDO);
        List<ExpressCompanyBO> list =  expressCompanyService.list();
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listShow(){
        ExpressCompanyDO expressCompanyDO = new ExpressCompanyDO();
        expressCompanyDO.setStatus(StatusEnum.VALID.getValue());
        expressCompanyDO.setName("test");
        expressCompanyDO.setIsShow(true);
        expressCompanyDO.setOrdinal(1);
        expressCompanyDO.setGmtCreate(new Date());
        expressCompanyDOMapper.insert(expressCompanyDO);
        List<ExpressCompanyBO> list =  expressCompanyService.list(true);
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void get(){
        ExpressCompanyDO expressCompanyDO = new ExpressCompanyDO();
        expressCompanyDO.setStatus(StatusEnum.VALID.getValue());
        expressCompanyDO.setName("test");
        expressCompanyDO.setIsShow(true);
        expressCompanyDO.setOrdinal(1);
        expressCompanyDO.setGmtCreate(new Date());
        expressCompanyDOMapper.insert(expressCompanyDO);
        ExpressCompanyBO expressCompanyBO = expressCompanyService.get(expressCompanyDO.getId());
        Assert.assertNotNull(expressCompanyBO);
        Assert.assertEquals("test",expressCompanyBO.getName());
    }
}
