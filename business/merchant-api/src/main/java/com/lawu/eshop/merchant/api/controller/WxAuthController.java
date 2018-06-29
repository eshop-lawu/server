package com.lawu.eshop.merchant.api.controller;

import com.lawu.eshop.merchant.api.MerchantApiConfig;
import com.lawu.eshop.merchant.api.service.AuthService;
import com.lawu.eshop.merchant.api.service.WxLoginMerchantService;
import com.lawu.eshop.user.dto.ThirdLoginAuthDTO;
import com.lawu.eshop.user.param.WxLoginMerchantParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.weixinapi.dto.WeixinUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "wxAuth", value = "微信授权接口")
@RestController
@RequestMapping(value = "wxAuth/")
public class WxAuthController extends BaseController {

    @Autowired
    private MerchantApiConfig merchantApiConfig;

    @Autowired
    private WxLoginMerchantService wxLoginMerchantService;

    @Autowired
    private AuthService authService;

    @Audit(date = "2018-06-08", reviewer = "孙林青")
    @ApiOperation(value = "获取appid", notes = "配置在服务端获取(杨清华)", httpMethod = "GET")
    @RequestMapping(value = "getAppId")
    public Result<ThirdLoginAuthDTO> getAppId() {
        ThirdLoginAuthDTO dto = new ThirdLoginAuthDTO();
        dto.setWxAppId(merchantApiConfig.getWxMpAppid());
        return successGet(dto);
    }

    @Audit(date = "2018-06-08", reviewer = "孙林青")
    @ApiOperation(value = "获取微信登录用户信息", notes = "获取微信用户信息 (杨清华)", httpMethod = "GET")
    @RequestMapping(value = "getWxLoginUser", method = RequestMethod.GET)
    public Result<WeixinUserDTO> getWxLoginUser(
            @RequestParam @ApiParam(name = "code", required = true, value = "code") String code) {

        Result<WeixinUserDTO> result = authService.getMerchantAuthUserInfo(code);
        if (!isSuccess(result)) {
            return successCreated(result.getRet());
        }
        WeixinUserDTO userDTO = result.getModel();

        WxLoginMerchantParam param = new WxLoginMerchantParam();
        param.setCity(userDTO.getCity());
        param.setCountry(userDTO.getCountry());
        param.setHeadimgurl(userDTO.getHeadimgurl());
        param.setGroupid(userDTO.getGroupid());
        param.setLanguage(userDTO.getLanguage());
        param.setNickname(userDTO.getNickname());
        param.setOpenid(userDTO.getOpenid());
        param.setProvince(userDTO.getProvince());
        param.setRemark(userDTO.getRemark());
        param.setSex(userDTO.getSex());
        param.setSubscribe(userDTO.getSubscribe());
        param.setSubscribeTime(userDTO.getSubscribeTime());
        param.setTagidList(userDTO.getTagidList());
        param.setUnionid(userDTO.getUnionid());
        wxLoginMerchantService.wxLoginMerchantSave(param);

        return successGet(result);
    }

}
