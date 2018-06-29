package com.lawu.eshop.product.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.MemberGradeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * <p>
 * Description: 用户端商品详情页面，用户评价
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月26日 上午10:23:23
 *
 */
public class MemberProductCommentInfoDTO {

	@ApiModelProperty(value = "头像")
	private String headUrl;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "等级")
	private String level;

	@ApiModelProperty(value = "评分")
	private Byte grade;

	@ApiModelProperty(value = "时间")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(value = "评价内容")
	private String content;
	
	@ApiModelProperty(value = "回复内容")
    private String replyContent;
	
	@ApiModelProperty(value = "评价图片")
	private List<String> imageUrl;
	
	@ApiModelProperty(value = "是否匿名")
    private Boolean isAnonymous;
	
	@ApiModelProperty(value = "商品型号名称")
	private String productModel;

	@ApiModelProperty(value = "SILVER：白银会员、GOLD：黄金会员、PLATINUM：铂金会员、MASONRY：钻石会员、CROWN：皇冠会员")
	private MemberGradeEnum memberGrade;

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Byte getGrade() {
		return grade;
	}

	public void setGrade(Byte grade) {
		this.grade = grade;
	}

	public Boolean getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public List<String> getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(List<String> imageUrl) {
		this.imageUrl = imageUrl;
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
