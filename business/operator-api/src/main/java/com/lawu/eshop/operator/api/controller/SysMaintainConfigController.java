package com.lawu.eshop.operator.api.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.cache.dto.SysMaintainConfigDTO;
import com.lawu.eshop.cache.dto.WriteFileDTO;
import com.lawu.eshop.cache.param.SysMaintainConfigParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.operator.api.OperatorApiConfig;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.SysMaintainConfigService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/** 
 * 系统维护提示
 * @author lihj
 * @date 2018年5月10日
 */
@Api(tags = "sysMaintain")
@RestController
@RequestMapping(value = "sysMaintain/")
public class SysMaintainConfigController extends BaseController{

	@Autowired
	private SysMaintainConfigService sysMaintainConfigService;
	@Autowired
	private OperatorApiConfig operatorApiConfig;
	
	@RequestMapping(value="getSysMaintainConfig",method=RequestMethod.GET)
	public Result<SysMaintainConfigDTO> getSysMaintainConfig(){
		return sysMaintainConfigService.getSysMaintainConfig();
	}
	
	
	@LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.ADD_SYS_MAINTAIN_CONFIG)
    @ApiOperation(value = "新增系统维护设置", notes = "新增系统维护设置（李洪军）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value="updateSysMaintainConfig",method=RequestMethod.POST)
	public Result updateSysMaintainConfig(@ModelAttribute SysMaintainConfigParam param){
		String fileUrl = operatorApiConfig.getFileUploadUrl();
		File file =new File(fileUrl);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (Exception e) {
				return successCreated(ResultCode.FAIL); 
			}
		}
		WriteFileDTO dto =new WriteFileDTO();
		dto.setRet(-1);
		dto.setMsg(JSON.toJSON(param.getContent()).toString());
		FileWriter fw;
		try {
			fw = new FileWriter(file,false);
			BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(JSON.toJSON(dto).toString());
		    bw.close();
		    fw.close();
		} catch (IOException e) {
			return successCreated(ResultCode.FAIL); 
		}
		return sysMaintainConfigService.updateSysMaintainConfig(param);
		
	}
	
	
}
