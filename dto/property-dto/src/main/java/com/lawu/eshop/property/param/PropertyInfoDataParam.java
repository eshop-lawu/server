package com.lawu.eshop.property.param;

import javax.validation.constraints.Pattern;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.property.constants.LoveTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;

/**
 * <p>
 * Description: 业务操作需要处理财产积分
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月11日 下午5:35:25
 */
public class PropertyInfoDataParam {

    @NotBlank(message = "userNum不能为空")
    private String userNum;

    @NotBlank(message = "point不能为空")
    @Pattern(regexp = "^\\d{0,8}\\.{0,1}(\\d{1,2})?$", message = "point格式错误或小数位不超过2位")
    private String point;

    //业务类型：根据业务需要传，如果是会员业务传memberTransactionTypeEnum（用户业务时必传）
    private MemberTransactionTypeEnum memberTransactionTypeEnum;
    //业务类型：根据业务需要传，如果是商家业务传merchantTransactionTypeEnum（商家业务时必传）
    private MerchantTransactionTypeEnum merchantTransactionTypeEnum;

    //业务ID，必传！！要求业务类型对应bizId唯一
    private String bizId;

    private String tempBizId;

    //主事务执行时间
    private Date gmtExecute;

    //省市区ID
    private String regionPath;

    //爱心账户类型
    private LoveTypeEnum loveTypeEnum;

    //交易明细列表显示的文案，为兼容title为空时取枚举类型name
    private String title;

    //交易说明
    private String transactionDesc;

    //商家ID(邀请粉丝添加邀请记录参数)
    private Long merchantId;

    //区域名称(邀请粉丝添加邀请记录参数)
    private String regionName;

    //邀请粉丝数量(邀请粉丝添加邀请记录参数)
    private Integer inviteFansCount;

    //性别(邀请粉丝添加邀请记录参数)
    private Byte sex;

    //年龄区间(邀请粉丝添加邀请记录参数)
    private String age;

    public String getTempBizId() {
        return tempBizId;
    }

    public void setTempBizId(String tempBizId) {
        this.tempBizId = tempBizId;
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

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public MemberTransactionTypeEnum getMemberTransactionTypeEnum() {
        return memberTransactionTypeEnum;
    }

    public void setMemberTransactionTypeEnum(MemberTransactionTypeEnum memberTransactionTypeEnum) {
        this.memberTransactionTypeEnum = memberTransactionTypeEnum;
    }

    public MerchantTransactionTypeEnum getMerchantTransactionTypeEnum() {
        return merchantTransactionTypeEnum;
    }

    public void setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum merchantTransactionTypeEnum) {
        this.merchantTransactionTypeEnum = merchantTransactionTypeEnum;
    }

    public LoveTypeEnum getLoveTypeEnum() {
        return loveTypeEnum;
    }

    public void setLoveTypeEnum(LoveTypeEnum loveTypeEnum) {
        this.loveTypeEnum = loveTypeEnum;
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

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }
}