package com.lawu.eshop.jobs.impl.order;

import java.util.List;

import com.lawu.eshop.jobs.service.OrderSrvService;
import com.lawu.eshop.jobs.service.ShoppingCommissionRefactorService;
import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.framework.web.Result;
import com.lawu.jobsextend.AbstractPageJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * Description: 购物提成
 *
 * @author yangqh
 * @date 2017年4月24日 下午3:31:10
 *
 */
public class ProductOrderCommissionJob extends AbstractPageJob<ShoppingOrderCommissionDTO> {

	private static Logger logger = LoggerFactory.getLogger(ProductOrderCommissionJob.class);

//	@Autowired
//	private SaleAndVolumeCommissionService saleAndVolumeCommissionService;
	@Autowired
	private OrderSrvService orderSrvService;
	@Autowired
	private ShoppingCommissionRefactorService shoppingCommissionRefactorService;

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
		//saleAndVolumeCommissionService.commission(shoppingOrderCommissionDTO, CommissionEnum.SHOPPING,false);
		shoppingCommissionRefactorService.commissionRefactor(shoppingOrderCommissionDTO);
	}

	@Override
	public List<ShoppingOrderCommissionDTO> queryPage(int offset, int pageSize) {
		Result<List<ShoppingOrderCommissionDTO>> ordersResult = orderSrvService.commissionShoppingOrder(offset, pageSize);
		return ordersResult.getModel();
	}
}
