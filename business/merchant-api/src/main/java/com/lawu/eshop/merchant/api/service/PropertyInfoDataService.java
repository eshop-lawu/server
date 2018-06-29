package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.param.PointDetailQueryData1Param;
import com.lawu.framework.web.Result;

@FeignClient(value = "property-srv")
public interface PropertyInfoDataService {
	
	/**
	 * 根据user_num、point_type、biz_id查询积分明细记录
	 * @param param
	 * @return
	 * @author yangqh
	 * @date 2017年6月15日 下午12:19:48
	 */
	@RequestMapping(value = "propertyInfoData/getPointDetailByUserNumAndPointTypeAndBizId", method = RequestMethod.POST)
	Result<Integer> getPointDetailByUserNumAndPointTypeAndBizId(@RequestBody PointDetailQueryData1Param param);
}
