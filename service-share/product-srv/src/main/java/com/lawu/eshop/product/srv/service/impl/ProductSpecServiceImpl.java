package com.lawu.eshop.product.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.product.param.OperatorProductSpecParam;
import com.lawu.eshop.product.param.ProductSpecParam;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecBO;
import com.lawu.eshop.product.srv.bo.ProductSpecBO;
import com.lawu.eshop.product.srv.converter.ProductSpecConverter;
import com.lawu.eshop.product.srv.domain.ProductSpecDO;
import com.lawu.eshop.product.srv.domain.ProductSpecDOExample;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductSpecDOView;
import com.lawu.eshop.product.srv.mapper.ProductSpecDOMapper;
import com.lawu.eshop.product.srv.mapper.extend.ProductSpecDOMapperExtend;
import com.lawu.eshop.product.srv.service.ProductSpecService;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    @Autowired
    private ProductSpecDOMapper productSpecDOMapper;

    @Autowired
    private ProductSpecDOMapperExtend productSpecDOMapperExtend;

    @Override
    public List<ProductSpecBO> getProductSpecByCategoryId(Integer productCategoryId) {
        ProductSpecDOExample example = new ProductSpecDOExample();
        example.createCriteria().andProductCategoryIdEqualTo(productCategoryId).andStatusEqualTo(true);
        List<ProductSpecDO> specDOS = productSpecDOMapper.selectByExample(example);
        return ProductSpecConverter.convertBOS(specDOS);
    }

    @Override
    @Transactional
    public void addProductSpec(ProductSpecParam param) {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setStatus(true);
        specDO.setGmtCreate(new Date());
        specDO.setGmtModified(new Date());
        specDO.setSpecName(param.getSpecName());
        specDO.setProductCategoryId(param.getProductCategoryId());
        specDO.setOrdinal(param.getOrdinal().byteValue());
        productSpecDOMapper.insertSelective(specDO);
    }

    @Override
    @Transactional
    public void editProductSpec(Long id, ProductSpecParam param) {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtModified(new Date());
        specDO.setSpecName(param.getSpecName());
        specDO.setOrdinal(param.getOrdinal().byteValue());
        specDO.setId(id);
        productSpecDOMapper.updateByPrimaryKeySelective(specDO);
    }

    @Override
    public Page<OperatorProductSpecBO> getOperatorProductSpecList(OperatorProductSpecParam param) {
        Page<OperatorProductSpecBO> page = new Page<>();
        page.setTotalCount(productSpecDOMapperExtend.getOperatorProductSpecListCount(param));
        page.setCurrentPage(param.getCurrentPage());
        List<OperatorProductSpecDOView> specDOViews = productSpecDOMapperExtend.getOperatorProductSpecList(param);
        page.setRecords(ProductSpecConverter.convertOperatorBOS(specDOViews));
        return page;
    }

    @Override
    @Transactional
    public void delProductSpec(Long id) {
        ProductSpecDO specDO = new ProductSpecDO();
        specDO.setGmtModified(new Date());
        specDO.setStatus(false);
        specDO.setId(id);
        productSpecDOMapper.updateByPrimaryKeySelective(specDO);
    }

    @Override
    public ProductSpecBO getSpecDetailById(Long id) {
        ProductSpecDO specDO = productSpecDOMapper.selectByPrimaryKey(id);
        return ProductSpecConverter.convertBO(specDO);
    }
}
