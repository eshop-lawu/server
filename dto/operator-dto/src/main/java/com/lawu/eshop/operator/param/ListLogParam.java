package com.lawu.eshop.operator.param;

import com.lawu.eshop.operator.constants.ModuleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
public class ListLogParam extends AbstractPageParam {

    @ApiModelProperty(value = "操作类型")
    private OperationTypeEnum typeEnum;

    @ApiModelProperty(value = "模块类型")
    private ModuleEnum moduleEnum;

    @ApiModelProperty(value = "排序名称")
    private String sortName;

    @ApiModelProperty(value = "排序类型")
    private String sortOrder;

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
