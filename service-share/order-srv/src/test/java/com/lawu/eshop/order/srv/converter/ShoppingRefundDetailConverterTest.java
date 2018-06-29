package com.lawu.eshop.order.srv.converter;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingRefundDetailDTO;
import com.lawu.eshop.order.srv.bo.ExpressInquiriesDetailBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingRefundDetailBO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDO;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月25日
 */
public class ShoppingRefundDetailConverterTest {
	
	@Test
	public void convertShoppingRefundDetailDTO() {
		ShoppingOrderItemExtendBO expected = ShoppingOrderItemExtendConverterTest.initShoppingOrderItemExtendBO();
		ShoppingRefundDetailDTO actual = ShoppingRefundDetailConverter.convert(expected);
		assertShoppingRefundDetailDTO(expected, actual);
	}
	
	@Test
	public void convertShoppingRefundDetailBO() {
		ShoppingRefundDetailDO expected = initShoppingRefundDetailDO();
		ShoppingRefundDetailBO actual = ShoppingRefundDetailConverter.convert(expected);
		assertShoppingRefundDetailBO(expected, actual);
	}
	
	@Test
	public void convertShoppingOrderExpressDTO() {
		ShoppingRefundDetailBO expected = initShoppingRefundDetailBO();
		ExpressInquiriesDetailBO expectedExpressInquiriesDetailBO = ExpressConverterTest.initExpressInquiriesDetailBO();
		ShoppingOrderExpressDTO actual = ShoppingRefundDetailConverter.covert(expected, expectedExpressInquiriesDetailBO);
		assertShoppingRefundDetailDTO(expected, expectedExpressInquiriesDetailBO, actual);
	}
	
	public static void assertShoppingRefundDetailDTO(ShoppingRefundDetailBO expected, ExpressInquiriesDetailBO expectedExpressInquiriesDetailBO, ShoppingOrderExpressDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getExpressCompanyName(), actual.getExpressCompanyName());
		Assert.assertEquals(expected.getWaybillNum(), actual.getWaybillNum());
		ExpressConverterTest.assertExpressInquiriesDetailDTO(expectedExpressInquiriesDetailBO, actual.getExpressInquiriesDetailDTO());
	}
	
	public static void assertShoppingRefundDetailDTO(ShoppingOrderItemExtendBO expected, ShoppingRefundDetailDTO actual) {
		Assert.assertNotNull(actual);
		ShoppingRefundDetailBO shoppingRefundDetailBO = expected.getShoppingRefundDetail();
		Assert.assertEquals(shoppingRefundDetailBO.getGmtCreate().getTime(), actual.getGmtCreate().getTime());
		Assert.assertEquals(shoppingRefundDetailBO.getAmount().doubleValue(), actual.getAmount().doubleValue(), 0D);
		Assert.assertEquals(shoppingRefundDetailBO.getId(), actual.getId());
		Assert.assertEquals(shoppingRefundDetailBO.getConsigneeAddress(), actual.getConsigneeAddress());
		Assert.assertEquals(shoppingRefundDetailBO.getConsigneeMobile(), actual.getConsigneeMobile());
		Assert.assertEquals(shoppingRefundDetailBO.getConsigneeName(), actual.getConsigneeName());
		Assert.assertEquals(shoppingRefundDetailBO.getExpressCompanyName(), actual.getExpressCompanyName());
		Assert.assertEquals(shoppingRefundDetailBO.getGmtConfirmed(), actual.getGmtConfirmed());
		Assert.assertEquals(shoppingRefundDetailBO.getGmtFill(), actual.getGmtFill());
		Assert.assertEquals(shoppingRefundDetailBO.getGmtIntervention(), actual.getGmtIntervention());
		Assert.assertEquals(shoppingRefundDetailBO.getGmtRefund(), actual.getGmtRefund());
		Assert.assertEquals(shoppingRefundDetailBO.getGmtSubmit(), actual.getGmtSubmit());
		Assert.assertEquals(shoppingRefundDetailBO.getIsAgree(), actual.getIsAgree());
		Assert.assertEquals(shoppingRefundDetailBO.getReason(), actual.getReason());
		Assert.assertEquals(shoppingRefundDetailBO.getRefusalReasons(), actual.getRefusalReasons());
		Assert.assertEquals(shoppingRefundDetailBO.getType(), actual.getType());
		Assert.assertEquals(shoppingRefundDetailBO.getVoucherPicture(), actual.getVoucherPicture());
		Assert.assertEquals(shoppingRefundDetailBO.getWaybillNum(), actual.getWaybillNum());
		Assert.assertEquals(shoppingRefundDetailBO.getDescription(), actual.getDescribe());
		Assert.assertEquals(expected.getShoppingOrderId(), actual.getShoppingOrderId());
		Assert.assertEquals(expected.getRefundStatus(), actual.getRefundStatus());
		Assert.assertEquals(expected.getShoppingOrder().getPaymentMethod(), actual.getPaymentMethod());
		ShoppingRefundProcessConverterTest.assertShoppingRefundProcessDTOList(expected.getShoppingRefundDetail().getShoppingRefundProcessList(), actual.getShoppingRefundProcessList());
	}
	
	public static void assertShoppingRefundDetailBO(ShoppingRefundDetailDO expected, ShoppingRefundDetailBO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getGmtCreate().getTime(), actual.getGmtCreate().getTime());
		Assert.assertEquals(expected.getGmtModified().getTime(), actual.getGmtModified().getTime());
		Assert.assertEquals(expected.getExpressCompanyId(), actual.getExpressCompanyId());
		Assert.assertEquals(expected.getAmount().doubleValue(), actual.getAmount().doubleValue(), 0D);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getConsigneeAddress(), actual.getConsigneeAddress());
		Assert.assertEquals(expected.getConsigneeMobile(), actual.getConsigneeMobile());
		Assert.assertEquals(expected.getConsigneeName(), actual.getConsigneeName());
		Assert.assertEquals(expected.getDescription(), actual.getDescription());
		Assert.assertEquals(expected.getExpressCompanyCode(), actual.getExpressCompanyCode());
		Assert.assertEquals(expected.getExpressCompanyName(), actual.getExpressCompanyName());
		Assert.assertEquals(expected.getGmtConfirmed(), actual.getGmtConfirmed());
		Assert.assertEquals(expected.getGmtFill(), actual.getGmtFill());
		Assert.assertEquals(expected.getGmtIntervention(), actual.getGmtIntervention());
		Assert.assertEquals(expected.getGmtRefund(), actual.getGmtRefund());
		Assert.assertEquals(expected.getGmtSubmit(), actual.getGmtSubmit());
		Assert.assertEquals(expected.getIsAgree(), actual.getIsAgree());
		Assert.assertEquals(expected.getReason(), actual.getReason());
		Assert.assertEquals(expected.getRefusalReasons(), actual.getRefusalReasons());
		Assert.assertEquals(expected.getShoppingOrderItemId(), actual.getShoppingOrderItemId());
		Assert.assertEquals(expected.getStatus(), actual.getStatus().getValue());
		Assert.assertEquals(expected.getType(), actual.getType().getValue());
		Assert.assertEquals(expected.getVoucherPicture(), actual.getVoucherPicture());
		Assert.assertEquals(expected.getWaybillNum(), actual.getWaybillNum());
	}
	
	private ShoppingRefundDetailDO initShoppingRefundDetailDO() {
		ShoppingRefundDetailDO rtn = new ShoppingRefundDetailDO();
    	rtn.setId(1L);
    	rtn.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	rtn.setAmount(new BigDecimal(1));
    	rtn.setDescription("就是想退款");
    	rtn.setGmtModified(new Date());
    	rtn.setGmtCreate(new Date());
    	rtn.setReason("七天无理由退货");
    	rtn.setShoppingOrderItemId(1L);
    	rtn.setStatus(StatusEnum.VALID.getValue());
    	return rtn;
	}
	
	private ShoppingRefundDetailBO initShoppingRefundDetailBO() {
		ShoppingRefundDetailBO rtn = new ShoppingRefundDetailBO();
    	rtn.setId(1L);
    	rtn.setType(ShoppingRefundTypeEnum.RETURN_REFUND);
    	rtn.setAmount(new BigDecimal(1));
    	rtn.setDescription("就是想退款");
    	rtn.setGmtModified(new Date());
    	rtn.setGmtCreate(new Date());
    	rtn.setReason("七天无理由退货");
    	rtn.setShoppingOrderItemId(1L);
    	rtn.setStatus(StatusEnum.VALID);
    	return rtn;
	}

}
