/**
 * 
 */
package com.lawu.eshop.mall.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 运营后台列表
 * @author lihj
 * @date 2017年7月28日
 */
public class InformDTO {

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "举报人账号")
	private String informerAccount;

	@ApiModelProperty(value = "举报类型")
	private Byte informType;

	@ApiModelProperty(value = "举报类型中文显示")
	private String informTypeStr;

	@ApiModelProperty(value = "被举报对象ID")
	private Long informtItemId;

	@ApiModelProperty(value = "被举报对象名称")
	private String informtItemName;

	@ApiModelProperty(value = "举报内容")
	private String content;

	@ApiModelProperty(value = "状态")
	private Byte status;
	
	@ApiModelProperty(value = "状态中文")
	private String statusStr;
	

	@ApiModelProperty(value = "审核人员名称")
	private String auditorName;

	@ApiModelProperty(value = "审核备注")
	private String remark;

	@ApiModelProperty(value = "举报时间")
	private Date gmtCreate;

	@ApiModelProperty(value = "编号")
	private String userNum;

	@ApiModelProperty(value = "商家账号")
	private String merchantAccount;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the informerAccount
	 */
	public String getInformerAccount() {
		return informerAccount;
	}

	/**
	 * @param informerAccount the informerAccount to set
	 */
	public void setInformerAccount(String informerAccount) {
		this.informerAccount = informerAccount;
	}

	/**
	 * @return the informType
	 */
	public Byte getInformType() {
		return informType;
	}

	/**
	 * @param informType the informType to set
	 */
	public void setInformType(Byte informType) {
		this.informType = informType;
	}

	/**
	 * @return the informTypeStr
	 */
	public String getInformTypeStr() {
		return informTypeStr;
	}

	/**
	 * @param informTypeStr the informTypeStr to set
	 */
	public void setInformTypeStr(String informTypeStr) {
		this.informTypeStr = informTypeStr;
	}

	/**
	 * @return the informtItemId
	 */
	public Long getInformtItemId() {
		return informtItemId;
	}

	/**
	 * @param informtItemId the informtItemId to set
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
	 * @param informtItemName the informtItemName to set
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
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the status
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * @return the auditorName
	 */
	public String getAuditorName() {
		return auditorName;
	}

	/**
	 * @param auditorName the auditorName to set
	 */
	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the gmtCreate
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/**
	 * @param gmtCreate the gmtCreate to set
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/**
	 * @return the statusStr
	 */
	public String getStatusStr() {
		return statusStr;
	}

	/**
	 * @param statusStr the statusStr to set
	 */
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount;
	}
}
