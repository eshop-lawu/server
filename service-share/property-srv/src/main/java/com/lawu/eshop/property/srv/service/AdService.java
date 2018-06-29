package com.lawu.eshop.property.srv.service;

import com.lawu.eshop.property.param.MerchantAdRefundDataParam;
import com.lawu.eshop.property.param.NotifyCallBackParam;

/**
 *
 */
public interface AdService {

    /**
     * 商家发E咻&红包时回调
     *
     * @param param
     * @return
     */
    int doHandleMerchantAdNotify(NotifyCallBackParam param);

    /**
     * E咻 & 红包 退款
     * @param param
     * @return
     */
    int doRefund(MerchantAdRefundDataParam param) throws Exception;
}
