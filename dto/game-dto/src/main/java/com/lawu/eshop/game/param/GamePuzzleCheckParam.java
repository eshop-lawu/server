package com.lawu.eshop.game.param;

import com.lawu.eshop.game.constants.AttendTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("拼图开始检查")
public class GamePuzzleCheckParam {
	@ApiModelProperty(value = "参与编号", required = true)
	private String attendNum;
    @ApiModelProperty(value = "参与类型")
    private AttendTypeEnum attendType;
    
	public String getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(String attendNum) {
		this.attendNum = attendNum;
	}

	public AttendTypeEnum getAttendType() {
		return attendType;
	}

	public void setAttendType(AttendTypeEnum attendType) {
		this.attendType = attendType;
	}

}
