package com.lawu.eshop.cache.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.dto.SysMaintainConfigDTO;
import com.lawu.eshop.cache.param.SysMaintainConfigParam;
import com.lawu.eshop.cache.srv.service.SysMaintainConfigService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 系统维护缓存配置
 * 
 * @author lihj
 * @date 2018年5月10日
 */
@RestController
@RequestMapping(value = "sysMaintainCache/")
public class SysMaintainConfigController extends BaseController {

	@Autowired
	private SysMaintainConfigService sysMaintainConfigService;

	/**
	 * 设置配置信息
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "updateSysMaintainConfig", method = RequestMethod.POST)
	public Result updateSysMaintainConfig(@RequestBody SysMaintainConfigParam content) {
		sysMaintainConfigService.updateSysMaintainConfig(content.getContent());
		return successCreated();
	}

	/**
	 * 获取配置
	 * @return
	 */
	@RequestMapping(value = "getSysMaintainConfig", method = RequestMethod.GET)
	public Result<SysMaintainConfigDTO> getSysMaintainConfig() {
		String content = sysMaintainConfigService.getSysMaintainConfig();
		SysMaintainConfigDTO dto =new SysMaintainConfigDTO();
		dto.setContent(content);
		return successGet(dto);
	}

}
