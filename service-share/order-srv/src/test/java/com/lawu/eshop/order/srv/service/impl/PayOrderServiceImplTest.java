package com.lawu.eshop.order.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.EvaluationEnum;
import com.lawu.eshop.order.constants.PayOrderStatusEnum;
import com.lawu.eshop.order.constants.ReportFansRiseRateEnum;
import com.lawu.eshop.order.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.eshop.order.param.MerchantPayOrderListParam;
import com.lawu.eshop.order.param.OperatorPayOrderParam;
import com.lawu.eshop.order.param.PayOrderDataParam;
import com.lawu.eshop.order.param.PayOrderListParam;
import com.lawu.eshop.order.param.ReportDataParam;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.bo.PayOrderBO;
import com.lawu.eshop.order.srv.bo.PayOrderBaseBO;
import com.lawu.eshop.order.srv.bo.ThirdPayCallBackQueryPayOrderBO;
import com.lawu.eshop.order.srv.converter.PayOrderConverterTest;
import com.lawu.eshop.order.srv.domain.PayOrderDO;
import com.lawu.eshop.order.srv.domain.PayOrderDOExample;
import com.lawu.eshop.order.srv.mapper.PayOrderDOMapper;
import com.lawu.eshop.order.srv.service.PayOrderService;
import com.lawu.framework.core.page.Page;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
public class PayOrderServiceImplTest {

	@Autowired
	private PayOrderService payOrderService;

	@Autowired
	private PayOrderDOMapper payOrderDOMapper;

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
		
		payOrderService.delPayOrderInfo(expected.getId(), expected.getMemberId());
		
		PayOrderDO actual = payOrderDOMapper.selectByPrimaryKey(expected.getId());
		Assert.assertNotNull(actual);
		Assert.assertEquals(false, actual.getOrderStatus());
	}
	
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	@Test
	public void getMerchantPayOrderList() {
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
		
		MerchantPayOrderListParam param = new MerchantPayOrderListParam();
		param.setCurrentPage(1);
		param.setPageSize(10);
		Page<PayOrderBO> actual = payOrderService.getMerchantPayOrderList(expected.getMemberId(), param);
		Assert.assertNotNull(actual);
		Assert.assertEquals(param.getCurrentPage(), actual.getCurrentPage());
		Assert.assertEquals(1, actual.getTotalCount().intValue());
		Assert.assertEquals(1, actual.getRecords().size());
		for (PayOrderBO item : actual.getRecords()) {
			PayOrderConverterTest.assertPayOrderBO(expected, item);
		}
	}
	
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	@Test
	public void getOperatorPayOrderList() {
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
		
		OperatorPayOrderParam param = new OperatorPayOrderParam();
		param.setOrderNum(expected.getOrderNum());
		param.setCurrentPage(1);
		param.setPageSize(10);
		 Page<PayOrderBO> actual = payOrderService.getOperatorPayOrderList(param);
		Assert.assertNotNull(actual);
		Assert.assertEquals(param.getCurrentPage(), actual.getCurrentPage());
		Assert.assertEquals(1, actual.getTotalCount().intValue());
		Assert.assertEquals(1, actual.getRecords().size());
		for (PayOrderBO item : actual.getRecords()) {
			PayOrderConverterTest.assertPayOrderBO(expected, item);
		}
	}
	
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	@Test
	public void getpayOrderList() {
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
		
		PayOrderListParam param = new PayOrderListParam();
		param.setEvaluationEnum(EvaluationEnum.EVALUATION_SUCCESS);
		param.setCurrentPage(1);
		param.setPageSize(10);
		Page<PayOrderBO> actual = payOrderService.getpayOrderList(expected.getMemberId(), param);
		Assert.assertNotNull(actual);
		Assert.assertEquals(param.getCurrentPage(), actual.getCurrentPage());
		Assert.assertEquals(1, actual.getTotalCount().intValue());
		Assert.assertEquals(1, actual.getRecords().size());
		for (PayOrderBO item : actual.getRecords()) {
			PayOrderConverterTest.assertPayOrderBO(expected, item);
		}
	}
	
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	@Test
	public void getOrderInfo() {
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
		
		PayOrderBO actual = payOrderService.getOrderInfo(expected.getId(), expected.getMemberId());
		PayOrderConverterTest.assertPayOrderBO(expected, actual);
	}
	
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	@Test
	public void savePayOrderInfo() {
		Long memberId = 1L;
		String memberNum = "M00001";
		PayOrderDataParam param = new PayOrderDataParam();
		param.setFavoredAmount(1D);
		param.setMerchantId(1L);
		param.setMerchantNum("B00001");
		param.setNotFavoredAmount(1D);
		param.setMerchantFavoredId(1L);
		param.setTotalAmount(2D);
		param.setFans(true);
		PayOrderBO payOrderBO = payOrderService.savePayOrderInfo(memberId, param, memberNum);
		
		PayOrderDOExample example = new PayOrderDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		PayOrderDO actual = payOrderDOMapper.selectByExample(example).get(0);
		Assert.assertNotNull(payOrderBO);
		Assert.assertNotNull(payOrderBO.getOrderNum());
		Assert.assertNotNull(payOrderBO.getId());
		Assert.assertEquals(payOrderBO.getId(), actual.getId());
		Assert.assertNotNull(actual);
		Assert.assertNotNull(actual.getId());
		Assert.assertNotNull(actual.getGmtCreate());
		Assert.assertNotNull(actual.getGmtModified());
		Assert.assertNotNull(actual.getOrderNum());
		Assert.assertNull(actual.getCommentTime());
		Assert.assertNull(actual.getPayType());
		Assert.assertNull(actual.getGmtCommission());
		Assert.assertNull(actual.getThirdNumber());
		Assert.assertEquals(memberNum, actual.getMemberNum());
		Assert.assertEquals(memberId, actual.getMemberId());
		Assert.assertEquals(param.getFavoredAmount(), actual.getFavoredAmount().doubleValue(), 0D);
		Assert.assertEquals(true, actual.getOrderStatus());
		Assert.assertEquals(param.getTotalAmount() - param.getFavoredAmount(), actual.getActualAmount().doubleValue(), 0D);
		Assert.assertEquals(CommissionStatusEnum.NOT_COUNTED.getValue(), actual.getCommissionStatus());
		Assert.assertEquals(false, actual.getIsEvaluation());
		Assert.assertEquals(param.getMerchantId(), actual.getMerchantId());
		Assert.assertEquals(param.getMerchantNum(), actual.getMerchantNum());
		Assert.assertEquals(param.getNotFavoredAmount(), actual.getNotFavoredAmount().doubleValue(), 0D);
		Assert.assertEquals(param.getTotalAmount(), actual.getTotalAmount().doubleValue(), 0D);
	}
	
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	@Test
	public void selectNotCommissionOrder() {
		// 插入一条未计算提成的订单
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
		
		List<ShoppingOrderCommissionDTO> actual =  payOrderService.selectNotCommissionOrder(0,10);
		Assert.assertNotNull(actual);
		Assert.assertEquals(1, actual.size());
		for (ShoppingOrderCommissionDTO item : actual) {
			assertShoppingOrderCommissionDTO(expected, item);
		}
	}
	
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	@Test
	public void selectThirdPayCallBackPayOrder() {
		// 插入一条未计算提成的订单
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
		
		ThirdPayCallBackQueryPayOrderBO actual =  payOrderService.selectThirdPayCallBackPayOrder(expected.getId().toString());
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getActualAmount().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), actual.getActualMoney(), 0D);
		Assert.assertEquals(expected.getMerchantNum(), actual.getBusinessUserNum());
		Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
		Assert.assertEquals(expected.getStatus(), actual.getPayOrderStatusEnum().getVal());
	}
	
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	@Test
	public void updateCommissionStatus() {
		// 插入一条未计算提成的订单
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
		
		List<Long> ids = new ArrayList<>();
		ids.add(expected.getId());
		payOrderService.updateCommissionStatus(ids);
		
		PayOrderDO actual = payOrderDOMapper.selectByPrimaryKey(expected.getId());
		Assert.assertNotNull(actual);
		Assert.assertNotNull(actual.getGmtCommission());
		Assert.assertEquals(CommissionStatusEnum.CALCULATED.getValue(), actual.getCommissionStatus());
	}
	
	/**
	 * TODO SQL不兼容
	 * 
	 * @author jiangxinjun
	 * @date 2017年11月2日
	 */
	@Ignore
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
	public void getAutoCommentPayOrderList() {
	    payOrderService.getAutoCommentPayOrderList();
	}
	
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
	public void fansSaleTransformPay() {
        // 插入一条未计算提成的订单
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
        expected.setIsFans(false);
        payOrderDOMapper.insert(expected);
        
        ReportDataParam dparam = new ReportDataParam();
        dparam.setFlag(ReportFansRiseRateEnum.DAY);
        dparam.setMerchantId(expected.getMerchantId());
        List<ReportRiseRerouceDTO> actual = payOrderService.fansSaleTransformPay(dparam);
        Assert.assertEquals(0, Integer.valueOf(actual.get(0).getValue()).intValue());
        Assert.assertEquals(1, Integer.valueOf(actual.get(1).getValue()).intValue());
	}
	
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void getPayOrderById() {
     // 插入一条未计算提成的订单
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
        expected.setIsFans(false);
        payOrderDOMapper.insert(expected);
        
        PayOrderBaseBO actual = payOrderService.getPayOrderById(expected.getId().toString());
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getMemberId(), actual.getMemberId());
        Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
    }
    
	public static void assertShoppingOrderCommissionDTO(PayOrderDO expected, ShoppingOrderCommissionDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getMemberNum(), actual.getMemberNum());
		Assert.assertEquals(expected.getMerchantNum(), actual.getMerchantNum());
		Assert.assertEquals(expected.getActualAmount().doubleValue(), actual.getActualAmount().doubleValue(), 0D);
	}
}
