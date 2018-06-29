package com.lawu.eshop.activity.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
public class RedpacketMchBillnoDTO {

    @ApiModelProperty(value = "商户订单号")
    private String mchBillno;

    @ApiModelProperty(value = "参与详情id")
    private Long attendDetailId;

    public String getMchBillno() {
        return mchBillno;
    }

    public void setMchBillno(String mchBillno) {
        this.mchBillno = mchBillno;
    }

    public Long getAttendDetailId() {
        return attendDetailId;
    }

    public void setAttendDetailId(Long attendDetailId) {
        this.attendDetailId = attendDetailId;
    }
}
