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

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.property.constants.BusinessDepositOperEnum;
import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;
import com.lawu.eshop.property.dto.BusinessDepositInitDTO;
import com.lawu.eshop.property.param.BusinessDepositOperDataParam;
import com.lawu.eshop.property.param.BusinessDepositQueryDataParam;
import com.lawu.eshop.property.param.BusinessDepositSaveDataParam;
import com.lawu.eshop.property.param.BusinessRefundDepositDataParam;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.BusinessDepositDetailBO;
import com.lawu.eshop.property.srv.bo.BusinessDepositQueryBO;
import com.lawu.eshop.property.srv.domain.BankAccountDO;
import com.lawu.eshop.property.srv.domain.BankDO;
import com.lawu.eshop.property.srv.domain.BusinessDepositDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.mapper.BankAccountDOMapper;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
import com.lawu.eshop.property.srv.mapper.BusinessDepositDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.service.BusinessDepositService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;
import com.lawu.utils.PwdUtil;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class BusinessDepositServiceImplTest {

    @Autowired
    private BusinessDepositService businessDepositService;

    @Autowired
    private BusinessDepositDOMapper businessDepositDOMapper;

    @Autowired
    private BankDOMapper bankDOMapper;

    @Autowired
    private BankAccountDOMapper bankAccountDOMapper;

    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;



    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save(){

        BusinessDepositSaveDataParam param = new BusinessDepositSaveDataParam();
        param.setBusinessId(1L);
        param.setUserNum("10001");
        param.setBusinessAccount("17512036361");
        param.setBusinessName("张三");
        param.setDeposit("1000");
        param.setProvinceId("1");
        param.setCityId("101");
        param.setAreaId("10101");
        BusinessDepositInitDTO dto = businessDepositService.save(param);

        Assert.assertNotNull(dto.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void doHandleDepositNotify(){
        NotifyCallBackParam notifyParam1 = new NotifyCallBackParam();
        notifyParam1.setBizIds("0");
        notifyParam1.setUserNum("B10001");
        notifyParam1.setTotalFee("1000");
        notifyParam1.setTradeNo("10000000001");
        notifyParam1.setBuyerLogonId("yqh**01");
        notifyParam1.setTransactionPayTypeEnum(TransactionPayTypeEnum.getEnum(new Byte("2")));
        notifyParam1.setMerchantId(1L);
        Result result1 = businessDepositService.doHandleDepositNotify(notifyParam1);
        Assert.assertEquals(ResultCode.FAIL,result1.getRet());

        BusinessDepositDO bddo = new BusinessDepositDO();
        bddo.setBusinessId(1L);
        bddo.setUserNum("B10001");
        bddo.setBusinessAccount("17512036361");
        bddo.setBusinessName("张三");
        bddo.setDepositNumber(IdWorkerHelperImpl.generate(BizIdType.DEPOSIT));
        bddo.setAmount(new BigDecimal("1000"));
        bddo.setStatus(BusinessDepositStatusEnum.PAYING.getVal());
        bddo.setProvinceId(Long.valueOf("1"));
        bddo.setCityId(Long.valueOf("101"));
        bddo.setAreaId(Long.valueOf("10101"));
        bddo.setGmtCreate(new Date());
        businessDepositDOMapper.insertSelective(bddo);

        NotifyCallBackParam notifyParam = new NotifyCallBackParam();
        notifyParam.setBizIds(bddo.getId().toString());
        notifyParam.setUserNum("B10001");
        notifyParam.setTotalFee("1001");
        notifyParam.setTradeNo("10000000001");
        notifyParam.setBuyerLogonId("yqh**01");
        notifyParam.setTransactionPayTypeEnum(TransactionPayTypeEnum.getEnum(new Byte("2")));
        notifyParam.setMerchantId(1L);
        Result result = businessDepositService.doHandleDepositNotify(notifyParam);
        Assert.assertEquals(ResultCode.NOTIFY_MONEY_ERROR,result.getRet());

        notifyParam.setTotalFee("1000");
        Result result2 = businessDepositService.doHandleDepositNotify(notifyParam);
        Assert.assertEquals(ResultCode.SUCCESS,result2.getRet());

        Result result3 = businessDepositService.doHandleDepositNotify(notifyParam);
        Assert.assertEquals(ResultCode.SUCCESS,result3.getRet());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectDepositList(){
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountDO bankAccountDO=new BankAccountDO();
        bankAccountDO.setUserNum("B10001");
        bankAccountDO.setAccountName("习大大");
        bankAccountDO.setAccountNumber("6217852000014838927");
        bankAccountDO.setBankId(bdo.getId());
        bankAccountDO.setSubBranchName("南山支行");
        bankAccountDO.setStatus(new Byte("1"));
        bankAccountDO.setNote("南山支行(0000)");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        BusinessDepositDO bddo = new BusinessDepositDO();
        bddo.setBusinessId(1L);
        bddo.setUserNum("B10001");
        bddo.setBusinessAccount("17512036361");
        bddo.setBusinessName("吕布");
        bddo.setDepositNumber("11111111111111111111");
        bddo.setAmount(new BigDecimal("1000"));
        bddo.setStatus(BusinessDepositStatusEnum.APPLY_REFUND.getVal());
        bddo.setProvinceId(1L);
        bddo.setCityId(11L);
        bddo.setAreaId(111L);
        bddo.setPaymentMethod(TransactionPayTypeEnum.ALIPAY.getVal());
        bddo.setGmtCreate(new Date());
        bddo.setGmtPay(new Date());
        bddo.setBusinessBankAccountId(bankAccountDO.getId());
        businessDepositDOMapper.insertSelective(bddo);

        bddo.setBusinessId(1L);
        bddo.setUserNum("B10001");
        bddo.setBusinessAccount("17512036361");
        bddo.setBusinessName("吕布");
        bddo.setDepositNumber("22222222222222222222");
        bddo.setAmount(new BigDecimal("1000"));
        bddo.setStatus(BusinessDepositStatusEnum.ACCPET_REFUND.getVal());
        bddo.setProvinceId(2L);
        bddo.setCityId(22L);
        bddo.setAreaId(222L);
        bddo.setPaymentMethod(TransactionPayTypeEnum.WX.getVal());
        bddo.setGmtCreate(new Date());
        bddo.setGmtPay(new Date());
        bddo.setBusinessBankAccountId(bankAccountDO.getId());
        businessDepositDOMapper.insertSelective(bddo);

        BusinessDepositQueryDataParam param = new BusinessDepositQueryDataParam();
        param.setContent("11111111111111111111");
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<BusinessDepositQueryBO> page = businessDepositService.selectDepositList(param);
        Assert.assertEquals(1,page.getTotalCount().intValue());

        param = new BusinessDepositQueryDataParam();
        param.setBusinessDepositStatusEnum(BusinessDepositStatusEnum.getEnum(BusinessDepositStatusEnum.APPLY_REFUND.getVal()));
        param.setRegionPath("1/11/111");
        param.setBeginDate(DateUtil.getDateFormat(new Date(),"yyyy-MM-dd"));
        param.setEndDate(DateUtil.getDateFormat(new Date(),"yyyy-MM-dd"));
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<BusinessDepositQueryBO> page1 = businessDepositService.selectDepositList(param);
        Assert.assertEquals(1,page1.getTotalCount().intValue());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateBusinessDeposit(){
        BusinessDepositDO bddo = new BusinessDepositDO();
        bddo.setBusinessId(1L);
        bddo.setUserNum("10001");
        bddo.setBusinessAccount("17512036361");
        bddo.setBusinessName("吕布");
        bddo.setDepositNumber("11111111111111111111");
        bddo.setAmount(new BigDecimal("1000"));
        bddo.setStatus(BusinessDepositStatusEnum.ACCPET_REFUND.getVal());
        bddo.setProvinceId(1L);
        bddo.setCityId(11L);
        bddo.setAreaId(111L);
        bddo.setPaymentMethod(TransactionPayTypeEnum.ALIPAY.getVal());
        bddo.setGmtCreate(new Date());
        bddo.setGmtPay(new Date());
        businessDepositDOMapper.insertSelective(bddo);

        BusinessDepositOperDataParam param = new BusinessDepositOperDataParam();
        param.setOperUserId(1L);
        param.setOperUserName("admin");
        param.setBusinessDepositOperEnum(BusinessDepositOperEnum.getEnum(BusinessDepositOperEnum.REFUND_SUCCESS.getVal()));
        param.setBusinessId(1L);
        param.setId("1");
        param.setUserNum("10001");
        int ret = businessDepositService.updateBusinessDeposit(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

        BusinessDepositQueryDataParam queryParam = new BusinessDepositQueryDataParam();
        queryParam.setBusinessDepositStatusEnum(BusinessDepositStatusEnum.getEnum(BusinessDepositStatusEnum.ACCPET_REFUND.getVal()));
        Page<BusinessDepositQueryBO> page = businessDepositService.selectDepositList(queryParam);
        Assert.assertEquals(0,page.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void refundDeposit(){
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountDO bankAccountDO=new BankAccountDO();
        bankAccountDO.setUserNum("B10001");
        bankAccountDO.setAccountName("习大大");
        bankAccountDO.setAccountNumber("6217852000014838927");
        bankAccountDO.setBankId(bdo.getId());
        bankAccountDO.setSubBranchName("南山支行");
        bankAccountDO.setStatus(new Byte("1"));
        bankAccountDO.setNote("南山支行(0000)");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("B10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        BusinessDepositDO bddo = new BusinessDepositDO();
        bddo.setBusinessId(1L);
        bddo.setUserNum("B10001");
        bddo.setBusinessAccount("17512036361");
        bddo.setBusinessName("吕布");
        bddo.setDepositNumber("11111111111111111111");
        bddo.setAmount(new BigDecimal("1000"));
        bddo.setStatus(BusinessDepositStatusEnum.VERIFYD.getVal());
        bddo.setProvinceId(1L);
        bddo.setCityId(11L);
        bddo.setAreaId(111L);
        bddo.setPaymentMethod(TransactionPayTypeEnum.ALIPAY.getVal());
        bddo.setGmtCreate(new Date());
        bddo.setGmtPay(new Date());
        bddo.setGmtModified(DateUtil.formatDate("2017-01-01 00:00:00","yyyy-MM-dd HH:mm:ss"));
        bddo.setBusinessBankAccountId(bankAccountDO.getId());
        businessDepositDOMapper.insertSelective(bddo);

        BusinessRefundDepositDataParam param = new BusinessRefundDepositDataParam();
        param.setUserNum("B10001");
        param.setId(bddo.getId().toString());
        param.setBusinessBankAccountId(bankAccountDO.getId().toString());
        param.setPayPwd("123456");
        int ret = businessDepositService.refundDeposit(param);
        Assert.assertEquals(ResultCode.SUCCESS,ret);

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectDeposit(){
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
        bankAccountDO.setNote("南山支行(0000)");
        bankAccountDOMapper.insertSelective(bankAccountDO);

        BusinessDepositDO bddo = new BusinessDepositDO();
        bddo.setBusinessId(1L);
        bddo.setUserNum("10001");
        bddo.setBusinessAccount("17512036361");
        bddo.setBusinessName("吕布");
        bddo.setDepositNumber("11111111111111111111");
        bddo.setAmount(new BigDecimal("1000"));
        bddo.setStatus(BusinessDepositStatusEnum.VERIFYD.getVal());
        bddo.setProvinceId(1L);
        bddo.setCityId(11L);
        bddo.setAreaId(111L);
        bddo.setPaymentMethod(TransactionPayTypeEnum.ALIPAY.getVal());
        bddo.setGmtCreate(new Date());
        bddo.setGmtPay(new Date());
        bddo.setGmtModified(DateUtil.formatDate("2017-01-01 00:00:00","yyyy-MM-dd HH:mm:ss"));
        bddo.setBusinessBankAccountId(bankAccountDO.getId());
        businessDepositDOMapper.insertSelective(bddo);

        BusinessDepositDetailBO bo = businessDepositService.selectDeposit("1");
        Assert.assertEquals(bddo.getId().intValue(),bo.getId().intValue());
    }
}
