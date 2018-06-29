package com.lawu.eshop.order.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.srv.bo.ShoppingRefundDetailExtendBO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingRefundDetailExtendDO;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月25日
 */
public class ShoppingRefundDetailExtendConverterTest {
	
	@Test
	public void convertShoppingRefundDetailDTO() {
		ShoppingRefundDetailExtendDO expected = initShoppingRefundDetailExtendDO();
		ShoppingRefundDetailExtendBO actual = ShoppingRefundDetailExtendConverter.convert(expected);
		assertShoppingRefundDetailBO(expected, actual);
	}
	
	public static void assertShoppingRefundDetailBO(ShoppingRefundDetailExtendDO expected, ShoppingRefundDetailExtendBO actual) {
		Assert.assertNotNull(actual);
		ShoppingRefundDetailConverterTest.assertShoppingRefundDetailBO(expected, actual);
		ShoppingRefundProcessConverterTest.assertShoppingRefundProcessBOList(expected.getShoppingRefundProcessList(), actual.getShoppingRefundProcessList());
	}
	
	private ShoppingRefundDetailExtendDO initShoppingRefundDetailExtendDO() {
		ShoppingRefundDetailExtendDO rtn = new ShoppingRefundDetailExtendDO();
    	rtn.setId(1L);
    	rtn.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	rtn.setAmount(new BigDecimal(1));
    	rtn.setDescription("就是想退款");
    	rtn.setGmtModified(new Date());
    	rtn.setGmtCreate(new Date());
    	rtn.setReason("七天无理由退货");
    	rtn.setShoppingOrderItemId(1L);
    	rtn.setStatus(StatusEnum.VALID.getValue());
    	
    	ShoppingRefundProcessDO shoppingRefundProcessDO = new ShoppingRefundProcessDO();
    	shoppingRefundProcessDO.setId(1L);
    	shoppingRefundProcessDO.setGmtCreate(new Date());
    	shoppingRefundProcessDO.setShoppingRefundDetailId(rtn.getId());
    	shoppingRefundProcessDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	rtn.setShoppingRefundProcessList(new ArrayList<>());
    	rtn.getShoppingRefundProcessList().add(shoppingRefundProcessDO);
    	return rtn;
	}
	
}
