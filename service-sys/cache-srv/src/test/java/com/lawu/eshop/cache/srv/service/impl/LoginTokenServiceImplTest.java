package com.lawu.eshop.cache.srv.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.cache.srv.CacheSrvApplicationTest;
import com.lawu.eshop.cache.srv.EmbeddedRedis;
import com.lawu.eshop.cache.srv.service.LoginTokenService;

/**
 * @author Leach
 * @date 2017/10/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CacheSrvApplicationTest.class)
public class LoginTokenServiceImplTest extends EmbeddedRedis {

    @Autowired
    private LoginTokenService loginTokenService;

    @Test
    public void setTokenOneToOne() {
        Integer userType = 1;
        String account = "11111111111";
        String token = "token11111111111";
        loginTokenService.setTokenOneToOne(userType, account, token, 3600, 1);
        String userAccount = loginTokenService.getAccount(userType, token, true, 3600, true);
        Assert.assertEquals(account, userAccount);
    }

    @Test
    public void setTokenOneToMany() {
        Integer userType = 1;
        String account = "22222222222";
        String token = "token22222222222";
        loginTokenService.setTokenOneToMany(userType, account, token, 3600);
        String userAccount = loginTokenService.getAccount(userType, token, true, 3600, false);
        Assert.assertEquals(account, userAccount);
    }

    @Test
    public void getTokenClearType() {

        // 记录tokenClearType
        Integer userType = 1;
        String account = "33333333333";
        String token = "token33333333333";
        Integer tokenClearType = 1;
        loginTokenService.setTokenOneToOne(userType, account, token, 3600, tokenClearType);

        loginTokenService.delRelationshipByAccount(userType, account, 3600, tokenClearType);

        Integer reason = loginTokenService.getTokenClearType(userType, token);
        Assert.assertNotNull(reason);
        Assert.assertEquals(tokenClearType.intValue(), reason.intValue());

        // 不记录tokenClearType
        Integer userType2 = 1;
        String account2 = "44444444444";
        String token2 = "token44444444444";
        loginTokenService.setTokenOneToOne(userType2, account2, token2, 3600, null);

        loginTokenService.delRelationshipByAccount(userType2, account2, 3600, null);

        Integer reason2 = loginTokenService.getTokenClearType(userType2, token2);
        Assert.assertNull(reason2);

    }

    @Test
    public void delRelationshipByAccount() {

        Integer userType = 1;
        String account = "55555555555";
        String token = "token55555555555";
        Integer tokenClearType = 1;
        loginTokenService.setTokenOneToOne(userType, account, token, 3600, tokenClearType);

        String userAccount = loginTokenService.getAccount(userType, token, true, 3600, true);
        Assert.assertEquals(account, userAccount);

        loginTokenService.delRelationshipByAccount(userType, account, 3600, tokenClearType);

        String userAccount2 = loginTokenService.getAccount(userType, token, true, 3600, true);
        Assert.assertNull(userAccount2);
    }

    @Test
    public void delRelationshipByToken() {

        Integer userType = 1;
        String account = "66666666666";
        String token = "token66666666666";
        Integer tokenClearType = 1;
        loginTokenService.setTokenOneToOne(userType, account, token, 3600, tokenClearType);

        String userAccount = loginTokenService.getAccount(userType, token, true, 3600, true);
        Assert.assertEquals(account, userAccount);

        loginTokenService.delRelationshipByToken(userType, token, true, 3600, tokenClearType);

        String userAccount2 = loginTokenService.getAccount(userType, token, true, 3600, true);
        Assert.assertNull(userAccount2);
    }
}
