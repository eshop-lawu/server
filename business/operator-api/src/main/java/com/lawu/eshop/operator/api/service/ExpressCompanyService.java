package com.lawu.eshop.operator.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.mall.dto.ExpressCompanyDTO;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@FeignClient(value= "mall-srv")
public interface ExpressCompanyService {

    /**
     * 查询全部快递公司，根据ordinal排序
     */
    @RequestMapping(method = RequestMethod.GET, value = "expressCompany/list")
    Result<List<ExpressCompanyDTO>> list();
 
	/**
	 * 根据id查询快递公司
	 * 
	 * @param id 快递公司id
	 * @return
	 */
	@RequestMapping(value = "expressCompany/get/{id}", method = RequestMethod.GET)
	Result<ExpressCompanyDTO> get(@PathVariable("id") Integer id);
}
