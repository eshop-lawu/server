package com.lawu.eshop.user.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.common.SolrInputDocument;

import com.lawu.eshop.solr.impl.entity.MerchantStoreSolr;
import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantInfoForShoppingCartDTO;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.dto.NewMerchantStoreDTO;
import com.lawu.eshop.user.dto.RecommendFoodDTO;
import com.lawu.eshop.user.dto.ShoppingOrderFindMerchantInfoDTO;
import com.lawu.eshop.user.dto.ShoppingOrderFindUserInfoDTO;
import com.lawu.eshop.user.dto.ShoppingStoreDetailDTO;
import com.lawu.eshop.user.dto.StoreDetailDTO;
import com.lawu.eshop.user.dto.StoreSolrDTO;
import com.lawu.eshop.user.dto.StoreSolrInfoDTO;
import com.lawu.eshop.user.param.MerchantStoreParam;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreProfileBO;
import com.lawu.eshop.user.srv.bo.NewMerchantStoreBO;
import com.lawu.eshop.user.srv.bo.PayOrderStoreInfoBO;
import com.lawu.eshop.user.srv.bo.RecommendFoodBO;
import com.lawu.eshop.user.srv.bo.ShoppingOrderFindMerchantInfoBO;
import com.lawu.eshop.user.srv.bo.ShoppingStoreDetailBO;
import com.lawu.eshop.user.srv.bo.StoreDetailBO;
import com.lawu.eshop.user.srv.bo.StoreSolrInfoBO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.domain.extend.NewMerchantStoreDOView;
import com.lawu.eshop.user.srv.domain.extend.PayOrderStoreInfoView;
import com.lawu.eshop.user.srv.domain.extend.RecommendFoodDOview;
import com.lawu.utils.DataTransUtil;
import com.lawu.utils.DateUtil;

/**
 * 商家门店信息转换
 * Created by Administrator on 2017/3/24.
 */
public class MerchantStoreConverter {
    /**
     * BO转换
     *
     * @param merchantStoreProfileDO
     * @return
     */
    public static MerchantStoreProfileBO convertBO(MerchantStoreProfileDO merchantStoreProfileDO) {
        if (merchantStoreProfileDO == null) {
            return null;
        }

        MerchantStoreProfileBO merchantStoreProfileBO = new MerchantStoreProfileBO();
        merchantStoreProfileBO.setMerchantId(merchantStoreProfileDO.getMerchantId());
        merchantStoreProfileBO.setRegNumber(merchantStoreProfileDO.getRegNumber());
        merchantStoreProfileBO.setOperatorCardId(merchantStoreProfileDO.getOperatorCardId());
        merchantStoreProfileBO.setManageType(merchantStoreProfileDO.getManageType());
        merchantStoreProfileBO.setCertifType(merchantStoreProfileDO.getCertifType());
        return merchantStoreProfileBO;

    }

    public static MerchantStoreInfoBO coverter(MerchantStoreDO merchantStoreDO) {
        if (merchantStoreDO == null) {
            return null;
        }
        MerchantStoreInfoBO merchantStoreInfoBO = new MerchantStoreInfoBO();
        merchantStoreInfoBO.setMerchantStoreId(merchantStoreDO.getId());
        merchantStoreInfoBO.setName(merchantStoreDO.getName());
        merchantStoreInfoBO.setPrincipalName(merchantStoreDO.getPrincipalName());
        merchantStoreInfoBO.setRegionPath(merchantStoreDO.getRegionPath());
        merchantStoreInfoBO.setAddress(merchantStoreDO.getAddress());
        merchantStoreInfoBO.setIndustryPath(merchantStoreDO.getIndustryPath());
        merchantStoreInfoBO.setLongitude(merchantStoreDO.getLongitude());
        merchantStoreInfoBO.setLatitude(merchantStoreDO.getLatitude());
        merchantStoreInfoBO.setIntro(merchantStoreDO.getIntro());
        merchantStoreInfoBO.setPrincipalMobile(merchantStoreDO.getPrincipalMobile());
        merchantStoreInfoBO.setIsNoReasonReturn(merchantStoreDO.getIsNoReasonReturn());
        merchantStoreInfoBO.setStatusEnum(MerchantStatusEnum.getEnum(merchantStoreDO.getStatus()));
        merchantStoreInfoBO.setRegionName(merchantStoreDO.getRegionName());
        merchantStoreInfoBO.setIndustryName(merchantStoreDO.getIndustryName());
        merchantStoreInfoBO.setKeywords(merchantStoreDO.getKeywords());
        return merchantStoreInfoBO;
    }

    /**
     * BO转DTO
     *
     * @param merchantStoreInfoBO
     * @return
     */
    public static MerchantStoreDTO coverDTO(MerchantStoreInfoBO merchantStoreInfoBO) {
        if (merchantStoreInfoBO == null) {
            return null;
        }
        MerchantStoreDTO merchantStoreDTO = new MerchantStoreDTO();
        merchantStoreDTO.setMerchantStoreId(merchantStoreInfoBO.getMerchantStoreId());
        merchantStoreDTO.setName(merchantStoreInfoBO.getName());
        merchantStoreDTO.setPrincipalName(merchantStoreInfoBO.getPrincipalName());
        merchantStoreDTO.setRegionPath(merchantStoreInfoBO.getRegionPath());
        merchantStoreDTO.setAddress(merchantStoreInfoBO.getAddress());
        merchantStoreDTO.setIndustryPath(merchantStoreInfoBO.getIndustryPath());
        merchantStoreDTO.setLongitude(merchantStoreInfoBO.getLongitude());
        merchantStoreDTO.setLatitude(merchantStoreInfoBO.getLatitude());
        merchantStoreDTO.setIntro(merchantStoreInfoBO.getIntro());
        merchantStoreDTO.setPrincipalMobile(merchantStoreInfoBO.getPrincipalMobile());

        merchantStoreDTO.setCompanyAddress(merchantStoreInfoBO.getCompanyAddress());
        merchantStoreDTO.setCompanyName(merchantStoreInfoBO.getCompanyName());
        merchantStoreDTO.setRegNumber(merchantStoreInfoBO.getRegNumber());
        merchantStoreDTO.setLicenseIndate(merchantStoreInfoBO.getLicenseIndate());
        merchantStoreDTO.setManageType(MerchantStoreTypeEnum.getEnum(merchantStoreInfoBO.getManageType()));
        merchantStoreDTO.setCertifType(CertifTypeEnum.getEnum(merchantStoreInfoBO.getCertifType()));
        merchantStoreDTO.setOperatorCardId(merchantStoreInfoBO.getOperatorCardId());
        merchantStoreDTO.setOperatorName(merchantStoreInfoBO.getOperatorName());

        merchantStoreDTO.setEnvironmentUrl(merchantStoreInfoBO.getEnvironmentUrl());
        merchantStoreDTO.setIdcardUrl(merchantStoreInfoBO.getIdcardUrl());
        merchantStoreDTO.setLicenseUrl(merchantStoreInfoBO.getLicenseUrl());
        merchantStoreDTO.setLogoUrl(merchantStoreInfoBO.getLogoUrl());
        merchantStoreDTO.setOtherUrl(merchantStoreInfoBO.getOtherUrl());
        merchantStoreDTO.setStoreUrl(merchantStoreInfoBO.getStoreUrl());
        merchantStoreDTO.setAuditSuccess(merchantStoreInfoBO.isAuditSuccess());
        merchantStoreDTO.setMerchantStatus(merchantStoreInfoBO.getStatusEnum());
        merchantStoreDTO.setRegionName(merchantStoreInfoBO.getRegionName());
        merchantStoreDTO.setIndustryName(merchantStoreInfoBO.getIndustryName());
        merchantStoreDTO.setKeywords(merchantStoreInfoBO.getKeywords());

        return merchantStoreDTO;
    }

    /**
     * 门店参数对象转DO
     *
     * @param merchantStoreParam
     * @param type
     * @return
     */
    public static Object couverDOByParam(MerchantStoreParam merchantStoreParam, int type) {
        if (merchantStoreParam == null) {
            return null;
        }
        if (type == 1) {//商店基本信息转换DO
            MerchantStoreDO merchantStoreDO = new MerchantStoreDO();
            merchantStoreDO.setIndustryPath(merchantStoreParam.getIndustryPath());
            merchantStoreDO.setPrincipalName(merchantStoreParam.getPrincipalName());
            merchantStoreDO.setName(merchantStoreParam.getName());
            merchantStoreDO.setAddress(merchantStoreParam.getAddress());
            merchantStoreDO.setIntro(merchantStoreParam.getIntro());
            merchantStoreDO.setLatitude(merchantStoreParam.getLatitude());
            merchantStoreDO.setLongitude(merchantStoreParam.getLongitude());
            merchantStoreDO.setPrincipalMobile(merchantStoreParam.getPrincipalMobile());
            merchantStoreDO.setRegionPath(merchantStoreParam.getRegionPath());
            merchantStoreDO.setRegionName(merchantStoreParam.getRegionName());
            merchantStoreDO.setIndustryName(merchantStoreParam.getIndustryName());

            return merchantStoreDO;
        } else if (type == 2) {
            MerchantStoreProfileDO merchantStoreProfileDO = new MerchantStoreProfileDO();

            merchantStoreProfileDO.setCertifType(merchantStoreParam.getCertifType().val);
            merchantStoreProfileDO.setCompanyAddress(merchantStoreParam.getCompanyAddress());
            merchantStoreProfileDO.setLicenseIndate(merchantStoreParam.getLicenseIndate());
            merchantStoreProfileDO.setOperatorCardId(merchantStoreParam.getOperatorCardId());
            merchantStoreProfileDO.setCompanyName(merchantStoreParam.getCompanyName());
            merchantStoreProfileDO.setManageType(merchantStoreParam.getManageType().val);
            merchantStoreProfileDO.setOperatorName(merchantStoreParam.getOperatorName());
            merchantStoreProfileDO.setRegNumber(merchantStoreParam.getRegNumber());

            return merchantStoreProfileDO;
        } else {

            return null;
        }
    }

    /**
     * DTO转换
     *
     * @param storeDetailBO
     * @return
     */
    public static StoreDetailDTO convertDTO(StoreDetailBO storeDetailBO) {
        if (storeDetailBO == null) {
            return null;
        }

        StoreDetailDTO storeDetailDTO = new StoreDetailDTO();
        storeDetailDTO.setMerchantId(storeDetailBO.getMerchantId());
        storeDetailDTO.setUserNum(storeDetailBO.getUserNum());
        storeDetailDTO.setName(storeDetailBO.getName());
        storeDetailDTO.setRegionName(storeDetailBO.getRegionName());
        storeDetailDTO.setRegionPath(storeDetailBO.getRegionPath());
        storeDetailDTO.setAddress(storeDetailBO.getAddress());
        storeDetailDTO.setPrincipalMobile(storeDetailBO.getPrincipalMobile());
        storeDetailDTO.setStorePic(storeDetailBO.getStorePic());
        storeDetailDTO.setPicCount(storeDetailBO.getPicCount());
        storeDetailDTO.setIntro(storeDetailBO.getIntro());
        storeDetailDTO.setFavoriteNumber(storeDetailBO.getFavoriteNumber());
        storeDetailDTO.setFavorite(storeDetailBO.getFavorite());
        storeDetailDTO.setAverageConsumeAmount(BigDecimal.valueOf(storeDetailBO.getAverageConsumeAmount().intValue()));
        storeDetailDTO.setAverageScore(storeDetailBO.getAverageScore().compareTo(BigDecimal.valueOf(0)) == 0 ? BigDecimal.valueOf(4) : storeDetailBO.getAverageScore());
        storeDetailDTO.setFeedbackRate(storeDetailBO.getFeedbackRate());
        storeDetailDTO.setBuyNumbers(storeDetailBO.getBuyNumbers());
        storeDetailDTO.setLongitude(storeDetailBO.getLongitude());
        storeDetailDTO.setLatitude(storeDetailBO.getLatitude());
        storeDetailDTO.setStoreLogo(storeDetailBO.getStoreLogo());
        storeDetailDTO.setIndustryPath(storeDetailBO.getIndustryPath());
        return storeDetailDTO;
    }

    /**
     * DTO转换
     *
     * @param shoppingStoreDetailBO
     * @return
     */
    public static ShoppingStoreDetailDTO convertDTO(ShoppingStoreDetailBO shoppingStoreDetailBO) {
        if (shoppingStoreDetailBO == null) {
            return null;
        }

        ShoppingStoreDetailDTO shoppingStoreDetailDTO = new ShoppingStoreDetailDTO();
        shoppingStoreDetailDTO.setMerchantId(shoppingStoreDetailBO.getMerchantId());
        shoppingStoreDetailDTO.setName(shoppingStoreDetailBO.getName());
        shoppingStoreDetailDTO.setLogoPic(shoppingStoreDetailBO.getLogoPic());
        shoppingStoreDetailDTO.setFansCount(shoppingStoreDetailBO.getFansCount());
        shoppingStoreDetailDTO.setFans(shoppingStoreDetailBO.getFans());
        shoppingStoreDetailDTO.setFavorite(shoppingStoreDetailBO.getFavorite());
        return shoppingStoreDetailDTO;
    }

    /**
     * BO转换
     *
     * @param merchantStoreDO
     * @return
     */
    public static StoreDetailBO convertBO(MerchantStoreDO merchantStoreDO) {
        if (merchantStoreDO == null) {
            return null;
        }

        StoreDetailBO storeDetailBO = new StoreDetailBO();
        storeDetailBO.setMerchantId(merchantStoreDO.getMerchantId());
        storeDetailBO.setName(merchantStoreDO.getName());
        storeDetailBO.setRegionName(merchantStoreDO.getRegionName());
        storeDetailBO.setAddress(merchantStoreDO.getAddress());
        storeDetailBO.setPrincipalMobile(merchantStoreDO.getPrincipalMobile());
        storeDetailBO.setIntro(merchantStoreDO.getIntro());
        storeDetailBO.setFavoriteNumber(merchantStoreDO.getFavoriteNumber());
        storeDetailBO.setAverageConsumeAmount(merchantStoreDO.getAverageConsumeAmount());
        storeDetailBO.setAverageScore(merchantStoreDO.getAverageScore());
        storeDetailBO.setFeedbackRate(merchantStoreDO.getFeedbackRate());
        storeDetailBO.setBuyNumbers(merchantStoreDO.getBuyNumbers());
        return storeDetailBO;
    }

    /**
     * MerchantStoreNoReasonReturnBO转换
     *
     * @param merchantStoreDO
     * @return
     */
    public static ShoppingOrderFindMerchantInfoBO convert(MerchantStoreDO merchantStoreDO, MerchantDO merchantDO) {
        if (merchantStoreDO == null || merchantDO == null) {
            return null;
        }
        ShoppingOrderFindMerchantInfoBO shoppingOrderFindMerchantInfoBO = new ShoppingOrderFindMerchantInfoBO();
        shoppingOrderFindMerchantInfoBO.setMerchantStoreId(merchantStoreDO.getId());
        shoppingOrderFindMerchantInfoBO.setMerchantStoreRegionPath(merchantStoreDO.getRegionPath());
        shoppingOrderFindMerchantInfoBO.setIsNoReasonReturn(merchantStoreDO.getIsNoReasonReturn());
        shoppingOrderFindMerchantInfoBO.setMerchantId(merchantStoreDO.getMerchantId());
        shoppingOrderFindMerchantInfoBO.setMerchantStoreName(merchantStoreDO.getName());
        shoppingOrderFindMerchantInfoBO.setMerchantNum(merchantDO.getNum());
        return shoppingOrderFindMerchantInfoBO;
    }

    /**
     * ShoppingOrderFindMerchantInfoDTO转换
     *
     * @param shoppingOrderFindMerchantInfoBO
     * @return
     */
    public static ShoppingOrderFindMerchantInfoDTO convert(ShoppingOrderFindMerchantInfoBO shoppingOrderFindMerchantInfoBO) {
        if (shoppingOrderFindMerchantInfoBO == null) {
            return null;
        }

        ShoppingOrderFindMerchantInfoDTO rtn = new ShoppingOrderFindMerchantInfoDTO();
        rtn.setMerchantId(shoppingOrderFindMerchantInfoBO.getMerchantId());
        rtn.setMerchantStoreId(shoppingOrderFindMerchantInfoBO.getMerchantStoreId());
        rtn.setMerchantStoreRegionPath(shoppingOrderFindMerchantInfoBO.getMerchantStoreRegionPath());
        rtn.setMerchantStoreName(shoppingOrderFindMerchantInfoBO.getMerchantStoreName());
        rtn.setMerchantNum(shoppingOrderFindMerchantInfoBO.getMerchantNum());
        rtn.setIsNoReasonReturn(shoppingOrderFindMerchantInfoBO.getIsNoReasonReturn());
        rtn.setIsFans(shoppingOrderFindMerchantInfoBO.getIsFans());
        return rtn;
    }

    /**
     * ShoppingOrderFindMerchantInfoDTO List转换
     *
     * @param merchantStoreNoReasonReturnBOList
     * @return
     */
    public static List<ShoppingOrderFindMerchantInfoDTO> convertShoppingOrderFindMerchantInfoDTOList(List<ShoppingOrderFindMerchantInfoBO> merchantStoreNoReasonReturnBOList) {
    	if (merchantStoreNoReasonReturnBOList == null || merchantStoreNoReasonReturnBOList.isEmpty()) {
            return null;
        }

        List<ShoppingOrderFindMerchantInfoDTO> merchantStoreNoReasonReturnDTOList = new ArrayList<>();
        for (ShoppingOrderFindMerchantInfoBO merchantStoreNoReasonReturnBO : merchantStoreNoReasonReturnBOList) {
            merchantStoreNoReasonReturnDTOList.add(convert(merchantStoreNoReasonReturnBO));
        }

        return merchantStoreNoReasonReturnDTOList;
    }

    public static MerchantStoreBO convertStoreBO(MerchantStoreDO merchantStoreDO) {
        if (merchantStoreDO == null) {
            return null;
        }
        MerchantStoreBO bo = new MerchantStoreBO();
        bo.setLongitude(merchantStoreDO.getLongitude());
        bo.setLatitude(merchantStoreDO.getLatitude());
        bo.setName(merchantStoreDO.getName());
        bo.setId(merchantStoreDO.getId());
        bo.setMerchantId(merchantStoreDO.getMerchantId());
        bo.setIndustryPath(merchantStoreDO.getIndustryPath());
        bo.setIndustryName(merchantStoreDO.getIndustryName());
        bo.setRegionPath(merchantStoreDO.getRegionPath());
        bo.setId(merchantStoreDO.getId());
        bo.setPrincipalName(merchantStoreDO.getPrincipalName());
        return bo;
    }

    public static MerchantStoreDTO convertStoreDTO(MerchantStoreBO merchantStoreBO) {
        if (merchantStoreBO == null) {
            return null;
        }
        MerchantStoreDTO dto = new MerchantStoreDTO();
        dto.setLongitude(merchantStoreBO.getLongitude());
        dto.setLatitude(merchantStoreBO.getLatitude());
        dto.setName(merchantStoreBO.getName());
        dto.setMerchantStoreId(merchantStoreBO.getId());
        dto.setMerchantId(merchantStoreBO.getMerchantId());
        dto.setIndustryPath(merchantStoreBO.getIndustryPath());
        dto.setIndustryName(merchantStoreBO.getIndustryName());
        dto.setRegionPath(merchantStoreBO.getRegionPath());
        dto.setManageType(merchantStoreBO.getManageTypeEnum());
        dto.setPrincipalName(merchantStoreBO.getPrincipalName());
        return dto;
    }

    public static List<MerchantStoreBO> convertStoreBO(List<MerchantStoreDO> merchantStoreDOList) {
        List<MerchantStoreBO> merchantStoreBOS = new ArrayList<>();
        if (merchantStoreDOList == null || merchantStoreDOList.isEmpty()) {
            return merchantStoreBOS;
        }
        for (MerchantStoreDO merchantStoreDO : merchantStoreDOList) {
            merchantStoreBOS.add(convertStoreBO(merchantStoreDO));
        }
        return merchantStoreBOS;
    }

    public static List<MerchantStoreDTO> convertStoreDTO(List<MerchantStoreBO> merchantStoreBOList) {
        List<MerchantStoreDTO> merchantStoreDTOS = new ArrayList<>();
        if (merchantStoreBOList == null || merchantStoreBOList.isEmpty()) {
            return merchantStoreDTOS;
        }
        for (MerchantStoreBO merchantStoreBO : merchantStoreBOList) {
            merchantStoreDTOS.add(convertStoreDTO(merchantStoreBO));
        }
        return merchantStoreDTOS;
    }

    /**
     * SolrInputDocument
     *
     * @param merchantStoreDO
     * @param storePic
     * @return
     */
    public static SolrInputDocument convertSolrInputDocument(MerchantStoreDO merchantStoreDO, String storePic) {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", merchantStoreDO.getId());
        document.addField("merchantId_l", merchantStoreDO.getMerchantId());
        document.addField("name", merchantStoreDO.getName());
        document.addField("regionPath_s", merchantStoreDO.getRegionPath());
        document.addField("latLon_p", merchantStoreDO.getLatitude() + "," + merchantStoreDO.getLongitude());
        document.addField("industryPath_s", merchantStoreDO.getIndustryPath());
        document.addField("industryName_s", merchantStoreDO.getIndustryName());
        document.addField("favoriteNumber_i", merchantStoreDO.getFavoriteNumber());
        document.addField("averageConsumeAmount_d", merchantStoreDO.getAverageConsumeAmount() == null ? 0 : merchantStoreDO.getAverageConsumeAmount().doubleValue());
        document.addField("averageScore_d", merchantStoreDO.getAverageScore() == null ? 0 : merchantStoreDO.getAverageScore().doubleValue());
        document.addField("storePic_s", storePic);
        if (StringUtils.isNotEmpty(merchantStoreDO.getKeywords())) {
            document.addField("keywords", merchantStoreDO.getKeywords());
            String[] keywords = merchantStoreDO.getKeywords().split(",");
            for (String keyword : keywords) {
                document.addField("keyword_ss", keyword.trim());
            }
        }
        return document;
    }
    
    public static MerchantStoreSolr convert(MerchantStoreDO merchantStoreDO, String storePic) {
        MerchantStoreSolr target = new MerchantStoreSolr();
        target.setId(merchantStoreDO.getId());
        target.setMerchantId(merchantStoreDO.getMerchantId());
        target.setName(merchantStoreDO.getName());
        target.setRegionPath(merchantStoreDO.getRegionPath());
        target.setLatLon(merchantStoreDO.getLatitude() + "," + merchantStoreDO.getLongitude());
        target.setIndustryPath(merchantStoreDO.getIndustryPath());
        target.setIndustryName(merchantStoreDO.getIndustryName());
        target.setFavoriteNumber(merchantStoreDO.getFavoriteNumber());
        target.setAverageConsumeAmount(merchantStoreDO.getAverageConsumeAmount() == null ? 0D : merchantStoreDO.getAverageConsumeAmount().doubleValue());
        target.setAverageScore(merchantStoreDO.getAverageScore() == null ? 0D : merchantStoreDO.getAverageScore().doubleValue());
        target.setStorePic(storePic);
        if (StringUtils.isNotEmpty(merchantStoreDO.getKeywords())) {
            target.setKeywords(merchantStoreDO.getKeywords());
            String[] keywords = merchantStoreDO.getKeywords().split(",");
            target.setKeywordss(Arrays.asList(keywords));
        }
        return target;
    }

    public static List<StoreSolrDTO> convertStoreSolrDTOList(List<MerchantStoreSolr> sources) {
        List<StoreSolrDTO> rtn = new ArrayList<>();
        if (sources == null || sources.isEmpty()) {
            return rtn;
        }
        for (MerchantStoreSolr item : sources) {
            StoreSolrDTO storeSolrDTO = new StoreSolrDTO();
            storeSolrDTO.setMerchantStoreId(item.getId() == null ? 0 : item.getId());
            storeSolrDTO.setMerchantId(item.getMerchantId() == null ? 0 : item.getMerchantId());
            storeSolrDTO.setName(item.getName() == null ? "" : item.getName());
            storeSolrDTO.setRegionPath(item.getRegionPath() == null ? "" : item.getRegionPath());
            storeSolrDTO.setIndustryPath(item.getIndustryPath() == null ? "" : item.getIndustryPath());
            storeSolrDTO.setIndustryName(item.getIndustryName() == null ? "" : item.getIndustryName());
            storeSolrDTO.setStorePic(item.getStorePic() == null ? "" : item.getStorePic());
            storeSolrDTO.setDistance(DataTransUtil.objectToDobule(item.getDistance(), 3));
            storeSolrDTO.setFavoriteNumber(item.getFavoriteNumber() == null ? 0 : item.getFavoriteNumber());
            storeSolrDTO.setAverageConsumeAmount(item.getAverageConsumeAmount() == null ? 0.0 : item.getAverageConsumeAmount());
            storeSolrDTO.setAverageScore(item.getAverageScore() == null ? 0.0 : item.getAverageScore());
            storeSolrDTO.setFavoreInfo(item.getFavoreInfo() == null ? "" : item.getFavoreInfo());
            storeSolrDTO.setDiscountPackage(item.getDiscountPackage() == null ? "" : item.getDiscountPackage());
            if (storeSolrDTO.getAverageScore() == 0) {
                storeSolrDTO.setAverageScore(4.0);
            }
            if (item.getFavoreEndTime() != null && StringUtils.isNotEmpty(item.getFavoreEndTime())) {
                String favoreEndTime = item.getFavoreEndTime();
                if (DateUtil.isOverdue(DateUtil.formatDate(favoreEndTime, "yyyy-MM-dd"))) {
                    storeSolrDTO.setFavoreInfo("");
                }
            }
            rtn.add(storeSolrDTO);
        }
        return rtn;
    }

    /**
     * ShoppingOrderFindUserInfoDTO转换
     *
     * @param merchantStoreNoReasonReturnBOList
     * @return
     */
    public static ShoppingOrderFindUserInfoDTO convert(List<ShoppingOrderFindMerchantInfoBO> merchantStoreNoReasonReturnBOList, MemberBO memberBO) {
    	ShoppingOrderFindUserInfoDTO rtn = new ShoppingOrderFindUserInfoDTO();
    	rtn.setShoppingOrderFindMerchantInfoDTOList(convertShoppingOrderFindMerchantInfoDTOList(merchantStoreNoReasonReturnBOList));
    	if (memberBO != null) {
    		rtn.setMemberNum(memberBO.getNum());
    		rtn.setMemberNickname(memberBO.getNickname());
    	}
        return rtn;
    }

    public static List<StoreSolrInfoDTO> coverStoreSolrDTOS(List<StoreSolrInfoBO> bos) {
        if (bos.isEmpty()) {
            return null;
        }
        List<StoreSolrInfoDTO> list = new ArrayList<>();
        for (StoreSolrInfoBO storeSolrInfoBO : bos) {
            StoreSolrInfoDTO storeSolrInfoDTO = new StoreSolrInfoDTO();
            storeSolrInfoDTO.setMerchantId(storeSolrInfoBO.getMerchantId());
            storeSolrInfoDTO.setMerchantStoreId(storeSolrInfoBO.getMerchantStoreId());
            storeSolrInfoDTO.setIndustryPath(storeSolrInfoBO.getIndustryPath());
            storeSolrInfoDTO.setIndustryName(storeSolrInfoBO.getIndustryName());
            list.add(storeSolrInfoDTO);
        }
        return list;
    }

    public static PayOrderStoreInfoBO coverPayOrderStoreInfoBO(PayOrderStoreInfoView storeInfoView) {
        if (storeInfoView == null) {
            return null;
        }
        PayOrderStoreInfoBO payOrderStoreInfoBO = new PayOrderStoreInfoBO();
        payOrderStoreInfoBO.setMerchantStoreId(storeInfoView.getMerchantStoreId());
        payOrderStoreInfoBO.setName(storeInfoView.getName());
        payOrderStoreInfoBO.setAddress(storeInfoView.getAddress());
        payOrderStoreInfoBO.setPrincipalMobile(storeInfoView.getPrincipalMobile());
        payOrderStoreInfoBO.setStoreUrl(storeInfoView.getPath());
        payOrderStoreInfoBO.setRegionName(storeInfoView.getRegionName());
        payOrderStoreInfoBO.setUserNum(storeInfoView.getUserNum());
        return payOrderStoreInfoBO;
    }

    /**
     * MerchantInfoForShoppingCartDTO 转换
     *
     * @param merchantStoreInfoBO
     * @return
     * @author Sunny
     * @date 2017年6月29日
     */
    public static MerchantInfoForShoppingCartDTO convert(MerchantStoreInfoBO merchantStoreInfoBO) {
    	MerchantInfoForShoppingCartDTO rtn = null;
        if (merchantStoreInfoBO == null) {
            return rtn;
        }
        rtn = new MerchantInfoForShoppingCartDTO();
        rtn.setMerchantStoreId(merchantStoreInfoBO.getMerchantStoreId());
        rtn.setMerchantStoreName(merchantStoreInfoBO.getName());
        return rtn;
    }

    /**
     * BO转换
     *
     * @param storeDOViews
     * @return
     * @author meishuquan
     */
    public static List<NewMerchantStoreBO> convertNewStoreBO(List<NewMerchantStoreDOView> storeDOViews) {
        List<NewMerchantStoreBO> storeBOS = new ArrayList<>();
        if (storeDOViews == null || storeDOViews.isEmpty()) {
            return storeBOS;
        }

        for (NewMerchantStoreDOView storeDOView : storeDOViews) {
            NewMerchantStoreBO storeBO = new NewMerchantStoreBO();
            storeBO.setMerchantId(storeDOView.getMerchantId());
            storeBO.setMerchantStoreId(storeDOView.getMerchantStoreId());
            storeBO.setName(storeDOView.getName());
            storeBO.setIndustryName(storeDOView.getIndustryName());
            storeBO.setRegionName(storeDOView.getRegionName());
            storeBO.setRegionPath(storeDOView.getRegionPath());
            storeBO.setAddress(storeDOView.getAddress());
            storeBO.setStorePic(storeDOView.getStorePic());
            storeBOS.add(storeBO);
        }
        return storeBOS;
    }

    /**
     * DTO转换
     *
     * @param storeBOS
     * @return
     * @author meishuquan
     */
    public static List<NewMerchantStoreDTO> convertNewStoreDTO(List<NewMerchantStoreBO> storeBOS) {
        List<NewMerchantStoreDTO> storeDTOS = new ArrayList<>();
        if (storeBOS == null || storeBOS.isEmpty()) {
            return storeDTOS;
        }

        for (NewMerchantStoreBO storeBO : storeBOS) {
            NewMerchantStoreDTO storeDTO = new NewMerchantStoreDTO();
            storeDTO.setMerchantId(storeBO.getMerchantId());
            storeDTO.setMerchantStoreId(storeBO.getMerchantStoreId());
            storeDTO.setName(storeBO.getName());
            storeDTO.setIndustryName(storeBO.getIndustryName());
            storeDTO.setRegionName(storeBO.getRegionName());
            storeDTO.setRegionPath(storeBO.getRegionPath());
            storeDTO.setAddress(storeBO.getAddress());
            storeDTO.setStorePic(storeBO.getStorePic());
            storeDTOS.add(storeDTO);
        }
        return storeDTOS;
    }

    /**
     * BO转换
     *
     * @param foodDOviews
     * @return
     * @author meishuquan
     */
    public static List<RecommendFoodBO> convertRecommendStoreBO(List<RecommendFoodDOview> foodDOviews) {
        List<RecommendFoodBO> foodBOS = new ArrayList<>();
        if (foodDOviews == null || foodDOviews.isEmpty()) {
            return foodBOS;
        }

        for (RecommendFoodDOview foodDOview : foodDOviews) {
            RecommendFoodBO foodBO = new RecommendFoodBO();
            foodBO.setMerchantId(foodDOview.getMerchantId());
            foodBO.setMerchantStoreId(foodDOview.getMerchantStoreId());
            foodBO.setName(foodDOview.getName());
            foodBO.setIndustryName(foodDOview.getIndustryName());
            foodBO.setRegionName(foodDOview.getRegionName());
            foodBO.setRegionPath(foodDOview.getRegionPath());
            foodBO.setAddress(foodDOview.getAddress());
            foodBO.setStorePic(foodDOview.getStorePic());
            foodBO.setLongitude(foodDOview.getLongitude());
            foodBO.setLatitude(foodDOview.getLatitude());
            foodBO.setAverageScore(foodDOview.getAverageScore());
            foodBO.setAverageConsumeAmount(foodDOview.getAverageConsumeAmount());
            foodBO.setBuyNumbers(foodDOview.getBuyNumbers());
            foodBO.setCommentsCount(foodDOview.getCommentsCount());
            foodBOS.add(foodBO);
        }
        return foodBOS;
    }

    /**
     * DTO转换
     *
     * @param foodBOS
     * @return
     * @author meishuquan
     */
    public static List<RecommendFoodDTO> convertRecommendStoreDTO(List<RecommendFoodBO> foodBOS) {
        List<RecommendFoodDTO> foodDTOS = new ArrayList<>();
        if (foodBOS == null || foodBOS.isEmpty()) {
            return foodDTOS;
        }

        for (RecommendFoodBO foodBO : foodBOS) {
            RecommendFoodDTO foodDTO = new RecommendFoodDTO();
            foodDTO.setMerchantId(foodBO.getMerchantId());
            foodDTO.setMerchantStoreId(foodBO.getMerchantStoreId());
            foodDTO.setName(foodBO.getName());
            foodDTO.setIndustryName(foodBO.getIndustryName());
            foodDTO.setRegionName(foodBO.getRegionName());
            foodDTO.setRegionPath(foodBO.getRegionPath());
            foodDTO.setAddress(foodBO.getAddress());
            foodDTO.setStorePic(foodBO.getStorePic());
            foodDTO.setLongitude(foodBO.getLongitude());
            foodDTO.setLatitude(foodBO.getLatitude());
            foodDTO.setAverageScore(foodBO.getAverageScore());
            foodDTO.setAverageConsumeAmount(foodBO.getAverageConsumeAmount());
            foodDTO.setBuyNumbers(foodBO.getBuyNumbers());
            foodDTO.setCommentsCount(foodBO.getCommentsCount());
            foodDTOS.add(foodDTO);
        }
        return foodDTOS;
    }

}
