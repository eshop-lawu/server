package com.lawu.eshop.order.dto.foreign;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingOrderExpressDTO {
	
    /**
     * 运单编号
     */
	@ApiModelProperty(value = "运单编号", required = true)
    private String waybillNum;

    /**
     * 快递公司名称
     */
	@ApiModelProperty(value = "快递公司名称", required = true)
    private String expressCompanyName;
    
    /**
     * 物流实时查询数据
     */
	@ApiModelProperty(value = "物流实时查询数据", required = true)
    private ExpressInquiriesDetailDTO expressInquiriesDetailDTO;

	public String getWaybillNum() {
		return waybillNum;
	}

	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	public ExpressInquiriesDetailDTO getExpressInquiriesDetailDTO() {
		return expressInquiriesDetailDTO;
	}

	public void setExpressInquiriesDetailDTO(ExpressInquiriesDetailDTO expressInquiriesDetailDTO) {
		this.expressInquiriesDetailDTO = expressInquiriesDetailDTO;
	}
    
}