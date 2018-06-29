package com.lawu.eshop.operator.param;

import com.lawu.eshop.operator.constants.ModuleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
public class LogParam {

    @ApiModelProperty(value = "操作账号")
    private String account;

    @ApiModelProperty(value = "操作类型")
    private OperationTypeEnum typeEnum;

    @ApiModelProperty(value = "模块类型")
    private ModuleEnum moduleEnum;

    @ApiModelProperty(value = "业务ID")
    private String businessId;

    @ApiModelProperty(value = "变更标题")
    private String changeTitle;

    @ApiModelProperty(value = "变更内容")
    private String changeContent;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public OperationTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(OperationTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public ModuleEnum getModuleEnum() {
        return moduleEnum;
    }

    public void setModuleEnum(ModuleEnum moduleEnum) {
        this.moduleEnum = moduleEnum;
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
}
