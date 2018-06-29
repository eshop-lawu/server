package com.lawu.eshop.member.api.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.game.constants.GameRoomStatusEnum;
import com.lawu.eshop.game.dto.GameAccountDTO;
import com.lawu.eshop.game.dto.GameRoomCreateDTO;
import com.lawu.eshop.game.dto.GameRoomDTO;
import com.lawu.eshop.game.dto.GameRoomMaxNumDTO;
import com.lawu.eshop.game.dto.GameRoomPlayerJoinDTO;
import com.lawu.eshop.game.dto.GameRoomPointDTO;
import com.lawu.eshop.game.param.GameAccountParam;
import com.lawu.eshop.game.param.GameRoomCreateParam;
import com.lawu.eshop.game.param.GameRoomParam;
import com.lawu.eshop.game.query.GameRoomQuery;
import com.lawu.eshop.member.api.service.GameAccountService;
import com.lawu.eshop.member.api.service.GameConfigCacheService;
import com.lawu.eshop.member.api.service.GameRoomService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.PropertyInfoService;
import com.lawu.eshop.property.dto.PropertyPointDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
@Api(tags = "gameRoom")
@RestController
@RequestMapping(value = "gameRoom/")
public class GameRoomController extends BaseController {

	@Autowired
	private GameRoomService gameRoomService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private GameConfigCacheService gameConfigCacheService;

	@Autowired
	private PropertyInfoService propertyInfoService;

	@Autowired
	private GameAccountService gameAccountService;

	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "创建房间", notes = "创建房间。[1004|1100|10006|10015] (梅述全)", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "saveGameRoom", method = RequestMethod.POST)
	public Result<GameRoomPlayerJoinDTO> saveGameRoom(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
													  @ModelAttribute GameRoomCreateParam param) {
		BigDecimal attendPoint;
		BigDecimal attendMaxPoint;
		if (param.getTypeEnum().toString().equals(GameTypeEnum.MIND.toString())) {
			Result<GameMindConfigDTO> result = gameConfigCacheService.getGameMindConfig();
			if (result == null || result.getModel() == null) {
				return successCreated(ResultCode.NOT_FOUND_DATA);
			}
			GameMindConfigDTO mindConfigParamDTO = result.getModel();
			if (GameConfigStatusEnum.DISABLE == mindConfigParamDTO.getStatusEnum()) {
				return successCreated(ResultCode.GAME_SETTING_DISABLE, mindConfigParamDTO.getForbiddenRemark());
			}
			attendPoint = BigDecimal.valueOf(mindConfigParamDTO.getAttendPoint());
			attendMaxPoint = BigDecimal.valueOf(mindConfigParamDTO.getAttendMaxPoint());
		} else if (param.getTypeEnum().toString().equals(GameTypeEnum.PUZZLE.toString())) {
			Result<GamePuzzleConfigCacheDTO> result = gameConfigCacheService.getGamePuzzleConfig();
			if (result == null || result.getModel() == null) {
				return successCreated(ResultCode.NOT_FOUND_DATA);
			}
			GamePuzzleConfigCacheDTO puzzleConfigParamDTO = result.getModel();
			if (GameConfigStatusEnum.DISABLE == puzzleConfigParamDTO.getStatusEnum()) {
				return successCreated(ResultCode.GAME_SETTING_DISABLE, puzzleConfigParamDTO.getForbiddenRemark());
			}
			attendPoint = BigDecimal.valueOf(puzzleConfigParamDTO.getAttendPoint());
			attendMaxPoint = BigDecimal.valueOf(puzzleConfigParamDTO.getAttendMaxPoint());
		} else {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
		}
		if (param.getPoint().compareTo(attendPoint) == -1 || param.getPoint().compareTo(attendMaxPoint) == 1) {
			return successCreated(ResultCode.GAME_ROOM_POINT_ERROR);
		}

		String userNum = UserUtil.getCurrentUserNum(getRequest());
		String account = UserUtil.getCurrentAccount(getRequest());
		Result validateResult = propertyInfoService.validatePoint(userNum, param.getPoint().toString());
		if (!isSuccess(validateResult)) {
			return successCreated(validateResult);
		}

		Result<UserDTO> userDTOResult = memberService.getMemberByNum(userNum);
		GameRoomPlayerInfoDTO infoDTO = new GameRoomPlayerInfoDTO();
		infoDTO.setUserNum(userNum);
		infoDTO.setHeadImg(userDTOResult.getModel().getHeadimg());
		infoDTO.setNickName(userDTOResult.getModel().getNickname());
		infoDTO.setIsRoomHost(true);
		infoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
		String playerInfo = JSONObject.toJSONString(infoDTO);

		GameRoomParam roomParam = new GameRoomParam();
		roomParam.setUserNum(userNum);
		roomParam.setAccount(account);
		roomParam.setPlayerInfo(playerInfo);
		roomParam.setPoint(param.getPoint());
		roomParam.setPwd(param.getPwd());
		roomParam.setTypeEnum(param.getTypeEnum());
		roomParam.setStatusEnum(GameRoomStatusEnum.WAITING);
		roomParam.setHardLevelEnum(param.getHardLevelEnum());
		Result<GameRoomCreateDTO> dtoResult = gameRoomService.saveGameRoom(roomParam);

		GameAccountParam accountParam = new GameAccountParam();
		accountParam.setAccount(account);
		accountParam.setUserNum(userNum);
		accountParam.setType(param.getTypeEnum());
		Result<GameAccountDTO> accountDTOResult = gameAccountService.getAccountInfo(accountParam);
		Result<PropertyPointDTO> pointDTOResult = propertyInfoService.getPropertyPoint(userNum);
		GameRoomPlayerJoinDTO joinDTO = new GameRoomPlayerJoinDTO();
		joinDTO.setRoomId(dtoResult.getModel().getRoomId());
		joinDTO.setRoomHostNum(userNum);
		joinDTO.setRoomNum(dtoResult.getModel().getRoomNum());
		joinDTO.setUserNum(userNum);
		joinDTO.setIsHomeowner(true);
		joinDTO.setHeadImg(userDTOResult.getModel().getHeadimg());
		joinDTO.setNickName(userDTOResult.getModel().getNickname());
		joinDTO.setPoint(param.getPoint());
		joinDTO.setAccountPoint(pointDTOResult.getModel().getPoint());
		joinDTO.setStarCount(accountDTOResult.getModel().getStarCount());
		joinDTO.setHardLevelEnum(param.getHardLevelEnum());
		joinDTO.setRoomPwd(param.getPwd());
		return successCreated(joinDTO);
	}

	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "房间列表", notes = "房间列表。(梅述全)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "listGameRoom", method = RequestMethod.GET)
	public Result<Page<GameRoomDTO>> listGameRoom(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute GameRoomQuery query) {
		Result<Page<GameRoomDTO>> result = gameRoomService.listGameRoom(query);
		return successGet(result);
	}

	@Audit(date = "2018-03-19", reviewer = "孙林青")
	@ApiOperation(value = "房间最大参与人数", notes = "房间最大参与人数。[1100] (梅述全)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getGameRoomMaxNum", method = RequestMethod.GET)
	public Result<GameRoomMaxNumDTO> getGameRoomMaxNum(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "房间类型：MIND--头脑PK，PUZZLE--拼图") GameTypeEnum typeEnum) {
		GameRoomMaxNumDTO maxNumDTO = new GameRoomMaxNumDTO();
		if (typeEnum.toString().equals(GameTypeEnum.MIND.toString())) {
			Result<GameMindConfigDTO> result = gameConfigCacheService.getGameMindConfig();
			if (result == null || result.getModel() == null) {
				return successGet(ResultCode.NOT_FOUND_DATA);
			}
			maxNumDTO.setRoomMaxNum(result.getModel().getRoomMaxNum());
		} else if (typeEnum.toString().equals(GameTypeEnum.PUZZLE.toString())) {
			Result<GamePuzzleConfigCacheDTO> result = gameConfigCacheService.getGamePuzzleConfig();
			if (result == null || result.getModel() == null) {
				return successGet(ResultCode.NOT_FOUND_DATA);
			}
			maxNumDTO.setRoomMaxNum(result.getModel().getRoomMaxNum());
		}
		return successGet(maxNumDTO);
	}

	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "创建房间所需积分", notes = "创建房间所需积分。[1100] (梅述全)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getGameRoomPoint", method = RequestMethod.GET)
	public Result<GameRoomPointDTO> getGameRoomPoint(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "房间类型：MIND--头脑PK，PUZZLE--拼图") GameTypeEnum typeEnum) {
		GameRoomPointDTO pointDTO = new GameRoomPointDTO();
		if (typeEnum.toString().equals(GameTypeEnum.MIND.toString())) {
			Result<GameMindConfigDTO> result = gameConfigCacheService.getGameMindConfig();
			if (result == null || result.getModel() == null) {
				return successGet(ResultCode.NOT_FOUND_DATA);
			}
			GameMindConfigDTO mindConfigParamDTO = result.getModel();
			pointDTO.setMinPoint(BigDecimal.valueOf(mindConfigParamDTO.getAttendPoint()));
			pointDTO.setMaxPoint(BigDecimal.valueOf(mindConfigParamDTO.getAttendMaxPoint()));
		} else if (typeEnum.toString().equals(GameTypeEnum.PUZZLE.toString())) {
			Result<GamePuzzleConfigCacheDTO> result = gameConfigCacheService.getGamePuzzleConfig();
			if (result == null || result.getModel() == null) {
				return successGet(ResultCode.NOT_FOUND_DATA);
			}
			GamePuzzleConfigCacheDTO puzzleConfigParamDTO = result.getModel();
			pointDTO.setMinPoint(BigDecimal.valueOf(puzzleConfigParamDTO.getAttendPoint()));
			pointDTO.setMaxPoint(BigDecimal.valueOf(puzzleConfigParamDTO.getAttendMaxPoint()));
		}
		return successGet(pointDTO);
	}

	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "进入房间", notes = "进入房间。[1002|1100|10007|10010|10015|10016|6024|6010] (梅述全)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "joinGameRoom/{id}", method = RequestMethod.GET)
	public Result<GameRoomPlayerJoinDTO> joinGameRoom(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@PathVariable @ApiParam(required = true, value = "id") Long id,
			@RequestParam @ApiParam(required = false, value = "密码") String pwd) {
		Result<GameRoomDTO> roomDTOResult = gameRoomService.getGameRoom(id);
		GameRoomDTO roomDTO = roomDTOResult.getModel();
		if (roomDTO == null) {
			return successGet(ResultCode.RESOURCE_NOT_FOUND);
		}
		int roomMaxNum = 0;
		// 判断游戏否禁用
		if (GameTypeEnum.MIND == roomDTO.getTypeEnum()) {
			Result<GameMindConfigDTO> configDTOResult = gameConfigCacheService.getGameMindConfig();
			if (configDTOResult == null || configDTOResult.getModel() == null) {
				return successGet(ResultCode.NOT_FOUND_DATA);
			}
			if (GameConfigStatusEnum.DISABLE == configDTOResult.getModel().getStatusEnum()) {
				return successGet(ResultCode.GAME_SETTING_DISABLE, configDTOResult.getModel().getForbiddenRemark());
			}
			roomMaxNum = configDTOResult.getModel().getRoomMaxNum();
		} else if (GameTypeEnum.PUZZLE == roomDTO.getTypeEnum()) {
			Result<GamePuzzleConfigCacheDTO> gamePuzzleConfig = gameConfigCacheService.getGamePuzzleConfig();
			if (gamePuzzleConfig == null || gamePuzzleConfig.getModel() == null) {
				return successGet(ResultCode.NOT_FOUND_DATA);
			}
			if (GameConfigStatusEnum.DISABLE == gamePuzzleConfig.getModel().getStatusEnum()) {
				return successGet(ResultCode.GAME_SETTING_DISABLE, gamePuzzleConfig.getModel().getForbiddenRemark());
			}
			roomMaxNum = gamePuzzleConfig.getModel().getRoomMaxNum();
		}
		if (roomDTO.getStatusEnum() != GameRoomStatusEnum.WAITING) {
			return successGet(ResultCode.GAME_ROOM_FORBID_JOIN);
		}
		if (roomDTO.getPlayerInfoDTOS().size() >= roomMaxNum) {
			return successGet(ResultCode.GAME_ROOM_FULL);
		}
		if (roomDTO.getIsPwd() && !roomDTO.getPwd().equalsIgnoreCase(pwd)) {
			return successGet(ResultCode.GAME_ROOM_PWD_ERROR);
		}

		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result validateResult = propertyInfoService.validatePoint(userNum, roomDTO.getPoint().toString());
		if (!isSuccess(validateResult)) {
			return successGet(validateResult);
		}

		Result<UserDTO> userDTOResult = memberService.getMemberByNum(userNum);
		GameAccountParam accountParam = new GameAccountParam();
		accountParam.setAccount(UserUtil.getCurrentAccount(getRequest()));
		accountParam.setUserNum(userNum);
		accountParam.setType(roomDTO.getTypeEnum());
		Result<GameAccountDTO> accountDTOResult = gameAccountService.getAccountInfo(accountParam);
		Result<PropertyPointDTO> pointDTOResult = propertyInfoService.getPropertyPoint(userNum);
		GameRoomPlayerJoinDTO joinDTO = new GameRoomPlayerJoinDTO();
		joinDTO.setRoomHostNum(roomDTO.getUserNum());
		joinDTO.setRoomNum(roomDTO.getRoomNum());
		joinDTO.setUserNum(userNum);
		joinDTO.setIsHomeowner(false);
		joinDTO.setHeadImg(userDTOResult.getModel().getHeadimg());
		joinDTO.setNickName(userDTOResult.getModel().getNickname());
		joinDTO.setPoint(roomDTO.getPoint());
		joinDTO.setAccountPoint(pointDTOResult.getModel().getPoint());
		joinDTO.setStarCount(accountDTOResult.getModel().getStarCount());
		joinDTO.setHardLevelEnum(roomDTO.getHardLevelEnum());
		joinDTO.setRoomPwd(roomDTO.getPwd());
		return successGet(joinDTO);
	}

	@Audit(date = "2018-05-15", reviewer = "孙林青")
	@ApiOperation(value = "通过消息进入房间", notes = "通过消息进入房间。[1002|1100|10007|10010|10015|10016|6024|6010] (李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "joinUrlGameRoom", method = RequestMethod.GET)
	public Result<GameRoomPlayerJoinDTO> joinUrlGameRoom(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "房主用户编号") String masterNum,
			@RequestParam @ApiParam(required = true, value = "房间id") Long roomId) {
		Result<GameRoomDTO> roomDTOResult = gameRoomService.getGameRoom(roomId);
		GameRoomDTO roomDTO = roomDTOResult.getModel();
		if (roomDTO == null) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		if(!roomDTO.getUserNum().equals(masterNum)){
			return successGet(ResultCode.GAME_ROOM_JOIN_FAIL);
		}
		int roomMaxNum = 0;
		// 判断游戏否禁用
		if (GameTypeEnum.MIND == roomDTO.getTypeEnum()) {
			Result<GameMindConfigDTO> configDTOResult = gameConfigCacheService.getGameMindConfig();
			if (configDTOResult == null || configDTOResult.getModel() == null) {
				return successGet(ResultCode.NOT_FOUND_DATA);
			}
			if (GameConfigStatusEnum.DISABLE == configDTOResult.getModel().getStatusEnum()) {
				return successGet(ResultCode.GAME_SETTING_DISABLE, configDTOResult.getModel().getForbiddenRemark());
			}
			roomMaxNum = configDTOResult.getModel().getRoomMaxNum();
		} else if (GameTypeEnum.PUZZLE == roomDTO.getTypeEnum()) {
			Result<GamePuzzleConfigCacheDTO> gamePuzzleConfig = gameConfigCacheService.getGamePuzzleConfig();
			if (gamePuzzleConfig == null || gamePuzzleConfig.getModel() == null) {
				return successGet(ResultCode.NOT_FOUND_DATA);
			}
			if (GameConfigStatusEnum.DISABLE == gamePuzzleConfig.getModel().getStatusEnum()) {
				return successGet(ResultCode.GAME_SETTING_DISABLE, gamePuzzleConfig.getModel().getForbiddenRemark());
			}
			roomMaxNum = gamePuzzleConfig.getModel().getRoomMaxNum();
		}
		if (roomDTO.getStatusEnum() != GameRoomStatusEnum.WAITING) {
			return successGet(ResultCode.GAME_ROOM_FORBID_JOIN);
		}
		if (roomDTO.getPlayerInfoDTOS().size() >= roomMaxNum) {
			return successGet(ResultCode.GAME_ROOM_FULL);
		}
		
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result validateResult = propertyInfoService.validatePoint(userNum, roomDTO.getPoint().toString());
		if (!isSuccess(validateResult)) {
			return successGet(validateResult);
		}
		Result<UserDTO> userDTOResult = memberService.getMemberByNum(userNum);
		GameAccountParam accountParam = new GameAccountParam();
		accountParam.setAccount(UserUtil.getCurrentAccount(getRequest()));
		accountParam.setUserNum(userNum);
		accountParam.setType(roomDTO.getTypeEnum());
		Result<GameAccountDTO> accountDTOResult = gameAccountService.getAccountInfo(accountParam);
		Result<PropertyPointDTO> pointDTOResult = propertyInfoService.getPropertyPoint(userNum);
		GameRoomPlayerJoinDTO joinDTO = new GameRoomPlayerJoinDTO();
		joinDTO.setRoomNum(roomDTO.getRoomNum());
		joinDTO.setUserNum(userNum);
		joinDTO.setIsHomeowner(false);
		joinDTO.setHeadImg(userDTOResult.getModel().getHeadimg());
		joinDTO.setNickName(userDTOResult.getModel().getNickname());
		joinDTO.setPoint(roomDTO.getPoint());
		joinDTO.setAccountPoint(pointDTOResult.getModel().getPoint());
		joinDTO.setStarCount(accountDTOResult.getModel().getStarCount());
		joinDTO.setHardLevelEnum(roomDTO.getHardLevelEnum());
		joinDTO.setRoomPwd(roomDTO.getPwd());
		return successGet(joinDTO);
	}

	@Audit(date = "2018-03-27", reviewer = "孙林青")
	@ApiOperation(value = "查询房间用户个人信息", notes = "查询房间用户个人信息。[1100] (梅述全)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getGameRoomUserInfo", method = RequestMethod.GET)
	public Result<GameRoomPlayerJoinDTO> getGameRoomUserInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "房间号") String roomNum,
			@RequestParam @ApiParam(required = true, value = "房间类型：MIND--头脑PK，PUZZLE--拼图") GameTypeEnum typeEnum) {
		Result<GameRoomDTO> roomDTOResult = gameRoomService.getGameRoomByRoomNum(roomNum, typeEnum);
		GameRoomDTO roomDTO = roomDTOResult.getModel();
		if (roomDTO == null) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}

		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result<UserDTO> userDTOResult = memberService.getMemberByNum(userNum);
		GameAccountParam accountParam = new GameAccountParam();
		accountParam.setAccount(UserUtil.getCurrentAccount(getRequest()));
		accountParam.setUserNum(userNum);
		accountParam.setType(roomDTO.getTypeEnum());
		Result<GameAccountDTO> accountDTOResult = gameAccountService.getAccountInfo(accountParam);
		Result<PropertyPointDTO> pointDTOResult = propertyInfoService.getPropertyPoint(userNum);

		GameRoomPlayerJoinDTO joinDTO = new GameRoomPlayerJoinDTO();
		joinDTO.setRoomNum(roomDTO.getRoomNum());
		joinDTO.setUserNum(userNum);
		joinDTO.setIsHomeowner(false);
		if (roomDTO.getUserNum().equals(userNum)) {
			joinDTO.setIsHomeowner(true);
		}
		joinDTO.setHeadImg(userDTOResult.getModel().getHeadimg());
		joinDTO.setNickName(userDTOResult.getModel().getNickname());
		joinDTO.setPoint(roomDTO.getPoint());
		joinDTO.setAccountPoint(pointDTOResult.getModel().getPoint());
		joinDTO.setStarCount(accountDTOResult.getModel().getStarCount());
		joinDTO.setHardLevelEnum(roomDTO.getHardLevelEnum());
		return successGet(joinDTO);
	}

}
