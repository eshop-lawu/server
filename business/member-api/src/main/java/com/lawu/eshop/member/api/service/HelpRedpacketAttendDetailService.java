package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.dto.HelpRedpacketAttendDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketUserDTO;
import com.lawu.eshop.activity.param.HelpRedpacketAttendParam;
import com.lawu.eshop.activity.param.HelpRedpacketUserParam;
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
	 * 活动详情
	 * @param userNum
	 * @return
	 */
    @Deprecated
	@RequestMapping(value = "helpRedpacketAttendDetail/helpRedpacketAttendDetail", method = RequestMethod.GET)
	Result<HelpRedpacketAttendDTO> helpRedpacketAttendDetail(@RequestParam("userNum") String userNum,@RequestParam("helpCount") Integer helpCount);
	
	   /**
     * 活动详情
     * @param userNum
     * @return
     */
    @RequestMapping(value = "helpRedpacketAttendDetail/helpRedpacketAttendDetail", method = RequestMethod.GET)
    Result<HelpRedpacketAttendDTO> helpRedpacketAttendDetail(@RequestParam(name = "activityId", required = false) Integer activityId, @RequestParam("userNum") String userNum,@RequestParam("helpCount") Integer helpCount);
	
	
	/**
	 * 参与报名
	 * @param attendParam
	 * @return
	 */
	@RequestMapping(value = "helpRedpacketAttendDetail/helpRedpacketAttend", method = RequestMethod.POST)
	Result helpRedpacketAttend(@RequestBody HelpRedpacketAttendParam attendParam);
	
	/**
	 * 领取红包
	 * @param userNum
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "helpRedpacketAttendDetail/getHelpRedpacket", method = RequestMethod.PUT)
	Result<HelpRedpacketDTO> getHelpRedpacket(@RequestParam("userNum") String userNum);
	
	   /**
     * 领取红包
     * @param userNum
     * @return
     */
    @RequestMapping(value = "helpRedpacketAttendDetail/getHelpRedpacket", method = RequestMethod.PUT)
    Result<HelpRedpacketDTO> getHelpRedpacket(@RequestParam(name = "activityId", required = false) Integer activityId, @RequestParam("userNum") String userNum);
	
	/**
	 * 获取领取用户
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "helpRedpacketAttendDetail/getHelpRedpacketUser", method = RequestMethod.POST)
	Result<Page<HelpRedpacketUserDTO>> getHelpRedpacketUser(@RequestBody HelpRedpacketUserParam param);

}
