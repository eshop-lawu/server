package com.lawu.eshop.member.api.service;

import com.lawu.eshop.user.constants.FansMerchantChannelEnum;
import com.lawu.eshop.user.dto.FansMerchantQueryDTO;
import com.lawu.eshop.user.param.FansMerchantQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "user-srv")
public interface FansMerchantService {

	/**
	 * 查询用户是否是商家的粉丝
	 * 
	 * @param merchantId
	 * @param memberId
	 * @return
	 * @author Sunny
	 */
    @RequestMapping(value = "fansMerchant/isFansMerchant/{merchantId}", method = RequestMethod.GET)
    Result<Boolean> isFansMerchant(@PathVariable("merchantId") Long merchantId, @RequestParam("memberId") Long memberId);
    
    /**
     * 粉丝列表
     *
     * @param memberId
     * @return
     */
    @RequestMapping(value = "fansMerchant/findMerchant", method = RequestMethod.GET)
    public List<Long> findMerchant(@RequestParam("memberId") Long memberId);

    /**
     * 成为商家粉丝
     *
     * @param merchantId
     * @param memberId
     * @param channelEnum
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "fansMerchant/saveFansMerchant/{merchantId}", method = RequestMethod.PUT)
    Result saveFansMerchant(@PathVariable("merchantId") Long merchantId, @RequestParam("memberId") Long memberId, @RequestParam("channelEnum") FansMerchantChannelEnum channelEnum);

    /**
     * 取消成为商家粉丝
     * @param merchantId
     * @param memberId
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "fansMerchant/removeFansMerchant/{merchantId}", method = RequestMethod.PUT)
    Result removeFansMerchant(@PathVariable("merchantId") Long merchantId, @RequestParam("memberId") Long memberId);
    
    
    /**
     * 用户处理粉丝邀请
     *
     * @param merchantId
     * @param memberId
     * @param channelEnum
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "fansMerchant/saveFansMerchantFromInvite/{merchantId}", method = RequestMethod.PUT)
    Result saveFansMerchantFromInvite(@PathVariable("merchantId") Long merchantId, @RequestParam("memberId") Long memberId, @RequestParam("messageId") Long messageId, @RequestParam("dealWay") Boolean dealWay);
    
    /**
     * 查询商家粉丝数量
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "fansMerchant/countFans/{merchantId}")
    Result<Integer> countFans(@PathVariable("merchantId") Long merchantId);

    @RequestMapping(value = "fansMerchant/getAttentionMerchantCount", method = RequestMethod.GET)
    Result<Integer> getAttentionMerchantCount(@RequestParam("memberId") Long memberId);

    @RequestMapping(method = RequestMethod.GET,value = "fansMerchant/getFansMerchantList")
	Result<Page<FansMerchantQueryDTO>> getFansMerchantList(@RequestParam("memberId")Long memberId, @RequestBody FansMerchantQueryParam pageQuery);
}
