package com.lawu.eshop.property.param;

import java.math.BigDecimal;

public class CommissionResultParam {

      private BigDecimal beforeMoney;//实际金额

	private BigDecimal commission0;//广告收益提成：实际*0.5|买单/购物：实际*1

	private BigDecimal currentCommission;//当前比例（广告不按level累加，买单/购物需要根据level累加）

	private BigDecimal actualCommissionScope;//扣除爱心账户

	private BigDecimal loveAccountScale;//爱心账号

	private Integer dept;

	public BigDecimal getBeforeMoney() {
		return beforeMoney;
	}

	public void setBeforeMoney(BigDecimal beforeMoney) {
		this.beforeMoney = beforeMoney;
	}

	public BigDecimal getCommission0() {
		return commission0;
	}

	public void setCommission0(BigDecimal commission0) {
		this.commission0 = commission0;
	}

	public BigDecimal getCurrentCommission() {
		return currentCommission;
	}

	public void setCurrentCommission(BigDecimal currentCommission) {
		this.currentCommission = currentCommission;
	}

	public BigDecimal getActualCommissionScope() {
		return actualCommissionScope;
	}

	public void setActualCommissionScope(BigDecimal actualCommissionScope) {
		this.actualCommissionScope = actualCommissionScope;
	}

	public BigDecimal getLoveAccountScale() {
		return loveAccountScale;
	}

	public void setLoveAccountScale(BigDecimal loveAccountScale) {
		this.loveAccountScale = loveAccountScale;
	}

	public Integer getDept() {
		return dept;
	}

	public void setDept(Integer dept) {
		this.dept = dept;
	}
}