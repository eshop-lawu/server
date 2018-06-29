package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 验证答案单人返回DTO
 * @author jiangxinjun
 * @createDate 2018年3月15日
 * @updateDate 2018年3月15日
 */
@ApiModel("头脑PK验证答案单人返回DTO")
public class MindPkSubmitSingleDTO {
    
    /**
     * 累计游戏得分
     */
    @ApiModelProperty(value = "累计游戏得分", required = true)
    private Integer accumulatedGameScore;
    
    /**
     * 是否答对
     */
    @ApiModelProperty(value = "是否答对", required = true)
    private Boolean flag;
    
    /**
     * 正确答案(答案下标)
     */
    @ApiModelProperty(value = "正确答案(答案下标)", required = true)
    private Integer rightAnswer;
    
    public Integer getAccumulatedGameScore() {
        return accumulatedGameScore;
    }

    public void setAccumulatedGameScore(Integer accumulatedGameScore) {
        this.accumulatedGameScore = accumulatedGameScore;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
