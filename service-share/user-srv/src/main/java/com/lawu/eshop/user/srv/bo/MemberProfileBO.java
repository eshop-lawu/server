package com.lawu.eshop.user.srv.bo;

import java.io.Serializable;
import java.util.Date;

public class MemberProfileBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 主键，与member主键一致
	 */
	private Long id;

	/**
	 * 邀请会员数(一级)
	 * 
	 * @mbg.generated
	 */
	private Integer inviteMemberCount;

	/**
	 * 邀请会员数(二级)
	 */
	private Integer inviteMemberCount2;

	/**
	 * 邀请会员数(三级)
	 */
	private Integer inviteMemberCount3;

	/**
	 * 邀请商家数(一级)
	 */
	private Integer inviteMerchantCount;

	/**
	 * 邀请商家数(二级)
	 */
	private Integer inviteMerchantCount2;

	/**
	 * 邀请商家数(三级)
	 */
	private Integer inviteMerchantCount3;

	/**
	 * 修改日期
	 */
	private Date gmtModified;

	/**
	 * 创建日期
	 */
	private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getInviteMemberCount() {
		return inviteMemberCount;
	}

	public void setInviteMemberCount(Integer inviteMemberCount) {
		this.inviteMemberCount = inviteMemberCount;
	}

	public Integer getInviteMemberCount2() {
		return inviteMemberCount2;
	}

	public void setInviteMemberCount2(Integer inviteMemberCount2) {
		this.inviteMemberCount2 = inviteMemberCount2;
	}

	public Integer getInviteMemberCount3() {
		return inviteMemberCount3;
	}

	public void setInviteMemberCount3(Integer inviteMemberCount3) {
		this.inviteMemberCount3 = inviteMemberCount3;
	}

	public Integer getInviteMerchantCount() {
		return inviteMerchantCount;
	}

	public void setInviteMerchantCount(Integer inviteMerchantCount) {
		this.inviteMerchantCount = inviteMerchantCount;
	}

	public Integer getInviteMerchantCount2() {
		return inviteMerchantCount2;
	}

	public void setInviteMerchantCount2(Integer inviteMerchantCount2) {
		this.inviteMerchantCount2 = inviteMerchantCount2;
	}

	public Integer getInviteMerchantCount3() {
		return inviteMerchantCount3;
	}

	public void setInviteMerchantCount3(Integer inviteMerchantCount3) {
		this.inviteMerchantCount3 = inviteMerchantCount3;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}