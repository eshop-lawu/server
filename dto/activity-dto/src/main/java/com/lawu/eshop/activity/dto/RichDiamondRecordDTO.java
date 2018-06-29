package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 钻石记录DTO
 * @author jiangxinjun
 * @createDate 2018年6月7日
 * @updateDate 2018年6月7日
 */
public class RichDiamondRecordDTO {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    
    /**
     * 关联id
     */
    @ApiModelProperty(value = "关联id", required = true)
    private Long relatedId;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private String userNum;
    
    /**
    * 用户类型
    */
    @ApiModelProperty(value = "用户类型", required = true)
    private UserTypeEnum userType;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    private String title;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型", required = true)
    private RichDiamondRecordTypeEnum type;

    /**
     * 来源
     */
    @ApiModelProperty(value = "来源", required = true)
    private RichDiamondRecordSourceEnum source;

    /**
     * 钻石流向
     */
    @ApiModelProperty(value = "钻石流向", required = true)
    private RichPowerRecordDirectionEnum direction;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", required = true)
    private BigDecimal amount;
    
    /**
     * 领取时间
     */
    @ApiModelProperty(value = "领取时间", required = true)
    private Date takeTime;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public RichPowerRecordDirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(RichPowerRecordDirectionEnum direction) {
        this.direction = direction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

	public Date getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}
    
}
