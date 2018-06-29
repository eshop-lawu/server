package com.lawu.eshop.game.srv.service;

import java.util.List;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.game.param.GamePuzzleChallengeParam;
import com.lawu.eshop.game.param.GamePuzzleCheckDeductionPointParam;
import com.lawu.eshop.game.param.GamePuzzleLoadRankingParam;
import com.lawu.eshop.game.param.GamePuzzleRewardPointAndStarParam;
import com.lawu.eshop.game.param.GamePuzzleStandaloneParam;
import com.lawu.eshop.game.param.GamePuzzleValidSuccSrvParam;
import com.lawu.eshop.game.param.PuzzleStartParam;
import com.lawu.eshop.game.srv.bo.CheckPointStatusBo;
import com.lawu.eshop.game.srv.bo.GameAttendSaveReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleRankReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleResultBO;
import com.lawu.eshop.game.srv.bo.PuzzleCheckIsOverBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDO;

/**
 * @author lihj <br>
 * @date 2018/3/12
 */
public interface GamePuzzleAttendRecordService {

	void batchSavePuzzleAttendRecord(List<GamePuzzleAttendRecordBO> attendBO);

	Long savePuzzleAttendRecord(GamePuzzleAttendRecordBO attendBo);
	
	GamePuzzleAttendRecordBO getPuzzleAttendRecordById(Long id);

	void updateGamePuzzleAttendRecordStatus(Long id, GameAttendRecordStatusEnum attendsuccess);

	GamePuzzleAttendRecordBO updatePuzzleAttendRecord(GamePuzzleRewardPointAndStarParam param);

	/**
	 * 单机游戏loading
	 * @param param
	 * @return
	 */
	GameAttendSaveReturnBO loadingStandalone(GamePuzzleStandaloneParam param);

	/**
	 * 随机PK loading
	 * @param param
	 * @return
	 */
	GameAttendSaveReturnBO loadingChallenge(GamePuzzleChallengeParam param);

	/**
	 * 好友开房
	 * @param param
	 * @return
	 */
	GameAttendSaveReturnBO friendPuzzleGameInit(PuzzleStartParam param);

	/**
	 * 判断拼图是否正确
	 * @param srvParam
	 * @return
	 */
	GamePuzzleResultBO checkPuzzlePicStatus(GamePuzzleValidSuccSrvParam srvParam);

	/**
	 * 随机接收房主请求
	 * @param param
	 * @return
	 */
	GameAttendSaveReturnBO loadingRoomMasterRequest(GamePuzzleChallengeParam param);

    CheckPointStatusBo checkDeductionPointSucc(GamePuzzleCheckDeductionPointParam checkParam);

	/**
	 * 获取自己的排名
	 * @param param
	 * @return
	 */
	List<GamePuzzleRankReturnBO> getMyGameRank(GamePuzzleLoadRankingParam param);

	/**
	 * 检查是否拼完图片
	 * @param userNum
	 * @param attendNum
	 * @return
	 */
	PuzzleCheckIsOverBO checkPuzzleIsOver(String userNum, String attendNum);

	/**
	 * 退出房间
	 * @param userNum
	 * @param groupNum
	 * @return
	 */
	GameRoomDetailsDTO quitRoom(String userNum, String groupNum);

	/**
	 * 根据参与编号获取记录总数
	 * @param attendNum
	 * @return
	 */
	int getPuzzleAttendRecordByAttendNum(String attendNum);
	
	List<GamePuzzleAttendRecordDO> getAttendRecordByAttendAndStatus(String attendNum,GameAttendRecordStatusEnum status);

	String exceptionExitExecute(String userNum);

    GamePuzzleAttendRecordBO getPuzzleAttendRecordNearlyData(String userNum);

	/**
	 * 根据参与编号查询参与游戏的用户编号(完成动力任务)
	 *
	 * @param attendNum
	 * @return
	 * @author meishuquan
	 */
	List<String> getAttendRecordUserNums(String attendNum);

}
