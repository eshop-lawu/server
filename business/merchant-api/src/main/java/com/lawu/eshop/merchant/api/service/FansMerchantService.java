package com.lawu.eshop.merchant.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.user.dto.FansMerchantDTO;
import com.lawu.eshop.user.param.ListFansParam;
import com.lawu.eshop.user.param.ListInviteFansParam;
import com.lawu.eshop.user.param.ListInviteFansWithContentParam;
import com.lawu.eshop.user.param.PageListInviteFansParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/4/6.
 */
@FeignClient(value = "user-srv")
public interface FansMerchantService {

    /**
     * 查询可邀请为粉丝的会员
     *
     * @param merchantId
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "fansMerchant/listInviteFans/{merchantId}")
    Result<List<FansMerchantDTO>> listInviteFans(@PathVariable("merchantId") Long merchantId, @ModelAttribute ListInviteFansParam param);

    /**
     * 查询可邀请为粉丝的会员
     *
     * @param merchantId
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "fansMerchant/listInviteFansWithContent/{merchantId}")
    Result<List<FansMerchantDTO>> listInviteFansWithContent(@PathVariable("merchantId") Long merchantId, @ModelAttribute ListInviteFansWithContentParam param);
    
    /**
     * 分页查询可邀请为粉丝的会员
     *
     * @param merchantId
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "fansMerchant/pageListInviteFans/{merchantId}")
    Result<Page<FansMerchantDTO>> pageListInviteFans(@PathVariable("merchantId") Long merchantId, @ModelAttribute PageListInviteFansParam param);

    /**
     * 粉丝列表
     *
     * @param merchantId    商户ID
     * @param listFansParam 查询参数
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "fansMerchant/listFans/{merchantId}")
    Result<Page<FansMerchantDTO>> listFans(@PathVariable("merchantId") Long merchantId, @ModelAttribute ListFansParam listFansParam);

    /**
     * 查询商家粉丝数量
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "fansMerchant/countFans/{merchantId}")
    Result<Integer> countFans(@PathVariable("merchantId") Long merchantId);
}
