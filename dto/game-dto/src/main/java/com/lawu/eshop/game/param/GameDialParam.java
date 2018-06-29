package com.lawu.eshop.game.param;

import io.swagger.annotations.ApiParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月15日
 */
public class GameDialParam {
	
	@ApiParam(name = "name", required = true, value = "游戏名称")
	private String name;
	
	@ApiParam(name = "imgPath", required = true, value = "游戏图片")
	private String imgPath;
	
	@ApiParam(name = "point", required = true, value = "参与积分")
	private int point;
	
	@ApiParam(name = "freeCount", required = true, value = "可免费次数")
	private int freeCount;
	
	@ApiParam(name = "shareAttendCount", required = true, value = "分享可免费次数")
	private int shareAttendCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(int freeCount) {
		this.freeCount = freeCount;
	}

	public int getShareAttendCount() {
		return shareAttendCount;
	}

	public void setShareAttendCount(int shareAttendCount) {
		this.shareAttendCount = shareAttendCount;
	}
	
	

}
