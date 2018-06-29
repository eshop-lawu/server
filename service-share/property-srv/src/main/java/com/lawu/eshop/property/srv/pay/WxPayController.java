package com.lawu.eshop.property.srv.pay;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.pay.sdk.weixin.base.HttpUtil;
import com.lawu.eshop.pay.sdk.weixin.base.PayCommonUtil;
import com.lawu.eshop.pay.sdk.weixin.base.RandomStringGenerator;
import com.lawu.eshop.pay.sdk.weixin.base.XMLUtil;
import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;
import com.lawu.eshop.property.constants.ThirdPartyBizFlagEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.eshop.property.param.ThirdPayDataParam;
import com.lawu.eshop.property.srv.PropertySrvConfig;
import com.lawu.eshop.property.srv.domain.BusinessDepositDO;
import com.lawu.eshop.property.srv.domain.BusinessDepositDOExample;
import com.lawu.eshop.property.srv.mapper.BusinessDepositDOMapper;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月7日 下午8:00:00
 */
@RestController
@RequestMapping(value = "wxPay/")
public class WxPayController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(WxPayController.class);

    public static final String split = "|";

    @Autowired
    private PropertySrvConfig propertySrvConfig;
    @Autowired
    private BusinessDepositDOMapper businessDepositDOMapper;

    /**
     * app和PC获取预支付订单返回的信息 1、app端通过后台服务获取prepay_id、pc获取预支付订单二维码 2、组装app调用支付接口参数
     *
     * @param param
     * @return
     * @throws IOException
     * @throws JDOMException
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getPrepayInfo", method = RequestMethod.POST)
    public Result getPrepayInfo(@RequestBody @Valid ThirdPayDataParam param, BindingResult result)
            throws JDOMException, IOException {

        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        //保证金判断重复付款
        if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BOND.getVal().equals(param.getBizFlagEnum().getVal())) {
            BusinessDepositDOExample businessDepositDOExample = new BusinessDepositDOExample();
            businessDepositDOExample.createCriteria().andUserNumEqualTo(param.getUserNum()).andStatusBetween(BusinessDepositStatusEnum.VERIFY.getVal(), BusinessDepositStatusEnum.ACCPET_REFUND.getVal());
            List<BusinessDepositDO> normarlBusinessList = businessDepositDOMapper.selectByExample(businessDepositDOExample);
            if (normarlBusinessList != null && !normarlBusinessList.isEmpty()) {
                return successCreated(ResultCode.FAIL, "重复支付保证金！");
            }
        }

        String key = propertySrvConfig.getWxpayKeyApp();
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("trade_type", "APP");
        packageParams.put("notify_url", propertySrvConfig.getWxpayNotifyUrl());
        if (UserTypeEnum.MEMBER.getVal() == param.getUserTypeEnum().getVal()) {
            packageParams.put("appid", propertySrvConfig.getWxpayAppIdMember());
            packageParams.put("mch_id", propertySrvConfig.getWxpayMchIdMember());
        } else if (UserTypeEnum.MERCHANT.getVal() == param.getUserTypeEnum().getVal()) {
            packageParams.put("appid", propertySrvConfig.getWxpayAppIdBusiness());
            packageParams.put("mch_id", propertySrvConfig.getWxpayMchIdBusiness());
        } else if (UserTypeEnum.MEMCHANT_PC.getVal() == param.getUserTypeEnum().getVal()) {
            packageParams.put("appid", propertySrvConfig.getWxpayAppId());
            packageParams.put("mch_id", propertySrvConfig.getWxpayMchId());
            packageParams.put("trade_type", "NATIVE");
            packageParams.put("notify_url", propertySrvConfig.getWxpayNotifyUrlPc());
            key = propertySrvConfig.getWxpayKey();
        }
        packageParams.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
        packageParams.put("body", param.getThirdPayBodyEnum().getVal());
        if (param.getOutTradeNo() == null || "".equals(param.getOutTradeNo())) {
            packageParams.put("out_trade_no", IdWorkerHelperImpl.generate(BizIdType.BUSINESS));
        } else {
            packageParams.put("out_trade_no", param.getOutTradeNo());
        }
        String totalFee = new BigDecimal(param.getTotalAmount()).multiply(new BigDecimal("100")).toString();
        if (totalFee.indexOf('.') > 0) {
            totalFee = totalFee.substring(0, totalFee.indexOf('.'));
        }
        packageParams.put("total_fee", totalFee);
        packageParams.put("spbill_create_ip", propertySrvConfig.getWxpayIp());
        packageParams.put("attach", param.getBizFlagEnum().getVal() + split + param.getUserNum() + split
                + param.getThirdPayBodyEnum().getVal() + split + param.getBizIds() + split + param.getSideUserNum() + split + param.getMerchantId() + split + param.getRegionPath());

        String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
        packageParams.put("sign", sign);

        String requestXML = PayCommonUtil.getRequestXml(packageParams);

        String resXml = HttpUtil.postData(propertySrvConfig.getWxpayNativePayApi(), requestXML);
        Map map = XMLUtil.doXMLParse(resXml);

        String return_code = map.get("return_code") == null ? "" : map.get("return_code").toString();
        if ("FAIL".equals(return_code)) {
            String return_msg = map.get("return_msg") == null ? "" : map.get("return_msg").toString();
            logger.error("微信支付预支付订单失败，return_code={},return_msg={}", return_code, return_msg);
            return successCreated(ResultCode.FAIL, return_code + ":" + return_msg);

        } else {
            String result_code = map.get("result_code") == null ? "" : map.get("result_code").toString();
            if ("FAIL".equals(result_code)) {
                String err_code = map.get("err_code") == null ? "" : map.get("err_code").toString();
                String err_code_des = map.get("err_code_des") == null ? "" : map.get("err_code_des").toString();
                logger.error("微信支付预支付订单失败，result_code={},err_code={},err_code_des={}", result_code, err_code,
                        err_code_des);
                return successCreated(ResultCode.FAIL, err_code + ":" + err_code_des);
            } else {
                if (UserTypeEnum.MEMCHANT_PC.getVal() == param.getUserTypeEnum().getVal()) {
                    String code_url = map.get("code_url") == null ? "" : map.get("code_url").toString();
                    packageParams.clear();
                    packageParams.put("codeUrl", code_url);
                } else {
                    String prepay_id = map.get("prepay_id") == null ? "" : map.get("prepay_id").toString();
                    packageParams.clear();
                    if (UserTypeEnum.MEMBER.getVal() == param.getUserTypeEnum().getVal()) {
                        packageParams.put("appid", propertySrvConfig.getWxpayAppIdMember());
                        packageParams.put("partnerid", propertySrvConfig.getWxpayMchIdMember());
                    } else if (UserTypeEnum.MERCHANT.getVal() == param.getUserTypeEnum().getVal()) {
                        packageParams.put("appid", propertySrvConfig.getWxpayAppIdBusiness());
                        packageParams.put("partnerid", propertySrvConfig.getWxpayMchIdBusiness());
                    }
                    packageParams.put("prepayid", prepay_id);
                    packageParams.put("package", "Sign=WXPay");
                    packageParams.put("noncestr", RandomStringGenerator.getRandomStringByLength(32));
                    String timestamp = String.valueOf(System.currentTimeMillis());
                    packageParams.put("timestamp", timestamp.substring(0, 10));
                    sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
                    packageParams.put("sign", sign);
                }
            }
        }
        return successCreated(packageParams);
    }

}
