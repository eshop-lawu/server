package com.lawu.eshop.property.srv.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.property.constants.CashChannelEnum;
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;

public class WithdrawCashBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 账号(冗余)
     */
    private String account;

    /**
     * 名称(冗余)
     */
    private String name;

    /**
     * 注册区域省ID(冗余)
     */
    private Integer provinceId;

    /**
     * 注册区域城市ID(冗余)
     */
    private Integer cityId;

    /**
     * 注册区域区ID(冗余)
     * @mbg.generated
     */
    private Integer areaId;

    /**
     * 用户区域全路径(冗余)
     */
    private String regionFullName;

    /**
     * 提现金额
     */
    private BigDecimal cashMoney;

    /**
     * 提现比例1:0.95
     */
    private String currentScale;

    /**
     * 到账金额
     */
    private BigDecimal money;

    /**
     * 提现类型(1-用户提现2-商家提现)
     */
    private UserTypeEnum userType;

    /**
     * 渠道
     */
    private CashChannelEnum channel;

    /**
     * 提现状态
     */
    private CashStatusEnum status;

    /**
     * 提现银行卡关联ID
     */
    private Long businessBankAccountId;

    /**
     * 提现单号
     */
    private String cashNumber;

    /**
     * 第三方订单号(代付时存在)
     */
    private String thirdNumber;

    /**
     * 审核人ID
     */
    private Long auditUserId;

    /**
     * 审核人姓名
     */
    private String auditUserName;

    /**
     * 审核失败原因(冗余)
     */
    private String auditFaildReason;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getRegionFullName() {
		return regionFullName;
	}

	public void setRegionFullName(String regionFullName) {
		this.regionFullName = regionFullName;
	}

	public BigDecimal getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(BigDecimal cashMoney) {
		this.cashMoney = cashMoney;
	}

	public String getCurrentScale() {
		return currentScale;
	}

	public void setCurrentScale(String currentScale) {
		this.currentScale = currentScale;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public UserTypeEnum getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}

	public CashChannelEnum getChannel() {
		return channel;
	}

	public void setChannel(CashChannelEnum channel) {
		this.channel = channel;
	}

	public CashStatusEnum getStatus() {
		return status;
	}

	public void setStatus(CashStatusEnum status) {
		this.status = status;
	}

	public Long getBusinessBankAccountId() {
		return businessBankAccountId;
	}

	public void setBusinessBankAccountId(Long businessBankAccountId) {
		this.businessBankAccountId = businessBankAccountId;
	}

	public String getCashNumber() {
		return cashNumber;
	}

	public void setCashNumber(String cashNumber) {
		this.cashNumber = cashNumber;
	}

	public String getThirdNumber() {
		return thirdNumber;
	}

	public void setThirdNumber(String thirdNumber) {
		this.thirdNumber = thirdNumber;
	}

	public Long getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Long auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getAuditFaildReason() {
		return auditFaildReason;
	}

	public void setAuditFaildReason(String auditFaildReason) {
		this.auditFaildReason = auditFaildReason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}