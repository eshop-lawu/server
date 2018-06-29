package com.lawu.eshop.game.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class GameRandomQuestionDTO {

	@ApiModelProperty(value = "题目类型名称")
	private String categoryName;

	@ApiModelProperty(value = "题目名称")
	private String title;

	@ApiModelProperty(value = "答案列表")
	private List<String> answers;

	@ApiModelProperty(value = "题目ID")
	private Long id;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
