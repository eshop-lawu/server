package com.lawu.eshop.rich.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.dto.MerchantSNSDTO;
import com.lawu.eshop.user.dto.MobileDTO;
import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.dto.UserHeadImgDTO;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.core.page.PageParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/3/22
 */
@FeignClient(value = "user-srv")
public interface MerchantService {

    /**
     * 查询商家信息
     *
     * @param account 登录账号
     * @param pwd     密码
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchant/withPwd/{account}")
    Result<LoginUserDTO> find(@PathVariable("account") String account, @RequestParam("pwd") String pwd);

    /**
     * 修改登录密码
     *
     * @param id          ID
     * @param originalPwd 原始密码
     * @param newPwd      新密码
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "merchant/updateLoginPwd/{id}")
    Result updateLoginPwd(@PathVariable("id") Long id, @RequestParam("originalPwd") String originalPwd, @RequestParam("newPwd") String newPwd);

    /**
     * 重置登录密码
     *
     * @param mobile 账号
     * @param newPwd 新密码
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "merchant/resetLoginPwd/{mobile}")
    Result updateLoginPwd(@PathVariable("mobile") String mobile, @RequestParam("newPwd") String newPwd);

    /**
     * 根据邀请人账号查询邀请人信息
     *
     * @param account 邀请人账号
     */
    @RequestMapping(method = RequestMethod.GET, value = "user/common/getInviter/{account}")
    Result getInviterByAccount(@PathVariable("account") String account);

    /**
     * 商户注册
     *
     * @param registerRealParam 商户注册信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "merchant/register")
    Result register(@ModelAttribute RegisterRealParam registerRealParam);


    /**
     * 查询我推荐的商家
     *
     * @return
     * @author zhangrc
     * @date 2017/03/27
     */
    @RequestMapping(method = RequestMethod.POST, value = "merchant/getMerchantByInviter")
    Result<Page<MerchantDTO>> getMerchantByInviter(@RequestParam("inviterId") Long inviterId, @RequestBody PageParam query);

    /**
     * 根据账号查询商户信息
     *
     * @param account 商家账号
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchant/getMerchant/{account}")
    Result<MerchantDTO> getMerchantByAccount(@PathVariable("account") String account);

    /**
     * 根据商家编号查询商家信息
     * @param num
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "getMerchantByNum")
    Result<MerchantDTO> getMerchantByNum(@RequestParam("num") String num);
    /**
     * 增加推送、 CID
     * @param id
     * @param cid
     * @return
     */
    @RequestMapping(value = "merchant/setGtAndRongYunInfo/{id}",method = RequestMethod.PUT)
    Result setGtAndRongYunInfo(@PathVariable("id") Long id,@RequestParam("cid") String cid);
    
    /**
     * 商家基本信息
     * @param merchantId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchant/selectMerchantInfo")
    Result<MerchantSNSDTO> selectMerchantInfo(@RequestParam("merchantId") Long merchantId);

    /**
     * 修改头像
     *
     * @param merchantId
     * @param headimg
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "merchant/saveHeadImage/{merchantId}")
    Result<UserHeadImgDTO> saveHeadImage(@PathVariable("merchantId") Long merchantId, @RequestParam("headimg") String headimg);
    
    /**
     * 获取电话
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "merchant/selectMobile/{merchantId}", method = RequestMethod.GET)
    public Result<MobileDTO> selectMobile(@PathVariable("merchantId") Long merchantId);

    /**
     * 根据商家编号查询融云需要的信息
     *
     * @param num
     * @return
     */
    @RequestMapping(value = "merchant/getRongYunInfo/{num}", method = RequestMethod.GET)
    Result<RongYunDTO> getRongYunInfoByNum(@PathVariable("num") String num);
    
    /**
     * 判断用户是否注册
     * @param mobile
     * @return
     */
    @RequestMapping(value = "merchant/isRegister", method = RequestMethod.GET)
    Result<Boolean> isRegister(@RequestParam("mobile") String mobile);

    @RequestMapping(value = "merchant/delMerchantGtPush", method = RequestMethod.PUT)
    Result delMerchantGtPush(@RequestParam("merchantId") Long merchantId);
    
    
    /**
     * 短信登录
     * @param account
     * @return
     */
    @RequestMapping(value = "merchant/smsLogin/{account}", method = RequestMethod.GET)
    Result<LoginUserDTO> smsLogin(@PathVariable("account") String account);

    /**
     * 获取完成E友邀请人的编号
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "merchant/getRichTaskInviterNum")
    Result<String> getRichTaskInviterNum(@RequestParam("userNum") String userNum);

}
