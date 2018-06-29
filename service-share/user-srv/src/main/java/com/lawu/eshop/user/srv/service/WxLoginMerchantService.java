package com.lawu.eshop.user.srv.service;

import com.lawu.eshop.user.param.WxLoginMerchantParam;

public interface WxLoginMerchantService {

	/**
	 * 保存微信用户信息
	 * @param param
	 */
	void wxLoginMerchantSave(WxLoginMerchantParam param);

}
