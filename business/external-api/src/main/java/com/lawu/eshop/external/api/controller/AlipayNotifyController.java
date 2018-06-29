package com.lawu.eshop.external.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.ad.dto.AdPayInfoDTO;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.external.api.ExternalApiConfig;
import com.lawu.eshop.external.api.service.*;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.order.dto.PayOrderBaseDTO;
import com.lawu.eshop.order.dto.ShoppingOrderMoneyDTO;
import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.pay.sdk.alipay.AliPayConfigParam;
import com.lawu.eshop.pay.sdk.alipay.util.AlipayNotify;
import com.lawu.eshop.pay.sdk.alipay.util.ParamUtil;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.constants.ThirdPartyBizFlagEnum;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.PayOrderMerchantStoreInfoDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Description: 支付宝第三方回调入口
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/alipay/")
public class AlipayNotifyController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(AlipayNotifyController.class);

    public static final String splitStr = "\\|";

    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private ExternalApiConfig externalApiConfig;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PayOrderService payOrderService;
    @Autowired
    private PropertySrvService propertyService;
    @Autowired
    private AdUserRedPacketService userRedPacketService;
    @Autowired
    private PropertyinfoUserRedPacketService propertyinfoUserRedPacketService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MerchantStoreService merchantStoreService;
    @Autowired
    private AdService adService;
    @Autowired
    private PropertyinfoAdService propertyinfoAdService;
    @Autowired
    private RichPowerTaskRecordService richPowerTaskRecordService;

    /**
     * 支付宝异步回调接口
     *
     * @throws AlipayApiException
     * @throws IOException
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes"})
    @RequestMapping(value = "appNotifyHandle", method = RequestMethod.POST)
    public void appNotifyHandle() throws IOException, AlipayApiException {
        logger.info("#####################APP支付宝回调开始#####################");
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        Result result = successCreated();

        String member_public_key = externalApiConfig.getAlipayEdianMemberPublicKey();
        String merchant_public_key = externalApiConfig.getAlipayEdianBusinessPublicKey();
        String input_charset = externalApiConfig.getAlipayInputCharset();
        String app_id_member = externalApiConfig.getAlipayAppIdMember();
        String app_id_business = externalApiConfig.getAlipayAppIdBusiness();
        String alipaySignType = externalApiConfig.getAlipaySignType();

        Map<String, String[]> requestParams = request.getParameterMap();
        Map<String, String> params = new HashMap<String, String>();
        ParamUtil.parseAlipayResMap(params, requestParams);

        String app_id = new String(request.getParameter("app_id").getBytes("ISO-8859-1"), "UTF-8");
        boolean b;
        params.remove("sign_type");
        if (app_id_member.equals(app_id)) {
            b = AlipaySignature.rsaCheckV2(params, member_public_key, input_charset, alipaySignType);
        } else if (app_id_business.equals(app_id)) {
            b = AlipaySignature.rsaCheckV2(params, merchant_public_key, input_charset, alipaySignType);
        } else {
            logger.error("app回调失败！回调app_id不匹配");
            return;
        }
        if (!b) {
            logger.error("app回调失败！验签失败！");
            return;
        }
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
        String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
        String passbackParams = request.getParameter("passback_params");
        passbackParams = java.net.URLDecoder.decode(passbackParams, "utf-8");
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
        String buyer_logon_id = new String(request.getParameter("buyer_logon_id").getBytes("ISO-8859-1"), "UTF-8");
        if (passbackParams == null || "".equals(passbackParams)) {
            logger.error("app回调失败！回传参数passback_params为空！");
            return;
        }
        boolean isSendMsg = false;
        double dTotalMoney = 0;
        String merchantUserNum = "0";
        int bizFlagInt = 0;
        String extra[] = null;
        if ("TRADE_FINISHED".equals(trade_status)) {
            // 超3个月不允许商家退款
        } else if ("TRADE_SUCCESS".equals(trade_status)) {
            extra = passbackParams.split(splitStr);
            String bizFlag = extra[0];
            NotifyCallBackParam param = new NotifyCallBackParam();
            param.setBizFlag(bizFlag);
            param.setUserNum(extra[1]);
            param.setBody(extra[2]);
            param.setBizIds(extra[3]);
            param.setSideUserNum(extra[4]);
            param.setTotalFee(total_amount);
            param.setOutTradeNo(out_trade_no);
            param.setTradeNo(trade_no);
            param.setBuyerLogonId(buyer_logon_id);
            param.setTransactionPayTypeEnum(TransactionPayTypeEnum.ALIPAY);

            dTotalMoney = Double.parseDouble(total_amount);
            bizFlagInt = Integer.parseInt(bizFlag);
            if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BALANCE.getVal().equals(StringUtil.intToByte(bizFlagInt))
                    || ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))
                    || ThirdPartyBizFlagEnum.MEMBER_PAY_BALANCE.getVal().equals(StringUtil.intToByte(bizFlagInt))
                    || ThirdPartyBizFlagEnum.MEMBER_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                isSendMsg = true;
                result = rechargeService.doHandleRechargeNotify(param);

            } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BOND.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                param.setMerchantId(Long.valueOf(extra[5]));
                result = depositService.doHandleDepositNotify(param);

            } else if (ThirdPartyBizFlagEnum.MEMBER_PAY_ORDER.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                Result<ShoppingOrderMoneyDTO> order = payOrderService.orderMoneyForNotify(param.getBizIds());
                double money = order.getModel().getOrderTotalPrice().doubleValue();
                if (StringUtil.doubleCompareTo(money, dTotalMoney) == 0) {
                    Result<String> orderItemProductNameRet = payOrderService.getOrderItemProductName(param.getBizIds().split(",")[0]);
                    param.setTitle(orderItemProductNameRet.getModel());

                    result = orderService.doHandleOrderPayNotify(param);
                } else {
                    result.setRet(ResultCode.NOTIFY_MONEY_ERROR);
                    result.setMsg(ResultCode.get(ResultCode.NOTIFY_MONEY_ERROR));
                }
            } else if (ThirdPartyBizFlagEnum.MEMBER_PAY_BILL.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                ThirdPayCallBackQueryPayOrderDTO payOrderCallback = payOrderService.selectThirdPayCallBackQueryPayOrder(param.getBizIds());
                merchantUserNum = payOrderCallback.getBusinessUserNum();
                isSendMsg = true;
                if (StringUtil.doubleCompareTo(payOrderCallback.getActualMoney(), dTotalMoney) == 0) {
                    param.setRegionPath(extra[6]);

                    PayOrderBaseDTO dto = payOrderService.getPayOrderById(param.getBizIds());
                    Result<MemberDTO> member = memberService.findMemberInfoById(dto.getMemberId());
                    PayOrderMerchantStoreInfoDTO merchantStore = merchantStoreService.getPayOrderDetailStoreInfo(dto.getMerchantId());
                    param.setTitle(merchantStore.getName());
                    param.setTitleMerchant(StringUtil.hideUserAccount(member.getModel().getAccount()));

                    result = orderService.doHandlePayOrderNotify(param);
                } else {
                    result.setRet(ResultCode.NOTIFY_MONEY_ERROR);
                    result.setMsg(ResultCode.get(ResultCode.NOTIFY_MONEY_ERROR));
                }
            } else if (ThirdPartyBizFlagEnum.MEMBER_RED_PACKET.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                Result<ThirdPayCallBackQueryPayOrderDTO> moneyResult = userRedPacketService.selectUserRedPacketInfoForThrid(Long.valueOf(param.getBizIds()));
                if (StringUtil.doubleCompareTo(moneyResult.getModel().getActualMoney(), dTotalMoney) == 0) {
                    result = propertyinfoUserRedPacketService.doHandleMemberRedPacketNotify(param);
                } else {
                    result.setRet(ResultCode.NOTIFY_MONEY_ERROR);
                    result.setMsg(ResultCode.get(ResultCode.NOTIFY_MONEY_ERROR));
                }
            } else if (ThirdPartyBizFlagEnum.BUSINESS_ADD_AD.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                Result<AdPayInfoDTO> ad = adService.selectAdPayInfoById(Long.valueOf(param.getBizIds()));
                if (StringUtil.doubleCompareTo(ad.getModel().getTotalPoint().doubleValue(), dTotalMoney) == 0) {
                    param.setRegionPath(extra[6]);
                    result = propertyinfoAdService.doHandleMerchantAdNotify(param);
                } else {
                    result.setRet(ResultCode.NOTIFY_MONEY_ERROR);
                    result.setMsg(ResultCode.get(ResultCode.NOTIFY_MONEY_ERROR));
                }
            } else {
                logger.error("app回调失败！非法的业务类型回调！");
                return;
            }
        }
        //成功过处理或已处理过(重复处理)
        if (ResultCode.SUCCESS == result.getRet() || ResultCode.PROCESSED_RETURN_SUCCESS == result.getRet()) {
            logger.info("APP支付宝回调成功");
            PrintWriter out = response.getWriter();
            out.print("success");// 请不要修改或删除
            out.flush();
            out.close();

            // ------------------------------发送站内消息
            if (isSendMsg && ResultCode.SUCCESS == result.getRet()) {
                DecimalFormat df = new DecimalFormat("######0.00");
                MessageInfoParam messageInfoParam = new MessageInfoParam();
                messageInfoParam.setRelateId(0L);
                MessageTempParam messageTempParam = new MessageTempParam();
                messageTempParam.setRechargeBalance(new BigDecimal(df.format(dTotalMoney)));
                Result<PropertyPointAndBalanceDTO> moneyResult = propertyService.getPropertyInfoMoney(extra[1]);
                if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BALANCE.getVal().equals(StringUtil.intToByte(bizFlagInt))
                        || ThirdPartyBizFlagEnum.MEMBER_PAY_BALANCE.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_BALANCE);
                    messageTempParam.setBalance(moneyResult.getModel().getBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
                } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))
                        || ThirdPartyBizFlagEnum.MEMBER_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                    String property_key = PropertyType.MERCHANT_BALANCE_PAY_POINT_SCALE;
                    if (ThirdPartyBizFlagEnum.MEMBER_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                        property_key = PropertyType.MEMBER_BALANCE_PAY_POINT_SCALE;
                    }
                    String scale = propertyService.getValue(property_key).getModel().toString();
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_POINT);
                    messageTempParam.setPoint(moneyResult.getModel().getPoint().setScale(2, BigDecimal.ROUND_HALF_UP));
                    messageTempParam.setRechargePoint(new BigDecimal(df.format(dTotalMoney * Double.valueOf(scale))));
                } else if (ThirdPartyBizFlagEnum.MEMBER_PAY_BILL.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_PAY_ORDER_SUCCESS_MERCHANT);
                    messageTempParam.setOrderAmount(new BigDecimal(df.format(Double.valueOf(total_amount))));
                }
                if (extra[1].startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                    messageTempParam.setUserName("E店会员");
                } else if (extra[1].startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                    messageTempParam.setUserName("E店商家");
                }
                messageInfoParam.setMessageParam(messageTempParam);
                messageService.saveMessage(ThirdPartyBizFlagEnum.MEMBER_PAY_BILL.getVal().equals(StringUtil.intToByte(bizFlagInt)) ? merchantUserNum : extra[1], messageInfoParam);
            }
            // ------------------------------发送站内消息

            //商家瑞奇岛每日积分充值任务
            if (ResultCode.SUCCESS == result.getRet() && ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                RichMerchantPowerTaskRecordParam taskRecordParam = new RichMerchantPowerTaskRecordParam();
                taskRecordParam.setMerchantNum(extra[1]);
                taskRecordParam.setType(MerchantPowerTaskTypeEnum.RECHARGE_POINT);
                taskRecordParam.setPoint(Integer.valueOf(total_amount).intValue());
                richPowerTaskRecordService.saveRichMerchantPowerTaskRecord(taskRecordParam);
            }

        } else {
            logger.error("APP支付宝回调失败,错误码:{},错误信息：{},回调参数：{}", result.getRet(), result.getMsg(), requestParams);
        }
        logger.info("#####################APP支付宝回调结束#####################");
    }

    /**
     * 支付宝异步回调接口
     *
     * @throws IOException
     * @throws
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes"})
    @RequestMapping(value = "pcNotifyHandle", method = RequestMethod.POST)
    public void pcNotifyHandle() throws IOException {
        logger.info("#####################PC支付宝回调开始#####################");
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        Result result = successCreated();

        Map<String, String[]> requestParams = request.getParameterMap();
        Map<String, String> params = new HashMap<String, String>();
        ParamUtil.parseAlipayResMap(params, requestParams);

        AliPayConfigParam aliPayConfigParam = new AliPayConfigParam();
        aliPayConfigParam.setAlipayPublicKey(externalApiConfig.getAlipayPublicKey());
        aliPayConfigParam.setAlipayPartner(externalApiConfig.getAlipayPartner());
        aliPayConfigParam.setAlipayHttpsVerifyUrl(externalApiConfig.getAlipayHttpsVerifyUrl());
        aliPayConfigParam.setAlipaySignType(externalApiConfig.getAlipaySignType());
        aliPayConfigParam.setAlipayInputCharset(externalApiConfig.getAlipayInputCharset());

        boolean isSendMsg = false;
        double dTotalFee = 0;
        int bizFlagInt = 0;
        String extra[] = null;
        boolean b = AlipayNotify.verify(params, aliPayConfigParam);
        if (!b) {
            logger.error("PC回调失败！验签失败！");
            return;
        }

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
        String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
        String extraCommonParam = request.getParameter("extra_common_param");
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
        String buyer_email = new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");

        if (extraCommonParam == null || "".equals(extraCommonParam)) {
            logger.error("PC回调失败！回传参数extra_common_param为空！");
            return;
        }

        if ("TRADE_FINISHED".equals(trade_status)) {
            // 超3个月不允许商家退款
        } else if ("TRADE_SUCCESS".equals(trade_status)) {
            extra = extraCommonParam.split(splitStr);
            // 1-商家充值余额、2-商家充值积分、3-缴纳保证金、4-用户充值余额、5-用户充值积分、6-订单付款、7-买单
            String bizFlag = extra[0];
            NotifyCallBackParam param = new NotifyCallBackParam();
            param.setBizFlag(bizFlag);
            param.setUserNum(extra[1]);
            param.setBody(extra[2]);
            param.setBizIds(extra[3]);
            param.setTotalFee(total_fee);
            param.setOutTradeNo(out_trade_no);
            param.setTradeNo(trade_no);
            param.setBuyerLogonId(buyer_email);
            param.setTransactionPayTypeEnum(TransactionPayTypeEnum.ALIPAY);

            dTotalFee = Double.parseDouble(total_fee);
            bizFlagInt = Integer.parseInt(bizFlag);
            if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BALANCE.getVal().equals(StringUtil.intToByte(bizFlagInt))
                    || ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                result = rechargeService.doHandleRechargeNotify(param);
                isSendMsg = true;

            } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BOND.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                param.setMerchantId(Long.valueOf(extra[4]));
                result = depositService.doHandleDepositNotify(param);

            } else if (ThirdPartyBizFlagEnum.BUSINESS_ADD_AD.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                Result<AdPayInfoDTO> ad = adService.selectAdPayInfoById(Long.valueOf(param.getBizIds()));
                if (StringUtil.doubleCompareTo(ad.getModel().getTotalPoint().doubleValue(), dTotalFee) == 0) {
                    param.setRegionPath(extra[4]);
                    result = propertyinfoAdService.doHandleMerchantAdNotify(param);
                } else {
                    result.setRet(ResultCode.NOTIFY_MONEY_ERROR);
                    result.setMsg(ResultCode.get(ResultCode.NOTIFY_MONEY_ERROR));
                }
            } else {
                logger.error("PC回调失败！非法的业务类型回调！");
                return;
            }
        }

        //成功过处理或已处理过(重复处理)
        if (ResultCode.SUCCESS == result.getRet() || ResultCode.PROCESSED_RETURN_SUCCESS == result.getRet()) {
            logger.info("APP支付宝回调成功");
            PrintWriter out = response.getWriter();
            out.print("success");// 请不要修改或删除
            out.flush();
            out.close();

            if (isSendMsg && ResultCode.SUCCESS == result.getRet()) {
                DecimalFormat df = new DecimalFormat("######0.00");
                MessageInfoParam messageInfoParam = new MessageInfoParam();
                messageInfoParam.setRelateId(0L);
                MessageTempParam messageTempParam = new MessageTempParam();
                messageTempParam.setRechargeBalance(new BigDecimal(df.format(dTotalFee)));
                Result<PropertyPointAndBalanceDTO> moneyResult = propertyService.getPropertyInfoMoney(extra[1]);
                if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BALANCE.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_BALANCE);
                    messageTempParam.setBalance(moneyResult.getModel().getBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
                } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_POINT);
                    messageTempParam.setPoint(moneyResult.getModel().getPoint().setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                messageTempParam.setUserName("E店商家");
                messageInfoParam.setMessageParam(messageTempParam);
                messageService.saveMessage(extra[1], messageInfoParam);
            }

            //商家瑞奇岛每日积分充值任务
            if (ResultCode.SUCCESS == result.getRet() && ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                RichMerchantPowerTaskRecordParam taskRecordParam = new RichMerchantPowerTaskRecordParam();
                taskRecordParam.setMerchantNum(extra[1]);
                taskRecordParam.setType(MerchantPowerTaskTypeEnum.RECHARGE_POINT);
                taskRecordParam.setPoint(Integer.valueOf(total_fee).intValue());
                richPowerTaskRecordService.saveRichMerchantPowerTaskRecord(taskRecordParam);
            }

        } else {
            logger.error("PC支付宝回调失败,错误码:{},错误信息：{},回调参数：{}", result.getRet(), result.getMsg(), requestParams);
        }
        logger.info("#####################PC支付宝回调结束#####################");
    }


    @SuppressWarnings({"rawtypes"})
    @RequestMapping(value = "pcNotifyHandle2048", method = RequestMethod.POST)
    public void pcNotifyHandle2048() throws IOException {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        Result result = successCreated();

        Map<String, String[]> requestParams = request.getParameterMap();
        Map<String, String> params = new HashMap<String, String>();
        ParamUtil.parseAlipayResMap(params, requestParams);

        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, externalApiConfig.getAlipayEdianBusinessPublicKey(), externalApiConfig.getAlipayInputCharset(), externalApiConfig.getAlipaySignType());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (!signVerified) {
            logger.error("验签失败！");
            return;
        }

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
        String totalAmount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
        String passbackParams = request.getParameter("passback_params");
        passbackParams = java.net.URLDecoder.decode(passbackParams, "utf-8");
        String buyerId = new String(request.getParameter("buyer_id").getBytes("ISO-8859-1"), "UTF-8");
        if (passbackParams == null || "".equals(passbackParams)) {
            logger.error("回调失败，网页支付回调回传参数passback_params为空！");
            return;
        }

        if (trade_status.equals("TRADE_FINISHED")) {
            //3个月
        } else if (trade_status.equals("TRADE_SUCCESS")) {
            boolean isSendMsg = false;
            String extra[] = passbackParams.split(splitStr);
            // 1-商家充值余额、2-商家充值积分、3-缴纳保证金、4-用户充值余额、5-用户充值积分、6-订单付款、7-买单
            String bizFlag = extra[0];
            NotifyCallBackParam param = new NotifyCallBackParam();
            param.setBizFlag(bizFlag);
            param.setUserNum(extra[1]);
            param.setBody(extra[2]);
            param.setBizIds(extra[3]);
            param.setTotalFee(totalAmount);
            param.setOutTradeNo(out_trade_no);
            param.setTradeNo(trade_no);
            param.setBuyerLogonId(buyerId);
            param.setTransactionPayTypeEnum(TransactionPayTypeEnum.ALIPAY);
            double dTotalFee = Double.parseDouble(totalAmount);
            int bizFlagInt = Integer.parseInt(bizFlag);
            if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BALANCE.getVal().equals(StringUtil.intToByte(bizFlagInt))
                    || ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                result = rechargeService.doHandleRechargeNotify(param);
                isSendMsg = true;

            } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BOND.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                param.setMerchantId(Long.valueOf(extra[4]));
                result = depositService.doHandleDepositNotify(param);

            } else if (ThirdPartyBizFlagEnum.BUSINESS_ADD_AD.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                Result<AdPayInfoDTO> ad = adService.selectAdPayInfoById(Long.valueOf(param.getBizIds()));
                if (StringUtil.doubleCompareTo(ad.getModel().getTotalPoint().doubleValue(), dTotalFee) == 0) {
                    param.setRegionPath(extra[4]);
                    result = propertyinfoAdService.doHandleMerchantAdNotify(param);
                } else {
                    result.setRet(ResultCode.NOTIFY_MONEY_ERROR);
                    result.setMsg(ResultCode.get(ResultCode.NOTIFY_MONEY_ERROR));
                }
            } else {
                logger.error("app回调失败！非法的业务类型回调！");
                return;
            }
            //成功过处理或已处理过(重复处理)
            if (ResultCode.SUCCESS == result.getRet() || ResultCode.PROCESSED_RETURN_SUCCESS == result.getRet()) {
                logger.info("APP支付宝回调成功");
                PrintWriter out = response.getWriter();
                out.print("success");
                out.flush();
                out.close();

                if (isSendMsg && ResultCode.SUCCESS == result.getRet()) {
                    DecimalFormat df = new DecimalFormat("######0.00");
                    MessageInfoParam messageInfoParam = new MessageInfoParam();
                    messageInfoParam.setRelateId(0L);
                    MessageTempParam messageTempParam = new MessageTempParam();
                    messageTempParam.setRechargeBalance(new BigDecimal(df.format(dTotalFee)));
                    Result<PropertyPointAndBalanceDTO> moneyResult = propertyService.getPropertyInfoMoney(extra[1]);
                    if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BALANCE.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                        messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_BALANCE);
                        messageTempParam.setBalance(moneyResult.getModel().getBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
                    } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                        messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RECHARGE_POINT);
                        messageTempParam.setPoint(moneyResult.getModel().getPoint().setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    messageTempParam.setUserName("E店商家");
                    messageInfoParam.setMessageParam(messageTempParam);
                    messageService.saveMessage(extra[1], messageInfoParam);
                }

                //商家瑞奇岛每日积分充值任务
                if (ResultCode.SUCCESS == result.getRet() && ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                    RichMerchantPowerTaskRecordParam taskRecordParam = new RichMerchantPowerTaskRecordParam();
                    taskRecordParam.setMerchantNum(extra[1]);
                    taskRecordParam.setType(MerchantPowerTaskTypeEnum.RECHARGE_POINT);
                    taskRecordParam.setPoint(Integer.valueOf(totalAmount).intValue());
                    richPowerTaskRecordService.saveRichMerchantPowerTaskRecord(taskRecordParam);
                }

            } else {
                logger.error("PC支付宝回调失败,错误码:{},错误信息：{},回调参数：{}", result.getRet(), result.getMsg(), requestParams);
            }
        }
    }
}
