package com.lawu.eshop.mall.srv.converter;

import com.lawu.eshop.mall.dto.IndustryTypeDTO;
import com.lawu.eshop.mall.srv.bo.IndustryTypeBO;
import com.lawu.eshop.mall.srv.domain.IndustryTypeDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/5.
 */
public class IndustryTypeConverter {

    /**
     * BO转换
     *
     * @param industryTypeDO
     * @return
     */
    public static IndustryTypeBO convertBO(IndustryTypeDO industryTypeDO) {
        if (industryTypeDO == null) {
            return null;
        }

        IndustryTypeBO industryTypeBO = new IndustryTypeBO();
        industryTypeBO.setId(industryTypeDO.getId());
        industryTypeBO.setParentId(industryTypeDO.getParentId());
        industryTypeBO.setPath(industryTypeDO.getPath());
        industryTypeBO.setName(industryTypeDO.getName());
        industryTypeBO.setImageUrl(industryTypeDO.getImageUrl());
        return industryTypeBO;
    }

    /**
     * DTO转换
     *
     * @param industryTypeBO
     * @return
     */
    public static IndustryTypeDTO convertDTO(IndustryTypeBO industryTypeBO) {
        if (industryTypeBO == null) {
            return null;
        }

        IndustryTypeDTO industryTypeDTO = new IndustryTypeDTO();
        industryTypeDTO.setId(industryTypeBO.getId());
        industryTypeDTO.setParentId(industryTypeBO.getParentId());
        industryTypeDTO.setPath(industryTypeBO.getPath());
        industryTypeDTO.setName(industryTypeBO.getName());
        industryTypeDTO.setImageUrl(industryTypeBO.getImageUrl());
        return industryTypeDTO;
    }

    /**
     * BO转换
     *
     * @param industryTypeDOList
     * @return
     */
    public static List<IndustryTypeBO> convertBO(List<IndustryTypeDO> industryTypeDOList) {
        List<IndustryTypeBO> industryTypeBOS = new ArrayList<IndustryTypeBO>();
        if (industryTypeDOList == null || industryTypeDOList.isEmpty()) {
            return industryTypeBOS;
        }

        for (IndustryTypeDO industryTypeDO : industryTypeDOList) {
            IndustryTypeBO industryTypeBO = new IndustryTypeBO();
            industryTypeBO.setId(industryTypeDO.getId());
            industryTypeBO.setParentId(industryTypeDO.getParentId());
            industryTypeBO.setPath(industryTypeDO.getPath());
            industryTypeBO.setName(industryTypeDO.getName());
            industryTypeBO.setImageUrl(industryTypeDO.getImageUrl());
            industryTypeBOS.add(industryTypeBO);
        }
        return industryTypeBOS;
    }

    /**
     * DTO转换
     *
     * @param industryTypeBOS
     * @return
     */
    public static List<IndustryTypeDTO> convertDTO(List<IndustryTypeBO> industryTypeBOS) {
        List<IndustryTypeDTO> industryTypeDTOS = new ArrayList<IndustryTypeDTO>();
        if (industryTypeBOS == null || industryTypeBOS.isEmpty()) {
            return industryTypeDTOS;
        }

        for (IndustryTypeBO industryTypeBO : industryTypeBOS) {
            IndustryTypeDTO industryTypeDTO = new IndustryTypeDTO();
            industryTypeDTO.setId(industryTypeBO.getId());
            industryTypeDTO.setParentId(industryTypeBO.getParentId());
            industryTypeDTO.setPath(industryTypeBO.getPath());
            industryTypeDTO.setName(industryTypeBO.getName());
            industryTypeDTO.setImageUrl(industryTypeBO.getImageUrl());
            industryTypeDTOS.add(industryTypeDTO);
        }
        return industryTypeDTOS;
    }
}
