package com.lawu.eshop.order.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.order.constants.CommissionStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendQueryDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToMerchantDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToOperatorDTO;
import com.lawu.eshop.order.srv.bo.ExpressInquiriesDetailBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderExtendDO;
import com.lawu.framework.core.page.Page;

/**
 *
 * 购物订单扩展转换器
 *
 * @author Sunny
 * @date 2017/04/06
 */
public class ShoppingOrderExtendConverter {
	
	/**
	 * 隐藏默认的构造器
	 */
	private ShoppingOrderExtendConverter() {
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * ShoppingOrderExtendDetailBO转换
	 * 
	 * @param shoppingOrderExtendDO
	 * @return
	 */
	public static ShoppingOrderExtendBO convertShoppingOrderExtendDetailBO(ShoppingOrderExtendDO shoppingOrderExtendDO) {
		ShoppingOrderExtendBO rtn = null;
		if (shoppingOrderExtendDO == null) {
			return rtn;
		}

		rtn = new ShoppingOrderExtendBO();
		
		rtn.setId(shoppingOrderExtendDO.getId());
		rtn.setMemberId(shoppingOrderExtendDO.getMemberId());
		rtn.setMemberNum(shoppingOrderExtendDO.getMemberNum());
		rtn.setMerchantId(shoppingOrderExtendDO.getMerchantId());
		rtn.setMerchantStoreId(shoppingOrderExtendDO.getMerchantStoreId());
		rtn.setMerchantStoreRegionPath(shoppingOrderExtendDO.getMerchantStoreRegionPath());
		rtn.setMerchantNum(shoppingOrderExtendDO.getMerchantNum());
		rtn.setMerchantName(shoppingOrderExtendDO.getMerchantName());
		rtn.setConsigneeName(shoppingOrderExtendDO.getConsigneeName());
		rtn.setConsigneeAddress(shoppingOrderExtendDO.getConsigneeAddress());
		rtn.setConsigneeMobile(shoppingOrderExtendDO.getConsigneeMobile());
		rtn.setRemark(shoppingOrderExtendDO.getRemark());
		rtn.setMessage(shoppingOrderExtendDO.getMessage());
		rtn.setFreightPrice(shoppingOrderExtendDO.getFreightPrice());
		rtn.setCommodityTotalPrice(shoppingOrderExtendDO.getCommodityTotalPrice());
		rtn.setOrderTotalPrice(shoppingOrderExtendDO.getOrderTotalPrice());
		rtn.setDeductionPoints(shoppingOrderExtendDO.getDeductionPoints());
		rtn.setDeductionPointsAmount(shoppingOrderExtendDO.getDeductionPointsAmount());
		rtn.setActualAmount(shoppingOrderExtendDO.getActualAmount());
		rtn.setSendTime(shoppingOrderExtendDO.getSendTime());
		rtn.setIsFans(shoppingOrderExtendDO.getIsFans());
		rtn.setIsNoReasonReturn(shoppingOrderExtendDO.getIsNoReasonReturn());
		rtn.setIsAutomaticReceipt(shoppingOrderExtendDO.getIsAutomaticReceipt());
		rtn.setIsDone(shoppingOrderExtendDO.getIsDone());
		rtn.setShoppingCartIdsStr(shoppingOrderExtendDO.getShoppingCartIdsStr());
		rtn.setOrderNum(shoppingOrderExtendDO.getOrderNum());
		rtn.setThirdNumber(shoppingOrderExtendDO.getThirdNumber());
		rtn.setIsNeedsLogistics(shoppingOrderExtendDO.getIsNeedsLogistics());
		rtn.setWaybillNum(shoppingOrderExtendDO.getWaybillNum());
		rtn.setExpressCompanyId(shoppingOrderExtendDO.getExpressCompanyId());
		rtn.setExpressCompanyCode(shoppingOrderExtendDO.getExpressCompanyCode());
		rtn.setExpressCompanyName(shoppingOrderExtendDO.getExpressCompanyName());
		rtn.setGmtCommission(shoppingOrderExtendDO.getGmtCommission());
		rtn.setGmtPayment(shoppingOrderExtendDO.getGmtPayment());
		rtn.setGmtTransport(shoppingOrderExtendDO.getGmtTransport());
		rtn.setGmtTransaction(shoppingOrderExtendDO.getGmtTransaction());
		rtn.setGmtCreate(shoppingOrderExtendDO.getGmtCreate());
		rtn.setGmtModified(shoppingOrderExtendDO.getGmtModified());
		rtn.setActivityId(shoppingOrderExtendDO.getActivityId());
		
		// 转换为枚举类型
		rtn.setStatus(StatusEnum.getEnum(shoppingOrderExtendDO.getStatus()));
		rtn.setOrderStatus(ShoppingOrderStatusEnum.getEnum(shoppingOrderExtendDO.getOrderStatus()));
		rtn.setPaymentMethod(TransactionPayTypeEnum.getEnum(shoppingOrderExtendDO.getPaymentMethod()));
		rtn.setCommissionStatus(CommissionStatusEnum.getEnum(shoppingOrderExtendDO.getCommissionStatus()));
		
		if (shoppingOrderExtendDO.getItems() != null) {
			rtn.setItems(ShoppingOrderItemConverter.convert(shoppingOrderExtendDO.getItems()));
		}
		
		return rtn;
	}
	
	public static List<ShoppingOrderExtendBO> convertShoppingOrderExtendBO(List<ShoppingOrderExtendDO> shoppingOrderExtendDOList) {
		List<ShoppingOrderExtendBO> rtn = null;
		if (shoppingOrderExtendDOList == null || shoppingOrderExtendDOList.isEmpty()) {
			return rtn;
		}
		
		rtn = new ArrayList<>();
		for (ShoppingOrderExtendDO shoppingOrderExtendDO : shoppingOrderExtendDOList) {
			rtn.add(convertShoppingOrderExtendDetailBO(shoppingOrderExtendDO));
		}
		
		return rtn;
	}
	
	/**
	 * ShoppingOrderExtendDetailDTO转换
	 * 
	 * @param shoppingOrderExtendBO
	 * @return
	 */
	public static ShoppingOrderExtendDetailDTO convert(ShoppingOrderExtendBO shoppingOrderExtendBO, ExpressInquiriesDetailBO expressInquiriesDetailBO) {
		ShoppingOrderExtendDetailDTO rtn = null;
		
		if (shoppingOrderExtendBO == null) {
			return rtn;
		}

		rtn = new ShoppingOrderExtendDetailDTO();
		rtn.setId(shoppingOrderExtendBO.getId());
		rtn.setMemberNum(shoppingOrderExtendBO.getMemberNum());
		rtn.setMerchantId(shoppingOrderExtendBO.getMerchantId());
		rtn.setMerchantStoreId(shoppingOrderExtendBO.getMerchantStoreId());
		rtn.setMerchantNum(shoppingOrderExtendBO.getMerchantNum());
		rtn.setMerchantName(shoppingOrderExtendBO.getMerchantName());
		rtn.setConsigneeName(shoppingOrderExtendBO.getConsigneeName());
		rtn.setConsigneeAddress(shoppingOrderExtendBO.getConsigneeAddress());
		rtn.setConsigneeMobile(shoppingOrderExtendBO.getConsigneeMobile());
		rtn.setFreightPrice(shoppingOrderExtendBO.getFreightPrice());
		rtn.setCommodityTotalPrice(shoppingOrderExtendBO.getCommodityTotalPrice());
		rtn.setOrderTotalPrice(shoppingOrderExtendBO.getOrderTotalPrice());
		rtn.setActualAmount(shoppingOrderExtendBO.getActualAmount());
		rtn.setIsNoReasonReturn(shoppingOrderExtendBO.getIsNoReasonReturn());
		rtn.setIsDone(shoppingOrderExtendBO.getIsDone());
		rtn.setOrderNum(shoppingOrderExtendBO.getOrderNum());
		rtn.setIsNeedsLogistics(shoppingOrderExtendBO.getIsNeedsLogistics());
		rtn.setWaybillNum(shoppingOrderExtendBO.getWaybillNum());
		rtn.setExpressCompanyId(shoppingOrderExtendBO.getExpressCompanyId());
		rtn.setExpressCompanyName(shoppingOrderExtendBO.getExpressCompanyName());
		rtn.setGmtPayment(shoppingOrderExtendBO.getGmtPayment());
		rtn.setGmtTransport(shoppingOrderExtendBO.getGmtTransport());
		rtn.setGmtTransaction(shoppingOrderExtendBO.getGmtTransaction());
		rtn.setGmtCreate(shoppingOrderExtendBO.getGmtCreate());
		rtn.setOrderStatus(shoppingOrderExtendBO.getOrderStatus());
		rtn.setPaymentMethod(shoppingOrderExtendBO.getPaymentMethod());
		rtn.setRemark(shoppingOrderExtendBO.getRemark());
		
		int quantity = 0;
		if (shoppingOrderExtendBO.getItems() != null) {
			if (shoppingOrderExtendBO.getItems() != null) {
				rtn.setItems(new ArrayList<>());
				for (ShoppingOrderItemBO item : shoppingOrderExtendBO.getItems()) {
					quantity += item.getQuantity();
					rtn.getItems().add(ShoppingOrderItemConverter.convert(item));
				}
			}
		}
		rtn.setAmountOfGoods(quantity);
		
		// 如果物流信息存在
		if (expressInquiriesDetailBO != null && expressInquiriesDetailBO.getTraces() != null && !expressInquiriesDetailBO.getTraces().isEmpty()) {
			rtn.setState(expressInquiriesDetailBO.getState());
			rtn.setTrace(ExpressConverter.convert(expressInquiriesDetailBO.getTraces().get(0)));
		}
		
		return rtn;
	}
	
	/**
	 * 
	 * @param shoppingOrderExtendBO
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月6日
	 */
	public static ShoppingOrderDetailDTO convert(ShoppingOrderExtendBO shoppingOrderExtendBO) {
		ShoppingOrderDetailDTO rtn = null;
		
		if (shoppingOrderExtendBO == null) {
			return rtn;
		}

		rtn = new ShoppingOrderDetailDTO();
		rtn.setId(shoppingOrderExtendBO.getId());
		rtn.setMemberNum(shoppingOrderExtendBO.getMemberNum());
		rtn.setMerchantId(shoppingOrderExtendBO.getMerchantId());
		rtn.setMerchantStoreId(shoppingOrderExtendBO.getMerchantStoreId());
		rtn.setMerchantNum(shoppingOrderExtendBO.getMerchantNum());
		rtn.setMerchantName(shoppingOrderExtendBO.getMerchantName());
		rtn.setConsigneeName(shoppingOrderExtendBO.getConsigneeName());
		rtn.setConsigneeAddress(shoppingOrderExtendBO.getConsigneeAddress());
		rtn.setConsigneeMobile(shoppingOrderExtendBO.getConsigneeMobile());
		rtn.setFreightPrice(shoppingOrderExtendBO.getFreightPrice());
		rtn.setCommodityTotalPrice(shoppingOrderExtendBO.getCommodityTotalPrice());
		rtn.setDeductionPointsAmount(shoppingOrderExtendBO.getDeductionPointsAmount());
		rtn.setOrderTotalPrice(shoppingOrderExtendBO.getOrderTotalPrice());
		rtn.setActualAmount(shoppingOrderExtendBO.getActualAmount());
		rtn.setIsNoReasonReturn(shoppingOrderExtendBO.getIsNoReasonReturn());
		rtn.setIsDone(shoppingOrderExtendBO.getIsDone());
		rtn.setOrderNum(shoppingOrderExtendBO.getOrderNum());
		rtn.setIsNeedsLogistics(shoppingOrderExtendBO.getIsNeedsLogistics());
		rtn.setWaybillNum(shoppingOrderExtendBO.getWaybillNum());
		rtn.setExpressCompanyId(shoppingOrderExtendBO.getExpressCompanyId());
		rtn.setExpressCompanyName(shoppingOrderExtendBO.getExpressCompanyName());
		rtn.setGmtPayment(shoppingOrderExtendBO.getGmtPayment());
		rtn.setGmtTransport(shoppingOrderExtendBO.getGmtTransport());
		rtn.setGmtTransaction(shoppingOrderExtendBO.getGmtTransaction());
		rtn.setGmtCreate(shoppingOrderExtendBO.getGmtCreate());
		rtn.setOrderStatus(shoppingOrderExtendBO.getOrderStatus());
		rtn.setPaymentMethod(shoppingOrderExtendBO.getPaymentMethod());
		rtn.setRemark(shoppingOrderExtendBO.getRemark());
		
		int quantity = 0;
		if (shoppingOrderExtendBO.getItems() != null) {
			if (shoppingOrderExtendBO.getItems() != null) {
				rtn.setItems(new ArrayList<>());
				for (ShoppingOrderItemBO item : shoppingOrderExtendBO.getItems()) {
					quantity += item.getQuantity();
					rtn.getItems().add(ShoppingOrderItemConverter.convert(item));
				}
			}
		}
		rtn.setAmountOfGoods(quantity);
		
		return rtn;
	}
	
	/**
	 * ShoppingOrderExtendQueryDTO转换
	 * 
	 * @param shoppingOrderExtendBO
	 * @return
	 */
	public static ShoppingOrderExtendQueryDTO convertShoppingOrderExtendQueryDTO(ShoppingOrderExtendBO shoppingOrderExtendBO) {
		ShoppingOrderExtendQueryDTO rtn = null;
		if (shoppingOrderExtendBO == null) {
			return rtn;
		}

		rtn = new ShoppingOrderExtendQueryDTO();
		rtn.setId(shoppingOrderExtendBO.getId());
		rtn.setFreightPrice(shoppingOrderExtendBO.getFreightPrice());
		rtn.setIsDone(shoppingOrderExtendBO.getIsDone());
		rtn.setIsNeedsLogistics(shoppingOrderExtendBO.getIsNeedsLogistics());
		rtn.setIsNoReasonReturn(shoppingOrderExtendBO.getIsNoReasonReturn());
		rtn.setMerchantId(shoppingOrderExtendBO.getMerchantId());
		rtn.setMerchantNum(shoppingOrderExtendBO.getMerchantNum());
		rtn.setMerchantName(shoppingOrderExtendBO.getMerchantName());
		rtn.setMerchantStoreId(shoppingOrderExtendBO.getMerchantStoreId());
		rtn.setOrderStatus(shoppingOrderExtendBO.getOrderStatus());
		rtn.setOrderTotalPrice(shoppingOrderExtendBO.getOrderTotalPrice());
		
		int quantity = 0;
		if (shoppingOrderExtendBO.getItems() != null) {
			rtn.setItems(new ArrayList<>());
			for (ShoppingOrderItemBO item : shoppingOrderExtendBO.getItems()) {
				quantity += item.getQuantity();
				rtn.getItems().add(ShoppingOrderItemConverter.convert(item));
			}
		}
		
		// 添加商品总数量
		rtn.setAmountOfGoods(quantity);
		
		return rtn;
	}
	
	/**
	 * ShoppingOrderExtendQueryDTO List转换
	 * 
	 * @param shoppingOrderExtendBO
	 * @return
	 */
	public static List<ShoppingOrderExtendQueryDTO> convertShoppingOrderExtendQueryDTOList(List<ShoppingOrderExtendBO> shoppingOrderExtendBOList) {
		List<ShoppingOrderExtendQueryDTO> rtn = new ArrayList<>();
		if (shoppingOrderExtendBOList == null || shoppingOrderExtendBOList.isEmpty()) {
			return rtn;
		}
		
		for (ShoppingOrderExtendBO shoppingOrderExtendBO : shoppingOrderExtendBOList) {
			rtn.add(convertShoppingOrderExtendQueryDTO(shoppingOrderExtendBO));
		}
		
		return rtn;
	}
	
	/**
	 * ShoppingOrderExtendQueryDTO Page转换
	 * 
	 * @param shoppingOrderExtendBOPage
	 * @return
	 */
	public static Page<ShoppingOrderExtendQueryDTO> convertShoppingOrderExtendQueryDTOPage(Page<ShoppingOrderExtendBO> shoppingOrderExtendBOPage) {
		Page<ShoppingOrderExtendQueryDTO> shoppingOrderExtendQueryDTOPage = new Page<>();
		shoppingOrderExtendQueryDTOPage.setCurrentPage(shoppingOrderExtendBOPage.getCurrentPage());
		shoppingOrderExtendQueryDTOPage.setTotalCount(shoppingOrderExtendBOPage.getTotalCount());
		shoppingOrderExtendQueryDTOPage.setRecords(convertShoppingOrderExtendQueryDTOList(shoppingOrderExtendBOPage.getRecords()));
		return shoppingOrderExtendQueryDTOPage;
	}
	
	/**
	 * ShoppingOrderQueryToMerchantDTO转换
	 * 
	 * @param shoppingOrderExtendBO
	 * @return
	 */
	public static ShoppingOrderQueryToMerchantDTO convertShoppingOrderQueryToMerchantDTO(ShoppingOrderExtendBO shoppingOrderExtendBO) {
		ShoppingOrderQueryToMerchantDTO rtn = null;
		if (shoppingOrderExtendBO == null) {
			return rtn;
		}

		rtn = new ShoppingOrderQueryToMerchantDTO();
		rtn.setConsigneeName(shoppingOrderExtendBO.getConsigneeName());
		rtn.setGmtCreate(shoppingOrderExtendBO.getGmtCreate());
		rtn.setId(shoppingOrderExtendBO.getId());
		rtn.setOrderNum(shoppingOrderExtendBO.getOrderNum());
		rtn.setOrderStatus(shoppingOrderExtendBO.getOrderStatus());
		
		if (shoppingOrderExtendBO.getItems() != null && !shoppingOrderExtendBO.getItems().isEmpty()) {
			rtn.setProductFeatureImage(shoppingOrderExtendBO.getItems().get(0).getProductFeatureImage());
		}
		
		
		return rtn;
	}
	
	/**
	 * ShoppingOrderQueryToMerchantDTO List转换
	 * 
	 * @param shoppingOrderExtendBO
	 * @return
	 */
	public static List<ShoppingOrderQueryToMerchantDTO> convertShoppingOrderQueryToMerchantDTOList(List<ShoppingOrderExtendBO> shoppingOrderExtendBOList) {
		List<ShoppingOrderQueryToMerchantDTO> rtn = new ArrayList<>();
		if (shoppingOrderExtendBOList == null) {
			return rtn;
		}
		
		for (ShoppingOrderExtendBO shoppingOrderExtendBO : shoppingOrderExtendBOList) {
			rtn.add(convertShoppingOrderQueryToMerchantDTO(shoppingOrderExtendBO));
		}
		
		return rtn;
	}
	
	/**
	 * ShoppingOrderQueryToMerchantDTO List转换
	 * 
	 * @param shoppingOrderExtendBO
	 * @return
	 */
	public static Page<ShoppingOrderQueryToMerchantDTO> convertShoppingOrderQueryToMerchantDTOPage(Page<ShoppingOrderExtendBO> shoppingOrderExtendQueryBOPage) {
		Page<ShoppingOrderQueryToMerchantDTO> rtn = new Page<>();
		rtn.setCurrentPage(shoppingOrderExtendQueryBOPage.getCurrentPage());
		rtn.setTotalCount(shoppingOrderExtendQueryBOPage.getTotalCount());
		rtn.setRecords(ShoppingOrderExtendConverter.convertShoppingOrderQueryToMerchantDTOList(shoppingOrderExtendQueryBOPage.getRecords()));
		return rtn;
	}
	
	/**
	 * ShoppingOrderQueryToOperatorDTO转换
	 * 
	 * @param shoppingOrderExtendBO
	 * @return
	 */
	public static ShoppingOrderQueryToOperatorDTO convertShoppingOrderQueryToOperatorDTO(ShoppingOrderExtendBO shoppingOrderExtendBO) {
		ShoppingOrderQueryToOperatorDTO rtn = null;
		if (shoppingOrderExtendBO == null) {
			return rtn;
		}

		rtn = new ShoppingOrderQueryToOperatorDTO();
		rtn.setConsigneeName(shoppingOrderExtendBO.getConsigneeName());
		rtn.setGmtCreate(shoppingOrderExtendBO.getGmtCreate());
		rtn.setId(shoppingOrderExtendBO.getId());
		rtn.setOrderNum(shoppingOrderExtendBO.getOrderNum());
		rtn.setOrderStatus(shoppingOrderExtendBO.getOrderStatus());
		
		if (shoppingOrderExtendBO.getItems() != null && !shoppingOrderExtendBO.getItems().isEmpty()) {
			rtn.setProductFeatureImage(shoppingOrderExtendBO.getItems().get(0).getProductFeatureImage());
		}
		
		return rtn;
	}
	
	/**
	 * ShoppingOrderQueryToOperatorDTO List转换
	 * 
	 * @param shoppingOrderExtendBO
	 * @return
	 */
	public static List<ShoppingOrderQueryToOperatorDTO> convertShoppingOrderQueryToOperatorDTOList(List<ShoppingOrderExtendBO> shoppingOrderExtendBOList) {
		List<ShoppingOrderQueryToOperatorDTO> rtn = new ArrayList<>();
		if (shoppingOrderExtendBOList == null || shoppingOrderExtendBOList.isEmpty()) {
			return rtn;
		}
		
		for (ShoppingOrderExtendBO shoppingOrderExtendBO : shoppingOrderExtendBOList) {
			rtn.add(convertShoppingOrderQueryToOperatorDTO(shoppingOrderExtendBO));
		}
		
		return rtn;
	}
	
}
