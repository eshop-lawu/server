package com.lawu.eshop.property.srv.converter;

import com.lawu.eshop.property.dto.BankDTO;
import com.lawu.eshop.property.srv.bo.BankBO;
import com.lawu.eshop.property.srv.domain.BankDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangqh
 * @date 2017/7/18
 */
public class BankConverterTest {

    @Test
    public void convertBO(){
        BankDO bankDO = new BankDO();
        bankDO.setIconUrl("fdfd");
        bankDO.setId(1);
        bankDO.setName("fdfd");
        BankBO bo = BankConverter.convertBO(bankDO);
        Assert.assertNotNull(bo);
    }

    @Test
    public void convertBO1(){
        List<BankDO> bankDOS = new ArrayList<>();
        BankDO bankDO = new BankDO();
        bankDO.setIconUrl("fdfd");
        bankDO.setId(1);
        bankDO.setName("fdfd");
        bankDOS.add(bankDO);
        List<BankBO> rtnList = BankConverter.convertBO(bankDOS);
        Assert.assertNotNull(rtnList);
    }

    @Test
    public void convertDTO(){
        BankBO bankBO = new BankBO();
        bankBO.setIconUrl("fdfd");
        bankBO.setId(1);
        bankBO.setName("fdfd");
        BankDTO dto = BankConverter.convertDTO(bankBO);
        Assert.assertNotNull(dto);
    }

    @Test
    public void convertDTOS(){
        List<BankBO> bankBOS = new ArrayList<>();
        BankBO bankBO = new BankBO();
        bankBO.setIconUrl("fdfd");
        bankBO.setId(1);
        bankBO.setName("fdfd");
        bankBOS.add(bankBO);
        List<BankDTO> rtnList = BankConverter.convertDTOS(bankBOS);
        Assert.assertNotNull(rtnList);
    }
}
