package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.property.dto.OrderAssetInformationDTO;
import com.lawu.eshop.property.dto.PropertyBalanceDTO;
import com.lawu.eshop.property.dto.PropertyInfoFreezeDTO;
import com.lawu.eshop.property.dto.PropertyLoveAccountDTO;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.dto.PropertyPointDTO;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/29
 */
@FeignClient(value = "property-srv")
public interface PropertyInfoService {

    /**
     * 修改支付密码
     *
     * @param userNum     会员编号
     * @param originalPwd 原始密码
     * @param newPwd      新密码
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.PUT, value = "propertyInfo/updatePayPwd/{userNum}")
    Result updatePayPwd(@PathVariable("userNum") String userNum, @RequestParam("originalPwd") String originalPwd, @RequestParam("newPwd") String newPwd);

    /**
     * 重置支付密码
     *
     * @param userNum 会员编号
     * @param newPwd  新密码
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.PUT, value = "propertyInfo/resetPayPwd/{userNum}")
    Result resetPayPwd(@PathVariable("userNum") String userNum, @RequestParam("newPwd") String newPwd);

    /**
     * 设置支付密码
     *
     * @param userNum 会员编号
     * @param newPwd  新密码
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.PUT, value = "propertyInfo/setPayPwd/{userNum}")
    Result setPayPwd(@PathVariable("userNum") String userNum, @RequestParam("newPwd") String newPwd);

    @SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.PUT, value = "propertyInfo/updatePayPwd/{userNum}")
    Result updatePayPwd(@PathVariable("userNum") String userNum, @RequestParam("originalPwd") String originalPwd, @RequestParam("newPwd") String newPwd, @RequestParam("type") Integer type);

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
     * 验证支付密码
     *
     * @param userNum
     * @param payPwd
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "propertyInfo/varifyPayPwd", method = RequestMethod.GET)
    Result varifyPayPwd(@RequestParam("userNum") String userNum, @RequestParam("payPwd") String payPwd);
     
    
    /** 获取商家积分余额
     * @param userNum
     * @return
     */
	@RequestMapping(value = "propertyInfo/getPropertyInfoMoney/{userNum}", method = RequestMethod.GET)
	Result<PropertyPointAndBalanceDTO> getPropertyInfoMoney(@PathVariable("userNum") String userNum);
	
	/**
	 * 查询爱心账户
	 * 
	 * @param userNum
	 * @return
	 */
	@RequestMapping(value = "propertyInfo/selectLoveAccount/{userNum}", method = RequestMethod.GET)
	Result<PropertyLoveAccountDTO> selectLoveAccount(@PathVariable("userNum") String userNum);
	
	/**
	 * 获取用户是否冻结
	 * @param userNum
	 * @return 0-否、1-是、2-异常
	 * @author yangqh
	 * @date 2017年5月26日 上午11:08:32
	 */
	@RequestMapping(value = "propertyInfo/getPropertyinfoFreeze/{userNum}", method = RequestMethod.GET)
	Result<PropertyInfoFreezeDTO> getPropertyinfoFreeze(@PathVariable("userNum") String userNum);

	/**
	 * 校验积分是否足够
	 *
	 * @param userNum
	 * @param point
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "propertyInfo/validatePoint", method = RequestMethod.GET)
	Result validatePoint(@RequestParam("userNum") String userNum, @RequestParam("point") String point);

    /**
     * 根据用户编号查询订单所需要的资产信息
     * 
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月10日
     * @updateDate 2018年3月10日
     */
    @GetMapping(value = "propertyInfo/orderAssetInformation/{userNum}")
    Result<OrderAssetInformationDTO> orderAssetInformation(@PathVariable("userNum") String userNum);

	/**
	 * 获取资产配置
	 * @param name
	 * @return
	 */
	@GetMapping(value = "property/getValue")
	Result getRechargeRate(@RequestParam("name") String name);
}
