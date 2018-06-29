package com.lawu.eshop.mall.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.mall.srv.bo.RegionSelectorDataBO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.mall.constants.RegionLevelEnum;
import com.lawu.eshop.mall.srv.bo.RegionBO;
import com.lawu.eshop.mall.srv.converter.RegionConverter;
import com.lawu.eshop.mall.srv.domain.RegionDO;
import com.lawu.eshop.mall.srv.domain.RegionDOExample;
import com.lawu.eshop.mall.srv.mapper.RegionDOMapper;
import com.lawu.eshop.mall.srv.service.RegionService;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDOMapper regionDOMapper;

    @Override
    public List<RegionBO> getRegionList() {
        List<RegionBO> regionBOS = new ArrayList<>();
        /*
        List<RegionDOView> viewList = regionDOMMapperExtend.getRegionList();
        if (viewList == null) {
            return regionBOS;
        }
        for (RegionDOView regionDOView : viewList) {
            RegionBO regionBO = RegionConverter.coverBO(regionDOView);
            regionBOS.add(regionBO);
        }
        */
        List<RegionDO> regionDOList = regionDOMapper.selectByExample(null);
        for (RegionDO regionDO : regionDOList) {
            RegionBO regionBO = RegionConverter.convert(regionDO);
            regionBOS.add(regionBO);
        }
        return regionBOS;
    }

    @Override
    public String getRegionFullName(Integer id) {
        StringBuilder regionFullName = new StringBuilder("");
        RegionDO regionDO = regionDOMapper.selectByPrimaryKey(id);
        if (regionDO == null) {
            return regionFullName.toString();
        }
        int cnt = 0;
        regionFullName.append(regionDO.getName());
        do {
            if (regionDO.getParentId() > 0) {
                regionDO = regionDOMapper.selectByPrimaryKey(regionDO.getParentId());
                if (regionDO == null) {
                    return regionFullName.toString();
                }
                regionFullName.append(regionFullName).append(regionDO.getName());
            }
            cnt++;
        } while (cnt < 2);
        return regionFullName.toString();
    }

    @Override
    public String getAreaName(String regionPath) {
        String areaName = "";
        if (StringUtils.isEmpty(regionPath)) {
            return areaName;
        }
        String[] paths = regionPath.split("/");
        if (paths.length < 3) {
            return areaName;
        }
        RegionDO regionDO = regionDOMapper.selectByPrimaryKey(Integer.valueOf(paths[2]));
        if (regionDO == null || StringUtils.isEmpty(regionDO.getName())) {
            return areaName;
        }
        return regionDO.getName();
    }

    @Override
    public List<RegionBO> getRegionLevelTwo() {
        RegionDOExample example = new RegionDOExample();
        example.createCriteria().andLevelEqualTo(RegionLevelEnum.REGION_LEVEL_TWO.val);
        List<RegionDO> regionDOS = regionDOMapper.selectByExample(example);
        return RegionConverter.coverBO(regionDOS);
    }

    @Override
    public RegionBO getRegionById(Integer id) {
        RegionDO regionDO = regionDOMapper.selectByPrimaryKey(id);
        return RegionConverter.coverBO(regionDO);
    }

    @Override
    public void updateRegionLonLat(Integer id, BigDecimal longitude, BigDecimal latitude) {
        RegionDO regionDO = new RegionDO();
        regionDO.setId(id);
        regionDO.setLongitude(longitude);
        regionDO.setLatitude(latitude);
        regionDOMapper.updateByPrimaryKeySelective(regionDO);
    }

    @Override
    public List<RegionSelectorDataBO> getRegionByParentId(String s) {
        RegionDOExample example = new RegionDOExample();
        example.createCriteria().andParentIdEqualTo(Integer.valueOf(s));
        List<RegionDO> regionDOS = regionDOMapper.selectByExample(example);
        List<RegionSelectorDataBO> bos = new ArrayList<>();
        for(RegionDO rdo : regionDOS){
            RegionSelectorDataBO bo = new RegionSelectorDataBO();
            bo.setCode(rdo.getId().toString());
            bo.setAddress(rdo.getName());
            bos.add(bo);
        }
        return bos;
    }
}
