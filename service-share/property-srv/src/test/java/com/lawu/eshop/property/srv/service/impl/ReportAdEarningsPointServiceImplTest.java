package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.property.constants.LoveTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.PropertyInfoDirectionEnum;
import com.lawu.eshop.property.param.ReportAdEarningsPointParam;
import com.lawu.eshop.property.param.TransactionDetailSaveDataParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.ReportAdEarningsPointBO;
import com.lawu.eshop.property.srv.bo.ReportEarningsBO;
import com.lawu.eshop.property.srv.domain.PointDetailDO;
import com.lawu.eshop.property.srv.mapper.PointDetailDOMapper;
import com.lawu.eshop.property.srv.service.ReportAdEarningsPointService;
import com.lawu.eshop.property.srv.service.TransactionDetailService;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class ReportAdEarningsPointServiceImplTest {

    @Autowired
    private ReportAdEarningsPointService reportAdEarningsPointService;

    @Autowired
    private PointDetailDOMapper pointDetailDOMapper;

    @Autowired
    private TransactionDetailService transactionDetailService;



    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getReportAdEarningsPoint(){
        TransactionDetailSaveDataParam param = new TransactionDetailSaveDataParam();
        param.setTitle("看广告");
        param.setTransactionNum("1111111");
        param.setUserNum("M10001");
        param.setTransactionType(MemberTransactionTypeEnum.AD_QZ.getValue());
        param.setTransactionAccount("121210");
        param.setTransactionAccountType(TransactionPayTypeEnum.ALIPAY.getVal());
        param.setAmount(new BigDecimal("100"));
        param.setBizId("1");
        param.setRemark("");
        param.setDirection(PropertyInfoDirectionEnum.IN.getVal());
        param.setBizNum("222");
        int ret = transactionDetailService.save(param);

        ReportAdEarningsPointParam param1 = new ReportAdEarningsPointParam();
        param1.setBizId(1L);
        param1.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.AD_QZ);
        param1.setLoveTypeEnum(LoveTypeEnum.AD_QZ);
        ReportAdEarningsPointBO bo = reportAdEarningsPointService.getReportAdEarningsPoint(param1);

        Assert.assertEquals(100,bo.getUserTotalPoint().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getReportEarnings(){
        PointDetailDO pointDetailDO = new PointDetailDO();
        pointDetailDO.setTitle("看广告");
        pointDetailDO.setPointNum(IdWorkerHelperImpl.generate(BizIdType.POINT));
        pointDetailDO.setUserNum("M10001");
        pointDetailDO.setPointType(new Byte("2"));
        pointDetailDO.setPoint(new BigDecimal("100"));
        pointDetailDO.setDirection(new Byte("1"));
        pointDetailDO.setBizId("2");
        pointDetailDO.setRemark("");
        pointDetailDO.setGmtCreate(new Date());
        pointDetailDOMapper.insertSelective(pointDetailDO);

        List<Long> list = new ArrayList<>();
        list.add(2L);
        ReportEarningsBO bo = reportAdEarningsPointService.getReportEarnings(list);
        Assert.assertNotNull(bo.getUserPoint());
    }
}
