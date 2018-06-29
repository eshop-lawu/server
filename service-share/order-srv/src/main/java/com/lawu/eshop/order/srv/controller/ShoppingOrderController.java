package com.lawu.eshop.order.srv.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.IllegalOperationException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.order.dto.CommentOrderDTO;
import com.lawu.eshop.order.dto.ReportRiseRateDTO;
import com.lawu.eshop.order.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.eshop.order.dto.ShoppingOrderIsNoOnGoingOrderDTO;
import com.lawu.eshop.order.dto.ShoppingOrderMoneyDTO;
import com.lawu.eshop.order.dto.ShoppingOrderPaymentDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressInfoDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendQueryDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundForMerchantDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundForOperatorDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderNumberOfOrderStatusDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToMerchantDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToOperatorDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderStatusDTO;
import com.lawu.eshop.order.param.ActivityProductBuyQueryParam;
import com.lawu.eshop.order.param.ReportDataParam;
import com.lawu.eshop.order.param.ShoppingOrderLogisticsInformationParam;
import com.lawu.eshop.order.param.ShoppingOrderRequestRefundParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementParam;
import com.lawu.eshop.order.param.ShoppingOrderUpdateInfomationParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMemberParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMerchantParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToOperatorParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundQueryForeignParam;
import com.lawu.eshop.order.srv.bo.ExpressInquiriesDetailBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderIsNoOnGoingOrderBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderMoneyBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderNumberOfOrderStatusBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderNumberOfOrderStatusForMerchantBO;
import com.lawu.eshop.order.srv.constants.ExceptionMessageConstant;
import com.lawu.eshop.order.srv.constants.PropertyConstant;
import com.lawu.eshop.order.srv.constants.PropertyNameConstant;
import com.lawu.eshop.order.srv.converter.ShoppingOrderConverter;
import com.lawu.eshop.order.srv.converter.ShoppingOrderExtendConverter;
import com.lawu.eshop.order.srv.converter.ShoppingOrderItemConverter;
import com.lawu.eshop.order.srv.converter.ShoppingOrderItemExtendConverter;
import com.lawu.eshop.order.srv.exception.CanNotFillInShippingLogisticsException;
import com.lawu.eshop.order.srv.exception.OrderCreationFailedException;
import com.lawu.eshop.order.srv.exception.OrderNotCanceledException;
import com.lawu.eshop.order.srv.exception.OrderNotDeleteException;
import com.lawu.eshop.order.srv.exception.OrderNotReceivedException;
import com.lawu.eshop.order.srv.exception.OrderNotRefundException;
import com.lawu.eshop.order.srv.exception.TheOrderIsBeingProcessedException;
import com.lawu.eshop.order.srv.service.PropertyService;
import com.lawu.eshop.order.srv.service.ShoppingOrderItemService;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;
import com.lawu.eshop.order.srv.strategy.ExpressStrategy;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * 购物订单
 * 
 * @author Sunny
 * @date 2017/04/06
 */
@RestController
@RequestMapping(value = "shoppingOrder/")
public class ShoppingOrderController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(ShoppingOrderController.class);
	
	@Autowired
	private ShoppingOrderService shoppingOrderService;

	@Autowired
	private ShoppingOrderItemService shoppingOrderItemService;

	@Autowired
	private ExpressStrategy expressStrategy;

	@Autowired
	private PropertyService propertyService;

	/*********************************************************
	 * Member Api
	 ******************************************************/

	/**
	 * 保存订单
	 * 
	 * @param param
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result<List<Long>> save(@RequestBody List<ShoppingOrderSettlementParam> params) {
	    try {
	        List<Long> ids = shoppingOrderService.save(params);
	        return successCreated(ids);
	    } catch (WrongOperationException e) {
	        return successCreated(e.getRet(), e.getMessage());
	    }
	}

	/**
	 * To 用户 根据查询参数分页查询
	 * 
	 * @param memberId
	 *            会员id
	 * @param params
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "selectPageByMemberId/{memberId}", method = RequestMethod.POST)
	public Result<Page<ShoppingOrderExtendQueryDTO>> selectPageByMemberId(@PathVariable("memberId") Long memberId, @RequestBody ShoppingOrderQueryForeignToMemberParam param) {
		Page<ShoppingOrderExtendBO> shoppingOrderExtendQueryBOPage = shoppingOrderService.selectPageByMemberId(memberId, param);
		Page<ShoppingOrderExtendQueryDTO> shoppingOrderExtendQueryDTOPage = ShoppingOrderExtendConverter.convertShoppingOrderExtendQueryDTOPage(shoppingOrderExtendQueryBOPage);
		return successCreated(shoppingOrderExtendQueryDTOPage);
	}

	/**
	 * 取消购物订单
	 * 
	 * @param memberId
	 *            会员id
	 * @param id
	 *            购物订单id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "cancelOrder/{memberId}/{id}", method = RequestMethod.PUT)
	public Result cancelOrder(@PathVariable("memberId") Long memberId, @PathVariable("id") Long id) {
		try {
			shoppingOrderService.cancelOrder(memberId, id);
		} catch (DataNotExistException e) {
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		} catch (OrderNotCanceledException e) {
			return successCreated(ResultCode.ORDER_NOT_CANCELED, e.getMessage());
		}
		return successCreated();
	}

	/**
	 * 
	 * @param memberId
	 *            会员id
	 * @param id
	 *            购物订单id
	 * @author jiangxinjun
	 * @date 2017年7月10日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "deleteOrder/{memberId}/{id}", method = RequestMethod.PUT)
	public Result deleteOrder(@PathVariable("memberId") Long memberId, @PathVariable("id") Long id) {
		try {
			shoppingOrderService.deleteOrder(memberId, id);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		} catch (OrderNotDeleteException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ORDER_NOT_DELETE, e.getMessage());
		}
		return successCreated();
	}

	/**
	 * 根据查询参数分页查询退款记录 购物订单 购物订单项 退款详情关联查询 To Member
	 * 
	 * @param memberId
	 *            会员id
	 * @param param
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "selectRefundPageByMemberId/{memberId}", method = RequestMethod.POST)
	public Result<Page<ShoppingOrderItemRefundDTO>> selectRefundPageByMemberId(@PathVariable("memberId") Long memberId, @RequestBody ShoppingRefundQueryForeignParam param) {
		Page<ShoppingOrderItemExtendBO> page = shoppingOrderItemService.selectRefundPageByMemberId(memberId, param);
		return successCreated(ShoppingOrderItemExtendConverter.convertShoppingOrderItemRefundDTOPage(page));
	}

	/**
	 * 买家申请退款 修改订单项订单状态为退款中 修改订单项退款状态为待商家确认
	 * 
	 * @param shoppingOrderItemId
	 *            购物订单项id
	 * @param memberId
	 *            会员id(用于鉴权)
	 * @param param
	 *            退款参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "requestRefund/{shoppingOrderItemId}", method = RequestMethod.PUT)
	public Result requestRefund(@PathVariable("shoppingOrderItemId") Long shoppingOrderItemId, @RequestParam("memberId") Long memberId, @RequestBody ShoppingOrderRequestRefundParam param) {
		try {
			// 修改购物订单以及订单项状态，保存退款详情记录
			shoppingOrderService.requestRefund(shoppingOrderItemId, memberId, param);
		} catch (DataNotExistException e) {
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		} catch (OrderNotRefundException e) {
			return successCreated(ResultCode.ORDER_NOT_REFUND, e.getMessage());
		}
		return successCreated();
	}

	/**
	 * 订单支付
	 * 
	 * @param id
	 *            购物订单id
	 * @param memberId
	 *            会员id
	 * @return
	 */
	@RequestMapping(value = "orderPayment/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderPaymentDTO> orderPayment(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
		ShoppingOrderBO shoppingOrderBO = shoppingOrderService.getShoppingOrder(id);
		if (shoppingOrderBO.getId() == null) {
			return successGet(ResultCode.NOT_FOUND_DATA, ExceptionMessageConstant.SHOPPING_ORDER_DATA_NOT_EXIST);
		}
		if (!shoppingOrderBO.getMemberId().equals(memberId)) {
			return successGet(ResultCode.ILLEGAL_OPERATION, ExceptionMessageConstant.ILLEGAL_OPERATION_SHOPPING_ORDER);
		}
		return successGet(ShoppingOrderConverter.convert(shoppingOrderBO));
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
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "tradingSuccess/{id}", method = RequestMethod.PUT)
	public Result tradingSuccess(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
		try {
			shoppingOrderService.tradingSuccess(id, memberId);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		} catch (OrderNotReceivedException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ORDER_NOT_REFUND, e.getMessage());
		}
		return successCreated();
	}
	
    /**
     * 查询当前用户是否购买过抢购商品
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    @RequestMapping(value = "isBuy", method = RequestMethod.PUT)
    public Result<Boolean> isBuy(@RequestBody @Validated ActivityProductBuyQueryParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        Long count = shoppingOrderService.isBuy(param);
        boolean isBuy = false;
        if (count != null && count > 0) {
            isBuy = true;
        }
        return successCreated(isBuy);
    }
    
    /**
     * 查询订单状态
     * 
     * @param id
     * @param memberId
     * @return
     * @author jiangxinjun
     * @createDate 2018年4月26日
     * @updateDate 2018年4月26日
     */
    @RequestMapping(value = "orderStatus/{id}", method = RequestMethod.GET)
    public Result<ShoppingOrderStatusDTO> orderStatus(@PathVariable("id") Long id,
            @RequestParam(value = "memberId", required = false) Long memberId, @RequestParam(value = "merchantId", required = false) Long merchantId) {
        try {
            ShoppingOrderBO shoppingOrderBO = shoppingOrderService.getShoppingOrder(id, memberId, merchantId);
            ShoppingOrderStatusDTO model = new ShoppingOrderStatusDTO();
            model.setOrderStatus(shoppingOrderBO.getOrderStatus());
            return successGet(model);
        } catch (DataNotExistException e) {
            logger.error(e.getMessage(), e);
            return successGet(ResultCode.NOT_FOUND_DATA, e.getMessage());
        } catch (IllegalOperationException e) {
            logger.error(e.getMessage(), e);
            return successGet(ResultCode.ILLEGAL_OPERATION, e.getMessage());
        }
    }

	/*********************************************************
	 * Merchant Api
	 ******************************************************/

	/**
	 * To商家 根据查询参数分页查询
	 * 
	 * @param merchantId
	 *            商家id
	 * @param params
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "selectPageByMerchantId/{merchantId}", method = RequestMethod.POST)
	public Result<Page<ShoppingOrderQueryToMerchantDTO>> selectPageByMerchantId(@PathVariable("merchantId") Long merchantId, @RequestBody ShoppingOrderQueryForeignToMerchantParam param) {
		Page<ShoppingOrderExtendBO> shoppingOrderExtendQueryBOPage = shoppingOrderService.selectPageByMerchantId(merchantId, param);
		Page<ShoppingOrderQueryToMerchantDTO> shoppingOrderExtendQueryDTOPage = ShoppingOrderExtendConverter.convertShoppingOrderQueryToMerchantDTOPage(shoppingOrderExtendQueryBOPage);
		return successCreated(shoppingOrderExtendQueryDTOPage);
	}

	/**
	 * 商家发货填写物流信息 并修改购物订单以及购物订单项的状态为待收货
	 * 
	 * @param id
	 *            购物订单id
	 * @param merchantId
	 *            商家id
	 * @param param
	 *            物流信息参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "fillLogisticsInformation/{id}", method = RequestMethod.PUT)
	public Result fillLogisticsInformation(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId, @RequestBody ShoppingOrderLogisticsInformationParam param) {
		try {
			shoppingOrderService.fillLogisticsInformation(id, merchantId, param);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		} catch (CanNotFillInShippingLogisticsException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.CAN_NOT_FILL_IN_SHIPPING_LOGISTICS, e.getMessage());
		}
		return successCreated();
	}

	/**
	 * 根据查询参数分页查询退款记录 购物订单 购物订单项 退款详情关联查询 To Merchant
	 * 
	 * @param merchantId
	 *            商家id
	 * @param param
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "selectRefundPageByMerchantId/{merchantId}", method = RequestMethod.POST)
	public Result<Page<ShoppingOrderItemRefundForMerchantDTO>> selectRefundPageByMerchantId(@PathVariable("merchantId") Long merchantId, @RequestBody ShoppingRefundQueryForeignParam param) {
		Page<ShoppingOrderItemExtendBO> page = shoppingOrderItemService.selectRefundPageByMerchantId(merchantId, param);
		return successCreated(ShoppingOrderItemExtendConverter.convertShoppingOrderItemRefundForMerchantDTOPage(page));
	}

	/*********************************************************
	 * Operator Api
	 ******************************************************/
	/**
	 * 根据查询参数分页查询
	 * 
	 * @param param
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "selectPage", method = RequestMethod.POST)
	public Result<Page<ShoppingOrderQueryToOperatorDTO>> selectPage(@RequestBody ShoppingOrderQueryForeignToOperatorParam param) {
		Page<ShoppingOrderExtendBO> shoppingOrderExtendQueryBOPage = shoppingOrderService.selectPage(param);
		Page<ShoppingOrderQueryToOperatorDTO> shoppingOrderQueryToOperatorDTOPage = new Page<ShoppingOrderQueryToOperatorDTO>();
		shoppingOrderQueryToOperatorDTOPage.setCurrentPage(shoppingOrderExtendQueryBOPage.getCurrentPage());
		shoppingOrderQueryToOperatorDTOPage.setTotalCount(shoppingOrderExtendQueryBOPage.getTotalCount());
		shoppingOrderQueryToOperatorDTOPage.setRecords(ShoppingOrderExtendConverter.convertShoppingOrderQueryToOperatorDTOList(shoppingOrderExtendQueryBOPage.getRecords()));
		return successCreated(shoppingOrderQueryToOperatorDTOPage);
	}

	/**
	 * 更新订单信息
	 * 
	 * @param param
	 *            查询参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "updateInformation/{id}", method = RequestMethod.PUT)
	public Result updateInformation(@PathVariable Long id, @RequestBody ShoppingOrderUpdateInfomationParam param) {
		try {
			shoppingOrderService.updateInformation(id, param);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		}
		return successCreated();
	}

	/**
	 * 查询各种订单状态的数量 To Merchant
	 * 
	 * @param merchantId
	 *            商家id
	 * @return
	 * @author Sunny
	 */
	@RequestMapping(value = "numberOfOrderStartusByMerchant/{merchantId}", method = RequestMethod.GET)
	public Result<ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO> numberOfOrderStartusByMerchant(@PathVariable("merchantId") Long merchantId) {
		ShoppingOrderNumberOfOrderStatusForMerchantBO shoppingOrderNumberOfOrderStatusForMerchantBO = shoppingOrderService.numberOfOrderStartusByMerchant(merchantId);
		return successGet(ShoppingOrderConverter.convert(shoppingOrderNumberOfOrderStatusForMerchantBO));
	}

	/*********************************************************
	 * Operator
	 ******************************************************/

	/**
	 * 根据查询参数分页查询退款记录 购物订单 购物订单项 退款详情关联查询 To Operator
	 * 
	 * @param param
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "selectRefundPage", method = RequestMethod.POST)
	public Result<Page<ShoppingOrderItemRefundForOperatorDTO>> selectRefundPage(@RequestBody ShoppingRefundQueryForeignParam param) {
		Page<ShoppingOrderItemExtendBO> page = shoppingOrderItemService.selectRefundPage(param);
		return successCreated(ShoppingOrderItemExtendConverter.convertShoppingOrderItemRefundForOperatorDTOPage(page));
	}

	/**
	 * 取消购物订单
	 * 
	 * @param id
	 *            购物订单id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "cancelOrder/{id}", method = RequestMethod.PUT)
	public Result cancelOrder(@PathVariable("id") Long id) {
		try {
			shoppingOrderService.cancelOrder(null, id);
		} catch (DataNotExistException e) {
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		} catch (OrderNotCanceledException e) {
			return successCreated(ResultCode.ORDER_NOT_CANCELED, e.getMessage());
		}
		return successCreated();
	}

	/*********************************************************
	 * Common
	 ******************************************************/
	
	/**
	 * 根据id查询订单详情
	 * 不再集成物流信息
	 * 
	 * @param id 订单id
	 * @param memberId 会员id
	 * @param merchantId 商家id
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月6日
	 */
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderDetailDTO> detail(@PathVariable("id") Long id, @RequestParam(value = "memberId", required = false) Long memberId, @RequestParam(value = "merchantId", required = false) Long merchantId) {
		ShoppingOrderExtendBO shoppingOrderExtendDetailBO = null;
		try {
			shoppingOrderExtendDetailBO = shoppingOrderService.get(id, memberId, merchantId);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successGet(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successGet(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		ShoppingOrderDetailDTO shoppingOrderDetailDTO = ShoppingOrderExtendConverter.convert(shoppingOrderExtendDetailBO);
		// 倒计时在服务端放入
		Long countdown = null;
		switch (shoppingOrderDetailDTO.getOrderStatus()) {
		case PENDING_PAYMENT:
			// 1.自动取消订单
		    Date automaticCancelOrderTo = null;
		    // 判断订单是否是抢购订单
		    if (shoppingOrderExtendDetailBO.getActivityId() == null) {
    			String automaticCancelOrder = propertyService.getByName(PropertyNameConstant.AUTOMATIC_CANCEL_ORDER);
    			automaticCancelOrderTo = DateUtil.add(shoppingOrderDetailDTO.getGmtCreate(), Integer.valueOf(automaticCancelOrder), Calendar.DAY_OF_YEAR);
		    } else {
		        automaticCancelOrderTo = DateUtil.add(shoppingOrderDetailDTO.getGmtCreate(), Integer.valueOf(PropertyConstant.SECKILL_ACTIVITY_CANCEL_ORDER_TIME), Calendar.MINUTE);
		    }
			countdown = DateUtil.interval(new Date(), automaticCancelOrderTo, Calendar.MILLISECOND);
			break;
		case TO_BE_RECEIVED:
			// 2.自动确认收货
			String automaticReceipt = propertyService.getByName(PropertyNameConstant.AUTOMATIC_RECEIPT);

			Date automaticReceiptTo = DateUtil.add(shoppingOrderDetailDTO.getGmtTransport(), Integer.valueOf(automaticReceipt), Calendar.DAY_OF_YEAR);

			countdown = DateUtil.interval(new Date(), automaticReceiptTo, Calendar.MILLISECOND);
			break;
		default:
			break;
		}
		shoppingOrderDetailDTO.setCountdown(countdown);
		return successGet(shoppingOrderDetailDTO);
	}
	
	/**
	 * 根据id查询订单详情
	 * 
	 * @deprecated 物流信息返回太慢，不再集成物流信息，使用独立的物流信息查询接口
	 * 
	 * @see #detail(Long, Long, Long)
	 * @param id 订单id
	 * @param memberId 会员id
	 * @param merchantId 商家id
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月6日
	 */
	@Deprecated
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderExtendDetailDTO> get(@PathVariable("id") Long id, @RequestParam(value = "memberId", required = false) Long memberId, @RequestParam(value = "merchantId", required = false) Long merchantId) {
		ShoppingOrderExtendBO shoppingOrderExtendDetailBO = null;
		try {
			shoppingOrderExtendDetailBO = shoppingOrderService.get(id, memberId, merchantId);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successGet(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successGet(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		// 如果快递公司编码和物流编号为空.不查询物流
		ExpressInquiriesDetailBO expressInquiriesDetailBO = null;
		if (StringUtils.isNotBlank(shoppingOrderExtendDetailBO.getExpressCompanyCode()) && StringUtils.isNotBlank(shoppingOrderExtendDetailBO.getWaybillNum())) {
			expressInquiriesDetailBO = expressStrategy.inquiries(shoppingOrderExtendDetailBO.getExpressCompanyCode(), shoppingOrderExtendDetailBO.getWaybillNum());
		}
		ShoppingOrderExtendDetailDTO shoppingOrderExtendDetailDTO = ShoppingOrderExtendConverter.convert(shoppingOrderExtendDetailBO, expressInquiriesDetailBO);
		// 倒计时在服务端放入
		Long countdown = null;
		switch (shoppingOrderExtendDetailDTO.getOrderStatus()) {
		case PENDING_PAYMENT:
			// 1.自动取消订单
			String automaticCancelOrder = propertyService.getByName(PropertyNameConstant.AUTOMATIC_CANCEL_ORDER);

			Date automaticCancelOrderTo = DateUtil.add(shoppingOrderExtendDetailDTO.getGmtCreate(), Integer.valueOf(automaticCancelOrder), Calendar.DAY_OF_YEAR);

			countdown = DateUtil.interval(new Date(), automaticCancelOrderTo, Calendar.MILLISECOND);
			break;
		case TO_BE_RECEIVED:
			// 2.自动确认收货
			String automaticReceipt = propertyService.getByName(PropertyNameConstant.AUTOMATIC_RECEIPT);

			Date automaticReceiptTo = DateUtil.add(shoppingOrderExtendDetailDTO.getGmtTransport(), Integer.valueOf(automaticReceipt), Calendar.DAY_OF_YEAR);

			countdown = DateUtil.interval(new Date(), automaticReceiptTo, Calendar.MILLISECOND);
			break;
		default:
			break;
		}
		shoppingOrderExtendDetailDTO.setCountdown(countdown);
		return successGet(shoppingOrderExtendDetailDTO);
	}
	
	/**
	 * 根据id查询订单物流信息
	 * @deprecated 物流信息返回太慢，不再集成物流信息，使用独立的物流信息查询接口
	 * @see #expressInfo(Long, Long, Long)
	 * @param id
	 *            购物订单id
	 * @param memberId
	 *            会员id
	 * @param merchantId
	 *            商家id
	 * @return
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月10日
	 */
	@Deprecated
	@RequestMapping(value = "getExpressInfo/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderExpressDTO> getExpressInfo(@PathVariable("id") Long id, @RequestParam(name = "memberId", required = false) Long memeberId, @RequestParam(name = "merchantId", required = false) Long merchantId) {
		ShoppingOrderBO shoppingOrderBO = null;
		try {
			shoppingOrderBO = shoppingOrderService.getShoppingOrder(id, memeberId, merchantId);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successGet(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successGet(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		// 如果快递公司编码和物流编号为空.不查询物流
		ExpressInquiriesDetailBO expressInquiriesDetailBO = null;
		if (StringUtils.isNotBlank(shoppingOrderBO.getExpressCompanyCode()) && StringUtils.isNotBlank(shoppingOrderBO.getWaybillNum())) {
			expressInquiriesDetailBO = expressStrategy.inquiries(shoppingOrderBO.getExpressCompanyCode(), shoppingOrderBO.getWaybillNum());
		}

		return successGet(ShoppingOrderConverter.covert(shoppingOrderBO, expressInquiriesDetailBO));
	}
	
	/**
	 * 根据id查询订单物流信息
	 * 
	 * @param id
	 *            购物订单id
	 * @param memberId
	 *            会员id
	 * @param merchantId
	 *            商家id
	 * @return
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月10日
	 */
	@RequestMapping(value = "expressInfo/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderExpressInfoDTO> expressInfo(@PathVariable("id") Long id, @RequestParam(name = "memberId", required = false) Long memeberId, @RequestParam(name = "merchantId", required = false) Long merchantId) {
		ShoppingOrderExtendBO shoppingOrderExtendBO = null;
		try {
			shoppingOrderExtendBO = shoppingOrderService.get(id, memeberId, merchantId);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successGet(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successGet(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		ShoppingOrderExpressInfoDTO rtn = ShoppingOrderConverter.covert(shoppingOrderExtendBO);
		return successGet(rtn);
	}
	
	/**
	 * 第三方支付时获取订单原始总金额，用于调用第三方支付平台
	 * 
	 * @param orderIds
	 * @return
	 * @author Yangqh
	 */
	@RequestMapping(value = "selectOrderMoney", method = RequestMethod.GET)
	public Result<ShoppingOrderMoneyDTO> selectOrderMoney(@RequestParam String orderIds) {
		ShoppingOrderMoneyBO shoppingOrderMoneyBO = null;
		try {
			shoppingOrderMoneyBO = shoppingOrderService.selectOrderMoney(orderIds);
		} catch (TheOrderIsBeingProcessedException e) {
			logger.warn(e.getMessage(), e);
			return successGet(ResultCode.THE_ORDER_IS_BEING_PROCESSED);
		} catch (OrderCreationFailedException e) {
			logger.warn(e.getMessage(), e);
			return successGet(ResultCode.ORDER_CREATION_FAILED);
		}
		ShoppingOrderMoneyDTO shoppingOrderMoneyDTO = new ShoppingOrderMoneyDTO();
		shoppingOrderMoneyDTO.setOrderTotalPrice(shoppingOrderMoneyBO.getOrderTotalPrice());
		shoppingOrderMoneyDTO.setActivity(shoppingOrderMoneyBO.isActivity());
		shoppingOrderMoneyDTO.setOrderNum(shoppingOrderMoneyBO.getOrderNum());
		shoppingOrderMoneyDTO.setDeductionPay(shoppingOrderMoneyBO.isDeductionPay());
		return successGet(shoppingOrderMoneyDTO);
	}
	
	/**
	 * 用于第三方回调获取订单总金额
	 * 
	 * @param orderIds
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月29日
	 */
	@RequestMapping(value = "orderMoneyForNotify", method = RequestMethod.GET)
	public Result<ShoppingOrderMoneyDTO> orderMoneyForNotify(@RequestParam String orderIds) {
		ShoppingOrderMoneyBO shoppingOrderMoneyBO = shoppingOrderService.orderMoneyForNotify(orderIds);
		ShoppingOrderMoneyDTO shoppingOrderMoneyDTO = new ShoppingOrderMoneyDTO();
		shoppingOrderMoneyDTO.setOrderTotalPrice(shoppingOrderMoneyBO.getOrderTotalPrice());
		return successGet(shoppingOrderMoneyDTO);
	}

	/**
	 * 获取商品评价状态
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "getOrderCommentStatus/{shoppingOrderItemId}", method = RequestMethod.GET)
	public Result<CommentOrderDTO> getOrderCommentStatus(@PathVariable("shoppingOrderItemId") Long shoppingOrderItemId) {
		// 查询订单商品评价状态
		ShoppingOrderItemBO shoppingOrderItemBO = shoppingOrderItemService.get(shoppingOrderItemId);
		CommentOrderDTO commentOrderDTO = ShoppingOrderItemConverter.coverCommentStatusDTO(shoppingOrderItemBO);
		return successGet(commentOrderDTO);
	}

	/**
	 * 查询商家是否有正在进行中的订单
	 * 
	 * @param merchantId
	 *            商家id
	 * @return
	 * @author Sunny
	 */
	@RequestMapping(value = "isNoOnGoingOrder/{merchantId}", method = RequestMethod.GET)
	public Result<ShoppingOrderIsNoOnGoingOrderDTO> isNoOnGoingOrder(@PathVariable("merchantId") Long merchantId) {
		// 查询订单商品评价状态
		ShoppingOrderIsNoOnGoingOrderBO shoppingOrderIsNoOnGoingOrderBO = shoppingOrderService.isNoOnGoingOrder(merchantId);
		return successGet(ShoppingOrderConverter.convert(shoppingOrderIsNoOnGoingOrderBO));
	}

	/**
	 * 查询各种订单状态的数量
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 * @author Sunny
	 */
	@RequestMapping(value = "numberOfOrderStartus/{memberId}", method = RequestMethod.GET)
	public Result<ShoppingOrderNumberOfOrderStatusDTO> numberOfOrderStartus(@PathVariable("memberId") Long memberId) {
		ShoppingOrderNumberOfOrderStatusBO shoppingOrderNumberOfOrderStatusBO = shoppingOrderService.numberOfOrderStartus(memberId);
		return successGet(ShoppingOrderConverter.convert(shoppingOrderNumberOfOrderStatusBO));
	}

	/**
	 * 查询已完成但是未计算提成的购物订单
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 * @author Sunny
	 */
	@RequestMapping(value = "commissionShoppingOrder", method = RequestMethod.GET)
	public Result<List<ShoppingOrderCommissionDTO>> commissionShoppingOrder(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
		List<ShoppingOrderBO> shoppingOrderBOList = shoppingOrderService.commissionShoppingOrder(offset, pageSize);
		return successGet(ShoppingOrderConverter.convertShoppingOrderCommissionDTOList(shoppingOrderBOList));
	}

	/**
	 * 查询已完成但是未计算提成的购物订单
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 * @author Sunny
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "updateCommissionStatus", method = RequestMethod.PUT)
	public Result updateCommissionStatus(@RequestParam("ids") List<Long> ids) {
		shoppingOrderService.updateCommissionStatus(ids);
		return successCreated();
	}

	/**
	 * 统计商家的交易数据
	 * 
	 * @param param
	 * @return
	 * @author Sunny
	 */
	@RequestMapping(value = "selectByTransactionData", method = RequestMethod.PUT)
	public Result<ReportRiseRateDTO> selectByTransactionData(@RequestBody ReportDataParam param) {
		ReportRiseRateDTO reportRiseRateDTO = shoppingOrderService.selectByTransactionData(param);
		return successCreated(reportRiseRateDTO);
	}

	/**
	 * 粉丝数据-消费转化
	 * 
	 * @param dparam
	 * @return
	 * @author Sunny
	 */
	@RequestMapping(value = "fansSaleTransform", method = RequestMethod.PUT)
	public Result<List<ReportRiseRerouceDTO>> fansSaleTransform(@RequestBody ReportDataParam dparam) {
		List<ReportRiseRerouceDTO> reportRiseRerouceDTOList = shoppingOrderService.fansSaleTransform(dparam);
		return successCreated(reportRiseRerouceDTOList);
	}

	/**
	 * 获取订单中第一个商品名称
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getOrderItemProductName/{id}", method = RequestMethod.GET)
	public Result<String> getOrderItemProductName(@PathVariable("id") String id) {
		String productName = shoppingOrderItemService.getOrderItemProductName(Long.parseLong(id));
		return successGet(productName);
	}

	/**
	 * 根据订单ID 查询所有订单积分抵扣金额总数
	 * @param orderIds
	 * @return
	 */
	@RequestMapping(value = "selectDeductionPointsAmount", method = RequestMethod.GET)
	public Result<BigDecimal> selectDeductionPointsAmount(@RequestParam("orderIds") String orderIds) {
		BigDecimal totalAmount = shoppingOrderService.selectDeductionPointsAmount(orderIds);
		return successGet(totalAmount);
	}
}
