package com.lawu.eshop.jobs.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.JobsConfig;
import com.lawu.eshop.jobs.service.DiscountPackageService;
import com.lawu.eshop.jobs.service.MerchantFavoredService;
import com.lawu.eshop.jobs.service.MerchantStoreService;
import com.lawu.eshop.jobs.service.RebuildStoreService;
import com.lawu.eshop.mall.constants.MerchantFavoredTypeEnum;
import com.lawu.eshop.mall.dto.DiscountPackageQueryDTO;
import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.param.ListMerchantStoreParam;
import com.lawu.eshop.user.param.StoreIndexParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2017/10/16.
 */
@Service
public class RebuildStoreServiceImpl implements RebuildStoreService {

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private MerchantFavoredService merchantFavoredService;

    @Autowired
    private DiscountPackageService discountPackageService;

    @Autowired
    private JobsConfig jobsConfig;

    @Override
    public void rebuildStoreService() {
        ListMerchantStoreParam listMerchantStoreParam = new ListMerchantStoreParam();
        listMerchantStoreParam.setStatus(MerchantStatusEnum.MERCHANT_STATUS_CHECKED.val);
        listMerchantStoreParam.setManageType(MerchantStoreTypeEnum.ENTITY_MERCHANT.val);
        listMerchantStoreParam.setPageSize(jobsConfig.getPageSize());
        int currentPage = 0;

        while (true) {
            currentPage++;
            listMerchantStoreParam.setCurrentPage(currentPage);
            Result<List<MerchantStoreDTO>> result = merchantStoreService.listMerchantStore(listMerchantStoreParam);
            if (result.getRet() != ResultCode.SUCCESS || result.getModel().isEmpty()) {
                return;
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
                if (favoredDTOResult.getRet() == ResultCode.SUCCESS) {
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
                if (discountResult.getRet() == ResultCode.SUCCESS && !discountResult.getModel().getRecords().isEmpty()) {
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
}
