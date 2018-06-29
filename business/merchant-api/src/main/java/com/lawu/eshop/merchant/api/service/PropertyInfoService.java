package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.property.dto.FreezeDTO;
import com.lawu.eshop.property.dto.PropertyBalanceDTO;
import com.lawu.eshop.property.dto.PropertyInfoFreezeDTO;
import com.lawu.eshop.property.dto.PropertyLoveAccountDTO;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.dto.PropertyPointDTO;
import com.lawu.eshop.property.param.FreezeQueryParam;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/3/27
 */
@FeignClient(value = "property-srv")
public interface PropertyInfoService {

    /**
     * 修改支付密码
     *
     * @param userNo      商户编号
     * @param originalPwd 原始密码
     * @param newPwd      新密码
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.PUT, value = "propertyInfo/updatePayPwd/{userNo}")
    Result updatePayPwd(@PathVariable("userNo") String userNo, @RequestParam("originalPwd") String originalPwd, @RequestParam("newPwd") String newPwd);

    /**
     * 重置支付密码
     *
     * @param userNo 商户编号
     * @param newPwd 新密码
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.PUT, value = "propertyInfo/resetPayPwd/{userNo}")
    Result resetPayPwd(@PathVariable("userNo") String userNo, @RequestParam("newPwd") String newPwd);

    /**
     * 设置支付密码
     *
     * @param userNo 商户编号
     * @param newPwd 新密码
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.PUT, value = "propertyInfo/setPayPwd/{userNo}")
    Result setPayPwd(@PathVariable("userNo") String userNo, @RequestParam("newPwd") String newPwd);

    /**
     * 查询是否设置支付密码
     *
     * @param userNum
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET, value = "propertyInfo/isSetPayPwd/{userNum}")
    Result isSetPayPwd(@PathVariable("userNum") String userNum);

    /**
     * 根据用户编号获取资产余额
     *
     * @param userNum 用户编号
     * @return
     */
    @RequestMapping(value = "propertyInfo/propertyBalance/{userNum}", method = RequestMethod.GET)
    Result<PropertyBalanceDTO> getPropertyBalance(@PathVariable("userNum") String userNum);

    /**
     * 根据用户编号获取用户积分
     *
     * @param userNum 用户编号
     * @return
     */
    @RequestMapping(value = "propertyInfo/propertyPoint/{userNum}", method = RequestMethod.GET)
    public Result<PropertyPointDTO> getPropertyPoint(@PathVariable("userNum") String userNum);

    /**
     * 减积分
     *
     * @param propertyInfoDataParam
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "propertyInfoData/doHanlderMinusPoint", method = RequestMethod.POST)
    Result inviteFans(@ModelAttribute PropertyInfoDataParam propertyInfoDataParam);

    /**
     * 邀请粉丝消费积分
     *
     * @param propertyInfoDataParam
     * @return
     */
	@RequestMapping(value = "propertyInfoData/doHanlderMinusPointByFans", method = RequestMethod.POST)
    Result doHanlderMinusPointByFans(@ModelAttribute PropertyInfoDataParam propertyInfoDataParam);
    
    /**
     * 验证支付密码
     *
     * @param userNum
     * @param payPwd
     * @return
     */
	@RequestMapping(value = "propertyInfo/varifyPayPwd", method = RequestMethod.GET)
    Result<Boolean> varifyPayPwd(@RequestParam("userNum") String userNum, @RequestParam("payPwd") String payPwd);
    
    /**
     * 获取商家积分余额
     * @param userNum
     * @return
     */
	@RequestMapping(value = "propertyInfo/getPropertyInfoMoney/{userNum}", method = RequestMethod.GET)
	Result<PropertyPointAndBalanceDTO> getPropertyInfoMoney(@PathVariable("userNum") String userNum);
	
	 /**
     * 根据用户编号获取爱心账户
     *
     * @param userNum 用户编号
     * @return
     */
    @RequestMapping(value = "propertyInfo/selectLoveAccount/{userNum}", method = RequestMethod.GET)
    Result<PropertyLoveAccountDTO> selectLoveAccount(@PathVariable("userNum") String userNum);
    
    /**
	 * 获取用户是否冻结
	 * @param userNum
	 * @return
	 * @author yangqh
	 * @date 2017年5月26日 上午11:08:32
	 */
	@RequestMapping(value = "propertyInfo/getPropertyinfoFreeze/{userNum}", method = RequestMethod.GET)
	Result<PropertyInfoFreezeDTO> getPropertyinfoFreeze(@PathVariable("userNum") String userNum);

    /**
     * 获取冻结资金列表
     * @param param
     * @return
     */
    @RequestMapping(value = "propertyInfo/getFreezeList", method = RequestMethod.POST)
    Result<Page<FreezeDTO>> getFreezeList(@RequestBody FreezeQueryParam param);

    /**
     * 校验积分账户
     *
     * @param userNum
     * @param point
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "propertyInfo/validatePoint", method = RequestMethod.GET)
    Result validatePoint(@RequestParam("userNum") String userNum, @RequestParam("point") String point);
}
