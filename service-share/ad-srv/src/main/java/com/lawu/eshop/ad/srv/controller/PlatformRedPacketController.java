package com.lawu.eshop.ad.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.GetPlatformRedPacketDTO;
import com.lawu.eshop.ad.dto.PlatformRedPacketDTO;
import com.lawu.eshop.ad.param.PlatformRedPacketParam;
import com.lawu.eshop.ad.param.PlatformRedPacketQueryParam;
import com.lawu.eshop.ad.srv.bo.GetPlatformRedPacketBO;
import com.lawu.eshop.ad.srv.bo.PlatformRedPacketBO;
import com.lawu.eshop.ad.srv.converter.PlatformRedPacketConverter;
import com.lawu.eshop.ad.srv.service.PlatformRedPacketService;
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
@RequestMapping(value = "platformRedPacket/")
public class PlatformRedPacketController extends BaseController{
	
	@Autowired
	private PlatformRedPacketService platformRedPacketService;
	
	/**
	 * 设置平台红包
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "saveRedPacket", method = RequestMethod.POST)
	public Result saveRedPacket(@RequestBody PlatformRedPacketParam param) {

		platformRedPacketService.saveRedPacket(param);
		
		return successCreated();

	}
	
	
	/**
	 * 禁用平台红包
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "setRedPacket/{id}", method = RequestMethod.PUT)
	public Result setRedPacket(@PathVariable Long id ,@RequestParam Long auditorId) {

		platformRedPacketService.setRedPacket(id, auditorId);
		
		return successCreated();

	}
	
	/**
	 * 平台红包列表查询
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "queryRedPacket", method = RequestMethod.POST)
	public Result<Page<PlatformRedPacketDTO>> queryRedPacket(@RequestBody PlatformRedPacketQueryParam query) {

		Page<PlatformRedPacketBO> page = platformRedPacketService.queryRedPacket(query);
		
		Page<PlatformRedPacketDTO> pageDto = new Page<>();
		pageDto.setCurrentPage(page.getCurrentPage());
		pageDto.setTotalCount(page.getTotalCount());
		pageDto.setRecords(PlatformRedPacketConverter.convertDTOS(page.getRecords()));
		
		return successGet(pageDto);

	}
	
	/**
	 * 领取平台红包
	 * @param userNum
	 * @return
	 */
	@RequestMapping(value = "getRedPacket", method = RequestMethod.GET)
	public Result<GetPlatformRedPacketDTO> getRedPacket(@RequestParam String userNum) {

		GetPlatformRedPacketBO bo = platformRedPacketService.getRedPacket(userNum);
		GetPlatformRedPacketDTO dto = new GetPlatformRedPacketDTO();
		dto.setGet(bo.isGet());
		dto.setMoney(bo.getMoney());
		dto.setId(bo.getId());
		return successGet(dto);

	}
	
	

}
