package com.lawu.eshop.member.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.AttendCountDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityAttendDetailDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityAttendRecordDTO;
import com.lawu.eshop.activity.dto.PointLotteryRollDTO;
import com.lawu.eshop.activity.param.PointLotteryActivityAttendParam;
import com.lawu.eshop.activity.param.PointLotteryActivityRecordParam;
import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.PointLotteryActivityRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
@Api(tags = "pointLotteryActivityRecord")
@RestController
@RequestMapping(value = "pointLotteryActivityRecord/")
public class PointLotteryActivityRecordController extends BaseController {

    @Autowired
    private PointLotteryActivityRecordService pointLotteryActivityRecordService;

	@Audit(date = "2018-02-06", reviewer = "孙林青")
    @ApiOperation(value = "中奖滚动列表", notes = "中奖滚动列表。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listLatestLotteryInfo", method = RequestMethod.GET)
    public Result<List<PointLotteryRollDTO>> listLatestLotteryInfo() {
        Result<List<PointLotteryRollDTO>> result = pointLotteryActivityRecordService.listLatestLotteryInfo();
        return successGet(result);
    }

	@Audit(date = "2018-02-06", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "我的抽奖", notes = "我的抽奖（张荣成）", httpMethod = "GET")
    @Authorization
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "attendPrizePage", method = RequestMethod.GET)
	public Result<Page<PointLotteryActivityAttendRecordDTO>> attendPrizePage(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(required = true, value = "查询信息") @Validated PointLotteryActivityAttendParam param, BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
		    return successGet(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		PointLotteryActivityRecordParam recordParam = new PointLotteryActivityRecordParam();
		recordParam.setCurrentPage(param.getCurrentPage());
		recordParam.setPageSize(param.getPageSize());
		recordParam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
	    Result<Page<PointLotteryActivityAttendRecordDTO>> result = pointLotteryActivityRecordService.attendPrizePage(recordParam);
		return successGet(result);
	}


	@Audit(date = "2018-02-06", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "我的抽奖详情", notes = "我的抽奖详情（张荣成）", httpMethod = "GET")
    @Authorization
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getPointLotteryActivityAttendDetail", method = RequestMethod.GET)
	public Result<PointLotteryActivityAttendDetailDTO> getPointLotteryActivityAttendDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestParam @ApiParam(required = true, value = "抽奖活动id") Long id) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
	    Result<PointLotteryActivityAttendDetailDTO> result = pointLotteryActivityRecordService.getPointLotteryActivityAttendDetail(userNum, id);
		return successGet(result);
	}


	@Audit(date = "2018-02-06", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
   	@ApiOperation(value = "我的抽奖总次数", notes = "我的抽奖总次数（张荣成）", httpMethod = "GET")
    @Authorization
   	@ApiResponse(code = HttpCode.SC_OK, message = "success")
   	@RequestMapping(value = "getAttendCount", method = RequestMethod.GET)
   	public Result<AttendCountDTO> getAttendCount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
   		String userNum = UserUtil.getCurrentUserNum(getRequest());
   	    Result<AttendCountDTO> result = pointLotteryActivityRecordService.getAttendCount(userNum);
   		return successGet(result);
   	}

}
