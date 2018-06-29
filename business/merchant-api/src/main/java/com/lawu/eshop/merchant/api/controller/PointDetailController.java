package com.lawu.eshop.merchant.api.controller;

import com.lawu.eshop.framework.web.impl.constants.UserConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.merchant.api.service.PointDetailService;
import com.lawu.eshop.property.dto.PointDetailDTO;
import com.lawu.eshop.property.param.PointDetailQueryParam;
import com.lawu.framework.core.page.Page;
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
 * @date 2017/3/30
 */
@Api(tags = "pointDetail")
@RestController
@RequestMapping(value = "pointDetail/")
public class PointDetailController extends BaseController {

	@Autowired
	private PointDetailService pointDetailService;

	/**
	 * 根据用户编号分页获取积分明细列表。
	 * 
	 * @param token
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@ApiOperation(value = "获取积分明细列表", notes = "根据用户编号分页获取积分明细列表。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "page", method = RequestMethod.GET)
	public Result<Page<PointDetailDTO>> page(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "查询资料") PointDetailQueryParam param) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());

		Result<Page<PointDetailDTO>> result = pointDetailService.findPageByUserNum(userNum, param);

		if (!isSuccess(result)) {
			return successGet(result.getRet());
		}

		return successGet(result);
	}

}
