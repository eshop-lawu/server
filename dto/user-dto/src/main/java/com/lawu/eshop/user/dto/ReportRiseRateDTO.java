package com.lawu.eshop.user.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年5月2日 下午2:43:39
 *
 */
public class ReportRiseRateDTO {

    @ApiModelProperty(value = "报表x轴(与y轴对应)")
    private List<String> x;
    
    @ApiModelProperty(value = "报表x轴辅助信息(与y轴对应)")
    private List<String> x1;

    @ApiModelProperty(value = "报表y轴(英文逗号分隔)")
    private List<String> y;
    
    @ApiModelProperty(value = "总额")
    private String total;

	public List<String> getX() {
		return x;
	}

	public void setX(List<String> x) {
		this.x = x;
	}

	public List<String> getY() {
		return y;
	}

	public void setY(List<String> y) {
		this.y = y;
	}

	public List<String> getX1() {
		return x1;
	}

	public void setX1(List<String> x1) {
		this.x1 = x1;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
   
}
