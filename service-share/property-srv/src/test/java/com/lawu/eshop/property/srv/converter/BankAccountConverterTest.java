package com.lawu.eshop.property.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.property.dto.BankAccountDTO;
import com.lawu.eshop.property.srv.bo.BankAccountBO;
import com.lawu.eshop.property.srv.domain.BankAccountDO;
import com.lawu.eshop.property.srv.domain.BankDO;

/**
 * @author yangqh
 * @date 2017/7/18
 */
public class BankAccountConverterTest {

    @Test
    public void convertBO(){
        BankAccountDO bankAccountDO = new BankAccountDO();
        bankAccountDO.setId(1L);
        bankAccountDO.setAccountName("sss");
        bankAccountDO.setAccountNumber("3432");
        bankAccountDO.setSubBranchName("rer4e");
        BankAccountBO bo = BankAccountConverter.convertBO(bankAccountDO,"ddd","fdfdf","");
        Assert.assertNotNull(bo);
    }

    @Test
    public void convertBOS(){
        List<BankAccountDO> bankAccountDOS = new ArrayList<>();
        BankAccountDO bado = new BankAccountDO();
        bado.setId(1L);
        bado.setSubBranchName("12312");
        bado.setAccountNumber("fdfdf");
        bado.setAccountName("fdfd");
        bado.setBankId(1);
        bado.setNote("faf(0000)");
        bado.setStatus(new Byte("1"));
        bado.setUserNum("M10001");
        bankAccountDOS.add(bado);
        List<BankDO> bankDOS = new ArrayList<>();
        BankDO bdo = new BankDO();
        bdo.setId(1);
        bdo.setName("111");
        bdo.setStatus(new Byte("1"));
        bdo.setIconUrl("343");
        List<BankAccountBO> rtnList = BankAccountConverter.convertBOS(bankAccountDOS,bankDOS);
        Assert.assertNotNull(rtnList);
    }

    @Test
    public void convertDTO(){
        BankAccountBO bankAccountBO = new BankAccountBO();
        bankAccountBO.setIconUrl("fdfd");
        bankAccountBO.setId(1L);
        bankAccountBO.setAccountName("dfd");
        bankAccountBO.setAccountNumber("zzz");
        bankAccountBO.setBankId(1);
        bankAccountBO.setBankName("中银");
        bankAccountBO.setSubBranchName("fdf");
        BankAccountDTO dto = BankAccountConverter.convertDTO(bankAccountBO);
        Assert.assertNotNull(dto);
    }

    @Test
    public void convertDTOS(){
        List<BankAccountBO> bankAccountBOS = new ArrayList<>();
        BankAccountBO bankAccountBO = new BankAccountBO();
        bankAccountBO.setIconUrl("fdfd");
        bankAccountBO.setId(1L);
        bankAccountBO.setAccountName("dfd");
        bankAccountBO.setAccountNumber("zzz");
        bankAccountBO.setBankId(1);
        bankAccountBO.setBankName("中银");
        bankAccountBO.setSubBranchName("fdf");
        bankAccountBOS.add(bankAccountBO);
        List<BankAccountDTO> rtnList = BankAccountConverter.convertDTOS(bankAccountBOS);
        Assert.assertNotNull(rtnList);
    }
}
