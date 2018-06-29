package com.lawu.eshop.user.srv.service.impl;

import com.lawu.eshop.user.constants.PropertyType;
import com.lawu.eshop.user.param.ListPropertyParam;
import com.lawu.eshop.user.srv.bo.PropertyBO;
import com.lawu.eshop.user.srv.converter.PropertyConverter;
import com.lawu.eshop.user.srv.domain.PropertyDO;
import com.lawu.eshop.user.srv.domain.PropertyDOExample;
import com.lawu.eshop.user.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.user.srv.service.PropertyService;
import com.lawu.framework.core.page.Page;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDOMapper propertyDOMapper;

    @Override
    public void saveProperty(String name, String value, String remark) {
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setName(name);
        propertyDO.setValue(value);
        propertyDO.setRemark(remark);
        propertyDO.setGmtCreate(new Date());
        propertyDOMapper.insertSelective(propertyDO);
    }

    @Override
    public void deletePropertyById(Long id) {
        propertyDOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updatePropertyById(Long id, String name, String value, String remark) {
        PropertyDO propertyDO = new PropertyDO();
        propertyDO.setId(id);
        propertyDO.setName(name);
        propertyDO.setValue(value);
        propertyDO.setRemark(remark);
        propertyDO.setGmtModified(new Date());
        propertyDOMapper.updateByPrimaryKeySelective(propertyDO);
    }

    @Override
    public PropertyBO getPropertyById(Long id) {
        PropertyDO propertyDO = propertyDOMapper.selectByPrimaryKey(id);
        return PropertyConverter.convertBO(propertyDO);
    }

    @Override
    public Page<PropertyBO> listProperty(ListPropertyParam listPropertyParam) {
        PropertyDOExample propertyDOExample = new PropertyDOExample();
        propertyDOExample.setOrderByClause("id desc");
        RowBounds rowBounds = new RowBounds(listPropertyParam.getOffset(), listPropertyParam.getPageSize());

        Page<PropertyBO> page = new Page<>();
        page.setCurrentPage(listPropertyParam.getCurrentPage());
        page.setTotalCount(propertyDOMapper.countByExample(propertyDOExample));

        List<PropertyDO> propertyDOList = propertyDOMapper.selectByExampleWithRowbounds(propertyDOExample, rowBounds);
        page.setRecords(PropertyConverter.convertBO(propertyDOList));
        return page;
    }

    @Override
    public String getValue(String key) {
        PropertyDOExample example = new PropertyDOExample();
        example.createCriteria().andNameEqualTo(key);
        List<PropertyDO> list = propertyDOMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            String defaultValue = "";
            switch (key) {
                case PropertyType.GROWTH_VALUE_RATE:
                    defaultValue = PropertyType.GROWTH_VALUE_RATE_DEFAULT;
                    break;
                default:
                    break;
            }
            return defaultValue;
        }
        return list.get(0).getValue();
    }
}
