package com.lawu.eshop.order.srv.strategy.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.order.srv.OrderSrvApplicationTest;
import com.lawu.eshop.order.srv.bo.ExpressInquiriesDetailBO;
import com.lawu.eshop.order.srv.bo.ExpressRecognitionDetailBO;
import com.lawu.eshop.order.srv.strategy.ExpressStrategy;

/**
 * 
 * @author jiangxinjun
 * @date 2017年9月21日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderSrvApplicationTest.class)
public class KuaiDi100ExpressStrategyTest {

	@Autowired
	@Qualifier("kuaiDi100ExpressStrategy")
	private ExpressStrategy expressStrategy;
	
	@Ignore
	@Test
	public void inquiries() {
		ExpressInquiriesDetailBO actual = expressStrategy.inquiries("huitongkuaid", "70094570443407");
		Assert.assertNotNull(actual);
	}
	
	@Ignore
	@Test
	public void recognition() {
		ExpressRecognitionDetailBO actual = expressStrategy.recognition("70094570443407");
		Assert.assertNotNull(actual);
	}
	
	@Ignore
	@Test
	public void recognitionWithInquiries() {
		ExpressInquiriesDetailBO actual = expressStrategy.recognitionWithInquiries("huitongkuaid", "70094570443407");
		Assert.assertNotNull(actual);
	}
	
}
