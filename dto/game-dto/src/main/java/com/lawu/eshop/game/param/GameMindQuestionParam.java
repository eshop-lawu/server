package com.lawu.eshop.game.param;

import java.util.List;

import com.lawu.eshop.game.constants.GameQuestionLevelEnum;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
public class GameMindQuestionParam {
	
	private Long id;

	/**
	 *
	 * 题目类型ID game_mind_question.category_id
	 *
	 * @mbg.generated
	 */
	@ApiParam(name = "categoryId", required = true, value = "题目类型")
	private Long categoryId;

	/**
	 *
	 * 题目标题 game_mind_question.title
	 *
	 * @mbg.generated
	 */
	@ApiParam(name = "title", required = true, value = "题目标题")
	private String title;

	/**
	 *
	 * 题目答案{"answer":["answer1","answer2","answer3","answer4"]}
	 * game_mind_question.answers
	 *
	 * @mbg.generated
	 */
	 @ApiModelProperty(value = "答案")
	private List<String> answers;

	/**
	 *
	 * 正确答案(答案下标) game_mind_question.right_answer
	 *
	 * @mbg.generated
	 */
	@ApiParam(name = "rightAnswer", required = true, value = "正确答案")
	private Integer rightAnswer;
	
	
	@ApiParam(name = "levelEnum", required = true, value = "困难度")
	private GameQuestionLevelEnum levelEnum;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public Integer getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(Integer rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public GameQuestionLevelEnum getLevelEnum() {
		return levelEnum;
	}

	public void setLevelEnum(GameQuestionLevelEnum levelEnum) {
		this.levelEnum = levelEnum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
