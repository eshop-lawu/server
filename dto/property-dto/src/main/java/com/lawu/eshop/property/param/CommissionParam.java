package com.lawu.eshop.property.param;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.property.constants.CommissionEnum;

public class CommissionParam {

	private Long id;//计算提成基础表主键

	private Long tempId;//广告id

	private String memberNum;

	private String merchantNum;

	private BigDecimal actualAmount;//积分或金额

	private List<CommissionInvitersUserDTO> inviters = new ArrayList<>();

	private CommissionEnum commissionEnum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTempId() {
		return tempId;
	}

	public void setTempId(Long tempId) {
		this.tempId = tempId;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public List<CommissionInvitersUserDTO> getInviters() {
		return inviters;
	}

	public void setInviters(List<CommissionInvitersUserDTO> inviters) {
		this.inviters = inviters;
	}

	public CommissionEnum getCommissionEnum() {
		return commissionEnum;
	}

	public void setCommissionEnum(CommissionEnum commissionEnum) {
		this.commissionEnum = commissionEnum;
	}
}
