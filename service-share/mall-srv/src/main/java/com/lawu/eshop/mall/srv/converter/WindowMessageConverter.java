package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.mall.constants.WindowMessageStatusEnum;
import com.lawu.eshop.mall.constants.WindowMessageTypeEnum;
import com.lawu.eshop.mall.dto.WindowMessageDTO;
import com.lawu.eshop.mall.dto.WindowMessageOperatorDTO;
import com.lawu.eshop.mall.srv.bo.WindowMessageBO;
import com.lawu.eshop.mall.srv.domain.WindowMessageDO;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
public class WindowMessageConverter {

    public static WindowMessageBO convertBO(WindowMessageDO messageDO) {
        if (messageDO == null) {
            return null;
        }

        WindowMessageBO messageBO = new WindowMessageBO();
        messageBO.setId(messageDO.getId());
        messageBO.setImgPath(messageDO.getImgPath());
        messageBO.setRelateId(messageDO.getRelateId());
        messageBO.setType(messageDO.getType());
        messageBO.setStatus(messageDO.getStatus());
        messageBO.setGmtModified(messageDO.getGmtModified());
        messageBO.setGmtCreate(messageDO.getGmtCreate());
        return messageBO;
    }

    public static List<WindowMessageBO> convertBOS(List<WindowMessageDO> messageDOS) {
        List<WindowMessageBO> messageBOS = new ArrayList<>();
        if (messageDOS == null || messageDOS.isEmpty()) {
            return messageBOS;
        }

        for (WindowMessageDO messageDO : messageDOS) {
            messageBOS.add(convertBO(messageDO));
        }
        return messageBOS;
    }

    public static WindowMessageDTO convertDTO(WindowMessageBO messageBO) {
        if (messageBO == null) {
            return null;
        }

        WindowMessageDTO messageDTO = new WindowMessageDTO();
        messageDTO.setId(messageBO.getId());
        messageDTO.setImgPath(messageBO.getImgPath());
        messageDTO.setRelateId(messageBO.getRelateId());
        messageDTO.setTypeEnum(WindowMessageTypeEnum.getEnum(messageBO.getType()));
        return messageDTO;
    }

    public static List<WindowMessageDTO> convertDTOS(List<WindowMessageBO> messageBOS) {
        List<WindowMessageDTO> messageDTOS = new ArrayList<>();
        if (messageBOS == null || messageBOS.isEmpty()) {
            return messageDTOS;
        }

        for (WindowMessageBO messageBO : messageBOS) {
            messageDTOS.add(convertDTO(messageBO));
        }
        return messageDTOS;
    }

    public static WindowMessageOperatorDTO convertOperatorDTO(WindowMessageBO messageBO) {
        if (messageBO == null) {
            return null;
        }

        WindowMessageOperatorDTO operatorDTO = new WindowMessageOperatorDTO();
        operatorDTO.setId(messageBO.getId());
        operatorDTO.setImgPath(messageBO.getImgPath());
        operatorDTO.setRelateId(messageBO.getRelateId());
        operatorDTO.setTypeEnum(WindowMessageTypeEnum.getEnum(messageBO.getType()));
        operatorDTO.setTypeDes(WindowMessageTypeEnum.getEnum(messageBO.getType()).getName());
        operatorDTO.setStatusEnum(WindowMessageStatusEnum.getEnum(messageBO.getStatus()));
        operatorDTO.setStatusDes(WindowMessageStatusEnum.getEnum(messageBO.getStatus()).getName());
        return operatorDTO;
    }

    public static List<WindowMessageOperatorDTO> convertOperatorDTOS(List<WindowMessageBO> messageBOS) {
        List<WindowMessageOperatorDTO> operatorDTOS = new ArrayList<>();
        if (messageBOS == null || messageBOS.isEmpty()) {
            return operatorDTOS;
        }

        for (WindowMessageBO messageBO : messageBOS) {
            operatorDTOS.add(convertOperatorDTO(messageBO));
        }
        return operatorDTOS;
    }

}
