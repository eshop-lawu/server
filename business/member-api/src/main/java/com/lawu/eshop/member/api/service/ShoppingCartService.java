package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.order.dto.ShoppingCartDTO;
import com.lawu.eshop.order.param.ShoppingCartSaveParam;
import com.lawu.eshop.order.param.ShoppingCartUpdateParam;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@FeignClient(value= "order-srv")
public interface ShoppingCartService {
	
	
	/**
	 * 加入购物车
	 * 
	 * @param parm
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "shoppingCart/{memberId}", method = RequestMethod.POST)
	Result save(@PathVariable("memberId") Long memberId, @RequestBody ShoppingCartSaveParam parm);
	
	/**
	 * 根据memberId查询用户的购物车列表
	 * 
	 * @param memberId
	 * @return
	 */
    @RequestMapping(value = "shoppingCart/list/{memberId}", method = RequestMethod.GET)
    Result<List<ShoppingCartDTO>> findListByMemberId(@PathVariable(name = "memberId") Long memberId);
    
    
    /**
     * 根据id更新购物车的商品（使用实时更新不采用批量更新的方式）
     * 
     * @param id
     * @param parm
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "shoppingCart/update/{id}", method = RequestMethod.PUT)
	public Result update(@PathVariable(name = "id") Long id, @RequestParam("memberId") Long memberId, @RequestBody ShoppingCartUpdateParam parm);
    
	/**
	 * 根据id列表删除购物车的商品
	 * 
	 * @param id
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "shoppingCart/delete", method = RequestMethod.PUT)
	public Result delete(@RequestParam("memberId") Long memberId, @RequestBody List<Long> ids);
	
	/**
	 * 根据购物车id列表查询购物车列表
	 * 
	 * @param memberId 会员id
	 * @param ids 购物车id列表
	 * @return
	 */
	@RequestMapping(value = "shoppingCart/list/findListByIds/{memberId}",  method = RequestMethod.GET)
	Result<List<ShoppingCartDTO>> findListByIds(@PathVariable("memberId") Long memberId, @RequestParam(name = "ids") List<Long> ids);
	
	/**
	 * 根据用户id列表查询购物车数量
	 * 
	 * @param memberId 用户id
	 * @return
	 */
	@RequestMapping(value = "shoppingCart/count/{memberId}",  method = RequestMethod.GET)
	Result<Long> findListByIds(@PathVariable("memberId") Long memberId);
}
