package com.lawu.eshop.mall.srv.converter;

import com.lawu.eshop.mall.constants.MerchantFavoredTypeEnum;
import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.eshop.mall.srv.bo.MerchantFavoredBO;
import com.lawu.eshop.mall.srv.domain.MerchantFavoredDO;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
public class MerchantFavoredConverter {

    public static MerchantFavoredBO coverBO(MerchantFavoredDO merchantFavoredDO) {
        if (merchantFavoredDO == null) {
            return null;
        }
        MerchantFavoredBO merchantFavoredBO = new MerchantFavoredBO();
        merchantFavoredBO.setId(merchantFavoredDO.getId());
        merchantFavoredBO.setType(merchantFavoredDO.getType());
        merchantFavoredBO.setValidDayBeginTime(merchantFavoredDO.getValidDayBeginTime());
        merchantFavoredBO.setValidDayEndTime(merchantFavoredDO.getValidDayEndTime());
        merchantFavoredBO.setValidWeekTime(merchantFavoredDO.getValidWeekTime());
        merchantFavoredBO.setDiscountRate(merchantFavoredDO.getDiscountRate());
        merchantFavoredBO.setFavoredAmount(merchantFavoredDO.getFavoredAmount());
        merchantFavoredBO.setReachAmount(merchantFavoredDO.getReachAmount());
        merchantFavoredBO.setEntireBeginTime(merchantFavoredDO.getEntireBeginTime());
        merchantFavoredBO.setEntireEndTime(merchantFavoredDO.getEntireEndTime());
        merchantFavoredBO.setStatus(merchantFavoredDO.getStatus());

        return merchantFavoredBO;
    }

    public static MerchantFavoredDTO coverDTO(MerchantFavoredBO merchantFavoredBO) {
        if (merchantFavoredBO == null) {
            return null;
        }
        MerchantFavoredDTO merchantFavoredDTO = new MerchantFavoredDTO();
        merchantFavoredDTO.setId(merchantFavoredBO.getId());
        merchantFavoredDTO.setMerchantId(merchantFavoredDTO.getMerchantId());
        merchantFavoredDTO.setDiscountRate(merchantFavoredBO.getDiscountRate());
        merchantFavoredDTO.setEntireBeginTime(merchantFavoredBO.getEntireBeginTime());
        merchantFavoredDTO.setValidDayEndTime(merchantFavoredBO.getValidDayEndTime());
        merchantFavoredDTO.setEntireEndTime(merchantFavoredBO.getEntireEndTime());
        merchantFavoredDTO.setFavoredAmount(merchantFavoredBO.getFavoredAmount());
        merchantFavoredDTO.setReachAmount(merchantFavoredBO.getReachAmount());
        merchantFavoredDTO.setTypeEnum(MerchantFavoredTypeEnum.getEnum(merchantFavoredBO.getType()));
        merchantFavoredDTO.setValidDayBeginTime(merchantFavoredBO.getValidDayBeginTime());
        merchantFavoredDTO.setValidWeekTime(merchantFavoredBO.getValidWeekTime());
        return merchantFavoredDTO;
    }
}
