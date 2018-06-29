package com.lawu.eshop.game.dto;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.game.constants.GameQuestionLevelEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
public class GameMindQuestionDTO {

	/**
	 *
	 * 
	 * game_mind_question.id
	 *
	 * @mbg.generated
	 */
	private Long id;

	/**
	 *
	 * 类型名称 game_mind_question.category_name
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty(value = "类型名称")
	private String categoryName;

	/**
	 *
	 * 题目标题 game_mind_question.title
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty(value = "题目标题")
	private String title;

	/**
	 *
	 * 题目答案{"answer":["answer1","answer2","answer3","answer4"]}
	 * game_mind_question.answers
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty(value = "题目答案")
	private String answers;

	/**
	 *
	 * 正确答案(答案下标) game_mind_question.right_answer
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty(value = "正确答案")
	private Integer rightAnswer;

	/**
	 *
	 * 题目状态 0-删除 1-正常 game_mind_question.status
	 *
	 * @mbg.generated
	 */
	@ApiModelProperty(value = "题目状态")
	private GameConfigStatusEnum status;
	
	
	private GameQuestionLevelEnum leverEnum;

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

	public GameQuestionLevelEnum getLeverEnum() {
		return leverEnum;
	}

	public void setLeverEnum(GameQuestionLevelEnum leverEnum) {
		this.leverEnum = leverEnum;
	}
	
	

}
