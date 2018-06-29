package com.lawu.eshop.operator.api.controller;

import java.math.BigDecimal;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDetailDTO;
import com.lawu.eshop.activity.param.DrawLotteryActivityPrizeParam;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityPrizeQuery;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.DrawLotteryActivityPrizeService;
import com.lawu.eshop.operator.api.service.MerchantStoreService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
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
@Api(tags = "drawLotteryActivityPrize")
@RestController
@RequestMapping(value = "drawLotteryActivityPrize/")
public class DrawLotteryActivityPrizeController extends BaseController {

    @Autowired
    private DrawLotteryActivityPrizeService drawLotteryActivityPrizeService;

    @Autowired
    private MerchantStoreService merchantStoreService;

    @ApiOperation(value = "奖品列表", notes = "奖品列表。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("draw_prize:list")
    @PageBody
    @RequestMapping(value = "listOperatorDrawLotteryActivityPrize", method = RequestMethod.POST)
    public Result<Page<DrawLotteryActivityPrizeDTO>> listOperatorDrawLotteryActivityPrize(@RequestBody @ApiParam OperatorDrawLotteryActivityPrizeQuery query) {
        Result<Page<DrawLotteryActivityPrizeDTO>> result = drawLotteryActivityPrizeService.listOperatorDrawLotteryActivityPrize(query);
        for (DrawLotteryActivityPrizeDTO prizeDTO : result.getModel().getRecords()) {
            Result<MerchantStoreDTO> storeDTOResult = merchantStoreService.getMerchantStoreById(prizeDTO.getMerchantStoreId());
            if (isSuccess(storeDTOResult)) {
                prizeDTO.setStoreName(storeDTOResult.getModel().getName());
            }
        }
        return successCreated(result);
    }

    @ApiOperation(value = "删除奖品", notes = "删除奖品。（梅述全）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.DELETE ,title = LogTitleEnum.LOTTER_ACTIVITY_PRIZE_DETELE,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("draw_prize:del")
    @RequestMapping(value = "delDrawLotteryActivityPrize/{id}", method = RequestMethod.PUT)
    public Result delDrawLotteryActivityPrize(@PathVariable @ApiParam(name = "id", required = true, value = "奖品ID") Long id) {
        return drawLotteryActivityPrizeService.updateDrawLotteryActivityPrizeStatus(id, DrawLotteryActivityPrizeStatusEnum.INVALID);
    }

    @ApiOperation(value = "新增奖品", notes = "新增奖品。（梅述全）", httpMethod = "POST")
    @LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.LOTTER_ACTIVITY_PRIZE_ADD)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("draw_prize:add")
    @RequestMapping(value = "saveDrawLotteryActivityPrize", method = RequestMethod.POST)
    public Result saveDrawLotteryActivityPrize(@ModelAttribute DrawLotteryActivityPrizeParam param) {
        Result<BigDecimal> leftPrizeRateResult = drawLotteryActivityPrizeService.getLeftPrizeRate(param.getDrawLotteryActivityId());
        BigDecimal leftPrizeRate = leftPrizeRateResult.getModel();
        if (param.getId() != null && param.getId() > 0) {
            Result<DrawLotteryActivityPrizeDetailDTO> result = drawLotteryActivityPrizeService.getPrizeDetail(param.getId());
            leftPrizeRate = leftPrizeRate.add(result.getModel().getRate());
        }
        if (param.getRate().compareTo(leftPrizeRate) == 1) {
            return successCreated(ResultCode.FAIL, "奖品中奖概率大于100%");
        }
        return drawLotteryActivityPrizeService.saveDrawLotteryActivityPrize(param);
    }

    @ApiOperation(value = "查询奖品详情", notes = "查询奖品详情。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getPrizeDetail/{id}", method = RequestMethod.GET)
    public Result<DrawLotteryActivityPrizeDetailDTO> getPrizeDetail(@PathVariable @ApiParam(name = "id", required = true, value = "奖品ID") Long id) {
        Result<DrawLotteryActivityPrizeDetailDTO> result = drawLotteryActivityPrizeService.getPrizeDetail(id);
        DrawLotteryActivityPrizeDetailDTO prizeDetailDTO = result.getModel();
        Result<MerchantStoreDTO> storeDTOResult = merchantStoreService.getMerchantStoreById(prizeDetailDTO.getMerchantStoreId());
        if (isSuccess(storeDTOResult)) {
            prizeDetailDTO.setStoreName(storeDTOResult.getModel().getName());
        }
        return successGet(prizeDetailDTO);
    }

    @ApiOperation(value = "查询奖品剩余中奖率", notes = "查询奖品剩余中奖率。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getLeftPrizeRate/{drawLotteryActivityId}", method = RequestMethod.GET)
    public Result<BigDecimal> getLeftPrizeRate(@PathVariable @ApiParam(name = "drawLotteryActivityId", required = true, value = "活动ID") Long drawLotteryActivityId) {
        return drawLotteryActivityPrizeService.getLeftPrizeRate(drawLotteryActivityId);
    }

}
