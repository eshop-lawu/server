package com.lawu.eshop.property.srv.converter;

import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;
import com.lawu.eshop.property.dto.FreezeDTO;
import com.lawu.eshop.property.dto.PropertyInfoDTO;
import com.lawu.eshop.property.srv.bo.FreezeBO;
import com.lawu.eshop.property.srv.bo.PropertyInfoBO;
import com.lawu.eshop.property.srv.domain.FreezeDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产信息转换器
 *
 * @author meishuquan
 * @date 2017/3/27
 */
public class PropertyInfoConverter {

    public static PropertyInfoBO convertBO(PropertyInfoDO propertyInfoDO ) {
        if (propertyInfoDO == null) {
            return null;
        }

        PropertyInfoBO propertyInfoBO = new PropertyInfoBO();
        propertyInfoBO.setUserNum(propertyInfoDO.getUserNum());
        propertyInfoBO.setBalance(propertyInfoDO.getBalance());
        propertyInfoBO.setPoint(propertyInfoDO.getPoint());
        propertyInfoBO.setLoveAccount(propertyInfoDO.getLoveAccount());
        propertyInfoBO.setFreeze(propertyInfoDO.getFreeze());
        propertyInfoBO.setPayPassword(propertyInfoDO.getPayPassword());
        return  propertyInfoBO;
    }

    public static PropertyInfoDTO convertDTO(PropertyInfoBO propertyInfoBO ) {
        if (propertyInfoBO == null) {
            return null;
        }

        PropertyInfoDTO propertyInfoDTO = new PropertyInfoDTO();
        propertyInfoDTO.setUserNum(propertyInfoBO.getUserNum());
        propertyInfoDTO.setBalance(propertyInfoBO.getBalance());
        propertyInfoDTO.setPoint(propertyInfoBO.getPoint());
        propertyInfoDTO.setLoveAccount(propertyInfoBO.getLoveAccount());
        propertyInfoDTO.setFreeze(PropertyinfoFreezeEnum.getEnum(propertyInfoBO.getFreeze()));
        return  propertyInfoDTO;
    }

    public static List<PropertyInfoBO> convertBO(List<PropertyInfoDO> propertyInfoDOList ) {
        List<PropertyInfoBO> propertyInfoBOS = new ArrayList<>();
        if (propertyInfoDOList == null || propertyInfoDOList.isEmpty()) {
            return propertyInfoBOS;
        }

        for(PropertyInfoDO propertyInfoDO : propertyInfoDOList){
            propertyInfoBOS.add(convertBO(propertyInfoDO));
        }
        return  propertyInfoBOS;
    }

    public static List<PropertyInfoDTO> convertDTO(List<PropertyInfoBO> propertyInfoBOList) {
        List<PropertyInfoDTO> propertyInfoDTOS = new ArrayList<>();
        if (propertyInfoBOList == null || propertyInfoBOList.isEmpty()) {
            return propertyInfoDTOS;
        }

        for(PropertyInfoBO propertyInfoBO : propertyInfoBOList){
            propertyInfoDTOS.add(convertDTO(propertyInfoBO));
        }
        return  propertyInfoDTOS;
    }

    public static List<FreezeBO> freezeConvertBO(List<FreezeDO> freezeDOS) {
        List<FreezeBO> bos = new ArrayList<>();
        if (freezeDOS == null || freezeDOS.isEmpty()) {
            return bos;
        }
        for(FreezeDO fdo : freezeDOS){
            FreezeBO bo = new FreezeBO();
            bo.setOrderNum(fdo.getOrderNum());
            bo.setMoney(fdo.getMoney());
            bo.setGmtCreate(fdo.getGmtCreate());
            bo.setBizId(fdo.getBizId());
            bos.add(bo);
        }
        return bos;
    }

    public static List<FreezeDTO> freezeConvertDTO(List<FreezeBO> records) {
        List<FreezeDTO> dtos = new ArrayList<>();
        if (records == null || records.isEmpty()) {
            return dtos;
        }
        for(FreezeBO bo : records){
            FreezeDTO dto = new FreezeDTO();
            dto.setOrderNum(bo.getOrderNum());
            dto.setMoney(bo.getMoney());
            dto.setGmtCreate(bo.getGmtCreate());
            dto.setBizId(bo.getBizId());
            dtos.add(dto);
        }
        return dtos;
    }
}
