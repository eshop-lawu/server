package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.eshop.property.srv.bo.IncomeMsgBO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.ConsumptionTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.PropertyInfoDirectionEnum;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.param.TransactionDetailQueryForBackageParam;
import com.lawu.eshop.property.param.TransactionDetailQueryForMemberParam;
import com.lawu.eshop.property.param.TransactionDetailQueryForMerchantParam;
import com.lawu.eshop.property.param.TransactionDetailSaveDataParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.TransactionDetailBO;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;
import com.lawu.eshop.property.srv.service.TransactionDetailService;
import com.lawu.framework.core.page.Page;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class TransactionDetailServiceImplTest {

    @Autowired
    private TransactionDetailService transactionDetailService;

    @Autowired
    private TransactionDetailDOMapper transactionDetailDOMapper;



    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save(){
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
        Assert.assertEquals(ResultCode.SUCCESS,ret);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findPageByUserNumForMerchant(){
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
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        TransactionDetailQueryForMerchantParam query = new TransactionDetailQueryForMerchantParam();
        query.setConsumptionType(ConsumptionTypeEnum.INCOME);
        query.setCurrentPage(1);
        query.setPageSize(10);
        Page<TransactionDetailBO> rtnPage = transactionDetailService.findPageByUserNumForMerchant("M10001",query);
        Assert.assertEquals(1,rtnPage.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findPageByUserNumForMember(){
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
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        TransactionDetailQueryForMemberParam query = new TransactionDetailQueryForMemberParam();
        query.setTransactionType(MemberTransactionTypeEnum.AD_QZ);
        query.setCurrentPage(1);
        query.setPageSize(10);
        Page<TransactionDetailBO> rtnPage = transactionDetailService.findPageByUserNumForMember("M10001",query);
        Assert.assertEquals(1,rtnPage.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void verifyOrderIsPaySuccess(){
        NotifyCallBackParam param = new NotifyCallBackParam();
        param.setOutTradeNo("4545121");
        param.setTradeNo("2313213232");
        param.setUserNum("M10001");
        Boolean bool = transactionDetailService.verifyOrderIsPaySuccess(param);
        Assert.assertEquals(false,bool);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getBackageRechargePageList(){
        TransactionDetailSaveDataParam param = new TransactionDetailSaveDataParam();
        param.setTitle("看广告");
        param.setTransactionNum("1111111");
        param.setUserNum("M10001");
        param.setTransactionType(MemberTransactionTypeEnum.BACKAGE.getValue());
        param.setTransactionAccount("121210");
        param.setTransactionAccountType(TransactionPayTypeEnum.ALIPAY.getVal());
        param.setAmount(new BigDecimal("100"));
        param.setBizId("1");
        param.setRemark("");
        param.setDirection(PropertyInfoDirectionEnum.IN.getVal());
        param.setBizNum("222");
        int ret = transactionDetailService.save(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        TransactionDetailQueryForBackageParam query = new TransactionDetailQueryForBackageParam();
        query.setMemberTransactionType(MemberTransactionTypeEnum.AD_QZ);
        query.setUserNum("M10001");
        query.setCurrentPage(1);
        query.setPageSize(10);
        Page<TransactionDetailBO> rtnPage = transactionDetailService.getBackageRechargePageList(query);
        Assert.assertEquals(1,rtnPage.getTotalCount().intValue());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void summaryIncome(){
        List<IncomeMsgBO> bos = new ArrayList<>();
        IncomeMsgBO incomeMsgBO1 = new IncomeMsgBO();
        incomeMsgBO1.setMoney(new BigDecimal(10));
        incomeMsgBO1.setUserNum("M000001");
        incomeMsgBO1.setPayTypeEnum(PayTypeEnum.POINT);
        bos.add(incomeMsgBO1);
        IncomeMsgBO incomeMsgBO2 = new IncomeMsgBO();
        incomeMsgBO2.setMoney(new BigDecimal(11));
        incomeMsgBO2.setUserNum("M000002");
        incomeMsgBO2.setPayTypeEnum(PayTypeEnum.BALANCE);
        bos.add(incomeMsgBO2);
        int ret = transactionDetailService.summaryIncome(bos);
        Assert.assertEquals(ret,ResultCode.SUCCESS);
    }
}
