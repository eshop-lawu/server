package com.lawu.eshop.product.srv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.product.constant.ProductCategoryTypeEnum;
import com.lawu.eshop.product.param.OperatorProductCategoryParam;
import com.lawu.eshop.product.param.ProductCategoryParam;
import com.lawu.eshop.product.srv.bo.ProductCategoryBO;
import com.lawu.eshop.product.srv.converter.ProductCategoryConverter;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDO;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDOExample;
import com.lawu.eshop.product.srv.mapper.ProductCategoryeDOMapper;
import com.lawu.eshop.product.srv.service.ProductCategoryService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DataTransUtil;

/**
 * Created by shoubao-016 on 2017/3/22.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryeDOMapper productCategoryeDOMapper;

	@Override
	public List<ProductCategoryBO> findAll() {
		ProductCategoryeDOExample example = new ProductCategoryeDOExample();
		example.createCriteria().andStatueEqualTo(true);
		example.setOrderByClause("type DESC,ordinal ASC");
		List<ProductCategoryeDO> productCategoryeDOS = productCategoryeDOMapper.selectByExample(example);

		return ProductCategoryConverter.convertBOS(productCategoryeDOS);
	}

	@Override
	public ProductCategoryBO getById(Integer id) {
		if(id == null){
			return null;
		}
		ProductCategoryeDOExample example = new ProductCategoryeDOExample();
		example.createCriteria().andIdEqualTo(id).andStatueEqualTo(true);
		List<ProductCategoryeDO> productCategoryeDOS = productCategoryeDOMapper.selectByExample(example);
		return productCategoryeDOS.isEmpty() ? null : ProductCategoryConverter.convertBO(productCategoryeDOS.get(0));
	}

	@Override
	public String getFullName(Integer id) {
		ProductCategoryeDOExample example = new ProductCategoryeDOExample();
		example.createCriteria().andIdEqualTo(id);
		List<ProductCategoryeDO> productCategoryeDOS = productCategoryeDOMapper.selectByExample(example);
		if (productCategoryeDOS == null || productCategoryeDOS.isEmpty()) {
			return "";
		}

		String path = productCategoryeDOS.get(0).getPath();
		String[] paths = path.split("/");
		StringBuilder sb = new StringBuilder();
		if (paths.length == 1) {
			return productCategoryeDOS.get(0).getName();
		} else if (paths.length == 2) {
			example.clear();
			example.createCriteria().andIdEqualTo(Integer.valueOf(paths[0]));
			List<ProductCategoryeDO> productCategoryeDOS1 = productCategoryeDOMapper.selectByExample(example);
			if (productCategoryeDOS1 != null && !productCategoryeDOS1.isEmpty()) {
				sb.append(productCategoryeDOS1.get(0).getName());
			}

			example.clear();
			example.createCriteria().andIdEqualTo(Integer.valueOf(paths[1]));
			List<ProductCategoryeDO> productCategoryeDOS2 = productCategoryeDOMapper.selectByExample(example);
			if (productCategoryeDOS2 != null && !productCategoryeDOS2.isEmpty()) {
				sb.append("-").append(productCategoryeDOS2.get(0).getName());
			}
			return sb.toString();
		} else {
			example.clear();
			example.createCriteria().andIdEqualTo(Integer.valueOf(paths[0]));
			List<ProductCategoryeDO> productCategoryeDOS1 = productCategoryeDOMapper.selectByExample(example);
			if (productCategoryeDOS1 != null && !productCategoryeDOS1.isEmpty()) {
				sb.append(productCategoryeDOS1.get(0).getName());
			}

			example.clear();
			example.createCriteria().andIdEqualTo(Integer.valueOf(paths[1]));
			List<ProductCategoryeDO> productCategoryeDOS2 = productCategoryeDOMapper.selectByExample(example);
			if (productCategoryeDOS2 != null && !productCategoryeDOS2.isEmpty()) {
				sb.append("-").append(productCategoryeDOS2.get(0).getName());
			}

			example.clear();
			example.createCriteria().andIdEqualTo(Integer.valueOf(paths[2]));
			List<ProductCategoryeDO> productCategoryeDOS3 = productCategoryeDOMapper.selectByExample(example);
			if (productCategoryeDOS3 != null && !productCategoryeDOS3.isEmpty()) {
				sb.append("-").append(productCategoryeDOS3.get(0).getName());
			}
			return sb.toString();
		}
	}

	@Override
	public List<ProductCategoryBO> listRecommendProductCategory() {
		ProductCategoryeDOExample example = new ProductCategoryeDOExample();
		example.createCriteria().andStatueEqualTo(true).andLevelEqualTo(DataTransUtil.intToByte(1));
		example.setOrderByClause("type DESC,ordinal ASC");
		List<ProductCategoryeDO> productCategoryeDOS = productCategoryeDOMapper.selectByExample(example);
		return productCategoryeDOS.isEmpty() ? new ArrayList<>() : ProductCategoryConverter.convertBOS(productCategoryeDOS);
	}

	@Override
	public List<ProductCategoryBO> find(Long parentId) {
		if(parentId == null){
			return new ArrayList<>();
		}
		ProductCategoryeDOExample example = new ProductCategoryeDOExample();
		example.createCriteria().andStatueEqualTo(true).andParentIdEqualTo(parentId.intValue());
		example.setOrderByClause(" ordinal asc ");
		List<ProductCategoryeDO> productCategoryeDOS = productCategoryeDOMapper.selectByExample(example);
		return productCategoryeDOS.isEmpty() ? new ArrayList<>() : ProductCategoryConverter.convertBOS(productCategoryeDOS);
	}

	@Deprecated
	@Override
	public String getFullCategoryId(Integer id) {
		ProductCategoryeDO productCategoryeDO = productCategoryeDOMapper.selectByPrimaryKey(id);
		if(productCategoryeDO == null){
			return "";
		}
		
		StringBuilder fullCategoryId = new StringBuilder();
		while (true){
			fullCategoryId.append(productCategoryeDO.getId()).append(",");
			productCategoryeDO = productCategoryeDOMapper.selectByPrimaryKey(productCategoryeDO.getParentId());
			if(productCategoryeDO == null){
				String fullCategoryIdStr = fullCategoryId.toString();
				if(StringUtils.isNotEmpty(fullCategoryId.toString())){
					fullCategoryIdStr = fullCategoryIdStr.substring(0, fullCategoryIdStr.length() - 1);
				}
				return fullCategoryIdStr;
			}
		}
	}

	@Override
	public Page<ProductCategoryBO> findOperatorAll(OperatorProductCategoryParam param) {
		ProductCategoryeDOExample example = new ProductCategoryeDOExample();
		ProductCategoryeDOExample.Criteria criteria = example.createCriteria();
		criteria.andStatueEqualTo(true);
		if (param.getLevel() != null) {
			criteria.andLevelEqualTo(param.getLevel());
		}
		if (StringUtils.isNotEmpty(param.getName())) {
			criteria.andNameEqualTo(param.getName());
		}
		if (param.getParentId() != null && param.getParentId() > 0) {
			criteria.andParentIdEqualTo(param.getParentId());
		}
		example.setOrderByClause("parent_id ASC,ordinal ASC");
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<ProductCategoryeDO> productCategoryDOS = productCategoryeDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		Page<ProductCategoryBO> page = new Page<>();
		page.setCurrentPage(param.getCurrentPage());
		page.setTotalCount((int) productCategoryeDOMapper.countByExample(example));
		page.setRecords(ProductCategoryConverter.convertBOS(productCategoryDOS));
		return page;
	}

	@Override
	@Transactional
	public void addProductCategory(ProductCategoryParam param) {
		ProductCategoryeDO categoryDO = new ProductCategoryeDO();
		categoryDO.setName(param.getName());
		categoryDO.setLevel(param.getLevel());
		categoryDO.setParentId(param.getParentId());
		categoryDO.setImageUrl(param.getImageUrl());
		categoryDO.setOrdinal(param.getOrdinal());
		categoryDO.setIsVirtual(param.getIsVirtual());
		categoryDO.setType(ProductCategoryTypeEnum.PRODUCT_CATEGORY_NORMAL.getVal());
		productCategoryeDOMapper.insertSelective(categoryDO);

		ProductCategoryeDO old = new ProductCategoryeDO();
		old.setId(categoryDO.getId());
		if (param.getParentId() != null && param.getParentId() > 0) {
			old.setPath(param.getParentId() + "/" + categoryDO.getId());
		} else {
			old.setPath(categoryDO.getId().toString());
		}
		productCategoryeDOMapper.updateByPrimaryKeySelective(old);
	}

	@Override
	@Transactional
	public void delProductCategory(Integer id) {
		ProductCategoryeDO categoryDO = new ProductCategoryeDO();
		categoryDO.setStatue(false);
		categoryDO.setId(id);
		productCategoryeDOMapper.updateByPrimaryKeySelective(categoryDO);
	}

	@Override
	@Transactional
	public void editProductCategory(Integer id, ProductCategoryParam param) {
		ProductCategoryeDO categoryDO = new ProductCategoryeDO();
		categoryDO.setName(param.getName());
		categoryDO.setLevel(param.getLevel());
		categoryDO.setParentId(param.getParentId());
		categoryDO.setImageUrl(param.getImageUrl());
		categoryDO.setOrdinal(param.getOrdinal());
		categoryDO.setIsVirtual(param.getIsVirtual());
		if (param.getParentId() != null && param.getParentId() > 0) {
			categoryDO.setPath(param.getParentId() + "/" + id);
		} else {
			categoryDO.setPath(id.toString());
		}
		categoryDO.setId(id);
		productCategoryeDOMapper.updateByPrimaryKeySelective(categoryDO);
	}

	@Override
	@Transactional
	public void editHot(Integer id, ProductCategoryTypeEnum type) {
		ProductCategoryeDO categoryDO = new ProductCategoryeDO();
		categoryDO.setId(id);
		categoryDO.setType(type.getVal());
		productCategoryeDOMapper.updateByPrimaryKeySelective(categoryDO);
	}

	@Override
	public List<ProductCategoryBO> getHotProductCategory() {
		ProductCategoryeDOExample example = new ProductCategoryeDOExample();
		example.createCriteria().andStatueEqualTo(true).andTypeEqualTo(ProductCategoryTypeEnum.PRODUCT_CATEGORY_HOT.getVal());
		example.setOrderByClause("ordinal is null, ordinal");
		List<ProductCategoryeDO> categoryDOS = productCategoryeDOMapper.selectByExample(example);
		return ProductCategoryConverter.convertBOS(categoryDOS);
	}

}
