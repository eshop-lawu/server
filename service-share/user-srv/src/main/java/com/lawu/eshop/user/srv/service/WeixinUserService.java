package com.lawu.eshop.user.srv.service;

import com.lawu.eshop.user.param.WeixinUserBindParam;
import com.lawu.eshop.user.param.WeixinUserParam;
import com.lawu.eshop.user.srv.bo.WeixinUserBindBO;
import com.lawu.eshop.user.srv.bo.WeixinUserInfoBO;

/**
 * 微信用户接口
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
public interface WeixinUserService {
	
	/**
	 * 获取微信用户相关信息
	 * @param userNum
	 * @return
	 */
	WeixinUserInfoBO getWeixinUserInfo(String userNum);
	
	
	/**
	 * 判断用户是否已经绑定过公众号
	 * @param openId
	 * @return
	 */
	WeixinUserBindBO getWeixinUserIsBind(String openId);
	
	/**
	 * 微信用户绑定
	 * @param bindParam
	 */
	Boolean weixinUserBind(WeixinUserBindParam bindParam);

	/**
	 * 保存微信用户信息
	 *
	 * @param param
	 * @author meishuquan
	 */
	void saveWeixinUser(WeixinUserParam param);
	
	/**
	 * 判断微信用户是否关注公众号
	 * @param openId
	 * @return
	 */
	Boolean weixinUserIsAttend(String openId);
	
	
	/**
	 * 通过账号密码判断用户是否已经绑定过公众号
	 * @param account
	 * @return
	 */
	Boolean getwxUserIsBindByAccount(String account);
	

}
