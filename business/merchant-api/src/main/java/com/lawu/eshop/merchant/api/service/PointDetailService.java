package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.PointDetailDTO;
import com.lawu.eshop.property.param.PointDetailQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/30
 */
@FeignClient(value = "property-srv")
public interface PointDetailService {
	
	/**
     * 根据用户编号和查询参数查询交易明细
     * 
     * @param userNo 用户编号
     * @param pointDetailQueryParam 查询参数
     * @return
     */
    @RequestMapping(value = "pointDetail/findPageByUserNum/{userNum}", method = RequestMethod.POST)
    public Result<Page<PointDetailDTO>> findPageByUserNum(@PathVariable("userNum") String userNum, @RequestBody PointDetailQueryParam pointDetailQueryParam);
	
}
