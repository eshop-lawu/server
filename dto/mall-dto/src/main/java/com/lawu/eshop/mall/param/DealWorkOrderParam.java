package com.lawu.eshop.mall.param;

import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.mall.constants.WorkOrderStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class DealWorkOrderParam {

	@ApiModelProperty(name = "id", required = true, value = "id")
	@NotBlank(message = "id不能为空")
	private Long id;
	
	@ApiModelProperty(name = "workOrderStatusEnum", required = true, value = "NOT_YET_DEAL--未处理，REPLIED--已处理，NOT_DEAL--不予处理")
	@NotBlank(message = "workOrderStatusEnum不能为空")
	private WorkOrderStatusEnum workOrderStatusEnum;
	
	@ApiModelProperty(name = "replyContent", required = false, value = "回复内容")
	private String replyContent;

	@ApiModelProperty(name = "auditorId", required = true, value = "操作人员编号")
	@NotBlank(message = "auditorId不能为空")
	private Integer auditorId;
	
	@ApiModelProperty(name = "auditorName", required = true, value = "操作人员名称")
	@NotBlank(message = "auditorName不能为空")
	private String auditorName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public WorkOrderStatusEnum getWorkOrderStatusEnum() {
		return workOrderStatusEnum;
	}

	public void setWorkOrderStatusEnum(WorkOrderStatusEnum workOrderStatusEnum) {
		this.workOrderStatusEnum = workOrderStatusEnum;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
	
}
