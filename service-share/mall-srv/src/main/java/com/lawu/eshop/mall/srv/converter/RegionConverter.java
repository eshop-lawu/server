package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.mall.constants.RegionLevelEnum;
import com.lawu.eshop.mall.dto.RegionCityDTO;
import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.mall.dto.RegionPathDTO;
import com.lawu.eshop.mall.dto.RegionProvinceDTO;
import com.lawu.eshop.mall.srv.bo.RegionBO;
import com.lawu.eshop.mall.srv.domain.RegionDO;
import com.lawu.eshop.mall.srv.domain.extend.RegionDOView;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
public class RegionConverter {

    public static RegionBO coverBO(RegionDOView regionDOView) {
        if (regionDOView == null) {
            return null;
        }
        RegionBO regionBO = new RegionBO();
        regionBO.setId(regionDOView.getId());
        regionBO.setName(regionDOView.getName());
        regionBO.setParentId(regionDOView.getParentId());
        regionBO.setPath(regionDOView.getPath());
        regionBO.setLevelEnum(RegionLevelEnum.getEnum(regionDOView.getLevel()));
        return regionBO;
    }
    
    public static RegionBO convert(RegionDO regionDO) {
        if (regionDO == null) {
            return null;
        }
        RegionBO regionBO = new RegionBO();
        regionBO.setId(regionDO.getId());
        regionBO.setName(regionDO.getName());
        regionBO.setParentId(regionDO.getParentId());
        regionBO.setPath(regionDO.getPath());
        regionBO.setLevelEnum(RegionLevelEnum.getEnum(regionDO.getLevel()));
        return regionBO;
    }

    public static RegionDTO coverDTO(RegionBO regionBO) {
        if (regionBO == null) {
            return null;
        }
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setId(regionBO.getId());
        regionDTO.setName(regionBO.getName());
        regionDTO.setParentId(regionBO.getParentId());
        regionDTO.setPath(regionBO.getPath());
        regionDTO.setLevelEnum(regionBO.getLevelEnum());
        regionDTO.setLongitude(regionBO.getLongitude());
        regionDTO.setLatitude(regionBO.getLatitude());
        return regionDTO;
    }
    
    public static RegionPathDTO coverRegionPathDTO(RegionBO regionBO) {
    	RegionPathDTO rtn = null;
    	if (regionBO == null) {
            return null;
        }
        rtn = new RegionPathDTO();
        rtn.setId(regionBO.getId());
        rtn.setName(regionBO.getName());
        rtn.setPath(regionBO.getPath());
        rtn.setParent_id(regionBO.getParentId());
        rtn.setLevel(regionBO.getLevelEnum().val.intValue());
        return rtn;
    }
    
    public static RegionProvinceDTO coverRegionProvinceDTO(RegionBO regionBO) {
    	RegionProvinceDTO rtn = null;
    	if (regionBO == null) {
            return null;
        }
        rtn = new RegionProvinceDTO();
        rtn.setId(regionBO.getId());
        rtn.setName(regionBO.getName());
        rtn.setPath(regionBO.getPath());
        rtn.setParent_id(regionBO.getParentId());
        rtn.setLevel(regionBO.getLevelEnum().val.intValue());
        return rtn;
    }
    
    public static RegionCityDTO coverRegionCityDTO(RegionBO regionBO) {
    	RegionCityDTO rtn = null;
    	if (regionBO == null) {
            return null;
        }
        rtn = new RegionCityDTO();
        rtn.setId(regionBO.getId());
        rtn.setName(regionBO.getName());
        rtn.setPath(regionBO.getPath());
        rtn.setParent_id(regionBO.getParentId());
        rtn.setLevel(regionBO.getLevelEnum().val.intValue());
        return rtn;
    }

    public static List<RegionBO> coverBO(List<RegionDO> regionDOList) {
        List<RegionBO> regionBOList = new ArrayList<>();
        if (regionDOList == null || regionDOList.isEmpty()) {
            return regionBOList;
        }
        for (RegionDO regionDO : regionDOList) {
            RegionBO regionBO = new RegionBO();
            regionBO.setId(regionDO.getId());
            regionBO.setName(regionDO.getName());
            regionBO.setParentId(regionDO.getParentId());
            regionBO.setPath(regionDO.getPath());
            regionBO.setLevelEnum(RegionLevelEnum.getEnum(regionDO.getLevel()));
            regionBOList.add(regionBO);
        }
        return regionBOList;
    }

    public static RegionBO coverBO(RegionDO regionDO) {
        if (regionDO == null) {
            return null;
        }
        RegionBO regionBO = new RegionBO();
        regionBO.setId(regionDO.getId());
        regionBO.setName(regionDO.getName());
        regionBO.setParentId(regionDO.getParentId());
        regionBO.setPath(regionDO.getPath());
        regionBO.setLevelEnum(RegionLevelEnum.getEnum(regionDO.getLevel()));
        regionBO.setLongitude(regionDO.getLongitude());
        regionBO.setLatitude(regionDO.getLatitude());
        return regionBO;
    }

}
