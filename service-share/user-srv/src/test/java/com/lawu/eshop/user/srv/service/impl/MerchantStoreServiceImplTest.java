package com.lawu.eshop.user.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.user.param.ListMerchantStoreParam;
import com.lawu.eshop.user.param.MerchantStorePlatParam;
import com.lawu.eshop.user.param.StoreIndexParam;
import com.lawu.eshop.user.param.StoreStatisticsParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.UserSrvConfig;
import com.lawu.eshop.user.srv.bo.MerchantAdInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreAdInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreStatusBO;
import com.lawu.eshop.user.srv.bo.NewMerchantStoreBO;
import com.lawu.eshop.user.srv.bo.RecommendFoodBO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.eshop.user.srv.service.MerchantStoreService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class MerchantStoreServiceImplTest {

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Autowired
    private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Autowired
    private UserSrvConfig userSrvConfig;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectMerchantStore() {
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

        MerchantStoreBO merchantStoreBO = merchantStoreService.selectMerchantStore(200L);
        Assert.assertNotNull(merchantStoreBO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateNoReasonReturn() {
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
        storeDO.setIsNoReasonReturn(false);
        merchantStoreDOMapper.insertSelective(storeDO);

        merchantStoreService.updateNoReasonReturn(storeDO.getId());
        MerchantStoreDO merchantStoreDO = merchantStoreDOMapper.selectByPrimaryKey(storeDO.getId());
        Assert.assertNotNull(merchantStoreDO);
        Assert.assertTrue(merchantStoreDO.getIsNoReasonReturn());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantStoreById() {
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

        MerchantStoreBO merchantStoreBO = merchantStoreService.getMerchantStoreById(storeDO.getId());
        Assert.assertNotNull(merchantStoreBO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectAllMerchantStore() {
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

        MerchantStorePlatParam param = new MerchantStorePlatParam();
        Page<MerchantStoreBO> storeBOS = merchantStoreService.selectAllMerchantStore(param);
        Assert.assertNotNull(storeBOS);
        Assert.assertEquals(1, storeBOS.getRecords().size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listMerchantStore() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setStatus(StatusEnum.VALID.getValue());
        merchantDO.setIsFreeze(false);
        merchantDOMapper.insertSelective(merchantDO);
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
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

        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(storeDO.getId());
        profileDO.setMerchantId(merchantDO.getId());
        profileDO.setManageType(DataTransUtil.intToByte(2));
        profileDO.setCertifType(DataTransUtil.intToByte(2));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        ListMerchantStoreParam param = new ListMerchantStoreParam();
        param.setStatus(DataTransUtil.intToByte(1));
        param.setManageType(DataTransUtil.intToByte(2));
        List<MerchantStoreBO> storeBOS = merchantStoreService.listMerchantStore(param);
        Assert.assertNotNull(storeBOS);
        Assert.assertEquals(1, storeBOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateStoreStatisticsById() {
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

        StoreStatisticsParam param = new StoreStatisticsParam();
        param.setAverageScore(new BigDecimal(80));
        merchantStoreService.updateStoreStatisticsById(storeDO.getId(), param);
        MerchantStoreDO merchantStoreDO = merchantStoreDOMapper.selectByPrimaryKey(storeDO.getId());
        Assert.assertNotNull(merchantStoreDO);
        Assert.assertEquals(80, merchantStoreDO.getAverageScore().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void rebuildStoreIndex() {
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

        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(storeDO.getId());
        profileDO.setMerchantId(200L);
        profileDO.setManageType(DataTransUtil.intToByte(2));
        profileDO.setCertifType(DataTransUtil.intToByte(2));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        List<StoreIndexParam> indexParamList = new ArrayList<>();
        StoreIndexParam indexParam = new StoreIndexParam();
        indexParam.setMerchantStoreId(storeDO.getId());
        indexParam.setFavoreInfo("满100减10");
        indexParam.setDiscountPackage("两人豪华午餐");
        indexParam.setDiscountOrdinal(0.9);
        indexParamList.add(indexParam);
        merchantStoreService.rebuildStoreIndex(indexParamList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delInvalidStoreIndex() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(0));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(storeDO.getId());
        profileDO.setMerchantId(200L);
        profileDO.setManageType(DataTransUtil.intToByte(2));
        profileDO.setCertifType(DataTransUtil.intToByte(2));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        merchantStoreService.delInvalidStoreIndex(DelIndexTypeEnum.ALL);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAdMerchantStoreByIds() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
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

        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(storeDO.getId());
        profileDO.setMerchantId(merchantDO.getId());
        profileDO.setManageType(DataTransUtil.intToByte(2));
        profileDO.setCertifType(DataTransUtil.intToByte(2));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        MerchantStoreImageDO imageDO = new MerchantStoreImageDO();
        imageDO.setMerchantId(merchantDO.getId());
        imageDO.setMerchantStoreId(storeDO.getId());
        imageDO.setStatus(true);
        imageDO.setType(DataTransUtil.intToByte(3));
        imageDO.setPath("pic");
        imageDO.setGmtModified(new Date());
        imageDO.setGmtCreate(new Date());
        merchantStoreImageDOMapper.insertSelective(imageDO);

        List<Long> merchantIds = new ArrayList<>();
        merchantIds.add(merchantDO.getId());
        List<MerchantAdInfoBO> merchantAdInfoBOS = merchantStoreService.getAdMerchantStoreByIds(merchantIds);
        Assert.assertNotNull(merchantAdInfoBOS);
        Assert.assertEquals(1, merchantAdInfoBOS.size());
        Assert.assertEquals("pic", merchantAdInfoBOS.get(0).getPath());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void merchantStoreIsExist() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setIsFreeze(false);
        merchantDO.setStatus(StatusEnum.VALID.getValue());
        merchantDOMapper.insertSelective(merchantDO);
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
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

        MerchantStoreStatusBO storeStatusBO = merchantStoreService.merchantStoreIsExist(storeDO.getId());
        Assert.assertNotNull(storeStatusBO);
        Assert.assertTrue(storeStatusBO.isExist());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findAccountAndRegionPathByNum() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum("B0001");
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
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

        MerchantInfoBO merchantInfoBO = merchantStoreService.findAccountAndRegionPathByNum("B0001");
        Assert.assertNotNull(merchantInfoBO);
        Assert.assertEquals(storeDO.getRegionPath(), merchantInfoBO.getRegionPath());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listNewMerchant() {
        List<NewMerchantStoreBO> storeBOS = merchantStoreService.listNewMerchant("44/4403");
        Assert.assertNotNull(storeBOS);
        Assert.assertEquals(0, storeBOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listRecommendFoodConsume() {
        List<RecommendFoodBO> foodBOS = merchantStoreService.listRecommendFoodConsume(10, "44/4403");
        Assert.assertNotNull(foodBOS);
        Assert.assertEquals(0, foodBOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listRecommendFoodComment() {
        List<RecommendFoodBO> foodBOS = merchantStoreService.listRecommendFoodComment(10, "44/4403");
        Assert.assertNotNull(foodBOS);
        Assert.assertEquals(0, foodBOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectMerchantStoreAdInfo() {
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

        MerchantStoreAdInfoBO merchantStoreBO = merchantStoreService.selectMerchantStoreAdInfo(200L);
        Assert.assertNotNull(merchantStoreBO);
    }

}
