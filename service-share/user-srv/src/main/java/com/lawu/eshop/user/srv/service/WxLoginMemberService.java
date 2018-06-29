package com.lawu.eshop.user.srv.service;

import com.lawu.eshop.user.param.WxLoginMemberParam;
import com.lawu.eshop.user.srv.bo.WxLoginMemberBO;

/**
 * 微信第三方登录接口
 * 
 * @Description
 * @author zhangrc
 * @date 2018年1月16日
 */
public interface WxLoginMemberService {
	
	
	/**
	 * 通过openId判断用户是否绑定微信登录
	 * @param openId
	 * @return
	 */
	WxLoginMemberBO wxLogin(String openId);

	/**
	 * 微信账户绑定
	 * @param openId
	 * @param account
	 */
	void wxMemberBind(String openId, String account,String imgUrl);

	
	/**
	 * 获取微信图像
	 * @param openId
	 * @return
	 */
	String getHeadImg(String openId);
	
	/**
	 * 保存微信用户信息
	 * @param param
	 */
	void wxLoginMemberSave(WxLoginMemberParam param);

}
