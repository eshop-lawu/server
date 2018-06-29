package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.framework.web.Result;

/**
 * 游戏配置缓存服务
 * 
 * @author jiangxinjun
 * @createDate 2018年3月13日
 * @updateDate 2018年3月13日
 */
@FeignClient(value = "cache-srv", path = "gameConfigCache/")
public interface GameConfigCacheService {

    @RequestMapping(value = "getGameMindConfig", method = RequestMethod.GET)
    Result<GameMindConfigDTO> getGameMindConfig();


    @RequestMapping(value = "getGamePuzzleConfig", method = RequestMethod.GET)
    Result<GamePuzzleConfigCacheDTO> getGamePuzzleConfig();

    @RequestMapping(value = "setGamePuzzleUserKeyValue", method = RequestMethod.GET)
    Result<Boolean> setGamePuzzleUserKeyValue(@RequestParam("key") String key, @RequestParam("val") String val);

    @RequestMapping(value = "getGamePuzzleUserKey", method = RequestMethod.GET)
    Result<String> getGamePuzzleUserKey(@RequestParam("key") String key);

    @RequestMapping(value = "delGamePuzzleUserKeyKey", method = RequestMethod.GET)
    Result<Long> delGamePuzzleUserKeyKey(@RequestParam("key") String key);

    @RequestMapping(value = "setGamePuzzleStartTimeValue", method = RequestMethod.GET)
    Result setGamePuzzleStartTimeValue(@RequestParam("userNum") String userNum,@RequestParam("date") String date);


    @RequestMapping(value = "getGamePuzzleStartTimeValue", method = RequestMethod.GET)
    Result<String> getGamePuzzleStartTimeValue();

    @RequestMapping(value = "getRedisKeyGamePuzzleStartType", method = RequestMethod.GET)
    Result<String> getRedisKeyGamePuzzleStartType(@RequestParam("key") String key);

    @RequestMapping(value = "setRedisKeyGamePuzzleStartType", method = RequestMethod.GET)
    Result setRedisKeyGamePuzzleStartType(@RequestParam("key") String key, @RequestParam("date") String date);

    /**
     * 获取前缀开头的
     *
     * @return
     */
    @RequestMapping(value = "getLikeRedisKeyGamePuzzleStartType", method = RequestMethod.GET)
    Result<String> getLikeRedisKeyGamePuzzleStartType(@RequestParam("key") String key,@RequestParam("value") String value);

    /**
     * 删除拼图用前缀key
     */
    @RequestMapping(value = "delRedisKeyGamePuzzleStartType", method = RequestMethod.GET)
    Result delRedisKeyGamePuzzleStartType(@RequestParam("key") String key);
    
    
	/**
	 * 头脑配置
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "setGameMindConfig", method = RequestMethod.POST)
     Result setGameMindConfig(@ModelAttribute GameMindConfigParam param);
	


}
