package com.lawu.eshop.operator.api.controller;

import java.math.BigDecimal;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.PlatformRedPacketDTO;
import com.lawu.eshop.ad.param.PlatformRedPacketParam;
import com.lawu.eshop.ad.param.PlatformRedPacketQueryParam;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.PlatformRedPacketService;
import com.lawu.eshop.operator.api.service.UserService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.operator.dto.UserListDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.shiro.util.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 描述：平台红包管理
 *
 * @author zhangrc
 * @date 2017/10/18
 */
@Api(tags = "platformRedPacket")
@RestController
@RequestMapping(value = "platformRedPacket/")
public class PlatformRedPacketController extends BaseController{
	
	@Autowired
	private PlatformRedPacketService platformRedPacketService;
	
	@Autowired
	private UserService userService;
	
	
	@ApiOperation(value = "红包设置", notes = "红包设置,[]（张荣成）", httpMethod = "POST")
	@LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.PLAT_REDPACKET_ADD)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("redPacket:save")
    @RequestMapping(value = "saveRedPacket", method = RequestMethod.POST)
    public Result saveRedPacket(@RequestParam @ApiParam(required = true, value = "金额") BigDecimal money) {
		Long auditorId = 0l;
        Result<UserListDTO> userResult = userService.getUserByAccount(UserUtil.getCurrentUserAccount());
        if(isSuccess(userResult)){
            auditorId = Long.parseLong(userResult.getModel().getId().toString());
        }
        
		PlatformRedPacketParam param = new  PlatformRedPacketParam();
		param.setAuditorId(auditorId);
		param.setMoney(money);
		return platformRedPacketService.saveRedPacket(param);
    }
	
	@LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.PLAT_REDPACKET_DISABLE,idParamName ="id")
	@ApiOperation(value = "禁用红包", notes = "禁用红包,[]（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("redPacket:edit")
    @RequestMapping(value = "setRedPacket/{id}", method = RequestMethod.PUT)
    public Result setRedPacket(@PathVariable @ApiParam(required = true, value = "id") Long id) {
		Long auditorId = 0l;
        Result<UserListDTO> userResult = userService.getUserByAccount(UserUtil.getCurrentUserAccount());
        if(isSuccess(userResult)){
            auditorId = Long.parseLong(userResult.getModel().getId().toString());
        }
		return platformRedPacketService.setRedPacket(id, auditorId);
    }

	@ApiOperation(value = "平台红包列表", notes = "平台红包列表,[]（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
	@PageBody
	@RequiresPermissions("redPacket:list")
    @RequestMapping(value = "queryRedPacket", method = RequestMethod.GET)
    public Result<Page<PlatformRedPacketDTO>> queryRedPacket(@ModelAttribute @ApiParam PlatformRedPacketQueryParam query) {
		
		Result<Page<PlatformRedPacketDTO>> result = platformRedPacketService.queryRedPacket(query);
		if(!result.getModel().getRecords().isEmpty()){
			for (PlatformRedPacketDTO dto : result.getModel().getRecords()) {
				Result<UserListDTO> userListDTOResult = userService.findUserById(Integer.valueOf(dto.getAuditorId().toString()));
		        if (isSuccess(userListDTOResult)) {
		        	dto.setAuditorName(userListDTOResult.getModel().getName());
		        }
			}
			
		}
		
		return result;
    }
}
