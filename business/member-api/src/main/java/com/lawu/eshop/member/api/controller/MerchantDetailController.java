package com.lawu.eshop.member.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.util.UserUtil;
import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.eshop.member.api.service.MerchantFavoredService;
import com.lawu.eshop.member.api.service.MerchantService;
import com.lawu.eshop.member.api.service.MerchantStoreImageService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.member.api.service.ProductSolrService;
import com.lawu.eshop.member.api.service.RegionService;
import com.lawu.eshop.member.api.service.ShoppingProductService;
import com.lawu.eshop.member.api.service.StoreSolrService;
import com.lawu.eshop.product.dto.ProductSearchDTO;
import com.lawu.eshop.product.param.ListShoppingProductParam;
import com.lawu.eshop.product.param.ProductSearchParam;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreImageDTO;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.dto.MerchantStoreInfoDTO;
import com.lawu.eshop.user.dto.MerchantStoreStatusDTO;
import com.lawu.eshop.user.dto.ProductSearchListDTO;
import com.lawu.eshop.user.dto.ShoppingStoreDetailDTO;
import com.lawu.eshop.user.dto.StoreDetailDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
@Api(tags = "merchantDetail")
@RestController
@RequestMapping(value = "merchantDetail/")
public class MerchantDetailController extends BaseController {

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private MerchantStoreImageService merchantStoreImageService;

    @Autowired
    private MerchantFavoredService merchantFavoredService;

    @Autowired
    private ShoppingProductService shoppingProductService;

    @Autowired
    private ProductSolrService productSolrService;

    @Autowired
    private StoreSolrService storeSolrService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private MerchantService merchantService;

    @AutoTesting
    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "要买单门店详情", notes = "要买单门店详情(用户评价、更多商家查询其他接口)。[1002]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "store/{id}", method = RequestMethod.GET)
    public Result<StoreDetailDTO> storeDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                              @PathVariable @ApiParam(required = true, value = "门店ID") Long id) {
        //判断门店是否存在
        MerchantStoreStatusDTO storeStatusDTO = merchantStoreService.merchantStoreIsExist(id);
        if (!storeStatusDTO.isExist()) {
            return successGet(ResultCode.MERCHANT_STORE_NO_EXIST);
        }
        if (MerchantStatusEnum.MERCHANT_STATUS_CANCEL.val == storeStatusDTO.getStatus()) {
            //门店状态注销，查询门店的logo
            String storeUrl = merchantStoreService.getStoreUrlByStoreId(id);
            StoreDetailDTO storeDetailDTO = new StoreDetailDTO();
            storeDetailDTO.setStorePic(storeUrl);
            storeDetailDTO.setIsExistStore(false);
            return successGet(storeDetailDTO);
        }

        Long memberId = UserUtil.getCurrentUserId(getRequest());
        Result<StoreDetailDTO> stoResult = merchantStoreService.getStoreDetailById(id, memberId);
        if (!isSuccess(stoResult)) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        StoreDetailDTO storeDetailDTO = stoResult.getModel();
        storeDetailDTO.setIsExistStore(true);
        Result<MerchantFavoredDTO> merResult = merchantFavoredService.findFavoredByMerchantId(storeDetailDTO.getMerchantId());
        if (isSuccess(merResult) && merResult.getModel().getId() != null) {
            MerchantFavoredDTO merchantFavoredDTO = merResult.getModel();
            storeDetailDTO.setTypeEnum(com.lawu.eshop.user.constants.MerchantFavoredTypeEnum.getEnum(merchantFavoredDTO.getTypeEnum().val));
            storeDetailDTO.setReachAmount(merchantFavoredDTO.getReachAmount());
            storeDetailDTO.setFavoredAmount(merchantFavoredDTO.getFavoredAmount());
            storeDetailDTO.setDiscountRate(merchantFavoredDTO.getDiscountRate());
            storeDetailDTO.setMerchantFavoredId(merchantFavoredDTO.getId());
            storeDetailDTO.setValidDayTime(merchantFavoredDTO.getValidDayBeginTime() + "-" + merchantFavoredDTO.getValidDayEndTime());
            storeDetailDTO.setPreferentialTime(merchantFavoredDTO.getValidWeekTime() + merchantFavoredDTO.getValidDayBeginTime() + "-" + merchantFavoredDTO.getValidDayEndTime());
            storeDetailDTO.setValidTime(DateUtil.getDateFormat(merchantFavoredDTO.getEntireBeginTime()) + "至" + DateUtil.getDateFormat(merchantFavoredDTO.getEntireEndTime()));
            storeDetailDTO.setIsOverdue(DateUtil.isOverdue(merchantFavoredDTO.getEntireEndTime()));
        }

        if (StringUtils.isNotEmpty(storeDetailDTO.getRegionPath())) {
            String areaName = regionService.getAreaName(storeDetailDTO.getRegionPath()).getModel();
            storeDetailDTO.setAreaName(areaName);
        }
        return successGet(storeDetailDTO);
    }

    @AutoTesting
    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "商家相册", notes = "商家相册(店内环境照)。[1002]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listMerchantStoreImage/{merchantId}", method = RequestMethod.GET)
    public Result<List<MerchantStoreImageDTO>> listMerchantStoreImage(@PathVariable @ApiParam(required = true, value = "商家ID") Long merchantId) {
        return merchantStoreImageService.listMerchantStoreImageByType(merchantId, MerchantStoreImageEnum.STORE_IMAGE_ENVIRONMENT);
    }

    @AutoTesting
    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "要购物门店详情", notes = "要购物门店详情基本信息。[1002,2009]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "shoppingStore/{id}", method = RequestMethod.GET)
    public Result<ShoppingStoreDetailDTO> shoppingStoreDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                              @PathVariable @ApiParam(required = true, value = "门店ID") Long id,
                                                              @ModelAttribute ProductSearchParam searchParam) {
        //判断门店是否存在
        MerchantStoreStatusDTO storeStatusDTO = merchantStoreService.merchantStoreIsExist(id);
        if (!storeStatusDTO.isExist()) {
            return successGet(ResultCode.MERCHANT_STORE_NO_EXIST);
        }
        if (MerchantStatusEnum.MERCHANT_STATUS_CANCEL.val.byteValue() == storeStatusDTO.getStatus()) {
            //门店状态注销，查询门店的logo
            String logoUrl = merchantStoreService.getLogoUrlByStoreId(id);
            ShoppingStoreDetailDTO storeDetailDTO = new ShoppingStoreDetailDTO();
            storeDetailDTO.setLogoPic(logoUrl);
            storeDetailDTO.setIsExistStore(false);
            //查询商品列表
            List<ProductSearchDTO> productSearchDTOList = productSolrService.findProductSearchList(searchParam);
            if (productSearchDTOList.isEmpty()) {
                storeDetailDTO.setProductList(new ArrayList<>());
                return successGet(storeDetailDTO);
            }
            List<ProductSearchListDTO> productLists = new ArrayList<>();
            ProductSearchListDTO productSearchListDTO ;
            for (ProductSearchDTO productSearchDTO : productSearchDTOList) {
                productSearchListDTO = new ProductSearchListDTO();
                productSearchListDTO.setContent(productSearchDTO.getContent());
                productSearchListDTO.setFeatureImage(productSearchDTO.getFeatureImage());
                productSearchListDTO.setName(productSearchDTO.getName());
                productSearchListDTO.setOriginalPrice(productSearchDTO.getOriginalPrice());
                productSearchListDTO.setPrice(productSearchDTO.getPrice());
                productSearchListDTO.setProductId(productSearchDTO.getProductId());
                productSearchListDTO.setSalesVolume(productSearchDTO.getSalesVolume());
                productLists.add(productSearchListDTO);
            }
            storeDetailDTO.setProductList(productLists);
            return successGet(storeDetailDTO);
        }

        Long memberId = UserUtil.getCurrentUserId(getRequest());
        Result<ShoppingStoreDetailDTO> shoppingStoreDetailDTOResult = merchantStoreService.getShoppingStoreDetailById(id, memberId);
        if(shoppingStoreDetailDTOResult.getModel() != null){
            shoppingStoreDetailDTOResult.getModel().setProductList(new ArrayList<>());
            shoppingStoreDetailDTOResult.getModel().setIsExistStore(true);
        }
        return shoppingStoreDetailDTOResult;
    }

    @AutoTesting
    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "要购物门店详情店铺首页", notes = "要购物门店详情店铺首页。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listHotProduct", method = RequestMethod.GET)
    public Result<Page<ProductSearchDTO>> listHotProduct(@ModelAttribute @ApiParam ListShoppingProductParam listShoppingProductParam) {
        return shoppingProductService.listHotProduct(listShoppingProductParam);
    }

    @AutoTesting
    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "要购物门店详情全部商品", notes = "要购物门店详情全部商品。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listAllProduct", method = RequestMethod.GET)
    public Result<Page<ProductSearchDTO>> listAllProduct(@ModelAttribute @ApiParam ListShoppingProductParam listShoppingProductParam) {
        return shoppingProductService.listAllProduct(listShoppingProductParam);
    }

    @AutoTesting
    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "要购物门店详情最新上架", notes = "要购物门店详情最新上架。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listNewProduct", method = RequestMethod.GET)
    public Result<Page<ProductSearchDTO>> listNewProduct(@ModelAttribute @ApiParam ListShoppingProductParam listShoppingProductParam) {
        return shoppingProductService.listNewProduct(listShoppingProductParam);
    }


    @Audit(date = "2018-05-16", reviewer = "孙林青")
    @ApiOperation(value = "根据编号获取商家ID,店铺ID", notes = "根据编号获取商家ID,店铺ID[1100] (章勇)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getMerchantChatInfo", method = RequestMethod.GET)
    public Result<MerchantStoreInfoDTO> getMerchantChatInfo(@RequestParam("userNum") @ApiParam(required = true, value = "商家编号") String userNum) {
        return merchantService.getMerchantChatInfo(userNum);
    }

}
