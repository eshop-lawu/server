package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.dto.RichPowerTaskDTO;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.RichPowerTaskRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月4日
 */
@Api(tags = "richPowerTaskRecord")
@RestController
@RequestMapping("richPowerTaskRecord/")
public class RichPowerTaskRecordController extends BaseController{

    @Autowired
    private RichPowerTaskRecordService richPowerTaskRecordService;


    @Audit(date = "2018-05-07", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @Authorization
	@ApiOperation(value = "获取所有动力任务已经完成情况", notes = "获取所有动力任务已经完成情况(张荣成)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getPowerTasks", method = RequestMethod.GET)
    public Result<RichPowerTaskDTO> getPowerTasks(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token){
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	Result<RichPowerTaskDTO> result =richPowerTaskRecordService.getPowerTasks(userNum);
    	return successGet(result);
    }
    
   
    


}
