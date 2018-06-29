package com.lawu.eshop.property.srv.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.dto.CommissionUtilDTO;
import com.lawu.eshop.property.srv.bo.CommissionUtilBO;
import com.lawu.eshop.property.srv.service.CommissionUtilService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 收益，爱心账户计算结果
 * </p>
 * @author Yangqh
 * @date 2017年5月24日 下午1:00:46
 *
 */
@RestController
@RequestMapping(value = "commissionUtil/")
public class CommissionUtilController extends BaseController {

	@Autowired
	private CommissionUtilService commissionUtilService;

	/**
	 * 获取用户点击广告后，自己所得余额和需要扣钱的爱心账户
	 * @param clickMoney
	 * @return
	 * @author yangqh
	 * @date 2017年5月24日 下午1:09:48
	 */
	@RequestMapping(value = "getClickAdMine", method = RequestMethod.PUT)
	public Result<CommissionUtilDTO> getClickAdMine(@RequestBody BigDecimal clickMoney)  {
		if(clickMoney == null || !(clickMoney.compareTo(BigDecimal.ZERO) == 1)){
			return successCreated(ResultCode.FAIL,"传入金额错误");
		}
		CommissionUtilBO bo = commissionUtilService.getClickAdMine(clickMoney);
		CommissionUtilDTO dto = new CommissionUtilDTO();
		dto.setActureLoveIn(bo.getActureLoveIn());
		dto.setActureMoneyIn(bo.getActureMoneyIn());
		return successCreated(dto);
	}
	
	/**
	 * 获取用户收入余额，按指定比例计算，计算爱心账户
	 * @param clickMoney
	 * @return
	 * @author yangqh
	 * @date 2017年5月24日 下午1:09:48
	 */
	@RequestMapping(value = "getIncomeMoney", method = RequestMethod.PUT)
	public Result<CommissionUtilDTO> getIncomeMoney(@RequestBody BigDecimal clickMoney)  {
		if(clickMoney == null || !(clickMoney.compareTo(BigDecimal.ZERO) == 1)){
			return successCreated(ResultCode.FAIL,"传入金额错误");
		}
		CommissionUtilBO bo = commissionUtilService.getIncomeMoney(clickMoney);
		CommissionUtilDTO dto = new CommissionUtilDTO();
		dto.setActureLoveIn(bo.getActureLoveIn());
		dto.setActureMoneyIn(bo.getActureMoneyIn());
		return successCreated(dto);
	}

}
