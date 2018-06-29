package com.lawu.eshop.activity.query;

import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordStatusEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 钻石记录查询参数
 * @author jiangxinjun
 * @createDate 2018年6月7日
 * @updateDate 2018年6月7日
 */
@ApiModel(description = "钻石记录查询参数", parent = AbstractPageParam.class)
public class DiamondRecordQueryParam extends AbstractPageParam {
    
    /**
     * 分配记录id
     */
    @ApiModelProperty(value = "分配记录id")
    private Long relatedId;
    
    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private UserTypeEnum userType;
    
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String userNum;
    
    /**
     * E钻流向
     */
    @ApiModelProperty(value = "E钻流向")
    private RichPowerRecordDirectionEnum direction;
    
    /**
     * 钻石类型
     */
    @ApiModelProperty(value = "钻石类型")
    private RichDiamondRecordTypeEnum type;
    
    /**
     * 钻石来源
     */
    @ApiModelProperty(value = "钻石来源")
    private RichDiamondRecordSourceEnum source;
    
    /**
     * 钻石状态
     */
    @ApiModelProperty(value = "钻石状态")
    private RichDiamondRecordStatusEnum status;
    
    public Long getRelatedId() {
        return relatedId;
    }

    
    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public RichPowerRecordDirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(RichPowerRecordDirectionEnum direction) {
        this.direction = direction;
    }

    public RichDiamondRecordTypeEnum getType() {
        return type;
    }

    public void setType(RichDiamondRecordTypeEnum type) {
        this.type = type;
    }

    public RichDiamondRecordSourceEnum getSource() {
        return source;
    }

    public void setSource(RichDiamondRecordSourceEnum source) {
        this.source = source;
    }

    public RichDiamondRecordStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RichDiamondRecordStatusEnum status) {
        this.status = status;
    }
    
}
