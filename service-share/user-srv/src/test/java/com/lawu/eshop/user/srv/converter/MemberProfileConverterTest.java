package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.MemberMineInfoDTO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MemberProfileBO;
import com.lawu.eshop.user.srv.domain.MemberProfileDO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class MemberProfileConverterTest {

    @Test
    public void convert() {
        MemberProfileDO memberProfileDO = new MemberProfileDO();
        memberProfileDO.setGmtCreate(new Date());
        memberProfileDO.setGmtModified(new Date());
        memberProfileDO.setId(100L);
        memberProfileDO.setInviteMemberCount(10);
        memberProfileDO.setInviteMemberCount2(10);
        memberProfileDO.setInviteMemberCount3(10);
        memberProfileDO.setInviteMerchantCount(10);
        memberProfileDO.setInviteMerchantCount2(10);
        memberProfileDO.setInviteMerchantCount3(10);
        MemberProfileBO rtn = MemberProfileConverter.convert(memberProfileDO);
        Assert.assertNotNull(rtn);
        Assert.assertEquals(memberProfileDO.getGmtCreate(), rtn.getGmtCreate());
    }

    @Test
    public void convertDTO() {
        MemberProfileBO memberProfileBO = new MemberProfileBO();
        memberProfileBO.setInviteMemberCount(10);
        memberProfileBO.setInviteMerchantCount(10);

        MemberBO memberBO = new MemberBO();
        memberBO.setHeadimg("pic");
        memberBO.setLevel(1);
        memberBO.setNickname("test");
        MemberMineInfoDTO rtn = MemberProfileConverter.convert(memberProfileBO, memberBO);
        Assert.assertNotNull(rtn);
        Assert.assertEquals(memberProfileBO.getInviteMemberCount(), rtn.getInviteMemberCount());
    }

}
