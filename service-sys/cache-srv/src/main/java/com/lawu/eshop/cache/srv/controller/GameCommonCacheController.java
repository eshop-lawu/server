package com.lawu.eshop.cache.srv.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.dto.GamePuzzleCacheDetail;
import com.lawu.eshop.cache.dto.GamePuzzleCallBackCacheDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.ExitMatchQueueParam;
import com.lawu.eshop.cache.param.JoinGameCacheParam;
import com.lawu.eshop.cache.param.MatchingRobotParam;
import com.lawu.eshop.cache.srv.service.ConcurrencyControlService;
import com.lawu.eshop.cache.srv.service.GameCommonCacheService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 游戏公用缓存
 * 
 * @author lihj
 * @Date 2018年3月14日
 */
@RestController
@RequestMapping(value = "gameCommonCache/")
public class GameCommonCacheController extends BaseController {

	@Autowired
	private GameCommonCacheService gameCommonCacheService;

	@Autowired
	private ConcurrencyControlService concurrencyControlService;

	/**
	 * 开始加入缓存
	 */
	@RequestMapping(value = "joinCache", method = RequestMethod.POST)
	public Result joinCache(@RequestBody JoinGameCacheParam param) {
		return successCreated(gameCommonCacheService.joinCache(param));
	}

	/**
	 * 检查缓存是否匹配成功并返回匹配成功用户编号返回参与编号
	 */
	@RequestMapping(value = "checkCacheMatchEatchother", method = RequestMethod.POST)
	public Result<GameMatchResultDTO> checkCacheMatchEatchother(@RequestBody CheckCacheMatchParam param) {
		return successCreated(gameCommonCacheService.checkCacheMatchEatchother(param));
	}

	/**
	 * 定时任务定时相互匹配更新
	 */
	@RequestMapping(value = "batchUpdateMatchEatchother", method = RequestMethod.POST)
	public void batchUpdateMatchEatchother(@RequestParam String key, @RequestParam String newKey) {
		gameCommonCacheService.batchUpdateMatchEatchother(key, newKey);
	}

	/**
	 * 退出匹配队列
	 */
	@RequestMapping(value = "exitMatchQueue", method = RequestMethod.POST)
	public void exitMatchQueue(@RequestBody ExitMatchQueueParam param) {
		gameCommonCacheService.exitMatchQueue(param);
	}

	@RequestMapping(value = "incrementAndGet", method = RequestMethod.GET)
	public Result<Long> incrementAndGet(@RequestParam("key") String key) {
		return successGet(concurrencyControlService.incrementAndGet(key, 60, TimeUnit.SECONDS));
	}
	@RequestMapping(value = "setCallBackCache", method = RequestMethod.POST)
	public Result<GamePuzzleCallBackCacheDTO> setCallBackCache(@RequestBody GamePuzzleCacheDetail param){
		return successCreated(gameCommonCacheService.setCallBackCache(param));
	}
	
	/**
	 * 匹配机器人<p> 
	 * 把用户个人的信息和机器人信息放入缓存中
	 * 
	 * @param param
	 * @return
	 * @author jiangxinjun
	 * @createDate 2018年5月11日
	 * @updateDate 2018年5月11日
	 */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "matchingRobot", method = RequestMethod.POST)
    public Result matchingRobot(@RequestBody MatchingRobotParam param) {
        gameCommonCacheService.matchingRobot(param);
        return successCreated();
    }

}
