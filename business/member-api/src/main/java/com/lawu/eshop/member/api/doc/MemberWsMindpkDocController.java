package com.lawu.eshop.member.api.doc;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.dto.MindPkSubmitDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.dto.MindPkAbandonDTO;
import com.lawu.eshop.game.dto.MindPkCheckDTO;
import com.lawu.eshop.game.dto.MindPkOnlineDTO;
import com.lawu.eshop.game.dto.MindPkReadyDTO;
import com.lawu.eshop.game.dto.MindPkStartDTO;
import com.lawu.eshop.game.dto.MindPkSubmitSingleDTO;
import com.lawu.eshop.game.param.MindPkAbandonParam;
import com.lawu.eshop.game.param.MindPkOnlineParam;
import com.lawu.eshop.game.param.MindPkReadyParam;
import com.lawu.eshop.game.param.MindPkRejectParam;
import com.lawu.eshop.game.param.MindPkStartParam;
import com.lawu.eshop.game.param.MindPkSubmitParam;
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
@Api(tags = "memberWsMindpkDoc")
@RestController
@RequestMapping(value = "memberWsMindpkDoc/")
public class MemberWsMindpkDocController extends BaseController {

    @Audit(date = "2018-03-16", reviewer = "孙林青")
    @ApiOperation(value = "websocket-上线", notes = "上线请求-ONLINE [11000]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "online", method = RequestMethod.POST)
    public Result<MindPkOnlineDTO> online(@RequestBody MindPkOnlineParam content) {
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
    public Result<MindPkAbandonDTO> reject(@RequestBody MindPkRejectParam content) {
        return successGet(ResultCode.GAME_WS_USER_OFFLINE);
    }
    
    @Audit(date = "2018-03-19", reviewer = "孙林青")
    @ApiOperation(value = "websocket-开始", notes = "开始请求-START [11002]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "start", method = RequestMethod.POST)
    public Result<MindPkStartDTO> start(@RequestBody MindPkStartParam content) {
        return successGet(ResultCode.GAME_WS_USER_START);
    }
    
    @Audit(date = "2018-03-19", reviewer = "孙林青")
    @ApiOperation(value = "websocket-检查", notes = "检查请求-CHECK [11004|11003]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public Result<MindPkCheckDTO> check() {
        return successGet(ResultCode.GAME_WS_USER_CHECK_DONE);
    }
    
    @Audit(date = "2018-03-17", reviewer = "孙林青")
    @ApiOperation(value = "websocket-提交", notes = "提交请求(单人)-SUBMIT [11005]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "submitSingle", method = RequestMethod.POST)
    public Result<MindPkSubmitSingleDTO> submitSingle(@RequestBody MindPkSubmitParam content) {
        return successGet(ResultCode.GAME_WS_USER_SUBMIT_SINGLE);
    }
    
    @Audit(date = "2018-03-17", reviewer = "孙林青")
    @ApiOperation(value = "websocket-提交", notes = "提交请求(多人)-SUBMIT [11006|11007|11009]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "submitMulti", method = RequestMethod.POST)
    public Result<MindPkSubmitDTO> submitMulti(@RequestBody MindPkSubmitParam content) {
        return successGet(ResultCode.GAME_WS_USER_SUBMIT_OTHER);
    }
    
    @Audit(date = "2018-03-17", reviewer = "孙林青")
    @ApiOperation(value = "websocket-超时", notes = "超时请求(单人)-TIMEOUT [11005]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "timeoutSingle", method = RequestMethod.POST)
    public Result<MindPkSubmitSingleDTO> timeoutSingle(@RequestBody MindPkSubmitParam content) {
        return successGet(ResultCode.GAME_WS_USER_SUBMIT_SINGLE);
    }
    
    @Audit(date = "2018-03-17", reviewer = "孙林青")
    @ApiOperation(value = "websocket-超时", notes = "超时请求(多人)-TIMEOUT [11006|11007|11009]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "timeoutMulti", method = RequestMethod.POST)
    public Result<MindPkSubmitDTO> timeoutMulti(@RequestBody MindPkSubmitParam content) {
        return successGet(ResultCode.GAME_WS_USER_SUBMIT_OTHER);
    }
    
    @Audit(date = "2018-03-17", reviewer = "孙林青")
    @ApiOperation(value = "websocket-放弃", notes = "放弃请求-ABANDON [11008]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "abandon", method = RequestMethod.POST)
    public Result<MindPkAbandonDTO> abandon(@RequestBody MindPkAbandonParam content) {
        return successGet(ResultCode.GAME_WS_USER_OFFLINE);
    }
    
    @Audit(date = "2018-03-20", reviewer = "孙林青")
    @ApiOperation(value = "websocket-离线", notes = "离线请求-OFFLINE [11008]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "offline", method = RequestMethod.POST)
    public Result<MindPkAbandonDTO> offline(@RequestBody MindPkAbandonParam content) {
        return successGet(ResultCode.GAME_WS_USER_OFFLINE);
    }
}
