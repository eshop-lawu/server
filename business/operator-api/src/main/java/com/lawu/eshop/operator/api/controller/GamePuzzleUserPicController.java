package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.dto.GamePuzzleUserPicOperatorDTO;
import com.lawu.eshop.game.query.OperatorGamePuzzleUserPicQuery;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.GamePuzzleUserPicService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/3/10.
 */
@Api(tags = "gamePuzzleUserPic")
@RestController
@RequestMapping(value = "gamePuzzleUserPic/")
public class GamePuzzleUserPicController extends BaseController {

    @Autowired
    private GamePuzzleUserPicService gamePuzzleUserPicService;

    @ApiOperation(value = "用户供图列表", notes = "用户供图列表。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("game_puzzle_user:list")
    @PageBody
    @RequestMapping(value = "listOperatorGamePuzzleUserPic", method = RequestMethod.POST)
    public Result<Page<GamePuzzleUserPicOperatorDTO>> listOperatorGamePuzzleUserPic(@RequestBody @ApiParam OperatorGamePuzzleUserPicQuery query) {
        return gamePuzzleUserPicService.listOperatorGamePuzzleUserPic(query);
    }

    @ApiOperation(value = "审核拼图", notes = "审核拼图。（梅述全）", httpMethod = "PUT")
    @LogRecord(type = OperationTypeEnum.UPDATE, title = LogTitleEnum.GAME_PUZZLE_USER_AUDIT, idParamName = "id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("game_puzzle_user:audit")
    @RequestMapping(value = "auditGamePuzzleUserPic/{id}", method = RequestMethod.PUT)
    public Result auditGamePuzzleUserPic(@PathVariable @ApiParam(name = "id", required = true, value = "ID") Long id,
                                         @RequestParam @ApiParam(name = "id", required = true, value = "状态") GamePuzzleUserPicStatusEnum statusEnum) {
        return gamePuzzleUserPicService.updateGamePuzzleUserPicStatus(id, statusEnum);
    }

}
