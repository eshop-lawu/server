package com.lawu.eshop.user.srv.service.impl;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.MerchantStoreProfileBO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.eshop.user.srv.service.MerchantStoreProfileService;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class MerchantStoreProfileServiceImplTest {

    @Autowired
    private MerchantStoreProfileService merchantStoreProfileService;

    @Autowired
    private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findMerchantStoreInfo() {
        MerchantStoreProfileDO storeProfileDO = new MerchantStoreProfileDO();
        storeProfileDO.setId(300L);
        storeProfileDO.setMerchantId(200L);
        storeProfileDO.setManageType(DataTransUtil.intToByte(1));
        storeProfileDO.setCertifType(DataTransUtil.intToByte(1));
        storeProfileDO.setGmtModified(new Date());
        storeProfileDO.setGmtCreate(new Date());
        merchantStoreProfileDOMapper.insertSelective(storeProfileDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(300L);
        storeImageDO.setStatus(true);
        storeImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val);
        storeImageDO.setPath("pic");
        storeImageDO.setGmtModified(new Date());
        storeImageDO.setGmtCreate(new Date());
        merchantStoreImageDOMapper.insertSelective(storeImageDO);

        MerchantStoreProfileBO storeProfileBO = merchantStoreProfileService.findMerchantStoreInfo(200L);
        Assert.assertNotNull(storeProfileBO);
        Assert.assertEquals("pic", storeProfileBO.getLogoUrl());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getManageType() {
        MerchantStoreProfileDO storeProfileDO = new MerchantStoreProfileDO();
        storeProfileDO.setId(300L);
        storeProfileDO.setMerchantId(200L);
        storeProfileDO.setManageType(DataTransUtil.intToByte(1));
        storeProfileDO.setCertifType(DataTransUtil.intToByte(1));
        storeProfileDO.setGmtModified(new Date());
        storeProfileDO.setGmtCreate(new Date());
        merchantStoreProfileDOMapper.insertSelective(storeProfileDO);

        ManageTypeEnum result = merchantStoreProfileService.getManageType(200L);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getVal() == 1);
    }

}
