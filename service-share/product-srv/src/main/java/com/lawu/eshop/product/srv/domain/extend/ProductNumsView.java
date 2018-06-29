package com.lawu.eshop.product.srv.domain.extend;

import java.util.Date;

public class ProductNumsView {
	
	private Long productId;
	
	private Integer num;
	 
	private String flag;
	 
	private Date gmtModified;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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
