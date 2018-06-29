package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.constants.WindowMessageStatusEnum;
import com.lawu.eshop.mall.constants.WindowMessageTypeEnum;
import com.lawu.eshop.mall.dto.WindowMessageDTO;
import com.lawu.eshop.mall.dto.WindowMessageOperatorDTO;
import com.lawu.eshop.mall.srv.bo.WindowMessageBO;
import com.lawu.eshop.mall.srv.domain.WindowMessageDO;

/**
 * @author meishuquan
 * @date 2018/3/5.
 */
public class WindowMessageConverterTest {

    @Test
    public void convertBO() {
        WindowMessageDO messageDO = new WindowMessageDO();
        messageDO.setId(100L);
        messageDO.setImgPath("test");
        messageDO.setRelateId(10L);
        messageDO.setType((byte) 1);
        messageDO.setStatus((byte) 1);
        messageDO.setGmtModified(new Date());
        messageDO.setGmtCreate(new Date());
        WindowMessageBO messageBO = WindowMessageConverter.convertBO(messageDO);
        Assert.assertNotNull(messageBO);
        Assert.assertEquals(messageDO.getId(), messageBO.getId());
    }

    @Test
    public void convertBOS() {
        List<WindowMessageDO> messageDOS = new ArrayList<>();
        WindowMessageDO messageDO = new WindowMessageDO();
        messageDO.setId(100L);
        messageDO.setImgPath("test");
        messageDO.setRelateId(10L);
        messageDO.setType((byte) 1);
        messageDO.setStatus((byte) 1);
        messageDO.setGmtModified(new Date());
        messageDO.setGmtCreate(new Date());
        messageDOS.add(messageDO);

        List<WindowMessageBO> messageBOS = WindowMessageConverter.convertBOS(messageDOS);
        Assert.assertNotNull(messageBOS);
        Assert.assertEquals(messageDO.getId(), messageBOS.get(0).getId());
    }

    @Test
    public void convertDTO() {
        WindowMessageBO messageBO = new WindowMessageBO();
        messageBO.setId(100L);
        messageBO.setImgPath("test");
        messageBO.setRelateId(10L);
        messageBO.setType(WindowMessageTypeEnum.POINT_LOTTERY.getVal());

        WindowMessageDTO messageDTO = WindowMessageConverter.convertDTO(messageBO);
        Assert.assertNotNull(messageDTO);
        Assert.assertEquals(messageBO.getId(), messageDTO.getId());
    }

    @Test
    public void convertDTOS() {
        List<WindowMessageBO> messageBOS = new ArrayList<>();
        WindowMessageBO messageBO = new WindowMessageBO();
        messageBO.setId(100L);
        messageBO.setImgPath("test");
        messageBO.setRelateId(10L);
        messageBO.setType(WindowMessageTypeEnum.POINT_LOTTERY.getVal());
        messageBOS.add(messageBO);

        List<WindowMessageDTO> messageDTOS = WindowMessageConverter.convertDTOS(messageBOS);
        Assert.assertNotNull(messageDTOS);
        Assert.assertEquals(messageBO.getId(), messageDTOS.get(0).getId());
    }

    @Test
    public void convertOperatorDTO() {
        WindowMessageBO messageBO = new WindowMessageBO();
        messageBO.setId(100L);
        messageBO.setImgPath("test");
        messageBO.setRelateId(10L);
        messageBO.setType(WindowMessageTypeEnum.POINT_LOTTERY.getVal());
        messageBO.setStatus(WindowMessageStatusEnum.ENABLE.getVal());
        WindowMessageOperatorDTO operatorDTO = WindowMessageConverter.convertOperatorDTO(messageBO);
        Assert.assertNotNull(operatorDTO);
        Assert.assertEquals(messageBO.getId(), operatorDTO.getId());
    }

    @Test
    public void convertOperatorDTOS() {
        List<WindowMessageBO> messageBOS = new ArrayList<>();
        WindowMessageBO messageBO = new WindowMessageBO();
        messageBO.setId(100L);
        messageBO.setImgPath("test");
        messageBO.setRelateId(10L);
        messageBO.setType(WindowMessageTypeEnum.POINT_LOTTERY.getVal());
        messageBO.setStatus(WindowMessageStatusEnum.ENABLE.getVal());
        messageBOS.add(messageBO);

        List<WindowMessageOperatorDTO> operatorDTOS = WindowMessageConverter.convertOperatorDTOS(messageBOS);
        Assert.assertNotNull(operatorDTOS);
        Assert.assertEquals(messageBO.getId(), operatorDTOS.get(0).getId());
    }

}
