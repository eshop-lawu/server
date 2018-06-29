package com.lawu.eshop.user.srv.service;

import java.util.List;

import com.lawu.eshop.user.constants.FansMerchantChannelEnum;
import com.lawu.eshop.user.param.FansMerchantQueryParam;
import com.lawu.eshop.user.param.FensCountQuery;
import com.lawu.eshop.user.param.ListFansParam;
import com.lawu.eshop.user.param.ListInviteFansParam;
import com.lawu.eshop.user.param.ListInviteFansWithContentParam;
import com.lawu.eshop.user.param.PageListInviteFansParam;
import com.lawu.eshop.user.srv.bo.FansMerchantBO;
import com.lawu.eshop.user.srv.bo.FansMerchantQueryBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/4/6.
 */
public interface FansMerchantService {

    /**
     * 查询会员邀请粉丝
     *
     * @param merchantId
     * @param param
     * @return
     */
    List<FansMerchantBO> listInviteFans(Long merchantId, ListInviteFansParam param);

    
    /**
     * 查询会员邀请粉丝
     *
     * @param merchantId
     * @param param
     * @return
     */
    List<FansMerchantBO> listInviteFansWithContent(Long merchantId, ListInviteFansWithContentParam param);
    
    
    /**
     * 分页查询会员邀请粉丝
     *
     * @param merchantId
     * @param param
     * @return
     */
    Page<FansMerchantBO> pageListInviteFans(Long merchantId, PageListInviteFansParam param);

    /**
     * 查询粉丝列表
     *
     * @param merchantId
     * @param listFansParam
     * @return
     */
    Page<FansMerchantBO> listFans(Long merchantId, ListFansParam listFansParam);

    /**
     * 根据会员ID和商家ID查询粉丝记录
     *
     * @param memberId
     * @param merchantId
     * @return
     */
    FansMerchantBO getFansMerchant(Long memberId, Long merchantId);

    /**
     * 查询当前商家的所有粉丝
     *
     * @param memberId
     * @return
     */
    List<Long> findMerchant(Long memberId);

    /**
     * 根据商家查询粉丝数量
     *
     * @param merchatID
     * @return
     */
    Integer findFensCount(Long merchatId);
    
    
    /**
     * 根据商家查询粉丝数量
     *
     * @param query
     * @return
     */
    Integer findMerchantFensCount(FensCountQuery query);

    /**
     * 成为商家粉丝
     *
     * @param merchantId
     * @param memberId
     * @param channelEnum
     */
    void saveFansMerchant(Long merchantId, Long memberId, FansMerchantChannelEnum channelEnum);

    /**
     * 用户处理粉丝邀请
     *
     * @param merchantId
     * @param memberId
     * @param messageId
     * @param dealWay
     */ 
	void saveFansMerchantFromInvite(Long merchantId, Long memberId, Long messageId, Boolean dealWay);

    /**
     * 根据ID查询粉丝记录
     *
     * @param id
     * @return
     * @author meishuquan
     */
    FansMerchantBO getFansMerchantById(Long id);

 /**
     * 取消成为粉丝
     * @param merchantId
     * @param memberId
     */
	void removeFansMerchant(Long merchantId, Long memberId);
	
    Integer getAttentionMerchantCount(Long memberId);


	Page<FansMerchantQueryBO> getFansMerchantList(Long memberId, FansMerchantQueryParam pageQuery);
}
