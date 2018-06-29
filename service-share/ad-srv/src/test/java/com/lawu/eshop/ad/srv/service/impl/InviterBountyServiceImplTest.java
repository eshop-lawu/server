package com.lawu.eshop.ad.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.ad.constants.PlatformRedPacketStatusEnum;
import com.lawu.eshop.ad.param.InviterBountyParam;
import com.lawu.eshop.ad.param.InviterBountyQueryParam;
import com.lawu.eshop.ad.srv.bo.GetInviterBountyBO;
import com.lawu.eshop.ad.srv.bo.InviterBountyBO;
import com.lawu.eshop.ad.srv.domain.InviterBountyDO;
import com.lawu.eshop.ad.srv.domain.InviterBountyDOExample;
import com.lawu.eshop.ad.srv.domain.TakeInviterBountyDetailDO;
import com.lawu.eshop.ad.srv.mapper.InviterBountyDOMapper;
import com.lawu.eshop.ad.srv.mapper.TakeInviterBountyDetailDOMapper;
import com.lawu.eshop.ad.srv.service.InviterBountyService;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangrc
 * @date 2017/12/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test.xml"})
public class InviterBountyServiceImplTest {

	@Autowired
	private InviterBountyDOMapper inviterBountyDOMapper;
	
	@Autowired
	private InviterBountyService inviterBountyService;
	
	@Autowired
	private TakeInviterBountyDetailDOMapper takeInviterBountyDetailDOMapper;
   
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveInviterBounty() {

    	InviterBountyParam param = new InviterBountyParam();
    	param.setAuditorId(1000l);
    	param.setMoney(BigDecimal.valueOf(5));
    	inviterBountyService.saveInviterBounty(param);
    	
        List<InviterBountyDO> list = inviterBountyDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void setInviterBounty() {
    	
    	InviterBountyDO record = new InviterBountyDO();
    	record.setAuditorId(1000l);
    	record.setGmtCreate(new Date());
    	record.setGmtModified(new Date());
    	record.setMoney(BigDecimal.valueOf(5));
    	record.setSendCount(10);
    	record.setStatus(PlatformRedPacketStatusEnum.ENABLE.val);
    	inviterBountyDOMapper.insertSelective(record);
    	
    	inviterBountyService.setInviterBounty(record.getId(), 1002l);
    	
    	InviterBountyDOExample example = new InviterBountyDOExample();
    	example.createCriteria().andStatusEqualTo(PlatformRedPacketStatusEnum.DISENABLE.val);
    	
        List<InviterBountyDO> list = inviterBountyDOMapper.selectByExample(example);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void queryInviterBounty() {
    	
    	InviterBountyDO record = new InviterBountyDO();
    	record.setAuditorId(1000l);
    	record.setGmtCreate(new Date());
    	record.setGmtModified(new Date());
    	record.setMoney(BigDecimal.valueOf(5));
    	record.setSendCount(10);
    	record.setStatus(PlatformRedPacketStatusEnum.ENABLE.val);
    	inviterBountyDOMapper.insertSelective(record);
    	
    	InviterBountyQueryParam param = new InviterBountyQueryParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	Page<InviterBountyBO> page = inviterBountyService.queryInviterBounty(param);
    	
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() == 1);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getInviterBounty() {
    	
    	InviterBountyDO record = new InviterBountyDO();
    	record.setAuditorId(1000l);
    	record.setGmtCreate(new Date());
    	record.setGmtModified(new Date());
    	record.setMoney(BigDecimal.valueOf(5));
    	record.setSendCount(10);
    	record.setStatus(PlatformRedPacketStatusEnum.ENABLE.val);
    	inviterBountyDOMapper.insertSelective(record);
    	
    	TakeInviterBountyDetailDO takeRecord = new TakeInviterBountyDetailDO();
    	takeRecord.setGmtCreate(new Date());
    	takeRecord.setId(record.getId());
    	takeRecord.setPoint(record.getMoney());
    	takeRecord.setUserNum("M100000");
    	takeInviterBountyDetailDOMapper.insertSelective(takeRecord);
    	
    	GetInviterBountyBO bo = inviterBountyService.getInviterBounty("M100001","M100002", UserTypeEnum.MEMBER);
    	
        Assert.assertTrue(bo.getMoney().compareTo(BigDecimal.valueOf(0))==1);
    }

}
