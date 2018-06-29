package com.lawu.eshop.member.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.FansMerchantService;
import com.lawu.eshop.member.api.service.ProductService;
import com.lawu.eshop.user.constants.FansMerchantChannelEnum;
import com.lawu.eshop.user.dto.FansMerchantQueryDTO;
import com.lawu.eshop.user.param.FansMerchantQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author meishuquan
 * @date 2017/4/6.
 */
@Api(tags = "fansMerchant")
@RestController
@RequestMapping(value = "fansMerchant/")
public class FansMerchantController extends BaseController {

	@Autowired
	private FansMerchantService fansMerchantService;
	
	@Autowired
	private ProductService productService;

	@Audit(date = "2017-04-21", reviewer = "孙林青")
	@ApiOperation(value = "成为商家粉丝", notes = "成为商家粉丝。 [2012] (梅述全)", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "saveFansMerchant", method = RequestMethod.PUT)
	public Result saveFansMerchant(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "商家ID") Long merchantId,
			@RequestParam @ApiParam(required = true, value = "粉丝来源") FansMerchantChannelEnum channelEnum) {
		long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<Boolean> result = fansMerchantService.isFansMerchant(merchantId, memberId);
		if (result.getModel()) {
			return successCreated(ResultCode.FANS_MERCHANT);
		}
		return fansMerchantService.saveFansMerchant(merchantId, memberId, channelEnum);
	}

	@Audit(date = "2018-06-08", reviewer = "孙林青")
	@ApiOperation(value = "取消成为商家粉丝", notes = "取消成为商家粉丝。 [2012] (李洪军)", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "removeFansMerchant", method = RequestMethod.PUT)
	public Result removeFansMerchant(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "商家ID") Long merchantId) {
		long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<Boolean> result = fansMerchantService.isFansMerchant(merchantId, memberId);
		if (result.getModel()) {
			return successCreated(fansMerchantService.removeFansMerchant(merchantId, memberId));
		} else {
			return successCreated(ResultCode.FANS_NOT_MERCHANT);
		}
	}

	@Audit(date = "2017-03-29", reviewer = "孙林青")
	@ApiOperation(value = "我关注的商家", notes = "我关注商家列表查询,[]（李洪军）", httpMethod = "GET")
	@Authorization
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getFansMerchantList", method = RequestMethod.GET)
	public Result<Page<FansMerchantQueryDTO>> getFansMerchantList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam(value = "查询信息") FansMerchantQueryParam pageQuery) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<Page<FansMerchantQueryDTO>> pageRs = fansMerchantService.getFansMerchantList(memberId, pageQuery);
		List<FansMerchantQueryDTO> list = pageRs.getModel().getRecords();
		Page<FansMerchantQueryDTO> newPage = new Page<>();
		newPage.setCurrentPage(pageQuery.getCurrentPage());
		newPage.setTotalCount(pageRs.getModel().getTotalCount());
		List<FansMerchantQueryDTO> newList = new ArrayList<>();
		if (!list.isEmpty()) {
			for (FansMerchantQueryDTO favoriteMerchantDTO : list) {
				Result<Integer> rsCount = productService.selectProductCount(favoriteMerchantDTO.getMerchantId());
				if (rsCount.getModel() == null) {
					favoriteMerchantDTO.setSaleCount(0);
				} else {
					favoriteMerchantDTO.setSaleCount(rsCount.getModel());
				}
				newList.add(favoriteMerchantDTO);
			}
		}
		newPage.setRecords(newList);
		return successGet(newPage);
	}

	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "用户处理商家邀请粉丝", notes = "用户处理商家邀请粉丝。 [2012] (洪钦明)", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "dealFansInvite", method = RequestMethod.PUT)
	public Result dealFansInvite(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "商家ID") Long merchantId,
			@RequestParam @ApiParam(required = true, value = "邀请的信息的ID") Long messageId,
			@RequestParam @ApiParam(required = true, value = "处理方式true同意,false拒绝") Boolean dealWay) {
		long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<Boolean> result = fansMerchantService.isFansMerchant(merchantId, memberId);
		if (result.getModel()) {
			return successCreated(ResultCode.FANS_MERCHANT);
		}
		return fansMerchantService.saveFansMerchantFromInvite(merchantId, memberId, messageId, dealWay);
	}
}
