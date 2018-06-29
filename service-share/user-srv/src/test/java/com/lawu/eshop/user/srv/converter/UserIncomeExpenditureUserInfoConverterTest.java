package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.UserIncomeExpenditureUserInfoDTO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MerchantBO;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class UserIncomeExpenditureUserInfoConverterTest {

    @Test
    public void convert() {
        MemberBO memberBO = new MemberBO();
        memberBO.setAccount("13666666666");
        memberBO.setNum("M0001");
        UserIncomeExpenditureUserInfoDTO rtn = UserIncomeExpenditureUserInfoConverter.convert(memberBO);
        Assert.assertNotNull(rtn);
        Assert.assertEquals(memberBO.getAccount(), rtn.getAccount());
    }

    @Test
    public void convert1() {
        MerchantBO merchantBO = new MerchantBO();
        merchantBO.setAccount("13888888888");
        merchantBO.setNum("B0001");
        UserIncomeExpenditureUserInfoDTO rtn = UserIncomeExpenditureUserInfoConverter.convert(merchantBO);
        Assert.assertNotNull(rtn);
        Assert.assertEquals(merchantBO.getAccount(), rtn.getAccount());
    }

}
