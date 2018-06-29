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
import com.lawu.eshop.cache.param.GamePuzzleConfigParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.dto.GameAttendSaveReturnDTO;
import com.lawu.eshop.game.dto.GamePointAllotDTO;
import com.lawu.eshop.game.dto.GamePuzzleConfigDTO;
import com.lawu.eshop.game.dto.GamePuzzleDifficultyDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicDTO;
import com.lawu.eshop.game.dto.GamePuzzleResultDTO;
import com.lawu.eshop.game.dto.SecScoreDTO;
import com.lawu.eshop.game.param.GameAttendSaveParam;
import com.lawu.eshop.game.param.GamePuzzleGetPicSaveAttendParam;
import com.lawu.eshop.game.param.GamePuzzleRewardPointAndStarParam;
import com.lawu.eshop.game.srv.bo.GameAttendSaveReturnBO;
import com.lawu.eshop.game.srv.bo.GamePointAllotBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleConfigBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleDifficultyBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleGetPicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleResultBO;
import com.lawu.eshop.game.srv.converter.GamePuzzleConfigConverter;
import com.lawu.eshop.game.srv.service.GamePuzzleAttendRecordService;
import com.lawu.eshop.game.srv.service.GamePuzzleConfigService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 拼图游戏设置
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@RestController
@RequestMapping(value = "gamePuzzleConfig/")
public class GamePuzzleConfigController extends BaseController {
	
	
	@Autowired
	private GamePuzzleConfigService gamePuzzleConfigService;

	@Autowired
	private GamePuzzleAttendRecordService gamePuzzleAttendRecordService;
	
	/**
	 * 保存拼图游戏设置
	 * 
	 * @param param
	 */
	@RequestMapping(value = "saveGamePuzzleConfig", method = RequestMethod.POST)
	public Result saveGamePuzzleConfig(@RequestBody GamePuzzleConfigParam param) {
		gamePuzzleConfigService.saveGamePuzzleConfig(param);
		return successCreated();
	}

	/**
	 * 根据游戏难易程度出拼图图片
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "getPicByHardLevel",method = RequestMethod.POST)
	public Result<List<GamePuzzleGetPicDTO>> getPicByHardLevel(@RequestBody GamePuzzleGetPicSaveAttendParam param){
		List<GamePuzzleGetPicBO> gamePuzzleGetPicBO = gamePuzzleConfigService.getPicByHardLevel(param);
		if(null ==gamePuzzleGetPicBO){
			return successCreated(ResultCode.NOT_FOUND_DATA);
		}
		List<GamePuzzleGetPicDTO> dto = GamePuzzleConfigConverter.convertGamePuzzleGetPicBO(gamePuzzleGetPicBO);
		return successCreated(dto);
	}
	
	/**
	 * 游戏禁用启用
	 * @param statusEnum
	 * @return
	 */
	@RequestMapping(value = "setEnable", method = RequestMethod.POST)
	public Result setEnable(@RequestParam GameConfigStatusEnum statusEnum) {
		gamePuzzleConfigService.setEnable(statusEnum);
		return successCreated();
	}
	
	/**
	 * 修改游戏配置
	 * @param id
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "updateGamePuzzleConfig/{id}", method = RequestMethod.POST)
	public Result updateGamePuzzleConfig(@PathVariable Long id,@RequestBody GamePuzzleConfigParam param) {
		gamePuzzleConfigService.updateGamePuzzleConfig(id,param);
		return successCreated();
	}
	
	/**
	 * 拼图开始初始化记录并扣除积分以及星星
	 * @param param
	 * @return
	 */
/*	@RequestMapping(value = "savePuzzleGameAttendInfo", method = RequestMethod.POST)
	public Result<GameAttendSaveReturnDTO> savePuzzleGameAttendInfo(@RequestBody GameAttendSaveParam param){
		GameAttendSaveReturnBO attendBO = gamePuzzleConfigService.savePuzzleGameAttendInfo(param);
		GameAttendSaveReturnDTO dto =GamePuzzleConfigConverter.convertGameAttendSaveReturnDTO(attendBO);
		return successCreated(dto);
	}*/
	
	/**
	 * 随机
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "saveRandomPuzzleGameAttendInfo", method = RequestMethod.POST)
	public Result<GameAttendSaveReturnDTO> saveRandomPuzzleGameAttendInfo(@RequestBody GameAttendSaveParam param){
		GameAttendSaveReturnBO attendBO = gamePuzzleConfigService.saveRandomPuzzleGameAttendInfo(param);
		GameAttendSaveReturnDTO dto =GamePuzzleConfigConverter.convertGameAttendSaveReturnDTO(attendBO);
		return successCreated(dto);
	}

	/**
	 * 检查积分是否扣除成功
	 * @param param
	 * @return
	 */
	/*@RequestMapping(value = "validatePointDeductionStatus", method = RequestMethod.POST)
	public Result<Boolean> validatePointDeductionStatus(@RequestBody ValidatePointStatus param){
		boolean flag =gamePuzzleAttendRecordService.validatePointDeductionStatus(param);
		return successCreated(flag);
	}*/

	/**
	 * 挑战成功增加积分以及星星
	 * @param gameRewardParam
	 * @return
	 */
	@RequestMapping(value = "rewardPointAndStar", method = RequestMethod.POST)
	public Result<GamePuzzleResultDTO> rewardPointAndStar(@RequestBody GamePuzzleRewardPointAndStarParam gameRewardParam){
		GamePuzzleResultBO bo =gamePuzzleConfigService.rewardPointAndStar(gameRewardParam);
		GamePuzzleResultDTO dto =GamePuzzleConfigConverter.convertGamePuzzleResultDTO(bo);
		return successCreated(dto);
	}
	@RequestMapping(value = "getGamePuzzleConfig", method = RequestMethod.GET)
	public Result<GamePuzzleConfigDTO> getGamePuzzleConfig() {
		GamePuzzleConfigBO config = gamePuzzleConfigService.getGamePuzzleConfig();
		GamePuzzleConfigDTO dto = GamePuzzleConfigConverter.converterDTO(config);
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
		
		List<GamePuzzleDifficultyDTO> levers = new ArrayList<>();
		for (GamePuzzleDifficultyBO gamePuzzleDifficultyBO : config.getDifficultys()) {
			GamePuzzleDifficultyDTO  difficulty = new GamePuzzleDifficultyDTO();
			difficulty.setId(gamePuzzleDifficultyBO.getId());
			difficulty.setCoefficient(gamePuzzleDifficultyBO.getCoefficient());
			difficulty.setLevel(gamePuzzleDifficultyBO.getLevel());
			difficulty.setPoint(gamePuzzleDifficultyBO.getPoint());
			difficulty.setStar(gamePuzzleDifficultyBO.getStar());
			difficulty.setChallengeTime(gamePuzzleDifficultyBO.getChallengeTime());
			difficulty.setSecScores(JSON.parseArray(gamePuzzleDifficultyBO.getSecScore(),SecScoreDTO.class));
			difficulty.setRobotMinSecond(gamePuzzleDifficultyBO.getRobotMinSecond());
			difficulty.setRobotMaxSecond(gamePuzzleDifficultyBO.getRobotMaxSecond());
			levers.add(difficulty);
		}
		dto.setDifficultys(levers);
		return successGet(dto);
	}
}
