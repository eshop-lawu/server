package com.lawu.eshop.user.param;

import com.lawu.eshop.user.constants.MerchantAuditStatusEnum;
import com.lawu.eshop.user.dto.param.MerchantAuditTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * meishuquan 2017/4/27.
 */
public class ListStoreAuditParam extends AbstractPageParam {

    @ApiModelProperty(value = "状态")
    private MerchantAuditStatusEnum statusEnum;

    @ApiModelProperty(value = "审核类型")
    private MerchantAuditTypeEnum typeEnum;

    @ApiModelProperty(value = "排序名称")
    private String sortName;

    @ApiModelProperty(value = "排序类型")
    private String sortOrder;

    public MerchantAuditStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(MerchantAuditStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public MerchantAuditTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(MerchantAuditTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
