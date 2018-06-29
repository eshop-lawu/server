package com.lawu.eshop.operator.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.product.dto.OperatorProductCategoryDTO;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.eshop.product.param.EditRecommendProductCategoryParam;
import com.lawu.eshop.product.param.ListRecommendProductCategoryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
@FeignClient(value = "product-srv")
public interface RecommendProductCategoryService {

    /**
     * 保存推荐商品类别
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "recommendProductCategory/saveRecommendProductCategory")
    Result saveRecommendProductCategory(@ModelAttribute EditRecommendProductCategoryParam param);

    /**
     * 根据ID删除商品类别
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "recommendProductCategory/deleteRecommendProductCategory/{id}")
    Result deleteRecommendProductCategoryById(@PathVariable("id") Long id);

    /**
     * 根据ID修改推荐商品类别信息
     *
     * @param id
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "recommendProductCategory/updateRecommendProductCategory/{id}")
    Result updateRecommendProductCategoryById(@PathVariable("id") Long id, @ModelAttribute EditRecommendProductCategoryParam param);

    /**
     * 根据ID查询商品类别
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "recommendProductCategory/getRecommendProductCategory/{id}")
    Result<OperatorProductCategoryDTO> getRecommendProductCategoryById(@PathVariable("id") Long id);

    /**
     * 商品类别列表
     *
     * @param listRecommendProductCategoryParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "recommendProductCategory/listRecommendProductCategory")
    Result<Page<OperatorProductCategoryDTO>> listRecommendProductCategory(@ModelAttribute ListRecommendProductCategoryParam listRecommendProductCategoryParam);

    /**
     * 查询推荐商品列表类别(一级)
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "productCategory/listRecommendProductCategory")
    Result<List<ProductCategoryDTO>> listRecommendProductCategory();

    /**
     * 编辑是否热门
     * @param id
     * @param isHot
     * @return
     */
    @RequestMapping(value = "recommendProductCategory/editHot/{id}", method = RequestMethod.PUT)
    Result editHot(@PathVariable("id") Long id, @RequestParam("isHot") Boolean isHot);

}
