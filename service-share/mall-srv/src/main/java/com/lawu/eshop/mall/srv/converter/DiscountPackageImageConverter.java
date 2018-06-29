package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.mall.dto.DiscountPackageImageDTO;
import com.lawu.eshop.mall.dto.DiscountPackageImageForMemberDTO;
import com.lawu.eshop.mall.param.DiscountPackageImageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageImageUpdateParam;
import com.lawu.eshop.mall.srv.bo.DiscountPackageImageBO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageImageDO;

/**
 *
 * 优惠套餐图片详情转换器
 *
 * @author Sunny
 * @date 2017/06/26
 */
public class DiscountPackageImageConverter {

	/**
	 * DiscountPackageImageBO转换
	 *
	 * @param discountPackageImageDO
	 * @return
	 */
	public static DiscountPackageImageBO convert(DiscountPackageImageDO discountPackageImageDO) {
		DiscountPackageImageBO rtn = null;
		if (discountPackageImageDO == null || discountPackageImageDO.getId() == null || discountPackageImageDO.getId() <= 0) {
			return rtn;
		}

		rtn = new DiscountPackageImageBO();
		rtn.setDescription(discountPackageImageDO.getDescription());
		rtn.setDiscountPackageId(discountPackageImageDO.getDiscountPackageId());
		rtn.setGmtCreate(discountPackageImageDO.getGmtCreate());
		rtn.setGmtModified(discountPackageImageDO.getGmtModified());
		rtn.setId(discountPackageImageDO.getId());
		rtn.setStatus(StatusEnum.getEnum(discountPackageImageDO.getStatus()));
		rtn.setImage(discountPackageImageDO.getImage());
		
		return rtn;
	}

	/**
	 * DiscountPackageImageBO转换
	 * 
	 * @param discountPackageImageDOList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static List<DiscountPackageImageBO> convertDiscountPackageImageBOList(List<DiscountPackageImageDO> discountPackageImageDOList) {
		List<DiscountPackageImageBO> rtn = null;
		if (discountPackageImageDOList == null || discountPackageImageDOList.isEmpty()) {
			return null;
		}

		rtn = new ArrayList<>();
		for (DiscountPackageImageDO discountPackageImageDO : discountPackageImageDOList) {
			rtn.add(convert(discountPackageImageDO));
		}

		return rtn;
	}

	/**
	 * DiscountPackageImageDO转换
	 *
	 * @param discountPackageId
	 * @param discountPackageImageSaveParam
	 * @return
	 */
	public static DiscountPackageImageDO convert(Long discountPackageId, DiscountPackageImageSaveParam discountPackageImageSaveParam) {
		DiscountPackageImageDO rtn = null;
		if (discountPackageImageSaveParam == null) {
			return rtn;
		}

		rtn = new DiscountPackageImageDO();
		rtn.setDescription(discountPackageImageSaveParam.getDescription());
		rtn.setDiscountPackageId(discountPackageId);
		rtn.setImage(discountPackageImageSaveParam.getImage());
		
		rtn.setStatus(StatusEnum.VALID.getValue());
		rtn.setGmtCreate(new Date());
		rtn.setGmtModified(new Date());
		return rtn;
	}
	
	/**
	 * DiscountPackageImageDO转换
	 *
	 * @param discountPackageId
	 * @param discountPackageImageSaveParam
	 * @return
	 */
	public static DiscountPackageImageDO convert(Long discountPackageId, DiscountPackageImageUpdateParam discountPackageImageUpdateParam) {
		DiscountPackageImageDO rtn = null;
		if (discountPackageImageUpdateParam == null) {
			return rtn;
		}

		rtn = new DiscountPackageImageDO();
		rtn.setDescription(discountPackageImageUpdateParam.getDescription());
		rtn.setDiscountPackageId(discountPackageId);
		rtn.setImage(discountPackageImageUpdateParam.getImage());
		
		rtn.setStatus(StatusEnum.VALID.getValue());
		rtn.setGmtCreate(new Date());
		rtn.setGmtModified(new Date());
		return rtn;
	}
	
	/**
	 * DiscountPackageImageDO转换
	 *
	 * @param discountPackageImageSaveParam
	 * @return
	 */
	public static DiscountPackageImageDO convert(DiscountPackageImageUpdateParam discountPackageImageUpdateParam) {
		DiscountPackageImageDO rtn = null;
		if (discountPackageImageUpdateParam == null) {
			return rtn;
		}

		rtn = new DiscountPackageImageDO();
		rtn.setId(discountPackageImageUpdateParam.getId());
		rtn.setDescription(discountPackageImageUpdateParam.getDescription());
		rtn.setImage(discountPackageImageUpdateParam.getImage());
		
		rtn.setGmtModified(new Date());
		return rtn;
	}
	
	/**
	 * DiscountPackageImageDTO转换
	 *
	 * @param discountPackageImageBO
	 * @return
	 */
	public static DiscountPackageImageDTO convert(DiscountPackageImageBO discountPackageImageBO) {
		DiscountPackageImageDTO rtn = null;
		if (discountPackageImageBO == null) {
			return rtn;
		}

		rtn = new DiscountPackageImageDTO();
		rtn.setDescription(discountPackageImageBO.getDescription());
		rtn.setId(discountPackageImageBO.getId());
		rtn.setImage(discountPackageImageBO.getImage());
		return rtn;
	}

	/**
	 * DiscountPackageImageDTOList转换
	 * 
	 * @param discountPackageImageDOList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static List<DiscountPackageImageDTO> convertDiscountPackageImageDTOList(List<DiscountPackageImageBO> discountPackageImageBOList) {
		List<DiscountPackageImageDTO> rtn = new ArrayList<>();
		if (discountPackageImageBOList == null || discountPackageImageBOList.isEmpty()) {
			return null;
		}

		for (DiscountPackageImageBO discountPackageImageBO : discountPackageImageBOList) {
			rtn.add(convert(discountPackageImageBO));
		}

		return rtn;
	}
	
	/**
	 * DiscountPackageImageDTO转换
	 *
	 * @param discountPackageImageBO
	 * @return
	 */
	public static DiscountPackageImageForMemberDTO convertDiscountPackageImageForMemberDTO(DiscountPackageImageBO discountPackageImageBO) {
		DiscountPackageImageForMemberDTO rtn = null;
		if (discountPackageImageBO == null) {
			return rtn;
		}
		rtn = new DiscountPackageImageForMemberDTO();
		rtn.setDescription(discountPackageImageBO.getDescription());
		rtn.setImage(discountPackageImageBO.getImage());
		return rtn;
	}

	/**
	 * DiscountPackageImageDTOList转换
	 * 
	 * @param discountPackageImageDOList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static List<DiscountPackageImageForMemberDTO> convertDiscountPackageImageForMemberDTOList(List<DiscountPackageImageBO> discountPackageImageBOList) {
		List<DiscountPackageImageForMemberDTO> rtn = new ArrayList<>();
		if (discountPackageImageBOList == null || discountPackageImageBOList.isEmpty()) {
			return null;
		}
		for (DiscountPackageImageBO discountPackageImageBO : discountPackageImageBOList) {
			rtn.add(convertDiscountPackageImageForMemberDTO(discountPackageImageBO));
		}
		return rtn;
	}
}
