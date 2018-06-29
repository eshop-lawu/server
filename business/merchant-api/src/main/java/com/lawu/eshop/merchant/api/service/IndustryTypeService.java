package com.lawu.eshop.merchant.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.constants.MerchantIndustryTypeEnum;
import com.lawu.eshop.mall.dto.IndustryTypeDTO;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/5/16.
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

    /**
     * 查询所有行业(不分组)
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "industryType/getAllIndustryList")
    Result<List<IndustryTypeDTO>> getAllIndustryList();

    /**
     * 根据行业类型查询行业列表
     *
     * @param industryTypeEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "industryType/listIndustryTypeByType")
    Result<List<IndustryTypeDTO>> listIndustryTypeByType(@RequestParam("industryTypeEnum") MerchantIndustryTypeEnum industryTypeEnum);
}
