package com.lawu.eshop.member.api.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.dto.CashFeeDTO;
import com.lawu.eshop.property.dto.CashFeeParamDTO;
import com.lawu.eshop.property.param.CashChargeDataParam;
import com.lawu.eshop.property.param.CashChargeParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.member.api.service.CashManageFrontService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.dto.WithdrawCashQueryDTO;
import com.lawu.eshop.property.param.CashBillDataParam;
import com.lawu.eshop.property.param.CashBillParam;
import com.lawu.eshop.property.param.CashDataParam;
import com.lawu.eshop.property.param.CashParam;
import com.lawu.eshop.user.dto.CashUserInfoDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;

/**
 * 
 * <p>
 * Description: 前端用户提现管理
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月5日 下午6:16:25
 *
 */
@Api(tags = "cashFront")
@RestController
@RequestMapping(value = "cashFront/")
public class CashManageFrontController extends BaseController {

	@Autowired
	private CashManageFrontService cashManageFrontService;
	@Autowired
	private MemberService memberService;

	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "用户提现", notes = "用户提现[]，(杨清华)", httpMethod = "POST")
	@Authorization
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result save(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam @Valid CashParam param, BindingResult result) {

		String message = validate(result);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}

		CashDataParam dataParam = new CashDataParam();
		dataParam.setCashMoney(param.getCashMoney());
		dataParam.setBusinessBankAccountId(param.getBusinessBankAccountId());
		dataParam.setAccount(UserUtil.getCurrentAccount(getRequest()));
		dataParam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
		dataParam.setTransactionType(MemberTransactionTypeEnum.WITHDRAW.getValue());
		dataParam.setUserType(UserTypeEnum.MEMBER.getValue());
		dataParam.setPayPwd(param.getPayPwd());
		
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		CashUserInfoDTO cashUserInfoDTO = memberService.findCashUserInfo(memberId);
//		if(cashUserInfoDTO == null){
//			return successCreated(ResultCode.PROPERTY_CASH_USER_INFO_NULL);
//		}
		dataParam.setName(cashUserInfoDTO == null ? "" : cashUserInfoDTO.getName());
		dataParam.setProvinceId(cashUserInfoDTO == null ? 0 : cashUserInfoDTO.getProvinceId());
		dataParam.setCityId(cashUserInfoDTO == null ? 0 : cashUserInfoDTO.getCityId());
		dataParam.setAreaId(cashUserInfoDTO == null ? 0 : cashUserInfoDTO.getAreaId());
		dataParam.setRegionFullName(cashUserInfoDTO == null ? "" : cashUserInfoDTO.getRegionFullName());

		return cashManageFrontService.save(dataParam);

	}

	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@ApiOperation(value = "提现明细", notes = "用户提现明细，[]，(杨清华)", httpMethod = "POST")
	@Authorization
	@RequestMapping(value = "findCashList", method = RequestMethod.POST)
	public Result<Page<WithdrawCashQueryDTO>> findCashList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam CashBillParam param) {
		CashBillDataParam cparam = new CashBillDataParam();
		cparam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
		cparam.setCurrentPage(param.getCurrentPage());
		cparam.setPageSize(param.getPageSize());
		return cashManageFrontService.findCashList(cparam);
	}

	@Audit(date = "2018-03-01", reviewer = "孙林青")
	@ApiOperation(value = "获取手续费和服务费", notes = "获取手续费和服务费，(杨清华)", httpMethod = "POST")
	@Authorization
	@RequestMapping(value = "calculationFee", method = RequestMethod.POST)
	public Result<CashFeeDTO> calculationFee(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
							     @ModelAttribute @ApiParam CashChargeParam cashChargeParam) {
		CashChargeDataParam cashChargeDataParam = new CashChargeDataParam();
		cashChargeDataParam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
		cashChargeDataParam.setBusinessBankAccountId(cashChargeParam.getBusinessBankAccountId());
		cashChargeDataParam.setCashMoney(cashChargeParam.getCashMoney());
		return cashManageFrontService.calculationFee(cashChargeDataParam);
	}

	@Audit(date = "2018-03-01", reviewer = "孙林青")
	@ApiOperation(value = "获取计算手续费和服务费参数", notes = "获取计算手续费和服务费参数，(杨清华)", httpMethod = "GET")
	@Authorization
	@RequestMapping(value = "getCalculationFeeParam", method = RequestMethod.GET)
	public Result<CashFeeParamDTO> getCalculationFeeParam(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		return cashManageFrontService.getCalculationFeeParam(UserUtil.getCurrentUserNum(getRequest()));
	}

}
