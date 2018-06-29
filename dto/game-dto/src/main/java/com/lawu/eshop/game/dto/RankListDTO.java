package com.lawu.eshop.game.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/12.
 */
public class RankListDTO {

    @ApiModelProperty(value = "当前用户头像")
    private String headImg;

    @ApiModelProperty(value = "当前用户昵称")
    private String nickName;

    @ApiModelProperty(value = "当前用户星星数量")
    private Integer starCount;

    @ApiModelProperty(value = "当前用户排名")
    private Integer rank;

    @ApiModelProperty(value = "top20排行榜列表")
    private List<UserStarRankListDTO> rankList;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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

    public List<UserStarRankListDTO> getRankList() {
        return rankList;
    }

    public void setRankList(List<UserStarRankListDTO> rankList) {
        this.rankList = rankList;
    }
}
