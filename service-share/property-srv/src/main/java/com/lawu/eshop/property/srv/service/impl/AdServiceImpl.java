package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.pay.param.BizTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.pay.ThirdPayRefundParam;
import com.lawu.eshop.pay.handle.AlipayBusinessHandle;
import com.lawu.eshop.pay.handle.WxpayBusinessHandle;
import com.lawu.eshop.pay.sdk.alipay.AliPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.base.WxPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.sdk.common.JsonResult;
import com.lawu.eshop.property.constants.ClientTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.constants.PropertyInfoDirectionEnum;
import com.lawu.eshop.property.param.MerchantAdRefundDataParam;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.param.TransactionDetailSaveDataParam;
import com.lawu.eshop.property.srv.PropertySrvConfig;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;
import com.lawu.eshop.property.srv.domain.TransactionDetailDOExample;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;
import com.lawu.eshop.property.srv.service.AdService;
import com.lawu.eshop.property.srv.service.TransactionDetailService;

@Service
public class AdServiceImpl implements AdService {

    private static Logger logger = LoggerFactory.getLogger(AdServiceImpl.class);

    @Autowired
    private TransactionDetailService transactionDetailService;

    @Autowired
    @Qualifier("merchantAdPaymentTransactionMainServiceImpl")
    private TransactionMainService<Reply> merchantAdPaymentTransactionMainServiceImpl;
    @Autowired
    private PropertySrvConfig propertySrvConfig;
    @Autowired
    private TransactionDetailDOMapper transactionDetailDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doHandleMerchantAdNotify(NotifyCallBackParam param) {
        boolean isPay = transactionDetailService.verifyOrderIsPaySuccess(param);
        if (isPay) {
            logger.info("重复回调(判断幂等)-商家发E咻&红包");
            return ResultCode.PROCESSED_RETURN_SUCCESS;
        }

        // 新增商家交易记录
        TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
        tdsParam.setTitle(MerchantTransactionTypeEnum.RED_PACKET.getName());
        tdsParam.setUserNum(param.getUserNum());
        tdsParam.setTransactionAccount(param.getBuyerLogonId());
        tdsParam.setTransactionType(MerchantTransactionTypeEnum.RED_PACKET.getValue());
        tdsParam.setTransactionAccountType(param.getTransactionPayTypeEnum().getVal());
        tdsParam.setAmount(new BigDecimal(param.getTotalFee()));
        tdsParam.setBizId(param.getBizIds());
        tdsParam.setThirdTransactionNum(param.getTradeNo());
        tdsParam.setDirection(PropertyInfoDirectionEnum.OUT.getVal());
        tdsParam.setBizNum(param.getOutTradeNo());
        tdsParam.setRegionPath(param.getRegionPath());
        tdsParam.setTransactionDesc(MerchantTransactionTypeEnum.RED_PACKET.getDescPrefix());
        transactionDetailService.save(tdsParam);

        merchantAdPaymentTransactionMainServiceImpl.sendNotice(tdsParam.getId());

        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doRefund(MerchantAdRefundDataParam param) {
        TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
        tdsParam.setTitle(MerchantTransactionTypeEnum.AD_DOWN.getName());
        tdsParam.setUserNum(param.getUserNum());
        tdsParam.setTransactionType(MerchantTransactionTypeEnum.AD_DOWN.getValue());
        tdsParam.setTransactionAccount("");
        tdsParam.setTransactionAccountType(param.getTransactionPayTypeEnum().getVal());
        tdsParam.setAmount(new BigDecimal(param.getRefundMoney()));
        tdsParam.setBizId(param.getAdId());
        tdsParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
        tdsParam.setThirdTransactionNum(param.getTradeNo() == null ? "" : param.getTradeNo());
        tdsParam.setBizNum(IdWorkerHelperImpl.generate(BizIdType.REFUND));
        tdsParam.setTransactionDesc(MerchantTransactionTypeEnum.AD_DOWN.getDescPrefix());
        tdsParam.setGmtExecute(param.getGmtExecute());
        transactionDetailService.save(tdsParam);

        JsonResult jsonResult = new JsonResult();
        ThirdPayRefundParam rparam = new ThirdPayRefundParam();
        rparam.setRefundId(tdsParam.getBizNum());
        rparam.setRefundMoney(param.getRefundMoney());
        rparam.setTradeNo(param.getTradeNo());
        if (TransactionPayTypeEnum.ALIPAY.getVal().equals(param.getTransactionPayTypeEnum().getVal())) {
            AliPayConfigParam aliPayConfigParam = new AliPayConfigParam();
            aliPayConfigParam.setAlipayRefundUrl(propertySrvConfig.getAlipayRefundUrl());
            aliPayConfigParam.setAlipayAppIdMember(propertySrvConfig.getAlipayAppIdBusiness());
            aliPayConfigParam.setAlipayPrivateKey(propertySrvConfig.getAlipayPrivateKey());
            aliPayConfigParam.setAlipayEdianMemberPublicKey(propertySrvConfig.getAlipayEdianBusinessPublicKey());
            aliPayConfigParam.setAlipaySignType(propertySrvConfig.getAlipaySignType());
            aliPayConfigParam.setAlipayInputCharset(propertySrvConfig.getAlipayInputCharset());
            try{
                AlipayBusinessHandle.refund(rparam, jsonResult, aliPayConfigParam);
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }
        } else if (TransactionPayTypeEnum.WX.getVal().equals(param.getTransactionPayTypeEnum().getVal())) {

            TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
            transactionDetailDOExample.createCriteria().andThirdTransactionNumEqualTo(param.getTradeNo()).andUserNumEqualTo(param.getUserNum());
            List<TransactionDetailDO> transactionDetailList = transactionDetailDOMapper.selectByExample(transactionDetailDOExample);
            if (transactionDetailList == null || transactionDetailList.isEmpty()) {
                logger.error("微信发广告({})退款根据第三方订单号({})和用户编号({})查询交易明细支付总金额时返回为空！", param.getAdId(), param.getTradeNo(), param.getUserNum());
                throw new RuntimeException("商家红包wx退款查询交易明细支付总金额时返回为空！");
            }
            TransactionDetailDO transactionDetailDO = transactionDetailList.get(0);
            rparam.setTotalMoney(transactionDetailDO.getAmount().toString());

            WxPayConfigParam wxPayConfigParam = new WxPayConfigParam();
            wxPayConfigParam.setWxpayAppId(propertySrvConfig.getWxpayAppId());
            wxPayConfigParam.setWxpayMchId(propertySrvConfig.getWxpayMchId());
            if(ClientTypeEnum.MOBLIE.getVal().equals(param.getClientType())){
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
                throw new RuntimeException(e.getMessage());
            }
        }

        if (!jsonResult.isSuccess()) {
            logger.error(jsonResult.getMessage());
            throw new RuntimeException(jsonResult.getMessage());
        }
        return ResultCode.SUCCESS;
    }
}
