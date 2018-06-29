package com.lawu.eshop.ad.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.ad.param.RateParam;
import com.lawu.eshop.ad.srv.bo.AdRateSettingBO;
import com.lawu.eshop.ad.srv.domain.AdRateSettingDO;
import com.lawu.eshop.ad.srv.mapper.AdRateSettingDOMapper;
import com.lawu.eshop.ad.srv.service.AdRateSettingService;
import com.lawu.framework.core.page.Page;

@Service
public class AdRateSettingServiceImpl implements AdRateSettingService {
	
	@Autowired
	private AdRateSettingDOMapper adRateSettingDOMapper;

	@Override
	public List<AdRateSettingBO> queryAdRateSetting() {
		List<AdRateSettingDO> list = adRateSettingDOMapper.selectByExample(null);
		
		List<AdRateSettingBO>  listSetting = new ArrayList<>();
		for (AdRateSettingDO adRateSettingDO : list) {
			AdRateSettingBO adRateSetting = new AdRateSettingBO();
			adRateSetting.setGameTime(adRateSettingDO.getGameTime());
			adRateSetting.setRate(adRateSettingDO.getRate());
			adRateSetting.setId(adRateSettingDO.getId());
			listSetting.add(adRateSetting);
		}
		return listSetting;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveRateSetting(int gameTime, int rate) {
		
		AdRateSettingDO record = new AdRateSettingDO();
		record.setGameTime(gameTime);
		record.setGmtCreate(new Date());
		record.setRate(rate);
		adRateSettingDOMapper.insertSelective(record);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteRateSetting(Long id) {
		
		adRateSettingDOMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public Page<AdRateSettingBO> queryRatePage(RateParam param) {
		Long count=adRateSettingDOMapper.countByExample(null);
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<AdRateSettingDO> list = adRateSettingDOMapper.selectByExampleWithRowbounds(null, rowBounds);
		
		List<AdRateSettingBO>  listSetting = new ArrayList<>();
		for (AdRateSettingDO adRateSettingDO : list) {
			AdRateSettingBO adRateSetting = new AdRateSettingBO();
			adRateSetting.setGameTime(adRateSettingDO.getGameTime());
			adRateSetting.setRate(adRateSettingDO.getRate());
			adRateSetting.setId(adRateSettingDO.getId());
			listSetting.add(adRateSetting);
		}
		Page<AdRateSettingBO> page = new Page<>();
		page.setCurrentPage(param.getCurrentPage());
		page.setRecords(listSetting);
		page.setTotalCount(count.intValue());
		
		return page;
	}

}
