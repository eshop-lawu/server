package com.lawu.eshop.user.srv.domain.extend;

import java.io.Serializable;
import java.util.Date;

/**
 * 我邀请的商家
 * 
 * @author zhangrc
 * @date 2017/03/30
 *
 */
public class InviterMerchantDOView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	/**
	 * 商家账号
	 */
	private String account;

	/**
	 * 商家电话
	 */
	private String mobile;

	/**
	 * 邀请人
	 */
	private Long inviterId;

	/**
	 * 邀请人类型
	 */
	private Byte inviterType;

	/**
	 * 店铺名称
	 */
	private String name;

	/**
	 * 所在区域
	 */
	private String regionName;

	/**
	 * 负责人名字
	 */
	private String principalName;

	/**
	 * 负责人手机
	 */
	private String principalMobile;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 是否审核
	 */
	private Byte status;

	/**
	 * 图片
	 */
	private String path;
	
	private String address;
	
	private Integer inviterCount;
	
	

	public Integer getInviterCount() {
		return inviterCount;
	}

	public void setInviterCount(Integer inviterCount) {
		this.inviterCount = inviterCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getPrincipalMobile() {
		return principalMobile;
	}

	public void setPrincipalMobile(String principalMobile) {
		this.principalMobile = principalMobile;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getInviterId() {
		return inviterId;
	}

	public void setInviterId(Long inviterId) {
		this.inviterId = inviterId;
	}

	public Byte getInviterType() {
		return inviterType;
	}

	public void setInviterType(Byte inviterType) {
		this.inviterType = inviterType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
