package com.lawu.eshop.operator.api.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawu.authorization.constants.TokenClearType;
import com.lawu.authorization.manager.TokenCacheService;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.constants.MerchantFavoredTypeEnum;
import com.lawu.eshop.mall.dto.DiscountPackageQueryDTO;
import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.eshop.operator.api.service.AdService;
import com.lawu.eshop.operator.api.service.DiscountPackageService;
import com.lawu.eshop.operator.api.service.FreezeService;
import com.lawu.eshop.operator.api.service.MemberService;
import com.lawu.eshop.operator.api.service.MerchantFavoredService;
import com.lawu.eshop.operator.api.service.MerchantService;
import com.lawu.eshop.operator.api.service.MerchantStoreService;
import com.lawu.eshop.operator.api.service.ProductService;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.dto.MerchantStoreProfileDTO;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.eshop.user.param.StoreIndexParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * 处理账号冻结服务接口
 * 
 * @author jiangxinjun
 * @createDate 2018年1月18日
 * @updateDate 2018年1月18日
 */
@Service
public class FreezeServiceImpl extends BaseController implements FreezeService {
    
    @Autowired
    private MemberService memberService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    @Qualifier("memberTokenCacheService")
    private TokenCacheService memberTokenCacheService;

    @Autowired
    @Qualifier("merchantTokenCacheService")
    private TokenCacheService merchantTokenCacheService;

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
    
    @SuppressWarnings("rawtypes")
    @Override
    public Result freeze(FreezeParam param) {
        if (param.getNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
            //查询用户冻结状态
            Result<UserDTO> getMemberByNumResult = memberService.getMemberByNum(param.getNum());
            if (!isSuccess(getMemberByNumResult)) {
                return successCreated(getMemberByNumResult);
            }
            UserDTO userDTO = getMemberByNumResult.getModel();
            if (userDTO.getIsFreeze() && param.getIsFreeze()) {
                return successCreated(ResultCode.WRONG_OPERATION, "改账号已被冻结");
            }
            if (!userDTO.getIsFreeze() && !param.getIsFreeze()) {
                return successCreated(ResultCode.WRONG_OPERATION, "改账号已解冻");
            }
            //修改用户冻结状态
            memberService.freezeAccount(param);
            if (param.getIsFreeze()) {//冻结
                memberService.delUserGtPush(userDTO.getId());
                forcedExit(UserTypeEnum.MEMBER, userDTO.getAccount());//删除token
            }
            return successCreated();
        }
        //查询商家冻结状态
        Result<MerchantDTO> getMerchantByNumResult = merchantService.getMerchantByNum(param.getNum());
        if(!isSuccess(getMerchantByNumResult)){
            return successCreated(getMerchantByNumResult);
        }
        MerchantDTO merchantDTO = getMerchantByNumResult.getModel();
        if (merchantDTO.getIsFreeze() && param.getIsFreeze()) {
            return successCreated(ResultCode.WRONG_OPERATION, "改账号已被冻结");
        }
        if (!merchantDTO.getIsFreeze() && !param.getIsFreeze()) {
            return successCreated(ResultCode.WRONG_OPERATION, "改账号已解冻");
        }
        //冻结商家
        merchantService.freezeAccount(param);
        Result<MerchantStoreProfileDTO> getMerchantStoreProfileInfoResult = merchantService.getMerchantStoreProfileInfo(merchantDTO.getId());
        MerchantStoreProfileDTO merchantStoreProfileDTO = getMerchantStoreProfileInfoResult.getModel();
        if (param.getIsFreeze()) {
            merchantService.delMerchantGtPush(merchantDTO.getId());
            forcedExit(UserTypeEnum.MERCHANT, merchantDTO.getAccount());//删除token
            //下架并删除solr广告
            adService.soldOutAdByMerchantId(merchantDTO.getId());
            //未创建门店
            if (ResultCode.MERCHANT_STORE_NO_EXIST == getMerchantStoreProfileInfoResult.getRet()) {
                return successCreated();
            }
            //冻结
            if (MerchantStoreTypeEnum.ENTITY_MERCHANT.equals(merchantStoreProfileDTO.getTypeEnum())) {
                //实体店铺
                //删除solr信息
                merchantStoreService.delSolrDocsById(merchantStoreProfileDTO.getMerchantStoreId());
            } else {
                //普通店铺
                //下架所有商品,删除solr商品
                productService.soldOutProductByMerchantId(merchantDTO.getId());
            }
        } else {
            //解冻
            // 添加solr门店信息-实体店铺
            if (MerchantStoreTypeEnum.ENTITY_MERCHANT.equals(merchantStoreProfileDTO.getTypeEnum())) {
                Result<MerchantFavoredDTO> favoredDTOResult;
                Result<Page<DiscountPackageQueryDTO>> discountResult;
                List<StoreIndexParam> indexParamList = new ArrayList<>();
                StoreIndexParam storeIndexParam = new StoreIndexParam();
                favoredDTOResult = merchantFavoredService.findFavoredByMerchantId(merchantDTO.getId());
                String favoredInfo = "";
                String favoreEndTime = "";
                double discountOrdinal = 1000;
                if (isSuccess(favoredDTOResult)) {
                    if (MerchantFavoredTypeEnum.TYPE_FULL.equals(favoredDTOResult.getModel().getTypeEnum())) {
                        favoredInfo = "买单每满" + favoredDTOResult.getModel().getReachAmount().intValue() + "减" + favoredDTOResult.getModel().getFavoredAmount().intValue() + "元";
                        discountOrdinal = (favoredDTOResult.getModel().getReachAmount().subtract(favoredDTOResult.getModel().getFavoredAmount())).divide(favoredDTOResult.getModel().getReachAmount(), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        discountOrdinal = discountOrdinal * 1000 + 2;
                    } else if (MerchantFavoredTypeEnum.TYPE_FULL_REDUCE.equals(favoredDTOResult.getModel().getTypeEnum())) {
                        favoredInfo = "买单满" + favoredDTOResult.getModel().getReachAmount().intValue() + "减" + favoredDTOResult.getModel().getFavoredAmount().intValue() + "元";
                        discountOrdinal = (favoredDTOResult.getModel().getReachAmount().subtract(favoredDTOResult.getModel().getFavoredAmount())).divide(favoredDTOResult.getModel().getReachAmount(), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        discountOrdinal = discountOrdinal * 1000 + 3;
                    } else if (MerchantFavoredTypeEnum.TYPE_DISCOUNT.equals(favoredDTOResult.getModel().getTypeEnum())) {
                        NumberFormat numberFormat = NumberFormat.getInstance();
                        favoredInfo = "买单" + numberFormat.format(favoredDTOResult.getModel().getDiscountRate()) + "折";
                        discountOrdinal = favoredDTOResult.getModel().getDiscountRate().divide(BigDecimal.valueOf(10), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        discountOrdinal = discountOrdinal * 1000 + 1;
                    }
                    favoreEndTime = DateUtil.getDateFormat(favoredDTOResult.getModel().getEntireEndTime());
                }

                //查询商家优惠套餐
                discountResult = discountPackageService.listForMember(merchantDTO.getId());
                String discountPackage = "";
                if (isSuccess(discountResult) && !discountResult.getModel().getRecords().isEmpty()) {
                    discountPackage = discountResult.getModel().getRecords().get(0).getName();
                }

                storeIndexParam.setMerchantStoreId(merchantStoreProfileDTO.getMerchantStoreId());
                storeIndexParam.setFavoreInfo(favoredInfo);
                storeIndexParam.setDiscountPackage(discountPackage);
                storeIndexParam.setDiscountOrdinal(discountOrdinal);
                storeIndexParam.setFavoreEndTime(favoreEndTime);
                indexParamList.add(storeIndexParam);

                merchantStoreService.rebuildStoreIndex(indexParamList);
            }
        }
        return successCreated();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Result forcedExit(UserTypeEnum userType, String account) {
        switch (userType) {
            case MEMBER:
                memberTokenCacheService.delRelationshipByAccount(account, null, TokenClearType.MANUAL_FREEZE);
                break;
            case MERCHANT:
                merchantTokenCacheService.delRelationshipByAccount(account, null, TokenClearType.MANUAL_FREEZE);
                break;
            default:
                break;
        }
        return successDelete();
    }
}
