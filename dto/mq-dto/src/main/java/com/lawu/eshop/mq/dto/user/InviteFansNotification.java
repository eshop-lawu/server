package com.lawu.eshop.mq.dto.user;

import java.util.Date;

import com.lawu.compensating.transaction.Notification;

/**
 * @author meishuquan
 * @date 2017/11/6
 */
public class InviteFansNotification extends Notification {

    private static final long serialVersionUID = 5296186256850883794L;

    private Long fansInviteContentId;

    private Long fansInviteDetailId;

    private String userNum;

    private String point;

    private Long merchantId;

    private String regionName;

    private Integer inviteFansCount;

    private Byte sex;

    private String age;

    //主事务执行时间
    private Date gmtExecute;

    public Long getFansInviteContentId() {
        return fansInviteContentId;
    }

    public void setFansInviteContentId(Long fansInviteContentId) {
        this.fansInviteContentId = fansInviteContentId;
    }

    public Long getFansInviteDetailId() {
        return fansInviteDetailId;
    }

    public void setFansInviteDetailId(Long fansInviteDetailId) {
        this.fansInviteDetailId = fansInviteDetailId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getInviteFansCount() {
        return inviteFansCount;
    }

    public void setInviteFansCount(Integer inviteFansCount) {
        this.inviteFansCount = inviteFansCount;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }
}
