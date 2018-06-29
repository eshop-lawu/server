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

import com.lawu.eshop.ad.constants.PlatformRedPacketStatusEnum;
import com.lawu.eshop.ad.param.PlatformRedPacketParam;
import com.lawu.eshop.ad.param.PlatformRedPacketQueryParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.bo.GetPlatformRedPacketBO;
import com.lawu.eshop.ad.srv.bo.PlatformRedPacketBO;
import com.lawu.eshop.ad.srv.domain.PlatformRedPacketDO;
import com.lawu.eshop.ad.srv.domain.PlatformRedPacketDOExample;
import com.lawu.eshop.ad.srv.domain.TakePlatformRedPacketDO;
import com.lawu.eshop.ad.srv.mapper.PlatformRedPacketDOMapper;
import com.lawu.eshop.ad.srv.mapper.TakePlatformRedPacketDOMapper;
import com.lawu.eshop.ad.srv.service.PlatformRedPacketService;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangrc
 * @date 2017/10/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
public class PlatformRedPacketServiceImplTest {

	@Autowired
	private PlatformRedPacketDOMapper platformRedPacketDOMapper;
	
	@Autowired
	private PlatformRedPacketService platformRedPacketService;
	
	@Autowired
	private TakePlatformRedPacketDOMapper takePlatformRedPacketDOMapper;
   
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveRedPacket() {

    	PlatformRedPacketParam param = new PlatformRedPacketParam();
    	param.setAuditorId(1000l);
    	param.setMoney(BigDecimal.valueOf(5));
    	platformRedPacketService.saveRedPacket(param);
    	
        List<PlatformRedPacketDO> list = platformRedPacketDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void setRedPacket() {
    	
    	PlatformRedPacketDO record = new PlatformRedPacketDO();
    	record.setAuditorId(1000l);
    	record.setGmtCreate(new Date());
    	record.setGmtModified(new Date());
    	record.setMoney(BigDecimal.valueOf(5));
    	record.setSendCount(10);
    	record.setStatus(PlatformRedPacketStatusEnum.ENABLE.val);
    	platformRedPacketDOMapper.insertSelective(record);
    	
    	platformRedPacketService.setRedPacket(record.getId(), 1002l);
    	
    	PlatformRedPacketDOExample example = new PlatformRedPacketDOExample();
    	example.createCriteria().andStatusEqualTo(PlatformRedPacketStatusEnum.DISENABLE.val);
    	
        List<PlatformRedPacketDO> list = platformRedPacketDOMapper.selectByExample(example);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void queryRedPacket() {
    	
    	PlatformRedPacketDO record = new PlatformRedPacketDO();
    	record.setAuditorId(1000l);
    	record.setGmtCreate(new Date());
    	record.setGmtModified(new Date());
    	record.setMoney(BigDecimal.valueOf(5));
    	record.setSendCount(10);
    	record.setStatus(PlatformRedPacketStatusEnum.ENABLE.val);
    	platformRedPacketDOMapper.insertSelective(record);
    	
    	PlatformRedPacketQueryParam param = new PlatformRedPacketQueryParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	Page<PlatformRedPacketBO> page = platformRedPacketService.queryRedPacket(param);
    	
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() == 1);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRedPacket() {
    	
    	PlatformRedPacketDO record = new PlatformRedPacketDO();
    	record.setAuditorId(1000l);
    	record.setGmtCreate(new Date());
    	record.setGmtModified(new Date());
    	record.setMoney(BigDecimal.valueOf(5));
    	record.setSendCount(10);
    	record.setStatus(PlatformRedPacketStatusEnum.ENABLE.val);
    	platformRedPacketDOMapper.insertSelective(record);
    	
    	TakePlatformRedPacketDO takeRecord = new TakePlatformRedPacketDO();
    	takeRecord.setGmtCreate(new Date());
    	takeRecord.setId(record.getId());
    	takeRecord.setPoint(record.getMoney());
    	takeRecord.setUserNum("M100000");
    	takePlatformRedPacketDOMapper.insertSelective(takeRecord);
    	
    	GetPlatformRedPacketBO bo = platformRedPacketService.getRedPacket("M100001");
    	
        Assert.assertTrue(bo.getMoney().compareTo(BigDecimal.valueOf(0))==1);
    }

}
