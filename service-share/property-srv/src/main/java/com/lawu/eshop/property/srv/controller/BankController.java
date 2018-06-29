package com.lawu.eshop.property.srv.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.property.dto.BankDTO;
import com.lawu.eshop.property.srv.bo.BankBO;
import com.lawu.eshop.property.srv.converter.BankConverter;
import com.lawu.eshop.property.srv.service.BankService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangrc
 * @date 2017/3/29
 */
@RestController
@RequestMapping(value = "bank/")
public class BankController extends BaseController{
	
	@Resource 
	private BankService bankService;
	
	/**
	 * 查询所有银行
	 * @return
	 */
	@RequestMapping(value = "findBank", method = RequestMethod.GET)
    public Result<List<BankDTO>> findBank() {
		List<BankBO> BOS = bankService.findBank();
		return  successAccepted(BankConverter.convertDTOS(BOS));
    }

}
