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

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.CashOperEnum;
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.eshop.property.param.CashBackageOperDataParam;
import com.lawu.eshop.property.param.CashBackageQueryDataParam;
import com.lawu.eshop.property.param.CashBackageQueryDetailParam;
import com.lawu.eshop.property.param.CashBackageQuerySumParam;
import com.lawu.eshop.property.param.WithdrawCashReportParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.WithdrawCashBackageQueryBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashBackageQuerySumBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashReportBO;
import com.lawu.eshop.property.srv.domain.BankAccountDO;
import com.lawu.eshop.property.srv.domain.BankDO;
import com.lawu.eshop.property.srv.domain.WithdrawCashDO;
import com.lawu.eshop.property.srv.mapper.BankAccountDOMapper;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
import com.lawu.eshop.property.srv.mapper.WithdrawCashDOMapper;
import com.lawu.eshop.property.srv.service.CashManageBackageService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class CashManageBackageServiceImplTest {

    @Autowired
    private BankDOMapper bankDOMapper;

    @Autowired
    private BankAccountDOMapper bankAccountDOMapper;

    @Autowired
    protected CashManageBackageService cashManageBackageService;

    @Autowired
    private WithdrawCashDOMapper withdrawCashDOMapper;


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findCashInfo(){
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
        bankAccountDO.setNote("南山支行(45464)");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setCashMoney(new BigDecimal("10"));
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setMoney(BigDecimal.valueOf(10));
        withdrawCashDO.setUserNum("M10001");
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

        CashBackageQueryDataParam param = new CashBackageQueryDataParam();
        param.setContent("1111111111111111111");
        param.setPageSize(10);
        param.setCurrentPage(1);

        Page<WithdrawCashBackageQueryBO> rntResult = cashManageBackageService.findCashInfo(param);
        Assert.assertEquals(1,rntResult.getTotalCount().intValue());

        CashBackageQueryDataParam param1 = new CashBackageQueryDataParam();
        param1.setPageSize(10);
        param1.setCurrentPage(1);
        param1.setUserTypeEnum(UserTypeEnum.MEMBER);
        param1.setBeginDate("2017-07-17");
        param1.setEndDate("2017-07-18");
        param1.setCashStatsuEnum(CashStatusEnum.APPLY);
        param1.setRegionPath("1/11/111");
        cashManageBackageService.findCashInfo(param1);
        Page<WithdrawCashBackageQueryBO> rntResult1 = cashManageBackageService.findCashInfo(param);
        Assert.assertEquals(1,rntResult1.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getTotalNum(){

        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setCashMoney(new BigDecimal("10"));
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setMoney(BigDecimal.valueOf(10));
        withdrawCashDO.setUserNum("M10001");
        withdrawCashDO.setUserType(new Byte("1"));
        withdrawCashDO.setChannel(new Byte("1"));
        withdrawCashDO.setStatus(CashStatusEnum.SUCCESS.getVal());
        withdrawCashDO.setBusinessBankAccountId(1L);
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

        CashBackageQuerySumParam param = new CashBackageQuerySumParam();
        param.setUserTypeEnum(UserTypeEnum.MEMBER);
        WithdrawCashBackageQuerySumBO rnt = cashManageBackageService.getTotalNum(param);
        Assert.assertEquals(1,rnt.getSuccessNums().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findCashInfoDetail(){
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
        bankAccountDO.setNote("南山支行(45464)");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setCashMoney(new BigDecimal("10"));
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setMoney(BigDecimal.valueOf(10));
        withdrawCashDO.setUserNum("M10001");
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

        CashBackageQueryDetailParam param = new CashBackageQueryDetailParam();
        param.setUserNum("M10001");
        param.setPageSize(10);
        param.setCurrentPage(1);
        Page<WithdrawCashBackageQueryBO> rntResult = cashManageBackageService.findCashInfoDetail(param);
        Assert.assertEquals(1,rntResult.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateWithdrawCash(){
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
        bankAccountDO.setNote("南山支行(45464)");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setCashMoney(new BigDecimal("10"));
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setMoney(BigDecimal.valueOf(10));
        withdrawCashDO.setUserNum("M10001");
        withdrawCashDO.setUserType(new Byte("1"));
        withdrawCashDO.setChannel(new Byte("1"));
        withdrawCashDO.setStatus(CashStatusEnum.ACCEPT.getVal());
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

        CashBackageOperDataParam param = new CashBackageOperDataParam();
        param.setUserNum("M10001");
        param.setAuditUserId(1L);
        param.setAuditUserName("nimei");
        param.setCashOperEnum(CashOperEnum.FAILURE);
        param.setFailReason("bugei");
        param.setId(withdrawCashDO.getId().toString());
        int ret1 = cashManageBackageService.updateWithdrawCash(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectWithdrawCashListByDateAndStatus(){
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
        bankAccountDO.setNote("南山支行(45464)");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setCashMoney(new BigDecimal("10"));
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setMoney(BigDecimal.valueOf(10));
        withdrawCashDO.setUserNum("M10001");
        withdrawCashDO.setUserType(new Byte("1"));
        withdrawCashDO.setChannel(new Byte("1"));
        withdrawCashDO.setStatus(CashStatusEnum.ACCEPT.getVal());
        withdrawCashDO.setBusinessBankAccountId(bankAccountDO.getId());
        withdrawCashDO.setCashNumber("1111111111111111111");
        withdrawCashDO.setGmtCreate(new Date());
        withdrawCashDO.setAccount("17512036361");
        withdrawCashDO.setName("习大大");
        withdrawCashDO.setProvinceId(1);
        withdrawCashDO.setCityId(11);
        withdrawCashDO.setAreaId(111);
        withdrawCashDO.setGmtAccept(new Date());
        withdrawCashDO.setGmtFinish(DateUtil.formatDate("2017-07-17 00:00:00","yyyy-MM-dd HH:mm:ss"));
        withdrawCashDO.setRegionFullName("北京市/县辖/东城区");
        int ret = withdrawCashDOMapper.insertSelective(withdrawCashDO);
        Assert.assertEquals(1,ret);

        WithdrawCashReportParam param = new WithdrawCashReportParam();
        param.setDate("2017-07-17");
        param.setStatus(CashStatusEnum.ACCEPT.getVal());
        List<WithdrawCashReportBO> rntList = cashManageBackageService.selectWithdrawCashListByDateAndStatus(param);
        Assert.assertEquals(1,rntList.size());
    }
}
