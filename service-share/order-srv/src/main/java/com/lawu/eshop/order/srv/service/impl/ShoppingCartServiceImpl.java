package com.lawu.eshop.order.srv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.IllegalOperationException;
import com.lawu.eshop.order.param.ShoppingCartSaveParam;
import com.lawu.eshop.order.param.ShoppingCartUpdateParam;
import com.lawu.eshop.order.srv.bo.ShoppingCartBO;
import com.lawu.eshop.order.srv.constants.ExceptionMessageConstant;
import com.lawu.eshop.order.srv.constants.PropertyNameConstant;
import com.lawu.eshop.order.srv.converter.ShoppingCartConverter;
import com.lawu.eshop.order.srv.domain.ShoppingCartDO;
import com.lawu.eshop.order.srv.domain.ShoppingCartDOExample;
import com.lawu.eshop.order.srv.domain.extend.ShoppingCartUpdateQuantityDO;
import com.lawu.eshop.order.srv.exception.MoreThanMaximumException;
import com.lawu.eshop.order.srv.mapper.ShoppingCartDOMapper;
import com.lawu.eshop.order.srv.mapper.extend.ShoppingCartExtendDOMapper;
import com.lawu.eshop.order.srv.service.PropertyService;
import com.lawu.eshop.order.srv.service.ShoppingCartService;

/**
 * 购物车服务实现
 *
 * @author Sunny
 * @date 2017/3/24
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartDOMapper shoppingCartDOMapper;
	
	@Autowired
	private ShoppingCartExtendDOMapper shoppingCartExtendDOMapper;
	
	@Autowired
	private PropertyService propertyService;

	/**
	 * 根据用户id查询用户的购物车
	 * 
	 * @param memberId
	 * @return
	 */
	@Override
	public List<ShoppingCartBO> findListByMemberId(Long memberId) {
		ShoppingCartDOExample example = new ShoppingCartDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		example.setOrderByClause("gmt_create desc");
		return ShoppingCartConverter.convertBOS(shoppingCartDOMapper.selectByExample(example));
	}
	
	/**
	 * 根据购物车id查询购物车
	 * 
	 * @param memberId 会员id
	 * @param id 购物车id
	 * @return
	 */
	@Override
	public ShoppingCartBO get(Long memberId, Long id) throws DataNotExistException, IllegalOperationException {
		ShoppingCartDO shoppingCartDO = shoppingCartDOMapper.selectByPrimaryKey(id);
		if (shoppingCartDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_CART_DATA_NOT_EXIST);
		}
		
		if (!shoppingCartDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_CART);
		}
		return ShoppingCartConverter.convert(shoppingCartDO);
	}

	/**
	 * 加入购物车
	 * 
	 * @param memberId 会员id
	 * @param param 保存参数
	 * @author Sunny
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void save(Long memberId, ShoppingCartSaveParam param) throws MoreThanMaximumException {
		// 查看用户购物车是否有重复记录
		ShoppingCartDOExample example = new ShoppingCartDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andProductModelIdEqualTo(param.getProductModelId());
		List<Long> list = shoppingCartExtendDOMapper.selectIdByExample(example);
		
		if (list != null && !list.isEmpty()) {
            ShoppingCartUpdateQuantityDO shoppingCartUpdateQuantityDO = new ShoppingCartUpdateQuantityDO();
            shoppingCartUpdateQuantityDO.setId(list.get(0));
            shoppingCartUpdateQuantityDO.setQuantity(param.getQuantity());
			shoppingCartExtendDOMapper.updateQuantityByPrimaryKey(shoppingCartUpdateQuantityDO);
		} else {
			example.clear();
			example.createCriteria().andMemberIdEqualTo(memberId);
			long count = shoppingCartDOMapper.countByExample(example);
			int maxShoppingCartQuantity = Integer.valueOf(propertyService.getByName(PropertyNameConstant.MAX_SHOPPING_CART_QUANTITY));
			
			if (count > maxShoppingCartQuantity) {
				throw new MoreThanMaximumException("购物车数量已达上限");
			}
			
			ShoppingCartDO shoppingCartDO = ShoppingCartConverter.convert(param, memberId);
			shoppingCartDOMapper.insert(shoppingCartDO);
		}
	}

	/**
	 * 根据id更新购物车
	 * 
	 * @param id
	 *            购物车id
	 * @param memberId
	 *            会员id
	 * @param param
	 *            更新参数
	 * @return
	 * @author Sunny
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(Long id, Long memberId, ShoppingCartUpdateParam param) throws DataNotExistException, IllegalOperationException {
		ShoppingCartDO shoppingCartDO = shoppingCartDOMapper.selectByPrimaryKey(id);
		if (shoppingCartDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_CART_DATA_NOT_EXIST);
		}
		
		if (!shoppingCartDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_CART);
		}
		shoppingCartDOMapper.updateByPrimaryKeySelective(ShoppingCartConverter.convert(param, id));
	}

	/**
	 * 根据id列表删除购物车的商品
	 * 
	 * @param ids
	 *            购物车id列表
	 * @param memberId
	 *            会员id
	 * @return
	 * @author Sunny
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void remove(Long memberId, List<Long> ids) throws DataNotExistException, IllegalOperationException {
		for (Long id : ids) {
			remove(memberId, id);
		}
	}
	
	/**
	 * 根据id删除购物车的商品
	 * 
	 * @param id
	 *            购物车id
	 * @param memberId
	 *            会员id
	 * @return
	 * @author Sunny
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void remove(Long memberId, Long id) throws DataNotExistException, IllegalOperationException {
		ShoppingCartDO shoppingCartDO = shoppingCartDOMapper.selectByPrimaryKey(id);
		if (shoppingCartDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_CART_DATA_NOT_EXIST);
		}
		
		if (!shoppingCartDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_CART);
		}
		shoppingCartDOMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据购物车id列表查询购物车列表
	 * 
	 * @param ids
	 *            购物车id列表
	 * @return
	 */
	@Override
	public List<ShoppingCartBO> findListByIds(Long memberId, List<Long> ids) throws DataNotExistException, IllegalOperationException {
		List<ShoppingCartBO> rtn = new ArrayList<>();
		for (Long id : ids) {
			rtn.add(get(memberId, id));
		}
		return rtn;
	}

	/**
	 * 根据用户id列表查询购物车内商品的数量
	 * 
	 * @param memberId 用户id
	 * @return
	 */
	@Override
	public Long count(Long memberId) {
		Long rtn = 0L;
		ShoppingCartDOExample example = new ShoppingCartDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		List<ShoppingCartDO> shoppingCartDOList = shoppingCartDOMapper.selectByExample(example);
		for (ShoppingCartDO item : shoppingCartDOList) {
			rtn += item.getQuantity();
		}
		return rtn;
	}
}
