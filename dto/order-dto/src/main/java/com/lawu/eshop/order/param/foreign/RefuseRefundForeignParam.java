package com.lawu.eshop.order.param.foreign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商家填写物流信息
 * api传递给order-srv参数
 * 
 * @author Sunny
 * @date 2017/08/03
 */
@ApiModel
public class RefuseRefundForeignParam {

    /**
    * 拒绝退款理由
    */
	@ApiModelProperty(value = "拒绝退款理由")
    private String refusalReasons;

	/**
	 * 拒绝退款图片
	 */
	@ApiModelProperty(value = "拒绝退款图片（图片路径用逗号分隔）")
	private String refuseImages;
	
	public String getRefusalReasons() {
		return refusalReasons;
	}

	public void setRefusalReasons(String refusalReasons) {
		this.refusalReasons = refusalReasons;
	}

	public String getRefuseImages() {
		return refuseImages;
	}

	public void setRefuseImages(String refuseImages) {
		this.refuseImages = refuseImages;
	}
}
