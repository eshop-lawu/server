package com.lawu.eshop.activity.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.DiamondConfigDTO;
import com.lawu.eshop.activity.dto.PowerTaskConfigDTO;
import com.lawu.eshop.activity.param.DiamondConfigParam;
import com.lawu.eshop.activity.param.MerchantPowerTaskConfigParam;
import com.lawu.eshop.activity.param.PowerTaskConfigParam;
import com.lawu.eshop.activity.srv.bo.DiamondConfigBO;
import com.lawu.eshop.activity.srv.bo.PowerTaskConfigBO;
import com.lawu.eshop.activity.srv.bo.RichDiamondInfoBO;
import com.lawu.eshop.activity.srv.converter.RichConfigConverter;
import com.lawu.eshop.activity.srv.servcie.RichConfigService;
import com.lawu.eshop.activity.srv.servcie.RichDiamondInfoService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月3日
 */
@RestController
@RequestMapping(value = "richConfig/")
public class RichConfigController extends BaseController{
	
	@Autowired
	private RichConfigService richConfigService;
	
	@Autowired
	private RichDiamondInfoService richDiamondInfoService;
	
	/**
	 * 保存动力任务配置
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "savePowerTaskConfig", method = RequestMethod.POST)
    public Result savePowerTaskConfig(@RequestBody PowerTaskConfigParam param) {
		richConfigService.savePowerTaskConfig(param);
		return successCreated();
    }
	

	/**
	 * 获取动力任务配置
	 * 
	 * @return
	 */
	@RequestMapping(value = "getPowerTaskConfig", method = RequestMethod.GET)
    public Result<PowerTaskConfigDTO> getPowerTaskConfig() {
		PowerTaskConfigBO powerTaskConfigBO = richConfigService.getPowerTaskConfig();
		PowerTaskConfigDTO dto = RichConfigConverter.converterPowerTaskDTO(powerTaskConfigBO);
		return successGet(dto);
    }

	/**
	 * 保存E钻配置
	 *
	 * @param param
	 * @author meishuquan
	 */
	@RequestMapping(value = "saveDiamondConfig", method = RequestMethod.POST)
	public Result saveDiamondConfig(@RequestBody DiamondConfigParam param) {
		richConfigService.saveDiamondConfig(param);
		return successCreated();
	}

    /**
     * 获取E钻配置
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getDiamondConfig", method = RequestMethod.GET)
    public Result<DiamondConfigDTO> getDiamondConfig() {
        DiamondConfigBO configBO = richConfigService.getDiamondConfig();
        DiamondConfigDTO configDTO = RichConfigConverter.convertDiamondConfigDTO(configBO);
        RichDiamondInfoBO richDiamondInfo = richDiamondInfoService.getRichDiamondInfo();
        configDTO.setDiamondSent(richDiamondInfo.getDiamondSent());
        configDTO.setDiamondLuckySent(richDiamondInfo.getDiamondLuckySent());
        return successGet(configDTO);
    }
	
	/**
	 * 定时任务更新配置
	 * 
	 * @return
	 */
	@RequestMapping(value = "effectiveJob", method = RequestMethod.GET)
	public Result effectiveJob() {
		richConfigService.effectiveJob();
		return successGet();
	}
	
	
	@RequestMapping(value = "effectivePowerJob", method = RequestMethod.GET)
	public Result effectivePowerJob() {
		richConfigService.effectivePowerJob();
		return successGet();
	}

	/**
	 * 新增商家动力任务
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "saveMerchantPowerTaskConfig", method = RequestMethod.POST)
	public Result saveMerchantPowerTaskConfig(@RequestBody MerchantPowerTaskConfigParam param) {
		richConfigService.saveMerchantPowerTaskConfig(param);
		return successCreated();
	}
	

}
