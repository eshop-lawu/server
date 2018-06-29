package com.lawu.eshop.mall.srv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.mall.constants.MerchantIndustryTypeEnum;
import com.lawu.eshop.mall.srv.bo.IndustryTypeBO;
import com.lawu.eshop.mall.srv.converter.IndustryTypeConverter;
import com.lawu.eshop.mall.srv.domain.IndustryTypeDO;
import com.lawu.eshop.mall.srv.domain.IndustryTypeDOExample;
import com.lawu.eshop.mall.srv.mapper.IndustryTypeDOMapper;
import com.lawu.eshop.mall.srv.service.IndustryTypeService;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/4/5.
 */
@Service
public class IndustryTypeServiceImpl implements IndustryTypeService {

    @Autowired
    private IndustryTypeDOMapper industryTypeDOMapper;

    @Override
    public List<IndustryTypeBO> listIndustryType() {
        IndustryTypeDOExample industryTypeDOExample = new IndustryTypeDOExample();
        industryTypeDOExample.createCriteria().andParentIdEqualTo(new Short("0")).andStatusEqualTo(DataTransUtil.intToByte(1)).andTypeEqualTo(MerchantIndustryTypeEnum.ENTITY_INDUSTRY.getVal());
        List<IndustryTypeDO> industryTypeDOS = industryTypeDOMapper.selectByExample(industryTypeDOExample);
        List<IndustryTypeBO> industryTypeBOS = IndustryTypeConverter.convertBO(industryTypeDOS);
        if (industryTypeBOS != null && !industryTypeBOS.isEmpty()) {
            for (IndustryTypeBO industryTypeBO : industryTypeBOS) {
                industryTypeDOExample.clear();
                industryTypeDOExample.createCriteria().andParentIdEqualTo(new Short(String.valueOf(industryTypeBO.getId()))).andStatusEqualTo(DataTransUtil.intToByte(1)).andTypeEqualTo(MerchantIndustryTypeEnum.ENTITY_INDUSTRY.getVal());
                industryTypeDOS = industryTypeDOMapper.selectByExample(industryTypeDOExample);
                industryTypeBO.setIndustryTypeBOList(IndustryTypeConverter.convertBO(industryTypeDOS));
            }
        }
        return industryTypeBOS;
    }

    @Override
    public List<IndustryTypeBO> listIndustryTypeByParentId(Short parentId) {
        IndustryTypeDOExample industryTypeDOExample = new IndustryTypeDOExample();
        industryTypeDOExample.createCriteria().andParentIdEqualTo(parentId).andStatusEqualTo(DataTransUtil.intToByte(1));
        List<IndustryTypeDO> industryTypeDOS = industryTypeDOMapper.selectByExample(industryTypeDOExample);
        return IndustryTypeConverter.convertBO(industryTypeDOS);
    }

    @Override
    public List<IndustryTypeBO> getAllIndustryList() {
        IndustryTypeDOExample example = new IndustryTypeDOExample();
        example.setOrderByClause("id asc");
        example.createCriteria().andStatusEqualTo(DataTransUtil.intToByte(1));
        List<IndustryTypeDO> industryTypeDOS = industryTypeDOMapper.selectByExample(example);
        return IndustryTypeConverter.convertBO(industryTypeDOS);
    }

    @Override
    public List<IndustryTypeBO> listIndustryTypeByType(MerchantIndustryTypeEnum industryTypeEnum) {
        IndustryTypeDOExample example = new IndustryTypeDOExample();
        example.createCriteria().andStatusEqualTo(DataTransUtil.intToByte(1)).andTypeEqualTo(industryTypeEnum.getVal());
        List<IndustryTypeDO> industryTypeDOS = industryTypeDOMapper.selectByExample(example);
        return IndustryTypeConverter.convertBO(industryTypeDOS);
    }

}
