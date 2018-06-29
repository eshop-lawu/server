package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/4/24.
 */
public class FansInviteDetailBO {

    private String regionName;

    private Byte sex;

    private String age;

    private Integer inviteFansCount;

    private BigDecimal consumePoint;

    private Date gmtCreate;

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

    public BigDecimal getConsumePoint() {
        return consumePoint;
    }

    public void setConsumePoint(BigDecimal consumePoint) {
        this.consumePoint = consumePoint;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
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
}
