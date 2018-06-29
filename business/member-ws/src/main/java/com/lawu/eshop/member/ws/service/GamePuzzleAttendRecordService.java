package com.lawu.eshop.member.ws.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.game.dto.CheckPointStatusDTO;
import com.lawu.eshop.game.dto.GameAttendSaveReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleAttendRecordDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleResultDTO;
import com.lawu.eshop.game.param.GamePuzzleChallengeParam;
import com.lawu.eshop.game.param.GamePuzzleCheckDeductionPointParam;
import com.lawu.eshop.game.param.GamePuzzleValidSuccSrvParam;
import com.lawu.eshop.game.param.PuzzleStartParam;
import com.lawu.framework.web.Result;

/**
 * @author lihj <br>
 * @date 2018/3/16
 */
@FeignClient(value = "game-srv", path = "gamePuzzleAttend/")
public interface GamePuzzleAttendRecordService {

	@RequestMapping(value = "friendPuzzleGameInit", method = RequestMethod.POST)
	Result<GameAttendSaveReturnDTO> friendPuzzleGameInit(@RequestBody PuzzleStartParam param);

	@RequestMapping(value = "checkPuzzlePicStatus", method = RequestMethod.POST)
	Result<GamePuzzleResultDTO> checkPuzzlePicStatus(@RequestBody GamePuzzleValidSuccSrvParam srvParam);

	/**
	 * 检查积分是否扣除成功
	 * 
	 * @param checkParam
	 * @return
	 */
	@RequestMapping(value = "checkDeductionPointSucc", method = RequestMethod.POST)
	Result<CheckPointStatusDTO> checkDeductionPointSucc(@RequestBody GamePuzzleCheckDeductionPointParam checkParam);

	@RequestMapping(value = "loadingRoomMasterRequest", method = RequestMethod.POST)
	Result<GameAttendSaveReturnDTO> loadingRoomMasterRequest(@RequestBody GamePuzzleChallengeParam puzzleParam);

	@RequestMapping(value = "getPicByHardLevel", method = RequestMethod.GET)
	Result<GamePuzzleGetPicReturnDTO> getPicByHardLevel(@RequestParam("userNum") String userNum,@RequestParam("attendNum") String attendNum);

	/**
	 * 退出房间清楚缓存
	 * @param userNum
	 * @param groupNum
	 * @return
	 */
	@RequestMapping(value = "quitRoom", method = RequestMethod.PUT)
	Result<GameRoomDetailsDTO> quitRoom(@RequestParam("userNum") String userNum,@RequestParam("groupNum") String groupNum);

	@RequestMapping(value = "removeStartCacheData", method = RequestMethod.POST)
    Result removeStartCacheData(@RequestBody GamePuzzleValidSuccSrvParam validParam);

	@RequestMapping(value = "removeMyPuzzleCacheData", method = RequestMethod.GET)
    Result removeMyPuzzleCacheData(@RequestParam("userNum") String userNum);
	
	@RequestMapping(value = "exceptionExitExecute", method = RequestMethod.GET)
    Result<String> exceptionExitExecute(@RequestParam("userNum") String userNum);

	@RequestMapping(value = "getPuzzleAttendRecordNearlyData", method = RequestMethod.GET)
	Result<GamePuzzleAttendRecordDTO> getPuzzleAttendRecordNearlyData(@RequestParam("userNum") String userNum);

	/**
	 * 根据参与编号查询参与游戏的用户编号(完成动力任务)
	 *
	 * @param attendNum
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "getAttendRecordUserNums", method = RequestMethod.GET)
	Result<List<String>> getAttendRecordUserNums(@RequestParam("attendNum") String attendNum);

}
