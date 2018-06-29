package com.lawu.eshop.ad.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.AdRateSettingDTO;
import com.lawu.eshop.ad.param.RateParam;
import com.lawu.eshop.ad.srv.bo.AdRateSettingBO;
import com.lawu.eshop.ad.srv.service.AdRateSettingService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 咻一咻中奖率配置接口
 * 
 * @author zhangrc
 * @date 2017/11/23
 *
 */
@RestController
@RequestMapping(value = "adRateSetting/")
public class AdRateSettingController extends BaseController{
	
	@Autowired
	private AdRateSettingService adRateSettingService;
	
	
	/**
	 * 咻一咻中奖率配置数据
	 * @return
	 */
	@RequestMapping(value = "queryAdRateSetting", method = RequestMethod.GET)
	public Result<List<AdRateSettingDTO>> queryAdRateSetting() {

		List<AdRateSettingBO> list = adRateSettingService.queryAdRateSetting();

		List<AdRateSettingDTO> rateList = new ArrayList<>();
		for (AdRateSettingBO adRateSettingBO : list) {
			AdRateSettingDTO adRateSetting = new AdRateSettingDTO();
			adRateSetting.setGameTime(adRateSettingBO.getGameTime());
			adRateSetting.setRate(adRateSettingBO.getRate());
			adRateSetting.setId(adRateSettingBO.getId());
			rateList.add(adRateSetting);
		}

		return successGet(rateList);

	}
	
	/**
	 * 保存中奖率设置
	 * @param gameTime
	 * @param rate
	 * @return
	 */
	@RequestMapping(value = "saveRateSetting", method = RequestMethod.POST)
	public Result saveRateSetting(@RequestParam int gameTime,@RequestParam int rate){
		
		adRateSettingService.saveRateSetting(gameTime, rate);
		
		return successCreated();
		
	}
	
	/**
	 * 删除中奖率设置
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteRateSetting/{id}", method = RequestMethod.DELETE)
	public Result deleteRateSetting(@PathVariable Long id){
		
		adRateSettingService.deleteRateSetting(id);
		
		return successCreated();
		
	}
	
	@RequestMapping(value = "queryRatePage", method = RequestMethod.POST)
	public Result<Page<AdRateSettingDTO>> queryRatePage(@RequestBody RateParam param) {
		Page<AdRateSettingBO>  page = adRateSettingService.queryRatePage(param);

		List<AdRateSettingDTO> rateList = new ArrayList<>();
		for (AdRateSettingBO adRateSettingBO : page.getRecords()) {
			
			AdRateSettingDTO adRateSetting = new AdRateSettingDTO();
			adRateSetting.setGameTime(adRateSettingBO.getGameTime());
			adRateSetting.setRate(adRateSettingBO.getRate());
			adRateSetting.setId(adRateSettingBO.getId());
			rateList.add(adRateSetting);
		}
		
		Page<AdRateSettingDTO> ratePage = new Page<>();
		ratePage.setCurrentPage(page.getCurrentPage());
		ratePage.setRecords(rateList);
		ratePage.setTotalCount(page.getTotalCount());

		return successCreated(ratePage);
	}

}
