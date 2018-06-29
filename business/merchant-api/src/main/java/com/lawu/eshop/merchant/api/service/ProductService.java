package com.lawu.eshop.merchant.api.service;

import java.util.List;

import com.lawu.eshop.product.dto.*;
import com.lawu.eshop.product.param.EditProductUpgradeDataParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
import com.lawu.eshop.product.param.EditProductUpgradeDataParam;
import com.lawu.eshop.product.query.ProductDataQuery;
import com.lawu.eshop.product.query.ProductListQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 产品服务接口
 *
 * @author Yangqh
 * @date 2017/3/22
 */
@SuppressWarnings("rawtypes")
@FeignClient(value = "product-srv")
public interface ProductService {

    /**
     * 查询所有商品类型
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "product/selectProduct")
    Result<Page<ProductQueryDTO>> selectProduct(@RequestBody ProductDataQuery query);

    /**
     * 批量处理
     *
     * @param ids
     * @param productStatus
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "product/updateProductStatus")
    Result<List<Long>> updateProductStatus(@RequestParam("ids") String ids, @RequestParam("productStatus") ProductStatusEnum productStatus, @RequestParam("merchantId") Long merchantId);

    /**
     * 根据商品ID查询商品详情信息
     *
     * @param productId 商品ID
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/selectEditProductById")
    Result<ProductEditInfoDTO> selectEditProductById(@RequestParam("productId") Long productId);

    /**
     * @param product
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "product/saveProduct_bak")
    Result saveProduct_bak(@RequestBody EditProductDataParam product);

    /**
     * @param product
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "product/saveProduct")
    Result saveProduct(@RequestBody EditProductDataParam product);

    /**
     * 根据型号ID查询商品详情信息
     *
     * @param productModelId 型号ID
     * @return
     */
    @RequestMapping(value = "productModel/selectCommentProductInfo/{productModelId}", method = RequestMethod.GET)
    Result<CommentProductInfoDTO> selectCommentProductInfo(@PathVariable("productModelId") Long productModelId);

    /**
     * 根据商品ID查询商品详情信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "product/getProduct/{id}", method = RequestMethod.GET)
    Result<ProductInfoDTO> getProduct(@PathVariable("id") Long id);

    /* 查询商家上架商品的总数量
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/selectProductCount")
    Result<Integer> selectProductCount(@RequestParam("merchantId") Long merchantId);

    @RequestMapping(value = "product/selectProductRelateAdInfo/{id}", method = RequestMethod.GET)
    Result<ProductRelateAdInfoDTO> selectProductRelateAdInfo(@PathVariable("id") Long id);


    /**
     * 商品型号查询
     *
     * @param productId
     * @return
     * @author zhangrc
     * @date 2017/11/23
     */
    @RequestMapping(value = "productModel/queryProductModel/{productId}", method = RequestMethod.GET)
    Result<List<ProductModelDataDTO>> queryProductModel(@PathVariable("productId") Long productId);


    /**
     * 判断商品是否参入抢购活动
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "seckillActivityJoin/isJoinActivity/{productId}", method = RequestMethod.GET)
    Result<Boolean> isJoinActivity(@PathVariable("productId") Long productId);

    /**
     * 统计不同类型下的商品数量
     *
     * @param merchantId
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/countProduct/{merchantId}")
    Result<ProductTypeCountDTO> countProduct(@PathVariable("merchantId") Long merchantId);

    /**
     * 商品列表
     *
     * @param merchantId
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "product/listProduct/{merchantId}")
    Result<Page<ProductListDTO>> listProduct(@PathVariable("merchantId") Long merchantId, @RequestBody ProductListQuery query);

    /**
     * 更新商品位置
     *
     * @param merchantId
     * @param ids
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.PUT, value = "product/updateProductPosition/{merchantId}")
    Result updateProductPosition(@PathVariable("merchantId") Long merchantId, @RequestParam("ids") String ids);

    /**
     * 保存商品
     *
     * @param product
     * @return
     * @since 2.8.0.1
     */
    @RequestMapping(method = RequestMethod.POST, value = "product/editProductUpgrade")
    Result editProductUpgrade(@RequestBody EditProductUpgradeDataParam product);

    /**
     * 查询商品spec
     *
     * @param productId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/getProductSelectedSpecInfo/{productId}")
    Result<List<ProductSpecJsonDTO>> getProductSelectedSpecInfo(@PathVariable("productId") Long productId);

    /**
     * 修改商品回显查询
     *
     * @param productId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/getEditProductById/{productId}")
    Result<ProductEditDetailDTO> getEditProductById(@PathVariable("productId") Long productId);

    /**
     * 商家添加的商品类别
     *
     * @param merchantId
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/listMerchantProductCategory/{merchantId}")
    Result<MerchantProductCategoryDTO> listMerchantProductCategory(@PathVariable("merchantId") Long merchantId);

}

