package com.lawu.eshop.game.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.dto.GameInformDTO;
import com.lawu.eshop.game.param.DealInformParam;
import com.lawu.eshop.game.param.GameInformParam;
import com.lawu.eshop.game.query.GameInformQuery;
import com.lawu.eshop.game.srv.bo.GameInformBO;
import com.lawu.eshop.game.srv.converter.GameInformConverter;
import com.lawu.eshop.game.srv.service.GameInformService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
@RestController
@RequestMapping(value = "gameInform/")
public class GameInformController extends BaseController{
	
	@Autowired
	private GameInformService gameInformService;
	
	/**
	 * 举报
	 * @param userNum
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "saveGameInform", method = RequestMethod.POST)
	public Result saveGameInform(@RequestParam String userNum, @RequestBody GameInformParam param) {
	    try {
			 gameInformService.saveGameInform(userNum, param);
	         return successCreated();
	    } catch (WrongOperationException e) {
	         return successCreated(ResultCode.GAME_INFORM_ALREADY, e.getMessage());
	    } 
	}
	
	/**
	 * 举报列表
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "page", method = RequestMethod.POST)
	public Result<Page<GameInformDTO>> page(@RequestBody GameInformQuery query) {
	    Page<GameInformBO> page =  gameInformService.page(query);
	    Page<GameInformDTO> pageDTO = new Page<>();
		pageDTO.setCurrentPage(query.getCurrentPage());
		pageDTO.setTotalCount(page.getTotalCount());
		pageDTO.setRecords(GameInformConverter.convertGameInformDTOS(page.getRecords()));
		return successCreated(pageDTO);
	}
	
	/**
	 * 处理举报
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "dealInform", method = RequestMethod.POST)
	public Result dealInform(@RequestBody DealInformParam param) {
	    gameInformService.dealInform(param);
		return successCreated();
	}
	
	

}
