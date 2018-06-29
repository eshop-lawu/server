package com.lawu.eshop.mall.srv.bo;

import java.util.Date;

import com.lawu.eshop.common.constants.StatusEnum;

public class DiscountPackageImageBO {
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 优惠套餐id
     */
    private Long discountPackageId;

    /**
     * 文字描述
     */
    private String description;

    /**
     * 图片
     */
    private String image;

    /**
     * 状态
     */
    private StatusEnum status;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDiscountPackageId() {
		return discountPackageId;
	}

	public void setDiscountPackageId(Long discountPackageId) {
		this.discountPackageId = discountPackageId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}