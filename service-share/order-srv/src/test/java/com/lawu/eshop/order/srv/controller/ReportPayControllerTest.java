package com.lawu.eshop.order.srv.controller;

import java.math.BigDecimal;
import java.util.Date;

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

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.PayOrderStatusEnum;
import com.lawu.eshop.order.constants.ReportFansRiseRateEnum;
import com.lawu.eshop.order.dto.ReportRiseRateDTO;
import com.lawu.eshop.order.param.ReportDataParam;
import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.domain.PayOrderDO;
import com.lawu.eshop.order.srv.mapper.PayOrderDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
@WebAppConfiguration
public class ReportPayControllerTest {

    private MockMvc mvc;

    @Autowired
    private ReportPayController reportPayController;
    
	@Autowired
	private PayOrderDOMapper payOrderDOMapper;
    
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(reportPayController).build();
    }

    /**
     * SQL不兼容
     * @throws Exception
     * @author jiangxinjun
     * @date 2017年8月30日
     */
    @Ignore
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void payVolumeRiseRate() throws Exception {
    	/*
    	 *  插入一条买单记录
    	 *  已支付/余额支付/支付成功/已经评论/未计算提成
    	 */
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
		expected.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
		payOrderDOMapper.insert(expected);
    	
		ReportDataParam param = new ReportDataParam();
		param.setFlag(ReportFansRiseRateEnum.DAY);
		param.setMerchantId(expected.getMerchantId());
		String content = JSONObject.toJSONString(param);
    	RequestBuilder request = MockMvcRequestBuilders.post("/reportPay/payVolumeRiseRate").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        ReportRiseRateDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), ReportRiseRateDTO.class);
        Assert.assertNotNull(actual);
    }
}