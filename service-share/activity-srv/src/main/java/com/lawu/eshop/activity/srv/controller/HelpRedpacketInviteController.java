package com.lawu.eshop.activity.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.HelpRedpacketInviteAbnormalDTO;
import com.lawu.eshop.activity.param.AbnormalInviteParam;
import com.lawu.eshop.activity.param.DoHelpDataParam;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketInviteService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 红包助力
 *
 * @author yangqh
 * @date 2017/12/28
 */
@RestController
@RequestMapping(value = "helpRedpacketInvite/")
public class HelpRedpacketInviteController extends BaseController {

    @Autowired
    private HelpRedpacketInviteService helpRedpacketInviteService;

    @RequestMapping(value = "doLoginHelp", method = RequestMethod.POST)
    public Result doLoginHelp(@RequestBody DoHelpDataParam param){

        int retCode = helpRedpacketInviteService.doLoginHelp(param);

        return successCreated(retCode);
    }

    @RequestMapping(value = "queryAbnormalInviteRecord", method = RequestMethod.POST)
    public Result<HelpRedpacketInviteAbnormalDTO> queryAbnormalInviteRecord(@RequestBody AbnormalInviteParam param) {
        return successGet(helpRedpacketInviteService.queryAbnormalInviteRecord(param));
    }
   
}
