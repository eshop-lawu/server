package com.lawu.eshop.cache.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.cache.param.GamePuzzleConfigParam;
import com.lawu.eshop.cache.srv.service.GameConfigCacheService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 游戏设置缓存
 *
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@RestController
@RequestMapping(value = "gameConfigCache/")
public class GameConfigCacheController extends BaseController{

	@Autowired
	private GameConfigCacheService gameConfigCacheService;

	/**
	 * 保存头脑PK设置
	 *
	 * @param param
	 */
	@RequestMapping(value = "setGameMindConfig", method = RequestMethod.POST)
    public Result setGameMindConfig(@RequestBody GameMindConfigParam param) {
		gameConfigCacheService.setGameMindConfig(param);
		return successCreated();
    }

	/**
	 * 获取头脑PK设置
	 * @return
	 */
    @RequestMapping(value = "getGameMindConfig", method = RequestMethod.GET)
    public Result<GameMindConfigDTO> getGameMindConfig() {
        return successGet(gameConfigCacheService.getGameMindConfig());
    }

    /**
	 * 保存拼图设置
	 *
	 * @param param
	 */
    @RequestMapping(value = "setGamePuzzleConfig", method = RequestMethod.POST)
    public Result setGamePuzzleConfig(@RequestBody GamePuzzleConfigParam param) {
		gameConfigCacheService.setGamePuzzleConfig(param);
		return successCreated();
    }

    /**
	 * 获取拼图设置
	 * @return
	 */
    @RequestMapping(value = "getGamePuzzleConfig", method = RequestMethod.GET)
    public Result<GamePuzzleConfigCacheDTO> getGamePuzzleConfig() {
        return successGet(gameConfigCacheService.getGamePuzzleConfig());
    }

	/**
	 * 设置用户参与拼图获取到的信息
	 *
	 * @Date 2018年3月10日<br>
	 * @param key
	 */
	@RequestMapping(value = "setGamePuzzleUserKeyValue",method = RequestMethod.GET)
	public Result<Boolean> setGamePuzzleUserKeyValue(@RequestParam("key") String key,@RequestParam("val") String val){
		Boolean flag =gameConfigCacheService.setGamePuzzleUserKeyValue(key,val);
		if(null ==flag){
			return successGet(false);
		}
		return successGet(flag);
	}
	/**
	 * 获取设置用户参与拼图获取到的信息
	 *
	 * @Date 2018年3月10日<br>
	 * @param key
	 */
	@RequestMapping(value = "getGamePuzzleUserKey",method = RequestMethod.GET)
	public Result<String> getGamePuzzleUserKey(@RequestParam("key") String key){
		return successGet(gameConfigCacheService.getGamePuzzleUserKey(key));
	}

	/**
	 * 解除锁用户参与拼图获取到的信息
	 *
	 * @Date 2018年3月10日<br>
	 * @param key
	 */
	@RequestMapping(value = "delGamePuzzleUserKeyKey",method = RequestMethod.GET)
	public Result<Long> delGamePuzzleUserKeyKey(@RequestParam("key") String key){
		Long lo =gameConfigCacheService.delGamePuzzleUserKeyKey(key);
		if(null == lo){
			return successGet(0);
		}
		return successGet(lo);
	}

	/**
	 * 设置用户拼图开始时间
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "setGamePuzzleStartTimeValue", method = RequestMethod.GET)
	public Result setGamePuzzleStartTimeValue(@RequestParam("userNum") String userNum,@RequestParam("date") String time){
		gameConfigCacheService.setGamePuzzleStartTimeValue(userNum,time);
		return successGet();
	}


	@RequestMapping(value = "getGamePuzzleStartTimeValue", method = RequestMethod.GET)
	public Result<String> getGamePuzzleStartTimeValue(@RequestParam("userNum") String userNum){
		return successGet(gameConfigCacheService.getGamePuzzleStartTimeValue(userNum));
	}

	@RequestMapping(value = "setRedisKeyGamePuzzleStartType",method = RequestMethod.GET)
	public Result setRedisKeyGamePuzzleStartType(@RequestParam("key") String key,@RequestParam("date") String date){
		gameConfigCacheService.setRedisKeyGamePuzzleStartType(key,date);
		return successGet();
	}

	@RequestMapping(value = "getRedisKeyGamePuzzleStartType",method = RequestMethod.GET)
	public Result<String> getRedisKeyGamePuzzleStartType(@RequestParam("key") String key){
		return successGet(gameConfigCacheService.getRedisKeyGamePuzzleStartType(key));
	}

	@RequestMapping(value = "getLikeRedisKeyGamePuzzleStartType",method = RequestMethod.GET)
	public Result<List<String>> getLikeRedisKeyGamePuzzleStartType(@RequestParam("key") String key){
		return successGet(gameConfigCacheService.getLikeRedisKeyGamePuzzleStartType(key));
	}

	@RequestMapping(value = "delRedisKeyGamePuzzleStartType", method = RequestMethod.GET)
	public Result delRedisKeyGamePuzzleStartType(@RequestParam("key") String key){
		gameConfigCacheService.delRedisKeyGamePuzzleStartType(key);
		return successGet();
	}
	@RequestMapping(value = "removeStartCacheData", method = RequestMethod.POST)
	public Result removeStartCacheData(@RequestParam("attendNum")String attendNum, @RequestParam("userNum")String userNum){
		return gameConfigCacheService.removeStartCacheData(attendNum,userNum);
	}

	@RequestMapping(value = "removeMyPuzzleCacheData", method = RequestMethod.GET)
	public Result removeMyPuzzleCacheData(@RequestParam("userNum")String userNum){
		return gameConfigCacheService.removeMyPuzzleCacheData(userNum);
	}



}
