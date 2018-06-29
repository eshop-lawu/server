package com.lawu.eshop.product.srv.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.constant.CustomTypeEnum;
import com.lawu.eshop.product.param.EditProductModelBeanParam;
import com.lawu.eshop.product.param.EditProductUpgradeDataParam;
import com.lawu.eshop.product.param.ProductSpecCustomParam;
import com.lawu.eshop.product.srv.domain.ProductDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.product.constant.CustomSpecStatusEnum;
import com.lawu.eshop.product.query.ProductCustomSpecPageQuery;
import com.lawu.eshop.product.srv.bo.ProductCustomSpecBO;
import com.lawu.eshop.product.srv.converter.ProductCustomSpecConverter;
import com.lawu.eshop.product.srv.domain.ProductCustomSpecDO;
import com.lawu.eshop.product.srv.domain.ProductCustomSpecDOExample;
import com.lawu.eshop.product.srv.domain.ProductCustomSpecDOExample.Criteria;
import com.lawu.eshop.product.srv.mapper.ProductCustomSpecDOMapper;
import com.lawu.eshop.product.srv.service.ProductCustomSpecService;
import com.lawu.framework.core.page.Page;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月16日
 */
@Service
public class ProductCustomSpecServiceImpl implements ProductCustomSpecService {
	
	@Autowired
	private ProductCustomSpecDOMapper productCustomSpecDOMapper;

	@Override
	public Page<ProductCustomSpecBO> specPage(ProductCustomSpecPageQuery query) {

		ProductCustomSpecDOExample example = new ProductCustomSpecDOExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusNotEqualTo(CustomSpecStatusEnum.DELETE.getVal());

		if (StringUtils.isNotEmpty(query.getRelateName())) {
			criteria.andRelateNameLike("%" + query.getRelateName() + "%");
		}
		if (query.getStatusEnum() != null) {
			criteria.andStatusEqualTo(query.getStatusEnum().getVal());
		}

		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		int count = (int) (productCustomSpecDOMapper.countByExample(example));
		example.setOrderByClause("id desc");
		List<ProductCustomSpecDO> list = productCustomSpecDOMapper.selectByExampleWithRowbounds(example, rowBounds);

		Page<ProductCustomSpecBO> page = new Page<>();
		page.setCurrentPage(query.getCurrentPage());
		page.setTotalCount(count);
		page.setRecords(ProductCustomSpecConverter.convertBOS(list));
		return page;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void dealCustomSpec(Long id, CustomSpecStatusEnum statusEnum) {
		ProductCustomSpecDO record = new ProductCustomSpecDO();
		record.setId(id);
		record.setStatus(statusEnum.getVal());
		record.setGmtModified(new Date());
		productCustomSpecDOMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int saveCustomInfo(Long productId, EditProductUpgradeDataParam product, ProductDO productDO, List<ProductSpecCustomParam> productSpecList, boolean isExistCustom) {
		if(org.apache.commons.lang.StringUtils.isNotBlank(product.getCategoryName())){
			ProductCustomSpecDO productCustomSpecDO = new ProductCustomSpecDO();
			productCustomSpecDO.setRelateId(productId);
			productCustomSpecDO.setRelateName(productDO.getCategoryName());
			productCustomSpecDO.setSpecName(productDO.getCategoryName());
			productCustomSpecDO.setCustomType(CustomTypeEnum.CATEGORY.getVal());
			productCustomSpecDO.setStatus(CustomSpecStatusEnum.ON_DEAL_WITH.getVal());
			productCustomSpecDO.setGmtCreate(new Date());
			productCustomSpecDO.setGmtModified(new Date());
			productCustomSpecDOMapper.insertSelective(productCustomSpecDO);
		}
		if(org.apache.commons.lang.StringUtils.isNotBlank(product.getCategorySubitemName())){
			ProductCustomSpecDO productCustomSpecDO = new ProductCustomSpecDO();
			productCustomSpecDO.setRelateId(new Long(productDO.getCategoryId()));
			productCustomSpecDO.setRelateName(productDO.getCategoryName()+"-"+productDO.getCategorySubitemName());
			productCustomSpecDO.setSpecName(productDO.getCategorySubitemName());
			productCustomSpecDO.setCustomType(CustomTypeEnum.CATEGORY.getVal());
			productCustomSpecDO.setStatus(CustomSpecStatusEnum.ON_DEAL_WITH.getVal());
			productCustomSpecDO.setGmtCreate(new Date());
			productCustomSpecDO.setGmtModified(new Date());
			productCustomSpecDOMapper.insertSelective(productCustomSpecDO);
		}
		if(org.apache.commons.lang.StringUtils.isNotBlank(product.getBrandName())){
			ProductCustomSpecDO productCustomSpecDO = new ProductCustomSpecDO();
			if (product.getCategorySubitemId() != null) {
				productCustomSpecDO.setRelateId(productDO.getCategorySubitemId());
				productCustomSpecDO.setRelateName(productDO.getCategoryName()+"-"+productDO.getCategorySubitemName()+"-"+productDO.getBrandName());
			} else {
				productCustomSpecDO.setRelateId(new Long(productDO.getCategoryId()));
				productCustomSpecDO.setRelateName(productDO.getCategoryName()+"-"+productDO.getBrandName());
			}
			productCustomSpecDO.setSpecName(productDO.getBrandName());
			productCustomSpecDO.setCustomType(CustomTypeEnum.BRAND.getVal());
			productCustomSpecDO.setStatus(CustomSpecStatusEnum.ON_DEAL_WITH.getVal());
			productCustomSpecDO.setGmtCreate(new Date());
			productCustomSpecDO.setGmtModified(new Date());
			productCustomSpecDOMapper.insertSelective(productCustomSpecDO);
		}
		if(isExistCustom){
			List<EditProductModelBeanParam> productModelBeanParamList = product.getProductModelParam();
			for (EditProductModelBeanParam modelParam : productModelBeanParamList) {
				List<String> specOptionIdArray = Arrays.asList(modelParam.getSpecOptionIdStr().split(","));
				List<String> specOptionNameArray = Arrays.asList(modelParam.getSpecOptionNameStr().split(","));
				for (int i = 0 ; i < specOptionIdArray.size() ; i++){
					if (new Integer(specOptionIdArray.get(i)).intValue() < 0) {
						ProductCustomSpecDO productCustomSpecDO = new ProductCustomSpecDO();
						productCustomSpecDO.setRelateId(productSpecList.get(i).getSpecId() < 0 ? 0 : productSpecList.get(i).getSpecId());
						if(org.apache.commons.lang.StringUtils.isNotBlank(productDO.getCategorySubitemName())){
							productCustomSpecDO.setRelateName(productDO.getCategoryName()+"-"+productDO.getCategorySubitemName()+"-"+productSpecList.get(i).getSpecName()+"-"+specOptionNameArray.get(i));
						} else {
							productCustomSpecDO.setRelateName(productDO.getCategoryName()+"-"+productSpecList.get(i).getSpecName()+"-"+specOptionNameArray.get(i));
						}
						productCustomSpecDO.setSpecName(specOptionNameArray.get(i));
						productCustomSpecDO.setCustomType(CustomTypeEnum.SPEC_OPTION.getVal());
						productCustomSpecDO.setStatus(CustomSpecStatusEnum.ON_DEAL_WITH.getVal());
						productCustomSpecDO.setGmtCreate(new Date());
						productCustomSpecDO.setGmtModified(new Date());
						productCustomSpecDOMapper.insertSelective(productCustomSpecDO);
					}
				}
			}
		}
		return ResultCode.SUCCESS;
	}

}
