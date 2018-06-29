package com.lawu.eshop.operator.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.AccountDTO;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.dto.MerchantDetailDTO;
import com.lawu.eshop.user.dto.MerchantFreezeInfoDTO;
import com.lawu.eshop.user.dto.MerchantSNSDTO;
import com.lawu.eshop.user.dto.MerchantStoreProfileDTO;
import com.lawu.eshop.user.dto.MerchantViewDTO;
import com.lawu.eshop.user.dto.MessagePushDTO;
import com.lawu.eshop.user.param.AccountParam;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/4/24.
 */
@FeignClient(value = "user-srv")
public interface MerchantService {

    @RequestMapping(value = "merchant/findMessagePushList", method = RequestMethod.GET)
    Result<List<MessagePushDTO>> findMessagePushList(@RequestParam(value = "area") String area);

    @RequestMapping(value = "merchant/findMessagePushByMobile", method = RequestMethod.GET)
    MessagePushDTO findMessagePushByMobile(@RequestParam("moblie") String moblie);

    /**
     * 商家基本信息
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchant/selectMerchantInfo")
    Result<MerchantSNSDTO> selectMerchantInfo(@RequestParam("merchantId") Long merchantId);

    /**
     * 根据账号查询商户信息
     *
     * @param account 商家账号
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchant/getMerchant/{account}")
    Result<MerchantDTO> getMerchantByAccount(@PathVariable("account") String account);

    /**
     * 根据编号查询商家信息
     *
     * @param num
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchant/getMerchantByNum")
    Result<MerchantDTO> getMerchantByNum(@RequestParam("num") String num);

    /**
     * 根据商家ID查询商家基本信息
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchant/getMerchantView/{id}")
    Result<MerchantViewDTO> getMerchantView(@PathVariable("id") Long id);

    @RequestMapping(value = "merchant/getAccountList", method = RequestMethod.POST)
     Result<Page<AccountDTO>> getAccountList(@RequestBody AccountParam param);

    @RequestMapping(value = "merchant/freezeAccount", method = RequestMethod.PUT)
    Result freezeAccount(@RequestBody FreezeParam param);

    @RequestMapping(value = "merchant/getMerchantStoreProfileInfo", method = RequestMethod.GET)
    Result<MerchantStoreProfileDTO> getMerchantStoreProfileInfo(@RequestParam(value ="id" )  Long id);

    /**
     * 根据商家ID查询商家详细信息(包括门店、图片等信息)
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "merchant/getMerchantDetail/{id}", method = RequestMethod.GET)
    Result<MerchantDetailDTO> getMerchantDetail(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "merchant/delMerchantGtPush", method = RequestMethod.PUT)
    Result delMerchantGtPush(@RequestParam("merchantId") Long merchantId);

    /**
     * 根据id查询商家编号
     *
     * @param merchantId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "merchant/getMerchantFreezeInfo/{merchantId}", method = RequestMethod.GET)
    Result<MerchantFreezeInfoDTO> getMerchantFreezeInfo(@PathVariable("merchantId") Long merchantId);
}
