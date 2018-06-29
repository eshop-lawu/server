package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.InviterDTO;
import com.lawu.eshop.user.srv.bo.InviterBO;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class InviterConverterTest {

    @Test
    public void convertBO() {
        MemberDO memberDO = new MemberDO();
        memberDO.setInviterId(100L);
        memberDO.setNum("M0001");
        memberDO.setName("test");
        InviterBO inviterBO = InviterConverter.convertBO(memberDO);
        Assert.assertNotNull(inviterBO);
        Assert.assertEquals(memberDO.getId(), inviterBO.getInviterId());
    }

    @Test
    public void convertBO1() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setId(100L);
        merchantDO.setNum("M0001");
        InviterBO inviterBO = InviterConverter.convertBO(merchantDO);
        Assert.assertNotNull(inviterBO);
        Assert.assertEquals(merchantDO.getId(), inviterBO.getInviterId());
    }

    @Test
    public void convertBO2() {
        MerchantStoreDO merchantStoreDO = new MerchantStoreDO();
        merchantStoreDO.setMerchantId(200L);
        merchantStoreDO.setName("B0001");
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum("M0001");
        InviterBO inviterBO = InviterConverter.convertBO(merchantStoreDO, merchantDO);
        Assert.assertNotNull(inviterBO);
        Assert.assertEquals(merchantStoreDO.getMerchantId(), inviterBO.getInviterId());
    }

    @Test
    public void convertDTO() {
        InviterBO inviterBO = new InviterBO();
        inviterBO.setInviterId(100L);
        inviterBO.setUserNum("M0001");
        inviterBO.setInviterName("test");
        InviterDTO inviterDTO = InviterConverter.convertDTO(inviterBO);
        Assert.assertNotNull(inviterDTO);
        Assert.assertEquals(inviterBO.getInviterId(), inviterDTO.getInviterId());
    }

}
