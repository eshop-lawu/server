package com.lawu.eshop.user.param;

import java.util.Date;

import com.lawu.eshop.user.constants.WxBindTypeEnum;

/**
 * @author meishuquan
 * @date 2018/1/2.
 */
public class WeixinUserParam {

    private String openid;
    private String nickname;
    private Integer sex;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private Boolean subscribe;
    private String language;
    private Date subscribeTime;
    private String unionid;
    private String remark;
    private Long groupid;
    private String tagidList;
    
    private WxBindTypeEnum typeEnum;

	public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList;
    }

	public WxBindTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(WxBindTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}
    
}
