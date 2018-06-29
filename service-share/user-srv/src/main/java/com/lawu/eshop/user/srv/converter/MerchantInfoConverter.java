package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.MerchantInfoDTO;
import com.lawu.eshop.user.param.MerchantProfileParam;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantProfileBO;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;

/**
 * 商家扩展信息转换器
 * Created by zhangyong on 2017/3/23.
 */
public class MerchantInfoConverter {

    /**
     * BO转换
     *
     * @param merchantProfileDO
     * @return
     */
    public static MerchantProfileBO convertBO(MerchantProfileDO merchantProfileDO) {
        if (merchantProfileDO == null) {
            return null;
        }

        MerchantProfileBO merchantProfileBO = new MerchantProfileBO();
        merchantProfileBO.setId(merchantProfileDO.getId());
        merchantProfileBO.setInviteMemberCount(merchantProfileDO.getInviteMemberCount()+merchantProfileDO.getInviteMemberCount2()+merchantProfileDO.getInviteMemberCount3());
        merchantProfileBO.setInviteMerchantCount(merchantProfileDO.getInviteMerchantCount()+merchantProfileDO.getInviteMerchantCount2()+merchantProfileDO.getInviteMerchantCount3());

        return merchantProfileBO;

    }

    /**
     * param 转DO
     * @param merchantProfileParam
     * @return
     */
    public static MerchantProfileDO paramConvertDO(MerchantProfileParam merchantProfileParam) {
        if (merchantProfileParam == null) {
            return null;
        }

        MerchantProfileDO merchantProfileDO = new MerchantProfileDO();
        merchantProfileDO.setJdUrl(merchantProfileParam.getJdUrl());
        merchantProfileDO.setTaobaoUrl(merchantProfileParam.getTaobaoUrl());
        merchantProfileDO.setTmallUrl(merchantProfileParam.getTmallUrl());
        merchantProfileDO.setWebsiteUrl(merchantProfileParam.getWebsiteUrl());

        return merchantProfileDO;

    }

    /**
     *
     * @param merchantProfileBO
     * @return
     */
    public static MerchantInfoDTO coverConverDTO(MerchantProfileBO merchantProfileBO, MerchantInfoBO merchantInfoBO){
        if(merchantProfileBO == null){
            return null;
        }

        MerchantInfoDTO merchantProfileDTO = new MerchantInfoDTO();
        merchantProfileDTO.setInviteMemberCount(merchantProfileBO.getInviteMemberCount());
        merchantProfileDTO.setInviteMerchantCount(merchantProfileBO.getInviteMerchantCount());
        merchantProfileDTO.setAccount(merchantInfoBO.getAccount());
        merchantProfileDTO.setHeadimg(merchantInfoBO.getHeadimg());
        return merchantProfileDTO;

    }

}
