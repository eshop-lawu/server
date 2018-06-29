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

import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.param.ApplyStoreParam;
import com.lawu.eshop.user.param.MerchantStoreParam;
import com.lawu.eshop.user.param.ShoppingOrderFindUserInfoParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.CashUserInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreAuditBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreProfileBO;
import com.lawu.eshop.user.srv.bo.PayOrderStoreInfoBO;
import com.lawu.eshop.user.srv.bo.ShoppingOrderFindMerchantInfoBO;
import com.lawu.eshop.user.srv.bo.ShoppingStoreDetailBO;
import com.lawu.eshop.user.srv.bo.StoreDetailBO;
import com.lawu.eshop.user.srv.bo.StoreSolrInfoBO;
import com.lawu.eshop.user.srv.domain.FansMerchantDO;
import com.lawu.eshop.user.srv.domain.FavoriteMerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreAuditDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.mapper.FansMerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.FavoriteMerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreAuditDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.eshop.user.srv.service.MerchantStoreInfoService;
import com.lawu.utils.DataTransUtil;

import net.sf.json.JSONObject;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class MerchantStoreInfoServiceImplTest {

    @Autowired
    private MerchantStoreInfoService merchantStoreInfoService;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @Autowired
    private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Autowired
    private MerchantStoreAuditDOMapper merchantStoreAuditDOMapper;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Autowired
    private FansMerchantDOMapper fansMerchantDOMapper;

    @Autowired
    private FavoriteMerchantDOMapper favoriteMerchantDOMapper;

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

        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(storeDO.getId());
        profileDO.setMerchantId(200L);
        profileDO.setManageType(DataTransUtil.intToByte(2));
        profileDO.setCertifType(DataTransUtil.intToByte(2));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_LICENSE);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(200L);
        storeAuditDO.setMerchantStoreId(storeDO.getId());
        storeAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(1));
        storeAuditDO.setType(DataTransUtil.intToByte(1));
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        MerchantStoreInfoBO merchantStoreInfoBO = merchantStoreInfoService.selectMerchantStore(storeDO.getId());
        Assert.assertNotNull(merchantStoreInfoBO);
        Assert.assertEquals("pic", merchantStoreInfoBO.getStoreUrl());
        Assert.assertTrue(merchantStoreInfoBO.isAuditSuccess());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantStore() {
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

        MerchantStoreInfoBO merchantStoreInfoBO = merchantStoreInfoService.getMerchantStore(storeDO.getId(), 200L);
        Assert.assertNotNull(merchantStoreInfoBO);
        Assert.assertEquals(storeDO.getName(), merchantStoreInfoBO.getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveMerchantStoreInfo() {
        MerchantStoreParam param = new MerchantStoreParam();
        param.setName("测试店铺");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddress("大冲商务中心");
        param.setLongitude(new BigDecimal(104.23));
        param.setLatitude(new BigDecimal(22.36));
        param.setIndustryPath("10");
        param.setIndustryName("食品");
        param.setIntro("测试店铺介绍");
        param.setPrincipalName("测试人");
        param.setPrincipalMobile("13888888888");
        param.setCompanyName("测试公司");
        param.setRegNumber("test");
        param.setCompanyAddress("大冲花园");
        param.setLicenseIndate(new Date());
        param.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT);
        param.setCertifType(CertifTypeEnum.CERTIF_TYPE_LICENSE);
        param.setOperatorCardId("test");
        param.setOperatorName("测试人");
        param.setStoreUrl("pic");
        param.setEnvironmentUrl("pic");
        param.setIdcardUrl("pic");
        param.setLicenseUrl("pic");
        param.setLogoUrl("pic");
        param.setOtherUrl("pic");
        merchantStoreInfoService.saveMerchantStoreInfo(200L, param);

        List<MerchantStoreDO> merchantStoreDOS = merchantStoreDOMapper.selectByExample(null);
        Assert.assertNotNull(merchantStoreDOS);
        Assert.assertEquals(1, merchantStoreDOS.size());

        List<MerchantStoreProfileDO> storeProfileDOS = merchantStoreProfileDOMapper.selectByExample(null);
        Assert.assertNotNull(storeProfileDOS);
        Assert.assertEquals(1, storeProfileDOS.size());

        List<MerchantStoreImageDO> storeImageDOS = merchantStoreImageDOMapper.selectByExample(null);
        Assert.assertNotNull(storeImageDOS);
        Assert.assertEquals(6, storeImageDOS.size());

        List<MerchantStoreAuditDO> storeAuditDOS = merchantStoreAuditDOMapper.selectByExample(null);
        Assert.assertNotNull(storeAuditDOS);
        Assert.assertEquals(1, storeAuditDOS.size());
        Assert.assertEquals(0, storeAuditDOS.get(0).getStatus().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectStoreInfoByExample() {
        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(300L);
        profileDO.setMerchantId(200L);
        profileDO.setRegNumber("test");
        profileDO.setOperatorCardId("testIdCard");
        profileDO.setManageType(DataTransUtil.intToByte(2));
        profileDO.setCertifType(DataTransUtil.intToByte(2));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        MerchantStoreProfileBO storeProfileBO = merchantStoreInfoService.selectStoreInfoByExample("test", 1);
        Assert.assertNotNull(storeProfileBO);
        Assert.assertEquals("test", storeProfileBO.getRegNumber());

        storeProfileBO = merchantStoreInfoService.selectStoreInfoByExample("testIdCard", 2);
        Assert.assertNotNull(storeProfileBO);
        Assert.assertEquals("testIdCard", storeProfileBO.getOperatorCardId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateMerchantStoreInfo() {
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

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        MerchantStoreParam param = new MerchantStoreParam();
        param.setName("测试店铺");
        param.setRegionPath("44/4403/440303");
        param.setRegionName("广东省深圳市南山区");
        param.setAddress("大冲商务中心");
        param.setLongitude(new BigDecimal(104.23));
        param.setLatitude(new BigDecimal(22.36));
        param.setIndustryPath("10");
        param.setIndustryName("食品");
        param.setIntro("测试店铺介绍");
        param.setPrincipalName("测试人");
        param.setPrincipalMobile("13888888888");
        param.setCompanyName("测试公司");
        param.setRegNumber("test");
        param.setCompanyAddress("大冲花园");
        param.setLicenseIndate(new Date());
        param.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT);
        param.setCertifType(CertifTypeEnum.CERTIF_TYPE_LICENSE);
        param.setOperatorCardId("test");
        param.setOperatorName("测试人");
        param.setStoreUrl("pic");
        param.setEnvironmentUrl("pic");
        param.setIdcardUrl("pic");
        param.setLicenseUrl("pic");
        param.setLogoUrl("pic");
        param.setOtherUrl("pic");
        merchantStoreInfoService.updateMerchantStoreInfo(200L, param, storeDO.getId());
        MerchantStoreDO merchantStoreDO = merchantStoreDOMapper.selectByPrimaryKey(storeDO.getId());
        Assert.assertNotNull(merchantStoreDO);
        Assert.assertEquals(0, merchantStoreDO.getStatus().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectMerchantStoreByMId() {
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

        MerchantStoreInfoBO merchantStoreInfoBO = merchantStoreInfoService.selectMerchantStoreByMId(200L);
        Assert.assertNotNull(merchantStoreInfoBO);
        Assert.assertEquals(1, merchantStoreInfoBO.getStatusEnum().val.intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void shoppingOrderFindUserInfo() {
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

        FansMerchantDO fansMerchantDO = new FansMerchantDO();
        fansMerchantDO.setMerchantId(merchantDO.getId());
        fansMerchantDO.setMemberId(100L);
        fansMerchantDOMapper.insertSelective(fansMerchantDO);

        ShoppingOrderFindUserInfoParam param = new ShoppingOrderFindUserInfoParam();
        List<Long> merchantIdList = new ArrayList<>();
        merchantIdList.add(merchantDO.getId());
        param.setMemberId(100L);
        param.setMerchantIdList(merchantIdList);
        List<ShoppingOrderFindMerchantInfoBO> merchantInfoBOS = merchantStoreInfoService.shoppingOrderFindUserInfo(param);
        Assert.assertNotNull(merchantInfoBOS);
        Assert.assertEquals(1, merchantInfoBOS.size());
        Assert.assertTrue(merchantInfoBOS.get(0).getIsFans());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveMerchantStoreAuditInfo() {
        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_LICENSE);
        merchantStoreParam.setMerchantStoreStatus(MerchantStatusEnum.MERCHANT_STATUS_CANCEL);
        merchantStoreInfoService.saveMerchantStoreAuditInfo(200L, merchantStoreParam, 300L);

        List<MerchantStoreAuditDO> storeAuditDOS = merchantStoreAuditDOMapper.selectByExample(null);
        Assert.assertNotNull(storeAuditDOS);
        Assert.assertEquals(1, storeAuditDOS.size());
        Assert.assertEquals(2, storeAuditDOS.get(0).getType().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getStoreDetailById() {
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

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(merchantDO.getId());
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        FavoriteMerchantDO favoriteMerchantDO = new FavoriteMerchantDO();
        favoriteMerchantDO.setManageType(ManageTypeEnum.ENTITY.getVal());
        favoriteMerchantDO.setMerchantId(merchantDO.getId());
        favoriteMerchantDO.setMemberId(100L);
        favoriteMerchantDOMapper.insertSelective(favoriteMerchantDO);

        StoreDetailBO storeDetailBO = merchantStoreInfoService.getStoreDetailById(storeDO.getId(), 100L);
        Assert.assertNotNull(storeDetailBO);
        Assert.assertTrue(storeDetailBO.getFavorite());
        Assert.assertEquals(1, storeDetailBO.getPicCount().intValue());
        Assert.assertEquals("pic", storeDetailBO.getStorePic());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findCashUserInfo() {
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

        CashUserInfoBO cashUserInfoBO = merchantStoreInfoService.findCashUserInfo(200L);
        Assert.assertNotNull(cashUserInfoBO);
        Assert.assertEquals(44, cashUserInfoBO.getProvinceId().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findStoreNameAndImgByMerchantId() {
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

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        MerchantStoreInfoBO merchantStoreInfoBO = merchantStoreInfoService.findStoreNameAndImgByMerchantId(200L);
        Assert.assertNotNull(merchantStoreInfoBO);
        Assert.assertEquals("pic", merchantStoreInfoBO.getStoreUrl());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findStoreAuditInfo() {
        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_LICENSE);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(200L);
        storeAuditDO.setMerchantStoreId(300L);
        storeAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(1));
        storeAuditDO.setType(DataTransUtil.intToByte(1));
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        MerchantStoreAuditBO merchantStoreAuditBO = merchantStoreInfoService.findStoreAuditInfo(200L);
        Assert.assertNotNull(merchantStoreAuditBO);
        Assert.assertEquals(1, merchantStoreAuditBO.getStatus().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void addMerchantStoreBuyNums() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(104.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIntro("店铺介绍");
        storeDO.setBuyNumbers(0);
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        merchantStoreInfoService.addMerchantStoreBuyNums(200L);
        MerchantStoreDO merchantStoreDO = merchantStoreDOMapper.selectByPrimaryKey(storeDO.getId());
        Assert.assertNotNull(merchantStoreDO);
        Assert.assertEquals(1, merchantStoreDO.getBuyNumbers().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void addMerchantStoreCommentsCount() {
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(200L);
        storeDO.setBuyNumbers(0);
        storeDO.setCommentsCount(0);
        storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        merchantStoreInfoService.addMerchantStoreCommentsCount(200L,10);
        MerchantStoreDO merchantStoreDO = merchantStoreDOMapper.selectByPrimaryKey(storeDO.getId());
        Assert.assertNotNull(merchantStoreDO);
        Assert.assertEquals(10, merchantStoreDO.getCommentsCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateMerchantStoreStatus() {
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

        merchantStoreInfoService.updateMerchantStoreStatus(200L, DataTransUtil.intToByte(0));
        MerchantStoreDO merchantStoreDO = merchantStoreDOMapper.selectByPrimaryKey(storeDO.getId());
        Assert.assertNotNull(merchantStoreDO);
        Assert.assertEquals(0, merchantStoreDO.getStatus().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void applyPhysicalStore() {
        ApplyStoreParam param = new ApplyStoreParam();
        param.setLogoUrl("pic");
        merchantStoreInfoService.applyPhysicalStore(200L, 300L, param);

        List<MerchantStoreAuditDO> storeAuditDOS = merchantStoreAuditDOMapper.selectByExample(null);
        Assert.assertNotNull(storeAuditDOS);
        Assert.assertEquals(1, storeAuditDOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getShoppingStoreDetailById() {
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

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        ShoppingStoreDetailBO storeDetailBO = merchantStoreInfoService.getShoppingStoreDetailById(storeDO.getId(), 100L);
        Assert.assertNotNull(storeDetailBO);
        Assert.assertEquals(0, storeDetailBO.getFansCount().intValue());
        Assert.assertTrue(!storeDetailBO.getFans());
        Assert.assertTrue(!storeDetailBO.getFavorite());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPayOrderStoreInfo() {
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

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        List<Long> merchantIds = new ArrayList<>();
        merchantIds.add(200L);
        List<PayOrderStoreInfoBO> storeInfoBOS = merchantStoreInfoService.getPayOrderStoreInfo(merchantIds);
        Assert.assertNotNull(storeInfoBOS);
        Assert.assertEquals(1, storeInfoBOS.size());
        Assert.assertEquals(storeDO.getName(), storeInfoBOS.get(0).getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantStoreByIds() {
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

        List<Long> merchantStoreIds = new ArrayList<>();
        merchantStoreIds.add(storeDO.getId());
        List<StoreSolrInfoBO> storeSolrInfoBOS = merchantStoreInfoService.getMerchantStoreByIds(merchantStoreIds);
        Assert.assertNotNull(storeSolrInfoBOS);
        Assert.assertEquals(1, storeSolrInfoBOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPayOrderDetailStoreInfo() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setStatus(StatusEnum.VALID.getValue());
        merchantDO.setIsFreeze(false);
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

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(merchantDO.getId());
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        PayOrderStoreInfoBO payOrderStoreInfoBO = merchantStoreInfoService.getPayOrderDetailStoreInfo(merchantDO.getId());
        Assert.assertNotNull(payOrderStoreInfoBO);
        Assert.assertEquals(storeDO.getName(), payOrderStoreInfoBO.getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPayOrderMerchantInfo() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setIsFreeze(false);
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

        MerchantInfoBO merchantInfoBO = merchantStoreInfoService.getPayOrderMerchantInfo(merchantDO.getId());
        Assert.assertNotNull(merchantInfoBO);
        Assert.assertEquals(merchantDO.getAccount(), merchantInfoBO.getAccount());
    }

}
