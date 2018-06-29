package com.lawu.eshop.user.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lawu.eshop.user.constants.MerchantAuditStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreAuditDTO;
import com.lawu.eshop.user.dto.param.MerchantAuditTypeEnum;
import com.lawu.eshop.user.param.MerchantStoreParam;
import com.lawu.eshop.user.srv.bo.MerchantStoreAuditBO;
import com.lawu.eshop.user.srv.domain.MerchantStoreAuditDO;

import net.sf.json.JSONObject;

/**
 * @author meishuquan
 * @date 2017/4/27.
 */
public class MerchantStoreAuditConverter {

    /**
     * BO转换
     *
     * @param merchantStoreAuditDO
     * @return
     */
    public static MerchantStoreAuditBO convertBO(MerchantStoreAuditDO merchantStoreAuditDO) {
        if (merchantStoreAuditDO == null) {
            return null;
        }

        MerchantStoreAuditBO merchantStoreAuditBO = new MerchantStoreAuditBO();
        merchantStoreAuditBO.setId(merchantStoreAuditDO.getId());
        merchantStoreAuditBO.setMerchantId(merchantStoreAuditDO.getMerchantId());
        merchantStoreAuditBO.setMerchantStoreId(merchantStoreAuditDO.getMerchantStoreId());
        merchantStoreAuditBO.setContent(merchantStoreAuditDO.getContent());
        merchantStoreAuditBO.setStatus(merchantStoreAuditDO.getStatus());
        merchantStoreAuditBO.setType(merchantStoreAuditDO.getType());
        merchantStoreAuditBO.setAuditorId(merchantStoreAuditDO.getAuditorId());
        merchantStoreAuditBO.setRemark(merchantStoreAuditDO.getRemark());
        merchantStoreAuditBO.setAuditTime(merchantStoreAuditDO.getAuditTime());
        merchantStoreAuditBO.setGmtModified(merchantStoreAuditDO.getGmtModified());
        return merchantStoreAuditBO;
    }

    /**
     * DTO转换
     *
     * @param merchantStoreAuditBO
     * @return
     */
    public static MerchantStoreAuditDTO convertDTO(MerchantStoreAuditBO merchantStoreAuditBO) {
        if (merchantStoreAuditBO == null) {
            return null;
        }

        JSONObject jsonObject = JSONObject.fromObject(merchantStoreAuditBO.getContent());
        MerchantStoreParam merchantStoreParam = (MerchantStoreParam) JSONObject.toBean(jsonObject, MerchantStoreParam.class);
        List<String> storeUrlList = new ArrayList<>();
        List<String> environmentUrlList = new ArrayList<>();
        List<String> idcardUrlList = new ArrayList<>();
        List<String> licenseUrlList = new ArrayList<>();
        List<String> otherUrlList = new ArrayList<>();

        MerchantStoreAuditDTO merchantStoreAuditDTO = new MerchantStoreAuditDTO();
        merchantStoreAuditDTO.setName(merchantStoreParam.getName());
        merchantStoreAuditDTO.setRegionPath(merchantStoreParam.getRegionPath());
        merchantStoreAuditDTO.setRegionName(merchantStoreParam.getRegionName());
        merchantStoreAuditDTO.setAddress(merchantStoreParam.getAddress());
        merchantStoreAuditDTO.setLongitude(merchantStoreParam.getLongitude());
        merchantStoreAuditDTO.setLatitude(merchantStoreParam.getLatitude());
        merchantStoreAuditDTO.setIndustryPath(merchantStoreParam.getIndustryPath());
        merchantStoreAuditDTO.setIndustryName(merchantStoreParam.getIndustryName());
        merchantStoreAuditDTO.setIntro(merchantStoreParam.getIntro());
        merchantStoreAuditDTO.setPrincipalName(merchantStoreParam.getPrincipalName());
        merchantStoreAuditDTO.setPrincipalMobile(merchantStoreParam.getPrincipalMobile());
        merchantStoreAuditDTO.setCompanyName(merchantStoreParam.getCompanyName());
        merchantStoreAuditDTO.setCompanyAddress(merchantStoreParam.getCompanyAddress());
        merchantStoreAuditDTO.setRegNumber(merchantStoreParam.getRegNumber());
        merchantStoreAuditDTO.setLicenseIndate(merchantStoreParam.getLicenseIndate());
        merchantStoreAuditDTO.setManageType(merchantStoreParam.getManageType());
        merchantStoreAuditDTO.setCertifType(merchantStoreParam.getCertifType());
        merchantStoreAuditDTO.setOperatorCardId(merchantStoreParam.getOperatorCardId());
        merchantStoreAuditDTO.setOperatorName(merchantStoreParam.getOperatorName());
        merchantStoreAuditDTO.setKeywords(merchantStoreParam.getKeywords());
        if(StringUtils.isNotEmpty(merchantStoreParam.getLogoUrl()) && merchantStoreParam.getLogoUrl().lastIndexOf(',') > 0){
            merchantStoreAuditDTO.setLogoUrl(merchantStoreParam.getLogoUrl().substring(0,merchantStoreParam.getLogoUrl().length() - 1));
        }else{
            merchantStoreAuditDTO.setLogoUrl(merchantStoreParam.getLogoUrl());
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getStoreUrl())) {
            String[] storeUrlArr = merchantStoreParam.getStoreUrl().split(",");
            for (String storeUrl : storeUrlArr) {
                storeUrlList.add(storeUrl);
            }
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getEnvironmentUrl())) {
            String[] environmentUrlArr = merchantStoreParam.getEnvironmentUrl().split(",");
            for (String environmentUrl : environmentUrlArr) {
                environmentUrlList.add(environmentUrl);
            }
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getIdcardUrl())) {
            String[] idcardUrlArr = merchantStoreParam.getIdcardUrl().split(",");
            for (String idcardUrl : idcardUrlArr) {
                idcardUrlList.add(idcardUrl);
            }
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getLicenseUrl())) {
            String[] licenseUrlArr = merchantStoreParam.getLicenseUrl().split(",");
            for (String licenseUrl : licenseUrlArr) {
                licenseUrlList.add(licenseUrl);
            }
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getOtherUrl())) {
            String[] otherUrlArr = merchantStoreParam.getOtherUrl().split(",");
            for (String otherUrl : otherUrlArr) {
                otherUrlList.add(otherUrl);
            }
        }
        merchantStoreAuditDTO.setStoreUrl(storeUrlList);
        merchantStoreAuditDTO.setEnvironmentUrl(environmentUrlList);
        merchantStoreAuditDTO.setIdcardUrl(idcardUrlList);
        merchantStoreAuditDTO.setLicenseUrl(licenseUrlList);
        merchantStoreAuditDTO.setOtherUrl(otherUrlList);

        merchantStoreAuditDTO.setId(merchantStoreAuditBO.getId());
        merchantStoreAuditDTO.setStoreId(merchantStoreAuditBO.getMerchantStoreId());
        merchantStoreAuditDTO.setMerchantId(merchantStoreAuditBO.getMerchantId());
        merchantStoreAuditDTO.setStatusEnum(MerchantAuditStatusEnum.getEnum(merchantStoreAuditBO.getStatus()));
        merchantStoreAuditDTO.setTypeEnum(MerchantAuditTypeEnum.getEnum(merchantStoreAuditBO.getType()));
        merchantStoreAuditDTO.setAuditorId(merchantStoreAuditBO.getAuditorId());
        merchantStoreAuditDTO.setRemark(merchantStoreAuditBO.getRemark());
        merchantStoreAuditDTO.setAuditTime(merchantStoreAuditBO.getAuditTime());
        merchantStoreAuditDTO.setGmtModified(merchantStoreAuditBO.getGmtModified());
        return merchantStoreAuditDTO;
    }

    /**
     * BO转换
     *
     * @param merchantStoreAuditDOS
     * @return
     */
    public static List<MerchantStoreAuditBO> convertBO(List<MerchantStoreAuditDO> merchantStoreAuditDOS) {
        List<MerchantStoreAuditBO> merchantStoreAuditBOS = new ArrayList<>();
        if (merchantStoreAuditDOS == null || merchantStoreAuditDOS.isEmpty()) {
            return merchantStoreAuditBOS;
        }

        for (MerchantStoreAuditDO merchantStoreAuditDO : merchantStoreAuditDOS) {
            merchantStoreAuditBOS.add(convertBO(merchantStoreAuditDO));
        }
        return merchantStoreAuditBOS;
    }

    /**
     * DTO转换
     *
     * @param merchantStoreAuditBOS
     * @return
     */
    public static List<MerchantStoreAuditDTO> convertDTO(List<MerchantStoreAuditBO> merchantStoreAuditBOS) {
        List<MerchantStoreAuditDTO> merchantStoreAuditDTOS = new ArrayList<>();
        if (merchantStoreAuditBOS == null || merchantStoreAuditBOS.isEmpty()) {
            return merchantStoreAuditDTOS;
        }

        for (MerchantStoreAuditBO merchantStoreAuditBO : merchantStoreAuditBOS) {
            merchantStoreAuditDTOS.add(convertDTO(merchantStoreAuditBO));

        }
        return merchantStoreAuditDTOS;
    }

}
