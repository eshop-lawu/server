package com.lawu.eshop.merchant.api.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.merchant.api.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.merchant.api.service.BalancePayService;
import com.lawu.eshop.merchant.api.service.MessageService;
import com.lawu.eshop.merchant.api.service.PropertyInfoService;
import com.lawu.eshop.merchant.api.service.PropertySrvPropertyService;
import com.lawu.eshop.merchant.api.service.RechargeService;
import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.param.BalancePayDataParam;
import com.lawu.eshop.property.param.BalancePayParam;
import com.lawu.eshop.property.param.BalancePayValidateDataParam;
import com.lawu.eshop.property.param.BalancePayValidateParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: 余额支付
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月11日 下午8:18:54
 *
 */
@Api(tags = "balancePay")
@RestController
@RequestMapping(value = "balancePay/")
public class BalancePayController extends BaseController {

	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private BalancePayService balancePayService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private PropertyInfoService propertyInfoService;
	@Autowired
	private PropertySrvPropertyService propertySrvPropertyService;
	@Autowired
	private EventPublisher eventPublisher;

	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@Deprecated
	@Authorization
	@ApiOperation(value = "余额充值积分", notes = "余额充值积分,[]（杨清华）", httpMethod = "POST")
	@RequestMapping(value = "balancePayPoint", method = RequestMethod.POST)
	public Result balancePayPoint(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam BalancePayParam param) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		BalancePayDataParam dparam = new BalancePayDataParam();
		dparam.setBizIds(param.getBizIds());
		dparam.setUserNum(userNum);
		dparam.setAccount(UserUtil.getCurrentAccount(getRequest()));

		ThirdPayCallBackQueryPayOrderDTO recharge = rechargeService.getRechargeMoney(param.getBizIds());
		double money = recharge.getActualMoney();
		if (StringUtil.doubleCompareTo(money, 0) == 0) {
			return successCreated(ResultCode.MONEY_IS_ZERO);
		}
		dparam.setTotalAmount(String.valueOf(money));

		Result result = balancePayService.balancePayPoint(dparam);
		if(ResultCode.SUCCESS != result.getRet()){
			return result;
		}
		
		// ------------------------------发送站内消息
		DecimalFormat df = new DecimalFormat("######0.00");
		MessageInfoParam messageInfoParam = new MessageInfoParam();
		messageInfoParam.setRelateId(0L);
		messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_POINT);
		MessageTempParam messageTempParam = new MessageTempParam();
		messageTempParam.setRechargeBalance(new BigDecimal(df.format(money)));
		Result<PropertyPointAndBalanceDTO> moneyResult = propertyInfoService.getPropertyInfoMoney(userNum);
		messageTempParam.setPoint(moneyResult.getModel().getPoint().setScale(2, BigDecimal.ROUND_HALF_UP));
		if (userNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
			messageTempParam.setUserName("E店会员");
		} else if (userNum.startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
			messageTempParam.setUserName("E店商家");
		}
		String property_key = PropertyType.MERCHANT_BALANCE_PAY_POINT_SCALE;
        String scale = propertySrvPropertyService.getValue(property_key).getModel().toString();
        double dPayMoney = Double.parseDouble(String.valueOf(money));
        double dPayScale = Double.parseDouble(scale);
        double point = dPayMoney * dPayScale;
        messageTempParam.setRechargePoint(new BigDecimal(df.format(point)));
		messageInfoParam.setMessageParam(messageTempParam);
		messageService.saveMessage(userNum, messageInfoParam);
		// ------------------------------发送站内消息

		//商家瑞奇岛每日积分充值任务
		RichMerchantPowerTaskRecordParam richMerchantPowerTaskRecordParam = new RichMerchantPowerTaskRecordParam();
		richMerchantPowerTaskRecordParam.setMerchantNum(userNum);
		richMerchantPowerTaskRecordParam.setType(MerchantPowerTaskTypeEnum.RECHARGE_POINT);
		richMerchantPowerTaskRecordParam.setPoint(Double.valueOf(money).intValue());
		eventPublisher.publishRichPowerTaskEvent(richMerchantPowerTaskRecordParam);

		return successCreated();
	}

	//#########################################################以下接口需要校验支付密码

	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@Authorization
	@ApiOperation(value = "余额充值积分", notes = "余额充值积分,校验支付密码（杨清华）", httpMethod = "POST")
	@RequestMapping(value = "balancePayPointValidatePwd", method = RequestMethod.POST)
	public Result balancePayPointValidatePwd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
						@ModelAttribute @ApiParam BalancePayValidateParam param) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		BalancePayValidateDataParam dparam = new BalancePayValidateDataParam();
		dparam.setBizIds(param.getBizIds());
		dparam.setUserNum(userNum);
		dparam.setAccount(UserUtil.getCurrentAccount(getRequest()));
		dparam.setPayPwd(param.getPayPwd());

		ThirdPayCallBackQueryPayOrderDTO recharge = rechargeService.getRechargeMoney(param.getBizIds());
		double money = recharge.getActualMoney();
		if (StringUtil.doubleCompareTo(money, 0) == 0) {
			return successCreated(ResultCode.MONEY_IS_ZERO);
		}
		dparam.setTotalAmount(String.valueOf(money));

		Result result = balancePayService.balancePayPointValidatePwd(dparam);
		if(ResultCode.SUCCESS != result.getRet()){
			return result;
		}

		// ------------------------------发送站内消息
		DecimalFormat df = new DecimalFormat("######0.00");
		MessageInfoParam messageInfoParam = new MessageInfoParam();
		messageInfoParam.setRelateId(0L);
		messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_POINT);
		MessageTempParam messageTempParam = new MessageTempParam();
		messageTempParam.setRechargeBalance(new BigDecimal(df.format(money)));
		Result<PropertyPointAndBalanceDTO> moneyResult = propertyInfoService.getPropertyInfoMoney(userNum);
		messageTempParam.setPoint(moneyResult.getModel().getPoint().setScale(2, BigDecimal.ROUND_HALF_UP));
		if (userNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
			messageTempParam.setUserName("E店会员");
		} else if (userNum.startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
			messageTempParam.setUserName("E店商家");
		}
		String property_key = PropertyType.MERCHANT_BALANCE_PAY_POINT_SCALE;
		String scale = propertySrvPropertyService.getValue(property_key).getModel().toString();
		double dPayMoney = Double.parseDouble(String.valueOf(money));
		double dPayScale = Double.parseDouble(scale);
		double point = dPayMoney * dPayScale;
		messageTempParam.setRechargePoint(new BigDecimal(df.format(point)));
		messageInfoParam.setMessageParam(messageTempParam);
		messageService.saveMessage(userNum, messageInfoParam);
		// ------------------------------发送站内消息

		//商家瑞奇岛每日积分充值任务
		RichMerchantPowerTaskRecordParam richMerchantPowerTaskRecordParam = new RichMerchantPowerTaskRecordParam();
		richMerchantPowerTaskRecordParam.setMerchantNum(userNum);
		richMerchantPowerTaskRecordParam.setType(MerchantPowerTaskTypeEnum.RECHARGE_POINT);
		richMerchantPowerTaskRecordParam.setPoint(Double.valueOf(money).intValue());
		eventPublisher.publishRichPowerTaskEvent(richMerchantPowerTaskRecordParam);

		return successCreated();
	}
}
