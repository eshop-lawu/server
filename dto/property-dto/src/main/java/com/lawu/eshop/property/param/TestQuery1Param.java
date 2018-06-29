package com.lawu.eshop.property.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月20日 下午2:57:42
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestQuery1Param extends AbstractPageParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiParam(name="name", value = "名称")
	@NotBlank(message="name不能为空")
	private String name;

	//bootsrap table列排序
	private String sortName;
	private String sortOrder;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}