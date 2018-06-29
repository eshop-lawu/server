package com.lawu.eshop.order.srv.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.constants.ReportFansRiseRateEnum;
import com.lawu.eshop.order.constants.ShoppingOrderSortEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusToMemberEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusToMerchantEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.constants.SortOrderEnum;
import com.lawu.eshop.order.dto.CommentOrderDTO;
import com.lawu.eshop.order.dto.ReportRiseRateDTO;
import com.lawu.eshop.order.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.eshop.order.dto.ShoppingOrderIsNoOnGoingOrderDTO;
import com.lawu.eshop.order.dto.ShoppingOrderMoneyDTO;
import com.lawu.eshop.order.dto.ShoppingOrderPaymentDTO;
import com.lawu.eshop.order.dto.foreign.ExpressInquiriesDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressInfoDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendQueryDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundForMerchantDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundForOperatorDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderNumberOfOrderStatusDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToMerchantDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToOperatorDTO;
import com.lawu.eshop.order.param.ReportDataParam;
import com.lawu.eshop.order.param.ShoppingOrderLogisticsInformationParam;
import com.lawu.eshop.order.param.ShoppingOrderRequestRefundParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementItemParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementParam;
import com.lawu.eshop.order.param.ShoppingOrderUpdateInfomationParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMemberParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMerchantParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToOperatorParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundQueryForeignParam;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.converter.ShoppingOrderConverterTest;
import com.lawu.eshop.order.srv.converter.ShoppingOrderItemConverterTest;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDOExample;
import com.lawu.eshop.order.srv.json.JCDateDeserializer;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderItemDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundDetailDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundProcessDOMapper;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.HttpCode;

/**
 * 
 * @author Sunny
 * @date 2017年6月20日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
@WebAppConfiguration
public class ShoppingOrderControllerTest {
	
    private MockMvc mvc;

    @Autowired
    private ShoppingOrderController shoppingOrderController;
    
	@Autowired
	private ShoppingOrderDOMapper shoppingOrderDOMapper;
	
	@Autowired
	private ShoppingOrderItemDOMapper shoppingOrderItemDOMapper;
	
	@Autowired
	private ShoppingRefundDetailDOMapper shoppingRefundDetailDOMapper;
	
	@Autowired
	private ShoppingRefundProcessDOMapper shoppingRefundProcessDOMapper;
	
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(shoppingOrderController).build();
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() throws Exception {
    	List<ShoppingOrderSettlementParam> expected = new ArrayList<>();
    	ShoppingOrderSettlementParam param = ShoppingOrderConverterTest.initShoppingOrderSettlementParam();
    	ShoppingOrderSettlementItemParam item = param.getItems().get(0);
    	expected.add(param);
    	
    	String content = JSONObject.toJSONString(expected);
    	RequestBuilder request = MockMvcRequestBuilders.post("/shoppingOrder/save").contentType(MediaType.APPLICATION_JSON).content(content);    	
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        List<Long> actual = JSONObject.parseArray(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), Long.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.size());
        
    	ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
    	shoppingOrderDOExample.createCriteria().andMemberIdEqualTo(param.getMemberId());
    	ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByExample(shoppingOrderDOExample).get(0);
    	
    	ShoppingOrderItemDOExample shoppingOrderItemDOExample = new ShoppingOrderItemDOExample();
    	shoppingOrderItemDOExample.createCriteria().andShoppingOrderIdEqualTo(shoppingOrderDO.getId());
    	ShoppingOrderItemDO shoppingOrderItemDO = shoppingOrderItemDOMapper.selectByExample(shoppingOrderItemDOExample).get(0);
    	
    	ShoppingOrderConverterTest.assertShoppingOrderDO(param, shoppingOrderDO);
    	Assert.assertEquals(actual.get(0), shoppingOrderDO.getId());
    	ShoppingOrderItemConverterTest.assertShoppingOrderItemDO(item, shoppingOrderItemDO, shoppingOrderDO.getId());
    }
    
    @SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectPageByMemberId() throws Exception {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	
    	ShoppingOrderQueryForeignToMemberParam param = new ShoppingOrderQueryForeignToMemberParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	param.setOrderStatus(ShoppingOrderStatusToMemberEnum.BE_EVALUATED);
    	param.setKeyword(expected.getOrderNum());
    	
    	String content = JSONObject.toJSONString(param);
    	RequestBuilder request = MockMvcRequestBuilders.post("/shoppingOrder/selectPageByMemberId/" + expected.getMemberId()).contentType(MediaType.APPLICATION_JSON).content(content);    	
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        Page<JSONObject> actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), Page.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(param.getCurrentPage(), actual.getCurrentPage());
    	Assert.assertEquals(1, actual.getTotalCount().intValue());
    	for (JSONObject jsonObject : actual.getRecords()) {
			 ShoppingOrderExtendQueryDTO shoppingOrderExtendQueryDTO = JSONObject.toJavaObject(jsonObject, ShoppingOrderExtendQueryDTO.class);
			 Assert.assertNotNull(shoppingOrderExtendQueryDTO);
			 Assert.assertEquals(expected.getMerchantName(), shoppingOrderExtendQueryDTO.getMerchantName());
			 Assert.assertEquals(1, shoppingOrderExtendQueryDTO.getAmountOfGoods().intValue());
			 Assert.assertEquals(expected.getId(), shoppingOrderExtendQueryDTO.getId());
			 Assert.assertEquals(expected.getFreightPrice().doubleValue(), shoppingOrderExtendQueryDTO.getFreightPrice().doubleValue(), 0D);
			 Assert.assertEquals(expected.getIsDone(), shoppingOrderExtendQueryDTO.getIsDone());
			 Assert.assertEquals(expected.getIsNeedsLogistics(), shoppingOrderExtendQueryDTO.getIsNeedsLogistics());
			 Assert.assertEquals(expected.getIsNoReasonReturn(), shoppingOrderExtendQueryDTO.getIsNoReasonReturn());
			 Assert.assertEquals(expected.getMerchantId(), shoppingOrderExtendQueryDTO.getMerchantId());
			 Assert.assertEquals(expected.getMerchantNum(), shoppingOrderExtendQueryDTO.getMerchantNum());
			 Assert.assertEquals(expected.getMerchantStoreId(), shoppingOrderExtendQueryDTO.getMerchantStoreId());
			 Assert.assertEquals(expected.getOrderStatus(), shoppingOrderExtendQueryDTO.getOrderStatus().getValue());
			 Assert.assertEquals(expected.getOrderTotalPrice().doubleValue(), shoppingOrderExtendQueryDTO.getOrderTotalPrice().doubleValue(), 0D);
			 for (ShoppingOrderItemDTO item : shoppingOrderExtendQueryDTO.getItems()) {
				Assert.assertEquals(shoppingOrderItemDO.getId(), item.getId());
				Assert.assertEquals(shoppingOrderItemDO.getIsAllowRefund(), item.getIsAllowRefund());
				Assert.assertEquals(shoppingOrderItemDO.getIsEvaluation(), item.getIsEvaluation());
				Assert.assertEquals(shoppingOrderItemDO.getOrderStatus(), item.getOrderStatus().getValue());
				Assert.assertEquals(shoppingOrderItemDO.getProductFeatureImage(), item.getProductFeatureImage());
				Assert.assertEquals(shoppingOrderItemDO.getProductId(), item.getProductId());
				Assert.assertEquals(shoppingOrderItemDO.getProductModelId(), item.getProductModelId());
				Assert.assertEquals(shoppingOrderItemDO.getProductModelName(), item.getProductModelName());
				Assert.assertEquals(shoppingOrderItemDO.getProductName(), item.getProductName());
				Assert.assertEquals(shoppingOrderItemDO.getQuantity(), item.getQuantity());
				Assert.assertEquals(shoppingOrderItemDO.getRefundStatus(), item.getRefundStatus() != null ? item.getRefundStatus().getValue() : null);
				Assert.assertEquals(shoppingOrderItemDO.getRegularPrice().doubleValue(), item.getRegularPrice().doubleValue(), 0D);
			 }
    	}
    }
    
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void cancelOrder() throws Exception {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingOrder/cancelOrder/" + expected.getMemberId() + "/" + expected.getId());
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
    	ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue(), actual.getOrderStatus());
    	ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertEquals(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    }
    
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void deleteOrder() throws Exception {
		/*
		 * 插入一条订单记录
		 * 是商家粉丝/需要物流/七天无理由退货/待支付/未计算提成/未完成/交易取消/有效
		 */
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingOrder/deleteOrder/" + expected.getMemberId() + "/" + expected.getId());
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
    	ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(StatusEnum.INVALID.getValue(), actual.getStatus());
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getExpressInfo() throws Exception {
		/*
		 * 插入一条订单记录
		 * 是粉丝/需要物流/七天无理由退货/未完成/未计算提成/待收货
		 */
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setSendTime(0);
    	expected.setExpressCompanyCode("SF");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("顺丰速递");
    	expected.setWaybillNum("123456");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransport(new Date());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	// 用户调用
    	if (true) {
	    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/getExpressInfo/" + expected.getId())
	    			.param("memberId", expected.getMemberId().toString());
	        ResultActions perform = mvc.perform(request);
	        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
	        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
	        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
	        		.andDo(MockMvcResultHandlers.print())
	        		.andReturn();
	        
	        ShoppingOrderExpressDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderExpressDTO.class);
	        Assert.assertNotNull(actual);
	        Assert.assertEquals(expected.getExpressCompanyName(), actual.getExpressCompanyName());
	        Assert.assertEquals(expected.getWaybillNum(), actual.getWaybillNum());
	        ExpressInquiriesDetailDTO actualExpressInquiriesDetailDTO = actual.getExpressInquiriesDetailDTO();
	        Assert.assertNotNull(actualExpressInquiriesDetailDTO);
    	}
    	
    	// 商家调用
        if (true) {
	        RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/getExpressInfo/" + expected.getId())
	    			.param("merchantId", expected.getMerchantId().toString());
	        ResultActions perform = mvc.perform(request);
	        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
	        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
	        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
	        		.andDo(MockMvcResultHandlers.print())
	        		.andReturn();
	        
	        ShoppingOrderExpressDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderExpressDTO.class);
	        Assert.assertNotNull(actual);
	        Assert.assertEquals(expected.getExpressCompanyName(), actual.getExpressCompanyName());
	        Assert.assertEquals(expected.getWaybillNum(), actual.getWaybillNum());
	        ExpressInquiriesDetailDTO actualExpressInquiriesDetailDTO = actual.getExpressInquiriesDetailDTO();
	        Assert.assertNotNull(actualExpressInquiriesDetailDTO);
        }
    }
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectRefundPageByMemberId() throws Exception {
		/*
		 * 插入一条订单记录
		 * 是粉丝/需要物流/七天无理由退货/未完成/未计算提成/待收货/余额支付
		 */
		ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	expected.setExpressCompanyCode("SF");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("顺丰速递");
    	expected.setWaybillNum("123456");
    	expected.setGmtPayment(new Date());
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
		/*
		 * 插入一条订单项记录
		 * 待收货/未评价/允许退货
		 */
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.FILL_RETURN_ADDRESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
		/*
		 * 插入一条订单项记录
		 * 退货退款/有效
		 */
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	ShoppingRefundQueryForeignParam param = new ShoppingRefundQueryForeignParam();
    	param.setCurrentPage(1);
    	param.setKeyword(expected.getOrderNum());
    	param.setPageSize(10);
    	String content = JSONObject.toJSONString(param);
    	
    	RequestBuilder request = MockMvcRequestBuilders.post("/shoppingOrder/selectRefundPageByMemberId/" + expected.getMemberId()).contentType(MediaType.APPLICATION_JSON_UTF8).content(content);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        Page<JSONObject> page = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), Page.class);
        Assert.assertNotNull(page);
        Assert.assertEquals(param.getCurrentPage(), page.getCurrentPage());
    	Assert.assertEquals(1, page.getTotalCount().intValue());
    	ShoppingOrderItemRefundDTO actual = JSONObject.parseObject(page.getRecords().get(0).toString(), ShoppingOrderItemRefundDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getMerchantName(), actual.getMerchantName());
        Assert.assertEquals(shoppingRefundDetailDO.getAmount().doubleValue(), actual.getAmount().doubleValue(), 0D);
        Assert.assertEquals(shoppingOrderItemDO.getId(), actual.getId());
        Assert.assertEquals(expected.getIsNoReasonReturn(), actual.getIsNoReasonReturn());
        Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
        Assert.assertEquals(expected.getMerchantStoreId(), actual.getMerchantStoreId());
        Assert.assertEquals(shoppingOrderItemDO.getProductFeatureImage(), actual.getProductFeatureImage());
        Assert.assertEquals(shoppingOrderItemDO.getProductModelName(), actual.getProductModelName());
        Assert.assertEquals(shoppingOrderItemDO.getProductName(), actual.getProductName());
        Assert.assertEquals(shoppingOrderItemDO.getQuantity(), actual.getQuantity());
        Assert.assertEquals(shoppingOrderItemDO.getRefundStatus(), actual.getRefundStatus().getValue());
        Assert.assertEquals(shoppingRefundDetailDO.getId(), actual.getShoppingRefundDetailId());
        Assert.assertEquals(shoppingRefundDetailDO.getType(), actual.getType().getValue());
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void requestRefund() throws Exception {
		/*
		 * 插入一条订单记录
		 * 是粉丝/需要物流/七天无理由退货/未完成/未计算提成/待收货/余额支付
		 */
		ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	expected.setExpressCompanyCode("SF");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("顺丰速递");
    	expected.setWaybillNum("123456");
    	expected.setGmtPayment(new Date());
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
		/*
		 * 插入一条订单项记录
		 * 待收货/未评价/允许退货
		 */
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderRequestRefundParam param = new ShoppingOrderRequestRefundParam();
    	param.setDescription("就是要退款");
    	param.setReason("七天无理由退款");
    	param.setType(ShoppingRefundTypeEnum.REFUND);
    	String content = JSONObject.toJSONString(param);
    	
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingOrder/requestRefund/" + shoppingOrderItemDO.getId())
    			.param("memberId", expected.getMemberId().toString())
    			.contentType(MediaType.APPLICATION_JSON_UTF8).content(content);
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderItemDO actual = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(ShoppingOrderStatusEnum.REFUNDING.getValue(), actual.getOrderStatus());
    	Assert.assertEquals(RefundStatusEnum.TO_BE_CONFIRMED.getValue(), actual.getRefundStatus());
    	
    	ShoppingRefundDetailDOExample example = new ShoppingRefundDetailDOExample();
    	example.createCriteria().andShoppingOrderItemIdEqualTo(shoppingOrderItemDO.getId());
    	ShoppingRefundDetailDO actualShoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByExample(example).get(0);
    	Assert.assertNotNull(actualShoppingRefundDetailDO);
    	Assert.assertEquals(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())).doubleValue(), actualShoppingRefundDetailDO.getAmount().doubleValue(), 0D);
    	Assert.assertEquals(param.getReason(), actualShoppingRefundDetailDO.getReason());
    	Assert.assertEquals(param.getDescription(), actualShoppingRefundDetailDO.getDescription());
    	Assert.assertEquals(param.getType().getValue(), actualShoppingRefundDetailDO.getType());
    	Assert.assertEquals(StatusEnum.VALID.getValue(), actualShoppingRefundDetailDO.getStatus());
    	
    	ShoppingRefundProcessDOExample shoppingRefundProcessDOExample = new ShoppingRefundProcessDOExample();
    	shoppingRefundProcessDOExample.createCriteria().andShoppingRefundDetailIdEqualTo(actualShoppingRefundDetailDO.getId());
    	ShoppingRefundProcessDO shoppingRefundProcessDO = shoppingRefundProcessDOMapper.selectByExample(shoppingRefundProcessDOExample).get(0);
    	Assert.assertNotNull(shoppingRefundProcessDO);
    	Assert.assertNotNull(shoppingRefundProcessDO.getGmtCreate());
    	Assert.assertEquals(actualShoppingRefundDetailDO.getId(), shoppingRefundProcessDO.getShoppingRefundDetailId());
    	Assert.assertEquals(actual.getRefundStatus(), shoppingRefundProcessDO.getRefundStatus());
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void orderPayment() throws Exception {
		/*
		 * 插入一条订单记录
		 * 是粉丝/需要物流/七天无理由退货/未完成/未计算提成/待支付
		 */
		ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
		/*
		 * 插入一条订单项记录
		 * 待收货/未评价/允许退货
		 */
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/orderPayment/" + expected.getId()).param("memberId", expected.getMemberId().toString());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderPaymentDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderPaymentDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
        Assert.assertEquals(expected.getOrderTotalPrice().doubleValue(), actual.getOrderTotalPrice().doubleValue(), 0D);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void tradingSuccess() throws Exception {
		/*
		 * 插入一条订单记录
		 * 是粉丝/需要物流/七天无理由退货/未完成/未计算提成/待收货/余额支付
		 */
		ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setGmtPayment(new Date());
    	expected.setExpressCompanyCode("SF");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("顺丰速递");
    	expected.setWaybillNum("123456");
    	expected.setGmtTransport(new Date());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
		/*
		 * 插入一条订单项记录
		 * 待收货/未评价/允许退货
		 */
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	/*
    	 * 查询一条退款中的订单项记录
    	 * 允许退款/未评论/退款中/待确认 
    	 */
    	ShoppingOrderItemDO refundShoppingOrderItemDO = new ShoppingOrderItemDO();
    	refundShoppingOrderItemDO.setGmtCreate(new Date());
    	refundShoppingOrderItemDO.setGmtModified(new Date());
    	refundShoppingOrderItemDO.setIsAllowRefund(true);
    	refundShoppingOrderItemDO.setIsEvaluation(false);
    	refundShoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
    	refundShoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	refundShoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	refundShoppingOrderItemDO.setProductId(1L);
    	refundShoppingOrderItemDO.setProductName("productName");
    	refundShoppingOrderItemDO.setProductModelId(1L);
    	refundShoppingOrderItemDO.setProductModelName("test");
    	refundShoppingOrderItemDO.setQuantity(1);
    	refundShoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	refundShoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	refundShoppingOrderItemDO.setSendTime(0);
    	refundShoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(refundShoppingOrderItemDO);
    	
    	/*
    	 * 查询一条退款详情记录
    	 * 退款/有效
    	 */
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setAmount(refundShoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(refundShoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.REFUND.getValue());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(refundShoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingOrder/tradingSuccess/" + expected.getId()).param("memberId", expected.getMemberId().toString());
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(false, actual.getIsAutomaticReceipt());
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actual.getOrderStatus());
    	
    	ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    	
    	actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(refundShoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertNull(actualShoppingOrderItemDO.getRefundStatus());
    	Assert.assertEquals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    	
    	ShoppingRefundDetailDO actualShoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getId());
    	Assert.assertNotNull(actualShoppingRefundDetailDO);
    	Assert.assertEquals(StatusEnum.INVALID.getValue(), actualShoppingRefundDetailDO.getStatus());
    }
	
    @SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectPageByMerchantId() throws Exception {
    	/*
    	 * 插入一条订单记录
    	 * 是粉丝/需要物流/七天无理由退货/交易成功/未计算提成/有效/余额支付/已完成
    	 */
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("YD");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	/*
    	 * 查询一条订单项记录
    	 * 交易成功
    	 */
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderQueryForeignToMerchantParam param = new ShoppingOrderQueryForeignToMerchantParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	param.setOrderStatus(ShoppingOrderStatusToMerchantEnum.COMPLETED);
    	param.setKeyword(expected.getOrderNum());
    	
    	String content = JSONObject.toJSONString(param);
    	RequestBuilder request = MockMvcRequestBuilders.post("/shoppingOrder/selectPageByMerchantId/" + expected.getMemberId()).contentType(MediaType.APPLICATION_JSON).content(content);    	
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        Page<JSONObject> page = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), Page.class);
        Assert.assertNotNull(page);
        Assert.assertEquals(param.getCurrentPage(), page.getCurrentPage());
    	Assert.assertEquals(1, page.getTotalCount().intValue());
    	
		ParserConfig mapping = new ParserConfig();
    	mapping.putDeserializer(Date.class, new JCDateDeserializer());
    	ShoppingOrderQueryToMerchantDTO actual = JSONObject.parseObject(page.getRecords().get(0).toJSONString(), ShoppingOrderQueryToMerchantDTO.class, mapping, JSON.DEFAULT_PARSER_FEATURE);
		
    	Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getConsigneeName(), actual.getConsigneeName());
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
		Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus().getValue());
		Assert.assertEquals(shoppingOrderItemDO.getProductFeatureImage(), actual.getProductFeatureImage());
		Assert.assertEquals(shoppingOrderItemDO.getGmtCreate().getTime(), actual.getGmtCreate().getTime(), 24*60*60*1000);
    }
    
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void fillLogisticsInformation() throws Exception {
		/* 需要物流 */
		if (true) {
	    	/*
	    	 * 插入一条订单记录
	    	 * 是粉丝/需要物流/七天无理由退货/待发货/未计算提成/有效/余额支付/已完成
	    	 */
	    	ShoppingOrderDO expected  = new ShoppingOrderDO();
	    	expected.setCommodityTotalPrice(new BigDecimal(1));
	    	expected.setActualAmount(new BigDecimal(1));
	    	expected.setFreightPrice(new BigDecimal(0));
	    	expected.setGmtCreate(new Date());
	    	expected.setGmtModified(new Date());
	    	expected.setIsFans(true);
	    	expected.setIsNeedsLogistics(true);
	    	expected.setIsNoReasonReturn(true);
	    	expected.setMemberId(1L);
	    	expected.setMemberNum("M0001");
	    	expected.setMerchantId(1L);
	    	expected.setMerchantName("拉乌网络");
	    	expected.setMerchantStoreId(1L);
	    	expected.setMerchantNum("B0001");
	    	expected.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
	    	expected.setOrderTotalPrice(new BigDecimal(1));
	    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
	    	expected.setStatus(StatusEnum.VALID.getValue());
	    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
	    	expected.setConsigneeAddress("大冲商务中心1301");
	    	expected.setConsigneeMobile("123456");
	    	expected.setConsigneeName("Sunny");
	    	expected.setGmtPayment(new Date());
	    	expected.setIsDone(false);
	    	expected.setShoppingCartIdsStr("1,2");
	    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
	    	expected.setSendTime(0);
	    	shoppingOrderDOMapper.insertSelective(expected);
	    	
	    	/*
	    	 * 查询一条订单项记录
	    	 * 待收货
	    	 */
	    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
	    	shoppingOrderItemDO.setGmtCreate(new Date());
	    	shoppingOrderItemDO.setGmtModified(new Date());
	    	shoppingOrderItemDO.setIsAllowRefund(true);
	    	shoppingOrderItemDO.setIsEvaluation(false);
	    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
	    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
	    	shoppingOrderItemDO.setProductId(1L);
	    	shoppingOrderItemDO.setProductName("productName");
	    	shoppingOrderItemDO.setProductModelId(1L);
	    	shoppingOrderItemDO.setProductModelName("test");
	    	shoppingOrderItemDO.setQuantity(1);
	    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
	    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
	    	shoppingOrderItemDO.setSendTime(0);
	    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
	    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
	    	
	    	/*
	    	 * 查询一条订单项记录
	    	 * 退款中/待退款
	    	 */
	    	ShoppingOrderItemDO refundShoppingOrderItemDO = new ShoppingOrderItemDO();
	    	refundShoppingOrderItemDO.setGmtCreate(new Date());
	    	refundShoppingOrderItemDO.setGmtModified(new Date());
	    	refundShoppingOrderItemDO.setIsAllowRefund(true);
	    	refundShoppingOrderItemDO.setIsEvaluation(false);
	    	refundShoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
	    	refundShoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
	    	refundShoppingOrderItemDO.setProductFeatureImage("test.jpg");
	    	refundShoppingOrderItemDO.setProductId(1L);
	    	refundShoppingOrderItemDO.setProductName("productName");
	    	refundShoppingOrderItemDO.setProductModelId(1L);
	    	refundShoppingOrderItemDO.setProductModelName("test");
	    	refundShoppingOrderItemDO.setQuantity(1);
	    	refundShoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
	    	refundShoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
	    	refundShoppingOrderItemDO.setSendTime(0);
	    	refundShoppingOrderItemDO.setShoppingOrderId(expected.getId());
	    	shoppingOrderItemDOMapper.insert(refundShoppingOrderItemDO);
	    	
	    	/*
	    	 * 查询一条退款详情记录
	    	 * 退款/有效
	    	 */
	    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
	    	shoppingRefundDetailDO.setAmount(refundShoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(refundShoppingOrderItemDO.getQuantity())));
	    	shoppingRefundDetailDO.setDescription("就是想退款");
	    	shoppingRefundDetailDO.setGmtModified(new Date());
	    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.REFUND.getValue());
	    	shoppingRefundDetailDO.setGmtCreate(new Date());
	    	shoppingRefundDetailDO.setReason("七天无理由退货");
	    	shoppingRefundDetailDO.setShoppingOrderItemId(refundShoppingOrderItemDO.getId());
	    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
	    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
	    	
	    	ShoppingOrderLogisticsInformationParam param = new ShoppingOrderLogisticsInformationParam();
	    	param.setExpressCompanyCode("SF");
	    	param.setExpressCompanyId(1);
	    	param.setExpressCompanyName("顺丰速递");
	    	param.setWaybillNum("123456");
	    	param.setIsNeedsLogistics(true);
	    	
	    	String content = JSONObject.toJSONString(param);
	    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingOrder/fillLogisticsInformation/" + expected.getId()).param("merchantId", expected.getMerchantId().toString()).contentType(MediaType.APPLICATION_JSON).content(content);
	        ResultActions perform = mvc.perform(request);
	        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
	        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
	        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
	        		.andDo(MockMvcResultHandlers.print())
	        		.andReturn();
	        
	        ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
	        Assert.assertNotNull(actual);
	        Assert.assertNotNull(actual.getGmtTransport());
	        Assert.assertEquals(true, actual.getIsNeedsLogistics());
	        Assert.assertEquals(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue(), actual.getOrderStatus());
	        Assert.assertEquals(param.getExpressCompanyCode(), actual.getExpressCompanyCode());
	        Assert.assertEquals(param.getExpressCompanyId(), actual.getExpressCompanyId());
	        Assert.assertEquals(param.getExpressCompanyName(), actual.getExpressCompanyName());
	        Assert.assertEquals(param.getWaybillNum(), actual.getWaybillNum());
	        
	        ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
	        Assert.assertNotNull(actualShoppingOrderItemDO);
	        Assert.assertEquals(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue(), actualShoppingOrderItemDO.getOrderStatus());
	        
	        ShoppingOrderItemDO actualRefundShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(refundShoppingOrderItemDO.getId());
	        Assert.assertNotNull(actualRefundShoppingOrderItemDO);
	        Assert.assertEquals(ShoppingOrderStatusEnum.REFUNDING.getValue(), actualRefundShoppingOrderItemDO.getOrderStatus());
		}
		
		/* 无需物流 */
		if (true) {
			/*
	    	 * 插入一条订单记录
	    	 * 是粉丝/需要物流/七天无理由退货/待发货/未计算提成/有效/余额支付/已完成
	    	 */
	    	ShoppingOrderDO expected  = new ShoppingOrderDO();
	    	expected.setCommodityTotalPrice(new BigDecimal(1));
	    	expected.setActualAmount(new BigDecimal(1));
	    	expected.setFreightPrice(new BigDecimal(0));
	    	expected.setGmtCreate(new Date());
	    	expected.setGmtModified(new Date());
	    	expected.setIsFans(true);
	    	expected.setIsNeedsLogistics(true);
	    	expected.setIsNoReasonReturn(true);
	    	expected.setMemberId(1L);
	    	expected.setMemberNum("M0001");
	    	expected.setMerchantId(1L);
	    	expected.setMerchantName("拉乌网络");
	    	expected.setMerchantStoreId(1L);
	    	expected.setMerchantNum("B0001");
	    	expected.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
	    	expected.setOrderTotalPrice(new BigDecimal(1));
	    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
	    	expected.setStatus(StatusEnum.VALID.getValue());
	    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
	    	expected.setConsigneeAddress("大冲商务中心1301");
	    	expected.setConsigneeMobile("123456");
	    	expected.setConsigneeName("Sunny");
	    	expected.setGmtPayment(new Date());
	    	expected.setIsDone(false);
	    	expected.setShoppingCartIdsStr("1,2");
	    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
	    	expected.setSendTime(0);
	    	shoppingOrderDOMapper.insertSelective(expected);
	    	
	    	/*
	    	 * 查询一条订单项记录
	    	 * 待收货
	    	 */
	    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
	    	shoppingOrderItemDO.setGmtCreate(new Date());
	    	shoppingOrderItemDO.setGmtModified(new Date());
	    	shoppingOrderItemDO.setIsAllowRefund(true);
	    	shoppingOrderItemDO.setIsEvaluation(false);
	    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
	    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
	    	shoppingOrderItemDO.setProductId(1L);
	    	shoppingOrderItemDO.setProductName("productName");
	    	shoppingOrderItemDO.setProductModelId(1L);
	    	shoppingOrderItemDO.setProductModelName("test");
	    	shoppingOrderItemDO.setQuantity(1);
	    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
	    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
	    	shoppingOrderItemDO.setSendTime(0);
	    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
	    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
	    	
	    	/*
	    	 * 查询一条订单项记录
	    	 * 退款中/待退款
	    	 */
	    	ShoppingOrderItemDO refundShoppingOrderItemDO = new ShoppingOrderItemDO();
	    	refundShoppingOrderItemDO.setGmtCreate(new Date());
	    	refundShoppingOrderItemDO.setGmtModified(new Date());
	    	refundShoppingOrderItemDO.setIsAllowRefund(true);
	    	refundShoppingOrderItemDO.setIsEvaluation(false);
	    	refundShoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
	    	refundShoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
	    	refundShoppingOrderItemDO.setProductFeatureImage("test.jpg");
	    	refundShoppingOrderItemDO.setProductId(1L);
	    	refundShoppingOrderItemDO.setProductName("productName");
	    	refundShoppingOrderItemDO.setProductModelId(1L);
	    	refundShoppingOrderItemDO.setProductModelName("test");
	    	refundShoppingOrderItemDO.setQuantity(1);
	    	refundShoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
	    	refundShoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
	    	refundShoppingOrderItemDO.setSendTime(0);
	    	refundShoppingOrderItemDO.setShoppingOrderId(expected.getId());
	    	shoppingOrderItemDOMapper.insert(refundShoppingOrderItemDO);
	    	
	    	/*
	    	 * 查询一条退款详情记录
	    	 * 退款/有效
	    	 */
	    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
	    	shoppingRefundDetailDO.setAmount(refundShoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(refundShoppingOrderItemDO.getQuantity())));
	    	shoppingRefundDetailDO.setDescription("就是想退款");
	    	shoppingRefundDetailDO.setGmtModified(new Date());
	    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.REFUND.getValue());
	    	shoppingRefundDetailDO.setGmtCreate(new Date());
	    	shoppingRefundDetailDO.setReason("七天无理由退货");
	    	shoppingRefundDetailDO.setShoppingOrderItemId(refundShoppingOrderItemDO.getId());
	    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
	    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
	    	
	    	ShoppingOrderLogisticsInformationParam param = new ShoppingOrderLogisticsInformationParam();
	    	param.setIsNeedsLogistics(false);
	    	
	    	String content = JSONObject.toJSONString(param);
	    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingOrder/fillLogisticsInformation/" + expected.getId()).param("merchantId", expected.getMerchantId().toString()).contentType(MediaType.APPLICATION_JSON).content(content);    	
	        ResultActions perform = mvc.perform(request);
	        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
	        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
	        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
	        		.andDo(MockMvcResultHandlers.print())
	        		.andReturn();
	        
	        ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
	        Assert.assertNotNull(actual);
	        Assert.assertNull(param.getExpressCompanyCode());
	        Assert.assertNull(param.getExpressCompanyId());
	        Assert.assertNull(param.getExpressCompanyName());
	        Assert.assertNull(actual.getWaybillNum());
	        Assert.assertNotNull(actual.getGmtTransport());
	        Assert.assertEquals(false, actual.getIsNeedsLogistics());
	        Assert.assertEquals(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue(), actual.getOrderStatus());
	        
	        ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
	        Assert.assertNotNull(actualShoppingOrderItemDO);
	        Assert.assertEquals(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue(), actualShoppingOrderItemDO.getOrderStatus());
	        
	        ShoppingOrderItemDO actualRefundShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(refundShoppingOrderItemDO.getId());
	        Assert.assertNotNull(actualRefundShoppingOrderItemDO);
	        Assert.assertEquals(ShoppingOrderStatusEnum.REFUNDING.getValue(), actualRefundShoppingOrderItemDO.getOrderStatus());
		}
    }
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectRefundPageByMerchantId() throws Exception {
		/*
		 * 插入一条订单记录
		 * 是粉丝/需要物流/七天无理由退货/未完成/未计算提成/待收货/余额支付
		 */
		ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	expected.setExpressCompanyCode("SF");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("顺丰速递");
    	expected.setWaybillNum("123456");
    	expected.setGmtPayment(new Date());
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
		/*
		 * 插入一条订单项记录
		 * 待收货/未评价/允许退货
		 */
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.FILL_RETURN_ADDRESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
		/*
		 * 插入一条订单项记录
		 * 退货退款/有效
		 */
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	ShoppingRefundQueryForeignParam param = new ShoppingRefundQueryForeignParam();
    	param.setCurrentPage(1);
    	param.setKeyword(expected.getOrderNum());
    	param.setPageSize(10);
    	String content = JSONObject.toJSONString(param);
    	
    	RequestBuilder request = MockMvcRequestBuilders.post("/shoppingOrder/selectRefundPageByMerchantId/" + expected.getMerchantId()).contentType(MediaType.APPLICATION_JSON_UTF8).content(content);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        Page<JSONObject> page = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), Page.class);
        Assert.assertNotNull(page);
        Assert.assertEquals(param.getCurrentPage(), page.getCurrentPage());
    	Assert.assertEquals(1, page.getTotalCount().intValue());
    	
    	ParserConfig mapping = new ParserConfig();
    	mapping.putDeserializer(Date.class, new JCDateDeserializer("yyyy-MM-dd HH:mm:ss"));
    	ShoppingOrderItemRefundForMerchantDTO actual = JSONObject.parseObject(page.getRecords().get(0).toJSONString(), ShoppingOrderItemRefundForMerchantDTO.class, mapping, JSON.DEFAULT_PARSER_FEATURE);
        Assert.assertNotNull(actual);
        Assert.assertEquals(shoppingOrderItemDO.getId(), actual.getId());
        Assert.assertEquals(shoppingOrderItemDO.getProductFeatureImage(), actual.getProductFeatureImage());
        Assert.assertEquals(shoppingOrderItemDO.getProductModelName(), actual.getProductModelName());
        Assert.assertEquals(shoppingOrderItemDO.getProductName(), actual.getProductName());
        Assert.assertEquals(shoppingOrderItemDO.getRefundStatus(), actual.getRefundStatus().getValue());
        Assert.assertEquals(shoppingRefundDetailDO.getId(), actual.getShoppingRefundDetailId());
        Assert.assertEquals(shoppingRefundDetailDO.getGmtCreate().getTime(), actual.getGmtCreate().getTime(), 1000);
        Assert.assertEquals(expected.getConsigneeName(), actual.getConsigneeName());
    }
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectPage() throws Exception {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingOrderQueryForeignToOperatorParam param = new ShoppingOrderQueryForeignToOperatorParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	param.setKeyword(expected.getOrderNum());
    	param.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS);
    	param.setSortName(ShoppingOrderSortEnum.gmtCreate);
    	param.setSortOrder(SortOrderEnum.asc);
    	String content = JSONObject.toJSONString(param);
    	
    	RequestBuilder request = MockMvcRequestBuilders.post("/shoppingOrder/selectPage").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        Page<JSONObject> page = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), Page.class);
        Assert.assertNotNull(page);
        Assert.assertEquals(param.getCurrentPage(), page.getCurrentPage());
    	Assert.assertEquals(1, page.getTotalCount().intValue());
    	
    	ParserConfig mapping = new ParserConfig();
    	mapping.putDeserializer(Date.class, new JCDateDeserializer("yyyy-MM-dd"));
    	ShoppingOrderQueryToOperatorDTO actual = JSONObject.parseObject(page.getRecords().get(0).toJSONString(), ShoppingOrderQueryToOperatorDTO.class, mapping, JSON.DEFAULT_PARSER_FEATURE);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus().getValue());
		Assert.assertEquals(expected.getGmtCreate().getTime(), actual.getGmtCreate().getTime(), 24*60*60*1000);
		Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
		Assert.assertEquals(shoppingOrderItemDO.getProductFeatureImage(), actual.getProductFeatureImage());
		Assert.assertEquals(expected.getConsigneeName(), actual.getConsigneeName());
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateInformation() throws Exception {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("YD");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderUpdateInfomationParam param = new ShoppingOrderUpdateInfomationParam();
    	param.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION);
    	param.setConsigneeAddress("深圳市南山区大冲商务中心");
    	param.setConsigneeMobile("15616175989");
    	param.setConsigneeName("John");
    	param.setExpressCompanyCode("SF");
    	param.setExpressCompanyId(2);
    	param.setExpressCompanyName("顺丰");
    	param.setWaybillNum("123456");
    	String content = JSONObject.toJSONString(param);
    	
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingOrder/updateInformation/" + expected.getId()).contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
        Assert.assertNotNull(actual);
        Assert.assertEquals(param.getConsigneeAddress(), actual.getConsigneeAddress());
        Assert.assertEquals(param.getConsigneeMobile(), actual.getConsigneeMobile());
        Assert.assertEquals(param.getConsigneeName(), actual.getConsigneeName());
        Assert.assertEquals(param.getExpressCompanyCode(), actual.getExpressCompanyCode());
        Assert.assertEquals(param.getWaybillNum(), actual.getWaybillNum());
        Assert.assertEquals(param.getExpressCompanyName(), actual.getExpressCompanyName());
        Assert.assertEquals(param.getExpressCompanyId(), actual.getExpressCompanyId());
        Assert.assertEquals(param.getOrderStatus().getValue(), actual.getOrderStatus());
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void numberOfOrderStartusByMerchant() throws Exception {
    	ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("YD");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/numberOfOrderStartusByMerchant/" + expected.getMerchantId());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(0L, actual.getBeShippedCount().longValue());
        Assert.assertEquals(0L, actual.getPendingPaymentCount().longValue());
        Assert.assertEquals(0L, actual.getRefundingCount().longValue());
        Assert.assertEquals(1L, actual.getToBeReceivedCount().longValue());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectRefundPage() throws Exception {
		ShoppingOrderDO expected = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setIsDone(false);
    	expected.setShoppingCartIdsStr("1");
    	expected.setSendTime(0);
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	ShoppingRefundQueryForeignParam param = new ShoppingRefundQueryForeignParam();
    	param.setCurrentPage(1);
    	param.setPageSize(10);
    	param.setKeyword(expected.getOrderNum());
    	String content = JSONObject.toJSONString(param);
    	
    	RequestBuilder request = MockMvcRequestBuilders.post("/shoppingOrder/selectRefundPage").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        Page<JSONObject> page = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), Page.class);
        Assert.assertNotNull(page);
        Assert.assertEquals(param.getCurrentPage(), page.getCurrentPage());
    	Assert.assertEquals(1, page.getTotalCount().intValue());
        
    	ParserConfig mapping = new ParserConfig();
    	mapping.putDeserializer(Date.class, new JCDateDeserializer("yyyy-MM-dd HH:mm:ss"));
    	ShoppingOrderItemRefundForOperatorDTO actual = JSONObject.parseObject(page.getRecords().get(0).toJSONString(), ShoppingOrderItemRefundForOperatorDTO.class, mapping, JSON.DEFAULT_PARSER_FEATURE);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getConsigneeAddress(), actual.getConsigneeAddress());
		Assert.assertEquals(expected.getConsigneeMobile(), actual.getConsigneeMobile());
		Assert.assertEquals(expected.getConsigneeName(), actual.getConsigneeName());
		Assert.assertEquals(shoppingRefundDetailDO.getGmtCreate().getTime(), actual.getGmtCreate().getTime(), 1000);
		if (shoppingRefundDetailDO.getGmtIntervention() == null || actual.getGmtIntervention() == null) {
			Assert.assertEquals(shoppingRefundDetailDO.getGmtIntervention(), actual.getGmtIntervention());
		} else {
			Assert.assertEquals(shoppingRefundDetailDO.getGmtIntervention().getTime(), actual.getGmtIntervention().getTime(), 1000);
		}
		Assert.assertEquals(shoppingRefundDetailDO.getId(), actual.getShoppingRefundDetailId());
		Assert.assertEquals(shoppingOrderItemDO.getId(), actual.getId());
		Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
		Assert.assertEquals(shoppingOrderItemDO.getProductFeatureImage(), actual.getProductFeatureImage());
		Assert.assertEquals(shoppingOrderItemDO.getProductModelName(), actual.getProductModelName());
		Assert.assertEquals(shoppingOrderItemDO.getProductName(), actual.getProductName());
		Assert.assertEquals(shoppingOrderItemDO.getRefundStatus(), actual.getRefundStatus().getValue());
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void detail() throws Exception {
		ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	Map<Long, ShoppingOrderItemDO> expectedMap = new HashMap<>();
    	// 交易成功
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	expectedMap.put(shoppingOrderItemDO.getId(), shoppingOrderItemDO);
    	
    	// 退款成功
    	shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(2L);
    	shoppingOrderItemDO.setProductModelName("productModelName");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	expectedMap.put(shoppingOrderItemDO.getId(), shoppingOrderItemDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/detail/" + expected.getId()).param("memberId", expected.getMemberId().toString());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderDetailDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderDetailDTO.class);
        assertShoppingOrderDTO(expected, actual);
        Assert.assertEquals(null, actual.getCountdown());
        for (ShoppingOrderItemDTO actualShoppingOrderItemDTO : actual.getItems()) {
    		assertShoppingOrderItemBO(expectedMap.get(actualShoppingOrderItemDTO.getId()), actualShoppingOrderItemDTO);
    	}
	}
	
	@Deprecated
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void get() throws Exception {
		ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	Map<Long, ShoppingOrderItemDO> expectedMap = new HashMap<>();
    	// 交易成功
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	expectedMap.put(shoppingOrderItemDO.getId(), shoppingOrderItemDO);
    	
    	// 退款成功
    	shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(2L);
    	shoppingOrderItemDO.setProductModelName("productModelName");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	expectedMap.put(shoppingOrderItemDO.getId(), shoppingOrderItemDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/get/" + expected.getId()).param("memberId", expected.getMemberId().toString());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderDetailDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderDetailDTO.class);
        assertShoppingOrderDTO(expected, actual);
        Assert.assertEquals(null, actual.getCountdown());
        for (ShoppingOrderItemDTO actualShoppingOrderItemDTO : actual.getItems()) {
    		assertShoppingOrderItemBO(expectedMap.get(actualShoppingOrderItemDTO.getId()), actualShoppingOrderItemDTO);
    	}
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectOrderMoney() throws Exception {
		ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setIsDone(true);
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/selectOrderMoney").param("orderIds", expected.getId().toString());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderMoneyDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderMoneyDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getOrderTotalPrice().doubleValue(), actual.getOrderTotalPrice().doubleValue(), 0D);
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void expressInfo() throws Exception {
		ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/expressInfo/" + expected.getId()).param("memberId", expected.getMemberId().toString());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderExpressInfoDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderExpressInfoDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getExpressCompanyName(), actual.getExpressCompanyName());
        Assert.assertEquals(expected.getWaybillNum(), actual.getWaybillNum());
        Assert.assertEquals(expected.getExpressCompanyId(), actual.getExpressCompanyId());
        Assert.assertEquals(shoppingOrderItemDO.getProductFeatureImage(), actual.getProductFeatureImage());
        Assert.assertEquals(shoppingOrderItemDO.getQuantity(), actual.getTotalQuantity());
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getOrderCommentStatus() throws Exception {
		ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/getOrderCommentStatus/" + shoppingOrderItemDO.getId());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        CommentOrderDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), CommentOrderDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(shoppingOrderItemDO.getIsEvaluation(), actual.getEvaluation());
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void isNoOnGoingOrder() throws Exception {
		ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(true);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/isNoOnGoingOrder/" + expected.getMerchantId());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderIsNoOnGoingOrderDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderIsNoOnGoingOrderDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(true, actual.getIsNoOnGoingOrder());
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void numberOfOrderStartus() throws Exception {
		ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setGmtCommission(new Date());
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/numberOfOrderStartus/" + expected.getMemberId());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderNumberOfOrderStatusDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderNumberOfOrderStatusDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(0L, actual.getBeShippedCount().longValue());
        Assert.assertEquals(1L, actual.getEvaluationCount().longValue());
        Assert.assertEquals(0L, actual.getPendingPaymentCount().longValue());
        Assert.assertEquals(0L, actual.getToBeReceivedCount().longValue());
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void commissionShoppingOrder() throws Exception {
		ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
    	shoppingOrderItemDO.setGmtCreate(new Date());
    	shoppingOrderItemDO.setGmtModified(new Date());
    	shoppingOrderItemDO.setIsAllowRefund(true);
    	shoppingOrderItemDO.setIsEvaluation(false);
    	shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSendTime(0);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/commissionShoppingOrder").param("offset", "0").param("pageSize", "100");
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        List<ShoppingOrderCommissionDTO> list = JSONObject.parseArray(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderCommissionDTO.class);
        ShoppingOrderCommissionDTO actual = list.get(0);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getMemberNum(), actual.getMemberNum());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getMerchantNum(), actual.getMerchantNum());
        Assert.assertEquals(expected.getActualAmount().doubleValue(), actual.getActualAmount().doubleValue(), 0D);
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateCommissionStatus() throws Exception {
		ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingOrder/updateCommissionStatus").param("ids", expected.getId().toString());
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getGmtCommission());
        Assert.assertEquals(CommissionStatusEnum.CALCULATED.getValue(), actual.getCommissionStatus());
	}
	
	/**
	 * SQL语法不支持
	 * @throws Exception
	 * @author jiangxinjun
	 * @date 2017年9月11日
	 */
	@Ignore
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectByTransactionData() throws Exception {
		ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ReportDataParam param = new ReportDataParam();
    	param.setFlag(ReportFansRiseRateEnum.DAY);
    	param.setMerchantId(expected.getMemberId());
    	String content = JSONObject.toJSONString(param);
    	
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingOrder/selectByTransactionData").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ReportRiseRateDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ReportRiseRateDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getOrderTotalPrice(), actual.getTotal());
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void fansSaleTransform() throws Exception {
		ShoppingOrderDO expected  = new ShoppingOrderDO();
    	expected.setCommodityTotalPrice(new BigDecimal(1));
    	expected.setActualAmount(new BigDecimal(1));
    	expected.setFreightPrice(new BigDecimal(0));
    	expected.setGmtCreate(new Date());
    	expected.setGmtModified(new Date());
    	expected.setIsFans(true);
    	expected.setIsNeedsLogistics(true);
    	expected.setIsNoReasonReturn(true);
    	expected.setMemberId(1L);
    	expected.setMemberNum("M0001");
    	expected.setMerchantId(1L);
    	expected.setMerchantName("拉乌网络");
    	expected.setMerchantStoreId(1L);
    	expected.setMerchantNum("B0001");
    	expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
    	expected.setOrderTotalPrice(new BigDecimal(1));
    	expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
    	expected.setStatus(StatusEnum.VALID.getValue());
    	expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("韵达");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("韵达");
    	expected.setGmtPayment(new Date());
    	expected.setGmtTransaction(new Date());
    	expected.setGmtTransport(new Date());
    	expected.setIsDone(true);
    	expected.setThirdNumber("654321");
    	expected.setWaybillNum("3923440690592");
    	expected.setShoppingCartIdsStr("1,2");
    	expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
    	expected.setSendTime(0);
    	shoppingOrderDOMapper.insertSelective(expected);
    	
    	ReportDataParam param = new ReportDataParam();
    	param.setFlag(ReportFansRiseRateEnum.DAY);
    	param.setMerchantId(expected.getMerchantId());
    	String content = JSONObject.toJSONString(param);
    	
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingOrder/fansSaleTransform").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        List<ReportRiseRerouceDTO> list = JSONObject.parseArray(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ReportRiseRerouceDTO.class);
        Assert.assertNotNull(list);
    	for (ReportRiseRerouceDTO actual : list) {
    		if ("is_fans".equals(actual.getName())) {
    			Assert.assertEquals("1", actual.getValue());
    		}
    		if ("no_fans".equals(actual.getName())) {
    			Assert.assertEquals("0", actual.getValue());
    		}
    	}
	}
	
	public static void assertShoppingOrderDTO(ShoppingOrderDO expected, ShoppingOrderDetailDTO actual){
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getActualAmount().doubleValue(), actual.getActualAmount().doubleValue(), 0D);
    	Assert.assertEquals(expected.getCommodityTotalPrice().doubleValue(), actual.getCommodityTotalPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getConsigneeAddress(), actual.getConsigneeAddress());
    	Assert.assertEquals(expected.getConsigneeMobile(), actual.getConsigneeMobile());
    	Assert.assertEquals(expected.getConsigneeName(), actual.getConsigneeName());
    	Assert.assertEquals(expected.getExpressCompanyId(), actual.getExpressCompanyId());
    	Assert.assertEquals(expected.getExpressCompanyName(), actual.getExpressCompanyName());
    	Assert.assertEquals(expected.getFreightPrice().doubleValue(), actual.getFreightPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getGmtCreate().getTime(), actual.getGmtCreate().getTime());
    	Assert.assertEquals(expected.getGmtPayment() != null ? expected.getGmtPayment().getTime() : null, actual.getGmtPayment() != null ? actual.getGmtPayment().getTime() : null);
    	Assert.assertEquals(expected.getId(), actual.getId());
    	Assert.assertEquals(expected.getIsDone(), actual.getIsDone());
    	Assert.assertEquals(expected.getIsNeedsLogistics(), actual.getIsNeedsLogistics());
    	Assert.assertEquals(expected.getIsNoReasonReturn(), actual.getIsNoReasonReturn());
    	Assert.assertEquals(expected.getMemberNum(), actual.getMemberNum());
    	Assert.assertEquals(expected.getMerchantId(), actual.getMerchantId());
    	Assert.assertEquals(expected.getMerchantName(), actual.getMerchantName());
    	Assert.assertEquals(expected.getMerchantNum(), actual.getMerchantNum());
    	Assert.assertEquals(expected.getMerchantStoreId(), actual.getMerchantStoreId());
    	Assert.assertEquals(expected.getOrderNum(), actual.getOrderNum());
    	Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus().getValue());
    	Assert.assertEquals(expected.getOrderTotalPrice().doubleValue(), actual.getOrderTotalPrice().doubleValue(), 0D);
    	Assert.assertEquals(expected.getPaymentMethod(), actual.getPaymentMethod() != null ? actual.getPaymentMethod().getVal() : null);
    	Assert.assertEquals(expected.getWaybillNum(), actual.getWaybillNum());
    	Assert.assertEquals(expected.getGmtTransaction() != null ? expected.getGmtTransaction().getTime() : null, expected.getGmtTransaction() != null ? actual.getGmtTransaction().getTime() : null);
    	Assert.assertEquals(expected.getGmtTransport() != null ? expected.getGmtTransport().getTime() : null, actual.getGmtTransport() != null ? actual.getGmtTransport().getTime() : null);
    }
	
	public static void assertShoppingOrderItemBO(ShoppingOrderItemDO expected, ShoppingOrderItemDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getIsAllowRefund(), actual.getIsAllowRefund());
		Assert.assertEquals(expected.getIsEvaluation(), actual.getIsEvaluation());
		Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus().getValue());
		Assert.assertEquals(expected.getProductFeatureImage(), actual.getProductFeatureImage());
		Assert.assertEquals(expected.getProductId(), actual.getProductId());
		Assert.assertEquals(expected.getProductModelId(), actual.getProductModelId());
		Assert.assertEquals(expected.getProductModelName(), actual.getProductModelName());
		Assert.assertEquals(expected.getProductName(), actual.getProductName());
		Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
		Assert.assertEquals(expected.getRefundStatus(), actual.getRefundStatus() != null ? actual.getRefundStatus().getValue() : null);
		Assert.assertEquals(expected.getRegularPrice().doubleValue(), actual.getRegularPrice().doubleValue(), 0D);
	}
	
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void cancelOrderById() throws Exception {
        ShoppingOrderDO expected  = new ShoppingOrderDO();
        expected.setCommodityTotalPrice(new BigDecimal(1));
        expected.setActualAmount(new BigDecimal(1));
        expected.setFreightPrice(new BigDecimal(0));
        expected.setGmtCreate(new Date());
        expected.setGmtModified(new Date());
        expected.setIsFans(true);
        expected.setIsNeedsLogistics(true);
        expected.setIsNoReasonReturn(true);
        expected.setMemberId(1L);
        expected.setMemberNum("M0001");
        expected.setMerchantId(1L);
        expected.setMerchantName("拉乌网络");
        expected.setMerchantStoreId(1L);
        expected.setMerchantNum("B0001");
        expected.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        expected.setOrderTotalPrice(new BigDecimal(1));
        expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        expected.setStatus(StatusEnum.VALID.getValue());
        expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        expected.setConsigneeAddress("大冲商务中心1301");
        expected.setConsigneeMobile("123456");
        expected.setConsigneeName("Sunny");
        expected.setIsDone(false);
        expected.setShoppingCartIdsStr("1,2");
        expected.setSendTime(0);
        shoppingOrderDOMapper.insertSelective(expected);
        
        ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
        shoppingOrderItemDO.setGmtCreate(new Date());
        shoppingOrderItemDO.setGmtModified(new Date());
        shoppingOrderItemDO.setIsAllowRefund(true);
        shoppingOrderItemDO.setIsEvaluation(false);
        shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        shoppingOrderItemDO.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO.setProductId(1L);
        shoppingOrderItemDO.setProductName("productName");
        shoppingOrderItemDO.setProductModelId(1L);
        shoppingOrderItemDO.setProductModelName("test");
        shoppingOrderItemDO.setQuantity(1);
        shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSendTime(0);
        shoppingOrderItemDO.setShoppingOrderId(expected.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
        
        RequestBuilder request = MockMvcRequestBuilders.put("/shoppingOrder/cancelOrder/" + expected.getId());
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        
        ShoppingOrderDO actual = shoppingOrderDOMapper.selectByPrimaryKey(expected.getId());
        Assert.assertNotNull(actual);
        Assert.assertEquals(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue(), actual.getOrderStatus());
        ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
        Assert.assertNotNull(actualShoppingOrderItemDO);
        Assert.assertEquals(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue(), actualShoppingOrderItemDO.getOrderStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void orderMoneyForNotify() throws Exception {
        /*
         * 插入两条待付款订单记录
         */
        ShoppingOrderDO shoppingOrderDO1 = new ShoppingOrderDO();
        shoppingOrderDO1.setCommodityTotalPrice(new BigDecimal(1));
        shoppingOrderDO1.setActualAmount(new BigDecimal(1));
        shoppingOrderDO1.setFreightPrice(new BigDecimal(0));
        shoppingOrderDO1.setGmtCreate(new Date());
        shoppingOrderDO1.setGmtModified(new Date());
        shoppingOrderDO1.setIsFans(true);
        shoppingOrderDO1.setIsNeedsLogistics(true);
        shoppingOrderDO1.setIsNoReasonReturn(true);
        shoppingOrderDO1.setMemberId(1L);
        shoppingOrderDO1.setMemberNum("M0001");
        shoppingOrderDO1.setMerchantId(1L);
        shoppingOrderDO1.setMerchantName("拉乌网络");
        shoppingOrderDO1.setMerchantStoreId(1L);
        shoppingOrderDO1.setMerchantNum("B0001");
        shoppingOrderDO1.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        shoppingOrderDO1.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        shoppingOrderDO1.setOrderTotalPrice(new BigDecimal(1));
        shoppingOrderDO1.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        shoppingOrderDO1.setStatus(StatusEnum.VALID.getValue());
        shoppingOrderDO1.setConsigneeAddress("大冲商务中心1301");
        shoppingOrderDO1.setConsigneeMobile("123456");
        shoppingOrderDO1.setConsigneeName("Sunny");
        shoppingOrderDO1.setIsDone(false);
        shoppingOrderDO1.setShoppingCartIdsStr("1");
        shoppingOrderDO1.setSendTime(0);
        shoppingOrderDOMapper.insertSelective(shoppingOrderDO1);
        
        ShoppingOrderDO shoppingOrderDO2 = new ShoppingOrderDO();
        shoppingOrderDO2.setCommodityTotalPrice(new BigDecimal(1));
        shoppingOrderDO2.setActualAmount(new BigDecimal(1));
        shoppingOrderDO2.setFreightPrice(new BigDecimal(0));
        shoppingOrderDO2.setGmtCreate(new Date());
        shoppingOrderDO2.setGmtModified(new Date());
        shoppingOrderDO2.setIsFans(true);
        shoppingOrderDO2.setIsNeedsLogistics(true);
        shoppingOrderDO2.setIsNoReasonReturn(true);
        shoppingOrderDO2.setMemberId(1L);
        shoppingOrderDO2.setMemberNum("M0001");
        shoppingOrderDO2.setMerchantId(2L);
        shoppingOrderDO2.setMerchantName("拉乌网络");
        shoppingOrderDO2.setMerchantStoreId(1L);
        shoppingOrderDO2.setMerchantNum("B0001");
        shoppingOrderDO2.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        shoppingOrderDO2.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        shoppingOrderDO2.setOrderTotalPrice(new BigDecimal(1));
        shoppingOrderDO2.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        shoppingOrderDO2.setStatus(StatusEnum.VALID.getValue());
        shoppingOrderDO2.setConsigneeAddress("大冲商务中心1301");
        shoppingOrderDO2.setConsigneeMobile("123456");
        shoppingOrderDO2.setConsigneeName("Sunny");
        shoppingOrderDO2.setIsDone(false);
        shoppingOrderDO2.setShoppingCartIdsStr("2");
        shoppingOrderDO2.setSendTime(0);
        shoppingOrderDOMapper.insertSelective(shoppingOrderDO2);
        
        String orderIds = shoppingOrderDO1.getId() + "," + shoppingOrderDO2.getId();
        
        RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/orderMoneyForNotify").param("orderIds", orderIds);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        
        ShoppingOrderMoneyDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderMoneyDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(shoppingOrderDO1.getOrderTotalPrice().add(shoppingOrderDO2.getOrderTotalPrice()).doubleValue(), actual.getOrderTotalPrice().doubleValue(), 0D);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getOrderItemProductName() throws Exception {
        /*
         * 插入一条订单记录，其下有两条订单项记录
         */
        ShoppingOrderDO expected = new ShoppingOrderDO();
        expected.setCommodityTotalPrice(new BigDecimal(1));
        expected.setActualAmount(new BigDecimal(1));
        expected.setFreightPrice(new BigDecimal(0));
        expected.setGmtCreate(new Date());
        expected.setGmtModified(new Date());
        expected.setGmtTransport(new Date());
        expected.setIsFans(true);
        expected.setIsNeedsLogistics(true);
        expected.setIsNoReasonReturn(true);
        expected.setMemberId(1L);
        expected.setMemberNum("M0001");
        expected.setMerchantId(1L);
        expected.setMerchantName("拉乌网络");
        expected.setMerchantStoreId(1L);
        expected.setMerchantNum("B0001");
        expected.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
        expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
        expected.setOrderTotalPrice(new BigDecimal(1));
        expected.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
        expected.setStatus(StatusEnum.VALID.getValue());
        expected.setConsigneeAddress("大冲商务中心1301");
        expected.setConsigneeMobile("123456");
        expected.setConsigneeName("Sunny");
        expected.setIsDone(false);
        expected.setShoppingCartIdsStr("1");
        expected.setSendTime(0);
        expected.setPaymentMethod(TransactionPayTypeEnum.BALANCE.getVal());
        shoppingOrderDOMapper.insertSelective(expected);
        
        ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
        shoppingOrderItemDO.setGmtCreate(new Date());
        shoppingOrderItemDO.setGmtModified(new Date());
        shoppingOrderItemDO.setIsAllowRefund(true);
        shoppingOrderItemDO.setIsEvaluation(false);
        shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
        shoppingOrderItemDO.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO.setProductId(1L);
        shoppingOrderItemDO.setProductName("productName");
        shoppingOrderItemDO.setProductModelId(1L);
        shoppingOrderItemDO.setProductModelName("test");
        shoppingOrderItemDO.setQuantity(1);
        shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO.setSendTime(0);
        shoppingOrderItemDO.setShoppingOrderId(expected.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
        
        ShoppingOrderItemDO shoppingOrderItemDO2 = new ShoppingOrderItemDO();
        shoppingOrderItemDO2.setGmtCreate(new Date());
        shoppingOrderItemDO2.setGmtModified(new Date());
        shoppingOrderItemDO2.setIsAllowRefund(true);
        shoppingOrderItemDO2.setIsEvaluation(false);
        shoppingOrderItemDO2.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
        shoppingOrderItemDO2.setProductFeatureImage("test.jpg");
        shoppingOrderItemDO2.setProductId(1L);
        shoppingOrderItemDO2.setProductName("productName2");
        shoppingOrderItemDO2.setProductModelId(1L);
        shoppingOrderItemDO2.setProductModelName("test");
        shoppingOrderItemDO2.setQuantity(1);
        shoppingOrderItemDO2.setRegularPrice(new BigDecimal(1));
        shoppingOrderItemDO2.setSalesPrice(new BigDecimal(1));
        shoppingOrderItemDO2.setSendTime(0);
        shoppingOrderItemDO2.setShoppingOrderId(expected.getId());
        shoppingOrderItemDOMapper.insert(shoppingOrderItemDO2);
        
        RequestBuilder request = MockMvcRequestBuilders.get("/shoppingOrder/getOrderItemProductName/" + expected.getId());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        
        String actual = JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model");
        Assert.assertEquals(shoppingOrderItemDO.getProductName(), actual);
    }
}
