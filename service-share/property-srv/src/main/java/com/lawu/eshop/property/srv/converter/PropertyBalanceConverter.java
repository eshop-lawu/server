package com.lawu.eshop.property.srv.converter;

import java.math.BigDecimal;

import com.lawu.eshop.property.dto.PropertyBalanceDTO;
import com.lawu.eshop.property.srv.bo.PropertyBalanceBO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;

/**
 * 资产余额转换器
 * @author Sunny
 * @date 2017/3/31
 */
public class PropertyBalanceConverter {
	private PropertyBalanceConverter() {
		throw new IllegalAccessError("Utility class");
	}
	/**
	 * PropertyBalanceBO转换器
	 * @param propertyInfoDO
	 * @return
	 */
    public static PropertyBalanceBO convert(PropertyInfoDO propertyInfoDO) {
        if (propertyInfoDO == null) {
            return null;
        }
        PropertyBalanceBO propertyBalanceBO = new PropertyBalanceBO();
        propertyBalanceBO.setBalance(propertyInfoDO.getBalance());
        propertyBalanceBO.setFreeze(propertyInfoDO.getFreezeMoney());
        return  propertyBalanceBO;
    }
    
    /**
     * PropertyBalanceDTO转换器
     * @param propertyBalanceBO
     * @return
     */
    public static PropertyBalanceDTO convert(PropertyBalanceBO propertyBalanceBO) {
        if (propertyBalanceBO == null) {
        	PropertyBalanceDTO dto = new PropertyBalanceDTO();
        	dto.setBalance(new BigDecimal("0"));
            return dto;
        }
        PropertyBalanceDTO propertyBalanceDTO = new PropertyBalanceDTO();
        propertyBalanceDTO.setBalance(propertyBalanceBO.getBalance().setScale(2,BigDecimal.ROUND_DOWN));
        propertyBalanceDTO.setFreeze(propertyBalanceBO.getFreeze().setScale(2,BigDecimal.ROUND_DOWN));
        return  propertyBalanceDTO;
    }
}
