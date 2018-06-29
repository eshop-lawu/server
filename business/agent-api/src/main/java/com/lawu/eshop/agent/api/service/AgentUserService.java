package com.lawu.eshop.agent.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.agent.dto.LoginUserDTO;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/8/9.
 */
@FeignClient(value = "agent-srv")
public interface AgentUserService {

    @RequestMapping(value = "user/withPwd/{account}", method = RequestMethod.POST)
    Result<LoginUserDTO> find(@PathVariable("account") String account,@RequestParam("pwd") String pwd);
}
