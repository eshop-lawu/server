package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.dto.IndustryTypeDTO;
import com.lawu.eshop.mall.srv.bo.IndustryTypeBO;
import com.lawu.eshop.mall.srv.domain.IndustryTypeDO;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class IndustryTypeConverterTest {

    @Test
    public void convertBO() {
        IndustryTypeDO industryTypeDO = new IndustryTypeDO();
        industryTypeDO.setId(1);
        industryTypeDO.setParentId(new Short("0"));
        industryTypeDO.setPath("44/4043");
        industryTypeDO.setName("test");
        industryTypeDO.setImageUrl("123");
        IndustryTypeBO industryTypeBO = IndustryTypeConverter.convertBO(industryTypeDO);
        Assert.assertEquals(industryTypeDO.getName(), industryTypeBO.getName());
    }

    @Test
    public void convertDTO(){
        IndustryTypeBO industryTypeBO = new IndustryTypeBO();
        industryTypeBO.setId(1);
        industryTypeBO.setParentId(new Short("0"));
        industryTypeBO.setPath("44/4043");
        industryTypeBO.setName("test");
        industryTypeBO.setImageUrl("123");
        IndustryTypeDTO industryTypeDTO = IndustryTypeConverter.convertDTO(industryTypeBO);
        Assert.assertEquals(industryTypeBO.getName(), industryTypeDTO.getName());
    }

    @Test
    public void convertBOS(){
        List<IndustryTypeDO> industryTypeDOList = new ArrayList<>();
        IndustryTypeDO industryTypeDO = new IndustryTypeDO();
        industryTypeDO.setId(1);
        industryTypeDO.setParentId(new Short("0"));
        industryTypeDO.setPath("44/4043");
        industryTypeDO.setName("test");
        industryTypeDO.setImageUrl("123");
        industryTypeDOList.add(industryTypeDO);
        List<IndustryTypeBO> list = IndustryTypeConverter.convertBO(industryTypeDOList);
        Assert.assertEquals(industryTypeDOList.get(0).getName(), list.get(0).getName());
    }

    @Test
    public void convertDTOS(){
        List<IndustryTypeBO> industryTypeBOS = new ArrayList<>();
        IndustryTypeBO industryTypeBO = new IndustryTypeBO();
        industryTypeBO.setId(1);
        industryTypeBO.setParentId(new Short("0"));
        industryTypeBO.setPath("44/4043");
        industryTypeBO.setName("test");
        industryTypeBO.setImageUrl("123");
        industryTypeBOS.add(industryTypeBO);
        List<IndustryTypeDTO> industryTypeDTOS = IndustryTypeConverter.convertDTO(industryTypeBOS);
        Assert.assertEquals(industryTypeBOS.get(0).getName(), industryTypeDTOS.get(0).getName());
    }
}
