package com.lawu.eshop.mall.srv.service;

import java.util.List;

import com.lawu.eshop.mall.constants.MerchantIndustryTypeEnum;
import com.lawu.eshop.mall.srv.bo.IndustryTypeBO;

/**
 * @author meishuquan
 * @date 2017/4/5.
 */
public interface IndustryTypeService {

    /**
     * 查询所有行业
     *
     * @return
     */
    List<IndustryTypeBO> listIndustryType();

    /**
     * 查询父行业下的所有行业
     *
     * @param parentId
     * @return
     */
    List<IndustryTypeBO> listIndustryTypeByParentId(Short parentId);

    List<IndustryTypeBO> getAllIndustryList();

    /**
     * 根据行业类型查询行业列表
     *
     * @param industryTypeEnum
     * @return
     * @author meishuquan
     */
    List<IndustryTypeBO> listIndustryTypeByType(MerchantIndustryTypeEnum industryTypeEnum);
}
