package com.lawu.eshop.mall.srv.converter;

import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.eshop.mall.srv.bo.VerifyCodeBO;
import com.lawu.eshop.mall.srv.domain.VerifyCodeDO;

/**
 * @author meishuquan
 * @date 2017/3/28.
 */
public class VerifyCodeConverter {

    /**
     * BO转换
     *
     * @param verifyCodeDO
     * @return
     */
    public static VerifyCodeBO convertBO(VerifyCodeDO verifyCodeDO) {
        if (verifyCodeDO == null) {
            return null;
        }

        VerifyCodeBO verifyCodeBO = new VerifyCodeBO();
        verifyCodeBO.setId(verifyCodeDO.getId());
        verifyCodeBO.setMobile(verifyCodeDO.getMobile());
        verifyCodeBO.setCode(verifyCodeDO.getCode());
        verifyCodeBO.setGmtCreate(verifyCodeDO.getGmtCreate());
        return verifyCodeBO;
    }

    /**
     * DTO转换
     *
     * @param verifyCodeBO
     * @return
     */
    public static VerifyCodeDTO convertDTO(VerifyCodeBO verifyCodeBO) {
        if (verifyCodeBO == null) {
            return null;
        }

        VerifyCodeDTO verifyCodeDTO = new VerifyCodeDTO();
        verifyCodeDTO.setId(verifyCodeBO.getId());
        verifyCodeDTO.setMobile(verifyCodeBO.getMobile());
        verifyCodeDTO.setCode(verifyCodeBO.getCode());
        verifyCodeDTO.setGmtCreate(verifyCodeBO.getGmtCreate());
        return verifyCodeDTO;
    }
}
