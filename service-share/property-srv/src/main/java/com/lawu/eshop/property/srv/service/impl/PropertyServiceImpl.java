package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.dto.PropertyDTO;
import com.lawu.eshop.property.srv.bo.PropertyBO;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.param.TestQuery1Param;
import com.lawu.eshop.property.param.TestQueryParam;
import com.lawu.eshop.property.srv.bo.QueryPropertyBO;
import com.lawu.eshop.property.srv.domain.PropertyDO;
import com.lawu.eshop.property.srv.domain.PropertyDOExample;
import com.lawu.eshop.property.srv.domain.PropertyDOExample.Criteria;
import com.lawu.eshop.property.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.property.srv.service.PropertyService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDOMapper propertyDOMapper;

    @Override
    public String getValue(String key) {
        PropertyDOExample example = new PropertyDOExample();
        example.createCriteria().andNameEqualTo(key);
        List<PropertyDO> list = propertyDOMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            String defaultValue = "";

            switch (key) {
                case PropertyType.CASH_SCALE:
                    defaultValue = PropertyType.CASH_SCALE_DEFAULT;
                    break;
                case PropertyType.CASH_GREATER_ONE_MINUS_MONEY:
                    defaultValue = PropertyType.CASH_GREATER_ONE_MINUS_MONEY_DEFAULT;
                    break;
                case PropertyType.CASH_MIN_MONEY:
                    defaultValue = PropertyType.CASH_MIN_MONEY_DEFAULT;
                    break;
                case PropertyType.MEMBER_THIRD_PAY_POINT:
                    defaultValue = PropertyType.MEMBER_THIRD_PAY_POINT_DEFAULT;
                    break;
                case PropertyType.MERCHANT_BONT:
                    defaultValue = PropertyType.MERCHANT_BONT_DEFAULT;
                    break;
                case PropertyType.PRODUCT_ORDER_MONEY_FREEZE_DAYS:
                    defaultValue = PropertyType.PRODUCT_ORDER_MONEY_FREEZE_DAYS_DEFAULT;
                    break;
                case PropertyType.DEPOSIT_REFUND_DIFF_DAYS:
                    defaultValue = PropertyType.DEPOSIT_REFUND_DIFF_DAYS_DEFAULT;
                    break;
                case PropertyType.ad_commission_0:
                    defaultValue = PropertyType.ad_commission_0_default;
                    break;
                case PropertyType.ad_commission_1:
                    defaultValue = PropertyType.ad_commission_1_default;
                    break;
                case PropertyType.ad_commission_2:
                    defaultValue = PropertyType.ad_commission_2_default;
                    break;
                case PropertyType.ad_commission_3:
                    defaultValue = PropertyType.ad_commission_3_default;
                    break;
                case PropertyType.love_account_scale:
                    defaultValue = PropertyType.love_account_scale_default;
                    break;
                case PropertyType.sale_commission_1:
                    defaultValue = PropertyType.sale_commission_1_default;
                    break;
                case PropertyType.sale_commission_2:
                    defaultValue = PropertyType.sale_commission_2_default;
                    break;
                case PropertyType.sale_commission_3:
                    defaultValue = PropertyType.sale_commission_3_default;
                    break;
                case PropertyType.sale_commission_add_scope:
                    defaultValue = PropertyType.sale_commission_add_scope_default;
                    break;
                default:
                    break;
            }
            return defaultValue;
        }
        return list.get(0).getValue();
    }

    @Override
    public List<String> getValues(String key) {
        PropertyDOExample example = new PropertyDOExample();
        example.createCriteria().andNameEqualTo(key);
        List<PropertyDO> list = propertyDOMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<String> values = new ArrayList<String>();
        for (PropertyDO pdo : list) {
            values.add(pdo.getValue());
        }
        return values;
    }

    @Override
    public List<PropertyBO> getAll() {
        PropertyDOExample example = new PropertyDOExample();
        List<PropertyDO> list = propertyDOMapper.selectByExample(example);
        List<PropertyBO> bos = new ArrayList<>();
        for (PropertyDO pdo : list) {
            PropertyBO bo = new PropertyBO();
            bo.setName(pdo.getName());
            bo.setValue(pdo.getValue());
            bos.add(bo);
        }
        return bos;
    }

    @Override
    public Map<String, BigDecimal> getAdCommissionPropertys() {
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        List<PropertyBO> list = this.getAll();
        for (PropertyBO dto : list) {
            if (PropertyType.ad_commission_0.equals(dto.getName())) {
                map.put(PropertyType.ad_commission_0, new BigDecimal(dto.getValue().toString())); // 基础比例
            } else if (PropertyType.ad_commission_1.equals(dto.getName())) {
                map.put(PropertyType.ad_commission_1, new BigDecimal(dto.getValue().toString())); // 上1级提成比例
            } else if (PropertyType.ad_commission_2.equals(dto.getName())) {
                map.put(PropertyType.ad_commission_2, new BigDecimal(dto.getValue().toString())); // 上2级提成比例
            } else if (PropertyType.ad_commission_3.equals(dto.getName())) {
                map.put(PropertyType.ad_commission_3, new BigDecimal(dto.getValue().toString())); // 上3级提成比例
            } else if (PropertyType.love_account_scale.equals(dto.getName())) {
                String love_account_scale = dto.getValue().toString();
                map.put(PropertyType.love_account_scale, new BigDecimal(love_account_scale)); // 爱心账户比例
                double d_love_account_scale = Double.parseDouble(love_account_scale);
                double d_acture_in = 1 - d_love_account_scale; // 用户实际进账比例：1-爱心账户比例
                map.put("acture_in_scale", new BigDecimal(String.valueOf(d_acture_in))); // 实际收入比例
            }
        }
        return map;
    }

    @Override
    public Map<String, BigDecimal> getSaleCommissionPropertys() {
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        List<PropertyBO> list = this.getAll();
        for (PropertyBO dto : list) {
            if (PropertyType.sale_commission_0.equals(dto.getName())) {
                map.put(PropertyType.sale_commission_0, new BigDecimal(dto.getValue().toString())); // 基础比例
            } else if (PropertyType.sale_commission_1.equals(dto.getName())) {
                map.put(PropertyType.sale_commission_1, new BigDecimal(dto.getValue().toString())); // 上1级提成比例
            } else if (PropertyType.sale_commission_2.equals(dto.getName())) {
                map.put(PropertyType.sale_commission_2, new BigDecimal(dto.getValue().toString())); // 上2级提成比例
            } else if (PropertyType.sale_commission_3.equals(dto.getName())) {
                map.put(PropertyType.sale_commission_3, new BigDecimal(dto.getValue().toString())); // 上3级提成比例
            } else if (PropertyType.sale_commission_add_scope.equals(dto.getName())) {
                map.put(PropertyType.sale_commission_add_scope, new BigDecimal(dto.getValue().toString())); // 上一个等级提成提升幅度
            } else if (PropertyType.love_account_scale.equals(dto.getName())) {
                String love_account_scale = dto.getValue().toString();
                map.put(PropertyType.love_account_scale, new BigDecimal(love_account_scale)); // 爱心账户比例
                double d_love_account_scale = Double.parseDouble(love_account_scale);
                double d_acture_in = 1 - d_love_account_scale; // 用户实际进账比例：1-爱心账户比例
                map.put("acture_in_scale", new BigDecimal(String.valueOf(d_acture_in))); // 实际收入比例
            }
        }
        return map;
    }

}
