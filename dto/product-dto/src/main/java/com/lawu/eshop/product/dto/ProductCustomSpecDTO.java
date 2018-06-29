package com.lawu.eshop.product.dto;

import com.lawu.eshop.product.constant.CustomSpecStatusEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月16日
 */
public class ProductCustomSpecDTO {

	/**
	 *
	 * 主键 product_custom_spec.id
	 *
	 * @mbg.generated
	 */
	private Long id;

	/**
	 *
	 * 关联ID：类型/品牌-商品ID，规格项-商品型号ID product_custom_spec.relate_id
	 *
	 * @mbg.generated
	 */
	private Long relateId;

	/**
	 *
	 * 关联名称，类型-上下级名称，品牌-品牌名称，规格项-上下级类型+规格名称 product_custom_spec.relate_name
	 *
	 * @mbg.generated
	 */
	private String relateName;

	/**
	 *
	 * 自定义规格名称 product_custom_spec.spec_name
	 *
	 * @mbg.generated
	 */
	private String specName;

	/**
	 *
	 * 状态(0-删除1-有效) product_custom_spec.status
	 *
	 * @mbg.generated
	 */
	private CustomSpecStatusEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRelateId() {
		return relateId;
	}

	public void setRelateId(Long relateId) {
		this.relateId = relateId;
	}

	public String getRelateName() {
		return relateName;
	}

	public void setRelateName(String relateName) {
		this.relateName = relateName;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public CustomSpecStatusEnum getStatus() {
		return status;
	}

	public void setStatus(CustomSpecStatusEnum status) {
		this.status = status;
	}

	

}
