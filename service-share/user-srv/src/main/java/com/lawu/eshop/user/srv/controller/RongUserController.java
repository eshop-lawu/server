package com.lawu.eshop.user.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.user.constants.RongOnlineStatusEnum;
import com.lawu.eshop.user.dto.RongYunHistoryMessageDTO;
import com.lawu.eshop.user.dto.RongYunOnlineDTO;
import com.lawu.eshop.user.dto.RongYunRefreshDTO;
import com.lawu.eshop.user.dto.RongYunTokenDTO;
import com.lawu.eshop.user.srv.rong.models.CheckOnlineResult;
import com.lawu.eshop.user.srv.rong.models.CodeSuccessResult;
import com.lawu.eshop.user.srv.rong.models.HistoryMessageResult;
import com.lawu.eshop.user.srv.rong.models.TokenResult;
import com.lawu.eshop.user.srv.rong.service.RongUserService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/4/14.
 */
@RestController
@RequestMapping(value = "rongUser")
public class RongUserController extends BaseController {
    @Autowired
    private RongUserService rongUserService;
    /**
     * 获取融云token
     *
     * @param userId      用户 Id
     * @param name        用户名称
     * @param portraitUri 用户头像 URI
     *    获取需要根据对应的service去获取
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getRongToken", method = RequestMethod.GET)
    public Result<RongYunTokenDTO> getRongToken(@RequestParam("userId") String userId, @RequestParam("name") String name, @RequestParam("portraitUri") String portraitUri){
        TokenResult token = rongUserService.getRongToken(userId, name, portraitUri);
        RongYunTokenDTO rongYunTokenDTO = new RongYunTokenDTO();
        if(token != null){
            rongYunTokenDTO.setCode(token.getCode());
            rongYunTokenDTO.setRyToken(token.getToken());
            rongYunTokenDTO.setErrorMessage(token.getErrorMessage());
            rongYunTokenDTO.setUserId(token.getUserId());
        }
        return successGet(rongYunTokenDTO);
    }

    /**
     * 获取融云用户在线状态
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "checkOnline/{userId}", method = RequestMethod.GET)
    public Result<RongYunOnlineDTO> checkOnline(@PathVariable("userId") String userId){
        CheckOnlineResult checkOnlineResult = rongUserService.checkOnline(userId);
        RongYunOnlineDTO onlineDTO = new RongYunOnlineDTO();
        onlineDTO.setCode(checkOnlineResult.getCode());
        onlineDTO.setStatus(RongOnlineStatusEnum.getEnum(checkOnlineResult.getStatus()));
        onlineDTO.setErrorMessage(checkOnlineResult.getErrorMessage());
        return successGet(onlineDTO);
    }

    @RequestMapping(value = "refreshUserInfo", method = RequestMethod.GET)
    public Result<RongYunRefreshDTO> refreshUserInfo(@RequestParam("userId") String userId, @RequestParam("name") String name, @RequestParam("portraitUri") String portraitUri){
        CodeSuccessResult codeSuccessResult = rongUserService.refreshUserInfo(userId,name,portraitUri);
        RongYunRefreshDTO refreshDTO = new RongYunRefreshDTO();
        refreshDTO.setCode(codeSuccessResult.getCode());
        refreshDTO.setErrorMessage(codeSuccessResult.getErrorMessage());
        return successGet(refreshDTO);
    }

    /**
     * 获取消息历史记录
     * @param date
     * @return
     */
    @RequestMapping(value = "getHistoryMessage", method = RequestMethod.POST)
    public Result<RongYunHistoryMessageDTO> getHistoryMessage(String date){
        HistoryMessageResult messageResult = rongUserService.getHistory(date);
        RongYunHistoryMessageDTO historyMessageDTO = new RongYunHistoryMessageDTO();
        historyMessageDTO.setCode(messageResult.getCode());
        historyMessageDTO.setUrl(messageResult.getUrl());
        historyMessageDTO.setDate(messageResult.getDate());
        historyMessageDTO.setErrorMessage(messageResult.getErrorMessage());
        return successGet(historyMessageDTO);
    }

}
