package com.lawu.eshop.merchant.api.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.service.MerchantStoreService;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.merchant.api.service.RechargeService;
import com.lawu.eshop.property.constants.ThirdPayStatusEnum;
import com.lawu.eshop.property.param.RechargeSaveDataParam;
import com.lawu.eshop.property.param.RechargeSaveParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: 商家充值中心
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月12日 下午8:34:25
 *
 */
@Api(tags = "recharge")
@RestController
@RequestMapping(value = "recharge/")
public class RechargeController extends BaseController {

	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private MerchantStoreService merchantStoreService;

	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "商家充值余额和积分", notes = "商家充值余额和积分，[]，(杨清华)", httpMethod = "POST")
	@Authorization
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result save(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam RechargeSaveParam param) {
		
		RechargeSaveDataParam dparam = new  RechargeSaveDataParam();
		dparam.setRechargeMoney(param.getRechargeMoney());
		dparam.setPayTypeEnum(param.getPayTypeEnum());
		dparam.setTransactionPayTypeEnum(param.getTransactionPayTypeEnum());
		dparam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
		VisitUserInfoDTO visitUserInfoDTO = merchantStoreService.findAccountAndRegionPathByNum(UserUtil.getCurrentUserNum(getRequest()));
		dparam.setRegionPath(visitUserInfoDTO.getRegionPath());
		if(TransactionPayTypeEnum.BALANCE.getVal().equals(param.getTransactionPayTypeEnum().getVal())
				&& (param.getPayPwd() == null || "".equals(param.getPayPwd()) )){
			return successCreated(ResultCode.PAY_PWD_NULL);
		}
		dparam.setPayPwd(param.getPayPwd());
		return rechargeService.save(dparam);

	}


	@Audit(date = "2017-08-28", reviewer = "章勇")
	@Authorization
	@ApiOperation(value = "查询充值状态", notes = "查询充值状态，[]，(章勇)", httpMethod = "GET")
	@RequestMapping(value = "getRechargeById/{rechargeId}", method = RequestMethod.GET)
	public Result<ThirdPayStatusEnum> getRechargeById(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
													  @PathVariable("rechargeId") Long rechargeId){

		return rechargeService.getRechargeById(rechargeId);
	}

}
