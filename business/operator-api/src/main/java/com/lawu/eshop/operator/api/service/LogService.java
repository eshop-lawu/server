package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.operator.dto.LogDTO;
import com.lawu.eshop.operator.param.ListLogParam;
import com.lawu.eshop.operator.param.LogParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
@FeignClient(value = "operator-srv")
public interface LogService {

    /**
     * 保存日志
     *
     * @param logParam
     * @return
     */
    @RequestMapping(value = "log/saveLog", method = RequestMethod.POST)
    Result saveLog(@ModelAttribute LogParam logParam);

    /**
     * 根据ID查询日志
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "log/getLog/{id}", method = RequestMethod.GET)
    Result<LogDTO> getLogById(@PathVariable("id") Long id);

    /**
     * 查询日志列表
     *
     * @param listLogParam
     * @return
     */
    @RequestMapping(value = "log/listLog", method = RequestMethod.POST)
    Result<Page<LogDTO>> listLog(@ModelAttribute ListLogParam listLogParam);
}
