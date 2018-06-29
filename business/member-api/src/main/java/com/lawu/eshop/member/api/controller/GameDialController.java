package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.game.dto.GameDialDetailDTO;
import com.lawu.eshop.member.api.service.GameDialService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
@Api(tags = "gameDial")
@RestController
@RequestMapping(value = "gameDial/")
public class GameDialController extends BaseController {

    @Autowired
    private GameDialService gameDialService;

    @Audit(date = "2018-03-17", reviewer = "杨清华")
    @ApiOperation(value = "转盘游戏详情", notes = "转盘游戏详情。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getGameDialDetail", method = RequestMethod.GET)
    public Result<GameDialDetailDTO> getGameDialDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Result<GameDialDetailDTO> result = gameDialService.getGameDialDetail();
        return successGet(result);
    }

}
