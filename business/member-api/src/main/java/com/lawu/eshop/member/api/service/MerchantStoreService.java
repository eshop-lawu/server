package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.product.dto.MemberProductStoreDTO;
import com.lawu.eshop.user.dto.MerchantAdInfoDTO;
import com.lawu.eshop.user.dto.MerchantInfoForShoppingCartDTO;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.dto.MerchantStoreFavorInfoDTO;
import com.lawu.eshop.user.dto.MerchantStoreStatusDTO;
import com.lawu.eshop.user.dto.PayOrderMerchantStoreInfoDTO;
import com.lawu.eshop.user.dto.PayOrderStoreInfoDTO;
import com.lawu.eshop.user.dto.ShoppingOrderFindUserInfoDTO;
import com.lawu.eshop.user.dto.ShoppingStoreDetailDTO;
import com.lawu.eshop.user.dto.StoreDetailDTO;
import com.lawu.eshop.user.dto.StoreSolrInfoDTO;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.eshop.user.param.ShoppingOrderFindUserInfoParam;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/30
 */
@FeignClient(value = "user-srv")
public interface MerchantStoreService {

    /**
     * 根据商家ID获取商家门店的名称
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "merchantStore/shoppingCart/{merchantId}", method = RequestMethod.GET)
    public Result<MerchantInfoForShoppingCartDTO> getMerchantInfoForShoppingCart(@PathVariable("merchantId") Long merchantId);

    /**
     * 根据商家ID查询门店是否支持七天无理由退货
     *
     * @param merchantId
     * @return
     * @author yangqh
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/findIsNoReasonReturnById")
    Result findIsNoReasonReturnById(@RequestParam("merchantId") Long merchantId);

    /**
     * 根据商家ID列表批量查询该商家是否支持七天无理由退货
     *
     * @param merchantIdList
     */
    @RequestMapping(value = "merchantStore/shoppingOrderFindUserInfo", method = RequestMethod.PUT)
    Result<ShoppingOrderFindUserInfoDTO> shoppingOrderFindUserInfo(@RequestBody ShoppingOrderFindUserInfoParam param);

    /**
     * 根据门店ID查询门店信息
     *
     * @param id
     * @param memberId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/storeDetail/{id}")
    Result<StoreDetailDTO> getStoreDetailById(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId);

    /**
     * 根据商家查询门店信息
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/selectMerchantStoreByMerchantId")
    Result<MerchantStoreDTO> selectMerchantStoreByMerchantId(@PathVariable("merchantId") Long merchantId);

    /**
     * 根据商家查询门店信息
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "merchantStore/selectMerchantStoreByMId", method = RequestMethod.GET)
    public Result<MerchantStoreDTO> selectMerchantStoreByMId(@RequestParam("merchantId") Long merchantId);

    /**
     * 根据商家ID查询门店信息
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/findStoreNameAndImgByMerchantId/{merchantId}")
    MerchantStoreDTO findStoreNameAndImgByMerchantId(@PathVariable("merchantId") Long merchantId);

    /**
     * 要购物门店详情
     *
     * @param id
     * @param memberId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/shoppingStoreDetail/{id}")
    Result<ShoppingStoreDetailDTO> getShoppingStoreDetailById(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId);

    /**
     * 根据门店ID查询门店信息
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/getMerchantStore/{id}")
    Result<MerchantStoreDTO> getMerchantStoreById(@PathVariable("id") Long id);

    /**
     * 用户端商品详情页面查询，店铺信息
     * @param merchantId
     * @return
     * @author yangqh
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/getMemberProductDetailStore")
    Result<MemberProductStoreDTO> getMemberProductDetailStore(@RequestParam("merchantId") Long merchantId);
    
    /**
     * 查询店铺类型
     * @param merchantId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/getManageType")
    Result<ManageTypeEnum> getManageType(@RequestParam("merchantId") Long merchantId);

    /**
     * 查询门店信息
     * @param merchantIds
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/getPayOrderStoreInfo")
    Result<List<PayOrderStoreInfoDTO>> getPayOrderStoreInfo(@RequestParam("merchantIds") List<Long> merchantIds);

    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/getMerchantStoreByIds")
    Result<List<StoreSolrInfoDTO>> getMerchantStoreByIds(@RequestParam("merchantStoreIds") List<Long> merchantStoreIds);
    
    @RequestMapping(value = "merchantStore/getAdMerchantStoreByIds", method = RequestMethod.GET)
	Result<List<MerchantAdInfoDTO>> getAdMerchantStoreByIds(@RequestParam("merchantIds") List<Long> merchantIds);

    /**
     * 判断门店是否存在
     * @param id
     * @return
     * @author zhangy
     */
    @RequestMapping(value = "merchantStore/merchantStoreIsExist/{id}", method = RequestMethod.GET)
    MerchantStoreStatusDTO merchantStoreIsExist(@PathVariable("id") Long id);

    /**
     * 查询logo图片
     * @param id
     * @return
     */
    @RequestMapping(value = "merchantStore/getLogoUrlByStoreId/{id}", method = RequestMethod.GET)
    String getLogoUrlByStoreId(@PathVariable("id") Long id);

    @RequestMapping(value = "merchantStore/getStoreUrlByStoreId/{id}", method = RequestMethod.GET)
    String getStoreUrlByStoreId(@PathVariable("id") Long id);

    /**
     * 买单详情门店信息
     * @param merchantId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchantStore/getPayOrderDetailStoreInfo")
    PayOrderMerchantStoreInfoDTO getPayOrderDetailStoreInfo(@RequestParam("merchantId") Long merchantId);

    /**
     * 根据商家编号获取商家账号和区域路径
     * @param merchantNum
     * @return
     */
    @RequestMapping(value = "merchantStore/findAccountAndRegionPathByNum", method = RequestMethod.GET)
    VisitUserInfoDTO findAccountAndRegionPathByNum(@RequestParam("merchantNum") String merchantNum);
    
    
    @RequestMapping(value = "merchantStore/selectMerchantStoreFavor/{merchantId}", method = RequestMethod.GET)
	Result<MerchantStoreFavorInfoDTO> selectMerchantStoreFavor(@PathVariable("merchantId") Long merchantId);
}
