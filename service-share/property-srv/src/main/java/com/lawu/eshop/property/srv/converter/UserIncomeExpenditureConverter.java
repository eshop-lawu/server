package com.lawu.eshop.property.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lawu.eshop.property.constants.ConsumptionTypeEnum;
import com.lawu.eshop.property.dto.UserIncomeExpenditureDTO;
import com.lawu.eshop.property.dto.UserIncomeExpenditureDatailDTO;
import com.lawu.eshop.property.srv.bo.UserIncomeExpenditureBO;
import com.lawu.eshop.property.srv.domain.extend.UserIncomeExpenditureDO;

/**
 * 平台总销量转换
 * 
 * @author Sunny
 * @date 2017/04/20
 *
 */
public class UserIncomeExpenditureConverter {

	/**
	 * 隐藏默认的构造方法
	 */
	private UserIncomeExpenditureConverter() {
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * UserIncomeExpenditureDO转UserIncomeExpenditureBO
	 * 
	 * @param userIncomeExpenditureDO
	 * @return
	 */
	public static UserIncomeExpenditureBO convert(UserIncomeExpenditureDO userIncomeExpenditureDO) {
		UserIncomeExpenditureBO rtn = null;
		if (userIncomeExpenditureDO == null) {
			return rtn;
		}
		rtn = new UserIncomeExpenditureBO();
		rtn.setAmount(userIncomeExpenditureDO.getAmount());
		rtn.setUserNum(userIncomeExpenditureDO.getUserNum());
		rtn.setDirection(ConsumptionTypeEnum.getEnum(userIncomeExpenditureDO.getDirection()));
		return rtn;
	}
	
	/**
	 * <code>List&lt;UserIncomeExpenditureDO&gt;<code>转<code>List&lt;UserIncomeExpenditureBO&gt;<code>
	 * 
	 * @param userIncomeExpenditureDOList
	 * @return
	 */
	public static List<UserIncomeExpenditureBO> convertUserIncomeExpenditureBOList(List<UserIncomeExpenditureDO> userIncomeExpenditureDOList) {
		List<UserIncomeExpenditureBO> rtn = null;
		if (userIncomeExpenditureDOList == null || userIncomeExpenditureDOList.isEmpty()) {
			return rtn;
		}
		rtn = new ArrayList<>();
		for (UserIncomeExpenditureDO userIncomeExpenditureDO : userIncomeExpenditureDOList) {
			rtn.add(convert(userIncomeExpenditureDO));
		}
		return rtn;
	}

	/**
	 * <code>List&lt;UserIncomeExpenditureBO&gt;<code>转<code>List&lt;UserIncomeExpenditureDTO&gt;<code>
	 * 
	 * @param userIncomeExpenditureBOList
	 * @return
	 */
	public static List<UserIncomeExpenditureDTO> convertUserIncomeExpenditureDTOList(List<UserIncomeExpenditureBO> userIncomeExpenditureBOList) {
		List<UserIncomeExpenditureDTO> rtn = new ArrayList<>();
		if (userIncomeExpenditureBOList == null || userIncomeExpenditureBOList.isEmpty()) {
			return rtn;
		}
		
		Map<String, UserIncomeExpenditureDTO> userIncomeExpenditureDTOMap = new HashMap<>();
		for (UserIncomeExpenditureBO userIncomeExpenditureBO : userIncomeExpenditureBOList) {
			UserIncomeExpenditureDTO userIncomeExpenditureDTO = userIncomeExpenditureDTOMap.get(userIncomeExpenditureBO.getUserNum());
			if (userIncomeExpenditureDTO == null) {
				userIncomeExpenditureDTO = new UserIncomeExpenditureDTO();
				userIncomeExpenditureDTO.setIncome(new BigDecimal(0));
				userIncomeExpenditureDTO.setExpenditure(new BigDecimal(0));
				userIncomeExpenditureDTO.setUserNum(userIncomeExpenditureBO.getUserNum());
				userIncomeExpenditureDTOMap.put(userIncomeExpenditureBO.getUserNum(), userIncomeExpenditureDTO);
			}
			switch (userIncomeExpenditureBO.getDirection()) {
				case EXPENDITURE:
					userIncomeExpenditureDTO.setExpenditure(userIncomeExpenditureBO.getAmount());
					break;
				case INCOME:
					userIncomeExpenditureDTO.setIncome(userIncomeExpenditureBO.getAmount());
					break;
				default:
					break;
			}
		}
		rtn = new ArrayList<>(userIncomeExpenditureDTOMap.values());
		return rtn;
	}
	
	/**
	 * <code>List&lt;UserIncomeExpenditureBO&gt;<code>转<code>UserIncomeExpenditureDatailDTO<code>
	 * 
	 * @param userIncomeExpenditureBOList
	 * @return
	 */
	public static UserIncomeExpenditureDatailDTO convertUserIncomeExpenditureDatailDTO(List<UserIncomeExpenditureBO> userIncomeExpenditureBOList) {
		UserIncomeExpenditureDatailDTO rtn = null;
		if (userIncomeExpenditureBOList == null || userIncomeExpenditureBOList.isEmpty()) {
			return rtn;
		}
		rtn = new UserIncomeExpenditureDatailDTO();
		rtn.setUserIncomeExpenditureList(convertUserIncomeExpenditureDTOList(userIncomeExpenditureBOList));
		return rtn;
	}
}
