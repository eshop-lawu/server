package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.beh.analyze.dto.AbnormalDTO;
import com.lawu.eshop.beh.analyze.dto.InviteAbnormalDecideRecordDTO;
import com.lawu.eshop.beh.analyze.param.AbnormalParam;
import com.lawu.eshop.beh.analyze.param.InviteAbnormalDecideRecordFreezeParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.AbnormalRecordService;
import com.lawu.eshop.operator.api.service.FreezeService;
import com.lawu.eshop.operator.api.service.InviteAbnormalDecideRecordService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.user.constants.FreezeTypeEnum;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
@Api(tags = "abnormal")
@RestController
@RequestMapping(value = "abnormal/")
public class AbnormalRecordController extends BaseController {

    @Autowired
    private AbnormalRecordService abnormalRecordService;
    
    @Autowired
    private InviteAbnormalDecideRecordService inviteAbnormalDecideRecordService;
    
    @Autowired
    private FreezeService freezeService;
    
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "查询异常账户列表", notes = "查询异常账户列表。（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @PageBody
    @RequiresPermissions("abnormal:list")
    @RequestMapping(value = "getAbnormalRecord", method = RequestMethod.GET)
    public Result<Page<AbnormalDTO>> getAbnormalRecord(@ModelAttribute AbnormalParam param) {
        return successGet(abnormalRecordService.getAbnormalRecord(param));
    }
    
    @SuppressWarnings("rawtypes")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ACCOUNT_NOT_DEAL,idParamName ="id")
    @ApiOperation(value = "标识为不处理", notes = "标识为不处理[]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequiresPermissions("abnormal:freeze")
    @RequestMapping(value = "notProcessed/{id}", method = RequestMethod.PUT)
    public Result notProcessed(@PathVariable("id") Long id) {
        return successCreated(inviteAbnormalDecideRecordService.notProcessed(id));
    }
    
    @Deprecated
    @SuppressWarnings("rawtypes")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ACCOUNT_FREEZE,idParamName ="id")
    @ApiOperation(value = "冻结异常账号", notes = "冻结异常账号[1001|1004]（蒋鑫俊）", httpMethod = "PUT")
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("abnormal:freeze")
    @RequestMapping(value = "freeze/{id}", method = RequestMethod.PUT)
    public Result freeze(@PathVariable("id") Long id, @ModelAttribute @Validated InviteAbnormalDecideRecordFreezeParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        Result<InviteAbnormalDecideRecordDTO> getResult = inviteAbnormalDecideRecordService.get(id);
        if (!isSuccess(getResult)) {
            return successCreated(getResult);
        }
        FreezeParam freezeParam = new FreezeParam();
        freezeParam.setFreezeReason(param.getFreezeReason());
        freezeParam.setFreezeType(FreezeTypeEnum.ROBOT_REGISTRATION);
        freezeParam.setIsFreeze(true);
        freezeParam.setNum(getResult.getModel().getUserNum());
        Result freezeResult = freezeService.freeze(freezeParam);
        if (!isSuccess(freezeResult)) {
            return successCreated(freezeResult);
        }
        return successCreated(inviteAbnormalDecideRecordService.freeze(id));
    }
    
    @SuppressWarnings("rawtypes")
    @LogRecord(type =OperationTypeEnum.UPDATE , title = LogTitleEnum.ACCOUNT_FREEZE)
    @ApiOperation(value = "冻结异常账号", notes = "冻结异常账号[1001|1004]（蒋鑫俊）", httpMethod = "PUT")
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("abnormal:freeze")
    @RequestMapping(value = "freeze", method = RequestMethod.PUT)
    public Result freeze(@RequestBody @Validated InviteAbnormalDecideRecordFreezeParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        for (Long id : param.getIds()) {
            Result<InviteAbnormalDecideRecordDTO> getResult = inviteAbnormalDecideRecordService.get(id);
            if (!isSuccess(getResult)) {
                return successCreated(getResult);
            }
            FreezeParam freezeParam = new FreezeParam();
            freezeParam.setFreezeReason(param.getFreezeReason());
            freezeParam.setFreezeType(FreezeTypeEnum.ROBOT_REGISTRATION);
            freezeParam.setIsFreeze(true);
            freezeParam.setNum(getResult.getModel().getUserNum());
            Result freezeResult = freezeService.freeze(freezeParam);
            if (!isSuccess(freezeResult)) {
                return successCreated(freezeResult);
            }
            Result abnormalFreezeResult = inviteAbnormalDecideRecordService.freeze(id);
            if (!isSuccess(abnormalFreezeResult)) {
                return successCreated(abnormalFreezeResult);
            }
        }
        return successCreated();
    }
}
