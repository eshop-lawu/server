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
import com.lawu.eshop.user.constants.MerchantAuditStatusEnum;
import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.dto.param.MerchantAuditTypeEnum;
import com.lawu.eshop.user.param.ListStoreAuditParam;
import com.lawu.eshop.user.param.MerchantAuditParam;
import com.lawu.eshop.user.param.MerchantStoreParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.MerchantStoreAuditBO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreAuditDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreAuditDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.eshop.user.srv.service.MerchantAuditService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DataTransUtil;

import net.sf.json.JSONObject;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class MerchantAuditServiceImplTest {

    @Autowired
    private MerchantAuditService merchantAuditService;

    @Autowired
    private MerchantStoreAuditDOMapper merchantStoreAuditDOMapper;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Autowired
    private MerchantProfileDOMapper merchantProfileDOMapper;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @Autowired
    private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateMerchantAudit() {
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

        //初始化门店
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(114.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIndustryPath("10");
        storeDO.setIndustryName("食品");
        storeDO.setIntro("测试店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(0));
        //storeDO.setStatus(DataTransUtil.intToByte(1));
        storeDO.setIsNoReasonReturn(true);
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        //初始化门店扩展信息
        MerchantStoreProfileDO storeProfileDO = new MerchantStoreProfileDO();
        storeProfileDO.setId(storeDO.getId());
        storeProfileDO.setMerchantId(merchantDO.getId());
        storeProfileDO.setManageType(DataTransUtil.intToByte(1));
        storeProfileDO.setCertifType(DataTransUtil.intToByte(1));
        storeProfileDO.setGmtModified(new Date());
        storeProfileDO.setGmtCreate(new Date());
        merchantStoreProfileDOMapper.insertSelective(storeProfileDO);

        //初始化门店图片
        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(merchantDO.getId());
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setStatus(true);
        storeImageDO.setType(DataTransUtil.intToByte(1));
        storeImageDO.setPath("pic");
        storeImageDO.setGmtModified(new Date());
        storeImageDO.setGmtCreate(new Date());
        merchantStoreImageDOMapper.insertSelective(storeImageDO);

        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(merchantDO.getId());
        storeAuditDO.setMerchantStoreId(storeDO.getId());
        storeAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(0));
        storeAuditDO.setType(DataTransUtil.intToByte(2));
        //storeAuditDO.setType(DataTransUtil.intToByte(1));
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        MerchantAuditParam param = new MerchantAuditParam();
        param.setAuditStatusEnum(MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_CHECKED);
        param.setAuditorId(10);
        param.setMerchantStoreId(storeDO.getId());
        param.setTypeEnum(MerchantAuditTypeEnum.AUDIT_TYPE_EDIT_INFO);
        //param.setTypeEnum(MerchantAuditTypeEnum.AUDIT_TYPE_STORE);
        merchantAuditService.updateMerchantAudit(storeAuditDO.getId(), param);

        MerchantStoreDO merchantStoreDO = merchantStoreDOMapper.selectByPrimaryKey(storeDO.getId());
        Assert.assertNotNull(merchantStoreDO);
        Assert.assertTrue(merchantStoreDO.getStatus() == 1);

        MerchantStoreProfileDO merchantStoreProfileDO = merchantStoreProfileDOMapper.selectByPrimaryKey(storeDO.getId());
        Assert.assertNotNull(merchantStoreProfileDO);
        Assert.assertTrue(merchantStoreProfileDO.getManageType() == 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantAuditInfo() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化门店
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(114.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIndustryPath("10");
        storeDO.setIndustryName("食品");
        storeDO.setIntro("测试店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(0));
        storeDO.setIsNoReasonReturn(true);
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(merchantDO.getId());
        storeAuditDO.setMerchantStoreId(storeDO.getId());
        storeAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(0));
        storeAuditDO.setType(DataTransUtil.intToByte(2));
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        MerchantStoreAuditBO merchantStoreAuditBO = merchantAuditService.getMerchantAuditInfo(merchantDO.getId());
        Assert.assertNotNull(merchantStoreAuditBO);
        Assert.assertTrue(merchantStoreAuditBO.getStatus() == 0);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantAuditInfoByUncheck() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化门店
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(114.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIndustryPath("10");
        storeDO.setIndustryName("食品");
        storeDO.setIntro("测试店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(0));
        storeDO.setIsNoReasonReturn(true);
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(merchantDO.getId());
        storeAuditDO.setMerchantStoreId(storeDO.getId());
        storeAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(0));
        storeAuditDO.setType(MerchantAuditTypeEnum.AUDIT_TYPE_EDIT_INFO.val);
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        MerchantStoreAuditBO merchantStoreAuditBO = merchantAuditService.getMerchantAuditInfoByUncheck(merchantDO.getId(), storeAuditDO.getStatus());
        Assert.assertNotNull(merchantStoreAuditBO);
        Assert.assertTrue(merchantStoreAuditBO.getStatus() == 0);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listAllStoreAudit() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化门店
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(114.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIndustryPath("10");
        storeDO.setIndustryName("食品");
        storeDO.setIntro("测试店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(0));
        storeDO.setIsNoReasonReturn(true);
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(merchantDO.getId());
        storeAuditDO.setMerchantStoreId(storeDO.getId());
        storeAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(0));
        storeAuditDO.setType(DataTransUtil.intToByte(2));
        storeAuditDO.setIsShow(true);
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        ListStoreAuditParam param = new ListStoreAuditParam();
        param.setSortName("gmtCreat");
        param.setSortOrder("desc");
        param.setStatusEnum(MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_UNCHECK);
        param.setTypeEnum(MerchantAuditTypeEnum.AUDIT_TYPE_EDIT_INFO);
        Page<MerchantStoreAuditBO> storeAuditBOPage = merchantAuditService.listAllStoreAudit(param);
        Assert.assertNotNull(storeAuditBOPage.getRecords());
        Assert.assertEquals(1, storeAuditBOPage.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantStoreAuditById() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化门店
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(114.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIndustryPath("10");
        storeDO.setIndustryName("食品");
        storeDO.setIntro("测试店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(0));
        storeDO.setIsNoReasonReturn(true);
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(merchantDO.getId());
        storeAuditDO.setMerchantStoreId(storeDO.getId());
        storeAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(0));
        storeAuditDO.setType(DataTransUtil.intToByte(2));
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);


        MerchantStoreAuditBO merchantStoreAuditBO = merchantAuditService.getMerchantStoreAuditById(storeAuditDO.getId());
        Assert.assertNotNull(merchantStoreAuditBO);
        Assert.assertTrue(merchantStoreAuditBO.getStatus() == 0);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantStoreAudit() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化门店
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(114.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIndustryPath("10");
        storeDO.setIndustryName("食品");
        storeDO.setIntro("测试店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(0));
        storeDO.setIsNoReasonReturn(true);
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(merchantDO.getId());
        storeAuditDO.setMerchantStoreId(storeDO.getId());
        storeAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(0));
        storeAuditDO.setType(DataTransUtil.intToByte(2));
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        MerchantStoreAuditBO merchantStoreAuditBO = merchantAuditService.getMerchantStoreAudit(storeAuditDO.getId(), storeDO.getId());
        Assert.assertNotNull(merchantStoreAuditBO);
        Assert.assertTrue(merchantStoreAuditBO.getStatus() == 0);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void setAuditInfoShow() {
        //初始化商家
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //初始化门店
        MerchantStoreDO storeDO = new MerchantStoreDO();
        storeDO.setMerchantId(merchantDO.getId());
        storeDO.setName("测试店铺");
        storeDO.setRegionPath("44/4403/440303");
        storeDO.setRegionName("广东省深圳市南山区");
        storeDO.setAddress("大冲商务中心");
        storeDO.setLongitude(new BigDecimal(114.23));
        storeDO.setLatitude(new BigDecimal(22.36));
        storeDO.setIndustryPath("10");
        storeDO.setIndustryName("食品");
        storeDO.setIntro("测试店铺介绍");
        storeDO.setStatus(DataTransUtil.intToByte(0));
        storeDO.setIsNoReasonReturn(true);
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(merchantDO.getId());
        storeAuditDO.setMerchantStoreId(storeDO.getId());
        storeAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(0));
        storeAuditDO.setType(DataTransUtil.intToByte(2));
        storeAuditDO.setIsShow(false);
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        merchantAuditService.setAuditInfoShow(merchantDO.getId());
        MerchantStoreAuditDO merchantStoreAuditDO = merchantStoreAuditDOMapper.selectByPrimaryKey(storeAuditDO.getId());
        Assert.assertNotNull(merchantStoreAuditDO);
        Assert.assertTrue(merchantStoreAuditDO.getIsShow());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRecentMerchantAuditRecord() {
        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(200L);
        storeAuditDO.setMerchantStoreId(300L);
        storeAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(0));
        storeAuditDO.setType(DataTransUtil.intToByte(2));
        storeAuditDO.setIsShow(false);
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        MerchantStoreAuditBO storeAuditBO = merchantAuditService.getRecentMerchantAuditRecord(200L);
        Assert.assertNotNull(storeAuditBO);
        Assert.assertEquals(storeAuditDO.getMerchantId(), storeAuditBO.getMerchantId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRecentMerchantAuditSuccessRecord() {
        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);

        //初始化门店审核
        MerchantStoreAuditDO storeAuditDO = new MerchantStoreAuditDO();
        storeAuditDO.setMerchantId(200L);
        storeAuditDO.setMerchantStoreId(300L);
        storeAuditDO.setContent(JSONObject.fromObject(merchantStoreParam).toString());
        storeAuditDO.setStatus(DataTransUtil.intToByte(1));
        storeAuditDO.setType(DataTransUtil.intToByte(2));
        storeAuditDO.setIsShow(false);
        storeAuditDO.setGmtModified(new Date());
        storeAuditDO.setGmtCreate(new Date());
        merchantStoreAuditDOMapper.insertSelective(storeAuditDO);

        Long merchantStoreAuditId = merchantAuditService.getRecentMerchantAuditSuccessRecord(200L);
        Assert.assertNotNull(merchantStoreAuditId);
        Assert.assertEquals(merchantStoreAuditId, storeAuditDO.getId());
    }

}
