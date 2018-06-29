package com.lawu.eshop.order.srv.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.order.param.foreign.ShoppingRefundQueryForeignParam;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemExtendBO;
import com.lawu.eshop.order.srv.converter.ShoppingOrderItemConverter;
import com.lawu.eshop.order.srv.converter.ShoppingOrderItemExtendConverter;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDOExample;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDOExample;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderItemDOMapper;
import com.lawu.eshop.order.srv.mapper.extend.ShoppingOrderItemExtendDOMapper;
import com.lawu.eshop.order.srv.service.ShoppingOrderItemService;
import com.lawu.framework.core.page.Page;

@Service
public class ShoppingOrderItemServiceImpl implements ShoppingOrderItemService {

	@Autowired
	private ShoppingOrderItemDOMapper shoppingOrderItemDOMapper;

	@Autowired
	private ShoppingOrderItemExtendDOMapper shoppingOrderItemExtendDOMapper;

	/**
	 * 根据购物订单id获取购物订单项
	 * 
	 * @param id
	 *            购物订单项id
	 * @return
	 */
	@Override
	public ShoppingOrderItemBO get(Long id) {
		ShoppingOrderItemDO shoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(id);
		return ShoppingOrderItemConverter.convert(shoppingOrderItemDO);
	}

	/**
	 * 查询处于退款中的订单项
	 * 
	 * @param memberId
	 *            会员id
	 * @param param
	 *            查询参数
	 * @return 订单列表
	 */
	@Override
	public Page<ShoppingOrderItemExtendBO> selectRefundPageByMemberId(Long memberId, ShoppingRefundQueryForeignParam param) {

		ShoppingOrderItemExtendDOExample shoppingOrderItemExtendDOExample = new ShoppingOrderItemExtendDOExample();
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingOrder(true);
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingRefundDetail(true);
		ShoppingOrderItemExtendDOExample.Criteria shoppingOrderItemExtendDOExampleCriteria = shoppingOrderItemExtendDOExample.createCriteria();
		shoppingOrderItemExtendDOExampleCriteria.andSOMemberIdEqualTo(memberId);
		// 用户可以多次申请退款，查询当中有效的一条记录
		shoppingOrderItemExtendDOExampleCriteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());

		// 查询总记录数
		Long count = shoppingOrderItemExtendDOMapper.countByExample(shoppingOrderItemExtendDOExample);

		Page<ShoppingOrderItemExtendBO> rtn = new Page<>();
		rtn.setCurrentPage(param.getCurrentPage());
		rtn.setTotalCount(count.intValue());

		if (count <= 0 || param.getOffset() >= count) {
			return rtn;
		}

		// 按照退款详情的创建时间排序
		shoppingOrderItemExtendDOExample.setOrderByClause("srd.gmt_create desc");

		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

		List<ShoppingOrderItemExtendDO> shoppingOrderItemExtendDOList = shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(shoppingOrderItemExtendDOExample, rowBounds);
		rtn.setRecords(ShoppingOrderItemExtendConverter.convert(shoppingOrderItemExtendDOList));

		return rtn;
	}

	/**
	 * 评论成功更新订单项为已评论
	 * 
	 * @param id
	 * @return
	 * @author Sunny
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void commentsSuccessful(Long id) {
		ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
		shoppingOrderItemDO.setId(id);
		// 设置为已评论
		shoppingOrderItemDO.setIsEvaluation(true);
		shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemDO);
	}

	/**
	 * 查询处于退款中的订单项 To Merchant
	 * 
	 * @param merchantId
	 *            会员id
	 * @param param
	 *            查询参数
	 * @return 订单列表
	 */
	@Override
	public Page<ShoppingOrderItemExtendBO> selectRefundPageByMerchantId(Long merchantId, ShoppingRefundQueryForeignParam param) {

		ShoppingOrderItemExtendDOExample shoppingOrderItemExtendDOExample = new ShoppingOrderItemExtendDOExample();
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingOrder(true);
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingRefundDetail(true);
		ShoppingOrderItemExtendDOExample.Criteria shoppingOrderItemExtendDOExampleCriteria = shoppingOrderItemExtendDOExample.createCriteria();
		shoppingOrderItemExtendDOExampleCriteria.andSOMerchantIdEqualTo(merchantId);
		// 用户可以多次申请退款，查询当中有效的一条记录
		shoppingOrderItemExtendDOExampleCriteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());

		// 查询总记录数
		Long count = shoppingOrderItemExtendDOMapper.countByExample(shoppingOrderItemExtendDOExample);

		Page<ShoppingOrderItemExtendBO> rtn = new Page<>();
		rtn.setCurrentPage(param.getCurrentPage());
		rtn.setTotalCount(count.intValue());

		if (count <= 0 || param.getOffset() >= count) {
			return rtn;
		}

		/*
		 *  先按照退款项的退款状态排序，之后按照退款详情的时间来排序
		 */
		shoppingOrderItemExtendDOExample.setOrderByClause("soi.refund_status asc,srd.gmt_create asc");

		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

		List<ShoppingOrderItemExtendDO> shoppingOrderItemExtendDOList = shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(shoppingOrderItemExtendDOExample, rowBounds);
		rtn.setRecords(ShoppingOrderItemExtendConverter.convert(shoppingOrderItemExtendDOList));

		return rtn;
	}

	/**
	 * 查询处于退款中的订单项 To Operator
	 * 
	 * @param param
	 *            查询参数
	 * @return 订单列表
	 */
	@Override
	public Page<ShoppingOrderItemExtendBO> selectRefundPage(ShoppingRefundQueryForeignParam param) {
		ShoppingOrderItemExtendDOExample shoppingOrderItemExtendDOExample = new ShoppingOrderItemExtendDOExample();
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingRefundDetail(true);
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingOrder(true);
		ShoppingOrderItemExtendDOExample.Criteria shoppingOrderItemExtendDOExampleCriteria = shoppingOrderItemExtendDOExample.createCriteria();
		shoppingOrderItemExtendDOExampleCriteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());

		if (StringUtils.isNotEmpty(param.getKeyword())) {
			shoppingOrderItemExtendDOExample.clear();
			ShoppingOrderItemExtendDOExample.Criteria orderNumCriteria = shoppingOrderItemExtendDOExample.or();
			orderNumCriteria.andOrderNumEqualTo(param.getKeyword());
			orderNumCriteria.getAllCriteria().addAll(shoppingOrderItemExtendDOExampleCriteria.getAllCriteria());

			ShoppingOrderItemExtendDOExample.Criteria paroductCriteria = shoppingOrderItemExtendDOExample.or();
			paroductCriteria.andConsigneeNameLike("%" + param.getKeyword() + "%");
			paroductCriteria.getAllCriteria().addAll(shoppingOrderItemExtendDOExampleCriteria.getAllCriteria());
		}

		// 查询总记录数
		Long count = shoppingOrderItemExtendDOMapper.countByExample(shoppingOrderItemExtendDOExample);

		Page<ShoppingOrderItemExtendBO> rtn = new Page<>();
		rtn.setCurrentPage(param.getCurrentPage());
		rtn.setTotalCount(count.intValue());

		if (count <= 0 || param.getOffset() >= count) {
			return rtn;
		}

		// 按照退款详情的创建时间排序
		shoppingOrderItemExtendDOExample.setOrderByClause("srd.gmt_create desc");

		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

		List<ShoppingOrderItemExtendDO> shoppingOrderItemExtendDOList = shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(shoppingOrderItemExtendDOExample, rowBounds);
		rtn.setRecords(ShoppingOrderItemExtendConverter.convert(shoppingOrderItemExtendDOList));

		return rtn;
	}

	/**
	 * 查询购物订单项以及订单资料 用于发送评论资料
	 * 
	 * @param id
	 *            购物订单项id
	 * @return
	 */
	@Override
	public ShoppingOrderItemExtendBO getByComment(Long id) {
		ShoppingOrderItemExtendDOExample shoppingOrderItemExtendDOExample = new ShoppingOrderItemExtendDOExample();
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingOrder(true);
		ShoppingOrderItemExtendDOExample.Criteria shoppingOrderItemExtendDOExampleCriteria = shoppingOrderItemExtendDOExample.createCriteria();
		shoppingOrderItemExtendDOExampleCriteria.andIdEqualTo(id);
		List<ShoppingOrderItemExtendDO> shoppingOrderItemExtendDOList = shoppingOrderItemExtendDOMapper.selectByExample(shoppingOrderItemExtendDOExample);
		return shoppingOrderItemExtendDOList.isEmpty() ? null : ShoppingOrderItemExtendConverter.convert(shoppingOrderItemExtendDOList.get(0));
	}

	@Override
	public String getOrderItemProductName(Long id) {
		ShoppingOrderItemDOExample example = new ShoppingOrderItemDOExample();
		example.createCriteria().andShoppingOrderIdEqualTo(id);
		example.setOrderByClause("id asc");
		List<ShoppingOrderItemDO> itmes = shoppingOrderItemDOMapper.selectByExample(example);
		return itmes.get(0).getProductName();
	}
}
