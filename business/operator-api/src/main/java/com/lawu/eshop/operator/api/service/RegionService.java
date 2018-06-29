package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.framework.web.Result;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
@FeignClient(value = "mall-srv", path ="region")
public interface RegionService {

    /**
     * 根据区域ID查询区域完整名称
     *
     * @return
     */
    @RequestMapping(value = "getRegionFullName/{id}", method = RequestMethod.GET)
    Result<String> getRegionFullName(@PathVariable("id") Integer id);

    /**
     * 根据区域ID查询区域信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getRegion/{id}", method = RequestMethod.GET)
    Result<RegionDTO> getRegionById(@PathVariable("id") Integer id);
    
    /**
     * pc网页地区选择控件数据来源
     * @return
     */
    @RequestMapping(value = "getRegionSelectorData", method = RequestMethod.GET)
    Result<JSONObject> getRegionSelectorData();

    @RequestMapping(value = "getById", method = RequestMethod.GET)
    Result<String> getById(@RequestParam("id") Long id);

}
