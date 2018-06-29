package com.lawu.eshop.activity.dto;

import java.util.List;

/**
 * @author zhangyong
 * @date 2018/6/8.
 */
public class MerchantRichPowerTaskDTO {

    private List<MerchantRichPowerTaskDetailDTO> detailDTOS;

    public List<MerchantRichPowerTaskDetailDTO> getDetailDTOS() {
        return detailDTOS;
    }

    public void setDetailDTOS(List<MerchantRichPowerTaskDetailDTO> detailDTOS) {
        this.detailDTOS = detailDTOS;
    }
}
