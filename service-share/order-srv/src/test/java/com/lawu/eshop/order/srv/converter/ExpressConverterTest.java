package com.lawu.eshop.order.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.order.constants.ExpressInquiriesDetailStateEnum;
import com.lawu.eshop.order.dto.foreign.ExpressInquiriesDetailDTO;
import com.lawu.eshop.order.dto.foreign.TraceDTO;
import com.lawu.eshop.order.srv.bo.ExpressInquiriesDetailBO;
import com.lawu.eshop.order.srv.bo.TraceBO;
import com.lawu.eshop.order.srv.utils.express.kdniao.bo.ExpressInquiriesDetail;
import com.lawu.eshop.order.srv.utils.express.kdniao.bo.Trace;
import com.lawu.eshop.order.srv.utils.express.kdniao.constants.StateEnum;
import com.lawu.utils.DateUtil;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月24日
 */
public class ExpressConverterTest {

	@Test
	public void convertExpressInquiriesDetailBO() {
		ExpressInquiriesDetail expected = new ExpressInquiriesDetail();
		expected.seteBusinessId("123456");
		expected.setOrderCode("84513374");
		expected.setShipperCode("9844512");
		expected.setState("2");
		expected.setSuccess(true);
		List<Trace> traces = new ArrayList<>();
		Trace trace = new Trace();
		trace.setAcceptStation("快递已经发货，等待揽收");
		trace.setAcceptTime("2017-07-24 17:35:00");
		traces.add(trace);
		expected.setTraces(traces);
		
		ExpressInquiriesDetailBO actual = ExpressConverter.convert(expected);
		assertExpressInquiriesDetailBO(expected, actual);
	}
	
	@Test
	public void convertTraceBO() {
		Trace expected = new Trace();
		expected.setAcceptStation("快递已经发货，等待揽收");
		expected.setAcceptTime("2017-07-24 17:35:00");
		TraceBO actual = ExpressConverter.convert(expected);
		assertTraceBO(expected, actual);
	}
	
	@Test
	public void convertExpressInquiriesDetailDTO() {
		ExpressInquiriesDetailBO expected = initExpressInquiriesDetailBO();
		ExpressInquiriesDetailDTO actual = ExpressConverter.convert(expected);
		assertExpressInquiriesDetailDTO(expected, actual);
	}
	
	@Test
	public void convertTraceDTO() {
		TraceBO expected = new TraceBO();
		expected.setAcceptStation("快递已经发货，等待揽收");
		expected.setAcceptTime("2017-07-24 17:35:00");
		TraceDTO actual = ExpressConverter.convert(expected);
		assertTraceDTO(expected, actual);
	}
	
	public static void assertExpressInquiriesDetailBO(ExpressInquiriesDetail expected, ExpressInquiriesDetailBO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(StateEnum.getEnum(expected.getState()).getState(), actual.getState());
		if (actual.getTraces() != null && !actual.getTraces().isEmpty()) {
			for (int i = 0; i < actual.getTraces().size(); i++) {
				assertTraceBO(expected.getTraces().get(i), actual.getTraces().get(i));
			}
		}
	}
	
	public static void assertTraceBO(Trace expected, TraceBO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getAcceptStation(), actual.getAcceptStation());
		Assert.assertEquals(expected.getAcceptTime(), actual.getAcceptTime());
		Assert.assertEquals(expected.getRemark(), actual.getRemark());
	}
	
	public static void assertExpressInquiriesDetailDTO(ExpressInquiriesDetailBO expected, ExpressInquiriesDetailDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getState(), actual.getState());
		if (actual.getTraces() != null || actual.getTraces().isEmpty()) {
			for (int i = 0; i < actual.getTraces().size(); i++) {
				assertTraceDTO(expected.getTraces().get(i), actual.getTraces().get(i));
			}
		}
	}
	
	public static void assertTraceDTO(TraceBO expected, TraceDTO actual) {
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getAcceptStation(), actual.getAcceptStation());
		Assert.assertEquals(expected.getAcceptTime(), actual.getAcceptTime());
		Assert.assertEquals(expected.getRemark(), actual.getRemark());
	}
	
    public static ExpressInquiriesDetailBO initExpressInquiriesDetailBO() {
    	ExpressInquiriesDetailBO rtn = new ExpressInquiriesDetailBO();
    	rtn.setState(ExpressInquiriesDetailStateEnum.ON_THE_WAY);
    	rtn.setTraces(new ArrayList<>());
    	TraceBO traceBO = new TraceBO();
    	traceBO.setAcceptStation("等待快递揽收");
    	traceBO.setAcceptTime(DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
    	rtn.getTraces().add(traceBO);
    	return rtn;
    }
}
