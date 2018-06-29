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

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.eshop.property.param.CashBillDataParam;
import com.lawu.eshop.property.param.CashDataParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.WithdrawCashBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashDetailBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashQueryBO;
import com.lawu.eshop.property.srv.domain.BankAccountDO;
import com.lawu.eshop.property.srv.domain.BankDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDOExample;
import com.lawu.eshop.property.srv.domain.WithdrawCashDO;
import com.lawu.eshop.property.srv.mapper.BankAccountDOMapper;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.mapper.WithdrawCashDOMapper;
import com.lawu.eshop.property.srv.service.CashManageFrontService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.PwdUtil;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class CashManageFrontServiceImplTest {

    @Autowired
    private BankDOMapper bankDOMapper;

    @Autowired
    private BankAccountDOMapper bankAccountDOMapper;

    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;

    @Autowired
    protected CashManageFrontService cashManageFrontService;

    @Autowired
    private WithdrawCashDOMapper withdrawCashDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save(){
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountDO bankAccountDO=new BankAccountDO();
        bankAccountDO.setUserNum("10001");
        bankAccountDO.setAccountName("习大大");
        bankAccountDO.setAccountNumber("6217852000014838927");
        bankAccountDO.setBankId(bdo.getId());
        bankAccountDO.setSubBranchName("南山支行");
        bankAccountDO.setStatus(new Byte("1"));
        bankAccountDO.setNote("南山支行");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("100"));
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        CashDataParam param = new CashDataParam();
        param.setAccount("17512036361");
        param.setUserNum("10001");
        param.setAreaId(111);
        param.setCityId(11);
        param.setName("习大大");
        param.setProvinceId(1);
        param.setRegionFullName("北京市/县辖/东城区");
        param.setTransactionType(MerchantTransactionTypeEnum.WITHDRAW.getValue());
        param.setUserType(UserTypeEnum.MERCHANT.getVal());
        param.setBusinessBankAccountId(bankAccountDO.getId());
        param.setCashMoney("10");
        param.setPayPwd("123456");
        int ret = cashManageFrontService.save(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        PropertyInfoDOExample example = new PropertyInfoDOExample();
        example.createCriteria().andUserNumEqualTo("10001");
        List<PropertyInfoDO> rntList = propertyInfoDOMapper.selectByExample(example);
        Assert.assertNotNull(rntList);
        PropertyInfoDO pdo1 = rntList.get(0);
        Assert.assertEquals(90,pdo1.getBalance().intValue());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findCashList(){
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountDO bankAccountDO=new BankAccountDO();
        bankAccountDO.setUserNum("10001");
        bankAccountDO.setAccountName("习大大");
        bankAccountDO.setAccountNumber("6217852000014838927");
        bankAccountDO.setBankId(bdo.getId());
        bankAccountDO.setSubBranchName("南山支行");
        bankAccountDO.setStatus(new Byte("1"));
        bankAccountDO.setNote("南山支行");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("100"));
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        CashDataParam param = new CashDataParam();
        param.setAccount("17512036361");
        param.setUserNum("10001");
        param.setAreaId(111);
        param.setCityId(11);
        param.setName("习大大");
        param.setProvinceId(1);
        param.setRegionFullName("北京市/县辖/东城区");
        param.setTransactionType(MerchantTransactionTypeEnum.WITHDRAW.getValue());
        param.setUserType(UserTypeEnum.MERCHANT.getVal());
        param.setBusinessBankAccountId(bankAccountDO.getId());
        param.setCashMoney("10");
        param.setPayPwd("123456");
        int ret = cashManageFrontService.save(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        CashBillDataParam param1 = new CashBillDataParam();
        param1.setUserNum("10001");
        param1.setPageSize(10);
        param1.setCurrentPage(1);
        Page<WithdrawCashQueryBO> rntResult = cashManageFrontService.findCashList(param1);
        Assert.assertEquals(1,rntResult.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void cashDetail(){
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountDO bankAccountDO=new BankAccountDO();
        bankAccountDO.setUserNum("10001");
        bankAccountDO.setAccountName("习大大");
        bankAccountDO.setAccountNumber("6217852000014838927");
        bankAccountDO.setBankId(bdo.getId());
        bankAccountDO.setSubBranchName("南山支行");
        bankAccountDO.setStatus(new Byte("1"));
        bankAccountDO.setNote("南山支行");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setCashMoney(new BigDecimal("10"));
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setMoney(BigDecimal.valueOf(10));
        withdrawCashDO.setUserNum("10001");
        withdrawCashDO.setUserType(new Byte("1"));
        withdrawCashDO.setChannel(new Byte("1"));
        withdrawCashDO.setStatus(CashStatusEnum.APPLY.getVal());
        withdrawCashDO.setBusinessBankAccountId(bankAccountDO.getId());
        withdrawCashDO.setCashNumber("1111111111111111111");
        withdrawCashDO.setGmtCreate(new Date());
        withdrawCashDO.setAccount("17512036361");
        withdrawCashDO.setName("习大大");
        withdrawCashDO.setProvinceId(1);
        withdrawCashDO.setCityId(11);
        withdrawCashDO.setAreaId(111);
        withdrawCashDO.setGmtAccept(new Date());
        withdrawCashDO.setGmtFinish(new Date());
        withdrawCashDO.setRegionFullName("北京市/县辖/东城区");
        int ret = withdrawCashDOMapper.insertSelective(withdrawCashDO);
        Assert.assertEquals(1,ret);

        WithdrawCashDetailBO bo = cashManageFrontService.cashDetail(withdrawCashDO.getId());
        Assert.assertEquals("1111111111111111111",bo.getCashNumber());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void list(){
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountDO bankAccountDO=new BankAccountDO();
        bankAccountDO.setUserNum("10001");
        bankAccountDO.setAccountName("习大大");
        bankAccountDO.setAccountNumber("6217852000014838927");
        bankAccountDO.setBankId(bdo.getId());
        bankAccountDO.setSubBranchName("南山支行");
        bankAccountDO.setStatus(new Byte("1"));
        bankAccountDO.setNote("南山支行");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setCashMoney(new BigDecimal("10"));
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setMoney(BigDecimal.valueOf(10));
        withdrawCashDO.setUserNum("10001");
        withdrawCashDO.setUserType(new Byte("1"));
        withdrawCashDO.setChannel(new Byte("1"));
        withdrawCashDO.setStatus(CashStatusEnum.APPLY.getVal());
        withdrawCashDO.setBusinessBankAccountId(bankAccountDO.getId());
        withdrawCashDO.setCashNumber("1111111111111111111");
        withdrawCashDO.setGmtCreate(new Date());
        withdrawCashDO.setAccount("17512036361");
        withdrawCashDO.setName("习大大");
        withdrawCashDO.setProvinceId(1);
        withdrawCashDO.setCityId(11);
        withdrawCashDO.setAreaId(111);
        withdrawCashDO.setGmtAccept(new Date());
        withdrawCashDO.setGmtFinish(new Date());
        withdrawCashDO.setRegionFullName("北京市/县辖/东城区");
        int ret = withdrawCashDOMapper.insertSelective(withdrawCashDO);
        Assert.assertEquals(1,ret);

        List<Long> param = new ArrayList<>();
        param.add(Long.valueOf(withdrawCashDO.getId()));
        List<WithdrawCashBO> rntList = cashManageFrontService.list(param);
        Assert.assertEquals(1,rntList.size());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void isExistCash(){
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountDO bankAccountDO=new BankAccountDO();
        bankAccountDO.setUserNum("10001");
        bankAccountDO.setAccountName("习大大");
        bankAccountDO.setAccountNumber("6217852000014838927");
        bankAccountDO.setBankId(bdo.getId());
        bankAccountDO.setSubBranchName("南山支行");
        bankAccountDO.setStatus(new Byte("1"));
        bankAccountDO.setNote("南山支行");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setCashMoney(new BigDecimal("10"));
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setMoney(BigDecimal.valueOf(10));
        withdrawCashDO.setUserNum("10001");
        withdrawCashDO.setUserType(new Byte("1"));
        withdrawCashDO.setChannel(new Byte("1"));
        withdrawCashDO.setStatus(CashStatusEnum.APPLY.getVal());
        withdrawCashDO.setBusinessBankAccountId(bankAccountDO.getId());
        withdrawCashDO.setCashNumber("1111111111111111111");
        withdrawCashDO.setGmtCreate(new Date());
        withdrawCashDO.setAccount("17512036361");
        withdrawCashDO.setName("习大大");
        withdrawCashDO.setProvinceId(1);
        withdrawCashDO.setCityId(11);
        withdrawCashDO.setAreaId(111);
        withdrawCashDO.setGmtAccept(new Date());
        withdrawCashDO.setGmtFinish(new Date());
        withdrawCashDO.setRegionFullName("北京市/县辖/东城区");
        int ret = withdrawCashDOMapper.insertSelective(withdrawCashDO);
        Assert.assertEquals(1,ret);

        Boolean bool = cashManageFrontService.isExistCash("10001",bankAccountDO.getId());
        Assert.assertEquals(true,bool);

        bool = cashManageFrontService.isExistCash("10002",bankAccountDO.getId());
        Assert.assertEquals(false,bool);
    }
}
