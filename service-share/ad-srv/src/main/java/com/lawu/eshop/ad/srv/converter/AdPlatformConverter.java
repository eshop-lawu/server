package com.lawu.eshop.ad.srv.converter;

import com.lawu.eshop.ad.constants.AdPlatformStatusEnum;
import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.dto.AdPlatformDTO;
import com.lawu.eshop.ad.dto.AdPlatformOperatorDTO;
import com.lawu.eshop.ad.dto.AdPlatformProductDTO;
import com.lawu.eshop.ad.srv.bo.AdPlatformBO;
import com.lawu.eshop.ad.srv.domain.AdPlatformDO;
import com.lawu.eshop.ad.srv.domain.extend.AdPlatformDOView;

import java.util.ArrayList;
import java.util.List;

/**
 * 平台广告实体转化
 *
 * @author zhangrc
 * @date 2017/3/5
 */
public class AdPlatformConverter {

    /**
     * DO转BO
     *
     * @param adPlatformDO
     * @return
     */
    public static AdPlatformBO convertBO(AdPlatformDO adPlatformDO) {
        if (adPlatformDO == null) {
            return null;
        }
        AdPlatformBO adPlatformBO = new AdPlatformBO();
        adPlatformBO.setId(adPlatformDO.getId());
        adPlatformBO.setTitle(adPlatformDO.getTitle());
        adPlatformBO.setType(adPlatformDO.getType());
        adPlatformBO.setContent(adPlatformDO.getContent());
        adPlatformBO.setMediaUrl(adPlatformDO.getMediaUrl());
        adPlatformBO.setTypeEnum(TypeEnum.getEnum(adPlatformDO.getType()));
        adPlatformBO.setPositionEnum(PositionEnum.getEnum(adPlatformDO.getPosition()));
        adPlatformBO.setMerchantStoreId(adPlatformDO.getMerchantStoreId());
        adPlatformBO.setLinkUrl(adPlatformDO.getLinkUrl());
        adPlatformBO.setProductId(adPlatformDO.getProductId());
        adPlatformBO.setRegionPath(adPlatformDO.getRegionPath());
        adPlatformBO.setAdId(adPlatformDO.getAdId());
        adPlatformBO.setRegionName(adPlatformDO.getRegionName());
        return adPlatformBO;

    }

    /**
     * BO 转DTO
     *
     * @param adPlatformBO
     * @return
     */
    public static AdPlatformDTO convertDTO(AdPlatformBO adPlatformBO) {
        if (adPlatformBO == null) {
            return null;
        }
        AdPlatformDTO adPlatformDTO = new AdPlatformDTO();
        adPlatformDTO.setId(adPlatformBO.getId());
        adPlatformDTO.setTitle(adPlatformBO.getTitle());
        adPlatformDTO.setMediaUrl(adPlatformBO.getMediaUrl());
        adPlatformDTO.setContent(adPlatformBO.getContent());
        if (adPlatformBO.getType() == 1) {
            adPlatformDTO.setLinkUrl(adPlatformBO.getLinkUrl());
        } else {
            adPlatformDTO.setProductId(adPlatformBO.getProductId());
        }
        return adPlatformDTO;
    }

    /**
     * DOS转BOS
     *
     * @param list
     * @return
     */
    public static List<AdPlatformBO> convertBOS(List<AdPlatformDO> list) {
        List<AdPlatformBO> BOS = new ArrayList<AdPlatformBO>();
        if (list == null || list.isEmpty()) {
            return BOS;
        }

        for (AdPlatformDO adPlatformDO : list) {
            AdPlatformBO adPlatformBO = new AdPlatformBO();
            adPlatformBO.setId(adPlatformDO.getId());
            adPlatformBO.setTitle(adPlatformDO.getTitle());
            adPlatformBO.setType(adPlatformDO.getType());
            adPlatformBO.setMediaUrl(adPlatformDO.getMediaUrl());
            adPlatformBO.setContent(adPlatformDO.getContent());
            adPlatformBO.setTypeEnum(TypeEnum.getEnum(adPlatformDO.getType()));
            adPlatformBO.setPositionEnum(PositionEnum.getEnum(adPlatformDO.getPosition()));
            adPlatformBO.setStatus(adPlatformDO.getStatus());
            adPlatformBO.setGmtCreate(adPlatformDO.getGmtCreate());
            adPlatformBO.setMerchantStoreId(adPlatformDO.getMerchantStoreId());
            adPlatformBO.setLinkUrl(adPlatformDO.getLinkUrl());
            adPlatformBO.setProductId(adPlatformDO.getProductId());
            adPlatformBO.setAdId(adPlatformDO.getAdId());
            adPlatformBO.setPriority(adPlatformDO.getPriority());
            BOS.add(adPlatformBO);
        }
        return BOS;

    }

    /**
     * BOS 转DTOS
     *
     * @param bOS
     * @return
     */
    public static List<AdPlatformDTO> convertDTOS(List<AdPlatformBO> bOS) {
        List<AdPlatformDTO> DTOS = new ArrayList<AdPlatformDTO>();
        if (bOS == null || bOS.isEmpty()) {
            return DTOS;
        }

        for (AdPlatformBO adPlatformBO : bOS) {
            AdPlatformDTO adPlatformDTO = new AdPlatformDTO();
            adPlatformDTO.setId(adPlatformBO.getId());
            adPlatformDTO.setTitle(adPlatformBO.getTitle());
            adPlatformDTO.setMediaUrl(adPlatformBO.getMediaUrl());
            adPlatformDTO.setContent(adPlatformBO.getContent());
            adPlatformDTO.setMerchantStoreId(adPlatformBO.getMerchantStoreId());
            adPlatformDTO.setProductId(adPlatformBO.getProductId());
            adPlatformDTO.setLinkUrl(adPlatformBO.getLinkUrl());
            DTOS.add(adPlatformDTO);
        }
        return DTOS;
    }
    
    
    /**
     * BOS 转DTOS
     *
     * @param bOS
     * @return
     */
    public static List<AdPlatformOperatorDTO> convertOperatorDTOS(List<AdPlatformBO> bOS) {
        List<AdPlatformOperatorDTO> DTOS = new ArrayList<AdPlatformOperatorDTO>();
        if (bOS == null || bOS.isEmpty()) {
            return DTOS;
        }
        for (AdPlatformBO adPlatformBO : bOS) {
        	AdPlatformOperatorDTO adPlatformDTO = new AdPlatformOperatorDTO();
            adPlatformDTO.setId(adPlatformBO.getId());
            adPlatformDTO.setTitle(adPlatformBO.getTitle());
            adPlatformDTO.setMediaUrl(adPlatformBO.getMediaUrl());
            adPlatformDTO.setContent(adPlatformBO.getContent());
            adPlatformDTO.setMerchantStoreId(adPlatformBO.getMerchantStoreId());
            adPlatformDTO.setTypeEnum(adPlatformBO.getTypeEnum());
            adPlatformDTO.setPositionEnum(adPlatformBO.getPositionEnum());
            adPlatformDTO.setStatusEnum(AdPlatformStatusEnum.getEnum(adPlatformBO.getStatus()));
            adPlatformDTO.setGmtCreate(adPlatformBO.getGmtCreate());
            adPlatformDTO.setLinkUrl(adPlatformBO.getLinkUrl());
            adPlatformDTO.setProductId(adPlatformBO.getProductId());
            adPlatformDTO.setAdId(adPlatformBO.getAdId());
            adPlatformDTO.setPriority(adPlatformBO.getPriority());
            DTOS.add(adPlatformDTO);
        }
        return DTOS;
    }
    
    
    public static AdPlatformOperatorDTO convertOperatorDTO(AdPlatformBO adPlatformBO) {
    	AdPlatformOperatorDTO adPlatformDTO = new AdPlatformOperatorDTO();
        if (adPlatformBO == null ) {
            return adPlatformDTO;
        }
        adPlatformDTO.setId(adPlatformBO.getId());
        adPlatformDTO.setTitle(adPlatformBO.getTitle());
        adPlatformDTO.setMediaUrl(adPlatformBO.getMediaUrl());
        adPlatformDTO.setContent(adPlatformBO.getContent());
        adPlatformDTO.setMerchantStoreId(adPlatformBO.getMerchantStoreId());
        adPlatformDTO.setPositionEnum(adPlatformBO.getPositionEnum());
        adPlatformDTO.setTypeEnum(adPlatformBO.getTypeEnum());
        adPlatformDTO.setMerchantStoreId(adPlatformBO.getMerchantStoreId());
        adPlatformDTO.setLinkUrl(adPlatformBO.getLinkUrl());
        adPlatformDTO.setProductId(adPlatformBO.getProductId());
        adPlatformDTO.setRegionPath(adPlatformBO.getRegionPath());
        adPlatformDTO.setAdId(adPlatformBO.getAdId());
        adPlatformDTO.setRegionName(adPlatformBO.getRegionName());
        return adPlatformDTO;
    }

    /**
     * AdPlatformDOView转BOS
     *
     * @param adPlatformDOViews
     * @return
     */
    public static List<AdPlatformBO> convertBO(List<AdPlatformDOView> adPlatformDOViews) {
        List<AdPlatformBO> BOS = new ArrayList<AdPlatformBO>();
        if (adPlatformDOViews == null || adPlatformDOViews.isEmpty()) {
            return BOS;
        }

        for (AdPlatformDOView adPlatformDOView : adPlatformDOViews) {
            AdPlatformBO adPlatformBO = new AdPlatformBO();
            adPlatformBO.setProductId(adPlatformDOView.getProductId());
            adPlatformBO.setMediaUrl(adPlatformDOView.getMediaUrl());
            adPlatformBO.setTitle(adPlatformDOView.getTitle());
            adPlatformBO.setLinkUrl(adPlatformDOView.getLinkUrl());
            BOS.add(adPlatformBO);
        }
        return BOS;

    }

    /**
     * BO转DTO
     *
     * @param adPlatformBOS
     * @return
     */
    public static List<AdPlatformProductDTO> convertDTO(List<AdPlatformBO> adPlatformBOS) {
        List<AdPlatformProductDTO> adPlatformProductDTOS = new ArrayList<>();
        if (adPlatformBOS == null || adPlatformBOS.isEmpty()) {
            return adPlatformProductDTOS;
        }

        for (AdPlatformBO adPlatformBO : adPlatformBOS) {
            AdPlatformProductDTO adPlatformProductDTO = new AdPlatformProductDTO();
            adPlatformProductDTO.setProductId(adPlatformBO.getProductId());
            adPlatformProductDTO.setMediaUrl(adPlatformBO.getMediaUrl());
            adPlatformProductDTO.setTitle(adPlatformBO.getTitle());
            adPlatformProductDTO.setLinkUrl(adPlatformBO.getLinkUrl());
            adPlatformProductDTO.setPostion(adPlatformBO.getPositionEnum());
            adPlatformProductDTOS.add(adPlatformProductDTO);
        }
        return adPlatformProductDTOS;

    }
    
    
    public static List<AdPlatformBO> convertProductTopBO(List<AdPlatformDO> adPlatformDOs) {
        List<AdPlatformBO> BOS = new ArrayList<AdPlatformBO>();
        if (adPlatformDOs == null || adPlatformDOs.isEmpty()) {
            return BOS;
        }

        for (AdPlatformDO adPlatformDO : adPlatformDOs) {
            AdPlatformBO adPlatformBO = new AdPlatformBO();
            adPlatformBO.setProductId(adPlatformDO.getProductId());
            adPlatformBO.setMediaUrl(adPlatformDO.getMediaUrl());
            adPlatformBO.setTitle(adPlatformDO.getTitle());
            adPlatformBO.setLinkUrl(adPlatformDO.getLinkUrl());
            adPlatformBO.setPositionEnum(PositionEnum.getEnum(adPlatformDO.getPosition()));
            BOS.add(adPlatformBO);
        }
        return BOS;

    }

}
