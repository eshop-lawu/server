package com.lawu.eshop.pay.handle;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.lawu.eshop.pay.ThirdPayRefundParam;
import com.lawu.eshop.pay.param.*;
import com.lawu.eshop.pay.param.AlipayUserInfoShareResponse;
import com.lawu.eshop.pay.sdk.alipay.AliPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.sdk.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Description: 处理支付对接业务
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月14日 下午3:25:25
 */
public class AlipayBusinessHandle {

    private static Logger logger = LoggerFactory.getLogger(AlipayBusinessHandle.class);

    /**
     * 支付宝退款
     *
     * @param rparam     参数
     * @param jsonResult 返回参数
     * @throws Exception
     */
    public static void refund(ThirdPayRefundParam rparam, JsonResult jsonResult, AliPayConfigParam aliPayConfigParam) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfigParam.getAlipayRefundUrl(), aliPayConfigParam.getAlipayAppIdMember(), aliPayConfigParam.getAlipayPrivateKey(), "JSON", aliPayConfigParam.getAlipayInputCharset(), aliPayConfigParam.getAlipayEdianMemberPublicKey(), aliPayConfigParam.getAlipaySignType());
        AlipayTradeRefundRequest req = new AlipayTradeRefundRequest();
        req.setBizContent("{" +
                "    \"trade_no\":\"" + rparam.getTradeNo() + "\"," +
                "    \"refund_amount\":" + rparam.getRefundMoney() + "," +
                "    \"out_request_no\":\"" + rparam.getRefundId() + "\"" +
                "  }");
        AlipayTradeRefundResponse res = alipayClient.execute(req);
        jsonResult.setSuccess(res.isSuccess());
        logger.info("支付宝退款接口code:{},msg:{},sub_code:{},sub_msg:{}", res.getCode(), res.getMsg(), res.getSubCode(), res.getSubMsg());
        if (!res.isSuccess()) {
            jsonResult.setMessage("code:" + res.getCode() + ",msg:" + res.getMsg());
        }

    }

    /**
     * alipay.fund.trans.toaccount.transfer(单笔转账到支付宝账户接口)     *
     *
     * @param param
     * @param jsonResult
     * @param aliPayConfigParam
     */
    public static void toaccountTransfer(AlipayToaccountTransferParam param, JsonResult jsonResult, AliPayConfigParam aliPayConfigParam) {
        jsonResult.setSuccess(false);
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfigParam.getAlipayGatewayUrl(), aliPayConfigParam.getAlipayAppIdMember(), aliPayConfigParam.getAlipayPrivateKey(), "json", aliPayConfigParam.getAlipayInputCharset(), aliPayConfigParam.getAlipayEdianMemberPublicKey(), aliPayConfigParam.getAlipaySignType());
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        param.getAmount().setScale(2, BigDecimal.ROUND_DOWN);
        request.setBizContent("{" +
                "\"out_biz_no\":\"" + param.getOutBizNo() + "\"," +
                "\"payee_type\":\"" + param.getPayeeType() + "\"," +
                "\"payee_account\":\"" + param.getPayeeAccount() + "\"," +
                "\"amount\":\"" + param.getAmount().setScale(2, BigDecimal.ROUND_DOWN).toString() + "\"," +
                "\"payer_show_name\":\"" + param.getPayerShowName() + "\"," +
                "\"payee_real_name\":\"" + param.getPayeeRealName() + "\"," +
                "\"remark\":\"" + param.getRemark() + "\"" +
                "}");
        try {
            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
            jsonResult.setSuccess(response.isSuccess());
            jsonResult.setCode(response.getCode());
            jsonResult.setSubCode(response.getSubCode());
            logger.info("单笔转账到支付宝账户接口code:{},msg:{},sub_code:{},sub_msg:{}", response.getCode(), response.getMsg(), response.getSubCode(), response.getSubMsg());
            jsonResult.setMessage("code:" + response.getCode() + ",msg:" + response.getMsg() + ",sub_code:" + response.getSubCode() + ",sub_msg:" + response.getSubMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * alipay.fund.trans.order.query(查询转账订单接口)
     *
     * @param outBizNo
     * @param jsonResult
     * @param aliPayConfigParam
     */
    public static void transOrderQuery(String outBizNo, JsonResult jsonResult, AliPayConfigParam aliPayConfigParam) {
        jsonResult.setSuccess(false);
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfigParam.getAlipayGatewayUrl(), aliPayConfigParam.getAlipayAppIdMember(), aliPayConfigParam.getAlipayPrivateKey(), "json", aliPayConfigParam.getAlipayInputCharset(), aliPayConfigParam.getAlipayEdianMemberPublicKey(), aliPayConfigParam.getAlipaySignType());
        AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
        request.setBizContent("{" +
                "\"out_biz_no\":\"" + outBizNo + "\"" +
                "}");
        try {
            AlipayFundTransOrderQueryResponse response = alipayClient.execute(request);
            jsonResult.setCode(response.getCode());
            jsonResult.setSubCode(response.getSubCode());
            jsonResult.setStatus(response.getStatus());
            logger.info("查询转账订单接口code:{},msg:{},sub_code:{},sub_msg:{},status:{},fail_reason:{},error_code :{}", response.getCode(), response.getMsg(), response.getSubCode(), response.getSubMsg(), response.getStatus(), response.getFailReason(), response.getErrorCode());
            jsonResult.setMessage("code:" + response.getCode() + ",msg:" + response.getMsg() + ",sub_code:" + response.getSubCode() + ",sub_msg:" + response.getSubMsg() + ",status:" + response.getStatus() + ",fail_reason:" + response.getFailReason() + ",error_code :" + response.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * alipay.trade.page.pay 电脑网页支付  	统一收单下单并支付页面接口
     *
     * @param configParam
     * @param bizParam
     * @return
     */
    public static String tradePagePay(AliPayConfigParam configParam, AlipayTradePagePayParam bizParam) {
        AlipayClient alipayClient = new DefaultAlipayClient(configParam.getAlipayGatewayUrl(), configParam.getAlipayAppIdMerchant(), configParam.getAlipayPrivateKey(), "json", configParam.getAlipayInputCharset(), configParam.getAlipayEdianMerchantPublicKey(), configParam.getAlipaySignType());
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(configParam.getAlipayReturnUrlPc());
        alipayRequest.setNotifyUrl(configParam.getAlipayNotifyUrlPc());
        String form = "";
        try {
            alipayRequest.setBizContent("{" +
                    "    \"out_trade_no\":\"" + bizParam.getOutTradeNo() + "\"," +
                    "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                    "    \"total_amount\":" + bizParam.getTotalAmount() + "," +
                    "    \"subject\":\"" + bizParam.getSubject() + "\"," +
                    "    \"passback_params\":\"" + java.net.URLEncoder.encode(bizParam.getPassbackParams(), "utf-8") + "\"" +
                    "  }");
            form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }

    /**
     * alipay.system.oauth.token(换取授权访问令牌)
     *
     * @param configParam
     * @param alipayOauthTakenParam
     * @param alipayOauthTakenResponse
     * @return
     */
    public static void getOauthToken(AliPayConfigParam configParam, AlipayOauthTakenParam alipayOauthTakenParam, AlipayOauthTakenResponse alipayOauthTakenResponse) {
        AlipayClient alipayClient = getAlipayClient(configParam);
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(alipayOauthTakenParam.getCode());
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response != null && response.isSuccess()) {
            alipayOauthTakenResponse.setAlipayUserId(response.getUserId());
            alipayOauthTakenResponse.setAccessToken(response.getAccessToken());
        } else {
            logger.error("支付宝授权失败，{},{},{},{}", response.getCode(), response.getMsg(), response.getSubCode(), response.getSubMsg());
        }
    }

    /**
     * @param configParam
     * @return
     */
    private static AlipayClient getAlipayClient(AliPayConfigParam configParam) {
        return new DefaultAlipayClient(configParam.getAlipayGatewayUrl(), configParam.getAlipayAppIdMember(), configParam.getAlipayPrivateKey(), "json", configParam.getAlipayInputCharset(), configParam.getAlipayEdianMemberPublicKey(), configParam.getAlipaySignType());
    }

    /**
     * alipay.user.info.share(支付宝会员授权信息查询接口)
     *
     * @param configParam
     * @param alipayUserInfoShareParam
     * @param alipayUserInfoShareResponse
     */
    public static void getAliUserInfoShare(AliPayConfigParam configParam, AlipayUserInfoShareParam alipayUserInfoShareParam, AlipayUserInfoShareResponse alipayUserInfoShareResponse) {
        AlipayClient alipayClient = getAlipayClient(configParam);
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        com.alipay.api.response.AlipayUserInfoShareResponse response = null;
        try {
            response = alipayClient.execute(request,alipayUserInfoShareParam.getAccessToken());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response != null && response.isSuccess()){
            alipayUserInfoShareResponse.setUserId(response.getUserId());
            alipayUserInfoShareResponse.setAvatar(response.getAvatar());
            alipayUserInfoShareResponse.setProvince(response.getProvince());
            alipayUserInfoShareResponse.setCity(response.getCity());
            alipayUserInfoShareResponse.setNickName(response.getNickName());
            alipayUserInfoShareResponse.setIsStudentCertified(response.getIsStudentCertified());
            alipayUserInfoShareResponse.setUserType(response.getUserType());
            alipayUserInfoShareResponse.setUserStatus(response.getUserStatus());
            alipayUserInfoShareResponse.setIsCertified(response.getIsCertified());
            alipayUserInfoShareResponse.setGender(response.getGender());
        } else {
            logger.error("获取支付宝用户基本信息失败，{},{},{},{}", response.getCode(), response.getMsg(), response.getSubCode(), response.getSubMsg());
        }
    }
}
