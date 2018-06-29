package com.lawu.eshop.game.srv.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.dto.GamePuzzleCacheDetail;
import com.lawu.eshop.cache.dto.GamePuzzleCallBackCacheDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.ExitMatchQueueParam;
import com.lawu.eshop.cache.param.JoinGameCacheParam;
import com.lawu.eshop.cache.param.MatchingRobotParam;
import com.lawu.framework.web.Result;

/**
 * 游戏公用缓存
 * 
 * @author lihj
 * @Date 2018年3月14日
 */
@FeignClient(value = "cache-srv", path = "gameCommonCache")
public interface GameCommonCacheService {

	/**
	 * 开始加入缓存
	 */
	@SuppressWarnings("rawtypes")
    @RequestMapping(value = "joinCache", method = RequestMethod.POST)
	Result joinCache(@RequestBody JoinGameCacheParam param);
	
	/**
	 * 检查缓存是否匹配成功并返回匹配成功用户编号返回参与编号
	 */
	@RequestMapping(value = "checkCacheMatchEatchother", method = RequestMethod.POST)
	Result<GameMatchResultDTO> checkCacheMatchEatchother(@RequestBody CheckCacheMatchParam param);
	
	/**
	 * 定时任务定时相互匹配更新
	 */
	/**
	 * 定时任务定时相互匹配更新
	 */
	@RequestMapping(value = "batchUpdateMatchEatchother", method = RequestMethod.POST)
	void batchUpdateMatchEatchother(@RequestParam("key") String key, @RequestParam("newKey") String newKey);
	
	@RequestMapping(value = "incrementAndGet", method = RequestMethod.GET)
	Result<Long> incrementAndGet(@RequestParam("key") String key);

	@RequestMapping(value = "setCallBackCache", method = RequestMethod.POST)
	Result<GamePuzzleCallBackCacheDTO> setCallBackCache(@RequestBody GamePuzzleCacheDetail param);
	
    /**
     * 匹配机器人
     * <p>
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
    Result matchingRobot(@RequestBody MatchingRobotParam param);
    
    /**
     * 退出匹配队列
     * @param param
     * @author jiangxinjun
     * @createDate 2018年5月13日
     * @updateDate 2018年5月13日
     */
    @RequestMapping(value = "exitMatchQueue", method = RequestMethod.POST)
    void exitMatchQueue(@RequestBody ExitMatchQueueParam param);

}
