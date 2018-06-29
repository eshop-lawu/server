package com.lawu.eshop.user.srv.service;

import com.lawu.eshop.user.param.QqLoginMemberParam;
import com.lawu.eshop.user.srv.bo.QqLoginMemberBO;

public interface QqLoginMemberService {
	
	/**
	 * 保存QQ登录信息
	 * @param param
	 */
	void QqLoginMemberSave(QqLoginMemberParam param);
	
	
	/**
	 * 通过openId判断用户是否绑定QQ登录
	 * @param openId
	 * @return
	 */
	QqLoginMemberBO qqLogin(String openId);

	/**
	 * QQ账户绑定
	 * @param openId
	 * @param account
	 */
	void qqMemberBind(String openId, String account,String imgUrl);
	
	
	/**
	 * 获取微信图像
	 * @param openId
	 * @return
	 */
	String getHeadImg(String openId);
	

}
