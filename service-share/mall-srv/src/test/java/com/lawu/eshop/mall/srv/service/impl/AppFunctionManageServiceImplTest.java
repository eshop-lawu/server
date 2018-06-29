package com.lawu.eshop.mall.srv.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.param.AppFunctionManageParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.AppFunctionManageBO;
import com.lawu.eshop.mall.srv.bo.AppFunctionManageInfoBO;
import com.lawu.eshop.mall.srv.domain.AppFunctionManageDO;
import com.lawu.eshop.mall.srv.domain.AppFunctionManageDOExample;
import com.lawu.eshop.mall.srv.mapper.AppFunctionManageDOMapper;
import com.lawu.eshop.mall.srv.service.AppFunctionManageService;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月12日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class AppFunctionManageServiceImplTest {
	
	@Autowired
	private AppFunctionManageDOMapper appFunctionManageDOMapper;
	
	@Autowired
	private AppFunctionManageService appFunctionManageService;
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAppFunctionManage() {
		AppFunctionManageBO  bo  = appFunctionManageService.getAppFunctionManage("v2.8.0.22.t",UserTypeEnum.MEMBER);
        Assert.assertNotNull(bo);
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAppFunctionManageInfo() {
		AppFunctionManageInfoBO  bo  = appFunctionManageService.getAppFunctionManageInfo();
        Assert.assertNotNull(bo);
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveAppFunctionManage() {
		AppFunctionManageParam param = new AppFunctionManageParam();
		param.setAppVersion("v2.8.0.22.t");
		param.setId(1l);
		param.setIsEnable(true);
		param.setIsEnableGame(true);
		param.setIsEnableLove(false);
		appFunctionManageService.saveAppFunctionManage(param);
		AppFunctionManageDOExample example = new AppFunctionManageDOExample();
		example.createCriteria().andIsEnableGameEqualTo(true);
		List<AppFunctionManageDO> list = appFunctionManageDOMapper.selectByExample(example);
		Assert.assertEquals(1, list.size());
    }

}
