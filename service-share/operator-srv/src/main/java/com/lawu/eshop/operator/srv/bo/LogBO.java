package com.lawu.eshop.operator.srv.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Sunny
 * @date 2017年5月3日
 */
public class LogBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 操作用户账号
     */
    private String account;

    /**
     * 操作类型
     */
    private Byte operationType;

    /**
     * 模块
     */
    private Byte module;

    /**
     * 业务id
     */
    private String businessId;

    /**
     * 变更标题
     */
    private String changeTitle;

    /**
     * 变更内容(JSON数据)
     */
    private String changeContent;

    /**
     * 创建时间
     */
    private Date gmtCreate;

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

	public Byte getOperationType() {
		return operationType;
	}

	public void setOperationType(Byte operationType) {
		this.operationType = operationType;
	}

	public Byte getModule() {
		return module;
	}

	public void setModule(Byte module) {
		this.module = module;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getChangeTitle() {
		return changeTitle;
	}

	public void setChangeTitle(String changeTitle) {
		this.changeTitle = changeTitle;
	}

	public String getChangeContent() {
		return changeContent;
	}

	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
    
    
}