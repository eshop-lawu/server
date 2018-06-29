package com.lawu.eshop.order.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.dto.ShoppingRefundProcessDTO;
import com.lawu.eshop.order.srv.bo.ShoppingRefundProcessBO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDO;

/**
 * 购物退货流程转换器
 *
 * @author Sunny
 * @date 2017/04/11
 */
public class ShoppingRefundProcessConverter {
	
	/**
	 * 隐藏默认的构造器
	 */
	private ShoppingRefundProcessConverter() {
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * ShoppingRefundProcessBO转换
	 * 
	 * @param shoppingRefundProcessDO
	 * @return
	 */
	public static ShoppingRefundProcessBO convert(ShoppingRefundProcessDO shoppingRefundProcessDO) {
		ShoppingRefundProcessBO rtn = null;
		if (shoppingRefundProcessDO == null) {
			return rtn;
		}

		rtn = new ShoppingRefundProcessBO();
		rtn.setId(shoppingRefundProcessDO.getId());
		rtn.setRefundStatus(RefundStatusEnum.getEnum(shoppingRefundProcessDO.getRefundStatus()));
		rtn.setShoppingRefundDetailId(shoppingRefundProcessDO.getShoppingRefundDetailId());
		rtn.setGmtCreate(shoppingRefundProcessDO.getGmtCreate());
		
		return rtn;
	}
	
	/**
	 * ShoppingRefundProcessBO List转换
	 * 
	 * @param shoppingRefundProcessDOList
	 * @return
	 */
	public static List<ShoppingRefundProcessBO> convert(List<ShoppingRefundProcessDO> shoppingRefundProcessDOList) {
		List<ShoppingRefundProcessBO> rtn = null;
		if (shoppingRefundProcessDOList == null || shoppingRefundProcessDOList.isEmpty()) {
			return rtn;
		}

		rtn = new ArrayList<>();
		
		for (ShoppingRefundProcessDO item : shoppingRefundProcessDOList) {
			rtn.add(convert(item));
		}
		
		return rtn;
	}
	
	/**
	 * ShoppingRefundProcessDTO转换
	 * 
	 * @param shoppingRefundProcessBO
	 * @return
	 */
	public static ShoppingRefundProcessDTO convert(ShoppingRefundProcessBO shoppingRefundProcessBO) {
		ShoppingRefundProcessDTO rtn = null;
		if (shoppingRefundProcessBO == null) {
			return rtn;
		}

		rtn = new ShoppingRefundProcessDTO();
		rtn.setRefundStatus(shoppingRefundProcessBO.getRefundStatus());
		rtn.setGmtCreate(shoppingRefundProcessBO.getGmtCreate());
		
		return rtn;
	}
	
	/**
	 * ShoppingRefundProcessDTO List转换
	 * 
	 * @param shoppingRefundProcessBOList
	 * @return
	 */
	public static List<ShoppingRefundProcessDTO> convertShoppingRefundProcessDTOList(List<ShoppingRefundProcessBO> shoppingRefundProcessBOList) {
		List<ShoppingRefundProcessDTO> rtn = new ArrayList<>();
		if (shoppingRefundProcessBOList  == null || shoppingRefundProcessBOList.isEmpty()) {
			return rtn;
		}
		
		for (ShoppingRefundProcessBO item : shoppingRefundProcessBOList) {
			rtn.add(convert(item));
		}
		
		return rtn;
	}
}
