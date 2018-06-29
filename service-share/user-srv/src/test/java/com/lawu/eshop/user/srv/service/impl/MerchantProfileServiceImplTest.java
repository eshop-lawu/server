package com.lawu.eshop.user.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.param.MerchantProfileParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.MerchantInfoFromInviteFansBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoFromPublishAdBO;
import com.lawu.eshop.user.srv.bo.MerchantProfileBO;
import com.lawu.eshop.user.srv.bo.MerchantSizeLinkBO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.service.MerchantProfileService;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class MerchantProfileServiceImplTest {

    @Autowired
    private MerchantProfileService merchantProfileService;

    @Autowired
    private MerchantProfileDOMapper merchantProfileDOMapper;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateMerchantSizeLink() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化商家扩展信息
        MerchantProfileDO profileDO = new MerchantProfileDO();
        profileDO.setId(merchantDO.getId());
        merchantProfileDOMapper.insertSelective(profileDO);

        MerchantProfileParam param = new MerchantProfileParam();
        param.setJdUrl("pic");
        merchantProfileService.updateMerchantSizeLink(param, profileDO.getId());
        MerchantProfileDO merchantProfileDO = merchantProfileDOMapper.selectByPrimaryKey(profileDO.getId());
        Assert.assertNotNull(merchantProfileDO);
        Assert.assertTrue("pic".equals(merchantProfileDO.getJdUrl()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findMerchantProfileInfo() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化商家扩展信息
        MerchantProfileDO profileDO = new MerchantProfileDO();
        profileDO.setId(merchantDO.getId());
        merchantProfileDOMapper.insertSelective(profileDO);

        MerchantProfileBO merchantProfileBO = merchantProfileService.findMerchantProfileInfo(profileDO.getId());
        Assert.assertNotNull(merchantProfileBO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantSizeLink() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化商家扩展信息
        MerchantProfileDO profileDO = new MerchantProfileDO();
        profileDO.setId(merchantDO.getId());
        merchantProfileDOMapper.insertSelective(profileDO);

        MerchantSizeLinkBO merchantSizeLinkBO = merchantProfileService.getMerchantSizeLink(profileDO.getId());
        Assert.assertNotNull(merchantSizeLinkBO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantProfile() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化商家扩展信息
        MerchantProfileDO profileDO = new MerchantProfileDO();
        profileDO.setId(merchantDO.getId());
        merchantProfileDOMapper.insertSelective(profileDO);

        MerchantProfileBO merchantProfileBO = merchantProfileService.getMerchantProfile(profileDO.getId());
        Assert.assertNotNull(merchantProfileBO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantInfoFromPublishAd() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantProfileDO profileDO = new MerchantProfileDO();
        profileDO.setId(200L);
        merchantProfileDOMapper.insertSelective(profileDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        storeImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val);
        merchantStoreImageDOMapper.insertSelective(storeImageDO);

        MerchantInfoFromPublishAdBO publishAdBO = merchantProfileService.getMerchantInfoFromPublishAd(200L);
        Assert.assertNotNull(publishAdBO);
        Assert.assertEquals(storeImageDO.getPath(), publishAdBO.getLogoUrl());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantInfoFromInviteFans() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantInfoFromInviteFansBO inviteFansBO = merchantProfileService.getMerchantInfoFromInviteFans(200L);
        Assert.assertNotNull(inviteFansBO);
        Assert.assertEquals(storeDO.getId(), inviteFansBO.getMerchantStoreId());
    }

}
