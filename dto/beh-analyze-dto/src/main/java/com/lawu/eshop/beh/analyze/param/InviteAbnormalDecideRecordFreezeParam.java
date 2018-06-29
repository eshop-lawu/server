package com.lawu.eshop.beh.analyze.param;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 冻结参数
 * @author jiangxinjun
 * @createDate 2018年1月18日
 * @updateDate 2018年1月18日
 */
@ApiModel
public class InviteAbnormalDecideRecordFreezeParam {

    /**
     * 冻结原因
     */
    @NotBlank(message = "冻结原因不能为空")
    @ApiModelProperty(value = "冻结原因", required = true)
    private String freezeReason;
    
    /**
     * 异常记录id列表
     */
    @ApiModelProperty(value = "异常记录id列表")
    private List<Long> ids;

    public String getFreezeReason() {
        return freezeReason;
    }

    public void setFreezeReason(String freezeReason) {
        this.freezeReason = freezeReason;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
    
}
