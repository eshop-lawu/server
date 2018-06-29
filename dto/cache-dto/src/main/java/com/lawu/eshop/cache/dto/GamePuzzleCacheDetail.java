package com.lawu.eshop.cache.dto;

/**
 * @author lihj
 * @date 2018/3/27
 */
public class GamePuzzleCacheDetail {
    private Long id;
    private String userNum;
    private String attendNum;
    private Byte status;
    private int star;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
