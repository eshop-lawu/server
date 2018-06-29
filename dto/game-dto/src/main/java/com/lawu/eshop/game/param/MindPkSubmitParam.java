package com.lawu.eshop.game.param;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK提交参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月15日
 * @updateDate 2018年3月15日
 */
@ApiModel("头脑PK提交答案参数")
public class MindPkSubmitParam {
    
    /**
     * 问题id
     */
    @NotNull(message = "问题id不能为空")
    @ApiModelProperty(value = "问题id", required = true)
    private Long questionId;
    
    /**
     * 答案(下标)
     */
    @NotNull(message = "答案(下标)不能为空")
    @ApiModelProperty(value = "答案(下标)", required = true)
    private Integer rightAnswer;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    
}
