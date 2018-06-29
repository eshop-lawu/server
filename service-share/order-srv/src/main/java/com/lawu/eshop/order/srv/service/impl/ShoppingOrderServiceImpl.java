package com.lawu.eshop.order.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.IllegalOperationException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.mq.dto.order.CompleteShoppingTaskNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderNoPaymentNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderOrdersTradingIncomeNoticeNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderRemindShipmentsNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderpaymentSuccessfulNotification;
import com.lawu.eshop.mq.dto.order.ShoppingRefundToBeConfirmedForRefundRemindNotification;
import com.lawu.eshop.mq.dto.order.reply.CreateOrderDeductionPointsReply;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderCreateOrderReply;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderPaymentStatusReply;
import com.lawu.eshop.mq.dto.property.ShoppingOrderPaymentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.constants.ReportFansRiseRateEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusToMemberEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusToMerchantEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.dto.ReportRiseRateDTO;
import com.lawu.eshop.order.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.order.param.ActivityProductBuyQueryParam;
import com.lawu.eshop.order.param.ReportDataParam;
import com.lawu.eshop.order.param.ShoppingOrderLogisticsInformationParam;
import com.lawu.eshop.order.param.ShoppingOrderReportDataParam;
import com.lawu.eshop.order.param.ShoppingOrderRequestRefundParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementItemParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementParam;
import com.lawu.eshop.order.param.ShoppingOrderUpdateInfomationParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMemberParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMerchantParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToOperatorParam;
import com.lawu.eshop.order.srv.bo.RefundInformationBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderIsNoOnGoingOrderBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderMoneyBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderNumberOfOrderStatusBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderNumberOfOrderStatusForMerchantBO;
import com.lawu.eshop.order.srv.constants.ExceptionMessageConstant;
import com.lawu.eshop.order.srv.constants.PropertyConstant;
import com.lawu.eshop.order.srv.constants.PropertyNameConstant;
import com.lawu.eshop.order.srv.converter.ReportConvert;
import com.lawu.eshop.order.srv.converter.ShoppingOrderConverter;
import com.lawu.eshop.order.srv.converter.ShoppingOrderExtendConverter;
import com.lawu.eshop.order.srv.domain.ShoppingCartDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDOExample;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDO;
import com.lawu.eshop.order.srv.domain.extend.NotShippedDO;
import com.lawu.eshop.order.srv.domain.extend.ReportFansSaleTransFormDO;
import com.lawu.eshop.order.srv.domain.extend.ReportRiseRateView;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderExtendDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderExtendDOExample;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderExtendDOExample.Criteria;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDOExample;
import com.lawu.eshop.order.srv.exception.CanNotFillInShippingLogisticsException;
import com.lawu.eshop.order.srv.exception.OrderCreationFailedException;
import com.lawu.eshop.order.srv.exception.OrderNotCanceledException;
import com.lawu.eshop.order.srv.exception.OrderNotDeleteException;
import com.lawu.eshop.order.srv.exception.OrderNotReceivedException;
import com.lawu.eshop.order.srv.exception.OrderNotRefundException;
import com.lawu.eshop.order.srv.exception.TheOrderIsBeingProcessedException;
import com.lawu.eshop.order.srv.mapper.ShoppingCartDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingOrderItemDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundDetailDOMapper;
import com.lawu.eshop.order.srv.mapper.ShoppingRefundProcessDOMapper;
import com.lawu.eshop.order.srv.mapper.extend.ShoppingOrderExtendDOMapper;
import com.lawu.eshop.order.srv.mapper.extend.ShoppingOrderItemExtendDOMapper;
import com.lawu.eshop.order.srv.service.PropertyService;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;
import com.lawu.eshop.order.srv.service.ShoppingRefundDetailService;
import com.lawu.eshop.synchronization.lock.impl.LockConstant;
import com.lawu.eshop.synchronization.lock.impl.LockConstant.LockModule;
import com.lawu.eshop.synchronization.lock.impl.LockService;
import com.lawu.framework.core.page.Page;
import com.lawu.mq.message.MessageProducerService;
import com.lawu.utils.DateUtil;

@Service
public class ShoppingOrderServiceImpl implements ShoppingOrderService {

	@Autowired
	private ShoppingCartDOMapper shoppingCartDOMapper;

	@Autowired
	private ShoppingOrderExtendDOMapper shoppingOrderDOExtendMapper;

	@Autowired
	private ShoppingOrderDOMapper shoppingOrderDOMapper;

	@Autowired
	private ShoppingOrderItemDOMapper shoppingOrderItemDOMapper;

	@Autowired
	private ShoppingOrderItemExtendDOMapper shoppingOrderItemExtendDOMapper;

	@Autowired
	private ShoppingRefundDetailDOMapper shoppingRefundDetailDOMapper;

	@Autowired
	private ShoppingRefundProcessDOMapper shoppingRefundProcessDOMapper;

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private MessageProducerService messageProducerService;
	
	@Autowired
	private ShoppingRefundDetailService shoppingRefundDetailService;
	
	@Autowired
	@Qualifier("shoppingOrderTradingSuccessTransactionMainServiceImpl")
	private TransactionMainService<Reply> shoppingOrderTradingSuccessTransactionMainServiceImpl;

	@Autowired
	@Qualifier("shoppingOrderCreateOrderTransactionMainServiceImpl")
	private TransactionMainService<ShoppingOrderCreateOrderReply> shoppingOrderCreateOrderTransactionMainServiceImpl;

	@Autowired
	@Qualifier("shoppingOrderCancelOrderTransactionMainServiceImpl")
	private TransactionMainService<Reply> shoppingOrderCancelOrderTransactionMainServiceImpl;

	@Autowired
	@Qualifier("shoppingOrderAutoCommentTransactionMainServiceImpl")
	private TransactionMainService<Reply> shoppingOrderAutoCommentTransactionMainServiceImpl;

	@Autowired
	@Qualifier("shoppingOrderCreateOrderFansTransactionMainServiceImpl")
	private TransactionMainService<Reply> shoppingOrderCreateOrderFansTransactionMainServiceImpl;

	@Autowired
	@Qualifier("shoppingOrderTradingSuccessIncreaseSalesTransactionMainServiceImpl")
	private TransactionMainService<Reply> shoppingOrderTradingSuccessIncreaseSalesTransactionMainServiceImpl;

	@Autowired
	@Qualifier("shoppingOrderPaymentsToMerchantTransactionMainServiceImpl")
	private TransactionMainService<Reply> shoppingOrderPaymentsToMerchantTransactionMainServiceImpl;
	
    @Autowired
    @Qualifier("createOrderDeductionPointsTransactionMainServiceImpl")
    private TransactionMainService<CreateOrderDeductionPointsReply> createOrderDeductionPointsTransactionMainServiceImpl;
    
    @Autowired
    @Qualifier("cancelOrderRefundPointsTransactionMainServiceImpl")
    private TransactionMainService<Reply> cancelOrderRefundPointsTransactionMainServiceImpl;

    @Autowired
    private LockService lockService;
	
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Long> save(List<ShoppingOrderSettlementParam> params) throws WrongOperationException {
        List<Long> rtn = new ArrayList<>();
        // 插入订单
        for (ShoppingOrderSettlementParam shoppingOrderSettlementParam : params) {
            ActivityProductBuyQueryParam activityProductBuyQueryParam = new ActivityProductBuyQueryParam();
            activityProductBuyQueryParam.setActivityProductId(shoppingOrderSettlementParam.getActivityProductId());
            activityProductBuyQueryParam.setMemberId(shoppingOrderSettlementParam.getMemberId());
            if ( activityProductBuyQueryParam.getActivityProductId() != null) {
                boolean isLock = lockService.tryLock(0, 5000, LockModule.LOCK_ORDER_SRV, LockConstant.CREATE_ACTIVITY_ORDER.concat(activityProductBuyQueryParam.getMemberId() + "_" + activityProductBuyQueryParam.getActivityProductId()));
                if (!isLock) {
                    throw new WrongOperationException(ExceptionMessageConstant.DUPLICATE_SECKILL_ACTIVITY_ORDER);
                }
                Long count = isBuy(activityProductBuyQueryParam);
                if (count != null && count > 0) {
                    throw new WrongOperationException(ExceptionMessageConstant.DUPLICATE_SECKILL_ACTIVITY_ORDER);
                }
            }
            ShoppingOrderDO shoppingOrderDO = new ShoppingOrderDO();
            shoppingOrderDO.setCommodityTotalPrice(shoppingOrderSettlementParam.getCommodityTotalPrice());
            shoppingOrderDO.setConsigneeAddress(shoppingOrderSettlementParam.getConsigneeAddress());
            shoppingOrderDO.setConsigneeMobile(shoppingOrderSettlementParam.getConsigneeMobile());
            shoppingOrderDO.setConsigneeName(shoppingOrderSettlementParam.getConsigneeName());
            shoppingOrderDO.setFreightPrice(shoppingOrderSettlementParam.getFreightPrice());
            shoppingOrderDO.setFreightPriceDeductionPoints(shoppingOrderSettlementParam.getFreightPriceDeductionPoints());
            shoppingOrderDO.setIsFans(shoppingOrderSettlementParam.getIsFans());
            shoppingOrderDO.setIsNoReasonReturn(shoppingOrderSettlementParam.getIsNoReasonReturn());
            shoppingOrderDO.setMemberId(shoppingOrderSettlementParam.getMemberId());
            shoppingOrderDO.setMemberNum(shoppingOrderSettlementParam.getMemberNum());
            shoppingOrderDO.setMemberNickname(shoppingOrderSettlementParam.getMemberNickname());
            shoppingOrderDO.setMerchantId(shoppingOrderSettlementParam.getMerchantId());
            shoppingOrderDO.setMerchantName(shoppingOrderSettlementParam.getMerchantName());
            shoppingOrderDO.setMerchantNum(shoppingOrderSettlementParam.getMerchantNum());
            shoppingOrderDO.setMerchantStoreId(shoppingOrderSettlementParam.getMerchantStoreId());
            shoppingOrderDO.setMerchantStoreRegionPath(shoppingOrderSettlementParam.getMerchantStoreRegionPath());
            shoppingOrderDO.setMessage(shoppingOrderSettlementParam.getMessage());
            shoppingOrderDO.setActivityId(shoppingOrderSettlementParam.getActivityId());
            shoppingOrderDO.setActivityProductId(shoppingOrderSettlementParam.getActivityProductId());
            List<Long> shoppingCartIdList = new ArrayList<>();
            for (ShoppingOrderSettlementItemParam item : shoppingOrderSettlementParam.getItems()) {
                shoppingCartIdList.add(item.getShoppingCartId());
            }
            // 把购物车id用逗号分隔保存在购物订单表中，用于删除购物车记录
            shoppingOrderDO.setShoppingCartIdsStr(StringUtils.join(shoppingCartIdList, ","));
            // 设置自动收货为false
            shoppingOrderDO.setIsAutomaticReceipt(false);
            // 设置为待处理状态
            shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING.getValue());
            // 记录状态设置为正常
            shoppingOrderDO.setStatus(StatusEnum.VALID.getValue());
            // 设置提成状态，和支付给商家的实际金额
            shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.NOT_COUNTED.getValue());
            shoppingOrderDO.setActualAmount(shoppingOrderSettlementParam.getCommodityTotalPrice().add(shoppingOrderDO.getFreightPrice()));
            // 设置抵扣积分和抵扣积分金额
            shoppingOrderDO.setDeductionPoints(shoppingOrderSettlementParam.getDeductionPoints());
            shoppingOrderDO.setDeductionPointsAmount(shoppingOrderSettlementParam.getDeductionPointsAmount());
            shoppingOrderDO.setExchangeRate(shoppingOrderSettlementParam.getExchangeRate());
            shoppingOrderDO.setOrderTotalPrice(shoppingOrderSettlementParam.getOrderTotalPrice());
            shoppingOrderDO.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.ORDER));
            shoppingOrderDO.setGmtCreate(new Date());
            shoppingOrderDO.setGmtModified(new Date());
            shoppingOrderDOMapper.insertSelective(shoppingOrderDO);
            Long id = shoppingOrderDO.getId();
            // 插入订单项
            for (ShoppingOrderSettlementItemParam item : shoppingOrderSettlementParam.getItems()) {
                ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
                shoppingOrderItemDO.setIsAllowRefund(item.getIsAllowRefund());
                shoppingOrderItemDO.setProductFeatureImage(item.getProductFeatureImage());
                shoppingOrderItemDO.setProductId(item.getProductId());
                shoppingOrderItemDO.setProductModelId(item.getProductModelId());
                shoppingOrderItemDO.setProductModelName(item.getProductModelName());
                shoppingOrderItemDO.setProductName(item.getProductName());
                shoppingOrderItemDO.setQuantity(item.getQuantity());
                shoppingOrderItemDO.setRegularPrice(item.getRegularPrice());
                shoppingOrderItemDO.setSalesPrice(item.getSalesPrice());
                shoppingOrderItemDO.setActivityProductModelId(item.getActivityProductModelId());
                // 设置订单id
                shoppingOrderItemDO.setShoppingOrderId(id);
                // 设置为待处理
                shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.PENDING.getValue());
                // 设置为未评价
                shoppingOrderItemDO.setIsEvaluation(false);
                // 设置抵扣积分以及抵扣积分金额
                shoppingOrderItemDO.setDeductionPoints(item.getPoint());
                shoppingOrderItemDO.setDeductionPointsAmount(item.getDeductionPointsAmount());
                shoppingOrderItemDO.setFreight(item.getFreight() != null ? JSONObject.toJSONString(item.getFreight()) : null);
                shoppingOrderItemDO.setSubtotal(item.getSubtotal());
                shoppingOrderItemDO.setGmtCreate(new Date());
                shoppingOrderItemDO.setGmtModified(new Date());
                shoppingOrderItemDOMapper.insertSelective(shoppingOrderItemDO);
            }
            // 考虑订单是否保存失败处理
            if (id != null && id > 0) {
                // 把订单id放入list返回
                rtn.add(id);
                // 判断用户是否是商家粉丝
                if (!shoppingOrderDO.getIsFans()) {
                    // 事务补偿(用户成为商家粉丝)
                    shoppingOrderCreateOrderFansTransactionMainServiceImpl.sendNotice(id);
                }
                // 事务补偿(减掉库存)
                shoppingOrderCreateOrderTransactionMainServiceImpl.sendNotice(id);
            }
        }
        return rtn;
    }

	@Override
	public Page<ShoppingOrderExtendBO> selectPageByMemberId(Long memberId, ShoppingOrderQueryForeignToMemberParam param) {
		ShoppingOrderExtendDOExample shoppingOrderExtendDOExample = new ShoppingOrderExtendDOExample();
		shoppingOrderExtendDOExample.setIncludeViewShoppingOrderItem(false);
		shoppingOrderExtendDOExample.setIncludeShoppingOrderItem(true);

		// 组装Criteria
		Criteria baseCriteria = shoppingOrderExtendDOExample.createCriteria();

		// 用户如果删除则不显示
		baseCriteria.andStatusEqualTo(StatusEnum.VALID.getValue());

		if (memberId != null) {
			baseCriteria.andMemberIdEqualTo(memberId);
		}

		if (param.getOrderStatus() != null) {
			baseCriteria.andOrderStatusEqualTo(param.getOrderStatus().getValue());

			// 查找待评价的订单
			if (param.getOrderStatus().equals(ShoppingOrderStatusToMemberEnum.BE_EVALUATED)) {
				// 只有订单状态为交易成功并且未评价才显示
				baseCriteria.andSOIOrderStatusEqualTo(param.getOrderStatus().getValue());
				baseCriteria.andSOIIsEvaluationEqualTo(false);
			}
		}

		if (!StringUtils.isEmpty(param.getKeyword())) {
			shoppingOrderExtendDOExample.clear();

			Criteria orderNumCriteria = shoppingOrderExtendDOExample.or();
			orderNumCriteria.andOrderNumEqualTo(param.getKeyword());
			orderNumCriteria.getAllCriteria().addAll(baseCriteria.getAllCriteria());

			Criteria paroductCriteria = shoppingOrderExtendDOExample.or();
			paroductCriteria.andSOIProductNameLike("%" + param.getKeyword() + "%");
			paroductCriteria.getAllCriteria().addAll(baseCriteria.getAllCriteria());
		}

		// 过滤重复记录
		shoppingOrderExtendDOExample.setDistinct(true);

		// 查询总记录数
		Long count = shoppingOrderDOExtendMapper.countByExample(shoppingOrderExtendDOExample);

		Page<ShoppingOrderExtendBO> shoppingOrderItemBOPage = new Page<>();
		shoppingOrderItemBOPage.setTotalCount(count.intValue());
		shoppingOrderItemBOPage.setCurrentPage(param.getCurrentPage());

		// 如果总记录为0，不再执行后续操作直接返回
		if (count <= 0 || param.getOffset() >= count) {
			return shoppingOrderItemBOPage;
		}
		
		String orderByClause = "";
		// 如果是待收货按照付款时间正序排序
		if (ShoppingOrderStatusToMemberEnum.BE_SHIPPED.equals(param.getOrderStatus())) {
			orderByClause += "so.gmt_payment asc,";
		}
		// 默认创建时间排序
		orderByClause += "so.gmt_create desc";
		
		shoppingOrderExtendDOExample.setOrderByClause(orderByClause);
		
		// 分页参数
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

		// 如果参数中的keyword有值，查询结果的订单项会缺少，所有先找出所有购物订单id再通过去查找购物订单以及级联的购物订单项
		List<Long> idList = shoppingOrderDOExtendMapper.selectIdByExample(shoppingOrderExtendDOExample, rowBounds);

		shoppingOrderExtendDOExample = new ShoppingOrderExtendDOExample();
		shoppingOrderExtendDOExample.setIncludeViewShoppingOrderItem(true);
		shoppingOrderExtendDOExample.setIncludeShoppingOrderItem(true);
		shoppingOrderExtendDOExample.createCriteria().andIdIn(idList);

		shoppingOrderExtendDOExample.setOrderByClause(orderByClause);

		List<ShoppingOrderExtendDO> shoppingOrderExtendDOList = shoppingOrderDOExtendMapper.selectByExample(shoppingOrderExtendDOExample);

		shoppingOrderItemBOPage.setRecords(ShoppingOrderExtendConverter.convertShoppingOrderExtendBO(shoppingOrderExtendDOList));
		shoppingOrderExtendDOList = null;

		return shoppingOrderItemBOPage;
	}

	@Override
	public Page<ShoppingOrderExtendBO> selectPageByMerchantId(Long merchantId, ShoppingOrderQueryForeignToMerchantParam param) {
		ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();

		// 组装Criteria
		ShoppingOrderDOExample.Criteria baseCriteria = shoppingOrderDOExample.createCriteria();

		if (merchantId != null) {
			baseCriteria.andMerchantIdEqualTo(merchantId);
		}

		if (param.getOrderStatus() != null) {
			// 参数的订单状态是一个数组类型，查询多个参数状态
			baseCriteria.andOrderStatusIn(Arrays.asList(param.getOrderStatus().getValue()));
		}

        if (!StringUtils.isEmpty(param.getKeyword())) {
            shoppingOrderDOExample.clear();

            ShoppingOrderDOExample.Criteria orderNumCriteria = shoppingOrderDOExample.or();
            orderNumCriteria.andOrderNumLike("%" + param.getKeyword() + "%");
            orderNumCriteria.getAllCriteria().addAll(baseCriteria.getAllCriteria());

            ShoppingOrderDOExample.Criteria paroductCriteria = shoppingOrderDOExample.or();
            paroductCriteria.andConsigneeNameLike("%" + param.getKeyword() + "%");
            paroductCriteria.getAllCriteria().addAll(baseCriteria.getAllCriteria());

            ShoppingOrderDOExample.Criteria memberUserNumCriteria = shoppingOrderDOExample.or();
            memberUserNumCriteria.andMemberNumLike("%" + param.getKeyword() + "%");
            memberUserNumCriteria.getAllCriteria().addAll(baseCriteria.getAllCriteria());
        }

		// 查询总记录数
		Long count = shoppingOrderDOMapper.countByExample(shoppingOrderDOExample);

		Page<ShoppingOrderExtendBO> shoppingOrderItemBOPage = new Page<>();
		shoppingOrderItemBOPage.setTotalCount(count.intValue());
		shoppingOrderItemBOPage.setCurrentPage(param.getCurrentPage());
		// 初始一条空记录
		shoppingOrderItemBOPage.setRecords(new ArrayList<>());

		/*
		 * 如果count为0，或者offset大于count 不再执行后续操作直接返回
		 */
		if (count <= 0 || param.getOffset() >= count) {
			return shoppingOrderItemBOPage;
		}

		// 分页参数
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		
		String orderByClause = "";
		if (ShoppingOrderStatusToMerchantEnum.BE_SHIPPED.equals(param.getOrderStatus())) {
			orderByClause += "gmt_payment asc,";
		}
		// 默认创建时间排序
		orderByClause += "gmt_create desc";
		
		shoppingOrderDOExample.setOrderByClause(orderByClause);
		// 如果参数中的keyword有值，查询结果的订单项会缺少，所有先找出所有购物订单id再通过去查找购物订单以及级联的购物订单项
		List<ShoppingOrderDO> shoppingOrderDOList = shoppingOrderDOMapper.selectByExampleWithRowbounds(shoppingOrderDOExample, rowBounds);
		List<Long> idList = new ArrayList<>();
		for (ShoppingOrderDO item : shoppingOrderDOList) {
		    idList.add(item.getId());
		}
		ShoppingOrderExtendDOExample shoppingOrderExtendDOExample = new ShoppingOrderExtendDOExample();
		shoppingOrderExtendDOExample.setIncludeViewShoppingOrderItem(true);
		shoppingOrderExtendDOExample.setIncludeShoppingOrderItem(true);
		shoppingOrderExtendDOExample.createCriteria().andIdIn(idList);
        String extendOrderByClause = "";
        if (ShoppingOrderStatusToMerchantEnum.BE_SHIPPED.equals(param.getOrderStatus())) {
            extendOrderByClause += "so.gmt_payment asc,";
        }
        // 默认创建时间排序
        extendOrderByClause += "so.gmt_create desc";
		shoppingOrderExtendDOExample.setOrderByClause(extendOrderByClause);
		List<ShoppingOrderExtendDO> shoppingOrderExtendDOList = shoppingOrderDOExtendMapper.selectByExample(shoppingOrderExtendDOExample);

		shoppingOrderItemBOPage.setRecords(ShoppingOrderExtendConverter.convertShoppingOrderExtendBO(shoppingOrderExtendDOList));

		return shoppingOrderItemBOPage;
	}

	/**
	 * 查询订单详情
	 * 
	 * @param id
	 *            购物订单id
	 * @param memberId
	 *            会员id
	 * @param merchantId
	 *            商家id
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月10日
	 */
	@Override
	public ShoppingOrderExtendBO get(Long id, Long memberId, Long merchantId) {
		ShoppingOrderExtendDOExample shoppingOrderExtendDOExample = new ShoppingOrderExtendDOExample();
		shoppingOrderExtendDOExample.setIncludeShoppingOrderItem(true);
		shoppingOrderExtendDOExample.setIncludeViewShoppingOrderItem(true);
		ShoppingOrderExtendDO shoppingOrderExtendDO = shoppingOrderDOExtendMapper.selectByPrimaryKey(id);
		// 如果是会员端请求,需要判断订单的数据状态是否是正常
		boolean isNotFind = shoppingOrderExtendDO == null || shoppingOrderExtendDO.getItems() == null || shoppingOrderExtendDO.getItems().isEmpty() || (memberId != null && shoppingOrderExtendDO.getStatus().equals(StatusEnum.INVALID.getValue()));
		if (isNotFind) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
		}
		if (memberId != null && !shoppingOrderExtendDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		if (merchantId != null && !shoppingOrderExtendDO.getMerchantId().equals(merchantId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		return ShoppingOrderExtendConverter.convertShoppingOrderExtendDetailBO(shoppingOrderExtendDO);
	}

	/**
	 * 查询订单详情
	 * 
	 * @param id
	 *            购物订单id
	 * @return
	 */
	@Override
	public ShoppingOrderExtendBO get(Long id) {
		return get(id, null, null);
	}

	/**
	 * 根据id获取购物订单
	 * 
	 * @param id
	 *            购物订单id
	 * @return
	 */
	@Override
	public ShoppingOrderBO getShoppingOrder(Long id) throws DataNotExistException, IllegalOperationException {
		return getShoppingOrder(id, null, null);
	}

	/**
	 * 根据id获取购物订单
	 * 
	 * @param id
	 *            购物订单id
	 * @return
	 */
	@Override
	public ShoppingOrderBO getShoppingOrder(Long id, Long memberId, Long merchantId) throws DataNotExistException, IllegalOperationException {
	    ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(id);
		if (shoppingOrderDO == null || shoppingOrderDO.getStatus().equals(StatusEnum.INVALID.getValue())) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
		}
		if (memberId != null && !shoppingOrderDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		if (merchantId != null && !shoppingOrderDO.getMerchantId().equals(merchantId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		return ShoppingOrderConverter.convertShoppingOrderBO(shoppingOrderDO);
	}

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelOrder(Long memberId, Long id)
            throws DataNotExistException, IllegalOperationException, OrderNotCanceledException {
        ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(id);
        if (shoppingOrderDO == null || shoppingOrderDO.getStatus().equals(StatusEnum.INVALID.getValue())) {
            throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
        }
        if (memberId != null && !shoppingOrderDO.getMemberId().equals(memberId)) {
            throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
        }
        // 被取消的订单必须要是待支付的状态
        if (!shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue())) {
            throw new OrderNotCanceledException(ExceptionMessageConstant.ORDER_IS_NOT_PENDING_PAYMENT_STATUS);
        }
        // 更新购物订单的状态
        ShoppingOrderDO shoppingOrderUpdateDO = new ShoppingOrderDO();
        shoppingOrderUpdateDO.setId(id);
        shoppingOrderUpdateDO.setGmtModified(new Date());
        // 更新订单状态
        shoppingOrderUpdateDO.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
        // 更新成交时间
        shoppingOrderUpdateDO.setGmtTransaction(new Date());
        shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderUpdateDO);
        // 更新购物订单项状态
        ShoppingOrderItemDOExample shoppingOrderItemDOExample = new ShoppingOrderItemDOExample();
        com.lawu.eshop.order.srv.domain.ShoppingOrderItemDOExample.Criteria shoppingOrderItemDOExampleCriteria = shoppingOrderItemDOExample
                .createCriteria();
        shoppingOrderItemDOExampleCriteria.andShoppingOrderIdEqualTo(id);
        ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
        shoppingOrderItemDO.setGmtModified(new Date());
        shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
        shoppingOrderItemDOMapper.updateByExampleSelective(shoppingOrderItemDO, shoppingOrderItemDOExample);
        // 释放库存
        shoppingOrderCancelOrderTransactionMainServiceImpl.sendNotice(id);
        // 如果该订单使用了积分
        if (shoppingOrderDO.getDeductionPoints() != null
                && shoppingOrderDO.getDeductionPoints().doubleValue() > 0D) {
            // 退还积分
            cancelOrderRefundPointsTransactionMainServiceImpl.sendNotice(id);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteOrder(Long memberId, Long id) {
        ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(id);
        if (shoppingOrderDO == null || shoppingOrderDO.getStatus().equals(StatusEnum.INVALID.getValue())) {
            throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
        }
        if (!shoppingOrderDO.getMemberId().equals(memberId)) {
            throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
        }
        // 订单的当前状态必须已结束状态的订单
        if (!shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue())
                && !shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue())) {
            throw new OrderNotDeleteException(ExceptionMessageConstant.ORDER_IS_NOT_OVER);
        }
        // 如果订单的状态是交易成功，检查订单项的订单状态是否是否是完成状态s
        if (shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue())
                && !shoppingOrderDO.getIsDone()) {
            throw new OrderNotDeleteException(ExceptionMessageConstant.ORDER_HAS_NOT_BEEN_COMPLETED);
        }
        // 更新购物订单的状态
        shoppingOrderDO = new ShoppingOrderDO();
        shoppingOrderDO.setId(id);
        shoppingOrderDO.setGmtModified(new Date());
        // 更改数据状态为删除
        shoppingOrderDO.setStatus(StatusEnum.INVALID.getValue());
        shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderDO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void paymentSuccessful(ShoppingOrderPaymentNotification notification) {
        if (notification == null) {
            return;
        }
        boolean isLock = lockService.tryLock(0, 5000, LockModule.LOCK_ORDER_SRV,
                LockConstant.ORDER_PAYMENT_SUCCESSFUL.concat(notification.getShoppingOrderIds()));
        if (!isLock) {
            throw new WrongOperationException(ExceptionMessageConstant.REPEAT_PAYMENT_ORDER);
        }
        String[] shoppingOrderIds = StringUtils.split(notification.getShoppingOrderIds(), ",");
        BigDecimal orderAmount = BigDecimal.ZERO;
        String memberNum = null;
        for (String shoppingOrderId : shoppingOrderIds) {
            Long id = Long.valueOf(shoppingOrderId);
            ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(id);
            // 如果回调时，订单状态不是待支付，对于订单不做任何操作
            if (!ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue().equals(shoppingOrderDO.getOrderStatus())) {
                return;
            }
            memberNum = shoppingOrderDO.getMemberNum();
            orderAmount = orderAmount.add(shoppingOrderDO.getCommodityTotalPrice())
                    .add(shoppingOrderDO.getFreightPrice());
            // 更新购物订单的状态
            shoppingOrderDO.setGmtModified(new Date());
            // 更改订单状态为待发货
            shoppingOrderDO.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
            // 更新付款时间
            shoppingOrderDO.setGmtPayment(new Date());
            // 更新付款方式以及第三方交易号
            shoppingOrderDO.setPaymentMethod(notification.getPaymentMethod().getVal());
            shoppingOrderDO.setThirdNumber(notification.getThirdNumber());
            shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderDO);

            // 更新购物订单项状态
            ShoppingOrderItemDOExample shoppingOrderItemDOExample = new ShoppingOrderItemDOExample();
            com.lawu.eshop.order.srv.domain.ShoppingOrderItemDOExample.Criteria shoppingOrderItemDOExampleCriteria = shoppingOrderItemDOExample
                    .createCriteria();
            shoppingOrderItemDOExampleCriteria.andShoppingOrderIdEqualTo(id);

            List<ShoppingOrderItemDO> shoppingOrderItemDOList = shoppingOrderItemDOMapper
                    .selectByExample(shoppingOrderItemDOExample);

            ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
            shoppingOrderItemDO.setGmtModified(new Date());
            shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
            shoppingOrderItemDOMapper.updateByExampleSelective(shoppingOrderItemDO, shoppingOrderItemDOExample);

            /*
             * 买家支付成功发送 推送消息给商家提醒买家新增了一个订单 发送推送消息提醒买家支付成功
             */
            // 组装要发送的消息
            ShoppingOrderpaymentSuccessfulNotification shoppingOrderpaymentSuccessfulNotification = new ShoppingOrderpaymentSuccessfulNotification();
            shoppingOrderpaymentSuccessfulNotification.setMerchantNum(shoppingOrderDO.getMerchantNum());
            shoppingOrderpaymentSuccessfulNotification.setMemberNum(shoppingOrderDO.getMemberNum());
            shoppingOrderpaymentSuccessfulNotification.setOrderNum(shoppingOrderDO.getOrderNum());
            StringBuilder stringBuilder = new StringBuilder();
            for (ShoppingOrderItemDO item : shoppingOrderItemDOList) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(item.getProductName());
            }
            shoppingOrderpaymentSuccessfulNotification.setProductName(stringBuilder.toString());
            shoppingOrderpaymentSuccessfulNotification.setId(shoppingOrderDO.getId());
            shoppingOrderpaymentSuccessfulNotification
                    .setImgUrl(shoppingOrderItemDOList.get(0).getProductFeatureImage());

            /*
             *  发送消付款成功的消息给用户
             *  发送有新订单消息给商家
             */
            messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_PAYMENT_SUCCESSFUL_PUSH,
                    shoppingOrderpaymentSuccessfulNotification);
        }
        
        CompleteShoppingTaskNotification completeShoppingTaskNotification = new CompleteShoppingTaskNotification();
        completeShoppingTaskNotification.setMemberNum(memberNum);
        completeShoppingTaskNotification.setOrderAmount(orderAmount);
        /*
         *  完成购物任务
         *  1.增加动力值
         */
        messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_COMPLETE_SHOPPING_TASK,
                completeShoppingTaskNotification);
    }

	/**
	 * 确认收货之后 修改购物订单以及订单项状态为交易成功
	 * 
	 * @param id
	 *            购物订单id
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void tradingSuccess(Long id, Long memberId, boolean isAutomaticReceipt) {
	    ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(id);
		if (shoppingOrderDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
		}
		if (memberId != null && !shoppingOrderDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		// 订单的当前状态必须是待收货
		if (!shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue())) {
			throw new OrderNotReceivedException(ExceptionMessageConstant.THE_ORDER_IS_NOT_IN_RECEIPT);
		}
		// 更新购物订单的状态
		ShoppingOrderDO shoppingOrderUpdateDO = new ShoppingOrderDO();
		shoppingOrderUpdateDO.setId(id);
		shoppingOrderUpdateDO.setGmtModified(new Date());
		shoppingOrderUpdateDO.setIsAutomaticReceipt(isAutomaticReceipt);
		// 如果是自动收货，设置订单的状态为完成
		if (isAutomaticReceipt) {
			shoppingOrderUpdateDO.setIsDone(true);
			shoppingOrderUpdateDO.setGmtDone(new Date());
		}
		// 更改订单状态为交易成功
		shoppingOrderUpdateDO.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
		// 更新成交时间
		shoppingOrderUpdateDO.setGmtTransaction(new Date());
		shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderUpdateDO);
		// 更新购物订单项状态
		ShoppingOrderItemDOExample shoppingOrderItemDOExample = new ShoppingOrderItemDOExample();
		ShoppingOrderItemDOExample.Criteria shoppingOrderItemDOExampleCriteria = shoppingOrderItemDOExample.createCriteria();
		shoppingOrderItemDOExampleCriteria.andShoppingOrderIdEqualTo(id);
		List<ShoppingOrderItemDO> shoppingOrderItemDOList = shoppingOrderItemDOMapper.selectByExample(shoppingOrderItemDOExample);
		// 如果用户确认收货，订单当中还有退款的商品，关闭退款申请
		for (ShoppingOrderItemDO item : shoppingOrderItemDOList) {
			// 如果订单项的状态是退款中的状态
			if (item.getOrderStatus().equals(ShoppingOrderStatusEnum.REFUNDING.getValue())) {
				item.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
				// 清空退款状态
				item.setRefundStatus(null);
				item.setGmtModified(new Date());
				shoppingOrderItemDOMapper.updateByPrimaryKey(item);
				// 撤销退款申请
				ShoppingRefundDetailDOExample shoppingRefundDetailDOExample = new ShoppingRefundDetailDOExample();
				ShoppingRefundDetailDOExample.Criteria shoppingRefundDetailDOExampleCriteria = shoppingRefundDetailDOExample.createCriteria();
				shoppingRefundDetailDOExampleCriteria.andShoppingOrderItemIdEqualTo(item.getId());
				shoppingRefundDetailDOExampleCriteria.andStatusEqualTo(StatusEnum.VALID.getValue());
				ShoppingRefundDetailDO shoppingRefundDetailDO = new ShoppingRefundDetailDO();
				shoppingRefundDetailDO.setStatus(StatusEnum.INVALID.getValue());
				shoppingRefundDetailDOMapper.updateByExampleSelective(shoppingRefundDetailDO, shoppingRefundDetailDOExample);
			} else if (!item.getOrderStatus().equals(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue())) {
				// 如果订单项状态是交易关闭，不予处理
				// 更改订单项状态为交易成功
				item.setOrderStatus(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
				item.setGmtModified(new Date());
				shoppingOrderItemDOMapper.updateByPrimaryKey(item);
			}
		}
		// 发送MQ消息通知产品模块增加销量
		shoppingOrderTradingSuccessIncreaseSalesTransactionMainServiceImpl.sendNotice(id);
		/*
		 * 发送MQ消息通知资产模块 如果是手动收货保存资金冻结表 如果是自动收货直接付款给商家
		 */
		shoppingOrderTradingSuccessTransactionMainServiceImpl.sendNotice(id);
		if (shoppingOrderDO.getIsDone()) {
			// 提示商家新增一笔订单交易收入
			ordersTradingIncomeNotice(shoppingOrderUpdateDO.getId(), shoppingOrderUpdateDO.getActualAmount(), shoppingOrderUpdateDO.getMerchantNum());
		}
	}

	/**
	 * 确认收货之后 修改购物订单以及订单项状态为交易成功
	 * 
	 * @param id
	 *            购物订单id
	 * @param memberId
	 *            会员id
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void tradingSuccess(Long id, Long memberId) {
		tradingSuccess(id, memberId, false);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void tradingSuccess(Long id, boolean isAutomaticReceipt) {
		tradingSuccess(id, null, isAutomaticReceipt);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void requestRefund(Long shoppingOrderItemId, Long memberId, ShoppingOrderRequestRefundParam param) {
		ShoppingOrderItemDO shoppingOrderItemDO = shoppingOrderItemDOMapper.selectByPrimaryKey(shoppingOrderItemId);
		if (shoppingOrderItemDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
		}
		// 检查订单是否已经是退款
		if (shoppingOrderItemDO.getRefundStatus() != null) {
			throw new OrderNotRefundException(ExceptionMessageConstant.IN_THE_ORDER_HAS_BEEN_REFUNDED);

		}
		ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderItemDO.getShoppingOrderId());
		if (shoppingOrderDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
		}
		if (!shoppingOrderDO.getMemberId().equals(memberId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		// 只有待发货、待收货、交易成功才能被允许退款
		if (!shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.BE_SHIPPED.getValue()) && !shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue()) && !shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue())) {
			throw new OrderNotRefundException(ExceptionMessageConstant.THE_CURRENT_ORDER_STATUS_DOES_NOT_MATCH);
		}
		// 商家还没有发货，无论商品是否允许支持退货都能允许退款
		if (!shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.BE_SHIPPED.getValue()) && !shoppingOrderItemDO.getIsAllowRefund()) {
			throw new OrderNotRefundException(ExceptionMessageConstant.PRODUCT_DOES_NOT_SUPPORT_REFUNDS);
		}
		// 订单是已完成状态不允许退款
		if (shoppingOrderDO.getIsDone()) {
			throw new OrderNotRefundException(ExceptionMessageConstant.THE_ORDER_EXCEEDS_THE_REFUND_TIME);
		}
		ShoppingOrderDO shoppingOrderUpdateDO = new ShoppingOrderDO();
		shoppingOrderUpdateDO.setId(shoppingOrderDO.getId());
		shoppingOrderUpdateDO.setIsRefundItems(true);
		// 更新购物订单项状态
		ShoppingOrderItemDO shoppingOrderItemUpdateDO = new ShoppingOrderItemDO();
		shoppingOrderItemUpdateDO.setId(shoppingOrderItemDO.getId());
		shoppingOrderItemUpdateDO.setGmtModified(new Date());
		shoppingOrderItemUpdateDO.setOrderStatus(ShoppingOrderStatusEnum.REFUNDING.getValue());
		// 根据订单状态是否需要退货
		ShoppingRefundTypeEnum shoppingRefundTypeEnum = null;
		// 根据订单状态是否需要退货
		boolean isAllowRejection = true;
		if (shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.BE_SHIPPED.getValue())) {
			shoppingRefundTypeEnum = ShoppingRefundTypeEnum.REFUND;
			isAllowRejection = false;
		} else {
		    isAllowRejection = true;
			// 判断当前订单是否需要物流
			if (shoppingOrderDO.getIsNeedsLogistics()) {
				shoppingRefundTypeEnum = ShoppingRefundTypeEnum.RETURN_REFUND;
			} else {
				shoppingRefundTypeEnum = ShoppingRefundTypeEnum.REFUND;
			}
		}
		RefundInformationBO refundInformationBO = shoppingRefundDetailService.getRefundInformation(shoppingOrderItemId, memberId);
		shoppingOrderUpdateDO.setFreightPrice(shoppingOrderDO.getFreightPrice().subtract(refundInformationBO.getFreightPrice()));
		shoppingOrderUpdateDO.setFreightPriceDeductionPoints(shoppingOrderDO.getFreightPriceDeductionPoints().subtract(refundInformationBO.getFreightPriceDeductionPoints()));
		shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderUpdateDO);
		/*
		 * 如果卖家支持七天无理由退货，跳过商家确认这个阶段 后台判断的类型是否跟用户选择的一致
		 */
		if (shoppingOrderDO.getIsNoReasonReturn() && shoppingRefundTypeEnum.equals(param.getType())) {
			// 订单是否需要物流
			if (ShoppingRefundTypeEnum.REFUND.equals(shoppingRefundTypeEnum)) {
			    if (!isAllowRejection) {
			        shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.TO_BE_REFUNDED.getValue());
			    } else {
			        shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
			    }
			} else if (ShoppingRefundTypeEnum.RETURN_REFUND.equals(shoppingRefundTypeEnum)) {
				shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.FILL_RETURN_ADDRESS.getValue());
			}
		} else {
			shoppingOrderItemUpdateDO.setRefundStatus(RefundStatusEnum.TO_BE_CONFIRMED.getValue());
		}
		shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemUpdateDO);
		
		ShoppingRefundDetailDO shoppingRefundDetailInsertDO = new ShoppingRefundDetailDO();
		// 保存退货详情记录
		shoppingRefundDetailInsertDO.setShoppingOrderItemId(shoppingOrderItemDO.getId());
		/*
		 *  真实退款金额为小计金额+(运费金额-运费抵扣积分金额)
		 *  退款金额为小计金额+运费金额+积分抵扣金额
		 *  退款积分为商品抵扣积分+运费抵扣积分
		 */
		shoppingRefundDetailInsertDO.setStatus(StatusEnum.VALID.getValue());
		shoppingRefundDetailInsertDO.setType(param.getType().getValue());
		shoppingRefundDetailInsertDO.setActualAmount(refundInformationBO.getActualAmount());
		shoppingRefundDetailInsertDO.setAmount(refundInformationBO.getAmount());
		shoppingRefundDetailInsertDO.setPoint(refundInformationBO.getPoint());
		shoppingRefundDetailInsertDO.setFreightPrice(refundInformationBO.getFreightPrice());
		shoppingRefundDetailInsertDO.setFreightPriceDeductionPoints(refundInformationBO.getFreightPriceDeductionPoints());
		shoppingRefundDetailInsertDO.setReason(param.getReason());
		shoppingRefundDetailInsertDO.setDescription(param.getDescription());
		if (StringUtils.isNotBlank(param.getVoucherPicture())) {
			shoppingRefundDetailInsertDO.setVoucherPicture(param.getVoucherPicture());
		}
		shoppingRefundDetailInsertDO.setGmtCreate(new Date());
		shoppingRefundDetailInsertDO.setGmtModified(new Date());
		shoppingRefundDetailDOMapper.insertSelective(shoppingRefundDetailInsertDO);
		// 把当前的退款状态加入记录到退款流程
		ShoppingRefundProcessDO shoppingRefundProcessDO = new ShoppingRefundProcessDO();
		shoppingRefundProcessDO.setRefundStatus(shoppingOrderItemUpdateDO.getRefundStatus());
		shoppingRefundProcessDO.setGmtCreate(new Date());
		shoppingRefundProcessDO.setShoppingRefundDetailId(shoppingRefundDetailInsertDO.getId());
		shoppingRefundProcessDOMapper.insertSelective(shoppingRefundProcessDO);
		// 用户申请退款，提醒商家处理
		ShoppingRefundToBeConfirmedForRefundRemindNotification notification = new ShoppingRefundToBeConfirmedForRefundRemindNotification();
		notification.setShoppingOrderItemId(shoppingOrderItemDO.getId());
		notification.setMemberNum(shoppingOrderDO.getMemberNum());
		notification.setMerchantNum(shoppingOrderDO.getMerchantNum());
		notification.setMemberNickname(shoppingOrderDO.getMemberNickname());
		notification.setImgUrl(shoppingOrderItemDO.getProductFeatureImage());
		messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_TO_BE_CONFIRMED_FOR_REFUND_REMIND, notification);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void fillLogisticsInformation(Long id, Long merchantId, ShoppingOrderLogisticsInformationParam param) throws DataNotExistException, IllegalOperationException, CanNotFillInShippingLogisticsException {
	    ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(id);
		if (shoppingOrderDO == null) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
		}
		if (!shoppingOrderDO.getMerchantId().equals(merchantId)) {
			throw new IllegalOperationException(ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		// 只有订单状态为待发货才能填写物流信息
		if (!shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.BE_SHIPPED.getValue())) {
			throw new CanNotFillInShippingLogisticsException(ExceptionMessageConstant.ORDERS_NOT_TO_BE_SHIPPING_STATUS);
		}
		// 更新购物订单的状态
		ShoppingOrderDO shoppingOrderUpdateDO = new ShoppingOrderDO();
		shoppingOrderUpdateDO.setId(shoppingOrderDO.getId());
		shoppingOrderUpdateDO.setGmtTransport(new Date());
		shoppingOrderUpdateDO.setGmtModified(new Date());
		// 更改订单状态为待商家确认
		shoppingOrderUpdateDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
		if (param.getIsNeedsLogistics()) {
			// 更新购物订单的物流信息
			shoppingOrderUpdateDO.setExpressCompanyId(param.getExpressCompanyId());
			shoppingOrderUpdateDO.setExpressCompanyCode(param.getExpressCompanyCode());
			shoppingOrderUpdateDO.setExpressCompanyName(param.getExpressCompanyName());
			shoppingOrderUpdateDO.setWaybillNum(param.getWaybillNum());
		}
		shoppingOrderUpdateDO.setIsNeedsLogistics(param.getIsNeedsLogistics());
		shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderUpdateDO);
		// 更新购物订单项状态
		ShoppingOrderItemDOExample shoppingOrderItemDOExample = new ShoppingOrderItemDOExample();
		com.lawu.eshop.order.srv.domain.ShoppingOrderItemDOExample.Criteria shoppingOrderItemDOExampleCriteria = shoppingOrderItemDOExample.createCriteria();
		shoppingOrderItemDOExampleCriteria.andShoppingOrderIdEqualTo(id);
		// 只更新待发货状态的订单项，对于退款状态的订单不予处理
		shoppingOrderItemDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
		ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
		shoppingOrderItemDO.setGmtModified(new Date());
		shoppingOrderItemDO.setOrderStatus(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
		shoppingOrderItemDOMapper.updateByExampleSelective(shoppingOrderItemDO, shoppingOrderItemDOExample);
	}

	/**
	 * 第三方支付时获取订单原始总金额，用于调用第三方支付平台
	 * 
	 * @param orderIds
	 * @return
	 * @author Yangqh
	 */
	@Override
	public ShoppingOrderMoneyBO selectOrderMoney(String orderIds) throws TheOrderIsBeingProcessedException, OrderCreationFailedException {
		String[] orderIdsArray = orderIds.split(",");
		BigDecimal total = new BigDecimal(0);
		boolean isActivity = false;
		boolean isDeductionPay = false;
		String orderNum = "";
		for (int i = 0; i < orderIdsArray.length; i++) {
		    ShoppingOrderDO orderDO = shoppingOrderDOMapper.selectByPrimaryKey(Long.valueOf(orderIdsArray[i]));
			/*
			 * 判断订单的状态是否是待支付 如果不是，说明扣除库存失败，提示买家库存不足
			 */
			if (ShoppingOrderStatusEnum.PENDING.getValue().equals(orderDO.getOrderStatus())) {
				throw new TheOrderIsBeingProcessedException(ExceptionMessageConstant.THE_ORDER_IS_BEING_PROCESSED);
			}
			if (ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue().equals(orderDO.getOrderStatus())) {
				throw new OrderCreationFailedException(ExceptionMessageConstant.ORDER_CREATION_FAILED);
			}
			total = total.add(orderDO.getOrderTotalPrice());
			if(orderDO.getActivityId() != null){
				isActivity = true;
			}
			if(orderDO.getDeductionPoints().compareTo(BigDecimal.ZERO) == 1 && !isDeductionPay) {
				isDeductionPay = true;
			}
			if (i == 0) {
				orderNum = orderDO.getOrderNum();
			}
		}
		ShoppingOrderMoneyBO rtn = new ShoppingOrderMoneyBO();
		rtn.setOrderTotalPrice(total);
		rtn.setActivity(isActivity);
		rtn.setOrderNum(orderNum);
		rtn.setDeductionPay(isDeductionPay);
		return rtn;
	}
	
	/**
	 * 用于第三方回调获取订单总金额
	 * 
	 * @param orderIds
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月29日
	 */
	@Override
	public ShoppingOrderMoneyBO orderMoneyForNotify(String orderIds) {
		String[] orderIdsArray = orderIds.split(",");
		BigDecimal total = new BigDecimal(0);
		for (int i = 0; i < orderIdsArray.length; i++) {
		    ShoppingOrderDO orderDO = shoppingOrderDOMapper.selectByPrimaryKey(Long.valueOf(orderIdsArray[i]));
			total = total.add(orderDO.getOrderTotalPrice());
		}
		ShoppingOrderMoneyBO rtn = new ShoppingOrderMoneyBO();
		rtn.setOrderTotalPrice(total);
		return rtn;
	}

	/**
	 * 根据查询参数分页查询订单列表
	 * 
	 * @param param
	 *            查询参数
	 * @return
	 */
	@Override
	public Page<ShoppingOrderExtendBO> selectPage(ShoppingOrderQueryForeignToOperatorParam param) {
		ShoppingOrderDOExample shoppingOrdeDOExample = new ShoppingOrderDOExample();
		// 组装Criteria
		ShoppingOrderDOExample.Criteria baseCriteria = shoppingOrdeDOExample.createCriteria();

		if (param.getOrderStatus() != null) {
			baseCriteria.andOrderStatusEqualTo(param.getOrderStatus().getValue());
		}

		if (!StringUtils.isEmpty(param.getKeyword())) {
			shoppingOrdeDOExample.clear();

			ShoppingOrderDOExample.Criteria orderNumCriteria = shoppingOrdeDOExample.or();
			orderNumCriteria.andOrderNumEqualTo(param.getKeyword());
			orderNumCriteria.getAllCriteria().addAll(baseCriteria.getAllCriteria());

			ShoppingOrderDOExample.Criteria paroductCriteria = shoppingOrdeDOExample.or();
			paroductCriteria.andConsigneeNameLike("%" + param.getKeyword() + "%");
			paroductCriteria.getAllCriteria().addAll(baseCriteria.getAllCriteria());
		}

		// 查询总记录数
		Long count = shoppingOrderDOMapper.countByExample(shoppingOrdeDOExample);

		Page<ShoppingOrderExtendBO> shoppingOrderItemBOPage = new Page<>();
		shoppingOrderItemBOPage.setTotalCount(count.intValue());
		shoppingOrderItemBOPage.setCurrentPage(param.getCurrentPage());

		// 如果总记录为0，不再执行后续操作直接返回
		if (count <= 0 || param.getOffset() >= count) {
			return shoppingOrderItemBOPage;
		}

		if (param.getSortName() != null && param.getSortOrder() != null) {
			shoppingOrdeDOExample.setOrderByClause(param.getSortName().getDatabaseField() + " " + param.getSortOrder().name());
		} else {
			// 默认创建时间排序
			shoppingOrdeDOExample.setOrderByClause("gmt_create desc");
		}

		// 分页参数
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

		// 如果参数中的keyword有值，查询结果的订单项会缺少，所有先找出所有购物订单id再通过去查找购物订单以及级联的购物订单项
		List<ShoppingOrderDO> list = shoppingOrderDOMapper.selectByExampleWithRowbounds(shoppingOrdeDOExample, rowBounds);

		List<Long> shoppingOrderIdList = new ArrayList<>();
		for (ShoppingOrderDO item : list) {
			shoppingOrderIdList.add(item.getId());
		}

		ShoppingOrderExtendDOExample shoppingOrdeExtendDOExample = new ShoppingOrderExtendDOExample();
		shoppingOrdeExtendDOExample.setIncludeViewShoppingOrderItem(true);
		shoppingOrdeExtendDOExample.setIncludeShoppingOrderItem(true);
		shoppingOrdeExtendDOExample.createCriteria().andIdIn(shoppingOrderIdList);

		if (param.getSortName() != null && param.getSortOrder() != null) {
			shoppingOrdeExtendDOExample.setOrderByClause(param.getSortName().getDatabaseField() + " " + param.getSortOrder().name());
		} else {
			// 默认创建时间排序
			shoppingOrdeExtendDOExample.setOrderByClause("so.gmt_create desc");
		}
		List<ShoppingOrderExtendDO> shoppingOrderExtendDOList = shoppingOrderDOExtendMapper.selectByExample(shoppingOrdeExtendDOExample);
		shoppingOrderItemBOPage.setRecords(ShoppingOrderExtendConverter.convertShoppingOrderExtendBO(shoppingOrderExtendDOList));
		return shoppingOrderItemBOPage;
	}

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void minusInventorySuccess(Long id, ShoppingOrderCreateOrderReply reply) {
        ShoppingOrderDO shoppingOrderDOUpdate = new ShoppingOrderDO();
        shoppingOrderDOUpdate.setId(id);

        // 设置购物订单项为待支付状态
        ShoppingOrderItemDOExample shoppingOrderItemDOExample = new ShoppingOrderItemDOExample();
        ShoppingOrderItemDOExample.Criteria shoppingOrderItemDOExampleCriteria = shoppingOrderItemDOExample
                .createCriteria();
        shoppingOrderItemDOExampleCriteria.andShoppingOrderIdEqualTo(id);
        ShoppingOrderItemDO shoppingOrderItemDOUpdate = new ShoppingOrderItemDO();

        if (reply.getResult() == null) {
            /*
             * 删除购物车记录 如果用户是点击立即购物，是不经过购物车的，不需要删除购物车记录
             */
            ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(id);
            if (!StringUtils.isEmpty(shoppingOrderDO.getShoppingCartIdsStr())) {
                // 拆分购物车id
                String[] shoppingCartIdStrAry = StringUtils.split(shoppingOrderDO.getShoppingCartIdsStr(), ",");
                List<Long> shoppingCartIdList = new ArrayList<>();
                for (String shoppingCartIdStr : shoppingCartIdStrAry) {
                    shoppingCartIdList.add(Long.valueOf(shoppingCartIdStr));
                }
                ShoppingCartDOExample shoppingCartDOExample = new ShoppingCartDOExample();
                shoppingCartDOExample.createCriteria().andIdIn(shoppingCartIdList);
                shoppingCartDOMapper.deleteByExample(shoppingCartDOExample);
            }
            // 如果该订单使用了积分,需要异步扣除积分
            if (shoppingOrderDO.getDeductionPoints() != null && shoppingOrderDO.getDeductionPoints().doubleValue() > 0D) {
                // 扣除积分
                createOrderDeductionPointsTransactionMainServiceImpl.sendNotice(id);
            } else {
                // 设置订单状态为待支付状态(局部更新)
                shoppingOrderDOUpdate.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
                shoppingOrderDOUpdate.setGmtModified(new Date());
                shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderDOUpdate);

                // 设置购物订单项为待支付状态
                shoppingOrderItemDOUpdate.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
                shoppingOrderItemDOUpdate.setGmtModified(new Date());
                shoppingOrderItemDOMapper.updateByExampleSelective(shoppingOrderItemDOUpdate, shoppingOrderItemDOExample);
            }
        } else {
            // 设置订单状态为交易关闭(局部更新)
            shoppingOrderDOUpdate.setRemark(reply.getResult().getDescription());
            shoppingOrderDOUpdate.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
            shoppingOrderDOUpdate.setGmtModified(new Date());
            shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderDOUpdate);

            // 设置购物订单项为交易关闭状态
            shoppingOrderItemDOUpdate.setOrderStatus(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
            shoppingOrderItemDOUpdate.setGmtModified(new Date());
            shoppingOrderItemDOMapper.updateByExampleSelective(shoppingOrderItemDOUpdate, shoppingOrderItemDOExample);
        }
    }

	/**
	 * 更新订单信息
	 * 
	 * @param id
	 *            购物订单id
	 * @param param
	 *            查询参数
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateInformation(Long id, ShoppingOrderUpdateInfomationParam param) {
	    ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(id);
		if (shoppingOrderDO == null || shoppingOrderDO.getStatus().equals(StatusEnum.INVALID.getValue())) {
			throw new DataNotExistException(ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
		}
		shoppingOrderDO = ShoppingOrderConverter.convert(id, param);
		shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderDO);
	}

	/**
	 * 根据商家的id查询商家是否有进行中的订单
	 * 
	 * @param merchantId
	 *            商家的id
	 * @return
	 * @author Sunny
	 */
	@Override
	public ShoppingOrderIsNoOnGoingOrderBO isNoOnGoingOrder(Long merchantId) {
		ShoppingOrderExtendDOExample shoppingOrderExtendDOExample = new ShoppingOrderExtendDOExample();
		shoppingOrderExtendDOExample.setIncludeShoppingOrderItem(true);
		ShoppingOrderExtendDOExample.Criteria shoppingOrderExtendDOExampleCriteria = shoppingOrderExtendDOExample.createCriteria();
		shoppingOrderExtendDOExampleCriteria.andMerchantIdEqualTo(merchantId);

		/*
		 * 查找订单项状态不为交易取消和交易成功的数目
		 */
		List<Byte> processingStatus = new ArrayList<>();
		processingStatus.add(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
		processingStatus.add(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());

		shoppingOrderExtendDOExampleCriteria.andSOIOrderStatusNotIn(processingStatus);

		long count = shoppingOrderDOExtendMapper.countByExample(shoppingOrderExtendDOExample);

		return ShoppingOrderConverter.convert(count);
	}

    @Override
    public ShoppingOrderExtendBO getByShoppingOrderItemId(Long shoppingOrderItemId) {
        ShoppingOrderExtendBO rtn = null;
        ShoppingOrderExtendDOExample shoppingOrderExtendDOExample = new ShoppingOrderExtendDOExample();
        shoppingOrderExtendDOExample.setIncludeShoppingOrderItem(true);
        shoppingOrderExtendDOExample.setIncludeViewShoppingOrderItem(true);
        shoppingOrderExtendDOExample.createCriteria().andSOIIdEqualTo(shoppingOrderItemId);
        List<ShoppingOrderExtendDO> shoppingOrderExtendDOList = shoppingOrderDOExtendMapper
                .selectByExample(shoppingOrderExtendDOExample);
        if (shoppingOrderExtendDOList == null || shoppingOrderExtendDOList.isEmpty()) {
            return rtn;
        }
        rtn = ShoppingOrderExtendConverter.convertShoppingOrderExtendDetailBO(shoppingOrderExtendDOList.get(0));
        return rtn;
    }

	@Override
	public void executeAutoRemindShipments() {
		String automaticRemindShipments = propertyService.getByName(PropertyNameConstant.AUTOMATIC_REMIND_SHIPMENTS);
		Date date = DateUtil.add(new Date(), Integer.valueOf(automaticRemindShipments) * -1, Calendar.DAY_OF_YEAR);
		// 查找所有超时未发货的订单，提醒卖家发货
		List<NotShippedDO> notShippedDOList = shoppingOrderDOExtendMapper.selectByNotShipped(date);
		for (NotShippedDO item : notShippedDOList) {
			// 发送站内信和推送
			ShoppingOrderRemindShipmentsNotification notification = new ShoppingOrderRemindShipmentsNotification();
			notification.setQuantity(item.getCount());
			notification.setMerchantNum(item.getMerchantNum());
			messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_REMIND_SHIPMENTS, notification);
		}
	}

	/**
	 * 查询各种订单状态的数量
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 * @author Sunny
	 */
	@Override
	public ShoppingOrderNumberOfOrderStatusBO numberOfOrderStartus(Long memberId) {
		ShoppingOrderNumberOfOrderStatusBO rtn = new ShoppingOrderNumberOfOrderStatusBO();

		// 查询待付款订单的数量
		ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
		ShoppingOrderDOExample.Criteria shoppingOrderDOExampleCriteria = shoppingOrderDOExample.createCriteria();
		shoppingOrderDOExampleCriteria.andMemberIdEqualTo(memberId);
		shoppingOrderDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
		long pendingPaymentCount = shoppingOrderDOMapper.countByExample(shoppingOrderDOExample);

		// 查询待发货数量
		shoppingOrderDOExample = new ShoppingOrderDOExample();
		shoppingOrderDOExampleCriteria = shoppingOrderDOExample.createCriteria();
		shoppingOrderDOExampleCriteria.andMemberIdEqualTo(memberId);
		shoppingOrderDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
		long beShippedCount = shoppingOrderDOMapper.countByExample(shoppingOrderDOExample);

		// 查询待收货数量
		shoppingOrderDOExample = new ShoppingOrderDOExample();
		shoppingOrderDOExampleCriteria = shoppingOrderDOExample.createCriteria();
		shoppingOrderDOExampleCriteria.andMemberIdEqualTo(memberId);
		shoppingOrderDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
		long toBeReceivedCount = shoppingOrderDOMapper.countByExample(shoppingOrderDOExample);

		// 查询待评价数量
		ShoppingOrderItemExtendDOExample shoppingOrderItemExtendDOExample = new ShoppingOrderItemExtendDOExample();
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingOrder(true);
		ShoppingOrderItemExtendDOExample.Criteria shoppingOrderItemExtendDOExampleCriteria = shoppingOrderItemExtendDOExample.createCriteria();
		shoppingOrderItemExtendDOExampleCriteria.andSOMemberIdEqualTo(memberId);
		shoppingOrderItemExtendDOExampleCriteria.andSOStatusEqualTo(StatusEnum.VALID.getValue());
		// 订单状态为交易成功并且未评价
		shoppingOrderItemExtendDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
		shoppingOrderItemExtendDOExampleCriteria.andIsEvaluationEqualTo(false);
		long evaluationCount = shoppingOrderItemExtendDOMapper.countByExample(shoppingOrderItemExtendDOExample);

		// 查询退货中数量
		shoppingOrderItemExtendDOExample = new ShoppingOrderItemExtendDOExample();
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingOrder(true);
		shoppingOrderItemExtendDOExampleCriteria = shoppingOrderItemExtendDOExample.createCriteria();
		shoppingOrderItemExtendDOExampleCriteria.andSOMemberIdEqualTo(memberId);
		shoppingOrderItemExtendDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
		// 用户可以多次申请退款，查询当中有效的一条记录
		long refundingCount = shoppingOrderItemExtendDOMapper.countByExample(shoppingOrderItemExtendDOExample);

		rtn.setPendingPaymentCount(pendingPaymentCount);
		rtn.setBeShippedCount(beShippedCount);
		rtn.setToBeReceivedCount(toBeReceivedCount);
		rtn.setEvaluationCount(evaluationCount);
		rtn.setRefundingCount(refundingCount);
		return rtn;
	}

	/**
	 * 查询未计算提成订单
	 * 
	 * @return
	 * @author Sunny
	 */
	@Override
	public List<ShoppingOrderBO> commissionShoppingOrder(int offset, int pageSize) {
		/*
		 * 查找未计算过提成的订单
		 */
		ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
		ShoppingOrderDOExample.Criteria shoppingOrderDOExampleCriteria = shoppingOrderDOExample.createCriteria();
		// 订单状态是交易成功的
		shoppingOrderDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
		// 没有计算过提成的
		shoppingOrderDOExampleCriteria.andCommissionStatusEqualTo(CommissionStatusEnum.NOT_COUNTED.getValue());
		// 订单已经完成，钱已经打给商家
		shoppingOrderDOExampleCriteria.andIsDoneEqualTo(true);
		// 订单金额大于0（0元抢购不算提成）
		shoppingOrderDOExampleCriteria.andActualAmountGreaterThan(new BigDecimal(0));

		shoppingOrderDOExample.setOrderByClause(" id asc ");

		RowBounds rowBounds = new RowBounds(offset,pageSize);

		List<ShoppingOrderDO> shoppingOrderDOList = shoppingOrderDOMapper.selectByExampleWithRowbounds(shoppingOrderDOExample,rowBounds);

		return ShoppingOrderConverter.convert(shoppingOrderDOList);
	}

	/**
	 * 根据订单id更新购物订单的提成状态和提成时间
	 * 
	 * @param ids
	 *            购物订单id集合
	 * @return
	 * @author Sunny
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateCommissionStatus(List<Long> ids) {
		ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
		shoppingOrderDOExample.createCriteria().andIdIn(ids);
		ShoppingOrderDO shoppingOrderDO = new ShoppingOrderDO();
		// 更新提成状态和提成时间
		shoppingOrderDO.setGmtCommission(new Date());
		shoppingOrderDO.setCommissionStatus(CommissionStatusEnum.CALCULATED.getValue());
		shoppingOrderDOMapper.updateByExampleSelective(shoppingOrderDO, shoppingOrderDOExample);
	}

	@Override
	public ShoppingOrderNumberOfOrderStatusForMerchantBO numberOfOrderStartusByMerchant(Long merchantId) {
		ShoppingOrderNumberOfOrderStatusForMerchantBO rtn = new ShoppingOrderNumberOfOrderStatusForMerchantBO();

		// 查询待收货订单的数量
		ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
		ShoppingOrderDOExample.Criteria shoppingOrderDOExampleCriteria = shoppingOrderDOExample.createCriteria();
		shoppingOrderDOExampleCriteria.andMerchantIdEqualTo(merchantId);
		shoppingOrderDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
		long pendingPaymentCount = shoppingOrderDOMapper.countByExample(shoppingOrderDOExample);

		// 查询待发货数量
		shoppingOrderDOExample = new ShoppingOrderDOExample();
		shoppingOrderDOExampleCriteria = shoppingOrderDOExample.createCriteria();
		shoppingOrderDOExampleCriteria.andMerchantIdEqualTo(merchantId);
		shoppingOrderDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.BE_SHIPPED.getValue());
		long beShippedCount = shoppingOrderDOMapper.countByExample(shoppingOrderDOExample);

		// 查询待收货数量
		shoppingOrderDOExample = new ShoppingOrderDOExample();
		shoppingOrderDOExampleCriteria = shoppingOrderDOExample.createCriteria();
		shoppingOrderDOExampleCriteria.andMerchantIdEqualTo(merchantId);
		shoppingOrderDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
		long toBeReceivedCount = shoppingOrderDOMapper.countByExample(shoppingOrderDOExample);

		// 查询退货中数量
		ShoppingOrderItemExtendDOExample shoppingOrderItemExtendDOExample = new ShoppingOrderItemExtendDOExample();
		shoppingOrderItemExtendDOExample.setIsIncludeShoppingOrder(true);
		ShoppingOrderItemExtendDOExample.Criteria shoppingOrderItemExtendDOExampleCriteria = shoppingOrderItemExtendDOExample.createCriteria();
		shoppingOrderItemExtendDOExampleCriteria.andSOMerchantIdEqualTo(merchantId);
		shoppingOrderItemExtendDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.REFUNDING.getValue());
		long refundingCount = shoppingOrderItemExtendDOMapper.countByExample(shoppingOrderItemExtendDOExample);

		rtn.setPendingPaymentCount(pendingPaymentCount);
		rtn.setBeShippedCount(beShippedCount);
		rtn.setToBeReceivedCount(toBeReceivedCount);
		rtn.setRefundingCount(refundingCount);
		return rtn;
	}

	/**
	 * 统计商家的交易数据
	 * 
	 * @param merchantId
	 *            商家id
	 * @return
	 * @author Sunny
	 */
	@Override
	public ReportRiseRateDTO selectByTransactionData(ReportDataParam param) {
		int x = 0;
		ShoppingOrderReportDataParam shoppingOrderReportDataParam = new ShoppingOrderReportDataParam();
		shoppingOrderReportDataParam.setMerchantId(param.getMerchantId());

		if (ReportFansRiseRateEnum.DAY.getValue().equals(param.getFlag().getValue())) {
			shoppingOrderReportDataParam.setFlag(ReportFansRiseRateEnum.DAY.getValue());
			shoppingOrderReportDataParam.setGmtDone(DateUtil.getDateFormat(new Date(), "yyyyMM"));
			x = DateUtil.getNowMonthDay();
		} else if (ReportFansRiseRateEnum.MONTH.getValue().equals(param.getFlag().getValue())) {
			shoppingOrderReportDataParam.setFlag(ReportFansRiseRateEnum.MONTH.getValue());
			shoppingOrderReportDataParam.setGmtDone(DateUtil.getDateFormat(new Date(), "yyyy"));
			x = 12;
		}

		List<ReportRiseRateView> list = shoppingOrderDOExtendMapper.selectByTransactionData(shoppingOrderReportDataParam);
		// 总金额
		BigDecimal total = shoppingOrderDOExtendMapper.selectByTransactionTotalAmount(shoppingOrderReportDataParam);

		ReportRiseRateDTO rtn = ReportConvert.reportBrokeLineShow(list, x);
		rtn.setTotal(total == null ? "0" : total.toString());
		return rtn;
	}

	/**
	 * 粉丝数据-消费转化
	 * 
	 * @param param
	 * @return
	 * @author Sunny
	 */
	@Override
	public List<ReportRiseRerouceDTO> fansSaleTransform(ReportDataParam param) {
		ShoppingOrderReportDataParam shoppingOrderReportDataParam = new ShoppingOrderReportDataParam();
		shoppingOrderReportDataParam.setMerchantId(param.getMerchantId());

//		if (ReportFansRiseRateEnum.DAY.getValue().equals(param.getFlag().getValue())) {
//			shoppingOrderReportDataParam.setFlag(ReportFansRiseRateEnum.DAY.getValue());
//			shoppingOrderReportDataParam.setGmtCreate(DateUtil.getDateFormat(new Date(), "yyyyMM"));
//		} else if (ReportFansRiseRateEnum.MONTH.getValue().equals(param.getFlag().getValue())) {
//			shoppingOrderReportDataParam.setFlag(ReportFansRiseRateEnum.MONTH.getValue());
//			shoppingOrderReportDataParam.setGmtCreate(DateUtil.getDateFormat(new Date(), "yyyy"));
//		}

		List<ReportFansSaleTransFormDO> reportFansSaleTransFormDOList = shoppingOrderDOExtendMapper.selectByFansSaleTransForm(shoppingOrderReportDataParam);

		return ShoppingOrderConverter.convertReportRiseRerouceDTOList(reportFansSaleTransFormDOList);
	}

    @Override
    public List<ShoppingOrderDO> selectAutoCancelOrder(int offset, int pageSize) {
        ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
        ShoppingOrderDOExample.Criteria criteria = shoppingOrderDOExample.createCriteria();
        String automaticCancelOrderTime = propertyService.getByName(PropertyNameConstant.AUTOMATIC_CANCEL_ORDER);
        // 不是抢购活动订单
        criteria.andActivityIdIsNull();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        criteria.andGmtCreateLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(automaticCancelOrderTime) * -1, Calendar.DAY_OF_YEAR));
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        // 查找所有超时未付款的订单
        return shoppingOrderDOMapper.selectByExampleWithRowbounds(shoppingOrderDOExample, rowBounds);
    }
	
    @Override
    public List<ShoppingOrderDO> selectAutoRemindToBeCancelledOrder(int offset, int pageSize) {
        ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
        ShoppingOrderDOExample.Criteria criteria = shoppingOrderDOExample.createCriteria();
        String automaticRemindNoPaymentOrderTime = propertyService.getByName(PropertyNameConstant.AUTOMATIC_REMIND_NO_PAYMENT_ORDER);
        // 不是抢购订单
        criteria.andActivityIdIsNull();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        criteria.andGmtCreateLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(automaticRemindNoPaymentOrderTime) * -1, Calendar.DAY_OF_YEAR));
        // 没有发送过提醒消息的
        criteria.andSendTimeEqualTo(0);
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        // 查找所有超时未付款的订单
        return shoppingOrderDOMapper.selectByExampleWithRowbounds(shoppingOrderDOExample, rowBounds);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void executeAutoRemindToBeCancelledOrder(ShoppingOrderDO shoppingOrderDO) {
        // 更新发送次数，但是不更新更新时间字段
        int sendTime = shoppingOrderDO.getSendTime() == null ? 1 : shoppingOrderDO.getSendTime().intValue() + 1;
        shoppingOrderDO.setSendTime(sendTime);
        shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderDO);
        
       

        /*
         * 买家下单没有及时支付，超过支付时间，提醒买家支付
         */
        // 组装要发送的消息
        ShoppingOrderNoPaymentNotification shoppingOrderNoPaymentNotification = new ShoppingOrderNoPaymentNotification();
        shoppingOrderNoPaymentNotification.setId(shoppingOrderDO.getId());
        shoppingOrderNoPaymentNotification.setMemberNum(shoppingOrderDO.getMemberNum());
        shoppingOrderNoPaymentNotification.setOrderNum(shoppingOrderDO.getOrderNum());
        
        ShoppingOrderItemDOExample shoppingOrderItemDOExample = new ShoppingOrderItemDOExample();
		com.lawu.eshop.order.srv.domain.ShoppingOrderItemDOExample.Criteria shoppingOrderItemDOExampleCriteria = shoppingOrderItemDOExample.createCriteria();
		shoppingOrderItemDOExampleCriteria.andShoppingOrderIdEqualTo(shoppingOrderDO.getId());

		List<ShoppingOrderItemDO> shoppingOrderItemDOList = shoppingOrderItemDOMapper.selectByExample(shoppingOrderItemDOExample);
		if (!shoppingOrderItemDOList.isEmpty()) {
			shoppingOrderNoPaymentNotification.setImgUrl(shoppingOrderItemDOList.get(0).getProductFeatureImage());
		}
       
        // 发送消MQ息
        messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_ORDER_NO_PAYMENT_PUSH_TO_MEMBER, shoppingOrderNoPaymentNotification);
    }
    
    @Override
    public List<ShoppingOrderItemExtendDO> selectAutoCommentOrder(int offset, int pageSize) {
        ShoppingOrderItemExtendDOExample shoppingOrderItemExtendDOExample = new ShoppingOrderItemExtendDOExample();
        shoppingOrderItemExtendDOExample.setIsIncludeShoppingOrder(true);
        ShoppingOrderItemExtendDOExample.Criteria shoppingOrderItemExtendDOExampleCriteria = shoppingOrderItemExtendDOExample.createCriteria();
        shoppingOrderItemExtendDOExampleCriteria.andIsEvaluationEqualTo(false);
        shoppingOrderItemExtendDOExampleCriteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());

        // 查找配置表获取自动好评时间
        String automaticEvaluation = propertyService.getByName(PropertyNameConstant.AUTOMATIC_EVALUATION);

        // 如果交易时间超过automaticEvaluation的记录
        shoppingOrderItemExtendDOExampleCriteria.andSOGmtTransactionLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(automaticEvaluation) * -1, Calendar.DAY_OF_YEAR));

        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
        return shoppingOrderItemExtendDOMapper.selectByExampleWithRowbounds(shoppingOrderItemExtendDOExample, rowBounds);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeAutoCommentOrder(ShoppingOrderItemExtendDO shoppingOrderItemExtendDO) {
        // 更新为已评论
        ShoppingOrderItemDO shoppingOrderItemDO = new ShoppingOrderItemDO();
        shoppingOrderItemDO.setId(shoppingOrderItemExtendDO.getId());
        shoppingOrderItemDO.setIsEvaluation(true);
        shoppingOrderItemDOMapper.updateByPrimaryKeySelective(shoppingOrderItemDO);

        // 发送MQ消息，通知mall模块添加默认好评记录
        shoppingOrderAutoCommentTransactionMainServiceImpl.sendNotice(shoppingOrderItemExtendDO.getId());
    }
    
    @Override
    public List<ShoppingOrderDO> selectAutoReleaseFrozenFundsOrder(int offset, int pageSize) {
        ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
        ShoppingOrderDOExample.Criteria criteria = shoppingOrderDOExample.createCriteria();

        String refundRequestTime = propertyService.getByName(PropertyNameConstant.REFUND_REQUEST_TIME);

        // 查找订单以及订单项状态都为交易成功的订单
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.TRADING_SUCCESS.getValue());
        criteria.andIsDoneEqualTo(false);
        criteria.andGmtTransactionLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(refundRequestTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andIsRefundItemsEqualTo(false);
        
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
        return shoppingOrderDOMapper.selectByExampleWithRowbounds(shoppingOrderDOExample, rowBounds);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void executeAutoReleaseFrozenFundsOrder(ShoppingOrderDO shoppingOrderDO) {
        // 更新订单状态为完成
        ShoppingOrderDO update = new ShoppingOrderDO();
        update.setSendTime(0);
        update.setId(shoppingOrderDO.getId());
        update.setIsDone(true);
        update.setGmtDone(new Date());
        update.setGmtModified(new Date());
        shoppingOrderDOMapper.updateByPrimaryKeySelective(update);

        // 发送提醒消息
        ordersTradingIncomeNotice(shoppingOrderDO.getId(), shoppingOrderDO.getActualAmount(), shoppingOrderDO.getMerchantNum());

        // 释放冻结资金
        shoppingOrderPaymentsToMerchantTransactionMainServiceImpl.sendNotice(shoppingOrderDO.getId());
    }
    
    @Override
    public List<ShoppingOrderDO> selectAutoReceiptOrder(int currentPage, int pageSize) {
        ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
        ShoppingOrderDOExample.Criteria criteria = shoppingOrderDOExample.createCriteria();

        String automaticReceiptTime = propertyService.getByName(PropertyNameConstant.AUTOMATIC_RECEIPT);

        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.TO_BE_RECEIVED.getValue());
        criteria.andGmtTransportLessThanOrEqualTo(DateUtil.add(new Date(), Integer.valueOf(automaticReceiptTime) * -1, Calendar.DAY_OF_YEAR));
        criteria.andIsRefundItemsEqualTo(false);
        
        // 分页参数
        RowBounds rowBounds = new RowBounds(0, pageSize);
        
        // 查找所有超时未收货的订单，自动收货
        return shoppingOrderDOMapper.selectByExampleWithRowbounds(shoppingOrderDOExample, rowBounds);
    }
    
    @Override
    public Long isBuy(ActivityProductBuyQueryParam param) {
        ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
        ShoppingOrderDOExample.Criteria criteria = shoppingOrderDOExample.createCriteria();
        criteria.andMemberIdEqualTo(param.getMemberId());
        criteria.andActivityProductIdEqualTo(param.getActivityProductId());
        criteria.andOrderStatusNotEqualTo(ShoppingOrderStatusEnum.CANCEL_TRANSACTION.getValue());
        return shoppingOrderDOMapper.countByExample(shoppingOrderDOExample);
    }
    
    @Override
    public List<ShoppingOrderBO> selectCancelSeckillActivityOrder(int offset, int pageSize) {
        ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
        ShoppingOrderDOExample.Criteria criteria = shoppingOrderDOExample.createCriteria();
        // 是抢购活动订单
        criteria.andActivityIdIsNotNull();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        criteria.andGmtCreateLessThanOrEqualTo(DateUtil.add(new Date(), PropertyConstant.SECKILL_ACTIVITY_CANCEL_ORDER_TIME * -1, Calendar.MINUTE));
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        // 查找所有超时未付款的订单
        List<ShoppingOrderDO> shoppingOrderDOList = shoppingOrderDOMapper.selectByExampleWithRowbounds(shoppingOrderDOExample, rowBounds);
        return ShoppingOrderConverter.convert(shoppingOrderDOList);
    }
    
    @Override
    public List<ShoppingOrderDO> selectRemindAboutCancelSeckillActivityOrder(int offset, int pageSize) {
        ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
        ShoppingOrderDOExample.Criteria criteria = shoppingOrderDOExample.createCriteria();
        criteria.andOrderStatusEqualTo(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        // 是抢购活动订单
        criteria.andActivityIdIsNotNull();
        criteria.andGmtCreateLessThanOrEqualTo(DateUtil.add(new Date(), PropertyConstant.SECKILL_ACTIVITY_REMIND_ABOUT_CANCEL_ORDER_TIME * -1, Calendar.MINUTE));
        // 没有发送过提醒消息的
        criteria.andSendTimeEqualTo(0);
        // 分页参数
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        // 查找所有超时未付款的订单
        return shoppingOrderDOMapper.selectByExampleWithRowbounds(shoppingOrderDOExample, rowBounds);
    }
    
    @Override
    public ShoppingOrderPaymentStatusReply paymentStatus(ShoppingOrderPaymentNotification notification) {
        ShoppingOrderPaymentStatusReply rtn = new ShoppingOrderPaymentStatusReply();
        /*
         *  通过shoppingOrderIds中的第一个订单id查询用户编号
         *  判断第三方编号是否一致
         */
        String[] shoppingOrderIds = StringUtils.split(notification.getShoppingOrderIds(), ",");
        if (shoppingOrderIds.length > 0) {
            Long shoppingOrderId = Long.valueOf(shoppingOrderIds[0]);
            ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(shoppingOrderId);
            if (!shoppingOrderDO.getOrderStatus().equals(ShoppingOrderStatusEnum.BE_SHIPPED.getValue()) || shoppingOrderDO.getThirdNumber().equals(notification.getThirdNumber())) {
                return rtn;
            }
            rtn.setMemberNum(shoppingOrderDO.getMemberNum());
            rtn.setPaymentMethod(notification.getPaymentMethod());
            rtn.setShoppingOrderIds(notification.getShoppingOrderIds());
            rtn.setThirdNumber(notification.getThirdNumber());
        }
        return rtn;
    }

	@Override
	public BigDecimal selectDeductionPointsAmount(String orderIds) {
		List<String> idsArray = Arrays.asList(orderIds.split(","));
		List<Long> idsList = new ArrayList<>();
		for(String id : idsArray){
			idsList.add(Long.valueOf(id));
		}
		ShoppingOrderDOExample shoppingOrderDOExample = new ShoppingOrderDOExample();
		ShoppingOrderDOExample.Criteria criteria = shoppingOrderDOExample.createCriteria();
		criteria.andIdIn(idsList);
		List<ShoppingOrderDO> shoppingOrderDOList = shoppingOrderDOMapper.selectByExample(shoppingOrderDOExample);
		BigDecimal totalDeductionPointsAmount = new BigDecimal(0);
		for(ShoppingOrderDO shoppingOrderDO : shoppingOrderDOList) {
			totalDeductionPointsAmount = totalDeductionPointsAmount.add(shoppingOrderDO.getDeductionPointsAmount());
		}
		return totalDeductionPointsAmount;
	}

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deductionPoints(Long id, CreateOrderDeductionPointsReply reply) {
        ShoppingOrderDO shoppingOrderDOUpdate = new ShoppingOrderDO();
        shoppingOrderDOUpdate.setId(id);

        // 设置购物订单项为待支付状态
        ShoppingOrderItemDOExample shoppingOrderItemDOExample = new ShoppingOrderItemDOExample();
        ShoppingOrderItemDOExample.Criteria shoppingOrderItemDOExampleCriteria = shoppingOrderItemDOExample.createCriteria();
        shoppingOrderItemDOExampleCriteria.andShoppingOrderIdEqualTo(id);
        ShoppingOrderItemDO shoppingOrderItemDOUpdate = new ShoppingOrderItemDO();
        
        if (!reply.getIsSuccess()) {
            ShoppingOrderDO shoppingOrderDO = shoppingOrderDOMapper.selectByPrimaryKey(id);
            // 设置抵扣积分和积分抵扣金额为0
            shoppingOrderDOUpdate.setDeductionPoints(new BigDecimal(0));
            shoppingOrderDOUpdate.setDeductionPointsAmount(new BigDecimal(0));
            // 订单总价加上积分抵扣金额
            shoppingOrderDOUpdate.setOrderTotalPrice(shoppingOrderDO.getOrderTotalPrice().add(shoppingOrderDO.getDeductionPointsAmount()));
            
            // 设置抵扣积分和积分抵扣金额为0
            shoppingOrderItemDOUpdate.setDeductionPoints(new BigDecimal(0));
            shoppingOrderItemDOUpdate.setDeductionPointsAmount(new BigDecimal(0));
        }

        // 设置订单状态为待支付状态(局部更新)
        shoppingOrderDOUpdate.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        shoppingOrderDOUpdate.setGmtModified(new Date());
        shoppingOrderDOMapper.updateByPrimaryKeySelective(shoppingOrderDOUpdate);

        // 设置购物订单项为待支付状态
        shoppingOrderItemDOUpdate.setOrderStatus(ShoppingOrderStatusEnum.PENDING_PAYMENT.getValue());
        shoppingOrderItemDOUpdate.setGmtModified(new Date());
        shoppingOrderItemDOMapper.updateByExampleSelective(shoppingOrderItemDOUpdate, shoppingOrderItemDOExample);
    }
    
	/**************************************************************
	 * PRIVATE METHOD
	 **************************************************************/

	/**
	 * 提醒商家新增订单交易收入
	 * 
	 * @param shoppingOrderId
	 * @author Sunny
	 * @date 2017年6月8日
	 */
	private void ordersTradingIncomeNotice(Long shoppingOrderId, BigDecimal actualAmount, String merchantNum) {
		ShoppingOrderOrdersTradingIncomeNoticeNotification notification = new ShoppingOrderOrdersTradingIncomeNoticeNotification();
		notification.setShoppingOrderId(shoppingOrderId);
		notification.setActualAmount(actualAmount);
		notification.setMerchantNum(merchantNum);
		messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_ORDERS_TRADING_INCOME_NOTICE, notification);
	}

}
