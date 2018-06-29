package com.lawu.eshop.operator.api.controller;

import java.math.BigDecimal;
import java.util.List;

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

import com.lawu.eshop.activity.dto.GenerateLargeRedpacketDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityQueryDTO;
import com.lawu.eshop.activity.param.GenerateLargeRedpacketParam;
import com.lawu.eshop.activity.param.GenerateNormalRedpacketParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivitySaveParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivityUpdateParam;
import com.lawu.eshop.activity.param.RedpacketActivityQueryParam;
import com.lawu.eshop.activity.param.SaveRedpacketParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.HelpRedpacketActivityService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Api(tags = "helpRedpacketActivity")
@RestController
@RequestMapping(value = "helpRedpacketActivity/")
public class HelpRedpacketActivityController extends BaseController {
    
    @Autowired
    private HelpRedpacketActivityService helpRedpacketActivityService;
    
    /**
     * 查询助力红包活动详情
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月28日
     * @updateDate 2017年12月28日
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
    @ApiOperation(value = "查询助力红包活动详情", notes = "查询助力红包活动详情（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("helpRedpacketActivity:detail")
    @RequestMapping(value = {"detail", "detail/{id}"}, method = RequestMethod.GET)
    public Result<HelpRedpacketActivityDTO> detail(@PathVariable(name = "id", required = false) Integer id) {
        if (id != null) {
            return successGet(helpRedpacketActivityService.detail(id));
        } else {
            return successGet(helpRedpacketActivityService.detail());
        }
    }
    
    /**
     * 获取已经分配的普通红包总金额
     * 
     * @author jiangxinjun
     * @createDate 2018年1月4日
     * @updateDate 2018年1月4日
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
    @ApiOperation(value = "获取已经分配的普通红包总金额", notes = "获取已经分配的普通红包总金额（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("helpRedpacketActivity:detail")
    @RequestMapping(value = {"getNormalRedpacketTotalAmount", "getNormalRedpacketTotalAmount/{id}"}, method = RequestMethod.GET)
    public Result<BigDecimal> getNormalRedpacketTotalAmount(@PathVariable(name = "id", required = false) Integer id) {
        if (id != null) {
            return successGet(helpRedpacketActivityService.getNormalRedpacketTotalAmount(id));
        } else {
            return successGet(helpRedpacketActivityService.getNormalRedpacketTotalAmount());
        }
    }
    
    /**
     * 查询助力红包活动详情
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月28日
     * @updateDate 2017年12月28日
     */
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.REDPACKET_ACTIVITY_UPDATE, idParamName ="id")
    @SuppressWarnings({ "rawtypes", "deprecation" })
    @ApiOperation(value = "更新助力红包活动", notes = "更新助力红包活动[1004]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("helpRedpacketActivity:update")
    @RequestMapping(value = {"update", "update/{id}"}, method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id", required = false) Integer id, @RequestBody @Validated HelpRedpacketActivityUpdateParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        if (id != null) {
            return successCreated(helpRedpacketActivityService.update(id, param));
        } else {
            return successCreated(helpRedpacketActivityService.update(param));
        }
    }
    
    /**
     * 生成大额红包
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2017年12月29日
     */
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.REDPACKET_ACTIVITY_GENERATE_LARGE_REDPACKET, idParamName ="id")
    @SuppressWarnings({ "unchecked", "deprecation" })
    @ApiOperation(value = "生成大额红包", notes = "生成大额红包[1004]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("helpRedpacketActivity:generateLargeRedpacket")
    @RequestMapping(value = {"generateLargeRedpacket", "generateLargeRedpacket/{id}"}, method = RequestMethod.PUT)
    public Result<GenerateLargeRedpacketDTO> generateLargeRedpacket(@PathVariable(name = "id", required = false) Integer id, @RequestBody @Validated GenerateLargeRedpacketParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        if (id != null) {
            return successCreated(helpRedpacketActivityService.generateLargeRedpacket(id, param));
        } else {
            return successCreated(helpRedpacketActivityService.generateLargeRedpacket(param));
        }
    }
    
    /**
     * 保存大额红包
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月30日
     * @updateDate 2017年12月30日
     */
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.REDPACKET_ACTIVITY_SAVE_LARGE_REDPACKET, idParamName ="id")
    @SuppressWarnings({ "rawtypes", "deprecation" })
    @ApiOperation(value = "保存大额红包", notes = "保存大额红包[1004]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("helpRedpacketActivity:generateLargeRedpacket")
    @RequestMapping(value = {"saveLargeRedpacket", "saveLargeRedpacket/{id}"}, method = RequestMethod.PUT)
    public Result saveLargeRedpacket(@PathVariable(name = "id", required = false) Integer id, @RequestBody @Validated List<SaveRedpacketParam> params, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        if (id != null) {
            return successCreated(helpRedpacketActivityService.saveLargeRedpacket(id, params));
        } else {
            return successCreated(helpRedpacketActivityService.saveLargeRedpacket(params));
        }
    }
    
    /**
     * 生成普通红包
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月2日
     * @updateDate 2018年1月2日
     */
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.REDPACKET_ACTIVITY_GENERATE_NORMAL_REDPACKET, idParamName ="id")
    @SuppressWarnings({ "rawtypes", "deprecation" })
    @ApiOperation(value = "生成普通红包", notes = "生成普通红包[1004]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("helpRedpacketActivity:generateLargeRedpacket")
    @RequestMapping(value = {"generateNormalRedpacket", "generateNormalRedpacket/{id}"}, method = RequestMethod.PUT)
    public Result generateNormalRedpacket(@PathVariable(name = "id", required = false) Integer id, @ModelAttribute @Validated GenerateNormalRedpacketParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        if (id != null) {
            return successCreated(helpRedpacketActivityService.generateNormalRedpacket(id, param));
        } else {
            return successCreated(helpRedpacketActivityService.generateNormalRedpacket(param));
        }
    }
    
    /**
     * 重新生成普通红包
     * 考虑生成红包的过程中可能出现异常,需要手动的重新生成红包
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月8日
     * @updateDate 2018年1月8日
     */
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.REDPACKET_ACTIVITY_AGAIN_GENERATE_NORMAL_REDPACKET, idParamName ="id")
    @SuppressWarnings({ "rawtypes", "deprecation" })
    @ApiOperation(value = "重新生成普通红包", notes = "重新生成普通红包[1004]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("helpRedpacketActivity:generateLargeRedpacket")
    @RequestMapping(value = {"againGenerateNormalRedpacket", "againGenerateNormalRedpacket/{id}"}, method = RequestMethod.PUT)
    public Result againGenerateNormalRedpacket(@PathVariable(name = "id", required = false) Integer id, @ModelAttribute @Validated GenerateNormalRedpacketParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        if (id != null) {
            return successCreated(helpRedpacketActivityService.againGenerateNormalRedpacket(id, param));
        } else {
            return successCreated(helpRedpacketActivityService.againGenerateNormalRedpacket(param));
        }
    }
    
    /**
     * 继续生成普通红包
     * 考虑生成红包的过程中可能出现异常,需要手动的继续生成红包
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月8日
     * @updateDate 2018年1月8日
     */
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.REDPACKET_ACTIVITY_CONTINUE_GENERATE_NORMAL_REDPACKET, idParamName ="id")
    @SuppressWarnings({ "rawtypes", "deprecation" })
    @ApiOperation(value = "继续生成普通红包", notes = "继续生成普通红包[1004]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("helpRedpacketActivity:generateLargeRedpacket")
    @RequestMapping(value = {"continueGenerateNormalRedpacket", "continueGenerateNormalRedpacket/{id}"}, method = RequestMethod.PUT)
    public Result continueGenerateNormalRedpacket(@PathVariable(name = "id", required = false) Integer id) {
        if (id != null) {
            return successCreated(helpRedpacketActivityService.continueGenerateNormalRedpacket(id));
        } else {
            return successCreated(helpRedpacketActivityService.continueGenerateNormalRedpacket());
        }
    }
    
    /**
     * 获取活动列表
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    @PageBody
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "红包活动列表", notes = "红包活动列表[1004]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("helpRedpacketActivity:list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<Page<HelpRedpacketActivityQueryDTO>> list(@ModelAttribute @Validated RedpacketActivityQueryParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        return successCreated(helpRedpacketActivityService.list(param));
    }
    
    /**
     * 保存红包活动
     * 
     * @param param
     *            保存参数
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.REDPACKET_ACTIVITY_ADD)
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "新增红包活动", notes = "新增红包活动[1004]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("helpRedpacketActivity:save")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody @Validated HelpRedpacketActivitySaveParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        return successCreated(helpRedpacketActivityService.save(param));
    }
}
