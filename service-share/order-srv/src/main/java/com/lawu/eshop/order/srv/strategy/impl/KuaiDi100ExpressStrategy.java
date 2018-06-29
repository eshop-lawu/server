package com.lawu.eshop.order.srv.strategy.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.order.constants.ExpressInquiriesDetailStateEnum;
import com.lawu.eshop.order.srv.bo.ExpressInquiriesDetailBO;
import com.lawu.eshop.order.srv.bo.ExpressRecognitionDetailBO;
import com.lawu.eshop.order.srv.converter.ExpressConverter;
import com.lawu.eshop.order.srv.strategy.ExpressStrategy;
import com.lawu.eshop.order.srv.utils.express.kuaidi100.KuaiDi100Api;
import com.lawu.eshop.order.srv.utils.express.kuaidi100.bo.Express;
import com.lawu.eshop.order.srv.utils.express.kuaidi100.bo.ExpressInquiriesDetail;
import com.lawu.eshop.order.srv.utils.express.kuaidi100.bo.ExpressTracesDetail;
import com.lawu.eshop.order.srv.utils.express.kuaidi100.config.KuaiDi100Config;
import com.lawu.eshop.order.srv.utils.express.kuaidi100.constants.ReturnCodeEnum;
import com.lawu.eshop.order.srv.utils.express.kuaidi100.constants.StatusEnum;

@Primary
@Service("kuaiDi100ExpressStrategy")
public class KuaiDi100ExpressStrategy implements ExpressStrategy {
	
	private static Logger logger = LoggerFactory.getLogger(KuaiDi100ExpressStrategy.class);
	
	@Autowired
	private KuaiDi100Api api;
	
	@Autowired
	private KuaiDi100Config config;
	
	/**
	 * 即时查询
	 */
	@Override
	public ExpressInquiriesDetailBO inquiries(String expCode, String expNo) {
		ExpressInquiriesDetailBO rtn = null;
		try {
			if (config.getIsTest()) {
				String result = api.orderTraces(expCode, expNo);
				
				// JSON转换成JOPO
				ExpressInquiriesDetail expressInquiriesDetail = JSONObject.parseObject(result, ExpressInquiriesDetail.class);
				
				if (expressInquiriesDetail.getStatus().equals(StatusEnum.INTERFACE_EXCEPTION.getValue())) {
					logger.error("快递查询接口返回异常");
					logger.error("Result:{}", result);
					logger.error("Message:{}", expressInquiriesDetail.getMessage());
					rtn = new ExpressInquiriesDetailBO();
					rtn.setLogisticCode(expNo);
					rtn.setShipperCode(expCode);
					rtn.setState(ExpressInquiriesDetailStateEnum.NO_INFO);
					return rtn;
				}
				
				if (expressInquiriesDetail.getStatus().equals(StatusEnum.NO_INFO.getValue())) {
					expressInquiriesDetail.setCom(expCode);
					expressInquiriesDetail.setNu(expNo);
				}
				
				rtn = ExpressConverter.convert(expressInquiriesDetail);
			} else {
				String result = api.inquiries(expCode, expNo);
				
				// JSON转换成JOPO
				ExpressTracesDetail expressTracesDetail = JSONObject.parseObject(result, ExpressTracesDetail.class);
				
				if (expressTracesDetail.getResult() != null && !expressTracesDetail.getResult()) {
					logger.error("快递查询接口返回异常");
					logger.error("Result:{}", result);
					logger.error("Message:{}", expressTracesDetail.getMessage());
					logger.error("ResultCode:{},{}", expressTracesDetail.getReturnCode(), ReturnCodeEnum.getEnum(expressTracesDetail.getReturnCode()).getLabel());
					rtn = new ExpressInquiriesDetailBO();
					rtn.setLogisticCode(expNo);
					rtn.setShipperCode(expCode);
					rtn.setState(ExpressInquiriesDetailStateEnum.NO_INFO);
					return rtn;
				}
				
				rtn = ExpressConverter.convert(expressTracesDetail);
			}
		} catch (Exception e) {
			logger.error("快递查询异常", e);
		}
		return rtn;
	}
	
	/**
	 * 识别快递单号
	 */
	@Override
	public ExpressRecognitionDetailBO recognition(String expNo) {
		ExpressRecognitionDetailBO rtn = null;
		try {
			String result = api.recognition(expNo);
			
			// JSON转换成JOPO
			List<Express> expressList= JSONObject.parseArray(result, Express.class);
			
			rtn = ExpressConverter.convert(expressList);
		} catch (Exception e) {
			logger.error("单号识别异常", e);
		}
		return rtn;
	}

}