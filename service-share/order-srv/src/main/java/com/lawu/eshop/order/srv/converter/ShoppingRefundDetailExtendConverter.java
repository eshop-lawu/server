package com.lawu.eshop.order.srv.converter;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.srv.bo.ShoppingRefundDetailExtendBO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingRefundDetailExtendDO;

/**
 * 购物退货详情转换器
 *
 * @author Sunny
 * @date 2017/04/11
 */
public class ShoppingRefundDetailExtendConverter {
	
	/**
	 * 隐藏默认的构造器
	 */
	private ShoppingRefundDetailExtendConverter() {
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * ShoppingRefundDetailExtendBO转换
	 * 
	 * @param shoppingRefundDetailExtendDO
	 * @return
	 */
	public static ShoppingRefundDetailExtendBO convert(ShoppingRefundDetailExtendDO shoppingRefundDetailExtendDO) {
		ShoppingRefundDetailExtendBO rtn = null;
		if (shoppingRefundDetailExtendDO == null) {
			return rtn;
		}

		rtn = new ShoppingRefundDetailExtendBO();
		rtn.setAmount(shoppingRefundDetailExtendDO.getAmount());
		rtn.setActualAmount(shoppingRefundDetailExtendDO.getActualAmount());
		rtn.setPoint(shoppingRefundDetailExtendDO.getPoint());
		rtn.setConsigneeAddress(shoppingRefundDetailExtendDO.getConsigneeAddress());
		rtn.setConsigneeMobile(shoppingRefundDetailExtendDO.getConsigneeMobile());
		rtn.setConsigneeName(shoppingRefundDetailExtendDO.getConsigneeName());
		rtn.setDescription(shoppingRefundDetailExtendDO.getDescription());
		rtn.setExpressCompanyCode(shoppingRefundDetailExtendDO.getExpressCompanyCode());
		rtn.setExpressCompanyId(shoppingRefundDetailExtendDO.getExpressCompanyId());
		rtn.setExpressCompanyName(shoppingRefundDetailExtendDO.getExpressCompanyName());
		rtn.setGmtCreate(shoppingRefundDetailExtendDO.getGmtCreate());
		rtn.setGmtConfirmed(shoppingRefundDetailExtendDO.getGmtConfirmed());
		rtn.setGmtFill(shoppingRefundDetailExtendDO.getGmtFill());
		rtn.setGmtIntervention(shoppingRefundDetailExtendDO.getGmtIntervention());
		rtn.setGmtModified(shoppingRefundDetailExtendDO.getGmtModified());
		rtn.setGmtRefund(shoppingRefundDetailExtendDO.getGmtRefund());
		rtn.setGmtSubmit(shoppingRefundDetailExtendDO.getGmtSubmit());
		rtn.setId(shoppingRefundDetailExtendDO.getId());
		rtn.setIsAgree(shoppingRefundDetailExtendDO.getIsAgree());
		rtn.setReason(shoppingRefundDetailExtendDO.getReason());
		rtn.setRefusalReasons(shoppingRefundDetailExtendDO.getRefusalReasons());
		rtn.setShoppingOrderItemId(shoppingRefundDetailExtendDO.getShoppingOrderItemId());
		rtn.setVoucherPicture(shoppingRefundDetailExtendDO.getVoucherPicture());
		rtn.setWaybillNum(shoppingRefundDetailExtendDO.getWaybillNum());
		
		rtn.setStatus(StatusEnum.getEnum(shoppingRefundDetailExtendDO.getStatus()));
		rtn.setType(ShoppingRefundTypeEnum.getEnum(shoppingRefundDetailExtendDO.getType()));
		rtn.setShoppingRefundProcessList(ShoppingRefundProcessConverter.convert(shoppingRefundDetailExtendDO.getShoppingRefundProcessList()));
		rtn.setRefuseImages(shoppingRefundDetailExtendDO.getRefuseImages());
		return rtn;
	}

}
