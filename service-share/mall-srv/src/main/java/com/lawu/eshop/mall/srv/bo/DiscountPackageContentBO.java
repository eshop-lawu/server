package com.lawu.eshop.mall.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.common.constants.StatusEnum;

/**
 * 优惠套餐内容BO
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class DiscountPackageContentBO {
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 优惠套餐id
     */
    private Long discountPackageId;

    /**
     * 内容名称
     */
    private String name;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 单位
     */
    private String unit;

    /**
     * 小计
     */
    private BigDecimal subtotal;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
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