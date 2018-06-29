package com.lawu.eshop.user.srv.service.impl;

import java.math.BigDecimal;
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

import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.param.FavoriteMerchantParam;
import com.lawu.eshop.user.param.FavoriteStoreParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.FavoriteMerchantBO;
import com.lawu.eshop.user.srv.domain.FavoriteMerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.mapper.FavoriteMerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.service.FavoriteMerchantService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class FavoriteMerchantServiceImplTest {

    @Autowired
    private FavoriteMerchantService favoriteMerchantService;

    @Autowired
    private FavoriteMerchantDOMapper favoriteMerchantDOMapper;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setFavoriteNumber(0);
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        FavoriteStoreParam param = new FavoriteStoreParam();
        param.setManageTypeEnum(ManageTypeEnum.COMMON);
        param.setMerchantId(200L);
        favoriteMerchantService.save(100L, param);

        List<FavoriteMerchantDO> favoriteMerchantDOList = favoriteMerchantDOMapper.selectByExample(null);
        Assert.assertNotNull(favoriteMerchantDOList);
        Assert.assertEquals(1, favoriteMerchantDOList.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMyFavoriteMerchant() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setFavoriteNumber(0);
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(300L);
        storeImageDO.setStatus(true);
        storeImageDO.setType(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val);
        storeImageDO.setPath("pic");
        storeImageDO.setGmtModified(new Date());
        storeImageDO.setGmtCreate(new Date());
        merchantStoreImageDOMapper.insertSelective(storeImageDO);

        FavoriteStoreParam param = new FavoriteStoreParam();
        param.setManageTypeEnum(ManageTypeEnum.COMMON);
        param.setMerchantId(200L);
        favoriteMerchantService.save(100L, param);

        FavoriteMerchantParam merchantParam = new FavoriteMerchantParam();
        merchantParam.setManageTypeEnum(ManageTypeEnum.COMMON);
        merchantParam.setLongitude(104.23);
        merchantParam.setLatitude(22.36);
        Page<FavoriteMerchantBO> favoriteMerchantBOPage = favoriteMerchantService.getMyFavoriteMerchant(100L, merchantParam);
        Assert.assertNotNull(favoriteMerchantBOPage.getRecords());
        Assert.assertEquals(1, favoriteMerchantBOPage.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void remove() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setFavoriteNumber(0);
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        FavoriteStoreParam param = new FavoriteStoreParam();
        param.setManageTypeEnum(ManageTypeEnum.COMMON);
        param.setMerchantId(200L);
        favoriteMerchantService.save(100L, param);

        FavoriteStoreParam storeParam = new FavoriteStoreParam();
        storeParam.setManageTypeEnum(ManageTypeEnum.COMMON);
        storeParam.setMerchantId(200L);
        int result = favoriteMerchantService.remove(storeParam, 100L);
        Assert.assertEquals(1, result);

        List<FavoriteMerchantDO> favoriteMerchantDOList = favoriteMerchantDOMapper.selectByExample(null);
        Assert.assertNotNull(favoriteMerchantDOList);
        Assert.assertEquals(0, favoriteMerchantDOList.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void get() {
        FavoriteStoreParam param = new FavoriteStoreParam();
        param.setManageTypeEnum(ManageTypeEnum.COMMON);
        param.setMerchantId(200L);
        favoriteMerchantService.save(100L, param);

        FavoriteStoreParam storeParam = new FavoriteStoreParam();
        storeParam.setManageTypeEnum(ManageTypeEnum.COMMON);
        storeParam.setMerchantId(200L);
        boolean result = favoriteMerchantService.get(100L, storeParam);
        Assert.assertTrue(result);
    }

}
