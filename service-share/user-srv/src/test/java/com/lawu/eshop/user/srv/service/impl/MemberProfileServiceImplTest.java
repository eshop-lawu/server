package com.lawu.eshop.user.srv.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.MemberProfileBO;
import com.lawu.eshop.user.srv.domain.MemberProfileDO;
import com.lawu.eshop.user.srv.mapper.MemberProfileDOMapper;
import com.lawu.eshop.user.srv.service.MemberProfileService;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class MemberProfileServiceImplTest {

    @Autowired
    private MemberProfileService memberProfileService;

    @Autowired
    private MemberProfileDOMapper memberProfileDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMemberCount() {
        MemberProfileDO memberProfileDO = new MemberProfileDO();
        memberProfileDO.setId(100L);
        memberProfileDOMapper.insertSelective(memberProfileDO);

        int result = memberProfileService.getMemberCount(100L);
        Assert.assertEquals(0, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantCount() {
        MemberProfileDO memberProfileDO = new MemberProfileDO();
        memberProfileDO.setId(200L);
        memberProfileDOMapper.insertSelective(memberProfileDO);

        int result = memberProfileService.getMerchantCount(200L);
        Assert.assertEquals(0, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void get() {
        MemberProfileDO memberProfileDO = new MemberProfileDO();
        memberProfileDO.setId(100L);
        memberProfileDOMapper.insertSelective(memberProfileDO);

        MemberProfileBO memberProfileBO = memberProfileService.get(100L);
        Assert.assertNotNull(memberProfileBO);
    }

}
