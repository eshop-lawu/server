package com.lawu.eshop.beh.analyze.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.beh.analyze.dto.AbnormalDTO;
import com.lawu.eshop.beh.analyze.param.AbnormalJobAddParam;
import com.lawu.eshop.beh.analyze.param.AbnormalParam;
import com.lawu.eshop.beh.analyze.srv.bo.AbnormalBO;
import com.lawu.eshop.beh.analyze.srv.convert.AbnormalConverter;
import com.lawu.eshop.beh.analyze.srv.event.EventPublisher;
import com.lawu.eshop.beh.analyze.srv.service.AbnormalRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
@RestController
@RequestMapping(value = "abnormal/")
public class AbnormalRecordController extends BaseController {

    @Autowired
    private AbnormalRecordService abnormalRecordService;

    @Autowired
    private EventPublisher eventPublisher;

    @RequestMapping(value = "getAbnormalRecord", method = RequestMethod.POST)
    public Result<Page<AbnormalDTO>> getAbnormalRecord(@RequestBody AbnormalParam param) {
        Page<AbnormalBO> boPage = abnormalRecordService.getAbnormalRecord(param);
        Page<AbnormalDTO> page = new Page<>();
        page.setTotalCount(boPage.getTotalCount());
        page.setCurrentPage(boPage.getCurrentPage());
        page.setRecords(AbnormalConverter.convertDTOS(boPage.getRecords()));
        return successGet(page);
    }

    @RequestMapping(value = "editAbnormalRecord", method = RequestMethod.POST)
    public Result editAbnormalRecord(@RequestBody AbnormalJobAddParam param) {
        abnormalRecordService.editAbnormalRecord(param);
        return successCreated();
    }

    @RequestMapping(value = "addActiveAbnormalInfo", method = RequestMethod.POST)
    public Result addActiveAbnormalInfo(@RequestBody AbnormalJobAddParam addParam){
        eventPublisher.publishActivityEvent(addParam);
        return successCreated();
    }
}
