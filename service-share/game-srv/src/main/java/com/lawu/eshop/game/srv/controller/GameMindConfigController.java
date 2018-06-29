package com.lawu.eshop.game.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.game.dto.GameMindConfigDTO;
import com.lawu.eshop.game.dto.GamePointAllotDTO;
import com.lawu.eshop.game.srv.bo.GameMindConfigBO;
import com.lawu.eshop.game.srv.bo.GamePointAllotBO;
import com.lawu.eshop.game.srv.converter.GameMindConfigConverter;
import com.lawu.eshop.game.srv.service.GameMindConfigService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 头脑PK游戏设置
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@RestController
@RequestMapping(value = "gameMindConfig/")
public class GameMindConfigController extends BaseController {
	
	
	@Autowired
	private GameMindConfigService gameMindConfigService;

	/**
	 * 保存头脑PK游戏设置
	 * 
	 * @param param
	 */
	@RequestMapping(value = "saveGameMindConfig", method = RequestMethod.POST)
	public Result saveGameMindConfig(@RequestBody GameMindConfigParam param) {
		gameMindConfigService.saveGameMindConfig(param);
		return successCreated();
	}
	
	/**
	 * 游戏禁用启用
	 * @param statusEnum
	 * @return
	 */
	@RequestMapping(value = "setEnable", method = RequestMethod.POST)
	public Result setEnable(@RequestParam GameConfigStatusEnum statusEnum) {
		gameMindConfigService.setEnable(statusEnum);
		return successCreated();
	}
	
	/**
	 * 修改游戏设置
	 * @param id
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "updateGameMindConfig/{id}", method = RequestMethod.POST)
	public Result updateGameMindConfig(@PathVariable Long id,@RequestBody GameMindConfigParam param) {
		gameMindConfigService.updateGameMindConfig(id,param);
		return successCreated();
	}
	
	/**
	 * 查询
	 * @return
	 */
	@RequestMapping(value = "getGameMindConfig", method = RequestMethod.GET)
	public Result<GameMindConfigDTO> getGameMindConfig() {
		GameMindConfigBO config = gameMindConfigService.getGameMindConfigById();
		GameMindConfigDTO dto = GameMindConfigConverter.converterDTO(config);
		List<GamePointAllotDTO> allots = new ArrayList<>();
		for (GamePointAllotBO gamePointAllotBO : config.getAllots()) {
			GamePointAllotDTO allot = new GamePointAllotDTO();
			allot.setAttendCount(gamePointAllotBO.getAttendCount());
			allot.setId(gamePointAllotBO.getId());
			allot.setRankPoint(JSON.parseArray(gamePointAllotBO.getRankPoint(),String.class));
			allot.setRankStar(JSON.parseArray(gamePointAllotBO.getRankStar(),String.class));
			allot.setWinNum(gamePointAllotBO.getWinNum());
			allots.add(allot);
		}
		dto.setAllots(allots);
		return successGet(dto);
	}
	
	
	@RequestMapping(value = "delAllot/{id}", method = RequestMethod.PUT)
	public Result delAllot(@PathVariable Long id) {
		gameMindConfigService.delAllot(id);
		return successCreated();
	}

}
