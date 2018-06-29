package com.lawu.eshop.property.srv.service.impl;

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

import com.lawu.eshop.property.param.BankAccountParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.BankAccountBO;
import com.lawu.eshop.property.srv.domain.BankAccountDO;
import com.lawu.eshop.property.srv.domain.BankDO;
import com.lawu.eshop.property.srv.mapper.BankAccountDOMapper;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
import com.lawu.eshop.property.srv.service.BankAccountService;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class BankAccountServiceImplTest {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountDOMapper bankAccountDOMapper;

    @Autowired
    private BankDOMapper bankDOMapper;


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveBankAccount(){

        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountParam bankAccountParam = new BankAccountParam();
        bankAccountParam.setAccountName("习大大");
        bankAccountParam.setAccountNumber("6217852000014838927");
        bankAccountParam.setBankId(bdo.getId());
        bankAccountParam.setSubBranchName("南山支行");
        bankAccountService.saveBankAccount("10001",bankAccountParam);

        List<BankAccountDO>  rntList = bankAccountDOMapper.selectByExample(null);
        Assert.assertNotNull(rntList);
        Assert.assertTrue(rntList.size() == 1);

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectMyBank(){
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountParam bankAccountParam = new BankAccountParam();
        bankAccountParam.setAccountName("习大大");
        bankAccountParam.setAccountNumber("6217852000014838927");
        bankAccountParam.setBankId(bdo.getId());
        bankAccountParam.setSubBranchName("南山支行");
        bankAccountService.saveBankAccount("10001",bankAccountParam);

        List<BankAccountBO> rntList = bankAccountService.selectMyBank("10001");
        Assert.assertNotNull(rntList);
        Assert.assertTrue(rntList.size() == 1);

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void remove(){
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountParam bankAccountParam = new BankAccountParam();
        bankAccountParam.setAccountName("习大大");
        bankAccountParam.setAccountNumber("6217852000014838927");
        bankAccountParam.setBankId(bdo.getId());
        bankAccountParam.setSubBranchName("南山支行");
        bankAccountService.saveBankAccount("10001",bankAccountParam);

        List<BankAccountBO> rntList = bankAccountService.selectMyBank("10001");
        Assert.assertNotNull(rntList);
        Assert.assertTrue(rntList.size() == 1);

        bankAccountService.remove(rntList.get(0).getId());

        rntList = bankAccountService.selectMyBank("10001");
        Assert.assertNull(rntList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectByAccount(){
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountParam bankAccountParam = new BankAccountParam();
        bankAccountParam.setAccountName("习大大");
        bankAccountParam.setAccountNumber("6217852000014838927");
        bankAccountParam.setBankId(bdo.getId());
        bankAccountParam.setSubBranchName("南山支行");
        bankAccountService.saveBankAccount("M10001",bankAccountParam);

        Boolean bool = bankAccountService.selectByAccount("6217852000014838927",Byte.valueOf("1"),"M10001",null);
        Assert.assertTrue(bool);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAccount(){
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
        BankDO bankDO=bankDOMapper.selectByPrimaryKey(bdo.getId());
        bankAccountDO.setNote("中银(0000)");
        bankAccountDOMapper.insert(bankAccountDO);

        BankAccountBO bo = bankAccountService.selectAccount(bankAccountDO.getId());
        Assert.assertNotNull(bo);
        Assert.assertEquals("6217852000014838927",bo.getAccountNumber());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateBankAccount(){
        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        BankAccountParam bankAccountParam = new BankAccountParam();
        bankAccountParam.setAccountName("习大大");
        bankAccountParam.setAccountNumber("6217852000014838927");
        bankAccountParam.setBankId(bdo.getId());
        bankAccountParam.setSubBranchName("南山支行");
        bankAccountService.saveBankAccount("10001",bankAccountParam);

        bankAccountParam = new BankAccountParam();
        bankAccountParam.setBankId(bdo.getId());
        bankAccountParam.setSubBranchName("罗湖支行");
        bankAccountParam.setAccountNumber("6212264000054195975");
        bankAccountParam.setAccountName("彭丽媛");
        bankAccountService.updateBankAccount(1L,bankAccountParam);

        BankAccountBO bo = bankAccountService.selectAccount(1L);
        Assert.assertNotNull(bo);
        Assert.assertEquals("6212264000054195975",bo.getAccountNumber());
    }
}
