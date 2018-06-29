package com.lawu.eshop.user.srv.service;

import java.util.List;

import com.lawu.eshop.user.param.ApplyStoreParam;
import com.lawu.eshop.user.param.MerchantStoreParam;
import com.lawu.eshop.user.param.ShoppingOrderFindUserInfoParam;
import com.lawu.eshop.user.srv.bo.CashUserInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreAuditBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreProfileBO;
import com.lawu.eshop.user.srv.bo.PayOrderStoreInfoBO;
import com.lawu.eshop.user.srv.bo.ShoppingOrderFindMerchantInfoBO;
import com.lawu.eshop.user.srv.bo.ShoppingStoreDetailBO;
import com.lawu.eshop.user.srv.bo.StoreDetailBO;
import com.lawu.eshop.user.srv.bo.StoreSolrInfoBO;

/**
 * 门店信息接口
 * Created by zhangyong on 2017/3/24.
 */
public interface MerchantStoreInfoService {

    /**
     * 根据商户id查询门店信息
     *
     * @param id
     * @return
     */
    MerchantStoreInfoBO selectMerchantStore(Long id);

    MerchantStoreInfoBO getMerchantStore(Long id, Long merchantId);

    /**
     * 新增门店信息
     *
     * @param merchantId
     * @param merchantStoreParam
     */
    void saveMerchantStoreInfo(Long merchantId, MerchantStoreParam merchantStoreParam);

    /**
     * 查询门店扩展信息
     *
     * @param example（营业执照号码/身份证号）
     * @param type（1：营业执照号码        2:身份证号）
     * @return
     */
    MerchantStoreProfileBO selectStoreInfoByExample(String example, Integer type);

    /**
     * 修改门店信息
     *
     * @param merchantId         门店id
     * @param merchantStoreParam 门店信息
     */
    void updateMerchantStoreInfo(Long merchantId, MerchantStoreParam merchantStoreParam, Long merchantStoreId);

    MerchantStoreInfoBO selectMerchantStoreByMId(Long merchantId);

    /**
     * 商家是否支持七天退货、商家的用户编号、当前用户是否是商家的粉丝
     *
     * @param param
     * @return
     */
    List<ShoppingOrderFindMerchantInfoBO> shoppingOrderFindUserInfo(ShoppingOrderFindUserInfoParam param);

    void saveMerchantStoreAuditInfo(Long merchantId, MerchantStoreParam merchantStoreParam, Long merchantStoreId);

    /**
     * 根据门店ID查询门店详细信息
     *
     * @param id
     * @param memberId
     * @return
     */
    StoreDetailBO getStoreDetailById(Long id, Long memberId);

    /**
     * 用户、商家提现时根据商家ID获取账号、名称、省市区信息冗余到提现表中
     *
     * @param id
     * @return
     * @author Yangqh
     */
    CashUserInfoBO findCashUserInfo(Long id);

    MerchantStoreInfoBO findStoreNameAndImgByMerchantId(Long merchantId);

    MerchantStoreAuditBO findStoreAuditInfo(Long merchantId);

    /**
     * 增加门店买单笔数
     *
     * @param merchantId
     */
    void addMerchantStoreBuyNums(Long merchantId);

    /**
     * 增加门店评论人数
     *
     * @param merchantId
     * @param commentsCount
     * @author meishuquan
     */
    void addMerchantStoreCommentsCount(Long merchantId, Integer commentsCount);

    /**
     * 根据商家Id修改门店状态
     *
     * @param merchantId
     */
    void updateMerchantStoreStatus(Long merchantId, Byte status);

    Integer applyPhysicalStore(Long merchantId, Long storeId, ApplyStoreParam param);

    /**
     * 要购物门店详情
     *
     * @param id
     * @param memberId
     * @return
     */
    ShoppingStoreDetailBO getShoppingStoreDetailById(Long id, Long memberId);

    List<PayOrderStoreInfoBO> getPayOrderStoreInfo(List<Long> merchantIds);

    List<StoreSolrInfoBO> getMerchantStoreByIds(List<Long> merchantStoreIds);

    PayOrderStoreInfoBO getPayOrderDetailStoreInfo(Long merchantId);

    MerchantInfoBO getPayOrderMerchantInfo(Long merchantId);
    
    /**
     * 更新买单笔数
     * @param merchantId
     * @param buyNums
     * @author jiangxinjun
     * @date 2017年10月12日
     */
    void updateMerchantStoreBuyNums(Long merchantId, Integer buyNums);
}
