package com.lawu.eshop.operator.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.mall.dto.ExpressCompanyDTO;
import com.lawu.eshop.operator.api.service.ExpressCompanyService;
import com.lawu.eshop.operator.api.service.ShoppingOrderExtendService;
import com.lawu.eshop.operator.api.service.ShoppingOrderService;
import com.lawu.eshop.order.param.ShoppingOrderUpdateInfomationParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderUpdateInfomationForeignParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class ShoppingOrderExtendServiceImpl extends BaseController implements ShoppingOrderExtendService {
	
	@Autowired
	private ExpressCompanyService expressCompanyService;
	
	@Autowired
	private ShoppingOrderService shoppingOrderService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public Result updateInformation(Long id, ShoppingOrderUpdateInfomationForeignParam param) {
		Result<ExpressCompanyDTO> resultExpressCompanyDTO = expressCompanyService.get(param.getExpressCompanyId());
		
		if (!isSuccess(resultExpressCompanyDTO)) {
			return successCreated(resultExpressCompanyDTO);
		}
		
		ShoppingOrderUpdateInfomationParam shoppingOrderUpdateInfomationParam = new ShoppingOrderUpdateInfomationParam();
		shoppingOrderUpdateInfomationParam.setConsigneeAddress(param.getConsigneeAddress());
		shoppingOrderUpdateInfomationParam.setConsigneeName(param.getConsigneeName());
		shoppingOrderUpdateInfomationParam.setConsigneeMobile(param.getConsigneeMobile());
		shoppingOrderUpdateInfomationParam.setExpressCompanyId(resultExpressCompanyDTO.getModel().getId());
		shoppingOrderUpdateInfomationParam.setExpressCompanyCode(resultExpressCompanyDTO.getModel().getCode());
		shoppingOrderUpdateInfomationParam.setExpressCompanyName(resultExpressCompanyDTO.getModel().getName());
		shoppingOrderUpdateInfomationParam.setOrderStatus(param.getOrderStatus());
		shoppingOrderUpdateInfomationParam.setWaybillNum(param.getWaybillNum());
		
		Result result = shoppingOrderService.updateInformation(id, shoppingOrderUpdateInfomationParam);
		
		if (!isSuccess(result)) {
			return successCreated(result.getRet());
		}
		
		return successCreated();
	}

}
