package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.MerchantInfoDTO;
import com.lawu.eshop.user.param.MerchantProfileParam;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantProfileBO;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class MerchantInfoConverterTest {

    @Test
    public void convertBO() {
        MerchantProfileDO merchantProfileDO = new MerchantProfileDO();
        merchantProfileDO.setId(100L);
        merchantProfileDO.setInviteMemberCount(10);
        merchantProfileDO.setInviteMemberCount2(10);
        merchantProfileDO.setInviteMemberCount3(10);
        merchantProfileDO.setInviteMerchantCount(10);
        merchantProfileDO.setInviteMerchantCount2(10);
        merchantProfileDO.setInviteMerchantCount3(10);
        MerchantProfileBO merchantProfileBO = MerchantInfoConverter.convertBO(merchantProfileDO);
        Assert.assertNotNull(merchantProfileBO);
        Assert.assertEquals(merchantProfileDO.getId(), merchantProfileBO.getId());
    }

    @Test
    public void paramConvertDO() {
        MerchantProfileParam merchantProfileParam = new MerchantProfileParam();
        merchantProfileParam.setJdUrl("url");
        merchantProfileParam.setTaobaoUrl("purl");
        merchantProfileParam.setTmallUrl("url");
        merchantProfileParam.setWebsiteUrl("url");
        MerchantProfileDO merchantProfileDO = MerchantInfoConverter.paramConvertDO(merchantProfileParam);
        Assert.assertNotNull(merchantProfileDO);
        Assert.assertEquals(merchantProfileParam.getJdUrl(), merchantProfileDO.getJdUrl());
    }

    @Test
    public void coverConverDTO() {
        MerchantProfileBO merchantProfileBO = new MerchantProfileBO();
        merchantProfileBO.setInviteMemberCount(10);
        merchantProfileBO.setInviteMerchantCount(10);

        MerchantInfoBO merchantInfoBO = new MerchantInfoBO();
        merchantInfoBO.setAccount("13666666666");
        merchantInfoBO.setHeadimg("pic");
        MerchantInfoDTO merchantProfileDTO = MerchantInfoConverter.coverConverDTO(merchantProfileBO, merchantInfoBO);
        Assert.assertNotNull(merchantProfileDTO);
        Assert.assertEquals(merchantProfileBO.getInviteMemberCount(), merchantProfileDTO.getInviteMemberCount());
    }

}
