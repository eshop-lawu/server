package com.lawu.eshop.mall.srv.converter;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.dto.SmsRecordDTO;
import com.lawu.eshop.mall.srv.bo.SmsRecordBO;
import com.lawu.eshop.mall.srv.domain.SmsRecordDO;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class SmsRecordConverterTest {

    @Test
    public void convertBO() {
        SmsRecordDO smsRecordDO = new SmsRecordDO();
        smsRecordDO.setId(1L);
        smsRecordDO.setContent("12345");
        SmsRecordBO smsRecordBO = SmsRecordConverter.convertBO(smsRecordDO);
        Assert.assertEquals(smsRecordDO.getContent(), smsRecordBO.getContent());
    }

    @Test
    public void convertDTO() {
        SmsRecordBO smsRecordBO = new SmsRecordBO();
        smsRecordBO.setId(1L);
        smsRecordBO.setContent("12345");
        SmsRecordDTO smsRecordDTO = SmsRecordConverter.convertDTO(smsRecordBO);
        Assert.assertEquals(smsRecordBO.getContent(), smsRecordDTO.getContent());
    }
}
