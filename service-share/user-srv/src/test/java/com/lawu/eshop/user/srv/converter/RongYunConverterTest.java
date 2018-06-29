package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.srv.bo.RongYunBO;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class RongYunConverterTest {

    @Test
    public void convertDTO() {
        RongYunBO rongYunBO = new RongYunBO();
        rongYunBO.setUserNum("M0001");
        rongYunBO.setNickName("test");
        rongYunBO.setHeadImg("pic");
        RongYunDTO rongYunDTO = RongYunConverter.convertDTO(rongYunBO);
        Assert.assertNotNull(rongYunDTO);
        Assert.assertEquals(rongYunBO.getUserNum(), rongYunDTO.getUserNum());
    }

}
