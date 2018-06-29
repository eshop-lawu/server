package com.lawu.eshop.user.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.MerchantStoreImageBO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.service.MerchantStoreImageService;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class MerchantStoreImageServiceImplTest {

    @Autowired
    private MerchantStoreImageService merchantStoreImageService;

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listMerchantStoreImageByType() {
        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(300L);
        storeImageDO.setStatus(true);
        storeImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_STORE.val);
        storeImageDO.setPath("pic");
        storeImageDO.setGmtModified(new Date());
        storeImageDO.setGmtCreate(new Date());
        merchantStoreImageDOMapper.insertSelective(storeImageDO);

        List<MerchantStoreImageBO> storeImageBOS = merchantStoreImageService.listMerchantStoreImageByType(200L, MerchantStoreImageEnum.STORE_IMAGE_STORE);
        Assert.assertNotNull(storeImageBOS);
        Assert.assertEquals(1, storeImageBOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectLogoPath() {
        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(300L);
        storeImageDO.setStatus(true);
        storeImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val);
        storeImageDO.setPath("pic");
        storeImageDO.setGmtModified(new Date());
        storeImageDO.setGmtCreate(new Date());
        merchantStoreImageDOMapper.insertSelective(storeImageDO);

        String result = merchantStoreImageService.selectLogoPath(200L);
        Assert.assertNotNull(result);
        Assert.assertEquals("pic", result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectLogoUrlByStoreId() {
        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(300L);
        storeImageDO.setStatus(true);
        storeImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val);
        storeImageDO.setPath("pic");
        storeImageDO.setGmtModified(new Date());
        storeImageDO.setGmtCreate(new Date());
        merchantStoreImageDOMapper.insertSelective(storeImageDO);

        String result = merchantStoreImageService.selectLogoUrlByStoreId(300L);
        Assert.assertNotNull(result);
        Assert.assertEquals("pic", result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getStoreUrlByStoreId() {
        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(300L);
        storeImageDO.setStatus(true);
        storeImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_STORE.val);
        storeImageDO.setPath("pic");
        storeImageDO.setGmtModified(new Date());
        storeImageDO.setGmtCreate(new Date());
        merchantStoreImageDOMapper.insertSelective(storeImageDO);

        String result = merchantStoreImageService.getStoreUrlByStoreId(300L);
        Assert.assertNotNull(result);
        Assert.assertEquals("pic", result);
    }

}
