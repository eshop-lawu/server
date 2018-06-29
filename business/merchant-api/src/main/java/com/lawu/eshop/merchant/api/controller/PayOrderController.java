package com.lawu.eshop.merchant.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.service.PayOrderService;
import com.lawu.eshop.order.dto.MerchantPayOrderListDTO;
import com.lawu.eshop.order.param.MerchantPayOrderListParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyong
 * @date 2017/6/22.
 */
@Api(tags = "payOrder")
@RestController
@RequestMapping(value = "payOrder/")
public class PayOrderController extends BaseController{

    @Autowired
    private PayOrderService payOrderService;

    @Audit(date = "2017-07-04", reviewer = "孙林青")
    @ApiOperation(value = "买单列表", notes = "买单列表 [1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getMerchantPayOrderList", method = RequestMethod.GET)
    public Result<Page<MerchantPayOrderListDTO>> getMerchantPayOrderList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute MerchantPayOrderListParam param){
        if(param == null || param.getCurrentPage() <1){
            return successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }
        Long userId = UserUtil.getCurrentUserId(getRequest());
        return payOrderService.getMerchantPayOrderList(userId,param);
    }
}
