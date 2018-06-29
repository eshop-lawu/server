package com.lawu.eshop.activity.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/2/9.
 */
public class RedpacketSendRecordDTO {

    @ApiModelProperty(value = "红包发放记录id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
