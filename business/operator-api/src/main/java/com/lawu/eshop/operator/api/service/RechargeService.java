package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.BalanceAndPointListQueryDTO;
import com.lawu.eshop.property.param.RechargeQueryDataParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年5月16日 下午2:32:29
 *
 */
@FeignClient(value = "property-srv")
public interface RechargeService {


	@RequestMapping(method = RequestMethod.POST, value = "recharge/selectPropertyinfoList")
    Result<Page<BalanceAndPointListQueryDTO>> selectPropertyinfoList(@RequestBody RechargeQueryDataParam dparam);

    /**
     * 根据ID查询充值方式
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "recharge/getRechargePayType/{id}")
    Result<String> getRechargePayType(@PathVariable("id") Long id);

}
