package com.lawu.eshop.game.param;

import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.constants.GamePuzzleTypeEnum;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public class GamePuzzleParam extends GamePuzzleBaseParam {

    private Long id;

    private String userNum;

    private String userNickname;

    private GamePuzzleTypeEnum typeEnum;

    private GamePuzzlePicStatusEnum picStatusEnum;

    private GamePuzzleUserPicStatusEnum userPicStatusEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public GamePuzzleTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(GamePuzzleTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public GamePuzzlePicStatusEnum getPicStatusEnum() {
        return picStatusEnum;
    }

    public void setPicStatusEnum(GamePuzzlePicStatusEnum picStatusEnum) {
        this.picStatusEnum = picStatusEnum;
    }

    public GamePuzzleUserPicStatusEnum getUserPicStatusEnum() {
        return userPicStatusEnum;
    }

    public void setUserPicStatusEnum(GamePuzzleUserPicStatusEnum userPicStatusEnum) {
        this.userPicStatusEnum = userPicStatusEnum;
    }
}
