package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.ProductCategoryService;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.eshop.product.dto.ProductCategoryHotDTO;
import com.lawu.framework.web.Result;


@Service
class MockProductCategoryService implements ProductCategoryService {

    @Override
    public List<ProductCategoryDTO> findAll() {
        List<ProductCategoryDTO> list = new ArrayList<>();
        return list;
    }

    @Override
    public ProductCategoryDTO getById(@RequestParam("id") Integer id) {
        ProductCategoryDTO dto = new ProductCategoryDTO();
        return dto;
    }

    @Override
    public Result<List<ProductCategoryDTO>> find(@PathVariable("parentId") Integer parentId) {
        return null;
    }

    @Override
    public Result<List<ProductCategoryDTO>> listRecommendProductCategory() {
        return  null;
    }

    @Override
    public Result<ProductCategoryHotDTO> getHotProductCategory() {
        return null;
    }
}
