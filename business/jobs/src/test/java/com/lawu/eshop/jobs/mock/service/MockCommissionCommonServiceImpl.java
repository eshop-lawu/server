package com.lawu.eshop.jobs.mock.service;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.jobs.service.CommissionCommonService;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockCommissionCommonServiceImpl implements CommissionCommonService {


	@Override
	public List<CommissionInvitersUserDTO> selectHigherLevelInviters(String invitedUserNum, int level, @RequestBody boolean isLevel) {
		List<CommissionInvitersUserDTO> dtos = new ArrayList<>();
		if(invitedUserNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)){
			//推荐E友收益
			CommissionInvitersUserDTO dto = new CommissionInvitersUserDTO();
			dto.setDept(1);
			dto.setFlag(1);
			dto.setLevel(1);
			dto.setUserNum("M0000002");
			dtos.add(dto);
			CommissionInvitersUserDTO dto1 = new CommissionInvitersUserDTO();
			dto1.setDept(2);
			dto1.setFlag(1);
			dto1.setLevel(2);
			dto1.setUserNum("B0000002");
			dtos.add(dto1);
			CommissionInvitersUserDTO dto2 = new CommissionInvitersUserDTO();
			dto2.setDept(3);
			dto2.setFlag(1);
			dto2.setLevel(3);
			dto2.setUserNum("M0000003");
			dtos.add(dto2);
		} else if(invitedUserNum.startsWith(UserCommonConstant.MERCHANT_NUM_TAG)){
			//推荐商家收益
			CommissionInvitersUserDTO dto = new CommissionInvitersUserDTO();
			dto.setDept(1);
			dto.setFlag(2);
			dto.setLevel(4);
			dto.setUserNum("M0000004");
			dtos.add(dto);
			CommissionInvitersUserDTO dto1 = new CommissionInvitersUserDTO();
			dto1.setDept(2);
			dto1.setFlag(2);
			dto1.setLevel(5);
			dto1.setUserNum("B0000003");
			dtos.add(dto1);
			CommissionInvitersUserDTO dto2 = new CommissionInvitersUserDTO();
			dto2.setDept(3);
			dto2.setFlag(2);
			dto2.setLevel(2);
			dto2.setUserNum("M0000005");
			dtos.add(dto2);
		}
		return dtos;
	}
}
