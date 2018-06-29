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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.eshop.property.constants.ThirdPayStatusEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.eshop.property.dto.RechargeSaveDTO;
import com.lawu.eshop.property.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.property.param.AgentReportRechargeQueryParam;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.param.RechargeQueryDataParam;
import com.lawu.eshop.property.param.RechargeReportParam;
import com.lawu.eshop.property.param.RechargeSaveDataParam;
import com.lawu.eshop.property.param.ReportAgentAreaPointParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.AgentReportRechargeQueryBO;
import com.lawu.eshop.property.srv.bo.AreaRechargePointBO;
import com.lawu.eshop.property.srv.bo.BalanceAndPointListQueryBO;
import com.lawu.eshop.property.srv.bo.RechargeReportBO;
import com.lawu.eshop.property.srv.domain.RechargeDO;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.mapper.RechargeDOMapper;
import com.lawu.eshop.property.srv.service.RechargeService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class RechargeServiceImplTest {

    @Autowired
    private RechargeService rechargeService;

    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;

    @Autowired
    private RechargeDOMapper rechargeDOMapper;


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save(){
        RechargeSaveDataParam param = new RechargeSaveDataParam();
        param.setRechargeMoney("100");
        param.setPayTypeEnum(PayTypeEnum.BALANCE);
        param.setTransactionPayTypeEnum(TransactionPayTypeEnum.ALIPAY);
        param.setPayPwd("123456");
        param.setRechargeScale("1");
        param.setUserNum("M10001");
        RechargeSaveDTO dto = rechargeService.save(param);
        Assert.assertNotNull(dto.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void doHandleRechargeNotify(){
        RechargeDO recharge = new RechargeDO();
        recharge.setUserNum("M10001");
        recharge.setRechargeMoney(new BigDecimal("20"));
        recharge.setCurrentScale("1");
        double dCurrentScale = Double.parseDouble("1");
        double dRechargeMoney = Double.parseDouble("20");
        double money = dRechargeMoney * dCurrentScale;
        recharge.setMoney(BigDecimal.valueOf(money));
        recharge.setRechargeType(PayTypeEnum.BALANCE.getVal());
        recharge.setChannel(TransactionPayTypeEnum.ALIPAY.getVal());
        recharge.setStatus(ThirdPayStatusEnum.PAYING.getVal());
        recharge.setRechargeNumber(IdWorkerHelperImpl.generate(BizIdType.RECHARGE));
        recharge.setGmtCreate(new Date());
        rechargeDOMapper.insertSelective(recharge);

        NotifyCallBackParam param1 = new NotifyCallBackParam();
        param1.setUserNum("M10001");
        param1.setBizIds(recharge.getId().toString());
        param1.setBizFlag("5");
        param1.setTotalFee("20");
        param1.setOutTradeNo("1111111");
        param1.setBuyerLogonId("1232**656");
        param1.setTransactionPayTypeEnum(TransactionPayTypeEnum.ALIPAY);
        param1.setTradeNo("222222");
        Result result = rechargeService.doHandleRechargeNotify(param1);
        Assert.assertEquals(ResultCode.SUCCESS,result.getRet());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRechargeMoney(){
        RechargeDO recharge = new RechargeDO();
        recharge.setUserNum("M10001");
        recharge.setRechargeMoney(new BigDecimal("20"));
        recharge.setCurrentScale("1");
        double dCurrentScale = Double.parseDouble("1");
        double dRechargeMoney = Double.parseDouble("20");
        double money = dRechargeMoney * dCurrentScale;
        recharge.setMoney(BigDecimal.valueOf(money));
        recharge.setRechargeType(PayTypeEnum.BALANCE.getVal());
        recharge.setChannel(TransactionPayTypeEnum.ALIPAY.getVal());
        recharge.setStatus(ThirdPayStatusEnum.PAYING.getVal());
        recharge.setRechargeNumber(IdWorkerHelperImpl.generate(BizIdType.RECHARGE));
        recharge.setGmtCreate(new Date());
        rechargeDOMapper.insertSelective(recharge);

        ThirdPayCallBackQueryPayOrderDTO dto = rechargeService.getRechargeMoney(recharge.getId().toString());
        Assert.assertNotNull(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectPropertyinfoList(){
        RechargeDO recharge = new RechargeDO();
        recharge.setUserNum("M10001");
        recharge.setRechargeMoney(new BigDecimal("20"));
        recharge.setCurrentScale("1");
        double dCurrentScale = Double.parseDouble("1");
        double dRechargeMoney = Double.parseDouble("20");
        double money = dRechargeMoney * dCurrentScale;
        recharge.setMoney(BigDecimal.valueOf(money));
        recharge.setRechargeType(PayTypeEnum.BALANCE.getVal());
        recharge.setChannel(TransactionPayTypeEnum.ALIPAY.getVal());
        recharge.setStatus(ThirdPayStatusEnum.PAYING.getVal());
        recharge.setRechargeNumber(IdWorkerHelperImpl.generate(BizIdType.RECHARGE));
        recharge.setGmtCreate(new Date());
        rechargeDOMapper.insertSelective(recharge);

        RechargeQueryDataParam param = new RechargeQueryDataParam();
        param.setUserType(UserTypeEnum.MEMBER);
        param.setStatus(ThirdPayStatusEnum.PAYING);
        param.setRechargeNumber(recharge.getRechargeNumber());
        param.setPageSize(10);
        param.setCurrentPage(1);
        Page<BalanceAndPointListQueryBO> rtnPage = rechargeService.selectPropertyinfoList(param);
        Assert.assertEquals(1,rtnPage.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRechargePayType(){
        RechargeDO recharge = new RechargeDO();
        recharge.setUserNum("M10001");
        recharge.setRechargeMoney(new BigDecimal("20"));
        recharge.setCurrentScale("1");
        double dCurrentScale = Double.parseDouble("1");
        double dRechargeMoney = Double.parseDouble("20");
        double money = dRechargeMoney * dCurrentScale;
        recharge.setMoney(BigDecimal.valueOf(money));
        recharge.setRechargeType(PayTypeEnum.BALANCE.getVal());
        recharge.setChannel(TransactionPayTypeEnum.ALIPAY.getVal());
        recharge.setStatus(ThirdPayStatusEnum.PAYING.getVal());
        recharge.setRechargeNumber(IdWorkerHelperImpl.generate(BizIdType.RECHARGE));
        recharge.setGmtCreate(new Date());
        rechargeDOMapper.insertSelective(recharge);

        String rtn = rechargeService.getRechargePayType(recharge.getId());
        Assert.assertNotEquals("",rtn);

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectWithdrawCashListByDateAndStatus(){
        RechargeDO recharge = new RechargeDO();
        recharge.setUserNum("M10001");
        recharge.setRechargeMoney(new BigDecimal("20"));
        recharge.setCurrentScale("1");
        double dCurrentScale = Double.parseDouble("1");
        double dRechargeMoney = Double.parseDouble("20");
        double money = dRechargeMoney * dCurrentScale;
        recharge.setMoney(BigDecimal.valueOf(money));
        recharge.setRechargeType(PayTypeEnum.BALANCE.getVal());
        recharge.setChannel(TransactionPayTypeEnum.ALIPAY.getVal());
        recharge.setStatus(ThirdPayStatusEnum.PAYING.getVal());
        recharge.setRechargeNumber(IdWorkerHelperImpl.generate(BizIdType.RECHARGE));
        recharge.setGmtCreate(new Date());
        recharge.setGmtModified(new Date());
        rechargeDOMapper.insertSelective(recharge);

        RechargeReportParam param = new RechargeReportParam();
        param.setDate(DateUtil.getDateFormat(new Date(),"yyyy-MM-dd"));
        param.setRechargeType(PayTypeEnum.BALANCE.getVal());
        param.setStatus(ThirdPayStatusEnum.PAYING.getVal());
        RechargeReportBO bo = rechargeService.selectWithdrawCashListByDateAndStatus(param);
        Assert.assertNotNull(bo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRechargeById(){
        RechargeDO recharge = new RechargeDO();
        recharge.setUserNum("M10001");
        recharge.setRechargeMoney(new BigDecimal("20"));
        recharge.setCurrentScale("1");
        double dCurrentScale = Double.parseDouble("1");
        double dRechargeMoney = Double.parseDouble("20");
        double money = dRechargeMoney * dCurrentScale;
        recharge.setMoney(BigDecimal.valueOf(money));
        recharge.setRechargeType(PayTypeEnum.BALANCE.getVal());
        recharge.setChannel(TransactionPayTypeEnum.ALIPAY.getVal());
        recharge.setStatus(ThirdPayStatusEnum.PAYING.getVal());
        recharge.setRechargeNumber(IdWorkerHelperImpl.generate(BizIdType.RECHARGE));
        recharge.setGmtCreate(new Date());
        recharge.setGmtModified(new Date());
        rechargeDOMapper.insertSelective(recharge);
        ThirdPayStatusEnum status = rechargeService.getRechargeById(recharge.getId());
        Assert.assertEquals(ThirdPayStatusEnum.PAYING.getVal(),status.getVal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAgentAreaReportRechargeListByDate(){
        RechargeDO recharge = new RechargeDO();
        recharge.setUserNum("M10001");
        recharge.setRechargeMoney(new BigDecimal("20"));
        recharge.setCurrentScale("1");
        double dCurrentScale = Double.parseDouble("1");
        double dRechargeMoney = Double.parseDouble("20");
        double money = dRechargeMoney * dCurrentScale;
        recharge.setMoney(BigDecimal.valueOf(money));
        recharge.setRechargeType(PayTypeEnum.BALANCE.getVal());
        recharge.setChannel(TransactionPayTypeEnum.ALIPAY.getVal());
        recharge.setStatus(ThirdPayStatusEnum.PAYING.getVal());
        recharge.setRechargeNumber(IdWorkerHelperImpl.generate(BizIdType.RECHARGE));
        recharge.setGmtCreate(new Date());
        recharge.setGmtModified(new Date());
        rechargeDOMapper.insertSelective(recharge);

        AgentReportRechargeQueryParam param = new AgentReportRechargeQueryParam();
        param.setStatus(ThirdPayStatusEnum.PAYING.getVal());
        param.setChannel(TransactionPayTypeEnum.BALANCE.getVal());
        param.setDate(DateUtil.getDateFormat(new Date(),"yyyy-MM-dd"));
        List<AgentReportRechargeQueryBO> rtnList = rechargeService.selectAgentAreaReportRechargeListByDate(param);
        Assert.assertEquals(1,rtnList.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAreaRechargePoint(){
        RechargeDO recharge = new RechargeDO();
        recharge.setUserNum("M10001");
        recharge.setRechargeMoney(new BigDecimal("20"));
        recharge.setCurrentScale("1");
        double dCurrentScale = Double.parseDouble("1");
        double dRechargeMoney = Double.parseDouble("20");
        double money = dRechargeMoney * dCurrentScale;
        recharge.setMoney(BigDecimal.valueOf(money));
        recharge.setRechargeType(PayTypeEnum.POINT.getVal());
        recharge.setChannel(TransactionPayTypeEnum.ALIPAY.getVal());
        recharge.setStatus(ThirdPayStatusEnum.SUCCESS.getVal());
        recharge.setRechargeNumber(IdWorkerHelperImpl.generate(BizIdType.RECHARGE));
        recharge.setAreaId(1);
        recharge.setGmtCreate(new Date());
        recharge.setGmtModified(new Date());
        rechargeDOMapper.insertSelective(recharge);

        String bdate = DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(new Date()), "yyyy-MM-dd");
        String edate = DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(new Date()), "yyyy-MM-dd");
        ReportAgentAreaPointParam param = new ReportAgentAreaPointParam();
        param.setOffset(0);
        param.setPageSize(10);
        param.setBeginDate(bdate);
        param.setEndDate(edate);
        List<AreaRechargePointBO> rtnList = rechargeService.selectAreaRechargePoint(param);
        Assert.assertEquals(1,rtnList.size());
    }
}
