package com.lawu.eshop.game.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.dto.GameUserInfoDTO;
import com.lawu.eshop.game.srv.bo.GameUserInfoBO;
import com.lawu.eshop.game.srv.converter.GameRobotAccountConverter;
import com.lawu.eshop.game.srv.service.GameRobotAccountService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 机器人游戏账户控制器
 * 
 * @author jiangxinjun
 * @createDate 2018年5月22日
 * @updateDate 2018年5月22日
 */
@RestController
@RequestMapping(value = "gameRobotAccount/")
public class GameRobotAccountController extends BaseController {

    @Autowired
    private GameRobotAccountService gameRobotAccountService;

    /**
     * 获取机器人游戏账户相关信息
     * @param userNums
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月22日
     * @updateDate 2018年5月22日
     */
    @RequestMapping(value = "findRobotUserInfo", method = RequestMethod.GET)
    public Result<List<GameUserInfoDTO>> findRobotUserInfo(@RequestParam("userNums") List<String> userNums) {
        List<GameUserInfoBO> userInfos = gameRobotAccountService.findRobotUserInfo(userNums);
        return successCreated(GameRobotAccountConverter.convert(userInfos));
    }

}
