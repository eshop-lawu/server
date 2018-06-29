/**
 * 
 */
package com.lawu.eshop.ad.srv.converter;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.lawu.eshop.ad.constants.RedPacketPutWayEnum;
import com.lawu.eshop.ad.constants.UserRedPacketEnum;
import com.lawu.eshop.ad.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.ad.dto.UserRedPacketAddReturnDTO;
import com.lawu.eshop.ad.dto.UserRedPacketDTO;
import com.lawu.eshop.ad.param.UserPacketRefundParam;
import com.lawu.eshop.ad.param.UserRedPacketSaveParam;
import com.lawu.eshop.ad.param.UserRedPacketUpdateParam;
import com.lawu.eshop.ad.srv.bo.UserRedPacketAddReturnBO;
import com.lawu.eshop.ad.srv.bo.UserRedPacketBO;
import com.lawu.eshop.ad.srv.domain.UserRedPacketDO;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.utils.DateUtil;

/**
 * @author lihj
 * @date 2017年8月3日
 */
public class UserRedPacketConverter {
	public static UserRedPacketDO converDO(UserRedPacketSaveParam param) {
		UserRedPacketDO info = new UserRedPacketDO();
		info.setGmtCreate(new Date());
		info.setGmtModified(new Date());
		info.setStatus(UserRedPacketEnum.USER_STATUS_UNPAID.val);
		info.setTotalCount(param.getTotalCount());
		info.setTotalMoney(param.getTotalMoney());
		info.setType(param.getRedPacketPutWayEnum().val);
		info.setUserAccount(param.getUserAccount());
		info.setUserNum(param.getUserNum());
		info.setOrderNum(IdWorkerHelperImpl.generate(BizIdType.USER_REDPACKET));
		info.setNickname(param.getNickname());
		return info;
	}

	public static UserRedPacketBO convertBO(UserRedPacketDO userDO) {
		UserRedPacketBO userBO = new UserRedPacketBO();
		userBO.setGmtCreate(userDO.getGmtCreate());
		userBO.setId(userDO.getId());
		userBO.setUserRedPacketEnum(UserRedPacketEnum.getEnum(userDO.getStatus()));
		userBO.setTotalCount(userDO.getTotalCount());
		userBO.setTotalMoney(userDO.getTotalMoney());
		userBO.setRedPacketPutWayEnum(RedPacketPutWayEnum.getEnum(userDO.getType()));
		userBO.setUserAccount(userDO.getUserAccount());
		userBO.setUserNum(userDO.getUserNum());
		return userBO;
	}

	/**
	 * @param records
	 * @return
	 */
	public static List<UserRedPacketDTO> convertDTOS(List<UserRedPacketBO> records) {
		List<UserRedPacketDTO> list = Lists.newArrayList();
		if (null == records) {
			return list;
		}
		for (UserRedPacketBO bo : records) {
			UserRedPacketDTO dto = coventDTO(bo);
			list.add(dto);
		}
		return list;
	}

	/**
	 * @param bo
	 * @return
	 */
	public static UserRedPacketDTO coventDTO(UserRedPacketBO bo) {
		UserRedPacketDTO dto = new UserRedPacketDTO();
		dto.setGmtCreate(bo.getGmtCreate());
		dto.setGmtCreateStr(DateUtil.getDateFormat(bo.getGmtCreate()));
		dto.setId(bo.getId());
		dto.setRedPacketPutWayEnum(bo.getRedPacketPutWayEnum());
		dto.setTotalCount(bo.getTotalCount());
		dto.setTotalMoney(bo.getTotalMoney());
		dto.setUserRedPacketEnum(bo.getUserRedPacketEnum());
		dto.setTypeStr(RedPacketPutWayEnum.getName(bo.getRedPacketPutWayEnum().val));
		return dto;
	}

	public static ThirdPayCallBackQueryPayOrderDTO convertThirdPay(UserRedPacketDO userRed) {
		ThirdPayCallBackQueryPayOrderDTO dto =new ThirdPayCallBackQueryPayOrderDTO();
		if(userRed != null){
			dto.setActualMoney(userRed.getTotalMoney().doubleValue());
			dto.setOrderNum(userRed.getOrderNum());
		}
		return dto;
	}

	public static UserRedPacketDO convertDO(UserRedPacketUpdateParam param) {
		UserRedPacketDO user =new UserRedPacketDO();
		user.setPayType(param.getPayType());
		user.setThirdNumber(param.getThirdNum() == null ? "" : param.getThirdNum());
		user.setId(param.getRedId());
		user.setStatus(UserRedPacketEnum.USER_STATUS_EFFECTIVE.val);
		user.setGmtModified(new Date());
		return user;
	}

	public static UserPacketRefundParam convertReFund(UserRedPacketDO userRedpacket) {
		UserPacketRefundParam param =new UserPacketRefundParam();
		param.setPayType(userRedpacket.getPayType());
		param.setRedId(userRedpacket.getId());
		param.setThirdNo(userRedpacket.getThirdNumber());
		param.setUserNum(userRedpacket.getUserNum());
		param.setRefundMoney(userRedpacket.getRefundMoney());
		param.setGmtModified(userRedpacket.getGmtModified());
		return param;
	}

	public static UserRedPacketAddReturnDTO convertAddDTO(UserRedPacketAddReturnBO bo) {
		UserRedPacketAddReturnDTO dto =new UserRedPacketAddReturnDTO();
		dto.setId(bo.getId());
		dto.setOrderNum(bo.getOrderNum());
		return dto;
	}

	public static UserRedPacketAddReturnBO convertAddBO(UserRedPacketDO userRed) {
		UserRedPacketAddReturnBO bo = new UserRedPacketAddReturnBO();
		bo.setId(userRed.getId());
		bo.setOrderNum(userRed.getOrderNum());
		return bo;
	}
}
