package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.param.QqLoginMemberParam;
import com.lawu.framework.web.Result;

/**
 * 微信登录
 * 
 * @Description
 * @author zhangrc
 * @date 2018年1月16日
 */
@FeignClient(value = "user-srv")
public interface QqLoginMemberService {
	
	/**
	 * 授权成功根据openId进行登录
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "qqLoginMember/qqLogin", method = RequestMethod.GET)
	Result<LoginUserDTO> qqLogin(@RequestParam("openId") String openId);

	/**
	 * 微信账户绑定
	 * @param openId
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "qqLoginMember/qqMemberBind", method = RequestMethod.POST)
	Result qqMemberBind(@RequestParam("openId") String openId, @RequestParam("account") String account, @RequestParam("imgUrl") String imgUrl);
	
	/**
	 * 微信用户信息保存
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "qqLoginMember/qqLoginMemberSave", method = RequestMethod.POST)
	Result  qqLoginMemberSave(@RequestBody QqLoginMemberParam param);
	
	/**
	 * 获取QQ图像
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "qqLoginMember/getHeadImg", method = RequestMethod.GET)
	Result<String> getHeadImg(@RequestParam("openId") String openId);

}
