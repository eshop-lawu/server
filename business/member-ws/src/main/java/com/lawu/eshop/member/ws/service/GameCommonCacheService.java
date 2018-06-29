package com.lawu.eshop.member.ws.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.JoinGameCacheParam;
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
	@RequestMapping(value = "joinCache", method = RequestMethod.POST)
	void joinCache(@RequestBody JoinGameCacheParam param);
	
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
}
