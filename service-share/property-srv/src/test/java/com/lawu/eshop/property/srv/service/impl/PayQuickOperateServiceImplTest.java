package com.lawu.eshop.property.srv.service.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.property.constants.ClientTypeEnum;
import com.lawu.eshop.property.param.ThirdPayRefundParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.service.PayQuickOperateService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class PayQuickOperateServiceImplTest {

    @Autowired
    private PayQuickOperateService payQuickOperateService;

    @Ignore
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save(){
        ThirdPayRefundParam param = new ThirdPayRefundParam();
        param.setRefundMoney("100");
        param.setRefundTotalMoney("100");
        param.setTradeNo("4200000036201712197172006492");
        param.setPayType(TransactionPayTypeEnum.WX);
        param.setClientType(ClientTypeEnum.PC);
        payQuickOperateService.refund(param);
    }


}
