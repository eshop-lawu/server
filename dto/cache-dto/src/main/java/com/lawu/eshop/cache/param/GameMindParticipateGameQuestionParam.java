package com.lawu.eshop.cache.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

/**
 * 头脑PK参与游戏问题参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
@ApiModel("头脑PK参与游戏问题参数")
public class GameMindParticipateGameQuestionParam implements Serializable {

    private static final long serialVersionUID = 376845121583174886L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 正确答案(答案下标)
     */
    private Integer rightAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

}
