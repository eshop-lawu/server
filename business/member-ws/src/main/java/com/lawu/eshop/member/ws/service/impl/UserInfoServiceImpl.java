package com.lawu.eshop.member.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.common.dto.GameUserInfoDTO;
import com.lawu.eshop.member.ws.service.GameRobotAccountService;
import com.lawu.eshop.member.ws.service.MemberService;
import com.lawu.eshop.member.ws.service.UserInfoService;
import com.lawu.eshop.member.ws.util.ResultUtil;
import com.lawu.framework.web.Result;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private GameRobotAccountService gameRobotAccountService;

    @Override
    public Result<List<GameUserInfoDTO>> findUserInfo(List<String> userNums) {
        List<String> userNumList = new ArrayList<>(userNums);
        Result<List<GameUserInfoDTO>> findRobotUserInfoResult =  gameRobotAccountService.findRobotUserInfo(userNumList);
        if (!ResultUtil.isSuccess(findRobotUserInfoResult)) {
            return findRobotUserInfoResult;
        }
        List<GameUserInfoDTO> model = findRobotUserInfoResult.getModel();
        for (GameUserInfoDTO item : model) {
            userNumList.remove(item.getUserNum());
        }
        if (userNumList.isEmpty()) {
            return ResultUtil.getSuccess(model);
        }
        Result<List<GameUserInfoDTO>> findUserInfoResult = memberService.findUserInfoForGameMind(userNumList);
        if (!ResultUtil.isSuccess(findUserInfoResult)) {
            return findUserInfoResult;
        }
        model.addAll(findUserInfoResult.getModel());
        return ResultUtil.getSuccess(model);
    }
    
}
