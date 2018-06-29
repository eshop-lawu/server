package com.lawu.eshop.mall.srv.controller;

import com.lawu.eshop.mall.srv.service.PropertyService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * <p>
 * Description: 系统参数服务
 * </p>
 * @author Yangqh
 * @date 2017年4月5日 下午6:49:08
 *
 */
@RestController
@RequestMapping(value = "property/")
public class PropertyController extends BaseController {

    @Autowired
    private PropertyService propertyService;

    /**
     * 根据name获取Value
     * @param name
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "getValue", method = RequestMethod.GET)
    public Result getValue(@RequestParam String name) {
    	
    	String value = propertyService.getValue(name);
        return successCreated(value);
    }


}
