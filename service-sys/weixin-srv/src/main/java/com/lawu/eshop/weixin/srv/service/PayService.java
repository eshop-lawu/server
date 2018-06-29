package com.lawu.eshop.weixin.srv.service;

import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.lawu.weixinapi.dto.param.RedpackParam;

/**
 * @author Leach
 * @date 2018/2/5
 */
public interface PayService {

    /**
     * 红包发送
     * @param redpackParam
     * @return
     * @throws WxPayException
     */
    WxPaySendRedpackResult sendRedpack(RedpackParam redpackParam) throws WxPayException;

    /**
     * 查询红包发送结果
     * @param mchBillNo
     * @return
     * @throws WxPayException
     */
    WxPayRedpackQueryResult queryRedpack(String mchBillNo) throws WxPayException;
}
