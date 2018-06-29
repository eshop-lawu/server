package com.lawu.eshop.member.api.service;

import com.lawu.eshop.activity.dto.HelpRedpacketActivityDTO;
import com.lawu.eshop.activity.param.DoHelpDataParam;
import com.lawu.eshop.activity.param.DoHelpParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "activity-srv")
public interface HelpRedpacketInviteService {

    /**
     * 助力页面登陆确认帮忙
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "helpRedpacketInvite/doLoginHelp", method = RequestMethod.POST)
    Result doLoginHelp(@RequestBody DoHelpDataParam param);

    @RequestMapping(value = "helpRedpacketActivity/detail/{id}", method = RequestMethod.GET)
    Result<HelpRedpacketActivityDTO> getHelpRedpacketActivitydetail(@PathVariable("id") Integer id);

    @RequestMapping(value = "helpRedpacketActivity/detail", method = RequestMethod.GET)
    Result<HelpRedpacketActivityDTO> getHelpRedpacketActivitydetail();
}
