package com.lawu.eshop.ad.srv.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.GetInviterBountyDTO;
import com.lawu.eshop.ad.dto.InviterBountyDTO;
import com.lawu.eshop.ad.dto.SentTotalInviterBountyDTO;
import com.lawu.eshop.ad.param.InviterBountyParam;
import com.lawu.eshop.ad.param.InviterBountyQueryParam;
import com.lawu.eshop.ad.srv.bo.GetInviterBountyBO;
import com.lawu.eshop.ad.srv.bo.InviterBountyBO;
import com.lawu.eshop.ad.srv.converter.InviterBountyConverter;
import com.lawu.eshop.ad.srv.service.InviterBountyService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 平台红包服务接口
 *
 * @author zhangrc
 * @date 2017/10/18
 *
 */
@RestController
@RequestMapping(value = "inviterBounty/")
public class InviterBountyController extends BaseController{
	
	@Autowired
	private InviterBountyService inviterBountyService;
	
	/**
	 * 设置邀请奖励金
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "saveInviterBounty", method = RequestMethod.POST)
	public Result saveInviterBounty(@RequestBody InviterBountyParam param) {

		inviterBountyService.saveInviterBounty(param);
		
		return successCreated();

	}
	
	
	/**
	 * 禁用邀请奖励金
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "setInviterBounty/{id}", method = RequestMethod.PUT)
	public Result setInviterBounty(@PathVariable Long id ,@RequestParam Long auditorId) {

		inviterBountyService.setInviterBounty(id, auditorId);
		
		return successCreated();

	}
	
	/**
	 * 邀请注册奖励金列表查询
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "queryInviterBounty", method = RequestMethod.POST)
	public Result<Page<InviterBountyDTO>> queryRedPacket(@RequestBody InviterBountyQueryParam query) {

		Page<InviterBountyBO> page = inviterBountyService.queryInviterBounty(query);
		
		Page<InviterBountyDTO> pageDto = new Page<>();
		pageDto.setCurrentPage(page.getCurrentPage());
		pageDto.setTotalCount(page.getTotalCount());
		pageDto.setRecords(InviterBountyConverter.convertDTOS(page.getRecords()));
		
		return successGet(pageDto);

	}
	
	/**
	 * 领取邀请奖励金
	 * @param userNum
	 * @return
	 */
	@RequestMapping(value = "getInviterBounty", method = RequestMethod.GET)
	public Result<GetInviterBountyDTO> getInviterBounty(@RequestParam String userNum,@RequestParam String regNum,@RequestParam UserTypeEnum userType) {

		GetInviterBountyBO getInviterBountyBO = inviterBountyService.getInviterBounty(userNum,regNum,userType);
		if (getInviterBountyBO == null || getInviterBountyBO.getMoney()==null) {
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		GetInviterBountyDTO dto = new GetInviterBountyDTO();
		dto.setMoney(getInviterBountyBO.getMoney());
		
		return successGet(dto);

	}
	
	/**
	 * 获取发送的邀请奖励金总金额
	 * @return
	 */
	@RequestMapping(value = "queryInviterBountyTotalMoney", method = RequestMethod.GET)
	public Result<SentTotalInviterBountyDTO> queryInviterBountyTotalMoney() {

		BigDecimal totalMoney = inviterBountyService.queryInviterBountyTotalMoney();
		
		SentTotalInviterBountyDTO dto = new SentTotalInviterBountyDTO();
		dto.setTotalMoney(totalMoney);
		
		return successGet(dto);

	}
	
	
	
	
	

}
