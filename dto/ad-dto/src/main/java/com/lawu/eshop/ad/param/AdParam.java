package com.lawu.eshop.ad.param;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Max;

import com.lawu.eshop.ad.constants.FileTypeEnum;
import com.lawu.eshop.ad.constants.PraiseTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.constants.RelateTypeEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.UserSexEnum;

import io.swagger.annotations.ApiParam;

public class AdParam {

	@ApiParam (name="title", value = "广告标题")
    private String title;

	@ApiParam (name="content", value = "广告描述")
    private String content;

	@ApiParam (name="typeEnum",required = true, value = "AD_TYPE_FLAT 平面 AD_TYPE_VIDEO 视频 AD_TYPE_PRAISE E赞 AD_TYPE_PACKET红包")
    private AdTypeEnum typeEnum;

	@ApiParam (name="putWayEnum",required = true, value = "PUT_WAY_AREAS 区域 PUT_WAY_FENS 粉丝 PUT_WAY_RADAR 雷达 PUT_WAY_COMMON 普通红包PUT_WAY_LUCK手气红包" )
    private PutWayEnum putWayEnum;

	@ApiParam (name="beginTime", value = "投放的开始时间")
    private String beginTime;

	@ApiParam (name="endTime", value = "投放的结束时间")
    private Date endTime;

	@ApiParam (name="areas", value = "区域 区域最后一位 XX,XX,XX")
    private String areas;

	@ApiParam (name="radius", value = "雷达半径")
    private Integer radius;

	@ApiParam (name="point", value = "单个广告所需积分")
    private BigDecimal point;

	@ApiParam (name="totalPoint",required = true, value = "广告所需总积分")
    private BigDecimal totalPoint;

	@ApiParam (name="adCount", value = "广告数量")
    private Integer adCount;
	
	@ApiParam (name="regionName", value = "区域名称")
	private String regionName;
	
	@ApiParam (name="mediaUrl", value = "附件路径(平面和视频路径)")
	private String mediaUrl;
	
	@ApiParam (name="videoImgUrl", value = "视频封面图片")
	private String videoImgUrl;
	
	@ApiParam (name="relateId", value = "关联id")
	private Long relateId;
	
	@ApiParam (name="relateType", value = "PRODUCT_TYPE 商品  | MERCHANT_STORE_TYPE 店铺")
	private RelateTypeEnum relateType;

	@ApiParam (name="fileType", value = "VIDEO 视频  | IMG 图片")
	private FileTypeEnum fileType;
	
	@ApiParam (name="fileSize", value = "视频大小  以M 为单位")
	private String fileSize;
	
	@ApiParam (name="fileTime", value = "视频长度  00:00:00格式")
	private String fileTime;
	
	@ApiParam (name="praiseType", value = "PRAISE_TYPE_PUZZLE 拼图  PRAISE_TYPE_CLICK 点赞")
	private PraiseTypeEnum praiseType;
	
	@ApiParam (name="sexEnum", value = "SEX_MALE 男  SEX_SECRET 全部  SEX_FEMALE 女")
	private UserSexEnum sexEnum;
	
	@ApiParam (name="minAge", value = "年龄范围（小）")
	private Integer minAge;
	
	@ApiParam (name="maxAge", value = "年龄范围（大）")
	@Max(value=200,message="最大年龄不能超过200")
	private Integer maxAge;

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	
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


	

	public String getBeginTime() {
		return beginTime;
	}


	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getAreas() {
		return areas;
	}


	public void setAreas(String areas) {
		this.areas = areas;
	}


	public Integer getRadius() {
		return radius;
	}


	public void setRadius(Integer radius) {
		this.radius = radius;
	}


	public BigDecimal getPoint() {
		return point;
	}


	public void setPoint(BigDecimal point) {
		this.point = point;
	}


	public BigDecimal getTotalPoint() {
		return totalPoint;
	}


	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}


	public Integer getAdCount() {
		return adCount;
	}


	public void setAdCount(Integer adCount) {
		this.adCount = adCount;
	}


	public String getRegionName() {
		return regionName;
	}


	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}


	public String getMediaUrl() {
		return mediaUrl;
	}


	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}


	public String getVideoImgUrl() {
		return videoImgUrl;
	}


	public void setVideoImgUrl(String videoImgUrl) {
		this.videoImgUrl = videoImgUrl;
	}


	public Long getRelateId() {
		return relateId;
	}


	public void setRelateId(Long relateId) {
		this.relateId = relateId;
	}


	public RelateTypeEnum getRelateType() {
		return relateType;
	}


	public void setRelateType(RelateTypeEnum relateType) {
		this.relateType = relateType;
	}


	public FileTypeEnum getFileType() {
		return fileType;
	}


	public void setFileType(FileTypeEnum fileType) {
		this.fileType = fileType;
	}


	public String getFileSize() {
		return fileSize;
	}


	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}


	public String getFileTime() {
		return fileTime;
	}


	public void setFileTime(String fileTime) {
		this.fileTime = fileTime;
	}


	public PraiseTypeEnum getPraiseType() {
		return praiseType;
	}


	public void setPraiseType(PraiseTypeEnum praiseType) {
		this.praiseType = praiseType;
	}


	public UserSexEnum getSexEnum() {
		return sexEnum;
	}


	public void setSexEnum(UserSexEnum sexEnum) {
		this.sexEnum = sexEnum;
	}


	public Integer getMinAge() {
		return minAge;
	}


	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}


	public Integer getMaxAge() {
		return maxAge;
	}


	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	
	
	

}
