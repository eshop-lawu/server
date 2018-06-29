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

import com.lawu.eshop.ad.dto.InviterBountyDTO;
import com.lawu.eshop.ad.dto.SentTotalInviterBountyDTO;
import com.lawu.eshop.ad.param.InviterBountyParam;
import com.lawu.eshop.ad.param.InviterBountyQueryParam;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.InviterBountyService;
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
 * 描述：邀请注册奖励金管理
 *
 * @author zhangrc
 * @date 2017/12/21
 */
@Api(tags = "inviterBounty")
@RestController
@RequestMapping(value = "inviterBounty/")
public class InviterBountyController extends BaseController{
	
	@Autowired
	private InviterBountyService inviterBountyService;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "奖励金设置", notes = "奖励金设置,[]（张荣成）", httpMethod = "POST")
	@LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.INVITER_BOUNTY_ABLE)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("inviterBounty:save")
    @RequestMapping(value = "saveInviterBounty", method = RequestMethod.POST)
    public Result saveInviterBounty(@RequestParam @ApiParam(required = true, value = "金额") BigDecimal money) {
		Long auditorId = 0l;
        Result<UserListDTO> userResult = userService.getUserByAccount(UserUtil.getCurrentUserAccount());
        if(isSuccess(userResult)){
            auditorId = Long.parseLong(userResult.getModel().getId().toString());
        }
        
		InviterBountyParam param = new  InviterBountyParam();
		param.setAuditorId(auditorId);
		param.setMoney(money);
		return inviterBountyService.saveInviterBounty(param);
    }
	
	@ApiOperation(value = "禁用奖励金", notes = "禁用奖励金,[]（张荣成）", httpMethod = "PUT")
	@LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.INVITER_BOUNTY_DISABLE,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("inviterBounty:edit")
    @RequestMapping(value = "setInviterBounty/{id}", method = RequestMethod.PUT)
    public Result setInviterBounty(@PathVariable @ApiParam(required = true, value = "id") Long id) {
		Long auditorId = 0l;
        Result<UserListDTO> userResult = userService.getUserByAccount(UserUtil.getCurrentUserAccount());
        if(isSuccess(userResult)){
            auditorId = Long.parseLong(userResult.getModel().getId().toString());
        }
		return inviterBountyService.setInviterBounty(id, auditorId);
    }

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "奖励金列表", notes = "平台红包列表,[]（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
	@PageBody
	@RequiresPermissions("inviterBounty:list")
    @RequestMapping(value = "queryInviterBounty", method = RequestMethod.GET)
    public Result<Page<InviterBountyDTO>> queryInviterBounty(@ModelAttribute @ApiParam InviterBountyQueryParam query) {
		
		Result<Page<InviterBountyDTO>> result = inviterBountyService.queryInviterBounty(query);
		if(!result.getModel().getRecords().isEmpty()){
			for (InviterBountyDTO dto : result.getModel().getRecords()) {
				Result<UserListDTO> userListDTOResult = userService.findUserById(Integer.valueOf(dto.getAuditorId().toString()));
		        if (isSuccess(userListDTOResult)) {
		        	dto.setAuditorName(userListDTOResult.getModel().getName());
		        }
			}
			
		}
		
		return successGet(result);
    }
	
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "统计奖励金", notes = "统计奖励金,[]（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "queryInviterBountyTotalMoney", method = RequestMethod.GET)
    public Result<SentTotalInviterBountyDTO> queryInviterBountyTotalMoney(@ModelAttribute @ApiParam InviterBountyQueryParam query) {
		
		Result<SentTotalInviterBountyDTO> result = inviterBountyService.queryInviterBountyTotalMoney();
		
		return successGet(result);
    }
}
