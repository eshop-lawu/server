package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/7/28.
 */
public class RecommendFoodStoreDTO {

    @ApiModelProperty(value = "人气最高")
    private List<RecommendFoodDTO> recommendConsume;

    @ApiModelProperty(value = "评分最高")
    private List<RecommendFoodDTO> recommendComment;

    public List<RecommendFoodDTO> getRecommendConsume() {
        return recommendConsume;
    }

    public void setRecommendConsume(List<RecommendFoodDTO> recommendConsume) {
        this.recommendConsume = recommendConsume;
    }

    public List<RecommendFoodDTO> getRecommendComment() {
        return recommendComment;
    }

    public void setRecommendComment(List<RecommendFoodDTO> recommendComment) {
        this.recommendComment = recommendComment;
    }
}
