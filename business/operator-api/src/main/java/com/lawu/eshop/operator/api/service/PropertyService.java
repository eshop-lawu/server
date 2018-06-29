package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.user.dto.PropertyDTO;
import com.lawu.eshop.user.param.ListPropertyParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
@FeignClient(value = "user-srv")
public interface PropertyService {

    /**
     * 保存系统参数
     *
     * @param name
     * @param value
     * @param remark
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "property/saveProperty")
    Result saveProperty(@RequestParam("name") String name, @RequestParam("value") String value, @RequestParam("remark") String remark);

    /**
     * 根据ID删除系统参数
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "property/deleteProperty/{id}")
    Result deletePropertyById(@PathVariable("id") Long id);

    /**
     * 根据ID修改系统参数
     *
     * @param id
     * @param name
     * @param value
     * @param remark
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "property/updateProperty/{id}")
    Result updatePropertyById(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("value") String value, @RequestParam("remark") String remark);

    /**
     * 根据ID查询系统参数
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "property/getProperty/{id}")
    Result<PropertyDTO> getPropertyById(@PathVariable("id") Long id);

    /**
     * 系统参数列表
     *
     * @param listPropertyParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "property/listProperty")
    Result<Page<PropertyDTO>> listProperty(@ModelAttribute ListPropertyParam listPropertyParam);
}
