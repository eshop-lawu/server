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

import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.bo.BankBO;
import com.lawu.eshop.property.srv.domain.BankDO;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
import com.lawu.eshop.property.srv.service.BankService;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class BankServiceImplTest {

    @Autowired
    private BankService bankService;

    @Autowired
    private BankDOMapper bankDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findBank(){

        BankDO bdo = new BankDO();
        bdo.setName("test");
        bdo.setIconUrl("/1.jpg");
        bdo.setStatus(new Byte("1"));
        bdo.setOrdinal(1);
        bdo.setGmtCreate(new Date());
        bankDOMapper.insert(bdo);

        List<BankBO> rntList = bankService.findBank();

        Assert.assertNotNull(rntList);
        Assert.assertTrue(rntList.size() == 1);

    }

}
