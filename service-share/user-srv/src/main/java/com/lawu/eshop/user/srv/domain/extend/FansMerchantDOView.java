package com.lawu.eshop.user.srv.domain.extend;

import java.io.Serializable;
import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/4/5.
 */
public class FansMerchantDOView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long memberId;

    private String num;

    private String account;

    private String regionPath;

    private String regionName;

    private String nickname;

    private String headimg;

    private Integer level;

    private Date gmtCreate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
