package com.lawu.eshop.operator.api.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDTO;
import com.lawu.eshop.activity.param.DrawLotteryActivityParam;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityQuery;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.DrawLotteryActivityPrizeService;
import com.lawu.eshop.operator.api.service.DrawLotteryActivityService;
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
 * @date 2018/1/16.
 */
@Api(tags = "drawLotteryActivity")
@RestController
@RequestMapping(value = "drawLotteryActivity/")
public class DrawLotteryActivityController extends BaseController {

    @Autowired
    private DrawLotteryActivityService drawLotteryActivityService;

    @Autowired
    private DrawLotteryActivityPrizeService drawLotteryActivityPrizeService;

    @ApiOperation(value = "抽奖活动列表", notes = "抽奖活动列表。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("draw_lottery:list")
    @PageBody
    @RequestMapping(value = "listOperatorDrawLotteryActivity", method = RequestMethod.POST)
    public Result<Page<DrawLotteryActivityDTO>> listOperatorLotteryActivity(@RequestBody @ApiParam OperatorDrawLotteryActivityQuery query) {
        return drawLotteryActivityService.listOperatorDrawLotteryActivity(query);
    }

    @ApiOperation(value = "抽奖活动发布", notes = "抽奖活动发布。[9103]（梅述全）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.LOTTER_ACTIVITY_PUBLISG,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("draw_lottery:publish")
    @RequestMapping(value = "publishDrawLotteryActivity/{id}", method = RequestMethod.PUT)
    public Result publishDrawLotteryActivity(@PathVariable @ApiParam(name = "id", required = true, value = "活动ID") Long id) {
        Result<List<DrawLotteryActivityPrizeDTO>> prizeResult = drawLotteryActivityPrizeService.getActivityAllPrize(id);
        if (prizeResult.getModel() == null || prizeResult.getModel().isEmpty()) {
            return successCreated(ResultCode.LOTTERY_PRIZE_ERROR);
        }
        return drawLotteryActivityService.updateDrawLotteryActivityStatus(id, DrawLotteryActivityStatusEnum.PUBLISHED);
    }

    @ApiOperation(value = "抽奖活动下架", notes = "抽奖活动下架。（梅述全）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.LOTTER_ACTIVITY_DOWN,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("draw_lottery:cancel")
    @RequestMapping(value = "cancelDrawLotteryActivity/{id}", method = RequestMethod.PUT)
    public Result cancelDrawLotteryActivity(@PathVariable @ApiParam(name = "id", required = true, value = "活动ID") Long id) {
        return drawLotteryActivityService.updateDrawLotteryActivityStatus(id, DrawLotteryActivityStatusEnum.CANCEL);
    }

    @ApiOperation(value = "抽奖活动删除", notes = "抽奖活动删除。（梅述全）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.DELETE ,title = LogTitleEnum.LOTTER_ACTIVITY_DETELE,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("draw_lottery:del")
    @RequestMapping(value = "delDrawLotteryActivity/{id}", method = RequestMethod.PUT)
    public Result delDrawLotteryActivity(@PathVariable @ApiParam(name = "id", required = true, value = "活动ID") Long id) {
        return drawLotteryActivityService.updateDrawLotteryActivityStatus(id, DrawLotteryActivityStatusEnum.DELETE);
    }

    @ApiOperation(value = "根据id查询抽奖活动", notes = "根据id查询抽奖活动。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getOperatorLotteryActivity/{id}", method = RequestMethod.GET)
    public Result<DrawLotteryActivityDTO> getOperatorLotteryActivity(@PathVariable @ApiParam(name = "id", required = true, value = "活动ID") Long id) {
        return drawLotteryActivityService.getDrawLotteryActivity(id);
    }

    @ApiOperation(value = "新增抽奖活动", notes = "新增抽奖活动。（梅述全）", httpMethod = "POST")
    @LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.LOTTER_ACTIVITY_ADD)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("draw_lottery:add")
    @RequestMapping(value = "saveDrawLotteryActivity", method = RequestMethod.POST)
    public Result saveDrawLotteryActivity(@ModelAttribute DrawLotteryActivityParam param) {
        return drawLotteryActivityService.saveDrawLotteryActivity(param);
    }

}
