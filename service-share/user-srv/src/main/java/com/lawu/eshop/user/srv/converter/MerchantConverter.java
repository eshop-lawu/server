package com.lawu.eshop.user.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.user.dto.AccountDTO;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.dto.MerchantDetailDTO;
import com.lawu.eshop.user.dto.MerchantSNSDTO;
import com.lawu.eshop.user.srv.bo.MerchantBO;
import com.lawu.eshop.user.srv.bo.MerchantDetailBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.framework.core.page.Page;

/**
 * 商户信息转换器
 *
 * @author meishuquan
 * @date 2017/3/23
 */
public class MerchantConverter {

    /**
     * BO转换
     *
     * @param merchantDO
     * @return
     */
    public static MerchantBO convertBO(MerchantDO merchantDO) {
        if (merchantDO == null) {
            return null;
        }

        MerchantBO merchantBO = new MerchantBO();
        merchantBO.setId(merchantDO.getId());
        merchantBO.setNum(merchantDO.getNum());
        merchantBO.setAccount(merchantDO.getAccount());
        merchantBO.setPwd(merchantDO.getPwd());
        merchantBO.setMobile(merchantDO.getMobile());
        merchantBO.setHeadimg(merchantDO.getHeadimg());
        merchantBO.setStatus(merchantDO.getStatus());
        merchantBO.setInviterId(merchantDO.getInviterId());
        merchantBO.setInviterType(merchantDO.getInviterType());
        merchantBO.setLevel(merchantDO.getLevel());
        merchantBO.setGmtModified(merchantDO.getGmtModified());
        merchantBO.setGmtCreate(merchantDO.getGmtCreate());
        merchantBO.setGtCid(merchantDO.getGtCid());
        merchantBO.setRyToken(merchantDO.getRyToken());
        merchantBO.setIsFreeze(merchantDO.getIsFreeze());
        merchantBO.setFreezeReason(merchantDO.getFreezeReason());
        return merchantBO;
    }

    /**
     * DTO转换
     *
     * @param merchantBO
     * @return
     */
    public static MerchantDTO convertDTO(MerchantBO merchantBO) {
        if (merchantBO == null) {
            return null;
        }

        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setId(merchantBO.getId());
        merchantDTO.setAccount(merchantBO.getAccount());
        merchantDTO.setNum(merchantBO.getNum());
        merchantDTO.setHeadimg(merchantBO.getHeadimg());
        merchantDTO.setLevel(merchantBO.getLevel());
        merchantDTO.setIsFreeze(merchantBO.getIsFreeze());
        return merchantDTO;
    }
    
    
    /**
     * DTO转换
     *
     * @param merchantBO
     * @return
     */
    public static MerchantSNSDTO convertSNSDTO(MerchantBO merchantBO) {
        if (merchantBO == null) {
            return null;
        }

        MerchantSNSDTO merchantDTO = new MerchantSNSDTO();
        merchantDTO.setId(merchantBO.getId());
        merchantDTO.setNum(merchantBO.getNum());
        merchantDTO.setHeadimg(merchantBO.getHeadimg());
        merchantDTO.setPrincipalName(merchantBO.getPrincipalName());
        return merchantDTO;
    }

    /**
     * DOS 转BOS
     *
     * @param merchantDOS
     * @return
     * @author zhangrc
     * @date 2013/03/23
     */
    public static List<MerchantBO> convertBOS(List<MerchantDO> merchantDOS) {
        if (merchantDOS == null) {
            return null;
        }
        List<MerchantBO> merchantBOS = new ArrayList<MerchantBO>();
        for (MerchantDO merchantDO : merchantDOS) {
            merchantBOS.add(convertBO(merchantDO));
        }
        return merchantBOS;
    }


    public static List<MerchantDTO> convertListDOTS(List<MerchantBO> merchantBOS) {
        if (merchantBOS == null) {
            return null;
        }
        List<MerchantDTO> memberDTOS = new ArrayList<MerchantDTO>();
        for (MerchantBO merchantBO : merchantBOS) {
            memberDTOS.add(convertDTO(merchantBO));
        }
        return memberDTOS;
    }

    /**
     * 商家主页信息BO转换
     *
     * @param merchantDO
     * @return
     */
    public static MerchantInfoBO convertInfoBO(MerchantDO merchantDO) {
        if (merchantDO == null) {
            return null;
        }

        MerchantInfoBO merchantBO = new MerchantInfoBO();
        merchantBO.setAccount(merchantDO.getAccount());
        merchantBO.setHeadimg(merchantDO.getHeadimg());
        merchantBO.setGtCid(merchantDO.getGtCid());
        merchantBO.setRyToken(merchantDO.getRyToken());
        merchantBO.setLevel(merchantDO.getLevel());
        return merchantBO;
    }


    /**
     * 描述：将pageBOS转成pageDTOS
     *
     * @param pageMerchantBOS
     * @return
     */
    public static Page<MerchantDTO> convertPageDOTS(Page<MerchantBO> pageMerchantBOS) {
        Page<MerchantDTO> pageDTO = new Page<MerchantDTO>();
        List<MerchantBO> BOS = pageMerchantBOS.getRecords();
        List<MerchantDTO> DTOS = pageDTO.getRecords();
        for (MerchantBO merchantBO : BOS) {
            DTOS.add(convertDTO(merchantBO));
        }
        return pageDTO;
    }

    public static List<AccountDTO> convertAccountDOTS(List<MerchantBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<AccountDTO> list = new ArrayList<>();
        AccountDTO accountDTO;
        for (MerchantBO merchantBO : records) {
            accountDTO = new AccountDTO();
            accountDTO.setAccount(merchantBO.getAccount());
            accountDTO.setNum(merchantBO.getNum());
            accountDTO.setId(merchantBO.getId());
            accountDTO.setUserType(UserTypeEnum.MERCHANT);
            accountDTO.setFreeze(merchantBO.getIsFreeze());
            accountDTO.setGmtCreate(merchantBO.getGmtCreate());
            accountDTO.setFreezeReason(merchantBO.getFreezeReason());
            list.add(accountDTO);
        }
        return list;
    }

    public static MerchantDetailDTO convertDTO(MerchantDetailBO detailBO) {
        if (detailBO == null) {
            return null;
        }

        MerchantDetailDTO detailDTO = new MerchantDetailDTO();
        detailDTO.setName(detailBO.getName());
        detailDTO.setRegionName(detailBO.getRegionName());
        detailDTO.setAddress(detailBO.getAddress());
        detailDTO.setIndustryName(detailBO.getIndustryName());
        detailDTO.setKeywords(detailBO.getKeywords());
        detailDTO.setIntro(detailBO.getIntro());
        detailDTO.setStatusEnum(detailBO.getStatusEnum());
        detailDTO.setPrincipalName(detailBO.getPrincipalName());
        detailDTO.setPrincipalMobile(detailBO.getPrincipalMobile());
        detailDTO.setCompanyName(detailBO.getCompanyName());
        detailDTO.setRegNumber(detailBO.getRegNumber());
        detailDTO.setCompanyAddress(detailBO.getCompanyAddress());
        detailDTO.setLicenseIndate(detailBO.getLicenseIndate());
        detailDTO.setManageType(detailBO.getManageType());
        detailDTO.setCertifType(detailBO.getCertifType());
        detailDTO.setOperatorCardId(detailBO.getOperatorCardId());
        detailDTO.setOperatorName(detailBO.getOperatorName());
        detailDTO.setStoreUrl(detailBO.getStoreUrl());
        detailDTO.setEnvironmentUrl(detailBO.getEnvironmentUrl());
        detailDTO.setIdcardUrl(detailBO.getIdcardUrl());
        detailDTO.setLicenseUrl(detailBO.getLicenseUrl());
        detailDTO.setLogoUrl(detailBO.getLogoUrl());
        detailDTO.setOtherUrl(detailBO.getOtherUrl());
        detailDTO.setUserNum(detailBO.getUserNum());
        return detailDTO;
    }

}
