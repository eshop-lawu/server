package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * @author lihj
 * @date 2018/5/2
 */
public class RichDiamondRecordBO {

    /**
     * 主键
     */
    private Long id;
    
    /**
     * 关联id
     */
    private Long relatedId;

    /**
     * 用户编号
     */
    private String userNum;
    
    /**
    * 用户类型
    */
    private UserTypeEnum userType;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型 1-E钻 2-幸运钻
     */
    private RichDiamondRecordTypeEnum typeEnum;

    /**
     * 来源 1-日常领取
     */
    private RichDiamondRecordSourceEnum sourceEnum;

    /**
     * 1-支出 2-收入
     */
    private RichPowerRecordDirectionEnum directionEnum;

    /**
     * 数量
     */
    private BigDecimal amount;
    
    /**
     * 领取时间
     */
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

    public RichDiamondRecordTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(RichDiamondRecordTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public RichDiamondRecordSourceEnum getSourceEnum() {
		return sourceEnum;
	}

	public void setSourceEnum(RichDiamondRecordSourceEnum sourceEnum) {
		this.sourceEnum = sourceEnum;
	}

	public RichPowerRecordDirectionEnum getDirectionEnum() {
        return directionEnum;
    }

    public void setDirectionEnum(RichPowerRecordDirectionEnum directionEnum) {
        this.directionEnum = directionEnum;
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
