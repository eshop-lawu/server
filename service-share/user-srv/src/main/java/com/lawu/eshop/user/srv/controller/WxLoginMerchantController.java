package com.lawu.eshop.user.srv.controller;

import com.lawu.eshop.user.param.WxLoginMerchantParam;
import com.lawu.eshop.user.srv.service.WxLoginMerchantService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "wxLoginMerchant/")
public class WxLoginMerchantController extends BaseController {

    @Autowired
    private WxLoginMerchantService wxLoginMerchantService;

    private static Logger logger = LoggerFactory.getLogger(WxLoginMerchantController.class);

    /**
     * 微信用户信息保存
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "wxLoginMerchantSave", method = RequestMethod.POST)
    public Result wxLoginMerchantSave(@RequestBody WxLoginMerchantParam param) {
        wxLoginMerchantService.wxLoginMerchantSave(param);
        return successCreated();
    }


}
