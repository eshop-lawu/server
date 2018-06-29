package com.lawu.eshop.member.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.dto.AdPlatformProductDTO;
import com.lawu.eshop.member.api.service.AdPlatformService;
import com.lawu.eshop.member.api.service.ProductService;
import com.lawu.eshop.member.api.service.ProductSolrService;
import com.lawu.eshop.member.api.service.RecommendProductCategoryService;
import com.lawu.eshop.product.constant.ProductTopPositionEnum;
import com.lawu.eshop.product.dto.ProductSearchDTO;
import com.lawu.eshop.product.dto.ProductSearchWordDTO;
import com.lawu.eshop.product.dto.RecommendProductCategoryDTO;
import com.lawu.eshop.product.dto.ShoppingProductDTO;
import com.lawu.eshop.product.param.ProductListSearchParam;
import com.lawu.eshop.product.param.ProductSearchParam;
import com.lawu.eshop.product.param.ProductSearchRealParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/3/30.
 */
@Api(tags = "productSolr")
@RestController
@RequestMapping(value = "productSolr/")
public class ProductSolrController extends BaseController {

    @Autowired
    private ProductSolrService productSolrService;

    @Autowired
    private RecommendProductCategoryService recommendProductCategoryService;

    @Autowired
    private AdPlatformService adPlatformService;

    @Autowired
    private ProductService productService;

    @AutoTesting
    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "根据商品类别查询商品信息", notes = "会员APP首页商品分类。[1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listProductByCategoryId", method = RequestMethod.GET)
    public Result<Page<ProductSearchDTO>> listProductByCategoryId(@ModelAttribute @ApiParam ProductSearchParam productSearchParam,
                                                                  @RequestParam @ApiParam(required = true, value = "商品类别ID") Integer categoryId) {
        ProductSearchRealParam param = new ProductSearchRealParam();
        param.setCurrentPage(productSearchParam.getCurrentPage());
        param.setPageSize(productSearchParam.getPageSize());
        param.setCategoryId(categoryId);
        return productSolrService.listProductByCategoryId(param);
    }

    @AutoTesting
    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "商品详情为你推荐", notes = "商品详情为你推荐(同类别按销量排行)。[1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listRecommendProduct", method = RequestMethod.GET)
    public Result<Page<ProductSearchDTO>> listRecommendProduct(@ModelAttribute @ApiParam ProductSearchParam productSearchParam,
                                                               @RequestParam @ApiParam(required = true, value = "商品类别ID") Integer categoryId,
                                                               @RequestParam @ApiParam(required = true, value = "商品ID") Long productId) {
        ProductSearchRealParam param = new ProductSearchRealParam();
        param.setCurrentPage(productSearchParam.getCurrentPage());
        param.setPageSize(productSearchParam.getPageSize());
        param.setCategoryId(categoryId);
        param.setProductId(productId);
        return productSolrService.listRecommendProduct(param);
    }

    @AutoTesting
    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "会员APP商品搜索", notes = "会员APP商品搜索。[1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listProductByName", method = RequestMethod.GET)
    public Result<Page<ProductSearchDTO>> listProductByName(@ModelAttribute @ApiParam ProductSearchParam productSearchParam,
                                                            @RequestParam @ApiParam(required = true, value = "商品名称") String name) {
        ProductSearchRealParam param = new ProductSearchRealParam();
        param.setCurrentPage(productSearchParam.getCurrentPage());
        param.setPageSize(productSearchParam.getPageSize());
        param.setName(name);
        return productSolrService.listProductByName(param);
    }

    @AutoTesting
    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "搜索词关联词频查询", notes = "根据搜索词推荐关联词和频率查询。 (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listProductSearchWord", method = RequestMethod.GET)
    public Result<List<ProductSearchWordDTO>> listProductSearchWord(@RequestParam @ApiParam(name = "name", required = true, value = "商品名称") String name) {
        return productSolrService.listProductSearchWord(name);
    }

    @AutoTesting
    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "要购物首页", notes = "要购物首页。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listProduct", method = RequestMethod.GET)
    public Result<ShoppingProductDTO> listProduct() {
        ShoppingProductDTO shoppingProductDTO = new ShoppingProductDTO();
        List<ProductSearchDTO> productSearchDTOS = new ArrayList<>();

        //商品分类
        Result<List<RecommendProductCategoryDTO>> recProCatResult = recommendProductCategoryService.getHotRecommendProductCategory();
        shoppingProductDTO.setRecommendProductCategoryDTOS(recProCatResult.getModel());

        
        //积分夺宝,商品顶部,抢购活动
        Result<List<AdPlatformProductDTO>> pointResult = adPlatformService.getAdPlatformProductTop();
        if (!pointResult.getModel().isEmpty()) {
            for (AdPlatformProductDTO adPlatformProductDTO : pointResult.getModel()) {
                ProductSearchDTO productSearchDTO = new ProductSearchDTO();
                if(adPlatformProductDTO.getProductId() != null){
                	productSearchDTO.setTypeEnum(ProductTopPositionEnum.SHOPPING_PRODUCT);
                }
                if(adPlatformProductDTO.getPostion() == PositionEnum.ACTIVITY_PRODUCT){
                	productSearchDTO.setTypeEnum(ProductTopPositionEnum.ACTIVITY_PRODUCT);
                }
                if(adPlatformProductDTO.getPostion() == PositionEnum.SHOPPING_INTEGRAL_INDIANA){
                	productSearchDTO.setTypeEnum(ProductTopPositionEnum.SHOPPING_INTEGRAL_INDIANA);
                }
                if(StringUtils.isNotBlank(adPlatformProductDTO.getLinkUrl())){
                	productSearchDTO.setTypeEnum(ProductTopPositionEnum.LINK);
                }
               
                productSearchDTO.setFeatureImage(adPlatformProductDTO.getMediaUrl());
                productSearchDTO.setLinkUrl(adPlatformProductDTO.getLinkUrl());
                productSearchDTOS.add(productSearchDTO);
            }
        }
        
        shoppingProductDTO.setTopProduct(productSearchDTOS);

        //今日推荐
        productSearchDTOS = new ArrayList<>();
        Result<List<AdPlatformProductDTO>> chooseResult = adPlatformService.getAdPlatformByTypePosition(TypeEnum.TYPE_PRODUCT, PositionEnum.POSITON_SHOP_CHOOSE);
        if (!chooseResult.getModel().isEmpty()) {
            List<Long> ids = new ArrayList<>();
            for (AdPlatformProductDTO adPlatformProductDTO : chooseResult.getModel()) {
                ids.add(adPlatformProductDTO.getProductId());
                /*Result<ProductInfoDTO> productInfoDTOResult = productService.getProductById(adPlatformDTO.getProductId());
                if (isSuccess(productInfoDTOResult)) {
                    ProductSearchDTO productSearchDTO = new ProductSearchDTO();
                    productSearchDTO.setProductId(adPlatformDTO.getProductId());
                    productSearchDTO.setFeatureImage(adPlatformDTO.getMediaUrl());
                    productSearchDTO.setName(adPlatformDTO.getTitle());
                    productSearchDTO.setSalesVolume(productInfoDTOResult.getModel().getTotalSalesVolume());
                    productSearchDTO.setOriginalPrice(Double.valueOf(productInfoDTOResult.getModel().getMaxPrice()));
                    productSearchDTO.setPrice(Double.valueOf(productInfoDTOResult.getModel().getMinPrice()));
                    productSearchDTOS.add(productSearchDTO);
                }*/
            }
            Result<List<ProductSearchDTO>> productSearchDTOResult = productService.listProductByIds(ids);
            if (!productSearchDTOResult.getModel().isEmpty()) {
                Map<Long, ProductSearchDTO> map = new HashMap<>();
                for (ProductSearchDTO productSearchDTO : productSearchDTOResult.getModel()) {
                    map.put(productSearchDTO.getProductId(), productSearchDTO);
                }
                
                for (AdPlatformProductDTO adPlatformProductDTO : chooseResult.getModel()) {
                	ProductSearchDTO searchDTO = map.get(adPlatformProductDTO.getProductId());
                	ProductSearchDTO productSearchDTO = new ProductSearchDTO();
                	productSearchDTO.setOriginalPrice(searchDTO.getOriginalPrice());
                	productSearchDTO.setPrice(searchDTO.getPrice());
                	productSearchDTO.setSalesVolume(searchDTO.getSalesVolume());
                	productSearchDTO.setContent(searchDTO.getContent());
                	productSearchDTO.setProductId(adPlatformProductDTO.getProductId());
                    productSearchDTO.setFeatureImage(adPlatformProductDTO.getMediaUrl());
                    productSearchDTO.setName(adPlatformProductDTO.getTitle());
                    productSearchDTO.setLinkUrl(adPlatformProductDTO.getLinkUrl());
                    productSearchDTOS.add(productSearchDTO);
                }
            }
        }
        shoppingProductDTO.setRecommendProduct(productSearchDTOS);

        //热门商品
        productSearchDTOS = new ArrayList<>();
        Result<List<AdPlatformProductDTO>> hotResult = adPlatformService.getAdPlatformByTypePosition(TypeEnum.TYPE_PRODUCT, PositionEnum.SHOPPING_HOT);
        if (!hotResult.getModel().isEmpty()) {
            List<Long> ids = new ArrayList<>();
            for (AdPlatformProductDTO adPlatformProductDTO : hotResult.getModel()) {
                ids.add(adPlatformProductDTO.getProductId());
            }
            Result<List<ProductSearchDTO>> productSearchDTOResult = productService.listProductByIds(ids);
            if (!productSearchDTOResult.getModel().isEmpty()) {
                Map<Long, ProductSearchDTO> map = new HashMap<>();
                for (ProductSearchDTO productSearchDTO : productSearchDTOResult.getModel()) {
                    map.put(productSearchDTO.getProductId(), productSearchDTO);
                }
                for (AdPlatformProductDTO adPlatformProductDTO : hotResult.getModel()) {
                	ProductSearchDTO searchDTO = map.get(adPlatformProductDTO.getProductId());
                	ProductSearchDTO productSearchDTO = new ProductSearchDTO();
                	productSearchDTO.setOriginalPrice(searchDTO.getOriginalPrice());
                	productSearchDTO.setPrice(searchDTO.getPrice());
                	productSearchDTO.setSalesVolume(searchDTO.getSalesVolume());
                	productSearchDTO.setContent(searchDTO.getContent());
                	productSearchDTO.setProductId(adPlatformProductDTO.getProductId());
                    productSearchDTO.setFeatureImage(adPlatformProductDTO.getMediaUrl());
                    productSearchDTO.setName(adPlatformProductDTO.getTitle());
                    productSearchDTO.setLinkUrl(adPlatformProductDTO.getLinkUrl());
                    productSearchDTOS.add(productSearchDTO);
                }
            }
        }
        shoppingProductDTO.setHotProduct(productSearchDTOS);

        //E店必购
        productSearchDTOS = new ArrayList<>();
        Result<List<AdPlatformProductDTO>> buyResult = adPlatformService.getAdPlatformByTypePosition(TypeEnum.TYPE_PRODUCT, PositionEnum.SHOPPING_BUY);
        if (!buyResult.getModel().isEmpty()) {
            List<Long> ids = new ArrayList<>();
            for (AdPlatformProductDTO adPlatformProductDTO : buyResult.getModel()) {
                ids.add(adPlatformProductDTO.getProductId());
            }
            Result<List<ProductSearchDTO>> productSearchDTOResult = productService.listProductByIds(ids);
            if (!productSearchDTOResult.getModel().isEmpty()) {
                Map<Long, ProductSearchDTO> map = new HashMap<>();
                for (ProductSearchDTO productSearchDTO : productSearchDTOResult.getModel()) {
                    map.put(productSearchDTO.getProductId(), productSearchDTO);
                }
                for (AdPlatformProductDTO adPlatformProductDTO : buyResult.getModel()) {
                	ProductSearchDTO searchDTO = map.get(adPlatformProductDTO.getProductId());
                	ProductSearchDTO productSearchDTO = new ProductSearchDTO();
                	productSearchDTO.setOriginalPrice(searchDTO.getOriginalPrice());
                	productSearchDTO.setPrice(searchDTO.getPrice());
                	productSearchDTO.setSalesVolume(searchDTO.getSalesVolume());
                	productSearchDTO.setContent(searchDTO.getContent());
                	productSearchDTO.setProductId(adPlatformProductDTO.getProductId());
                    productSearchDTO.setFeatureImage(adPlatformProductDTO.getMediaUrl());
                    productSearchDTO.setName(adPlatformProductDTO.getTitle());
                    productSearchDTO.setLinkUrl(adPlatformProductDTO.getLinkUrl());
                    productSearchDTOS.add(productSearchDTO);
                }
            }
        }
        shoppingProductDTO.setBuyProduct(productSearchDTOS);

        //特色好货
        productSearchDTOS = new ArrayList<>();
        Result<List<AdPlatformProductDTO>> featureResult = adPlatformService.getAdPlatformByTypePosition(TypeEnum.TYPE_PRODUCT, PositionEnum.SHOPPING_GOODS);
        if (!featureResult.getModel().isEmpty()) {
            List<Long> ids = new ArrayList<>();
            for (AdPlatformProductDTO adPlatformProductDTO : featureResult.getModel()) {
                ids.add(adPlatformProductDTO.getProductId());
            }
            Result<List<ProductSearchDTO>> productSearchDTOResult = productService.listProductByIds(ids);
            if (!productSearchDTOResult.getModel().isEmpty()) {
                Map<Long, ProductSearchDTO> map = new HashMap<>();
                for (ProductSearchDTO productSearchDTO : productSearchDTOResult.getModel()) {
                    map.put(productSearchDTO.getProductId(), productSearchDTO);
                }
                for (AdPlatformProductDTO adPlatformProductDTO : featureResult.getModel()) {
                	ProductSearchDTO searchDTO = map.get(adPlatformProductDTO.getProductId());
                	ProductSearchDTO productSearchDTO = new ProductSearchDTO();
                	productSearchDTO.setOriginalPrice(searchDTO.getOriginalPrice());
                	productSearchDTO.setPrice(searchDTO.getPrice());
                	productSearchDTO.setSalesVolume(searchDTO.getSalesVolume());
                	productSearchDTO.setContent(searchDTO.getContent());
                	productSearchDTO.setProductId(adPlatformProductDTO.getProductId());
                    productSearchDTO.setFeatureImage(adPlatformProductDTO.getMediaUrl());
                    productSearchDTO.setName(adPlatformProductDTO.getTitle());
                    productSearchDTO.setLinkUrl(adPlatformProductDTO.getLinkUrl());
                    productSearchDTOS.add(productSearchDTO);
                }
            }
        }
        shoppingProductDTO.setFeatureProduct(productSearchDTOS);

        //实惠单品
        productSearchDTOS = new ArrayList<>();
        Result<List<AdPlatformProductDTO>> benefitResult = adPlatformService.getAdPlatformByTypePosition(TypeEnum.TYPE_PRODUCT, PositionEnum.SHOPPING_BENEFIT);
        if (!benefitResult.getModel().isEmpty()) {
            List<Long> ids = new ArrayList<>();
            for (AdPlatformProductDTO adPlatformDTO : benefitResult.getModel()) {
                ids.add(adPlatformDTO.getProductId());
            }
            Result<List<ProductSearchDTO>> productSearchDTOResult = productService.listProductByIds(ids);
            if (!productSearchDTOResult.getModel().isEmpty()) {
                Map<Long, ProductSearchDTO> map = new HashMap<>();
                for (ProductSearchDTO productSearchDTO : productSearchDTOResult.getModel()) {
                    map.put(productSearchDTO.getProductId(), productSearchDTO);
                }
                for (AdPlatformProductDTO adPlatformProductDTO : benefitResult.getModel()) {
                	ProductSearchDTO searchDTO = map.get(adPlatformProductDTO.getProductId());
                	ProductSearchDTO productSearchDTO = new ProductSearchDTO();
                	productSearchDTO.setOriginalPrice(searchDTO.getOriginalPrice());
                	productSearchDTO.setPrice(searchDTO.getPrice());
                	productSearchDTO.setSalesVolume(searchDTO.getSalesVolume());
                	productSearchDTO.setContent(searchDTO.getContent());
                	productSearchDTO.setProductId(adPlatformProductDTO.getProductId());
                    productSearchDTO.setFeatureImage(adPlatformProductDTO.getMediaUrl());
                    productSearchDTO.setName(adPlatformProductDTO.getTitle());
                    productSearchDTO.setLinkUrl(adPlatformProductDTO.getLinkUrl());
                    productSearchDTOS.add(productSearchDTO);
                }
            }
        }
        shoppingProductDTO.setBenefitProduct(productSearchDTOS);
        return successGet(shoppingProductDTO);
    }

    @AutoTesting
    @Audit(date = "2017-05-02", reviewer = "孙林青")
    @ApiOperation(value = "要购物首页猜你喜欢", notes = "要购物首页猜你喜欢。[1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listYouLikeProduct", method = RequestMethod.GET)
    public Result<Page<ProductSearchDTO>> listYouLikeProduct(@ModelAttribute @ApiParam ProductSearchParam productSearchParam) {
        return productSolrService.listYouLikeProduct(productSearchParam);
    }

    @AutoTesting
    @Audit(date = "2017-05-02", reviewer = "孙林青")
    @ApiOperation(value = "查询商品推荐类别", notes = "查询商品类别。[1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listRecommendProductCategory", method = RequestMethod.GET)
    public Result<List<RecommendProductCategoryDTO>> listRecommendProductCategory() {
        return recommendProductCategoryService.listAllRecommendProductCategory();
    }

    @Audit(date = "2018-04-18", reviewer = "孙林青")
    @ApiOperation(value = "商品列表", notes = "商品列表。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listProductSearch", method = RequestMethod.GET)
    public Result<Page<ProductSearchDTO>> listProduct(@ModelAttribute @ApiParam ProductListSearchParam param) {
        Result<Page<ProductSearchDTO>> result = productSolrService.listProduct(param);
        return successGet(result);
    }

}
