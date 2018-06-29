package com.lawu.eshop.product.srv.service;

import com.lawu.eshop.product.constant.CustomSpecStatusEnum;
import com.lawu.eshop.product.param.EditProductUpgradeDataParam;
import com.lawu.eshop.product.param.ProductSpecCustomParam;
import com.lawu.eshop.product.query.ProductCustomSpecPageQuery;
import com.lawu.eshop.product.srv.bo.ProductCustomSpecBO;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.framework.core.page.Page;

import java.util.List;

/**
 * 商品自定义类别
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月16日
 */
public interface ProductCustomSpecService {
	
	/**
	 * 查询自定义类型/品牌/规格
	 * 
	 * @param query
	 * @return
	 */
	Page<ProductCustomSpecBO> specPage(ProductCustomSpecPageQuery query);
	
	/**
	 * 自定义处理
	 * 
	 * @param id
	 * @param statusEnum
	 */
	void dealCustomSpec(Long id ,CustomSpecStatusEnum statusEnum);

	/**
	 * 保存商家自定义信息
	 * @param product
	 * @param productSpecList
	 * @param isExistCustom
	 * @return
	 */
    int saveCustomInfo(Long productId, EditProductUpgradeDataParam product, ProductDO productDO, List<ProductSpecCustomParam> productSpecList, boolean isExistCustom);
}
