package com.lawu.eshop.game.query;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
public class GameMindQuestionQuery extends AbstractPageParam{
	
	private Long categoryId;
	
	private String title;
	
	@ApiModelProperty(value = "排序类型")
	private String sortOrder;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	

}
