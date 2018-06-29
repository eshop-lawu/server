package com.lawu.eshop.member.api.service;

import com.lawu.eshop.game.dto.GameAttendSaveReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicDTO;
import com.lawu.eshop.game.dto.GamePuzzleResultDTO;
import com.lawu.eshop.game.param.GameAttendSaveParam;
import com.lawu.eshop.game.param.GamePuzzleGetPicSaveAttendParam;
import com.lawu.eshop.game.param.GamePuzzleRewardPointAndStarParam;
import com.lawu.eshop.game.param.ValidatePointStatus;
import com.lawu.framework.web.Result;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 拼图配置
 * 
 * @author lihj <br>
 * @date 2018/3/10
 */
@FeignClient(value = "game-srv", path = "gamePuzzleConfig/")
public interface GamePuzzleConfigService {

	@RequestMapping(value = "getPicByHardLevel", method = RequestMethod.POST)
	Result<List<GamePuzzleGetPicDTO>> getPicByHardLevel(@RequestBody GamePuzzleGetPicSaveAttendParam param);

	@RequestMapping(value = "savePuzzleGameAttendInfo", method = RequestMethod.POST)
	Result<GameAttendSaveReturnDTO> savePuzzleGameAttendInfo(@RequestBody GameAttendSaveParam saveParam);

	@RequestMapping(value = "validatePointDeductionStatus", method = RequestMethod.POST)
	Result<Boolean> validatePointDeductionStatus(@RequestBody ValidatePointStatus param);

	@RequestMapping(value = "rewardPointAndStar", method = RequestMethod.POST)
	Result<GamePuzzleResultDTO> rewardPointAndStar(@RequestBody GamePuzzleRewardPointAndStarParam gameRewardParam);
	
	@RequestMapping(value = "saveRandomPuzzleGameAttendInfo", method = RequestMethod.POST)
	Result<GameAttendSaveReturnDTO> saveRandomPuzzleGameAttendInfo(@RequestBody GameAttendSaveParam saveParam);

}
