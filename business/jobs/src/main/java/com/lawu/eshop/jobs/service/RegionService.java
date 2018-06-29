package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
@FeignClient(value = "mall-srv")
public interface RegionService {

    /**
     * 查询城市列表
     *
     * @return
     */
    @RequestMapping(value = "region/getRegionLevelTwo", method = RequestMethod.GET)
    Result<List<RegionDTO>> getRegionLevelTwo();

    @RequestMapping(value = "region/getRegion/{id}", method = RequestMethod.GET)
    Result<RegionDTO> getRegion(@PathVariable("id") Integer id);

    /**
     * 根据区域路径查询区域名称
     *
     * @param regionPath
     * @return
     */
    @RequestMapping(value = "region/getAreaName", method = RequestMethod.GET)
    Result<String> getAreaName(@RequestParam("regionPath") String regionPath);

    @RequestMapping(value = "region/getById", method = RequestMethod.GET)
    Result<String> getById(@RequestParam("id") Long id);

}
