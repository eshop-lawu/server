package com.lawu.eshop.property.srv.service.impl;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.pay.handle.AlipayBusinessHandle;
import com.lawu.eshop.pay.handle.WxpayBusinessHandle;
import com.lawu.eshop.pay.param.BizTypeEnum;
import com.lawu.eshop.pay.sdk.alipay.AliPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.base.WxPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.sdk.common.JsonResult;
import com.lawu.eshop.property.constants.ClientTypeEnum;
import com.lawu.eshop.property.param.ThirdPayRefundParam;
import com.lawu.eshop.property.srv.PropertySrvConfig;
import com.lawu.eshop.property.srv.service.PayQuickOperateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayQuickOperateImpl implements PayQuickOperateService {

    @Autowired
    private PropertySrvConfig propertySrvConfig;

    private static Logger logger = LoggerFactory.getLogger(PayQuickOperateImpl.class);

    @Override
    public void refund(ThirdPayRefundParam param) {
        JsonResult jsonResult = new JsonResult();
        com.lawu.eshop.pay.ThirdPayRefundParam rparam = new com.lawu.eshop.pay.ThirdPayRefundParam();
        rparam.setRefundId(IdWorkerHelperImpl.generate(BizIdType.BUSINESS));
        rparam.setRefundMoney(param.getRefundMoney().toString());
        rparam.setTradeNo(param.getTradeNo());
        if (TransactionPayTypeEnum.ALIPAY.getVal().equals(param.getPayType().getVal())) {
            AliPayConfigParam aliPayConfigParam = new AliPayConfigParam();
            aliPayConfigParam.setAlipayRefundUrl(propertySrvConfig.getAlipayRefundUrl());
            aliPayConfigParam.setAlipayAppIdMember(propertySrvConfig.getAlipayAppIdMember());
            aliPayConfigParam.setAlipayPrivateKey(propertySrvConfig.getAlipayPrivateKey());
            aliPayConfigParam.setAlipayEdianMemberPublicKey(propertySrvConfig.getAlipayEdianMemberPublicKey());
            aliPayConfigParam.setAlipaySignType(propertySrvConfig.getAlipaySignType());
            aliPayConfigParam.setAlipayInputCharset(propertySrvConfig.getAlipayInputCharset());
            aliPayConfigParam.setAlipaySignType(propertySrvConfig.getAlipaySignType());
            try{
                AlipayBusinessHandle.refund(rparam, jsonResult, aliPayConfigParam);
            }catch (Exception e){
                throw new RuntimeException(jsonResult.getMessage() + "；exception msg：" + e.getMessage());
            }
        } else if (TransactionPayTypeEnum.WX.getVal().equals(param.getPayType().getVal())) {

            rparam.setTotalMoney(param.getRefundTotalMoney().toString());
            WxPayConfigParam wxPayConfigParam = new WxPayConfigParam();
            wxPayConfigParam.setWxpayAppId(propertySrvConfig.getWxpayAppId());
            wxPayConfigParam.setWxpayMchId(propertySrvConfig.getWxpayMchId());
            if(ClientTypeEnum.MOBLIE.getVal().equals(param.getClientType().getVal())){
                wxPayConfigParam.setWxpayAppIdMember(propertySrvConfig.getWxpayAppIdBusiness());
                wxPayConfigParam.setWxpayMchIdMember(propertySrvConfig.getWxpayMchIdBusiness());
                wxPayConfigParam.setWxpayCertLocalPathMember(propertySrvConfig.getWxpayCertLocalPathBusinessApp());
                wxPayConfigParam.setWxpayCertBasePath(propertySrvConfig.getWxpayCertLocalPathBusinessApp());
                wxPayConfigParam.setWxpayCertPasswordMember(propertySrvConfig.getWxpayCertPasswordBusinessApp());
                wxPayConfigParam.setWxpayKeyApp(propertySrvConfig.getWxpayKeyApp());
            }else {
                wxPayConfigParam.setWxpayAppIdMember(propertySrvConfig.getWxpayAppId());
                wxPayConfigParam.setWxpayMchIdMember(propertySrvConfig.getWxpayMchId());
                wxPayConfigParam.setWxpayCertLocalPathMember(propertySrvConfig.getWxpayCertLocalPathBusinessPc());
                wxPayConfigParam.setWxpayCertBasePath(propertySrvConfig.getWxpayCertLocalPathBusinessPc());
                wxPayConfigParam.setWxpayCertPasswordMember(propertySrvConfig.getWxpayCertPasswordBusinessPc());
                wxPayConfigParam.setWxpayKeyApp(propertySrvConfig.getWxpayKey());
            }
            wxPayConfigParam.setWxpayRefundApi(propertySrvConfig.getWxpayRefundApi());
            wxPayConfigParam.setWxpayHttpsRequestClassName(propertySrvConfig.getWxpayHttpsRequestClassName());
            wxPayConfigParam.setBizTypeEnum(BizTypeEnum.REFUND);
            try{
                WxpayBusinessHandle.refund(rparam, jsonResult, wxPayConfigParam);
            }catch (Exception e){
                throw new RuntimeException(jsonResult.getMessage() + "；exception msg：" + e.getMessage());
            }
        }
        if (!jsonResult.isSuccess()) {
            throw new RuntimeException(jsonResult.getMessage() + "；exception msg：" + jsonResult.getMessage());
        }
    }
}
