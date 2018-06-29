package com.lawu.eshop.mall.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.constants.RegionLevelEnum;
import com.lawu.eshop.mall.dto.RegionDTO;
import com.lawu.eshop.mall.srv.bo.RegionBO;
import com.lawu.eshop.mall.srv.domain.RegionDO;
import com.lawu.eshop.mall.srv.domain.extend.RegionDOView;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class RegionConverterTest {

    @Test
    public void coverBO() {
        RegionDOView regionDOView = new RegionDOView();
        regionDOView.setId(1);
        regionDOView.setName("TEST");
        regionDOView.setParentId(1);
        regionDOView.setPath("44/4404");
        regionDOView.setLevel(RegionLevelEnum.REGION_LEVEL_ONE.val);

        RegionBO regionBO = RegionConverter.coverBO(regionDOView);
        Assert.assertEquals(regionDOView.getName(), regionBO.getName());
    }

    @Test
    public void coverDTO() {
        RegionBO regionBO = new RegionBO();
        regionBO.setId(1);
        regionBO.setName("test");
        regionBO.setParentId(1);
        regionBO.setPath("11/11");
        regionBO.setLevelEnum(RegionLevelEnum.REGION_LEVEL_ONE);
        regionBO.setLongitude(BigDecimal.TEN);
        regionBO.setLatitude(BigDecimal.ONE);
        RegionDTO regionDTO = RegionConverter.coverDTO(regionBO);
        Assert.assertEquals(regionBO.getName(), regionDTO.getName());
    }

    @Test
    public void coverBOS() {
        List<RegionDO> regionDOList = new ArrayList<>();
        RegionDO regionDO = new RegionDO();
        regionDO.setId(1);
        regionDO.setName("test");
        regionDO.setParentId(1);
        regionDO.setPath("11/11");
        regionDO.setLevel(RegionLevelEnum.REGION_LEVEL_ONE.val);
        regionDO.setLongitude(BigDecimal.TEN);
        regionDO.setLatitude(BigDecimal.ONE);
        regionDOList.add(regionDO);
        List<RegionBO> list = RegionConverter.coverBO(regionDOList);
        Assert.assertEquals(regionDOList.get(0).getName(), list.get(0).getName());
    }

    @Test
    public void coverBO2() {
        RegionDO regionDO = new RegionDO();
        regionDO.setId(1);
        regionDO.setName("test");
        regionDO.setParentId(1);
        regionDO.setPath("11/11");
        regionDO.setLevel(RegionLevelEnum.REGION_LEVEL_ONE.val);
        regionDO.setLongitude(BigDecimal.TEN);
        regionDO.setLatitude(BigDecimal.ONE);

        RegionBO regionBO = RegionConverter.coverBO(regionDO);
        Assert.assertEquals(regionDO.getName(), regionBO.getName());

    }

}
