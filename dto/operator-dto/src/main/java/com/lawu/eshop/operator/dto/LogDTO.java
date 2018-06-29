package com.lawu.eshop.operator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.operator.constants.ModuleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
public class LogDTO {

    @ApiModelProperty(value = "ID ")
    private  Long id;

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

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
