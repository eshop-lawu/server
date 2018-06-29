package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.agent.dto.AgentUserListDTO;
import com.lawu.eshop.agent.param.AgentUserListParam;
import com.lawu.eshop.agent.param.AgentUserParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/8/9.
 */
@FeignClient(value = "agent-srv")
public interface AgentUserService {

    @RequestMapping(value = "user/addAgentUser",method = RequestMethod.POST)
    Result addAgentUser(@RequestBody AgentUserParam param);

    @RequestMapping(value = "user/getAgentUserList",method = RequestMethod.POST)
    Result<Page<AgentUserListDTO>> getAgentUserList(@RequestBody AgentUserListParam param);

    @RequestMapping(value = "user/getAgentUser/{id}",method = RequestMethod.GET)
    Result<AgentUserListDTO> getAgentUser(@PathVariable("id") Long id);

    @RequestMapping(value = "user/editAgentUser/{id}", method = RequestMethod.PUT)
    Result editAgentUser(@PathVariable("id") Long id, @RequestBody AgentUserParam param);
}
