package com.lawu.eshop.ad.param;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;
import com.lawu.framework.core.page.OrderType;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

public class AdMerchantParam extends  AbstractPageParam{
	
	@ApiParam (name="typeEnum", value = "AD_TYPE_FLAT 平面 AD_TYPE_VIDEO 视频 AD_TYPE_PRAISE E赞 AD_TYPE_PACKET 红包")
	private AdTypeEnum typeEnum;

	@ApiParam (name="putWayEnum", value = "PUT_WAY_AREAS 区域 PUT_WAY_FENS 粉丝 PUT_WAY_RADAR 雷达")
    private PutWayEnum putWayEnum;
    
	@ApiParam (name="statusEnum", value = "AD_STATUS_DELETE 删除 AD_STATUS_ADD 上架 AD_STATUS_PUTING 投放中 AD_STATUS_PUTED 投放结束"
			+ "AD_STATUS_OUT 下架")
    private AdStatusEnum statusEnum;
	
    @ApiModelProperty(value = "排序类型，ASC正序（默认），DESC反序")
    private OrderType orderType = OrderType.DESC;

	public AdTypeEnum getTypeEnum() {
		return typeEnum;
	}


	public void setTypeEnum(AdTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}


	public PutWayEnum getPutWayEnum() {
		return putWayEnum;
	}


	public void setPutWayEnum(PutWayEnum putWayEnum) {
		this.putWayEnum = putWayEnum;
	}


	public AdStatusEnum getStatusEnum() {
		return statusEnum;
	}


	public void setStatusEnum(AdStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}


	public OrderType getOrderType() {
		return orderType;
	}


	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
    
    
    

}
