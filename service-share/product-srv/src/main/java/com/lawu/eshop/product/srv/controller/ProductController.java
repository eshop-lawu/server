package com.lawu.eshop.product.srv.controller;

import com.alibaba.fastjson.JSONObject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.lawu.eshop.product.dto.*;
import com.lawu.eshop.product.param.*;
import com.lawu.eshop.product.srv.bo.*;
import com.lawu.eshop.product.srv.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.query.ProductDataQuery;
import com.lawu.eshop.product.query.ProductListQuery;
import com.lawu.eshop.product.srv.converter.ProductConverter;
import com.lawu.eshop.product.srv.domain.extend.ProductCityDOView;
import com.lawu.eshop.product.srv.service.ProductService;
import com.lawu.eshop.product.srv.service.SeckillActivityJoinService;
import com.lawu.eshop.product.srv.solr.service.ProductSolrService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.BeanUtil;
import com.lawu.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangqh
 * @date 2017/3/13
 */
@RestController
@RequestMapping(value = "product/")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSolrService productSolrService;

    @Autowired
    private SeckillActivityJoinService seckillActivityJoinService;

    /**
     * 查询商品列表
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "selectProduct", method = RequestMethod.POST)
    public Result<Page<ProductQueryDTO>> selectProduct(@RequestBody ProductDataQuery query) {
        Page<ProductQueryBO> page = productService.selectProduct(query);
        List<ProductQueryBO> list = page.getRecords();
        List<ProductQueryDTO> dtos = ProductConverter.convertDTOS(list);

        Page<ProductQueryDTO> retPage = new Page<>();
        retPage.setCurrentPage(query.getCurrentPage());
        retPage.setTotalCount(page.getTotalCount());
        retPage.setRecords(dtos);

        return successCreated(retPage);
    }

    /**
     * 商品批量操作
     *
     * @param ids           商品ID字符串
     * @param productStatus 目标修改的状态
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    @RequestMapping(value = "updateProductStatus", method = RequestMethod.PUT)
    public Result<List<Long>> updateProductStatus(@RequestParam String ids, @RequestParam ProductStatusEnum productStatus, @RequestParam Long merchantId) {
        List<Long> newUpIds = new ArrayList<>();
        int counts = productService.updateProductStatus(ids, productStatus, merchantId,newUpIds);
        if (counts == -1) {
            return successCreated(ResultCode.GOODS_PRODUCT_INVENTORY);
        }
        if (counts == 0 || counts != ids.split(",").length) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successCreated(newUpIds);
    }

    /**
     * 用户端商品详情，根据ID查询商品详情
     *
     * @param productId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectProductById", method = RequestMethod.GET)
    public Result<ProductInfoDTO> selectProductById(@RequestParam Long productId) {
        if (productId == null) {
            return successGet(ResultCode.ID_EMPTY);
        }

        // 商品基本信息
        ProductInfoBO productBO = productService.selectProductById(productId);
        if (productBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        ProductInfoDTO productDTO = new ProductInfoDTO();
        productDTO.setGmtCreate(productBO.getGmtCreate());
        productDTO.setId(productBO.getId());
        productDTO.setMerchantId(productBO.getMerchantId());
        productDTO.setMerchantUserNum(productBO.getMerchantNum());
        productDTO.setCategoryId(productBO.getCategoryId());
        productDTO.setName(productBO.getName());
        productDTO.setFeatureImage(productBO.getFeatureImage());
        productDTO.setImagesHeadUrl(productBO.getImagesHeadUrl());
        productDTO.setMaxPrice(StringUtil.moneyPrecisionConvert(productBO.getMaxPrice()));
        productDTO.setMinPrice(StringUtil.moneyPrecisionConvert(productBO.getMinPrice()));
        productDTO.setTotalSalesVolume(productBO.getTotalSalesVolume());
        productDTO.setTotalInventory(productBO.getTotalInventory());
        productDTO.setSpec(productBO.getSpec());
        productDTO.setContent(productBO.getContent());
        productDTO.setImageDetail(productBO.getImageDetail());
        productDTO.setAllowRefund(productBO.isAllowRefund());
        productDTO.setProductStatus(productBO.getProductStatus());
        return successGet(productDTO);
    }

    /**
     * 商家端编辑商品时，根据ID查询商品
     *
     * @param productId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectEditProductById", method = RequestMethod.GET)
    public Result<ProductEditInfoDTO> selectEditProductById(@RequestParam Long productId) throws Exception {
        if (productId == null) {
            return successCreated(ResultCode.ID_EMPTY, null);
        }

        // 商品基本信息
        ProductEditInfoBO productBO = productService.selectEditProductById(productId);
        if (productBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND, null);
        }
        ProductEditInfoDTO productDTO = new ProductEditInfoDTO();
        BeanUtil.copyProperties(productBO, productDTO);

        productDTO.setAllowRefund(productBO.isAllowRefund());
        productDTO.setKeywords(productBO.getKeywords());
        return successGet(productDTO);
    }

    /**
     * 添加、编辑商品
     *
     * @param product
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "saveProduct", method = RequestMethod.POST)
    public Result saveProduct(@RequestBody @Valid EditProductDataParam product,
                              BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        //编辑，判断是否存在参入抢购活动
        if (product.getProductId() > 0) {
            Boolean isJoin = seckillActivityJoinService.isJoinActivity(product.getProductId());
            if (isJoin) {
                return successCreated(ResultCode.SECKILL_ACTIVITY_PRODUCT_EXISTS);
            }
        }


        productService.eidtProduct(product);
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 操作库存
     *
     * @param productId
     * @param num       加减数量数量
     * @param flag      M-减、A-加
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "editTotalInventory", method = RequestMethod.POST)
    public Result editTotalInventory(@RequestParam Long productId, @RequestParam int num, @RequestParam String flag) {
        productService.editTotalInventory(productId, num, flag);
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 操作销量
     *
     * @param productId
     * @param num       加减数量数量
     * @param flag      M-减、A-加
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "editTotalSaleVolume", method = RequestMethod.POST)
    public Result editTotalSaleVolume(@RequestParam Long productId, @RequestParam int num, @RequestParam String flag) {
        productService.editTotalSaleVolume(productId, num, flag);
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 根据商品ID查询商品
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getProduct/{id}", method = RequestMethod.GET)
    public Result<ProductInfoDTO> getProduct(@PathVariable Long id) {
        ProductInfoBO productBO = productService.getProductById(id);
        if (productBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(ProductConverter.convertInfoDTO(productBO));
    }

    /**
     * 查询已审核的所有商品
     *
     * @param param
     * @return
     * @author zhangrc
     * @date 2017/4/25
     */
    @RequestMapping(value = "selectProductByPlat", method = RequestMethod.POST)
    public Result<Page<ProductPlatDTO>> selectProductByPlat(@RequestBody ProductParam param) {
        Page<ProductQueryBO> page = productService.selectProductPlat(param);
        List<ProductPlatDTO> dtoList = new ArrayList<>();
        if (!page.getRecords().isEmpty()) {
            for (ProductQueryBO productQueryBO : page.getRecords()) {
                ProductPlatDTO dto = new ProductPlatDTO();
                dto.setId(productQueryBO.getId());
                dto.setName(productQueryBO.getName());
                dto.setMerchantId(productQueryBO.getMerchantId());
                dtoList.add(dto);
            }
        }
        Page<ProductPlatDTO> pageDTO = new Page<>();
        pageDTO.setCurrentPage(page.getCurrentPage());
        pageDTO.setRecords(dtoList);
        pageDTO.setTotalCount(page.getTotalCount());
        return successCreated(pageDTO);
    }


    /**
     * 查询商家上架商品的总数量
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "selectProductCount", method = RequestMethod.GET)
    public Result<Integer> selectProductCount(@RequestParam Long merchantId) {
        Integer count = productService.selectProductCount(merchantId);
        return successGet(count);
    }

    /**
     * 查询所有上架中商品
     *
     * @return
     */
    @RequestMapping(value = "listProduct", method = RequestMethod.POST)
    public Result<List<ProductInfoDTO>> listProduct(@RequestBody ListProductParam listProductParam) {
        List<ProductInfoBO> productInfoBOS = productService.listProduct(listProductParam);
        if (productInfoBOS == null || productInfoBOS.isEmpty()) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        return successCreated(ProductConverter.convertInfoDTO(productInfoBOS));
    }

    /**
     * 更新商品平均日销量
     *
     * @param id
     * @param averageDailySales
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "updateAverageDailySales/{id}", method = RequestMethod.PUT)
    public Result updateAverageDailySales(@PathVariable Long id, @RequestParam BigDecimal averageDailySales) {
        ProductInfoBO productBO = productService.getProductById(id);
        if (productBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        productService.updateAverageDailySalesById(id, averageDailySales);
        return successCreated();
    }

    /**
     * 更新商品平均日销量
     *
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "executeProductAverageDailySales", method = RequestMethod.PUT)
    public Result executeProductAverageDailySales(@RequestParam Integer pageSize) {
        productService.executeProductAverageDailySales(pageSize);
        return successCreated();
    }

    /**
     * 重建商品索引
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "rebuildProductIndex", method = RequestMethod.GET)
    public Result rebuildProductIndex(@RequestParam Integer pageSize) {
        productService.rebuildProductIndex(pageSize);
        return successGet();
    }

    /**
     * 删除无效的商品索引
     *
     * @param typeEnum
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "delInvalidProductIndex", method = RequestMethod.GET)
    public Result delInvalidProductIndex(@RequestParam DelIndexTypeEnum typeEnum) {
        productService.delInvalidProductIndex(typeEnum);
        return successGet();
    }

    /**
     * 查询所有上架的商品
     *
     * @param listProductParam
     * @return
     */
    @RequestMapping(value = "listAllProduct", method = RequestMethod.POST)
    public Result<Page<ProductQueryDTO>> listAllProduct(@RequestBody ListProductParam listProductParam) {
        Page<ProductQueryBO> productQueryBOPage = productService.listAllProduct(listProductParam);
        Page<ProductQueryDTO> page = new Page<>();
        page.setCurrentPage(productQueryBOPage.getCurrentPage());
        page.setTotalCount(productQueryBOPage.getTotalCount());
        page.setRecords(ProductConverter.convertDTOS(productQueryBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 根据ids查询商品信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "listProductByIds", method = RequestMethod.GET)
    public Result<List<ProductSearchDTO>> listProductByIds(@RequestParam List<Long> ids) {
        List<ProductBO> productBOS = productService.listProductByIds(ids);
        return successGet(ProductConverter.convertSearchDTO(productBOS));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "soldOutProductByMerchantId")
    public Result soldOutProductByMerchantId(@RequestParam(value = "id") Long id) {
        productService.soldOutProductByMerchantId(id);
        return successCreated();
    }

    /**
     * 根据商品ID查询商品
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectProductRelateAdInfo/{id}", method = RequestMethod.GET)
    public Result<ProductRelateAdInfoDTO> selectProductRelateAdInfo(@PathVariable Long id) {
        ProductRelateAdInfoBO bo = productService.selectProductRelateAdInfo(id);
        if (bo == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }

        ProductRelateAdInfoDTO dto = new ProductRelateAdInfoDTO();
        dto.setImgUrl(bo.getImgUrl());
        dto.setName(bo.getName());

        return successGet(dto);
    }

    /**
     * 删除全部索引数据
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "delAllProductIndex", method = RequestMethod.GET)
    public Result delAllProductIndex() {
        productSolrService.deleteAll();
        return successGet();
    }

    /**
     * 运营平台强制下架广告
     *
     * @param id
     * @param remark
     * @return
     */
    @RequestMapping(value = "downOperatorById", method = RequestMethod.PUT)
    public Result downOperatorById(@RequestParam Long id, @RequestParam String remark) {
        productService.downOperatorById(id, remark);
        return successCreated();
    }

    /**
     * 统计不同类型下的商品数量
     *
     * @param merchantId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "countProduct/{merchantId}", method = RequestMethod.GET)
    public Result<ProductTypeCountDTO> countProduct(@PathVariable Long merchantId) {
        ProductTypeCountBO countBO = productService.countProduct(merchantId);
        return successGet(ProductConverter.countCountDTO(countBO));
    }

    /**
     * 商品列表
     *
     * @param merchantId
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listProduct/{merchantId}", method = RequestMethod.POST)
    public Result<Page<ProductListDTO>> listProduct(@PathVariable Long merchantId, @RequestBody ProductListQuery query) {
        Page<ProductListBO> listBOPage = productService.listProduct(merchantId, query);
        Page<ProductListDTO> page = new Page<>();
        page.setCurrentPage(listBOPage.getCurrentPage());
        page.setTotalCount(listBOPage.getTotalCount());
        page.setRecords(ProductConverter.convertListDTOS(listBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 更新商品位置
     *
     * @param merchantId
     * @param ids
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "updateProductPosition/{merchantId}", method = RequestMethod.PUT)
    public Result updateProductPosition(@PathVariable Long merchantId, @RequestParam String ids) {
        productService.updateProductPosition(merchantId, ids);
        return successCreated();
    }

    /**
     * 商品详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getProductDetail/{id}", method = RequestMethod.GET)
    public Result<ProductDetailDTO> getProductDetail(@PathVariable Long id) {
        ProductDetailBO detailBO = productService.getProductDetail(id);
        if (detailBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(ProductConverter.convertDetailDTO(detailBO));
    }


    /**
     * 保存商品
     *
     * @param product
     * @param result
     * @return
     * @since v2.8.1
     */
    @RequestMapping(value = "editProductUpgrade", method = RequestMethod.POST)
    public Result editProductUpgrade(@RequestBody @Valid EditProductUpgradeDataParam product, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        //编辑，判断是否存在参入抢购活动
        if (product.getProductId() > 0) {
            Boolean isJoin = seckillActivityJoinService.isJoinActivity(product.getProductId());
            if (isJoin) {
                return successCreated(ResultCode.SECKILL_ACTIVITY_PRODUCT_EXISTS);
            }
        }

        int retCode;
        try {
            retCode = productService.editProductUpgrade(product);
        } catch (ProductModelNotEmptyException ex) {
            //商品规格型号不能为空
            return successCreated(ex.getResultCode());
        } catch (TitleImageNotEmptyException ex) {
            //商品头部图片不能为空
            return successCreated(ex.getResultCode());
        } catch (ProductSpecOptionMismatchException ex) {
            //客户端传入的规格数据和规格选项，长度不匹配
            return successCreated(ex.getResultCode());
        } catch (ProductSpecOutSizeException ex) {
            //商品型号规格最多定义5个
            return successCreated(ex.getResultCode());
        } catch (InvalidPriceAndStockException ex) {
            //商品价格和库存为空
            return successCreated(ex.getResultCode());
        }
        return successCreated(retCode);
    }

    /**
     * 修改回显查询
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "getEditProductById/{productId}", method = RequestMethod.GET)
    public Result<ProductEditDetailDTO> getEditProductById(@PathVariable Long productId) {
        ProductEditDetailBO detailBO = productService.getEditProductById(productId);
        if (detailBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(ProductConverter.convertEditDetailDTO(detailBO));
    }

    /**
     * 查询商品spec
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "getProductSelectedSpecInfo/{productId}", method = RequestMethod.GET)
    public Result<List<ProductSpecJsonDTO>> getProductSelectedSpecInfo(@PathVariable Long productId) {
        ProductInfoBO productInfoBO = productService.getProductById(productId);
        List<ProductSpecJsonDTO> itemList = JSONObject.parseArray(productInfoBO.getSpecInfo(),ProductSpecJsonDTO.class);
        return successGet(itemList);
    }

    /**
     * 商品所在地列表
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listProductCity", method = RequestMethod.GET)
    public Result<ProductShipmentsPlaceDTO> listProductCity() {
        List<ProductCityDOView> doViews = productService.listProductCity();
        List<ProductCityDTO> cityDTOS = ProductConverter.convertProductCityDTOS(doViews);
        ProductShipmentsPlaceDTO placeDTO = new ProductShipmentsPlaceDTO();
        placeDTO.setCityDTOS(cityDTOS);
        return successGet(placeDTO);
    }

    /**
     * 商家添加的商品类别
     *
     * @param merchantId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listMerchantProductCategory/{merchantId}", method = RequestMethod.GET)
    public Result<MerchantProductCategoryDTO> listMerchantProductCategory(@PathVariable Long merchantId) {
        List<ProductCategoryTierBO> tierBOS = productService.listMerchantProductCategory(merchantId);
        List<ProductCategoryTierDTO> tierDTOS = ProductConverter.convertProductCategoryTierDTOS(tierBOS);
        MerchantProductCategoryDTO categoryDTO = new MerchantProductCategoryDTO();
        categoryDTO.setTierDTOS(tierDTOS);
        return successGet(categoryDTO);
    }

    /**
     * 查询所有商品，一级分类名称不为空
     *
     * @return
     */
    @RequestMapping(value = "getAllProduct", method = RequestMethod.GET)
    public Result<ProductAllRtnDTO> getAllProduct() {
        List<ProductAllBO> productAllBOList = productService.getAllProduct();
        List<ProductAllDTO> productAllDTOList = ProductConverter.convertProductAllDTOList(productAllBOList);
        ProductAllRtnDTO rtnDTO = new ProductAllRtnDTO();
        rtnDTO.setProductAllDTOList(productAllDTOList);
        return successCreated(rtnDTO);
    }

    /**
     * 商品型号兼容性处理
     *
     * @param productCompatibleParam
     * @return
     */
    @RequestMapping(value = "compatible", method = RequestMethod.POST)
    public Result compatible(@RequestBody ProductCompatibleParam productCompatibleParam) {
        productService.compatible(productCompatibleParam);
        return successCreated(ResultCode.SUCCESS);
    }
}
