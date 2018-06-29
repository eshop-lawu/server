package com.lawu.eshop.property.srv.converter;

import com.lawu.eshop.property.dto.BusinessDepositDTO;
import com.lawu.eshop.property.srv.bo.BusinessDepositBO;
import com.lawu.eshop.property.srv.domain.BusinessDepositDO;

/**
 * 银行卡信息转换
 *
 * @author zhangrc
 * @date 2017/03/29
 */
public class BusinessDepositConverter {

    public static BusinessDepositBO convert(BusinessDepositDO businessDepositDO) {
        if(businessDepositDO == null){
            return null;
        }
        BusinessDepositBO businessDepositBO = new BusinessDepositBO();
        businessDepositBO.setId(businessDepositDO.getId());
        businessDepositBO.setBusinessId(businessDepositDO.getBusinessId());
        businessDepositBO.setUserNum(businessDepositDO.getUserNum());
        businessDepositBO.setBusinessName(businessDepositDO.getBusinessName());
        businessDepositBO.setDepositNumber(businessDepositDO.getDepositNumber());
        return businessDepositBO;
    }

    public static BusinessDepositDTO convert(BusinessDepositBO businessDepositBO) {
        if(businessDepositBO == null){
            return null;
        }
        BusinessDepositDTO businessDepositDTO = new BusinessDepositDTO();
        businessDepositBO.setId(businessDepositBO.getId());
        businessDepositBO.setBusinessId(businessDepositBO.getBusinessId());
        businessDepositBO.setUserNum(businessDepositBO.getUserNum());
        businessDepositBO.setBusinessName(businessDepositBO.getBusinessName());
        businessDepositBO.setDepositNumber(businessDepositBO.getDepositNumber());
        return businessDepositDTO;
    }
}
