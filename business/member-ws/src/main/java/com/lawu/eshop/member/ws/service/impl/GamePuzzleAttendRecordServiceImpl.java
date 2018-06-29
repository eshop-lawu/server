package com.lawu.eshop.member.ws.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gexin.fastjson.JSON;
import com.lawu.eshop.cache.constants.CacheGameTypeEnum;
import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameCommonNumDTO;
import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.GameRoomOperationParam;
import com.lawu.eshop.common.dto.GameUserInfoDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.dto.CheckPointStatusDTO;
import com.lawu.eshop.game.dto.GameAttendSaveReturnDTO;
import com.lawu.eshop.game.dto.GameMindBattleUserUserInfoDTO;
import com.lawu.eshop.game.dto.GamePuzzleAttendRecordDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleResultDTO;
import com.lawu.eshop.game.dto.MindPkOnlineDTO;
import com.lawu.eshop.game.dto.PuzzlePkAbandonDTO;
import com.lawu.eshop.game.param.GamePuzzleChallengeParam;
import com.lawu.eshop.game.param.GamePuzzleCheckDeductionPointParam;
import com.lawu.eshop.game.param.GamePuzzleValidSuccSrvParam;
import com.lawu.eshop.game.param.PuzzlePKStartParam;
import com.lawu.eshop.game.param.PuzzleStartParam;
import com.lawu.eshop.member.ws.channel.ChannelManager;
import com.lawu.eshop.member.ws.event.EventPublisher;
import com.lawu.eshop.member.ws.processor.SendProcessor;
import com.lawu.eshop.member.ws.service.GameCommonCacheService;
import com.lawu.eshop.member.ws.service.GameConfigCacheService;
import com.lawu.eshop.member.ws.service.GamePuzzleAttendRecordService;
import com.lawu.eshop.member.ws.service.GamePuzzleCacheService;
import com.lawu.eshop.member.ws.service.GamePuzzleRecordService;
import com.lawu.eshop.member.ws.service.GameRoomCacheService;
import com.lawu.eshop.member.ws.service.GameRoomExtendService;
import com.lawu.eshop.member.ws.service.MemberService;
import com.lawu.eshop.member.ws.service.UserInfoService;
import com.lawu.eshop.member.ws.util.ResultUtil;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.web.Result;

/**
 * 
 * @author lihj <br>
 * @date 2018/3/16
 */
@Service
public class GamePuzzleAttendRecordServiceImpl implements GamePuzzleRecordService {

	@Autowired
	private GamePuzzleAttendRecordService gamePuzzleAttendRecordService;

	@Autowired
	private MemberService memberService;
	/**
	 * 主动推送服务
	 */
	@Autowired
	private SendProcessor sendProcessor;
	
    @Autowired
    private GameRoomExtendService gameRoomExtendService;
    
	@Autowired
	private GamePuzzleCacheService gamePuzzleCacheService;
	
	@Autowired
	private GameCommonCacheService gameCommonCacheService;
	
	@Autowired
	private GameRoomCacheService gameRoomCacheService;
	
	@Autowired
	private GameConfigCacheService gameConfigCacheService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private EventPublisher eventPublisher;
	private Logger logger = LoggerFactory.getLogger(GamePuzzleAttendRecordServiceImpl.class);

	@Override
	public Result<GamePuzzleGetPicReturnDTO> checkDeductionPointSucc(GamePuzzleCheckDeductionPointParam checkParam) {
		Result<CheckPointStatusDTO> result = gamePuzzleAttendRecordService.checkDeductionPointSucc(checkParam);
		if (ResultUtil.isSuccess(result)) {
			CheckPointStatusDTO dto = result.getModel();
			if(dto.getWaitDepoint()){
				return ResultUtil.get(ResultCode.GAME_WS_USER_CHECK_NOTDONE);
			}
			if (dto.getAllUserCheckPoint() == true) { // 所有人都扣除成功
				Result<GamePuzzleGetPicReturnDTO> listDto = gamePuzzleAttendRecordService.getPicByHardLevel(checkParam.getUserNum(),
						checkParam.getAttendNum());
				if (checkParam.getAttendType().equals(AttendTypeEnum.RANDOM)) {
					CheckCacheMatchParam check = new CheckCacheMatchParam();
					check.setType(CacheGameTypeEnum.PUZZLE);
					check.setKey(checkParam.getUserNum());
					Result<GameMatchResultDTO> resultGame = gameCommonCacheService.checkCacheMatchEatchother(check);
					List<GameCommonNumDTO> list = resultGame.getModel().getCommonInfo();
					for (GameCommonNumDTO game : list) {
						gameConfigCacheService.setGamePuzzleStartTimeValue(game.getUserNum(), String.valueOf(System.currentTimeMillis()));
					}
				} else {
					String groupNum = ChannelManager.getGroupNum(checkParam.getUserNum());
					GameRoomOperationParam roomParam = new GameRoomOperationParam();
					roomParam.setRoomNum(groupNum);
					roomParam.setGameType(GameTypeEnum.PUZZLE);
					Result<GameRoomDetailsDTO> resultGameRoom = gameRoomCacheService.getGameRoomAllPlayer(roomParam);
					List<GameRoomPlayerInfoDTO> list = resultGameRoom.getModel().getPlayerInfos();
					for (GameRoomPlayerInfoDTO game : list) {
						gameConfigCacheService.setGamePuzzleStartTimeValue(game.getUserNum(), String.valueOf(System.currentTimeMillis()));
					}
				}
				//玩游戏完成瑞奇岛动力任务
				eventPublisher.publishRichPowerTaskEvent(checkParam.getAttendNum(), GameTypeEnum.PUZZLE);
				return ResultUtil.get(ResultCode.GAME_WS_USER_CHECK_DONE, listDto.getModel());
			} else { // 有人未扣除成功
				Result<GameRoomDetailsDTO>  quitResult = gameRoomExtendService.exitGameRoom(null, checkParam.getUserNum(), GameTypeEnum.PUZZLE);
				/*Result<GameRoomDetailsDTO> quitResult = gamePuzzleRecordService.quitRoom(checkParam.getUserNum());*/
				if (ResultUtil.isSuccess(quitResult)) {
					GameRoomDetailsDTO quitModel = quitResult.getModel();
					PuzzlePkAbandonDTO model = new PuzzlePkAbandonDTO();
					model.setExitUserNum(checkParam.getUserNum());
					if (quitModel != null) {
		                 model.setIsDissolution(quitModel.getIsDissolution());
		             }
					sendProcessor.sendToGroupByUser(ResultUtil.get(ResultCode.GAME_WS_USER_OFFLINE, model),
							checkParam.getUserNum());
				} else {
					// 如果异常只发送给当前用户
					sendProcessor.send(ResultUtil.get(quitResult.getRet(), quitResult.getMsg()), checkParam.getUserNum());
				}
			}
		} else {
			return ResultUtil.get(ResultCode.GAME_WS_USER_CHECK_NOTDONE);
		}
		return ResultUtil.get(ResultCode.GAME_WS_USER_CHECK_NOTDONE);
	}

	private List<GameMindBattleUserUserInfoDTO> getUsers(GameRoomDetailsDTO gameRoomDetailsDTO) {
		List<GameMindBattleUserUserInfoDTO> lt = new ArrayList<GameMindBattleUserUserInfoDTO>();
		for (GameRoomPlayerInfoDTO plays : gameRoomDetailsDTO.getPlayerInfos()) {
			GameMindBattleUserUserInfoDTO dto = new GameMindBattleUserUserInfoDTO();
			dto.setHeadImg(plays.getHeadImg());
			dto.setNickName(plays.getNickName());
			dto.setUserNum(plays.getUserNum());
			UserDTO userDto = memberService.getMemberByNum(plays.getUserNum()).getModel();
			dto.setRegionName(userDto.getRegionName());
			dto.setRegionPath(userDto.getRegionPath());
			lt.add(dto);
		}
		return lt;
	}

	@Override
	public Result<GameAttendSaveReturnDTO> startGame(String userNum, PuzzlePKStartParam gameParam) {
		Result<GameAttendSaveReturnDTO> result;
		if (gameParam.getAttendType().equals(AttendTypeEnum.RANDOM)) {// 随机
			GamePuzzleChallengeParam challParam = new GamePuzzleChallengeParam();
			challParam.setUserNum(userNum);
			result = gamePuzzleAttendRecordService.loadingRoomMasterRequest(challParam);
		} else {// 多人
			PuzzleStartParam param = new PuzzleStartParam();
			param.setRoomNum(ChannelManager.getGroupNum(userNum));
			param.setCostPoint(gameParam.getCostPoint());
			param.setGameLevel(gameParam.getGameLevel());
			result = gamePuzzleAttendRecordService.friendPuzzleGameInit(param);
			String groupNum = ChannelManager.getGroupNum(userNum);
			GameRoomOperationParam roomParam = new GameRoomOperationParam();
			roomParam.setRoomNum(groupNum);
			roomParam.setGameType(GameTypeEnum.PUZZLE);
			Result<GameRoomDetailsDTO> resultGameRoom = gameRoomCacheService.getGameRoomAllPlayer(roomParam);
			List<GameMindBattleUserUserInfoDTO> lt = getUsers(resultGameRoom.getModel());
			result.getModel().setBattleUsers(lt);
		}
		return ResultUtil.get(ResultCode.GAME_WS_USER_START, result.getModel());
	}

	@Override
	public Result<GamePuzzleResultDTO> checkPuzzlePicStatus(GamePuzzleValidSuccSrvParam srvParam) {
		Result<GamePuzzleResultDTO> result = gamePuzzleAttendRecordService.checkPuzzlePicStatus(srvParam);
		return ResultUtil.get(ResultCode.GAME_WS_USER_SUBMIT_SINGLE, result.getModel());
	}

	@Override
	public Result<MindPkOnlineDTO> readyPuzzleStartGame(String userNum, String attendNum) {
		List<String> userNums = gamePuzzleCacheService.readyStartGame(userNum, attendNum).getModel();
		Result<MindPkOnlineDTO> result = new Result<>();

		CheckCacheMatchParam check = new CheckCacheMatchParam();
		check.setType(CacheGameTypeEnum.PUZZLE);
		check.setKey(userNum);
		Result<GameMatchResultDTO> resultGame = gameCommonCacheService.checkCacheMatchEatchother(check);
		List<GameCommonNumDTO> list = resultGame.getModel().getCommonInfo();
		logger.info("readyPuzzleStartGame获取到的值是"+ JSON.toJSONString(list)+"  "+JSON.toJSONString(userNums));
		Map<String, Boolean> map =null;
		if(null ==list ||list.size()==0){
			map = initRoomHost(userNums);
		}else{
			map = getRoomHost(list);
		}

		Result<List<GameUserInfoDTO>> findUserInfoForGameMindResult = userInfoService.findUserInfo(userNums);
		if (!ResultUtil.isSuccess(findUserInfoForGameMindResult)) {
			result.setRet(findUserInfoForGameMindResult.getRet());
			result.setMsg(findUserInfoForGameMindResult.getMsg());
			return result;
		}
		List<GameRoomPlayerInfoDTO> users = new ArrayList<>();
		for (GameUserInfoDTO dto : findUserInfoForGameMindResult.getModel()) {
			GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO = new GameRoomPlayerInfoDTO();
			gameRoomPlayerInfoDTO.setHeadImg(dto.getHeadImg());
			gameRoomPlayerInfoDTO.setIsRoomHost(map.get(dto.getUserNum()));
			gameRoomPlayerInfoDTO.setNickName(dto.getNickName());
			gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
			gameRoomPlayerInfoDTO.setUserNum(dto.getUserNum());
			gameRoomPlayerInfoDTO.setRegionName(dto.getRegionName());
			gameRoomPlayerInfoDTO.setRegionPath(dto.getRegionPath());
			users.add(gameRoomPlayerInfoDTO);
		}
		MindPkOnlineDTO puzzleDTO = new MindPkOnlineDTO();
		puzzleDTO.setUsers(users);
		return ResultUtil.getSuccess(puzzleDTO);
	}

	private Map<String,Boolean> initRoomHost(List<String> userNums) {
		Map<String,Boolean> map =new HashMap<>();
		for(int i=0;i<userNums.size();i++){
			if(i==0){
				map.put(userNums.get(i),true);
			}else{
				map.put(userNums.get(i),false);
			}
		}
		return map;
	}

	private Map<String, Boolean> getRoomHost(List<GameCommonNumDTO> list) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for (GameCommonNumDTO comm : list) {
			map.put(comm.getUserNum(), comm.isRoomMaster());
		}
		return map;
	}

	private List<String> getUserNums(List<GameCommonNumDTO> list) {
		List<String> lt = new ArrayList();
		for (GameCommonNumDTO game : list) {
			lt.add(game.getUserNum());
		}
		return lt;
	}

	@Override
	public Result<GameRoomDetailsDTO> quitRoom(String userNum) {
		String groupNum = ChannelManager.getGroupNum(userNum);
		// 分组编号为空
		if (groupNum == null) {
			groupNum = "";
		}
		return gamePuzzleAttendRecordService.quitRoom(userNum, groupNum);
	}

	@Override
	public Result removeStartCacheData(GamePuzzleValidSuccSrvParam validParam) {
		return gamePuzzleAttendRecordService.removeStartCacheData(validParam);
	}

	@Override
	public Result removeMyPuzzleCacheData(String userNum) {
		return gamePuzzleAttendRecordService.removeMyPuzzleCacheData(userNum);
	}

	@Override
    public Result<String> exceptionExitExecute(String userNum){
		return gamePuzzleAttendRecordService.exceptionExitExecute(userNum);
	}

	@Override
	public Result<GamePuzzleAttendRecordDTO> getPuzzleAttendRecordNearlyData(String userNum) {
		return gamePuzzleAttendRecordService.getPuzzleAttendRecordNearlyData(userNum);
	}
}
