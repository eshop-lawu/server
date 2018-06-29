package com.lawu.eshop.order.srv.service;

import java.util.List;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.IllegalOperationException;
import com.lawu.eshop.order.param.ShoppingCartSaveParam;
import com.lawu.eshop.order.param.ShoppingCartUpdateParam;
import com.lawu.eshop.order.srv.bo.ShoppingCartBO;
import com.lawu.eshop.order.srv.exception.MoreThanMaximumException;

/**
 * 购物车服务接口
 *
 * @author Sunny
 * @date 2017/3/27
 */
public interface ShoppingCartService {
	
	
	/**
	 * 根据用户id查询用户的购物车
	 * 
	 * @param memberId
	 * @return
	 */
	List<ShoppingCartBO> findListByMemberId(Long memberId);
	
	/**
	 * 根据购物车id查询购物车
	 * 
	 * @param memberId 会员id
	 * @param id 购物车id
	 * @return
	 */
	ShoppingCartBO get(Long memberId, Long id);
	
	/**
	 * 加入购物车
	 * 
	 * @param memberId 会员id
	 * @param param 保存参数
	 * @author Sunny
	 */
	void save(Long memberId, ShoppingCartSaveParam param) throws MoreThanMaximumException;
	
	/**
	 * 根据id更新购物车
	 * 
	 * @param id 购物车id
	 * @param memberId 会员id
	 * @param param 更新参数
	 * @author Sunny
	 */
	void update(Long id, Long memberId, ShoppingCartUpdateParam param) throws DataNotExistException, IllegalOperationException ;
	
	/**
	 * 根据id列表删除购物车的商品
	 * 
	 * @param memberId 会员id
	 * @param ids 购物车id列表
	 * @return
	 * @author Sunny
	 */
	void remove(Long memberId, List<Long> ids) throws DataNotExistException, IllegalOperationException;
	
	/**
	 * 根据id删除购物车的商品
	 * 
	 * @param memberId 会员id
	 * @param id 购物车id
	 * @return
	 * @author Sunny
	 */
	void remove(Long memberId, Long id) throws DataNotExistException, IllegalOperationException;
	
	/**
	 * 根据购物车id列表查询购物车列表
	 * 
	 * @param ids 购物车id列表
	 * @return
	 */
	List<ShoppingCartBO> findListByIds(Long memberId, List<Long> ids);
	
	/**
	 * 根据用户id列表查询购物车数量
	 * 
	 * @param memberId 用户id
	 * @return
	 */
	Long count(Long memberId);
	
}
