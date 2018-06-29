package com.lawu.eshop.member.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.CacheGameTypeEnum;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.param.ExitMatchQueueParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.game.dto.CheckPointStatusDTO;
import com.lawu.eshop.game.dto.GameAttendSaveReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleRankReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleResultDTO;
import com.lawu.eshop.game.dto.PuzzleCheckIsOverDTO;
import com.lawu.eshop.game.param.GamePuzzleChallengeParam;
import com.lawu.eshop.game.param.GamePuzzleCheckDeductionPointParam;
import com.lawu.eshop.game.param.GamePuzzleFriendPlayInitParam;
import com.lawu.eshop.game.param.GamePuzzleFriendPlaySaveParam;
import com.lawu.eshop.game.param.GamePuzzleLoadRankingParam;
import com.lawu.eshop.game.param.GamePuzzleStandaloneParam;
import com.lawu.eshop.game.param.GamePuzzleValidSuccParam;
import com.lawu.eshop.game.param.GamePuzzleValidSuccSrvParam;
import com.lawu.eshop.member.api.event.EventPublisher;
import com.lawu.eshop.member.api.service.GameCommonCacheService;
import com.lawu.eshop.member.api.service.GameConfigCacheService;
import com.lawu.eshop.member.api.service.GamePuzzleAttendRecordService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @author lihj
 * @Date 2018年3月15日
 */
@Api(tags = "gamePuzzleAttend")
@RestController
@RequestMapping(value = "gamePuzzleAttend/")
public class GamePuzzleAttendRecordController extends BaseController{
	@Autowired
	private GameConfigCacheService gameConfigCacheService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private GamePuzzleAttendRecordService gamePuzzleAttendRecordService;
	@Autowired
	private GameCommonCacheService gameCommonCacheService;

	@Autowired
	private EventPublisher eventPublisher;

	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "查询是否扣除积分成功", notes = "查询是否扣除积分成功(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "checkDeductionPointSucc", method = RequestMethod.GET)
	public Result<CheckPointStatusDTO> checkDeductionPointSucc(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestParam @ApiParam(required = true, value = "参与编号") String attendNum) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		GamePuzzleCheckDeductionPointParam checkParam =new GamePuzzleCheckDeductionPointParam();
		checkParam.setAttendNum(attendNum);
		checkParam.setUserNum(userNum);
		Result result =gamePuzzleAttendRecordService.checkDeductionPointSucc(checkParam);
		return result;
	}

	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "拼图单机开始", notes = "拼图单机开始(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "loadingStandalone", method = RequestMethod.GET)
	public Result<GameAttendSaveReturnDTO> loadingStandalone(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		Result<GamePuzzleConfigCacheDTO> gamePuzzleConfig = gameConfigCacheService.getGamePuzzleConfig();
		if (gamePuzzleConfig == null || gamePuzzleConfig.getModel() == null
				|| GameConfigStatusEnum.DISABLE == gamePuzzleConfig.getModel().getStatusEnum()) {
			return successCreated(ResultCode.GAME_SETTING_DISABLE,gamePuzzleConfig.getModel().getForbiddenRemark());
		}
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		GamePuzzleStandaloneParam gameStanda =new GamePuzzleStandaloneParam();
		gameStanda.setUserNum(userNum);
		Result<GameAttendSaveReturnDTO> returnDTO = gamePuzzleAttendRecordService.loadingStandalone(gameStanda);
		return returnDTO;
	}

	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "拼图随机匹配开始", notes = "拼图随机匹配开始(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "loadingChallenge", method = RequestMethod.GET)
	public Result<GameAttendSaveReturnDTO> loadingChallenge(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		GamePuzzleChallengeParam param =new GamePuzzleChallengeParam();
		param.setUserNum(userNum);
		Result<GameAttendSaveReturnDTO> returnDTO = gamePuzzleAttendRecordService.loadingChallenge(param);
		return returnDTO;
	}


	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "拼图随机匹配初始化记录房主调用", notes = "拼图随机匹配初始化记录房主调用(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "loadingRoomMasterRequest", method = RequestMethod.GET)
	public Result<GameAttendSaveReturnDTO> loadingRoomMasterRequest(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@RequestParam @ApiParam(required = true, value = "参与编号") String attendNum) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		GamePuzzleChallengeParam puzzleParam = new GamePuzzleChallengeParam();
		puzzleParam.setUserNum(userNum);
		puzzleParam.setAttendNum(attendNum);
		Result<GameAttendSaveReturnDTO> dto = gamePuzzleAttendRecordService.loadingRoomMasterRequest(puzzleParam);
		return dto;
	}


	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "拼图loading好友匹配开始", notes = "拼图loading好友匹配开始(李洪军)", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "friendPuzzleGameInit", method = RequestMethod.POST)
	public Result<GameAttendSaveReturnDTO> friendPuzzleGameInit(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@ModelAttribute GamePuzzleFriendPlayInitParam param) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		GamePuzzleFriendPlaySaveParam initParam =new GamePuzzleFriendPlaySaveParam();
		initParam.setUserNum(userNum);
		initParam.setCostPoint(param.getCostPoint());
		initParam.setGameLevel(param.getGameLevel());
		initParam.setRoomNum(param.getRoomNum());
		initParam.setUserNums(param.getUserNums());
		Result<GameAttendSaveReturnDTO> returnDTO = gamePuzzleAttendRecordService.friendPuzzleGameInit(initParam);
		return returnDTO;
	}


	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "根据参与编号出拼图图片", notes = "根据参与编号出拼图图片(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getPicByHardLevel", method = RequestMethod.GET)
	public Result<GamePuzzleGetPicReturnDTO> getPicByHardLevel(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "房间参与编号") String attendNum) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result<GamePuzzleGetPicReturnDTO> returnDTO = gamePuzzleAttendRecordService.getPicByHardLevel(userNum,attendNum);
		gameConfigCacheService.setGamePuzzleStartTimeValue(userNum,String.valueOf(System.currentTimeMillis()));

		//玩游戏完成瑞奇岛动力任务
		RichPowerTaskRecordParam taskRecordParam = new RichPowerTaskRecordParam();
		taskRecordParam.setMemberNum(userNum);
		taskRecordParam.setType(PowerTaskTypeEnum.GAME);
		eventPublisher.publishRichPowerTaskEvent(taskRecordParam);
		return successGet(returnDTO);
	}

	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "判断用户拼图是否正确", notes = "判断用户拼图是否正确,正确加积分加星星(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "checkPuzzlePicStatus", method = RequestMethod.GET)
	public Result<GamePuzzleResultDTO> checkPuzzlePicStatus(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute GamePuzzleValidSuccParam param) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		GamePuzzleValidSuccSrvParam srvParam =new GamePuzzleValidSuccSrvParam();
		srvParam.setAttendNum(param.getAttendNum());
		srvParam.setGameId(param.getGameId());
		srvParam.setPicNum(param.getPicNum());
		srvParam.setUserNum(userNum);
		Result<GamePuzzleResultDTO> dto = gamePuzzleAttendRecordService.checkPuzzlePicStatus(srvParam);
		return dto;
	}

	@Audit(date = "2018-03-19", reviewer = "孙林青")
	@ApiOperation(value = "拼图结束更新游戏排名", notes = "拼图结束更新游戏排名(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "loadRankingInfo",method = RequestMethod.GET)
	public Result loadRankingInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@RequestParam @ApiParam(required = true, value = "房间参与编号") String attendNum){
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		GamePuzzleLoadRankingParam param =new GamePuzzleLoadRankingParam();
		param.setAttendNum(attendNum);
		param.setUserNum(userNum);
		Result result = gamePuzzleAttendRecordService.loadRankingInfo(param);
		return successGet(result);
	}

	@Audit(date = "2018-03-19", reviewer = "孙林青")
	@ApiOperation(value = "获取自己的游戏排名以及加分情况", notes = "获取自己的游戏排名以及加分情况(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getMyGameRank",method = RequestMethod.GET)
	public Result<List<GamePuzzleRankReturnDTO>> getMyGameRank(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@RequestParam @ApiParam(required = true, value = "房间参与编号") String attendNum){
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		GamePuzzleLoadRankingParam param =new GamePuzzleLoadRankingParam();
		param.setAttendNum(attendNum);
		param.setUserNum(userNum);
		Result<List<GamePuzzleRankReturnDTO>> result = gamePuzzleAttendRecordService.getMyGameRank(param);
		List<GamePuzzleRankReturnDTO> lt =result.getModel();
		for(int i=0;i<lt.size();i++){
			UserDTO userDTO = memberService.getMemberByNum(lt.get(i).getUserNum()).getModel();
			lt.get(i).setHeadImg(userDTO.getHeadimg());
			lt.get(i).setNickName(userDTO.getNickname());
			
		}
		return successGet(result);
	}

	@Audit(date = "2018-03-21", reviewer = "孙林青")
	@ApiOperation(value = "退出随机PK等待队列", notes = "退出随机PK等待队列(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "exitMatchQueue",method = RequestMethod.GET)
	public Result exitMatchQueue(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@RequestParam @ApiParam(required = true, value = "游戏类型") CacheGameTypeEnum gameType){
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		ExitMatchQueueParam exitParam =new ExitMatchQueueParam();
		exitParam.setType(gameType);
		exitParam.setKey(userNum);
		gameCommonCacheService.exitMatchQueue(exitParam);
		return successGet();
	}

	@Audit(date = "2018-03-24", reviewer = "孙林青")
	@ApiOperation(value = "加入随机匹配队列", notes = "加入随机匹配队列(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "joinGameCache", method = RequestMethod.GET)
    public Result joinGameCache(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@RequestParam @ApiParam(required = true, value = "游戏类型") CacheGameTypeEnum gameType){
		Result<GamePuzzleConfigCacheDTO> gamePuzzleConfig = gameConfigCacheService.getGamePuzzleConfig();
		if (gamePuzzleConfig == null || gamePuzzleConfig.getModel() == null
				|| GameConfigStatusEnum.DISABLE == gamePuzzleConfig.getModel().getStatusEnum()) {
			return successCreated(ResultCode.GAME_SETTING_DISABLE, gamePuzzleConfig.getModel().getForbiddenRemark());
		}
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		return gamePuzzleAttendRecordService.joinGameCache(userNum, gameType);
	}
	
	@Audit(date = "2018-03-19", reviewer = "孙林青")
	@ApiOperation(value = "查询当前房间中是否都拼完图", notes = "查询当前房间中是否都拼完图(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "checkPuzzleIsOver",method = RequestMethod.GET)
	public Result<PuzzleCheckIsOverDTO> checkPuzzleIsOver(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@RequestParam @ApiParam(required = true, value = "房间参与编号") String attendNum){
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result<PuzzleCheckIsOverDTO> result = gamePuzzleAttendRecordService.checkPuzzleIsOver(userNum,attendNum);
		return successGet(result);
	}
	
	
}
