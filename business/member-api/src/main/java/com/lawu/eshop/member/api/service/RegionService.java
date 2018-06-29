package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.mall.dto.RegionPathDTO;
import com.lawu.eshop.mall.dto.RegionProvinceDTO;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
@FeignClient(value = "mall-srv", path = "region")
public interface RegionService {
	
    /**
     * 城市列表
     * @return
     */
    @RequestMapping(value = "getRegionList", method = RequestMethod.GET)
    Result<List<RegionDTO>> getRegionList();
    
    /**
     * 查询所有地区，分组
     * @return
     * @author jiangxinjun
     * @date 2017年9月13日
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    Result<List<RegionPathDTO>> list();
    
    /**
     * 查询所有地区
     * @return
     * @author jiangxinjun
     * @date 2017年9月13日
     */
    @RequestMapping(value = "group", method = RequestMethod.GET)
    Result<List<RegionProvinceDTO>> group();

    /**
     * 根据区域路径查询区域名称
     *
     * @param regionPath
     * @return
     */
    @RequestMapping(value = "getAreaName", method = RequestMethod.GET)
    Result<String> getAreaName(@RequestParam("regionPath") String regionPath);

}
