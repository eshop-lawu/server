package com.lawu.eshop.user.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.dto.WeixinUserBindDTO;
import com.lawu.eshop.user.dto.WeixinUserInfoDTO;
import com.lawu.eshop.user.param.WeixinUserBindParam;
import com.lawu.eshop.user.param.WeixinUserParam;
import com.lawu.eshop.user.srv.bo.WeixinUserBindBO;
import com.lawu.eshop.user.srv.bo.WeixinUserInfoBO;
import com.lawu.eshop.user.srv.converter.WeixinUserConverter;
import com.lawu.eshop.user.srv.service.WeixinUserService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 微信用户接口类
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
@RestController
@RequestMapping(value = "weixinUser/")
public class WeixinUserController extends BaseController{
	
	@Autowired
	private WeixinUserService weixinUserService;
	
	/**
     * 获取用户报名助力红包信息
     * @param userNum
     * @return
     */
	@RequestMapping(value = "getWeixinUserInfo", method = RequestMethod.GET)
    public Result<WeixinUserInfoDTO> getWeixinUserInfo(@RequestParam String userNum){
    	WeixinUserInfoBO weixinUserInfoBO = weixinUserService.getWeixinUserInfo(userNum);
    	if(weixinUserInfoBO == null){
    		return successCreated(ResultCode.WEIXIN_OPENDID_NOT_BIND);
    	}
    	WeixinUserInfoDTO weixinUserInfoDTO = WeixinUserConverter.WeixinUserConverterDTO(weixinUserInfoBO);
    	
    	return successGet(weixinUserInfoDTO);
    	
    }

	/**
     * 获取用户是否绑定公众号
     * @param openId
     * @return
     */
	@RequestMapping(value = "getWeixinUserIsBind", method = RequestMethod.GET)
    public Result<WeixinUserBindDTO> getWeixinUserIsBind(@RequestParam String openId){
		WeixinUserBindBO weixinUserBindBO = weixinUserService.getWeixinUserIsBind(openId);
    	
		WeixinUserBindDTO weixinUserBindDTO = new WeixinUserBindDTO();
		weixinUserBindDTO.setIsBind(weixinUserBindBO.getIsBind());
    	
    	return successGet(weixinUserBindDTO);
    	
    }
	
	/**
	 * 微信用户绑定
	 * @param bindParam
	 * @return
	 */
	@RequestMapping(value = "weixinUserBind", method = RequestMethod.POST)
    public Result weixinUserBind(@RequestBody WeixinUserBindParam bindParam){
		Boolean isAttend = weixinUserService.weixinUserIsAttend(bindParam.getOpenId());
		if(!isAttend){
			return successCreated(ResultCode.WEI_XIN_IS_NOT_ATTEND);
		}
		Boolean flag = weixinUserService.weixinUserBind(bindParam);
    	if(flag){
    		return successCreated(ResultCode.MEMBER_WRONG_PWD);
    	}
    	return successCreated();
    	
    }

	/**
	 * 保存微信用户信息
	 *
	 * @param param
	 * @author meishuquan
	 */
	@RequestMapping(value = "saveWeixinUser", method = RequestMethod.POST)
	public Result saveWeixinUser(@RequestBody WeixinUserParam param) {
		weixinUserService.saveWeixinUser(param);
		return successCreated();
	}
	
	
	/**
	 * 通过账号密码判断是否绑定
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "getwxUserIsBindByAccount", method = RequestMethod.GET)
	public Result<Boolean> getwxUserIsBindByAccount(@RequestParam String account){
		Boolean isBind  = weixinUserService.getwxUserIsBindByAccount(account);
		return successGet(isBind);
	}

}
