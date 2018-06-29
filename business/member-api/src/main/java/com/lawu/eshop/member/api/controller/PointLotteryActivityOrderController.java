package com.lawu.eshop.member.api.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.constants.PointLotteryActivityOrderStatusEnum;
import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.eshop.activity.dto.PointLotteryAttentDTO;
import com.lawu.eshop.activity.dto.PointLotteryInfoDTO;
import com.lawu.eshop.activity.param.PointLotteryAttentParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.PointLotteryActivityOrderService;
import com.lawu.eshop.member.api.service.PointLotteryActivityService;
import com.lawu.eshop.member.api.service.PropertyInfoService;
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
@Api(tags = "pointLotteryActivityOrder")
@RestController
@RequestMapping(value = "pointLotteryActivityOrder/")
public class PointLotteryActivityOrderController extends BaseController {

    @Autowired
    private PointLotteryActivityOrderService pointLotteryActivityOrderService;

    @Autowired
    private PointLotteryActivityService pointLotteryActivityService;

    @Autowired
    private PropertyInfoService propertyInfoService;

    @Audit(date = "2018-02-06", reviewer = "孙林青")
    @ApiOperation(value = "积分夺宝", notes = "积分夺宝。[1002|9101|6002|6003|6024|6010|9105] (梅述全)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "savePointLotteryActivityOrder/{pointLotteryActivityId}", method = RequestMethod.POST)
    public Result<PointLotteryAttentDTO> savePointLotteryActivityOrder(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                        @PathVariable @ApiParam(required = true, value = "活动ID") Long pointLotteryActivityId,
                                                                        @RequestParam @ApiParam(required = true, value = "手机号码") String mobile,
                                                                        @RequestParam @ApiParam(required = true, value = "参与次数") Integer attentCount) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<PointLotteryInfoDTO> infoDTOResult = pointLotteryActivityService.getPointLotteryInfo(pointLotteryActivityId);
        if (infoDTOResult.getModel() == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (infoDTOResult.getModel().getStatusEnum().equals(PointLotteryActivityStatusEnum.PARTICIPATION_END)) {
            return successCreated(ResultCode.LOTTERY_ACTIVITY_FINISHED);
        }
        if (!infoDTOResult.getModel().getStatusEnum().equals(PointLotteryActivityStatusEnum.PROCESSING)) {
            return successCreated(ResultCode.LOTTERY_NOT_ING);
        }

        int payPoint = infoDTOResult.getModel().getLotteryPoint() * attentCount;
        Result validatePointResult = propertyInfoService.validatePoint(userNum, String.valueOf(payPoint));
        if (!isSuccess(validatePointResult)) {
            return successCreated(validatePointResult.getRet());
        }

        PointLotteryAttentParam param = new PointLotteryAttentParam();
        param.setUserNum(userNum);
        param.setMobile(mobile);
        param.setPointLotteryActivityId(pointLotteryActivityId);
        param.setAttentCount(attentCount);
        param.setPayPoint(BigDecimal.valueOf(payPoint));
        param.setStatusEnum(PointLotteryActivityOrderStatusEnum.PENDING);
        Result<PointLotteryAttentDTO> result = pointLotteryActivityOrderService.savePointLotteryActivityOrder(param);
        return successCreated(result);
    }

    @Audit(date = "2018-02-06", reviewer = "孙林青")
    @ApiOperation(value = "参与抽奖详情", notes = "参与抽奖详情，状态为SUCCESS说明参与成功。[1002] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getPointLotteryAttentInfo/{id}", method = RequestMethod.GET)
    public Result<PointLotteryAttentDTO> getPointLotteryAttentInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                            @PathVariable @ApiParam(required = true, value = "抽奖订单ID") Long id) {
        Result<PointLotteryAttentDTO> attentDTOResult = pointLotteryActivityOrderService.getPointLotteryAttentInfo(id);
        if (!isSuccess(attentDTOResult)) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(attentDTOResult);
    }

}
