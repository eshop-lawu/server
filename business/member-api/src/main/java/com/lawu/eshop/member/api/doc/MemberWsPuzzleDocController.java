package com.lawu.eshop.member.api.doc;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.dto.GameAttendSaveReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicReturnDTO;
import com.lawu.eshop.game.dto.MindPkReadyDTO;
import com.lawu.eshop.game.dto.PuzzlePKRejectDTO;
import com.lawu.eshop.game.dto.PuzzlePkAbandonDTO;
import com.lawu.eshop.game.param.MindPkOnlineParam;
import com.lawu.eshop.game.param.MindPkReadyParam;
import com.lawu.eshop.game.param.PuzzlePKRejectParam;
import com.lawu.eshop.game.param.PuzzlePKStartParam;
import com.lawu.eshop.game.param.PuzzlePkAbandonParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author Leach
 * @date 2018/3/16
 */
@Api(tags = "memberWsPuzzleDoc")
@RestController
@RequestMapping(value = "memberWsPuzzleDoc/")
public class MemberWsPuzzleDocController extends BaseController {

    @Audit(date = "2018-03-16", reviewer = "孙林青")
    @ApiOperation(value = "websocket-上线", notes = "上线请求-ONLINE [11000]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "online", method = RequestMethod.GET)
    public Result<List<GameRoomPlayerInfoDTO>> online(@ModelAttribute MindPkOnlineParam content) {
        return successGet(ResultCode.GAME_WS_USER_ONLINE);
    }

    @Audit(date = "2018-03-17", reviewer = "孙林青")
    @ApiOperation(value = "websocket-准备", notes = "准备请求-READY [11001]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "ready", method = RequestMethod.POST)
    public Result<MindPkReadyDTO> ready(@RequestBody MindPkReadyParam content) {
        return successGet(ResultCode.GAME_WS_USER_READY);
    }

    @Audit(date = "2018-03-17", reviewer = "孙林青")
    @ApiOperation(value = "websocket-踢人", notes = "踢人请求-REJECT [11008]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "reject", method = RequestMethod.POST)
    public Result<PuzzlePKRejectDTO> reject(@RequestBody PuzzlePKRejectParam content) {
        return successGet(ResultCode.GAME_WS_USER_OFFLINE);
    }

    @Audit(date = "2018-03-19", reviewer = "孙林青")
    @ApiOperation(value = "websocket-开始", notes = "开始请求-START [11002]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "start", method = RequestMethod.POST)
    public Result<GameAttendSaveReturnDTO> start(@RequestBody PuzzlePKStartParam content) {
        return successGet(ResultCode.GAME_WS_USER_START);
    }

    @Audit(date = "2018-03-19", reviewer = "孙林青")
    @ApiOperation(value = "websocket-检查", notes = "检查请求-CHECK [11004|11003]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public Result<GamePuzzleGetPicReturnDTO> check() {
        return successGet(ResultCode.GAME_WS_USER_CHECK_DONE);
    }

   
    @Audit(date = "2018-03-17", reviewer = "孙林青")
    @ApiOperation(value = "websocket-放弃", notes = "放弃请求-ABANDON [11008]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "abandon", method = RequestMethod.POST)
    public Result<PuzzlePkAbandonDTO> abandon(@RequestBody PuzzlePkAbandonParam content) {
        return successGet(ResultCode.GAME_WS_USER_OFFLINE);
    }

    @Audit(date = "2018-03-20", reviewer = "孙林青")
    @ApiOperation(value = "websocket-离线", notes = "离线请求-OFFLINE [11008]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "offline", method = RequestMethod.POST)
    public Result<PuzzlePkAbandonDTO> offline(@RequestParam String userNum) {
        return successGet(ResultCode.GAME_WS_USER_OFFLINE);
    }



}
