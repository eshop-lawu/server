package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MerchantBO;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class LoginUserConverterTest {

    @Test
    public void convert() {
        MemberBO memberBO = new MemberBO();
        memberBO.setId(100L);
        memberBO.setNum("M0001");
        memberBO.setAccount("1366666666");
        memberBO.setRyToken("8888");
        LoginUserDTO loginUserDTO = LoginUserConverter.convert(memberBO);
        Assert.assertNotNull(loginUserDTO);
        Assert.assertEquals(memberBO.getId(), loginUserDTO.getId());
    }

    @Test
    public void convert1() {
        MerchantBO merchantBO = new MerchantBO();
        merchantBO.setId(200L);
        merchantBO.setNum("B0001");
        merchantBO.setAccount("13888888888");
        merchantBO.setRyToken("8888");
        LoginUserDTO loginUserDTO = LoginUserConverter.convert(merchantBO);
        Assert.assertNotNull(loginUserDTO);
        Assert.assertEquals(merchantBO.getId(), loginUserDTO.getId());
    }

}
