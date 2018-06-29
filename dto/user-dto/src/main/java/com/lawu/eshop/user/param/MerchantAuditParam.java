package com.lawu.eshop.user.param;

import com.lawu.eshop.user.constants.MerchantAuditStatusEnum;
import com.lawu.eshop.user.dto.param.MerchantAuditTypeEnum;
import io.swagger.annotations.ApiParam;

/**
 * @author zhangyong
 * @date 2017/3/31.
 */
public class MerchantAuditParam {

    /**
     * 门店审核状态
     */
    @ApiParam(name = "auditStatusEnum", value = "审核状态",required = true)
    private MerchantAuditStatusEnum auditStatusEnum;


    /**
     * 审核备注
     */
    @ApiParam(name = "remark", value = "审核备注")
    private String remark;

    @ApiParam(name = "typeEnum", value = "审核类型：申请实体店：AUDIT_TYPE_STORE，修改资料：AUDIT_TYPE_EDIT_INFO",required = true)
    private MerchantAuditTypeEnum typeEnum;

    /**
     * 门店id
     */
    @ApiParam(name = "merchantStoreId", value = "门店id",required = true)
    private Long merchantStoreId;

    @ApiParam(name = "auditorId", value = "审核人员ID")
    private Integer auditorId;

    public MerchantAuditStatusEnum getAuditStatusEnum() {
        return auditStatusEnum;
    }

    public void setAuditStatusEnum(MerchantAuditStatusEnum auditStatusEnum) {
        this.auditStatusEnum = auditStatusEnum;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public MerchantAuditTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(MerchantAuditTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }
}
