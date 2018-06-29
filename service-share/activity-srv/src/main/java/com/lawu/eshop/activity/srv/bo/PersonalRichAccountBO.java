package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 个人E钻账户信息
 * @author lihj
 * @date 2018/5/2
 */
public class PersonalRichAccountBO {

    /**
     * E钻总数
     */
    private BigDecimal diamondTotal;
    /**
     * 动力
     */
    private int power;
    private boolean isFirstOpen;

    /**
     * 未领取E钻集合
     */
    private List<PersonalRichDiamondDetailBO> diamondList;

    public BigDecimal getDiamondTotal() {
        return diamondTotal;
    }

    public void setDiamondTotal(BigDecimal diamondTotal) {
        this.diamondTotal = diamondTotal;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public List<PersonalRichDiamondDetailBO> getDiamondList() {
        return diamondList;
    }

    public void setDiamondList(List<PersonalRichDiamondDetailBO> diamondList) {
        this.diamondList = diamondList;
    }

	public boolean isFirstOpen() {
		return isFirstOpen;
	}

	public void setFirstOpen(boolean isFirstOpen) {
		this.isFirstOpen = isFirstOpen;
	}
    
}
