package com.lawu.eshop.operator.srv.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.operator.dto.LogDTO;
import com.lawu.eshop.operator.param.ListLogParam;
import com.lawu.eshop.operator.param.LogParam;
import com.lawu.eshop.operator.srv.bo.LogBO;
import com.lawu.eshop.operator.srv.converter.LogConverter;
import com.lawu.eshop.operator.srv.service.LogService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
@RestController
@RequestMapping(value = "log/")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    /**
     * 保存日志
     *
     * @param logParam
     * @return
     */
    @RequestMapping(value = "saveLog", method = RequestMethod.POST)
    public Result saveLog(@RequestBody LogParam logParam) {
        logService.saveLog(logParam);
        return successCreated();
    }

    /**
     * 根据ID查询日志
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getLog/{id}", method = RequestMethod.GET)
    public Result<LogDTO> getLog(@PathVariable Long id) {
        LogBO logBO = logService.getLogById(id);
        if (logBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(LogConverter.convertDTO(logBO));
    }

    /**
     * 运营后台查询日志列表
     *
     * @param listLogParam
     * @return
     */
    @RequestMapping(value = "listLog", method = RequestMethod.POST)
    public Result<Page<LogDTO>> listLog(@RequestBody ListLogParam listLogParam) {
        Page<LogBO> logBOPage = logService.listLog(listLogParam);
        Page<LogDTO> page = new Page<>();
        page.setCurrentPage(logBOPage.getCurrentPage());
        page.setTotalCount(logBOPage.getTotalCount());
        page.setRecords(LogConverter.convertDTO(logBOPage.getRecords()));
        return successGet(page);
    }

}
