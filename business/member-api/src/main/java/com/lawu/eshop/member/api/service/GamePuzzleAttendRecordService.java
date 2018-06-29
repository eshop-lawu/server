package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.constants.CacheGameTypeEnum;
import com.lawu.eshop.game.dto.CheckPointStatusDTO;
import com.lawu.eshop.game.dto.GameAttendSaveReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleRankReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleResultDTO;
import com.lawu.eshop.game.dto.PuzzleCheckIsOverDTO;
import com.lawu.eshop.game.param.GamePuzzleChallengeParam;
import com.lawu.eshop.game.param.GamePuzzleCheckDeductionPointParam;
import com.lawu.eshop.game.param.GamePuzzleFriendPlaySaveParam;
import com.lawu.eshop.game.param.GamePuzzleLoadRankingParam;
import com.lawu.eshop.game.param.GamePuzzleStandaloneParam;
import com.lawu.eshop.game.param.GamePuzzleValidSuccSrvParam;
import com.lawu.framework.web.Result;

@FeignClient(value = "game-srv", path = "gamePuzzleAttend/")
public interface GamePuzzleAttendRecordService {

	@RequestMapping(value = "loadingStandalone", method = RequestMethod.POST)
	Result<GameAttendSaveReturnDTO> loadingStandalone(@RequestBody GamePuzzleStandaloneParam gameStanda);

	@RequestMapping(value = "loadingChallenge", method = RequestMethod.POST)
	Result<GameAttendSaveReturnDTO> loadingChallenge(@RequestBody GamePuzzleChallengeParam param);

	@RequestMapping(value = "friendPuzzleGameInit", method = RequestMethod.POST)
	Result<GameAttendSaveReturnDTO> friendPuzzleGameInit(@RequestBody GamePuzzleFriendPlaySaveParam param);
	
	@RequestMapping(value = "checkPuzzlePicStatus", method = RequestMethod.POST)
	Result<GamePuzzleResultDTO> checkPuzzlePicStatus(@RequestBody GamePuzzleValidSuccSrvParam srvParam);
	
	@RequestMapping(value = "getPicByHardLevel", method = RequestMethod.GET)
	Result<GamePuzzleGetPicReturnDTO> getPicByHardLevel(@RequestParam("userNum") String userNum,@RequestParam("attendNum") String attendNum);
	/**
	 * 检查积分是否扣除成功
	 * @param checkParam
	 * @return
	 */
	@RequestMapping(value = "checkDeductionPointSucc", method = RequestMethod.POST)
    Result<CheckPointStatusDTO> checkDeductionPointSucc(@RequestBody GamePuzzleCheckDeductionPointParam checkParam);

	@RequestMapping(value = "loadingRoomMasterRequest", method = RequestMethod.POST)
    Result<GameAttendSaveReturnDTO> loadingRoomMasterRequest(@RequestBody GamePuzzleChallengeParam puzzleParam);

	@RequestMapping(value = "loadRankingInfo", method = RequestMethod.POST)
	Result loadRankingInfo(@RequestBody GamePuzzleLoadRankingParam param);

	@RequestMapping(value = "getMyGameRank", method = RequestMethod.POST)
	Result<List<GamePuzzleRankReturnDTO>> getMyGameRank(@RequestBody GamePuzzleLoadRankingParam param);

	@RequestMapping(value = "checkPuzzleIsOver", method = RequestMethod.GET)
	Result<PuzzleCheckIsOverDTO> checkPuzzleIsOver(@RequestParam("userNum") String userNum,@RequestParam("attendNum") String attendNum);
	
	@RequestMapping(value = "joinGameCache", method = RequestMethod.GET)
    Result joinGameCache(@RequestParam("userNum") String userNum,@RequestParam("type") CacheGameTypeEnum type);
}
