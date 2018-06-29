/**
 * 
 */
package com.lawu.eshop.mall.param;

import java.util.Date;

/**
 * @author lihj
 * @date 2017年7月27日
 */
public class InformSaveParam {

	/**
	 *
	 * 举报人用户编号 inform.informer_user_num
	 *
	 * @mbg.generated
	 */
	private String informerUserNum;

	/**
	 *
	 * 举报人账号 inform.informer_account
	 *
	 * @mbg.generated
	 */
	private String informerAccount;

	/**
	 *
	 * 举报类型:1平面广告、2E赞、3商家、4商品 inform.inform_type
	 *
	 * @mbg.generated
	 */
	private Byte informType;

	/**
	 *
	 * 被举报对象ID inform.informt_item_id
	 *
	 * @mbg.generated
	 */
	private Long informtItemId;

	/**
	 *
	 * 被举报对象名称 inform.informt_item_name
	 *
	 * @mbg.generated
	 */
	private String informtItemName;

	/**
	 *
	 * 举报内容 inform.content
	 *
	 * @mbg.generated
	 */
	private String content;

	/**
	 *
	 * 0：待处理，1：已处理，2：不处理 inform.status
	 *
	 * @mbg.generated
	 */
	private Byte status;

	/**
	 *
	 * 修改时间 inform.gmt_modified
	 *
	 * @mbg.generated
	 */
	private Date gmtModified;

	/**
	 *
	 * 创建时间 inform.gmt_create
	 *
	 * @mbg.generated
	 */
	private Date gmtCreate;

	/**
	 * @return the informerUserNum
	 */
	public String getInformerUserNum() {
		return informerUserNum;
	}

	/**
	 * @param informerUserNum
	 *            the informerUserNum to set
	 */
	public void setInformerUserNum(String informerUserNum) {
		this.informerUserNum = informerUserNum;
	}

	/**
	 * @return the informerAccount
	 */
	public String getInformerAccount() {
		return informerAccount;
	}

	/**
	 * @param informerAccount
	 *            the informerAccount to set
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
	 * @param informType
	 *            the informType to set
	 */
	public void setInformType(Byte informType) {
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

	/**
	 * @return the status
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * @return the gmtModified
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	/**
	 * @param gmtModified
	 *            the gmtModified to set
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * @return the gmtCreate
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/**
	 * @param gmtCreate
	 *            the gmtCreate to set
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}
