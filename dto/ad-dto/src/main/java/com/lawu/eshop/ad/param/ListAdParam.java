package com.lawu.eshop.ad.param;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/29.
 */
public class ListAdParam extends AbstractPageParam{

    @ApiModelProperty(value = "广告标题")
    private String title;

    @ApiModelProperty(value = "类型")
    private AdTypeEnum typeEnum;

    @ApiModelProperty(value = "投放方式")
    private PutWayEnum putWayEnum;

    @ApiModelProperty(value = "状态")
    private AdStatusEnum statusEnum;

    @ApiModelProperty(value = "排序名称")
    private String sortName;

    @ApiModelProperty(value = "排序类型")
    private String sortOrder;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AdTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(AdTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public PutWayEnum getPutWayEnum() {
        return putWayEnum;
    }

    public void setPutWayEnum(PutWayEnum putWayEnum) {
        this.putWayEnum = putWayEnum;
    }

    public AdStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(AdStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
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
