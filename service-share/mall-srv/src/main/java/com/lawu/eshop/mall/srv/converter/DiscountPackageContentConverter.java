package com.lawu.eshop.mall.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.mall.dto.DiscountPackageContentDTO;
import com.lawu.eshop.mall.dto.DiscountPackageContentForMemberDTO;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentSaveForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentUpdateForeignParam;
import com.lawu.eshop.mall.srv.bo.DiscountPackageContentBO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageContentDO;

/**
 *
 * 优惠套餐内容转换器
 *
 * @author Sunny
 * @date 2017/06/26
 */
public class DiscountPackageContentConverter {

	/**
	 * DiscountPackageContentBO转换
	 * 
	 * @param discountPackageContentDO
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static DiscountPackageContentBO convert(DiscountPackageContentDO discountPackageContentDO) {
		DiscountPackageContentBO rtn = null;
		if (discountPackageContentDO == null) {
			return rtn;
		}

		rtn = new DiscountPackageContentBO();
		rtn.setDiscountPackageId(discountPackageContentDO.getDiscountPackageId());
		rtn.setGmtCreate(discountPackageContentDO.getGmtCreate());
		rtn.setGmtModified(discountPackageContentDO.getGmtModified());
		rtn.setId(discountPackageContentDO.getId());
		rtn.setName(discountPackageContentDO.getName());
		rtn.setQuantity(discountPackageContentDO.getQuantity());
		rtn.setStatus(StatusEnum.getEnum(discountPackageContentDO.getStatus()));
		rtn.setSubtotal(discountPackageContentDO.getSubtotal());
		rtn.setUnit(discountPackageContentDO.getUnit());
		rtn.setUnitPrice(discountPackageContentDO.getUnitPrice());
		
		return rtn;
	}

	/**
	 * DiscountPackageContentBOList转换
	 * 
	 * @param discountPackageContentDOList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static List<DiscountPackageContentBO> convertDiscountPackageContentBOList(List<DiscountPackageContentDO> discountPackageContentDOList) {
		List<DiscountPackageContentBO> rtn = null;
		if (discountPackageContentDOList == null || discountPackageContentDOList.isEmpty()) {
			return rtn;
		}

		rtn = new ArrayList<>();
		for (DiscountPackageContentDO discountPackageContentDO : discountPackageContentDOList) {
			rtn.add(convert(discountPackageContentDO));
		}
		return rtn;
	}
	
	/**
	 * DiscountPackageContentDO转换
	 * 
	 * @param discountPackageId
	 * @param discountPackageContentSaveForeignParam
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static DiscountPackageContentDO convert(Long discountPackageId, DiscountPackageContentSaveForeignParam discountPackageContentSaveForeignParam) {
		DiscountPackageContentDO rtn = null;
		if (discountPackageContentSaveForeignParam == null) {
			return rtn;
		}

		rtn = new DiscountPackageContentDO();
		rtn.setDiscountPackageId(discountPackageId);
		rtn.setName(discountPackageContentSaveForeignParam.getName());
		rtn.setQuantity(discountPackageContentSaveForeignParam.getQuantity());
		rtn.setUnit(discountPackageContentSaveForeignParam.getUnit());
		rtn.setUnitPrice(discountPackageContentSaveForeignParam.getUnitPrice());
		rtn.setSubtotal(discountPackageContentSaveForeignParam.getUnitPrice().multiply(new BigDecimal(discountPackageContentSaveForeignParam.getQuantity())));
		
		rtn.setStatus(StatusEnum.VALID.getValue());
		rtn.setGmtCreate(new Date());
		rtn.setGmtModified(new Date());
		
		return rtn;
	}
	
	/**
	 * DiscountPackageContentDO转换
	 * 
	 * @param discountPackageId
	 * @param discountPackageContentSaveParam
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static DiscountPackageContentDO convert(Long discountPackageId, DiscountPackageContentUpdateForeignParam discountPackageContentUpdateForeignParam) {
		DiscountPackageContentDO rtn = null;
		if (discountPackageContentUpdateForeignParam == null) {
			return rtn;
		}

		rtn = new DiscountPackageContentDO();
		rtn.setDiscountPackageId(discountPackageId);
		rtn.setName(discountPackageContentUpdateForeignParam.getName());
		rtn.setQuantity(discountPackageContentUpdateForeignParam.getQuantity());
		rtn.setUnit(discountPackageContentUpdateForeignParam.getUnit());
		rtn.setUnitPrice(discountPackageContentUpdateForeignParam.getUnitPrice());
		rtn.setSubtotal(discountPackageContentUpdateForeignParam.getUnitPrice().multiply(new BigDecimal(discountPackageContentUpdateForeignParam.getQuantity())));
		
		rtn.setStatus(StatusEnum.VALID.getValue());
		rtn.setGmtCreate(new Date());
		rtn.setGmtModified(new Date());
		
		return rtn;
	}
	
	/**
	 * DiscountPackageContentDO转换
	 * 
	 * @param discountPackageContentUpdateForeignParam
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static DiscountPackageContentDO convert(DiscountPackageContentUpdateForeignParam discountPackageContentUpdateForeignParam) {
		DiscountPackageContentDO rtn = null;
		if (discountPackageContentUpdateForeignParam == null) {
			return rtn;
		}

		rtn = new DiscountPackageContentDO();
		rtn.setId(discountPackageContentUpdateForeignParam.getId());
		rtn.setName(discountPackageContentUpdateForeignParam.getName());
		rtn.setQuantity(discountPackageContentUpdateForeignParam.getQuantity());
		rtn.setUnit(discountPackageContentUpdateForeignParam.getUnit());
		rtn.setUnitPrice(discountPackageContentUpdateForeignParam.getUnitPrice());
		rtn.setSubtotal(discountPackageContentUpdateForeignParam.getUnitPrice().multiply(new BigDecimal(discountPackageContentUpdateForeignParam.getQuantity())));
		
		rtn.setGmtModified(new Date());
		return rtn;
	}
	
	/**
	 * DiscountPackageContentDTO转换
	 * 
	 * @param discountPackageContentBO
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static DiscountPackageContentDTO convert(DiscountPackageContentBO discountPackageContentBO) {
		DiscountPackageContentDTO rtn = null;
		if (discountPackageContentBO == null) {
			return rtn;
		}

		rtn = new DiscountPackageContentDTO();
		rtn.setId(discountPackageContentBO.getId());
		rtn.setName(discountPackageContentBO.getName());
		rtn.setQuantity(discountPackageContentBO.getQuantity());
		rtn.setSubtotal(discountPackageContentBO.getSubtotal());
		rtn.setUnit(discountPackageContentBO.getUnit());
		rtn.setUnitPrice(discountPackageContentBO.getUnitPrice());
		return rtn;
	}

	/**
	 * DiscountPackageContentBOList转换
	 * 
	 * @param discountPackageContentDOList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static List<DiscountPackageContentDTO> convertDiscountPackageContentDTOList(List<DiscountPackageContentBO> discountPackageContentBOList) {
		List<DiscountPackageContentDTO> rtn = new ArrayList<>();
		if (discountPackageContentBOList == null || discountPackageContentBOList.isEmpty()) {
			return rtn;
		}

		for (DiscountPackageContentBO discountPackageContentBO : discountPackageContentBOList) {
			rtn.add(convert(discountPackageContentBO));
		}
		return rtn;
	}
	
	/**
	 * DiscountPackageContentForMemberDTO转换
	 * 
	 * @param discountPackageContentBO
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static DiscountPackageContentForMemberDTO convertDiscountPackageContentForMemberDTO(DiscountPackageContentBO discountPackageContentBO) {
		DiscountPackageContentForMemberDTO rtn = null;
		if (discountPackageContentBO == null) {
			return rtn;
		}

		rtn = new DiscountPackageContentForMemberDTO();
		rtn.setName(discountPackageContentBO.getName());
		rtn.setQuantity(discountPackageContentBO.getQuantity());
		rtn.setSubtotal(discountPackageContentBO.getSubtotal());
		rtn.setUnit(discountPackageContentBO.getUnit());
		rtn.setUnitPrice(discountPackageContentBO.getUnitPrice());
		return rtn;
	}

	/**
	 * DiscountPackageContentBOList转换
	 * 
	 * @param discountPackageContentDOList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static List<DiscountPackageContentForMemberDTO> convertDiscountPackageContentForMemberDTOList(List<DiscountPackageContentBO> discountPackageContentBOList) {
		List<DiscountPackageContentForMemberDTO> rtn = new ArrayList<>();
		if (discountPackageContentBOList == null || discountPackageContentBOList.isEmpty()) {
			return rtn;
		}
		for (DiscountPackageContentBO discountPackageContentBO : discountPackageContentBOList) {
			rtn.add(convertDiscountPackageContentForMemberDTO(discountPackageContentBO));
		}
		return rtn;
	}
}
