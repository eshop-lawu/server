package com.lawu.eshop.member.api.service;

import javax.validation.Valid;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.param.PointDetailQueryData1Param;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.framework.web.Result;

@FeignClient(value = "property-srv")
public interface PropertyInfoDataService {
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "propertyInfoData/doHanlderMinusPoint", method = RequestMethod.POST)
	public Result doHanlderMinusPoint(@RequestBody @Valid PropertyInfoDataParam param);

	/**
	 * 根据user_num、point_type、biz_id查询积分明细记录
	 * @param param
	 * @return
	 * @author yangqh
	 * @date 2017年6月15日 下午12:19:48
	 */
	@RequestMapping(value = "propertyInfoData/getPointDetailByUserNumAndPointTypeAndBizId", method = RequestMethod.POST)
	Result<Integer> getPointDetailByUserNumAndPointTypeAndBizId(@RequestBody PointDetailQueryData1Param param);

	/**
	 * 积分兑换抽奖
	 *
	 * @param param
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "propertyInfoData/doHanlderMinusPointWithLottery", method = RequestMethod.POST)
	Result doHanlderMinusPointWithLottery(@RequestBody PropertyInfoDataParam param);

}
