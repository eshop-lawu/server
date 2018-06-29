package com.lawu.eshop.merchant.api.mock.service;

import java.util.List;

import com.lawu.eshop.product.dto.*;
import com.lawu.eshop.product.param.EditProductUpgradeDataParam;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.merchant.api.service.ProductService;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.CommentProductInfoDTO;
import com.lawu.eshop.product.dto.MerchantProductCategoryDTO;
import com.lawu.eshop.product.dto.ProductEditDetailDTO;
import com.lawu.eshop.product.dto.ProductEditInfoDTO;
import com.lawu.eshop.product.dto.ProductInfoDTO;
import com.lawu.eshop.product.dto.ProductListDTO;
import com.lawu.eshop.product.dto.ProductModelDataDTO;
import com.lawu.eshop.product.dto.ProductQueryDTO;
import com.lawu.eshop.product.dto.ProductRelateAdInfoDTO;
import com.lawu.eshop.product.dto.ProductSpecJsonDTO;
import com.lawu.eshop.product.dto.ProductTypeCountDTO;
import com.lawu.eshop.product.param.EditProductDataParam;
import com.lawu.eshop.product.query.ProductDataQuery;
import com.lawu.eshop.product.query.ProductListQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockProductService extends BaseController implements ProductService {
    @Override
    public Result<Page<ProductQueryDTO>> selectProduct(@RequestBody ProductDataQuery query) {
        return successCreated();
    }

    @Override
    public Result updateProductStatus(@RequestParam("ids") String ids, @RequestParam("productStatus") ProductStatusEnum productStatus, @RequestParam("merchantId") Long merchantId) {
        return successCreated();
    }

    @Override
    public Result<ProductEditInfoDTO> selectEditProductById(@RequestParam("productId") Long productId) {
        return successGet();
    }

    @Override
    public Result saveProduct_bak(@RequestBody EditProductDataParam product) {
        return successCreated();
    }

    @Override
    public Result saveProduct(@RequestBody EditProductDataParam product) {
        return successCreated();
    }

    @Override
    public Result<CommentProductInfoDTO> selectCommentProductInfo(@PathVariable("productModelId") Long productModelId) {
        CommentProductInfoDTO dto = new CommentProductInfoDTO();
        return successGet(dto);
    }

    @Override
    public Result<ProductInfoDTO> getProduct(@PathVariable("id") Long id) {
        ProductInfoDTO dto = new ProductInfoDTO();
        dto.setName("test");
        dto.setFeatureImage("pic");
        return successGet(dto);
    }

    @Override
    public Result<Integer> selectProductCount(@RequestParam("merchantId") Long merchantId) {
        return successCreated(new Integer(1));
    }

	@Override
	public Result<ProductRelateAdInfoDTO> selectProductRelateAdInfo(Long id) {
		return successGet(new ProductRelateAdInfoDTO());
	}

	@Override
	public Result<List<ProductModelDataDTO>> queryProductModel(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Boolean> isJoinActivity(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Result<ProductTypeCountDTO> countProduct(@RequestParam("merchantId") Long merchantId) {
        return null;
    }

    @Override
    public Result<Page<ProductListDTO>> listProduct(@PathVariable("merchantId") Long merchantId, @RequestBody ProductListQuery query) {
        return null;
    }

    @Override
    public Result updateProductPosition(@PathVariable("merchantId") Long merchantId, @RequestParam("ids") String ids) {
        return null;
    }

    @Override
    public Result editProductUpgrade(EditProductUpgradeDataParam product) {
        return null;
    }

    @Override
    public Result<List<ProductSpecJsonDTO>> getProductSelectedSpecInfo(Long productId) {
        return null;
    }

    @Override
    public Result<ProductEditDetailDTO> getEditProductById(Long productId) {
        return null;
    }

    @Override
    public Result<MerchantProductCategoryDTO> listMerchantProductCategory(@PathVariable("merchantId") Long merchantId) {
        return null;
    }

}
