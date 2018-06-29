package com.lawu.eshop.product.srv.service;

import java.util.List;

import com.lawu.eshop.product.param.EditRecommendProductCategoryParam;
import com.lawu.eshop.product.param.ListRecommendProductCategoryParam;
import com.lawu.eshop.product.srv.bo.RecommendProductCategoryBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
public interface RecommendProductCategoryService {

    /**
     * 保存商品类别
     *
     * @param param
     */
    void saveRecommendProductCategory(EditRecommendProductCategoryParam param);

    /**
     * 根据ID删除商品类别
     *
     * @param id
     */
    void deleteRecommendProductCategoryById(Long id);

    /**
     * 根据ID修改商品类别
     *
     * @param id
     * @param param
     */
    void updateRecommendProductCategoryById(Long id, EditRecommendProductCategoryParam param);

    /**
     * 根据ID查询商品类别
     *
     * @param id
     * @return
     */
    RecommendProductCategoryBO getRecommendProductCategoryById(Long id);

    /**
     * 商品类别列表
     *
     * @param listRecommendProductCategoryParam
     * @return
     */
    Page<RecommendProductCategoryBO> listRecommendProductCategory(ListRecommendProductCategoryParam listRecommendProductCategoryParam);

    /**
     * 商品类别列表
     *
     * @return
     */
    List<RecommendProductCategoryBO> listAllRecommendProductCategory();

    List<RecommendProductCategoryBO> getHotRecommendProductCategory();

    void editHot(Long id, Boolean isHot);
}
