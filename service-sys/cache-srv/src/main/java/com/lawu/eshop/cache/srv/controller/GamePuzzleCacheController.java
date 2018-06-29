package com.lawu.eshop.cache.srv.controller;

import com.lawu.eshop.cache.srv.service.GamePuzzleCacheService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
@RestController
@RequestMapping(value = "gamePuzzleCache/")
public class GamePuzzleCacheController extends BaseController {

    @Autowired
    protected GamePuzzleCacheService gamePuzzleCacheService;

    /**
     * 每次用户上线, 把当前用户放入缓存中, 并且返回, 目前已经在组内的用户
     *
     * @param userNum
     * @param attendNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月16日
     */
    @RequestMapping(value = "readyStartGame", method = RequestMethod.PUT)
    public Result<List<String>> readyStartGame(@RequestParam("userNum") String userNum, @RequestParam("attendNum") String attendNum) {
        return successCreated(gamePuzzleCacheService.readyStartGame(userNum, attendNum));
    }
    
    @RequestMapping(value = "incrementCount", method = RequestMethod.GET)
    public Result<Long> incrementCount(@RequestParam("attendNum") String attendNum){
    	return successGet(gamePuzzleCacheService.incrementCount(attendNum));
    }
    
}
