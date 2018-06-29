package com.lawu.eshop.mall.srv.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.constants.RegionLevelEnum;
import com.lawu.eshop.mall.dto.RegionCityDTO;
import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.mall.dto.RegionPathDTO;
import com.lawu.eshop.mall.dto.RegionProvinceDTO;
import com.lawu.eshop.mall.srv.bo.RegionBO;
import com.lawu.eshop.mall.srv.bo.RegionSelectorDataBO;
import com.lawu.eshop.mall.srv.converter.RegionConverter;
import com.lawu.eshop.mall.srv.service.RegionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.LonLatUtil;
import com.lawu.utils.PinyinUtil;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
@RestController
@RequestMapping(value = "region/")
public class RegionController extends BaseController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "getRegionList", method = RequestMethod.GET)
    public Result<List<RegionDTO>> getRegionList() {
        List<RegionBO> regionBOS = regionService.getRegionList();
        if (regionBOS.isEmpty()) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        List<RegionDTO> list = new ArrayList<>();
        for (RegionBO regionBO : regionBOS) {
            RegionDTO regionDTO = RegionConverter.coverDTO(regionBO);
            list.add(regionDTO);
        }
        return successGet(list);
    }
    
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<List<RegionPathDTO>> list() {
    	List<RegionBO> regionBOS = regionService.getRegionList();
        if (regionBOS.isEmpty()) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        List<RegionPathDTO> list = new ArrayList<>();
        for (RegionBO regionBO : regionBOS) {
        	RegionPathDTO regionDTO = RegionConverter.coverRegionPathDTO(regionBO);
            list.add(regionDTO);
        }
        return successGet(list);
    }
    
    @RequestMapping(value = "group", method = RequestMethod.GET)
    public Result<List<RegionProvinceDTO>> group() {
        List<RegionBO> regionBOS = regionService.getRegionList();
        if (regionBOS.isEmpty()) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        
        // 一级
        Map<Integer, RegionProvinceDTO> region1 = new LinkedHashMap<>();
        // 二级
        Map<Integer, List<RegionCityDTO>> region2 = new LinkedHashMap<>();
        // 三级
        Map<Integer, List<RegionPathDTO>> region3 = new LinkedHashMap<>();
        for (RegionBO regionBO : regionBOS) {
            if (regionBO.getLevelEnum().equals(RegionLevelEnum.REGION_LEVEL_ONE)) {
            	RegionProvinceDTO regionProvinceDTO = RegionConverter.coverRegionProvinceDTO(regionBO);
            	region1.put(regionBO.getId(), regionProvinceDTO);
            } else if (regionBO.getLevelEnum().equals(RegionLevelEnum.REGION_LEVEL_TWO)) {
            	if (region2.get(regionBO.getParentId()) == null) {
            		region2.put(regionBO.getParentId(), new ArrayList<>());
            	}
            	RegionCityDTO regionCityDTO = RegionConverter.coverRegionCityDTO(regionBO);
            	region2.get(regionBO.getParentId()).add(regionCityDTO);
            } else if (regionBO.getLevelEnum().equals(RegionLevelEnum.REGION_LEVEL_THREE)) {
            	if (region3.get(regionBO.getParentId()) == null) {
            		region3.put(regionBO.getParentId(), new ArrayList<>());
            	}
            	RegionPathDTO regionPathDTO = RegionConverter.coverRegionProvinceDTO(regionBO);
            	region3.get(regionBO.getParentId()).add(regionPathDTO);
            }
        }
        
        List<RegionProvinceDTO> rtn = new ArrayList<>();
        for (Map.Entry<Integer, RegionProvinceDTO> entry : region1.entrySet()) {
        	RegionProvinceDTO regionProvinceDTO = entry.getValue();
        	List<RegionCityDTO> regionCityDTOList = region2.get(regionProvinceDTO.getId());
        	for (RegionCityDTO regionCityDTO : regionCityDTOList) {
        		List<RegionPathDTO> regionPathDTOList = region3.get(regionCityDTO.getId());
        		regionCityDTO.setArea(regionPathDTOList);
        	}
        	regionProvinceDTO.setCity(regionCityDTOList);
        	rtn.add(regionProvinceDTO);
        }
        return successGet(rtn);
    }

    /**
     * 根据区域ID查询区域完整名称
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getRegionFullName/{id}", method = RequestMethod.GET)
    public Result<String> getRegionFullName(@PathVariable Integer id) {
        return successGet(regionService.getRegionFullName(id));
    }

    /**
     * 根据区域路径查询区域名称
     *
     * @param regionPath
     * @return
     */
    @RequestMapping(value = "getAreaName", method = RequestMethod.GET)
    public Result<String> getAreaName(@RequestParam String regionPath) {
        String areaPath = regionService.getAreaName(regionPath);
        return successGet(areaPath);
    }

    /**
     * 查询城市列表
     *
     * @return
     */
    @RequestMapping(value = "getRegionLevelTwo", method = RequestMethod.GET)
    public Result<List<RegionDTO>> getRegionLevelTwo() {
        List<RegionDTO> regionDTOS = new ArrayList<>();
        List<RegionBO> regionBOS = regionService.getRegionLevelTwo();
        if (regionBOS.isEmpty()) {
            return successGet(regionDTOS);
        }
        for (RegionBO regionBO : regionBOS) {
            RegionDTO regionDTO = RegionConverter.coverDTO(regionBO);
            regionDTOS.add(regionDTO);
        }
        return successGet(regionDTOS);
    }

    /**
     * 根据区域ID查询区域信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getRegion/{id}", method = RequestMethod.GET)
    public Result<RegionDTO> getRegion(@PathVariable Integer id) {
        RegionBO regionBO = regionService.getRegionById(id);
        return successGet(RegionConverter.coverDTO(regionBO));
    }

    /**
     * 更新区域表经纬度
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "updateRegionLonLat", method = RequestMethod.GET)
    public Result updateRegionLonLat() {
        List<RegionBO> regionBOS = regionService.getRegionLevelTwo();
        if (!regionBOS.isEmpty()) {
            for (RegionBO regionBO : regionBOS) {
                Map<String, String> map = LonLatUtil.getLonLat(regionBO.getName());
                if (map != null) {
                    BigDecimal lon = new BigDecimal(map.get("lng"));
                    BigDecimal lat = new BigDecimal(map.get("lat"));
                    regionService.updateRegionLonLat(regionBO.getId(), lon.setScale(7, BigDecimal.ROUND_HALF_UP), lat.setScale(7, BigDecimal.ROUND_HALF_UP));
                }
            }
        }
        return successGet();
    }

    @RequestMapping(value = "getRegionSelectorData", method = RequestMethod.GET)
    public Result<JSONObject> getRegionSelectorData() {
    	JSONArray A_G = new JSONArray();
		JSONArray H_K = new JSONArray();
		JSONArray L_S = new JSONArray();
		JSONArray T_Z = new JSONArray();
		Map<String, JSONObject> city = new LinkedHashMap<>();
		
		List<RegionBO> regionBOS = regionService.getRegionList();
        if (regionBOS.isEmpty()) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        for (RegionBO regionBO : regionBOS) {
            if (regionBO.getLevelEnum().equals(RegionLevelEnum.REGION_LEVEL_ONE)) {
            	RegionSelectorDataBO regionSelectorDataBO = new RegionSelectorDataBO();
            	regionSelectorDataBO.setAddress(regionBO.getName());
        		regionSelectorDataBO.setCode(regionBO.getId().toString());
            	char headChar = PinyinUtil.getPinYinHeadChar(regionBO.getName()).toUpperCase().charAt(0);
            	if (Integer.valueOf('A') <= Integer.valueOf(headChar) && Integer.valueOf(headChar) <= Integer.valueOf('G')) {
            		A_G.add(regionSelectorDataBO);
            	} else if (Integer.valueOf('H') <= Integer.valueOf(headChar) && Integer.valueOf(headChar) <= Integer.valueOf('K')) {
            		H_K.add(regionSelectorDataBO);
            	} else if (Integer.valueOf('L') <= Integer.valueOf(headChar) && Integer.valueOf(headChar) <= Integer.valueOf('S')) {
            		L_S.add(regionSelectorDataBO);
            	} else if (Integer.valueOf('T') <= Integer.valueOf(headChar) && Integer.valueOf(headChar) <= Integer.valueOf('Z')) {
            		T_Z.add(regionSelectorDataBO);
            	}
            } else if (regionBO.getLevelEnum().equals(RegionLevelEnum.REGION_LEVEL_TWO) || regionBO.getLevelEnum().equals(RegionLevelEnum.REGION_LEVEL_THREE)) {
            	if (city.get(regionBO.getParentId().toString()) == null) {
            		city.put(regionBO.getParentId().toString(), new JSONObject());
            	}
            	city.get(regionBO.getParentId().toString()).put(regionBO.getId().toString(), regionBO.getName());
            }
        }
        JSONObject json = new JSONObject(city.size() + 4, true);
        for (Map.Entry<String, JSONObject> entry : city.entrySet()) {
        	json.put(entry.getKey(), entry.getValue());
        }
        JSONObject group = new JSONObject(4, true);
        group.put("A-G", A_G);
        group.put("L-S", L_S);
        group.put("H-K", H_K);
        group.put("T-Z", T_Z);
        json.put("86", group);
        return successGet(json);
    }

    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public Result<String> getById(@RequestParam Long id) {
        RegionBO regionBO = regionService.getRegionById(id.intValue());
        return successGet(regionBO.getName());
    }
}
