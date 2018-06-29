package com.lawu.eshop.activity.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lihj
 * @date 2018/5/2
 */
public class PersonalRichAccountDTO {

    @ApiModelProperty(value = "E钻总数")
    private BigDecimal diamondTotal;
    @ApiModelProperty(value = "动力")
    private int power;
    @ApiModelProperty(value = "是否首次打开")
    private boolean isFirstOpen;
    @ApiModelProperty(value = "未领取E钻集合")
    private List<PersonalRichDiamondDetailDTO> diamondList;

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

    public List<PersonalRichDiamondDetailDTO> getDiamondList() {
        return diamondList;
    }

    public void setDiamondList(List<PersonalRichDiamondDetailDTO> diamondList) {
        this.diamondList = diamondList;
    }

	public boolean isFirstOpen() {
		return isFirstOpen;
	}

	public void setFirstOpen(boolean isFirstOpen) {
		this.isFirstOpen = isFirstOpen;
	}
    
}
