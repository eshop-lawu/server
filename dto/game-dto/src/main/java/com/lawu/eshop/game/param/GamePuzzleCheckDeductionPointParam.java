package com.lawu.eshop.game.param;

import com.lawu.eshop.game.constants.AttendTypeEnum;

/**
 * @author lihj <br>
 * @date 2018/3/15
 */
public class GamePuzzleCheckDeductionPointParam {
    private String attendNum;
    private String userNum;
    private AttendTypeEnum attendType;
    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public AttendTypeEnum getAttendType() {
		return attendType;
	}

	public void setAttendType(AttendTypeEnum attendType) {
		this.attendType = attendType;
	}
    
   
}
