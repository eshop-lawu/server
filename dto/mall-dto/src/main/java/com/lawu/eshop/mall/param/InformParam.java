/**
 * 
 */
package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.dto.InformEnum;

import io.swagger.annotations.ApiParam;

/**
 * API添加用param
 * 
 * @author lihj
 * @date 2017年7月27日
 */
public class InformParam {

	@ApiParam(name = "informType", required = true, value = "INFORM_TYPE_PLAT 平面广告、INFORM_TYPE_PRAISE E赞、INFORM_TYPE_MERCHANT 商家、INFORM_TYPE_GOODS 商品、INFORM_TYPE_VIDEO视频")
	private InformEnum informType;

	@ApiParam(name = "informtItemId", required = true, value = "被举报类型id")
	private Long informtItemId;

	@ApiParam(name = "informtItemName", required = true, value = "被举报类型名称")
	private String informtItemName;

	@ApiParam(name = "content", value = "举报内容", required = true)
	private String content;

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
	 * @return the informtItemName
	 */
	public String getInformtItemName() {
		return informtItemName;
	}

	/**
	 * @param informtItemName
	 *            the informtItemName to set
	 */
	public void setInformtItemName(String informtItemName) {
		this.informtItemName = informtItemName;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
