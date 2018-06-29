package com.lawu.eshop.merchant.api.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.merchant.api.service.ReportTradeDataService;
import com.lawu.eshop.user.dto.ReportRiseRateDTO;
import com.lawu.eshop.user.param.ReportDataParam;
import com.lawu.eshop.user.param.ReportParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.BeanUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: 买单、商品订单交易数据报表
 * </p>
 * @author Yangqh
 * @date 2017年5月3日 下午3:56:30
 *
 */
@Api(tags = "reportTrade")
@RestController
@RequestMapping(value = "reportTrade/")
public class ReportTradeDataController extends BaseController {

	@Autowired
	private ReportTradeDataService reportTradeDataService;

	@Audit(date = "2017-05-03", reviewer = "孙林青")
	@ApiOperation(value = "买单交易数据", notes = "买单交易数据(日增长、月增长)。[]，(杨清华)", httpMethod = "GET")
	@Authorization
	@RequestMapping(value = "payVolumeRiseRate", method = RequestMethod.GET)
	public Result<ReportRiseRateDTO> payVolumeRiseRate(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam ReportParam param) throws Exception {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		if (merchantId == 0L) {
			return successCreated(ResultCode.MEMBER_NO_EXIST);
		}
		ReportDataParam dparam = new ReportDataParam();
		BeanUtil.copyProperties(param, dparam);
		dparam.setMerchantId(merchantId);
		dparam.setFlag(param.getFlag());
		return reportTradeDataService.payVolumeRiseRate(dparam);
	}

	@Audit(date = "2017-05-04", reviewer = "孙林青")
	@ApiOperation(value = "商品订单交易数据", notes = "商品订单交易数据(日增长、月增长)。[]，(杨清华)", httpMethod = "GET")
	@Authorization
	@RequestMapping(value = "productOrderVolumeRiseRate", method = RequestMethod.GET)
	public Result<ReportRiseRateDTO> productOrderVolumeRiseRate(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam ReportParam param) throws Exception {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		if (merchantId == 0L) {
			return successCreated(ResultCode.MEMBER_NO_EXIST);
		}
		ReportDataParam dparam = new ReportDataParam();
		BeanUtil.copyProperties(param, dparam);
		dparam.setMerchantId(merchantId);
		dparam.setFlag(param.getFlag());
		return reportTradeDataService.selectByTransactionData(dparam);
	}
}
