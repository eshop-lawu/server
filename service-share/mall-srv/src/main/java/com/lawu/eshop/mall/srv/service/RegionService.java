package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.mall.srv.bo.RegionBO;
import com.lawu.eshop.mall.srv.bo.RegionSelectorDataBO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
public interface RegionService {


    List<RegionBO> getRegionList();

    /**
     * 根据区域ID查询区域完整名称
     *
     * @param id
     * @return
     */
    String getRegionFullName(Integer id);

    /**
     * 根据区域路径查询区域名称
     *
     * @param regionPath
     * @return
     */
    String getAreaName(String regionPath);

    /**
     * 查询城市列表
     *
     * @return
     */
    List<RegionBO> getRegionLevelTwo();

    /**
     * 根据区域ID查询区域信息
     *
     * @param id
     * @return
     */
    RegionBO getRegionById(Integer id);

    /**
     *
     * 更新区域表经纬度
     *
     * @param id
     * @param longitude
     * @param latitude
     */
    void updateRegionLonLat(Integer id, BigDecimal longitude, BigDecimal latitude);

    List<RegionSelectorDataBO> getRegionByParentId(String s);
}
