package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.user.dto.UserIncomeExpenditureUserInfoWrapperDTO;
import com.lawu.eshop.user.param.UserIncomeExpenditureUserInfoQueryParam;
import com.lawu.framework.web.Result;

/**
 * 用户公共接口服务
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
@FeignClient(value = "user-srv", path = "user/")
public interface UserService {

    /**
     * 获取用户账号
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "account", method = RequestMethod.PUT)
    Result<UserIncomeExpenditureUserInfoWrapperDTO> selectAccount(@RequestBody UserIncomeExpenditureUserInfoQueryParam param);
}
