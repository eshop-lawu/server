package com.lawu.eshop.operator.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.operator.api.service.LogService;
import com.lawu.eshop.operator.param.LogParam;
import com.lawu.framework.core.event.AsyncEventHandle;
import com.lawu.shiro.util.UserUtil;

/**
 * @author zhangrc
 * @date 2017/12/26
 */
@Component
public class LogEventHandle implements AsyncEventHandle<LogEvent> {

	@Autowired
    private LogService logService;

	@Override
	public void execute(LogEvent event) {
		LogParam logParam = new LogParam();
		logParam.setAccount(UserUtil.getCurrentUserAccount());
		logParam.setTypeEnum(event.getTypeEnum());
		logParam.setModuleEnum(event.getModuleEnum());
		logParam.setBusinessId(event.getBusinessId());
		logParam.setChangeTitle(event.getTitleEnum().getName());
		if(event.getChangContent() !=null){
			logParam.setChangeContent(event.getChangContent().toString());
		}
		logService.saveLog(logParam);
	}
}
