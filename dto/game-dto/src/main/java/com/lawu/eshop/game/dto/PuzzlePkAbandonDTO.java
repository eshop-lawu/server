package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lihj
 * @date 2018/3/20
 */
@ApiModel("拼图OK退出DTO")
public class PuzzlePkAbandonDTO {

	/**
	 * 房间是否解散
	 * <p>
	 * 用于判断好友对战时, 是否是房主退出
	 */
	@ApiModelProperty(value = "房间是否解散", required = true)
	private Boolean isDissolution;

	/**
	 * 退出的用户编号
	 */
	@ApiModelProperty(value = "退出的用户编号", required = true)
	private String exitUserNum;

	public Boolean getIsDissolution() {
		return isDissolution;
	}

	public void setIsDissolution(Boolean isDissolution) {
		this.isDissolution = isDissolution;
	}

	public String getExitUserNum() {
		return exitUserNum;
	}

	public void setExitUserNum(String exitUserNum) {
		this.exitUserNum = exitUserNum;
	}

}
