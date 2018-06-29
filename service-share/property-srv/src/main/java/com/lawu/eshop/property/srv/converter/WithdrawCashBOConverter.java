package com.lawu.eshop.property.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.property.constants.CashChannelEnum;
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.eshop.property.dto.CashFeeDTO;
import com.lawu.eshop.property.dto.CashFeeParamDTO;
import com.lawu.eshop.property.dto.WithdrawCashStatusDTO;
import com.lawu.eshop.property.srv.bo.CashFeeBO;
import com.lawu.eshop.property.srv.bo.CashFeeParamBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashBO;
import com.lawu.eshop.property.srv.domain.WithdrawCashDO;

/**
 * 用户提现转换
 * @author Sunny
 * @date 2017/04/20
 *
 */
public class WithdrawCashBOConverter {
	
	/**
	 * 隐藏默认的构造方法
	 */
	private WithdrawCashBOConverter() {
		throw new IllegalAccessError("Utility class");
	}
	
	/**
	 * WithdrawCashDO 转 WithdrawCashBO
	 * 
	 * @param withdrawCashDO
	 * @return
	 */
	public static WithdrawCashBO convert(WithdrawCashDO withdrawCashDO) {
		WithdrawCashBO rtn = null;
		
        if (withdrawCashDO == null || withdrawCashDO.getId() == null || withdrawCashDO.getId() <= 0) {
            return rtn;
        }
        
        rtn = new WithdrawCashBO();
        rtn.setAccount(withdrawCashDO.getAccount());
        rtn.setAreaId(withdrawCashDO.getAreaId());
        rtn.setAuditFaildReason(withdrawCashDO.getAuditFaildReason());
        rtn.setAuditUserId(withdrawCashDO.getAuditUserId());
        rtn.setAuditUserName(withdrawCashDO.getAuditUserName());
        rtn.setBusinessBankAccountId(withdrawCashDO.getBusinessBankAccountId());
        rtn.setCashMoney(withdrawCashDO.getCashMoney());
        rtn.setCashNumber(withdrawCashDO.getCashNumber());
        rtn.setCityId(withdrawCashDO.getCityId());
        rtn.setCurrentScale(withdrawCashDO.getCurrentScale());
        rtn.setGmtCreate(withdrawCashDO.getGmtCreate());
        rtn.setGmtModified(withdrawCashDO.getGmtModified());
        rtn.setId(withdrawCashDO.getId());
        rtn.setMoney(withdrawCashDO.getMoney());
        rtn.setName(withdrawCashDO.getName());
        rtn.setProvinceId(withdrawCashDO.getProvinceId());
        rtn.setRegionFullName(withdrawCashDO.getRegionFullName());
        rtn.setRemark(withdrawCashDO.getRemark());
        rtn.setThirdNumber(withdrawCashDO.getThirdNumber());
        rtn.setUserNum(withdrawCashDO.getUserNum());
        
        rtn.setStatus(CashStatusEnum.getEnum(withdrawCashDO.getStatus()));
        rtn.setUserType(UserTypeEnum.getEnum(withdrawCashDO.getUserType()));
        rtn.setChannel(CashChannelEnum.getEnum(withdrawCashDO.getChannel()));
        
        return rtn;
    }
	
	/**
	 * WithdrawCashDO List 转换为 WithdrawCashBO List
	 * 
	 * @param withdrawCashDOList
	 * @return
	 */
	public static List<WithdrawCashBO> convert(List<WithdrawCashDO> withdrawCashDOList) {
		List<WithdrawCashBO> rtn = null;
		
        if (withdrawCashDOList == null || withdrawCashDOList.isEmpty()) {
            return rtn;
        }
        
        rtn = new ArrayList<>();
        
        for (WithdrawCashDO item : withdrawCashDOList) {
        	rtn.add(convert(item));
        }
        
        return rtn;
    }
	
	/**
	 * WithdrawCashBO 转 WithdrawCashStatusDTO
	 * 
	 * @param withdrawCashBO
	 * @return
	 */
	public static WithdrawCashStatusDTO convert(WithdrawCashBO withdrawCashBO) {
		WithdrawCashStatusDTO rtn = null;
		
        if (withdrawCashBO == null || withdrawCashBO.getId() == null || withdrawCashBO.getId() <= 0) {
            return rtn;
        }
        
        rtn = new WithdrawCashStatusDTO();
        rtn.setId(withdrawCashBO.getId());
        rtn.setStatus(withdrawCashBO.getStatus());
        
        return rtn;
    }
	
	/**
	 * WithdrawCashBO List 转换为 WithdrawCashStatusDTO List
	 * 
	 * @param withdrawCashBOList
	 * @return
	 */
	public static List<WithdrawCashStatusDTO> convertWithdrawCashStatusDTOList(List<WithdrawCashBO> withdrawCashBOList) {
		List<WithdrawCashStatusDTO> rtn = null;
		
        if (withdrawCashBOList == null || withdrawCashBOList.isEmpty()) {
            return rtn;
        }
        
        rtn = new ArrayList<>();
        
        for (WithdrawCashBO item : withdrawCashBOList) {
        	rtn.add(convert(item));
        }
        
        return rtn;
    }

    public static CashFeeDTO convert(CashFeeBO cashFeeBO) {
        CashFeeDTO dto = new CashFeeDTO();
        dto.setActualMoney(cashFeeBO.getActualMoney());
        dto.setChargeMoney(cashFeeBO.getChargeMoney());
        dto.setServiceMoney(cashFeeBO.getServiceMoney());
        return dto;
    }

    public static CashFeeParamDTO convert(CashFeeParamBO calculationFeeParam) {
        CashFeeParamDTO rtn = new CashFeeParamDTO();
        rtn.setChargeMoney(calculationFeeParam.getChargeMoney());
        rtn.setScale(calculationFeeParam.getScale());
        return rtn;
    }
}
