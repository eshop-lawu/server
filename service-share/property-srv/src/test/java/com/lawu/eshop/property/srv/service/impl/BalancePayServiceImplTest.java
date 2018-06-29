package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.property.constants.FreezeStatusEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.eshop.property.constants.ThirdPayStatusEnum;
import com.lawu.eshop.property.param.BalancePayDataParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.domain.PropertyDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDOExample;
import com.lawu.eshop.property.srv.domain.RechargeDO;
import com.lawu.eshop.property.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.mapper.RechargeDOMapper;
import com.lawu.eshop.property.srv.service.BalancePayService;
import com.lawu.utils.PwdUtil;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class BalancePayServiceImplTest {

    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;

    @Autowired
    private RechargeDOMapper rechargeDOMapper;

    @Autowired
    private BalancePayService balancePayService;

    @Autowired
    private PropertyDOMapper propertyDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void balancePayProductOrder(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        BalancePayDataParam param = new BalancePayDataParam();
        param.setUserNum("10001");
        param.setAccount("17512036361");
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.getEnum(MemberTransactionTypeEnum.RECHARGE_POINT.getValue()));
        param.setOrderNum("1236547890");
        param.setTitle(MerchantTransactionTypeEnum.INTEGRAL_RECHARGE.getName());
        param.setTotalAmount("100");
        param.setBizIds("1");

        int ret = balancePayService.balancePayProductOrder(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        // 余额减去100=100
        PropertyInfoDOExample example = new PropertyInfoDOExample();
        example.createCriteria().andUserNumEqualTo("10001");
        List<PropertyInfoDO> rntList = propertyInfoDOMapper.selectByExample(example);
        Assert.assertNotNull(rntList);
        PropertyInfoDO pdo1 = rntList.get(0);
        Assert.assertEquals(100,pdo1.getBalance().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void balancePay(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        propertyInfoDO.setUserNum("10002");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        BalancePayDataParam param = new BalancePayDataParam();
        param.setUserNum("10001");
        param.setSideUserNum("10002");
        param.setAccount("17512036361");
        param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.getEnum(MerchantTransactionTypeEnum.INTEGRAL_RECHARGE.getValue()));
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.getEnum(MemberTransactionTypeEnum.RECHARGE_POINT.getValue()));
        param.setOrderNum("1236547890");
        param.setTitle(MerchantTransactionTypeEnum.INTEGRAL_RECHARGE.getName());
        param.setTotalAmount("100");
        param.setBizIds("1");
        int ret = balancePayService.balancePay(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        // 余额减去100=100
        PropertyInfoDOExample example = new PropertyInfoDOExample();
        example.createCriteria().andUserNumEqualTo("10001");
        List<PropertyInfoDO> rntList = propertyInfoDOMapper.selectByExample(example);
        Assert.assertNotNull(rntList);
        PropertyInfoDO pdo1 = rntList.get(0);
        Assert.assertEquals(100,pdo1.getBalance().intValue());

        //余额加上100=300
        PropertyInfoDOExample example1 = new PropertyInfoDOExample();
        example1.createCriteria().andUserNumEqualTo("10002");
        List<PropertyInfoDO> rntList1 = propertyInfoDOMapper.selectByExample(example1);
        Assert.assertNotNull(rntList1);
        PropertyInfoDO pdo2 = rntList1.get(0);
        Assert.assertEquals(300,pdo2.getBalance().intValue());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void balancePayPoint(){
        PropertyDO pdo = new PropertyDO();
        pdo.setName("m_balance_pay_point");
        pdo.setValue("1");
        pdo.setRemark("m_balance_pay_point");
        pdo.setGmtCreate(new Date());
        propertyDOMapper.insertSelective(pdo);

        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        RechargeDO recharge = new RechargeDO();
        recharge.setUserNum("10001");
        recharge.setRechargeMoney(new BigDecimal("100"));
        recharge.setCurrentScale("1");
        double dCurrentScale = Double.parseDouble("1");
        double dRechargeMoney = Double.parseDouble("100");
        double money = dRechargeMoney * dCurrentScale;
        recharge.setMoney(BigDecimal.valueOf(money));
        recharge.setRechargeType(PayTypeEnum.POINT.getVal());
        recharge.setChannel(TransactionPayTypeEnum.BALANCE.getVal());
        recharge.setStatus(ThirdPayStatusEnum.PAYING.getVal());
        recharge.setRechargeNumber(IdWorkerHelperImpl.generate(BizIdType.RECHARGE));
        recharge.setGmtCreate(new Date());
        rechargeDOMapper.insertSelective(recharge);

        BalancePayDataParam param = new BalancePayDataParam();
        param.setUserNum("10001");
        param.setAccount("17512036361");
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.getEnum(MemberTransactionTypeEnum.RECHARGE_POINT.getValue()));
        param.setOrderNum("1236547890");
        param.setTitle(MerchantTransactionTypeEnum.INTEGRAL_RECHARGE.getName());
        param.setTotalAmount("100");
        param.setBizIds("1");

        int ret = balancePayService.balancePayPoint(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        // 余额加上100=300
        PropertyInfoDOExample example = new PropertyInfoDOExample();
        example.createCriteria().andUserNumEqualTo("10001");
        List<PropertyInfoDO> rntList = propertyInfoDOMapper.selectByExample(example);
        Assert.assertNotNull(rntList);
        PropertyInfoDO pdo1 = rntList.get(0);
        Assert.assertEquals(100,pdo1.getBalance().intValue());
        Assert.assertEquals(300,pdo1.getPoint().intValue());
    }
}
