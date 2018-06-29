package com.lawu.eshop.weixin.srv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.weixin.srv.converter.WeixinUserConverter;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.weixinapi.dto.WeixinUserDTO;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author Leach
 * @date 2017/12/29
 */
@RestController
@RequestMapping(value = "auth/")
public class AuthController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Qualifier("memberWxMpService")
    @Autowired
    private WxMpService memberWxMpService;

    @Qualifier("merchantWxMpService")
    @Autowired
    private WxMpService merchantWxMpService;

    @RequestMapping(value = "getAuthUserInfo", method = RequestMethod.GET)
    public Result<WeixinUserDTO> getAuthUserInfo(@RequestParam String code) {
        return oauth2getUserInfo(memberWxMpService, code);
    }

    @RequestMapping(value = "merchant/getAuthUserInfo", method = RequestMethod.GET)
    public Result<WeixinUserDTO> getMerchantAuthUserInfo(@RequestParam String code) {
        return oauth2getUserInfo(merchantWxMpService, code);
    }

    private Result<WeixinUserDTO> oauth2getUserInfo(WxMpService wxMpService, String code) {
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");

            return successGet(WeixinUserConverter.convert(wxMpUser));
        } catch (WxErrorException e) {
            logger.warn("Fail to get user info by code", e);
            return successGet(ResultCode.FAIL, e.getMessage());
        }
    }
}
