package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.order.dto.ExpressInquiriesDTO;
import com.lawu.eshop.order.dto.ExpressRecognitionDetailDTO;
import com.lawu.eshop.order.param.ExpressQueryParam;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@FeignClient(value= "order-srv", path = "express/")
public interface ExpressService {
	
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
	Result<ExpressInquiriesDTO> inquiries(@RequestBody ExpressQueryParam param);
	
	/**
	 * 根据快递单号识别快递公司
	 * 
	 * @param param 查询物流轨迹参数
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月5日
	 */
	@RequestMapping(value = "recognition/{expNo}", method = RequestMethod.GET)
	Result<ExpressRecognitionDetailDTO> recognition(@PathVariable("expNo") String expNo);
}
