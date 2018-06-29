package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.MerchantProfileDTO;
import com.lawu.eshop.user.srv.bo.MerchantProfileBO;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class MerchantProfileConverterTest {

    @Test
    public void convertBO() {
        MerchantProfileDO merchantProfileDO = new MerchantProfileDO();
        merchantProfileDO.setJdUrl("url");
        merchantProfileDO.setTaobaoUrl("url");
        merchantProfileDO.setTmallUrl("url");
        merchantProfileDO.setWebsiteUrl("url");
        MerchantProfileBO merchantProfileBO = MerchantProfileConverter.convertBO(merchantProfileDO);
        Assert.assertNotNull(merchantProfileBO);
        Assert.assertEquals(merchantProfileDO.getJdUrl(), merchantProfileBO.getJdUrl());
    }

    @Test
    public void convertDTO() {
        MerchantProfileBO merchantProfileBO = new MerchantProfileBO();
        merchantProfileBO.setJdUrl("url");
        merchantProfileBO.setTaobaoUrl("url");
        merchantProfileBO.setTmallUrl("url");
        merchantProfileBO.setWebsiteUrl("url");
        MerchantProfileDTO merchantProfileDTO = MerchantProfileConverter.convertDTO(merchantProfileBO);
        Assert.assertNotNull(merchantProfileDTO);
        Assert.assertEquals(merchantProfileBO.getJdUrl(), merchantProfileDTO.getJdUrl());
    }

}
