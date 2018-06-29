package com.lawu.eshop.game.srv.bo;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.game.constants.GameQuestionLevelEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
public class GameMindQuestionBO {

	/**
	 *
	 * 
	 * game_mind_question.id
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 *
	 * 类型名称 game_mind_question.category_name
	 *
	 * @mbg.generated
	 */
	private String categoryName;

	/**
	 *
	 * 题目标题 game_mind_question.title
	 *
	 * @mbg.generated
	 */
	private String title;

	/**
	 *
	 * 题目答案{"answer":["answer1","answer2","answer3","answer4"]}
	 * game_mind_question.answers
	 *
	 * @mbg.generated
	 */
	private String answers;

	/**
	 *
	 * 正确答案(答案下标) game_mind_question.right_answer
	 *
	 * @mbg.generated
	 */
	private Integer rightAnswer;

	/**
	 *
	 * 题目状态 0-禁用 1-启用 game_mind_question.status
	 *
	 * @mbg.generated
	 */
	private GameConfigStatusEnum status;
	
	/**
	 * 答题内容
	 */
	private String answerName;
	
	private GameQuestionLevelEnum leverEnum;
	
	private Long categoryId;

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

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public Integer getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(Integer rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public GameConfigStatusEnum getStatus() {
		return status;
	}

	public void setStatus(GameConfigStatusEnum status) {
		this.status = status;
	}

	public String getAnswerName() {
		return answerName;
	}

	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}

	public GameQuestionLevelEnum getLeverEnum() {
		return leverEnum;
	}

	public void setLeverEnum(GameQuestionLevelEnum leverEnum) {
		this.leverEnum = leverEnum;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
