package com.lawu.eshop.external.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.external.api.service.*;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.AdPayInfoDTO;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.external.api.ExternalApiConfig;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.order.dto.PayOrderBaseDTO;
import com.lawu.eshop.order.dto.ShoppingOrderMoneyDTO;
import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.pay.sdk.weixin.base.PayCommonUtil;
import com.lawu.eshop.pay.sdk.weixin.base.XMLUtil;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.constants.ThirdPartyBizFlagEnum;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.PayOrderMerchantStoreInfoDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.StringUtil;

/**
 * <p>
 * Description: 微信第三方回调入口
 * </p>
 *
 */
@RestController
@RequestMapping(value = "wxpay/")
public class WxpayNotifyController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(WxpayNotifyController.class);

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
     * APP微信异步回调接口
     *
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes"})
    @RequestMapping(value = "appNotifyHandle", method = RequestMethod.POST)
    public void appNotifyHandle() throws Exception {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        Result result = successCreated();

        boolean isSendMsg = false;
        double dmoney = 0;
        int bizFlagInt = 0;
        String extra[] = null;
        String merchantUserNum = "0";
        SortedMap<Object, Object> packageParams = parseWxNotifyData(request);
        if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, externalApiConfig.getWxpayKeyApp())) {
            String return_code = packageParams.get("return_code") == null ? "" : packageParams.get("return_code").toString();
            if ("SUCCESS".equals(return_code)) {
                String result_code = packageParams.get("result_code") == null ? "" : packageParams.get("result_code").toString();
                if ("SUCCESS".equals(result_code)) {
                    String attach = packageParams.get("attach") == null ? "" : packageParams.get("attach").toString();
                    String transaction_id = packageParams.get("transaction_id") == null ? "" : packageParams.get("transaction_id").toString();
                    String total_fee = packageParams.get("total_fee") == null ? "" : packageParams.get("total_fee").toString();
                    String out_trade_no = packageParams.get("out_trade_no") == null ? "" : packageParams.get("out_trade_no").toString();

                    extra = attach.split(splitStr);
                    dmoney = Double.parseDouble(total_fee);
                    dmoney = dmoney / 100;
                    // 1-商家充值余额、2-商家充值积分、3-缴纳保证金、4-用户充值余额、5-用户充值积分、6-订单付款、7-买单
                    String bizFlag = extra[0];
                    NotifyCallBackParam param = new NotifyCallBackParam();
                    param.setBizFlag(bizFlag);
                    param.setUserNum(extra[1]);
                    param.setBody(extra[2]);
                    param.setBizIds(extra[3]);
                    param.setSideUserNum(extra[4]);
                    param.setTotalFee(String.valueOf(dmoney));
                    param.setOutTradeNo(out_trade_no);
                    param.setTradeNo(transaction_id);
                    param.setBuyerLogonId("回调没返回");
                    param.setTransactionPayTypeEnum(TransactionPayTypeEnum.WX);

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
                        if (order == null || order.getModel() == null) {
                            result = successCreated(ResultCode.FAIL, "找不到订单:" + param.getBizIds());
                        } else {
                            double money = order.getModel().getOrderTotalPrice().doubleValue();
                            if (StringUtil.doubleCompareTo(money, dmoney) == 0) {
                                Result<String> orderItemProductNameRet = payOrderService.getOrderItemProductName(param.getBizIds().split(",")[0]);
                                param.setTitle(orderItemProductNameRet.getModel());

                                result = orderService.doHandleOrderPayNotify(param);
                            } else {
                                result.setRet(ResultCode.NOTIFY_MONEY_ERROR);
                                result.setMsg(ResultCode.get(ResultCode.NOTIFY_MONEY_ERROR));
                            }
                        }
                    } else if (ThirdPartyBizFlagEnum.MEMBER_PAY_BILL.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                        ThirdPayCallBackQueryPayOrderDTO payOrderCallback = payOrderService
                                .selectThirdPayCallBackQueryPayOrder(param.getBizIds());
                        merchantUserNum = payOrderCallback.getBusinessUserNum();
                        isSendMsg = true;
                        if (StringUtil.doubleCompareTo(payOrderCallback.getActualMoney(), dmoney) == 0) {
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
                        if (StringUtil.doubleCompareTo(moneyResult.getModel().getActualMoney(), dmoney) == 0) {
                            result = propertyinfoUserRedPacketService.doHandleMemberRedPacketNotify(param);
                        } else {
                            result.setRet(ResultCode.NOTIFY_MONEY_ERROR);
                            result.setMsg(ResultCode.get(ResultCode.NOTIFY_MONEY_ERROR));
                        }
                    } else if (ThirdPartyBizFlagEnum.BUSINESS_ADD_AD.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                        Result<AdPayInfoDTO> ad = adService.selectAdPayInfoById(Long.valueOf(param.getBizIds()));
                        if (StringUtil.doubleCompareTo(ad.getModel().getTotalPoint().doubleValue(), dmoney) == 0) {
                            param.setRegionPath(extra[6]);
                            result = propertyinfoAdService.doHandleMerchantAdNotify(param);
                        } else {
                            result.setRet(ResultCode.NOTIFY_MONEY_ERROR);
                            result.setMsg(ResultCode.get(ResultCode.NOTIFY_MONEY_ERROR));
                        }
                    } else {
                        result = successCreated(ResultCode.FAIL, "非法的业务类型回调");
                    }

                } else {

                    result = successCreated(ResultCode.FAIL, "APP端微信回调失败，return_code成功，但业务结果失败，err_code="
                            + packageParams.get("err_code") + "，err_code_des=" + packageParams.get("err_code_des"));
                }
            } else {
                result = successCreated(ResultCode.FAIL,
                        "APP端微信回调失败，返回代码return_code=" + return_code + "，return_msg=" + packageParams.get("return_msg"));
            }
        } else {
            result = successCreated(ResultCode.FAIL, "APP微信回调验签失败！");
        }

        //成功过处理或已处理过(重复处理)
        if (ResultCode.SUCCESS == result.getRet() || ResultCode.PROCESSED_RETURN_SUCCESS == result.getRet()) {
            logger.info("APP微信回调成功");
            PrintWriter out = response.getWriter();
            out.print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");// 请不要修改或删除
            out.flush();
            out.close();

            // ------------------------------发送站内消息
            if (isSendMsg && ResultCode.SUCCESS == result.getRet()) {
                DecimalFormat df = new DecimalFormat("######0.00");
                MessageInfoParam messageInfoParam = new MessageInfoParam();
                messageInfoParam.setRelateId(0L);
                MessageTempParam messageTempParam = new MessageTempParam();
                messageTempParam.setRechargeBalance(new BigDecimal(df.format(dmoney)));
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
                    messageTempParam.setRechargeBalance(new BigDecimal(df.format(dmoney)));
                    messageTempParam.setRechargePoint(new BigDecimal(dmoney * Double.valueOf(scale)));
                } else if (ThirdPartyBizFlagEnum.MEMBER_PAY_BILL.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_PAY_ORDER_SUCCESS_MERCHANT);
                    messageTempParam.setOrderAmount(new BigDecimal(df.format(dmoney)));
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
                taskRecordParam.setPoint(Double.valueOf(dmoney).intValue());
                richPowerTaskRecordService.saveRichMerchantPowerTaskRecord(taskRecordParam);
            }

        } else {
            logger.error("APP微信回调失败,错误码:{},错误信息：{},回调参数：{}", result.getRet(), result.getMsg(), packageParams);
        }
    }

    /**
     * PC微信异步回调接口
     *
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes"})
    @RequestMapping(value = "pcNotifyHandle", method = RequestMethod.POST)
    public void pcNotifyHandle() throws Exception {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        Result result;

        boolean isSendMsg = false;
        double dmoney = 0;
        int bizFlagInt = 0;
        String extra[] = null;
        SortedMap<Object, Object> packageParams = parseWxNotifyData(request);
        if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, externalApiConfig.getWxpayKey())) {
            String return_code = packageParams.get("return_code") == null ? "" : packageParams.get("return_code").toString();
            if ("SUCCESS".equals(return_code)) {
                String result_code = packageParams.get("result_code") == null ? "" : packageParams.get("result_code").toString();
                if ("SUCCESS".equals(result_code)) {
                    String attach = packageParams.get("attach") == null ? "" : packageParams.get("attach").toString();
                    String transaction_id = packageParams.get("transaction_id") == null ? "" : packageParams.get("transaction_id").toString();
                    String total_fee = packageParams.get("total_fee") == null ? "" : packageParams.get("total_fee").toString();
                    String out_trade_no = packageParams.get("out_trade_no") == null ? "" : packageParams.get("out_trade_no").toString();

                    extra = attach.split(splitStr);
                    dmoney = Double.parseDouble(total_fee);
                    dmoney = dmoney / 100;
                    // 1-商家充值余额、2-商家充值积分、3-缴纳保证金
                    String bizFlag = extra[0];
                    NotifyCallBackParam param = new NotifyCallBackParam();
                    param.setBizFlag(bizFlag);
                    param.setUserNum(extra[1]);
                    param.setBody(extra[2]);
                    param.setBizIds(extra[3]);
                    param.setTotalFee(String.valueOf(dmoney));
                    param.setOutTradeNo(out_trade_no);
                    param.setTradeNo(transaction_id);
                    param.setBuyerLogonId("回调没返回");
                    param.setTransactionPayTypeEnum(TransactionPayTypeEnum.WX);

                    bizFlagInt = Integer.parseInt(bizFlag);
                    if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BALANCE.getVal().equals(StringUtil.intToByte(bizFlagInt))
                            || ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                        isSendMsg = true;
                        result = rechargeService.doHandleRechargeNotify(param);

                    } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BOND.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                        param.setMerchantId(Long.valueOf(extra[5]));
                        result = depositService.doHandleDepositNotify(param);

                    } else if (ThirdPartyBizFlagEnum.BUSINESS_ADD_AD.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                        Result<AdPayInfoDTO> ad = adService.selectAdPayInfoById(Long.valueOf(param.getBizIds()));
                        if (StringUtil.doubleCompareTo(ad.getModel().getTotalPoint().doubleValue(), dmoney) == 0) {
                            param.setRegionPath(extra[6]);
                            result = propertyinfoAdService.doHandleMerchantAdNotify(param);
                        } else {
                            logger.error("商家发广告微信支付后回调金额校验不通过！");
                            return;
                        }
                    } else {
                        result = successCreated(ResultCode.FAIL, "非法的业务类型回调");
                    }

                } else {

                    result = successCreated(ResultCode.FAIL, "PC端微信回调失败，return_code成功，但业务结果失败，err_code="
                            + packageParams.get("err_code") + "，err_code_des=" + packageParams.get("err_code_des"));
                }
            } else {
                result = successCreated(ResultCode.FAIL,
                        "APP端微信回调失败，返回代码return_code=" + return_code + "，return_msg=" + packageParams.get("return_msg"));
            }
        } else {
            result = successCreated(ResultCode.FAIL, "PC微信回调验签失败！");
        }

        //成功过处理或已处理过(重复处理)
        if (ResultCode.SUCCESS == result.getRet() || ResultCode.PROCESSED_RETURN_SUCCESS == result.getRet()) {
            logger.info("APP微信回调成功");
            PrintWriter out = response.getWriter();
            out.print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");// 请不要修改或删除
            out.flush();
            out.close();

            // ------------------------------发送站内消息
            if (isSendMsg && ResultCode.SUCCESS == result.getRet()) {
                DecimalFormat df = new DecimalFormat("######0.00");
                MessageInfoParam messageInfoParam = new MessageInfoParam();
                messageInfoParam.setRelateId(0L);
                MessageTempParam messageTempParam = new MessageTempParam();
                messageTempParam.setRechargeBalance(new BigDecimal(df.format(dmoney)));
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
            // ------------------------------发送站内消息

            //商家瑞奇岛每日积分充值任务
            if (ResultCode.SUCCESS == result.getRet() && ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(StringUtil.intToByte(bizFlagInt))) {
                RichMerchantPowerTaskRecordParam taskRecordParam = new RichMerchantPowerTaskRecordParam();
                taskRecordParam.setMerchantNum(extra[1]);
                taskRecordParam.setType(MerchantPowerTaskTypeEnum.RECHARGE_POINT);
                taskRecordParam.setPoint(Double.valueOf(dmoney).intValue());
                richPowerTaskRecordService.saveRichMerchantPowerTaskRecord(taskRecordParam);
            }

        } else {
            logger.error("PC微信回调失败,错误码:{},错误信息：{},回调参数：{}", result.getRet(), result.getMsg(), packageParams);
        }
    }

    /**
     * 获取微信异步post回调数据，并封装map
     *
     * @param request
     * @return
     * @throws IOException
     * @throws JDOMException
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private SortedMap<Object, Object> parseWxNotifyData(HttpServletRequest request) throws JDOMException, IOException {
        // 读取参数
        InputStream inputStream;
        StringBuilder sb = new StringBuilder();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        // 解析xml成map
        Map<String, String> m = XMLUtil.doXMLParse(sb.toString());

        // 过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = m.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }
        return packageParams;
    }
}
