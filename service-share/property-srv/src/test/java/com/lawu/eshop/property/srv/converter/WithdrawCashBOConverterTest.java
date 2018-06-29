package com.lawu.eshop.property.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.property.constants.CashChannelEnum;
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.eshop.property.dto.WithdrawCashStatusDTO;
import com.lawu.eshop.property.srv.bo.WithdrawCashBO;
import com.lawu.eshop.property.srv.domain.WithdrawCashDO;

/**
 * @author yangqh
 * @date 2017/7/19
 */
public class WithdrawCashBOConverterTest {

    @Test
    public void convert(){
        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setAccount("");
        withdrawCashDO.setAreaId(111);
        withdrawCashDO.setAuditFaildReason("");
        withdrawCashDO.setAuditUserId(1L);
        withdrawCashDO.setAuditUserName("");
        withdrawCashDO.setBusinessBankAccountId(1L);
        withdrawCashDO.setCashMoney(new BigDecimal("1"));
        withdrawCashDO.setCashNumber("22222");
        withdrawCashDO.setCityId(11);
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setGmtCreate(new Date());
        withdrawCashDO.setGmtModified(new Date());
        withdrawCashDO.setId(1L);
        withdrawCashDO.setMoney(new BigDecimal("1"));
        withdrawCashDO.setName("fdfd");
        withdrawCashDO.setProvinceId(1);
        withdrawCashDO.setRegionFullName("343/343/333");
        withdrawCashDO.setRemark("fdfd");
        withdrawCashDO.setThirdNumber("fdfd");
        withdrawCashDO.setUserNum("fdfd");
        withdrawCashDO.setStatus(CashStatusEnum.ACCEPT.getVal());
        withdrawCashDO.setUserType(UserTypeEnum.MEMBER.getVal());
        withdrawCashDO.setChannel(CashChannelEnum.ARTIFICIAL.getVal());

        WithdrawCashBO bo = WithdrawCashBOConverter.convert(withdrawCashDO);
        Assert.assertNotNull(bo);
    }

    @Test
    public void convert1(){
        List<WithdrawCashDO> withdrawCashDOList = new ArrayList<>();
        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();
        withdrawCashDO.setAccount("");
        withdrawCashDO.setAreaId(111);
        withdrawCashDO.setAuditFaildReason("");
        withdrawCashDO.setAuditUserId(1L);
        withdrawCashDO.setAuditUserName("");
        withdrawCashDO.setBusinessBankAccountId(1L);
        withdrawCashDO.setCashMoney(new BigDecimal("1"));
        withdrawCashDO.setCashNumber("22222");
        withdrawCashDO.setCityId(11);
        withdrawCashDO.setCurrentScale("1");
        withdrawCashDO.setGmtCreate(new Date());
        withdrawCashDO.setGmtModified(new Date());
        withdrawCashDO.setId(1L);
        withdrawCashDO.setMoney(new BigDecimal("1"));
        withdrawCashDO.setName("fdfd");
        withdrawCashDO.setProvinceId(1);
        withdrawCashDO.setRegionFullName("343/343/333");
        withdrawCashDO.setRemark("fdfd");
        withdrawCashDO.setThirdNumber("fdfd");
        withdrawCashDO.setUserNum("fdfd");
        withdrawCashDO.setStatus(CashStatusEnum.ACCEPT.getVal());
        withdrawCashDO.setUserType(UserTypeEnum.MEMBER.getVal());
        withdrawCashDO.setChannel(CashChannelEnum.ARTIFICIAL.getVal());
        withdrawCashDOList.add(withdrawCashDO);
        List<WithdrawCashBO> bos = WithdrawCashBOConverter.convert(withdrawCashDOList);
        Assert.assertNotNull(bos);
    }

    @Test
    public void convert2(){
        WithdrawCashBO withdrawCashBO = new WithdrawCashBO();
        withdrawCashBO.setId(1L);
        withdrawCashBO.setStatus(CashStatusEnum.ACCEPT);
        WithdrawCashStatusDTO dto = WithdrawCashBOConverter.convert(withdrawCashBO);
        Assert.assertNotNull(dto);
    }

    @Test
    public void convertWithdrawCashStatusDTOList(){
        List<WithdrawCashBO> withdrawCashBOList = new ArrayList<>();
        WithdrawCashBO withdrawCashBO = new WithdrawCashBO();
        withdrawCashBO.setId(1L);
        withdrawCashBO.setStatus(CashStatusEnum.ACCEPT);
        withdrawCashBOList.add(withdrawCashBO);
        List<WithdrawCashStatusDTO> dtos =WithdrawCashBOConverter.convertWithdrawCashStatusDTOList(withdrawCashBOList);
        Assert.assertNotNull(dtos);
    }
}
