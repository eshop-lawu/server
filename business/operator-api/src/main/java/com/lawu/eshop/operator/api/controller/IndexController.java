package com.lawu.eshop.operator.api.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.mall.constants.MerchantFavoredTypeEnum;
import com.lawu.eshop.mall.dto.DiscountPackageQueryDTO;
import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.eshop.operator.api.service.AdService;
import com.lawu.eshop.operator.api.service.DiscountPackageService;
import com.lawu.eshop.operator.api.service.MerchantFavoredService;
import com.lawu.eshop.operator.api.service.MerchantStoreService;
import com.lawu.eshop.operator.api.service.ProductService;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.param.ListMerchantStoreParam;
import com.lawu.eshop.user.param.StoreIndexParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/5/10.
 */
@Api(tags = "index")
@RestController
@RequestMapping(value = "index/")
public class IndexController extends BaseController {

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AdService adService;

    @Autowired
    private MerchantFavoredService merchantFavoredService;

    @Autowired
    private DiscountPackageService discountPackageService;

    @ApiOperation(value = "重建门店索引", notes = "更新门店索引。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("index:store")
    @RequestMapping(value = "updateStoreIndex", method = RequestMethod.GET)
    public Result updateStoreIndex() {
        ListMerchantStoreParam listMerchantStoreParam = new ListMerchantStoreParam();
        listMerchantStoreParam.setStatus(MerchantStatusEnum.MERCHANT_STATUS_CHECKED.val);
        listMerchantStoreParam.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT.val);
        listMerchantStoreParam.setPageSize(1000);
        int currentPage = 0;

        while (true) {
            currentPage++;
            listMerchantStoreParam.setCurrentPage(currentPage);
            Result<List<MerchantStoreDTO>> result = merchantStoreService.listMerchantStore(listMerchantStoreParam);
            if (!isSuccess(result) || result.getModel().isEmpty()) {
                return successGet();
            }

            Result<MerchantFavoredDTO> favoredDTOResult;
            Result<Page<DiscountPackageQueryDTO>> discountResult;
            List<StoreIndexParam> indexParamList = new ArrayList<>();
            for (MerchantStoreDTO storeDTO : result.getModel()) {
                //查询商家优惠信息
                favoredDTOResult = merchantFavoredService.findFavoredByMerchantId(storeDTO.getMerchantId());
                String favoreInfo = "";
                String favoreEndTime = "";
                double discountOrdinal = 1000;
                if (isSuccess(favoredDTOResult) && favoredDTOResult.getModel() != null && favoredDTOResult.getModel().getId() != null) {
                    if (favoredDTOResult.getModel().getTypeEnum().val.byteValue() == MerchantFavoredTypeEnum.TYPE_FULL.val) {
                        favoreInfo = "买单每满" + favoredDTOResult.getModel().getReachAmount().intValue() + "减" + favoredDTOResult.getModel().getFavoredAmount().intValue() + "元";
                        discountOrdinal = (favoredDTOResult.getModel().getReachAmount().subtract(favoredDTOResult.getModel().getFavoredAmount())).divide(favoredDTOResult.getModel().getReachAmount(), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        discountOrdinal = discountOrdinal * 1000 + 2;
                    } else if (favoredDTOResult.getModel().getTypeEnum().val.byteValue() == MerchantFavoredTypeEnum.TYPE_FULL_REDUCE.val) {
                        favoreInfo = "买单满" + favoredDTOResult.getModel().getReachAmount().intValue() + "减" + favoredDTOResult.getModel().getFavoredAmount().intValue() + "元";
                        discountOrdinal = (favoredDTOResult.getModel().getReachAmount().subtract(favoredDTOResult.getModel().getFavoredAmount())).divide(favoredDTOResult.getModel().getReachAmount(), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        discountOrdinal = discountOrdinal * 1000 + 3;
                    } else if (favoredDTOResult.getModel().getTypeEnum().val.byteValue() == MerchantFavoredTypeEnum.TYPE_DISCOUNT.val) {
                        NumberFormat numberFormat = NumberFormat.getInstance();
                        favoreInfo = "买单" + numberFormat.format(favoredDTOResult.getModel().getDiscountRate()) + "折";
                        discountOrdinal = favoredDTOResult.getModel().getDiscountRate().divide(BigDecimal.valueOf(10), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        discountOrdinal = discountOrdinal * 1000 + 1;
                    }
                    favoreEndTime = DateUtil.getDateFormat(favoredDTOResult.getModel().getEntireEndTime());
                }

                //查询商家优惠套餐
                discountResult = discountPackageService.listForMember(storeDTO.getMerchantId());
                String discountPackage = "";
                if (isSuccess(discountResult) && !discountResult.getModel().getRecords().isEmpty()) {
                    discountPackage = discountResult.getModel().getRecords().get(0).getName();
                }

                StoreIndexParam storeIndexParam = new StoreIndexParam();
                storeIndexParam.setMerchantStoreId(storeDTO.getMerchantStoreId());
                storeIndexParam.setFavoreInfo(favoreInfo);
                storeIndexParam.setDiscountPackage(discountPackage);
                storeIndexParam.setDiscountOrdinal(discountOrdinal);
                storeIndexParam.setFavoreEndTime(favoreEndTime);
                indexParamList.add(storeIndexParam);
            }
            if (!indexParamList.isEmpty()) {
                merchantStoreService.rebuildStoreIndex(indexParamList);
            }
        }
    }

    @ApiOperation(value = "重建商品索引", notes = "更新商品索引。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("index:product")
    @RequestMapping(value = "updateProductIndex", method = RequestMethod.GET)
    public Result updateProductIndex() {
        return productService.rebuildProductIndex(1000);
    }

    @ApiOperation(value = "重建广告索引", notes = "更新广告索引。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("index:ad")
    @RequestMapping(value = "updateAdIndex", method = RequestMethod.GET)
    public Result updateAdIndex() {
        return adService.rebuildAdIndex(1000);
    }

    @ApiOperation(value = "删除所有无效门店索引", notes = "删除无效门店索引。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("indexDel:store")
    @RequestMapping(value = "delInvalidStoreIndex", method = RequestMethod.GET)
    public Result delInvalidStoreIndex() {
        return merchantStoreService.delInvalidStoreIndex(DelIndexTypeEnum.ALL);
    }

    @ApiOperation(value = "删除所有无效商品索引", notes = "删除无效商品索引。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("indexDel:product")
    @RequestMapping(value = "delInvalidProductIndex", method = RequestMethod.GET)
    public Result delInvalidProductIndex() {
        return productService.delInvalidProductIndex(DelIndexTypeEnum.ALL);
    }

    @ApiOperation(value = "删除所有无效广告索引", notes = "删除无效广告索引。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("indexDel:ad")
    @RequestMapping(value = "delInvalidAdIndex", method = RequestMethod.GET)
    public Result delInvalidAdIndex() {
        return adService.delInvalidAdIndex(DelIndexTypeEnum.ALL);
    }

    @ApiOperation(value = "删除全部门店索引", notes = "删除全部门店索引。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("indexDelAll:store")
    @RequestMapping(value = "delAllStoreIndex", method = RequestMethod.GET)
    public Result delAllStoreIndex() {
        return merchantStoreService.delAllStoreIndex();
    }

    @ApiOperation(value = "删除全部商品索引", notes = "删除全部商品索引。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("indexDelAll:product")
    @RequestMapping(value = "delAllProductIndex", method = RequestMethod.GET)
    public Result delAllProductIndex() {
        return productService.delAllProductIndex();
    }

    @ApiOperation(value = "删除全部广告索引", notes = "删除全部广告索引。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("indexDelAll:ad")
    @RequestMapping(value = "delAllAdIndex", method = RequestMethod.GET)
    public Result delAllAdIndex() {
        return adService.delAllAdIndex();
    }

}
