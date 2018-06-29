package com.lawu.eshop.activity.param;

import java.io.Serializable;

import com.lawu.eshop.activity.constants.AbnormalStatusEnum;
import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * 
 * @Description 
 * 运营平台红包活动查询实体
 * 
 * @author zhangrc
 * @date 2017年12月28日
 */
public class HelpRedpacketDetailOperatorParam extends AbstractPageParam implements Serializable{
    
    /**
     * 红包活动id
     */
    private Integer activityId;
    
	private String account;
	
	private ActivityAttendStatusEnum statusEnum;
	
    private String sortOrder;
    
    private Boolean isLucky;
    
    /**
     * 异常状态
     */
    private AbnormalStatusEnum abnormalStatus;

	public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Boolean getIsLucky() {
		return isLucky;
	}

	public void setIsLucky(Boolean isLucky) {
		this.isLucky = isLucky;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public ActivityAttendStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(ActivityAttendStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

    public AbnormalStatusEnum getAbnormalStatus() {
        return abnormalStatus;
    }

    public void setAbnormalStatus(AbnormalStatusEnum abnormalStatus) {
        this.abnormalStatus = abnormalStatus;
    }
	
}
