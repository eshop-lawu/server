package com.lawu.eshop.activity.dto;
import java.math.BigDecimal;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;

/** 
 * 我的数字资产 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichMyDiamondRecordInfoDTO {
	
	@ApiModelProperty(value="E钻总数")
	private BigDecimal totalDiamond;
	
	@ApiModelProperty(value="E钻收支记录")
	private List<RichMyDiamondRecordDetailDTO> detailList;

	public BigDecimal getTotalDiamond() {
		return totalDiamond;
	}

	public void setTotalDiamond(BigDecimal totalDiamond) {
		this.totalDiamond = totalDiamond;
	}

	public List<RichMyDiamondRecordDetailDTO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<RichMyDiamondRecordDetailDTO> detailList) {
		this.detailList = detailList;
	}
	
	
}
