package com.lawu.eshop.mall.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.MemberGradeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/17.
 */
public class ProductCommentListDTO {
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headImg;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 评价内容
     */
    @ApiModelProperty(value = "评价内容")
    private String content;

    /**
     * 评价时间
     */
    @ApiModelProperty(value = "评价时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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

    @ApiModelProperty(value = "商品名称")
    private String name;
    @ApiModelProperty(value = "型号信息")
    private String spec;
    @ApiModelProperty(value = "型号价格")
    private String price;

    @ApiModelProperty(value = "评价ID")
    private Long id;
    @ApiModelProperty(value = "会员等级")
    private Integer level;
    @ApiModelProperty(value = "商品特征图片")
    private String featureImage;
    @ApiModelProperty(value = "商品评分")
    private Byte grade;
    @ApiModelProperty(value = "回复内容")
    private String replyContent;

    @ApiModelProperty(value = "SILVER：白银会员、GOLD：黄金会员、PLATINUM：铂金会员、MASONRY：钻石会员、CROWN：皇冠会员")
    private MemberGradeEnum memberGrade;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public MemberGradeEnum getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(MemberGradeEnum memberGrade) {
        this.memberGrade = memberGrade;
    }
}
