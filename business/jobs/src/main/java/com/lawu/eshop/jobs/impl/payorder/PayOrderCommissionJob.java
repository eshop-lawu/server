package com.lawu.eshop.jobs.impl.payorder;

import java.util.List;

import com.lawu.eshop.jobs.service.OrderSrvService;
import com.lawu.eshop.jobs.service.PayCommissionRefactorService;
import com.lawu.eshop.jobs.service.SaleAndVolumeCommissionService;
import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.eshop.property.constants.CommissionEnum;
import com.lawu.framework.web.Result;
import com.lawu.jobsextend.AbstractPageJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * Description: 买单提成
 *
 * @author yangqh
 * @date 2017年4月24日 下午3:31:10
 *
 */
public class PayOrderCommissionJob extends AbstractPageJob<ShoppingOrderCommissionDTO> {

	private static Logger logger = LoggerFactory.getLogger(PayOrderCommissionJob.class);

	@Autowired
	private OrderSrvService orderSrvService;
	@Autowired
	private SaleAndVolumeCommissionService saleAndVolumeCommissionService;
	@Autowired
	private PayCommissionRefactorService payCommissionRefactorService;

	@Override
	public boolean isStatusData() {
		return true;
	}

	@Override
	public boolean continueWhenSinglePageFail() {
		return true;
	}

	@Override
	public void executeSingle(ShoppingOrderCommissionDTO shoppingOrderCommissionDTO) {
		//saleAndVolumeCommissionService.commission(shoppingOrderCommissionDTO, CommissionEnum.PAY,false);
		payCommissionRefactorService.commissionRefactor(shoppingOrderCommissionDTO);
	}

	@Override
	public List<ShoppingOrderCommissionDTO> queryPage(int offset, int pageSize) {
		Result<List<ShoppingOrderCommissionDTO>> ordersResult = orderSrvService.selectNotCommissionOrder(offset, pageSize);
		return ordersResult.getModel();
	}
}
