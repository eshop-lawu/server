package com.lawu.eshop.game.srv.bo;

/**
 * @author zhangyong
 * @date 2018/3/12.
 */
public class UserRankBO {

    //当前用户星星数量
    private Integer starCount;

    //当前用户排名
    private Integer rank;


    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
