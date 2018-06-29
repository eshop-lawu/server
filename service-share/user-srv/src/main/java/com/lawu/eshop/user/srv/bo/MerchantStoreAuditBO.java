package com.lawu.eshop.user.srv.bo;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/4/13.
 */
public class MerchantStoreAuditBO {
    /**
     *
     * 主键
     * merchant_store_audit.id
     *
     * @mbg.generated 2017-03-31 15:41:45
     */
    private Long id;

    /**
     *
     * 商家
     * merchant_store_audit.merchant_id
     *
     * @mbg.generated 2017-03-31 15:41:45
     */
    private Long merchantId;

    /**
     *
     * 门店
     * merchant_store_audit.merchant_store_id
     *
     * @mbg.generated 2017-03-31 15:41:45
     */
    private Long merchantStoreId;

    /**
     *
     * 审核状态
     * merchant_store_audit.status
     *
     * @mbg.generated 2017-03-31 15:41:45
     */
    private Byte status;

    private Byte type;

    /**
     *
     * 审核人员ID
     * merchant_store_audit.auditor_id
     *
     * @mbg.generated 2017-03-31 15:41:45
     */
    private Integer auditorId;

    /**
     *
     * 审核备注
     * merchant_store_audit.remark
     *
     * @mbg.generated 2017-03-31 15:41:45
     */
    private String remark;

    /**
     *
     * 审核时间
     * merchant_store_audit.audit_time
     *
     * @mbg.generated 2017-03-31 15:41:45
     */
    private Date auditTime;

    /**
     *
     * 同步时间
     * merchant_store_audit.syn_time
     *
     * @mbg.generated 2017-03-31 15:41:45
     */
    private Date synTime;

    /**
     *
     * 修改时间
     * merchant_store_audit.gmt_modified
     *
     * @mbg.generated 2017-03-31 15:41:45
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * merchant_store_audit.gmt_create
     *
     * @mbg.generated 2017-03-31 15:41:45
     */
    private Date gmtCreate;

    /**
     *
     * 修改内容json
     * merchant_store_audit.content
     *
     * @mbg.generated 2017-03-31 15:41:45
     */
    private String content;

    private String keywords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getSynTime() {
        return synTime;
    }

    public void setSynTime(Date synTime) {
        this.synTime = synTime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
