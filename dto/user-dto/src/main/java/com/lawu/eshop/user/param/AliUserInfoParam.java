package com.lawu.eshop.user.param;

/**
 * alipay.user.info.share(支付宝会员授权信息查询接口) 返回数据实体
 */
public class AliUserInfoParam {

    /**
     * //支付宝用户的userId	2088102104794936
     */
    private String aliUserId;
    /**
     * //用户头像地址	http://tfsimg.alipay.com/images/partner/T1uIxXXbpXXXXXXXX
     */
    private String avatar;
    /**
     * //省份名称	安徽省
     */
    private String province;
    /**
     * //市名称。	安庆
     */
    private String city;
    /**
     * //用户昵称	支付宝小二
     */
    private String nickName;
    /**
     * //是否是学生	T
     */
    private String isStudentCertified;
    /**
     * //用户类型（1/2） 1代表公司账户2代表个人账户	1
     */
    private String userType;
    /**
     * //用户状态（Q/T/B/W）。Q代表快速注册用户 T代表已认证用户  B代表被冻结账户  W代表已注册，未激活的账户	T
     */
    private String userStatus;
    /**
     * //是否通过实名认证。T是通过 F是没有实名认证。	T
     */
    private String isCertified;
    /**
     * //【注意】只有is_certified为T的时候才有意义，否则不保证准确性.性别（F：女性；M：男性）。
     */
    private String gender;

    public String getAliUserId() {
        return aliUserId;
    }

    public void setAliUserId(String aliUserId) {
        this.aliUserId = aliUserId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIsStudentCertified() {
        return isStudentCertified;
    }

    public void setIsStudentCertified(String isStudentCertified) {
        this.isStudentCertified = isStudentCertified;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(String isCertified) {
        this.isCertified = isCertified;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
