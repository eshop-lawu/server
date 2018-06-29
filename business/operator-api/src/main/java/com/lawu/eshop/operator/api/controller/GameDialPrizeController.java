package com.lawu.eshop.operator.api.controller;

import java.math.BigDecimal;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.game.constants.GameDialPrizeStatusEnum;
import com.lawu.eshop.game.dto.GameDialPrizeAttendDTO;
import com.lawu.eshop.game.param.GameDailPrizeParam;
import com.lawu.eshop.game.query.GameDailPrizeQuery;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.GameDialPrizeService;
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
 * 转盘游戏奖品管理
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月16日
 */
@Api(tags = "gameDialPrize")
@RestController
@RequestMapping(value = "gameDialPrize/")
public class GameDialPrizeController extends BaseController{
	
	@Autowired
	private GameDialPrizeService gameDialPrizeService;
	
	@LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.GAME_DIAL_PRAIZE_ADD)
    @ApiOperation(value = "新增奖品", notes = "新增奖品（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDialPrize:save")
    @RequestMapping(value = {"saveGameDialPrize"}, method = RequestMethod.POST)
	public Result saveGameDialPrize(@RequestBody @ApiParam GameDailPrizeParam param ,BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		Result result = gameDialPrizeService.updateGameDialPrize(param);
		
		return successCreated(result);
	}
	
	@LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.GAME_DIAL_PRAIZE_UPDATE) 
    @ApiOperation(value = "修改奖品", notes = "修改奖品（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDialPrize:update")
    @RequestMapping(value = {"updateGameDialPrize"}, method = RequestMethod.POST)
	public Result updateGameDialPrize(@RequestBody @ApiParam GameDailPrizeParam param ,BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		Result<BigDecimal> leftPrizeRateResult = gameDialPrizeService.getLeftPrizeRate(param.getGameDialId());
        BigDecimal leftPrizeRate = leftPrizeRateResult.getModel();
        if (param.getId() != null && param.getId() > 0) {
            Result<GameDialPrizeAttendDTO> result = gameDialPrizeService.getGameDialPrize(param.getId());
            leftPrizeRate = leftPrizeRate.add(result.getModel().getRate());
        }
        if (param.getRate().compareTo(leftPrizeRate) == 1) {
            return successCreated(ResultCode.FAIL, "奖品中奖概率大于100%");
        }
		Result result = gameDialPrizeService.updateGameDialPrize(param);
		
		return successCreated(result);
	}
	
	
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "查询奖品详情", notes = "查询奖品详情（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDialPrize:get")
    @RequestMapping(value = {"getGameDialPrize/{id}"}, method = RequestMethod.GET)
	public  Result<GameDialPrizeAttendDTO> getGameDialPrize(@PathVariable @ApiParam(required = true, value = "id") Long id) {
    	Result<GameDialPrizeAttendDTO>  result = gameDialPrizeService.getGameDialPrize(id);
		return successGet(result);
	}
    
    
    @SuppressWarnings("unchecked")
    @PageBody
	@ApiOperation(value = "查询奖品列表", notes = "查询奖品列表（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDialPrize:list")
    @RequestMapping(value = {"page"}, method = RequestMethod.POST)
	public  Result<Page<GameDialPrizeAttendDTO>> page(@RequestBody GameDailPrizeQuery query) {
    	Result<Page<GameDialPrizeAttendDTO>>  result = gameDialPrizeService.page(query);
		return successCreated(result);
	}
    
    
    @ApiOperation(value = "查询奖品剩余中奖率", notes = "查询奖品剩余中奖率。（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getLeftPrizeRate/{gameDialId}", method = RequestMethod.GET)
    public Result<BigDecimal> getLeftPrizeRate(@PathVariable @ApiParam(name = "gameDialPrizeId", required = true, value = "活动ID") Long gameDialId) {
        return gameDialPrizeService.getLeftPrizeRate(gameDialId);
    }
    
    
    @ApiOperation(value = "删除奖品", notes = "删除奖品。（张荣成）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.DELETE ,title = LogTitleEnum.GAME_DIAL_PRAIZE_DEL) 
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDialPrize:del")
    @RequestMapping(value = "delGameDialPrize/{id}", method = RequestMethod.PUT)
    public Result delGameDialPrize(@PathVariable @ApiParam(name = "id", required = true, value = "奖品ID") Long id) {
        return gameDialPrizeService.setGameDialPrizeStatus(id, GameDialPrizeStatusEnum.INVALID);
    }

}
