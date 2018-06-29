package com.lawu.eshop.user.srv.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.dto.PropertyDTO;
import com.lawu.eshop.user.param.ListPropertyParam;
import com.lawu.eshop.user.srv.bo.PropertyBO;
import com.lawu.eshop.user.srv.converter.PropertyConverter;
import com.lawu.eshop.user.srv.service.PropertyService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
@RestController
@RequestMapping(value = "property/")
public class PropertyController extends BaseController {

    @Autowired
    private PropertyService propertyService;

    /**
     * 保存系统参数信息
     *
     * @param name
     * @param value
     * @param remark
     * @return
     */
    @RequestMapping(value = "saveProperty", method = RequestMethod.POST)
    public Result saveProperty(@RequestParam String name, @RequestParam String value, @RequestParam String remark) {
        propertyService.saveProperty(name, value, remark);
        return successCreated();
    }

    /**
     * 根据ID删除系统参数信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteProperty/{id}", method = RequestMethod.DELETE)
    public Result deleteProperty(@PathVariable Long id) {
        PropertyBO propertyBO = propertyService.getPropertyById(id);
        if (propertyBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        propertyService.deletePropertyById(id);
        return successDelete();
    }

    /**
     * 根据ID修改系统参数信息
     *
     * @param id
     * @param name
     * @param value
     * @param remark
     * @return
     */
    @RequestMapping(value = "updateProperty/{id}", method = RequestMethod.PUT)
    public Result updateProperty(@PathVariable Long id, @RequestParam String name, @RequestParam String value, @RequestParam String remark) {
        PropertyBO propertyBO = propertyService.getPropertyById(id);
        if (propertyBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        propertyService.updatePropertyById(id, name, value, remark);
        return successCreated();
    }

    /**
     * 根据ID查询系统参数
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getProperty/{id}", method = RequestMethod.GET)
    public Result<PropertyDTO> getProperty(@PathVariable Long id) {
        PropertyBO propertyBO = propertyService.getPropertyById(id);
        if (propertyBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(PropertyConverter.convertDTO(propertyBO));
    }

    /**
     * 系统参数列表
     *
     * @param listPropertyParam
     * @return
     */
    @RequestMapping(value = "listProperty", method = RequestMethod.POST)
    public Result<Page<PropertyDTO>> listProperty(@RequestBody ListPropertyParam listPropertyParam) {
        Page<PropertyBO> propertyBOPage = propertyService.listProperty(listPropertyParam);
        Page<PropertyDTO> page = new Page<>();
        page.setRecords(PropertyConverter.convertDTO(propertyBOPage.getRecords()));
        page.setCurrentPage(propertyBOPage.getCurrentPage());
        page.setTotalCount(propertyBOPage.getTotalCount());
        return successGet(page);
    }
}
