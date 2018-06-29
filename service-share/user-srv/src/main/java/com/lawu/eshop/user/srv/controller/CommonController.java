package com.lawu.eshop.user.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.dto.EFriendInviterDTO;
import com.lawu.eshop.user.dto.InviterDTO;
import com.lawu.eshop.user.param.EFriendQueryDataParam;
import com.lawu.eshop.user.srv.bo.EFriendInviterBO;
import com.lawu.eshop.user.srv.bo.InviterBO;
import com.lawu.eshop.user.srv.converter.EFriendInviterConverter;
import com.lawu.eshop.user.srv.converter.InviterConverter;
import com.lawu.eshop.user.srv.service.CommonService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/3/23
 */
@RestController
@RequestMapping(value = "user/common/")
public class CommonController extends BaseController {

	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "getInviter/{account}", method = RequestMethod.GET)
	public Result<InviterDTO> getInviterByAccount(@PathVariable String account) {
		InviterBO inviterBO = commonService.getInviterByAccount(account);
		if (inviterBO == null) {
			return successGet(ResultCode.RESOURCE_NOT_FOUND);
		}
		return successGet(InviterConverter.convertDTO(inviterBO));
	}

	/**
	 * 根据被邀请人查询出该人所有level邀请人编号
	 * 
	 * @param invitedUserNum	当前用户编号
	 * @param level	需要查询的上几级
	 * @param isLevel	是否需要查询上级级别
	 * @return
	 */
	@RequestMapping(value = "selectHigherLevelInviters/{invitedUserNum}", method = RequestMethod.POST)
	public List<CommissionInvitersUserDTO> selectHigherLevelInviters(@PathVariable String invitedUserNum,
											     @RequestParam int level, @RequestBody boolean isLevel) {
		List<CommissionInvitersUserDTO> userNumList = commonService.selectHigherLevelInviters(invitedUserNum, level,isLevel);
		return userNumList;
	}

	/**
	 * 我的E友
	 * @param dataParam
	 * @since  v2.3.0
	 * @return
	 */
	@RequestMapping(value = "selectEFriend", method = RequestMethod.POST)
	public Result<Page<EFriendInviterDTO>> selectEFriend(@RequestBody EFriendQueryDataParam dataParam) {
		Page<EFriendInviterBO> page = commonService.selectEFriend(dataParam);
		Page<EFriendInviterDTO> rtnPage = new Page<>();
		rtnPage.setCurrentPage(page.getCurrentPage());
		rtnPage.setTotalCount(page.getTotalCount());
		List<EFriendInviterDTO> rtnList = EFriendInviterConverter.converter(page.getRecords());
		rtnPage.setRecords(rtnList);
		return successCreated(rtnPage);
	}
}
