package com.lawu.eshop.property.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.property.dto.BankDTO;
import com.lawu.eshop.property.srv.bo.BankBO;
import com.lawu.eshop.property.srv.domain.BankDO;

/**
 * 银行信息实体转化
 * @author Administrator
 *
 */
public class BankConverter {
	
	/**
	 * DO转BO
	 * @param bankDO
	 * @return
	 */
	public static BankBO convertBO(BankDO bankDO ) {
        if (bankDO == null) {
            return null;
        }
        BankBO bankBO=new BankBO();
        bankBO.setId(bankDO.getId());
        bankBO.setName(bankDO.getName());
        bankBO.setIconUrl(bankDO.getIconUrl());
        return bankBO;
    }
	
	/**
	 * DOS 转BOS
	 * @param bankDOS
	 * @return
	 */
	public static List<BankBO> convertBO(List<BankDO> bankDOS ) {
        if (bankDOS == null) {
            return null;
        }
        List<BankBO> BOS=new ArrayList<BankBO>();
        for (BankDO bankDO: bankDOS) {
        	BOS.add(convertBO(bankDO));
		}
        
        return BOS;
    }
	
	/**
	 * BO转DTO
	 * @param bankBO
	 * @return
	 */
	public static BankDTO convertDTO(BankBO bankBO ) {
        if (bankBO == null) {
            return null;
        }
        BankDTO bankDTO=new BankDTO();
        bankDTO.setId(bankBO.getId());
        bankDTO.setName(bankBO.getName());
        bankDTO.setIconUrl(bankBO.getIconUrl());
        return bankDTO;
    }
	
	/**
	 * BOS 转DTOS
	 * @param bankBOS
	 * @return
	 */
	public static List<BankDTO> convertDTOS(List<BankBO> bankBOS ) {
        if (bankBOS == null) {
            return null;
        }
        List<BankDTO> DTOS=new ArrayList<BankDTO>();
        for (BankBO bankBO: bankBOS) {
        	DTOS.add(convertDTO(bankBO));
		}
        
        return DTOS;
    }
	

}
