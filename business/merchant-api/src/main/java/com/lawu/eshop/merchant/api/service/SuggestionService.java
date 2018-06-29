package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.mall.param.SuggestionParam;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/24
 */
@FeignClient(value= "mall-srv")
public interface SuggestionService {

    /**
     * 保存意见反馈
     * 
     * @param parm
     */
    @RequestMapping(method = RequestMethod.POST, value = "suggestion/{userNum}")
    Result save(@PathVariable("userNum") String userNum, @RequestBody SuggestionParam parm);
    
}
