package com.lawu.eshop.mall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
public class CommentDTO {
    /**
     * 评价内容
     */
    @ApiModelProperty(value = "评价内容")
    private String content;

    @ApiModelProperty(value = "回复内容")
    private String replyContent;

    /**
     * 评价时间
     */
    @ApiModelProperty(value = "评价时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 评价图片
     */
    @ApiModelProperty(value = "评价图片")
    private List imgUrls;

    /**
     * 是否匿名
     */
    @ApiModelProperty(value = "是否匿名")
    private Boolean isAnonymous;

    /**
     * 评价用户
     */
    @ApiModelProperty(value = "评价用户")
    private Long memberId;
    /**
     * 评价ID
     */
    @ApiModelProperty(value = "评价ID")
    private Long id;

    @ApiModelProperty(value = "单个评分")
    private Byte grade;
    @ApiModelProperty(value = "综合评分")
    private Float avgGrade;

    @ApiModelProperty(value = "人均消费")
    private BigDecimal avgSpend;
    @ApiModelProperty(value = "商品ID")
    private Long productId;
    @ApiModelProperty(value = "商品型号ID")
    private Long productModelId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public List getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public Float getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Float avgGrade) {
        this.avgGrade = avgGrade;
    }

    public BigDecimal getAvgSpend() {
        return avgSpend;
    }

    public void setAvgSpend(BigDecimal avgSpend) {
        this.avgSpend = avgSpend;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(Long productModelId) {
        this.productModelId = productModelId;
    }
}
