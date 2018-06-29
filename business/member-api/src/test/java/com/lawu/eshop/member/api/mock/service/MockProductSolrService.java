package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.ProductSolrService;
import com.lawu.eshop.product.dto.ProductSearchDTO;
import com.lawu.eshop.product.dto.ProductSearchWordDTO;
import com.lawu.eshop.product.param.ProductListSearchParam;
import com.lawu.eshop.product.param.ProductSearchParam;
import com.lawu.eshop.product.param.ProductSearchRealParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockProductSolrService extends BaseController implements ProductSolrService {

    @Override
    public Result<Page<ProductSearchDTO>> listProductByCategoryId(@ModelAttribute ProductSearchRealParam param) {
        ProductSearchDTO dto = new ProductSearchDTO();
        List<ProductSearchDTO> list = new ArrayList<>();
        list.add(dto);
        Page<ProductSearchDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<Page<ProductSearchDTO>> listRecommendProduct(@ModelAttribute ProductSearchRealParam param) {
        ProductSearchDTO dto = new ProductSearchDTO();
        List<ProductSearchDTO> list = new ArrayList<>();
        list.add(dto);
        Page<ProductSearchDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<Page<ProductSearchDTO>> listYouLikeProduct(@ModelAttribute ProductSearchParam productSearchParam) {
        ProductSearchDTO dto = new ProductSearchDTO();
        List<ProductSearchDTO> list = new ArrayList<>();
        list.add(dto);
        Page<ProductSearchDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<Page<ProductSearchDTO>> listProductByName(@ModelAttribute ProductSearchRealParam param) {
        ProductSearchDTO dto = new ProductSearchDTO();
        List<ProductSearchDTO> list = new ArrayList<>();
        list.add(dto);
        Page<ProductSearchDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<List<ProductSearchWordDTO>> listProductSearchWord(@RequestParam("name") String name) {
        ProductSearchWordDTO dto = new ProductSearchWordDTO();
        List<ProductSearchWordDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

    @Override
    public List<ProductSearchDTO> findProductSearchList(@RequestBody ProductSearchParam searchParam) {
        ProductSearchDTO dto = new ProductSearchDTO();
        dto.setName("name");
        dto.setContent("content");
        dto.setFeatureImage("1.jpg");
        dto.setOriginalPrice(Double.parseDouble("2"));
        dto.setPrice(Double.parseDouble("2"));
        dto.setProductId(1L);
        dto.setSalesVolume(2);
        List<ProductSearchDTO> list = new ArrayList<>();
        list.add(dto);
        return list;
    }

    @Override
    public Result<Page<ProductSearchDTO>> listProduct(@RequestBody ProductListSearchParam param) {
        return null;
    }
}
