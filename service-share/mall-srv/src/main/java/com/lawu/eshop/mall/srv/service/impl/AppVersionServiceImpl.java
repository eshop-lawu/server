package com.lawu.eshop.mall.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.mall.constants.AppStatusEnum;
import com.lawu.eshop.mall.param.AppVersionOperatorParam;
import com.lawu.eshop.mall.param.AppVersionParam;
import com.lawu.eshop.mall.srv.bo.AppVersionBO;
import com.lawu.eshop.mall.srv.bo.AppVersionOperatorBO;
import com.lawu.eshop.mall.srv.converter.AppVersionConverter;
import com.lawu.eshop.mall.srv.domain.AppVersionDO;
import com.lawu.eshop.mall.srv.domain.AppVersionDOExample;
import com.lawu.eshop.mall.srv.mapper.AppVersionDOMapper;
import com.lawu.eshop.mall.srv.service.AppVersionService;
import com.lawu.framework.core.page.Page;

@Service
public class AppVersionServiceImpl implements AppVersionService{

	@Autowired
	private AppVersionDOMapper appVersionDOMapper;
	
	@Override
	public AppVersionBO getVersion(byte mobileType , byte appType) {
		AppVersionDOExample example = new AppVersionDOExample();
		example.createCriteria().andStatusEqualTo(AppStatusEnum.ENABLE.val).andAppTypeEqualTo(appType).andPlatformEqualTo(mobileType);
		List<AppVersionDO> list = appVersionDOMapper.selectByExample(example);
		if(list != null && !list.isEmpty()) {
			AppVersionBO bo = new AppVersionBO();
			AppVersionDO DO = list.get(0);
			bo.setAppBigVersion(DO.getAppBigVersion());
			bo.setAppVersion(DO.getAppVersion());
			bo.setStatus(DO.getStatus());
			bo.setUpdateDesc(DO.getUpdateDesc());
			bo.setIsForce(DO.getIsForce());
			return bo;
		}else{
			return null;
		}
		
	}

	@Override
	public void saveAppVersion(AppVersionParam param) {
		
		AppVersionDO appVersion = new AppVersionDO();
		appVersion.setAppBigVersion(param.getAppBigVersion());
		appVersion.setAppType(param.getAppType().val);
		appVersion.setAppVersion(param.getAppVersion());
		appVersion.setIsForce(param.getIsForce());
		appVersion.setPlatform(param.getMobileType().val);
		appVersion.setStatus(AppStatusEnum.ENABLE.val);
		appVersion.setUpdateDesc(param.getUpdateDesc());
		appVersion.setGmtCreate(new Date());
		
		appVersionDOMapper.insert(appVersion);
	}
 
	
	@Override
	public void updateAppVersion(Integer id,AppStatusEnum statusEnum) {
		AppVersionDO appVersion = new AppVersionDO();
		appVersion.setId(id);
		appVersion.setStatus(statusEnum.val);
		appVersionDOMapper.updateByPrimaryKeySelective(appVersion);
	}

	@Override
	public Page<AppVersionOperatorBO> getVersionOperator(AppVersionOperatorParam query) {
		AppVersionDOExample example = new AppVersionDOExample();
		example.createCriteria().andStatusNotEqualTo(AppStatusEnum.DELETE.val);
		
		example.setOrderByClause("gmt_create desc");
		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		 
		List<AppVersionDO> list = appVersionDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		
		Long count = appVersionDOMapper.countByExample(example);
		
		Page<AppVersionOperatorBO> page = new Page<>();
		page.setCurrentPage(query.getCurrentPage());
		page.setTotalCount(count.intValue());
		page.setRecords(AppVersionConverter.converterBOS(list));
		
		return page;
	}
	
	@Override
	public Boolean isExistEnable(byte mobileType , byte appType) {
		AppVersionDOExample example = new AppVersionDOExample();
		example.createCriteria().andStatusEqualTo(AppStatusEnum.ENABLE.val).andAppTypeEqualTo(appType).andPlatformEqualTo(mobileType);
		Long count = appVersionDOMapper.countByExample(example);
		return count>0 ? true:false;
	}

	@Override
	public Boolean isExistEnable(Integer id) {
		AppVersionDO appVersion = appVersionDOMapper.selectByPrimaryKey(id);
		AppVersionDOExample example = new AppVersionDOExample();
		example.createCriteria().andStatusEqualTo(AppStatusEnum.ENABLE.val).andAppTypeEqualTo(appVersion.getAppType()).andPlatformEqualTo(appVersion.getPlatform());
		Long count = appVersionDOMapper.countByExample(example);
		return count>0 ? true:false;
	}
}
