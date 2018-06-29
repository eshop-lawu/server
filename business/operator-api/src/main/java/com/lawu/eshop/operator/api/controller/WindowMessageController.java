package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.PointLotteryActivityOperatorDTO;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.constants.WindowMessageStatusEnum;
import com.lawu.eshop.mall.dto.WindowMessageOperatorDTO;
import com.lawu.eshop.mall.param.WindowMessageParam;
import com.lawu.eshop.mall.query.OperatorWindowMessageQuery;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.PointLotteryActivityService;
import com.lawu.eshop.operator.api.service.WindowMessageService;
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
 * @date 2018/3/1.
 */
@Api(tags = "windowMessage")
@RestController
@RequestMapping(value = "windowMessage/")
public class WindowMessageController extends BaseController {

    @Autowired
    private WindowMessageService windowMessageService;

    @Autowired
    private PointLotteryActivityService pointLotteryActivityService;

    @ApiOperation(value = "弹窗消息列表", notes = "弹窗消息列表。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("window_message:list")
    @PageBody
    @RequestMapping(value = "listOperatorWindowMessage", method = RequestMethod.POST)
    public Result<Page<WindowMessageOperatorDTO>> listOperatorWindowMessage(@RequestBody @ApiParam OperatorWindowMessageQuery query) {
        Result<Page<WindowMessageOperatorDTO>> result = windowMessageService.listOperatorWindowMessage(query);
        for (WindowMessageOperatorDTO operatorDTO : result.getModel().getRecords()) {
            Result<PointLotteryActivityOperatorDTO> operatorDTOResult = pointLotteryActivityService.getPointLotteryActivity(operatorDTO.getRelateId());
            operatorDTO.setRelateName(operatorDTOResult.getModel().getPrizeName());
        }
        return successCreated(result);
    }

    @ApiOperation(value = "根据id查询弹窗消息", notes = "根据id查询弹窗消息。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "getWindowMessage/{id}", method = RequestMethod.GET)
    public Result<WindowMessageOperatorDTO> getWindowMessage(@PathVariable @ApiParam(name = "id", required = true, value = "ID") Long id) {
        Result<WindowMessageOperatorDTO> result = windowMessageService.getWindowMessage(id);
        WindowMessageOperatorDTO operatorDTO = result.getModel();
        Result<PointLotteryActivityOperatorDTO> operatorDTOResult = pointLotteryActivityService.getPointLotteryActivity(operatorDTO.getRelateId());
        operatorDTO.setRelateName(operatorDTOResult.getModel().getPrizeName());
        return successGet(result);
    }

    @ApiOperation(value = "添加弹窗消息", notes = "添加弹窗消息。（梅述全）", httpMethod = "POST")
    @LogRecord(type = OperationTypeEnum.ADD, title = LogTitleEnum.WINDOW_MESSAGE_ADD)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("window_message:add")
    @RequestMapping(value = "saveWindowMessage", method = RequestMethod.POST)
    public Result saveWindowMessage(@ModelAttribute WindowMessageParam param) {
        return windowMessageService.saveWindowMessage(param);
    }

    @ApiOperation(value = "更新弹窗消息状态", notes = "更新弹窗消息状态。（梅述全）", httpMethod = "PUT")
    @LogRecord(type = OperationTypeEnum.UPDATE, title = LogTitleEnum.WINDOW_MESSAGE_UPDATE)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("window_message:update")
    @RequestMapping(value = "updateWindowMessageStatus/{id}", method = RequestMethod.PUT)
    public Result updateWindowMessageStatus(@PathVariable @ApiParam(name = "id", required = true, value = "ID") Long id,
                                            @RequestParam @ApiParam(name = "statusEnum", required = true, value = "状态") WindowMessageStatusEnum statusEnum) {
        return windowMessageService.updateWindowMessageStatus(id, statusEnum);
    }

}
