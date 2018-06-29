package com.lawu.eshop.order.param.foreign;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 购物订单退货参数 api暴露给app参数
 * 
 * @author Sunny
 * @date 2017/4/11
 */
@ApiModel
public class ShoppingOrderRequestRefundForeignParam {

	/**
	 * 退货原因
	 */
	@NotBlank(message="退款原因不能为空")
	@ApiModelProperty(required = true, value = "退款原因")
	private String reason;

	/**
	 * 退款描述
	 */
	@ApiModelProperty(required = false, value = "退款描述")
	private String description;

	/**
	 * 退款类型
	 */
	@NotNull(message="退款类型为空")
	@ApiModelProperty(required = true, value = "退款类型(REFUND 退款|RETURN_REFUND 退货退款)")
	private ShoppingRefundTypeEnum type;
	
	/**
	 * 凭证图片
	 */
	@ApiModelProperty(value = "凭证图片（图片路径用逗号分隔）")
	private String voucherPicture;
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

		public ShoppingRefundTypeEnum getType() {
		return type;
	}

	public void setType(ShoppingRefundTypeEnum type) {
		this.type = type;
	}

	public String getVoucherPicture() {
		return voucherPicture;
	}

	public void setVoucherPicture(String voucherPicture) {
		this.voucherPicture = voucherPicture;
	}

}
