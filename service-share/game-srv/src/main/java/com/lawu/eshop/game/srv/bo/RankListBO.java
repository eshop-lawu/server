package com.lawu.eshop.game.srv.bo;

/**
 * @author zhangyong
 * @date 2018/3/9.
 */
public class RankListBO {

    /**
     * 账号
     */
    private String userNum;

    /**
     * 当月星星增加总数
     */
    private Integer monthStarCount;

    private Long id;

    private Boolean status;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Integer getMonthStarCount() {
        return monthStarCount;
    }

    public void setMonthStarCount(Integer monthStarCount) {
        this.monthStarCount = monthStarCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
