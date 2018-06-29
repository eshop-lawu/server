package com.lawu.eshop.merchant.api.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.dto.ExpressCompanyDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyQueryDTO;
import com.lawu.eshop.mall.dto.ExpressCompanyRetrieveDTO;
import com.lawu.eshop.merchant.api.service.ExpressCompanyService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@Api(tags = "expressCompany")
@RestController
@RequestMapping(value = "expressCompany/")
public class ExpressCompanyController extends BaseController {

	@Autowired
	private ExpressCompanyService expressCompanyService;

	@SuppressWarnings("unchecked")
	@Audit(date = "2017-04-01", reviewer = "孙林青")
	@ApiOperation(value = "查询全部快递公司", notes = "查询全部快递公司，根据ordinal排序。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Result<List<ExpressCompanyDTO>> list(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {

		Result<List<ExpressCompanyDTO>> result = expressCompanyService.list();

		if (!isSuccess(result)) {
			return successGet(result.getRet());
		}

		return successGet(result);
	}

	@Audit(date = "2017-07-07", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "分组查询全部快递公司", notes = "查询全部快递公司,并且按照名称首字母分组。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "group", method = RequestMethod.GET)
    public Result<ExpressCompanyQueryDTO> group(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
    	return successGet(expressCompanyService.group());
    }

	@Audit(date = "2017-07-07", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "检索快递公司", notes = "根据关键字查询全部快递公司[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "keyWord", method = RequestMethod.GET)
    public Result<ExpressCompanyRetrieveDTO> listByKeyWord(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "关键字", required = true) @RequestParam("keyWord") String keyWord) {
    	if (StringUtils.isBlank(keyWord)) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, "关键字不能为空");
    	}
    	Result<ExpressCompanyRetrieveDTO> result = expressCompanyService.listByKeyWord(keyWord);
    	return successGet(result);
    }

}
