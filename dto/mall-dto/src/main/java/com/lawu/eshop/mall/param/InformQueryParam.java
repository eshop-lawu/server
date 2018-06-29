/**
 * 
 */
package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.dto.InformEnum;
import com.lawu.eshop.mall.dto.InformStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

/**
 * 运营后台查询param
 * 
 * @author lihj
 * @date 2017年7月27日
 */
public class InformQueryParam extends AbstractPageParam{

	@ApiParam(name = "informType", value = "INFORM_TYPE_PLAT 平面广告、INFORM_TYPE_PRAISE E赞、INFORM_TYPE_MERCHANT 商家、INFORM_TYPE_GOODS 商品")
	private InformEnum informType;

	@ApiParam(name = "informtItemName", value = "被举报类型名称")
	private String informtItemName;

	@ApiParam(name = "content", value = "举报内容")
	private String content;

	@ApiParam(name = "status", value = "状态")
	private InformStatusEnum status;

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

	/**
	 * @return the status
	 */
	public InformStatusEnum getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(InformStatusEnum status) {
		this.status = status;
	}

}
