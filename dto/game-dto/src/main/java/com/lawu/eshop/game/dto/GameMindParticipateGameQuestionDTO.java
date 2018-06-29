package com.lawu.eshop.game.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

/**
 * 头脑PK参与游戏信息DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
@ApiModel("头脑PK参与游戏信息")
public class GameMindParticipateGameQuestionDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 类型名称
     */
    private String categoryName;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目答案
     */
    private List<String> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
    
}
