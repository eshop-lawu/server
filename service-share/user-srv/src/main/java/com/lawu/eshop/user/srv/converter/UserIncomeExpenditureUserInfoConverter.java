package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.UserIncomeExpenditureUserInfoDTO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MerchantBO;

/**
 * 
 * 
 * @author Sunny
 * @date 2017年7月4日
 */
public class UserIncomeExpenditureUserInfoConverter {

	/**
	 * MemberBO转换UserIncomeExpenditureUserInfoDTO
	 *
	 * @param addressDO
	 * @return
	 */
	public static UserIncomeExpenditureUserInfoDTO convert(MemberBO memberBO) {
		UserIncomeExpenditureUserInfoDTO rtn = null;
		if (memberBO == null) {
			return rtn;
		}
		rtn = new UserIncomeExpenditureUserInfoDTO();
		rtn.setAccount(memberBO.getAccount());
		rtn.setUserNum(memberBO.getNum());
		return rtn;
	}
	
	/**
	 * MerchantBO转换UserIncomeExpenditureUserInfoDTO
	 *
	 * @param addressDO
	 * @return
	 */
	public static UserIncomeExpenditureUserInfoDTO convert(MerchantBO merchantBO) {
		UserIncomeExpenditureUserInfoDTO rtn = null;
		if (merchantBO == null) {
			return rtn;
		}
		rtn = new UserIncomeExpenditureUserInfoDTO();
		rtn.setAccount(merchantBO.getAccount());
		rtn.setUserNum(merchantBO.getNum());
		return rtn;
	}

}
