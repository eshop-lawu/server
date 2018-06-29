package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.mall.dto.SuggestionDTO;
import com.lawu.eshop.mall.param.SuggestionListParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
@FeignClient(value = "mall-srv")
public interface SuggestionService {

    /**
     * 查询意见记录列表
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "suggestion/getSuggestionList", method = RequestMethod.POST)
    public Result<Page<SuggestionDTO>> getSuggestionList(@RequestBody SuggestionListParam pageParam);
    
    /**
     * 删除反馈意见
     * @param id
     * @return
     */
    @RequestMapping(value = "suggestion/delSuggestion/{id}",method = RequestMethod.PUT)
    public Result delSuggestion(@PathVariable("id") Long id);
}
