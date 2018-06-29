package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.mall.constants.SuggestionClientType;
import com.lawu.eshop.mall.constants.SuggestionUserType;
import com.lawu.eshop.mall.dto.SuggestionDTO;
import com.lawu.eshop.mall.param.SuggestionParam;
import com.lawu.eshop.mall.srv.bo.SuggestionBO;
import com.lawu.eshop.mall.srv.domain.SuggestionDO;

/**
 *
 * 反馈意见转换器
 *
 * @author Sunny
 * @date 2017/3/24
 */
public class SuggestionConverter {
	
	/**
	 * 隐藏默认的构造函数
	 */
	private SuggestionConverter() {
		throw new IllegalAccessError("Utility class");
	}
	
	/**
	 * BO转换
	 *
	 * @param suggestionDO
	 * @return
	 */
	public static SuggestionBO convert(SuggestionDO suggestionDO) {
		SuggestionBO rtn = null;
		if (suggestionDO == null) {
			return rtn;
		}

		rtn = new SuggestionBO();
		rtn.setContent(suggestionDO.getContent());
		rtn.setGmtCreate(suggestionDO.getGmtCreate());
		rtn.setGmtModified(suggestionDO.getGmtModified());
		rtn.setId(suggestionDO.getId());
		rtn.setUserNum(suggestionDO.getUserNum());
		
		rtn.setClientType(SuggestionClientType.getEnum(suggestionDO.getClientType()));
		rtn.setUserType(SuggestionUserType.getEnum(suggestionDO.getUserType()));
		return rtn;
	}

	public static List<SuggestionBO> convertBOS(List<SuggestionDO> dos) {
		if (dos == null || dos.isEmpty()) {
			return null;
		}

		List<SuggestionBO> bos = new ArrayList<>();
		for (SuggestionDO suggestionDO : dos) {
			bos.add(convert(suggestionDO));
		}

		return bos;
	}

	/**
	 * DTO转换
	 *
	 * @param bo
	 * @return
	 */
	public static SuggestionDTO convert(SuggestionBO bo) {
		SuggestionDTO rtn = null;
		if (bo == null) {
			return null;
		}

		rtn = new SuggestionDTO();
		rtn.setContent(bo.getContent());
		rtn.setGmtCreate(bo.getGmtCreate());
		rtn.setGmtModified(bo.getGmtModified());
		rtn.setId(bo.getId());
		rtn.setUserNum(bo.getUserNum());
		rtn.setClientType(bo.getClientType());
		rtn.setUserType(bo.getUserType());

		return rtn;
	}

	public static List<SuggestionDTO> convertDTOS(List<SuggestionBO> bos) {
		if (bos == null || bos.isEmpty()) {
			return null;
		}

		List<SuggestionDTO> dtos = new ArrayList<>();
		for (SuggestionBO bo : bos) {
			dtos.add(convert(bo));
		}

		return dtos;
	}
	
	public static SuggestionDO convert(String userNum, SuggestionParam param) {
		SuggestionDO rtn = null;
		if (param == null) {
			return rtn;
		}

		rtn = new SuggestionDO();
		rtn.setContent(param.getContent());
		rtn.setUserNum(userNum);
		
		// 用户类型，1是商家，2是会员
		if (userNum.startsWith("B")) {
			rtn.setUserType((byte)1);
		}
		
		if (userNum.startsWith("M")) {
			rtn.setUserType((byte)2);
		}
		
		if (param.getClientType() != null) {
			rtn.setClientType(param.getClientType().value);
		}
		
		rtn.setGmtCreate(new Date());
        rtn.setGmtModified(new Date());
		
		return rtn;
	}

}
