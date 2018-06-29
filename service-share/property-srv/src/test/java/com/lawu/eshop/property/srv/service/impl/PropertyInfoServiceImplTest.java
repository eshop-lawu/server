package com.lawu.eshop.property.srv.service.impl;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.*;
import com.lawu.eshop.property.param.BackagePropertyinfoDataParam;
import com.lawu.eshop.property.param.FreezeQueryParam;
import com.lawu.eshop.property.param.PropertyInfoBackageParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.FreezeBO;
import com.lawu.eshop.property.srv.bo.PropertyBalanceBO;
import com.lawu.eshop.property.srv.bo.PropertyInfoBO;
import com.lawu.eshop.property.srv.bo.PropertyPointAndBalanceBO;
import com.lawu.eshop.property.srv.bo.PropertyPointBO;
import com.lawu.eshop.property.srv.domain.FreezeDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDOExample;
import com.lawu.eshop.property.srv.mapper.FreezeDOMapper;
import com.lawu.eshop.property.srv.mapper.PointDetailDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.service.CommissionUtilService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.PwdUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class PropertyInfoServiceImplTest {

    @Autowired
    private com.lawu.eshop.property.srv.service.PropertyInfoService propertyInfoService;

    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;
    @Autowired
    private FreezeDOMapper freezeDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPropertyInfoByUserNum(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        PropertyInfoBO bo = propertyInfoService.getPropertyInfoByUserNum("M10001");
        Assert.assertNotNull(bo);

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updatePayPwd(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        propertyInfoService.updatePayPwd("M10001","111111");

        PropertyInfoDO pdo = propertyInfoDOMapper.selectByPrimaryKey(propertyInfoDO.getId());
        Boolean bool = PwdUtil.verify("111111",pdo.getPayPassword());
        Assert.assertEquals(true,bool);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPropertyBalanceByUserNum(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        PropertyBalanceBO bo = propertyInfoService.getPropertyBalanceByUserNum("M10001");
        Assert.assertNotNull(bo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPropertyPointByUserNum(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.RELEASE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        PropertyPointBO bo = propertyInfoService.getPropertyPointByUserNum("M10001");
        Assert.assertNotNull(bo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updatePropertyNumbers(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.RELEASE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        int ret1 = propertyInfoService.updatePropertyNumbers("M10001","B","A",new BigDecimal("5"));
        PropertyInfoDO pdo1 = propertyInfoDOMapper.selectByPrimaryKey(propertyInfoDO.getId());
        Assert.assertEquals(ResultCode.SUCCESS,ret1);
        Assert.assertEquals(205,pdo1.getBalance().intValue());

        int ret2 = propertyInfoService.updatePropertyNumbers("M10001","B","M",new BigDecimal("5"));
        PropertyInfoDO pdo2 = propertyInfoDOMapper.selectByPrimaryKey(propertyInfoDO.getId());
        Assert.assertEquals(ResultCode.SUCCESS,ret2);
        Assert.assertEquals(200,pdo2.getBalance().intValue());

        int ret3 = propertyInfoService.updatePropertyNumbers("M10001","P","A",new BigDecimal("5"));
        PropertyInfoDO pdo3 = propertyInfoDOMapper.selectByPrimaryKey(propertyInfoDO.getId());
        Assert.assertEquals(ResultCode.SUCCESS,ret3);
        Assert.assertEquals(205, pdo3.getPoint().intValue());

        int ret4 = propertyInfoService.updatePropertyNumbers("M10001","P","M",new BigDecimal("5"));
        PropertyInfoDO pdo4 = propertyInfoDOMapper.selectByPrimaryKey(propertyInfoDO.getId());
        Assert.assertEquals(ResultCode.SUCCESS,ret4);
        Assert.assertEquals(200,pdo4.getPoint().intValue());

        int ret5 = propertyInfoService.updatePropertyNumbers("M10001","L","A",new BigDecimal("5"));
        PropertyInfoDO pdo5 = propertyInfoDOMapper.selectByPrimaryKey(propertyInfoDO.getId());
        Assert.assertEquals(ResultCode.SUCCESS,ret5);
        Assert.assertEquals(205, pdo5.getLoveAccount().intValue());

        int ret6 = propertyInfoService.updatePropertyNumbers("M10001","L","M",new BigDecimal("5"));
        PropertyInfoDO pdo6 = propertyInfoDOMapper.selectByPrimaryKey(propertyInfoDO.getId());
        Assert.assertEquals(ResultCode.SUCCESS,ret6);
        Assert.assertEquals(200,pdo6.getLoveAccount().intValue());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void validateBalance(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("200"));
        propertyInfoDO.setFreeze(new Byte("0"));
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        int ret = propertyInfoService.validateBalance("M10001","200",true,"123456");
        Assert.assertEquals(ResultCode.SUCCESS,ret);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void validatePoint(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.RELEASE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        int ret = propertyInfoService.validatePoint("M10001","200");
        Assert.assertEquals(ResultCode.SUCCESS,ret);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPropertyInfoMoney(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.RELEASE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        PropertyPointAndBalanceBO bo = propertyInfoService.getPropertyInfoMoney("M10001");
        Assert.assertNotNull(bo);
        Assert.assertEquals(200,bo.getBalance().intValue());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void savePropertyInfo(){
        propertyInfoService.savePropertyInfo("M10001");
        PropertyInfoDOExample example = new PropertyInfoDOExample();
        example.createCriteria().andUserNumEqualTo("M10001");
        List<PropertyInfoDO> rtnList =  propertyInfoDOMapper.selectByExample(example);
        Assert.assertNotNull(rtnList);
        Assert.assertEquals(1,rtnList.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectLoveAccount(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.RELEASE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);
        BigDecimal money = propertyInfoService.selectLoveAccount("M10001");
        Assert.assertEquals(200,money.intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateBalanceAndPoint(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.RELEASE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        BackagePropertyinfoDataParam param = new BackagePropertyinfoDataParam();
        param.setAccount("12045");
        param.setUserTypeEnum(UserTypeEnum.MEMBER);
        param.setPayTypeEnum(PayTypeEnum.BALANCE);
        param.setMoney("20");
        param.setUserNum("M10001");
        int ret = propertyInfoService.updateBalanceAndPoint(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        param.setPayTypeEnum(PayTypeEnum.POINT);
        int ret1 = propertyInfoService.updateBalanceAndPoint(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret1);

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updatePropertyinfoFreeze(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.RELEASE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        int ret = propertyInfoService.updatePropertyinfoFreeze("M10001", PropertyinfoFreezeEnum.YES);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPropertyinfoPageList(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.RELEASE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        PropertyInfoBackageParam param = new PropertyInfoBackageParam();
        param.setUserNum("M10001");
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<PropertyInfoBO> rtnPage = propertyInfoService.getPropertyinfoPageList(param);
        Assert.assertEquals(1,rtnPage.getTotalCount().intValue());

        PropertyInfoBackageParam param1 = new PropertyInfoBackageParam();
        param1.setCurrentPage(1);
        param1.setPageSize(10);
        param1.setUserType(UserTypeEnum.MEMBER);
        Page<PropertyInfoBO> rtnPage1 = propertyInfoService.getPropertyinfoPageList(param1);
        Assert.assertEquals(1,rtnPage1.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPropertyinfoFreeze(){
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("200"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.RELEASE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        PropertyinfoFreezeEnum bean = propertyInfoService.getPropertyinfoFreeze("M10001");
        Assert.assertNotNull(bean);

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getFreezeList(){
        FreezeDO fdo = new FreezeDO();
        fdo.setOrderNum("1111111111");
        fdo.setUserNum("M34343");
        fdo.setGmtCreate(new Date());
        fdo.setMoney(new BigDecimal(1));
        fdo.setBizId(1L);
        fdo.setDays(7);
        fdo.setFundType(new Byte("1"));
        fdo.setOriginalMoney(new BigDecimal(1));
        fdo.setStatus(new Byte("0"));
        freezeDOMapper.insert(fdo);

        FreezeQueryParam param = new FreezeQueryParam();
        param.setUserNum("M34343");
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<FreezeBO> rtnPage = propertyInfoService.getFreezeList(param);
        Assert.assertEquals(1,rtnPage.getTotalCount().intValue());

    }
}
