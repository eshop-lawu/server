package com.lawu.eshop.property.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.property.constants.ConsumptionTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.dto.PointDetailBackageDTO;
import com.lawu.eshop.property.dto.PointDetailDTO;
import com.lawu.eshop.property.srv.bo.PointDetailBO;
import com.lawu.eshop.property.srv.domain.PointDetailDO;
import com.lawu.framework.core.page.Page;

/**
 * 积分明细转换器
 *
 * @author Sunny
 * @date 2017/3/30
 */
public class PointDetailConverter {
	
	/**
	 * 隐藏默认的构造函数
	 */
	private PointDetailConverter() {
		throw new IllegalAccessError("Utility class");
	}
	
	public static PointDetailBO convert(PointDetailDO pointDetailDO) {
		PointDetailBO rtn = null;
		if (pointDetailDO == null || pointDetailDO.getId() == null || pointDetailDO.getId() <= 0) {
			return rtn;
		}
		rtn = new PointDetailBO();
		rtn.setId(pointDetailDO.getId());
		rtn.setGmtCreate(pointDetailDO.getGmtCreate());
		rtn.setPoint(pointDetailDO.getPoint());
		rtn.setPointNum(pointDetailDO.getPointNum());
		rtn.setPointType(pointDetailDO.getPointType());
		rtn.setRemark(pointDetailDO.getRemark());
		rtn.setTitle(pointDetailDO.getTitle());
		rtn.setUserNum(pointDetailDO.getUserNum());
		rtn.setBizId(pointDetailDO.getBizId());
		rtn.setDirection(ConsumptionTypeEnum.getEnum(pointDetailDO.getDirection()));
		return rtn;
	}

	public static List<PointDetailBO> convertBOS(List<PointDetailDO> pointDetailDOS) {
		List<PointDetailBO> rtn = null;
		if (pointDetailDOS == null || pointDetailDOS.isEmpty()) {
			return rtn;
		}
		rtn = new ArrayList<>();
		for (PointDetailDO item : pointDetailDOS) {
			rtn.add(convert(item));
		}
		return rtn;
	}

	public static PointDetailDTO convert(PointDetailBO pointDetailBO) {
		PointDetailDTO rtn = null;
		if (pointDetailBO == null) {
			return rtn;
		}
		rtn = new PointDetailDTO();
		rtn.setDirection(pointDetailBO.getDirection());
		rtn.setPoint(pointDetailBO.getPoint());
		rtn.setRemark(pointDetailBO.getRemark());
		rtn.setTitle(pointDetailBO.getTitle());
		rtn.setIntegralDate(pointDetailBO.getGmtCreate());
		return rtn;
	}

	public static List<PointDetailDTO> convertDTOS(List<PointDetailBO> pointDetailBOS) {
		List<PointDetailDTO> rtn = new ArrayList<>();
		if (pointDetailBOS == null || pointDetailBOS.isEmpty()) {
			return rtn;
		}
		for (PointDetailBO item : pointDetailBOS) {
			rtn.add(convert(item));
		}
		return rtn;
	}
	
	public static Page<PointDetailDTO> convertDTOPage(Page<PointDetailBO> pointDetailBOPage) {
		Page<PointDetailDTO> rtn = new Page<>();
		rtn.setCurrentPage(pointDetailBOPage.getCurrentPage());
		rtn.setTotalCount(pointDetailBOPage.getTotalCount());
		rtn.setRecords(convertDTOS(pointDetailBOPage.getRecords()));
		return rtn;
	}

	public static Page<PointDetailBackageDTO> convertBackageDTOPage(Page<PointDetailBO> pointDetailBOPage) {
		Page<PointDetailBackageDTO> rtn = new Page<>();
		rtn.setCurrentPage(pointDetailBOPage.getCurrentPage());
		rtn.setTotalCount(pointDetailBOPage.getTotalCount());
		List<PointDetailBackageDTO> pointDetailBackageDTOS = new ArrayList<>();
		if(pointDetailBOPage.getRecords() == null || pointDetailBOPage.getRecords().isEmpty()){
			rtn.setRecords(pointDetailBackageDTOS);
			return rtn;
		}
		for(PointDetailBO pointDetailBO : pointDetailBOPage.getRecords()){
			PointDetailBackageDTO pointDetailBackageDTO = new PointDetailBackageDTO();
			pointDetailBackageDTO.setTitle(pointDetailBO.getTitle());
			pointDetailBackageDTO.setUserNum(pointDetailBO.getUserNum());
			pointDetailBackageDTO.setPoint(pointDetailBO.getPoint());
			pointDetailBackageDTO.setBizId(pointDetailBO.getBizId());
			pointDetailBackageDTO.setGmtCreate(pointDetailBO.getGmtCreate());
			if (MemberTransactionTypeEnum.getEnum(pointDetailBO.getPointType()) == MemberTransactionTypeEnum.BACKAGE || MerchantTransactionTypeEnum.getEnum(pointDetailBO.getPointType()) == MerchantTransactionTypeEnum.BACKAGE) {
				pointDetailBackageDTO.setPayType(TransactionPayTypeEnum.PLAT.getName());
			}
			pointDetailBackageDTOS.add(pointDetailBackageDTO);
		}
		rtn.setRecords(pointDetailBackageDTOS);
		return rtn;
	}

}
