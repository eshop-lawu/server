package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.PointLotteryActivityRecordPageDTO;
import com.lawu.eshop.activity.query.PointLotteryActivityQueryParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.PointLotteryActivityRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 积分夺宝参与记录控制器
 * @author jiangxinjun
 * @createDate 2018年2月1日
 * @updateDate 2018年2月1日
 */
@Api(tags = "pointLotteryActivityRecord")
@RestController
@RequestMapping(value = "pointLotteryActivityRecord/")
public class PointLotteryActivityRecordController extends BaseController {

    @Autowired
    private PointLotteryActivityRecordService pointLotteryActivityRecordService;
	
	/**
     * 分页查询当前活动的参与记录
     * @param param 查询参数
     * @return
     * @author jiangxinjun
     * @createDate 2018年2月1日
     * @updateDate 2018年2月1日
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "分页查询当前活动的参与记录", notes = "分页查询当前活动的参与记录[1004](蒋鑫俊)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @PageBody
    @RequestMapping(value = "page/{pointLotteryActivityId}", method = RequestMethod.GET)
    public Result<Page<PointLotteryActivityRecordPageDTO>> generateBasicNumber(@PathVariable("pointLotteryActivityId") Long pointLotteryActivityId, @ModelAttribute @Validated PointLotteryActivityQueryParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        return successGet(pointLotteryActivityRecordService.page(pointLotteryActivityId, param));
    }

}
