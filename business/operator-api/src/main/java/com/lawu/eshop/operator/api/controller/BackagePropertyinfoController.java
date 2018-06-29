package com.lawu.eshop.operator.api.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import com.lawu.eshop.property.dto.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.MemberService;
import com.lawu.eshop.operator.api.service.MerchantService;
import com.lawu.eshop.operator.api.service.MessageService;
import com.lawu.eshop.operator.api.service.PointDetailService;
import com.lawu.eshop.operator.api.service.PropertyinfoService;
import com.lawu.eshop.operator.api.service.RechargeService;
import com.lawu.eshop.operator.api.service.TransactionDetailService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.eshop.property.param.BackagePropertyinfoDataParam;
import com.lawu.eshop.property.param.BackagePropertyinfoParam;
import com.lawu.eshop.property.param.PropertyInfoBackageParam;
import com.lawu.eshop.property.param.RechargeQueryDataParam;
import com.lawu.eshop.property.param.RechargeQueryParam;
import com.lawu.eshop.property.param.TransactionDetailQueryForBackageParam;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: 运营平台余额积分充值，资金管理
 * </p>
 * 
 * @author Yangqh
 * @date 2017年5月16日 下午2:06:48
 *
 */
@Api(tags = "backagePropertyinfo")
@RestController
@RequestMapping(value = "backagePropertyinfo/")
public class BackagePropertyinfoController extends BaseController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private PropertyinfoService propertyinfoService;
	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private TransactionDetailService transactionDetailService;
	@Autowired
	private PointDetailService pointDetailService;
	@Autowired
	private MessageService messageService;

	@PageBody
	@ApiOperation(value = "余额、积分明细查询", notes = "余额、积分明细查询[]（杨清华）", httpMethod = "POST")
	@RequestMapping(value = "selectPropertyinfoList", method = RequestMethod.POST)
	@RequiresAuthentication
	public Result<Page<BalanceAndPointListQueryDTO>> selectPropertyinfoList(@RequestBody RechargeQueryParam param) {
		RechargeQueryDataParam dparam = new RechargeQueryDataParam();
		String userNum = "";
		if (UserTypeEnum.MEMBER.getVal().equals(param.getUserType().getVal())) {
			Result<MemberDTO> member = memberService.getMemberByAccount(param.getAccount());
			if (member.getRet() == ResultCode.SUCCESS) {
				userNum = member.getModel().getNum();
			}
		} else if (UserTypeEnum.MERCHANT.getVal().equals(param.getUserType().getVal())) {
			Result<MerchantDTO> merchant = merchantService.getMerchantByAccount(param.getAccount());
			if (merchant.getRet() == ResultCode.SUCCESS) {
				userNum = merchant.getModel().getNum();
			}
		}
		dparam.setUserNum(userNum);
		dparam.setRechargeNumber(param.getRechargeNumber());
		dparam.setStatus(param.getStatus());
		dparam.setCurrentPage(param.getCurrentPage());
		dparam.setPageSize(param.getPageSize());
		return rechargeService.selectPropertyinfoList(dparam);
	}

	@PageBody
	@ApiOperation(value = "余额充值列表", notes = "余额充值列表。（梅述全）", httpMethod = "POST")
	@RequestMapping(value = "getBackageRechargePageList", method = RequestMethod.POST)
	@RequiresPermissions("balance:list")
	public Result<Page<TransactionDetailBackageDTO>> getBackageRechargePageList(@RequestBody RechargeQueryParam param) {
		String userNum = "";
		TransactionDetailQueryForBackageParam realParam = new TransactionDetailQueryForBackageParam();
		if(!param.getUserType().getVal().equals(UserTypeEnum.MEMCHANT_PC.getVal())){
			if (UserTypeEnum.MEMBER.getVal().equals(param.getUserType().getVal())) {
				if(StringUtils.isNotEmpty(param.getAccount())){
					Result<MemberDTO> member = memberService.getMemberByAccount(param.getAccount());
					if (member.getRet() == ResultCode.SUCCESS) {
						userNum = member.getModel().getNum();
					}
				}
				realParam.setMemberTransactionType(MemberTransactionTypeEnum.BACKAGE);
			} else if (UserTypeEnum.MERCHANT.getVal().equals(param.getUserType().getVal())) {
				if(StringUtils.isNotEmpty(param.getAccount())){
					Result<MerchantDTO> merchant = merchantService.getMerchantByAccount(param.getAccount());
					if (merchant.getRet() == ResultCode.SUCCESS) {
						userNum = merchant.getModel().getNum();
					}
				}
				realParam.setMerchantTransactionType(MerchantTransactionTypeEnum.BACKAGE);
			}
			if(StringUtils.isEmpty(userNum)){
				realParam.setUserNum(param.getAccount());
			}else {
				realParam.setUserNum(userNum);
			}
		}
		realParam.setCurrentPage(param.getCurrentPage());
		realParam.setPageSize(param.getPageSize());
		Result<Page<TransactionDetailBackageDTO>> result = transactionDetailService.getBackageRechargePageList(realParam);
		List<TransactionDetailBackageDTO> transactionDetailBackageDTOS = result.getModel().getRecords();
		if(!transactionDetailBackageDTOS.isEmpty()){
			for(TransactionDetailBackageDTO transactionDetailBackageDTO : transactionDetailBackageDTOS){
				if(transactionDetailBackageDTO.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)){
					transactionDetailBackageDTO.setAccountType(UserCommonConstant.MEMBER_NUM_TAG);
					Result<UserDTO> userDTOResult = memberService.getMemberByNum(transactionDetailBackageDTO.getUserNum());
					if(isSuccess(userDTOResult)){
						transactionDetailBackageDTO.setAccount(userDTOResult.getModel().getAccount());
					}
				}else{
					transactionDetailBackageDTO.setAccountType(UserCommonConstant.MERCHANT_NUM_TAG);
					Result<MerchantDTO> merchantDTOResult = merchantService.getMerchantByNum(transactionDetailBackageDTO.getUserNum());
					if(isSuccess(merchantDTOResult)){
						transactionDetailBackageDTO.setAccount(merchantDTOResult.getModel().getAccount());
					}
				}
			}
		}
		return result;
	}

	@PageBody
	@ApiOperation(value = "积分充值列表", notes = "积分充值列表。（梅述全）", httpMethod = "POST")
	@RequestMapping(value = "getBackagePointPageList", method = RequestMethod.POST)
	@RequiresPermissions("point:list")
	public Result<Page<PointDetailBackageDTO>> getBackagePointPageList(@RequestBody RechargeQueryParam param) {
		String userNum = "";
		TransactionDetailQueryForBackageParam realParam = new TransactionDetailQueryForBackageParam();
		if(!param.getUserType().getVal().equals(UserTypeEnum.MEMCHANT_PC.getVal())){
			if (UserTypeEnum.MEMBER.getVal().equals(param.getUserType().getVal())) {
				if(StringUtils.isNotEmpty(param.getAccount())){
					Result<MemberDTO> member = memberService.getMemberByAccount(param.getAccount());
					if (member.getRet() == ResultCode.SUCCESS) {
						userNum = member.getModel().getNum();
					}
				}
				realParam.setMemberTransactionType(MemberTransactionTypeEnum.BACKAGE);
			} else if (UserTypeEnum.MERCHANT.getVal().equals(param.getUserType().getVal())) {
				if(StringUtils.isNotEmpty(param.getAccount())){
					Result<MerchantDTO> merchant = merchantService.getMerchantByAccount(param.getAccount());
					if (merchant.getRet() == ResultCode.SUCCESS) {
						userNum = merchant.getModel().getNum();
					}
				}
				realParam.setMerchantTransactionType(MerchantTransactionTypeEnum.BACKAGE);
			}
			if(StringUtils.isEmpty(userNum)){
				realParam.setUserNum(param.getAccount());
			}else {
				realParam.setUserNum(userNum);
			}
		}
		realParam.setCurrentPage(param.getCurrentPage());
		realParam.setPageSize(param.getPageSize());
		Result<Page<PointDetailBackageDTO>> result = pointDetailService.getBackagePointPageList(realParam);
		List<PointDetailBackageDTO> pointDetailBackageDTOS = result.getModel().getRecords();
		if(!pointDetailBackageDTOS.isEmpty()){
			for(PointDetailBackageDTO pointDetailBackageDTO : pointDetailBackageDTOS){
				if(pointDetailBackageDTO.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)){
					pointDetailBackageDTO.setAccountType(UserCommonConstant.MEMBER_NUM_TAG);
					Result<UserDTO> userDTOResult = memberService.getMemberByNum(pointDetailBackageDTO.getUserNum());
					if(isSuccess(userDTOResult)){
						pointDetailBackageDTO.setAccount(userDTOResult.getModel().getAccount());
					}
				}else{
					pointDetailBackageDTO.setAccountType(UserCommonConstant.MERCHANT_NUM_TAG);
					Result<MerchantDTO> merchantDTOResult = merchantService.getMerchantByNum(pointDetailBackageDTO.getUserNum());
					if(isSuccess(merchantDTOResult)){
						pointDetailBackageDTO.setAccount(merchantDTOResult.getModel().getAccount());
					}
				}
				if (StringUtils.isEmpty(pointDetailBackageDTO.getPayType())) {
					Result<String> payType = rechargeService.getRechargePayType(Long.valueOf(pointDetailBackageDTO.getBizId()));
					pointDetailBackageDTO.setPayType(payType.getModel());
				}
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.BALANCE_UPDATE)
	@ApiOperation(value = "充值余额", notes = "充值余额[]（杨清华）", httpMethod = "POST")
	@RequestMapping(value = "updateBalance", method = RequestMethod.POST)
	@RequiresPermissions("balance:recharge")
	public Result updateBalance(@Valid BackagePropertyinfoParam param, BindingResult result) {
		String message = validate(result);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}

		BackagePropertyinfoDataParam dparam = new BackagePropertyinfoDataParam();
		dparam.setMoney(param.getMoney());
		dparam.setPayTypeEnum(param.getPayTypeEnum());
		MessageTempParam messageTempParam = new MessageTempParam();
		if (UserTypeEnum.MEMBER.getVal().equals(param.getUserTypeEnum().getVal())) {
			Result<MemberDTO> member = memberService.getMemberByAccount(param.getAccount());
			if (member.getRet() != ResultCode.SUCCESS) {
				return member;
			}
			dparam.setUserNum(member.getModel().getNum());
			messageTempParam.setUserName("E店会员");
		} else if (UserTypeEnum.MERCHANT.getVal().equals(param.getUserTypeEnum().getVal())) {
			Result<MerchantDTO> merchant = merchantService.getMerchantByAccount(param.getAccount());
			if (merchant.getRet() != ResultCode.SUCCESS) {
				return merchant;
			}
			dparam.setUserNum(merchant.getModel().getNum());
			messageTempParam.setUserName("E店商家");
		}

		Result result1 = propertyinfoService.updateBalanceAndPoint(dparam);
		if (result1.getRet() != ResultCode.SUCCESS) {
			return result1;
		}

		Result<PropertyPointAndBalanceDTO> propertyPointAndBalanceDTOResult = propertyinfoService.getPropertyInfoMoney(dparam.getUserNum());
		messageTempParam.setBalance(propertyPointAndBalanceDTOResult.getModel().getBalance());
		messageTempParam.setRechargeBalance(new BigDecimal(param.getMoney()));
		MessageInfoParam messageInfoParam = new MessageInfoParam();
		messageInfoParam.setTypeEnum(MessageTypeEnum.PLAT_RECHARGE_BALANCE);
		messageInfoParam.setMessageParam(messageTempParam);
		messageService.saveMessage(dparam.getUserNum(), messageInfoParam);

		return result1;
	}

	@SuppressWarnings("rawtypes")
	@LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.POINT_UPDATE)
	@ApiOperation(value = "充值积分", notes = "充值积分[]（杨清华）", httpMethod = "POST")
	@RequestMapping(value = "updatePoint", method = RequestMethod.POST)
	@RequiresPermissions("point:recharge")
	public Result updatePoint(@Valid BackagePropertyinfoParam param, BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}

		BackagePropertyinfoDataParam dparam = new BackagePropertyinfoDataParam();
		dparam.setMoney(param.getMoney());
		dparam.setPayTypeEnum(param.getPayTypeEnum());
		MessageTempParam messageTempParam = new MessageTempParam();
		if (UserTypeEnum.MEMBER.getVal().equals(param.getUserTypeEnum().getVal())) {
			Result<MemberDTO> member = memberService.getMemberByAccount(param.getAccount());
			if (member.getRet() != ResultCode.SUCCESS) {
				return member;
			}
			dparam.setUserNum(member.getModel().getNum());
			messageTempParam.setUserName("E店会员");
		} else if (UserTypeEnum.MERCHANT.getVal().equals(param.getUserTypeEnum().getVal())) {
			Result<MerchantDTO> merchant = merchantService.getMerchantByAccount(param.getAccount());
			if (merchant.getRet() != ResultCode.SUCCESS) {
				return merchant;
			}
			dparam.setUserNum(merchant.getModel().getNum());
			messageTempParam.setUserName("E店商家");
		}

		Result result1 = propertyinfoService.updateBalanceAndPoint(dparam);
		if (result1.getRet() != ResultCode.SUCCESS) {
			return result1;
		}

		Result<PropertyPointAndBalanceDTO> propertyPointAndBalanceDTOResult = propertyinfoService.getPropertyInfoMoney(dparam.getUserNum());
		messageTempParam.setPoint(propertyPointAndBalanceDTOResult.getModel().getPoint());
		messageTempParam.setRechargePoint(new BigDecimal(param.getMoney()));
		MessageInfoParam messageInfoParam = new MessageInfoParam();
		messageInfoParam.setTypeEnum(MessageTypeEnum.PLAT_RECHARGE_POINT);
		messageInfoParam.setMessageParam(messageTempParam);
		messageService.saveMessage(dparam.getUserNum(), messageInfoParam);

		return result1;
	}

	@PageBody
	@ApiOperation(value = "查询用户资产信息", notes = "查询用户资产信息 []（杨清华）", httpMethod = "POST")
	@RequestMapping(value = "getPropertyinfoPageList", method = RequestMethod.POST)
	@RequiresPermissions("property:list")
	public Result<Page<PropertyInfoDTO>> getPropertyinfoPageList(@RequestBody PropertyInfoBackageParam param) {
		String userNum = "";
		if(param.getUserType().getVal() == UserTypeEnum.MEMCHANT_PC.getVal()){
			param.setUserType(null);
		}else{
			if (UserTypeEnum.MEMBER.getVal().equals(param.getUserType().getVal())) {
				if(StringUtils.isNotEmpty(param.getAccount())){
					Result<MemberDTO> member = memberService.getMemberByAccount(param.getAccount());
					if (member.getRet() == ResultCode.SUCCESS) {
						userNum = member.getModel().getNum();
					}
				}
			} else if (UserTypeEnum.MERCHANT.getVal().equals(param.getUserType().getVal())) {
				if(StringUtils.isNotEmpty(param.getAccount())){
					Result<MerchantDTO> merchant = merchantService.getMerchantByAccount(param.getAccount());
					if (merchant.getRet() == ResultCode.SUCCESS) {
						userNum = merchant.getModel().getNum();
					}
				}
			}
			if(StringUtils.isEmpty(userNum)){
				param.setUserNum(param.getAccount());
			}else {
				param.setUserNum(userNum);
			}
		}
		Result<Page<PropertyInfoDTO>> result = propertyinfoService.getPropertyinfoPageList(param);
		List<PropertyInfoDTO> propertyInfoDTOS = result.getModel().getRecords();
		if(!propertyInfoDTOS.isEmpty()){
			for(PropertyInfoDTO propertyInfoDTO : propertyInfoDTOS){
				if(propertyInfoDTO.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)){
					propertyInfoDTO.setAccountType(UserCommonConstant.MEMBER_NUM_TAG);
					Result<UserDTO> userDTOResult = memberService.getMemberByNum(propertyInfoDTO.getUserNum());
					if(isSuccess(userDTOResult)){
						propertyInfoDTO.setAccount(userDTOResult.getModel().getAccount());
					}
				}else{
					propertyInfoDTO.setAccountType(UserCommonConstant.MERCHANT_NUM_TAG);
					Result<MerchantDTO> merchantDTOResult = merchantService.getMerchantByNum(propertyInfoDTO.getUserNum());
					if(isSuccess(merchantDTOResult)){
						propertyInfoDTO.setAccount(merchantDTOResult.getModel().getAccount());
					}
				}
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ACCOUNT_FREEZE)
	@ApiOperation(value = "冻结账号", notes = "冻结解冻账号[]（杨清华）", httpMethod = "PUT")
	@RequiresPermissions("property:freeze")
	@RequestMapping(value = "updatePropertyinfoFreeze", method = RequestMethod.PUT)
	public Result updatePropertyinfoFreeze(@RequestParam @ApiParam(required = true, value = "用户编号") String userNum) {
		Result result =  propertyinfoService.updatePropertyinfoFreeze(userNum, PropertyinfoFreezeEnum.YES);
		if(isSuccess(result)){
			//发送站内消息
			MessageTempParam messageTempParam = new MessageTempParam();
			if (userNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
				messageTempParam.setUserName("E店会员");
			} else {
				messageTempParam.setUserName("E店商家");
			}
			MessageInfoParam messageInfoParam = new MessageInfoParam();
			messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_MONEY_FREEZE);
			messageInfoParam.setMessageParam(messageTempParam);
			messageService.saveMessage(userNum, messageInfoParam);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "解冻账号", notes = "冻结解冻账号[]（杨清华）", httpMethod = "PUT")
	@LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ACCOUNT_FREEZE)
	@RequiresPermissions("property:unfreeze")
	@RequestMapping(value = "updatePropertyinfoUnFreeze", method = RequestMethod.PUT)
	public Result updatePropertyinfoUnFreeze(@RequestParam @ApiParam(required = true, value = "用户编号") String userNum) {
		Result result = propertyinfoService.updatePropertyinfoFreeze(userNum, PropertyinfoFreezeEnum.NO);
		if(isSuccess(result)){
			//发送站内消息
			MessageTempParam messageTempParam = new MessageTempParam();
			if (userNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
				messageTempParam.setUserName("E店会员");
			} else {
				messageTempParam.setUserName("E店商家");
			}
			MessageInfoParam messageInfoParam = new MessageInfoParam();
			messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_MONEY_UNFREEZE);
			messageInfoParam.setMessageParam(messageTempParam);
			messageService.saveMessage(userNum, messageInfoParam);
		}
		return result;
	}

}
