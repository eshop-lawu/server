package com.lawu.eshop.member.ws.service;

import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.game.dto.GameAttendSaveReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleAttendRecordDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleResultDTO;
import com.lawu.eshop.game.dto.MindPkOnlineDTO;
import com.lawu.eshop.game.param.GamePuzzleCheckDeductionPointParam;
import com.lawu.eshop.game.param.GamePuzzleValidSuccSrvParam;
import com.lawu.eshop.game.param.PuzzlePKStartParam;
import com.lawu.framework.web.Result;

/**
 * @author lihj <br>
 * @date 2018/3/16
 */
public interface GamePuzzleRecordService {

	Result<GamePuzzleGetPicReturnDTO> checkDeductionPointSucc(GamePuzzleCheckDeductionPointParam checkParam);

	Result<GameAttendSaveReturnDTO> startGame(String userNum, PuzzlePKStartParam gameParam);
	
	Result<GamePuzzleResultDTO> checkPuzzlePicStatus(GamePuzzleValidSuccSrvParam srvParam);

	Result<MindPkOnlineDTO> readyPuzzleStartGame(String userNum,String attendNum);

	/**
	 * 退出房间
	 * @param userNum
	 * @return
	 */
	Result<GameRoomDetailsDTO> quitRoom(String userNum);


    @SuppressWarnings("rawtypes")
    Result removeStartCacheData(GamePuzzleValidSuccSrvParam validParam);

    @SuppressWarnings("rawtypes")
    Result removeMyPuzzleCacheData(String userNum);
    
    Result<String> exceptionExitExecute(String userNum);

	/**
	 * 根据用户编号查询最新的参与记录并返回
	 * @param userNum
	 * @return
	 */
	Result<GamePuzzleAttendRecordDTO> getPuzzleAttendRecordNearlyData(String userNum);
}
