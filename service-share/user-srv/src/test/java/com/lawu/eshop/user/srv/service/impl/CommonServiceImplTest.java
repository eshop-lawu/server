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

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.user.param.EFriendQueryDataParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.EFriendInviterBO;
import com.lawu.eshop.user.srv.bo.InviterBO;
import com.lawu.eshop.user.srv.domain.InviteRelationDO;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MemberProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.mapper.InviteRelationDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.service.CommonService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class CommonServiceImplTest {

    @Autowired
    private CommonService commonService;

    @Autowired
    private MemberDOMapper memberDOMapper;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @Autowired
    private InviteRelationDOMapper inviteRelationDOMapper;

    @Autowired
    private MemberProfileDOMapper memberProfileDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getInviterByAccount() {
        MemberDO memberDO = new MemberDO();
        memberDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MEMBER));
        memberDO.setAccount("13666666666");
        memberDO.setPwd("123456");
        memberDO.setMobile("13666666666");
        memberDO.setStatus(DataTransUtil.intToByte(1));
        memberDO.setGmtCreate(new Date());
        memberDOMapper.insertSelective(memberDO);

        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(IdWorkerHelperImpl.generate(BizIdType.MERCHANT));
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);

        MerchantStoreDO merchantStoreDO = new MerchantStoreDO();
        merchantStoreDO.setMerchantId(merchantDO.getId());
        merchantStoreDO.setName("测试店铺");
        merchantStoreDO.setRegionPath("44/4403/440303");
        merchantStoreDO.setAddress("大冲商务中心");
        merchantStoreDO.setLongitude(new BigDecimal(114.2365889));
        merchantStoreDO.setLatitude(new BigDecimal(22.365244));
        merchantStoreDO.setIntro("测试店铺介绍");
        merchantStoreDO.setStatus(DataTransUtil.intToByte(1));
        merchantStoreDO.setIsNoReasonReturn(true);
        merchantStoreDOMapper.insertSelective(merchantStoreDO);

        InviterBO inviterBO = commonService.getInviterByAccount("13666666666");
        Assert.assertNotNull(inviterBO);

        inviterBO = commonService.getInviterByAccount("13888888888");
        Assert.assertNotNull(inviterBO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectHigherLevelInviters() {
        InviteRelationDO inviteRelationDO = new InviteRelationDO();
        inviteRelationDO.setUserNum("M0001");
        inviteRelationDO.setInviteUserNum("M0002");
        inviteRelationDO.setDepth(1);
        inviteRelationDO.setType(DataTransUtil.intToByte(1));
        inviteRelationDOMapper.insertSelective(inviteRelationDO);

        List<CommissionInvitersUserDTO> invitersUserDTOS = commonService.selectHigherLevelInviters("M0002", 1, false);
        Assert.assertNotNull(invitersUserDTOS);
        Assert.assertEquals(1, invitersUserDTOS.size());

        invitersUserDTOS = commonService.selectHigherLevelInviters("M0002", 1, true);
        Assert.assertNotNull(invitersUserDTOS);
        Assert.assertEquals(1, invitersUserDTOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectEFriend() {

        InviteRelationDO inviteRelationDO = new InviteRelationDO();
        inviteRelationDO.setUserNum("M00001");
        inviteRelationDO.setDepth(0);
        inviteRelationDO.setInviteUserNum("M00001");
        inviteRelationDO.setType(new Byte("1"));
        inviteRelationDOMapper.insert(inviteRelationDO);
        InviteRelationDO inviteRelationDO1 = new InviteRelationDO();
        inviteRelationDO1.setUserNum("M00001");
        inviteRelationDO1.setDepth(1);
        inviteRelationDO1.setInviteUserNum("M00002");
        inviteRelationDO1.setType(new Byte("1"));
        inviteRelationDOMapper.insert(inviteRelationDO1);
        InviteRelationDO inviteRelationDO2 = new InviteRelationDO();
        inviteRelationDO2.setUserNum("M00001");
        inviteRelationDO2.setDepth(1);
        inviteRelationDO2.setInviteUserNum("B00001");
        inviteRelationDO2.setType(new Byte("1"));
        inviteRelationDOMapper.insert(inviteRelationDO2);

        MemberDO memberDO = new MemberDO();
        memberDO.setName("鲁班七号");
        memberDO.setAccount("13510231310");
        memberDO.setGmtCreate(new Date());
        memberDO.setLevel(1);
        memberDO.setNickname("小短腿");
        memberDO.setRegionName("wzry");
        memberDO.setNum("M00002");
        memberDO.setStatus(new Byte("1"));
        memberDOMapper.insertSelective(memberDO);
        MemberProfileDO memberProfileDO = new MemberProfileDO();
        memberProfileDO.setId(memberDO.getId());
        memberProfileDO.setInviteMemberCount(1);
        memberProfileDO.setGmtCreate(new Date());
        memberProfileDOMapper.insertSelective(memberProfileDO);

        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setLevel(1);
        merchantDO.setGmtCreate(new Date());
        merchantDO.setAccount("13590901232");
        merchantDO.setNum("B00001");
        merchantDO.setStatus(new Byte("1"));
        merchantDOMapper.insertSelective(merchantDO);
        MerchantProfileDO merchantProfileDO = new MerchantProfileDO();
        merchantProfileDO.setId(merchantDO.getId());
        merchantProfileDO.setInviteMemberCount(1);
        merchantProfileDO.setGmtCreate(new Date());
        MerchantStoreDO merchantStoreDO = new MerchantStoreDO();
        merchantStoreDO.setGmtCreate(new Date());
        merchantStoreDO.setName("土地");
        merchantStoreDO.setAddress("网内");
        merchantStoreDO.setPrincipalName("鲁班");
        merchantStoreDO.setMerchantId(merchantDO.getId());
        merchantStoreDO.setRegionName("区域");
        merchantStoreDO.setStatus(new Byte("1"));
        merchantStoreDOMapper.insertSelective(merchantStoreDO);

        EFriendQueryDataParam param = new EFriendQueryDataParam();
        param.setQueryContent("135");
        param.setUserNum("M00001");
        param.setCurrentPage(1);
        param.setPageSize(10);
        Page<EFriendInviterBO> rtnPage = commonService.selectEFriend(param);
        Assert.assertNotNull(rtnPage);
        Assert.assertEquals(2, rtnPage.getTotalCount().intValue());


    }
}
