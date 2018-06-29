package com.lawu.eshop.member.api.mock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.property.dto.TransactionDetailInfoMemberDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.lawu.eshop.member.api.service.TransactionDetailService;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.dto.MonthlyBillDTO;
import com.lawu.eshop.property.dto.TransactionDetailToMemberDTO;
import com.lawu.eshop.property.dto.foreign.TransactionDetailOfMemberDTO;
import com.lawu.eshop.property.param.TransactionDetailQueryForMemberParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailMonthlyBillOfMemberForeignParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailQueryForMemberForeignParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockTransactionDetailService extends BaseController implements TransactionDetailService {


	@Override
	public Result<Page<TransactionDetailToMemberDTO>> findPageByUserNumForMember(@PathVariable("userNum") String userNum, @RequestBody TransactionDetailQueryForMemberParam param) {
		TransactionDetailToMemberDTO dto = new TransactionDetailToMemberDTO();
		dto.setBizId("1");
		dto.setTransactionType(MemberTransactionTypeEnum.AD_QZ);
		List<TransactionDetailToMemberDTO> list = new ArrayList<>();
		list.add(dto);
		Page<TransactionDetailToMemberDTO> page = new Page<>();
		page.setCurrentPage(1);
		page.setTotalCount(10);
		page.setRecords(list);
		return successCreated(page);
	}

    @Override
    public Result<Page<TransactionDetailOfMemberDTO>> page(String userNum, TransactionDetailQueryForMemberForeignParam param) {
        TransactionDetailOfMemberDTO dto = new TransactionDetailOfMemberDTO();
        dto.setTransactionCategory(MemberTransactionTypeEnum.AD_QZ.getPriorityCategory());
        List<TransactionDetailOfMemberDTO> list = new ArrayList<>();
        list.add(dto);
        Page<TransactionDetailOfMemberDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<MonthlyBillDTO> monthlyBill(String userNum, TransactionDetailMonthlyBillOfMemberForeignParam param) {
        MonthlyBillDTO rtn = new MonthlyBillDTO();
        rtn.setTotalExpenditure(new BigDecimal(0));
        rtn.setTotalIncome(new BigDecimal(0));
        return successGet(rtn);
    }

    @Override
    public Result<TransactionDetailInfoMemberDTO> getById(@PathVariable("id") Long id) {
        return null;
    }


}
