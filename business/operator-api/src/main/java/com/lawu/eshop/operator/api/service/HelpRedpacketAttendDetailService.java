package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.activity.dto.HelpRedpacketAttendPageDTO;
import com.lawu.eshop.activity.param.HelpRedpacketDetailOperatorParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 活动详情接口
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
@FeignClient(value = "activity-srv")
public interface HelpRedpacketAttendDetailService {
	
	/**
	 * 运营后台查询报名列表
	 * @param detailParam
	 * @return
	 */
	@RequestMapping(value = "helpRedpacketAttendDetail/helpRedpacketAttendPageOperator", method = RequestMethod.POST)
	Result<Page<HelpRedpacketAttendPageDTO>> helpRedpacketAttendPageOperator(@RequestBody HelpRedpacketDetailOperatorParam detailParam);

}
