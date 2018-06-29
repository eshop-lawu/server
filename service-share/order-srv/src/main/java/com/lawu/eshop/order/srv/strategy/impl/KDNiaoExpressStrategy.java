package com.lawu.eshop.order.srv.strategy.impl;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.order.constants.ExpressInquiriesDetailStateEnum;
import com.lawu.eshop.order.srv.bo.ExpressInquiriesDetailBO;
import com.lawu.eshop.order.srv.bo.ExpressRecognitionDetailBO;
import com.lawu.eshop.order.srv.converter.ExpressConverter;
import com.lawu.eshop.order.srv.strategy.ExpressStrategy;
import com.lawu.eshop.order.srv.utils.express.kdniao.KdniaoTrackQueryAPI;
import com.lawu.eshop.order.srv.utils.express.kdniao.bo.ExpressInquiriesDetail;
import com.lawu.eshop.order.srv.utils.express.kdniao.bo.ExpressRecognitionDetail;
import com.lawu.eshop.order.srv.utils.express.kdniao.constants.CodeEnum;

@Service("kDNiaoExpressStrategy")
public class KDNiaoExpressStrategy implements ExpressStrategy {
	
	private static Logger logger = LoggerFactory.getLogger(KDNiaoExpressStrategy.class);
	
	@Autowired
	KdniaoTrackQueryAPI api;
	
	/**
	 * 即时查询
	 */
	@Override
	public ExpressInquiriesDetailBO inquiries(String expCode, String expNo) {
		ExpressInquiriesDetailBO rtn = null;
		try {
			String result = api.orderTraces(expCode, expNo);
			
			// JSON转换成JOPO
			ExpressInquiriesDetail expressInquiriesDetail = JSONObject.parseObject(result, ExpressInquiriesDetail.class);
			
			if (!expressInquiriesDetail.getSuccess()) {
				logger.error("即时查询接口返回异常");
				logger.error("Result:{}", result);
				logger.error("Result:{}", expressInquiriesDetail.getReason());
				rtn = new ExpressInquiriesDetailBO();
				rtn.setLogisticCode(expNo);
				rtn.setShipperCode(expCode);
				rtn.setState(ExpressInquiriesDetailStateEnum.NO_INFO);
				return rtn;
			}
			
			// Trace按照时间倒序排序
			Collections.reverse(expressInquiriesDetail.getTraces());
			rtn = ExpressConverter.convert(expressInquiriesDetail);
		} catch (Exception e) {
			logger.error("即时查询异常", e);
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
			ExpressRecognitionDetail expressRecognitionDetail = JSONObject.parseObject(result, ExpressRecognitionDetail.class);
			
			if (!expressRecognitionDetail.getSuccess()) {
				logger.error("单号识别接口返回异常");
				logger.error("Result:{}", result);
				// 如果code不能空，打印详细的返回码信息
				if (expressRecognitionDetail.getCode() != null) {
					logger.error("CodeInfo:{}", CodeEnum.getEnum(expressRecognitionDetail.getCode()).getLabel());
				}
			}
			rtn = ExpressConverter.convert(expressRecognitionDetail);
		} catch (Exception e) {
			logger.error("单号识别异常", e);
		}
		return rtn;
	}
	
}