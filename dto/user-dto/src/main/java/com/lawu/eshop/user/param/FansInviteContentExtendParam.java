package com.lawu.eshop.user.param;

public class FansInviteContentExtendParam extends FansInviteContentParam {

    private String ids;

    private String nums;

    //事务消息参数
    private String regionName;

    //事务消息参数
    private Integer inviteFansCount;

    //事务消息参数
    private Byte sex;

    //事务消息参数
    private String age;

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
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
}
