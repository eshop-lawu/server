package com.lawu.eshop.member.api.controller;

import java.util.List;

import javax.annotation.Resource;

import com.lawu.eshop.framework.web.impl.constants.UserConstant;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.member.api.service.BankService;
import com.lawu.eshop.property.dto.BankDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangrc
 * @date 2017/3/29
 */
@Api(tags = "bank")
@RestController
@RequestMapping(value = "bank/")
public class BankController extends BaseController{
	
	@Resource 
	private BankService bankService;
	
	/**
	 * 查询所有银行
	 * @return
	 */
	@Audit(date = "2017-03-29", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "银行数据信息查询", notes = "银行数据信息查询[]（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "all", method = RequestMethod.GET)
    public Result<List<BankDTO>> findBank(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		Result<List<BankDTO>> DTOS = bankService.findBank();
		return  DTOS;
    }

}
