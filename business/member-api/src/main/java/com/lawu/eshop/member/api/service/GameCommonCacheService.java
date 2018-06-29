package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.ExitMatchQueueParam;
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
	Result joinCache(@RequestBody JoinGameCacheParam param);
	
	/**
	 * 检查缓存是否匹配成功并返回匹配成功用户编号返回参与编号
	 */
	@RequestMapping(value = "checkCacheMatchEatchother", method = RequestMethod.POST)
	Result<GameMatchResultDTO> checkCacheMatchEatchother(@RequestBody CheckCacheMatchParam param);
	
	/**
	 * 退出等待序列
	 * @param param
	 */
	@RequestMapping(value = "exitMatchQueue", method = RequestMethod.POST)
	void exitMatchQueue(@RequestBody ExitMatchQueueParam param);
}
