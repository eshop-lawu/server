package com.lawu.eshop.mall.srv.converter;

import com.lawu.eshop.mall.dto.SmsRecordDTO;
import com.lawu.eshop.mall.srv.bo.SmsRecordBO;
import com.lawu.eshop.mall.srv.domain.SmsRecordDO;

/**
 * 短信转换器
 *
 * @author meishuquan
 * @date 2017/3/23
 */
public class SmsRecordConverter {

    /**
     * BO转换
     *
     * @param smsRecordDO
     * @return
     */
    public static SmsRecordBO convertBO(SmsRecordDO smsRecordDO) {
        if (smsRecordDO == null) {
            return null;
        }

        SmsRecordBO smsRecordBO = new SmsRecordBO();
        smsRecordBO.setId(smsRecordDO.getId());
        smsRecordBO.setContent(smsRecordDO.getContent());
        return smsRecordBO;
    }

    /**
     * DTO转换
     *
     * @param smsRecordBO
     * @return
     */
    public static SmsRecordDTO convertDTO(SmsRecordBO smsRecordBO) {
        if (smsRecordBO == null) {
            return null;
        }

        SmsRecordDTO smsRecordDTO = new SmsRecordDTO();
        smsRecordDTO.setId(smsRecordBO.getId());
        smsRecordDTO.setContent(smsRecordBO.getContent());
        return smsRecordDTO;
    }
}
