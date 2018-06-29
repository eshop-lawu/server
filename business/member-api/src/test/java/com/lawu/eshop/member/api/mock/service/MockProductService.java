package com.lawu.eshop.member.api.mock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.ProductService;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.CommentProductInfoDTO;
import com.lawu.eshop.product.dto.MemberProductModelDTO;
import com.lawu.eshop.product.dto.ProductDetailDTO;
import com.lawu.eshop.product.dto.ProductInfoDTO;
import com.lawu.eshop.product.dto.ProductSearchDTO;
import com.lawu.eshop.product.dto.ProductShipmentsPlaceDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
class MockProductService extends BaseController implements ProductService {


    @Override
    public Result<ProductInfoDTO> selectProductById(@RequestParam("productId") Long productId) {
        ProductInfoDTO dto = new ProductInfoDTO();
        dto.setProductStatus(ProductStatusEnum.PRODUCT_STATUS_UP);
        dto.setMerchantId(1L);
        List<MemberProductModelDTO> spec = new ArrayList<>();
        MemberProductModelDTO ddto = new MemberProductModelDTO();
        ddto.setId(1L);
        ddto.setInventory(1);
        ddto.setName("name");
        ddto.setOriginalPrice(new BigDecimal("1"));
        ddto.setPrice(new BigDecimal("1"));
        dto.setSpec(spec);
        return successCreated(dto);
    }

    @Override
    public Result<CommentProductInfoDTO> selectCommentProductInfo(@PathVariable("productModelId") Long productModelId) {
        CommentProductInfoDTO dto = new CommentProductInfoDTO();
        dto.setName("name");
        dto.setModelName("modelName");
        dto.setPrice("12.21");
        return successCreated(dto);
    }

    @Override
    public Result<ProductInfoDTO> getProductById(@PathVariable("id") Long id) {
        return null;
    }

    @Override
    public Result<Integer> selectProductCount(@RequestParam("merchantId") Long merchantId) {
        return successCreated(new Integer("1"));
    }

    @Override
    public Result<List<ProductSearchDTO>> listProductByIds(@RequestParam("ids") List<Long> ids) {
        ProductSearchDTO dto = new ProductSearchDTO();
        dto.setProductId(1L);
        List<ProductSearchDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

    @Override
    public Result<ProductDetailDTO> getProductDetail(@RequestParam("id") Long id) {
        return null;
    }

    @Override
    public Result<ProductShipmentsPlaceDTO> listProductCity() {
        return null;
    }
}
