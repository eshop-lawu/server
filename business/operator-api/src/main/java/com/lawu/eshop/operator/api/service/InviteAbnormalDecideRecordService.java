package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.beh.analyze.dto.InviteAbnormalDecideRecordDTO;
import com.lawu.framework.web.Result;

/**
 * 注册异常判定记录服务接口
 * 
 * @author jiangxinjun
 * @createDate 2018年1月18日
 * @updateDate 2018年1月18日
 */
@FeignClient(value = "beh-analyze-srv", path="inviteAbnormalDecideRecord/")
public interface InviteAbnormalDecideRecordService {
    
    /**
     * 根据id查询注册异常判定记录
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月19日
     * @updateDate 2018年1月19日
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Result<InviteAbnormalDecideRecordDTO> get(@PathVariable("id") Long id);
    
    /**
     * 标识为不处理
     * 
     * @author jiangxinjun
     * @createDate 2018年1月19日
     * @updateDate 2018年1月19日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "notProcessed/{id}", method = RequestMethod.PUT)
    Result notProcessed(@PathVariable("id") Long id);
    
    /**
     * 标识为冻结
     * 
     * @author jiangxinjun
     * @createDate 2018年1月19日
     * @updateDate 2018年1月19日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "freeze/{id}", method = RequestMethod.PUT)
    Result freeze(@PathVariable("id") Long id);
}

