package com.lawu.eshop.member.api.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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
import com.lawu.eshop.member.api.service.BalancePayService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.member.api.service.MessageService;
import com.lawu.eshop.member.api.service.PayOrderService;
import com.lawu.eshop.member.api.service.PropertyInfoService;
import com.lawu.eshop.member.api.service.PropertySrvPropertyService;
import com.lawu.eshop.member.api.service.RechargeService;
import com.lawu.eshop.member.api.service.ShoppingOrderService;
import com.lawu.eshop.member.api.service.UserRedPacketService;
import com.lawu.eshop.order.constants.PayOrderStatusEnum;
import com.lawu.eshop.order.dto.PayOrderBaseDTO;
import com.lawu.eshop.order.dto.ShoppingOrderMoneyDTO;
import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.param.BalancePayDataParam;
import com.lawu.eshop.property.param.BalancePayParam;
import com.lawu.eshop.property.param.BalancePayValidateDataParam;
import com.lawu.eshop.property.param.BalancePayValidateParam;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.PayOrderMerchantStoreInfoDTO;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * Description: 余额支付
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月11日 下午8:18:54
 */
@Api(tags = "balancePay")
@RestController
@RequestMapping(value = "balancePay/")
public class BalancePayController extends BaseController {

    @Autowired
    private BalancePayService balancePayService;
    @Autowired
    private PayOrderService payOrderService;
    @Autowired
    private ShoppingOrderService shoppingOrderService;
    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PropertyInfoService propertyInfoService;
    @Autowired
    private MerchantStoreService merchantStoreService;
    @Autowired
    private UserRedPacketService userRedPacketService;
    @Autowired
    private PropertySrvPropertyService propertySrvPropertyService;
    @Autowired
    private MemberService memberService;

    /**
     * 余额支付订单
     *
     * @param param
     * @return
     */
    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @Deprecated
    @Authorization
    @ApiOperation(value = "商品订单余额支付", notes = "商品订单余额支付,[4020|4021]（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "orderPay", method = RequestMethod.POST)
    public Result orderPay(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                           @ModelAttribute @ApiParam BalancePayParam param) {
        BalancePayDataParam dparam = new BalancePayDataParam();
        dparam.setBizIds(param.getBizIds());
        dparam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
        dparam.setAccount(UserUtil.getCurrentAccount(getRequest()));
            /*
             *  获取订单金额
		 *  考虑商品可能有减库存失败可能
		 */
        Result<ShoppingOrderMoneyDTO> result = shoppingOrderService.selectOrderMoney(param.getBizIds());
        if (!isSuccess(result)) {
            return successCreated(result.getRet());
        }
        double orderMoney = result.getModel().getOrderTotalPrice().doubleValue();

        if (StringUtil.doubleCompareTo(orderMoney, 0) == 0 && !result.getModel().isActivity() && !result.getModel().isDeductionPay()) {
            return successCreated(ResultCode.MONEY_IS_ZERO);
        }

        dparam.setTotalAmount(String.valueOf(orderMoney));

        Result<String> orderItemProductNameRet = shoppingOrderService.getOrderItemProductName(param.getBizIds().split(",")[0]);
        dparam.setTitle(orderItemProductNameRet.getModel());

        return balancePayService.orderPay(dparam);
    }

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @Deprecated
    @Authorization
    @ApiOperation(value = "买单余额支付", notes = "买单余额支付,[]（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "billPay", method = RequestMethod.POST)
    public Result billPay(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                          @ModelAttribute @ApiParam BalancePayParam param) {
        BalancePayDataParam dparam = new BalancePayDataParam();
        dparam.setBizIds(param.getBizIds());
        dparam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
        dparam.setAccount(UserUtil.getCurrentAccount(getRequest()));
        ThirdPayCallBackQueryPayOrderDTO payOrderCallback = payOrderService
                .selectThirdPayCallBackQueryPayOrder(param.getBizIds());
        if (payOrderCallback == null) {
            return successCreated(ResultCode.PAY_ORDER_NULL);
        } else if (PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal().equals(payOrderCallback.getPayOrderStatusEnum().getVal())) {
            return successCreated(ResultCode.PAY_ORDER_IS_SUCCESS);
        } else {
            if (StringUtil.doubleCompareTo(payOrderCallback.getActualMoney(), 0) == 0) {
                return successCreated(ResultCode.MONEY_IS_ZERO);
            }
        }
        dparam.setTotalAmount(String.valueOf(payOrderCallback.getActualMoney()));
        dparam.setSideUserNum(payOrderCallback.getBusinessUserNum());
        dparam.setOrderNum(payOrderCallback.getOrderNum());

        VisitUserInfoDTO visitUserInfoDTO = merchantStoreService.findAccountAndRegionPathByNum(payOrderCallback.getBusinessUserNum());
        dparam.setRegionPath(visitUserInfoDTO.getRegionPath());

        PayOrderBaseDTO dto = payOrderService.getPayOrderById(param.getBizIds());
        Result<MemberDTO> member = memberService.findMemberInfoById(dto.getMemberId());
        PayOrderMerchantStoreInfoDTO merchantStore = merchantStoreService.getPayOrderDetailStoreInfo(dto.getMerchantId());
        dparam.setTitle(merchantStore.getName());
        dparam.setTitleMerchant(StringUtil.hideUserAccount(member.getModel().getAccount()));

        Result result = balancePayService.billPay(dparam);
        if (ResultCode.SUCCESS != result.getRet()) {
            return result;
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        MessageInfoParam messageInfoParam = new MessageInfoParam();
        messageInfoParam.setRelateId(0L);
        messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_PAY_ORDER_SUCCESS_MERCHANT);
        MessageTempParam messageTempParam = new MessageTempParam();
        messageTempParam.setOrderAmount(new BigDecimal(df.format(payOrderCallback.getActualMoney())));
        messageInfoParam.setMessageParam(messageTempParam);
        messageService.saveMessage(payOrderCallback.getBusinessUserNum(), messageInfoParam);
        return successCreated();
    }

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

        ThirdPayCallBackQueryPayOrderDTO payOrderCallback = rechargeService.getRechargeMoney(param.getBizIds());
        if (StringUtil.doubleCompareTo(payOrderCallback.getActualMoney(), 0) == 0) {
            return successCreated(ResultCode.MONEY_IS_ZERO);
        }
        dparam.setTotalAmount(String.valueOf(payOrderCallback.getActualMoney()));
        dparam.setOrderNum(payOrderCallback.getOrderNum());

        Result result = balancePayService.balancePayPoint(dparam);
        if (ResultCode.SUCCESS != result.getRet()) {
            return result;
        }

        // ------------------------------发送站内消息
        DecimalFormat df = new DecimalFormat("######0.00");
        MessageInfoParam messageInfoParam = new MessageInfoParam();
        messageInfoParam.setRelateId(0L);
        messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_POINT);
        MessageTempParam messageTempParam = new MessageTempParam();
        messageTempParam.setRechargeBalance(new BigDecimal(df.format(payOrderCallback.getActualMoney())));
        Result<PropertyPointAndBalanceDTO> moneyResult = propertyInfoService.getPropertyInfoMoney(userNum);
        messageTempParam.setPoint(moneyResult.getModel().getPoint().setScale(2, BigDecimal.ROUND_HALF_UP));
        if (userNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
            messageTempParam.setUserName("E店会员");
        } else if (userNum.startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
            messageTempParam.setUserName("E店商家");
        }
        String property_key = PropertyType.MEMBER_BALANCE_PAY_POINT_SCALE;
        Result scale = propertySrvPropertyService.getValue(property_key);
        double dPayMoney = Double.parseDouble(String.valueOf(payOrderCallback.getActualMoney()));
        double dPayScale = Double.parseDouble(scale.getModel().toString());
        double point = dPayMoney * dPayScale;
        messageTempParam.setRechargePoint(new BigDecimal(df.format(point)));
        messageInfoParam.setMessageParam(messageTempParam);
        messageService.saveMessage(userNum, messageInfoParam);
        // ------------------------------发送站内消息

        return successCreated();
    }

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @Deprecated
    @Authorization
    @ApiOperation(value = "用户发红包余额支付", notes = "用户发红包余额支付,[]（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "memberRedPacketPay", method = RequestMethod.POST)
    public Result memberRedPacketPay(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                     @ModelAttribute @ApiParam BalancePayParam param) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        BalancePayDataParam dparam = new BalancePayDataParam();
        dparam.setBizIds(param.getBizIds());
        dparam.setUserNum(userNum);
        dparam.setAccount(UserUtil.getCurrentAccount(getRequest()));

        Result<ThirdPayCallBackQueryPayOrderDTO> moneyResult = userRedPacketService.selectUserRedPacketInfoForThrid(Long.valueOf(param.getBizIds()));
        if (StringUtil.doubleCompareTo(moneyResult.getModel().getActualMoney(), 0) == 0) {
            return successCreated(ResultCode.MONEY_IS_ZERO);
        }
        dparam.setTotalAmount(String.valueOf(moneyResult.getModel().getActualMoney()));
        dparam.setOrderNum(moneyResult.getModel().getOrderNum());

        return balancePayService.memberRedPacketPay(dparam);
    }

    //#########################################################以下接口需要校验支付密码

    /**
     * 余额支付订单
     *
     * @param param
     * @return
     */
    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @Authorization
    @ApiOperation(value = "商品订单余额支付", notes = "商品订单余额支付，校验支付密码（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "orderPayValidatePwd", method = RequestMethod.POST)
    public Result orderPayValidatePwd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                      @ModelAttribute @ApiParam BalancePayValidateParam param) {
        BalancePayValidateDataParam dparam = new BalancePayValidateDataParam();
        dparam.setBizIds(param.getBizIds());
        dparam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
        dparam.setAccount(UserUtil.getCurrentAccount(getRequest()));
        dparam.setPayPwd(param.getPayPwd());

            /*
		 *  获取订单金额
		 *  考虑商品可能有减库存失败可能
		 */
        Result<ShoppingOrderMoneyDTO> result = shoppingOrderService.selectOrderMoney(param.getBizIds());
        if (!isSuccess(result)) {
            return successCreated(result.getRet());
        }
        double orderMoney = result.getModel().getOrderTotalPrice().doubleValue();

        if (StringUtil.doubleCompareTo(orderMoney, 0) == 0 && !result.getModel().isActivity() && !result.getModel().isDeductionPay()) {
            return successCreated(ResultCode.MONEY_IS_ZERO);
        }

        dparam.setTotalAmount(String.valueOf(orderMoney));

        Result<String> orderItemProductNameRet = shoppingOrderService.getOrderItemProductName(param.getBizIds().split(",")[0]);
        dparam.setTitle(orderItemProductNameRet.getModel());

        return balancePayService.orderPayValidatePwd(dparam);
    }

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @Authorization
    @ApiOperation(value = "买单余额支付", notes = "买单余额支付,校验支付密码（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "billPayValidatePwd", method = RequestMethod.POST)
    public Result billPayValidatePwd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                     @ModelAttribute @ApiParam BalancePayValidateParam param) {
        BalancePayValidateDataParam dparam = new BalancePayValidateDataParam();
        dparam.setBizIds(param.getBizIds());
        dparam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
        dparam.setAccount(UserUtil.getCurrentAccount(getRequest()));
        dparam.setPayPwd(param.getPayPwd());

        ThirdPayCallBackQueryPayOrderDTO payOrderCallback = payOrderService
                .selectThirdPayCallBackQueryPayOrder(param.getBizIds());
        if (payOrderCallback == null) {
            return successCreated(ResultCode.PAY_ORDER_NULL);
        } else if (PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal().equals(payOrderCallback.getPayOrderStatusEnum().getVal())) {
            return successCreated(ResultCode.PAY_ORDER_IS_SUCCESS);
        } else {
            if (StringUtil.doubleCompareTo(payOrderCallback.getActualMoney(), 0) == 0) {
                return successCreated(ResultCode.MONEY_IS_ZERO);
            }
        }
        dparam.setTotalAmount(String.valueOf(payOrderCallback.getActualMoney()));
        dparam.setSideUserNum(payOrderCallback.getBusinessUserNum());
        dparam.setOrderNum(payOrderCallback.getOrderNum());

        VisitUserInfoDTO visitUserInfoDTO = merchantStoreService.findAccountAndRegionPathByNum(payOrderCallback.getBusinessUserNum());
        dparam.setRegionPath(visitUserInfoDTO.getRegionPath());

        PayOrderBaseDTO dto = payOrderService.getPayOrderById(param.getBizIds());
        Result<MemberDTO> member = memberService.findMemberInfoById(dto.getMemberId());
        PayOrderMerchantStoreInfoDTO merchantStore = merchantStoreService.getPayOrderDetailStoreInfo(dto.getMerchantId());
        dparam.setTitle(merchantStore.getName());
        dparam.setTitleMerchant(StringUtil.hideUserAccount(member.getModel().getAccount()));

        Result result = balancePayService.billPayValidatePwd(dparam);
        if (ResultCode.SUCCESS != result.getRet()) {
            return result;
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        MessageInfoParam messageInfoParam = new MessageInfoParam();
        messageInfoParam.setRelateId(0L);
        messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_PAY_ORDER_SUCCESS_MERCHANT);
        MessageTempParam messageTempParam = new MessageTempParam();
        messageTempParam.setOrderAmount(new BigDecimal(df.format(payOrderCallback.getActualMoney())));
        messageInfoParam.setMessageParam(messageTempParam);
        messageService.saveMessage(payOrderCallback.getBusinessUserNum(), messageInfoParam);
        return successCreated();
    }

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

        ThirdPayCallBackQueryPayOrderDTO payOrderCallback = rechargeService.getRechargeMoney(param.getBizIds());
        if (StringUtil.doubleCompareTo(payOrderCallback.getActualMoney(), 0) == 0) {
            return successCreated(ResultCode.MONEY_IS_ZERO);
        }
        dparam.setTotalAmount(String.valueOf(payOrderCallback.getActualMoney()));
        dparam.setOrderNum(payOrderCallback.getOrderNum());

        Result result = balancePayService.balancePayPointValidatePwd(dparam);
        if (ResultCode.SUCCESS != result.getRet()) {
            return result;
        }

        // ------------------------------发送站内消息
        DecimalFormat df = new DecimalFormat("######0.00");
        MessageInfoParam messageInfoParam = new MessageInfoParam();
        messageInfoParam.setRelateId(0L);
        messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_POINT);
        MessageTempParam messageTempParam = new MessageTempParam();
        messageTempParam.setRechargeBalance(new BigDecimal(df.format(payOrderCallback.getActualMoney())));
        Result<PropertyPointAndBalanceDTO> moneyResult = propertyInfoService.getPropertyInfoMoney(userNum);
        messageTempParam.setPoint(moneyResult.getModel().getPoint().setScale(2, BigDecimal.ROUND_HALF_UP));
        if (userNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
            messageTempParam.setUserName("E店会员");
        } else if (userNum.startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
            messageTempParam.setUserName("E店商家");
        }
        String property_key = PropertyType.MEMBER_BALANCE_PAY_POINT_SCALE;
        Result scale = propertySrvPropertyService.getValue(property_key);
        double dPayMoney = Double.parseDouble(String.valueOf(payOrderCallback.getActualMoney()));
        double dPayScale = Double.parseDouble(scale.getModel().toString());
        double point = dPayMoney * dPayScale;
        messageTempParam.setRechargePoint(new BigDecimal(df.format(point)));
        messageInfoParam.setMessageParam(messageTempParam);
        messageService.saveMessage(userNum, messageInfoParam);
        // ------------------------------发送站内消息

        return successCreated();
    }

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @Authorization
    @ApiOperation(value = "用户发红包余额支付", notes = "用户发红包余额支付,[]（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "memberRedPacketPayValidatePwd", method = RequestMethod.POST)
    public Result memberRedPacketPayValidatePwd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                @ModelAttribute @ApiParam BalancePayValidateParam param) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        BalancePayValidateDataParam dparam = new BalancePayValidateDataParam();
        dparam.setBizIds(param.getBizIds());
        dparam.setUserNum(userNum);
        dparam.setAccount(UserUtil.getCurrentAccount(getRequest()));
        dparam.setPayPwd(param.getPayPwd());

        Result<ThirdPayCallBackQueryPayOrderDTO> moneyResult = userRedPacketService.selectUserRedPacketInfoForThrid(Long.valueOf(param.getBizIds()));
        if (StringUtil.doubleCompareTo(moneyResult.getModel().getActualMoney(), 0) == 0) {
            return successCreated(ResultCode.MONEY_IS_ZERO);
        }
        dparam.setTotalAmount(String.valueOf(moneyResult.getModel().getActualMoney()));
        dparam.setOrderNum(moneyResult.getModel().getOrderNum());

        return balancePayService.memberRedPacketPayValidatePwd(dparam);
    }
}
