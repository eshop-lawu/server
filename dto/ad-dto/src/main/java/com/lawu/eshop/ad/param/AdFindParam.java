package com.lawu.eshop.ad.param;

import java.util.Date;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class AdFindParam extends AbstractPageParam{
	
	@ApiParam (name="typeEnum", value = "AD_TYPE_FLAT 平面 AD_TYPE_VIDEO 视频 AD_TYPE_PRAISE E赞")
	private AdTypeEnum typeEnum;

	@ApiParam (name="putWayEnum", value = "PUT_WAY_AREAS 区域 PUT_WAY_FENS 粉丝 PUT_WAY_RADAR 雷达 AD_TYPE_PACKET 红包")
    private PutWayEnum putWayEnum;
    
	@ApiParam (name="statusEnum", value = "AD_STATUS_DELETE 删除 AD_STATUS_ADD 上架 AD_STATUS_PUTING 投放中 AD_STATUS_PUTED 投放结束"
			+ "AD_STATUS_OUT 下架")
    private AdStatusEnum statusEnum;
	
	@ApiParam (name="beginTime", value = "开始时间")
	private Date beginTime;
	
	@ApiParam (name="endTime", value = "结束时间")
	private Date endTime;

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


	public Date getBeginTime() {
		return beginTime;
	}


	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
    
    
    

}
