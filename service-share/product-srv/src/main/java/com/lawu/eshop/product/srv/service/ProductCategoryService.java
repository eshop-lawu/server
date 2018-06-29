package com.lawu.eshop.product.srv.service;


import java.util.List;

import com.lawu.eshop.product.constant.ProductCategoryTypeEnum;
import com.lawu.eshop.product.param.OperatorProductCategoryParam;
import com.lawu.eshop.product.param.ProductCategoryParam;
import com.lawu.eshop.product.srv.bo.ProductCategoryBO;
import com.lawu.framework.core.page.Page;

/**
 * 产品服务接口
 *
 * @author yangqh
 * @date 2017/3/22
 */
public interface ProductCategoryService {

    /**
     * 查询所有产品分类
     *
     * @return
     */
    List<ProductCategoryBO> findAll();

    /**
     * 通过ID查询
     * @return
     */
	ProductCategoryBO getById(Integer id);
	
	/**
	 * 获取商品分类全名称
	 * @param id
	 * @return
	 */
	String getFullName(Integer id);

	/**
	 * 查询推荐商品类别
	 * @return
	 */
	List<ProductCategoryBO> listRecommendProductCategory();

	
	List<ProductCategoryBO> find(Long parentId);

	/**
	 * 根据商品类别ID查询商品完整ID
	 *
	 * @param id
	 * @return
	 */
	@Deprecated
	String getFullCategoryId(Integer id);

    Page<ProductCategoryBO> findOperatorAll(OperatorProductCategoryParam param);

    void addProductCategory(ProductCategoryParam param);

	void delProductCategory(Integer lid);

	void editProductCategory(Integer id, ProductCategoryParam param);

	void editHot(Integer id, ProductCategoryTypeEnum type);

	List<ProductCategoryBO> getHotProductCategory();
}
