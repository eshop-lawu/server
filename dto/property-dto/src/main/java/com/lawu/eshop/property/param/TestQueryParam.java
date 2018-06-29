package com.lawu.eshop.property.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

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
public class TestQueryParam extends AbstractPageParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@ApiParam(name="name", value = "名称")
	@NotBlank(message="name不能为空")
	private String name;

	@ApiParam(name="value", value = "值")
	@NotBlank(message="value不能为空")
	private String value;
	
	@ApiParam(name="name", value = "备注")
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	//bootsrap table列排序
	private String sortName;
	private String sortOrder;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}