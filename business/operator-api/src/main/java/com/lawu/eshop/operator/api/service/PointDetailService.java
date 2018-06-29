package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.property.dto.PointDetailBackageDTO;
import com.lawu.eshop.property.param.TransactionDetailQueryForBackageParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Sunny
 * @date 2017/3/30
 */
@FeignClient(value = "property-srv")
public interface PointDetailService {
	
	/**
     * 根据用户编号和查询参数查询交易明细
     * 
     * @param param 查询参数
     * @return
     */
    @RequestMapping(value = "pointDetail/getBackagePointPageList", method = RequestMethod.POST)
    Result<Page<PointDetailBackageDTO>> getBackagePointPageList(@RequestBody TransactionDetailQueryForBackageParam param);
	
}
