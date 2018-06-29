package com.lawu.eshop.weixin.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.lawu.eshop.weixin.srv.WeixinSrvConfig;
import com.lawu.eshop.weixin.srv.converter.RedpackConverter;
import com.lawu.eshop.weixin.srv.service.PayService;
import com.lawu.weixinapi.dto.param.RedpackParam;

/**
 * @author Leach
 * @date 2018/2/5
 */
@Service
public class PayServiceImpl implements PayService {

    private static final String ERR_CODE_NOT_FOUND = "NOT_FOUND";

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private WeixinSrvConfig weixinSrvConfig;

    @Override
    public WxPaySendRedpackResult sendRedpack(RedpackParam redpackParam) throws WxPayException {
        if (!weixinSrvConfig.isEnableRedpack()) {
            return null;
        }
        WxPaySendRedpackRequest wxPaySendRedpackRequest = RedpackConverter.convert(redpackParam);
        WxPaySendRedpackResult wxPaySendRedpackResult = wxPayService.sendRedpack(wxPaySendRedpackRequest);
        return wxPaySendRedpackResult;
    }

    @Override
    public WxPayRedpackQueryResult queryRedpack(String mchBillNo) throws WxPayException {
        WxPayRedpackQueryResult wxPayRedpackQueryResult;
        try {
            wxPayRedpackQueryResult = wxPayService.queryRedpack(mchBillNo);
            return wxPayRedpackQueryResult;
        } catch (WxPayException e) {
            if(ERR_CODE_NOT_FOUND.equals(e.getErrCode())) {
                return null;
            }
            throw e;
        }
    }
}
