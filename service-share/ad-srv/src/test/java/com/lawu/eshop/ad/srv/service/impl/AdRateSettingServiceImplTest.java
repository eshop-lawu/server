package com.lawu.eshop.ad.srv.service.impl;

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

import com.lawu.eshop.ad.param.RateParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.bo.AdRateSettingBO;
import com.lawu.eshop.ad.srv.domain.AdRateSettingDO;
import com.lawu.eshop.ad.srv.mapper.AdRateSettingDOMapper;
import com.lawu.eshop.ad.srv.service.AdRateSettingService;
import com.lawu.framework.core.page.Page;

/**
 * 
 * @Description E咻中奖率单元测试
 * @author zhangrc
 * @date 2017年11月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
public class AdRateSettingServiceImplTest {
	
	@Autowired
	private AdRateSettingService adRateSettingService;
	
	@Autowired
	private AdRateSettingDOMapper adRateSettingDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveRateSetting() {

		adRateSettingService.saveRateSetting(1, 90);
		
        List<AdRateSettingDO> list = adRateSettingDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void queryAdRateSetting() {
		
		AdRateSettingDO record = new AdRateSettingDO();
		record.setGameTime(1);
		record.setGmtCreate(new Date());
		record.setRate(20);
		adRateSettingDOMapper.insertSelective(record);
		
		List<AdRateSettingBO> list =  adRateSettingService.queryAdRateSetting();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void deleteRateSetting() {
		
		AdRateSettingDO record = new AdRateSettingDO();
		record.setGameTime(1);
		record.setGmtCreate(new Date());
		record.setRate(20);
		adRateSettingDOMapper.insertSelective(record);
		
		adRateSettingService.deleteRateSetting(record.getId());
		List<AdRateSettingDO> list = adRateSettingDOMapper.selectByExample(null);
        Assert.assertNull(list);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void queryRatePage() {
		
		AdRateSettingDO record = new AdRateSettingDO();
		record.setGameTime(1);
		record.setGmtCreate(new Date());
		record.setRate(20);
		adRateSettingDOMapper.insertSelective(record);
		
		RateParam param = new RateParam();
		param.setCurrentPage(1);
		param.setPageSize(20);
		Page<AdRateSettingBO> page = adRateSettingService.queryRatePage(param);
		
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() == 1);
    }

}
