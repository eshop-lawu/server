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

import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.param.UserGradeQuery;
import com.lawu.eshop.user.param.UserGradeUpdateParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.bo.UserGradeBO;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.UserGradeDO;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.UserGradeDOMapper;
import com.lawu.eshop.user.srv.service.UserGradeService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DataTransUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class UserGradeServiceImplTest {

    @Autowired
    private UserGradeService userGradeService;

    @Autowired
    private UserGradeDOMapper userGradeDOMapper;

    @Autowired
    private MemberDOMapper memberDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectLotteryActivityPointByGradeValue() {
        UserGradeDO userGradeDO = new UserGradeDO();
        userGradeDO.setGradeValue(MemberGradeEnum.SILVER.getVal());
        userGradeDO.setGradeWeight(1);
        userGradeDO.setMinGrowthValue(0);
        userGradeDO.setLotteryActivityPoint(100);
        userGradeDO.setGradeName("白银会员");
        userGradeDO.setGmtCreate(new Date());
        userGradeDOMapper.insertSelective(userGradeDO);

        Integer lotteryActivityPoint = userGradeService.selectLotteryActivityPointByGradeValue(MemberGradeEnum.SILVER.getVal());
        Assert.assertEquals(100, lotteryActivityPoint.intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getLotteryGradeInfo() {
        MemberDO memberDO = new MemberDO();
        memberDO.setGrade(DataTransUtil.intToByte(1));
        memberDOMapper.insertSelective(memberDO);

        UserGradeDO userGradeDO = new UserGradeDO();
        userGradeDO.setGradeValue(MemberGradeEnum.SILVER.getVal());
        userGradeDO.setGradeWeight(1);
        userGradeDO.setMinGrowthValue(0);
        userGradeDO.setFreeLotteryCount(1);
        userGradeDO.setLotteryActivityPoint(100);
        userGradeDO.setGradeName("白银会员");
        userGradeDO.setGmtCreate(new Date());
        userGradeDOMapper.insertSelective(userGradeDO);

        UserGradeBO userGradeBO = userGradeService.getLotteryGradeInfo(memberDO.getId(), MemberGradeEnum.SILVER.getVal());
        Assert.assertNotNull(userGradeBO);
        Assert.assertEquals(userGradeDO.getFreeLotteryCount(), userGradeBO.getFreeLotteryCount());
        Assert.assertEquals(userGradeDO.getLotteryActivityPoint(), userGradeBO.getLotteryActivityPoint());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectGradeList() {
        UserGradeDO userGradeDO = new UserGradeDO();
        userGradeDO.setGradeValue(MemberGradeEnum.SILVER.getVal());
        userGradeDO.setGradeWeight(1);
        userGradeDO.setMinGrowthValue(0);
        userGradeDO.setLotteryActivityPoint(100);
        userGradeDO.setGradeName("白银会员");
        userGradeDO.setGmtCreate(new Date());
        userGradeDOMapper.insertSelective(userGradeDO);

        List<UserGradeBO> gradeList = userGradeService.selectGradeList();
        Assert.assertEquals(1, gradeList.size());
        Assert.assertEquals("白银会员",gradeList.get(0).getGradeName());
        Assert.assertEquals(MemberGradeEnum.SILVER.getVal(),gradeList.get(0).getGradeValue());
        Assert.assertEquals(1,gradeList.get(0).getGradeWeight().intValue());
        Assert.assertEquals(0,gradeList.get(0).getMinGrowthValue().intValue());
        Assert.assertEquals(100,gradeList.get(0).getLotteryActivityPoint().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectUserGradeByMinGrowthValue() {
        UserGradeDO userGradeDO = new UserGradeDO();
        userGradeDO.setGradeValue(MemberGradeEnum.GOLD.getVal());
        userGradeDO.setGradeWeight(2);
        userGradeDO.setMinGrowthValue(200);
        userGradeDO.setLotteryActivityPoint(200);
        userGradeDO.setGradeName("黄金会员");
        userGradeDO.setGmtCreate(new Date());
        userGradeDOMapper.insertSelective(userGradeDO);

        UserGradeBO grade = userGradeService.selectUserGradeByMinGrowthValue(400);
        Assert.assertNotNull(grade);
        Assert.assertEquals("黄金会员",grade.getGradeName());
        Assert.assertEquals(MemberGradeEnum.GOLD.getVal(),grade.getGradeValue());
        Assert.assertEquals(2,grade.getGradeWeight().intValue());
        Assert.assertEquals(200,grade.getMinGrowthValue().intValue());
        Assert.assertEquals(200,grade.getLotteryActivityPoint().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectPage() {
        UserGradeDO userGradeDO = new UserGradeDO();
        userGradeDO.setGradeValue(MemberGradeEnum.GOLD.getVal());
        userGradeDO.setGradeWeight(2);
        userGradeDO.setMinGrowthValue(200);
        userGradeDO.setLotteryActivityPoint(200);
        userGradeDO.setGradeName("黄金会员");
        userGradeDO.setGmtCreate(new Date());
        userGradeDOMapper.insertSelective(userGradeDO);

        UserGradeQuery query = new UserGradeQuery();
        Page<UserGradeBO> page = userGradeService.selectPage(query);
        Assert.assertNotNull(page);
        Assert.assertEquals(1,page.getTotalCount().intValue());
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1,page.getRecords().size());
        Assert.assertEquals("黄金会员",page.getRecords().get(0).getGradeName());
        Assert.assertEquals(MemberGradeEnum.GOLD.getVal(),page.getRecords().get(0).getGradeValue());
        Assert.assertEquals(2,page.getRecords().get(0).getGradeWeight().intValue());
        Assert.assertEquals(200,page.getRecords().get(0).getMinGrowthValue().intValue());
        Assert.assertEquals(200,page.getRecords().get(0).getLotteryActivityPoint().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateById() {
        UserGradeDO userGradeDO = new UserGradeDO();
        userGradeDO.setGradeValue(MemberGradeEnum.GOLD.getVal());
        userGradeDO.setGradeWeight(2);
        userGradeDO.setMinGrowthValue(200);
        userGradeDO.setLotteryActivityPoint(200);
        userGradeDO.setGradeName("黄金会员");
        userGradeDO.setGmtCreate(new Date());
        userGradeDOMapper.insertSelective(userGradeDO);

        UserGradeDO userGradeDO1 = new UserGradeDO();
        userGradeDO1.setGradeValue(MemberGradeEnum.PLATINUM.getVal());
        userGradeDO1.setGradeWeight(3);
        userGradeDO1.setMinGrowthValue(300);
        userGradeDO1.setLotteryActivityPoint(300);
        userGradeDO1.setGradeName("铂金会员");
        userGradeDO1.setGmtCreate(new Date());
        userGradeDOMapper.insertSelective(userGradeDO1);

        UserGradeUpdateParam param = new UserGradeUpdateParam();
        param.setGradeName("白银会员");
        param.setGradeWeight(1);
        param.setMinGrowthValue(0);
        param.setLotteryActivityPoint(100);
        int ret = userGradeService.updateById(userGradeDO.getId(),param);

        UserGradeDO udo = userGradeDOMapper.selectByPrimaryKey(userGradeDO.getId());

        Assert.assertEquals(ResultCode.SUCCESS,ret);
        Assert.assertEquals("白银会员",udo.getGradeName());
        Assert.assertEquals(1,udo.getGradeWeight().intValue());
        Assert.assertEquals(0,udo.getMinGrowthValue().intValue());
        Assert.assertEquals(100,udo.getLotteryActivityPoint().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectUserGradeById() {
        UserGradeDO userGradeDO = new UserGradeDO();
        userGradeDO.setGradeValue(MemberGradeEnum.GOLD.getVal());
        userGradeDO.setGradeWeight(2);
        userGradeDO.setMinGrowthValue(200);
        userGradeDO.setLotteryActivityPoint(200);
        userGradeDO.setGradeName("黄金会员");
        userGradeDO.setGmtCreate(new Date());
        userGradeDOMapper.insertSelective(userGradeDO);

        UserGradeBO grade = userGradeService.selectUserGradeById(userGradeDO.getId());
        Assert.assertNotNull(grade);
        Assert.assertEquals("黄金会员",grade.getGradeName());
        Assert.assertEquals(MemberGradeEnum.GOLD.getVal(),grade.getGradeValue());
        Assert.assertEquals(2,grade.getGradeWeight().intValue());
        Assert.assertEquals(200,grade.getMinGrowthValue().intValue());
        Assert.assertEquals(200,grade.getLotteryActivityPoint().intValue());

    }
}
