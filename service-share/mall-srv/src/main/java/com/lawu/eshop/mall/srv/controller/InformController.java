/**
 * 
 */
package com.lawu.eshop.mall.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.dto.InformDTO;
import com.lawu.eshop.mall.param.InformEditParam;
import com.lawu.eshop.mall.param.InformQueryParam;
import com.lawu.eshop.mall.param.InformSaveParam;
import com.lawu.eshop.mall.srv.bo.InformBO;
import com.lawu.eshop.mall.srv.bo.InformEditBO;
import com.lawu.eshop.mall.srv.converter.InformConverter;
import com.lawu.eshop.mall.srv.service.InformService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author lihj
 * @date 2017年7月27日
 */
@RestController
@RequestMapping(value = "inform/")
public class InformController extends BaseController {

	@Autowired
	private InformService informService;


	/**
	 * 添加举报
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "addInform", method = RequestMethod.POST)
	public Result addInform(@RequestBody InformSaveParam param) {
		Integer id = informService.addInform(param);
		if (id == null || id <= 0) {
			return successCreated(ResultCode.SAVE_FAIL);
		}
		return successCreated(ResultCode.SUCCESS);
	}

	/**
	 * 查询举报信息(运营后台操作)
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "selectInformList", method = RequestMethod.POST)
	public Result<Page<InformDTO>> selectInformList(@RequestBody InformQueryParam param) {
		Page<InformBO> pageBO = informService.selectInformList(param);
		Page<InformDTO> pageDTO = new Page<InformDTO>();
		pageDTO.setCurrentPage(pageBO.getCurrentPage());
		pageDTO.setTotalCount(pageBO.getTotalCount());
		pageDTO.setRecords(InformConverter.convertDTOS(pageBO.getRecords()));
		return successCreated(pageDTO);
	}

	/**
	 * 处理举报信息(运营后台操作)
	 * @param param
	 * @return
	 */
	@RequestMapping(value="editInform",method = RequestMethod.POST)
	public Result editInform(@RequestBody InformEditParam param){
		InformEditBO bo =InformConverter.convertBO(param);
		informService.editInform(bo);
		return successCreated(ResultCode.SUCCESS);
	} 
	
}
