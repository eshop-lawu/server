package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.OrderRefundStatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.FreezeStatusEnum;
import com.lawu.eshop.property.constants.FreezeTypeEnum;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.param.OrderComfirmDataParam;
import com.lawu.eshop.property.param.OrderRefundDataParam;
import com.lawu.eshop.property.param.OrderReleaseFreezeParam;
import com.lawu.eshop.property.param.OrderSysJobParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.domain.FreezeDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;
import com.lawu.eshop.property.srv.mapper.FreezeDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;
import com.lawu.eshop.property.srv.service.OrderService;
import com.lawu.utils.PwdUtil;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TransactionDetailDOMapper transactionDetailDOMapper;

    @Autowired
    private FreezeDOMapper freezeDOMapper;

    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void doHandleOrderPayNotify(){
        NotifyCallBackParam param = new NotifyCallBackParam();
        param.setUserNum("M10001");
        param.setOutTradeNo("111111111");
        param.setBuyerLogonId("fdf**fdfd");
        param.setTransactionPayTypeEnum(TransactionPayTypeEnum.ALIPAY);
        param.setTotalFee("100");
        param.setBizIds("1");
        param.setTradeNo("2222222222");
        int ret = orderService.doHandleOrderPayNotify(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        TransactionDetailDO tdo = new TransactionDetailDO();
        tdo.setUserNum("M10001");
        tdo.setAmount(new BigDecimal("100"));
        tdo.setBizId("1");
        tdo.setBizNum("1");
        tdo.setDirection(new Byte("1"));
        tdo.setThirdTransactionNum("111111111");
        tdo.setTitle("ce");
        tdo.setTransactionAccount("fdfd");
        tdo.setTransactionAccountType(new Byte("1"));
        transactionDetailDOMapper.insert(tdo);

        NotifyCallBackParam param1 = new NotifyCallBackParam();
        param1.setUserNum("M10001");
        param1.setOutTradeNo("111111111");
        param1.setBuyerLogonId("fdf**fdfd");
        param1.setTransactionPayTypeEnum(TransactionPayTypeEnum.ALIPAY);
        param1.setTotalFee("100");
        param1.setBizIds("1");
        param1.setTradeNo("2222222222");
        int ret1 = orderService.doHandleOrderPayNotify(param1);
        Assert.assertEquals(ResultCode.SUCCESS,ret1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void doHandlePayOrderNotify(){
        NotifyCallBackParam param = new NotifyCallBackParam();
        param.setUserNum("M10001");
        param.setOutTradeNo("111111111");
        param.setBuyerLogonId("fdf**fdfd");
        param.setTransactionPayTypeEnum(TransactionPayTypeEnum.ALIPAY);
        param.setTotalFee("100");
        param.setBizIds("1");
        param.setTradeNo("2222222222");
        param.setSideUserNum("B10002");
        int ret = orderService.doHandlePayOrderNotify(param);

        NotifyCallBackParam param1 = new NotifyCallBackParam();
        param1.setUserNum("M10001");
        param1.setOutTradeNo("111111111");
        param1.setBuyerLogonId("fdf**fdfd");
        param1.setTransactionPayTypeEnum(TransactionPayTypeEnum.ALIPAY);
        param1.setTotalFee("100");
        param1.setBizIds("1");
        param1.setTradeNo("2222222222");
        param1.setSideUserNum("B10002");
        int ret1 = orderService.doHandlePayOrderNotify(param1);
        Assert.assertEquals(ResultCode.SUCCESS,ret1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void comfirmDelivery(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        OrderComfirmDataParam param = new OrderComfirmDataParam();
        param.setUserNum("M10001");
        param.setBizId("1");
        param.setTotalOrderMoney("100");
        int ret1 = orderService.comfirmDelivery(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void doRefundScopeInside() {
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        FreezeDO freezeDO = new FreezeDO();
        freezeDO.setUserNum("M10001");
        freezeDO.setMoney(new BigDecimal("10"));
        freezeDO.setOriginalMoney(new BigDecimal("1"));
        freezeDO.setFundType(FreezeTypeEnum.PRODUCT_ORDER.getVal());
        freezeDO.setBizId(Long.valueOf("1"));
        freezeDO.setStatus(FreezeStatusEnum.FREEZE.getVal());
        freezeDO.setGmtCreate(new Date());
        freezeDO.setDays(Integer.valueOf("7"));
        freezeDOMapper.insertSelective(freezeDO);

        OrderRefundDataParam param = new OrderRefundDataParam();
        param.setUserNum("M10001");
        param.setSideUserNum("B10002");
        param.setOrderId("1");
        param.setOrderItemIds("1");
        param.setRefundMoney("1");
        param.setLast(false);
        param.setTransactionPayTypeEnum(TransactionPayTypeEnum.BALANCE);
        param.setTradeNo("1111111111333");
        param.setOrderRefundStatusEnum(OrderRefundStatusEnum.FINISH);
        try {
            int ret = orderService.doRefundScopeInside(param);
            Assert.assertEquals(ResultCode.SUCCESS,ret);

            param.setLast(true);
            int ret1 = orderService.doRefundScopeInside(param);
            Assert.assertEquals(ResultCode.SUCCESS,ret1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        param.setTransactionPayTypeEnum(TransactionPayTypeEnum.ALIPAY);
        try {
            int ret = orderService.doRefundScopeInside(param);
            //Assert.assertEquals(ResultCode.SUCCESS,ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        param.setTransactionPayTypeEnum(TransactionPayTypeEnum.WX);
        try {
            int ret = orderService.doRefundScopeInside(param);
            //Assert.assertEquals(ResultCode.SUCCESS,ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void comfirmReleaseFreeze(){
        FreezeDO freezeDO = new FreezeDO();
        freezeDO.setUserNum("M10001");
        freezeDO.setMoney(new BigDecimal("100"));
        freezeDO.setOriginalMoney(new BigDecimal("100"));
        freezeDO.setFundType(FreezeTypeEnum.PRODUCT_ORDER.getVal());
        freezeDO.setBizId(Long.valueOf("1"));
        freezeDO.setStatus(FreezeStatusEnum.FREEZE.getVal());
        freezeDO.setGmtCreate(new Date());
        freezeDO.setDays(Integer.valueOf("7"));
        freezeDOMapper.insertSelective(freezeDO);

        OrderReleaseFreezeParam param = new OrderReleaseFreezeParam();
        param.setUserNums("M10001");
        param.setOrderIds("1");
        param.setAccounts("1361313");
        param.setPayWays(TransactionPayTypeEnum.ALIPAY.getVal());
        param.setRegionPaths("1/11/111");
        int ret = orderService.comfirmReleaseFreeze(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void comfirmSysJob(){
        OrderSysJobParam param = new OrderSysJobParam();
        param.setUserNums("M10001");
        param.setOrderIds("1");
        param.setAccounts("164641");
        param.setOrderActualMoney("100");
        param.setPayWays(TransactionPayTypeEnum.ALIPAY.getVal());
        param.setRegionPaths("1/11/111");
        int ret = orderService.comfirmSysJob(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);
    }
}
