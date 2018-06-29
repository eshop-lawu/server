package com.lawu.eshop.member.api.event;

import com.lawu.eshop.mall.constants.AttendTypeEnum;
import com.lawu.eshop.mall.constants.ShareTypeEnum;
import com.lawu.eshop.mall.param.ShareFriendsParam;
import com.lawu.framework.core.event.AsyncEvent;

/**
 * @author meihsuquan
 * @date 2018/5/14.
 */
public class ShareFriendsEvent extends AsyncEvent {

    private String shareUserNum;

    private ShareTypeEnum typeEnum;

    private Long relateId;

    private String userNums;

    private Boolean isAll;

    private String roomHostNum;

    private AttendTypeEnum attendTypeEnum;

    private String productName;

    private Long memberId;

    public ShareFriendsEvent(Object source, String shareUserNum, Long memberId, ShareFriendsParam param) {
        super(source);
        this.shareUserNum = shareUserNum;
        this.typeEnum = param.getTypeEnum();
        this.relateId = param.getRelateId();
        this.userNums = param.getUserNums();
        this.isAll = param.getIsAll();
        this.roomHostNum = param.getRoomHostNum();
        this.attendTypeEnum = param.getAttendTypeEnum();
        this.productName = param.getProductName();
        this.memberId = memberId;
    }

    public String getShareUserNum() {
        return shareUserNum;
    }

    public void setShareUserNum(String shareUserNum) {
        this.shareUserNum = shareUserNum;
    }

    public ShareTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(ShareTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public Boolean getIsAll() {
        return isAll;
    }

    public void setIsAll(Boolean all) {
        isAll = all;
    }

    public String getUserNums() {
        return userNums;
    }

    public void setUserNums(String userNums) {
        this.userNums = userNums;
    }

    public String getRoomHostNum() {
        return roomHostNum;
    }

    public void setRoomHostNum(String roomHostNum) {
        this.roomHostNum = roomHostNum;
    }

    public AttendTypeEnum getAttendTypeEnum() {
        return attendTypeEnum;
    }

    public void setAttendTypeEnum(AttendTypeEnum attendTypeEnum) {
        this.attendTypeEnum = attendTypeEnum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
