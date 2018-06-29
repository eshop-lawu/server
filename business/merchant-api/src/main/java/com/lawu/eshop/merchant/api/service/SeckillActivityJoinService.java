package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.product.dto.SeckillActivityDetailDTO;
import com.lawu.eshop.product.dto.SeckillActivityJoinDTO;
import com.lawu.eshop.product.dto.SeckillActivityManageDetailDTO;
import com.lawu.eshop.product.dto.SeckillActivityManagerDTO;
import com.lawu.eshop.product.param.JoinSeckillActivityParam;
import com.lawu.eshop.product.param.SeckillActivityJoinParam;
import com.lawu.eshop.product.param.SeckillActivityManageParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;


/**
 * 参加抢购活动服务api接口
 * @author zhangrc
 *
 */
@FeignClient(value= "product-srv")
public interface SeckillActivityJoinService {

	/**
	 * 专场活动列表
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "seckillActivityJoin/queryPage", method = RequestMethod.POST)
	Result<Page<SeckillActivityJoinDTO>> queryPage(@RequestBody SeckillActivityJoinParam param);
	
	/**
	 * 专场管理列表
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "seckillActivityJoin/queryManagePage", method = RequestMethod.POST)
	Result<Page<SeckillActivityManagerDTO>> queryManagePage(@RequestBody SeckillActivityManageParam param);
	
	
	/**
	 * 活动详情
	 * @param id
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "seckillActivityJoin/querySeckillActivityDetail/{id}", method = RequestMethod.GET)
	Result<SeckillActivityDetailDTO> querySeckillActivityDetail(@PathVariable("id") Long id,@RequestParam("merchantId") Long merchantId);
	
	/**
	 * 活动管理详情
	 * @param id
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "seckillActivityJoin/querySeckillActivityManageDetail/{id}", method = RequestMethod.GET)
	Result<SeckillActivityManageDetailDTO> querySeckillActivityManageDetail(@PathVariable("id") Long id,@RequestParam("merchantId") Long merchantId);
	
	/**
	 * 参入报名
	 * @param joinParam
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "seckillActivityJoin/joinSeckillActivity", method = RequestMethod.POST)
	Result joinSeckillActivity(@RequestBody JoinSeckillActivityParam joinParam, @RequestParam("merchantId") Long merchantId);
}
