package com.lawu.eshop.mall.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
public class WindowMessageListDTO {

    @ApiModelProperty(value = "弹窗消息列表")
    private List<WindowMessageDTO> messageDTOS;

    public List<WindowMessageDTO> getMessageDTOS() {
        return messageDTOS;
    }

    public void setMessageDTOS(List<WindowMessageDTO> messageDTOS) {
        this.messageDTOS = messageDTOS;
    }

}
