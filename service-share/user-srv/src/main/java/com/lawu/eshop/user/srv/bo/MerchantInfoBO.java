package com.lawu.eshop.user.srv.bo;

/**
 * 商家主页信息
 * Created by Administrator on 2017/3/24.
 */
public class MerchantInfoBO {
    /**
     * 商家账号
     */
    private String account;

    /**
     * 商家头像
     */
    private String headimg;

    private String gtCid;

    private String ryToken;

    private String name;

    private String regionPath;

    private Integer level;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getGtCid() {
        return gtCid;
    }

    public void setGtCid(String gtCid) {
        this.gtCid = gtCid;
    }

    public String getRyToken() {
        return ryToken;
    }

    public void setRyToken(String ryToken) {
        this.ryToken = ryToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
