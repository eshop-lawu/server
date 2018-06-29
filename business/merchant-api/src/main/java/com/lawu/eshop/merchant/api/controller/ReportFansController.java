package com.lawu.eshop.merchant.api.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.lawu.eshop.merchant.api.service.ReportFansService;
import com.lawu.eshop.merchant.api.service.ReportTradeDataService;
import com.lawu.eshop.user.dto.ReportRiseRateDTO;
import com.lawu.eshop.user.dto.ReportRiseRerouceDTO;
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
 * Description: 报表----粉丝数据
 * </p>
 * 
 * @author Yangqh
 * @date 2017年5月2日 下午2:13:10
 *
 */
@Api(tags = "reportFans")
@RestController
@RequestMapping(value = "reportFans/")
public class ReportFansController extends BaseController {

	@Autowired
	private ReportFansService reportFansService;
	@Autowired
	private ReportTradeDataService reportTradeDataService;

	@Audit(date = "2017-05-03", reviewer = "孙林青")
	@ApiOperation(value = "粉丝数据，粉丝增长量", notes = "粉丝数据，粉丝增长量(日增长、月增长)。[]，(杨清华)", httpMethod = "GET")
	@Authorization
	@RequestMapping(value = "fansRiseRate", method = RequestMethod.GET)
	public Result<ReportRiseRateDTO> fansRiseRate(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam ReportParam param) throws Exception {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		if (merchantId == 0L) {
			return successCreated(ResultCode.MEMBER_NO_EXIST);
		}
		ReportDataParam dparam = new ReportDataParam();
		BeanUtil.copyProperties(param, dparam);
		dparam.setMerchantId(merchantId);
		dparam.setFlag(param.getFlag());
		return reportFansService.fansRiseRate(dparam);
	}

	@Audit(date = "2017-09-14", reviewer = "孙林青")
	@ApiOperation(value = "粉丝数据，增长来源", notes = "粉丝数据，增长来源。[]，(杨清华)", httpMethod = "GET")
	@Authorization
	@RequestMapping(value = "fansRiseSource", method = RequestMethod.GET)
	public Result<List<ReportRiseRerouceDTO>> fansRiseSource(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		if (merchantId == 0L) {
			return successCreated(ResultCode.MEMBER_NO_EXIST);
		}
		ReportDataParam dparam = new ReportDataParam();
		dparam.setMerchantId(merchantId);
		return reportFansService.fansRiseSource(dparam);
	}

	@Audit(date = "2017-05-04", reviewer = "孙林青")
	@ApiOperation(value = "粉丝数据，消费转化", notes = "粉丝数据，消费转化。[]，(杨清华)", httpMethod = "GET")
	@Authorization
	@RequestMapping(value = "fansSaleTransform", method = RequestMethod.GET)
	public Result<List<ReportRiseRerouceDTO>> fansSaleTransform(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		if (merchantId == 0L) {
			return successCreated(ResultCode.MEMBER_NO_EXIST);
		}
		ReportDataParam dparam = new ReportDataParam();
		dparam.setMerchantId(merchantId);

		Result<List<ReportRiseRerouceDTO>> payRet = reportTradeDataService.fansSaleTransformPay(dparam);
		Result<List<ReportRiseRerouceDTO>> orderRet = reportTradeDataService.fansSaleTransform(dparam);
		List<ReportRiseRerouceDTO> retList = new ArrayList<>();
		List<ReportRiseRerouceDTO> payList = payRet.getModel();
		List<ReportRiseRerouceDTO> orderList = orderRet.getModel();
		retList.addAll(payList);
		retList.addAll(orderList);
		int i = 0;
		for(ReportRiseRerouceDTO dto : retList){
			Integer count = Integer.parseInt(dto.getValue());
			if(count > 0){
				break;
			}
			i++;
		}
		if(i == 4){
			retList.clear();
			return successCreated(retList);
		}
		return successCreated(retList);
	}
}
