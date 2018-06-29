package com.lawu.eshop.order.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.order.dto.ExpressInquiriesDTO;
import com.lawu.eshop.order.dto.ExpressRecognitionDetailDTO;
import com.lawu.eshop.order.param.ExpressQueryParam;
import com.lawu.eshop.order.srv.bo.ExpressInquiriesDetailBO;
import com.lawu.eshop.order.srv.bo.ExpressRecognitionDetailBO;
import com.lawu.eshop.order.srv.converter.ExpressConverter;
import com.lawu.eshop.order.srv.strategy.ExpressStrategy;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @author jiangxinjun
 * @date 2017年9月5日
 */
@RestController
@RequestMapping(value = "express")
public class ExpressController extends BaseController {
	
	@Autowired
	private ExpressStrategy expressStrategy;
	
	/**
	 * 根据快递单号和快递公司编码查询物流轨迹
	 * 快递公司非必填，可单独通过快递单号查询
	 * 
	 * @param param 查询物流轨迹参数
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月5日
	 */
	@RequestMapping(value = "inquiries", method = RequestMethod.PUT)
	public Result<ExpressInquiriesDTO> inquiries(@RequestBody @Validated ExpressQueryParam param, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
		ExpressInquiriesDetailBO expressInquiriesDetailBO = expressStrategy.recognitionWithInquiries(param.getExpCode(), param.getExpNo());
		if (expressInquiriesDetailBO == null) {
			return successCreated(ResultCode.THIRD_PARTY_LOGISTICS_INTERFACE_EXCEPTION);
		}
		ExpressInquiriesDTO rtn = ExpressConverter.convertExpressInquiriesDTO(expressInquiriesDetailBO);
		return successCreated(rtn);
	}
	
	/**
	 * 根据快递单号识别快递公司
	 * 
	 * @param param 查询物流轨迹参数
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月5日
	 */
	@RequestMapping(value = "recognition/{expNo}", method = RequestMethod.GET)
	public Result<ExpressRecognitionDetailDTO> recognition(@PathVariable("expNo") String expNo) {
		ExpressRecognitionDetailBO expressRecognitionDetailBO = expressStrategy.recognition(expNo);
		if (expressRecognitionDetailBO == null) {
			successCreated(ResultCode.THIRD_PARTY_LOGISTICS_INTERFACE_EXCEPTION);
		}
		ExpressRecognitionDetailDTO rtn = ExpressConverter.convert(expressRecognitionDetailBO);
		return successGet(rtn);
	}
}
