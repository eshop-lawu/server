package com.lawu.eshop.merchant.api.mock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.property.dto.TransactionDetailInfoMerchantDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.lawu.eshop.merchant.api.service.TransactionDetailService;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.dto.MonthlyBillDTO;
import com.lawu.eshop.property.dto.TransactionDetailToMerchantDTO;
import com.lawu.eshop.property.dto.foreign.TransactionDetailOfMerchantDTO;
import com.lawu.eshop.property.param.TransactionDetailQueryForMerchantParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailMonthlyBillOfMerchantForeignParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailQueryForMerchantForeignParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockTransactionDetailService extends BaseController implements TransactionDetailService {
    @Override
    public Result<Page<TransactionDetailToMerchantDTO>> findPageByUserNumForMerchant(@PathVariable("userNum") String userNum, @RequestBody TransactionDetailQueryForMerchantParam param) {
        TransactionDetailToMerchantDTO dto = new TransactionDetailToMerchantDTO();
        dto.setBizId("10");
        dto.setTransactionType(MerchantTransactionTypeEnum.WITHDRAW);

        List<TransactionDetailToMerchantDTO> list = new ArrayList<>();
        list.add(dto);

        Page<TransactionDetailToMerchantDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<Page<TransactionDetailOfMerchantDTO>> page(String userNum,
            TransactionDetailQueryForMerchantForeignParam param) {
        TransactionDetailOfMerchantDTO dto = new TransactionDetailOfMerchantDTO();
        dto.setTransactionCategory(MerchantTransactionTypeEnum.WITHDRAW.getPriorityCategory());
        List<TransactionDetailOfMerchantDTO> list = new ArrayList<>();
        list.add(dto);
        Page<TransactionDetailOfMerchantDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(10);
        page.setRecords(list);
        return successCreated(page);
    }

    @Override
    public Result<MonthlyBillDTO> monthlyBill(String userNum,
            TransactionDetailMonthlyBillOfMerchantForeignParam param) {
        MonthlyBillDTO rtn = new MonthlyBillDTO();
        rtn.setTotalExpenditure(new BigDecimal(0));
        rtn.setTotalIncome(new BigDecimal(0));
        return null;
    }

    @Override
    public Result<TransactionDetailInfoMerchantDTO> getById(@PathVariable("id") Long id) {
        return null;
    }

}
