package com.lawu.eshop.operator.api.service;

import java.util.List;

import com.lawu.eshop.product.dto.ProductAllRtnDTO;
import com.lawu.eshop.product.param.ProductCompatibleParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.product.dto.ProductInfoDTO;
import com.lawu.eshop.product.dto.ProductPlatDTO;
import com.lawu.eshop.product.param.ListProductParam;
import com.lawu.eshop.product.param.ProductParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 广告查收所有的商品
 *
 * @author zhangrc
 * @date 2017/4/21
 */
@FeignClient(value = "product-srv")
public interface ProductService {


    /**
     * 查询所有上架的商品
     *
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "product/selectProductByPlat")
    Result<Page<ProductPlatDTO>> selectProductByPlat(@ModelAttribute ProductParam param);

    /**
     * 查询所有上架的商品
     *
     * @param listProductParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "product/listProduct")
    Result<List<ProductInfoDTO>> listProduct(@ModelAttribute ListProductParam listProductParam);

    /**
     * 重建商品索引
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/rebuildProductIndex")
    Result rebuildProductIndex(@RequestParam("pageSize") Integer pageSize);

    /**
     * 删除无效商品索引
     *
     * @param typeEnum
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/delInvalidProductIndex")
    Result delInvalidProductIndex(@RequestParam("typeEnum") DelIndexTypeEnum typeEnum);

    /**
     * 删除全部商品索引
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/delAllProductIndex")
    Result delAllProductIndex();

    @RequestMapping(method = RequestMethod.PUT, value = "product/soldOutProductByMerchantId")
    Result soldOutProductByMerchantId(@RequestParam(value = "id") Long id);
    
    @RequestMapping(value = "product/selectProductById", method = RequestMethod.GET)
    Result<ProductInfoDTO> selectProductById(@RequestParam("productId") Long productId);
    
    @RequestMapping(value = "product/downOperatorById", method = RequestMethod.PUT)
	Result downOperatorById(@RequestParam("id") Long id, @RequestParam("remark") String remark);

    /**
     * 查询所有商品，一级分类名称不为空
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "product/getAllProduct")
    Result<ProductAllRtnDTO> getAllProduct();

    /**
     * 商品型号兼容性处理
     *
     * @param productCompatibleParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "product/compatible")
    Result compatible(@RequestBody ProductCompatibleParam productCompatibleParam);
}
