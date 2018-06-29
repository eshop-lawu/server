package com.lawu.eshop.mq.dto.ad;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.compensating.transaction.Notification;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * @author zhangrc
 * @date 2017年12月21日
 */
public class UserTakeInviterBountyNotification extends Notification {


    private static final long serialVersionUID = 524900152243811280L;

    private Long id;
    private String userNum;

    private BigDecimal money;

    private UserTypeEnum userType;

    //主事务执行时间
    private Date gmtExecute;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the userNum
     */
    public String getUserNum() {
        return userNum;
    }

    /**
     * @param userNum the userNum to set
     */
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    /**
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }
}
