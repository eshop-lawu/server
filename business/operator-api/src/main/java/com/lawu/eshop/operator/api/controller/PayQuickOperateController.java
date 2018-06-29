package com.lawu.eshop.operator.api.controller;

import javax.validation.Valid;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.PayQuickOperateService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.property.param.ThirdPayRefundParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "payQuick")
@RestController
@RequestMapping(value = "/payQuick")
public class PayQuickOperateController extends BaseController {

    @Autowired
    private PayQuickOperateService payQuickOperateService;

    @Audit(date = "2017-12-19", reviewer = "杨清华")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.THIED_PAY_REFUND)
    @ApiOperation(value = "第三方支付快捷退款", notes = "第三方支付快捷退款[]，(杨清华)", httpMethod = "POST")
    @RequestMapping(value = "refund", method = RequestMethod.POST)
    public Result refund(@ModelAttribute @ApiParam @Valid ThirdPayRefundParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        return payQuickOperateService.refund(param);
    }

}
