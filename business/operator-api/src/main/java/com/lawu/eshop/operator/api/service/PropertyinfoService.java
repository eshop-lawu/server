package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;
import com.lawu.eshop.property.dto.PropertyInfoDTO;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.param.BackagePropertyinfoDataParam;
import com.lawu.eshop.property.param.PropertyInfoBackageParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author Yangqh
 * @date 2017年5月16日 下午2:32:29
 *
 */
@FeignClient(value = "property-srv")
public interface PropertyinfoService {

	/**
	 * 运营平台充值余额积分
	 * 
	 * @param dparam
	 * @return
	 * @author yangqh
	 * @date 2017年5月16日 下午4:40:38
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "propertyInfo/updateBalanceAndPoint")
	Result updateBalanceAndPoint(@RequestBody BackagePropertyinfoDataParam dparam);

	/**
	 * 运营平台冻结账号资金
	 * 
	 * @param userNum
	 * @param freeze
	 * @return
	 * @author yangqh
	 * @date 2017年5月16日 下午4:40:49
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "propertyInfo/updatePropertyinfoFreeze")
	Result updatePropertyinfoFreeze(@RequestParam("userNum") String userNum,
			@RequestParam("freeze") PropertyinfoFreezeEnum freeze);

	/**
	 * 运营平台查询用户资金冻结情况
	 * @param param
	 * @return
	 * @author yangqh
	 * @date 2017年5月16日 下午4:53:07
	 */
	@RequestMapping(method = RequestMethod.POST, value = "propertyInfo/getPropertyinfoPageList")
	Result<Page<PropertyInfoDTO>> getPropertyinfoPageList(@RequestBody PropertyInfoBackageParam param);

	/**
	 * 查询资产信息
	 * @param userNum
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "propertyInfo/getPropertyInfoMoney/{userNum}")
	Result<PropertyPointAndBalanceDTO> getPropertyInfoMoney(@PathVariable("userNum") String userNum);
}
