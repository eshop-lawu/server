package com.lawu.eshop.member.api.service;

import com.lawu.eshop.mall.dto.IndustryTypeDTO;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/5.
 */
@FeignClient(value = "mall-srv")
public interface IndustryTypeService {

    /**
     * 查询所有行业
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "industryType/listIndustryType")
    Result<List<IndustryTypeDTO>> listIndustryType();

    /**
     * 查询父行业下的所有行业
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "industryType/listIndustryType/{parentId}")
    Result<List<IndustryTypeDTO>> listIndustryTypeByParentId(@PathVariable("parentId") Short parentId);

}
