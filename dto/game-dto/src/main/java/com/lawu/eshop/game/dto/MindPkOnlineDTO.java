package com.lawu.eshop.game.dto;

import java.util.List;

import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK上线DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月16日
 * @updateDate 2018年3月16日
 */
@ApiModel("头脑PK上线")
public class MindPkOnlineDTO {
    
    /**
     * 房间内已经在线的用户
     */
    @ApiModelProperty(value = "房间内已经在线的用户", required = true)
    private List<GameRoomPlayerInfoDTO> users;

    public List<GameRoomPlayerInfoDTO> getUsers() {
        return users;
    }

    public void setUsers(List<GameRoomPlayerInfoDTO> users) {
        this.users = users;
    }
    
}
