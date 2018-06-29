package com.lawu.eshop.property.srv.controller;

import javax.validation.Valid;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.srv.service.UserRedPacketService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Description: 用户红包
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月13日 下午1:56:41
 */
@RestController
@RequestMapping(value = "userRedPacket/")
public class UserRedPacketController extends BaseController {

    @Autowired
    private UserRedPacketService userRedPacketService;

    /**
     * 支付回调
     *
     * @param param
     * @param result
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "doHandleMemberRedPacketNotify", method = RequestMethod.POST)
    public Result doHandleMemberRedPacketNotify(@RequestBody @Valid NotifyCallBackParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        int retCode = userRedPacketService.doHandleMemberRedPacketNotify(param);
        return successCreated(retCode);
    }

//    @SuppressWarnings("rawtypes")
//    @Deprecated
//    @RequestMapping(value = "doRefund", method = RequestMethod.POST)
//    public Result doRefund(@RequestBody @Valid MemberRedPacketRefundDataParam param, BindingResult result)
//            throws Exception {
//        String message = validate(result);
//        if (message != null) {
//            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
//        }
//
//        int retCode = userRedPacketService.doRefund(param);
//        return successCreated(retCode);
//    }

}
