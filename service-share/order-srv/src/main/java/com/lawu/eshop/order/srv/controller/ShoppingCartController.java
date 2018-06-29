package com.lawu.eshop.order.srv.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.IllegalOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.order.dto.ShoppingCartDTO;
import com.lawu.eshop.order.param.ShoppingCartSaveParam;
import com.lawu.eshop.order.param.ShoppingCartUpdateParam;
import com.lawu.eshop.order.srv.bo.ShoppingCartBO;
import com.lawu.eshop.order.srv.converter.ShoppingCartConverter;
import com.lawu.eshop.order.srv.exception.MoreThanMaximumException;
import com.lawu.eshop.order.srv.service.ShoppingCartService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@RestController
@RequestMapping(value = "shoppingCart/")
public class ShoppingCartController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * 加入购物车
	 * 
	 * @param param
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "{memberId}", method = RequestMethod.POST)
	public Result save(@PathVariable("memberId") Long memberId, @RequestBody ShoppingCartSaveParam param) {
		try {
			shoppingCartService.save(memberId, param);
		} catch (MoreThanMaximumException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.MAX_SHOPPING_CART_QUANTITY);
		}
		return successCreated();
	}

	/**
	 * 根据memberId查询用户的购物车列表
	 * 
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "list/{memberId}", method = RequestMethod.GET)
	public Result<List<ShoppingCartDTO>> findListByMemberId(@PathVariable(name = "memberId") Long memberId) {
		List<ShoppingCartBO> list = shoppingCartService.findListByMemberId(memberId);
		List<ShoppingCartDTO> rtn = ShoppingCartConverter.convertDTOS(list);
		return successGet(rtn);
	}

	/**
	 * 根据id更新购物车的商品（使用实时更新不采用批量更新的方式）
	 * 
	 * @param id
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public Result update(@PathVariable(name = "id") Long id, @RequestParam("memberId") Long memberId, @RequestBody ShoppingCartUpdateParam param) {
		try {
			shoppingCartService.update(id, memberId, param);
		} catch ( DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch ( IllegalOperationException e ) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		return successCreated();
	}

	/**
	 * 根据id列表删除购物车的商品
	 * 
	 * @param memberId
	 * @param ids
	 * @return
	 * @author Sunny
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "delete", method = RequestMethod.PUT)
	public Result delete(@RequestParam("memberId") Long memberId, @RequestBody List<Long> ids) {
		try {	
			shoppingCartService.remove(memberId, ids);
		} catch ( DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch ( IllegalOperationException e ) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		return successCreated();
	}

	/**
	 * 根据购物车id列表查询购物车列表
	 * 
	 * @param memberId 会员id
	 * @param ids 购物车id列表
	 * @return
	 */
	@RequestMapping(value = "list/findListByIds/{memberId}", method = RequestMethod.GET)
	public Result<List<ShoppingCartDTO>> findListByIds(@PathVariable("memberId") Long memberId, @RequestParam(name = "ids") List<Long> ids) {
		List<ShoppingCartBO> shoppingCartBOList = null;
		try {
			shoppingCartBOList = shoppingCartService.findListByIds(memberId, ids);
		} catch ( DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch ( IllegalOperationException e ) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		return successGet(ShoppingCartConverter.convertDTOS(shoppingCartBOList));
	}

	/**
	 * 根据用户id列表查询购物车数量
	 * 
	 * @param memberId
	 *            用户id
	 * @return
	 */
	@RequestMapping(value = "count/{memberId}", method = RequestMethod.GET)
	public Result<Long> count(@PathVariable("memberId") Long memberId) {
		return successGet(shoppingCartService.count(memberId));
	}
}
