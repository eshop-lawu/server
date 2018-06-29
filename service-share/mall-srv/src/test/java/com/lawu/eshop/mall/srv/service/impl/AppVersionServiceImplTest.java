package com.lawu.eshop.mall.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.mall.constants.AppStatusEnum;
import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.eshop.mall.constants.MobileTypeEnum;
import com.lawu.eshop.mall.param.AppVersionOperatorParam;
import com.lawu.eshop.mall.param.AppVersionParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.AppVersionOperatorBO;
import com.lawu.eshop.mall.srv.domain.AppVersionDO;
import com.lawu.eshop.mall.srv.domain.AppVersionDOExample;
import com.lawu.eshop.mall.srv.mapper.AppVersionDOMapper;
import com.lawu.eshop.mall.srv.service.AppVersionService;
import com.lawu.framework.core.page.Page;

/**
 * app 版本管理单元测试
 * 
 * @author zhangrc
 * @date 2017/09/26
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class AppVersionServiceImplTest {
	
	@Autowired
	private AppVersionService appVersionService;
	
	@Autowired
	private AppVersionDOMapper appVersionDOMapper;
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveAppVersion() {
		AppVersionParam param = new AppVersionParam();
		param.setAppBigVersion("2.4.18.t");
		param.setAppType(AppTypeEnum.MEMBER);
		param.setAppBigVersion("2");
		param.setIsForce(false);
		param.setMobileType(MobileTypeEnum.Android);
		param.setUpdateDesc("更新内容");
		
		appVersionService.saveAppVersion(param);

		List<AppVersionDO> list = appVersionDOMapper.selectByExample(null);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() == 1);

	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getVersion() {
		
		AppVersionDO appVersion = new AppVersionDO();
		appVersion.setAppBigVersion("2");
		appVersion.setAppType(AppTypeEnum.MEMBER.val);
		appVersion.setAppVersion("2.4.18.t");
		appVersion.setIsForce(false);
		appVersion.setPlatform(MobileTypeEnum.Android.val);
		appVersion.setStatus(AppStatusEnum.ENABLE.val);
		appVersion.setUpdateDesc("更新内容");
		appVersion.setGmtCreate(new Date());
		
		appVersionDOMapper.insert(appVersion);
		
		appVersionService.getVersion(appVersion.getPlatform(), appVersion.getAppType());

		List<AppVersionDO> list = appVersionDOMapper.selectByExample(null);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() == 1);

	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void updateAppVersion() {
		
		AppVersionDO appVersion = new AppVersionDO();
		appVersion.setAppBigVersion("2");
		appVersion.setAppType(AppTypeEnum.MEMBER.val);
		appVersion.setAppVersion("2.4.18.t");
		appVersion.setIsForce(false);
		appVersion.setPlatform(MobileTypeEnum.Android.val);
		appVersion.setStatus(AppStatusEnum.ENABLE.val);
		appVersion.setUpdateDesc("更新内容");
		appVersion.setGmtCreate(new Date());
		
		appVersionDOMapper.insert(appVersion);
		
		appVersionService.updateAppVersion(appVersion.getId(), AppStatusEnum.DISENABLE);
		
		AppVersionDOExample example = new AppVersionDOExample();
		example.createCriteria().andStatusEqualTo(AppStatusEnum.DISENABLE.val);
		List<AppVersionDO> list = appVersionDOMapper.selectByExample(example);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() == 1);

	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getVersionOperator() {
		
		AppVersionDO appVersion = new AppVersionDO();
		appVersion.setAppBigVersion("2");
		appVersion.setAppType(AppTypeEnum.MEMBER.val);
		appVersion.setAppVersion("2.4.18.t");
		appVersion.setIsForce(false);
		appVersion.setPlatform(MobileTypeEnum.Android.val);
		appVersion.setStatus(AppStatusEnum.ENABLE.val);
		appVersion.setUpdateDesc("更新内容");
		appVersion.setGmtCreate(new Date());
		
		appVersionDOMapper.insert(appVersion);
		
		AppVersionOperatorParam query = new AppVersionOperatorParam();
		query.setCurrentPage(1);
		query.setPageSize(10);
		Page<AppVersionOperatorBO>  page = appVersionService.getVersionOperator(query);
		
		Assert.assertNotNull(page.getRecords());
		Assert.assertTrue(page.getRecords().size() == 1);

	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void isExistEnable() {
		
		AppVersionDO appVersion = new AppVersionDO();
		appVersion.setAppBigVersion("2");
		appVersion.setAppType(AppTypeEnum.MEMBER.val);
		appVersion.setAppVersion("2.4.18.t");
		appVersion.setIsForce(false);
		appVersion.setPlatform(MobileTypeEnum.Android.val);
		appVersion.setStatus(AppStatusEnum.ENABLE.val);
		appVersion.setUpdateDesc("更新内容");
		appVersion.setGmtCreate(new Date());
		
		appVersionDOMapper.insert(appVersion);
		
		Boolean flag = appVersionService.isExistEnable(appVersion.getPlatform(), appVersion.getAppType());
		
		Assert.assertTrue(flag);

	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void isExistEnable2() {
		
		AppVersionDO appVersion = new AppVersionDO();
		appVersion.setAppBigVersion("2");
		appVersion.setAppType(AppTypeEnum.MEMBER.val);
		appVersion.setAppVersion("2.4.18.t");
		appVersion.setIsForce(false);
		appVersion.setPlatform(MobileTypeEnum.Android.val);
		appVersion.setStatus(AppStatusEnum.ENABLE.val);
		appVersion.setUpdateDesc("更新内容");
		appVersion.setGmtCreate(new Date());
		
		appVersionDOMapper.insert(appVersion);
		
		Boolean flag = appVersionService.isExistEnable(appVersion.getId());
		
		Assert.assertTrue(flag);

	}
	

}
