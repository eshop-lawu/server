package com.lawu.eshop.property.srv.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.pay.handle.AlipayBusinessHandle;
import com.lawu.eshop.pay.param.*;
import com.lawu.eshop.pay.sdk.alipay.AliPayConfigParam;
import com.lawu.eshop.pay.sdk.alipay.util.AlipaySubmit;
import com.lawu.eshop.pay.sdk.alipay.util.ParamUtil;
import com.lawu.eshop.property.constants.ThirdPartyBizFlagEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.eshop.property.dto.OauthTokenRtnDTO;
import com.lawu.eshop.property.param.AlipayOauthDataParam;
import com.lawu.eshop.property.param.AlipayOauthParam;
import com.lawu.eshop.property.param.PcAlipayDataParam;
import com.lawu.eshop.property.param.ThirdPayDataParam;
import com.lawu.eshop.property.srv.PropertySrvConfig;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月7日 下午8:00:08
 */
@RestController
@RequestMapping(value = "alipay/")
public class AlipayController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(AlipayController.class);

    public static final String split = "|";

    @Autowired
    private PropertySrvConfig propertySrvConfig;

    /**
     * 客户端调用支付宝获取请求参数
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getAppAlipayReqParams", method = RequestMethod.POST)
    public Result getAppAlipayReqParams(@RequestBody @Valid ThirdPayDataParam param, BindingResult result) {

        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        String appId = "";
        String public_key = "";
        if (param.getUserTypeEnum().getVal().equals(UserTypeEnum.MEMBER.getVal())) {
            appId = propertySrvConfig.getAlipayAppIdMember();
            public_key = propertySrvConfig.getAlipayEdianMemberPublicKey();
        } else if (param.getUserTypeEnum().getVal().equals(UserTypeEnum.MERCHANT.getVal())) {
            appId = propertySrvConfig.getAlipayAppIdBusiness();
            public_key = propertySrvConfig.getAlipayEdianBusinessPublicKey();
        }
        String passbackParams = param.getBizFlagEnum().getVal() + split + param.getUserNum() + split + param.getThirdPayBodyEnum().getVal() + split + param.getBizIds() + split + param.getSideUserNum() + split + param.getMerchantId() + split + param.getRegionPath();
        passbackParams = java.net.URLEncoder.encode(passbackParams);
        // 实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(propertySrvConfig.getAlipayGateway(), appId,
                propertySrvConfig.getAlipayPrivateKey(), "json", "utf-8", public_key, propertySrvConfig.getAlipaySignType());
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setSubject(param.getSubject());
        if (param.getOutTradeNo() == null || "".equals(param.getOutTradeNo())) {
            model.setOutTradeNo(IdWorkerHelperImpl.generate(BizIdType.BUSINESS));
        } else {
            model.setOutTradeNo(param.getOutTradeNo());
        }
        model.setTotalAmount(param.getTotalAmount());
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setPassbackParams(passbackParams);
        request.setBizModel(model);
        request.setNotifyUrl(propertySrvConfig.getAlipayNotifyUrl());
        String msg = "";
        try {
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            msg = response.getBody();// 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            logger.error("支付宝支付封装参数错误，错误信息：{}", e.getMessage(), e);
            return successCreated(ResultCode.FAIL);
        }
        return successCreated(msg);
    }

    /**
     * pc端生成预支付订单返回扫码二维码
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "initPcPay", method = RequestMethod.POST)
    public Result initPcPay(@RequestBody @Valid PcAlipayDataParam param, BindingResult result) {

        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("service", "create_direct_pay_by_user");
        paramMap.put("partner", propertySrvConfig.getAlipayPartner());
        paramMap.put("seller_id", propertySrvConfig.getAlipaySellerId());
        paramMap.put("_input_charset", propertySrvConfig.getAlipayInputCharset());
        paramMap.put("payment_type", "1");
        paramMap.put("notify_url", propertySrvConfig.getAlipayNotifyUrlPc());
        paramMap.put("return_url", propertySrvConfig.getAlipayReturnUrlPc());
        paramMap.put("anti_phishing_key", "");
        paramMap.put("exter_invoke_ip", "");

        paramMap.put("out_trade_no", IdWorkerHelperImpl.generate(BizIdType.BUSINESS));
        paramMap.put("subject", param.getSubject());
        if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BOND.getVal().equals(param.getBizFlagEnum().getVal())) {
            paramMap.put("extra_common_param", param.getBizFlagEnum().getVal() + split + param.getUserNum() + split
                    + "商家缴纳保证金P" + split + param.getBizId() + split + param.getMerchantId());
        } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BALANCE.getVal().equals(param.getBizFlagEnum().getVal())) {
            paramMap.put("extra_common_param", param.getBizFlagEnum().getVal() + split + param.getUserNum() + split
                    + "商家充值余额P" + split + param.getBizId());
        } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(param.getBizFlagEnum().getVal())) {
            paramMap.put("extra_common_param", param.getBizFlagEnum().getVal() + split + param.getUserNum() + split
                    + "商家充值积分P" + split + param.getBizId());
        } else if (ThirdPartyBizFlagEnum.BUSINESS_ADD_AD.getVal().equals(param.getBizFlagEnum().getVal())) {
            paramMap.put("extra_common_param", param.getBizFlagEnum().getVal() + split + param.getUserNum() + split
                    + "发广告P" + split + param.getBizId() + split + param.getRegionPath());
        }
        paramMap.put("total_fee", param.getTotalAmount());

        AliPayConfigParam aliPayConfigParam = new AliPayConfigParam();
        aliPayConfigParam.setAlipaySignType(propertySrvConfig.getAlipaySignType());
        aliPayConfigParam.setAlipayPrivateKey(propertySrvConfig.getAlipayPrivateKey());
        aliPayConfigParam.setAlipayInputCharset(propertySrvConfig.getAlipayInputCharset());
        String sHtmlText = AlipaySubmit.buildRequest(paramMap, "get", "确认", aliPayConfigParam);

        String html = "<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">";
        sHtmlText = html + sHtmlText;

        return successCreated(sHtmlText);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "initPcPay2048", method = RequestMethod.POST)
    public Result initPcPay2048(@RequestBody @Valid PcAlipayDataParam param, BindingResult result) {

        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        StringBuilder passbackParams = new StringBuilder();
        if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BOND.getVal().equals(param.getBizFlagEnum().getVal())) {
            passbackParams.append(param.getBizFlagEnum().getVal()).append(split).append(param.getUserNum()).append(split).append("商家缴纳保证金P").append(split).append(param.getBizId() + split + param.getMerchantId());
        } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BALANCE.getVal().equals(param.getBizFlagEnum().getVal())) {
            passbackParams.append(param.getBizFlagEnum().getVal()).append(split).append(param.getUserNum()).append(split).append("商家充值余额P").append(split).append(param.getBizId());
        } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(param.getBizFlagEnum().getVal())) {
            passbackParams.append(param.getBizFlagEnum().getVal()).append(split).append(param.getUserNum()).append(split).append("商家充值积分P").append(split).append(param.getBizId());
        } else if (ThirdPartyBizFlagEnum.BUSINESS_ADD_AD.getVal().equals(param.getBizFlagEnum().getVal())) {
            passbackParams.append(param.getBizFlagEnum().getVal()).append(split).append(param.getUserNum()).append(split).append("发广告P").append(split).append(param.getBizId()).append(split).append(param.getRegionPath());
        }

        AliPayConfigParam configParam = new AliPayConfigParam();
        configParam.setAlipayGatewayUrl(propertySrvConfig.getAlipayGateway());
        configParam.setAlipayAppIdMerchant(propertySrvConfig.getAlipayAppIdBusiness());
        configParam.setAlipayPrivateKey(propertySrvConfig.getAlipayPrivateKey());
        configParam.setAlipayInputCharset(propertySrvConfig.getAlipayInputCharset());
        configParam.setAlipayEdianMerchantPublicKey(propertySrvConfig.getAlipayEdianBusinessPublicKey());
        configParam.setAlipaySignType(propertySrvConfig.getAlipaySignType());
        configParam.setAlipayNotifyUrlPc(propertySrvConfig.getAlipayNotifyUrlPc());
        configParam.setAlipayReturnUrlPc(propertySrvConfig.getAlipayReturnUrlPc());

        AlipayTradePagePayParam bizParam = new AlipayTradePagePayParam();
        bizParam.setOutTradeNo(IdWorkerHelperImpl.generate(BizIdType.BUSINESS));
        bizParam.setTotalAmount(param.getTotalAmount());
        bizParam.setSubject(param.getSubject());
        bizParam.setPassbackParams(passbackParams.toString());
        String ret = AlipayBusinessHandle.tradePagePay(configParam, bizParam);
        return successCreated(ret);
    }


    /**
     * com.alipay.account.auth
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "alipayUserInfoAuth", method = RequestMethod.POST)
    public Result alipayUserInfoAuth(@RequestBody @Valid AlipayOauthDataParam alipayOauthDataParam, BindingResult result) {

        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        String appId = propertySrvConfig.getAlipayAppIdMember();
        if (alipayOauthDataParam.getUserTypeEnum().getVal().equals(UserTypeEnum.MERCHANT.getVal())) {
            appId = propertySrvConfig.getAlipayAppIdBusiness();
        }
        String privateKey = propertySrvConfig.getAlipayPrivateKey();

        SortedMap<String, String> baseMap = ParamUtil.buildAuthInfoMap(propertySrvConfig.getAlipayPartner(), appId, IdWorkerHelperImpl.generate(BizIdType.TRANSACTION), propertySrvConfig.getAlipaySignType());
        String info = ParamUtil.buildOrderParam(baseMap);
        String oriSign;
        try {
            oriSign = AlipaySignature.rsaSign(info, privateKey, propertySrvConfig.getAlipayInputCharset(), propertySrvConfig.getAlipaySignType());
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return successCreated(ResultCode.FAIL);
        }
        String encodedSign;
        try {
            encodedSign = URLEncoder.encode(oriSign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return successCreated(ResultCode.FAIL);
        }
        return successCreated(info + "&sign=" + encodedSign);

    }

    /**
     * alipay.system.oauth.token(换取授权访问令牌)
     *
     * @param alipayOauthParam
     * @param result
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getOauthToken", method = RequestMethod.POST)
    public Result<OauthTokenRtnDTO> getOauthToken(@RequestBody @Valid AlipayOauthParam alipayOauthParam, BindingResult result) {

        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        AliPayConfigParam aliPayConfigParam = new AliPayConfigParam();
        aliPayConfigParam.setAlipayGatewayUrl(propertySrvConfig.getAlipayGateway());
        aliPayConfigParam.setAlipayAppIdMember(propertySrvConfig.getAlipayAppIdMember());
        aliPayConfigParam.setAlipayPrivateKey(propertySrvConfig.getAlipayPrivateKey());
        aliPayConfigParam.setAlipayEdianMemberPublicKey(propertySrvConfig.getAlipayEdianMemberPublicKey());
        aliPayConfigParam.setAlipaySignType(propertySrvConfig.getAlipaySignType());
        aliPayConfigParam.setAlipayInputCharset(propertySrvConfig.getAlipayInputCharset());
        if (alipayOauthParam.getUserTypeEnum().getVal().equals(UserTypeEnum.MERCHANT.getVal())) {
            aliPayConfigParam.setAlipayAppIdMember(propertySrvConfig.getAlipayAppIdBusiness());
            aliPayConfigParam.setAlipayEdianMemberPublicKey(propertySrvConfig.getAlipayEdianBusinessPublicKey());
        }
        //获取授权信息
        AlipayOauthTakenParam alipayOauthTakenParam = new AlipayOauthTakenParam();
        alipayOauthTakenParam.setCode(alipayOauthParam.getCode());
        AlipayOauthTakenResponse alipayOauthTakenResponse = new AlipayOauthTakenResponse();
        AlipayBusinessHandle.getOauthToken(aliPayConfigParam, alipayOauthTakenParam,alipayOauthTakenResponse);

        if (StringUtils.isNotBlank(alipayOauthTakenResponse.getAlipayUserId())) {
            //获取支付宝用户基本信息
            AlipayUserInfoShareParam alipayUserInfoShareParam = new AlipayUserInfoShareParam();
            alipayUserInfoShareParam.setAccessToken(alipayOauthTakenResponse.getAccessToken());
            AlipayUserInfoShareResponse alipayUserInfoShareResponse = new AlipayUserInfoShareResponse();
            AlipayBusinessHandle.getAliUserInfoShare(aliPayConfigParam,alipayUserInfoShareParam,alipayUserInfoShareResponse);

            OauthTokenRtnDTO oauthTokenRtnDTO = new OauthTokenRtnDTO();
            oauthTokenRtnDTO.setAliUserId(alipayOauthTakenResponse.getAlipayUserId());
            oauthTokenRtnDTO.setAvatar(alipayUserInfoShareResponse.getAvatar());
            oauthTokenRtnDTO.setProvince(alipayUserInfoShareResponse.getProvince());
            oauthTokenRtnDTO.setCity(alipayUserInfoShareResponse.getCity());
            oauthTokenRtnDTO.setNickName(alipayUserInfoShareResponse.getNickName());
            oauthTokenRtnDTO.setIsStudentCertified(alipayUserInfoShareResponse.getIsStudentCertified());
            oauthTokenRtnDTO.setUserType(alipayUserInfoShareResponse.getUserType());
            oauthTokenRtnDTO.setUserStatus(alipayUserInfoShareResponse.getUserStatus());
            oauthTokenRtnDTO.setIsCertified(alipayUserInfoShareResponse.getIsCertified());
            oauthTokenRtnDTO.setGender(alipayUserInfoShareResponse.getGender());
            return successCreated(oauthTokenRtnDTO);
        } else {
            return successCreated(ResultCode.FAIL);
        }
    }


}
