package com.lawu.eshop.operator.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.property.dto.BankAccountDTO;
import com.lawu.eshop.property.dto.BankAccountOperatorDTO;
import com.lawu.eshop.property.dto.BankDTO;
import com.lawu.eshop.property.param.BankAccountOperatorParam;
import com.lawu.framework.web.Result;

/**
 * 会员api 银行卡管理接口
 * @author zhangrc
 * @date 2017/10/24
 *
 */
@FeignClient(value = "property-srv")
public interface BankAccountService {
	
	/**
	 * 查询我绑定的银行卡
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "bankAccount/selectMyBankAccount")
	Result<List<BankAccountDTO>> selectMyBank(@RequestParam("userNum") String userNum);
	
	
	/**
	 * 单个查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "bankAccount/selectAccount/{id}", method = RequestMethod.GET)
    Result<BankAccountDTO> selectAccount(@PathVariable("id") Long id);
	
	
	/**
	 * 修改
	 * @param id
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "bankAccount/updateBankOperator/{id}", method = RequestMethod.PUT)
    Result updateBankOperator(@PathVariable("id") Long id,@RequestBody BankAccountOperatorParam param);
	
	/**
	 * 列表查询
	 * @param userNum
	 * @return
	 */
	@RequestMapping(value = "bankAccount/selectBankOperator", method = RequestMethod.GET)
    Result<List<BankAccountOperatorDTO>> selectBankOperator(@RequestParam("userNum") String userNum);
	
	/**
	 * 银行卡数据
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "bank/findBank")
    Result<List<BankDTO>> findBank();

	
}
