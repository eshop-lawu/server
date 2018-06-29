package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.cache.constants.CacheGameTypeEnum;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.dto.GameCommonNumDTO;
import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.JoinGameCacheParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.game.dto.FindMatchStateDTO;
import com.lawu.eshop.member.api.service.GameCommonCacheService;
import com.lawu.eshop.member.api.service.GameConfigCacheService;
import com.lawu.eshop.member.api.service.GameMindAttendRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 头脑PK参与控制器
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
@Api(tags = "gameMindAttend")
@RestController
@RequestMapping(value = "gameMindAttend/")
public class GameMindAttendController extends BaseController {

    @Autowired
    private GameCommonCacheService gameCommonCacheService;

    @Autowired
    private GameConfigCacheService gameConfigCacheService;
    
    @Autowired
    private GameMindAttendRecordService gameMindAttendRecordService;
    
    @Audit(date = "2018-03-15", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @Authorization
    @ApiOperation(value = "随机匹配", notes = "随机匹配（蒋鑫俊）[]",httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "randomMatch", method = RequestMethod.POST)
    public Result randomMatch(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Result<GameMindConfigDTO> configDTOResult = gameConfigCacheService.getGameMindConfig();
        if (configDTOResult == null || configDTOResult.getModel() == null
                || GameConfigStatusEnum.DISABLE == configDTOResult.getModel().getStatusEnum()) {
            return successCreated(ResultCode.GAME_SETTING_DISABLE, configDTOResult.getModel().getForbiddenRemark());
        }
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        JoinGameCacheParam param = new JoinGameCacheParam();
        param.setType(CacheGameTypeEnum.MIND);
        param.setKey(userNum);
        return successCreated(gameCommonCacheService.joinCache(param));
    }

    @Audit(date = "2018-03-15", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @Authorization
    @ApiOperation(value = "查询匹配状态", notes = "查询匹配状态（蒋鑫俊）[]",httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "findMatchState", method = RequestMethod.GET)
    public Result<FindMatchStateDTO> findMatchState(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        CheckCacheMatchParam param = new CheckCacheMatchParam();
        param.setType(CacheGameTypeEnum.MIND);
        param.setKey(userNum);
        Result<GameMatchResultDTO> checkCacheMatchEatchotherResult = gameMindAttendRecordService.checkMatch(param);
        if (!isSuccess(checkCacheMatchEatchotherResult)) {
            return successGet(checkCacheMatchEatchotherResult);
        }
        GameMatchResultDTO checkCacheMatchEatchotherModel = checkCacheMatchEatchotherResult.getModel();
        // 如果返回的参与编号为空, 说明还未匹配到相关的对手
        if (checkCacheMatchEatchotherModel.getAttendNum() == null) {
            return successGet(ResultCode.GAME_MATCH_LOADING);
        }
        FindMatchStateDTO model = new FindMatchStateDTO();
        model.setAttendNum(checkCacheMatchEatchotherModel.getAttendNum());
        if (checkCacheMatchEatchotherModel.getCommonInfo() != null) {
            for (GameCommonNumDTO item : checkCacheMatchEatchotherModel.getCommonInfo()) {
                if (userNum.equals(item.getUserNum())) {
                    model.setRoomMaster(item.isRoomMaster());
                }
            }
        }
        return successGet(model);
    }
}
