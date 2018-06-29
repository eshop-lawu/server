package com.lawu.eshop.agent.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.statistics.dto.UserActiveListDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/8/14.
 */
@FeignClient(value = "statistics-srv")
public interface ReportAreaUserActiveService {

    @RequestMapping(value ="userActive/getAgentUserActiveListDaily" ,method = RequestMethod.POST)
    Result<List<UserActiveListDTO>> getAgentUserActiveListDaily(@RequestBody  AgentReportParam param);

    @RequestMapping(value ="userActive/getAgentUserActiveListMonth" ,method = RequestMethod.POST)
    Result<List<UserActiveListDTO>> getAgentUserActiveListMonth(@RequestBody AgentReportParam param);
}
