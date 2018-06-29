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

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.property.constants.FreezeStatusEnum;
import com.lawu.eshop.property.constants.LoveTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.param.PointDetailQueryData1Param;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.CommissionUtilBO;
import com.lawu.eshop.property.srv.domain.PointDetailDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.mapper.PointDetailDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.service.CommissionUtilService;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;
import com.lawu.utils.PwdUtil;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class PropertyInfoDataServiceImplTest {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;

    @Autowired
    private PointDetailDOMapper pointDetailDOMapper;

    @Autowired
    private CommissionUtilService commissionUtilService;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void doHanlderMinusPoint(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum("M10001");
        param.setPoint("50");
        param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.ADD_AD);
        param.setBizId("1");
        int ret = propertyInfoDataService.doHanlderMinusPoint(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        PropertyInfoDO pdo = propertyInfoDOMapper.selectByPrimaryKey(propertyInfoDO.getId());
        Assert.assertEquals(150,pdo.getPoint().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void doHanlderAddPoint(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum("M10001");
        param.setPoint("50");
        param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.INVITE_FANS);
        param.setBizId("1");
        int ret = propertyInfoDataService.doHanlderAddPoint(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        PropertyInfoDO pdo = propertyInfoDOMapper.selectByPrimaryKey(propertyInfoDO.getId());
        Assert.assertEquals(250,pdo.getPoint().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void doHanlderBalanceIncome(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum("M10001");
        param.setPoint("50");
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.AD_QZ);
        param.setBizId("1");
        param.setLoveTypeEnum(LoveTypeEnum.AD_QZ);
        param.setTempBizId("1");
        int ret = propertyInfoDataService.doHanlderBalanceIncome(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        CommissionUtilBO commissionBO = commissionUtilService.getIncomeMoney(new BigDecimal(param.getPoint()));

        PropertyInfoDO pdo = propertyInfoDOMapper.selectByPrimaryKey(propertyInfoDO.getId());
        Assert.assertEquals(commissionBO.getActureMoneyIn().add(propertyInfoDO.getBalance()),pdo.getBalance());

        Assert.assertEquals(commissionBO.getActureLoveIn().intValue(),pdo.getLoveAccount().intValue());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPointDetailByUserNumAndPointTypeAndBizId(){
        PointDetailDO pointDetailDO = new PointDetailDO();
        pointDetailDO.setTitle("看广告");
        pointDetailDO.setPointNum(IdWorkerHelperImpl.generate(BizIdType.POINT));
        pointDetailDO.setUserNum("M10001");
        pointDetailDO.setPointType(MemberTransactionTypeEnum.BACKAGE.getValue());
        pointDetailDO.setPoint(new BigDecimal("100"));
        pointDetailDO.setDirection(new Byte("1"));
        pointDetailDO.setBizId("1");
        pointDetailDO.setRemark("");
        pointDetailDO.setGmtCreate(new Date());
        pointDetailDOMapper.insertSelective(pointDetailDO);

        PointDetailQueryData1Param param = new PointDetailQueryData1Param();
        param.setUserNum("M10001");
        param.setPointType(MemberTransactionTypeEnum.BACKAGE.getValue());
        param.setBizId("1");
        Integer ret = propertyInfoDataService.getPointDetailByUserNumAndPointTypeAndBizId(param);
        Assert.assertEquals(1,ret.intValue());
    }
}
