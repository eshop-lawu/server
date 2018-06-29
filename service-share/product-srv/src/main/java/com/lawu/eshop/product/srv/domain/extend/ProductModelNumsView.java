package com.lawu.eshop.product.srv.domain.extend;

import java.util.Date;

public class ProductModelNumsView {

	/**
	 * 商品型号id
	 */
	private Long productModelId;

	/**
	 * 增减量
	 */
	private Integer num;

	/**
	 * A-增加|M-减少
	 */
	private String flag;

	/**
	 * 更新时间
	 */
	private Date gmtModified;

	public Long getProductModelId() {
		return productModelId;
	}

	public void setProductModelId(Long productModelId) {
		this.productModelId = productModelId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

}
