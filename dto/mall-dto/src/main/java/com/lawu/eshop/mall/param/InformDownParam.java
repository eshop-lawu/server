/**
 * 
 */
package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.dto.InformEnum;

import io.swagger.annotations.ApiParam;

/**
 * 下架和不处理param
 * 
 * @author lihj
 * @date 2017年7月27日
 */
public class InformDownParam {

	@ApiParam(name = "id", required = true, value = "id")
	private Long id;

	@ApiParam(name = "status", required = true, value = "状态")
	private Integer status;

	@ApiParam(name = "informType", required = true, value = "INFORM_TYPE_PLAT 平面广告、INFORM_TYPE_PRAISE E赞、INFORM_TYPE_MERCHANT 商家、INFORM_TYPE_GOODS 商品")
	private InformEnum informType;

	@ApiParam(name = "informtItemId", required = true, value = "被举报类型id")
	private Long informtItemId;

	@ApiParam(name = "informtItemId", required = true, value = "描述")
	private String remark;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the informType
	 */
	public InformEnum getInformType() {
		return informType;
	}

	/**
	 * @param informType
	 *            the informType to set
	 */
	public void setInformType(InformEnum informType) {
		this.informType = informType;
	}

	/**
	 * @return the informtItemId
	 */
	public Long getInformtItemId() {
		return informtItemId;
	}

	/**
	 * @param informtItemId
	 *            the informtItemId to set
	 */
	public void setInformtItemId(Long informtItemId) {
		this.informtItemId = informtItemId;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
