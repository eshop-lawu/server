package com.lawu.eshop.mall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/7.
 */
public class CommentOperatorDTO {

    @ApiModelProperty(value = "评价id")
    private Long id;

    @ApiModelProperty(value = "评价内容")
    private String content;
    
    @ApiModelProperty(value = "评论对象id")
    private Long commentToId;

    @ApiModelProperty(value = "评价时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    
    @ApiModelProperty(value = "名称")
    private String name;
    
    @ApiModelProperty(value = "评价图片列表")
    private List urlImgs;
    
    @ApiModelProperty(value = "评价人id")
    private Long memberId;
    
    @ApiModelProperty(value = "评价人姓名")
    private String memberName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public Long getCommentToId() {
		return commentToId;
	}

	public void setCommentToId(Long commentToId) {
		this.commentToId = commentToId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getUrlImgs() {
		return urlImgs;
	}

	public void setUrlImgs(List urlImgs) {
		this.urlImgs = urlImgs;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	
    
	
    
}
