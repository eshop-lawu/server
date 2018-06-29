package com.lawu.eshop.game.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * 游戏账户信息
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月11日
 */
public class GameAccountInfoDTO {
	
	@ApiModelProperty(value = "用户图像")
	private String headImg;
	
	@ApiModelProperty(value = "用户昵称")
	private String nikeName;
	
	@ApiModelProperty(value = "用户地区路径")
	private String regionPath;
	
	@ApiModelProperty(value = "用户地区")
	private String regionName;
	
	@ApiModelProperty(value = "参与积分")
	private int attendPoint;
	
	@ApiModelProperty(value = "星星数量")
	private int starCount;
	
	@ApiModelProperty(value = "免费次数")
	private int freeCount;
	
	@ApiModelProperty(value = "积分")
	private BigDecimal point;

	@ApiModelProperty(value = "参与星星数量")
	private int attendStarCount;
	
	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getAttendPoint() {
		return attendPoint;
	}

	public void setAttendPoint(int attendPoint) {
		this.attendPoint = attendPoint;
	}

	public int getStarCount() {
		return starCount;
	}

	public void setStarCount(int starCount) {
		this.starCount = starCount;
	}

	public int getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(int freeCount) {
		this.freeCount = freeCount;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}

	public int getAttendStarCount() {
		return attendStarCount;
	}

	public void setAttendStarCount(int attendStarCount) {
		this.attendStarCount = attendStarCount;
	}
}
