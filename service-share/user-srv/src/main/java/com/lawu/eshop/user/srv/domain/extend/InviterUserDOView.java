package com.lawu.eshop.user.srv.domain.extend;

import java.io.Serializable;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年6月16日 上午11:23:47
 *
 */
public class InviterUserDOView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long inviterId;
	private Byte status;
	private Byte inviterType;
	private String content;
	private int offset;
	private int limit;
	public Long getInviterId() {
		return inviterId;
	}
	public void setInviterId(Long inviterId) {
		this.inviterId = inviterId;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Byte getInviterType() {
		return inviterType;
	}
	public void setInviterType(Byte inviterType) {
		this.inviterType = inviterType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	

	
}
