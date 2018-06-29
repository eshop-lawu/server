package com.lawu.eshop.game.srv.bo;

import java.util.List;

public class GameAttendSaveReturnBO {

	private String attendNum;
	private boolean flag;
	private Boolean roomMaster;
	private int resultCode;
	private List<String> users;
	
	public String getAttendNum() {
		return attendNum;
	}
	public void setAttendNum(String attendNum) {
		this.attendNum = attendNum;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public Boolean getRoomMaster() {
		return roomMaster;
	}
	public void setRoomMaster(Boolean roomMaster) {
		this.roomMaster = roomMaster;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	
}
