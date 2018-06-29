package com.lawu.eshop.order.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.order.constants.PayOrderStatusEnum;
import com.lawu.eshop.order.constants.ReportFansRiseRateEnum;
import com.lawu.eshop.order.dto.ReportRiseRateDTO;
import com.lawu.eshop.order.param.ReportDataParam;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.domain.PayOrderDO;
import com.lawu.eshop.order.srv.mapper.PayOrderDOMapper;
import com.lawu.eshop.order.srv.service.ReportPayService;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
public class ReportPayServiceImplTest {

	@Autowired
	private ReportPayService reportPayService;

	@Autowired
	private PayOrderDOMapper payOrderDOMapper;

	/**
	 * TODO SQL不兼容
	 * 
	 * @author jiangxinjun
	 * @date 2017年7月26日
	 */
	@Ignore
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	@Test
	public void delPayOrderInfo() {
		// 插入一条已经支付成功并且已经评论的买单记录
		PayOrderDO expected = new PayOrderDO();
		expected.setActualAmount(new BigDecimal(1));
		expected.setCommentTime(new Date());
		expected.setFavoredAmount(new BigDecimal(1));
		expected.setGmtCreate(new Date());
		expected.setGmtModified(new Date());
		expected.setIsEvaluation(true);
		expected.setMemberId(1L);
		expected.setMemberNum("M00001");
		expected.setMerchantId(1L);
		expected.setMerchantNum("B00001");
		expected.setNotFavoredAmount(new BigDecimal(1));
		expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.PAY_ORDER));
		expected.setOrderStatus(true);
		expected.setPayType(TransactionPayTypeEnum.BALANCE.getVal());
		expected.setStatus(PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal());
		expected.setTotalAmount(new BigDecimal(2));
		payOrderDOMapper.insert(expected);
		
		ReportDataParam dparam = new ReportDataParam();
		dparam.setFlag(ReportFansRiseRateEnum.DAY);
		dparam.setMerchantId(expected.getMerchantId());
		ReportRiseRateDTO actual = reportPayService.payVolumeRiseRate(dparam);
		Assert.assertNotNull(actual);
	}
}
