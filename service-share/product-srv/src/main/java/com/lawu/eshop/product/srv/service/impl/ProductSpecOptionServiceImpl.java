package com.lawu.eshop.product.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.product.param.OperatorSpecOptionListParam;
import com.lawu.eshop.product.param.ProductSpecOptionParam;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecOptionBO;
import com.lawu.eshop.product.srv.bo.ProductSpecOptionBO;
import com.lawu.eshop.product.srv.converter.ProductSpecOptionConverter;
import com.lawu.eshop.product.srv.domain.ProductSpecOptionDO;
import com.lawu.eshop.product.srv.domain.ProductSpecOptionDOExample;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductSpecOptionDOView;
import com.lawu.eshop.product.srv.mapper.ProductSpecOptionDOMapper;
import com.lawu.eshop.product.srv.mapper.extend.ProductSpecDOMapperExtend;
import com.lawu.eshop.product.srv.service.ProductSpecOptionService;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
@Service
public class ProductSpecOptionServiceImpl implements ProductSpecOptionService {

    @Autowired
    private ProductSpecOptionDOMapper productSpecOptionDOMapper;

    @Autowired
    private ProductSpecDOMapperExtend productSpecDOMapperExtend;

    @Override
    public List<ProductSpecOptionBO> getSpecOptionBySpecId(Long productSpecId) {
        ProductSpecOptionDOExample example = new ProductSpecOptionDOExample();
        example.createCriteria().andProductSpecIdEqualTo(productSpecId).andStatusEqualTo(true);
        List<ProductSpecOptionDO> list = productSpecOptionDOMapper.selectByExample(example);
        return ProductSpecOptionConverter.convertBOS(list);
    }

    @Override
    @Transactional
    public void addProductSpecOption(ProductSpecOptionParam param) {
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setOrdinal(param.getOrdinal().byteValue());
        optionDO.setProductSpecId(param.getProductSpecId());
        optionDO.setOptionName(param.getOptionName());
        optionDO.setIconUrl(param.getIconUrl());
        optionDO.setStatus(true);
        optionDO.setGmtCreate(new Date());
        optionDO.setGmtModified(new Date());
        productSpecOptionDOMapper.insertSelective(optionDO);
    }

    @Override
    @Transactional
    public void editProductSpecOption(Long id, ProductSpecOptionParam param) {
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setOrdinal(param.getOrdinal().byteValue());
        optionDO.setOptionName(param.getOptionName());
        optionDO.setIconUrl(param.getIconUrl());
        optionDO.setGmtModified(new Date());
        optionDO.setId(id);
        productSpecOptionDOMapper.updateByPrimaryKeySelective(optionDO);
    }

    @Override
    @Transactional
    public void delProductSpecOption(Long id) {
        ProductSpecOptionDO optionDO = new ProductSpecOptionDO();
        optionDO.setId(id);
        optionDO.setStatus(false);
        productSpecOptionDOMapper.updateByPrimaryKeySelective(optionDO);
    }

    @Override
    public ProductSpecOptionBO getSpecOptionDetail(Long id) {
        ProductSpecOptionDO optionDO = productSpecOptionDOMapper.selectByPrimaryKey(id);

        return ProductSpecOptionConverter.convertBO(optionDO);
    }

    @Override
    public Page<OperatorProductSpecOptionBO> getOperatorSpecOptionList(OperatorSpecOptionListParam param) {
        Page<OperatorProductSpecOptionBO> page = new Page<>();
        page.setTotalCount(productSpecDOMapperExtend.getOperatorSpecOptionListCount(param));
        page.setCurrentPage(param.getCurrentPage());
        List<OperatorProductSpecOptionDOView> optionDOS = productSpecDOMapperExtend.getOperatorSpecOptionList(param);
        page.setRecords(ProductSpecOptionConverter.convertOperatorBOS(optionDOS));
        return page;
    }
}
