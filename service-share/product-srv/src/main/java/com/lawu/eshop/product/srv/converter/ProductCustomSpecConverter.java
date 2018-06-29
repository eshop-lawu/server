package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.product.constant.CustomSpecStatusEnum;
import com.lawu.eshop.product.dto.ProductCustomSpecDTO;
import com.lawu.eshop.product.srv.bo.ProductCustomSpecBO;
import com.lawu.eshop.product.srv.domain.ProductCustomSpecDO;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月16日
 */
public class ProductCustomSpecConverter {

	
	public static List<ProductCustomSpecBO> convertBOS(List<ProductCustomSpecDO> list) {
		List<ProductCustomSpecBO> records = new ArrayList<>();
		if(list.isEmpty()){
			return records;
		}
		for (ProductCustomSpecDO ProductCustomSpecDO : list) {
			ProductCustomSpecBO BO = convertBO(ProductCustomSpecDO);
			records.add(BO);
		}
		return records;
	}
	
	
	public static ProductCustomSpecBO convertBO(ProductCustomSpecDO record) {
		ProductCustomSpecBO resouse = new ProductCustomSpecBO();
		if(record == null){
			return resouse;
		}
		resouse.setId(record.getId());
		resouse.setRelateId(record.getId());
		resouse.setRelateName(record.getRelateName());
		resouse.setSpecName(record.getSpecName());
		resouse.setStatus(CustomSpecStatusEnum.getEnum(record.getStatus()));
		return resouse;
	}
	
	
	public static List<ProductCustomSpecDTO> convertDTOS(List<ProductCustomSpecBO> list) {
		List<ProductCustomSpecDTO> records = new ArrayList<>();
		if(list.isEmpty()){
			return records;
		}
		for (ProductCustomSpecBO productCustomSpecBO : list) {
			ProductCustomSpecDTO DTO = convertDTO(productCustomSpecBO);
			records.add(DTO);
		}
		return records;
	}
	
	
	public static ProductCustomSpecDTO convertDTO(ProductCustomSpecBO record) {
		ProductCustomSpecDTO resouse = new ProductCustomSpecDTO();
		if(record == null){
			return resouse;
		}
		resouse.setId(record.getId());
		resouse.setRelateId(record.getId());
		resouse.setRelateName(record.getRelateName());
		resouse.setSpecName(record.getSpecName());
		resouse.setStatus(record.getStatus());
		return resouse;
	}

}
