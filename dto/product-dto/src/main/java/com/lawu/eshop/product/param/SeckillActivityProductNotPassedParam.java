package com.lawu.eshop.product.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 抢购活动商品不通过参数
 * 
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月27日
 */
@ApiModel
public class SeckillActivityProductNotPassedParam {
    
    /**
     * 反馈原因
     */
    @ApiModelProperty(value = "反馈原因", required = false)
    private String reasons;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商家id")
    private Long merchantId;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "活动开始时间")
    private String startDate;

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
