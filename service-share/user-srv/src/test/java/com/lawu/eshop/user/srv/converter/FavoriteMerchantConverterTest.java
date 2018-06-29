package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.FavoriteMerchantDTO;
import com.lawu.eshop.user.srv.bo.FavoriteMerchantBO;
import com.lawu.eshop.user.srv.domain.extend.FavoriteMerchantDOView;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class FavoriteMerchantConverterTest {

    @Test
    public void convertListBO() {
        FavoriteMerchantDOView favoriteMerchantDOView = new FavoriteMerchantDOView();
        favoriteMerchantDOView.setMerchantId(200L);
        favoriteMerchantDOView.setName("test");
        favoriteMerchantDOView.setIndustryName("10");
        favoriteMerchantDOView.setFeedbackRate(new BigDecimal(50));
        favoriteMerchantDOView.setPath("pic");
        favoriteMerchantDOView.setMerchantStoreId(300L);
        favoriteMerchantDOView.setCountFs(10);
        FavoriteMerchantBO favoriteMerchantBO = FavoriteMerchantConverter.convertListBO(favoriteMerchantDOView);
        Assert.assertNotNull(favoriteMerchantBO);
        Assert.assertEquals(favoriteMerchantDOView.getMerchantId(), favoriteMerchantBO.getMerchantId());
    }

    @Test
    public void convertDTO() {
        FavoriteMerchantBO favoriteMerchantBO = new FavoriteMerchantBO();
        favoriteMerchantBO.setMerchantId(200L);
        favoriteMerchantBO.setName("test");
        favoriteMerchantBO.setFeedbackRate(new BigDecimal(50));
        favoriteMerchantBO.setIndustryName("10");
        favoriteMerchantBO.setPath("pic");
        favoriteMerchantBO.setDistance(10);
        favoriteMerchantBO.setMerchantStoreId(300L);
        favoriteMerchantBO.setFansCount(10);
        FavoriteMerchantDTO favoriteMerchantDTO = FavoriteMerchantConverter.convertDTO(favoriteMerchantBO);
        Assert.assertNotNull(favoriteMerchantDTO);
        Assert.assertEquals(favoriteMerchantBO.getMerchantId(), favoriteMerchantDTO.getMerchantId());
    }

}
