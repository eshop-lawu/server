package com.lawu.eshop.mall.srv.bo;

import java.util.Date;

/**
 * 优惠套餐购买须知BO
 * 
 * @author jiangxinjun
 * @date 2017年7月31日
 */
public class DiscountPackagePurchaseNotesBO {
	
    /**
     * 主键
     */
    private Integer id;

    /**
     * 购买须知选项
     */
    private String note;

    /**
     * 创建时间
     */
    private Date gmtCreate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
    
}