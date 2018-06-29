package com.lawu.eshop.user.srv.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
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
import com.lawu.eshop.user.param.CollectionUserRegParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.extend.UserRegDOMapperExtend;
import com.lawu.eshop.user.srv.service.UserRegService;
import com.lawu.utils.DataTransUtil;
import com.lawu.utils.PwdUtil;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class UserRegServiceImplTest {

    @Autowired
    private UserRegService userRegService;

    @Autowired
    private UserRegDOMapperExtend userRegDOMapperExtend;

    @Autowired
    private MemberDOMapper memberDOMapper;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @Autowired
    private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMemberRegDaily() {
        MemberDO memberDO = new MemberDO();
        memberDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MEMBER));
        memberDO.setAccount("13666666666");
        memberDO.setPwd(PwdUtil.generate("123456"));
        memberDO.setMobile("13666666666");
        memberDO.setStatus(DataTransUtil.intToByte(1));
        memberDO.setGmtCreate(new Date());
        memberDOMapper.insertSelective(memberDO);

        Calendar calendar = Calendar.getInstance();
        CollectionUserRegParam param = new CollectionUserRegParam();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);
        param.setDay(calendar.get(Calendar.DATE));
        int result = userRegService.collectionMemberRegDaily(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMerchantRegDaily() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        Calendar calendar = Calendar.getInstance();
        CollectionUserRegParam param = new CollectionUserRegParam();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);
        param.setDay(calendar.get(Calendar.DATE));
        int result = userRegService.collectionMerchantRegDaily(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMemberRegMonth() {
        MemberDO memberDO = new MemberDO();
        memberDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MEMBER));
        memberDO.setAccount("13666666666");
        memberDO.setPwd(PwdUtil.generate("123456"));
        memberDO.setMobile("13666666666");
        memberDO.setStatus(DataTransUtil.intToByte(1));
        memberDO.setGmtCreate(new Date());
        memberDOMapper.insertSelective(memberDO);

        Calendar calendar = Calendar.getInstance();
        CollectionUserRegParam param = new CollectionUserRegParam();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);
        int result = userRegService.collectionMemberRegMonth(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMerchantRegMonth() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        Calendar calendar = Calendar.getInstance();
        CollectionUserRegParam param = new CollectionUserRegParam();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);
        int result = userRegService.collectionMerchantRegMonth(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMemberRegArea() {
        MemberDO memberDO = new MemberDO();
        memberDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MEMBER));
        memberDO.setAccount("13666666666");
        memberDO.setPwd(PwdUtil.generate("123456"));
        memberDO.setMobile("13666666666");
        memberDO.setRegionPath("44/4403/440303");
        memberDO.setStatus(DataTransUtil.intToByte(1));
        memberDO.setGmtCreate(new Date());
        memberDOMapper.insertSelective(memberDO);

        CollectionUserRegParam param = new CollectionUserRegParam();
        param.setRegionPath("44/4403");
        int result = userRegService.collectionMemberRegArea(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMerchantCommonRegArea() {
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
        profileDO.setManageType(DataTransUtil.intToByte(1));
        profileDO.setCertifType(DataTransUtil.intToByte(1));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        CollectionUserRegParam param = new CollectionUserRegParam();
        param.setRegionPath("44/4403");
        int result = userRegService.collectionMerchantCommonRegArea(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMerchantEntityRegArea() {
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

        CollectionUserRegParam param = new CollectionUserRegParam();
        param.setRegionPath("44/4403");
        int result = userRegService.collectionMerchantEntityRegArea(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMemberRegDailyArea() {
        MemberDO memberDO = new MemberDO();
        memberDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MEMBER));
        memberDO.setAccount("13666666666");
        memberDO.setPwd(PwdUtil.generate("123456"));
        memberDO.setMobile("13666666666");
        memberDO.setRegionPath("44/4403/440303");
        memberDO.setStatus(DataTransUtil.intToByte(1));
        memberDO.setGmtCreate(new Date());
        memberDOMapper.insertSelective(memberDO);

        CollectionUserRegParam param = new CollectionUserRegParam();
        Calendar calendar = Calendar.getInstance();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);
        param.setDay(calendar.get(Calendar.DATE));
        param.setRegionPath("44/4403");
        int result = userRegService.collectionMemberRegDailyArea(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMerchantEntityRegDailyArea() {
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
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(storeDO.getId());
        profileDO.setMerchantId(merchantDO.getId());
        profileDO.setManageType(DataTransUtil.intToByte(2));
        profileDO.setCertifType(DataTransUtil.intToByte(2));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        CollectionUserRegParam param = new CollectionUserRegParam();
        Calendar calendar = Calendar.getInstance();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);
        param.setDay(calendar.get(Calendar.DATE));
        param.setRegionPath("44/4403");
        int result = userRegService.collectionMerchantEntityRegDailyArea(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMerchantNormalRegDailyArea() {
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
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(storeDO.getId());
        profileDO.setMerchantId(merchantDO.getId());
        profileDO.setManageType(DataTransUtil.intToByte(1));
        profileDO.setCertifType(DataTransUtil.intToByte(1));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        CollectionUserRegParam param = new CollectionUserRegParam();
        Calendar calendar = Calendar.getInstance();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);
        param.setDay(calendar.get(Calendar.DATE));
        param.setRegionPath("44/4403");
        int result = userRegService.collectionMerchantNormalRegDailyArea(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMemberRegMonthArea() {
        MemberDO memberDO = new MemberDO();
        memberDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MEMBER));
        memberDO.setAccount("13666666666");
        memberDO.setPwd(PwdUtil.generate("123456"));
        memberDO.setMobile("13666666666");
        memberDO.setRegionPath("44/4403/440303");
        memberDO.setStatus(DataTransUtil.intToByte(1));
        memberDO.setGmtCreate(new Date());
        memberDOMapper.insertSelective(memberDO);

        CollectionUserRegParam param = new CollectionUserRegParam();
        Calendar calendar = Calendar.getInstance();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);
        param.setRegionPath("44/4403");
        int result = userRegService.collectionMemberRegMonthArea(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMerchantNormalRegMonthArea() {
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
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(storeDO.getId());
        profileDO.setMerchantId(merchantDO.getId());
        profileDO.setManageType(DataTransUtil.intToByte(1));
        profileDO.setCertifType(DataTransUtil.intToByte(1));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        CollectionUserRegParam param = new CollectionUserRegParam();
        Calendar calendar = Calendar.getInstance();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);
        param.setRegionPath("44/4403");
        int result = userRegService.collectionMerchantNormalRegMonthArea(param);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void collectionMerchantEntityRegMonthArea() {
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
        storeDO.setGmtCreate(new Date());
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreProfileDO profileDO = new MerchantStoreProfileDO();
        profileDO.setId(storeDO.getId());
        profileDO.setMerchantId(merchantDO.getId());
        profileDO.setManageType(DataTransUtil.intToByte(2));
        profileDO.setCertifType(DataTransUtil.intToByte(2));
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        CollectionUserRegParam param = new CollectionUserRegParam();
        Calendar calendar = Calendar.getInstance();
        param.setYear(calendar.get(Calendar.YEAR));
        param.setMonth(calendar.get(Calendar.MONTH) + 1);
        param.setRegionPath("44/4403");
        int result = userRegService.collectionMerchantEntityRegMonthArea(param);
        Assert.assertEquals(1, result);
    }

}
