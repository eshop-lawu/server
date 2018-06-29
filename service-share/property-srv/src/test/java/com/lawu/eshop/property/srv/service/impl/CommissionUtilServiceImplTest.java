package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.CommissionUtilBO;
import com.lawu.eshop.property.srv.service.CommissionUtilService;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class CommissionUtilServiceImplTest {

    @Autowired
    private CommissionUtilService commissionUtilService;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getClickAdMine(){

        CommissionUtilBO bo = commissionUtilService.getClickAdMine(new BigDecimal(1));
        Assert.assertNotNull(bo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getIncomeMoney(){

        CommissionUtilBO bo = commissionUtilService.getIncomeMoney(new BigDecimal(1));
        Assert.assertNotNull(bo);
    }
}
