package com.lawu.eshop.agent.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.statistics.dto.AgentUserRegUserListDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/8/14.
 */
@FeignClient(value = "statistics-srv")
public interface ReportAreaUserRegMonthService {

    @RequestMapping(value = "regUserMonth/getUserRegListMonth", method = RequestMethod.POST)
    Result<List<AgentUserRegUserListDTO>> getUserRegListMonth(@RequestBody  AgentReportParam param);
}
