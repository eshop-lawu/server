package com.lawu.eshop.product.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.dto.SeckillActivityDetailDTO;
import com.lawu.eshop.product.dto.SeckillActivityJoinDTO;
import com.lawu.eshop.product.dto.SeckillActivityManageDetailDTO;
import com.lawu.eshop.product.dto.SeckillActivityManagerDTO;
import com.lawu.eshop.product.param.JoinSeckillActivityParam;
import com.lawu.eshop.product.param.SeckillActivityJoinParam;
import com.lawu.eshop.product.param.SeckillActivityManageParam;
import com.lawu.eshop.product.srv.bo.SeckillActivityDetailBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityInfoBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityJoinBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityManageBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityManageDetailBO;
import com.lawu.eshop.product.srv.converter.SeckillActivityJoinConverter;
import com.lawu.eshop.product.srv.service.SeckillActivityJoinService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 参加活动服务接口
 * 
 * @author zhangrc
 * @date 2017/3/30.
 */
@RestController
@RequestMapping(value = "seckillActivityJoin/")
public class SeckillActivityJoinController extends BaseController{
	
	@Autowired SeckillActivityJoinService seckillActivityJoinService;
	
	/**
	 * 活动专场列表查询
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public Result<Page<SeckillActivityJoinDTO>> queryPage(@RequestBody SeckillActivityJoinParam param){
		
		Page<SeckillActivityJoinBO> page =seckillActivityJoinService.queryPage(param);
		List<SeckillActivityJoinBO> list = page.getRecords();
		Page<SeckillActivityJoinDTO> pageActivity = new Page<>();
		pageActivity.setRecords(SeckillActivityJoinConverter.seckillActivityJoinDTOConverter(list));
		pageActivity.setCurrentPage(page.getCurrentPage());
		pageActivity.setTotalCount(page.getTotalCount());
		
		return successGet(pageActivity);
		
	}
	
	
	/**
	 * 活动管理列表查询
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "queryManagePage", method = RequestMethod.POST)
	public Result<Page<SeckillActivityManagerDTO>> queryManagePage(@RequestBody SeckillActivityManageParam param){
		
		Page<SeckillActivityManageBO> page =seckillActivityJoinService.queryManagePage(param);
		List<SeckillActivityManageBO> list = page.getRecords();
		Page<SeckillActivityManagerDTO> pageActivity = new Page<>();
		pageActivity.setRecords(SeckillActivityJoinConverter.seckillActivityJoinManageDTOConverter(list));
		pageActivity.setCurrentPage(page.getCurrentPage());
		pageActivity.setTotalCount(page.getTotalCount());
		
		return successGet(pageActivity);
		
	}
	
	/**
	 * 活动详情
	 * @param id
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "querySeckillActivityDetail/{id}", method = RequestMethod.GET)
	public Result<SeckillActivityDetailDTO> querySeckillActivityDetail(@PathVariable Long id,@RequestParam Long merchantId){
		
		SeckillActivityDetailBO  seckillActivityDetailBO  = seckillActivityJoinService.querySeckillActivityDetail(id, merchantId);
		
		return successGet(SeckillActivityJoinConverter.seckillActivityJoinDetailDTOConverter(seckillActivityDetailBO));
	}
	
	
	/**
	 * 活动管理详情
	 * @param id
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "querySeckillActivityManageDetail/{id}", method = RequestMethod.GET)
	public Result<SeckillActivityManageDetailDTO> querySeckillActivityManageDetail(@PathVariable Long id,@RequestParam Long merchantId){
		
		SeckillActivityManageDetailBO  seckillActivityManageDetailBO  = seckillActivityJoinService.querySeckillActivityManageDetail(id, merchantId);
		SeckillActivityManageDetailDTO	seckillActivityManageDetailDTO	= SeckillActivityJoinConverter.seckillActivityManageDetailDTOConverter(seckillActivityManageDetailBO);
		seckillActivityManageDetailDTO.setList(SeckillActivityJoinConverter.seckillActivityProductManageDTOConverter(seckillActivityManageDetailBO.getList()));
		
		return successGet(seckillActivityManageDetailDTO);
	}
	
	/**
	 * 参加活动
	 * @param joinParam
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "joinSeckillActivity", method = RequestMethod.POST)
	public Result joinSeckillActivity(@RequestBody JoinSeckillActivityParam joinParam, @RequestParam Long merchantId){
		
		SeckillActivityInfoBO seckillActivityInfoBO = seckillActivityJoinService.querySeckillActivityInfo(joinParam.getSeckillActivityId(),merchantId,joinParam.getProductId());
		
		//该商品是否已经参与活动
		if(seckillActivityInfoBO.getIsExists()){
			return successCreated(ResultCode.SECKILL_ACTIVITY_PRODUCT_EXISTS);
		}
		
		//鉴权
		if(!seckillActivityInfoBO.getIsCheckProduct()){
			return successCreated(ResultCode.SECKILL_ACTIVITY_PRODUCT_STATUS);
		}
		
		//判断活动状态
		if(!seckillActivityInfoBO.getIsJoin()){
			return successCreated(ResultCode.SECKILL_ACTIVITY_JOIN_STATUS);
		}
		
		//可报名数已满
		if(seckillActivityInfoBO.getIsOverCount()){
			return successCreated(ResultCode.SECKILL_ACTIVITY_COUNT_OVER);
			
		}
		seckillActivityJoinService.joinSeckillActivity(joinParam, merchantId);
		
		return successCreated();
	}
	
	/**
	 * 判断商品是否参入活动
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "isJoinActivity/{productId}", method = RequestMethod.GET)
	public Result<Boolean> isJoinActivity(@PathVariable Long productId){
		
		Boolean isJoin = seckillActivityJoinService.isJoinActivity(productId);
		return successGet(isJoin);
	}

}
