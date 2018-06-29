package com.lawu.eshop.order.srv.converter;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressInfoDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingRefundDetailDTO;
import com.lawu.eshop.order.srv.bo.ExpressInquiriesDetailBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingRefundDetailBO;
import com.lawu.eshop.order.srv.bo.ShoppingRefundDetailExtendBO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDO;

/**
 * 购物退货详情转换器
 *
 * @author Sunny
 * @date 2017/04/11
 */
public class ShoppingRefundDetailConverter {
	
	/**
	 * 隐藏默认的构造器
	 */
	private ShoppingRefundDetailConverter() {
		throw new IllegalAccessError("Utility class");
	}
	
	/**
	 * ShoppingRefundDetailBO转换
	 * 
	 * @param shoppingRefundDetailDO
	 * @return
	 */
	public static ShoppingRefundDetailBO convert(ShoppingRefundDetailDO shoppingRefundDetailDO) {
		ShoppingRefundDetailBO rtn = null;
		if (shoppingRefundDetailDO == null) {
			return rtn;
		}

		rtn = new ShoppingRefundDetailBO();
		rtn.setAmount(shoppingRefundDetailDO.getAmount());
		rtn.setConsigneeAddress(shoppingRefundDetailDO.getConsigneeAddress());
		rtn.setConsigneeMobile(shoppingRefundDetailDO.getConsigneeMobile());
		rtn.setConsigneeName(shoppingRefundDetailDO.getConsigneeName());
		rtn.setDescription(shoppingRefundDetailDO.getDescription());
		rtn.setExpressCompanyCode(shoppingRefundDetailDO.getExpressCompanyCode());
		rtn.setExpressCompanyId(shoppingRefundDetailDO.getExpressCompanyId());
		rtn.setExpressCompanyName(shoppingRefundDetailDO.getExpressCompanyName());
		rtn.setGmtCreate(shoppingRefundDetailDO.getGmtCreate());
		rtn.setGmtConfirmed(shoppingRefundDetailDO.getGmtConfirmed());
		rtn.setGmtFill(shoppingRefundDetailDO.getGmtFill());
		rtn.setGmtIntervention(shoppingRefundDetailDO.getGmtIntervention());
		rtn.setGmtModified(shoppingRefundDetailDO.getGmtModified());
		rtn.setGmtRefund(shoppingRefundDetailDO.getGmtRefund());
		rtn.setGmtSubmit(shoppingRefundDetailDO.getGmtSubmit());
		rtn.setId(shoppingRefundDetailDO.getId());
		rtn.setIsAgree(shoppingRefundDetailDO.getIsAgree());
		rtn.setReason(shoppingRefundDetailDO.getReason());
		rtn.setRefusalReasons(shoppingRefundDetailDO.getRefusalReasons());
		rtn.setShoppingOrderItemId(shoppingRefundDetailDO.getShoppingOrderItemId());
		rtn.setVoucherPicture(shoppingRefundDetailDO.getVoucherPicture());
		rtn.setWaybillNum(shoppingRefundDetailDO.getWaybillNum());
		
		rtn.setStatus(StatusEnum.getEnum(shoppingRefundDetailDO.getStatus()));
		rtn.setType(ShoppingRefundTypeEnum.getEnum(shoppingRefundDetailDO.getType()));
		
		return rtn;
	}
	
	/**
	 * ShoppingRefundDetailDTO转换
	 * 
	 * @param shoppingOrderItemExtendBO
	 * @return
	 */
	public static ShoppingRefundDetailDTO convert(ShoppingOrderItemExtendBO shoppingOrderItemExtendBO) {
		ShoppingRefundDetailDTO rtn = null;
		
		if (shoppingOrderItemExtendBO == null) {
			return rtn;
		}

		rtn = new ShoppingRefundDetailDTO();
		
		ShoppingRefundDetailExtendBO shoppingRefundDetailExtendBO = shoppingOrderItemExtendBO.getShoppingRefundDetail();
		rtn.setAmount(shoppingRefundDetailExtendBO.getAmount());
		rtn.setActualAmount(shoppingRefundDetailExtendBO.getActualAmount());
		rtn.setPoint(shoppingRefundDetailExtendBO.getPoint());
		rtn.setConsigneeAddress(shoppingRefundDetailExtendBO.getConsigneeAddress());
		rtn.setConsigneeMobile(shoppingRefundDetailExtendBO.getConsigneeMobile());
		rtn.setConsigneeName(shoppingRefundDetailExtendBO.getConsigneeName());
		rtn.setExpressCompanyName(shoppingRefundDetailExtendBO.getExpressCompanyName());
		rtn.setGmtCreate(shoppingRefundDetailExtendBO.getGmtCreate());
		rtn.setGmtConfirmed(shoppingRefundDetailExtendBO.getGmtConfirmed());
		rtn.setGmtFill(shoppingRefundDetailExtendBO.getGmtFill());
		rtn.setGmtIntervention(shoppingRefundDetailExtendBO.getGmtIntervention());
		rtn.setGmtRefund(shoppingRefundDetailExtendBO.getGmtRefund());
		rtn.setGmtSubmit(shoppingRefundDetailExtendBO.getGmtSubmit());
		rtn.setId(shoppingRefundDetailExtendBO.getId());
		rtn.setIsAgree(shoppingRefundDetailExtendBO.getIsAgree());
		rtn.setReason(shoppingRefundDetailExtendBO.getReason());
		rtn.setRefusalReasons(shoppingRefundDetailExtendBO.getRefusalReasons());
		rtn.setVoucherPicture(shoppingRefundDetailExtendBO.getVoucherPicture());
		rtn.setWaybillNum(shoppingRefundDetailExtendBO.getWaybillNum());
		rtn.setType(shoppingRefundDetailExtendBO.getType());
		
		rtn.setShoppingOrderId(shoppingOrderItemExtendBO.getShoppingOrderId());
		rtn.setRefundStatus(shoppingOrderItemExtendBO.getRefundStatus());
		rtn.setDescribe(shoppingOrderItemExtendBO.getShoppingRefundDetail().getDescription());
		rtn.setPaymentMethod(shoppingOrderItemExtendBO.getShoppingOrder().getPaymentMethod());
		rtn.setShoppingRefundProcessList(ShoppingRefundProcessConverter.convertShoppingRefundProcessDTOList(shoppingRefundDetailExtendBO.getShoppingRefundProcessList()));
		rtn.setRefuseImages(shoppingOrderItemExtendBO.getShoppingRefundDetail().getRefuseImages() == null ? "" : shoppingOrderItemExtendBO.getShoppingRefundDetail().getRefuseImages());
		return rtn;
	}
	
    /**
     * ShoppingOrderExpressDTO转换
     * 
     * @param shoppingRefundDetailBO
     * @param expressInquiriesDetailBO
     * @return
     */
    public static ShoppingOrderExpressDTO covert(ShoppingRefundDetailBO shoppingRefundDetailBO, ExpressInquiriesDetailBO expressInquiriesDetailBO) {
    	ShoppingOrderExpressDTO rtn = null;
		if(shoppingRefundDetailBO == null){
			return rtn;
		}
		
		rtn = new ShoppingOrderExpressDTO();
		rtn.setExpressCompanyName(shoppingRefundDetailBO.getExpressCompanyName());
		rtn.setWaybillNum(shoppingRefundDetailBO.getWaybillNum());
		rtn.setExpressInquiriesDetailDTO(ExpressConverter.convert(expressInquiriesDetailBO));
		
		return rtn;
    }
    
    /**
     * 
     * @param shoppingRefundDetailBO
     * @param expressInquiriesDetailBO
     * @return
     * @author jiangxinjun
     * @date 2017年9月6日
     */
    public static ShoppingOrderExpressInfoDTO covert(ShoppingRefundDetailBO shoppingRefundDetailBO, ShoppingOrderItemBO shoppingOrderItemBO) {
    	ShoppingOrderExpressInfoDTO rtn = null;
		if(shoppingRefundDetailBO == null || shoppingOrderItemBO == null){
			return rtn;
		}
		rtn = new ShoppingOrderExpressInfoDTO();
		rtn.setExpressCompanyId(shoppingRefundDetailBO.getExpressCompanyId());
		rtn.setExpressCompanyName(shoppingRefundDetailBO.getExpressCompanyName());
		rtn.setWaybillNum(shoppingRefundDetailBO.getWaybillNum());
		rtn.setProductFeatureImage(shoppingOrderItemBO.getProductFeatureImage());
		rtn.setTotalQuantity(shoppingOrderItemBO.getQuantity());
		return rtn;
    }

}
