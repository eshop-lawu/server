package com.lawu.eshop.member.ws.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.common.dto.GameUserInfoDTO;
import com.lawu.framework.web.Result;

/**
 * 机器人游戏账号服务接口
 */
@FeignClient(value = "game-srv", path = "gameRobotAccount/")
public interface GameRobotAccountService {
    
    /**
     * 获取机器人游戏账户相关信息
     * @param userNums
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月22日
     * @updateDate 2018年5月22日
     */
    @RequestMapping(value = "findRobotUserInfo", method = RequestMethod.GET)
    Result<List<GameUserInfoDTO>> findRobotUserInfo(@RequestParam("userNums") List<String> userNums);
    
}
