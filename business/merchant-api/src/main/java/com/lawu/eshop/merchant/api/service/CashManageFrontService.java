package com.lawu.eshop.merchant.api.service;

import java.util.List;

import com.lawu.eshop.property.dto.CashFeeDTO;
import com.lawu.eshop.property.dto.CashFeeParamDTO;
import com.lawu.eshop.property.param.CashChargeDataParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.property.dto.WithdrawCashDetailDTO;
import com.lawu.eshop.property.dto.WithdrawCashQueryDTO;
import com.lawu.eshop.property.dto.WithdrawCashStatusDTO;
import com.lawu.eshop.property.param.CashBillDataParam;
import com.lawu.eshop.property.param.CashDataParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 前端用户提现
 * </p>
 * @author Yangqh
 * @date 2017年4月5日 下午6:18:44
 *
 */
@FeignClient(value= "property-srv")
public interface CashManageFrontService {
	
	/**
	 * 用户、商家提现
	 * @param cash
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "cashFront/save")
	Result save(@RequestBody CashDataParam cash);

	/**
	 * 商家提现明细
	 * @param cparam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "cashFront/findCashList")
	Result<Page<WithdrawCashQueryDTO>> findCashList(@RequestBody CashBillDataParam cparam);

	/**
	 * 商家提现详情
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "cashFront/cashDetail")
	Result<WithdrawCashDetailDTO> cashDetail(@RequestParam("id") Long id);
	
	/**
	 * 查询交易明细 如果交易类型为提现，需要知道提现的状态 查询提现明细状态
	 * 
	 * @param ids
	 *            提现id列表
	 * @return
	 */
	@RequestMapping(value = "cashFront/findCashDetailStatus", method = RequestMethod.GET)
	Result<List<WithdrawCashStatusDTO>> findCashDetailStatus(@RequestParam("ids") List<Long> ids);
	
	/**
	 * 用户是否存在提现申请
	 * @param userNum
	 * @param bankAccountId
	 * @return
	 */
	@RequestMapping(value = "cashFront/isExistCash", method = RequestMethod.GET)
	Result<Boolean> isExistCash(@RequestParam("userNum") String userNum,@RequestParam("bankAccountId") Long bankAccountId);

	/**
	 * 获取提现手续费和服务费
	 *
	 * @param cashChargeDataParam
	 * @return
	 */
	@RequestMapping(value = "cashFront/calculationFee", method = RequestMethod.POST)
	Result<CashFeeDTO> calculationFee(@RequestBody CashChargeDataParam cashChargeDataParam);

	/**
	 * 获取计算手续费和服务费参数
	 *
	 * @param userNum
	 * @return
	 */
	@RequestMapping(value = "cashFront/getCalculationFeeParam", method = RequestMethod.GET)
	Result<CashFeeParamDTO> getCalculationFeeParam(@RequestParam("userNum") String userNum);
}
