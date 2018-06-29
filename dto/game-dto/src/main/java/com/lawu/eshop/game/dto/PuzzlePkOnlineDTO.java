package com.lawu.eshop.game.dto;

import java.util.List;

import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author lihj
 * @Date 2018年3月21日
 */
@ApiModel("拼图OK上线dto")
public class PuzzlePkOnlineDTO {

	@ApiModelProperty(value = "房间内已经在线的用户", required = true)
	private List<GameRoomPlayerInfoDTO> users;

	public List<GameRoomPlayerInfoDTO> getUsers() {
		return users;
	}

	public void setUsers(List<GameRoomPlayerInfoDTO> users) {
		this.users = users;
	}
	
}
