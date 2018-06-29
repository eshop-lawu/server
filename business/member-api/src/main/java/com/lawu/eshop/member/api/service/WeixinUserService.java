package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.WeixinUserBindDTO;
import com.lawu.eshop.user.dto.WeixinUserInfoDTO;
import com.lawu.eshop.user.param.WeixinUserBindParam;
import com.lawu.eshop.user.param.WeixinUserParam;
import com.lawu.framework.web.Result;

@FeignClient(value = "user-srv")
public interface WeixinUserService {
	
	 /**
     * 获取用户参与助力红包相关信息
     * @param userNum
     * @return
     */
	@RequestMapping(value = "weixinUser/getWeixinUserInfo", method = RequestMethod.GET)
    Result<WeixinUserInfoDTO> getWeixinUserInfo(@RequestParam("userNum") String userNum);
	
	/**
	 * 通过opendId判断用户是否绑定过公众号
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "weixinUser/getWeixinUserIsBind", method = RequestMethod.GET)
    Result<WeixinUserBindDTO> getWeixinUserIsBind(@RequestParam("openId") String openId);
	
	/**
	 * 微信用户绑定
	 * @param bindParam
	 * @return
	 */
	@RequestMapping(value = "weixinUser/weixinUserBind", method = RequestMethod.POST)
    Result weixinUserBind(@RequestBody WeixinUserBindParam bindParam);

	/**
	 * 保存微信用户信息
	 *
	 * @param param
	 * @author meishuquan
	 */
	@RequestMapping(value = "weixinUser/saveWeixinUser", method = RequestMethod.POST)
	Result saveWeixinUser(@RequestBody WeixinUserParam param);
	
	
	/**
	 * 通过账号密码判断是否绑定
	 * @param account
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "weixinUser/getwxUserIsBindByAccount", method = RequestMethod.GET)
	Result<Boolean> getwxUserIsBindByAccount(@RequestParam("account") String account);

}
