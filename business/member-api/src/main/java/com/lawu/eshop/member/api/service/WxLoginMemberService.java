package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.param.WxLoginMemberParam;
import com.lawu.framework.web.Result;

/**
 * 微信登录
 * 
 * @Description
 * @author zhangrc
 * @date 2018年1月16日
 */
@FeignClient(value = "user-srv")
public interface WxLoginMemberService {
	
	/**
	 * 授权成功根据openId进行登录
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "wxLoginMember/wxLogin", method = RequestMethod.GET)
	Result<LoginUserDTO> wxLogin(@RequestParam("openId") String openId);

	/**
	 * 微信账户绑定
	 * @param openId
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "wxLoginMember/wxMemberBind", method = RequestMethod.POST)
	Result wxMemberBind(@RequestParam("openId") String openId, @RequestParam("account") String account,@RequestParam("imgUrl") String imgUrl);
	

	/**
	 * 获取微信图像
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "wxLoginMember/getHeadImg", method = RequestMethod.GET)
	Result<String> getHeadImg(@RequestParam("openId") String openId);
	
	/**
	 * 保存微信用户信息
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "wxLoginMember/wxLoginMemberSave", method = RequestMethod.POST)
	Result  wxLoginMemberSave(@RequestBody WxLoginMemberParam param);
}
