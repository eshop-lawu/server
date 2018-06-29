package com.lawu.eshop.operator.api.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.eshop.activity.dto.PointLotteryActivityOperatorDTO;
import com.lawu.eshop.activity.dto.PointLotteryRelateDTO;
import com.lawu.eshop.activity.param.GenerateBasicNumberParam;
import com.lawu.eshop.activity.param.PointLotteryActivityParam;
import com.lawu.eshop.activity.query.OperatorPointLotteryActivityQuery;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.PointLotteryActivityService;
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
 * 积分夺宝活动控制器
 * @author jiangxinjun
 * @createDate 2018年2月1日
 * @updateDate 2018年2月1日
 */
@Api(tags = "pointLotteryActivity")
@RestController
@RequestMapping(value = "pointLotteryActivity/")
public class PointLotteryActivityController extends BaseController {

    @Autowired
    private PointLotteryActivityService pointLotteryActivityService;
    
    /**
     * 生成基础中奖号码
     * @param id 积分夺宝活动id
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年2月1日
     * @updateDate 2018年2月1日
     */
    @LogRecord(type = OperationTypeEnum.UPDATE, title = LogTitleEnum.LOTTER_ACTIVITY_GENERATE_BASIC_NUMBER, idParamName = "id")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "生成基础中奖号码", notes = "生成基础中奖号码（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("pointLotteryActivity:generateBasicNumber")
    @RequestMapping(value = "generateBasicNumber/{id}", method = RequestMethod.PUT)
    public Result<Integer> generateBasicNumber(@PathVariable("id") @ApiParam(value = "积分夺宝活动id", required = true) Long id, @ModelAttribute @Validated GenerateBasicNumberParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        return successCreated(pointLotteryActivityService.generateBasicNumber(id, param));
    }
    
    /**
     * 保存中奖号码
     * @param id 积分夺宝活动id
     * @param prizeNumbers 中奖号码用','分隔
     * @author jiangxinjun
     * @createDate 2018年2月1日
     * @updateDate 2018年2月1日
     */
    @LogRecord(type = OperationTypeEnum.UPDATE, title = LogTitleEnum.LOTTER_ACTIVITY_SAVE_WINNING_NUMBER, idParamName = "id")
    @SuppressWarnings({ "rawtypes" })
    @ApiOperation(value = "保存中奖号码", notes = "保存中奖号码（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("pointLotteryActivity:saveWinningNumber")
    @RequestMapping(value = "saveWinningNumber/{id}", method = RequestMethod.PUT)
    public Result saveWinningNumber(@PathVariable("id") @ApiParam(value = "积分夺宝活动id", required = true) Long id, 
            @RequestParam("prizeNumbers") @ApiParam(value = "中奖号码为数字,且用逗号(',')分隔", required = true) String prizeNumbers) {
        Pattern pattern = Pattern.compile("^\\d+(,\\d+)*$");
        if (!pattern.matcher(prizeNumbers).matches()) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, "中奖号码格式错误,中奖号码为数字,且用逗号(',')分隔");
        }
        return successCreated(pointLotteryActivityService.saveWinningNumber(id, prizeNumbers));
    }

    @ApiOperation(value = "抽奖活动列表", notes = "抽奖活动列表。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("point_lottery:list")
    @PageBody
    @RequestMapping(value = "listOperatorPointLotteryActivity", method = RequestMethod.POST)
    public Result<Page<PointLotteryActivityOperatorDTO>> listOperatorPointLotteryActivity(@RequestBody @ApiParam OperatorPointLotteryActivityQuery query) {
        return pointLotteryActivityService.listOperatorPointLotteryActivity(query);
    }

    @ApiOperation(value = "抽奖活动发布", notes = "抽奖活动发布。（梅述全）", httpMethod = "PUT")
    @LogRecord(type = OperationTypeEnum.UPDATE, title = LogTitleEnum.LOTTER_ACTIVITY_PUBLISG, idParamName = "id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("point_lottery:publish")
    @RequestMapping(value = "publishPointLotteryActivity/{id}", method = RequestMethod.PUT)
    public Result publishPointLotteryActivity(@PathVariable @ApiParam(name = "id", required = true, value = "活动ID") Long id) {
        return pointLotteryActivityService.updatePointLotteryActivityStatus(id, PointLotteryActivityStatusEnum.PUBLISHED);
    }

    @ApiOperation(value = "抽奖活动开奖", notes = "抽奖活动开奖。（梅述全）", httpMethod = "PUT")
    @LogRecord(type = OperationTypeEnum.UPDATE, title = LogTitleEnum.LOTTER_ACTIVITY_DRAW, idParamName = "id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("point_lottery:draw")
    @RequestMapping(value = "drawPointLotteryActivity/{id}", method = RequestMethod.PUT)
    public Result drawPointLotteryActivity(@PathVariable @ApiParam(name = "id", required = true, value = "活动ID") Long id) {
        return pointLotteryActivityService.updatePointLotteryActivityStatus(id, PointLotteryActivityStatusEnum.ALREADY_LOTTERY);
    }

    @ApiOperation(value = "抽奖活动删除", notes = "抽奖活动删除。（梅述全）", httpMethod = "PUT")
    @LogRecord(type = OperationTypeEnum.DELETE, title = LogTitleEnum.LOTTER_ACTIVITY_DETELE, idParamName = "id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("point_lottery:del")
    @RequestMapping(value = "delPointLotteryActivity/{id}", method = RequestMethod.PUT)
    public Result delPointLotteryActivity(@PathVariable @ApiParam(name = "id", required = true, value = "活动ID") Long id) {
        return pointLotteryActivityService.updatePointLotteryActivityStatus(id, PointLotteryActivityStatusEnum.DELETE);
    }

    @ApiOperation(value = "根据id查询抽奖活动", notes = "根据id查询抽奖活动。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getPointLotteryActivity/{id}", method = RequestMethod.GET)
    public Result<PointLotteryActivityOperatorDTO> getPointLotteryActivity(@PathVariable @ApiParam(name = "id", required = true, value = "活动ID") Long id) {
        return pointLotteryActivityService.getPointLotteryActivity(id);
    }

    @ApiOperation(value = "新增抽奖活动", notes = "新增抽奖活动。（梅述全）", httpMethod = "POST")
    @LogRecord(type = OperationTypeEnum.ADD, title = LogTitleEnum.LOTTER_ACTIVITY_ADD)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("point_lottery:add")
    @RequestMapping(value = "savePointLotteryActivity", method = RequestMethod.POST)
    public Result savePointLotteryActivity(@ModelAttribute PointLotteryActivityParam param) {
        return pointLotteryActivityService.savePointLotteryActivity(param);
    }

    @ApiOperation(value = "查询可关联积分抽奖活动列表", notes = "查询可关联积分抽奖活动列表。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @PageBody
    @RequestMapping(value = "listRelatePointLotteryActivity", method = RequestMethod.GET)
    public Result<Page<PointLotteryRelateDTO>> listRelatePointLotteryActivity() {
        Result<List<PointLotteryRelateDTO>> result = pointLotteryActivityService.listRelatePointLotteryActivity();
        Page<PointLotteryRelateDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(result.getModel().size());
        page.setRecords(result.getModel());
        return successGet(page);
    }

}
