package com.lawu.eshop.game.query;

import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2018/3/10.
 */
public class OperatorGamePuzzlePicQuery extends AbstractPageParam {

    private String userNickname;

    private String beginTime;

    private String endTime;

    private GamePuzzlePicStatusEnum statusEnum;

    private String sortName;

    private String sortOrder;

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public GamePuzzlePicStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(GamePuzzlePicStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
