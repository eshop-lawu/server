package com.lawu.eshop.ad.srv.service.impl;

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

import com.lawu.eshop.ad.constants.MemberAdRecordStatusEnum;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.bo.MemberAdRecodeCommissionBO;
import com.lawu.eshop.ad.srv.domain.MemberAdRecordDO;
import com.lawu.eshop.ad.srv.mapper.MemberAdRecordDOMapper;
import com.lawu.eshop.ad.srv.service.MemberAdRecordService;

/**
 * 广告记录测试
 * @author zhangrc
 * @date 2017/07/12
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
public class MemberAdRecordServiceImplTest {
	
	
	@Autowired
	private MemberAdRecordDOMapper memberAdRecordDOMapper;
	
	
	@Autowired
	private MemberAdRecordService memberAdRecordService;
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getNoneCommissionAds() {

		MemberAdRecordDO memberAdRecordDO=new MemberAdRecordDO();
        memberAdRecordDO.setAdId(1l);
        memberAdRecordDO.setClickDate(new Date());
        memberAdRecordDO.setGmtCommission(new Date());
        memberAdRecordDO.setGmtCreate(new Date());
        memberAdRecordDO.setMemberId(1l);
        memberAdRecordDO.setMemberNum("aa");
        memberAdRecordDO.setOriginalPoint(BigDecimal.valueOf(0.5));
        memberAdRecordDO.setPoint(BigDecimal.valueOf(0.4));
        memberAdRecordDO.setStatus(MemberAdRecordStatusEnum.NONE.getVal());
        memberAdRecordDOMapper.insert(memberAdRecordDO);
        
        List<MemberAdRecodeCommissionBO>  list= memberAdRecordService.getNoneCommissionAds(0,10);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }

	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateMemberAdRecardStatus() {

		MemberAdRecordDO memberAdRecordDO=new MemberAdRecordDO();
        memberAdRecordDO.setAdId(1l);
        memberAdRecordDO.setClickDate(new Date());
        memberAdRecordDO.setGmtCommission(new Date());
        memberAdRecordDO.setGmtCreate(new Date());
        memberAdRecordDO.setMemberId(1l);
        memberAdRecordDO.setMemberNum("aa");
        memberAdRecordDO.setOriginalPoint(BigDecimal.valueOf(0.5));
        memberAdRecordDO.setPoint(BigDecimal.valueOf(0.4));
        memberAdRecordDO.setStatus(MemberAdRecordStatusEnum.NONE.getVal());
        memberAdRecordDOMapper.insert(memberAdRecordDO);
        
        int row=memberAdRecordService.updateMemberAdRecardStatus(memberAdRecordDO.getId());
        Assert.assertTrue(row >0);
    }
}
