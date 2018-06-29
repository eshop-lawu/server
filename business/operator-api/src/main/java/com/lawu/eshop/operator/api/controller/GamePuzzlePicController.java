package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.dto.GamePuzzlePicOperatorDTO;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.OperatorGamePuzzlePicQuery;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.GamePuzzlePicService;
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
@Api(tags = "gamePuzzlePic")
@RestController
@RequestMapping(value = "gamePuzzlePic/")
public class GamePuzzlePicController extends BaseController {

    @Autowired
    private GamePuzzlePicService gamePuzzlePicService;

    @ApiOperation(value = "拼图列表", notes = "拼图列表。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("game_puzzle:list")
    @PageBody
    @RequestMapping(value = "listOperatorGamePuzzlePic", method = RequestMethod.POST)
    public Result<Page<GamePuzzlePicOperatorDTO>> listOperatorGamePuzzlePic(@RequestBody @ApiParam OperatorGamePuzzlePicQuery query) {
        return gamePuzzlePicService.listOperatorGamePuzzlePic(query);
    }

    @ApiOperation(value = "根据id查询拼图", notes = "根据id查询拼图。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getGamePuzzlePic/{id}", method = RequestMethod.GET)
    public Result<GamePuzzlePicOperatorDTO> getGamePuzzlePic(@PathVariable @ApiParam(name = "id", required = true, value = "ID") Long id) {
        return gamePuzzlePicService.getGamePuzzlePic(id);
    }

    @ApiOperation(value = "启用拼图", notes = "启用拼图。（梅述全）", httpMethod = "PUT")
    @LogRecord(type = OperationTypeEnum.UPDATE, title = LogTitleEnum.GAME_PUZZLE_ENABLE, idParamName = "id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("game_puzzle:enable")
    @RequestMapping(value = "enableGamePuzzlePic/{id}", method = RequestMethod.PUT)
    public Result enableGamePuzzlePic(@PathVariable @ApiParam(name = "id", required = true, value = "ID") Long id) {
        return gamePuzzlePicService.updateGamePuzzlePicStatus(id, GamePuzzlePicStatusEnum.ENABLED);
    }

    @ApiOperation(value = "禁用拼图", notes = "禁用拼图。（梅述全）", httpMethod = "PUT")
    @LogRecord(type = OperationTypeEnum.UPDATE, title = LogTitleEnum.GAME_PUZZLE_DISABLE, idParamName = "id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("game_puzzle:disable")
    @RequestMapping(value = "disableGamePuzzlePic/{id}", method = RequestMethod.PUT)
    public Result disableGamePuzzlePic(@PathVariable @ApiParam(name = "id", required = true, value = "ID") Long id) {
        return gamePuzzlePicService.updateGamePuzzlePicStatus(id, GamePuzzlePicStatusEnum.DISABLED);
    }

    @ApiOperation(value = "新增拼图", notes = "新增拼图。（梅述全）", httpMethod = "POST")
    @LogRecord(type = OperationTypeEnum.ADD, title = LogTitleEnum.GAME_PUZZLE_ADD)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("game_puzzle:add")
    @RequestMapping(value = "saveGamePuzzlePic", method = RequestMethod.POST)
    public Result saveGamePuzzlePic(@ModelAttribute GamePuzzleParam param) {
        return gamePuzzlePicService.saveGamePuzzlePic(param);
    }

}
