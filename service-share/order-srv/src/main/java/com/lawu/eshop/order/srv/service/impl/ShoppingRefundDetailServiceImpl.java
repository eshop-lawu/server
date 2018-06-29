package com.lawu.eshop.order.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.dto.FreightDTO;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.IllegalOperationException;
import com.lawu.eshop.mq.dto.order.RevokeRefundRequestNoticeNotification;
import com.lawu.eshop.mq.dto.order.ShoppingRefundFillReturnAddressRemindNotification;
import com.lawu.eshop.mq.dto.order.ShoppingRefundRefuseRefundRemindNotification;
import com.lawu.eshop.mq.dto.order.ShoppingRefundToBeConfirmedForRefundRemindNotification;
import com.lawu.eshop.mq.dto.order.ShoppingRefundToBeRefundRemindNotification;
import com.lawu.eshop.mq.dto.order.ShoppingRefundToBeReturnRemindNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.dto.CalculateFreightDTO;
import com.lawu.eshop.order.param.ShoppingRefundDetailLogisticsInformationParam;
import com.lawu.eshop.order.param.ShoppingRefundDetailRerurnAddressParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToApplyForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToRefundForeignParam;
import com.lawu.eshop.order.srv.bo.RefundInformationBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingRefundDetailBO;
import com.lawu.eshop.order.srv.constants.ExceptionMessageConstant;
import com.lawu.eshop.order.srv.constants.PropertyNameConstant;
import com.lawu.eshop.order.srv.converter.ShoppingOrderItemExtendConverter;
import com.lawu.eshop.order.srv.converter.ShoppingRefundDetailConverter;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDOExample;
import com.lawu.eshop.order.srv.domain.extend.UpdateMerchantActualIncomeParam;
import com.lawu.eshop.order.srv.exception.CanNotAgreeToARefundException;
import com.lawu.eshop.order.srv.exception.CanNotAgreeToApplyException;
import com.lawu.eshop.order.srv.exception.CanNotApplyForPlatformInterventionException;
import com.lawu.eshop.order.srv.exception.CanNotCancelApplicationException;
import com.lawu.eshop.order.srv.exception.CanNotFillInTheReturnAddressException;
import com.lawu.eshop.order.srv.exception.CanNotFillOutTheReturnLogisticsException;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderItemDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundDetailDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundProcessDOMapper;
import com.lawu.eshop.order.srv.mapper.extend.ShoppingOrderExtendDOMapper;
import com.lawu.eshop.order.srv.mapper.extend.ShoppingOrderItemExtendDOMapper;
import com.lawu.eshop.order.srv.service.PropertyService;
import com.lawu.eshop.order.srv.service.ShoppingRefundDetailService;
import com.lawu.mq.message.MessageProducerService;
import com.lawu.utils.DateUtil;

@Service
public class ShoppingRefundDetailServiceImpl implements ShoppingRefundDetailService {

	private static Logger logger = LoggerFactory.getLogger(ShoppingRefundDetailServiceImpl.class);
	
	@Autowired
	private ShoppingRefundDetailDOMapper shoppingRefundDetailDOMapper;
	
	@Autowired
	private ShoppingRefundProcessDOMapper shoppingRefundProcessDOMapper;

	@Autowired
	private ShoppingOrderItemDOMapper shoppingOrderItemDOMapper;
	
	@Autowired
	private ShoppingOrderItemExtendDOMapper shoppingOrderItemExtendDOMapper;

	@Autowired
	private ShoppingOrderDOMapper shoppingOrderDOMapper;
	
	@Autowired
	private ShoppingOrderExtendDOMapper shoppingOrderExtendDOMapper;

	@Autowired
	private PropertyService propertyService;
	
    @Autowired
    private MessageProducerService messageProducerService;
	
	@Autowired
	@Qualifier("shoppingRefundAgreeToRefundTransactionMainServiceImpl")
	private TransactionMainService<Reply> shoppingRefundAgreeToRefundTransactionMainServiceImpl;
	
	@Autowired
	@Qualifier("shoppingRefundAgreeToRefundDeleteCommentTransactionMainServiceImpl")
	private TransactionMainService<Reply> shoppingRefundAgreeToRefundDeleteCommentTransactionMainServiceImpl;
	
	/**
	 * 根据购物退货详情id 获取购物退货详情
	 * 
	 * @param id
	 *            退货详情id
	 * @return
	 */
	@Override
	public ShoppingRefundDetailBO get(Long id) {
		ShoppingRefundDetailDO shoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(id);
		return ShoppingRefundDetailConverter.convert(shoppingRefundDetailDO);
	}

	/**
	 * 根据购物订单项id 获取购物退货详情
	 * 
	 * @param shoppingOrderItemId
	 *            购物订单项id
	 * @return
	 */
	@Override
	public ShoppingOrderItemExtendBO getByShoppingOrderItemId(Long shoppingOrderItemId) {
		ShoppingOrderItemExtendDOExample shoppingOrderItemExtendDOExample = new ShoppingOrderItemExtendDOExample();
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingOrder(true);
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingRefundDetail(true);
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingRefundProcess(true);
		ShoppingOrderItemExtendDOExample.Criteria criteria = shoppingOrderItemExtendDOExample.createCriteria();
		criteria.andIdEqualTo(shoppingOrderItemId);
		// 找到有效记录
		criteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());
		shoppingOrderItemExtendDOExample.setOrderByClause("srp.gmt_create desc");
		List<ShoppingOrderItemExtendDO> list = shoppingOrderItemExtendDOMapper.selectByExample(shoppingOrderItemExtendDOExample);
		return (list != null && !list.isEmpty()) ? ShoppingOrderItemExtendConverter.convert(list.get(0)) : null;
	}

	/**
	 * 买家填写退货的物流信息
	 * 
	 * @param id
	 *            退款详情id
	 * @param memberId
	 *            会员id
	 * @param param
	 *            退款详情物流信息
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月10日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void fillLogisticsInformation(Long id, Long memberId, ShoppingRefundDetailLogisticsInformationParam param) {
		ShoppingRefundDetailDO shoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(id);
		if (shoppingRefundDetailDO == null || shoppingRefundDetailDO.getStatus().equals(StatusEnum.INVALID.getValue())) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_REFUND_DATA_DOES_NOT_EXIST);
		}
		ShoppingOrderItemDO shoppingOrderItemDO  = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getShoppingOrderItemId());
		if (shoppingOrderItemDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
		}
		// 订单状态必须为退款中
		if (!shoppingOrderItemDO.getOrderStatus().equals(ShoppingOrderStatusEnum.REFUNDING.getValue())) {
			throw new CanNotFillOutTheReturnLogisticsException(ExceptionMessageConstant.ORDER_STATUS_IS_NOT_TO_BE_REFUND_STATUS);
		}
		// 只有待退货状态才能填写退货物流
		if (!shoppingOrderItemDO.getRefundStatus().equals(RefundStatusEnum.TO_BE_RETURNED.getValue())) {
			throw new CanNotFillOutTheReturnLogisticsException(ExceptionMessageConstant.REFUND_STATE_IS_NOT_A_STATE_TO_BE_RETURNED);
		}
		ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
		if (shoppingOrderDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
		}
		if (!shoppingOrderDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		// 更新订单项状态为待退款
		ShoppingOrderItemDO shoppingOrderItemUpdateDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getShoppingOrderItemId());
		shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.TO_BE_REFUNDED.getValue());
		// 重置发送提醒的次数
		shoppingOrderItemUpdateDO.setSendTime(0);
		shoppingOrderItemUpdateDO.setGmtModified(new Date());
		shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemUpdateDO);
		// 更新退款详情的物流信息
		ShoppingRefundDetailDO shoppingRefundDetailUpdateDO = new ShoppingRefundDetailDO();
		shoppingRefundDetailUpdateDO.setId(shoppingRefundDetailDO.getId());
		shoppingRefundDetailUpdateDO.setGmtSubmit(new Date());
		shoppingRefundDetailUpdateDO.setExpressCompanyId(param.getExpressCompanyId());
		shoppingRefundDetailUpdateDO.setExpressCompanyCode(param.getExpressCompanyCode());
		shoppingRefundDetailUpdateDO.setExpressCompanyName(param.getExpressCompanyName());
		shoppingRefundDetailUpdateDO.setWaybillNum(param.getWaybillNum());
		shoppingRefundDetailUpdateDO.setGmtModified(new Date());
		shoppingRefundDetailDOMapper.updateByPrimaryKeySelective(shoppingRefundDetailUpdateDO);
		// 加入退款流程
		ShoppingRefundProcessDO shoppingRefundProcessDO = new ShoppingRefundProcessDO();
		shoppingRefundProcessDO.setRefundStatus(shoppingOrderItemUpdateDO.getRefundStatus());
		shoppingRefundProcessDO.setShoppingRefundDetailId(shoppingRefundDetailUpdateDO.getId());
		shoppingRefundProcessDO.setGmtCreate(new Date());
		shoppingRefundProcessDOMapper.insertSelective(shoppingRefundProcessDO);
		// 用户填写退货物流，提醒买家退款
		ShoppingRefundToBeReturnRemindNotification notification = new ShoppingRefundToBeReturnRemindNotification();
		notification.setShoppingOrderItemId(shoppingOrderItemUpdateDO.getId());
		notification.setMerchantNum(shoppingOrderDO.getMerchantNum());
		notification.setMemberNum(shoppingOrderDO.getMemberNum());
		notification.setMemberNickname(shoppingOrderDO.getMemberNickname());
		notification.setOrderNum(shoppingOrderDO.getOrderNum());
		notification.setWaybillNum(shoppingRefundDetailUpdateDO.getWaybillNum());
		notification.setImgUrl(shoppingOrderItemDO.getProductFeatureImage());
		messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_TO_BE_RETURN_REMIND, notification);
	}

	/**
	 * 商家填写退货地址信息
	 * 
	 * @param id
	 *            退款详情id
	 * @param merchantId
	 *            商家id
	 * @param param
	 * 			     退货地址信息
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void fillReturnAddress(Long id, Long merchantId, ShoppingRefundDetailRerurnAddressParam param) {
		ShoppingRefundDetailDO shoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(id);
		if (shoppingRefundDetailDO == null || shoppingRefundDetailDO.getStatus().equals(StatusEnum.INVALID.getValue())) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_REFUND_DATA_DOES_NOT_EXIST);
		}
		ShoppingOrderItemDO shoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getShoppingOrderItemId());
		if (shoppingOrderItemDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
		}
		// 订单状态必须为退款中
		if (!shoppingOrderItemDO.getOrderStatus().equals(ShoppingOrderStatusEnum.REFUNDING.getValue())) {
			throw new CanNotFillInTheReturnAddressException(ExceptionMessageConstant.ORDER_STATUS_IS_NOT_TO_BE_REFUND_STATUS);
		}
		/*
		 *  1.退款状态必须为填写退货地址
		 *  2.退款类型为退货退款,并且退款状态当前为待确认状态	
		 */
		if (!shoppingOrderItemDO.getRefundStatus().equals(RefundStatusEnum.FILL_RETURN_ADDRESS.getValue())
				&& !(RefundStatusEnum.TO_BE_CONFIRMED.getValue().equals(shoppingOrderItemDO.getRefundStatus()) && ShoppingRefundTypeEnum.RETURN_REFUND.getValue().equals(shoppingRefundDetailDO.getType()))) {
			throw new CanNotFillInTheReturnAddressException(ExceptionMessageConstant.REFUND_STATUS_DOES_NOT_MATCH);
		}
		ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
		if (shoppingOrderDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
		}
		if (!shoppingOrderDO.getMerchantId().equals(merchantId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		
		ShoppingRefundDetailDO shoppingRefundDetailUpdateDO = new ShoppingRefundDetailDO();
		shoppingRefundDetailUpdateDO.setId(shoppingRefundDetailDO.getId());
		// 更新退款详情的退货地址信息
		shoppingRefundDetailUpdateDO.setGmtFill(new Date());
		shoppingRefundDetailUpdateDO.setConsigneeName(param.getConsigneeName());
		shoppingRefundDetailUpdateDO.setConsigneeMobile(param.getConsigneeMobile());
		shoppingRefundDetailUpdateDO.setConsigneeAddress(param.getConsigneeAddress());
		shoppingRefundDetailUpdateDO.setGmtModified(new Date());
		shoppingRefundDetailDOMapper.updateByPrimaryKeySelective(shoppingRefundDetailUpdateDO);
		
		ShoppingOrderItemDO shoppingOrderItemUpdateDO = new ShoppingOrderItemDO();
		shoppingOrderItemUpdateDO.setId(shoppingOrderItemDO.getId());
		// 更新订单项退款状态为待退货
		shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.TO_BE_RETURNED.getValue());
		// 重置发送提醒的次数
		shoppingOrderItemUpdateDO.setSendTime(0);
		shoppingOrderItemUpdateDO.setGmtModified(new Date());
		shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemUpdateDO);
		
		// 加入退款流程
		ShoppingRefundProcessDO shoppingRefundProcessDO = new ShoppingRefundProcessDO();
		shoppingRefundProcessDO.setRefundStatus(shoppingOrderItemUpdateDO.getRefundStatus());
		shoppingRefundProcessDO.setShoppingRefundDetailId(shoppingRefundDetailUpdateDO.getId());
		shoppingRefundProcessDO.setGmtCreate(new Date());
		shoppingRefundProcessDOMapper.insertSelective(shoppingRefundProcessDO);
		
		// 商家填写退货地址，提醒买家退货
		ShoppingRefundFillReturnAddressRemindNotification notification = new ShoppingRefundFillReturnAddressRemindNotification();
		notification.setShoppingOrderItemId(shoppingOrderItemUpdateDO.getId());
		notification.setMemberNum(shoppingOrderDO.getMemberNum());
		notification.setImgUrl(shoppingOrderItemDO.getProductFeatureImage());
		messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_FILL_RETURN_ADDRESS_REMIND, notification);
	}

	/**
	 * 商家是否同意买家的退货申请
	 * 
	 * @param id
	 *            退款详情id
	 * @param merchantId
	 *            商家id
	 * @param param
	 *            参数 是否同意申请
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	@Deprecated
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void agreeToApply(Long id, Long merchantId, ShoppingRefundDetailAgreeToApplyForeignParam param) {
		ShoppingRefundDetailDO shoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(id);
		if (shoppingRefundDetailDO == null || shoppingRefundDetailDO.getStatus().equals(StatusEnum.INVALID.getValue())) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_REFUND_DATA_DOES_NOT_EXIST);
		}
		ShoppingOrderItemDO shoppingOrderItemDO  = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getShoppingOrderItemId());
		if (shoppingOrderItemDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
		}
		// 订单状态必须为退款中
		if (!shoppingOrderItemDO.getOrderStatus().equals(ShoppingOrderStatusEnum.REFUNDING.getValue())) {
			throw new CanNotAgreeToApplyException(ExceptionMessageConstant.ORDER_STATUS_IS_NOT_TO_BE_REFUND_STATUS);
		}
		// 只有待确认状态才能同意会员的退款申请
		if (!shoppingOrderItemDO.getRefundStatus().equals(RefundStatusEnum.TO_BE_CONFIRMED.getValue())) {
			throw new CanNotAgreeToApplyException(ExceptionMessageConstant.THE_STATUS_OF_THE_REFUND_IS_NOT_PENDING);
		}
		ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
		if (shoppingOrderDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
		}
		if (!shoppingOrderDO.getMerchantId().equals(merchantId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		ShoppingOrderItemDO shoppingOrderItemUpdateDO = new ShoppingOrderItemDO();
		shoppingOrderItemUpdateDO.setId(shoppingOrderItemDO.getId());
		// 更新退款状态
		if (param.getIsAgree()) {
			// 判断是否需要退货
			if (shoppingRefundDetailDO.getType().equals(ShoppingRefundTypeEnum.REFUND.getValue())) {
				shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.TO_BE_REFUNDED.getValue());
			} else {
				shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.FILL_RETURN_ADDRESS.getValue());
			}
		} else {
			shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.REFUND_FAILED.getValue());
		}
		// 重置发送提醒的次数
		shoppingOrderItemUpdateDO.setSendTime(0);
		shoppingOrderItemUpdateDO.setGmtModified(new Date());
		shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemUpdateDO);
		ShoppingRefundDetailDO shoppingRefundDetailUpdateDO = new ShoppingRefundDetailDO();
		shoppingRefundDetailUpdateDO.setId(shoppingRefundDetailDO.getId());
		// 更新退款详情
		shoppingRefundDetailUpdateDO.setGmtConfirmed(new Date());
		if (!param.getIsAgree()) {
			shoppingRefundDetailUpdateDO.setGmtRefund(new Date());
		}
		shoppingRefundDetailUpdateDO.setRefusalReasons(param.getRefusalReasons());
		shoppingRefundDetailUpdateDO.setIsAgree(param.getIsAgree());
		shoppingRefundDetailUpdateDO.setGmtModified(new Date());
		shoppingRefundDetailDOMapper.updateByPrimaryKeySelective(shoppingRefundDetailUpdateDO);
		// 加入退款流程
		ShoppingRefundProcessDO shoppingRefundProcessDO = new ShoppingRefundProcessDO();
		shoppingRefundProcessDO.setRefundStatus(shoppingOrderItemUpdateDO.getRefundStatus());
		shoppingRefundProcessDO.setShoppingRefundDetailId(shoppingRefundDetailDO.getId());
		shoppingRefundProcessDO.setGmtCreate(new Date());
		shoppingRefundProcessDOMapper.insertSelective(shoppingRefundProcessDO);
		if (!param.getIsAgree()) {
			// 商家拒绝退款，提醒买家
			ShoppingRefundRefuseRefundRemindNotification notification = new ShoppingRefundRefuseRefundRemindNotification();
			notification.setShoppingOrderItemId(shoppingOrderDO.getId());
			notification.setMemberNum(shoppingOrderDO.getMemberNum());
			notification.setImgUrl(shoppingOrderItemDO.getProductFeatureImage());
			messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_REFUSE_REFUND_REMIND, notification);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void agreeToRefund(Long id, Long merchantId, ShoppingRefundDetailAgreeToRefundForeignParam param, boolean isAutoRefund) {
		ShoppingRefundDetailDO shoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(id);
		if (shoppingRefundDetailDO == null || shoppingRefundDetailDO.getStatus().equals(StatusEnum.INVALID.getValue())) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_REFUND_DATA_DOES_NOT_EXIST);
		}
		ShoppingOrderItemDO shoppingOrderItemDO  = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getShoppingOrderItemId());
		if (shoppingOrderItemDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
		}
		// 订单状态必须为退款中
		if (!shoppingOrderItemDO.getOrderStatus().equals(ShoppingOrderStatusEnum.REFUNDING.getValue())) {
			throw new CanNotAgreeToARefundException(ExceptionMessageConstant.ORDER_STATUS_IS_NOT_TO_BE_REFUND_STATUS);
		}
		/*
		 *  1.退款状态必须为待退款或者平台介入状态
		 *  2.待确认状态，如果退款类型为退款可以直接退款
		 *  3.如果退款状态为平台介入,运营人员可以操作退款
		 */
		if (!isAutoRefund && !shoppingOrderItemDO.getRefundStatus().equals(RefundStatusEnum.TO_BE_REFUNDED.getValue())
				&& !shoppingOrderItemDO.getRefundStatus().equals(RefundStatusEnum.PLATFORM_INTERVENTION.getValue())
				// 待商家确认|退款-商家可以直接操作退款
				&& !(ShoppingRefundTypeEnum.REFUND.getValue().equals(shoppingRefundDetailDO.getType()) && RefundStatusEnum.TO_BE_CONFIRMED.getValue().equals(shoppingOrderItemDO.getRefundStatus())) 
				// 待商家确认-商家可以拒绝退款
				&& !(!param.getIsAgree() && RefundStatusEnum.TO_BE_CONFIRMED.getValue().equals(shoppingOrderItemDO.getRefundStatus()))) {
			throw new CanNotAgreeToARefundException(ExceptionMessageConstant.REFUND_STATUS_DOES_NOT_MATCH);
		}
		ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
		if (shoppingOrderDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
		}
		if (merchantId != null && !shoppingOrderDO.getMerchantId().equals(merchantId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		ShoppingOrderItemDO shoppingOrderItemUpdateDO = new ShoppingOrderItemDO();
		shoppingOrderItemUpdateDO.setId(shoppingOrderItemDO.getId());
		if (param.getIsAgree()) {
			shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.REFUND_SUCCESSFULLY.getValue());
			// 设置购物订单项的订单状态为交易关闭
			shoppingOrderItemUpdateDO.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
		} else {
			shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.REFUND_FAILED.getValue());
		}
		// 重置发送提醒的次数
		shoppingOrderItemUpdateDO.setSendTime(0);
		shoppingOrderItemUpdateDO.setGmtModified(new Date());
		shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemUpdateDO);
		// 更新退款详情
		ShoppingRefundDetailDO shoppingRefundDetailUpdateDO = new ShoppingRefundDetailDO();
		shoppingRefundDetailUpdateDO.setId(shoppingRefundDetailDO.getId());
		shoppingRefundDetailUpdateDO.setIsAgree(param.getIsAgree());
		shoppingRefundDetailUpdateDO.setRefusalReasons(param.getRefusalReasons());
		shoppingRefundDetailUpdateDO.setGmtRefund(new Date());
		shoppingRefundDetailUpdateDO.setGmtModified(new Date());
		shoppingRefundDetailUpdateDO.setRefuseImages(param.getRefuseImages());
	      // 冗余退款前订单的状态
        shoppingRefundDetailUpdateDO.setOrderStatus(shoppingOrderDO.getOrderStatus());
		shoppingRefundDetailDOMapper.updateByPrimaryKeySelective(shoppingRefundDetailUpdateDO);
		// 加入退款流程
		ShoppingRefundProcessDO shoppingRefundProcessDO = new ShoppingRefundProcessDO();
		shoppingRefundProcessDO.setRefundStatus(shoppingOrderItemUpdateDO.getRefundStatus());
		shoppingRefundProcessDO.setShoppingRefundDetailId(shoppingRefundDetailUpdateDO.getId());
		shoppingRefundProcessDO.setGmtCreate(new Date());
		shoppingRefundProcessDOMapper.insertSelective(shoppingRefundProcessDO);
		if (param.getIsAgree()) {
			// 更新实际支付给商家的金额
			UpdateMerchantActualIncomeParam updateMerchantActualIncomeParam = new UpdateMerchantActualIncomeParam();
			updateMerchantActualIncomeParam.setId(shoppingOrderDO.getId());
			updateMerchantActualIncomeParam.setRefundAmount(shoppingRefundDetailDO.getAmount());
			shoppingOrderExtendDOMapper.updateMerchantActualIncome(updateMerchantActualIncomeParam);
			
			// 发送MQ消息给property模块，退款给用户
			shoppingRefundAgreeToRefundTransactionMainServiceImpl.sendNotice(shoppingOrderItemDO.getId());
			
			// 发送MQ消息给mall模块，删除商品订单评价
			shoppingRefundAgreeToRefundDeleteCommentTransactionMainServiceImpl.sendNotice(shoppingOrderItemDO.getId());
			
	        // 如果当前订单下的所有订单项都是交易关闭状态。关闭订单
            ShoppingOrderItemDOExample shoppingOrderItemDOExample = new ShoppingOrderItemDOExample();
            shoppingOrderItemDOExample.createCriteria().andShoppingOrderIdEqualTo(shoppingOrderItemDO.getShoppingOrderId());
            List<ShoppingOrderItemDO> shoppingOrderItemDOList = shoppingOrderItemDOMapper.selectByExample(shoppingOrderItemDOExample);
            /*
             * 是否有订单处于退款中
             */
            boolean is_any_order_for_refund = false;
            /*
             * 是否全部订单都是关闭的
             */
            boolean is_all_orders_are_closed = true;
            for (ShoppingOrderItemDO item : shoppingOrderItemDOList) {
                if (ShoppingOrderStatusEnum.REFUNDING.getValue().equals(item.getOrderStatus())) {
                    is_any_order_for_refund = true;
                }
                if (!ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue().equals(item.getOrderStatus())) {
                    is_all_orders_are_closed = false;
                }
            }
            ShoppingOrderDO shoppingOrderUpdateDO = new ShoppingOrderDO();
            shoppingOrderUpdateDO.setId(shoppingOrderDO.getId());
            shoppingOrderUpdateDO.setGmtModified(new Date());
            // 如果购物订单下的所有订单项都是关闭状态关闭购物订单
            if (is_all_orders_are_closed) {
                shoppingOrderUpdateDO.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
                // 更新交易时间
                shoppingOrderUpdateDO.setGmtTransaction(new Date());
            }
            if (!is_any_order_for_refund) {
                shoppingOrderUpdateDO.setIsRefundItems(false);
            }
			// 发送MQ消息之后再更新购物订单表，因为需要获取订单取消之前的订单状态
			shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderUpdateDO);
			
			// 商家同意退款，提醒买家
			ShoppingRefundToBeRefundRemindNotification notification = new ShoppingRefundToBeRefundRemindNotification();
			notification.setShoppingOrderItemId(shoppingRefundDetailDO.getShoppingOrderItemId());
			notification.setMemberNum(shoppingOrderDO.getMemberNum());
			notification.setRefundAmount(shoppingRefundDetailDO.getAmount());
			notification.setActualAmount(shoppingRefundDetailDO.getActualAmount());
			notification.setPoint(shoppingRefundDetailDO.getPoint());
			notification.setImgUrl(shoppingOrderItemDO.getProductFeatureImage());
			messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_TO_BE_REFUND_REMIND, notification);
		} else {
			// 商家拒绝退款，提醒买家
			ShoppingRefundRefuseRefundRemindNotification notification = new ShoppingRefundRefuseRefundRemindNotification();
			notification.setShoppingOrderItemId(shoppingRefundDetailDO.getShoppingOrderItemId());
			notification.setMemberNum(shoppingOrderDO.getMemberNum());
			notification.setImgUrl(shoppingOrderItemDO.getProductFeatureImage());
			messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_REFUSE_REFUND_REMIND, notification);
		}
	}

	/**
	 * 如果商家拒绝买家的退款申请或者拒绝退款 买家可以申请平台介入
	 * 
	 * @param id
	 *            退款详情id
	 * @param memberId
	 *            会员id
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void platformIntervention(Long id, Long memberId) {
		ShoppingRefundDetailDO shoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(id);
		if (shoppingRefundDetailDO == null || shoppingRefundDetailDO.getStatus().equals(StatusEnum.INVALID.getValue())) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_REFUND_DATA_DOES_NOT_EXIST);
		}
		ShoppingOrderItemDO shoppingOrderItemDO  = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getShoppingOrderItemId());
		if (shoppingOrderItemDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
		}
		// 订单状态必须为退款中
		if (!shoppingOrderItemDO.getOrderStatus().equals(ShoppingOrderStatusEnum.REFUNDING.getValue())) {
			throw new CanNotApplyForPlatformInterventionException(ExceptionMessageConstant.ORDER_STATUS_IS_NOT_TO_BE_REFUND_STATUS);
		}
		// 只有退款失败才能申请平台介入
		if (!shoppingOrderItemDO.getRefundStatus().equals(RefundStatusEnum.REFUND_FAILED.getValue())) {
			throw new CanNotApplyForPlatformInterventionException(ExceptionMessageConstant.THE_REFUND_STATUS_IS_NOT_A_REFUND_FAILURE);
		}
		ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
		if (shoppingOrderDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
		}
		if (!shoppingOrderDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		// 更新订单项的退款状态为平台介入
		ShoppingOrderItemDO shoppingOrderItemUpdateDO = new ShoppingOrderItemDO();
		shoppingOrderItemUpdateDO.setId(shoppingOrderItemDO.getId());
		shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.PLATFORM_INTERVENTION.getValue());
		// 重置发送提醒的次数
		shoppingOrderItemUpdateDO.setSendTime(0);
		shoppingOrderItemUpdateDO.setGmtModified(new Date());
		shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemUpdateDO);

		// 更新退款详情
		ShoppingRefundDetailDO shoppingRefundDetailUpdateDO = new ShoppingRefundDetailDO();
		shoppingRefundDetailUpdateDO.setId(shoppingRefundDetailDO.getId());
		shoppingRefundDetailUpdateDO.setGmtIntervention(new Date());
		shoppingRefundDetailUpdateDO.setGmtModified(new Date());
		shoppingRefundDetailDOMapper.updateByPrimaryKeySelective(shoppingRefundDetailUpdateDO);
		
		// 加入退款流程
		ShoppingRefundProcessDO shoppingRefundProcessDO = new ShoppingRefundProcessDO();
		shoppingRefundProcessDO.setRefundStatus(shoppingOrderItemUpdateDO.getRefundStatus());
		shoppingRefundProcessDO.setShoppingRefundDetailId(shoppingRefundDetailUpdateDO.getId());
		shoppingRefundProcessDO.setGmtCreate(new Date());
		shoppingRefundProcessDOMapper.insertSelective(shoppingRefundProcessDO);
	}

	/**
	 * 买家撤销退货申请
	 * 
	 * @param id
	 *            退款详情id
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void revokeRefundRequest(Long id) {
		revokeRefundRequest(id, null);
	}
	
	/**
	 * 买家撤销退货申请
	 * 
	 * @param id
	 *            退款详情id
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void revokeRefundRequest(Long id, Long memberId) {
		ShoppingRefundDetailDO shoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByPrimaryKey(id);
		if (shoppingRefundDetailDO == null || shoppingRefundDetailDO.getStatus().equals(StatusEnum.INVALID.getValue())) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_REFUND_DATA_DOES_NOT_EXIST);
		}
		ShoppingOrderItemDO shoppingOrderItemDO  = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingRefundDetailDO.getShoppingOrderItemId());
		if (shoppingOrderItemDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
		}
		// 订单状态必须为退款中
		if (!shoppingOrderItemDO.getOrderStatus().equals(ShoppingOrderStatusEnum.REFUNDING.getValue())) {
			throw new CanNotCancelApplicationException(ExceptionMessageConstant.ORDER_STATUS_IS_NOT_TO_BE_REFUND_STATUS);
		}
		ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
		if (shoppingOrderDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
		}
		if (memberId != null && !shoppingOrderDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		// 清空退款状态
		shoppingOrderItemDO.setRefundStatus(null);
		// 还原订单之前状态
		shoppingOrderItemDO.setOrderStatus(shoppingOrderDO.getOrderStatus());
		// 重置发送提醒的次数
		shoppingOrderItemDO.setSendTime(0);
		shoppingOrderItemDO.setGmtModified(new Date());
		shoppingOrderItemDOMapper.updateByPrimaryKey(shoppingOrderItemDO);
		
		/*
		 *  查询是否还有处于退款中的订单项
		 *  如果没有更新订单IsRefundItems为false
		 */
		ShoppingOrderItemDOExample shoppingOrderItemDOExample = new ShoppingOrderItemDOExample();
		shoppingOrderItemDOExample.createCriteria().andShoppingOrderIdEqualTo(shoppingOrderDO.getId()).andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
		Long count = shoppingOrderItemDOMapper.countByExample(shoppingOrderItemDOExample);
		if (count == null || count <= 0) {
		    ShoppingOrderDO shoppingOrderUpdateDO = new ShoppingOrderDO();
		    if (shoppingRefundDetailDO.getFreightPrice() != null) {
    		    shoppingOrderUpdateDO.setFreightPrice(shoppingOrderDO.getFreightPrice()
    		            .add(shoppingRefundDetailDO.getFreightPrice()));
		    }
		    if (shoppingRefundDetailDO.getFreightPriceDeductionPoints() != null) {
		        shoppingOrderUpdateDO.setFreightPrice(shoppingOrderUpdateDO.getFreightPrice()
		                .add(shoppingRefundDetailDO.getFreightPriceDeductionPoints().divide(shoppingOrderDO.getExchangeRate())));
		        shoppingOrderUpdateDO.setFreightPriceDeductionPoints(shoppingOrderDO.getFreightPriceDeductionPoints().add(shoppingRefundDetailDO.getFreightPriceDeductionPoints()));
		    }
		    shoppingOrderUpdateDO.setId(shoppingOrderDO.getId());
		    shoppingOrderUpdateDO.setIsRefundItems(false);
		    shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderUpdateDO);
		}
		
		// 更新退款详情
		// 设置订单的状态为无效
		ShoppingRefundDetailDO shoppingRefundDetailUpdateDO = new ShoppingRefundDetailDO();
		shoppingRefundDetailUpdateDO.setId(shoppingRefundDetailDO.getId());
		shoppingRefundDetailUpdateDO.setStatus(StatusEnum.INVALID.getValue());
		shoppingRefundDetailUpdateDO.setGmtModified(new Date());
		shoppingRefundDetailDOMapper.updateByPrimaryKeySelective(shoppingRefundDetailUpdateDO);
		
		// 撤销退款申请发送推送消息给商家
		RevokeRefundRequestNoticeNotification notification = new RevokeRefundRequestNoticeNotification();
        notification.setShoppingOrderItemId(shoppingOrderItemDO.getId());
        notification.setMerchantNum(shoppingOrderDO.getMerchantNum());
        notification.setMemberNickname(shoppingOrderDO.getMemberNickname());
        notification.setOrderNum(shoppingOrderDO.getOrderNum());
        notification.setImgUrl(shoppingOrderItemDO.getProductFeatureImage());
        messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_REVOKE_REFUND_REQUEST_NOTICE, notification);
	}
	
    @Override
    public List<ShoppingOrderItemExtendDO> selectAutoRemindToBeConfirmedForRefund(int offset, int pageSize) {
        ShoppingOrderItemExtendDOExample example = new ShoppingOrderItemExtendDOExample();
        // 查询结果包括退款详情
        example.setIsIncludeShoppingRefundDetail(true);
        
        ShoppingOrderItemExtendDOExample.Criteria criteria = example.createCriteria();
        
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
        // 找到其中有效的记录
        criteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());
        criteria.andSRDTypeEqualTo(ShoppingRefundTypeEnum.REFUND.getValue());
        
        // 提醒时间
        String remindTime = propertyService.getByName(PropertyNameConstant.TO_BE_CONFIRMED_FOR_REFUND_REMIND_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(remindTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeEqualTo(0);
        
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
        return shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    /**
     * 买家申请退款，商家未操作处理
     * 退款类型-退款
     * 平台提醒商家，否则自动退款给买家
     * 
     * @author Sunny
     * @return 
     */
    @Override
    public List<ShoppingOrderItemExtendDO> selectAutoRefundToBeConfirmedForRefund(int offset, int pageSize) {
        ShoppingOrderItemExtendDOExample example = new ShoppingOrderItemExtendDOExample();
        
        // 查询结果包括退款详情
        example.setIsIncludeShoppingRefundDetail(true);
        
        ShoppingOrderItemExtendDOExample.Criteria criteria = example.createCriteria();
        
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
        // 找到其中有效的记录
        criteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());
        criteria.andSRDTypeEqualTo(ShoppingRefundTypeEnum.REFUND.getValue());
        
        // 退款时间
        String refundTime = propertyService.getByName(PropertyNameConstant.TO_BE_CONFIRMED_FOR_REFUND_REFUND_TIME);
        // 超过退款时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(refundTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeGreaterThanOrEqualTo(1);
        
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
        return shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    public List<ShoppingOrderItemExtendDO> selectAutoRemindToBeConfirmedForReturnRefund(int offset, int pageSize) {
        ShoppingOrderItemExtendDOExample example = new ShoppingOrderItemExtendDOExample();
        
        // 查询结果包括退款详情
        example.setIsIncludeShoppingRefundDetail(true);
        
        ShoppingOrderItemExtendDOExample.Criteria criteria = example.createCriteria();
        
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
        // 找到其中有效的记录
        criteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());
        criteria.andSRDTypeEqualTo(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
        
        // 提醒时间
        String remindTime = propertyService.getByName(PropertyNameConstant.TO_BE_CONFIRMED_FOR_RETURN_REFUND_REMIND_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(remindTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeEqualTo(0);
        
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
        return shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    public List<ShoppingOrderItemExtendDO> selectAutoRefundToBeConfirmedForReturnRefund(int offset, int pageSize) {
        ShoppingOrderItemExtendDOExample example = new ShoppingOrderItemExtendDOExample();
        
        // 查询结果包括退款详情
        example.setIsIncludeShoppingRefundDetail(true);
        
        ShoppingOrderItemExtendDOExample.Criteria criteria = example.createCriteria();
        
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
        // 找到其中有效的记录
        criteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());
        criteria.andSRDTypeEqualTo(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
        
        // 退款时间
        String refundTime = propertyService.getByName(PropertyNameConstant.TO_BE_CONFIRMED_FOR_RETURN_REFUND_REFUND_TIME);
        
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(refundTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeGreaterThanOrEqualTo(1);
        
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
        return shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    public List<ShoppingOrderItemDO> selectAutoRemindRefundFailed(int offset, int pageSize) {
        ShoppingOrderItemDOExample example = new ShoppingOrderItemDOExample();
        ShoppingOrderItemDOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.REFUND_FAILED.getValue());
        // 提醒时间
        String remindTime = propertyService.getByName(PropertyNameConstant.REFUND_FAILED_REMIND_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(remindTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeEqualTo(0);
        
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
        return shoppingOrderItemDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    public List<ShoppingOrderItemDO> selectAutoRevokeRefundFailed(int offset, int pageSize) {
        ShoppingOrderItemDOExample example = new ShoppingOrderItemDOExample();
        ShoppingOrderItemDOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.REFUND_FAILED.getValue());
        // 自动撤销撤销时间
        String revokeTime = propertyService.getByName(PropertyNameConstant.REFUND_FAILED_REVOKE_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(revokeTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeGreaterThanOrEqualTo(1);
        
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
        return shoppingOrderItemDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void executeAutoRevokeRefundRequest(ShoppingOrderItemDO shoppingOrderItemDO) {
        ShoppingRefundDetailDOExample example = new ShoppingRefundDetailDOExample();
        example.createCriteria().andShoppingOrderItemIdEqualTo(shoppingOrderItemDO.getId()).andStatusEqualTo(StatusEnum.VALID.getValue());
        ShoppingRefundDetailDO shoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByExample(example).get(0);
        revokeRefundRequest(shoppingRefundDetailDO.getId());
    }
    
    @Override
    public List<ShoppingOrderItemDO> selectAutoRemindFillReturnAddress(int offset, int pageSize) {
        ShoppingOrderItemDOExample example = new ShoppingOrderItemDOExample();
        
        ShoppingOrderItemDOExample.Criteria criteria = example.createCriteria();
        
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.FILL_RETURN_ADDRESS.getValue());
        
        // 提醒时间
        String remindTime = propertyService.getByName(PropertyNameConstant.FILL_RETURN_ADDRESS_REMIND_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(remindTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeEqualTo(0);
        
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
        return shoppingOrderItemDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    public List<ShoppingOrderItemDO> selectAutoRefundFillReturnAddress(int offset, int pageSize) {
        ShoppingOrderItemDOExample example = new ShoppingOrderItemDOExample();
        
        ShoppingOrderItemDOExample.Criteria criteria = example.createCriteria();
        
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.FILL_RETURN_ADDRESS.getValue());
        
        // 自动退款时间
        String refundTime = propertyService.getByName(PropertyNameConstant.FILL_RETURN_ADDRESS_REFUND_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(refundTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeGreaterThanOrEqualTo(1);
        
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
        return shoppingOrderItemDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void executeAutoRefund(ShoppingOrderItemDO shoppingOrderItemDO) {
        ShoppingRefundDetailDO shoppingRefundDetailDO = null;
        if (!(shoppingOrderItemDO instanceof ShoppingOrderItemExtendDO)) {
            ShoppingRefundDetailDOExample example = new ShoppingRefundDetailDOExample();
            example.createCriteria().andShoppingOrderItemIdEqualTo(shoppingOrderItemDO.getId()).andStatusEqualTo(StatusEnum.VALID.getValue());
            shoppingRefundDetailDO = shoppingRefundDetailDOMapper.selectByExample(example).get(0);
        } else {
            shoppingRefundDetailDO = ((ShoppingOrderItemExtendDO) shoppingOrderItemDO).getShoppingRefundDetail();
        }
        agreeToRefund(shoppingRefundDetailDO.getId());
    }
    
    @Override
    public List<ShoppingOrderItemDO> selectAutoRemindToBeReturn(int offset, int pageSize) {
        ShoppingOrderItemDOExample example = new ShoppingOrderItemDOExample();
        ShoppingOrderItemDOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.TO_BE_RETURNED.getValue());
        // 提醒时间
        String remindTime = propertyService.getByName(PropertyNameConstant.TO_BE_RETURNED_REMIND_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(remindTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeEqualTo(0);
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return shoppingOrderItemDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    public List<ShoppingOrderItemDO> selectAutoRevokeToBeReturn(int offset, int pageSize) {
        ShoppingOrderItemDOExample example = new ShoppingOrderItemDOExample();
        ShoppingOrderItemDOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.TO_BE_RETURNED.getValue());
        // 自动撤销撤销时间
        String revokeTime = propertyService.getByName(PropertyNameConstant.TO_BE_RETURNED_REVOKE_TIME);
        // 超过撤销时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(revokeTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeGreaterThanOrEqualTo(1);
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return shoppingOrderItemDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeAutoRemindToBeReturn(ShoppingOrderItemDO shoppingOrderItemDO) {
        // 更新发送次数，但是不更新更新时间字段
        ShoppingOrderItemDO shoppingOrderItemUpdateDO = new ShoppingOrderItemDO();
        shoppingOrderItemUpdateDO.setId(shoppingOrderItemDO.getId());
        int sendTime = shoppingOrderItemDO.getSendTime() == null ? 1 : shoppingOrderItemDO.getSendTime().intValue() + 1;
        shoppingOrderItemUpdateDO.setSendTime(sendTime);
        shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemUpdateDO);
        
        // 商家填写退货地址，提醒买家退货
        ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
        ShoppingRefundFillReturnAddressRemindNotification notification = new ShoppingRefundFillReturnAddressRemindNotification();
        notification.setShoppingOrderItemId(shoppingOrderItemDO.getId());
        notification.setMemberNum(shoppingOrderDO.getMemberNum());
        messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_FILL_RETURN_ADDRESS_REMIND, notification);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundRemind(ShoppingOrderItemDO shoppingOrderItemDO) {
        // 更新发送次数，但是不更新更新时间字段
        ShoppingOrderItemDO shoppingOrderItemUpdateDO = new ShoppingOrderItemDO();
        shoppingOrderItemUpdateDO.setId(shoppingOrderItemDO.getId());
        int sendTime = shoppingOrderItemDO.getSendTime() == null ? 1 : shoppingOrderItemDO.getSendTime().intValue() + 1;
        shoppingOrderItemUpdateDO.setSendTime(sendTime);
        shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemUpdateDO);
        
        // 用户申请退款，提醒商家处理
        ShoppingRefundToBeConfirmedForRefundRemindNotification notification = new ShoppingRefundToBeConfirmedForRefundRemindNotification();
        ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
        notification.setShoppingOrderItemId(shoppingOrderItemDO.getId());
        notification.setMemberNum(shoppingOrderDO.getMemberNum());
        notification.setMerchantNum(shoppingOrderDO.getMerchantNum());
        notification.setMemberNickname(shoppingOrderDO.getMemberNickname());
        messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_TO_BE_CONFIRMED_FOR_REFUND_REMIND, notification);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundFailedRemind(ShoppingOrderItemDO shoppingOrderItemDO) {
        // 更新发送次数，但是不更新更新时间字段
        ShoppingOrderItemDO shoppingOrderItemUpdateDO = new ShoppingOrderItemDO();
        shoppingOrderItemUpdateDO.setId(shoppingOrderItemDO.getId());
        int sendTime = shoppingOrderItemDO.getSendTime() == null ? 1 : shoppingOrderItemDO.getSendTime().intValue() + 1;
        shoppingOrderItemUpdateDO.setSendTime(sendTime);
        shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemUpdateDO);
        
        // 商家拒绝退款，提醒买家
        ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
        ShoppingRefundRefuseRefundRemindNotification notification = new ShoppingRefundRefuseRefundRemindNotification();
        notification.setShoppingOrderItemId(shoppingOrderDO.getId());
        notification.setMemberNum(shoppingOrderDO.getMemberNum());
        messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_REFUSE_REFUND_REMIND, notification);
    }
    
    @Override
    public List<ShoppingOrderItemExtendDO> selectAutoRemindToBeRefundWithRefund(int offset, int pageSize) {
        ShoppingOrderItemExtendDOExample example = new ShoppingOrderItemExtendDOExample();
        example.setIsIncludeShoppingRefundDetail(true);
        ShoppingOrderItemExtendDOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.TO_BE_REFUNDED.getValue());
        // 退款类型为退款
        criteria.andSRDTypeEqualTo(ShoppingRefundTypeEnum.REFUND.getValue());
        // 有效的退款记录
        criteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());
        // 提醒时间
        String remindTime = propertyService.getByName(PropertyNameConstant.TO_BE_CONFIRMED_FOR_REFUND_REMIND_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(remindTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeEqualTo(0);
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    public List<ShoppingOrderItemExtendDO> selectAutoRefundToBeRefundWithRefund(int offset, int pageSize) {
        ShoppingOrderItemExtendDOExample example = new ShoppingOrderItemExtendDOExample();
        example.setIsIncludeShoppingRefundDetail(true);
        ShoppingOrderItemExtendDOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.TO_BE_REFUNDED.getValue());
        // 退款类型为退款
        criteria.andSRDTypeEqualTo(ShoppingRefundTypeEnum.REFUND.getValue());
        // 有效的退款记录
        criteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());
        // 退款时间
        String refundTime = propertyService.getByName(PropertyNameConstant.TO_BE_CONFIRMED_FOR_REFUND_REFUND_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(refundTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeGreaterThanOrEqualTo(1);
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    public List<ShoppingOrderItemExtendDO> selectAutoFirstTimeRemindToBeRefundWithReturnRefund(int offset, int pageSize) {
        ShoppingOrderItemExtendDOExample example = new ShoppingOrderItemExtendDOExample();
        example.setIsIncludeShoppingRefundDetail(true);
        ShoppingOrderItemExtendDOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.TO_BE_REFUNDED.getValue());
        // 退款类型为退款
        criteria.andSRDTypeEqualTo(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
        // 有效的退款记录
        criteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());
        // 第一次提醒时间
        String firstRemindTime = propertyService.getByName(PropertyNameConstant.TO_BE_REFUNDED_REMIND_FIRST_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(firstRemindTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeEqualTo(0);
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    public List<ShoppingOrderItemExtendDO> selectAutoSecondTimeRemindToBeRefundWithReturnRefund(int offset, int pageSize) {
        ShoppingOrderItemExtendDOExample example = new ShoppingOrderItemExtendDOExample();
        example.setIsIncludeShoppingRefundDetail(true);
        ShoppingOrderItemExtendDOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.TO_BE_REFUNDED.getValue());
        // 退款类型为退款
        criteria.andSRDTypeEqualTo(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
        // 有效的退款记录
        criteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());
        // 第二次提醒时间
        String secondRemindTime = propertyService.getByName(PropertyNameConstant.TO_BE_REFUNDED_REMIND_SECOND_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(secondRemindTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeEqualTo(1);
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    public List<ShoppingOrderItemExtendDO> selectAutoRefundToBeRefundWithReturnRefund(int offset, int pageSize) {
        ShoppingOrderItemExtendDOExample example = new ShoppingOrderItemExtendDOExample();
        example.setIsIncludeShoppingRefundDetail(true);
        ShoppingOrderItemExtendDOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
        criteria.andRefundStatusEqualTo(RefundStatusEnum.TO_BE_REFUNDED.getValue());
        // 退款类型为退款
        criteria.andSRDTypeEqualTo(ShoppingRefundTypeEnum.RETURN_REFUND.getValue());
        // 有效的退款记录
        criteria.andSRDStatusEqualTo(StatusEnum.VALID.getValue());
        // 退款时间
        String refundTime = propertyService.getByName(PropertyNameConstant.TO_BE_REFUNDED_REFUND_TIME);
        // 超过提醒时间
        criteria.andGmtModifiedLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(refundTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andSendTimeGreaterThanOrEqualTo(2);
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundwithReturnRefundRemind(ShoppingOrderItemExtendDO shoppingOrderItemExtendDO) {
        // 更新发送次数，但是不更新更新时间字段
        ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
        shoppingOrderItemDO.setId(shoppingOrderItemExtendDO.getId());
        int sendTime = shoppingOrderItemExtendDO.getSendTime() == null ? 1 : shoppingOrderItemExtendDO.getSendTime().intValue() + 1;
        shoppingOrderItemDO.setSendTime(sendTime);
        shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemDO);
        
        // 用户填写退货物流，提醒买家退款
        ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemExtendDO.getShoppingOrderId());
        ShoppingRefundToBeReturnRemindNotification notification = new ShoppingRefundToBeReturnRemindNotification();
        notification.setShoppingOrderItemId(shoppingOrderItemExtendDO.getId());
        notification.setMerchantNum(shoppingOrderDO.getMerchantNum());
        notification.setMemberNum(shoppingOrderDO.getMemberNum());
        notification.setMemberNickname(shoppingOrderDO.getMemberNickname());
        notification.setOrderNum(shoppingOrderDO.getOrderNum());
        notification.setWaybillNum(shoppingOrderItemExtendDO.getShoppingRefundDetail().getWaybillNum());
        messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_TO_BE_RETURN_REMIND, notification);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void agreeToRefund(Long id) {
        ShoppingRefundDetailAgreeToRefundForeignParam param = new ShoppingRefundDetailAgreeToRefundForeignParam();
        param.setIsAgree(true);
        // 因为定时任务也有用到这个退款接口，为了防止影响后续的流程，捕捉可能产生的异常
        try {
            agreeToRefund(id, null, param, true);
        } catch (DataNotExistException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalOperationException e) {
            logger.error(e.getMessage(), e);
        } catch (CanNotAgreeToARefundException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public RefundInformationBO getRefundInformation(Long shoppingOrderItemId, Long memberId) {
        ShoppingOrderItemDO shoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemId);
        if (shoppingOrderItemDO == null) {
            throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST);
        }
        ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
        if (shoppingOrderDO == null) {
            throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
        }
        // 鉴权
        if (!shoppingOrderDO.getMemberId().equals(memberId)) {
            throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
        }
        
        /*
         *  如果商品还未发货需要计算运费金额
         *  1.如果运费规则为空,则不计算运费
         */
        RefundInformationBO rtn = new RefundInformationBO();
        rtn.setAmount(BigDecimal.ZERO);
        rtn.setActualAmount(BigDecimal.ZERO);
        rtn.setPoint(BigDecimal.ZERO);
        rtn.setFreightPrice(BigDecimal.ZERO);
        rtn.setFreightPriceDeductionPoints(BigDecimal.ZERO);
        if (ShoppingOrderStatusEnum.BE_SHIPPED == ShoppingOrderStatusEnum.getEnum(shoppingOrderDO.getOrderStatus()) 
                && shoppingOrderItemDO.getFreight() != null && StringUtils.isNotBlank(shoppingOrderItemDO.getFreight())) {
            ShoppingOrderItemDOExample example = new ShoppingOrderItemDOExample();
            example.createCriteria().andShoppingOrderIdEqualTo(shoppingOrderItemDO.getShoppingOrderId())
            .andIdNotEqualTo(shoppingOrderItemDO.getId()).andOrderStatusEqualTo(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
            List<ShoppingOrderItemDO> shoppingOrderItemDOList = shoppingOrderItemDOMapper.selectByExample(example);
            List<CalculateFreightDTO> calculateFreights = new ArrayList<>();
            for (ShoppingOrderItemDO item : shoppingOrderItemDOList) {
                CalculateFreightDTO calculateFreightDTO = new CalculateFreightDTO();
                calculateFreightDTO.setFreight(JSONObject.parseObject(item.getFreight(), FreightDTO.class));
                calculateFreightDTO.setQuantity(item.getQuantity());
                calculateFreights.add(calculateFreightDTO);
            }
            // 重新计算运费
            BigDecimal freightPrice = CalculateFreightDTO.calculateFreight(calculateFreights);
            rtn.setFreightPrice(shoppingOrderDO.getFreightPrice().subtract(freightPrice));
            // 积分抵扣运费金额
            BigDecimal freightPriceDeductionPointsAmount = new BigDecimal(0);
            /*
             * 按照优先退金额的原则, 只退超出部分的积分
             */
            BigDecimal currentRefundFreightPriceDeductionPointsAmount = new BigDecimal(0);
            BigDecimal currentRefundFreightPriceDeductionPoints = new BigDecimal(0);
            if (shoppingOrderDO.getExchangeRate() != null) {
                freightPriceDeductionPointsAmount = shoppingOrderDO.getFreightPriceDeductionPoints().divide(shoppingOrderDO.getExchangeRate());
                if (freightPriceDeductionPointsAmount.compareTo(freightPrice) >= 0) {
                    currentRefundFreightPriceDeductionPointsAmount = freightPriceDeductionPointsAmount.subtract(freightPrice);
                    currentRefundFreightPriceDeductionPoints = currentRefundFreightPriceDeductionPointsAmount.multiply(shoppingOrderDO.getExchangeRate());
                }
            }
            rtn.setAmount(rtn.getFreightPrice());
            rtn.setFreightPrice(rtn.getFreightPrice().subtract(currentRefundFreightPriceDeductionPointsAmount));
            rtn.setFreightPriceDeductionPoints(currentRefundFreightPriceDeductionPoints);
            rtn.setPoint(rtn.getPoint().add(currentRefundFreightPriceDeductionPoints));
            rtn.setActualAmount(rtn.getFreightPrice());
        }
        rtn.setActualAmount(rtn.getActualAmount().add(shoppingOrderItemDO.getSubtotal()));
        rtn.setAmount(rtn.getAmount().add(shoppingOrderItemDO.getSubtotal()).add(shoppingOrderItemDO.getDeductionPointsAmount()));
        rtn.setPoint(rtn.getPoint().add(shoppingOrderItemDO.getDeductionPoints()));
        return rtn;
    }
}
