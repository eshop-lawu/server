package com.lawu.eshop.product.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.product.param.OperatorProductBrandParam;
import com.lawu.eshop.product.param.ProductBrandParam;
import com.lawu.eshop.product.srv.bo.OperatorProductBrandBO;
import com.lawu.eshop.product.srv.bo.ProductBrandBO;
import com.lawu.eshop.product.srv.converter.ProductBrandConverter;
import com.lawu.eshop.product.srv.domain.ProductBrandDO;
import com.lawu.eshop.product.srv.domain.ProductBrandDOExample;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDO;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDOExample;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductBrandDOView;
import com.lawu.eshop.product.srv.mapper.ProductBrandDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductCategoryeDOMapper;
import com.lawu.eshop.product.srv.mapper.extend.ProductBrandDOMapperExtend;
import com.lawu.eshop.product.srv.service.ProductBrandService;
import com.lawu.framework.core.page.Page;


/**
 * @author zhangyong
 * @date 2018/4/16.
 */
@Service
public class ProductBrandServiceImpl implements ProductBrandService {

    @Autowired
    private ProductBrandDOMapper productBrandDOMapper;

    @Autowired
    private ProductBrandDOMapperExtend productBrandDOMapperExtend;

    @Autowired
    private ProductCategoryeDOMapper productCategoryeDOMapper;

    @Override
    public List<ProductBrandBO> getProductBrandByCategoryId(Integer productCategoryId) {
        ProductBrandDOExample example = new ProductBrandDOExample();
        example.createCriteria().andProductCategoryIdEqualTo(productCategoryId).andStatusEqualTo(true);
        example.setOrderByClause("ordinal asc");
        List<ProductBrandDO> brandDOS = productBrandDOMapper.selectByExample(example);
        return ProductBrandConverter.covertBOS(brandDOS);
    }

    @Override
    public ProductBrandBO getProductBrandById(Long brandId) {
        if(brandId == null){
            return null;
        }
        ProductBrandDO productBrandDO = productBrandDOMapper.selectByPrimaryKey(brandId);
        return ProductBrandConverter.covertBO(productBrandDO);
    }

    @Override
    @Transactional
    public void addProductBrand(ProductBrandParam param) {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setProductCategoryId(param.getProductCategoryId());
        brandDO.setBrandName(param.getBrandName());
        brandDO.setOrdinal(param.getOrdinal().byteValue());
        brandDO.setStatus(true);
        brandDO.setGmtModified(new Date());
        brandDO.setGmtCreate(new Date());
        productBrandDOMapper.insertSelective(brandDO);
    }

    @Override
    public ProductBrandBO getProductBrandDetail(Long id) {
        ProductBrandDO brandDO = productBrandDOMapper.selectByPrimaryKey(id);
        return ProductBrandConverter.covertBO(brandDO);
    }

    @Override
    @Transactional
    public void editProductBrand(Long id, ProductBrandParam param) {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setBrandName(param.getBrandName());
        brandDO.setOrdinal(param.getOrdinal().byteValue());
        brandDO.setGmtModified(new Date());
        brandDO.setId(id);
        productBrandDOMapper.updateByPrimaryKeySelective(brandDO);
    }

    @Override
    public Page<OperatorProductBrandBO> getOperatorProductBrandList(OperatorProductBrandParam param) {
        Page<OperatorProductBrandBO> page = new Page<>();
        page.setCurrentPage(param.getCurrentPage());
        page.setTotalCount(productBrandDOMapperExtend.getOperatorProductBrandListCount(param));
        List<OperatorProductBrandDOView> brandDOViews = productBrandDOMapperExtend.getOperatorProductBrandList(param);
        page.setRecords(ProductBrandConverter.covertOperatorBOS(brandDOViews));
        return page;
    }

    @Override
    @Transactional
    public void delProductBrand(Long id) {
        ProductBrandDO brandDO = new ProductBrandDO();
        brandDO.setId(id);
        brandDO.setStatus(false);
        productBrandDOMapper.updateByPrimaryKeySelective(brandDO);
    }

    @Override
    public List<ProductBrandBO> getProductBrandByName(String brandName,Integer categoryId) {
        ProductBrandDOExample example = new ProductBrandDOExample();
        example.createCriteria().andProductCategoryIdEqualTo(categoryId).andBrandNameLike("%"+brandName+"%").andStatusEqualTo(true);
        example.setOrderByClause("ordinal asc");
        List<ProductBrandDO> brandDOS = productBrandDOMapper.selectByExample(example);
        return ProductBrandConverter.covertBOS(brandDOS);
    }

    @Override
    public List<ProductBrandBO> listProductBrand(Integer productCategoryId) {
        ProductBrandDOExample example = new ProductBrandDOExample();
        example.createCriteria().andProductCategoryIdEqualTo(productCategoryId).andStatusEqualTo(true);
        example.setOrderByClause("ordinal asc");
        List<ProductBrandDO> brandDOS = productBrandDOMapper.selectByExample(example);
        if (!brandDOS.isEmpty()) {
            brandDOS.remove(brandDOS.size() - 1);
        }

        ProductCategoryeDOExample categoryeDOExample = new ProductCategoryeDOExample();
        categoryeDOExample.createCriteria().andParentIdEqualTo(productCategoryId).andStatueEqualTo(true);
        List<ProductCategoryeDO> categoryeDOS = productCategoryeDOMapper.selectByExample(categoryeDOExample);
        if (!categoryeDOS.isEmpty()) {
            for (ProductCategoryeDO categoryeDO : categoryeDOS) {
                example.clear();
                example.createCriteria().andProductCategoryIdEqualTo(categoryeDO.getId()).andStatusEqualTo(true);
                example.setOrderByClause("ordinal asc");
                List<ProductBrandDO> list = productBrandDOMapper.selectByExample(example);
                if (!list.isEmpty()) {
                    list.remove(list.size() - 1);
                    brandDOS.addAll(list);
                }
            }
        }
        return ProductBrandConverter.covertBOS(brandDOS);
    }

}
