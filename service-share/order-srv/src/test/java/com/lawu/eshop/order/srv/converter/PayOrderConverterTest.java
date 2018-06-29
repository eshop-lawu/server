package com.lawu.eshop.order.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.PayOrderStatusEnum;
import com.lawu.eshop.order.dto.MemberPayOrderInfoDTO;
import com.lawu.eshop.order.dto.MerchantPayOrderListDTO;
import com.lawu.eshop.order.dto.OperatorPayOrderListDTO;
import com.lawu.eshop.order.dto.PayOrderDTO;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.bo.PayOrderBO;
import com.lawu.eshop.order.srv.domain.PayOrderDO;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月25日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
public class PayOrderConverterTest {

	@Test
	public void coverBOPayOrderBO() {
		PayOrderDO expected = intiPayOrderDO();
		PayOrderBO actual = PayOrderConverter.coverBO(expected);
		assertPayOrderBO(expected, actual);
	}

	@Test
	public void coverBOS() {
		List<PayOrderDO> expected = new ArrayList<>();
		expected.add(intiPayOrderDO());

		List<PayOrderBO> actual = PayOrderConverter.coverBOS(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertPayOrderBO(expected.get(i), actual.get(i));
		}
	}

	@Test
	public void coverDTO() {
		PayOrderBO expected = intiPayOrderBO();

		PayOrderDTO actual = PayOrderConverter.coverDTO(expected);
		assertPayOrderDTO(expected, actual);
	}

	@Test
	public void coverDTOS() {
		List<PayOrderBO> expected = new ArrayList<>();
		expected.add(intiPayOrderBO());

		List<MerchantPayOrderListDTO> actual = PayOrderConverter.coverDTOS(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertMerchantPayOrderListDTO(expected.get(i), actual.get(i));
		}
	}

	@Test
	public void coverOperatorPayOrderListDTOS() {
		List<PayOrderBO> expected = new ArrayList<>();
		expected.add(intiPayOrderBO());

		List<OperatorPayOrderListDTO> actual = PayOrderConverter.coverOperatorPayOrderListDTOS(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertOperatorPayOrderListDTO(expected.get(i), actual.get(i));
		}
	}

	@Test
	public void coverOrderInfoDTO() {
		PayOrderBO expected = intiPayOrderBO();

		MemberPayOrderInfoDTO actual = PayOrderConverter.coverOrderInfoDTO(expected);
		assertMemberPayOrderInfoDTO(expected, actual);
	}

	private PayOrderDO intiPayOrderDO() {
		PayOrderDO rtn = new PayOrderDO();
		rtn.setActualAmount(new BigDecimal(1));
		rtn.setCommentTime(new Date());
		rtn.setCommissionStatus(CommissionStatusEnum.CALCULATED.getValue());
		rtn.setFavoredAmount(new BigDecimal(1));
		rtn.setGmtCommission(new Date());
		rtn.setGmtModified(new Date());
		rtn.setGmtCreate(new Date());
		rtn.setId(1L);
		rtn.setIsEvaluation(true);
		rtn.setMemberId(1L);
		rtn.setMemberNum("M00001");
		rtn.setMerchantId(1L);
		rtn.setMerchantNum("B0001");
		rtn.setNotFavoredAmount(new BigDecimal(1));
		rtn.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.PAY_ORDER));
		rtn.setOrderStatus(false);
		rtn.setPayType(TransactionPayTypeEnum.ALIPAY.getVal());
		rtn.setStatus(PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal());
		rtn.setThirdNumber("257455851212145");
		rtn.setTotalAmount(new BigDecimal(2));
		return rtn;
	}
	
	private PayOrderBO intiPayOrderBO() {
		PayOrderBO rtn = new PayOrderBO();
		rtn.setActualAmount(new BigDecimal(1));
		rtn.setCommentTime(new Date());
		rtn.setFavoredAmount(new BigDecimal(1));
		rtn.setGmtModified(new Date());
		rtn.setGmtCreate(new Date());
		rtn.setId(1L);
		rtn.setMemberId(1L);
		rtn.setMerchantId(1L);
		rtn.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.PAY_ORDER));
		rtn.setPayType(TransactionPayTypeEnum.ALIPAY.getVal());
		rtn.setTotalAmount(new BigDecimal(2));
		rtn.setEvaluation(true);
		return rtn;
	}

	public static void assertPayOrderBO(PayOrderDO expected, PayOrderBO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getGmtCreate(), actual.getGmtCreate());
		Assert.assertEquals(expected.getMemberId(), actual.getMemberId());
		Assert.assertEquals(expected.getCommentTime() != null ? expected.getCommentTime().getTime() : null, actual.getCommentTime() != null ? actual.getCommentTime().getTime() : null);
		Assert.assertEquals(expected.getActualAmount().doubleValue(), actual.getActualAmount().doubleValue(), 0D);
		Assert.assertEquals(expected.getPayType(), actual.getPayType());
		Assert.assertEquals(expected.getIsEvaluation(), actual.getEvaluation());
		Assert.assertEquals(expected.getFavoredAmount().doubleValue(), actual.getFavoredAmount().doubleValue(), 0D);
		Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
		Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
		Assert.assertEquals(expected.getTotalAmount().doubleValue(), actual.getTotalAmount().doubleValue(), 0D);
	}

	public static void assertPayOrderDTO(PayOrderBO expected, PayOrderDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getActualAmount().doubleValue(), actual.getActualAmount().doubleValue(), 0D);
		Assert.assertEquals(expected.getFavoredAmount().doubleValue(), actual.getFavoredAmount().doubleValue(), 0D);
		Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
		Assert.assertEquals(expected.getTotalAmount().doubleValue(), actual.getTotalAmount().doubleValue(), 0D);
		Assert.assertEquals(expected.getEvaluation(), actual.getEvaluationEnum().getVal());
		Assert.assertEquals(expected.getGmtCreate(), actual.getGmtCreate());
	}

	public static void assertMerchantPayOrderListDTO(PayOrderBO expected, MerchantPayOrderListDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getActualAmount().doubleValue(), actual.getActualAmount().doubleValue(), 0D);
		Assert.assertEquals(expected.getGmtCreate(), actual.getGmtCreate());
		Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
	}

	public static void assertOperatorPayOrderListDTO(PayOrderBO expected, OperatorPayOrderListDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getActualAmount().doubleValue(), actual.getActualAmount().doubleValue(), 0D);
		Assert.assertEquals(expected.getGmtCreate(), actual.getGmtCreate());
		Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
		Assert.assertEquals(expected.getMemberId(), actual.getMemberId());
		Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
	}

	public static void assertMemberPayOrderInfoDTO(PayOrderBO expected, MemberPayOrderInfoDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getActualAmount().doubleValue(), actual.getActualAmount().doubleValue(), 0D);
		Assert.assertEquals(expected.getGmtCreate(), actual.getGmtCreate());
		Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
		Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
		Assert.assertEquals(expected.getEvaluation(), actual.getEvaluationEnum().getVal());
		Assert.assertEquals(expected.getFavoredAmount().doubleValue(), actual.getFavoredAmount().doubleValue(), 0D);
		Assert.assertEquals(expected.getTotalAmount().doubleValue(), actual.getTotalAmount().doubleValue(), 0D);
	}
}
