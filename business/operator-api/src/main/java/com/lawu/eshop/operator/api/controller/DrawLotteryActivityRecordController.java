package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordOperatorDTO;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityRecordQuery;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.DrawLotteryActivityRecordService;
import com.lawu.eshop.operator.api.service.MemberService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.user.dto.MemberDTO;
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
 * @date 2018/1/17.
 */
@Api(tags = "drawLotteryActivityRecord")
@RestController
@RequestMapping(value = "drawLotteryActivityRecord/")
public class DrawLotteryActivityRecordController extends BaseController {

    @Autowired
    private DrawLotteryActivityRecordService drawLotteryActivityRecordService;

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "抽奖记录列表", notes = "抽奖记录列表。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("draw_record:list")
    @PageBody
    @RequestMapping(value = "listOperatorDrawLotteryActivityPrize", method = RequestMethod.POST)
    public Result<Page<DrawLotteryActivityRecordOperatorDTO>> listOperatorDrawLotteryActivityPrize(@RequestBody @ApiParam OperatorDrawLotteryActivityRecordQuery query) {
        Result<Page<DrawLotteryActivityRecordOperatorDTO>> result = drawLotteryActivityRecordService.listOperatorDrawLotteryActivityRecord(query);
        for (DrawLotteryActivityRecordOperatorDTO operatorDTO : result.getModel().getRecords()) {
            Result<MemberDTO> memberResult = memberService.getMemberByAccount(operatorDTO.getUserAccount());
            if (isSuccess(memberResult)) {
                operatorDTO.setNickName(memberResult.getModel().getNickname());
            }
        }
        return successCreated(result);
    }

    @ApiOperation(value = "发放奖品", notes = "发放奖品。（梅述全）", httpMethod = "PUT")
    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.LOTTER_ACTIVITY_PRIZE_SEND)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("draw_prize:send")
    @RequestMapping(value = "sendPrize/{id}", method = RequestMethod.PUT)
    public Result sendPrize(@PathVariable Long id , @RequestParam @ApiParam(required = true, value = "物流单号") String expressNum) {
        drawLotteryActivityRecordService.sendPrize(id,expressNum);
        return successCreated();
    }

}
