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

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.user.constants.FreezeTypeEnum;
import com.lawu.eshop.user.param.AccountParam;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.eshop.user.param.MerchantInviterParam;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.eshop.user.param.UserLoginLogParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.MerchantBO;
import com.lawu.eshop.user.srv.bo.MerchantBaseInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantDetailBO;
import com.lawu.eshop.user.srv.bo.MerchantFreezeInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInviterBO;
import com.lawu.eshop.user.srv.bo.MessagePushBO;
import com.lawu.eshop.user.srv.bo.RongYunBO;
import com.lawu.eshop.user.srv.domain.InviteRelationDO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.domain.UserLoginLogDO;
import com.lawu.eshop.user.srv.domain.extend.MerchantDOView;
import com.lawu.eshop.user.srv.mapper.InviteRelationDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.UserLoginLogDOMapper;
import com.lawu.eshop.user.srv.service.MerchantService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DataTransUtil;
import com.lawu.utils.PwdUtil;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class MerchantServiceImplTest {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Autowired
    private InviteRelationDOMapper inviteRelationDOMapper;

    @Autowired
    private MerchantProfileDOMapper merchantProfileDOMapper;

    @Autowired
    private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;

    @Autowired
    private UserLoginLogDOMapper userLoginLogDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateLoginPwd() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        merchantService.updateLoginPwd(merchantDO.getId(), "222222");
        MerchantDO merchantDO1 = merchantDOMapper.selectByPrimaryKey(merchantDO.getId());
        Assert.assertNotNull(merchantDO1);
        Assert.assertTrue(PwdUtil.verify("222222", merchantDO1.getPwd()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findMerchantInfo() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantInfoBO merchantInfoBO = merchantService.findMerchantInfo(merchantDO.getId());
        Assert.assertNotNull(merchantInfoBO);
        Assert.assertEquals(merchantDO.getAccount(), merchantInfoBO.getAccount());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantByAccount() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantBO merchantBO = merchantService.getMerchantByAccount(merchantDO.getAccount());
        Assert.assertNotNull(merchantBO);
        Assert.assertEquals(merchantDO.getAccount(), merchantBO.getAccount());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void register() {
        //邀请人
        String userNum = IdWorkerHelperImpl.generate(BizIdType.MERCHANT);
        //邀请人的推荐人
        String bNum = IdWorkerHelperImpl.generate(BizIdType.MERCHANT);

        //邀请人的推荐人和邀请人关系
        InviteRelationDO inviteRelationDO = new InviteRelationDO();
        inviteRelationDO.setUserNum(bNum);
        inviteRelationDO.setInviteUserNum(userNum);
        inviteRelationDO.setDepth(1);
        inviteRelationDO.setType(DataTransUtil.intToByte(2));
        inviteRelationDOMapper.insertSelective(inviteRelationDO);

        //邀请人信息
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(userNum);
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        //邀请人扩展信息
        MerchantProfileDO merchantProfileDO = new MerchantProfileDO();
        merchantProfileDO.setId(merchantDO.getId());
        merchantProfileDOMapper.insertSelective(merchantProfileDO);

        //邀请人的推荐人信息
        MerchantDO merchantDO1 = new MerchantDO();
        merchantDO1.setNum(bNum);
        merchantDO1.setAccount("13888888888");
        merchantDO1.setPwd("123456");
        merchantDO1.setMobile("13888888888");
        merchantDO1.setStatus(DataTransUtil.intToByte(1));
        merchantDO1.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO1);

        //邀请人的推荐人扩展信息
        MerchantProfileDO merchantProfileDO1 = new MerchantProfileDO();
        merchantProfileDO1.setId(merchantDO1.getId());
        merchantProfileDOMapper.insertSelective(merchantProfileDO1);

        RegisterRealParam param = new RegisterRealParam();
        param.setAccount("13888888888");
        param.setPwd("123456");
        param.setInviterId(merchantDO.getId());
        param.setUserNum(userNum);
        merchantService.register(param);

        //会员信息
        List<MerchantDO> merchantDOS = merchantDOMapper.selectByExample(null);
        Assert.assertNotNull(merchantDOS);
        Assert.assertEquals(3, merchantDOS.size());

        //扩展信息
        List<MerchantProfileDO> merchantProfileDOS = merchantProfileDOMapper.selectByExample(null);
        Assert.assertNotNull(merchantProfileDOS);
        Assert.assertEquals(3, merchantProfileDOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantBOById() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantBO merchantBO = merchantService.getMerchantBOById(merchantDO.getId());
        Assert.assertNotNull(merchantBO);
        Assert.assertEquals(merchantDO.getAccount(), merchantBO.getAccount());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantByInviter() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
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
        storeImageDO.setMerchantId(storeDO.getId());
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        MerchantInviterParam param = new MerchantInviterParam();
        param.setName("测试店铺");
        Page<MerchantInviterBO> merchantInviterBOPage = merchantService.getMerchantByInviter(200L, param, DataTransUtil.intToByte(2));
        Assert.assertNotNull(merchantInviterBOPage.getRecords());
        Assert.assertEquals(1, merchantInviterBOPage.getTotalCount().intValue());
        Assert.assertEquals(0, merchantInviterBOPage.getRecords().get(0).getInviterCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void find() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd(PwdUtil.generate("123456"));
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantBO merchantBO = merchantService.find("13888888888", "123456");
        Assert.assertNotNull(merchantBO);
        Assert.assertEquals(merchantDO.getAccount(), merchantBO.getAccount());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void setGtAndRongYunInfo() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd(PwdUtil.generate("123456"));
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        merchantService.setGtAndRongYunInfo(merchantDO.getId(), "8888");
        MerchantDO merchantDO1 = merchantDOMapper.selectByPrimaryKey(merchantDO.getId());
        Assert.assertNotNull(merchantDO1);
        Assert.assertEquals("8888", merchantDO1.getGtCid());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findMemberByNum() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd(PwdUtil.generate("123456"));
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantBO merchantBO = merchantService.findMemberByNum(merchantDO.getNum());
        Assert.assertNotNull(merchantBO);
        Assert.assertEquals(merchantDO.getNum(), merchantBO.getNum());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectMerchantInfo() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
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
        storeDO.setPrincipalName("测试人");
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantBO merchantBO = merchantService.selectMerchantInfo(merchantDO.getId());
        Assert.assertNotNull(merchantBO);
        Assert.assertEquals(storeDO.getPrincipalName(), merchantBO.getPrincipalName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findMessagePushList() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
        merchantDO.setGtCid("8888");
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
        storeDO.setPrincipalName("测试人");
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        List<MessagePushBO> pushBOS = merchantService.findMessagePushList("44/4403");
        Assert.assertNotNull(pushBOS);
        Assert.assertEquals(1, pushBOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findMessagePushByMobile() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
        merchantDO.setGtCid("8888");
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MessagePushBO pushBO = merchantService.findMessagePushByMobile("13888888888");
        Assert.assertNotNull(pushBO);
        Assert.assertEquals(merchantDO.getNum(), pushBO.getUserNum());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateHeadImg() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
        merchantDO.setGtCid("8888");
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        merchantService.updateHeadImg("pic", merchantDO.getId());
        MerchantDO merchantDO1 = merchantDOMapper.selectByPrimaryKey(merchantDO.getId());
        Assert.assertNotNull(merchantDO1);
        Assert.assertEquals("pic", merchantDO1.getHeadimg());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRongYunInfoByNum() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
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
        storeDO.setPrincipalName("测试人");
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(storeDO.getId());
        storeImageDO.setMerchantStoreId(storeDO.getId());
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        RongYunBO rongYunBO = merchantService.getRongYunInfoByNum(merchantDO.getNum());
        Assert.assertNotNull(rongYunBO);
        Assert.assertEquals(storeDO.getName(), rongYunBO.getNickName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantById() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantBaseInfoBO merchantBaseInfoBO = merchantService.getMerchantById(merchantDO.getId());
        Assert.assertNotNull(merchantBaseInfoBO);
        Assert.assertEquals(merchantDO.getNum(), merchantBaseInfoBO.getUserNum());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantByNum() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantBO merchantBO = merchantService.getMerchantByNum(merchantDO.getNum());
        Assert.assertNotNull(merchantBO);
        Assert.assertEquals(merchantDO.getNum(), merchantBO.getNum());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void isRegister() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        boolean result = merchantService.isRegister(merchantDO.getAccount());
        Assert.assertTrue(result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delMerchantGtPush() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
        merchantDO.setGtCid("8888");
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        merchantService.delMerchantGtPush(merchantDO.getId());
        MerchantDO merchantDO1 = merchantDOMapper.selectByPrimaryKey(merchantDO.getId());
        Assert.assertNotNull(merchantDO1);
        Assert.assertNull(merchantDO1.getGtCid());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantView() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
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
        storeDO.setPrincipalName("测试人");
        storeDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(storeDO);

        MerchantDOView merchantDOView = merchantService.getMerchantView(merchantDO.getId());
        Assert.assertNotNull(merchantDOView);
        Assert.assertEquals(merchantDO.getAccount(), merchantDOView.getAccount());
        Assert.assertEquals(storeDO.getName(), merchantDOView.getStoreName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getTotalCount() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        int result = merchantService.getTotalCount();
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAccountList(){
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setId(1L);
        merchantDO.setStatus(StatusEnum.VALID.getValue());
        merchantDO.setAccount("123456");
        merchantDO.setIsFreeze(false);
        merchantDO.setNum("123");
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        AccountParam param = new AccountParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setAccount("123456");
        param.setUserType(UserTypeEnum.MEMBER);

        Page<MerchantBO> page = merchantService.getAccountList(param);

        Assert.assertEquals(1, page.getRecords().size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void freezeAccount(){
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setId(1L);
        merchantDO.setStatus(StatusEnum.VALID.getValue());
        merchantDO.setAccount("123456");
        merchantDO.setIsFreeze(false);
        merchantDO.setNum("123");
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);
        FreezeParam param = new FreezeParam();
        param.setFreezeReason(param.getFreezeReason());
        param.setFreezeType(FreezeTypeEnum.BACKGROUND_FREEZE);
        param.setIsFreeze(true);
        param.setNum(merchantDO.getNum());
        merchantService.freezeAccount(param);
        List<MerchantDO> list = merchantDOMapper.selectByExample(null);
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(true, list.get(0).getIsFreeze());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantDetailById() {
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
        profileDO.setMerchantId(200L);
        merchantStoreProfileDOMapper.insertSelective(profileDO);

        MerchantStoreImageDO storeImageDO = new MerchantStoreImageDO();
        storeImageDO.setMerchantId(200L);
        storeImageDO.setPath("pic");
        storeImageDO.setStatus(true);
        for (int i = 1; i <= 6; i++) {
            storeImageDO.setType(DataTransUtil.intToByte(i));
            merchantStoreImageDOMapper.insertSelective(storeImageDO);
        }

        MerchantDetailBO detailBO = merchantService.getMerchantDetailById(200L);
        Assert.assertNotNull(detailBO);
        Assert.assertEquals(storeDO.getName(), detailBO.getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveLoginLog() {
        UserLoginLogParam loginLogParam = new UserLoginLogParam();
        loginLogParam.setUserNum("M0001");
        loginLogParam.setAccount("13666666666");
        loginLogParam.setUserType(UserTypeEnum.MERCHANT.getValue());
        loginLogParam.setImei("test");
        loginLogParam.setPlatform(DataTransUtil.intToByte(1));
        loginLogParam.setPlatformVer("test");
        loginLogParam.setAppVer("test");
        loginLogParam.setCityId(10);
        loginLogParam.setChannel("test");
        loginLogParam.setIpAddr("test");
        merchantService.saveLoginLog(loginLogParam);

        List<UserLoginLogDO> list = userLoginLogDOMapper.selectByExample(null);
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(loginLogParam.getUserNum(), list.get(0).getUserNum());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getMerchantFreezeInfo() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setAccount("13666666666");
        merchantDO.setNum("B001");
        merchantDO.setFreezeReason("fail");
        merchantDOMapper.insertSelective(merchantDO);

        MerchantFreezeInfoBO freezeInfoBO = merchantService.getMerchantFreezeInfo(merchantDO.getId());
        Assert.assertNotNull(freezeInfoBO);
        Assert.assertEquals(merchantDO.getAccount(), freezeInfoBO.getAccount());
    }

}
