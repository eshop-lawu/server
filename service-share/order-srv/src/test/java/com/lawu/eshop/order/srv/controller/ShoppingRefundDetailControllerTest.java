package com.lawu.eshop.order.srv.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
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
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.dto.ShoppingRefundProcessDTO;
import com.lawu.eshop.order.dto.foreign.ExpressInquiriesDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressInfoDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingRefundDetailDTO;
import com.lawu.eshop.order.param.ShoppingRefundDetailLogisticsInformationParam;
import com.lawu.eshop.order.param.ShoppingRefundDetailRerurnAddressParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToApplyForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToRefundForeignParam;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.constants.PropertyNameConstant;
import com.lawu.eshop.order.srv.domain.PropertyDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDOExample;
import com.lawu.eshop.order.srv.json.JCDateDeserializer;
import com.lawu.eshop.order.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderItemDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundDetailDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundProcessDOMapper;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.DateUtil;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
@WebAppConfiguration
public class ShoppingRefundDetailControllerTest {

    private MockMvc mvc;

    @Autowired
    private ShoppingRefundDetailController shoppingRefundDetailController;
    
	@Autowired
	private ShoppingOrderDOMapper shoppingOrderDOMapper;
	
	@Autowired
	private ShoppingOrderItemDOMapper shoppingOrderItemDOMapper;
	
	@Autowired
	private ShoppingRefundDetailDOMapper shoppingRefundDetailDOMapper;
	
	@Autowired
	private ShoppingRefundProcessDOMapper shoppingRefundProcessDOMapper;
	
	@Autowired
	private PropertyDOMapper propertyDOMapper;
    
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(shoppingRefundDetailController).build();
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRefundDetail() throws Exception {
    	PropertyDO propertyDO = new PropertyDO();
    	propertyDO.setGmtCreate(new Date());
    	propertyDO.setGmtModified(new Date());
    	propertyDO.setName(PropertyNameConstant.TO_BE_CONFIRMED_FOR_RETURN_REFUND_REFUND_TIME);
    	propertyDO.setRemark("订单退款，银行到账时间");
    	propertyDO.setValue("3");
    	propertyDOMapper.insert(propertyDO);
    	
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
    	expected.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
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
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	// 已经发送两次提醒消息
    	shoppingOrderItemDO.setSendTime(1);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	Map<Long, ShoppingRefundProcessDO> expectedShoppingRefundProcessDOMap = new HashMap<>();
    	ShoppingRefundProcessDO shoppingRefundProcessDO = new ShoppingRefundProcessDO();
    	shoppingRefundProcessDO.setGmtCreate(new Date());
    	shoppingRefundProcessDO.setShoppingRefundDetailId(shoppingRefundDetailDO.getId());
    	shoppingRefundProcessDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	shoppingRefundProcessDOMapper.insert(shoppingRefundProcessDO);
    	expectedShoppingRefundProcessDOMap.put(shoppingRefundProcessDO.getId(), shoppingRefundProcessDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingRefundDetail/getRefundDetail/" + shoppingOrderItemDO.getId())
    			.param("memberId", expected.getMemberId().toString());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ParserConfig mapping = new ParserConfig();
    	mapping.putDeserializer(Date.class, new JCDateDeserializer("yyyy-MM-dd HH:mm:ss"));
        ShoppingRefundDetailDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingRefundDetailDTO.class, mapping, JSON.DEFAULT_PARSER_FEATURE);
        Assert.assertNotNull(actual);
        Assert.assertEquals(shoppingRefundDetailDO.getAmount().doubleValue(), actual.getAmount().doubleValue(), 0D);
        Assert.assertEquals(shoppingRefundDetailDO.getConsigneeAddress(), actual.getConsigneeAddress());
        Assert.assertEquals(shoppingRefundDetailDO.getConsigneeMobile(), actual.getConsigneeMobile());
        Assert.assertEquals(shoppingRefundDetailDO.getConsigneeName(), actual.getConsigneeName());
        Assert.assertEquals(shoppingRefundDetailDO.getDescription(), actual.getDescribe());
        Assert.assertEquals(shoppingRefundDetailDO.getExpressCompanyName(), actual.getExpressCompanyName());
        if (shoppingRefundDetailDO.getGmtConfirmed() == null ||  actual.getGmtConfirmed() == null) {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtConfirmed() != null ? shoppingRefundDetailDO.getGmtConfirmed().getTime() : null, actual.getGmtConfirmed() != null ? actual.getGmtConfirmed().getTime() : null);
        } else {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtConfirmed() != null ? shoppingRefundDetailDO.getGmtConfirmed().getTime() : null, actual.getGmtConfirmed() != null ? actual.getGmtConfirmed().getTime() : null, 1000);
        }
        if (shoppingRefundDetailDO.getGmtCreate() == null || actual.getGmtCreate() == null) {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtCreate() != null ? shoppingRefundDetailDO.getGmtCreate().getTime() : null, actual.getGmtCreate() != null ? actual.getGmtCreate().getTime() : null);
        } else {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtCreate() != null ? shoppingRefundDetailDO.getGmtCreate().getTime() : null, actual.getGmtCreate() != null ? actual.getGmtCreate().getTime() : null, 1000);
        }
        if (shoppingRefundDetailDO.getGmtFill() == null || actual.getGmtFill() == null) {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtFill() != null ? shoppingRefundDetailDO.getGmtFill().getTime() : null, actual.getGmtFill() != null ? actual.getGmtFill().getTime() : null);
        } else {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtFill() != null ? shoppingRefundDetailDO.getGmtFill().getTime() : null, actual.getGmtFill() != null ? actual.getGmtFill().getTime() : null, 1000);
        }
        if (shoppingRefundDetailDO.getGmtIntervention() == null || actual.getGmtIntervention() == null) {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtIntervention() != null ? shoppingRefundDetailDO.getGmtIntervention().getTime() : null, actual.getGmtIntervention() != null ? actual.getGmtIntervention().getTime() : null);
        } else {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtIntervention() != null ? shoppingRefundDetailDO.getGmtIntervention().getTime() : null, actual.getGmtIntervention() != null ? actual.getGmtIntervention().getTime() : null, 1000);
        }
        if (shoppingRefundDetailDO.getGmtRefund() == null || actual.getGmtRefund() ==  null) {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtRefund() != null ? shoppingRefundDetailDO.getGmtRefund().getTime() : null, actual.getGmtRefund() != null ? actual.getGmtRefund().getTime() : null);
        } else {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtRefund() != null ? shoppingRefundDetailDO.getGmtRefund().getTime() : null, actual.getGmtRefund() != null ? actual.getGmtRefund().getTime() : null, 1000);
        }
        if (shoppingRefundDetailDO.getGmtSubmit() == null || actual.getGmtSubmit() == null) {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtSubmit() != null ? shoppingRefundDetailDO.getGmtSubmit().getTime() : null, actual.getGmtSubmit() != null ? actual.getGmtSubmit().getTime() : null);
        } else {
        	Assert.assertEquals(shoppingRefundDetailDO.getGmtSubmit() != null ? shoppingRefundDetailDO.getGmtSubmit().getTime() : null, actual.getGmtSubmit() != null ? actual.getGmtSubmit().getTime() : null, 1000);
        }
        if (shoppingOrderItemDO.getRefundStatus().equals(RefundStatusEnum.REFUND_SUCCESSFULLY.getValue())) {
        	Assert.assertEquals(DateUtil.add(shoppingOrderItemDO.getGmtModified(), Integer.valueOf(propertyDO.getValue()), Calendar.DAY_OF_YEAR).getTime(), actual.getGmtToAccount().getTime(), 1000);	
        	Assert.assertNull(actual.getCountdown());
        } else {
        	Assert.assertNotNull(actual.getCountdown());
        }
        Assert.assertEquals(shoppingRefundDetailDO.getId(), actual.getId());
        Assert.assertEquals(shoppingRefundDetailDO.getIsAgree(), actual.getIsAgree());
        Assert.assertEquals(expected.getPaymentMethod(), actual.getPaymentMethod().getVal());
        Assert.assertEquals(shoppingRefundDetailDO.getReason(), actual.getReason());
        Assert.assertEquals(shoppingOrderItemDO.getRefundStatus(), actual.getRefundStatus() != null ? actual.getRefundStatus().getValue() : null);
        Assert.assertEquals(shoppingRefundDetailDO.getRefusalReasons(), actual.getRefusalReasons());
        Assert.assertEquals(shoppingRefundDetailDO.getRefuseImages(), StringUtils.isBlank(actual.getRefuseImages()) ? null : actual.getRefuseImages());
        Assert.assertEquals(expected.getId(), actual.getShoppingOrderId());
        Assert.assertEquals(shoppingRefundDetailDO.getType(), actual.getType().getValue());
        Assert.assertEquals(shoppingRefundDetailDO.getVoucherPicture(), actual.getVoucherPicture());
        Assert.assertEquals(shoppingRefundDetailDO.getWaybillNum(), actual.getWaybillNum());
        for (ShoppingRefundProcessDTO actualShoppingRefundProcess : actual.getShoppingRefundProcessList()) {
        	Assert.assertNotNull(actualShoppingRefundProcess.getGmtCreate());
        	Assert.assertEquals(shoppingRefundProcessDO.getRefundStatus(), actualShoppingRefundProcess.getRefundStatus().getValue());
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void agreeToApply() throws Exception {
    	PropertyDO propertyDO = new PropertyDO();
    	propertyDO.setGmtCreate(new Date());
    	propertyDO.setGmtModified(new Date());
    	propertyDO.setName(PropertyNameConstant.REFUND_TO_THE_ACCOUNT_TIME);
    	propertyDO.setRemark("订单退款，银行到账时间");
    	propertyDO.setValue("7");
    	propertyDOMapper.insert(propertyDO);
    	
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
    	expected.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
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
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	// 已经发送两次提醒消息
    	shoppingOrderItemDO.setSendTime(1);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	ShoppingRefundDetailAgreeToApplyForeignParam param = new ShoppingRefundDetailAgreeToApplyForeignParam();
    	param.setIsAgree(false);
    	param.setRefusalReasons("商品已经损毁");
    	String content = JSONObject.toJSONString(param);
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingRefundDetail/agreeToApply/" + shoppingRefundDetailDO.getId())
    			.param("merchantId", expected.getMerchantId().toString())
    			.contentType(MediaType.APPLICATION_JSON).content(content);    	
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderItemDO actual = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(RefundStatusEnum.REFUND_FAILED.getValue(), actual.getRefundStatus());
    	Assert.assertEquals(0, actual.getSendTime().intValue());
    	
    	ShoppingRefundDetailDO actualShoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getId());
    	Assert.assertNotNull(actualShoppingRefundDetailDO);
    	Assert.assertNotNull(actualShoppingRefundDetailDO.getGmtConfirmed());
    	Assert.assertNotNull(actualShoppingRefundDetailDO.getGmtRefund());
    	Assert.assertEquals(param.getIsAgree(), actualShoppingRefundDetailDO.getIsAgree());
    	Assert.assertEquals(param.getRefusalReasons(), actualShoppingRefundDetailDO.getRefusalReasons());
    	
    	ShoppingRefundProcessDOExample shoppingRefundProcessDOExample = new ShoppingRefundProcessDOExample();
    	shoppingRefundProcessDOExample.createCriteria().andShoppingRefundDetailIdEqualTo(shoppingRefundDetailDO.getId());
    	ShoppingRefundProcessDO actualShoppingRefundProcessDO = shoppingRefundProcessDOMapper.selectByExample(shoppingRefundProcessDOExample).get(0);
    	Assert.assertNotNull(actualShoppingRefundProcessDO);
    	Assert.assertNotNull(actualShoppingRefundProcessDO.getGmtCreate());
    	Assert.assertEquals(shoppingRefundDetailDO.getId(), actualShoppingRefundProcessDO.getShoppingRefundDetailId());
    	Assert.assertEquals(RefundStatusEnum.REFUND_FAILED.getValue(), actualShoppingRefundProcessDO.getRefundStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void fillReturnAddress() throws Exception {
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
    	expected.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
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
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	// 已经发送两次提醒消息
    	shoppingOrderItemDO.setSendTime(1);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.FILL_RETURN_ADDRESS.getValue());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	ShoppingRefundDetailRerurnAddressParam param = new ShoppingRefundDetailRerurnAddressParam();
    	param.setConsigneeAddress("大冲商务中心");
    	param.setConsigneeMobile("123456");
    	param.setConsigneeName("Sunny");
    	String content = JSONObject.toJSONString(param);
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingRefundDetail/fillReturnAddress/" + shoppingRefundDetailDO.getId())
    			.param("merchantId", expected.getMerchantId().toString())
    			.contentType(MediaType.APPLICATION_JSON).content(content);    	
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertEquals(RefundStatusEnum.TO_BE_RETURNED.getValue(), actualShoppingOrderItemDO.getRefundStatus());
    	Assert.assertEquals(0, actualShoppingOrderItemDO.getSendTime().intValue());
    	
    	ShoppingRefundDetailDO actual = shoppingRefundDetailDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertNotNull(actual.getGmtFill());
    	Assert.assertEquals(param.getConsigneeAddress(), actual.getConsigneeAddress());
    	Assert.assertEquals(param.getConsigneeMobile(), actual.getConsigneeMobile());
    	Assert.assertEquals(param.getConsigneeName(), actual.getConsigneeName());
    	
    	ShoppingRefundProcessDOExample shoppingRefundProcessDOExample = new ShoppingRefundProcessDOExample();
    	shoppingRefundProcessDOExample.createCriteria().andShoppingRefundDetailIdEqualTo(shoppingRefundDetailDO.getId());
    	ShoppingRefundProcessDO shoppingRefundProcessDO = shoppingRefundProcessDOMapper.selectByExample(shoppingRefundProcessDOExample).get(0);
    	Assert.assertNotNull(shoppingRefundProcessDO);
    	Assert.assertNotNull(shoppingRefundProcessDO.getGmtCreate());
    	Assert.assertEquals(shoppingRefundDetailDO.getId(), shoppingRefundProcessDO.getShoppingRefundDetailId());
    	Assert.assertEquals(actualShoppingOrderItemDO.getRefundStatus(), shoppingRefundProcessDO.getRefundStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void fillLogisticsInformation() throws Exception {
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
    	expected.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
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
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	// 已经发送两次提醒消息
    	shoppingOrderItemDO.setSendTime(1);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_RETURNED.getValue());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	ShoppingRefundDetailLogisticsInformationParam param = new ShoppingRefundDetailLogisticsInformationParam();
    	param.setExpressCompanyCode("SF");
    	param.setExpressCompanyId(1);
    	param.setExpressCompanyName("顺丰");
    	param.setWaybillNum("123456789");
    	String content = JSONObject.toJSONString(param);
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingRefundDetail/fillLogisticsInformation/" + shoppingRefundDetailDO.getId())
    			.param("memberId", expected.getMemberId().toString())
    			.contentType(MediaType.APPLICATION_JSON).content(content);    	
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
    	ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertEquals(RefundStatusEnum.TO_BE_REFUNDED.getValue(), actualShoppingOrderItemDO.getRefundStatus());
    	Assert.assertEquals(0, actualShoppingOrderItemDO.getSendTime().intValue());
    	
    	ShoppingRefundDetailDO actual = shoppingRefundDetailDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertNotNull(actual.getGmtSubmit());
    	Assert.assertEquals(param.getExpressCompanyCode(), actual.getExpressCompanyCode());
    	Assert.assertEquals(param.getExpressCompanyId(), actual.getExpressCompanyId());
    	Assert.assertEquals(param.getExpressCompanyName(), actual.getExpressCompanyName());
    	Assert.assertEquals(param.getWaybillNum(), actual.getWaybillNum());
    	
    	ShoppingRefundProcessDOExample shoppingRefundProcessDOExample = new ShoppingRefundProcessDOExample();
    	shoppingRefundProcessDOExample.createCriteria().andShoppingRefundDetailIdEqualTo(shoppingRefundDetailDO.getId());
    	ShoppingRefundProcessDO shoppingRefundProcessDO = shoppingRefundProcessDOMapper.selectByExample(shoppingRefundProcessDOExample).get(0);
    	Assert.assertNotNull(shoppingRefundProcessDO);
    	Assert.assertNotNull(shoppingRefundProcessDO.getGmtCreate());
    	Assert.assertEquals(shoppingRefundDetailDO.getId(), shoppingRefundProcessDO.getShoppingRefundDetailId());
    	Assert.assertEquals(actualShoppingOrderItemDO.getRefundStatus(), shoppingRefundProcessDO.getRefundStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void agreeToRefund() throws Exception {
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
    	expected.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
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
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.TO_BE_REFUNDED.getValue());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.REFUND.getValue());
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	ShoppingRefundDetailAgreeToRefundForeignParam param = new ShoppingRefundDetailAgreeToRefundForeignParam();
    	param.setIsAgree(false);
    	param.setRefusalReasons("商品已经损坏");
    	String content = JSONObject.toJSONString(param);
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingRefundDetail/agreeToRefund/" + shoppingRefundDetailDO.getId())
    			.param("merchantId", expected.getMerchantId().toString())
    			.contentType(MediaType.APPLICATION_JSON).content(content);    	
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderItemDO actual = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(RefundStatusEnum.REFUND_FAILED.getValue(), actual.getRefundStatus());
    	Assert.assertEquals(0, actual.getSendTime().intValue());
    	
    	ShoppingRefundDetailDO actualShoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getId());
    	Assert.assertNotNull(actualShoppingRefundDetailDO);
    	Assert.assertNotNull(actualShoppingRefundDetailDO.getGmtRefund());
    	Assert.assertEquals(param.getIsAgree(), actualShoppingRefundDetailDO.getIsAgree());
    	Assert.assertEquals(param.getRefusalReasons(), actualShoppingRefundDetailDO.getRefusalReasons());
    	
    	ShoppingRefundProcessDOExample shoppingRefundProcessDOExample = new ShoppingRefundProcessDOExample();
    	shoppingRefundProcessDOExample.createCriteria().andShoppingRefundDetailIdEqualTo(shoppingRefundDetailDO.getId());
    	ShoppingRefundProcessDO shoppingRefundProcessDO = shoppingRefundProcessDOMapper.selectByExample(shoppingRefundProcessDOExample).get(0);
    	Assert.assertNotNull(shoppingRefundProcessDO);
    	Assert.assertNotNull(shoppingRefundProcessDO.getGmtCreate());
    	Assert.assertEquals(shoppingRefundDetailDO.getId(), shoppingRefundProcessDO.getShoppingRefundDetailId());
    	Assert.assertEquals(RefundStatusEnum.REFUND_FAILED.getValue(), shoppingRefundProcessDO.getRefundStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void platformIntervention() throws Exception {
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
    	expected.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
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
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	// 已经发送两次提醒消息
    	shoppingOrderItemDO.setSendTime(1);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.REFUND_FAILED.getValue());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingRefundDetail/platformIntervention/" + shoppingRefundDetailDO.getId())
    			.param("memberId", expected.getMemberId().toString());
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderItemDO actualShoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actualShoppingOrderItemDO);
    	Assert.assertEquals(RefundStatusEnum.PLATFORM_INTERVENTION.getValue(), actualShoppingOrderItemDO.getRefundStatus());
    	Assert.assertEquals(0, actualShoppingOrderItemDO.getSendTime().intValue());
    	
    	ShoppingRefundDetailDO actual = shoppingRefundDetailDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertNotNull(actual.getGmtIntervention());
    	
    	ShoppingRefundProcessDOExample shoppingRefundProcessDOExample = new ShoppingRefundProcessDOExample();
    	shoppingRefundProcessDOExample.createCriteria().andShoppingRefundDetailIdEqualTo(shoppingRefundDetailDO.getId());
    	ShoppingRefundProcessDO shoppingRefundProcessDO = shoppingRefundProcessDOMapper.selectByExample(shoppingRefundProcessDOExample).get(0);
    	Assert.assertNotNull(shoppingRefundProcessDO);
    	Assert.assertNotNull(shoppingRefundProcessDO.getGmtCreate());
    	Assert.assertEquals(shoppingRefundDetailDO.getId(), shoppingRefundProcessDO.getShoppingRefundDetailId());
    	Assert.assertEquals(RefundStatusEnum.PLATFORM_INTERVENTION.getValue(), shoppingRefundProcessDO.getRefundStatus());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void expressInfo() throws Exception {
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
    	expected.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
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
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	// 已经发送两次提醒消息
    	shoppingOrderItemDO.setSendTime(1);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.REFUND_FAILED.getValue());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	expected.setConsigneeAddress("大冲商务中心1301");
    	expected.setConsigneeMobile("123456");
    	expected.setConsigneeName("Sunny");
    	expected.setExpressCompanyCode("SF");
    	expected.setExpressCompanyId(1);
    	expected.setExpressCompanyName("顺丰");
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingRefundDetail/expressInfo/" + shoppingRefundDetailDO.getId())
    			.param("merchantId", expected.getMerchantId().toString());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ParserConfig mapping = new ParserConfig();
    	mapping.putDeserializer(Date.class, new JCDateDeserializer());
    	ShoppingOrderExpressInfoDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderExpressInfoDTO.class, mapping, JSON.DEFAULT_PARSER_FEATURE);
        Assert.assertNotNull(actual);
        Assert.assertEquals(shoppingRefundDetailDO.getExpressCompanyName(), actual.getExpressCompanyName());
        Assert.assertEquals(shoppingRefundDetailDO.getExpressCompanyId(), actual.getExpressCompanyId());
        Assert.assertEquals(shoppingOrderItemDO.getQuantity(), actual.getTotalQuantity());
        Assert.assertEquals(shoppingRefundDetailDO.getWaybillNum(), actual.getWaybillNum());
        Assert.assertEquals(shoppingOrderItemDO.getProductFeatureImage(), actual.getProductFeatureImage());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getExpressInfo() throws Exception {
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
    	expected.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
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
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	// 已经发送两次提醒消息
    	shoppingOrderItemDO.setSendTime(1);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.REFUND_FAILED.getValue());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDO.setConsigneeAddress("大冲商务中心1301");
    	shoppingRefundDetailDO.setConsigneeMobile("123456");
    	shoppingRefundDetailDO.setConsigneeName("Sunny");
    	shoppingRefundDetailDO.setExpressCompanyCode("SF");
    	shoppingRefundDetailDO.setExpressCompanyId(1);
    	shoppingRefundDetailDO.setExpressCompanyName("顺丰");
    	shoppingRefundDetailDO.setWaybillNum("123456");
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.get("/shoppingRefundDetail/getExpressInfo/" + shoppingRefundDetailDO.getId())
    			.param("merchantId", expected.getMerchantId().toString());
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ParserConfig mapping = new ParserConfig();
    	mapping.putDeserializer(Date.class, new JCDateDeserializer());
    	ShoppingOrderExpressDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ShoppingOrderExpressDTO.class, mapping, JSON.DEFAULT_PARSER_FEATURE);
        Assert.assertNotNull(actual);
        Assert.assertEquals(shoppingRefundDetailDO.getExpressCompanyName(), actual.getExpressCompanyName());
        Assert.assertEquals(shoppingRefundDetailDO.getWaybillNum(), actual.getWaybillNum());
        ExpressInquiriesDetailDTO expressInquiriesDetailDTO = actual.getExpressInquiriesDetailDTO();
        Assert.assertNotNull(expressInquiriesDetailDTO);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void revokeRefundRequest() throws Exception {
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
    	expected.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
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
    	shoppingOrderItemDO.setProductFeatureImage("test.jpg");
    	shoppingOrderItemDO.setProductId(1L);
    	shoppingOrderItemDO.setProductName("productName");
    	shoppingOrderItemDO.setProductModelId(1L);
    	shoppingOrderItemDO.setProductModelName("test");
    	shoppingOrderItemDO.setQuantity(1);
    	shoppingOrderItemDO.setRegularPrice(new BigDecimal(1));
    	shoppingOrderItemDO.setSalesPrice(new BigDecimal(1));
    	// 已经发送两次提醒消息
    	shoppingOrderItemDO.setSendTime(1);
    	shoppingOrderItemDO.setShoppingOrderId(expected.getId());
    	shoppingOrderItemDO.setRefundStatus(RefundStatusEnum.REFUND_FAILED.getValue());
    	shoppingOrderItemDOMapper.insert(shoppingOrderItemDO);
    	
    	ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
    	shoppingRefundDetailDO.setType(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
    	shoppingRefundDetailDO.setAmount(shoppingOrderItemDO.getSalesPrice().multiply(new BigDecimal(shoppingOrderItemDO.getQuantity())));
    	shoppingRefundDetailDO.setDescription("就是想退款");
    	shoppingRefundDetailDO.setGmtModified(new Date());
    	shoppingRefundDetailDO.setGmtCreate(new Date());
    	shoppingRefundDetailDO.setReason("七天无理由退货");
    	shoppingRefundDetailDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
    	shoppingRefundDetailDO.setStatus(StatusEnum.VALID.getValue());
    	shoppingRefundDetailDOMapper.insert(shoppingRefundDetailDO);
    	
    	RequestBuilder request = MockMvcRequestBuilders.put("/shoppingRefundDetail/revokeRefundRequest/" + shoppingRefundDetailDO.getId())
    			.param("memberId", expected.getMemberId().toString());
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ShoppingOrderItemDO actual = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getId());
    	Assert.assertNotNull(actual);
    	Assert.assertNull(actual.getRefundStatus());
    	Assert.assertEquals(expected.getOrderStatus(), actual.getOrderStatus());
    	Assert.assertEquals(0, actual.getSendTime().intValue());
    	
    	ShoppingRefundDetailDO actualShoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getId());
    	Assert.assertNotNull(actualShoppingRefundDetailDO);
    	Assert.assertEquals(StatusEnum.INVALID.getValue(), actualShoppingRefundDetailDO.getStatus());
    }
}
