package com.lawu.eshop.mall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月26日 下午12:59:57
 *
 */
public class MemberProductCommentDTO {

	@ApiModelProperty(value = "评价内容")
    private String content;

    @ApiModelProperty(value = "回复内容")
    private String replyContent;

    @ApiModelProperty(value = "评价时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "评价图片")
    private List<String> imgUrls;

    @ApiModelProperty(value = "是否匿名")
    private Boolean isAnonymous;

    @ApiModelProperty(value = "评价用户")
    private Long memberId;

    @ApiModelProperty(value = "单个评分")
    private Byte grade;
    
    @ApiModelProperty(value = "商品型号ID")
    private Long productModelId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public List<String> getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}

	public Boolean getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Byte getGrade() {
		return grade;
	}

	public void setGrade(Byte grade) {
		this.grade = grade;
	}

	public Long getProductModelId() {
		return productModelId;
	}

	public void setProductModelId(Long productModelId) {
		this.productModelId = productModelId;
	}

   
}
