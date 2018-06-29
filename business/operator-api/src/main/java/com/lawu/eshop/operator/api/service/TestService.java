package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.QueryPropertyDTO;
import com.lawu.eshop.property.param.TestQuery1Param;
import com.lawu.eshop.property.param.TestQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "property-srv")
public interface TestService {

	@RequestMapping(method = RequestMethod.POST, value = "property/query")
	Result<Page<QueryPropertyDTO>> query(@RequestBody TestQuery1Param param);

	@RequestMapping(method = RequestMethod.POST, value = "property/save")
	int save(@RequestBody TestQueryParam param);

	@RequestMapping(method = RequestMethod.DELETE, value = "property/delete/{propertyIds}")
	int delete(@PathVariable("propertyIds") String propertyIds);

	@RequestMapping(method = RequestMethod.GET, value = "property/get/{propertyId}")
	QueryPropertyDTO get(@PathVariable("propertyId") Long propertyId);

   
}
