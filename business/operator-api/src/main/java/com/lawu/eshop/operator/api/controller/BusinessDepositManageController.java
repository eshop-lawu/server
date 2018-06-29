package com.lawu.eshop.operator.api.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.BusinessDepositManageService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.property.dto.BusinessDepositQueryDTO;
import com.lawu.eshop.property.param.BusinessDepositOperDataParam;
import com.lawu.eshop.property.param.BusinessDepositOperParam;
import com.lawu.eshop.property.param.BusinessDepositQueryDataParam;
import com.lawu.eshop.property.param.BusinessDepositQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.shiro.util.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Description: 运营平台商家保证金管理
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月15日 下午3:15:16
 *
 */
@Api(tags = "businessDepositBackage")
@RestController
@RequestMapping(value = "businessDepositBackage/")
public class BusinessDepositManageController extends BaseController {

	@Autowired
	private BusinessDepositManageService businessDepositManageService;

	/**
	 * 运营平台财务提现管理
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PageBody
	@ApiOperation(value = "保证金明细查询", notes = "保证金明细查询,[]（杨清华）", httpMethod = "POST")
	@RequestMapping(value = "selectDepositList", method = RequestMethod.POST)
	@RequiresPermissions("depositcash:list")
	public Result<Page<BusinessDepositQueryDTO>> selectDepositList(@RequestBody BusinessDepositQueryParam param) {
		BusinessDepositQueryDataParam dparam = new BusinessDepositQueryDataParam();
		dparam.setContent(param.getContent());
		dparam.setRegionPath(param.getRegionPath());
		dparam.setBeginDate(param.getBeginDate());
		dparam.setEndDate(param.getEndDate());
		dparam.setBusinessDepositStatusEnum(param.getBusinessDepositStatusEnum());
		dparam.setTransactionPayTypeEnum(param.getTransactionPayTypeEnum());
		dparam.setCurrentPage(param.getCurrentPage());
		dparam.setPageSize(param.getPageSize());
		dparam.setSortOrder(param.getSortOrder());
		dparam.setSortName(param.getSortName());
		Result<Page<BusinessDepositQueryDTO>> dtos = businessDepositManageService.selectDepositList(dparam);
		return dtos;
	}

	@SuppressWarnings("rawtypes")
	@LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.BUSINESS_DEPOSIT_UPDATE,idParamName ="param.id")
	@ApiOperation(value = "保证金运营处理操作", notes = "保证金运营处理操作(核实、受理、成功、失败),[]（杨清华）", httpMethod = "POST")
	@RequiresPermissions("depositcash:edit")
	@RequestMapping(value = "updateBusinessDeposit", method = RequestMethod.POST)
	public Result updateBusinessDeposit(@Valid BusinessDepositOperParam param, BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
		
		BusinessDepositOperDataParam dparam = new BusinessDepositOperDataParam();
		dparam.setId(param.getId());
		dparam.setBusinessDepositOperEnum(param.getBusinessDepositOperEnum());
		dparam.setFailReason(param.getFailReason());
		dparam.setUserNum(param.getUserNum());
		dparam.setOperUserId(0L);
		dparam.setOperUserName(UserUtil.getCurrentUserAccount());
		dparam.setBusinessId(param.getBusinessId());
		return businessDepositManageService.updateBusinessDeposit(dparam);
	}
}
