package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.eshop.product.dto.ProductCategoryHotDTO;
import com.lawu.framework.web.Result;

/**
 * 产品服务接口
 *
 * @author Yangqh
 * @date 2017/3/22
 */
@FeignClient(value= "product-srv")
public interface ProductCategoryService {

    /**
     * 查询所有商品类型
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "productCategory/findAll")
    List<ProductCategoryDTO> findAll();
    
    /**
     * 根据ID查询商品分类
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "productCategory/getById")
    ProductCategoryDTO getById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "productCategory/find/{parentId}")
	Result<List<ProductCategoryDTO>> find(@PathVariable("parentId") Integer parentId);

    @RequestMapping(method = RequestMethod.GET, value = "productCategory/listRecommendProductCategory")
    Result<List<ProductCategoryDTO>> listRecommendProductCategory();

    @RequestMapping(value = "productCategory/getHotProductCategory", method = RequestMethod.GET)
    Result<ProductCategoryHotDTO> getHotProductCategory();
}
