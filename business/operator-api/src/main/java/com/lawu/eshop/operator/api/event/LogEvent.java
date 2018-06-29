package com.lawu.eshop.operator.api.event;

import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.ModuleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.operator.param.LogEventParam;
import com.lawu.framework.core.event.AsyncEvent;

/**
 * @author zhangrc
 * @date 2017/12/26
 */
public class LogEvent<T> extends AsyncEvent {
	
	/**
	 * 操作业务id
	 */
	private String businessId;
	
	/**
	 * 日志操作类型
	 */
	private OperationTypeEnum typeEnum;
	
	/**
	 * 所属模块
	 */
	private ModuleEnum moduleEnum;
	 
	/**
	 * 标题名称
	 */
	private LogTitleEnum titleEnum;
	
	/**
	 * 更新内容
	 */
	private T changContent;

   public LogEvent(Object source, LogEventParam<T> logParam ) {
        super(source);
        this.businessId=logParam.getBusinessId();
        this.titleEnum=logParam.getTitleEnum();
        this.typeEnum=logParam.getTypeEnum();
        this.moduleEnum=logParam.getModuleEnum();
        this.changContent=logParam.getChangeContent();
    }

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public OperationTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(OperationTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public ModuleEnum getModuleEnum() {
		return moduleEnum;
	}

	public void setModuleEnum(ModuleEnum moduleEnum) {
		this.moduleEnum = moduleEnum;
	}

	public LogTitleEnum getTitleEnum() {
		return titleEnum;
	}

	public void setTitleEnum(LogTitleEnum titleEnum) {
		this.titleEnum = titleEnum;
	}

	public T getChangContent() {
		return changContent;
	}

	public void setChangContent(T changContent) {
		this.changContent = changContent;
	}

	
	
	

  
}
