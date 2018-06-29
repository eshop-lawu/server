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

import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.user.constants.FansMerchantChannelEnum;
import com.lawu.eshop.user.constants.UserSexEnum;
import com.lawu.eshop.user.param.ListFansParam;
import com.lawu.eshop.user.param.ListInviteFansParam;
import com.lawu.eshop.user.param.PageListInviteFansParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.FansMerchantBO;
import com.lawu.eshop.user.srv.domain.FansInviteResultDOExample;
import com.lawu.eshop.user.srv.domain.FansMerchantDO;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.mapper.FansInviteResultDOMapper;
import com.lawu.eshop.user.srv.mapper.FansMerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.service.FansMerchantService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class FansMerchantServiceImplTest {

    @Autowired
    private FansMerchantService fansMerchantService;

    @Autowired
    private FansMerchantDOMapper fansMerchantDOMapper;

    @Autowired
    private MemberDOMapper memberDOMapper;
    
    @Autowired
    private FansInviteResultDOMapper fansInviteResultDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listInviteFans() {
        MemberDO memberDO = new MemberDO();
        memberDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MEMBER));
        memberDO.setAccount("13666666666");
        memberDO.setPwd("123456");
        memberDO.setMobile("13666666666");
        memberDO.setStatus(DataTransUtil.intToByte(1));
        memberDO.setGmtCreate(new Date());
        memberDOMapper.insertSelective(memberDO);

        ListInviteFansParam param = new ListInviteFansParam();
        param.setUserSexEnum(UserSexEnum.SEX_SECRET);
        param.setIsAgeLimit(false);
        param.setInviteCount(100);
        List<FansMerchantBO> fansMerchantBOS = fansMerchantService.listInviteFans(200L, param);
        Assert.assertNotNull(fansMerchantBOS);
        Assert.assertEquals(1, fansMerchantBOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void pageListInviteFans() {
        MemberDO memberDO = new MemberDO();
        memberDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MEMBER));
        memberDO.setAccount("13666666666");
        memberDO.setPwd("123456");
        memberDO.setMobile("13666666666");
        memberDO.setStatus(DataTransUtil.intToByte(1));
        memberDO.setGmtCreate(new Date());
        memberDOMapper.insertSelective(memberDO);

        memberDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MEMBER));
        memberDO.setAccount("13666666666");
        memberDO.setPwd("123456");
        memberDO.setMobile("13666666666");
        memberDO.setStatus(DataTransUtil.intToByte(1));
        memberDO.setGmtCreate(new Date());
        memberDOMapper.insertSelective(memberDO);

        FansMerchantDO fansMerchantDO = new FansMerchantDO();
        fansMerchantDO.setMerchantId(200L);
        fansMerchantDO.setMemberId(memberDO.getId());
        fansMerchantDOMapper.insertSelective(fansMerchantDO);

        PageListInviteFansParam param = new PageListInviteFansParam();
        param.setUserSexEnum(UserSexEnum.SEX_SECRET);
        param.setIsAgeLimit(false);
        Page<FansMerchantBO> fansMerchantBOPage = fansMerchantService.pageListInviteFans(200L, param);
        Assert.assertNotNull(fansMerchantBOPage.getRecords());
        Assert.assertEquals(1, fansMerchantBOPage.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listFans() {
        MemberDO memberDO = new MemberDO();
        memberDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MEMBER));
        memberDO.setAccount("13666666666");
        memberDO.setPwd("123456");
        memberDO.setMobile("13666666666");
        memberDO.setStatus(DataTransUtil.intToByte(1));
        memberDO.setGmtCreate(new Date());
        memberDOMapper.insertSelective(memberDO);

        FansMerchantDO fansMerchantDO = new FansMerchantDO();
        fansMerchantDO.setMerchantId(200L);
        fansMerchantDO.setMemberId(memberDO.getId());
        fansMerchantDO.setStatus((byte)1);
        fansMerchantDOMapper.insertSelective(fansMerchantDO);

        ListFansParam param = new ListFansParam();
        Page<FansMerchantBO> fansMerchantBOPage = fansMerchantService.listFans(200L, param);
        Assert.assertNotNull(fansMerchantBOPage.getRecords());
        Assert.assertEquals(1, fansMerchantBOPage.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getFansMerchant() {
        FansMerchantDO fansMerchantDO = new FansMerchantDO();
        fansMerchantDO.setMerchantId(200L);
        fansMerchantDO.setMemberId(100L);
        fansMerchantDOMapper.insertSelective(fansMerchantDO);
        FansMerchantBO fansMerchantBO = fansMerchantService.getFansMerchant(100L, 200L);
        Assert.assertNotNull(fansMerchantBO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findMerchant() {
        FansMerchantDO fansMerchantDO = new FansMerchantDO();
        fansMerchantDO.setMerchantId(200L);
        fansMerchantDO.setMemberId(100L);
        fansMerchantDOMapper.insertSelective(fansMerchantDO);

        List<Long> list = fansMerchantService.findMerchant(100L);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findFensCount() {
        FansMerchantDO fansMerchantDO = new FansMerchantDO();
        fansMerchantDO.setMerchantId(200L);
        fansMerchantDO.setMemberId(100L);
        fansMerchantDOMapper.insertSelective(fansMerchantDO);

        int result = fansMerchantService.findFensCount(200L);
        Assert.assertEquals(1, result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveFansMerchant() {
        fansMerchantService.saveFansMerchant(200L, 100L, FansMerchantChannelEnum.REGISTER);

        List<FansMerchantDO> fansMerchantDOS = fansMerchantDOMapper.selectByExample(null);
        Assert.assertNotNull(fansMerchantDOS);
        Assert.assertEquals(1, fansMerchantDOS.size());
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveFansMerchantFromInvite() {
        fansMerchantService.saveFansMerchantFromInvite(1L, 2L, 10L, true);
        List<FansMerchantDO> fansMerchantDOS = fansMerchantDOMapper.selectByExample(null);
        Assert.assertNotNull(fansMerchantDOS);
        Assert.assertEquals(0, fansMerchantDOS.size());
        FansInviteResultDOExample fansInviteResultDOExample = new FansInviteResultDOExample();
        fansInviteResultDOExample.createCriteria().andMerchantIdEqualTo(1L).andMemberIdEqualTo(2L);
        int i = fansInviteResultDOMapper.countByExample(fansInviteResultDOExample);
        Assert.assertEquals(1, i);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void countOverdueFans() {
        FansMerchantDO fansMerchantDO = new FansMerchantDO();
        fansMerchantDO.setMemberId(100L);
        fansMerchantDO.setMerchantId(200L);
        fansMerchantDO.setStatus((byte) 0);
        fansMerchantDO.setChannel(FansMerchantChannelEnum.INVITE.getValue());
        fansMerchantDO.setGmtCreate(new Date());
        fansMerchantDOMapper.insertSelective(fansMerchantDO);
        FansMerchantBO fansMerchantBO = fansMerchantService.getFansMerchantById(fansMerchantDO.getId());
        Assert.assertNotNull(fansMerchantBO);
        Assert.assertEquals(fansMerchantDO.getMemberId(), fansMerchantBO.getMemberId());
    }
    
}
