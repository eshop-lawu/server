package com.lawu.eshop.user.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.user.dto.MerchantInviterDTO;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.srv.bo.MerchantInviterBO;
import com.lawu.eshop.user.srv.domain.extend.InviterMerchantDOView;
import com.lawu.framework.core.page.Page;

public class MerchantInviterConverter {
	/**
	 * 商家收藏展示信息转化
	 * @param merchantDOS
	 * @param storeList
	 * @return
	 */
	public static List<MerchantInviterBO> convertMerchantInviterBOS(List<InviterMerchantDOView> inviterMerchantDOS) {
		if(inviterMerchantDOS==null){
			return null;
		}
		List<MerchantInviterBO> FIBOS=new ArrayList<MerchantInviterBO>();
		for (InviterMerchantDOView inviterMerchantDO : inviterMerchantDOS) {
			MerchantInviterBO FIO=new MerchantInviterBO();
			FIO.setAccount(inviterMerchantDO.getAccount());
			FIO.setName(inviterMerchantDO.getName());
			FIO.setPrincipalName(inviterMerchantDO.getPrincipalName());
			FIO.setRegionName(inviterMerchantDO.getRegionName());
			FIO.setGmtCreate(inviterMerchantDO.getGmtCreate());
			FIO.setStatusEnum(MerchantStatusEnum.getEnum(inviterMerchantDO.getStatus()));
			FIO.setPath(inviterMerchantDO.getPath());
			FIO.setAddress(inviterMerchantDO.getAddress());
			FIO.setInviterCount(inviterMerchantDO.getInviterCount());
			FIBOS.add(FIO);
			
		}
		return FIBOS;
	}
	
	/**
     * DTO转换
     *
     * @param merchantInviterBO
     * @return
     */
    public static MerchantInviterDTO convertMDTO(MerchantInviterBO merchantInviterBO) {
        if (merchantInviterBO == null) {
            return null;
        }
       
        MerchantInviterDTO DTO = new MerchantInviterDTO();
        DTO.setAccount(merchantInviterBO.getAccount().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
        DTO.setRegionName(merchantInviterBO.getRegionName());
        DTO.setAddress(merchantInviterBO.getAddress());
        DTO.setGmtCreate(merchantInviterBO.getGmtCreate());
        if(merchantInviterBO.getName()==null){
        	 DTO.setName("E店商家");
        }else{
        	 DTO.setName(merchantInviterBO.getName());
        }
        if(merchantInviterBO.getPrincipalName()==null){
       	     DTO.setPrincipalName("E店商家");
        }else{
    	     DTO.setPrincipalName(merchantInviterBO.getPrincipalName());
        }
        if(merchantInviterBO.getStatusEnum()==null){
        	 DTO.setStatusEnum(MerchantStatusEnum.MERCHANT_STATUS_UNCHECK);
        }else{
        	 DTO.setStatusEnum(merchantInviterBO.getStatusEnum());
        }
       
        DTO.setPath(merchantInviterBO.getPath());
        DTO.setInviterCount(merchantInviterBO.getInviterCount());
        DTO.setAddress(merchantInviterBO.getAddress());
        return DTO;
    }
	
    /**
     * BOS 转DTOS
     * @param merchantInviterBOS
     * @return
     */
	public static List<MerchantInviterDTO> convertMIListDOTS(List<MerchantInviterBO> merchantInviterBOS) {
		if (merchantInviterBOS == null) {
		       return null;
		    }
			List<MerchantInviterDTO> DTOS=new ArrayList<MerchantInviterDTO>();
			for (MerchantInviterBO merchantInviterBO : merchantInviterBOS) {
				DTOS.add(convertMDTO(merchantInviterBO));
			}
		return DTOS;
	}

	/**
	 * pageBO 转 pageDO
	 * @param pageBO
	 * @return
	 */
	public static Page<MerchantInviterDTO> convertPageMIDOTS(Page<MerchantInviterBO> pageBO) {
		Page<MerchantInviterDTO> pageDTO=new Page<MerchantInviterDTO>();
		List<MerchantInviterBO> BOS=pageBO.getRecords();
		pageDTO.setRecords(convertMIListDOTS(BOS));
		pageDTO.setTotalCount(pageBO.getTotalCount());
		pageDTO.setCurrentPage(pageBO.getCurrentPage());
		return pageDTO;
	}

}
