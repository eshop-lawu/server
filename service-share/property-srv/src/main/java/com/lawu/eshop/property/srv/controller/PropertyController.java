package com.lawu.eshop.property.srv.controller;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.property.dto.PropertyDTO;
import com.lawu.eshop.property.srv.bo.PropertyBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.property.dto.QueryPropertyDTO;
import com.lawu.eshop.property.param.TestQuery1Param;
import com.lawu.eshop.property.param.TestQueryParam;
import com.lawu.eshop.property.srv.bo.QueryPropertyBO;
import com.lawu.eshop.property.srv.service.PropertyService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.BeanUtil;

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

    /**
     * 
     * @param name
     * @return
     */
    @RequestMapping(value = "getValues", method = RequestMethod.GET)
    public List<String> getValues(@PathVariable("name") String name) {
    	
    	List<String> values = propertyService.getValues(name);
        return values;
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public Result<List<PropertyDTO>> getAll() {
        List<PropertyBO> bos = propertyService.getAll();
        List<PropertyDTO> dtos = new ArrayList<>();
        for(PropertyBO bo : bos){
            PropertyDTO dto = new PropertyDTO();
            dto.setName(bo.getName());
            dto.setValue(bo.getValue());
            dtos.add(dto);
        }
        return successGet(dtos);
    }
}
