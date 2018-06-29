package com.lawu.eshop.statistics.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.statistics.dto.AgentUserRegUserListDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaUserRegMonthBO;
import com.lawu.eshop.statistics.srv.converter.ReportAreaUserRegConverter;
import com.lawu.eshop.statistics.srv.service.ReportAreaUserRegService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/8/14.
 */
@RestController
@RequestMapping(value = "regUserMonth/")
public class ReportAreaRegUserMonthController extends BaseController{

    @Autowired
    private ReportAreaUserRegService reportAreaUserRegService;

    @RequestMapping(value = "getUserRegListMonth", method = RequestMethod.POST)
    public Result<List<AgentUserRegUserListDTO>> getUserRegListMonth(@RequestBody AgentReportParam param) {

        List<ReportAreaUserRegMonthBO> listBOS = reportAreaUserRegService.getUserRegListMonth(param);
        List<AgentUserRegUserListDTO> list = ReportAreaUserRegConverter.coverMonthDTOS(listBOS);

        return successCreated(list);
    }
}
