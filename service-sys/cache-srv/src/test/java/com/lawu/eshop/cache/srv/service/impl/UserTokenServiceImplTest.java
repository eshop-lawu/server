package com.lawu.eshop.cache.srv.service.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.cache.srv.CacheSrvApplicationTest;
import com.lawu.eshop.cache.srv.service.UserTokenService;

/**
 * @author Leach
 * @date 2017/9/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CacheSrvApplicationTest.class)
public class UserTokenServiceImplTest {

    @Autowired
    private UserTokenService userTokenService;

    @Ignore
    @Test
    public void setMemberTokenOneToOne() {
        String account = "12345678901";
        String token = "token12345678901";
        userTokenService.setMemberTokenOneToOne(account, token, 3600);
        String memberAccount = userTokenService.getMemberAccount(token, true, 3600, true);
        Assert.assertEquals(account, memberAccount);
    }
}
