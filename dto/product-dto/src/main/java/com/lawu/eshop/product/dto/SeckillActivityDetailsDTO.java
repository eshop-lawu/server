package com.lawu.eshop.product.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * 抢购活动详细信息DTO
 * 
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月27日
 */
public class SeckillActivityDetailsDTO extends SeckillActivityInfoDTO {
    
    /**
     * 结束时间(yyyy-MM-dd HH:mm)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "结束时间(yyyy-MM-dd HH:mm)", required = true)
    private Date endDate;
    
    
    /**
     * 商家可提交审核的商品数
     */
    @ApiModelProperty(value = "商家可提交审核的商品数", required = true)
    private Integer productValidCount;
    
    /**
    * 首页图片
    */
    @ApiModelProperty(value = "首页图片", required = true)
    private String homePicture;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getProductValidCount() {
        return productValidCount;
    }

    public void setProductValidCount(Integer productValidCount) {
        this.productValidCount = productValidCount;
    }

    public String getHomePicture() {
        return homePicture;
    }

    public void setHomePicture(String homePicture) {
        this.homePicture = homePicture;
    }
    
}