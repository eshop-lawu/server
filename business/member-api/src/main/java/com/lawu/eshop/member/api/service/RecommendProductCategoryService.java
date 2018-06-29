package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.product.dto.RecommendProductCategoryDTO;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
@FeignClient(value = "product-srv")
public interface RecommendProductCategoryService {

    /**
     * 商品类别列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "recommendProductCategory/listAllRecommendProductCategory")
    Result<List<RecommendProductCategoryDTO>> listAllRecommendProductCategory();

    @RequestMapping(value = "recommendProductCategory/getHotRecommendProductCategory", method = RequestMethod.GET)
    Result<List<RecommendProductCategoryDTO>> getHotRecommendProductCategory();

}
