package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.PropertyDTO;
import com.lawu.eshop.user.srv.bo.PropertyBO;
import com.lawu.eshop.user.srv.domain.PropertyDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
public class PropertyConverter {

    /**
     * BO转换
     *
     * @param propertyDO
     * @return
     */
    public static PropertyBO convertBO(PropertyDO propertyDO) {
        if (propertyDO == null) {
            return null;
        }

        PropertyBO propertyBO = new PropertyBO();
        propertyBO.setId(propertyDO.getId());
        propertyBO.setName(propertyDO.getName());
        propertyBO.setValue(propertyDO.getValue());
        propertyBO.setRemark(propertyDO.getRemark());
        propertyBO.setGmtModified(propertyDO.getGmtModified());
        propertyBO.setGmtCreate(propertyDO.getGmtCreate());
        return propertyBO;
    }

    /**
     * BO转换
     *
     * @param propertyDOS
     * @return
     */
    public static List<PropertyBO> convertBO(List<PropertyDO> propertyDOS) {
        List<PropertyBO> propertyBOS = new ArrayList<PropertyBO>();
        if (propertyDOS == null || propertyDOS.isEmpty()) {
            return propertyBOS;
        }

        for (PropertyDO propertyDO : propertyDOS) {
            PropertyBO propertyBO = new PropertyBO();
            propertyBO.setId(propertyDO.getId());
            propertyBO.setName(propertyDO.getName());
            propertyBO.setValue(propertyDO.getValue());
            propertyBO.setRemark(propertyDO.getRemark());
            propertyBO.setGmtModified(propertyDO.getGmtModified());
            propertyBO.setGmtCreate(propertyDO.getGmtCreate());
            propertyBOS.add(propertyBO);
        }
        return propertyBOS;
    }

    /**
     * DTO转换
     *
     * @param propertyBO
     * @return
     */
    public static PropertyDTO convertDTO(PropertyBO propertyBO) {
        if (propertyBO == null) {
            return null;
        }

        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(propertyBO.getId());
        propertyDTO.setName(propertyBO.getName());
        propertyDTO.setValue(propertyBO.getValue());
        propertyDTO.setRemark(propertyBO.getRemark());
        propertyDTO.setGmtModified(propertyBO.getGmtModified());
        propertyDTO.setGmtCreate(propertyBO.getGmtCreate());
        return propertyDTO;
    }

    /**
     * DTO转换
     *
     * @param propertyBOS
     * @return
     */
    public static List<PropertyDTO> convertDTO(List<PropertyBO> propertyBOS) {
        List<PropertyDTO> propertyDTOS = new ArrayList<PropertyDTO>();
        if (propertyBOS == null || propertyBOS.isEmpty()) {
            return propertyDTOS;
        }

        for (PropertyBO propertyBO : propertyBOS) {
            PropertyDTO propertyDTO = new PropertyDTO();
            propertyDTO.setId(propertyBO.getId());
            propertyDTO.setName(propertyBO.getName());
            propertyDTO.setValue(propertyBO.getValue());
            propertyDTO.setRemark(propertyBO.getRemark());
            propertyDTO.setGmtModified(propertyBO.getGmtModified());
            propertyDTO.setGmtCreate(propertyBO.getGmtCreate());
            propertyDTOS.add(propertyDTO);
        }
        return propertyDTOS;
    }
}
