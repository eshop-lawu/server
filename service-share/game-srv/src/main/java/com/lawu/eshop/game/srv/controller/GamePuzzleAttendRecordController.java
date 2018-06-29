package com.lawu.eshop.game.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.cache.constants.CacheGameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.param.JoinGameCacheParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.constants.GameCacheKeyEnum;
import com.lawu.eshop.game.dto.CheckPointStatusDTO;
import com.lawu.eshop.game.dto.GameAttendSaveReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleAttendRecordDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleRankReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleResultDTO;
import com.lawu.eshop.game.dto.PuzzleCheckIsOverDTO;
import com.lawu.eshop.game.param.GamePuzzleChallengeParam;
import com.lawu.eshop.game.param.GamePuzzleCheckDeductionPointParam;
import com.lawu.eshop.game.param.GamePuzzleLoadRankingParam;
import com.lawu.eshop.game.param.GamePuzzleStandaloneParam;
import com.lawu.eshop.game.param.GamePuzzleValidSuccSrvParam;
import com.lawu.eshop.game.param.PuzzleStartParam;
import com.lawu.eshop.game.srv.bo.CheckPointStatusBo;
import com.lawu.eshop.game.srv.bo.GameAttendSaveReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleRankReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleResultBO;
import com.lawu.eshop.game.srv.bo.PuzzleCheckIsOverBO;
import com.lawu.eshop.game.srv.converter.GamePuzzleConfigConverter;
import com.lawu.eshop.game.srv.service.GameCommonCacheService;
import com.lawu.eshop.game.srv.service.GameConfigCacheService;
import com.lawu.eshop.game.srv.service.GamePuzzleAttendRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import io.swagger.annotations.ApiParam;

/**
 * @author lihj <br>
 * @date 2018/3/15
 */
@RestController
@RequestMapping(value = "gamePuzzleAttend/")
public class GamePuzzleAttendRecordController extends BaseController {

    @Autowired
    private GamePuzzleAttendRecordService gamePuzzleAttendRecordService;
    @Autowired
    private GameConfigCacheService gameConfigCacheService;
    @Autowired
    private GameCommonCacheService gameCommonCacheService;

    @RequestMapping(value = "checkDeductionPointSucc", method = RequestMethod.POST)
    public Result<CheckPointStatusDTO> checkDeductionPointSucc(@RequestBody GamePuzzleCheckDeductionPointParam checkParam) {
    	CheckPointStatusBo bo = gamePuzzleAttendRecordService.checkDeductionPointSucc(checkParam);
    	CheckPointStatusDTO dto =new CheckPointStatusDTO();
    	dto.setAllUserCheckPoint(bo.getAllUserCheckPoint());
    	dto.setWaitDepoint(bo.getWaitDepoint());
    	/*dto.setCheckPoint(bo.getCheckPoint());*/
        return successCreated(dto);
    }

    /**
     * 单机游戏loadding
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "loadingStandalone", method = RequestMethod.POST)
    public Result<GameAttendSaveReturnDTO> loadingStandalone(@RequestBody GamePuzzleStandaloneParam param) {
        GameAttendSaveReturnBO attendBO = gamePuzzleAttendRecordService.loadingStandalone(param);
        GameAttendSaveReturnDTO gameSaveReturn = GamePuzzleConfigConverter.convertGameAttendSaveReturnDTO(attendBO);
        return successCreated(gameSaveReturn);
    }

    @RequestMapping(value = "joinGameCache", method = RequestMethod.GET)
    public Result joinGameCache(@RequestParam @ApiParam(required = true, value = "用户编号") String userNum,@RequestParam @ApiParam(required = true, value = "参与类型") CacheGameTypeEnum type){
    	JoinGameCacheParam joinParam = new JoinGameCacheParam();
        joinParam.setType(type);
        joinParam.setKey(userNum);
    	return successGet(gameCommonCacheService.joinCache(joinParam));
    }
    
    /**
     * 随机loadding
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "loadingChallenge", method = RequestMethod.POST)
    public Result<GameAttendSaveReturnDTO> loadingChallenge(@RequestBody GamePuzzleChallengeParam param) {
        GameAttendSaveReturnBO attendBO = gamePuzzleAttendRecordService.loadingChallenge(param);
        GameAttendSaveReturnDTO gameSaveReturn = GamePuzzleConfigConverter.convertGameAttendSaveReturnDTO(attendBO);
        return successCreated(gameSaveReturn);
    }

    /**
     * 接收随机房主请求
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "loadingRoomMasterRequest", method = RequestMethod.POST)
    public Result<GameAttendSaveReturnDTO> loadingRoomMasterRequest(@RequestBody GamePuzzleChallengeParam param) {
        GameAttendSaveReturnBO attendBO = gamePuzzleAttendRecordService.loadingRoomMasterRequest(param);
        GameAttendSaveReturnDTO gameSaveReturn = GamePuzzleConfigConverter.convertGameAttendSaveReturnDTO(attendBO);
        return successCreated(gameSaveReturn);
    }


    /**
     * 好友开房匹配
     *
     * @return
     */
    @RequestMapping(value = "friendPuzzleGameInit", method = RequestMethod.POST)
    public Result<GameAttendSaveReturnDTO> friendPuzzleGameInit(@RequestBody PuzzleStartParam param) {
        GameAttendSaveReturnBO attendBO = gamePuzzleAttendRecordService.friendPuzzleGameInit(param);
        GameAttendSaveReturnDTO gameSaveReturn = GamePuzzleConfigConverter.convertGameAttendSaveReturnDTO(attendBO);
        return successCreated(gameSaveReturn);
    }
    
	
	@RequestMapping(value = "getPicByHardLevel", method = RequestMethod.GET)
	public Result<GamePuzzleGetPicReturnDTO> getPicByHardLevel(@RequestParam @ApiParam(required = true, value = "用户编号") String userNum,@RequestParam @ApiParam(required = true, value = "房间参与编号") String attendNum) {
		String jsonObject = gameConfigCacheService.getRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_JSON + attendNum).getModel();
        List<GamePuzzleGetPicDTO> dtoCache = JSON.parseArray(jsonObject, GamePuzzleGetPicDTO.class);// 缓存数据
		GamePuzzleGetPicReturnDTO returnDTO = GamePuzzleConfigConverter.convertGamePuzzleGetPicReturnDTO(dtoCache);
		return successGet(returnDTO);
	}

	/**
	 * 判断拼图是否正确
	 * @param srvParam
	 * @return
	 */
    @RequestMapping(value = "checkPuzzlePicStatus", method = RequestMethod.POST)
    public Result<GamePuzzleResultDTO> checkPuzzlePicStatus(@RequestBody GamePuzzleValidSuccSrvParam srvParam) {
        GamePuzzleResultBO gameResultBo = gamePuzzleAttendRecordService.checkPuzzlePicStatus(srvParam);
        if(gameResultBo.getResultCode()!= ResultCode.SUCCESS){
            return successCreated(gameResultBo.getResultCode());
        }
        GamePuzzleResultDTO dto = GamePuzzleConfigConverter.convertGamePuzzleResultDTO(gameResultBo);
        return successCreated(dto);
    }

    /**
     * 获取自己的排名
     * @param param
     * @return
     */
    @RequestMapping(value="getMyGameRank",method=RequestMethod.POST)
    public Result<List<GamePuzzleRankReturnDTO>> getMyGameRank(@RequestBody GamePuzzleLoadRankingParam param){
    	List<GamePuzzleRankReturnBO> game = gamePuzzleAttendRecordService.getMyGameRank(param);
    	if(null ==game){
    		return successCreated(ResultCode.NOT_FOUND_DATA);
    	}
    	List<GamePuzzleRankReturnDTO> returnDTO = GamePuzzleConfigConverter.convertGamePuzzleConfigConverter(game);
    	return successCreated(returnDTO);
    }
    
    /**
     * 检查图片是否拼完
     * @param userNum
     * @param attendNum
     * @return
     */
    @RequestMapping(value = "checkPuzzleIsOver", method = RequestMethod.GET)
	public Result<PuzzleCheckIsOverDTO> checkPuzzleIsOver(@RequestParam("userNum") String userNum,@RequestParam("attendNum") String attendNum){
    	PuzzleCheckIsOverBO puzzle = gamePuzzleAttendRecordService.checkPuzzleIsOver(userNum,attendNum);
    	PuzzleCheckIsOverDTO dto =GamePuzzleConfigConverter.convertPuzzleCheckIsOverDTO(puzzle);
    	return successGet(dto);
    }
    
    @RequestMapping(value = "quitRoom", method = RequestMethod.PUT)
    public Result<GameRoomDetailsDTO> quitRoom(@RequestParam("userNum") String userNum, @RequestParam("groupNum") String groupNum){
    	return successCreated(gamePuzzleAttendRecordService.quitRoom(userNum,groupNum));
    }

    @RequestMapping(value = "removeStartCacheData", method = RequestMethod.POST)
   public Result removeStartCacheData(@RequestBody GamePuzzleValidSuccSrvParam validParam){
        gameConfigCacheService.removeStartCacheData(validParam.getAttendNum(),validParam.getUserNum());
        return successCreated();
    }

    @RequestMapping(value = "removeMyPuzzleCacheData", method = RequestMethod.GET)
    public Result removeMyPuzzleCacheData(@RequestParam("userNum") String userNum){
        gameConfigCacheService.removeMyPuzzleCacheData(userNum);
        return successGet();
    }
    
    /**
     * 根据用户编号获取参与编号
     * @param userNum
     * @return
     */
    @RequestMapping(value = "exceptionExitExecute", method = RequestMethod.GET)
    public Result<String> exceptionExitExecute(@RequestParam("userNum") String userNum){
    	return successGet(gamePuzzleAttendRecordService.exceptionExitExecute(userNum));
    }
    @RequestMapping(value = "getPuzzleAttendRecordNearlyData", method = RequestMethod.GET)
    public Result<GamePuzzleAttendRecordDTO> getPuzzleAttendRecordNearlyData(@RequestParam("userNum") String userNum){
        GamePuzzleAttendRecordBO bo = gamePuzzleAttendRecordService.getPuzzleAttendRecordNearlyData(userNum);
        if(null == bo){
            return successGet();
        }
        GamePuzzleAttendRecordDTO dto =GamePuzzleConfigConverter.convertGamePuzzleAttendRecordDTO(bo);
        return successGet(dto);
    }

    /**
     * 根据参与编号查询参与游戏的用户编号(完成动力任务)
     *
     * @param attendNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getAttendRecordUserNums", method = RequestMethod.GET)
    public Result<List<String>> getAttendRecordUserNums(@RequestParam String attendNum) {
        List<String> userNumList = gamePuzzleAttendRecordService.getAttendRecordUserNums(attendNum);
        return successGet(userNumList);
    }
    
}
