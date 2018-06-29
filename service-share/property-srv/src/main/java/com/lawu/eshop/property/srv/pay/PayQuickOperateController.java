package com.lawu.eshop.property.srv.pay;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.param.ThirdPayRefundParam;
import com.lawu.eshop.property.srv.service.PayQuickOperateService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "payQuick/")
public class PayQuickOperateController extends BaseController {

    @Autowired
    private PayQuickOperateService payQuickOperateService;

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "refund", method = RequestMethod.POST)
    public Result refund(@RequestBody ThirdPayRefundParam param) {
        try {
            payQuickOperateService.refund(param);
        }catch (Exception e){
            return successCreated(ResultCode.FAIL,e.getMessage());
        }
        return successCreated(ResultCode.SUCCESS);
    }
}
