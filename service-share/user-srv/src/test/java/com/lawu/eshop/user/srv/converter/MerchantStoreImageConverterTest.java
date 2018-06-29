package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.MerchantStoreImageDTO;
import com.lawu.eshop.user.srv.bo.MerchantStoreImageBO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.utils.DataTransUtil;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class MerchantStoreImageConverterTest {

    @Test
    public void convertBO() {
        List<MerchantStoreImageDO> merchantStoreImageDOS = new ArrayList<>();
        MerchantStoreImageDO merchantStoreImageDO = new MerchantStoreImageDO();
        merchantStoreImageDO.setId(300L);
        merchantStoreImageDO.setPath("pic");
        merchantStoreImageDO.setType(DataTransUtil.intToByte(1));
        merchantStoreImageDOS.add(merchantStoreImageDO);
        List<MerchantStoreImageBO> merchantStoreImageBOS = MerchantStoreImageConverter.convertBO(merchantStoreImageDOS);
        Assert.assertNotNull(merchantStoreImageBOS);
        Assert.assertEquals(merchantStoreImageDO.getId(), merchantStoreImageBOS.get(0).getId());
    }

    @Test
    public void convertDTO() {
        List<MerchantStoreImageBO> merchantStoreImageBOS = new ArrayList<>();
        MerchantStoreImageBO merchantStoreImageBO = new MerchantStoreImageBO();
        merchantStoreImageBO.setPath("pic");
        merchantStoreImageBO.setId(300L);
        merchantStoreImageBO.setType(DataTransUtil.intToByte(1));
        merchantStoreImageBOS.add(merchantStoreImageBO);
        List<MerchantStoreImageDTO> merchantStoreImageDTOS = MerchantStoreImageConverter.convertDTO(merchantStoreImageBOS);
        Assert.assertNotNull(merchantStoreImageDTOS);
        Assert.assertEquals(merchantStoreImageBO.getPath(), merchantStoreImageDTOS.get(0).getPath());
    }

}
